package com.example.deepgandhi.ngoapp.di;

import android.app.Application;

import com.example.deepgandhi.ngoapp.Utils.LiveDataCallAdapterFactory;
import com.example.deepgandhi.ngoapp.repositories.RetrofitServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class UrlModule {

    private String baseUrl = "https://tranquil-garden-97851.herokuapp.com";
    private String localUrl = "https://tranquil-garden-97851.herokuapp.com";


    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeSpecialFloatingPointValues();
        gsonBuilder.setLenient();
        return gsonBuilder.create();
    }



    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .baseUrl(baseUrl)
                .build();
    }


//    @Provides
//    @Singleton
//    public MyInfoService providesMyInfoService(
//            Retrofit retrofit) {
//        return retrofit.create(MyInfoService.class);
//    }

    @Provides
    @Singleton
    public RetrofitServices getServices(Retrofit retrofit){
        return retrofit.create(RetrofitServices.class);
    }

    @Provides
    @Singleton
    @Named("local")
    Retrofit provideRetrofitLocal(Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .baseUrl(localUrl)
                .build();
    }

    @Provides
    @Singleton
    @Named("local")
    public RetrofitServices getServicesLocal(@Named("local") Retrofit retrofit){
        return retrofit.create(RetrofitServices.class);
    }
}
