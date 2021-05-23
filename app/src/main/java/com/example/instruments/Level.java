package com.example.instruments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class Level extends AppCompatActivity {

    private double x_acceleration;
    private double y_acceleration;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private SensorEventListener accelerometerEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            x_acceleration = (double) Math.round(event.values[0]*100)/100;
            y_acceleration = (double) Math.round(event.values[1]*100)/100;

            ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.constraint_layout);
            ConstraintSet cs = new ConstraintSet();
            cs.clone(cl);

            cs.setVerticalBias(R.id.left_point, (float) ((x_acceleration + 9.8)/19.6));
            cs.setHorizontalBias(R.id.right_point, (float) ((y_acceleration + 9.8)/19.6));

            cs.applyTo(cl);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (accelerometer == null) {
            Toast.makeText(this, "У устройства отсутствует акселерометр", Toast.LENGTH_SHORT).show();
        }

        sensorManager.registerListener(accelerometerEventListener, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(accelerometerEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(accelerometerEventListener);
    }

}