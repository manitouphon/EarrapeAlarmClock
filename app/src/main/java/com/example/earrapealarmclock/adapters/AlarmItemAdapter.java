package com.example.earrapealarmclock.adapters;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earrapealarmclock.AlarmItemConfigActivity;
import com.example.earrapealarmclock.util.GlobalAlarmData;
import com.example.earrapealarmclock.viewHolders.AlarmItemViewHolder;
import com.example.earrapealarmclock.R;
import com.example.earrapealarmclock.util.AlarmData;
import com.google.gson.Gson;

import java.util.Vector;


public class AlarmItemAdapter extends RecyclerView.Adapter<AlarmItemViewHolder> {
    private RecyclerView mainRecyclerView;
    private Vector<AlarmData> alarmData;


    //OnClick Handler for each view holders
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //get ItemPosition
            int itemPosition = mainRecyclerView.getChildLayoutPosition(v);

            //---Create another activity:
            //get context from it's recycler view, container.
            Intent alarmConfigActivity = new Intent(mainRecyclerView.getContext(), AlarmItemConfigActivity.class);

            //Pass alarmData[position] using Parcelable to the alarmConfigActivity:Intent
            alarmConfigActivity.putExtra("alarmData", new Gson().toJson(alarmData.get(itemPosition)));
            alarmConfigActivity.putExtra("dataIndex", itemPosition);
            mainRecyclerView.getContext().startActivity(alarmConfigActivity);

        }
    };







    //Constructor
    public AlarmItemAdapter(Vector<AlarmData> data, RecyclerView recyclerView) {
        this.mainRecyclerView = recyclerView;

        alarmData = GlobalAlarmData.getInstance().getAlarmData();

    }



    //OnCreate
    @NonNull
    @Override
    public AlarmItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_alarm_item, parent, false);
        //Add onclick listener from this class's property (onClickListener::View.OnClickListener)
        view.setOnClickListener(onClickListener);
        AlarmItemViewHolder alarmItemViewHolder = new AlarmItemViewHolder(view);


        return alarmItemViewHolder;
    }



    //OnBinding
    @Override
    public void onBindViewHolder(@NonNull AlarmItemViewHolder holder, int position) {

        //Set Label
        TextView label = holder.itemView.findViewById(R.id.alarmLabel);
        label.setText(alarmData.get(position).getLabel());


        //Set Time
        TextView time = holder.itemView.findViewById(R.id.alarmTime);
        String hour = String.valueOf(alarmData.get(position).getHour());
        String minute = String.valueOf(alarmData.get(position).getMinute());
        String period;
        if (alarmData.get(position).getHour() < 10) {
            hour = "0" + hour;
        }
        if (alarmData.get(position).getMinute() < 10) {
            minute = "0" + minute;
        }
        if (alarmData.get(position).isPm()) {
            period = "PM";
        } else period = "AM";
        String timeLabel = hour + ":" + minute + " " + period;
        time.setText(timeLabel);

        //toggle day of the week labels:
        TextView[] daysOfWeeks = new TextView[7];
        daysOfWeeks[0] = holder.itemView.findViewById(R.id.alarm_item_sun);
        daysOfWeeks[1] = holder.itemView.findViewById(R.id.alarm_item_mon);
        daysOfWeeks[2] = holder.itemView.findViewById(R.id.alarm_item_tue);
        daysOfWeeks[3] = holder.itemView.findViewById(R.id.alarm_item_wed);
        daysOfWeeks[4] = holder.itemView.findViewById(R.id.alarm_item_thu);
        daysOfWeeks[5] = holder.itemView.findViewById(R.id.alarm_item_fri);
        daysOfWeeks[6] = holder.itemView.findViewById(R.id.alarm_item_sat);

        if (alarmData.get(position).isSun()) {
            daysOfWeeks[0].setTextColor(ContextCompat.getColor(daysOfWeeks[0].getContext(),R.color.orange_3));
            daysOfWeeks[0].setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (alarmData.get(position).isMon()) {
            daysOfWeeks[1].setTextColor(ContextCompat.getColor(daysOfWeeks[0].getContext(),R.color.orange_3));
            daysOfWeeks[1].setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (alarmData.get(position).isTue()) {
            daysOfWeeks[2].setTextColor(ContextCompat.getColor(daysOfWeeks[0].getContext(),R.color.orange_3));
            daysOfWeeks[2].setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (alarmData.get(position).isWed()) {
            daysOfWeeks[3].setTextColor(ContextCompat.getColor(daysOfWeeks[0].getContext(),R.color.orange_3));
            daysOfWeeks[3].setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (alarmData.get(position).isThu()) {
            daysOfWeeks[4].setTextColor(ContextCompat.getColor(daysOfWeeks[0].getContext(),R.color.orange_3));
            daysOfWeeks[4].setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (alarmData.get(position).isFri()) {
            daysOfWeeks[5].setTextColor(ContextCompat.getColor(daysOfWeeks[0].getContext(),R.color.orange_3));
            daysOfWeeks[5].setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (alarmData.get(position).isSat()) {
            daysOfWeeks[6].setTextColor(ContextCompat.getColor(daysOfWeeks[0].getContext(),R.color.orange_3));
            daysOfWeeks[6].setTypeface(Typeface.DEFAULT_BOLD);
        }


        //Set alarm on/off state:
        ImageButton alarmSwitchingImgBtn = holder.itemView.findViewById(R.id.alarmSwitchingImageButton);
        //Init
        if (alarmData.get(position).isActive()) {
            alarmSwitchingImgBtn.setImageResource(R.drawable.ic_alarm_on);
        } else {
            alarmSwitchingImgBtn.setImageResource(R.drawable.ic_alarm_off);
        }
        alarmSwitchingImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toggle the Active::Boolean status
                alarmData.get(position).setActive(!alarmData.get(position).isActive());
                //Re-init
                if (alarmData.get(position).isActive()) {
                    alarmSwitchingImgBtn.setImageResource(R.drawable.ic_alarm_on);
                } else {
                    alarmSwitchingImgBtn.setImageResource(R.drawable.ic_alarm_off);
                }
            }
        });





    }






    @Override
    public int getItemCount() {
        return alarmData.size();
    }
}
