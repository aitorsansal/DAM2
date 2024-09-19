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
            lbl_GenereCounter = new Label();
            label4 = new Label();
            txtBox_OuputGenereFile = new TextBox();
            label3 = new Label();
            txtBox_GenereName = new TextBox();
            btn_GenereFileGenerator = new Button();
            groupBox2 = new GroupBox();
            button2 = new Button();
            label2 = new Label();
            groupBox1.SuspendLayout();
            groupBox2.SuspendLayout();
            SuspendLayout();
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(6, 19);
            label1.Name = "label1";
            label1.Size = new Size(135, 15);
            label1.TabIndex = 1;
            label1.Text = "Select RawTitle by Genre";
            // 
            // groupBox1
            // 
            groupBox1.Controls.Add(lbl_GenereCounter);
            groupBox1.Controls.Add(label4);
            groupBox1.Controls.Add(txtBox_OuputGenereFile);
            groupBox1.Controls.Add(label3);
            groupBox1.Controls.Add(txtBox_GenereName);
            groupBox1.Controls.Add(btn_GenereFileGenerator);
            groupBox1.Controls.Add(label1);
            groupBox1.Location = new Point(12, 12);
            groupBox1.Name = "groupBox1";
            groupBox1.Size = new Size(249, 506);
            groupBox1.TabIndex = 1;
            groupBox1.TabStop = false;
            groupBox1.Text = "Exercise 1";
            // 
            // lbl_GenereCounter
            // 
            lbl_GenereCounter.AutoSize = true;
            lbl_GenereCounter.Location = new Point(6, 178);
            lbl_GenereCounter.Name = "lbl_GenereCounter";
            lbl_GenereCounter.Size = new Size(180, 15);
            lbl_GenereCounter.TabIndex = 7;
            lbl_GenereCounter.Text = "Write the name of the output file";
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Location = new Point(6, 113);
            label4.Name = "label4";
            label4.Size = new Size(180, 15);
            label4.TabIndex = 6;
            label4.Text = "Write the name of the output file";
            // 
            // txtBox_OuputGenereFile
            // 
            txtBox_OuputGenereFile.Location = new Point(6, 131);
            txtBox_OuputGenereFile.Name = "txtBox_OuputGenereFile";
            txtBox_OuputGenereFile.Size = new Size(213, 23);
            txtBox_OuputGenereFile.TabIndex = 5;
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(6, 50);
            label3.Name = "label3";
            label3.Size = new Size(207, 15);
            label3.TabIndex = 4;
            label3.Text = "Write the genre you want to search by";
            // 
            // txtBox_GenereName
            // 
            txtBox_GenereName.Location = new Point(6, 68);
            txtBox_GenereName.Name = "txtBox_GenereName";
            txtBox_GenereName.Size = new Size(213, 23);
            txtBox_GenereName.TabIndex = 3;
            // 
            // btn_GenereFileGenerator
            // 
            btn_GenereFileGenerator.Location = new Point(6, 196);
            btn_GenereFileGenerator.Name = "btn_GenereFileGenerator";
            btn_GenereFileGenerator.Size = new Size(112, 34);
            btn_GenereFileGenerator.TabIndex = 2;
            btn_GenereFileGenerator.Text = "Generate File";
            btn_GenereFileGenerator.UseVisualStyleBackColor = true;
            btn_GenereFileGenerator.Click += btn_GenereFileGenerator_Click;
            // 
            // groupBox2
            // 
            groupBox2.Controls.Add(button2);
            groupBox2.Controls.Add(label2);
            groupBox2.Location = new Point(12, 524);
            groupBox2.Name = "groupBox2";
            groupBox2.Size = new Size(249, 506);
            groupBox2.TabIndex = 2;
            groupBox2.TabStop = false;
            groupBox2.Text = "Exercise 1";
            // 
            // button2
            // 
            button2.Location = new Point(168, 397);
            button2.Name = "button2";
            button2.Size = new Size(75, 23);
            button2.TabIndex = 0;
            button2.Text = "button2";
            button2.UseVisualStyleBackColor = true;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(6, 19);
            label2.Name = "label2";
            label2.Size = new Size(135, 15);
            label2.TabIndex = 1;
            label2.Text = "Select RawTitle by Genre";
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
            ResumeLayout(false);
        }

        #endregion
        private Label label1;
        private GroupBox groupBox1;
        private GroupBox groupBox2;
        private Button button2;
        private Label label2;
        private Button btn_GenereFileGenerator;
        private Label label4;
        private TextBox txtBox_OuputGenereFile;
        private Label label3;
        private TextBox txtBox_GenereName;
        private Label lbl_GenereCounter;
    }
}
