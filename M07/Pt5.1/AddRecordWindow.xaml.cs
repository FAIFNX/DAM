using MySqlConnector;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace Pt5._1
{
    /// <summary>
    /// Lógica de interacción para AddRecordWindow.xaml
    /// </summary>
    public partial class AddRecordWindow : Window
    {
        //Se que tenia que cambiarlo pero no me acuerdo lo que tenia que poner para que funcionara en tu base de datos, perdona
        private string connectionString = "Server=localhost;Database=Pt5.1;Uid=pol;Pwd=123;";

        public AddRecordWindow()
        {
            InitializeComponent();
            LoadNextId();
        }

        private void LoadNextId()
        {
            try
            {
                using (MySqlConnection connection = new MySqlConnection(connectionString))
                {
                    connection.Open();

                    // Consulta para obtener el recuento total de registros
                    string query = "SELECT COUNT(*) FROM Clients";

                    using (MySqlCommand command = new MySqlCommand(query, connection))
                    {
                        int count = Convert.ToInt32(command.ExecuteScalar());
                        IdClientTextBox.Text = (count + 1).ToString(); // Asignar el siguiente ID disponible
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al cargar el próximo ID: {ex.Message}", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void TextNombre_TextChanged(object sender, TextChangedEventArgs e)
        {
            TextBox textBox = sender as TextBox;
            if (System.Text.RegularExpressions.Regex.IsMatch(textBox.Text, @"[^a-zA-Z\s]"))
            {
                MessageBox.Show("El nombre solo puede contener letras.", "Error de validación", MessageBoxButton.OK, MessageBoxImage.Error);
                textBox.Text = new string(textBox.Text.Where(c => char.IsLetter(c) || char.IsWhiteSpace(c)).ToArray());
                textBox.CaretIndex = textBox.Text.Length; // Mantener el cursor al final
            }
        }

        private void TextApellido_TextChanged(object sender, TextChangedEventArgs e)
        {
            TextBox textBox = sender as TextBox;
            if (System.Text.RegularExpressions.Regex.IsMatch(textBox.Text, @"[^a-zA-Z\s]"))
            {
                MessageBox.Show("El apellido solo puede contener letras.", "Error de validación", MessageBoxButton.OK, MessageBoxImage.Error);
                textBox.Text = new string(textBox.Text.Where(c => char.IsLetter(c) || char.IsWhiteSpace(c)).ToArray());
                textBox.CaretIndex = textBox.Text.Length; // Mantener el cursor al final
            }
        }

        private void TextDireccion_TextChanged(object sender, TextChangedEventArgs e)
        {
            /*
            TextBox textBox = sender as TextBox;
            if (System.Text.RegularExpressions.Regex.IsMatch(textBox.Text, @"[^a-zA-Z\s]"))
            {
                MessageBox.Show("El nombre solo puede contener letras.", "Error de validación", MessageBoxButton.OK, MessageBoxImage.Error);
                textBox.Text = new string(textBox.Text.Where(c => char.IsLetter(c) || char.IsWhiteSpace(c)).ToArray());
                textBox.CaretIndex = textBox.Text.Length; // Mantener el cursor al final
            }
            else
            if (System.Text.RegularExpressions.Regex.IsMatch(textBox.Text, @"\D"))
            {
                MessageBox.Show("El teléfono solo puede contener números.", "Error de validación", MessageBoxButton.OK, MessageBoxImage.Error);
                textBox.Text = new string(textBox.Text.Where(char.IsDigit).ToArray());
                textBox.CaretIndex = textBox.Text.Length; // Mantener el cursor al final
            }
            */
        }

        private void TextTelefono_TextChanged(object sender, TextChangedEventArgs e)
        {
            TextBox textBox = sender as TextBox;
            if (System.Text.RegularExpressions.Regex.IsMatch(textBox.Text, @"\D"))
            {
                MessageBox.Show("El teléfono solo puede contener números.", "Error de validación", MessageBoxButton.OK, MessageBoxImage.Error);
                textBox.Text = new string(textBox.Text.Where(char.IsDigit).ToArray());
                textBox.CaretIndex = textBox.Text.Length; // Mantener el cursor al final
            }
        }

        private void TextNombre_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
            // Permitir solo letras y espacios
            e.Handled = !System.Text.RegularExpressions.Regex.IsMatch(e.Text, @"^[a-zA-Z\s]$");
        }

        private void TextApellido_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
            // Permitir solo letras y espacios
            e.Handled = !System.Text.RegularExpressions.Regex.IsMatch(e.Text, @"^[a-zA-Z\s]$");
        }

        private void TextTelefono_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
            // Permitir solo números
            e.Handled = !System.Text.RegularExpressions.Regex.IsMatch(e.Text, @"^\d$");
        }


        private void Button_Click(object sender, object e)
        {
            añadirDatos();
        }

        public void añadirDatos()
        {
            try
            {
                using (MySqlConnection connection = new MySqlConnection(connectionString))
                {
                    connection.Open();

                    // Obtener los valores de los campos
                    string nombre = TextNombre.Text;
                    string apellido = TextApellido.Text;
                    string Direccion = TextDireccion.Text;
                    string telefono = TextTelefono.Text;

                    // Validaciones previas
                    if (string.IsNullOrWhiteSpace(nombre) || string.IsNullOrWhiteSpace(apellido) || string.IsNullOrWhiteSpace(Direccion) || string.IsNullOrWhiteSpace(telefono))
                    {
                        MessageBox.Show("Por favor, complete todos los campos.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                        return;
                    }

                    // Consulta de inserción
                    string query = "INSERT INTO Clients (nom_cli, cognom_cli, telf_cli, direccio_cli) VALUES (@Nom, @Apellido, @Telefono, @Correo)";

                    using (MySqlCommand command = new MySqlCommand(query, connection))
                    {
                        // Parámetros para evitar inyecciones SQL
                        command.Parameters.AddWithValue("@Nom", nombre);
                        command.Parameters.AddWithValue("@Apellido", apellido);
                        command.Parameters.AddWithValue("@Telefono", telefono);
                        command.Parameters.AddWithValue("@Correo", Direccion);

                        // Ejecutar la consulta
                        int result = command.ExecuteNonQuery();
                        if (result > 0)
                        {
                            MessageBox.Show("Datos insertados correctamente.", "Éxito", MessageBoxButton.OK, MessageBoxImage.Information);
                        }
                        else
                        {
                            MessageBox.Show("Hubo un problema al insertar los datos.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al insertar los datos: {ex.Message}", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void TextBox_TextChanged(object sender, TextChangedEventArgs e)
        {

        }
    }
}
