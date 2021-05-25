package com.example.earrapealarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.earrapealarmclock.util.NetworkUtil;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Hide actionBar if possible
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //Fetch from Api within the Global Application::Class
        NetworkUtil.fetchAllAlarmData(getApplicationContext());
        //Set a timer to start after 3 seconds

        Timer timer2s = new Timer();
        timer2s.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
            }
        }, 3000);

    }



}