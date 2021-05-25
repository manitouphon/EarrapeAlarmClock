package com.example.earrapealarmclock.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;


public class NetworkUtil {

    public static String serverEndPoint = "http://10.0.2.2:8000/AlarmData";   //10.0.2.2 on android Emu = localhost on Host

    public static void AddAlarmData(AlarmData newData, Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        //Create a JSONObject
        Gson gson = new Gson();
        JSONObject dataInJson = new JSONObject();
        try {
            dataInJson = new JSONObject(gson.toJson(newData));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Create a json request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, serverEndPoint, dataInJson, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("nnu", "POST onResponse: Success => \n " + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("nnu", "POST onErrorResponse: Error Detected => \n" + error);
            }
        });
        Volley.newRequestQueue(context).add(request);

    }



    public static void fetchAllAlarmData(Context context){
        GlobalAlarmData globalAlarmData = (GlobalAlarmData) context.getApplicationContext();

        //create var
        Gson gson = new Gson();
        Vector<AlarmData> vecAlarmData = new Vector<AlarmData>();
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //Start GET array.
        JsonArrayRequest arrayRequest = new JsonArrayRequest(serverEndPoint, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i< response.length(); i++){
                    AlarmData alarmData = new AlarmData();
                    try {
                        alarmData = gson.fromJson(response.getJSONObject(i).toString(),AlarmData.class);
                        Log.d("nnu", "Assigned! ");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    vecAlarmData.add(alarmData);
                    Log.d("nnu", "vec added  " + i);
                }


                Log.d("nnu", "GET onResponse: SUCCEED" + response);
                Log.d("nnu", "GET DATA on Vec: " + vecAlarmData);
                globalAlarmData.reInitAlarmData(vecAlarmData);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("nnu", "GET onErrorResponse: " + error) ;
            }
        });
        Volley.newRequestQueue(context).add(arrayRequest);

    }




    public static void updateAlarmData(AlarmData newData,int index, Context context){
        index++;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        //Create a JSONObject
        Gson gson = new Gson();
        JSONObject dataInJson = new JSONObject();
        try {
            dataInJson = new JSONObject(gson.toJson(newData));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Create a json request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, serverEndPoint+"/"+ index, dataInJson, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("nnuPUT", "PUT onResponse: Success => \n " + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("nnuPUT", "PUT onErrorResponse: Error Detected => \n" + error);
            }
        });
        Volley.newRequestQueue(context).add(request);

    }



}
