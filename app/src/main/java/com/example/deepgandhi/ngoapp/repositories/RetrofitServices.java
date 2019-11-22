package com.example.deepgandhi.ngoapp.repositories;



import com.example.deepgandhi.ngoapp.Utils.ApiResponse;
import com.example.deepgandhi.ngoapp.models.Request.LoginUser;
import com.example.deepgandhi.ngoapp.models.Request.RequestUser;
import com.example.deepgandhi.ngoapp.models.Response.EventModel;
import com.example.deepgandhi.ngoapp.models.Response.NGODetailEventModel;
import com.example.deepgandhi.ngoapp.models.Response.NGOModel;
import com.example.deepgandhi.ngoapp.models.Response.Users;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RetrofitServices {

//    @GET("/stockFilter2")
//    @Headers({
////            "Access-Token: MWNjMGYwN2YtOTEzZS00N2I0LTkyNjgtZjA1NDc2OTc4YWFlJVVTRVIlNDAyMQ==",
//            "Agent: Mozilla/5.0 (Linux; Android 7.1.1; Android SDK built for x86 Build/NYC; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.100 Mobile Safari/537.36",
////            "User-ID: 4021",
////            "User-IP: 0.0.0.0"
//    })
//    LiveData<ApiResponse<List<Stocks>>> getStocks(@Query("priceCategory") Integer priceCategory,
//                                                  @Query("assetClass") String assetClass);

    @PUT("/user")
    @Headers({
//            "Access-Token: MWNjMGYwN2YtOTEzZS00N2I0LTkyNjgtZjA1NDc2OTc4YWFlJVVTRVIlNDAyMQ==",
            "Agent: Mozilla/5.0 (Linux; Android 7.1.1; Android SDK built for x86 Build/NYC; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.100 Mobile Safari/537.36",
//            "User-ID: 4021",
//            "User-IP: 0.0.0.0"
    })
    LiveData<ApiResponse<Users>> signInUser(@Body RequestUser requestUser);


    @POST("/user/login")
    @Headers({
//            "Access-Token: MWNjMGYwN2YtOTEzZS00N2I0LTkyNjgtZjA1NDc2OTc4YWFlJVVTRVIlNDAyMQ==",
            "Agent: Mozilla/5.0 (Linux; Android 7.1.1; Android SDK built for x86 Build/NYC; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.100 Mobile Safari/537.36",
//            "User-ID: 4021",
//            "User-IP: 0.0.0.0"
    })
    LiveData<ApiResponse<Users>> loginUser(@Body RequestUser user);

    @GET("/ngo/all")
    @Headers({
//            "Access-Token: MWNjMGYwN2YtOTEzZS00N2I0LTkyNjgtZjA1NDc2OTc4YWFlJVVTRVIlNDAyMQ==",
            "Agent: Mozilla/5.0 (Linux; Android 7.1.1; Android SDK built for x86 Build/NYC; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.100 Mobile Safari/537.36",
//            "User-ID: 4021",
//            "User-IP: 0.0.0.0"
    })
    LiveData<ApiResponse<List<NGOModel>>> getNGOs();

    @GET("/events/all")
    @Headers({
//            "Access-Token: MWNjMGYwN2YtOTEzZS00N2I0LTkyNjgtZjA1NDc2OTc4YWFlJVVTRVIlNDAyMQ==",
            "Agent: Mozilla/5.0 (Linux; Android 7.1.1; Android SDK built for x86 Build/NYC; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.100 Mobile Safari/537.36",
//            "User-ID: 4021",
//            "User-IP: 0.0.0.0"
    })
    LiveData<ApiResponse<List<EventModel>>> getEvents();


    @GET
    @Headers({
//            "Access-Token: MWNjMGYwN2YtOTEzZS00N2I0LTkyNjgtZjA1NDc2OTc4YWFlJVVTRVIlNDAyMQ==",
            "Agent: Mozilla/5.0 (Linux; Android 7.1.1; Android SDK built for x86 Build/NYC; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.100 Mobile Safari/537.36",
//            "User-ID: 4021",
//            "User-IP: 0.0.0.0"
    })
    LiveData<ApiResponse<List<NGODetailEventModel>>> getDetailEvents();
}
