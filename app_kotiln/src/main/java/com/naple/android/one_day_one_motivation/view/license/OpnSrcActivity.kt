package com.naple.android.one_day_one_motivation.view.license

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.naple.android.one_day_one_motivation.databinding.ActivityOpnSrcBinding
import java.util.*

class OpnSrcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpnSrcBinding
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOpnSrcBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // 툴바
        val toolbarView = binding.ToolbarOpnsrc
        setSupportActionBar(toolbarView)
        val toobar = supportActionBar!!
        toobar.setDisplayHomeAsUpEnabled(true)
        toobar.setTitle("오픈소스 라이선스")

        val arrayList: MutableList<String> = ArrayList()
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
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, arrayList)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
            val intent = Intent(application, LicenseActivity::class.java)
            intent.putExtra("itemText", arrayList[i])
            startActivity(intent)
        }
    }

    // 메뉴 아이템 선택 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home) {
            finish()
        }
        return true
    }

}