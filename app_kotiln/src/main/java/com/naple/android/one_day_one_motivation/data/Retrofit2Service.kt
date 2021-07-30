package com.naple.android.one_day_one_motivation.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface Retrofit2Service {
    @GET("/video")
    fun getData(@Query("keyword") keyword: String?): Call<List<Video?>?>?

    @GET("/user")
    fun getUser(@Query("uuid") uuid: String?): Call<Map<String?, String?>?>?
}