package com.unex.android.mvp_notificationapp.ui.addnotification;

public class AddNotifPresenter implements AddNotifContract.Presenter {


    private AddNotifContract.View view;

    @Override
    public void checkFields(String title, String description, String venue) {

        if(title.isEmpty() || description.isEmpty() || venue.isEmpty()){
            view.notifyFieldsCannotBeEmpty();
        }
    }
    
    public AddNotifPresenter(AddNotifContract.View view){
        this.view = view;
    }
}
