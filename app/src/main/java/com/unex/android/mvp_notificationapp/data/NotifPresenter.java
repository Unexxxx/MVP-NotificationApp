package com.unex.android.mvp_notificationapp.data;

import android.text.TextUtils;
import android.util.Patterns;

import com.unex.android.mvp_notificationapp.ui.addnotification.AddNotifContract;

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
            presenter.onFailed("Please input fields");
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
