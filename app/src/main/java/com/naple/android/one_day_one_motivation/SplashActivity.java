package com.naple.android.one_day_one_motivation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new splashHandler(), 600);
    }

    private class splashHandler implements Runnable {
        public void run(){
            startActivity(new Intent(getApplication(), VideosListActivity.class));
            SplashActivity.this.finish();

        }
    }
}