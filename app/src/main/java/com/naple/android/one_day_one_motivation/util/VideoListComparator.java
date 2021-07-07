package com.naple.android.one_day_one_motivation.util;

import com.naple.android.one_day_one_motivation.model.VideoDTO;

import java.util.Comparator;

public class VideoListComparator implements Comparator<VideoDTO> {

    public String subtitle;

    public VideoListComparator( String subTitle){
        this.subtitle = subTitle;
    }

    //subtitle을 받아 초기화하고 데이터 값에 따라 정렬을 한다.
    @Override
    public int compare(VideoDTO o1, VideoDTO o2) {
        if(this.subtitle.equals("업로드순서")){
            if(Long.parseLong(o1.getValue()) < Long.parseLong(o2.getValue())){
                return 1;
            }else if(Long.parseLong(o1.getValue()) > Long.parseLong(o2.getValue())) {
                return -1;
            }
            return 0;
        }else{
            if(Integer.parseInt(o1.getViewCount()) < Integer.parseInt(o2.getViewCount())){
                return 1;
            }else if(Integer.parseInt(o1.getViewCount()) > Integer.parseInt(o2.getViewCount())){
                return -1;
            }
            return 0;
        }
    }
}
