package io.github.danistrebel.sensitive;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void listSensors(View v) {
        Intent sensorReport = new Intent(this, SensorReportActivity.class);
        startActivity(sensorReport);
    }

    public void distanceExperiment(View v) {
        Intent sensorReport = new Intent(this, DistanceExperimentActivity.class);
        startActivity(sensorReport);
    }

}
