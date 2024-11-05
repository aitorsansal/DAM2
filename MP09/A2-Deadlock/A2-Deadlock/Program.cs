// See https://aka.ms/new-console-template for more information

using System.Net.Http.Headers;
using System.Reflection.Metadata;
using System.Text;

internal class Program
{
    static readonly Mutex mutexAcc1 = new Mutex(false, "acc1");
    static readonly Mutex mutexAcc2 = new Mutex(false, "acc2");
    static readonly Mutex mutexAcc3 = new Mutex(false, "acc3");
    static readonly Mutex mutexAcc4 = new Mutex(false, "acc4");
    static Random random = new Random();
    static string[] fileNames = ["acc1.bin", "acc2.bin", "acc3.bin", "acc4.bin"];
    
    public static void Main(string[] args)
    {
        CreateFiles();
        int quantityOfThreads = 50;
        Thread[] threads = new Thread[quantityOfThreads];
        
        for (int i = 0; i < quantityOfThreads; i++)
        {
            threads[i] = new Thread(ExecuteTransaction);
            threads[i].Start();
            Thread.Sleep(200);
        }

        foreach (var thread in threads)
        {
            thread.Join();
        }

        foreach (var fileName in fileNames)
        {
            FileStream fs = new FileStream(fileName, FileMode.Open, FileAccess.Read, FileShare.ReadWrite);
            BinaryReader br = new BinaryReader(fs);
            {
                br.BaseStream.Seek(0, SeekOrigin.Begin);
                Console.WriteLine($"{fileName} final amount: {br.ReadInt32()}");
            }
        }
    }



    static void ExecuteTransaction()
    {
        int sender = random.Next(4);
        int quantityToSend = random.Next(1,500);
        int reciver = random.Next(4);
        while(reciver == sender)
            reciver = random.Next(4);
        Console.ForegroundColor = ConsoleColor.Blue;
        Console.WriteLine($"Account {sender+1} is trying to send {quantityToSend} to Account {reciver+1}.");
        try
        {
            if (sender < reciver)
            {
                BlockMutex(sender);
                BlockMutex(reciver);
            }
            else
            {
                BlockMutex(reciver);
                BlockMutex(sender);
            }
            Console.ForegroundColor = ConsoleColor.Red;
            Console.WriteLine($"Account {sender+1} has been blocked with Mutex bc of sending.");
            Console.ForegroundColor = ConsoleColor.Red;
            Console.WriteLine($"Account {reciver+1} has been blocked with Mutex bc of recieving.");
            using (FileStream fsSender = new FileStream(fileNames[sender], FileMode.OpenOrCreate, FileAccess.ReadWrite,
                       FileShare.Read))
            using (FileStream fsReciever = new FileStream(fileNames[reciver], FileMode.OpenOrCreate,
                       FileAccess.ReadWrite, FileShare.Read))
            using (BinaryReader brSender = new BinaryReader(fsSender))
            using (BinaryReader brReciever = new BinaryReader(fsReciever))
            using (BinaryWriter bwSender = new BinaryWriter(fsSender))
            using (BinaryWriter bwReciever = new BinaryWriter(fsReciever))
            {
                fsSender.Seek(0, SeekOrigin.Begin);
                int actualTotal = brSender.ReadInt32();
                if (actualTotal < quantityToSend)
                {
                    Console.ForegroundColor = ConsoleColor.DarkMagenta;
                    Console.WriteLine($"Account {sender + 1} doesn't have the necessary amount.");
                }
                else
                {
                    Thread.Sleep(1000);
                    int newTotal = actualTotal - quantityToSend;
                    fsSender.Seek(0, SeekOrigin.Begin);
                    bwSender.Write(newTotal);
                    Console.ForegroundColor = ConsoleColor.Yellow;
                    Console.WriteLine($"-->Account {sender + 1} before amount was: {actualTotal}. New amount: {newTotal}.");
                    int recieverActualTotal = brReciever.ReadInt32();
                    int newRecieverActualTotal = recieverActualTotal + quantityToSend;
                    fsReciever.Seek(0, SeekOrigin.Begin);
                    bwReciever.Write(newRecieverActualTotal);
                    Console.ForegroundColor = ConsoleColor.Yellow;
                    Console.WriteLine($"-->Account {reciver + 1} before amount was: {recieverActualTotal}. New amount: {newRecieverActualTotal}. Added from account {sender + 1}.");

                }
            }
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
        }
        finally
        {
            Console.ForegroundColor = ConsoleColor.White;
            Console.WriteLine($"Released Mutex of account {sender+1} and account {reciver+1}.\n================================================================");
            ReleaseMutex(sender);
            ReleaseMutex(reciver);
        }
    }

    static void BlockMutex(int value)
    {
        switch (value)
        {
            case 0:
                mutexAcc1.WaitOne();
                break;
            case 1:
                mutexAcc2.WaitOne();
                break;
            case 2:
                mutexAcc3.WaitOne();
                break;
            case 3:
                mutexAcc4.WaitOne();
                break;
        }
    }

    static void ReleaseMutex(int value)
    {
        switch (value)
        {
            case 0:
                mutexAcc1.ReleaseMutex();
                break;
            case 1:
                mutexAcc2.ReleaseMutex();
                break;
            case 2:
                mutexAcc3.ReleaseMutex();
                break;
            case 3:
                mutexAcc4.ReleaseMutex();
                break;
        }
    }
    
    
    
    static void CreateFiles()
    {
        foreach (var fileName in fileNames)
        {   
            using (FileStream fs = new FileStream(fileName, FileMode.OpenOrCreate, FileAccess.Write))
            using (BinaryWriter bw = new BinaryWriter(fs))
            {
                bw.Write(1000);
            }
        }
    }
}