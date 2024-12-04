using System.Collections;
using System.Windows.Forms;

namespace JocPenjat
{
    public partial class Form1 : Form
    {
        public List<string> listaDeTexto = new List<string>();
        public string palabraSeleccionada;
        private TextBox[] letrasTextBoxes;
        private int intentosRestantes;
        private int letrasEncontradas;
        private Random random;
        private int contadorImagen;

        public Form1()
        {
            InitializeComponent();

            // Inicializamos la lista de palabras aquí
            listaDeTexto = new List<string> { "Akali", "Annie", "Kayle", "Neeko", "Nasus", "Sylas", "Qiyana", "Galio", "Janna", "Karma" };
            random = new Random();

            IniciarJuego();
        }

        private void IniciarJuego()
        {
            // Selecciona una palabra aleatoria
            palabraSeleccionada = listaDeTexto[random.Next(listaDeTexto.Count)].ToUpper();
            letrasEncontradas = 0;
            contadorImagen = 0;
            pImagen.Image = null;

            // Inicializa el contador de intentos restantes
            intentosRestantes = 10;
            tCount.Text = intentosRestantes.ToString();

            // Asigna los TextBox preexistentes para mostrar las letras
            letrasTextBoxes = new TextBox[] { tCajaDeLetras1, tCajaDeLetras2, tCajaDeLetras3, tCajaDeLetras4, tCajaDeLetras5 };

            // Limpia los TextBox para la nueva palabra
            for (int i = 0; i < letrasTextBoxes.Length; i++)
            {
                letrasTextBoxes[i].Text = i < palabraSeleccionada.Length ? "" : null;
                letrasTextBoxes[i].Visible = i < palabraSeleccionada.Length; // Muestra solo los necesarios
            }
        }

        private void bSort_Click(object sender, EventArgs e)
        {
            if (intentosRestantes <= 0)
            {
                MessageBox.Show("Has perdido. ¡Inténtalo de nuevo!");
                IniciarJuego();
                return;
            }

            // Obtiene la letra ingresada y la valida
            string letra = tLetraEndevinar.Text.ToUpper();
            tLetraEndevinar.Clear();

            if (string.IsNullOrEmpty(letra) || letra.Length != 1 || !Char.IsLetter(letra[0]))
            {
                MessageBox.Show("Introduce una única letra.");
                return;
            }

            bool letraEncontrada = false;
            for (int i = 0; i < palabraSeleccionada.Length; i++)
            {
                if (palabraSeleccionada[i] == letra[0] && letrasTextBoxes[i].Text == "")
                {
                    letrasTextBoxes[i].Text = letra;
                    letraEncontrada = true;
                    letrasEncontradas++;
                }
            }

            if (letraEncontrada)
            {
                // Verifica si ha ganado
                if (letrasEncontradas == palabraSeleccionada.Length)
                {
                    MessageBox.Show("¡Felicidades, has ganado!");
                    IniciarJuego();
                }
            }
            else
            {
                // Si falla, reduce los intentos restantes y muestra el siguiente paso del ahorcado
                intentosRestantes--;
                tCount.Text = intentosRestantes.ToString();
                contadorImagen++;
                MostrarParteDelAhorcado();

                if (intentosRestantes == 0)
                {
                    MessageBox.Show("Has perdido. La palabra era: " + palabraSeleccionada);
                    IniciarJuego();
                }
            }
        }

        private void MostrarParteDelAhorcado()
        {
            switch (contadorImagen)
            {
                case 1:
                    pImagen.Image = Properties.Resources._1;
                    break;
                case 2:
                    pImagen.Image = Properties.Resources._2;
                    break;
                case 3:
                    pImagen.Image = Properties.Resources._3;
                    break;
                case 4:
                    pImagen.Image = Properties.Resources._4;
                    break;
                case 5:
                    pImagen.Image = Properties.Resources._5;
                    break;
                case 6:
                    pImagen.Image = Properties.Resources._6;
                    break;
                case 7:
                    pImagen.Image = Properties.Resources._7;
                    break;
                case 8:
                    pImagen.Image = Properties.Resources._8;
                    break;
                case 9:
                    pImagen.Image = Properties.Resources._9;
                    break;
                case 10:
                    pImagen.Image = Properties.Resources._10;
                    break;
                default:
                    pImagen.Image = null; // En caso de que no haya más imágenes
                    break;
            }
        }
    }
}
