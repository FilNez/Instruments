package com.example.instruments;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Flashlight extends AppCompatActivity {

    private ImageButton switchOff;
    private ImageButton switchOn;
    private TextView text;
    Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);

        switchOff = (ImageButton) findViewById(R.id.flashlight_is_on);
        switchOn = (ImageButton) findViewById(R.id.flashlight_is_off);
        text = (TextView) findViewById(R.id.flashlight_condition);

        camera = Camera.open();
        final Camera.Parameters parameters = camera.getParameters();

        switchOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("ON");
                switchOff.setVisibility(View.VISIBLE);
                switchOn.setVisibility(View.GONE);

                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.startPreview();
            }
        });

        switchOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("OFF");
                switchOff.setVisibility(View.GONE);
                switchOn.setVisibility(View.VISIBLE);

                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(parameters);
                camera.stopPreview();
            }
        });
    }


}