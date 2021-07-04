package com.naple.android.one_day_one_motivation;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoDTO implements Serializable {

    private String channelId;
    private String title;
    private String url;
    private String channelTitle;
    private String duration;
    private String viewCount;
    private String date;
    private String id;

}
