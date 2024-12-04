package com.example.blackjack;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity_Inicio extends AppCompatActivity {
    private Button buttonInicio, buttonSettings; // Nombres más claros
    private View.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inicio);  // Cargar el layout de la actividad

        // Configura el Toolbar como la ActionBar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#FF5733"));


        instanciarElements();  // Instanciar los botones
        prepareListeners();    // Preparar el listener
        setButtonListeners();  // Asignar los listeners a los botones
    }

    private void prepareListeners() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Comparación directa de vistas
                if (v == buttonInicio) {
                    changeActivityGame();  // Cambiar a la actividad del juego
                } else if (v == buttonSettings) {
                    changeActivitySettings();  // Cambiar a la actividad de configuración
                }
            }
        };
    }

    private void instanciarElements() {
        // Asegúrate de que los botones existen en el layout
        buttonSettings = findViewById(R.id.Bsettings);
        buttonInicio = findViewById(R.id.Binicio);
    }

    private void changeActivityGame() {
        // Cambiar a la actividad del juego
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void changeActivitySettings() {
        // Cambiar a la actividad de configuración
        Intent intent = new Intent(this, MainActivity_Settings.class);
        startActivity(intent);
    }

    private void setButtonListeners() {
        // Asignar el listener a los botones
        buttonInicio.setOnClickListener(listener);
        buttonSettings.setOnClickListener(listener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el menú desde el archivo XML
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Verifica el ID del ítem seleccionado
        if (item.getItemId() == R.id.menu_game) {
            // Navegar a la actividad del juego
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (item.getItemId() == R.id.menu_settings) {
            // Navegar a la pantalla de configuración
            startActivity(new Intent(this, MainActivity_Settings.class));
            return true;
        } else if (item.getItemId() == R.id.menu_about) {
            // Mostrar el diálogo "About"
            showAboutDialog();
            return true;
        } else if (item.getItemId() == R.id.menu_quit) {
            // Mostrar el diálogo de confirmación "Quit"
            showQuitDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // Mostrar el diálogo "About"
    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About")
                .setMessage("Aplicació feta per Pol_Collantes")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

    // Mostrar el diálogo "Quit"
    private void showQuitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quit")
                .setMessage("¿Estás seguro de que quieres salir?")
                .setPositiveButton("Yes", (dialog, which) -> finish())  // Cerrar la actividad
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }
}