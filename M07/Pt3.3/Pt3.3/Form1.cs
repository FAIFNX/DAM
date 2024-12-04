namespace Pt3._3
{
    public partial class Form1 : Form
    {
        private int numeroRandom; // Número oculto
        private int numeroIntento; // Número ingresado por el usuario
        private int count = 0; // Contador de intentos

        public Form1()
        {
            InitializeComponent();
            ReiniciarJuego();
        }

        private void ReiniciarJuego()
        {
            //@PC: Inicializar el juego, generando un número aleatorio y restableciendo los valores
            numeroRandom = GenerarNumeroAleatorio();
            count = 0;
            tContador.Text = count.ToString();
            tResultado.Clear();
            tResultado.Enabled = true;
            bSort.Enabled = false;
            bReinici.Enabled = false;
            lResultadoNumerico.Visible = false;
            lResultadoNumerico.Text = numeroRandom.ToString();
            pImagenResultado.Visible = false;
        }

        private int GenerarNumeroAleatorio()
        {
            //@PC: Generar un número aleatorio entre 0 y 100
            Random random = new Random();
            return random.Next(0, 101);
        }

        private void bSort_Click(object sender, EventArgs e)
        {
            //@PC: Manejar la lógica del botón "Sort!" para comparar el número ingresado con el número oculto
            count++;
            tContador.Text = count.ToString();

            if (int.TryParse(tResultado.Text, out numeroIntento))
            {
                if (numeroIntento >= 0 && numeroIntento <= 100)
                {
                    pImagenResultado.Visible = true;

                    if (numeroIntento < numeroRandom)
                    {
                        pImagenResultado.Image = Properties.Resources.flecha_hacia_arriba1;
                    }
                    else if (numeroIntento > numeroRandom)
                    {
                        pImagenResultado.Image = Properties.Resources.flecha_hacia_abajo1;
                    }
                    else
                    {
                        pImagenResultado.Image = Properties.Resources.Check;
                        tResultado.Enabled = false;
                        bReinici.Enabled = true;
                        MessageBox.Show("¡Felicidades! Has acertado el número.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    }
                }
                else
                {
                    MessageBox.Show("Introduce un número entre 0 y 100.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            else
            {
                MessageBox.Show("Introduce un número válido.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void bReinici_Click(object sender, EventArgs e)
        {
            //@PC: Restablecer el juego al presionar el botón de reinicio
            ReiniciarJuego();
        }

        private void tResultado_TextChanged(object sender, EventArgs e)
        {
            //@PC: Evento de cambio en el campo de resultado
            bSort.Enabled = !string.IsNullOrWhiteSpace(tResultado.Text) && tResultado.Enabled;
        }
    }
}
