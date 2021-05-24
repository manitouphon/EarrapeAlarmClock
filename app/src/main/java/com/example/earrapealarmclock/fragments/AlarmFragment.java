package com.example.earrapealarmclock.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earrapealarmclock.MainActivity;
import com.example.earrapealarmclock.R;

import com.example.earrapealarmclock.adapters.AlarmItemAdapter;
import com.example.earrapealarmclock.util.AlarmData;

import java.util.Vector;

public class AlarmFragment extends Fragment {

    private Vector<AlarmData> alarmData;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_alarm, container, false);


        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        alarmData = ((MainActivity)getActivity()).getGlobalAlarmData().getAlarmData();



        AlarmItemAdapter alarmItemAdapter = new AlarmItemAdapter(alarmData, recyclerView);
        recyclerView.setAdapter(alarmItemAdapter);


        return root;
    }


}