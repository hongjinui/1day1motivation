package com.naple.android.one_day_one_motivation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.naple.android.one_day_one_motivation.R
import com.naple.android.one_day_one_motivation.view.license.LicenseActivity

class OpnSrcAdapter(private val arrayList :ArrayList<String>) : BaseAdapter() {

    override fun getCount(): Int = arrayList.size
    override fun getItem(position: Int): Any = arrayList[position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var tempView = convertView
        if (tempView == null) tempView = LayoutInflater.from(parent?.context)
        .inflate(R.layout.listview_item,parent,false)

        val view = tempView!!

        val titleTextView : TextView = view.findViewById(R.id.TextView_opn_src_title)
        titleTextView.text = arrayList[position]

        tempView.setOnClickListener(View.OnClickListener {
            val context : Context = tempView.context

            val intent = Intent(context, LicenseActivity::class.java)
            intent.putExtra("itemText", arrayList[position])
            context.startActivity(intent)
        })

        return view

    }

}