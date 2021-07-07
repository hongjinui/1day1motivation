package com.naple.android.one_day_one_motivation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.naple.android.one_day_one_motivation.R;
import com.naple.android.one_day_one_motivation.rest.MongoREST;

import java.util.UUID;

public class SplashActivity extends AppCompatActivity {

    private MongoREST mongoREST = new MongoREST();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //개인설정 파일 데이터 불러오기
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shardPreferences), MODE_PRIVATE);
        String uuid = sharedPreferences.getString("uuid", "");
        // 개인설정 파일에 UUID에 대한 데이터가 없다면 생성하여 저장하기
        if (uuid == null || uuid.equals("")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("uuid", UUID.randomUUID().toString());
            editor.commit();
        } else {
//            uuid = sharedPreferences.getString("uuid", "");
        }

        Handler handler = new Handler();
        handler.postDelayed(new splashHandler(), 600);
    }

    private class splashHandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), VideosListActivity.class));
            SplashActivity.this.finish();

        }
    }
}