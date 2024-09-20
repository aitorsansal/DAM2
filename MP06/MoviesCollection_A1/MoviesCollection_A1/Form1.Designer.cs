namespace MoviesCollection_A1
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            label1 = new Label();
            groupBox1 = new GroupBox();
            button1 = new Button();
            lbl_GenereCounter = new Label();
            label4 = new Label();
            txtBox_OuputGenereFile = new TextBox();
            label3 = new Label();
            txtBox_GenereName = new TextBox();
            btn_GenereFileGenerator = new Button();
            groupBox2 = new GroupBox();
            groupBox3 = new GroupBox();
            lbl_show_votes_pos = new Label();
            lbl_votes_info = new Label();
            lbl_score_info = new Label();
            lbl_show_score_pos = new Label();
            lbl_seasons_info = new Label();
            lbl_release_info = new Label();
            lbl_show_season_pos = new Label();
            lbl_show_id_pos = new Label();
            lbl_type_info = new Label();
            lbl_show_year_pos = new Label();
            lbl_title_info = new Label();
            lbl_show_title_pos = new Label();
            lbl_id_info = new Label();
            lbl_show_type_pos = new Label();
            btn_SearchByIndex = new Button();
            label5 = new Label();
            textBox_IDSearch = new TextBox();
            label2 = new Label();
            groupBox1.SuspendLayout();
            groupBox2.SuspendLayout();
            groupBox3.SuspendLayout();
            SuspendLayout();
            // 
            // label1
            // 
            label1.AllowDrop = true;
            label1.Location = new Point(34, 19);
            label1.Name = "label1";
            label1.Size = new Size(152, 46);
            label1.TabIndex = 1;
            label1.Text = "Generate a file with the RawTitle selected by Genre";
            // 
            // groupBox1
            // 
            groupBox1.Controls.Add(button1);
            groupBox1.Controls.Add(lbl_GenereCounter);
            groupBox1.Controls.Add(label4);
            groupBox1.Controls.Add(txtBox_OuputGenereFile);
            groupBox1.Controls.Add(label3);
            groupBox1.Controls.Add(txtBox_GenereName);
            groupBox1.Controls.Add(btn_GenereFileGenerator);
            groupBox1.Controls.Add(label1);
            groupBox1.Location = new Point(12, 12);
            groupBox1.Name = "groupBox1";
            groupBox1.Size = new Size(249, 341);
            groupBox1.TabIndex = 1;
            groupBox1.TabStop = false;
            groupBox1.Text = "Exercise 1";
            // 
            // button1
            // 
            button1.Enabled = false;
            button1.Location = new Point(6, 257);
            button1.Name = "button1";
            button1.Size = new Size(112, 34);
            button1.TabIndex = 8;
            button1.Text = "Open File";
            button1.UseVisualStyleBackColor = true;
            // 
            // lbl_GenereCounter
            // 
            lbl_GenereCounter.AutoSize = true;
            lbl_GenereCounter.Location = new Point(6, 178);
            lbl_GenereCounter.Name = "lbl_GenereCounter";
            lbl_GenereCounter.Size = new Size(0, 15);
            lbl_GenereCounter.TabIndex = 7;
            lbl_GenereCounter.Text = "Say how many found";
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Location = new Point(6, 125);
            label4.Name = "label4";
            label4.Size = new Size(180, 15);
            label4.TabIndex = 6;
            label4.Text = "Write the name of the output file";
            // 
            // txtBox_OuputGenereFile
            // 
            txtBox_OuputGenereFile.Location = new Point(6, 143);
            txtBox_OuputGenereFile.Name = "txtBox_OuputGenereFile";
            txtBox_OuputGenereFile.Size = new Size(213, 23);
            txtBox_OuputGenereFile.TabIndex = 5;
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(6, 62);
            label3.Name = "label3";
            label3.Size = new Size(207, 15);
            label3.TabIndex = 4;
            label3.Text = "Write the genre you want to search by";
            // 
            // txtBox_GenereName
            // 
            txtBox_GenereName.Location = new Point(6, 80);
            txtBox_GenereName.Name = "txtBox_GenereName";
            txtBox_GenereName.Size = new Size(213, 23);
            txtBox_GenereName.TabIndex = 3;
            // 
            // btn_GenereFileGenerator
            // 
            btn_GenereFileGenerator.Location = new Point(6, 208);
            btn_GenereFileGenerator.Name = "btn_GenereFileGenerator";
            btn_GenereFileGenerator.Size = new Size(112, 34);
            btn_GenereFileGenerator.TabIndex = 2;
            btn_GenereFileGenerator.Text = "Generate File";
            btn_GenereFileGenerator.UseVisualStyleBackColor = true;
            btn_GenereFileGenerator.Click += btn_GenereFileGenerator_Click;
            // 
            // groupBox2
            // 
            groupBox2.Controls.Add(groupBox3);
            groupBox2.Controls.Add(btn_SearchByIndex);
            groupBox2.Controls.Add(label5);
            groupBox2.Controls.Add(textBox_IDSearch);
            groupBox2.Controls.Add(label2);
            groupBox2.Location = new Point(276, 12);
            groupBox2.Name = "groupBox2";
            groupBox2.Size = new Size(256, 555);
            groupBox2.TabIndex = 2;
            groupBox2.TabStop = false;
            groupBox2.Text = "Exercise 2";
            // 
            // groupBox3
            // 
            groupBox3.Controls.Add(lbl_show_votes_pos);
            groupBox3.Controls.Add(lbl_votes_info);
            groupBox3.Controls.Add(lbl_score_info);
            groupBox3.Controls.Add(lbl_seasons_info);
            groupBox3.Controls.Add(lbl_show_type_pos);
            groupBox3.Controls.Add(lbl_release_info);
            groupBox3.Controls.Add(lbl_show_score_pos);
            groupBox3.Controls.Add(lbl_type_info);
            groupBox3.Controls.Add(lbl_show_title_pos);
            groupBox3.Controls.Add(lbl_title_info);
            groupBox3.Controls.Add(lbl_show_year_pos);
            groupBox3.Controls.Add(lbl_id_info);
            groupBox3.Controls.Add(lbl_show_id_pos);
            groupBox3.Controls.Add(lbl_show_season_pos);
            groupBox3.Location = new Point(6, 165);
            groupBox3.Name = "groupBox3";
            groupBox3.Size = new Size(242, 295);
            groupBox3.TabIndex = 11;
            groupBox3.TabStop = false;
            // 
            // lbl_show_votes_pos
            // 
            lbl_show_votes_pos.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            lbl_show_votes_pos.Location = new Point(90, 241);
            lbl_show_votes_pos.MaximumSize = new Size(146, 37);
            lbl_show_votes_pos.Name = "lbl_show_votes_pos";
            lbl_show_votes_pos.Size = new Size(146, 37);
            lbl_show_votes_pos.TabIndex = 13;
            lbl_show_votes_pos.Text = "Id";
            lbl_show_votes_pos.TextAlign = ContentAlignment.MiddleRight;
            // 
            // lbl_votes_info
            // 
            lbl_votes_info.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            lbl_votes_info.AutoSize = true;
            lbl_votes_info.Location = new Point(7, 252);
            lbl_votes_info.Name = "lbl_votes_info";
            lbl_votes_info.Size = new Size(75, 15);
            lbl_votes_info.TabIndex = 6;
            lbl_votes_info.Text = "IMDB VOTES:";
            lbl_votes_info.Click += lbl_votes_info_Click;
            // 
            // lbl_score_info
            // 
            lbl_score_info.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            lbl_score_info.AutoSize = true;
            lbl_score_info.Location = new Point(7, 215);
            lbl_score_info.Name = "lbl_score_info";
            lbl_score_info.Size = new Size(78, 15);
            lbl_score_info.TabIndex = 5;
            lbl_score_info.Text = "IMDB SCORE:";
            // 
            // lbl_show_score_pos
            // 
            lbl_show_score_pos.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            lbl_show_score_pos.Location = new Point(90, 204);
            lbl_show_score_pos.MaximumSize = new Size(146, 37);
            lbl_show_score_pos.Name = "lbl_show_score_pos";
            lbl_show_score_pos.Size = new Size(146, 37);
            lbl_show_score_pos.TabIndex = 12;
            lbl_show_score_pos.Text = "Id";
            lbl_show_score_pos.TextAlign = ContentAlignment.MiddleRight;
            // 
            // lbl_seasons_info
            // 
            lbl_seasons_info.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            lbl_seasons_info.AutoSize = true;
            lbl_seasons_info.Location = new Point(7, 178);
            lbl_seasons_info.Name = "lbl_seasons_info";
            lbl_seasons_info.Size = new Size(60, 15);
            lbl_seasons_info.TabIndex = 4;
            lbl_seasons_info.Text = "SEASONS:";
            // 
            // lbl_release_info
            // 
            lbl_release_info.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            lbl_release_info.AutoSize = true;
            lbl_release_info.Location = new Point(7, 141);
            lbl_release_info.Name = "lbl_release_info";
            lbl_release_info.Size = new Size(86, 15);
            lbl_release_info.TabIndex = 3;
            lbl_release_info.Text = "RELEASE YEAR:";
            // 
            // lbl_show_season_pos
            // 
            lbl_show_season_pos.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            lbl_show_season_pos.Location = new Point(90, 167);
            lbl_show_season_pos.MaximumSize = new Size(146, 37);
            lbl_show_season_pos.Name = "lbl_show_season_pos";
            lbl_show_season_pos.Size = new Size(146, 37);
            lbl_show_season_pos.TabIndex = 11;
            lbl_show_season_pos.Text = "Id";
            lbl_show_season_pos.TextAlign = ContentAlignment.MiddleRight;
            // 
            // lbl_show_id_pos
            // 
            lbl_show_id_pos.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            lbl_show_id_pos.Location = new Point(90, 19);
            lbl_show_id_pos.MaximumSize = new Size(146, 37);
            lbl_show_id_pos.Name = "lbl_show_id_pos";
            lbl_show_id_pos.Size = new Size(146, 37);
            lbl_show_id_pos.TabIndex = 7;
            lbl_show_id_pos.Text = "Id";
            lbl_show_id_pos.TextAlign = ContentAlignment.MiddleRight;
            // 
            // lbl_type_info
            // 
            lbl_type_info.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            lbl_type_info.AutoSize = true;
            lbl_type_info.Location = new Point(7, 104);
            lbl_type_info.Name = "lbl_type_info";
            lbl_type_info.Size = new Size(36, 15);
            lbl_type_info.TabIndex = 2;
            lbl_type_info.Text = "TYPE:";
            // 
            // lbl_show_year_pos
            // 
            lbl_show_year_pos.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            lbl_show_year_pos.Location = new Point(90, 130);
            lbl_show_year_pos.MaximumSize = new Size(146, 37);
            lbl_show_year_pos.Name = "lbl_show_year_pos";
            lbl_show_year_pos.Size = new Size(146, 37);
            lbl_show_year_pos.TabIndex = 10;
            lbl_show_year_pos.Text = "Id";
            lbl_show_year_pos.TextAlign = ContentAlignment.MiddleRight;
            // 
            // lbl_title_info
            // 
            lbl_title_info.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            lbl_title_info.AutoSize = true;
            lbl_title_info.Location = new Point(7, 67);
            lbl_title_info.Name = "lbl_title_info";
            lbl_title_info.Size = new Size(37, 15);
            lbl_title_info.TabIndex = 1;
            lbl_title_info.Text = "TITLE:";
            // 
            // lbl_show_title_pos
            // 
            lbl_show_title_pos.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            lbl_show_title_pos.Location = new Point(90, 56);
            lbl_show_title_pos.MaximumSize = new Size(146, 37);
            lbl_show_title_pos.Name = "lbl_show_title_pos";
            lbl_show_title_pos.Size = new Size(146, 37);
            lbl_show_title_pos.TabIndex = 8;
            lbl_show_title_pos.Text = "Id";
            lbl_show_title_pos.TextAlign = ContentAlignment.MiddleRight;
            // 
            // lbl_id_info
            // 
            lbl_id_info.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            lbl_id_info.AutoSize = true;
            lbl_id_info.Location = new Point(7, 30);
            lbl_id_info.Name = "lbl_id_info";
            lbl_id_info.Size = new Size(21, 15);
            lbl_id_info.TabIndex = 0;
            lbl_id_info.Text = "ID:";
            // 
            // lbl_show_type_pos
            // 
            lbl_show_type_pos.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            lbl_show_type_pos.Location = new Point(90, 93);
            lbl_show_type_pos.MaximumSize = new Size(146, 37);
            lbl_show_type_pos.Name = "lbl_show_type_pos";
            lbl_show_type_pos.Size = new Size(146, 37);
            lbl_show_type_pos.TabIndex = 9;
            lbl_show_type_pos.Text = "Id";
            lbl_show_type_pos.TextAlign = ContentAlignment.MiddleRight;
            // 
            // btn_SearchByIndex
            // 
            btn_SearchByIndex.Location = new Point(6, 125);
            btn_SearchByIndex.Name = "btn_SearchByIndex";
            btn_SearchByIndex.Size = new Size(112, 34);
            btn_SearchByIndex.TabIndex = 9;
            btn_SearchByIndex.Text = "Search Raw Title";
            btn_SearchByIndex.UseVisualStyleBackColor = true;
            btn_SearchByIndex.Click += btn_SearchByIndex_Click;
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Location = new Point(6, 65);
            label5.Name = "label5";
            label5.Size = new Size(168, 15);
            label5.TabIndex = 10;
            label5.Text = "Write the index of the RawTitle";
            // 
            // textBox_IDSearch
            // 
            textBox_IDSearch.Location = new Point(6, 83);
            textBox_IDSearch.Name = "textBox_IDSearch";
            textBox_IDSearch.Size = new Size(213, 23);
            textBox_IDSearch.TabIndex = 9;
            textBox_IDSearch.KeyPress += textBox_IDSearch_KeyPress;
            // 
            // label2
            // 
            label2.Location = new Point(57, 19);
            label2.Name = "label2";
            label2.Size = new Size(133, 46);
            label2.TabIndex = 1;
            label2.Text = "Get the info of a Raw Title with it's position";
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(1904, 1041);
            Controls.Add(groupBox2);
            Controls.Add(groupBox1);
            Name = "Form1";
            StartPosition = FormStartPosition.CenterScreen;
            Text = "Form1";
            WindowState = FormWindowState.Maximized;
            groupBox1.ResumeLayout(false);
            groupBox1.PerformLayout();
            groupBox2.ResumeLayout(false);
            groupBox2.PerformLayout();
            groupBox3.ResumeLayout(false);
            groupBox3.PerformLayout();
            ResumeLayout(false);
        }

        #endregion
        private Label label1;
        private GroupBox groupBox1;
        private GroupBox groupBox2;
        private Label label2;
        private Button btn_GenereFileGenerator;
        private Label label4;
        private TextBox txtBox_OuputGenereFile;
        private Label label3;
        private TextBox txtBox_GenereName;
        private Label lbl_GenereCounter;
        private Button button1;
        private Button btn_SearchByIndex;
        private Label label5;
        private TextBox textBox_IDSearch;
        private GroupBox groupBox3;
        private Label lbl_votes_info;
        private Label lbl_score_info;
        private Label lbl_seasons_info;
        private Label lbl_release_info;
        private Label lbl_type_info;
        private Label lbl_title_info;
        private Label lbl_id_info;
        private Label lbl_show_votes_pos;
        private Label lbl_show_score_pos;
        private Label lbl_show_season_pos;
        private Label lbl_show_year_pos;
        private Label lbl_show_type_pos;
        private Label lbl_show_title_pos;
        private Label lbl_show_id_pos;
    }
}
