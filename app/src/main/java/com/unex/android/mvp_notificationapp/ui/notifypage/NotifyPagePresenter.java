package com.unex.android.mvp_notificationapp.ui.notifypage;

public class NotifyPagePresenter implements NotifyPageContract.Presenter {

    NotifyPageContract.View view;

    public NotifyPagePresenter(NotifyPageContract.View view) {
        this.view = view;
    }


}
