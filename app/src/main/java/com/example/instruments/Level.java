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

    private double x_acceleration;
    private double y_acceleration;
    private double z_acceleration;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private SensorEventListener accelerometerEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            x_acceleration = event.values[0];
            y_acceleration = event.values[1];
            z_acceleration = event.values[2];

            changeRoundsPosition(x_acceleration, y_acceleration, z_acceleration);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        x_text = (TextView) findViewById(R.id.x);
        y_text = (TextView) findViewById(R.id.y);
        z_text = (TextView) findViewById(R.id.z);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (accelerometer == null) {
            Toast.makeText(this, "У устройства отсутствует акселерометр", Toast.LENGTH_SHORT).show();
        }

        sensorManager.registerListener(accelerometerEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(accelerometerEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(accelerometerEventListener);
    }

    private void changeRoundsPosition(double x, double y, double z) {
        x_text.setText((double) Math.round(x*1000)/1000 + "");
        y_text.setText((double) Math.round(y*1000)/1000 + "");
        z_text.setText((double) Math.round(z*1000)/1000 + "");
    }
}