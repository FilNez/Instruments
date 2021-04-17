package com.example.instruments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity {

    private Button flashlight_button;
    private Button coinflip_button;
    private Button dice_button;
    private Button counter_button;

    private Context context;
    private PackageManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashlight_button = (Button) findViewById(R.id.flashlight_app);
        coinflip_button = (Button) findViewById(R.id.coinflip_app);
        dice_button = (Button) findViewById(R.id.dice_app);
        counter_button = (Button) findViewById(R.id.counter_app);

        context = this;
        pm = context.getPackageManager();

        Dexter.withContext(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                flashlight_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openFlashlightActivity();
                    }
                });
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(MainActivity.this, "Camera permission required.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

            }
        }).check();

        coinflip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCoinflipActivity();
            }
        });

        dice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiceActivity();
            }
        });

        counter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCounterActivity();
            }
        });
    }

    public void openFlashlightActivity() {
        if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Intent intent = new Intent(this, Flashlight.class);
            startActivity(intent);
        }
    }

    public void openCoinflipActivity() {
        Intent intent = new Intent(this, Coinflip.class);
        startActivity(intent);
    }

    public void openDiceActivity() {
        Intent intent = new Intent(this, Dice.class);
        startActivity(intent);
    }

    public void openCounterActivity() {
        Intent intent = new Intent(this, Counter.class);
        startActivity(intent);
    }
}