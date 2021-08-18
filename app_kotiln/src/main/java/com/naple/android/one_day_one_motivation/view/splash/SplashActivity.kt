package com.naple.android.one_day_one_motivation.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.naple.android.one_day_one_motivation.R
import com.naple.android.one_day_one_motivation.databinding.ActivitySplashBinding
import com.naple.android.one_day_one_motivation.view.main.MainActivity
import com.naple.android.one_day_one_motivation.view.splash.presenter.SplashContract
import com.naple.android.one_day_one_motivation.view.splash.presenter.SplashPresenter
import java.util.*

class SplashActivity : AppCompatActivity() , SplashContract.View{

    private var presenter : SplashPresenter? = null
    private lateinit var binding: ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed(
                {startActivity(Intent(application, MainActivity::class.java))}
                ,600)

    }

    override fun onStop() {
        super.onStop()

        //개인설정 파일 데이터 불러오기
        val sharedPreferences = getSharedPreferences(getString(R.string.shardPreferences), MODE_PRIVATE)
        var uuid = sharedPreferences.getString("uuid", "")

        // 개인설정 파일에 UUID 없다면 생성하여 저장하기
        if (uuid == null || uuid == "") {
            val editor = sharedPreferences.edit()
            uuid = UUID.randomUUID().toString()
            editor.putString("uuid", uuid)
            editor.apply()
        } else {
//            uuid = sharedPreferences.getString("uuid", "");
        }
        presenter = SplashPresenter()
        presenter?.loginInsertOrUpdate(uuid)

    }

    override fun onDestroy() {
        super.onDestroy()

        presenter = null
    }
}