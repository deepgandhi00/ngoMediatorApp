package com.example.deepgandhi.ngoapp.repos;




import com.example.deepgandhi.ngoapp.Utils.AbsentLiveData;
import com.example.deepgandhi.ngoapp.Utils.ApiResponse;
import com.example.deepgandhi.ngoapp.Utils.AppExecutors;
import com.example.deepgandhi.ngoapp.Utils.NetworkBoundResource;
import com.example.deepgandhi.ngoapp.Utils.Resource;
import com.example.deepgandhi.ngoapp.models.Request.LoginUser;
import com.example.deepgandhi.ngoapp.models.Request.RequestUser;
import com.example.deepgandhi.ngoapp.models.Response.EventModel;
import com.example.deepgandhi.ngoapp.models.Response.NGODetailEventModel;
import com.example.deepgandhi.ngoapp.models.Response.NGOModel;
import com.example.deepgandhi.ngoapp.models.Response.Users;
import com.example.deepgandhi.ngoapp.repositories.RetrofitServices;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

@Singleton
public class ServiceRepo {

//    Retrofit retrofit;

    RetrofitServices services;

    @Inject
    RetrofitServices localServices;

    AppExecutors appExecutors;

//    private List<Stocks> AllEtflist;
//
//    private StocksWithPagination stocksWithPagination;

    @Inject
    public ServiceRepo(AppExecutors appExecutors, RetrofitServices services){
        this.appExecutors = appExecutors;
        this.services = services;
    }

    public LiveData<Resource<Users>> lsignInUser(RequestUser requestUser){
        return new NetworkBoundResource<Users, Users>(appExecutors) {
            private Users users;
            @Override
            protected void saveCallResult(@NonNull Users item) {
                users = item;
            }

            @Override
            protected boolean shouldFetch(@Nullable Users data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<Users> loadFromDb() {
                if(users == null){
                    return AbsentLiveData.create();
                }
                else{
                    return new LiveData<Users>() {
                        @Override
                        protected void onActive() {
                            super.onActive();
                            setValue(users);
                        }
                    };
                }
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Users>> createCall() {
                return localServices.signInUser(requestUser);
            }
        }.asLiveData();
    }

    public LiveData<Resource<Users>> loginUser(RequestUser loginUser){
        return new NetworkBoundResource<Users,Users>(appExecutors){
            private Users users;
            @Override
            protected void saveCallResult(@NonNull Users item) {
                users = item;
            }

            @Override
            protected boolean shouldFetch(@Nullable Users data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<Users> loadFromDb() {
                if(users == null){
                    return AbsentLiveData.create();
                }
                else{
                    return new LiveData<Users>() {
                        @Override
                        protected void onActive() {
                            super.onActive();
                            setValue(users);
                        }
                    };
                }
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Users>> createCall() {
                return services.loginUser(loginUser);
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<NGOModel>>> getNGOs(){
        return new NetworkBoundResource<List<NGOModel>,List<NGOModel>>(appExecutors){
            private List<NGOModel> list;
            @Override
            protected void saveCallResult(@NonNull List<NGOModel> item) {
                list = item;
            }

            @Override
            protected boolean shouldFetch(@Nullable List<NGOModel> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<NGOModel>> loadFromDb() {
                if(list == null){
                    return AbsentLiveData.create();
                }
                else{
                    return new LiveData<List<NGOModel>>() {
                        @Override
                        protected void onActive() {
                            super.onActive();
                            setValue(list);
                        }
                    };
                }
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<NGOModel>>> createCall() {
                return localServices.getNGOs();
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<EventModel>>> getEvents(){
        return new NetworkBoundResource<List<EventModel>,List<EventModel>>(appExecutors){
            private List<EventModel> list;
            @Override
            protected void saveCallResult(@NonNull List<EventModel> item) {
                list = item;
            }

            @Override
            protected boolean shouldFetch(@Nullable List<EventModel> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<EventModel>> loadFromDb() {
                if(list == null){
                    return AbsentLiveData.create();
                }
                else{
                    return new LiveData<List<EventModel>>() {
                        @Override
                        protected void onActive() {
                            super.onActive();
                            setValue(list);
                        }
                    };
                }
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<EventModel>>> createCall() {
                return services.getEvents();
            }
        }.asLiveData();
    }


    //ngo detail events recycler
    public LiveData<Resource<List<NGODetailEventModel>>> getDetailEvents(){
        return new NetworkBoundResource<List<NGODetailEventModel>,List<NGODetailEventModel>>(appExecutors){
            private List<NGODetailEventModel> ngoDetailEventModels;
            @Override
            protected void saveCallResult(@NonNull List<NGODetailEventModel> item) {
                ngoDetailEventModels = item;
            }

            @Override
            protected boolean shouldFetch(@Nullable List<NGODetailEventModel> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<NGODetailEventModel>> loadFromDb() {
                if(ngoDetailEventModels == null){
                    return AbsentLiveData.create();
                }
                else{
                    return new LiveData<List<NGODetailEventModel>>() {
                        @Override
                        protected void onActive() {
                            super.onActive();
                            setValue(ngoDetailEventModels);
                        }
                    };
                }
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<NGODetailEventModel>>> createCall() {
                return services.getDetailEvents();
            }
        }.asLiveData();
    }
}
