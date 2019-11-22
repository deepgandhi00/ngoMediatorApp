package com.example.deepgandhi.ngoapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deepgandhi.ngoapp.R;
import com.example.deepgandhi.ngoapp.Utils.Resource;
import com.example.deepgandhi.ngoapp.Utils.Status;
import com.example.deepgandhi.ngoapp.models.Response.EventModel;
import com.example.deepgandhi.ngoapp.models.Response.NGODetailEventModel;
import com.example.deepgandhi.ngoapp.viewModels.CommonViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class NGODetailFragment extends Fragment {
    private LinearLayout ngoDetailTopLinearLayout;
    private ImageView ngoDetailLogo;
    private TextView ngoDetailName;
    private TextView ngoDetailTagLine;
    private RecyclerView ngoDetailActiveEventsRecycler;
    private RecyclerView ngoDetailPastEventsRecycler;
    private Button ngoDetailDonate,ngoDetailVolunteer;
    private CommonViewModel commonViewModel;
    private List<NGODetailEventModel> activeEvents,pastEvents;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ngo_detail,container,false);

        ngoDetailTopLinearLayout = view.findViewById(R.id.ngoDetailTopLinearLayout);
        ngoDetailLogo = view.findViewById(R.id.ngoDetailLogo);
        ngoDetailName = view.findViewById(R.id.ngoDetailName);
        ngoDetailTagLine = view.findViewById(R.id.ngoDetailTagLine);
        ngoDetailActiveEventsRecycler = view.findViewById(R.id.ngoDetailActiveEventsRecycler);
        ngoDetailPastEventsRecycler = view.findViewById(R.id.ngoDetailPastEventsRecycler);
        ngoDetailDonate = view.findViewById(R.id.ngoDetailDonate);
        ngoDetailVolunteer = view.findViewById(R.id.ngoDetailVolunteer);
        activeEvents = new ArrayList<>();
        pastEvents = new ArrayList<>();

        setUpEventsViewModel();

        return view;
    }

    private void setUpEventsViewModel() {
        commonViewModel = ViewModelProviders.of(this).get(CommonViewModel.class);

        commonViewModel.getDetailEvents().observe(getViewLifecycleOwner(), new Observer<Resource<List<NGODetailEventModel>>>() {
            @Override
            public void onChanged(Resource<List<NGODetailEventModel>> listResource) {
                if(listResource.status == Status.SUCCESS){
                    for(NGODetailEventModel eventModel : listResource.data){
                        Date date = new Date();
                        String s = eventModel.getDate().substring(0,10);
                        if(s.substring(0,4).equals(date.getYear()) && s.substring(5,7).equals(date.getMonth()) &&
                                s.substring(8,10).equals(date.getDay())){
                            activeEvents.add(eventModel);
                        }
                        else {
                            pastEvents.add(eventModel);
                        }
                    }

                    setUpAdapters();
                }
            }
        });
    }

    private void setUpAdapters() {

    }


}
