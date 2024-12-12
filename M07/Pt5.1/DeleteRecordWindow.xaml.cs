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
    /// Lógica de interacción para DeleteRecordWindow.xaml
    /// </summary>
    public partial class DeleteRecordWindow : Window
    {
        private string connectionString = "Server=localhost;Database=Pt5.1;Uid=pol;Pwd=123;";

        public DeleteRecordWindow()
        {
            InitializeComponent();
        }

        private void buttonBuscar_Click(object sender, RoutedEventArgs e)
        {
            string idClient = textId.Text; // Obtener el ID ingresado

            if (string.IsNullOrWhiteSpace(idClient))
            {
                MessageBox.Show("Por favor ingrese un ID.", "Advertencia", MessageBoxButton.OK, MessageBoxImage.Warning);
                return;
            }

            try
            {
                using (MySqlConnection connection = new MySqlConnection(connectionString))
                {
                    connection.Open();

                    // Consulta para obtener los datos del cliente por ID
                    string query = "SELECT * FROM Clients WHERE idClient = @idClient";

                    using (MySqlCommand command = new MySqlCommand(query, connection))
                    {
                        command.Parameters.AddWithValue("@idClient", idClient);

                        using (MySqlDataReader reader = command.ExecuteReader())
                        {
                            if (reader.Read())
                            {
                                // Si se encuentra un registro, llenar los campos con los datos
                                NombreTextBox.Text = reader["nom_cli"].ToString();
                                ApellidoTextBox.Text = reader["cognom_cli"].ToString();
                                DireccionTextBox.Text = reader["direccio_cli"].ToString();
                                TelefonoTextBox.Text = reader["telf_cli"].ToString();
                            }
                            else
                            {
                                MessageBox.Show("No se encontró ningún cliente con ese ID.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                            }
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al buscar el cliente: {ex.Message}", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void buttonDelete_Click(object sender, RoutedEventArgs e)
        {
            string idClient = textId.Text; // Obtener el ID ingresado

            if (string.IsNullOrWhiteSpace(idClient))
            {
                MessageBox.Show("Por favor ingrese un ID para eliminar.", "Advertencia", MessageBoxButton.OK, MessageBoxImage.Warning);
                return;
            }

            try
            {
                using (MySqlConnection connection = new MySqlConnection(connectionString))
                {
                    connection.Open();

                    // Consulta para eliminar el cliente por ID
                    string query = "DELETE FROM Clients WHERE idClient = @idClient";

                    using (MySqlCommand command = new MySqlCommand(query, connection))
                    {
                        command.Parameters.AddWithValue("@idClient", idClient);

                        int rowsAffected = command.ExecuteNonQuery();
                        if (rowsAffected > 0)
                        {
                            MessageBox.Show("Registro eliminado con éxito.", "Información", MessageBoxButton.OK, MessageBoxImage.Information);
                            ClearFields(); // Limpiar los campos
                        }
                        else
                        {
                            MessageBox.Show("No se encontró ningún cliente con ese ID para eliminar.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al eliminar el cliente: {ex.Message}", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }
        private void ClearFields()
        {
            // Limpiar todos los campos después de eliminar o buscar
            textId.Clear();
            NombreTextBox.Clear();
            ApellidoTextBox.Clear();
            DireccionTextBox.Clear();
            TelefonoTextBox.Clear();
        }

        private void textId_TextChanged(object sender, TextChangedEventArgs e)
        {
            TextBox textBox = sender as TextBox;
            if (System.Text.RegularExpressions.Regex.IsMatch(textBox.Text, @"\D"))
            {
                MessageBox.Show("El teléfono solo puede contener números.", "Error de validación", MessageBoxButton.OK, MessageBoxImage.Error);
                textBox.Text = new string(textBox.Text.Where(char.IsDigit).ToArray());
                textBox.CaretIndex = textBox.Text.Length; // Mantener el cursor al final
            }
        }
    }
}
