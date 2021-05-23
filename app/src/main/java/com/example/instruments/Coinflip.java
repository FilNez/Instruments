package com.example.instruments;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
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
                flipCoin();
            }
        });
    }

    public void flipCoin() {
        random_number = (int)(Math.random()*2);

        Animation zoom_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
        Animation zoom_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

        zoom_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                coin.setImageResource(images[random_number]);
                coin.startAnimation(zoom_in);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        coin.startAnimation(zoom_out);
    }
}