package com.emrekose.famula.ui.detail;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.emrekose.famula.databinding.UserReviewsItemBinding;
import com.emrekose.famula.model.restaurant.reviews.UserReview;

public class ReviewsAdapter extends ListAdapter<UserReview, ReviewsAdapter.ViewHolder> {

    protected ReviewsAdapter() {
        super(REVIEWS_DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public static ViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            UserReviewsItemBinding userReviewsItemBinding = UserReviewsItemBinding.inflate(inflater, parent, false);
            return new ViewHolder(userReviewsItemBinding);
        }

        UserReviewsItemBinding binding;

        public ViewHolder(UserReviewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(UserReview userReview) {
            binding.setReview(userReview);
            binding.executePendingBindings();
        }
    }

    private static DiffUtil.ItemCallback<UserReview> REVIEWS_DIFF_CALLBACK = new DiffUtil.ItemCallback<UserReview>() {

        @Override
        public boolean areItemsTheSame(UserReview oldItem, UserReview newItem) {
            return oldItem.getReview().getId().equals(newItem.getReview().getId());
        }

        @Override
        public boolean areContentsTheSame(UserReview oldItem, UserReview newItem) {
            return oldItem.getReview().getUser().getName().equals(newItem.getReview().getUser().getName());
        }
    };
}
