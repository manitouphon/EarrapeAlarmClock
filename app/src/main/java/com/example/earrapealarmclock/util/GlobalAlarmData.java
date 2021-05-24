package com.example.earrapealarmclock.util;

import android.app.Application;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Vector;

public class GlobalAlarmData extends Application {

    static GlobalAlarmData instance;
    private Vector<AlarmData> alarmData = new Vector<AlarmData>();

        

    public GlobalAlarmData( ) {
        GlobalAlarmData newData = JsonFileHandler.getNewAlarmData();
        if(newData != null){
            alarmData = newData.getAlarmData();
        }
        else {
            instance = this;

            for (int i = 0; i < 3; i++) {
                alarmData.add(new AlarmData());
            }

            alarmData.get(0).setActive(true);
            alarmData.get(0).setLabel("Join ACE class");
            alarmData.get(0).setHour(15);
            alarmData.get(0).setMinute(15);

            alarmData.get(0).setMon(true);
            alarmData.get(0).setWed(true);
            alarmData.get(0).setFri(true);

            alarmData.get(0).setActive(true);


            alarmData.get(1).setActive(true);
            alarmData.get(1).setLabel("Super early wake");
            alarmData.get(1).setHour(4);
            alarmData.get(1).setMinute(30);

            alarmData.get(1).setMon(true);
            alarmData.get(1).setSat(true);
            alarmData.get(1).setSun(true);

            alarmData.get(1).setActive(true);
        }
    }

    public void setAlarmData(int index,AlarmData newAlarmData){
        this.alarmData.set(index, newAlarmData);

    }





    public AlarmData getAlarmData(int index) {
        return alarmData.get(index);
    }
    public Vector<AlarmData> getAlarmData() {
        return alarmData;
    }
    public int getAlarmDataSize(){
        return alarmData.size();
    }
    public static GlobalAlarmData getInstance(){
        return instance;
    }
}
