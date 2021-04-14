package com.example.instruments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dice extends AppCompatActivity {

    private TextView dice_1;
    private TextView dice_2;
    private Button flip_dice_button;

    private int random_number_1;
    private int random_number_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        dice_1 = (TextView) findViewById(R.id.dice_1);
        dice_2 = (TextView) findViewById(R.id.dice_2);

        flip_dice_button = (Button) findViewById(R.id.flip_dice);

        flip_dice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dice_1.setText(((int)(Math.random()*6) + 1) + "");
                dice_2.setText(((int)(Math.random()*6) + 1) + "");
            }
        });
    }

}