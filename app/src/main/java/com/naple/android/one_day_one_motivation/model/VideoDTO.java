package com.naple.android.one_day_one_motivation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class VideoDTO implements Serializable {


    @JsonProperty("title")
    private String title;
    @JsonProperty("url")
    private String url;
    @JsonProperty("channelTitle")
    private String channelTitle;
    @JsonProperty("duration")
    private String duration;
    @JsonProperty("viewCount")
    private String viewCount;
    @JsonProperty("date")
    private String date;
    @JsonProperty("id")
    private String id;
    @JsonProperty("value")
    private String value;


}
