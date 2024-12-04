using System.Windows.Forms;

namespace Pt3._2
{
    public partial class Form1 : Form
    {
        double pes, altura;

        public Form1()
        {
            InitializeComponent();
            bCalcul.Enabled = false; //@PC: Deshabilitar el bot�n de c�lculo al inicio
        }

        private void bReiniciar_Click(object sender, EventArgs e)
        {
            //@PC: Limpiar los campos de texto y deshabilitar el bot�n de c�lculo
            tPes.Text = string.Empty;
            tAltura.Text = string.Empty;
            tResultat.Text = string.Empty;
            bCalcul.Enabled = false;
            tAltura.Enabled = true;
            tPes.Enabled = true;
            pSemafor.Image = null;
        }

        private void bCalcul_Click(object sender, EventArgs e)
        {
            //@PC: Deshabilitar campos y bot�n despu�s del c�lculo
            tAltura.Enabled = false;
            tPes.Enabled = false;
            bCalcul.Enabled = false;

            //@PC: Variables para el c�lculo
            double alturaM, imc = 0;

            //@PC: Intentar convertir el texto a n�meros
            if (double.TryParse(tPes.Text, out pes) && double.TryParse(tAltura.Text, out altura))
            {
                if (altura > 50 && altura < 250 && pes > 40 && pes < 200)
                {
                    //@PC: Convertir altura a metros y calcular el IMC
                    alturaM = altura / 100;
                    imc = pes / (alturaM * alturaM);

                    //@PC: Mostrar el resultado en el TextBox de resultado
                    tResultat.Text = imc.ToString("F2"); // Mostrar con dos decimales

                    //@PC: Cambiar el color del sem�foro seg�n el IMC
                    if (imc < 25)
                    {
                        pSemafor.Image = Properties.Resources.descarga__5_;
                    }
                    else if (imc < 30)
                    {
                        pSemafor.Image = Properties.Resources.descarga__7_;
                    }
                    else
                    {
                        pSemafor.Image = Properties.Resources.descarga__6_;
                    }
                }
                else
                {
                    //@PC: Mostrar mensajes de error espec�ficos para altura o peso fuera de rango
                    if (altura <= 50 || altura >= 250)
                    {
                        MessageBox.Show("Valors v�lids: nom�s n�meros majors a 50 i menors a 250", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }

                    if (pes <= 40 || pes >= 200)
                    {
                        MessageBox.Show("Valors v�lids: nom�s n�meros majors a 40 i menors a 200", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                }
            }
            else
            {
                MessageBox.Show("Introduce valores num�ricos v�lidos para peso y altura.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void tAltura_TextChanged(object sender, EventArgs e)
        {
            HabilitarBotonCalculo();
        }

        private void tPes_TextChanged(object sender, EventArgs e)
        {
            HabilitarBotonCalculo();
        }

        private void HabilitarBotonCalculo()
        {
            //@PC: Habilitar el bot�n solo si ambos campos tienen valores v�lidos
            bCalcul.Enabled = !string.IsNullOrEmpty(tAltura.Text) && !string.IsNullOrEmpty(tPes.Text);
        }
    }
}
