package com.example.deepgandhi.ngoapp.models.Response;

import com.google.gson.annotations.SerializedName;

public class NGODetailEventModel {

    @SerializedName("id")
    private String id;

    @SerializedName("ngo_id")
    private String ngo_id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("event_on")
    private String date;

    public NGODetailEventModel() {
    }

    public NGODetailEventModel(String id, String ngo_id, String title, String description, String date) {
        this.id = id;
        this.ngo_id = ngo_id;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNgo_id() {
        return ngo_id;
    }

    public void setNgo_id(String ngo_id) {
        this.ngo_id = ngo_id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
