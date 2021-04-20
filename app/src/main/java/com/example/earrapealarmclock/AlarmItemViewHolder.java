package com.example.earrapealarmclock;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlarmItemViewHolder extends RecyclerView.ViewHolder {

    public TextView alarmLabel,alarmTime, mon, tue, wed, thu, fri, sat, sun;
    public ImageButton alarmSwitchImgButton;


    public AlarmItemViewHolder(@NonNull View itemView) {
        super(itemView);
        alarmLabel = itemView.findViewById(R.id.alarmLabel);
        alarmTime = itemView.findViewById(R.id.alarmTime);
        mon = itemView.findViewById(R.id.mon);
        tue = itemView.findViewById(R.id.tue);
        wed = itemView.findViewById(R.id.wed);
        thu = itemView.findViewById(R.id.thu);
        fri = itemView.findViewById(R.id.fri);
        sat = itemView.findViewById(R.id.sat);
        sun = itemView.findViewById(R.id.sun);

        alarmSwitchImgButton = itemView.findViewById(R.id.alarmSwitchingImageButton);





    }
}
