package com.emrekose.popularmoviesstage2.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.emrekose.popularmoviesstage2.R;
import com.emrekose.popularmoviesstage2.model.movie.MovieResults;
import com.emrekose.popularmoviesstage2.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {

    private List<MovieResults> resultsList = new ArrayList<>();
    private Context context;
    private MovieClickListener movieClickListener;

    public MovieRecyclerAdapter(List<MovieResults> resultsList, Context context, MovieClickListener movieClickListener) {
        this.resultsList = resultsList;
        this.context = context;
        this.movieClickListener = movieClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieResults results = resultsList.get(position);

        holder.itemTitle.setText(results.getTitle());

        Glide.with(context)
                .load(Constants.IMAGE_BASE_URL + Constants.IMAGE_W185 + results.getPoster_path())
                .apply((new RequestOptions().placeholder(R.drawable.no_movie_image)))
                .into(holder.itemThumbnail);

        holder.itemThumbnail.setOnClickListener(v -> {
            if (movieClickListener != null) {
                movieClickListener.onMovieClick(results);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public void setData(List<MovieResults> dataList) {
        DiffUtil.DiffResult diffCallback = DiffUtil.calculateDiff(new MovieDiffCallback(resultsList, dataList));
        resultsList.clear();
        resultsList.addAll(dataList);
        diffCallback.dispatchUpdatesTo(this);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_thumbnail)
        ImageView itemThumbnail;

        @BindView(R.id.item_title)
        TextView itemTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    interface MovieClickListener {
        void onMovieClick(MovieResults results);
    }
}
