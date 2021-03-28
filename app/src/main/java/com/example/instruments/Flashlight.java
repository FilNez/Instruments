package com.example.instruments;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Flashlight extends AppCompatActivity {

    private ImageButton switchOff;
    private ImageButton switchOn;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);

        switchOff = (ImageButton) findViewById(R.id.flashlight_is_on);
        switchOn = (ImageButton) findViewById(R.id.flashlight_is_off);
        text = (TextView) findViewById(R.id.flashlight_condition);

        switchOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("ON");
                switchOff.setVisibility(View.VISIBLE);
                switchOn.setVisibility(View.GONE);

                CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

                try {
                    String cameraId = cameraManager.getCameraIdList()[0];
                    cameraManager.setTorchMode(cameraId, true);
                }
                catch (CameraAccessException e)
                {}
            }
        });

        switchOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("OFF");
                switchOff.setVisibility(View.GONE);
                switchOn.setVisibility(View.VISIBLE);

                CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

                try {
                    String cameraId = cameraManager.getCameraIdList()[0];
                    cameraManager.setTorchMode(cameraId, false);
                }
                catch (CameraAccessException e)
                {}
            }
        });
    }


}