package com.unex.android.mvp_notificationapp.ui.addnotification;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.shawnlin.numberpicker.NumberPicker;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;
import com.unex.mvp_notificationapp.R;

public class AddNotif extends AppCompatActivity implements AddNotifContract.View {

    private AddNotifContract.Presenter presenter = new AddNotifPresenter(AddNotif.this);

    CollapsibleCalendar viewCalendar;
    NumberPicker npHour, npMin;
    TextView tvTitle, tvDescription, tvVenue;
    TextView tvCancel, tvDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notify);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        initViews();
        initListeners();

    }

    private void initViews(){
        viewCalendar = findViewById(R.id.calendarView);
        npHour = findViewById(R.id.np_hour);
        npMin = findViewById(R.id.np_min);
        tvTitle = findViewById(R.id.tv_title);
        tvDescription = findViewById(R.id.tv_description);
        tvVenue = findViewById(R.id.tv_venue);
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
                String title = tvTitle.getText().toString();
                String description = tvDescription.getText().toString();
                String venue = tvVenue.getText().toString();

                presenter.checkFields(title, description, venue);


            }
        });
    }


    @Override
    public void notifyFieldsCannotBeEmpty() {
        Toast.makeText(AddNotif.this, "Done!!!", Toast.LENGTH_SHORT).show();
    }
}