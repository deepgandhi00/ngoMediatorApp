package com.example.deepgandhi.ngoapp.viewModels;



import com.example.deepgandhi.ngoapp.Utils.Resource;
import com.example.deepgandhi.ngoapp.models.Request.LoginUser;
import com.example.deepgandhi.ngoapp.models.Request.RequestUser;
import com.example.deepgandhi.ngoapp.models.Response.EventModel;
import com.example.deepgandhi.ngoapp.models.Response.NGODetailEventModel;
import com.example.deepgandhi.ngoapp.models.Response.NGOModel;
import com.example.deepgandhi.ngoapp.models.Response.Users;
import com.example.deepgandhi.ngoapp.repos.ServiceRepo;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CommonViewModel extends ViewModel {


    private ServiceRepo serviceRepo;

    private LiveData<Resource<Users>> signInUser;

    private LiveData<Resource<Users>> loginUser;

    private LiveData<Resource<List<NGOModel>>> ngolist;

    private LiveData<Resource<List<EventModel>>> eventlist;

    private LiveData<Resource<List<NGODetailEventModel>>> detailEventList;


    @Inject
    public CommonViewModel(ServiceRepo serviceRepo){
        this.serviceRepo = serviceRepo;
    }

    //get sign in user
    public LiveData<Resource<Users>> getSignInUser(RequestUser user){
        if(signInUser == null){
            signInUser = getSignedInUser(user);
            return signInUser;
        }
        else {
            return signInUser;
        }
    }

    private LiveData<Resource<Users>> getSignedInUser(RequestUser user) {
        return serviceRepo.lsignInUser(user);
    }


    //get login user
    public LiveData<Resource<Users>> getLoginUser(RequestUser user){
        if(loginUser == null){
            loginUser = getLoggedUser(user);
            return loginUser;
        }
        else {
            return loginUser;
        }
    }

    private LiveData<Resource<Users>> getLoggedUser(RequestUser user) {
        return serviceRepo.loginUser(user);
    }

    //get NGO
    public LiveData<Resource<List<NGOModel>>> getNGOs(){
        if(ngolist == null){
            ngolist = getListNGOs();
            return ngolist;
        }
        else {
            return ngolist;
        }
    }

    private LiveData<Resource<List<NGOModel>>> getListNGOs() {
        return serviceRepo.getNGOs();
    }


    //get events
    public LiveData<Resource<List<EventModel>>> getEvents(){
        if(eventlist == null){
            ngolist = getListNGOs();
            return eventlist;
        }
        else {
            return eventlist;
        }
    }

    private LiveData<Resource<List<EventModel>>> getListEvents() {
        return serviceRepo.getEvents();
    }


    //get detail events
    public LiveData<Resource<List<NGODetailEventModel>>> getDetailEvents(){
        if(detailEventList == null){
            detailEventList = getDetailEventsList();
            return detailEventList;
        }
        else {
            return detailEventList;
        }
    }

    private LiveData<Resource<List<NGODetailEventModel>>> getDetailEventsList() {
        return serviceRepo.getDetailEvents();
    }
}
