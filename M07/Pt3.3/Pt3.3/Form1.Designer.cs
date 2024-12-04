namespace Pt3._3
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
            tResultado = new TextBox();
            lResultadoNumerico = new Label();
            iContador = new Label();
            tContador = new TextBox();
            textBox3 = new TextBox();
            pImagenResultado = new PictureBox();
            bSort = new Button();
            bReinici = new Button();
            ((System.ComponentModel.ISupportInitialize)pImagenResultado).BeginInit();
            SuspendLayout();
            // 
            // tResultado
            // 
            tResultado.Location = new Point(122, 350);
            tResultado.Name = "tResultado";
            tResultado.Size = new Size(100, 23);
            tResultado.TabIndex = 0;
            tResultado.TextChanged += tResultado_TextChanged;
            // 
            // lResultadoNumerico
            // 
            lResultadoNumerico.AutoSize = true;
            lResultadoNumerico.Location = new Point(149, 322);
            lResultadoNumerico.Name = "lResultadoNumerico";
            lResultadoNumerico.Size = new Size(38, 15);
            lResultadoNumerico.TabIndex = 1;
            lResultadoNumerico.Text = "label1";
            // 
            // iContador
            // 
            iContador.AutoSize = true;
            iContador.Location = new Point(12, 9);
            iContador.Name = "iContador";
            iContador.Size = new Size(57, 15);
            iContador.TabIndex = 2;
            iContador.Text = "Contador";
            // 
            // tContador
            // 
            tContador.Enabled = false;
            tContador.Location = new Point(75, 6);
            tContador.Name = "tContador";
            tContador.Size = new Size(31, 23);
            tContador.TabIndex = 3;
            // 
            // textBox3
            // 
            textBox3.Enabled = false;
            textBox3.Location = new Point(12, 415);
            textBox3.Name = "textBox3";
            textBox3.Size = new Size(31, 23);
            textBox3.TabIndex = 4;
            // 
            // pImagenResultado
            // 
            pImagenResultado.Location = new Point(105, 114);
            pImagenResultado.Name = "pImagenResultado";
            pImagenResultado.Size = new Size(134, 148);
            pImagenResultado.TabIndex = 5;
            pImagenResultado.TabStop = false;
            // 
            // bSort
            // 
            bSort.Location = new Point(134, 379);
            bSort.Name = "bSort";
            bSort.Size = new Size(75, 23);
            bSort.TabIndex = 6;
            bSort.Text = "Soirt!";
            bSort.UseVisualStyleBackColor = true;
            bSort.Click += bSort_Click;
            // 
            // bReinici
            // 
            bReinici.Location = new Point(268, 415);
            bReinici.Name = "bReinici";
            bReinici.Size = new Size(75, 23);
            bReinici.TabIndex = 7;
            bReinici.Text = "Reiniciar";
            bReinici.UseVisualStyleBackColor = true;
            bReinici.Click += bReinici_Click;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(355, 450);
            Controls.Add(bReinici);
            Controls.Add(bSort);
            Controls.Add(pImagenResultado);
            Controls.Add(textBox3);
            Controls.Add(tContador);
            Controls.Add(iContador);
            Controls.Add(lResultadoNumerico);
            Controls.Add(tResultado);
            Name = "Form1";
            Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)pImagenResultado).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private TextBox tResultado;
        private Label lResultadoNumerico;
        private Label iContador;
        private TextBox tContador;
        private TextBox textBox3;
        private PictureBox pImagenResultado;
        private Button bSort;
        private Button bReinici;
    }
}
