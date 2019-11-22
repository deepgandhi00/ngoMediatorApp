package com.example.deepgandhi.ngoapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepgandhi.ngoapp.R;
import com.example.deepgandhi.ngoapp.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class HomeFragment extends Fragment {
    private TabLayout homeTabs;
    private ViewPager homeViewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home,container,false);

        homeTabs = view.findViewById(R.id.homeTabs);
        homeViewPager = view.findViewById(R.id.homeViewPager);

        homeViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        homeTabs.setupWithViewPager(homeViewPager);
        return view;
    }
}
