package io.github.danistrebel.sensitive;

import android.content.Context;
import android.hardware.SensorEvent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.zip.Inflater;

/**
 * Created by strebel on 07.05.17.
 */

public class SensorDataModel extends BaseAdapter {

    TreeMap<String, String> sensorMap = new TreeMap<String, String>();

    public void update(String sensorId, SensorEvent event) {

        if (sensorId.trim().length() == 0) {
            return;
        }

        String sensorValue = "";
        try {
            sensorValue = Arrays.toString(event.values);
        } catch (Exception e) {
            sensorValue = "ERROR";
        }
        this.sensorMap.put(sensorId, sensorValue);
        this.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        String key = (String) this.sensorMap.keySet().toArray()[position];
        return key + ":" + this.sensorMap.get(key);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.sensor_entry, null);
        }

        String[] item = getItem(position).split(":");
        TextView label = (TextView) convertView.findViewById(R.id.sensor_name);
        label.setText(item[0].replace("android.sensor.", ""));
        TextView value = (TextView) convertView.findViewById(R.id.sensor_value);
        value.setText(item[1]);

        return convertView;
    }

    @Override
    public int getCount() {
        return this.sensorMap.size();
    }
}
