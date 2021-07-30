package com.naple.android.one_day_one_motivation.util

import com.naple.android.one_day_one_motivation.data.Video
import java.util.*

class VideoListComparator(var subtitle: String) : Comparator<Video> {
    //subtitle을 받아 초기화하고 데이터 값에 따라 정렬을 한다.
    override fun compare(o1: Video, o2: Video): Int {
        return if (subtitle == "업로드순서") {
            if (o1.value.toLong() < o2.value.toLong()) {
                return 1
            } else if (o1.value.toLong() > o2.value.toLong()) {
                return -1
            }
            0
        } else {
            if (o1.viewCount.toInt() < o2.viewCount.toInt()) {
                return 1
            } else if (o1.viewCount.toInt() > o2.viewCount.toInt()) {
                return -1
            }
            0
        }
    }
}