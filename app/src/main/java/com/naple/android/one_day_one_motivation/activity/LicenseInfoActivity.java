package com.naple.android.one_day_one_motivation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.naple.android.one_day_one_motivation.R;

import java.io.InputStream;

public class LicenseInfoActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license_info);

        actionBar = getSupportActionBar();
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