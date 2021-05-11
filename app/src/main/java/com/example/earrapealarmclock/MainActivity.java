package com.example.earrapealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_alarm, R.id.navigation_timer, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);





    }

    public void addAlarm(View view){
        TextView label = (TextView) findViewById(R.id.alarmLabel);
        label.setText("New Alarm");
        Intent alarmActivity = new Intent(getApplicationContext(), AlarmActivity.class);
        startActivity(alarmActivity);

    }



    public void switchAlarm(View view){
        ImageButton alarmSwitch = (ImageButton) findViewById(R.id.alarmSwitchingImageButton);

        boolean buttonIsOn = false;
        buttonIsOn = !buttonIsOn;
        if(buttonIsOn) {
            alarmSwitch.setImageResource(R.drawable.ic_alarm_off);
        }
        else {
            alarmSwitch.setImageResource(R.drawable.ic_alarm_on);
        }

    }

}