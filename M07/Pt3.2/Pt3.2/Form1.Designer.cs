namespace Pt3._2
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
            lAltura = new Label();
            lPeso = new Label();
            lResultado = new Label();
            tAltura = new TextBox();
            tPes = new TextBox();
            tResultat = new TextBox();
            bCalcul = new Button();
            bReiniciar = new Button();
            pSemafor = new PictureBox();
            ((System.ComponentModel.ISupportInitialize)pSemafor).BeginInit();
            SuspendLayout();
            // 
            // lAltura
            // 
            lAltura.AutoSize = true;
            lAltura.Location = new Point(38, 32);
            lAltura.Name = "lAltura";
            lAltura.Size = new Size(69, 15);
            lAltura.TabIndex = 0;
            lAltura.Text = "Altura (CM)";
            // 
            // lPeso
            // 
            lPeso.AutoSize = true;
            lPeso.Location = new Point(38, 58);
            lPeso.Name = "lPeso";
            lPeso.Size = new Size(50, 15);
            lPeso.TabIndex = 1;
            lPeso.Text = "Pes (KG)";
            // 
            // lResultado
            // 
            lResultado.AutoSize = true;
            lResultado.Location = new Point(38, 87);
            lResultado.Name = "lResultado";
            lResultado.Size = new Size(49, 15);
            lResultado.TabIndex = 2;
            lResultado.Text = "Resultat";
            // 
            // tAltura
            // 
            tAltura.Location = new Point(143, 29);
            tAltura.Name = "tAltura";
            tAltura.Size = new Size(100, 23);
            tAltura.TabIndex = 3;
            tAltura.TextChanged += tAltura_TextChanged;
            // 
            // tPes
            // 
            tPes.Location = new Point(143, 58);
            tPes.Name = "tPes";
            tPes.Size = new Size(100, 23);
            tPes.TabIndex = 4;
            tPes.TextChanged += tPes_TextChanged;
            // 
            // tResultat
            // 
            tResultat.Enabled = false;
            tResultat.Location = new Point(143, 87);
            tResultat.Name = "tResultat";
            tResultat.Size = new Size(100, 23);
            tResultat.TabIndex = 5;
            // 
            // bCalcul
            // 
            bCalcul.Location = new Point(64, 373);
            bCalcul.Name = "bCalcul";
            bCalcul.Size = new Size(75, 23);
            bCalcul.TabIndex = 6;
            bCalcul.Text = "Calcular";
            bCalcul.UseVisualStyleBackColor = true;
            bCalcul.Click += bCalcul_Click;
            // 
            // bReiniciar
            // 
            bReiniciar.Location = new Point(250, 373);
            bReiniciar.Name = "bReiniciar";
            bReiniciar.Size = new Size(75, 23);
            bReiniciar.TabIndex = 7;
            bReiniciar.Text = "Reiniciar";
            bReiniciar.UseVisualStyleBackColor = true;
            bReiniciar.Click += bReiniciar_Click;
            // 
            // pSemafor
            // 
            pSemafor.Location = new Point(97, 151);
            pSemafor.Name = "pSemafor";
            pSemafor.Size = new Size(192, 174);
            pSemafor.TabIndex = 8;
            pSemafor.TabStop = false;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(393, 450);
            Controls.Add(pSemafor);
            Controls.Add(bReiniciar);
            Controls.Add(bCalcul);
            Controls.Add(tResultat);
            Controls.Add(tPes);
            Controls.Add(tAltura);
            Controls.Add(lResultado);
            Controls.Add(lPeso);
            Controls.Add(lAltura);
            Name = "Form1";
            Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)pSemafor).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label lAltura;
        private Label lPeso;
        private Label lResultado;
        private TextBox tAltura;
        private TextBox tPes;
        private TextBox tResultat;
        private Button bCalcul;
        private Button bReiniciar;
        private PictureBox pSemafor;
    }
}
