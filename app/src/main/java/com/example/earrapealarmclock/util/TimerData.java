package com.example.earrapealarmclock.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class TimerData  implements Runnable{
    private Date now;
    private Date target;

    private Timer timer;

    private void updateDurationTime(){
        this.now = new Date(System.currentTimeMillis());
        long duration ;

        Calendar now = Calendar.getInstance();
        Calendar target = Calendar.getInstance();

        now.setTime(this.now);
        now.set(Calendar.SECOND,0);

        target.setTime(now.getTime());


    }

    @Override
    public void run() {

    }

    public void startTimer(){


    }

    public void pauseTimer(){

    }

    public void stopTimer(){

    }

}
