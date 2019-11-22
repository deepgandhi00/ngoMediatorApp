package com.example.deepgandhi.ngoapp.models.Response;

import java.util.Date;

public class EventModel {
    private String title;
    private String description;
    private Date event_on;

    public EventModel(String title, String description, Date event_on) {
        this.title = title;
        this.description = description;
        this.event_on = event_on;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEvent_on() {
        return event_on;
    }

    public void setEvent_on(Date event_on) {
        this.event_on = event_on;
    }
}
