package com.example.earrapealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.earrapealarmclock.util.GlobalAlarmData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private GlobalAlarmData globalAlarmData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Set main activity layout as content view
        setContentView(R.layout.activity_main);

        //Create a var for storing reference to nav_view in activity_main
        BottomNavigationView navView = findViewById(R.id.nav_view);

        //Create a config for the bottom nav
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_alarm, R.id.navigation_timer, R.id.navigation_settings)
                .build();


        //Create NavController that is in the NavHostFragment view
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //set up the action bar config
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //Set up the bottom NavView to work wit navController
        NavigationUI.setupWithNavController(navView, navController);NavigationUI.setupWithNavController(navView, navController);


        //GlobalVariable::Application
        globalAlarmData = (GlobalAlarmData) getApplicationContext();



    }

    public void addAlarm(View view){
        Intent alarmActivity = new Intent(getApplicationContext(), AlarmItemConfigActivity.class);
//        startActivity(alarmActivity);

    }



    public void switchAlarm(View view){
        ImageButton alarmSwitch = (ImageButton) findViewById(R.id.alarmSwitchingImageButton);

        boolean buttonIsOn = false;
        buttonIsOn = !buttonIsOn;
        if(buttonIsOn) {
            alarmSwitch.setImageResource(R.drawable.ic_alarm_on);
        }
        else {
            alarmSwitch.setImageResource(R.drawable.ic_alarm_off);
        }

    }
    public GlobalAlarmData getGlobalAlarmData(){
        return globalAlarmData;
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}