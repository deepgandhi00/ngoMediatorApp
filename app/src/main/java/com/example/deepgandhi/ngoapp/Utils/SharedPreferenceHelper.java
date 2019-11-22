package com.example.deepgandhi.ngoapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.deepgandhi.ngoapp.models.Response.Users;

public class SharedPreferenceHelper {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String PREFERENCE = "data";
    private Context context;
    public static final String EMAIL = "email";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String DOB = "dob";

    public SharedPreferenceHelper(Context context) {
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(PREFERENCE,Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    public boolean saveUser(Users users){
        editor.putString(EMAIL,users.getEmail());
        editor.putString(ID,users.getId());
        editor.putString(NAME,users.getName());
        editor.putString(PHONE,users.getPhone());
        editor.putString(DOB,users.getDate_of_birth());

        return editor.commit();
    }

    public boolean checkData(){
        if(sharedPreferences.contains(EMAIL) && sharedPreferences.contains(ID)){
            return true;
        }
        else {
            return false;
        }
    }

    public Users getUser() throws UserNotSavedException {
        if(checkData()){
            Users users = new Users();
            users.setId(sharedPreferences.getString(ID,null));
            users.setEmail(sharedPreferences.getString(EMAIL,null));
            users.setName(sharedPreferences.getString(NAME,null));
            users.setPassword(null);
            users.setPhone(sharedPreferences.getString(PHONE,null));
            users.setDate_of_birth(sharedPreferences.getString(DOB,null));

            return users;
        }
        else {
            throw new UserNotSavedException();
        }
    }

    public boolean removeUser(){
        if(checkData()){
            editor.remove(EMAIL);
            editor.remove(ID);
            editor.remove(NAME);
            editor.remove(PHONE);
            editor.remove(DOB);

            return editor.commit();
        }
        else {
            return false;
        }
    }
}
