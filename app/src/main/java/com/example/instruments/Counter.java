package com.example.instruments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Counter extends AppCompatActivity {
    private Button plus_button;
    private Button minus_button;
    private Button reset_button;
    private TextView count_text;

    private int count;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COUNT = "count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        plus_button = (Button) findViewById(R.id.button_plus);
        minus_button = (Button) findViewById(R.id.button_minus);
        reset_button = (Button) findViewById(R.id.button_reset);
        count_text = (TextView) findViewById(R.id.count);

        count = loadCount();
        count_text.setText(String.valueOf(count));

        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_counter(1);
            }
        });
        minus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_counter(-1);
            }
        });
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_counter(0);
            }
        });
    }

    public void update_counter(int number) {
        if (number == 0) {
            count = 0;
            count_text.setText(String.valueOf(count));
        }
        else if (count + number > 9999) {
            Toast.makeText(Counter.this, "Number is too big", Toast.LENGTH_SHORT).show();
        }
        else if (count + number < -9999) {
            Toast.makeText(Counter.this, "Number is too small", Toast.LENGTH_SHORT).show();
        }
        else {
            count += number;
            count_text.setText(String.valueOf(count));
        }
        saveCount();
    }

    public void saveCount() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(COUNT, count);

        editor.apply();
    }

    public int loadCount() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        return sharedPreferences.getInt(COUNT, 0);
    }
}