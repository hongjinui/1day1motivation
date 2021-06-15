package com.naple.android.one_day_one_motivation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout RelativeLayout_1,RelativeLayout_2,RelativeLayout_3,RelativeLayout_4;
    String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout_1 = findViewById(R.id.RelativeLayout_1);
        RelativeLayout_2 = findViewById(R.id.RelativeLayout_2);
        RelativeLayout_3 = findViewById(R.id.RelativeLayout_3);
        RelativeLayout_4 = findViewById(R.id.RelativeLayout_4);

    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.RelativeLayout_1:
                keyword = "#운동브이로그";
                System.out.println(1);
                break;
            case R.id.RelativeLayout_2:
                keyword = "#운동동기부여";
                System.out.println(2);
                break;
            case R.id.RelativeLayout_3:
                keyword = "#공부브이로그";
                System.out.println(3);
                break;
            case R.id.RelativeLayout_4:
                keyword = "#명언동기부여";
                System.out.println(4);
                break;
        }
        System.out.println(keyword);
        Intent intent = new Intent(MainActivity.this, VideosListActivity.class);
        intent.putExtra("keyword",keyword);
        startActivity(intent);
    }
}