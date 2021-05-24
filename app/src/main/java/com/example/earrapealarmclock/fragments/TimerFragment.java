package com.example.earrapealarmclock.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.earrapealarmclock.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class TimerFragment extends Fragment {
    private Timer demoTimer;

    private EditText hourET;
    private EditText minuteET;
    private EditText secondET;
    private boolean buttonIsPlayBtn;
    private ExtendedFloatingActionButton play;
    private ExtendedFloatingActionButton stop;

    private MaterialProgressBar timerProgressbar;
    private int DEMO_progressBarValue = 10000;

    private void updateProgressBar(){
        if(DEMO_progressBarValue <= 0) DEMO_progressBarValue = 10000;

        DEMO_progressBarValue = DEMO_progressBarValue - 50 ;
        timerProgressbar.setProgress(DEMO_progressBarValue);
    }

    private void startDEMOTimer(){
        demoTimer = new Timer();
        demoTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateProgressBar();
            }
        },0,50);
    }

    private void updateFABs(){
        if(buttonIsPlayBtn){
            buttonIsPlayBtn = false;
            demoTimer.cancel();
            demoTimer.purge();
            startDEMOTimer();
            play.setIcon(getResources().getDrawable(R.drawable.ic_pause));
        }
        else {
            buttonIsPlayBtn = true;
            demoTimer.cancel();
            demoTimer.purge();
            play.setIcon(getResources().getDrawable(R.drawable.ic_play));
        }

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Var init
        View root = inflater.inflate(R.layout.fragment_timer, container, false);
        timerProgressbar = root.findViewById(R.id.timer_progressbar);
        hourET = root.findViewById(R.id.hours_text_edit);
        minuteET = root.findViewById(R.id.minutes_text_edit);
        secondET = root.findViewById(R.id.seconds_text_edit);
        play = root.findViewById(R.id.play_fab);
        stop = root.findViewById(R.id.stop_fab);


        timerProgressbar.setMax(10000);
        secondET.setText("10");

        //UX init:
        startDEMOTimer();
        updateFABs();




        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFABs();

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonIsPlayBtn = false;
                updateFABs();
                demoTimer.cancel();
                demoTimer.purge();
                DEMO_progressBarValue = 0 ;
            }
        });



        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
        //DEMO
        demoTimer.cancel();
        demoTimer.purge();
    }
}