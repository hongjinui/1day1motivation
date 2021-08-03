package com.naple.android.one_day_one_motivation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.naple.android.one_day_one_motivation.R
import com.naple.android.one_day_one_motivation.model.Video
import com.naple.android.one_day_one_motivation.view.videoscreen.VideoScreenActivity
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class MainAdapter(val videoList : ArrayList<Video>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    // 뷰 홀더 inner class
    class MainViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.TextView_title)
        val channelTitle = itemView.findViewById<TextView>(R.id.TextView_channelTitle)
        val duration = itemView.findViewById<TextView>(R.id.TextView_duration)
        val viewCount = itemView.findViewById<TextView>(R.id.TextView_viewCount)
        val thumnail = itemView.findViewById<ImageView>(R.id.ImageView_thumbnail)
    }

    // 호출 할 때 초기화 하겠다.
    private lateinit var itemClickListener : OnItemClickListener

    //
    interface OnItemClickListener{
        fun onClickItem(view : View, position: Int)
    }

    //
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.itemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        // Create a new view, which defines the UI of the list item
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)

        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {

        val decimalFormat = DecimalFormat("###,###")

        holder.title.text = videoList.get(position).title
        holder.channelTitle.text = videoList.get(position).channelTitle
        holder.duration.text = formatDuration(videoList.get(position).duration)
        holder.viewCount.text = "${decimalFormat.format(Integer.parseInt(videoList.get(position).viewCount))} views · ${videoList.get(position).date}"
        Picasso.get().load(videoList.get(position).url).into(holder.thumnail)

        holder.itemView.setOnClickListener{

            //클릭한 영상 channelId
            val videoId: String = videoList.get(position).id
            val context: Context = holder.itemView.context
            val intent = Intent(context, VideoScreenActivity::class.java)
            intent.putExtra("videoId", videoId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = videoList.size

    private fun formatDuration(duration: String): String? {
        var duration = duration
        duration = duration.replace("PT", "")
        val strArr: Array<String?> = duration.split("M".toRegex()).toTypedArray()
        var mm: String? = ""
        var ss: String? = ""

        // 영상 시간이 mm,ss 둘 다 있을 때
        if (strArr.size == 2) {
            if (strArr[0] != null && strArr[0]!!.length == 1) {
                mm = "0" + strArr[0]
            } else if (strArr[0] != null) {
                mm = strArr[0]
            }
            if (strArr[1] != null && strArr[1]!!.length == 1) {
                ss = "00"
            } else if (strArr[1] != null && strArr[1]!!.length == 2) {
                ss = "0" + strArr[1]
            } else if (strArr[1] != null) {
                ss = strArr[1]
            }
            ss = ss!!.replace("S", "")
            // mm만 있을때
        } else {
            if (strArr[0] != null && strArr[0]!!.length == 1) {
                mm = "0" + strArr[0]
            } else if (strArr[0] != null) {
                mm = strArr[0]
            }
            ss = "00"
        }
        return "$mm:$ss"
    }

}
