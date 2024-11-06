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
    static Mutex mutexLog;
    static Random random = new Random();
    static string[] fileNames = ["acc1.bin", "acc2.bin", "acc3.bin", "acc4.bin"];
    private const int QUANTITY_OF_THREADS = 10;

    private static string logsFileName = "logs_";
    
    public static void Main(string[] args)
    {
        try
        {
            logsFileName += DateTime.Now.ToString("hh_mm_ss") + ".txt";
            mutexLog = new Mutex(false, logsFileName);
            File.Create(logsFileName).Close();
            bool doneProgram = false;
            while (!doneProgram)
            {
                WriteOnFileAndConsole("Press 1 for generating the new files and 2 for start the program: (0 for quit)");
                int val = int.Parse(Console.ReadLine());
                WriteOnFileAndConsole(val.ToString(), justFile:true);
                switch (val)
                {
                    case 1:
                        CreateFiles();
                        break;
                    case 2:
                    {
                        WriteOnFileAndConsole("=====\nSTARTING PROGRAM\n=====");
                        Thread[] threads = new Thread[QUANTITY_OF_THREADS];
        
                        for (int i = 0; i < QUANTITY_OF_THREADS; i++)
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
                                WriteOnFileAndConsole($"{fileName} final amount: {br.ReadInt32()}");
                            }
                        }
                        WriteOnFileAndConsole("Program finished correctly. Shutting down.");
                        Thread.Sleep(500);
                        doneProgram = true;
                        break;
                    }
                    case 0:
                        WriteOnFileAndConsole("Program finished correctly. Shutting down.");
                        Thread.Sleep(500);
                        doneProgram = true;
                        break;
                    default:
                        WriteOnFileAndConsole("Value not recognized.");
                        break;
                }
            }

        }
        catch (Exception e)
        {
            WriteOnFileAndConsole(e.Message, ConsoleColor.DarkRed);
        }

    }


    static void WriteOnFileAndConsole(string message, ConsoleColor color = ConsoleColor.White, bool justFile = false)
    {
        Console.ForegroundColor = color;
        if(!justFile)
            Console.WriteLine(message);
        mutexLog.WaitOne();
        using (FileStream fs = new FileStream(logsFileName,FileMode.Append, FileAccess.Write))
        using (StreamWriter sw = new StreamWriter(fs))
        {
            sw.WriteLine(message);
        }
        mutexLog.ReleaseMutex();
    }


    static void ExecuteTransaction()
    {
        int sender = random.Next(4);
        int quantityToSend = random.Next(1,500);
        int reciver = random.Next(4);
        while(reciver == sender)
            reciver = random.Next(4);
        WriteOnFileAndConsole($"Account {sender+1} is trying to send {quantityToSend} to Account {reciver+1}.", ConsoleColor.Blue);
        WriteOnFileAndConsole("inside execute transaction");
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
            WriteOnFileAndConsole($"Account {sender+1} has been blocked with Mutex bc of sending.", ConsoleColor.Red);
            WriteOnFileAndConsole($"Account {reciver+1} has been blocked with Mutex bc of recieving.", ConsoleColor.Red);
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
                    WriteOnFileAndConsole($"Account {sender + 1} doesn't have the necessary amount.", ConsoleColor.DarkMagenta);
                }
                else
                {
                    Thread.Sleep(1000);
                    int newTotal = actualTotal - quantityToSend;
                    fsSender.Seek(0, SeekOrigin.Begin);
                    bwSender.Write(newTotal);
                    WriteOnFileAndConsole($"-->Account {sender + 1} before amount was: {actualTotal}. New amount: {newTotal}.", ConsoleColor.Yellow);
                    int recieverActualTotal = brReciever.ReadInt32();
                    int newRecieverActualTotal = recieverActualTotal + quantityToSend;
                    fsReciever.Seek(0, SeekOrigin.Begin);
                    bwReciever.Write(newRecieverActualTotal);
                    Console.ForegroundColor = ConsoleColor.Yellow;
                    WriteOnFileAndConsole($"-->Account {reciver + 1} before amount was: {recieverActualTotal}. New amount: {newRecieverActualTotal}. Added from account {sender + 1}.",
                        ConsoleColor.Yellow);

                }
            }
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
        }
        finally
        {
            WriteOnFileAndConsole($"Released Mutex of account {sender+1} and account {reciver+1}.\n================================================================");
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
        for (int i = 0; i < fileNames.Length; i++)
        {
            BlockMutex(i+1);
            using (FileStream fs = new FileStream(fileNames[i], FileMode.OpenOrCreate, FileAccess.Write))
            using (BinaryWriter bw = new BinaryWriter(fs))
            {
                bw.Write(1000);
            }
            WriteOnFileAndConsole($"File for account {i+1} has been created.");
            ReleaseMutex(i+1);
        }
    }
}