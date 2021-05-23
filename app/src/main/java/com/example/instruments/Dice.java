package com.example.instruments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Dice extends AppCompatActivity {

    private ImageView dice_1;
    private ImageView dice_2;
    private Button flip_dice_button;

    private int random_number_1;
    private int random_number_2;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String RANDOM_NUMBER_1 = "random_number_1";
    public static final String RANDOM_NUMBER_2 = "random_number_2";

    int[] images = {R.drawable.ic_dado_1, R.drawable.ic_dado_2, R.drawable.ic_dado_3, R.drawable.ic_dado_4, R.drawable.ic_dado_5, R.drawable.ic_dado_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        dice_1 = (ImageView) findViewById(R.id.dice_1);
        dice_2 = (ImageView) findViewById(R.id.dice_2);

        flip_dice_button = (Button) findViewById(R.id.flip_dice);

        loadData();
        updateImage();

        flip_dice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                random_number_1 = (int)((Math.random()*6));
                random_number_2 = (int)((Math.random()*6));

                updateImage();
            }
        });
    }

    public void updateImage() {
        dice_1.setImageResource(images[random_number_1]);
        dice_2.setImageResource(images[random_number_2]);

        /*
        dice_1.startAnimation(AnimationUtils.loadAnimation(
                getApplicationContext(),
                R.anim.zoom_in
        ));
        dice_2.startAnimation(AnimationUtils.loadAnimation(
                getApplicationContext(),
                R.anim.zoom_in
        ));*/

        saveData();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(RANDOM_NUMBER_1, random_number_1);
        editor.putInt(RANDOM_NUMBER_2, random_number_2);

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        random_number_1 =  sharedPreferences.getInt(RANDOM_NUMBER_1, 0);
        random_number_2 =  sharedPreferences.getInt(RANDOM_NUMBER_2, 0);
    }

}