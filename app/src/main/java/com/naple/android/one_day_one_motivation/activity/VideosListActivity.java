package com.naple.android.one_day_one_motivation.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;
import com.naple.android.one_day_one_motivation.R;
import com.naple.android.one_day_one_motivation.adaptor.RecyclerViewAdapter;
import com.naple.android.one_day_one_motivation.model.VideoDTO;
import com.naple.android.one_day_one_motivation.rest.MongoREST;
import com.naple.android.one_day_one_motivation.util.VideoListComparator;

import java.util.ArrayList;
import java.util.Collections;

public class VideosListActivity extends AppCompatActivity {

    private ArrayList<VideoDTO> videoDTOList;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter adapter;
    private AdView adView_video_list;

    private ActionBar actionBar;
    private MongoREST mongoREST = new MongoREST();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_list);

        // main thread가 disk I/O나 network 작업을 하는지 감시
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        actionBar = getSupportActionBar();

        createEntity();
        actionBar.setTitle("동기부여");
        actionBar.setSubtitle("업로드순서");
        /*
         * keyword
         * 0 : 동기부여
         * 1 : 운동동기부여
         * 2 : 운동브이로그
         * 3 : 공부브이로그
         *  첫 페이지 로딩될 떄 동기부여로 초기화
         *
         */
        String keyword = "0";
        videoDTOList = mongoREST.getVideoList(keyword);

        //선택된 영상 보기 위한 어댑터
        adapter = new RecyclerViewAdapter(videoDTOList);
        recyclerView.setAdapter(adapter);

        //선택된 영상 보기 위한 어댑터
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                //클릭한 영상 channelId
                String videoId = videoDTOList.get(pos).getId();
                Intent intent = new Intent(VideosListActivity.this, VideoScreenActivity.class);
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
                    case R.id.home_motivation:
//                        Toast.makeText(VideosListActivity.this, "동기부여(home)", Toast.LENGTH_SHORT).show();
                        keyword = "0";
                        toolbarKeyword = "동기부여(home)";
                        break;
                    case R.id.exercise_motivation:
//                        Toast.makeText(VideosListActivity.this, "운동동기부여", Toast.LENGTH_SHORT).show();
                        keyword = "1";
                        toolbarKeyword = "운동동기부여";
                        break;
                    case R.id.exercise_vlog:
//                        Toast.makeText(VideosListActivity.this, "운동브이로그", Toast.LENGTH_SHORT).show();
                        keyword = "2";
                        toolbarKeyword = "운동브이로그";
                        break;
                    case R.id.studying_vlog:
//                        Toast.makeText(VideosListActivity.this, "공부브이로그", Toast.LENGTH_SHORT).show();
                        keyword = "3";
                        toolbarKeyword = "공부브이로그";
                        break;
                }
                //비디오리스트 클리어 후 재검색
                drawerLayout.closeDrawer(navigationView);
                videoDTOList.clear();

                actionBar.setTitle(toolbarKeyword);
                actionBar.setSubtitle("업로드순서");

                videoDTOList = mongoREST.getVideoList(keyword);

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

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

        recyclerView = findViewById(R.id.RecyclerView);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView_video_list = findViewById(R.id.adView_video_list);
        adView_video_list.loadAd(adRequest);

        //로그인 정보 insert or update -- 앱 사용 날짜, 총 로그인 횟수 등등
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shardPreferences), MODE_PRIVATE);
        mongoREST.loginInsertOrUpdate(sharedPreferences.getString("uuid", ""));
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
                Toast.makeText(VideosListActivity.this, "settings 준비중..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.open_source_licence:
                startActivity(new Intent(getApplication(), OpenSourceListActivity.class));
                break;
            case R.id.sort:

                String subTitle = actionBar.getSubtitle().toString();

                if (subTitle.equals("업로드순서")) {

                    actionBar.setSubtitle("조회수순서");
                    //비디오 리스트 조회수 순서로 정렬
                    Collections.sort(videoDTOList, new VideoListComparator("조회수순서"));
                } else {

                    actionBar.setSubtitle("업로드순서");
                    //비디오 리스트 업로드 순서로 정렬
                    Collections.sort(videoDTOList, new VideoListComparator("업로드순서"));
                }
                adapter = new RecyclerViewAdapter(videoDTOList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(VideosListActivity.this, actionBar.getSubtitle() + " 정렬", Toast.LENGTH_SHORT).show();
                break;

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