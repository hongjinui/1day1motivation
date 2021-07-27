package com.napl.android.motivationPerDay.model;



import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class VideoDTO{


    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;
    @SerializedName("channelTitle")
    private String channelTitle;
    @SerializedName("duration")
    private String duration;
    @SerializedName("viewCount")
    private String viewCount;
    @SerializedName("date")
    private String date;
    @SerializedName("id")
    private String id;
    @SerializedName("value")
    private String value;


}
