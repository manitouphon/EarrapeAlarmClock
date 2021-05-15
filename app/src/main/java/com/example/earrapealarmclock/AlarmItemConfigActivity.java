package com.example.earrapealarmclock;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.earrapealarmclock.util.AlarmData;
import com.example.earrapealarmclock.util.GlobalAlarmData;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class AlarmItemConfigActivity extends AppCompatActivity {

    private AlarmData currentAlarmData;
    private int dataIndex;
    private TimePicker timePicker;
    private MaterialButtonToggleGroup buttonToggleGroup;
    private boolean needToSave = true;
    private GlobalAlarmData globalAlarmData;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //Set Alarm Item Config Layout as Content View
        setContentView(R.layout.activity_alarm_item_config);
        timePicker = findViewById(R.id.time_picker);
        buttonToggleGroup = findViewById(R.id.alarm_config_toggle_btn_group);

        currentAlarmData = new AlarmData();//Null safe!
        globalAlarmData = globalAlarmData = (GlobalAlarmData) getApplicationContext();


        //Get Alarm Data:
        Intent intent = getIntent();
        Gson gson = new Gson();
        String stringExtra = intent.getStringExtra("alarmData");
        dataIndex = intent.getIntExtra("dataIndex", 0);
        currentAlarmData = gson.fromJson(stringExtra, AlarmData.class);
        Log.d("gson", String.valueOf(dataIndex) + currentAlarmData.DEBUG_getInfo());


        //Initialize TimePicker Config:
        timePicker.setHour(currentAlarmData.getHour());
        timePicker.setMinute(currentAlarmData.getMinute());
        //set timePicker work around
        Date date = new Date();
        date.setHours(currentAlarmData.getHour());
        date.setMinutes(currentAlarmData.getMinute());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        timePicker.setCurrentHour(cal.HOUR_OF_DAY);


        //Initialize days of weeks:
        if (currentAlarmData.isSun())
            buttonToggleGroup.check(R.id.alarm_config_sun);
        if (currentAlarmData.isMon())
            buttonToggleGroup.check(R.id.alarm_config_mon);
        if (currentAlarmData.isTue())
            buttonToggleGroup.check(R.id.alarm_config_tue);
        if (currentAlarmData.isWed())
            buttonToggleGroup.check(R.id.alarm_config_wed);
        if (currentAlarmData.isThu())
            buttonToggleGroup.check(R.id.alarm_config_thu);
        if (currentAlarmData.isFri())
            buttonToggleGroup.check(R.id.alarm_config_fri);
        if (currentAlarmData.isSat())
            buttonToggleGroup.check(R.id.alarm_config_sat);


        //Set Days of weeks
        buttonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                switch (checkedId) {
                    case R.id.alarm_config_sun:
                        currentAlarmData.setSun(isChecked);

                        break;
                    case R.id.alarm_config_mon:
                        currentAlarmData.setMon(isChecked);

                        break;
                    case R.id.alarm_config_tue:
                        currentAlarmData.setTue(isChecked);

                        break;
                    case R.id.alarm_config_wed:
                        currentAlarmData.setWed(isChecked);

                        break;
                    case R.id.alarm_config_thu:
                        currentAlarmData.setThu(isChecked);
                        break;
                    case R.id.alarm_config_fri:
                        currentAlarmData.setFri(isChecked);

                        break;
                    case R.id.alarm_config_sat:
                        currentAlarmData.setSat(isChecked);

                        break;
                }
                Log.d("gson", currentAlarmData.DEBUG_getInfo());


            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (needToSave){
            globalAlarmData.setAlarmData(dataIndex,currentAlarmData);
        }




    }


}
