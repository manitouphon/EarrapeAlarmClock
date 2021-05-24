package com.example.earrapealarmclock.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earrapealarmclock.AlarmItemConfigActivity;
import com.example.earrapealarmclock.MainActivity;
import com.example.earrapealarmclock.R;

import com.example.earrapealarmclock.adapters.AlarmItemAdapter;
import com.example.earrapealarmclock.util.AlarmData;
import com.example.earrapealarmclock.util.NetworkUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.Vector;

public class AlarmFragment extends Fragment {

    private Vector<AlarmData> alarmData;
    private FloatingActionButton addAlarmFAB;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        alarmData = ((MainActivity)getActivity()).getGlobalAlarmData().getAlarmData();
        Log.d("nnuxx", "onCreateView: "+alarmData);
        for(int i = 0 ; i!= alarmData.size(); i++){
            alarmData.get(1).DEBUG_getInfo();
        }

        View root = inflater.inflate(R.layout.fragment_alarm, container, false);


        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);




        AlarmItemAdapter alarmItemAdapter = new AlarmItemAdapter(alarmData, recyclerView);
        recyclerView.setAdapter(alarmItemAdapter);

        addAlarmFAB = root.findViewById(R.id.addAlarmFloatingButton);
        addAlarmFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmData newData = new AlarmData();
                ((MainActivity)getActivity()).getGlobalAlarmData().addAlarmData(newData,getContext());
                getActivity().recreate();
            }
        });

        return root;
    }


}