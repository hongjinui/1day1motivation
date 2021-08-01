package com.naple.android.one_day_one_motivation.view.main.presenter

import android.content.Context
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView

interface MainContract {

    interface View {
    }

    interface Presenter{

        fun setVideoList(keyword : String)
        fun sortVideoList(item: MenuItem, subtitle: String, isSortIcon: Boolean,supportActionBar: ActionBar)
        fun release()
    }
}