package com.unex.android.mvp_notificationapp.ui.addnotification;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shawnlin.numberpicker.NumberPicker;
import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;
import com.unex.android.mvp_notificationapp.MainActivity;
import com.unex.android.mvp_notificationapp.data.NotifFields;
import com.unex.mvp_notificationapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddNotif extends AppCompatActivity implements AddNotifContract.View {

    AddNotifPresenter presenter;

    Day day;
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

        Calendar currentTime = Calendar.getInstance();
        int currentHourIn24Format = currentTime.get(Calendar.HOUR_OF_DAY);
        int currentMinute = currentTime.get(Calendar.MINUTE);

        npHour.setValue(currentHourIn24Format);
        npMin.setValue(currentMinute);



        viewCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                day = viewCalendar.getSelectedDay();
                Log.i(getClass().getName(), "Selected Day: " + day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay());
            }

            @Override
            public void onItemClick(View view) {

            }

            @Override
            public void onDataUpdate() {

            }

            @Override
            public void onMonthChange() {

            }

            @Override
            public void onWeekChange(int i) {

            }

            @Override
            public void onClickListener() {

            }

            @Override
            public void onDayChanged() {

            }
        });

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
                int timeH = npHour.getValue();
                int timeM = npMin.getValue();

                NotifFields notifFields = new NotifFields(title, description, venue);
                presenter.done(notifFields);

                saveToSharedPref(title +"\n"+ (day.getMonth() + 1) + "/" + day.getDay() + "/" + day.getYear() + "\n" + timeH + ":" + timeM);

            }
        });
    }

    private void saveToSharedPref(String activityData){
        Gson gson = new Gson();

        SharedPreferences aklatSharedPref = getSharedPreferences("notification", Context.MODE_PRIVATE);
        String aklatData = aklatSharedPref.getString("notifData", null);

        ArrayList<String> data = new ArrayList<>();
        if(aklatData != null){
            data = gson.fromJson(aklatData, new TypeToken<List<String>>(){}.getType());
        }
        data.add(activityData);

        String dataString = gson.toJson(data);
        SharedPreferences.Editor editor = aklatSharedPref.edit();
        editor.putString("notifData", dataString);
        editor.commit();
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