package com.naple.android.one_day_one_motivation.view.main

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.os.StrictMode
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.material.navigation.NavigationView
import com.naple.android.one_day_one_motivation.R
import com.naple.android.one_day_one_motivation.databinding.ActivityMainBinding
import com.naple.android.one_day_one_motivation.view.license.OpnSrcActivity
import com.naple.android.one_day_one_motivation.view.main.presenter.MainContract
import com.naple.android.one_day_one_motivation.view.main.presenter.MainPresenter

class MainActivity : AppCompatActivity(),
        MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var orderUpload: MenuItem
    private lateinit var orderViewCount: MenuItem
    private lateinit var presenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // main thread가 disk I/O나 network 작업을 하는지 감시
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // 액션바
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu)
        supportActionBar?.setTitle("동기부여")
        supportActionBar?.setSubtitle("업로드순서")


        // 네비게이션 바
        binding.NavigationView.setNavigationItemSelectedListener(this)

        // presenter 초기화
        presenter = MainPresenter(recyclerView = binding.RecyclerView,mainContext = applicationContext)


        // 리사이클러 뷰 레이아웃 매니저
        binding.RecyclerView.layoutManager = GridLayoutManager(this, 1)
        // 리사이클러 뷰 아이템의 크기 고정, false -> 매번 아이템 크기를 계산해서 성능저하ㅣ
        binding.RecyclerView.setHasFixedSize(true)


        // adMob
        val adRequest = AdRequest.Builder().build()
        binding.AdView.loadAd(adRequest)

        /*
         * keyword
         * 0 : 동기부여
         * 1 : 운동동기부여
         * 2 : 운동브이로그
         * 3 : 공부브이로그
         *  첫 페이지 로딩될 떄 동기부여로 초기화
         *
         */
        val keyword = "0"
        presenter.setVideoList(keyword)
    }


    //네비게이션 아이템 클릭 시
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var keyword = ""
        var toolbarKeyword = ""
        when (item.itemId) {
            R.id.home_motivation -> {
                keyword = "0"
                toolbarKeyword = "동기부여(home)"
            }
            R.id.exercise_motivation -> {
                keyword = "1"
                toolbarKeyword = "운동동기부여"
            }
            R.id.exercise_vlog -> {
                keyword = "2"
                toolbarKeyword = "운동브이로그"
            }
            R.id.studying_vlog -> {
                keyword = "3"
                toolbarKeyword = "공부브이로그"
            }
        }
        binding.DrawerLayout.closeDrawer(binding.NavigationView)
        supportActionBar?.setTitle(toolbarKeyword)
        supportActionBar?.setSubtitle("업로드순서")
        orderUpload.setChecked(true)

        presenter.setVideoList(keyword)

        return true
    }

    //액션바 메뉴 inflate
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)
        orderUpload = menu.findItem(R.id.orderUpload)
        orderViewCount = menu.findItem(R.id.orderViewCount)
        return true
    }

    // 액션바 아이템 클릭 시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> binding.DrawerLayout.openDrawer(GravityCompat.START)
            R.id.open_source_licence -> startActivity(Intent(application, OpnSrcActivity::class.java))
            R.id.orderUpload -> presenter.sortVideoList(item,"업로드순서",false,supportActionBar!!)
            R.id.orderViewCount -> presenter.sortVideoList(item,"조회수순서",false,supportActionBar!!)
            R.id.sort -> {
                val subtitle = supportActionBar?.subtitle.toString()
                if(subtitle.equals("업로드순서")){
                    orderViewCount.setChecked(true)
                    presenter.sortVideoList(item,"조회수순서",true,supportActionBar!!)
                }else{
                    orderUpload.setChecked(true)
                    presenter.sortVideoList(item,"업로드순서",true,supportActionBar!!)
                }
            }
        }
        return true
    }

    // 액티비티 종료 직전 호출
    override fun onDestroy() {
        super.onDestroy()

        //
        presenter.release()
    }


    private var lastTimeBackPressed: Long = 0

    // 뒤로가기 눌렀을 때
    override fun onBackPressed() {

        val drawerLayout : DrawerLayout = binding.DrawerLayout
        val navigationView : NavigationView = binding.NavigationView

        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView)
        } else {
            //2초 이내에 뒤로가기 버튼을 재 클릭 시 앱 종료
            if (System.currentTimeMillis() - lastTimeBackPressed < 2000) {
                AppFinish()
                return
            }
            Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
            lastTimeBackPressed = System.currentTimeMillis()
        }
    }

    //앱종료
    fun AppFinish() {
        finish()
        System.exit(0)
        Process.killProcess(Process.myPid())
    }

}