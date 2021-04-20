package com.example.earrapealarmclock.ui.alarm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earrapealarmclock.R;

import com.example.earrapealarmclock.Util.AlarmAdapter;
import com.example.earrapealarmclock.Util.AlarmData;

public class AlarmFragment extends Fragment {

    private AlarmViewModel alarmViewModel;
    private AlarmData[] alarmData = new AlarmData[10];


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        alarmViewModel =
                new ViewModelProvider(this).get(AlarmViewModel.class);
        View root = inflater.inflate(R.layout.fragment_alarm, container, false);


        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        for(int i = 0; i < 10; i++){
            alarmData[i] = new AlarmData();
        }

        alarmData[0].setActive(true);
        alarmData[0].setLabel("Join ACE class");
        alarmData[0].setHour(3);
        alarmData[0].setMinute(15);
        alarmData[0].setPm(true);

        alarmData[0].setMon(true);
        alarmData[0].setWed(true);
        alarmData[0].setFri(true);

        alarmData[0].setActive(true);


        alarmData[1].setActive(true);
        alarmData[1].setLabel("Super early wake");
        alarmData[1].setHour(4);
        alarmData[1].setMinute(30);

        alarmData[1].setMon(true);
        alarmData[1].setSat(true);
        alarmData[1].setSun(true);

        alarmData[1].setActive(true);


        AlarmAdapter alarmAdapter = new AlarmAdapter(alarmData);
        recyclerView.setAdapter(alarmAdapter);


        return root;
    }
}