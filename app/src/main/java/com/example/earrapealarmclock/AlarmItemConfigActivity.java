package com.example.earrapealarmclock;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;

import com.example.earrapealarmclock.util.AlarmData;
import com.example.earrapealarmclock.util.GlobalAlarmData;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

public class AlarmItemConfigActivity extends AppCompatActivity {

    private AlarmData currentAlarmData;
    private int dataIndex;
    private TimePicker timePicker;
    private MaterialButtonToggleGroup buttonToggleGroup;
    private boolean needToSave = false;
    private boolean dialogSayYes ;
    private GlobalAlarmData globalAlarmData;
    private TextInputEditText labelTextEdit;
    private MaterialButton saveButton, discardButton;

    private void showDiscardAlertDialog(){
        AlertDialog.Builder confirmAlertDialog = new AlertDialog.Builder(this);
        confirmAlertDialog.setTitle("Discard Changes");
        confirmAlertDialog.setMessage("Do you want to discard these changes?");
        confirmAlertDialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                needToSave = false;
                dialogSayYes = true;
                AlarmItemConfigActivity.super.onBackPressed();
                finish();
            }
        });
        confirmAlertDialog.setNegativeButton(android.R.string.no, null);
        confirmAlertDialog.setIcon(android.R.drawable.ic_menu_save);
        AlertDialog alertDialog = confirmAlertDialog.create();
        alertDialog.show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //Set Alarm Item Config Layout as Content View
        setContentView(R.layout.activity_alarm_item_config);

        //private variables init.
        timePicker = findViewById(R.id.time_picker);
        buttonToggleGroup = findViewById(R.id.alarm_config_toggle_btn_group);
        discardButton = findViewById(R.id.discard_button);
        saveButton = findViewById(R.id.save_button);

        currentAlarmData = new AlarmData();//Null safe!
        globalAlarmData = globalAlarmData = (GlobalAlarmData) getApplicationContext();


        //Get Alarm Data:
        Intent intent = getIntent();
        Gson gson = new Gson();
        String stringExtra = intent.getStringExtra("alarmData");
        dataIndex = intent.getIntExtra("dataIndex", 0);
        currentAlarmData = gson.fromJson(stringExtra, AlarmData.class);
        Log.d("gson", String.valueOf(dataIndex) + currentAlarmData.DEBUG_getInfo());


        //-------------------------Init section

        //Initialize TimePicker Config:
        timePicker.setCurrentHour(currentAlarmData.get24HHour());
        timePicker.setCurrentMinute(currentAlarmData.getMinute());



        //Initialize days of weeks:
        if (currentAlarmData.isSun()) {
            buttonToggleGroup.check(R.id.alarm_config_sun);
        }
        if (currentAlarmData.isMon()) {
            buttonToggleGroup.check(R.id.alarm_config_mon);
        }
        if (currentAlarmData.isTue()) {
            buttonToggleGroup.check(R.id.alarm_config_tue);
        }
        if (currentAlarmData.isWed()) {
            buttonToggleGroup.check(R.id.alarm_config_wed);
        }
        if (currentAlarmData.isThu()) {
            buttonToggleGroup.check(R.id.alarm_config_thu);
        }
        if (currentAlarmData.isFri()) {
            buttonToggleGroup.check(R.id.alarm_config_fri);
        }
        if (currentAlarmData.isSat()) {
            buttonToggleGroup.check(R.id.alarm_config_sat);
        }



        //-------------------Actions Section:




        //Set timepicker action:
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                currentAlarmData.setHour(hourOfDay);
                currentAlarmData.setMinute(minute);
                needToSave = true;
            }
        });
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
                needToSave = true;
                Log.d("gson", currentAlarmData.DEBUG_getInfo());


            }
        });


        //Initialize Label:
        labelTextEdit = findViewById(R.id.Label_text_edit);
        labelTextEdit.setText(currentAlarmData.getLabel());



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                needToSave = true;
                finish();
                Log.d("button", "onClick: save ");
            }
        });
        discardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                needToSave = false;
                showDiscardAlertDialog();
                Log.d("button", "onClick: discard");
            }
        });



    }//On Create

    @Override
    public void onBackPressed() {
        showDiscardAlertDialog();

    }

    @Override
    protected void onPause() {

        super.onPause();
        if (needToSave){
            Log.d("button", "onStop: data saved" + currentAlarmData.getLabel());
            currentAlarmData.setLabel(labelTextEdit.getText().toString());
            globalAlarmData.setAlarmData(dataIndex,currentAlarmData);
        }
        finish();




    }


}
