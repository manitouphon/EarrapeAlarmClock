package com.example.earrapealarmclock.util;

import android.app.Application;
import android.content.Context;
import android.util.Log;


import java.util.Vector;

public class GlobalAlarmData extends Application {

    static GlobalAlarmData instance;
    private Vector<AlarmData> alarmData = new Vector<AlarmData>();



    public GlobalAlarmData() {
        instance = this;
    }

    public void setAlarmData(int index, AlarmData newAlarmData) {
        this.alarmData.set(index, newAlarmData);
        NetworkUtil.updateAlarmData(newAlarmData,index,getApplicationContext());
    }


    public AlarmData getAlarmData(int index) {
        return alarmData.get(index);
    }

    public Vector<AlarmData> getAlarmData() {
        return alarmData;
    }

    public int getAlarmDataSize() {
        return alarmData.size();
    }

    public static GlobalAlarmData getInstance() {
        return instance;
    }

    public void reInitAlarmData(Vector<AlarmData> newAlarmData) {
        this.alarmData = newAlarmData;
        for (int i = 0; i< newAlarmData.size(); i++){
            Log.d("nnuEX", "new ALDTA " +newAlarmData.elementAt(i).DEBUG_getInfo());
        }
    }
    public void addAlarmData(AlarmData newData, Context context){
        NetworkUtil.AddAlarmData(newData,context);
        this.alarmData.add(newData);

    }

}
