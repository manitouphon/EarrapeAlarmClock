package com.example.earrapealarmclock.util;

import android.app.Application;



import java.util.Vector;

public class GlobalAlarmData extends Application {

    static GlobalAlarmData instance;
    private Vector<AlarmData> alarmData = new Vector<AlarmData>();

    public GlobalAlarmData( ) {
        instance = this;

        for(int i = 0; i < 3; i++){
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

    public AlarmData getAlarmData(int index) {
        return alarmData.get(index);
    }

    public Vector<AlarmData> getAlarmData() {
        return alarmData;
    }
    public int getAlarmDataSize(){
        return alarmData.size();
    }
    public void setAlarmData(int index,AlarmData newAlarmData){
        this.alarmData.set(index, newAlarmData);
    }
    public static GlobalAlarmData getInstance(){
        return instance;
    }
}
