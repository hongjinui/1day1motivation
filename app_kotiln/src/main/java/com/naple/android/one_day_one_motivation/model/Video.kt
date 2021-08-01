package com.naple.android.one_day_one_motivation.model

import com.google.gson.annotations.SerializedName

data class Video(
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("channelTitle")
        val channelTitle: String,
        @SerializedName("duration")
        val duration: String,
        @SerializedName("viewCount")
        val viewCount: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("value")
        val value: String,
)
