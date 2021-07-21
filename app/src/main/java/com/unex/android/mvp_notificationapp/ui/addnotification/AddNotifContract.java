package com.unex.android.mvp_notificationapp.ui.addnotification;

public interface AddNotifContract {

    interface View{
        void notifyFieldsCannotBeEmpty();
    }

    interface Presenter{
        void checkFields(String title, String description, String venue);
    }
}
