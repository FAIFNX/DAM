package com.example.juegosimon;
import android.annotation.TargetApi;
import android.media.AudioManager;
import android.media.Image;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.media.AudioAttributes; // Correct import!
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<SoundColor> numColors = new ArrayList<>();
    SoundPool soundPool;
    int sonAzul, sonVerde, sonRojo, sonAmarillo, sonError;
    int soundAzul = 0, soundGreen = 0, soundRed = 0, soundYellow = 0;
    ImageView ImageViewGreen, ImageViewRed, ImageViewYellow, ImageViewBlue, ImageViewPlay;
    ImageView ImagenViewGreenCheck, ImageViewRedCheck, ImageViewYellowCheck, ImageViewBlueCheck;

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.ibVerde)
            {
                soundPool.play(sonVerde, 1, 1, 0, 0, 1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ImageViewGreen.setImageResource(R.drawable.greenimg);
                    }
                } , 500);
                ImageViewGreen.setImageResource(R.drawable.greenimglight);
            }
            else if (view.getId() == R.id.ibRojo)
            {
                soundPool.play(sonRojo, 1, 1, 0, 0, 1);
                ImageViewRed.setImageResource(R.drawable.redimg);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ImageViewRed.setImageResource(R.drawable.redimg);
                    }
                },500);
                ImageViewRed.setImageResource(R.drawable.redimglight);
            }
            else if (view.getId() == R.id.ibAmarillo)
            {
                soundPool.play(sonRojo, 1, 1, 0, 0, 1);
                ImageViewYellow.setImageResource(R.drawable.yellowimg);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ImageViewYellow.setImageResource(R.drawable.yellowimg);
                    }
                },500);
                ImageViewYellow.setImageResource(R.drawable.yellowimglight);
            }
            else if (view.getId() == R.id.ibAzul)
            {
                soundPool.play(sonRojo, 1, 1, 0, 0, 1);
                ImageViewBlue.setImageResource(R.drawable.blueimg);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ImageViewBlue.setImageResource(R.drawable.blueimg);
                    }
                },500);
                ImageViewBlue.setImageResource(R.drawable.blueimglight);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSoundPool();
        initAtributes();
        instantElements();
        initListener();
    }

    private void initListener()
    {
        ImageViewGreen.setOnClickListener(listener);
        ImageViewRed.setOnClickListener(listener);
        ImageViewYellow.setOnClickListener(listener);
        ImageViewBlue.setOnClickListener(listener);
    }

    private void instantElements()
    {
        ImageViewGreen = findViewById(R.id.ibVerde);
        ImageViewRed = findViewById(R.id.ibRojo);
        ImageViewYellow = findViewById(R.id.ibAmarillo);
        ImageViewBlue = findViewById(R.id.ibAzul);
    }

    private void initAtributes()
    {
        sonAzul = soundPool.load(this, R.raw.sounds_01, 1);
        sonVerde = soundPool.load(this, R.raw.sounds_02, 1);
        sonRojo = soundPool.load(this, R.raw.sounds_03, 1);
        sonAmarillo = soundPool.load(this, R.raw.sounds_04, 1);
        sonError = soundPool.load(this, R.raw.error, 1);
    }

    protected void createSoundPool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            createNewSoundPool();
        } else {
            createOldSoundPool();
        }
    }
    /**
     * Create SoundPool for versions >= LOLLIPOP
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME) // Correct usage
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION) // Correct usage
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .setMaxStreams(5) // Add this line to set the maximum number of streams
                .build();
    }
    /**
     * Create SoundPool for deprecated versions < LOLLIPOP
     */
    @SuppressWarnings("deprecation")
    protected void createOldSoundPool() {
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
    }

    private ColorAudio getRandomColor() {
        ColorAudio colorAudio;
        int rnd = new Random().nextInt(numColors.size());
        if(rnd == 0) {
            colorAudio = new ColorAudio(SoundColor.AZUL.ordinal(),soundAzul);
        }else if(rnd == 1) {
            colorAudio = new ColorAudio(SoundColor.ROJO.ordinal(),soundRed);
        }else if(rnd == 2) {
            colorAudio = new ColorAudio(SoundColor.AMARILLO.ordinal(),soundYellow);
        }else{
            colorAudio = new ColorAudio (SoundColor.VERDE.ordinal(),soundGreen);
        }
        return colorAudio;
    }
}