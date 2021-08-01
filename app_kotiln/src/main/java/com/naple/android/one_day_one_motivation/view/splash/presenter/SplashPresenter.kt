package com.naple.android.one_day_one_motivation.view.splash.presenter

import com.naple.android.one_day_one_motivation.model.RetrofitMongo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashPresenter : SplashContract.Presenter{

    private lateinit var retrofitMongo : RetrofitMongo

    override fun loginInsertOrUpdate(uuid: String?) {
        retrofitMongo = RetrofitMongo()
        val retrofit = retrofitMongo.getRetrofit()
        retrofit.getUser(uuid).enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(call: Call<Map<String, String>>, response: Response<Map<String, String>>) {
                if (response.isSuccessful ?: false) {
                    var result : Map<String, String>? = response.body() // stateCode: 200, msg: success

                }
            }
            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }


}