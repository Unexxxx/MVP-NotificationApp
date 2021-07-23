package com.unex.android.mvp_notificationapp.ui.addnotification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.shawnlin.numberpicker.NumberPicker;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;
import com.unex.android.mvp_notificationapp.data.NotifFields;
import com.unex.mvp_notificationapp.MainActivity;
import com.unex.mvp_notificationapp.R;

public class AddNotif extends AppCompatActivity implements AddNotifContract.View {

    AddNotifPresenter presenter;

    CollapsibleCalendar viewCalendar;
    NumberPicker npHour, npMin;
    EditText etTitle, etDescription, etVenue;
    TextView tvCancel, tvDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notify);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        presenter = new AddNotifPresenter(this);

        initViews();
        initListeners();

    }

    private void initViews(){
        viewCalendar = findViewById(R.id.calendarView);
        npHour = findViewById(R.id.np_hour);
        npMin = findViewById(R.id.np_min);
        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        etVenue = findViewById(R.id.et_venue);
        tvCancel = findViewById(R.id.tv_cancel);
        tvDone = findViewById(R.id.tv_done);

    }

    private void initListeners(){
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String description = etDescription.getText().toString();
                String venue = etVenue.getText().toString();

                NotifFields notifFields = new NotifFields(title, description, venue);
                presenter.done(notifFields);

            }
        });
    }


    @Override
    public void onSuccess() {
        startActivity(new Intent(this, MainActivity.class));
        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}