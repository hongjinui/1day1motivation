package com.naple.android.one_day_one_motivation.view.license

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.naple.android.one_day_one_motivation.R
import com.naple.android.one_day_one_motivation.adapter.OpnSrcAdapter
import com.naple.android.one_day_one_motivation.databinding.ActivityOpnSrcBinding
import java.util.*
import kotlin.collections.ArrayList

class OpnSrcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpnSrcBinding
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOpnSrcBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        // 툴바
        val toolbarView = binding.ToolbarOpnsrc
        setSupportActionBar(toolbarView)
        val toolBar = supportActionBar!!
        toolBar.setDisplayHomeAsUpEnabled(true)
        toolBar.title = "오픈소스 라이선스"

        val arrayList: ArrayList<String> = ArrayList()
        arrayList.add("Android material SDK")
        arrayList.add("Androidx appcompat library")
        arrayList.add("Androidx constraintlayout library")
        arrayList.add("Google android library")
        arrayList.add("Google api-client library")
        arrayList.add("Google apis library")
        arrayList.add("Google oauth-client library")
        arrayList.add("Picasso library")
        arrayList.add("Square retrofit2 library")
        arrayList.add("YouTubeAndroidPlayerApi library")

        listView = binding.ListView
        listView.adapter = OpnSrcAdapter(arrayList)
    }

    override fun onStop() {
        super.onStop()

        listView.adapter = null
    }

    // 메뉴 아이템 선택 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

}