namespace MoviesCollection_A1
{

    internal static class Program
    {
        public static string FILE_NAME = "raw_titles.csv";
        [STAThread]
        static void Main()
        {
            ApplicationConfiguration.Initialize();
            Application.Run(new Form1());
        }



        static int SelectByGenre(String genre, String outputFile)
        {
            return 0;
        }
    }
}