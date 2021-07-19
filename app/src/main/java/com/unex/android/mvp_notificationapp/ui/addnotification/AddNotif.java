package com.unex.android.mvp_notificationapp.ui.addnotification;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;
import com.unex.mvp_notificationapp.R;

public class AddNotif extends AppCompatActivity {

    CollapsibleCalendar viewCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notify);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);


    }

}