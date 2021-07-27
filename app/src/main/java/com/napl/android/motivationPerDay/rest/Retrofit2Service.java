package com.napl.android.motivationPerDay.rest;

import com.napl.android.motivationPerDay.model.VideoDTO;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Retrofit2Service {

    @GET("/video")
    Call<List<VideoDTO>> getData(@Query("keyword") String keyword);

    @GET("/user")
    Call<Map<String,String>> getUser(@Query("uuid") String uuid);
}
