package com.example.earrapealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.earrapealarmclock.fragments.AlarmFragment;
import com.example.earrapealarmclock.fragments.SettingsFragment;
import com.example.earrapealarmclock.fragments.TimerFragment;
import com.example.earrapealarmclock.util.GlobalAlarmData;
import com.example.earrapealarmclock.util.NetworkUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private GlobalAlarmData globalAlarmData ;
    private AlarmFragment alarmFragment;
    private SettingsFragment settingsFragment;
    private TimerFragment timerFragment;
    private BottomNavigationView bottomNavView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private int selectedBottomNavID;



    private void createFragment(){
        alarmFragment = new AlarmFragment();
        settingsFragment = new SettingsFragment();
        timerFragment = new TimerFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        bottomNavView = findViewById(R.id.bottom_nav);
        fragmentTransaction.replace(R.id.main_fragment_container,alarmFragment);
        selectedBottomNavID = 0;
        fragmentTransaction.commit();
        bottomNavView.setOnNavigationItemSelectedListener(this);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        fragmentTransaction = fragmentManager.beginTransaction();

        if(item.getItemId() == R.id.menu_item_alarm){
            fragmentTransaction.replace(R.id.main_fragment_container,alarmFragment);
            selectedBottomNavID = 0;
            fragmentTransaction.commit();
        }
        else if(item.getItemId() == R.id.menu_item_timer) {
            fragmentTransaction.replace(R.id.main_fragment_container, timerFragment);
            selectedBottomNavID = 1;
            fragmentTransaction.commit();
        }
        else if(item.getItemId() == R.id.menu_item_setting){
            fragmentTransaction.replace(R.id.main_fragment_container,settingsFragment);
            selectedBottomNavID = 2;
            fragmentTransaction.commit();
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NetworkUtil.fetchAllAlarmData(getApplicationContext());
        super.onCreate(savedInstanceState);
        //Set main activity layout as content view
        setContentView(R.layout.activity_main);

        //GlobalVariable::Application

        globalAlarmData = (GlobalAlarmData) getApplicationContext();
        Log.d("nnuxx", "onCreate: " +globalAlarmData.getAlarmData());
        createFragment();
        onResume();

    }

    public void addAlarm(View view){
        Intent alarmActivity = new Intent(getApplicationContext(), AlarmItemConfigActivity.class);
//        startActivity(alarmActivity);

        finish();
        startActivity(getIntent());

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
    protected void onResume() {
        NetworkUtil.fetchAllAlarmData(getApplicationContext());
        globalAlarmData = (GlobalAlarmData) getApplicationContext();
        super.onResume();
        alarmFragment = new AlarmFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        if(selectedBottomNavID == 0)
            fragmentTransaction.replace(R.id.main_fragment_container, alarmFragment);
        else if(selectedBottomNavID == 1)
            fragmentTransaction.replace(R.id.main_fragment_container, timerFragment);
        else if(selectedBottomNavID == 2)
            fragmentTransaction.replace(R.id.main_fragment_container, settingsFragment);
        fragmentTransaction.commit();


    }
}