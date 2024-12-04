namespace JocPenjat
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
            tCajaDeLetras1 = new TextBox();
            tLetraEndevinar = new TextBox();
            lletras = new Label();
            bSort = new Button();
            lResultat = new Label();
            label1 = new Label();
            tCount = new TextBox();
            pImagen = new PictureBox();
            ltexto = new Label();
            tCajaDeLetras2 = new TextBox();
            tCajaDeLetras3 = new TextBox();
            tCajaDeLetras4 = new TextBox();
            tCajaDeLetras5 = new TextBox();
            ((System.ComponentModel.ISupportInitialize)pImagen).BeginInit();
            SuspendLayout();
            // 
            // tCajaDeLetras1
            // 
            tCajaDeLetras1.Enabled = false;
            tCajaDeLetras1.Location = new Point(148, 61);
            tCajaDeLetras1.Name = "tCajaDeLetras1";
            tCajaDeLetras1.Size = new Size(27, 23);
            tCajaDeLetras1.TabIndex = 0;
            // 
            // tLetraEndevinar
            // 
            tLetraEndevinar.Location = new Point(207, 428);
            tLetraEndevinar.Name = "tLetraEndevinar";
            tLetraEndevinar.Size = new Size(27, 23);
            tLetraEndevinar.TabIndex = 1;
            // 
            // lletras
            // 
            lletras.AutoSize = true;
            lletras.Location = new Point(165, 410);
            lletras.Name = "lletras";
            lletras.Size = new Size(111, 15);
            lletras.TabIndex = 2;
            lletras.Text = "Lletra per endevinar";
            // 
            // bSort
            // 
            bSort.Location = new Point(380, 437);
            bSort.Name = "bSort";
            bSort.Size = new Size(75, 23);
            bSort.TabIndex = 3;
            bSort.Text = "Sort!";
            bSort.UseVisualStyleBackColor = true;
            bSort.Click += bSort_Click;
            // 
            // lResultat
            // 
            lResultat.AutoSize = true;
            lResultat.Location = new Point(224, 21);
            lResultat.Name = "lResultat";
            lResultat.Size = new Size(10, 15);
            lResultat.TabIndex = 4;
            lResultat.Text = " ";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(12, 9);
            label1.Name = "label1";
            label1.Size = new Size(40, 15);
            label1.TabIndex = 5;
            label1.Text = "Count";
            // 
            // tCount
            // 
            tCount.Enabled = false;
            tCount.Location = new Point(57, 6);
            tCount.Name = "tCount";
            tCount.Size = new Size(37, 23);
            tCount.TabIndex = 6;
            // 
            // pImagen
            // 
            pImagen.Location = new Point(57, 90);
            pImagen.Name = "pImagen";
            pImagen.Size = new Size(362, 317);
            pImagen.TabIndex = 7;
            pImagen.TabStop = false;
            // 
            // ltexto
            // 
            ltexto.AutoSize = true;
            ltexto.Location = new Point(100, 322);
            ltexto.Name = "ltexto";
            ltexto.Size = new Size(0, 15);
            ltexto.TabIndex = 8;
            // 
            // tCajaDeLetras2
            // 
            tCajaDeLetras2.Enabled = false;
            tCajaDeLetras2.Location = new Point(181, 61);
            tCajaDeLetras2.Name = "tCajaDeLetras2";
            tCajaDeLetras2.Size = new Size(27, 23);
            tCajaDeLetras2.TabIndex = 9;
            // 
            // tCajaDeLetras3
            // 
            tCajaDeLetras3.Enabled = false;
            tCajaDeLetras3.Location = new Point(214, 61);
            tCajaDeLetras3.Name = "tCajaDeLetras3";
            tCajaDeLetras3.Size = new Size(27, 23);
            tCajaDeLetras3.TabIndex = 10;
            // 
            // tCajaDeLetras4
            // 
            tCajaDeLetras4.Enabled = false;
            tCajaDeLetras4.Location = new Point(247, 61);
            tCajaDeLetras4.Name = "tCajaDeLetras4";
            tCajaDeLetras4.Size = new Size(27, 23);
            tCajaDeLetras4.TabIndex = 11;
            // 
            // tCajaDeLetras5
            // 
            tCajaDeLetras5.Enabled = false;
            tCajaDeLetras5.Location = new Point(280, 61);
            tCajaDeLetras5.Name = "tCajaDeLetras5";
            tCajaDeLetras5.Size = new Size(27, 23);
            tCajaDeLetras5.TabIndex = 12;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(467, 472);
            Controls.Add(tCajaDeLetras5);
            Controls.Add(tCajaDeLetras4);
            Controls.Add(tCajaDeLetras3);
            Controls.Add(tCajaDeLetras2);
            Controls.Add(ltexto);
            Controls.Add(pImagen);
            Controls.Add(tCount);
            Controls.Add(label1);
            Controls.Add(lResultat);
            Controls.Add(bSort);
            Controls.Add(lletras);
            Controls.Add(tLetraEndevinar);
            Controls.Add(tCajaDeLetras1);
            Name = "Form1";
            Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)pImagen).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private TextBox tCajaDeLetras1;
        private TextBox tLetraEndevinar;
        private Label lletras;
        private Button bSort;
        private Label lResultat;
        private Label label1;
        private TextBox tCount;
        private PictureBox pImagen;
        private Label ltexto;
        private TextBox tCajaDeLetras2;
        private TextBox tCajaDeLetras3;
        private TextBox tCajaDeLetras4;
        private TextBox tCajaDeLetras5;
    }
}
