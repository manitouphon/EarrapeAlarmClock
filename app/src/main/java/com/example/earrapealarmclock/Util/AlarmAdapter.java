package com.example.earrapealarmclock.Util;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earrapealarmclock.AlarmItemViewHolder;
import com.example.earrapealarmclock.R;


public class AlarmAdapter extends RecyclerView.Adapter<AlarmItemViewHolder> {

    private AlarmData[] alarmData;

    public AlarmAdapter(AlarmData[] alarmData) {
        this.alarmData = alarmData;
    }

    @NonNull
    @Override
    public AlarmItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.alarm_item_view_holder, parent, false);

        AlarmItemViewHolder alarmItemViewHolder = new AlarmItemViewHolder(view);


        return alarmItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmItemViewHolder holder, int position) {

        //Set Label
        TextView label = holder.itemView.findViewById(R.id.alarmLabel);
        label.setText(alarmData[position].getLabel());


        //Set Time
        TextView time = holder.itemView.findViewById(R.id.alarmTime);
        String hour = String.valueOf(alarmData[position].getHour());
        String minute = String.valueOf(alarmData[position].getMinute());
        String period;
        if (alarmData[position].getHour() < 10) {
            hour = "0" + hour;
        }
        if (alarmData[position].getMinute() < 10) {
            minute = "0" + minute;
        }
        if (alarmData[position].isPm()) {
            period = "PM";
        } else period = "AM";
        time.setText(hour + ":" + minute + " " + period);

        //toggle day of the week labels:
        TextView[] daysOfWeeks = new TextView[7];
        daysOfWeeks[0] = holder.itemView.findViewById(R.id.mon);
        daysOfWeeks[1] = holder.itemView.findViewById(R.id.tue);
        daysOfWeeks[2] = holder.itemView.findViewById(R.id.wed);
        daysOfWeeks[3] = holder.itemView.findViewById(R.id.thu);
        daysOfWeeks[4] = holder.itemView.findViewById(R.id.fri);
        daysOfWeeks[5] = holder.itemView.findViewById(R.id.sat);
        daysOfWeeks[6] = holder.itemView.findViewById(R.id.sun);

        if (alarmData[position].isMon()) {
            daysOfWeeks[0].setTextColor(ContextCompat.getColor(daysOfWeeks[0].getContext(),R.color.orange_3));
            daysOfWeeks[0].setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (alarmData[position].isTue()) {
            daysOfWeeks[1].setTextColor(ContextCompat.getColor(daysOfWeeks[0].getContext(),R.color.orange_3));
            daysOfWeeks[1].setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (alarmData[position].isWed()) {
            daysOfWeeks[2].setTextColor(ContextCompat.getColor(daysOfWeeks[0].getContext(),R.color.orange_3));
            daysOfWeeks[2].setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (alarmData[position].isThu()) {
            daysOfWeeks[3].setTextColor(ContextCompat.getColor(daysOfWeeks[0].getContext(),R.color.orange_3));
            daysOfWeeks[3].setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (alarmData[position].isFri()) {
            daysOfWeeks[4].setTextColor(ContextCompat.getColor(daysOfWeeks[0].getContext(),R.color.orange_3));
            daysOfWeeks[4].setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (alarmData[position].isSat()) {
            daysOfWeeks[5].setTextColor(ContextCompat.getColor(daysOfWeeks[0].getContext(),R.color.orange_3));
            daysOfWeeks[5].setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (alarmData[position].isSun()) {
            daysOfWeeks[6].setTextColor(ContextCompat.getColor(daysOfWeeks[0].getContext(),R.color.orange_3));
            daysOfWeeks[6].setTypeface(Typeface.DEFAULT_BOLD);
        }


        //Set alarm on/off state:
        ImageButton alarmSwitchingImgBtn = holder.itemView.findViewById(R.id.alarmSwitchingImageButton);
        alarmSwitchingImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmData[position].setActive(!alarmData[position].isActive());
                System.out.println(alarmData[position].isActive());
                if (alarmData[position].isActive()) {
                    alarmSwitchingImgBtn.setImageResource(R.drawable.ic_alarm_on);
                } else {
                    alarmSwitchingImgBtn.setImageResource(R.drawable.ic_alarm_off);
                }
            }
        });
        if (alarmData[position].isActive()) {
            alarmSwitchingImgBtn.setImageResource(R.drawable.ic_alarm_on);
        } else {
            alarmSwitchingImgBtn.setImageResource(R.drawable.ic_alarm_off);
        }


    }


    @Override
    public int getItemCount() {
        return alarmData.length;
    }
}
