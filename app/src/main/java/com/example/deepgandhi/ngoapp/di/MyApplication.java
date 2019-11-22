package com.example.deepgandhi.ngoapp.di;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import retrofit2.Retrofit;

public class MyApplication extends Application implements HasSupportFragmentInjector,HasActivityInjector,Injectable {

    ApplicationComponent component;

    @Inject
    Retrofit retrofit;


    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjectoractivity;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjectorfrags;


    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .application(this)
                .build();

        component.inject(this);

        AppInjector.init(this);
    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return component;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjectoractivity;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjectorfrags;
    }
}
