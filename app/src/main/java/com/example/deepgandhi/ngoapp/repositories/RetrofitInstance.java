package com.example.deepgandhi.ngoapp.repositories;

import com.example.deepgandhi.ngoapp.Utils.LiveDataCallAdapterFactory;

import retrofit2.Retrofit;


public class RetrofitInstance {

    private static Retrofit retrofit;
    private static String baseUrl = "https://services.investo2o.com";
    public static Retrofit getAutoCompleteInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                    .build();
        }

        return retrofit;
    }
}
