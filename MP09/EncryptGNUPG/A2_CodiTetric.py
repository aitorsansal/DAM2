import tkinter as tk
from tkinter import filedialog, messagebox
import gnupg
from PIL import Image, ImageTk
import os

gpg = gnupg.GPG()
def encrypt_data(data, output_file):
    ret = gpg.encrypt(data, symmetric = True, recipients=None, passphrase=os.environ.get('PassWord'))
    if(os.path.exists("users.txt")):
        with open(output_file,'a') as file:
            file.write(str(ret)+"////")
    else:
        with open(output_file,'w') as file:
            file.write(str(ret)+"////")
           
def decrypt_data(input_file):
    lst = []
    if(os.path.exists("users.txt")):
        with open(input_file,'r') as file:
            splited = file.read().split("////") 
            for s in splited:
                lst.append(str(gpg.decrypt(s, passphrase=os.environ.get('PassWord'))).strip())                              
    else: 
        print("l'arxiu no existeix")
    
    return lst
    

# Verificar usuari
def verify_user(username, password):
    list = []
    list = decrypt_data("users.txt")
    if list.__contains__(f"{username};{password}"):
        return True
    return False

# Guardar usuari
def save_user(username, password):
    encrypt_data(f"{username};{password}","users.txt")


def hide_message():
    selectedPath = load_image.file_path
    if not selectedPath:
        print("No file selected")
        return
    
    
    try:
        img = Image.open(selectedPath)
        img = img.convert("RGB")
    except Exception as e:
        print(f"Error opening image: {e}")
        return
    
    
    message = text_area.get("1.0", tk.END).strip()
    message += f"-/-/-by {current_user}" + chr(0)
    binary_message = ''.join(format(ord(char), '08b') for char in message)
    
    
    required_bits = len(binary_message) * 3 
    width, height = img.size
    available_pixels = width * height

    
    if required_bits > available_pixels * 3:
        messagebox("Error: The message is too large to fit in this image.")
        return
    
    # Modify the pixels to hide the message
    message_index = 0
    pixel_count = 0
    for x in range(width):
        for y in range(height):
            if message_index < len(binary_message):
                r, g, b = img.getpixel((x, y))
                

                for color in range(3):
                    if message_index < len(binary_message):
                        bit_value = int(binary_message[message_index])
                        if color == 0:
                            r = (r // 2 * 2) + bit_value
                        elif color == 1:
                            g = (g // 2 * 2) + bit_value
                        elif color == 2:
                            b = (b // 2 * 2) + bit_value
                        message_index += 1
                

                img.putpixel((x, y), (r, g, b))
                pixel_count += 1
            
            if message_index >= len(binary_message):
                break
    
    # Save the modified image
    modified_image_path = 'hidden_message_image.png'
    img.save(modified_image_path)

def getMessageFromImage():
    # Open a file dialog to select an image
    file_path = filedialog.askopenfilename(title="Select an Image")
    
    if file_path:
        try:
            hidden_message = reveal_message(file_path)
            spt = hidden_message.split("-/-/-")
            if(len(spt) > 2):
                for i in range(0, len(spt)-1):
                    hidden_message += spt[i] + "-/-/-"
                hidden_message += "\n"+spt[len(spt)-1]
            else:
                hidden_message = spt[0] + "\n" + spt[1]
            if hidden_message:
                messagebox.showinfo("Hidden Message", hidden_message)
            else:
                messagebox.showwarning("No Message Found", "No hidden message was found in the image.")
        except Exception as e:
            print(e)


def reveal_message(file_path):
    img = Image.open(file_path)
    width,height = img.size
    binary_message = ''
    for x in range(width):
        for y in range(height):
            r,g,b = img.getpixel((x, y))
            for i in range(0,3):
                if i == 0:
                    binary_message += str(r & 1)    
                elif i == 1:
                    binary_message += str(g & 1)    
                elif i == 2:
                    binary_message += str(b & 1)                          


                if len(binary_message) >= 8 and len(binary_message) % 8 == 0:
                    last_byte = binary_message[-8:]  # Last 8 bits
                    if chr(int(last_byte, 2)) == chr(0):
                        return convertToString(binary_message[:-8])
                    
    return convertToString(binary_message)

def convertToString(binaryString):
    chars = []
    for i in range(0, len(binaryString), 8):
        byte = binaryString[i:i + 8]
        if len(byte) < 8:
            continue  # Skip incomplete bytes
        try:
            char = chr(int(byte, 2))
            if char == chr(0):  # Check for the terminator
                break
            chars.append(char)
        except ValueError:
            continue  # Skip invalid bytes

    return ''.join(chars)

# Funció per carregar imatges
def load_image():
    file_path = filedialog.askopenfilename()
    if file_path:
        try:
            img = Image.open(file_path)
            img.thumbnail((250, 250))  # Ajustar la mida de la imatge
            img_tk = ImageTk.PhotoImage(img)
            panel.config(image=img_tk)
            panel.image = img_tk
            panel.pack(pady=10)
            text_area.pack(pady=10)
            hide_message_button.pack(pady=5)
            load_image.file_path = file_path

        except Exception as e:
            messagebox.showerror("Error", f"No s'ha pogut carregar la imatge: {str(e)}")

# Funció de login
def login():
    global logged_in, current_user
    username = username_entry.get()
    password = password_entry.get()
    
    if verify_user(username, password):
        logged_in = True
        current_user = username
        messagebox.showinfo("Èxit", f"Login correcte! Benvingut, {username}.")
        show_welcome_message()
    else:
        messagebox.showerror("Error", "Login incorrecte. Intenta-ho de nou.")
        

# Funció de registre
def register():
    username = username_entry.get()
    password = password_entry.get()
    
    save_user(username, password)
    messagebox.showinfo("SignedIn", "S'ha creat la teva conta correctament. Ja pots iniciar sessió")

# Mostrar missatge de benvinguda i botó de carregar imatge
def show_welcome_message():
    login_frame.pack_forget()  # Amaga el formulari de login
    welcome_label.config(text=f"Hola, {current_user}!")
    welcome_label.pack(pady=10)
    reveal_message_button.pack(pady=10)
    load_image_button.pack(pady=10)

# Configura la finestra principal
app = tk.Tk()
app.title("A2")
app.geometry('500x600')  # Mida de la finestra
app.resizable(False, False)

# Centra la finestra
screen_width = app.winfo_screenwidth()
screen_height = app.winfo_screenheight()
x_coordinate = int((screen_width / 2) - (500 / 2))
y_coordinate = int((screen_height / 2) - (600 / 2))
app.geometry(f"+{x_coordinate}+{y_coordinate}")

# Variables per controlar l'estat del login i l'usuari actual
logged_in = False
current_user = None

# Panell de login
login_frame = tk.Frame(app)
tk.Label(login_frame, text="Usuari:").pack()
username_entry = tk.Entry(login_frame)
username_entry.pack()

tk.Label(login_frame, text="Contrasenya:").pack()
password_entry = tk.Entry(login_frame, show="*")
password_entry.pack()

tk.Button(login_frame, text="Login", command=login).pack(side=tk.LEFT, padx=5)
tk.Button(login_frame, text="Registrar-se", command=register).pack(side=tk.LEFT, padx=5)
login_frame.pack(pady=20)

# Missatge de benvinguda (ocult)
welcome_label = tk.Label(app, text="", font=("Arial", 14))

# Botó per carregar imatges (ocult)
load_image_button = tk.Button(app, text="Carregar Imatge", command=load_image)

#Botó per revelar el missatge (ocult)
reveal_message_button = tk.Button(app, text="Revelar Missatge", command=getMessageFromImage)

# Panell per mostrar la imatge (ocult)
panel = tk.Label(app)

# Text area i botó "Amagar missatge" (ocult)
text_area = tk.Text(app, height=4, width=40)
hide_message_button = tk.Button(app, text="Amagar missatge", command=hide_message)

os.environ['PassWord'] = "totallySavePassPhrase"

app.mainloop()
