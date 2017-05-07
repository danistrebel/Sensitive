package io.github.danistrebel.sensitive;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/**
 * Created by strebel on 07.05.17.
 */

public class PhoneSensorListener implements SensorEventListener {

    private String sensorId;
    private SensorDataModel dataModel;


    public PhoneSensorListener(String sensorId, SensorDataModel dataModel) {
        this.sensorId = sensorId;
        this.dataModel = dataModel;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        this.dataModel.update(this.sensorId, event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
