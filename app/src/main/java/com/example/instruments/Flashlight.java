package com.example.instruments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Flashlight extends AppCompatActivity {

    private ImageButton switchButton;
    private TextView text;

    private boolean flashlight_is_on = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);

        switchButton = (ImageButton) findViewById(R.id.flashlight_switch);
        text = (TextView) findViewById(R.id.flashlight_condition);

        switchOff();

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flashlight_is_on)
                    switchOff();
                else
                    switchOn();
            }
        });
    }

    public void switchOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);

            flashlight_is_on = true;

            text.setText("ON");
            switchButton.setBackgroundColor(Color.GREEN);

        }
        catch (Exception e)
        {
            Toast.makeText(Flashlight.this, "Flashlight error", Toast.LENGTH_SHORT).show();
        }
    }

    public void switchOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);

            flashlight_is_on = false;

            text.setText("OFF");
            switchButton.setBackgroundColor(Color.WHITE);

        }
        catch (Exception e)
        {
            Toast.makeText(Flashlight.this, "Flashlight error", Toast.LENGTH_SHORT).show();
        }
    }

}