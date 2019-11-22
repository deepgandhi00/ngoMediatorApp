package com.example.deepgandhi.ngoapp.adapters;

import com.example.deepgandhi.ngoapp.fragments.EventListFragment;
import com.example.deepgandhi.ngoapp.fragments.HomeFragment;
import com.example.deepgandhi.ngoapp.fragments.NGOListFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new NGOListFragment();
        }
        else if(position == 1){
            return new EventListFragment();
        }
        else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
