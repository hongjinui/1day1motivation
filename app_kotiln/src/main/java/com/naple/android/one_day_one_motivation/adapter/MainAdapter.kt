package com.naple.android.one_day_one_motivation.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.naple.android.one_day_one_motivation.R
import com.naple.android.one_day_one_motivation.model.Video
import com.naple.android.one_day_one_motivation.view.videoscreen.VideoScreenActivity
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class MainAdapter(private val videoList : ArrayList<Video>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    // 뷰 홀더 inner class
    class MainViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.TextView_title)
        val channelTitle: TextView = itemView.findViewById(R.id.TextView_channelTitle)
        val duration: TextView = itemView.findViewById(R.id.TextView_duration)
        val viewCount: TextView = itemView.findViewById(R.id.TextView_viewCount)
        val thumbnail: ImageView = itemView.findViewById(R.id.ImageView_thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        // Create a new view, which defines the UI of the list item
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)

        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val decimalFormat = DecimalFormat("###,###")

        holder.title.text = videoList[position].title
        holder.channelTitle.text = "BY ${videoList[position].channelTitle}"
        holder.duration.text = formatDuration(videoList[position].duration)
        holder.viewCount.text = "${decimalFormat.format(Integer.parseInt(videoList[position].viewCount))} views · ${videoList[position].date}"
        Picasso.get().load(videoList[position].url).into(holder.thumbnail)

        holder.itemView.setOnClickListener{

            //클릭한 영상 channelId
            val videoId: String = videoList[position].id
            val context: Context = holder.itemView.context
            val intent = Intent(context, VideoScreenActivity::class.java)
            intent.putExtra("videoId", videoId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = videoList.size

    private fun formatDuration(duration: String): String {
        var playtime = duration

        playtime = playtime.replace("PT", "").trim()
        val strArr: List<String> = playtime.split("M")
        var mm: String? = ""
        var ss: String? = ""
        // 영상 시간이 mm,ss 둘 다 있을 때
        if ( strArr[1].isNotEmpty()) {
            mm = if (strArr[0].length == 1) {
                "0" + strArr[0]
            } else {
                strArr[0]
            }
            ss = when (strArr[1].length) {
                1 -> {
                    "00"
                }
                2 -> {
                    "0" + strArr[1]
                }
                else -> {
                    strArr[1]
                }
            }
            ss = ss.replace("S", "")
            // mm만 있을때
        } else {
            Log.d("false", playtime.length.toString())
            mm = if (strArr[0].length == 1) {
                "0" + strArr[0]
            } else {
                strArr[0]
            }
            ss = "00"
        }
        return "$mm:$ss"
    }

}
