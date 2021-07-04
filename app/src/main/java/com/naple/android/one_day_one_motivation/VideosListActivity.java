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

import java.util.ArrayList;

public class VideosListActivity extends AppCompatActivity {

    private ArrayList<VideoDTO> videoDTOList;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter adapter;
    private AdView adView_video_list;
    private AdView adView_navi;

    private VideoDAO videoDAO = new VideoDAO();


    /*
     * keyword
     * 0 : 동기부여
     * 1 : 운동동기부여
     * 2 : 운동브이로그
     * 3 : 공부브이로그
     *
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_list);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        createEntity();

        toolbar = findViewById(R.id.Toolbar);
        toolbar.setTitle("동기부여");

        //첫 페이지 로딩될 떄 동기부여로 초기화
        String keyword = "0";
        videoDTOList = videoDAO.getVideoList(keyword);

        adapter = new RecyclerViewAdapter(videoDTOList);
        recyclerView.setAdapter(adapter);

        //선택된 영상 보기 위한 어댑터
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                String videoId = videoDTOList.get(pos).getId();
                Intent intent = new Intent(VideosListActivity.this, VideoScreen.class);
                intent.putExtra("videoId", videoId);
                startActivity(intent);
            }
        });

        // 네비게이션 화면 이벤트 처리
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String keyword = "";
                String toolbarKeyword = "";
                switch (item.getItemId()) {
                    case R.id.wise_saying_motivation:
                        Toast.makeText(VideosListActivity.this, "동기부여(home)", Toast.LENGTH_SHORT).show();
                        keyword = "0";
                        toolbarKeyword = "동기부여(home)";
                        break;
                    case R.id.exercise_motivation:
                        Toast.makeText(VideosListActivity.this, "운동동기부여", Toast.LENGTH_SHORT).show();
                        keyword = "1";
                        toolbarKeyword = "운동동기부여";
                        break;
                    case R.id.exercise_vlog:
                        Toast.makeText(VideosListActivity.this, "운동브이로그", Toast.LENGTH_SHORT).show();
                        keyword = "2";
                        toolbarKeyword = "운동브이로그";
                        break;
                    case R.id.studying_vlog:
                        Toast.makeText(VideosListActivity.this, "공부브이로그", Toast.LENGTH_SHORT).show();
                        keyword = "3";
                        toolbarKeyword = "공부브이로그";
                        break;
                }
                //비디오리스트 클리어 후 재검색
                drawerLayout.closeDrawer(navigationView);
                toolbar.setTitle(toolbarKeyword);
                videoDTOList.clear();

                videoDTOList = videoDAO.getVideoList(keyword);
                adapter = new RecyclerViewAdapter(videoDTOList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

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

    // 뒤로가기 눌렀을 때
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