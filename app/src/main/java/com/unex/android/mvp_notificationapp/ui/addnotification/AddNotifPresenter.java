package com.unex.android.mvp_notificationapp.ui.addnotification;

import com.unex.android.mvp_notificationapp.data.NotifFields;
import com.unex.android.mvp_notificationapp.data.NotifPresenter;

public class AddNotifPresenter implements AddNotifContract.Presenter {


    private AddNotifContract.View view;

    private NotifPresenter notifPresenter;


    public void done(NotifFields notifFields){
        notifPresenter.checkFields(notifFields);
    }

    @Override
    public void onSuccess() {
        view.onSuccess();
    }

    @Override
    public void onFailed(String message) {
        view.onFailed(message);
    }

    @Override
    public void saveToSharedPref(String activityData) {
        view.saveToSharedPref(activityData);
    }


    public AddNotifPresenter(AddNotifContract.View view){
        this.view = view;
        notifPresenter = new NotifPresenter(this);
    }

}
