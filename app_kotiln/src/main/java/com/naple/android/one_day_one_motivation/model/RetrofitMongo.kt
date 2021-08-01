package com.naple.android.one_day_one_motivation.model

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitMongo {

    companion object {
      const val BASE_URL = "http://napl.asuscomm.com"
    }

    fun getRetrofit(): RetrofitService {
        Log.d("VideoListData", "getRetrofit() ")
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit.create(RetrofitService::class.java)
    }

}