package com.naple.android.one_day_one_motivation.view.main.presenter

import android.content.Context
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView
import com.naple.android.one_day_one_motivation.adapter.MainAdapter
import com.naple.android.one_day_one_motivation.model.RetrofitMongo
import com.naple.android.one_day_one_motivation.model.Video
import com.naple.android.one_day_one_motivation.util.VideoListComparator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainPresenter(
        private val recyclerView: RecyclerView,
        private val mainContext: Context,
) : MainContract.Presenter {

    private var videoList :  ArrayList<Video> = ArrayList()
    private lateinit var retrofitMongo : RetrofitMongo



    override fun setVideoList(keyword: String) {

        retrofitMongo = RetrofitMongo()
        val retrofit = retrofitMongo.getRetrofit()

        retrofit.getData(keyword).enqueue(object : Callback<ArrayList<Video>>{
            override fun onResponse(call: Call<ArrayList<Video>>, response: Response<ArrayList<Video>>) {
                if(response.isSuccessful){
                    videoList = response.body()!!
                    recyclerView.adapter = MainAdapter(videoList)
                }
            }
            override fun onFailure(call: Call<ArrayList<Video>>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    override fun sortVideoList(item: MenuItem, subtitle: String, isSortIcon: Boolean,supportActionBar: ActionBar) {

        if (!isSortIcon){
            item.isChecked = true
        }

        Toast.makeText(mainContext, "$subtitle 정렬", Toast.LENGTH_SHORT).show()
        supportActionBar.subtitle = subtitle
        Collections.sort(videoList, VideoListComparator(subtitle))
        recyclerView.adapter = MainAdapter(videoList)
    }

    override fun release() {
    }

    fun getAdapter() :MainAdapter{
        return MainAdapter(videoList)
    }

}