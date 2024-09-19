namespace MoviesCollection_A1
{
    public partial class Form1 : Form
    {
        private IDAO iDao;
        public Form1()
        {
            iDao = IDAONetflix.CreateNetflixDao();
            InitializeComponent();
        }

        private void btn_GenereFileGenerator_Click(object sender, EventArgs e)
        {
            int generated = 0;
            if (string.IsNullOrEmpty(txtBox_OuputGenereFile.Text)) return;

            generated = iDao.SelectByGenre(txtBox_GenereName.Text, txtBox_OuputGenereFile.Text);
            lbl_GenereCounter.Text = $"Found: {generated} films with {txtBox_GenereName.Text} genre.";
        }
    }
}
