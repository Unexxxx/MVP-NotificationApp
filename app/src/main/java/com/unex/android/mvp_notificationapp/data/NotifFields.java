package com.unex.android.mvp_notificationapp.data;

public class NotifFields {

    private String title;

    private String description;

    private String venue;

    public NotifFields(String title, String description, String venue) {
        this.title = title;
        this.description = description;
        this.venue = venue;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVenue() {
        return venue;
    }
}
