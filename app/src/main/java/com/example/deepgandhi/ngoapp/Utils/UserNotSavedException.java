package com.example.deepgandhi.ngoapp.Utils;

public class UserNotSavedException extends Exception {
    @Override
    public String getMessage() {
        return "User not saved";
    }
}
