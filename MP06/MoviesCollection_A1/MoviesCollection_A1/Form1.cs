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

        private void textBox_IDSearch_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar))
            {
                // If the key is not a control key or a digit, consume the event (do not allow it)
                e.Handled = true;
            }
        }

        private void btn_SearchByIndex_Click(object sender, EventArgs e)
        {
            string? result = iDao.SelectByIndex(Convert.ToInt32(textBox_IDSearch.Text));
            var film = IDAO.ConvertToRawTitle(result);
            lbl_show_id_pos.Text = film.Id;
            lbl_show_title_pos.Text = film.Title;
            lbl_show_type_pos.Text = film.Type;
            lbl_show_year_pos.Text = film.ReleaseYear.ToString();
            lbl_show_season_pos.Text = film.Seasons;
            lbl_show_score_pos.Text = film.Imdb_Score.ToString();
            lbl_show_votes_pos.Text = film.Imdb_Votes.ToString();

        }
    }
}
