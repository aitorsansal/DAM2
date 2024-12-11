// See https://aka.ms/new-console-template for more information

using System.Net.Sockets;
using System.Text;

internal class Program
{
    static void Main(string[] args)
    {
        Console.Write("Introdueix l'adreça IP del servidor: ");
        // Sol·licita la IP del servidor i el port estàndard (12345).
        string serverIp = Console.ReadLine();
        int port = 12345;
        try
        {
            // Crea una connexió TcpClient i inicialitza el flux de comunicació.
            TcpClient client = new TcpClient(serverIp, port);
            Console.WriteLine("Connectat al servidor!");

            // Crea un NetworkStream per comunicar-se amb el servidor.
            NetworkStream stream = client.GetStream();

            // Llegeix missatges entrants en un thread separat.
            Thread readThread = new Thread(() => ReadMessages(stream));
            readThread.Start();

            while (true)
            {
                // Llegeix l'entrada de l'usuari i l'envia al servidor.
                string message = Console.ReadLine();

                if (message == "/exit")
                {
                    // Envia una comanda de desconnexió al servidor.
                    byte[] exitBytes = Encoding.UTF8.GetBytes(message);
                    stream.Write(exitBytes, 0, exitBytes.Length);
                    Console.WriteLine("Desconnectant...");
                    break;
                }

                // Envia el missatge al servidor.
                byte[] messageBytes = Encoding.UTF8.GetBytes(message);
                stream.Write(messageBytes, 0, messageBytes.Length);
            }

            // Tanca la connexió amb el servidor.
            client.Close();
        }
        catch (Exception ex)
        {
            // Gestiona errors com problemes de connexió.
            Console.WriteLine($"Error: {ex.Message}");
        }
    }

    /// <summary>
    /// Llegeix missatges enviats pel servidor i els mostra a la consola.
    /// Funciona en un thread separat per evitar bloquejos.
    /// </summary>
    /// <param name="stream">El NetworkStream que representa la connexió amb el servidor.</param>
    private static void ReadMessages(NetworkStream stream)
    {
        byte[] buffer = new byte[1024];
        int bytesRead;

        try
        {
            // Llegeix contínuament els missatges entrants fins que el servidor es desconnecta.
            while ((bytesRead = stream.Read(buffer, 0, buffer.Length)) != 0)
            {
                // Converteix el missatge rebut de bytes a text i el mostra.
                string message = Encoding.UTF8.GetString(buffer, 0, bytesRead);
                Console.WriteLine(message);
            }
        }
        catch (Exception)
        {
            // Gestiona errors o desconnexió del servidor.
            Console.WriteLine("Desconnectat del servidor.");
        }
    }
}