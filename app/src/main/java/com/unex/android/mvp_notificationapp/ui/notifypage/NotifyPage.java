package com.unex.android.mvp_notificationapp.ui.notifypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unex.mvp_notificationapp.R;

public class NotifyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_page);
    }

    public void onClickAddNotif(View v){
        Intent intent = new Intent(this, AddNotif.class);
        startActivity(intent);
    }
}