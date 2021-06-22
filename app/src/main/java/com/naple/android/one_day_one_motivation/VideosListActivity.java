package com.naple.android.one_day_one_motivation;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;
import com.google.api.services.youtube.model.Video;

import java.util.ArrayList;
import java.util.List;

public class VideosListActivity extends AppCompatActivity {

    static private List<Video> videoList = new ArrayList<>();

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private AdView adView_video_list;
    private AdView adView_navi;
//    List<VideoDTO> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_list);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Search search = new Search();
        createEntity();

        Bundle bundle = getIntent().getExtras() == null ? new Bundle() : getIntent().getExtras();
        String keyword = bundle.getString("keyword");
        if (keyword != null && !keyword.equals("")) {
        } else {
            keyword = "#동기부여";
        }

        toolbar = findViewById(R.id.Toolbar);
        toolbar.setTitle(keyword);

        videoList = search.getVideos(keyword);
        /*
        List<VideoDTO> videoDTOList = new ArrayList<>();
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setDuration("08:00");
        videoDTO.setTitle(keyword);
        videoDTOList.add(videoDTO);

        videoDTO = new VideoDTO();
        videoDTO.setDuration("12:12");
        videoDTO.setTitle(keyword);
        videoDTOList.add(videoDTO);
        */
        if(videoList == null || videoList.size() == 0){
            System.out.println("하루 할당량 쿼리 소진");
            return;
        }

        adapter = new RecyclerViewAdapter(videoList);
        recyclerView.setAdapter(adapter);

        RecyclerViewAdapter adapter2 = new RecyclerViewAdapter();
        adapter2.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                String videoId = videoList.get(pos).getId();
                Intent intent = new Intent(VideosListActivity.this, VideoScreen.class);
                intent.putExtra("videoId", videoId);
                startActivity(intent);
            }
        });

        // 네비게이션 화면 이벤트 처리
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            //            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String keyword = "";
                switch (item.getItemId()) {
                    case R.id.exercise_motivation:
                        Toast.makeText(VideosListActivity.this, "운동 동기부여", Toast.LENGTH_SHORT).show();
                        keyword = "#운동동기부여";
                        break;
                    case R.id.exercise_vlog:
                        Toast.makeText(VideosListActivity.this, "운동 브이로그", Toast.LENGTH_SHORT).show();
                        keyword = "#운동브이로그";
                        break;
                    case R.id.studying_vlog:
                        Toast.makeText(VideosListActivity.this, "공부 브이로그", Toast.LENGTH_SHORT).show();
                        keyword = "#공부브이로그";
                        break;
                    case R.id.wise_saying_motivation:
                        Toast.makeText(VideosListActivity.this, "동기부여(home)", Toast.LENGTH_SHORT).show();
                        keyword = "#동기부여";
                        break;
                }
                /*Intent intent = new Intent(VideosListActivity.this, VideosListActivity.class);
                intent.putExtra("keyword", keyword);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/
                drawerLayout.closeDrawer(navigationView);
                recyclerView.removeAllViews();
                toolbar.setTitle(keyword);
                videoList = search.getVideos(keyword);
                adapter = new RecyclerViewAdapter(videoList);
                recyclerView.setAdapter(adapter);

                return true;
            }
        });

    }


    // entity 생성
    private void createEntity() {

        navigationView = findViewById(R.id.NavigationView);
        drawerLayout = findViewById(R.id.DrawerLayout);
        toolbar = findViewById(R.id.Toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
        recyclerView = findViewById(R.id.RecyclerView);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView_video_list = findViewById(R.id.adView_video_list);
        adView_video_list.loadAd(adRequest);
//        adView_navi = findViewById(R.id.adView_navi);
//        adView_navi.loadAd(adRequest);

    }

    // 툴바에 메뉴들 나열?
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    // 메뉴 아이템 선택 이벤트
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.settings:
                Toast.makeText(VideosListActivity.this, "settings!", Toast.LENGTH_SHORT).show();
        }


        return true;
    }

    private long lastTimeBackPressed;

    @Override
    public void onBackPressed() {


        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView);
        } else {
            //2초 이내에 뒤로가기 버튼을 재 클릭 시 앱 종료


            if (System.currentTimeMillis() - lastTimeBackPressed < 2000) {
                AppFinish();
                return;
            }
            Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
            lastTimeBackPressed = System.currentTimeMillis();


        }
    }

    //앱종료
    public void AppFinish() {
        finish();
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}