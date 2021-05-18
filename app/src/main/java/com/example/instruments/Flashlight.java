package com.example.instruments;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Flashlight extends AppCompatActivity {

    private ImageButton switchOffButton;
    private ImageButton switchOnButton;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);

        switchOffButton = (ImageButton) findViewById(R.id.flashlight_is_on);
        switchOnButton = (ImageButton) findViewById(R.id.flashlight_is_off);
        text = (TextView) findViewById(R.id.flashlight_condition);

        switchOff();

        switchOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchOn();
            }
        });

        switchOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchOff();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void switchOn() {
        text.setText("ON");
        switchOffButton.setVisibility(View.VISIBLE);
        switchOnButton.setVisibility(View.GONE);

        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];

            cameraManager.setTorchMode(cameraId, true);
        }
        catch (Exception e)
        {
            Toast.makeText(Flashlight.this, "Flashlight error", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void switchOff() {
        text.setText("OFF");
        switchOnButton.setVisibility(View.VISIBLE);
        switchOffButton.setVisibility(View.GONE);

        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
        }
        catch (Exception e)
        {
            Toast.makeText(Flashlight.this, "Flashlight error", Toast.LENGTH_SHORT).show();
        }
    }

}