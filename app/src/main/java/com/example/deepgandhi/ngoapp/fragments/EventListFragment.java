package com.example.deepgandhi.ngoapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepgandhi.ngoapp.R;
import com.example.deepgandhi.ngoapp.Utils.Resource;
import com.example.deepgandhi.ngoapp.Utils.Status;
import com.example.deepgandhi.ngoapp.adapters.EventsRecyclerAdapter;
import com.example.deepgandhi.ngoapp.models.Response.EventModel;
import com.example.deepgandhi.ngoapp.viewModels.CommonViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EventListFragment extends Fragment {
    private RecyclerView eventsRecyclerView;
    private CommonViewModel viewModel;

    @Inject
    ViewModelProvider.Factory factory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events,container,false);
        eventsRecyclerView = view.findViewById(R.id.eventsRecyclerView);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setUpViewModel();
        return view;
    }

    private void setUpViewModel() {
        viewModel = ViewModelProviders.of(this,factory).get(CommonViewModel.class);

        viewModel.getEvents().observe(getViewLifecycleOwner(), listResource -> {
            if(listResource.status == Status.SUCCESS){
                setUpAdapter(listResource.data);
            }
        });
    }

    private void setUpAdapter(List<EventModel> data) {
        eventsRecyclerView.setAdapter(new EventsRecyclerAdapter(data,getContext()));
    }
}
