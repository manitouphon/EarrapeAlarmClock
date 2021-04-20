package com.example.earrapealarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void disableAlarm(View view) {
        this.finish();
    }




}