package com.emrekose.popularmoviesstage2.ui.detail.overview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrekose.popularmoviesstage2.R;
import com.emrekose.popularmoviesstage2.model.videos.MovieVideosResults;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by emrekose on 22.03.2018.
 */

public class TrailerRecyclerAdapter extends RecyclerView.Adapter<TrailerRecyclerAdapter.ViewHolder> {

    private List<MovieVideosResults> resultsList = new ArrayList<>();
    private Context context;
    private TrailerClickListener listener;

    public TrailerRecyclerAdapter(List<MovieVideosResults> resultsList, Context context, TrailerClickListener listener) {
        this.resultsList = resultsList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_trailer, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieVideosResults results = resultsList.get(position);

        holder.trailerCardTitle.setText(results.getName());

        holder.cardThumbnail.setOnClickListener(view -> {
            if (listener != null) {
                listener.onTrailerClick(results);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.trailer_card_thumbnail)
        ImageView cardThumbnail;

        @BindView(R.id.trailer_card_title)
        TextView trailerCardTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    interface TrailerClickListener {
        void onTrailerClick(MovieVideosResults results);
    }
}
