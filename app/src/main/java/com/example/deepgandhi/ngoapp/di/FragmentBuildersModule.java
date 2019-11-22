package com.example.deepgandhi.ngoapp.di;


import com.example.deepgandhi.ngoapp.fragments.LoginFragment;
import com.example.deepgandhi.ngoapp.fragments.SignUpFragment;
import com.example.deepgandhi.ngoapp.fragments.SplashFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract SplashFragment splashFragment();

    @ContributesAndroidInjector
    abstract LoginFragment loginFragment();

    @ContributesAndroidInjector
    abstract SignUpFragment signUpFragment();
}
