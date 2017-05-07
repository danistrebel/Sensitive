package io.github.danistrebel.sensitive;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SensorReportActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private SensorDataModel mSensorDataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_report);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> allDeviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        this.mSensorDataModel = new SensorDataModel();

        for (Sensor s: allDeviceSensors) {
            PhoneSensorListener listener = new PhoneSensorListener(s.getStringType(), this.mSensorDataModel);
            mSensorManager.registerListener(listener, s, SensorManager.SENSOR_DELAY_FASTEST);
        }

        ListView sensorList = (ListView) findViewById(R.id.sensor_data);
        sensorList.setAdapter(this.mSensorDataModel);
    }
}
