 package com.naple.android.one_day_one_motivation;

 import android.os.Bundle;
 import android.view.Menu;
 import android.view.MenuItem;
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

 public class VideosListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    String[] localDataSet = {"00:00","08:12","15:19","03:43","15:15"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_list);

        navigationView = findViewById(R.id.NavigationView);
        drawerLayout = findViewById(R.id.DrawerLayout);
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
        recyclerView = findViewById(R.id.RecyclerView);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(localDataSet);
        recyclerView.setAdapter(adapter);






        // 네비게이션 화면 이벤트 처리
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.exercise_motivation:
                        Toast.makeText(VideosListActivity.this, "운동동기부여!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.exercise_vlog:
                        Toast.makeText(VideosListActivity.this, "운동브이로그!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.studying_vlog:
                        Toast.makeText(VideosListActivity.this, "공부브이로그!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.wise_saying_motivation:
                        Toast.makeText(VideosListActivity.this, "명언동기부여!", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
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
 }