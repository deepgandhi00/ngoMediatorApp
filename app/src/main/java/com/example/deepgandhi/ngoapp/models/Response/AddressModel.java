package com.example.deepgandhi.ngoapp.models.Response;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class AddressModel {

    @SerializedName("line1")
    private String line1;

    @SerializedName("line2")
    private String line2;

    @SerializedName("pincode")
    private int pincode;

    @SerializedName("city")
    private String city;

    @SerializedName("country")
    private String country;

    public AddressModel(String line1, String line2, int pincode, String city, String country) {
        this.line1 = line1;
        this.line2 = line2;
        this.pincode = pincode;
        this.city = city;
        this.country = country;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @NonNull
    @Override
    public String toString() {
        return line1 + " " + line2 + " " + pincode;
    }
}
