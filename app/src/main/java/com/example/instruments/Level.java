package com.example.instruments;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Level extends AppCompatActivity {
    private TextView x_text;
    private TextView y_text;
    private TextView z_text;

    private double x;
    private double y;
    private double z;

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private SensorEventListener accelerometerEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        x_text = (TextView) findViewById(R.id.x);
        y_text = (TextView) findViewById(R.id.y);
        z_text = (TextView) findViewById(R.id.z);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (accelerometerSensor == null) {
            Toast.makeText(this, "У устройства отсутствует акселерометр", Toast.LENGTH_SHORT).show();
        }

        accelerometerEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                x_text.setText(event.values[0] + "");
                y_text.setText(event.values[1] + "");
                z_text.setText(event.values[2] + "");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }
}