package com.example.instruments;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Coinflip extends AppCompatActivity {

    private ImageView coin;
    private Button flip_coin_button;

    private int random_number;

    int[] images = {R.drawable.ic_heads, R.drawable.ic_tails};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coinflip);

        coin = (ImageView) findViewById(R.id.coin);

        flip_coin_button = (Button) findViewById(R.id.flip_coin_button);

        flip_coin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                random_number = (int)(Math.random()*2);

                coin.setImageResource(images[random_number]);
            }
        });
    }
}