package com.naple.android.one_day_one_motivation.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/video")
    fun getData(@Query("keyword") keyword: String?): Call<ArrayList<Video>>

    @GET("/user")
    fun getUser(@Query("uuid") uuid: String?): Call<Map<String, String>>
}