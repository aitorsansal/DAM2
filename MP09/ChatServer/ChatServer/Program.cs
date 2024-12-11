using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

class ChatServer
{

    /// <summary>
    /// Relació entre noms d'usuari i els seus clients connectats.
    /// </summary>
    private static Dictionary<string, TcpClient> userClients = new Dictionary<string, TcpClient>();

    /// <summary>
    /// Relació entre els clients connectats i els seus noms d'usuari.
    /// </summary>
    private static Dictionary<TcpClient, string> clientUsers = new Dictionary<TcpClient, string>();

    /// <summary>
    /// Relació de connexions privades entre dos clients.
    /// </summary>
    private static Dictionary<TcpClient, TcpClient> privateChats = new Dictionary<TcpClient, TcpClient>();

    /// <summary>
    /// Listener que escolta les connexions entrants dels clients.
    /// </summary>
    private static TcpListener listener;

    static void Main(string[] args)
    {
        int port = 12345;
        listener = new TcpListener(IPAddress.Any, port);

        try
        {
            listener.Start();
            Console.WriteLine($"Server is running on port {port}...");

            while (true)
            {
                // Accept a new client connection
                TcpClient client = listener.AcceptTcpClient();
                Console.WriteLine("New client connected.");

                // Start a new thread to handle the client
                Thread clientThread = new Thread(HandleClient);
                clientThread.Start(client);
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error: {ex.Message}");
        }
        finally
        {
            listener.Stop();
        }
    }

    /// <summary>
    /// Gestiona un client connectat, llegint els seus missatges i processant-los.
    /// </summary>
    /// <param name="obj">El client connectat (TcpClient).</param>
    private static void HandleClient(object obj)
    {
        TcpClient client = (TcpClient)obj;
        NetworkStream stream = client.GetStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        try
        {
            while ((bytesRead = stream.Read(buffer, 0, buffer.Length)) != 0)
            {
                // Convert the message to a string
                string message = Encoding.UTF8.GetString(buffer, 0, bytesRead);
                Console.WriteLine($"Received: {message}");

                if (message.StartsWith('/'))
                {
                    // Process command if the message starts with "/"
                    ProcessCommand(message, client);
                }
                else
                {
                    // If not a command, broadcast the message
                    BroadcastMessage(message, client);
                }
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error handling client: {ex.Message}");
        }
        finally
        {
            // Disconnect the client when done
            DisconnectClient(client);
        }
    }

    /// <summary>
    /// Processa una comanda enviada pel client.
    /// </summary>
    /// <param name="command">La comanda enviada.</param>
    /// <param name="client">El client que ha enviat la comanda.</param>
    private static void ProcessCommand(string command, TcpClient client)
    {
        string[] commandParts = command.Split(' ');

        switch (commandParts[0].ToLower())
        {
            case "/user":
                // Command to set or change the username
                if (commandParts.Length > 1)
                {
                    string username = commandParts[1];
                    SetUsername(client, username);
                }
                else
                {
                    SendMessage("Usage: /user <username>", client);
                }
                break;

            case "/open":
                // Command to open a private chat with a target user
                if (commandParts.Length > 1)
                {
                    string targetUsername = commandParts[1];
                    OpenPrivateChat(client, targetUsername);
                }
                else
                {
                    SendMessage("Usage: /open <username>", client);
                }
                break;

            case "/close":
                // Command to close the current private chat
                ClosePrivateChat(client);
                break;

            case "/exit":
                // Command to exit the chat and disconnect
                DisconnectClient(client);
                break;
            case "/getuser":
                SendMessage(GetUsername(client), client);
                break;

            default:
                // Handle unknown commands
                SendMessage("Unknown command. Available commands: /user, /open, /close, /exit, /getuser", client);
                break;
        }
    }

    /// <summary>
    /// Estableix un nom d'usuari per al client.
    /// </summary>
    /// <param name="client">El client que vol establir un nom.</param>
    /// <param name="username">El nom d'usuari a assignar.</param>
    private static void SetUsername(TcpClient client, string username)
    {
        // Check if the username is already taken
        if (userClients.ContainsKey(username))
        {
            SendMessage("Username is already taken. Please choose a different one.", client);
            return;
        }

        // If the client already has a username, we should remove it from the old entry
        if (clientUsers.TryGetValue(client, out var oldUsername))
        {
            SendMessage("You already have a username. Do you want to delete it? (Y/N)", client);
            string response = Console.ReadLine();
            if (response?.ToUpper() == "Y")
                userClients.Remove(oldUsername); // Remove the old username
            else
                return;
        }

        // Assign the new username to the client
        clientUsers[client] = username;
        userClients[username] = client;

        // Send a confirmation message to the client
        SendMessage($"Your username is now set to {username}.", client);
    }

    /// <summary>
    /// Obre una connexió privada entre el client i un altre usuari.
    /// </summary>
    /// <param name="client">El client que sol·licita la connexió.</param>
    /// <param name="targetUsername">El nom d'usuari del destinatari.</param>
    private static void OpenPrivateChat(TcpClient client, string targetUsername)
    {
        // Check if the client is already in a private chat
        if (privateChats.ContainsKey(client))
        {
            SendMessage("You are already in a private chat.", client);
            return;
        }

        // Look for the target username and get their TcpClient
        TcpClient targetClient = null;
        foreach (var kvp in clientUsers)
        {
            if (kvp.Value == targetUsername)
            {
                targetClient = kvp.Key;
                break;
            }
        }

        if (targetClient == null)
        {
            SendMessage("Target user not found or not connected.", client);
            return;
        }

        // Check if the target is already in a private chat
        if (privateChats.ContainsKey(targetClient))
        {
            SendMessage($"The user {targetUsername} is already in a private chat.", client);
            return;
        }

        // Establish the private chat
        privateChats[client] = targetClient;
        privateChats[targetClient] = client;

        SendMessage($"Private chat with {targetUsername} established.", client);
        SendMessage("Private chat opened with you.", targetClient);
    }

    /// <summary>
    /// Tanca la connexió privada del client.
    /// </summary>
    /// <param name="client">El client que sol·licita tancar la connexió.</param>
    private static void ClosePrivateChat(TcpClient client)
    {
        if (!privateChats.ContainsKey(client))
        {
            SendMessage("You are not in a private chat.", client);
            return;
        }

        // Get the target client in the private chat
        TcpClient targetClient = privateChats[client];

        // Remove both clients from the private chats dictionary
        privateChats.Remove(client);
        privateChats.Remove(targetClient);

        // Notify both clients
        SendMessage("Private chat closed.", client);
        SendMessage("Your private chat has been closed.", targetClient);
    }

    /// <summary>
    /// Envia un missatge al client especificat.
    /// </summary>
    /// <param name="message">El missatge a enviar.</param>
    /// <param name="client">El client destinatari.</param>
    private static void SendMessage(string message, TcpClient client)
    {
        try
        {
            // Get the NetworkStream for the client
            NetworkStream stream = client.GetStream();

            // Convert the message to a byte array
            byte[] messageBytes = Encoding.UTF8.GetBytes(message);

            // Write the message to the stream
            stream.Write(messageBytes, 0, messageBytes.Length);
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error sending message to client: {ex.Message}");
        }
    }

    /// <summary>
    /// Elimina un client del servidor quan es desconnecta.
    /// </summary>
    /// <param name="client">El client desconnectat.</param>
    private static void DisconnectClient(TcpClient client)
    {
        // Check if the client is in a private chat and close the chat
        if (privateChats.ContainsKey(client))
        {
            TcpClient targetClient = privateChats[client];
            privateChats.Remove(client);
            privateChats.Remove(targetClient);

            // Notify both clients that the private chat has been closed
            SendMessage("Your private chat has been closed.", client);
            SendMessage("Your private chat has been closed.", targetClient);
        }

        // Remove the client from the user-client and client-user dictionaries
        if (clientUsers.ContainsKey(client))
        {
            string username = clientUsers[client];
            clientUsers.Remove(client);
            userClients.Remove(username);
        }

        // Close the client's connection
        try
        {
            client.GetStream().Close();
            client.Close();
            Console.WriteLine($"Client {client} disconnected.");
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error during disconnection: {ex.Message}");
        }
    }

    /// <summary>
    /// Obté el nom d'usuari associat a un client.
    /// </summary>
    /// <param name="client">El client del qual volem el nom.</param>
    /// <returns>El nom d'usuari o "Desconegut" si no té nom assignat.</returns>
    private static string GetUsername(TcpClient client)
    {
        return clientUsers.GetValueOrDefault(client, "Unknown");
    }

    private static void BroadcastMessage(string message, TcpClient sender)
    {
        // Check if the sender is in a private chat
        // Get the username of the sender (User1)
        string senderUsername = GetUsername(sender);

        // Check if the sender has a private chat partner
        if (privateChats.TryGetValue(sender, out var recipientClient))
        {
            // Get the recipient client (User2)
            string recipientUsername = GetUsername(recipientClient);

            // Format the message
            string formattedMessage = $"[{senderUsername}] : {message}";

            // Send the formatted message to User2
            SendMessage(formattedMessage, recipientClient);
        }
        else
        {
            SendMessage("You are not in a private chat. Use /open <username> to start a chat.", sender);
        }
    }
}
