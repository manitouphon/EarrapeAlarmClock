package com.example.earrapealarmclock.util;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

public class JsonFileHandler {


    private static Gson gson = new Gson();


    public static GlobalAlarmData getNewAlarmData() {
        String jsonString = "";


        File file = new File("storage/sdcard/EarrapeAlarmClock/AlarmData/alarmData.json");


        GlobalAlarmData newData = gson.fromJson(jsonString, GlobalAlarmData.class);
        return newData;
    }

    public static void writeAlarmDataTo(GlobalAlarmData targetData) {
        String jsonObject = gson.toJson(targetData);
        try {
            Writer output = null;
            File file = new File("storage/sdcard/EarrapeAlarmClock/AlarmData/alarmData.json");
            output = new BufferedWriter(new FileWriter(file));
            output.write(jsonObject);
            output.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
