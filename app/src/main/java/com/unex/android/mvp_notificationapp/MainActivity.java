package com.unex.android.mvp_notificationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unex.mvp_notificationapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvActivities;
    String[] mStringArray = {};
    ArrayAdapter<String> activitiesArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvActivities = findViewById(R.id.lv_activities);

        SharedPreferences notifSharedPref = getSharedPreferences("notification", Context.MODE_PRIVATE);
        String notificationData = notifSharedPref.getString("notifData", null);
        Gson gson = new Gson();
        List<String> data = new ArrayList<>();
        if(notificationData != null){
            data = gson.fromJson(notificationData, new TypeToken<List<String>>(){}.getType());
        }

        mStringArray = new String[data.size()];
        mStringArray = (String[]) data.toArray(mStringArray);
        activitiesArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStringArray);
        lvActivities.setAdapter(activitiesArrayAdapter);
        lvActivities.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences aklatSharedPref = getSharedPreferences("notification", Context.MODE_PRIVATE);
                String aklatData = aklatSharedPref.getString("notifData", null);
                Gson gson = new Gson();
                List<String> data = new ArrayList<>();
                if(aklatData != null){
                    data = gson.fromJson(aklatData, new TypeToken<List<String>>(){}.getType());
                }

//                remove data from listview
//                data.remove(mStringArray[i]);
//                activitiesArrayAdapter.notifyDataSetChanged();
                saveToSharedPref(data);
                return false;
            }
        });


    }

    private void saveToSharedPref(List<String> wholeData){
        Gson gson = new Gson();
        String wholeDataString = gson.toJson(wholeData);
        SharedPreferences notifSharedPref = getSharedPreferences("notification", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = notifSharedPref.edit();
        editor.putString("notifData", wholeDataString);
        editor.apply();
    }
}