package com.example.instruments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Coinflip extends AppCompatActivity {

    private TextView tails_text;
    private TextView heads_text;
    private Button flip_coin_button;

    private int random_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coinflip);

        tails_text = (TextView) findViewById(R.id.tails);
        heads_text = (TextView) findViewById(R.id.heads);

        flip_coin_button = (Button) findViewById(R.id.flip_coin_button);

        flip_coin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                random_number = (int)(Math.random()*2);

                if (random_number == 1) {
                    setHeads();
                }
                else {
                    setTails();
                }
            }
        });
    }

    public void setTails() {
        tails_text.setVisibility(View.VISIBLE);
        heads_text.setVisibility(View.GONE);
    }

    public void setHeads() {
        tails_text.setVisibility(View.GONE);
        heads_text.setVisibility(View.VISIBLE);
    }
}