 package com.naple.android.one_day_one_motivation;

 import android.content.Intent;
 import android.os.Bundle;
 import android.os.StrictMode;
 import android.view.Menu;
 import android.view.MenuItem;
 import android.view.View;
 import android.widget.LinearLayout;
 import android.widget.TextView;
 import android.widget.Toast;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.ActionBar;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.appcompat.widget.Toolbar;
 import androidx.core.view.GravityCompat;
 import androidx.drawerlayout.widget.DrawerLayout;
 import androidx.recyclerview.widget.GridLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import com.google.android.material.navigation.NavigationView;
 import com.google.api.services.youtube.model.Video;

 import java.util.List;

 public class VideosListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
//    List<VideoDTO> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_list);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Search search = new Search();
        createEntity();

        Bundle bundle = getIntent().getExtras() ==  null ? new Bundle() : getIntent().getExtras();
        String keyword = bundle.getString("keyword");
        if( keyword != null && !keyword.equals("")) {
        } else{
            keyword = "#동기부여";
        }

        toolbar = findViewById(R.id.Toolbar);
        toolbar.setTitle(keyword);

        List<Video> videoList = search.getVideos(keyword);
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
        adapter = new RecyclerViewAdapter(videoList);
        recyclerView.setAdapter(adapter);

        RecyclerViewAdapter adapter2 = new RecyclerViewAdapter();
        adapter2.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                String videoId = videoList.get(pos).getId();
                Intent intent = new Intent(VideosListActivity.this, VideoScreen.class);
                intent.putExtra("videoId", videoId);
                System.out.println(videoId + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
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
                        Toast.makeText(VideosListActivity.this, "운동브이로그!", Toast.LENGTH_SHORT).show();
                        keyword = "#운동브이로그";
                        break;
                    case R.id.exercise_vlog:
                        Toast.makeText(VideosListActivity.this, "운동동기부여!", Toast.LENGTH_SHORT).show();
                        keyword = "#운동동기부여";
                        break;
                    case R.id.studying_vlog:
                        Toast.makeText(VideosListActivity.this, "공부브이로그!", Toast.LENGTH_SHORT).show();
                        keyword = "#공부브이로그";
                        break;
                    case R.id.wise_saying_motivation:
                        Toast.makeText(VideosListActivity.this, "동기부여!", Toast.LENGTH_SHORT).show();
                        keyword = "#동기부여";
                        break;
                }

                Intent intent = new Intent(VideosListActivity.this, VideosListActivity.class);
                intent.putExtra("keyword", keyword);
                startActivity(intent);

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

        switch(item.getItemId()){
            case android.R.id.home:
            drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.settings:
                Toast.makeText(VideosListActivity.this, "settings!", Toast.LENGTH_SHORT).show();
        }


         return true;
     }
/*
     public void onClick(View view) {

        try {
            System.out.println("1");
            String text = "";
            if (view.getId() == R.id.LinearLayout_recycler){
                System.out.println("2");
                LinearLayout linearLayout = findViewById(R.id.LinearLayout_recycler);
                if(linearLayout.getChildAt(1).getId() == R.id.LinearLayout_videoInfo){
                    System.out.println("3");

                    LinearLayout linearLayout2 = findViewById(R.id.LinearLayout_videoInfo);
                    TextView textView = (TextView)linearLayout2.getChildAt(3);
                    text = textView.getText().toString();

                    System.out.println(text + "#################");
                }
            }else{
            }
            Intent intent = new Intent(VideosListActivity.this, VideoScreen.class);
            intent.putExtra("videoId", text);
            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }

     }*/
 }