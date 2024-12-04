package com.example.blackjack;
import static android.os.SystemClock.sleep;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class MainActivity extends AppCompatActivity {
    LinearLayout dealerLayout, playerLayout, logoLayout; // Nuevo LinearLayout para el logo
    TextView dealerScoreText, playerScoreText, playerMoneyText, resultText, title, subtitle, textPoint, menuText;
    EditText betAmountText;
    Button hitButton, standButton, menuButton_1, menuButton_2;
    ImageView menuImage;

    int playerScore = 0;
    int initialMoney;
    int dealerScore = 0;
    int playerMoney = 500;
    int betAmount = 0;
    int minBet = 0;
    ArrayList<Integer> deck = new ArrayList<>();
    boolean dealerTurn = false;
    boolean dealerCardFlipped = false;
    int count = 0, count2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Cargar el layout de la actividad

        // Configura el Toolbar como la ActionBar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#FF5733"));

        // Instanciar elementos
        instantElements();

        // Cargar configuraciones desde SharedPreferences
        loadSettingsFromPreferences();

        // Configurar el juego
        prepareListener();
        prepareGame();
        dealInitialCards();
    }


    private void instantElements() {
        dealerLayout = findViewById(R.id.linearLayoutDealer);
        playerLayout = findViewById(R.id.linearLayoutPlayer);
        logoLayout = findViewById(R.id.logo); // LinearLayout para el logo
        dealerScoreText = findViewById(R.id.Dcontador);
        playerScoreText = findViewById(R.id.Ucontador);
        playerMoneyText = findViewById(R.id.Cmoney);
        resultText = findViewById(R.id.textoapuesta);
        betAmountText = findViewById(R.id.EdBet);
        hitButton = findViewById(R.id.Bcard);
        standButton = findViewById(R.id.Bstand);
        title = findViewById(R.id.Black);
        subtitle = findViewById(R.id.Jack);
        textPoint = findViewById(R.id.textPoints);
        menuText = findViewById(R.id.Menu_as_Text);
        menuImage = findViewById(R.id.Menu_as);
        menuButton_1 = findViewById(R.id.Menu_as_1);
        menuButton_2 = findViewById(R.id.Menu_as_11);
    }

    private void prepareListener() {

        // Botón "Hit" (Pedir carta)
        hitButton.setOnClickListener(v -> {
            String betText = betAmountText.getText().toString().trim();

            try {
                betAmount = Integer.parseInt(betText);

                if (betAmount > playerMoney) {
                    Toast.makeText(this, "No tienes suficiente dinero!", Toast.LENGTH_SHORT).show();
                } else if (betAmount < minBet) {
                    Toast.makeText(this, "La apuesta debe ser al menos " + minBet, Toast.LENGTH_SHORT).show();
                } else if (betAmount <= 0) {
                    Toast.makeText(this, "La apuesta no puede ser 0 o negativa!", Toast.LENGTH_SHORT).show();
                    standButton.setEnabled(false);
                } else {
                    standButton.setEnabled(true); // Activar "Stand" si la apuesta es válida
                    dealCardToPlayer();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Ingresa un valor numérico válido!", Toast.LENGTH_SHORT).show();
                standButton.setEnabled(false);
            }
        });

        // Botón "Stand" (Quedarse)
        standButton.setOnClickListener(v -> {
            String betText = betAmountText.getText().toString().trim();

                betAmount = Integer.parseInt(betText);
                if (betAmount > playerMoney) {
                    Toast.makeText(this, "No tienes suficiente dinero!", Toast.LENGTH_SHORT).show();
                } else if (betAmount <= 0) {
                    Toast.makeText(this, "La apuesta no puede ser 0 o negativa!", Toast.LENGTH_SHORT).show();
                } else if (!dealerTurn) {
                    dealerTurn = true;
                    hitButton.setEnabled(false);  // Deshabilitar el botón "Hit"
                    standButton.setEnabled(false);  // Deshabilitar el botón "Stand"
                    dealerPlays();  // El dealer juega después de que el jugador hace "Stand"
                }

                else {
                    // Activamos el botón de "Stand" si la apuesta es mayor a 0
                    standButton.setEnabled(true);
                    dealCardToPlayer();  // Repartir la carta al jugador
                }
        });

        menuButton_1.setOnClickListener(v -> {
            uno();
            closeMenu();
        });

        menuButton_2.setOnClickListener(v -> {
            once();
            closeMenu();
        });
    }

    private void prepareGame() {
        menuButton_1.setVisibility(View.GONE);
        menuButton_2.setVisibility(View.GONE);
        menuImage.setVisibility(View.GONE);
        menuText.setVisibility(View.GONE);
        resultText.setText("MAKE YOUR BET!");
        playerMoneyText.setText(String.valueOf(playerMoney));
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(i);
            }
        }
        shuffleDeck();
    }

    private void shuffleDeck() {
        Random rand = new Random();
        for (int i = 0; i < deck.size(); i++) {
            int randomIndex = rand.nextInt(deck.size());
            int temp = deck.get(i);
            deck.set(i, deck.get(randomIndex));
            deck.set(randomIndex, temp);
        }
    }

    private void dealInitialCards() {
        Random rand = new Random();

        // Repartir dos cartas al jugador
        for (int i = 0; i < 2; i++) {
            dealCardToPlayer();
        }

        // Repartir dos cartas al dealer, con la segunda carta oculta
        int dealerCardType = rand.nextInt(4) + 1;
        int dealerCardValue = rand.nextInt(13) + 1;
        dealerScore += Math.min(dealerCardValue, 10);
        dealerScoreText.setText(String.valueOf(dealerScore));

        FrameLayout dealerCardLayout = createCardLayout(dealerCardType, dealerCardValue, false);
        dealerLayout.addView(dealerCardLayout);

        // La carta oculta del dealer
        FrameLayout dealerCardBackLayout = createCardLayout(0, 0, true);
        dealerLayout.addView(dealerCardBackLayout);
    }

    private FrameLayout createCardLayout(int cardType, int cardValue, boolean isHidden) {
        FrameLayout cardLayout = new FrameLayout(this);
        ImageView cardBackground = new ImageView(this);
        cardBackground.setImageResource(R.drawable.front);
        cardBackground.setLayoutParams(new FrameLayout.LayoutParams(150, 250));
        cardBackground.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        if (isHidden) {
            ImageView dealerCardBackImage = new ImageView(this);
            dealerCardBackImage.setImageResource(R.drawable.back);
            dealerCardBackImage.setLayoutParams(new FrameLayout.LayoutParams(150, 250));
            cardLayout.addView(dealerCardBackImage);
        } else {
            ImageView cardTypeImage = new ImageView(this);
            cardTypeImage.setImageResource(getCardTypeImage(cardType));
            FrameLayout.LayoutParams typeParams = new FrameLayout.LayoutParams(75, 75);
            typeParams.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
            typeParams.topMargin = 50;
            cardTypeImage.setLayoutParams(typeParams);

            ImageView cardValueImage = new ImageView(this);
            cardValueImage.setImageResource(getCardValueImage(cardValue));
            FrameLayout.LayoutParams valueParams = new FrameLayout.LayoutParams(37, 37);
            valueParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
            valueParams.bottomMargin = 50;
            cardValueImage.setLayoutParams(valueParams);

            cardLayout.addView(cardBackground);
            cardLayout.addView(cardTypeImage);
            cardLayout.addView(cardValueImage);
        }

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(150, 250);
        params.setMargins(10, 0, 10, 0);
        cardLayout.setLayoutParams(params);

        return cardLayout;
    }

    private void dealCardToPlayer() {
        count ++;
        Random rand = new Random();
        int cardType = rand.nextInt(4) + 1;
        int cardValue = rand.nextInt(13) + 1;
        playerScoreText.setText(String.valueOf(playerScore));
        if(cardValue == 1 && playerScore + 11 <= 21)
        {
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            openMenu();
            FrameLayout cardLayout = createCardLayout(cardType, cardValue, false);
            playerLayout.addView(cardLayout);
        }
        else if(cardValue == 1 && playerScore + 11 > 21)
        {
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            openMenu();
            FrameLayout cardLayout = createCardLayout(cardType, cardValue, false);
            playerLayout.addView(cardLayout);
        }
        else
        {
            playerScore += Math.min(cardValue, 10);
            playerScoreText.setText(String.valueOf(playerScore));
            textPoint.setText(String.valueOf(playerScore));
            FrameLayout cardLayout = createCardLayout(cardType, cardValue, false);
            playerLayout.addView(cardLayout);
            checkPlayerBust();
        }
    }

    public void uno()
    {
        playerScoreText.setText(String.valueOf(playerScore));
        //standButton.setEnabled(true);
        //hitButton.setEnabled(true);
        textPoint.setText(String.valueOf(playerScore + 10));
        playerScore += 1;
        textPoint.setEnabled(true);
        playerScoreText.setText(String.valueOf(playerScore));
        checkPlayerBust();
    }

    public void once()
    {
        if(playerScore + 11 >= 21)
        {
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            playerScoreText.setText(String.valueOf(playerScore));
            //standButton.setEnabled(true);
            //hitButton.setEnabled(true);
            textPoint.setText(String.valueOf(playerScore + 10));
            playerScore += 11;
            textPoint.setEnabled(true);
            playerScoreText.setText(String.valueOf(playerScore));
            checkPlayerBust();
        }
        else {
            playerScoreText.setText(String.valueOf(playerScore));
            //standButton.setEnabled(true);
            //hitButton.setEnabled(true);
            textPoint.setText(String.valueOf(playerScore + 10));
            playerScore += 11;
            textPoint.setEnabled(true);
            playerScoreText.setText(String.valueOf(playerScore));
            checkPlayerBust();
        }
    }

    private void dealerPlays() {
        if (!dealerCardFlipped) {
            standButton.setEnabled(false);
            hitButton.setEnabled(false);
            FrameLayout dealerCardBackLayout = (FrameLayout) dealerLayout.getChildAt(1);
            dealerCardBackLayout.removeAllViews();
            int cardType = 1;
            int cardValue = 1;
            dealerScore += Math.min(cardValue, 10);
            dealerScoreText.setText(String.valueOf(dealerScore));
            FrameLayout flippedDealerCardLayout = createCardLayout(cardType, cardValue, false);
            dealerLayout.removeView(dealerCardBackLayout);
            dealerLayout.addView(flippedDealerCardLayout);
            dealerCardFlipped = true;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dealerScore < playerScore && dealerScore < 21) {
                    Random rand = new Random();
                    int cardType = rand.nextInt(4) + 1;
                    int cardValue = rand.nextInt(13) + 1;

                    int cardValueScore = Math.min(cardValue, 10);
                    dealerScore += cardValueScore;
                    dealerScoreText.setText(String.valueOf(dealerScore));

                    FrameLayout dealerCardLayout = createCardLayout(cardType, cardValue, false);
                    dealerLayout.addView(dealerCardLayout);

                    dealerPlays();
                }
                else
                {
                    checkGameResult();
                }
            }
        }, 500);
    }

    private void checkPlayerBust() {
        if (playerScore > 21) {
            playerMoney -= betAmount;
            playerMoneyText.setText(String.valueOf(playerMoney));
            resultText.setText("The dealer wins!");
            hitButton.setEnabled(false);
            standButton.setEnabled(false);

            // Pausa para mostrar la última carta antes de reiniciar el juego
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    hitButton.setEnabled(false);
                    standButton.setEnabled(false);
                    resetGame();
                }
            }, 2000); // Espera de 2 segundos antes de reiniciar el juego
        }
        else if (count == 2 && playerScore == 21)
        {
            playerMoneyText.setText(String.valueOf(playerMoney));
            resultText.setText("Black Jack!");
            playerMoney += (betAmount * 1.5) * 2;
            betAmount = 0;
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    hitButton.setEnabled(false);
                    standButton.setEnabled(false);
                    resetGame();
                }
            }, 2000);
        }
        else if(playerScore == 21)
        {
            hitButton.setEnabled(false);
        }
    }


    private void checkGameResult() {
        String result = "";
        if(dealerScore > 21)
        {
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            resultText.setText("Player win!");
            result = "win";
            playerMoney += betAmount * 2;
            betAmount = 0;
        }
        else if(dealerScore > playerScore)
        {
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            resultText.setText("Dealer wins!");
            result = "lose";
            playerMoney -= betAmount;
            betAmount = 0;
        }
        else if(dealerScore == playerScore)
        {
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            result = "draw";
            resultText.setText("Draw!");
            betAmount = 0;
        }
        saveGameResult(result);

        playerMoneyText.setText(String.valueOf(playerMoney));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                resetGame();
            }
        }, 1000); // 1000 milisegundos de espera
    }

    private void saveGameResult(String result) {
        String fileName = "lastResults.txt";
        String data = "Player: " + playerScore + "; Dealer: " + dealerScore + "; " + result + "\n";

        try (FileOutputStream fos = openFileOutput(fileName, MODE_APPEND)) {
            fos.write(data.getBytes());
        } catch (IOException e) {
            Toast.makeText(this, "Error al guardar los resultados.", Toast.LENGTH_SHORT).show();
        }
    }



    private void loadSettingsFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("BlackJackSettings", MODE_PRIVATE);

        // Recuperar datos guardados
        String playerName = preferences.getString("playerName", "Player");
        playerMoney = preferences.getInt("initialMoney", 500);
        minBet = preferences.getInt("minBet", 0);
        boolean showLogo = preferences.getBoolean("showLogo", true);
        String background = preferences.getString("background", "green");

        // Configurar elementos de UI
        TextView playerNameTextView = findViewById(R.id.Tplayer);
        playerNameTextView.setText(playerName);
        playerMoneyText.setText(String.valueOf(playerMoney));
        betAmountText.setText(String.valueOf(minBet));

        // Cambiar fondo
        if ("green".equals(background)) {
            findViewById(R.id.main).setBackgroundResource(R.drawable.background);
        } else if ("brown".equals(background)) {
            findViewById(R.id.main).setBackgroundResource(R.drawable.background_brown);
        }

        // Mostrar u ocultar el logo
        if (showLogo) {
            title.setVisibility(View.VISIBLE);
            subtitle.setVisibility(View.VISIBLE);
        } else {
            title.setVisibility(View.GONE);
            subtitle.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveGameState();
    }

    private void saveGameState() {
        SharedPreferences preferences = getSharedPreferences("BlackJackSettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Guardar el dinero restante del jugador
        editor.putInt("initialMoney", playerMoney);

        editor.apply(); // Guardar cambios
    }

    private void resetGame() {
        playerMoneyText.setText(String.valueOf(playerMoney));
        playerScore = 0;
        dealerScore = 0;
        playerScoreText.setText("0");
        dealerScoreText.setText("0");
        playerLayout.removeAllViews();
        dealerLayout.removeAllViews();
        deck.clear();
        standButton.setEnabled(true);
        hitButton.setEnabled(true);
        prepareGame();
        dealerCardFlipped = false;
        dealInitialCards();
        resultText.setText("MAKE YOUR BET!");
        dealerTurn = false;
        count = 0;
    }

    private int getCardValueImage(int cardValue) {
        switch (cardValue) {
            case 1: return R.drawable.a;
            case 2: return R.drawable.c2;
            case 3: return R.drawable.c3;
            case 4: return R.drawable.c4;
            case 5: return R.drawable.c5;
            case 6: return R.drawable.c6;
            case 7: return R.drawable.c7;
            case 8: return R.drawable.c8;
            case 9: return R.drawable.c9;
            case 10: return R.drawable.c10;
            case 11: return R.drawable.j;
            case 12: return R.drawable.q;
            case 13: return R.drawable.k;
            default: return R.drawable.a;
        }
    }

    private int getCardTypeImage(int cardType) {
        switch (cardType) {
            case 1: return R.drawable.hearts;
            case 2: return R.drawable.diamond;
            case 3: return R.drawable.spades;
            case 4: return R.drawable.clubs;
            default: return R.drawable.front;
        }
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

    private void openMenu()
    {
        menuButton_1.setVisibility(View.VISIBLE);
        menuButton_2.setVisibility(View.VISIBLE);
        menuImage.setVisibility(View.VISIBLE);
        menuText.setVisibility(View.VISIBLE);
        standButton.setEnabled(false);
        hitButton.setEnabled(false);
    }

    private void closeMenu()
    {
        menuButton_1.setVisibility(View.GONE);
        menuButton_2.setVisibility(View.GONE);
        menuImage.setVisibility(View.GONE);
        menuText.setVisibility(View.GONE);
        standButton.setEnabled(true);
        hitButton.setEnabled(true);
    }

}
