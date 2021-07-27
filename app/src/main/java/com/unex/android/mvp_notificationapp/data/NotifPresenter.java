package com.unex.android.mvp_notificationapp.data;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unex.android.mvp_notificationapp.ui.addnotification.AddNotifContract;

import java.util.ArrayList;
import java.util.List;

public class NotifPresenter {

    private AddNotifContract.Presenter presenter;


    public NotifPresenter(AddNotifContract.Presenter presenter) {
        this.presenter = presenter;

    }

    public void checkFields(NotifFields notifFields){
        if (hasError(notifFields)){
            return;
        }
        presenter.onSuccess();
    }

    private boolean hasError(NotifFields notifFields){

        String title = notifFields.getTitle();
        String description = notifFields.getDescription();
        String venue = notifFields.getVenue();

        if(TextUtils.isEmpty(title) && TextUtils.isEmpty(description) && TextUtils.isEmpty(venue)){
            presenter.onFailed("Fields cannot be empty");
            return true;
        }

        if(TextUtils.isEmpty(title)){
            presenter.onFailed("The title is empty");
            return true;
        }

        if(TextUtils.isEmpty(description)){
            presenter.onFailed("The description is empty");
            return true;
        }

        if(TextUtils.isEmpty(venue)){
            presenter.onFailed("The venue  is empty");
            return true;
        }
        return false;

    }

}
