package io.github.danistrebel.sensitive;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DistanceExperimentActivity extends AppCompatActivity implements SensorEventListener {

    private static final int SAMPLE_FREQ = 1000000;

    private SensorManager mSensorManager;

    private double distance = 0.0;
    private long lastEventTime = 0l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_experiment);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor mLinAccSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        mSensorManager.registerListener(this, mLinAccSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if(lastEventTime == 0l) {
            this.lastEventTime = event.timestamp;
            return;
        } else if (SAMPLE_FREQ > (event.timestamp - lastEventTime)) {
            return;
        }

        long nanoDuration = event.timestamp - lastEventTime;
        this.lastEventTime = event.timestamp;

        this.distance += ((nanoDuration / 1000000000) ^ 2) * Math.max(0.0f, event.values[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
