package com.example.deepgandhi.ngoapp.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepgandhi.ngoapp.R;
import com.example.deepgandhi.ngoapp.Utils.Resource;
import com.example.deepgandhi.ngoapp.Utils.Status;
import com.example.deepgandhi.ngoapp.adapters.NGORecyclerAdapter;
import com.example.deepgandhi.ngoapp.di.Injectable;
import com.example.deepgandhi.ngoapp.models.Response.NGOModel;
import com.example.deepgandhi.ngoapp.viewModels.CommonViewModel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NGOListFragment extends Fragment implements Injectable /*LocationListener*/ {
    private RecyclerView fragmentNGORecyclerView;
    private CommonViewModel viewModel;
    private LocationManager locationManager;
    private LocationListener locationListener;


    @Inject
    ViewModelProvider.Factory factory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ngos, container, false);

//        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//        }
//        else {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            fragmentNGORecyclerView = view.findViewById(R.id.fragmentNGORecyclerView);
            fragmentNGORecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            setUpViewModel();
//        }
        return view;
    }

    private void setUpViewModel() {
        viewModel = ViewModelProviders.of(this,factory).get(CommonViewModel.class);
        viewModel.getNGOs().observe(getViewLifecycleOwner(), new Observer<Resource<List<NGOModel>>>() {
            @Override
            public void onChanged(Resource<List<NGOModel>> listResource) {
                if(listResource.status == Status.SUCCESS){
                    setUpAdapter(listResource.data);
                }
            }
        });
    }

    private void setUpAdapter(List<NGOModel> data) {
        fragmentNGORecyclerView.setAdapter(new NGORecyclerAdapter(data,getContext()));
    }

//    @Override
//    public void onLocationChanged(Location location) {
////        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
////        List<Address> addresses = null;
////        try {
////            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        String cityName = addresses.get(0).getAddressLine(0);
////        setUpViewModel();
//    }
//
//    @Override
//    public void onStatusChanged(String s, int i, Bundle bundle) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String s) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String s) {
//
//    }
}
