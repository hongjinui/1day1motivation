package com.naple.android.one_day_one_motivation.view.license

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.naple.android.one_day_one_motivation.R
import com.naple.android.one_day_one_motivation.databinding.ActivityLicenseBinding

class LicenseActivity : AppCompatActivity(){

    private lateinit var binding : ActivityLicenseBinding
    private lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLicenseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent : Intent = getIntent()
        val itemText : String? = intent.getStringExtra("itemText")


        // 툴바
        val toolbarView = binding.ToolbarLicense
        setSupportActionBar(toolbarView)
        val toobar = supportActionBar!!
        toobar.setDisplayHomeAsUpEnabled(true)
        toobar.setTitle(itemText)

        textView = binding.TextViewLicense
        textView.movementMethod = ScrollingMovementMethod.getInstance()

        try {
            val inputStream = resources.openRawResource(R.raw.license)
            val str: String = inputStream.bufferedReader().use { it.readText() }

            textView.setText(str)

        }catch (e : Exception){
            e.printStackTrace()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}