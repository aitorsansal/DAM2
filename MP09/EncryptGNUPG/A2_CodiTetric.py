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
    load_image_button.pack(pady=20)

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

# Panell per mostrar la imatge (ocult)
panel = tk.Label(app)

# Text area i botó "Amagar missatge" (ocult)
text_area = tk.Text(app, height=4, width=40)
hide_message_button = tk.Button(app, text="Amagar missatge")

os.environ['PassWord'] = "totallySavePassPhrase"

app.mainloop()
