package com.example.blackjack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_Settings extends AppCompatActivity {

    private EditText editName, editMoney;
    private SeekBar betSeekBar;
    private TextView betValueText;
    private RadioButton bGreen, bBrown;
    private CheckBox showLogoCheckBox;
    private Button bOk, bCancel;
    private TextView title, subtitle;  // Títulos para el logo
    private int maxMoney = 500; // Valor predeterminado para dinero inicial

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cargar el layout de la actividad
        setContentView(R.layout.activity_main_settings);

        // Configura el Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#FF5733"));

        // Inicializar elementos de la vista
        instantElements();

        // Cargar configuraciones desde SharedPreferences
        loadSettingsFromPreferences();

        // Configurar el SeekBar y otros elementos
        configureSeekBar(maxMoney);

        // Configurar los listeners y validadores
        configureListeners();
    }

    private void instantElements() {
        // Inicializa los elementos de la vista
        editName = findViewById(R.id.editName);
        editMoney = findViewById(R.id.editMoney);
        betSeekBar = findViewById(R.id.Bet);
        betValueText = findViewById(R.id.textView4);
        bGreen = findViewById(R.id.bGreen);
        bBrown = findViewById(R.id.radioButton3);
        showLogoCheckBox = findViewById(R.id.checkBox);
        bOk = findViewById(R.id.bOk);
        bCancel = findViewById(R.id.bCancel);
        title = findViewById(R.id.Black);  // Título (Black)
        subtitle = findViewById(R.id.Jack);  // Subtítulo (Jack)
    }

    private void configureListeners() {
        // Validar y limitar a 3 dígitos el campo de dinero inicial
        if (editMoney != null) {
            editMoney.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        }

        // Configurar TextWatcher para actualizar el SeekBar cuando cambie el dinero
        if (editMoney != null) {
            editMoney.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try {
                        int newMoney = Integer.parseInt(s.toString());
                        if (newMoney > 0) {
                            betSeekBar.setMax(newMoney);
                            if (betSeekBar.getProgress() > newMoney) {
                                betSeekBar.setProgress(newMoney);
                            }
                        }
                    } catch (NumberFormatException e) {
                        betSeekBar.setMax(0);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }

        // Configurar el cambio de fondo con el RadioGroup
        RadioGroup backgroundGroup = findViewById(R.id.radioGroupBackground);
        if (backgroundGroup != null) {
            backgroundGroup.setOnCheckedChangeListener((group, checkedId) -> {
                if (checkedId == R.id.bGreen) {
                    findViewById(R.id.main).setBackgroundResource(R.drawable.background);
                } else if (checkedId == R.id.radioButton3) {
                    findViewById(R.id.main).setBackgroundResource(R.drawable.background_brown);
                }
            });
        }

        // Configurar botones OK y Cancel
        if (bOk != null) {
            bOk.setOnClickListener(v -> saveSettings(maxMoney));
        }

        if (bCancel != null) {
            bCancel.setOnClickListener(v -> {
                // Regresar sin guardar cambios
                startActivity(new Intent(MainActivity_Settings.this, MainActivity.class));
                finish();
            });
        }
    }

    private void configureSeekBar(int initialMoney) {
        if (initialMoney <= 0) {
            initialMoney = maxMoney;
        }

        betSeekBar.setMax(initialMoney);
        betSeekBar.setProgress(initialMoney);
        betValueText.setText("Min Bet: " + betSeekBar.getProgress());

        betSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                betValueText.setText("Min Bet: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void saveSettings(int finalInitialMoney) {
        // Validar el nombre del jugador
        String playerName = editName.getText().toString().trim();
        if (playerName.isEmpty()) {
            editName.setError("Por favor, ingresa un nombre válido.");
            return;
        }

        // Validar el dinero inicial
        int newInitialMoney;
        try {
            newInitialMoney = Integer.parseInt(editMoney.getText().toString());
            if (newInitialMoney <= 0) {
                newInitialMoney = finalInitialMoney;
            }
        } catch (NumberFormatException e) {
            newInitialMoney = finalInitialMoney;
        }

        // Validar la apuesta mínima
        int minBet = betSeekBar.getProgress();
        if (minBet > newInitialMoney) {
            minBet = newInitialMoney;
        }

        // Obtener preferencias restantes
        boolean showLogo = showLogoCheckBox.isChecked();
        String background = bGreen.isChecked() ? "green" : "brown";

        // Guardar configuraciones en SharedPreferences
        SharedPreferences preferences = getSharedPreferences("BlackJackSettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("playerName", playerName);
        editor.putInt("initialMoney", newInitialMoney);
        editor.putInt("minBet", minBet);
        editor.putBoolean("showLogo", showLogo);
        editor.putString("background", background);

        editor.apply(); // Guardar cambios

        // Iniciar MainActivity
        Intent intent = new Intent(MainActivity_Settings.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void loadSettingsFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("BlackJackSettings", MODE_PRIVATE);

        // Recuperar datos guardados
        String playerName = preferences.getString("playerName", "Player");
        int initialMoney = preferences.getInt("initialMoney", 500);
        int minBet = preferences.getInt("minBet", 0);
        boolean showLogo = preferences.getBoolean("showLogo", true);  // Recupera si se debe mostrar el logo
        String background = preferences.getString("background", "green");

        // Configurar elementos de UI
        editName.setText(playerName);
        editMoney.setText(String.valueOf(initialMoney));
        betValueText.setText("Min Bet: " + minBet);

        // Cambiar fondo
        if ("green".equals(background)) {
            findViewById(R.id.main).setBackgroundResource(R.drawable.background);
            bGreen.setChecked(true);  // Marcar RadioButton de fondo verde
            bBrown.setChecked(false);  // Desmarcar el RadioButton marrón
        } else if ("brown".equals(background)) {
            findViewById(R.id.main).setBackgroundResource(R.drawable.background_brown);
            bGreen.setChecked(false);  // Desmarcar RadioButton de fondo verde
            bBrown.setChecked(true);   // Marcar RadioButton marrón
        }

        // Mostrar u ocultar el logo
        if (showLogo) {
            if (title != null) {
                title.setVisibility(View.VISIBLE);  // Hacer visible el título
            }
            if (subtitle != null) {
                subtitle.setVisibility(View.VISIBLE);  // Hacer visible el subtítulo
            }
            showLogoCheckBox.setChecked(true);  // Marcar el CheckBox de mostrar logo
        } else {
            if (title != null) {
                title.setVisibility(View.GONE);  // Ocultar el título
            }
            if (subtitle != null) {
                subtitle.setVisibility(View.GONE);  // Ocultar el subtítulo
            }
            showLogoCheckBox.setChecked(false);  // Desmarcar el CheckBox de mostrar logo
        }

        // Configurar el SeekBar con el valor mínimo
        betSeekBar.setProgress(minBet);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el menú desde el archivo XML
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_game) {
            startActivity(new Intent(this, MainActivity.class));  // Iniciar juego
            return true;
        } else if (item.getItemId() == R.id.menu_settings) {
            startActivity(new Intent(this, MainActivity_Settings.class));  // Iniciar configuración
            return true;
        } else if (item.getItemId() == R.id.menu_about) {
            showAboutDialog();  // Mostrar el diálogo "About"
            return true;
        } else if (item.getItemId() == R.id.menu_quit) {
            showQuitDialog();  // Mostrar el diálogo de confirmación para salir
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // Mostrar el diálogo "About"
    private void showAboutDialog() {
        // Crea un contenedor para la imagen y el texto
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 20, 50, 20); // Espaciado opcional

        // Añade la imagen al contenedor
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.foto); // Asegúrate de que "foto.jpg" esté en res/drawable
        imageView.setLayoutParams(new LinearLayout.LayoutParams(300, 300)); // Tamaño de la imagen
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        layout.addView(imageView);

        // Añade el texto al contenedor
        TextView textView = new TextView(this);
        textView.setText("Aplicació feta per Pol_Collantes");
        textView.setTextSize(16);
        textView.setPadding(0, 20, 0, 0); // Espaciado opcional
        textView.setGravity(Gravity.CENTER);
        layout.addView(textView);

        // Crea y muestra el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About")
                .setView(layout)
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
