package com.naple.android.one_day_one_motivation.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.naple.android.one_day_one_motivation.R;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;

public class LicenseInfoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license_info);

        toolbar = findViewById(R.id.Toolbar_license);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String itemText = intent.getStringExtra("itemText");

        actionBar.setTitle(itemText);

        textView = findViewById(R.id.TextView_license);
        textView.setMovementMethod(new ScrollingMovementMethod());

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.license);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            String s = new String(b);

            textView.setText(s);
        } catch (Exception e) {

        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}