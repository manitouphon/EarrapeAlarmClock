package com.example.earrapealarmclock.viewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earrapealarmclock.R;

public class AlarmItemViewHolder extends RecyclerView.ViewHolder {

    public TextView alarmLabel,alarmTime, mon, tue, wed, thu, fri, sat, sun;
    public ImageButton alarmSwitchImgButton;


    public AlarmItemViewHolder(@NonNull View itemView) {
        super(itemView);
        alarmLabel = itemView.findViewById(R.id.alarmLabel);
        alarmTime = itemView.findViewById(R.id.alarmTime);
        mon = itemView.findViewById(R.id.alarm_item_mon);
        tue = itemView.findViewById(R.id.alarm_item_tue);
        wed = itemView.findViewById(R.id.alarm_item_wed);
        thu = itemView.findViewById(R.id.alarm_item_thu);
        fri = itemView.findViewById(R.id.alarm_item_fri);
        sat = itemView.findViewById(R.id.alarm_item_sat);
        sun = itemView.findViewById(R.id.alarm_item_sun);

        alarmSwitchImgButton = itemView.findViewById(R.id.alarmSwitchingImageButton);





    }
}
