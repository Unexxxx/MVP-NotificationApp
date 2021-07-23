package com.unex.android.mvp_notificationapp.ui.addnotification;

public interface AddNotifContract {

    interface View{

        void onSuccess();

        void onFailed(String message);
    }

    interface Presenter{

        void onSuccess();

        void onFailed(String message);
    }
}
