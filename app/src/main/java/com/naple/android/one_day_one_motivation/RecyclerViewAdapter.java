package com.naple.android.one_day_one_motivation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<VideoDTO> dataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //single_view
//        private ImageView imageView;
        private TextView textView_duration;
        private TextView textView_title;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView_duration = view.findViewById(R.id.TextView_duration);
            textView_title = view.findViewById(R.id.TextView_title);
//            imageView = view.findViewById(R.id.ImageView_10);
        }

        public TextView getTextView_duration() {return textView_duration;}
        public TextView getTextView_title() {return textView_title;}
//        public ImageView getImageView() {
//            return imageView;
//        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView.
     */
    public RecyclerViewAdapter(List<VideoDTO> dataSet) {
        this.dataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_view, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView_duration().setText(dataSet.get(position).getDuration());
        viewHolder.getTextView_title().setText(dataSet.get(position).getTitle());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {return dataSet != null ? dataSet.size() : 0;}
}
