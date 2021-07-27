package com.naple.android.one_day_one_motivation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.naple.android.one_day_one_motivation.R;

import java.util.ArrayList;
import java.util.List;

public class OpenSourceListActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_source_list);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("오픈소스 라이선스");

        List<String> arrayList = new ArrayList<>();
        arrayList.add("Android material SDK");
        arrayList.add("Androidx appcompat library");
        arrayList.add("Androidx constraintlayout library");
        arrayList.add("Google android library");
        arrayList.add("Google api-client library");
        arrayList.add("Google apis library");
        arrayList.add("Google oauth-client library");
        arrayList.add("Picasso library");
        arrayList.add("Square retrofit2 library");
        arrayList.add("YouTubeAndroidPlayerApi library");


        listView = findViewById(R.id.ListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplication(),LicenseInfoActivity.class);
                intent.putExtra("itemText", arrayList.get(i));

                startActivity(intent);
            }
        });

    }

    // 메뉴 아이템 선택 이벤트
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {finish();}
        return true;
    }
}