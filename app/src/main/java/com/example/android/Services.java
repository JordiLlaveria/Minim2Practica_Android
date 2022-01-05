package com.example.android;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface Services {
    final String BASE_URL = "http://10.0.2.2:8080";

    @GET("/dsaApp/endpoint/badges")
    Call<List<Badge>> getBadges();
    @GET("/dsaApp/endpoint/user/{userId}")
    Call<User> getUser(@Path("userId") String userId);
}
