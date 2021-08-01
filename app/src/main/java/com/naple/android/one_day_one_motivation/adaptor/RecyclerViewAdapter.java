package com.naple.android.one_day_one_motivation.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.naple.android.one_day_one_motivation.R;
import com.naple.android.one_day_one_motivation.model.VideoDTO;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

// adapter class : 데이터 목록을 아이템 단위의 뷰로 구성하여 화면에 보여주기 위한 클래스
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<VideoDTO> dataSet = new ArrayList<>();

    public interface OnItemClickListener {
        void onItemClick(View view, int pos);
    }

    static private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    //데이터와 뷰를 바인딩해주는 객체, 화면에 표시될 아이템뷰를 저장하는 객체
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //single_view
        private final TextView textView_duration;
        private final TextView textView_title;
        private final TextView textView_channelTitle;
        private final TextView textView_viewCount;

        private final ImageView imageView_thumbnail;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView_duration = view.findViewById(R.id.TextView_duration);
            textView_title = view.findViewById(R.id.TextView_title);
            textView_channelTitle = view.findViewById(R.id.TextView_channelTitle);
            textView_viewCount = view.findViewById(R.id.TextView_viewCount);
            imageView_thumbnail = view.findViewById(R.id.ImageView_thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(view, pos);
                        }
                    }

                }
            });
        }

        public TextView getTextView_duration() {return textView_duration;}

        public TextView getTextView_title() {return textView_title;}

        public TextView getTextView_channelTitle() {return textView_channelTitle;}

        public TextView getTextView_viewCount() {return textView_viewCount;}

        public ImageView getImageView_thumbnail() {return imageView_thumbnail;}
//        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView.
     */
    public RecyclerViewAdapter(List<VideoDTO> dataSet) {this.dataSet = dataSet;}

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_view, viewGroup, false);


        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // viewCount 콤마 찍기
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        Integer views = Integer.parseInt(dataSet.get(position).getViewCount());

        // duration 변경
        String duration = dataSet.get(position).getDuration();
        duration = formatDuration(duration);

        // publishedAt
        String publishedAt = dataSet.get(position).getDate();

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView_duration().setText(duration);
        viewHolder.getTextView_title().setText(dataSet.get(position).getTitle());
        viewHolder.getTextView_channelTitle().setText("by  " + dataSet.get(position).getChannelTitle());
        viewHolder.getTextView_viewCount().setText(decimalFormat.format(views) + " views · " + publishedAt);

        //썸네일 이미지 로드
        Picasso.get().load(dataSet.get(position).getUrl()).into(viewHolder.getImageView_thumbnail());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() { return dataSet == null ? 0 : dataSet.size();}

    private String formatDuration(String duration) {

        duration = duration.replace("PT", "");
        String[] strArr = duration.split("M");

        String mm = "";
        String ss = "";

        // 영상 시간이 mm,ss 둘 다 있을 때
        if (strArr.length == 2){
            if (strArr[0] != null && strArr[0].length() == 1)      { mm = "0" + strArr[0];}
            else if (strArr[0] != null )                           { mm = strArr[0];}

            if (strArr[1] != null &&strArr[1].length() == 1)       { ss = "00"; }
            else if(strArr[1] != null && strArr[1].length() == 2 ) { ss = "0"+ strArr[1];}
            else if(strArr[1] != null)                             { ss = strArr[1];}

            ss = ss.replace("S", "");
        // mm만 있을때
        }else{
            if (strArr[0] != null && strArr[0].length() == 1)      { mm = "0" + strArr[0];}
            else if (strArr[0] != null )                           { mm = strArr[0];}

            ss = "00";
        }

        return mm + ":" + ss;

    }
}