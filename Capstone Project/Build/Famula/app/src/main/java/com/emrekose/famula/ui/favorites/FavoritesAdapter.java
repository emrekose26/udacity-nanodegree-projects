package com.emrekose.famula.ui.favorites;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.emrekose.famula.data.local.entity.CommonRestaurant;
import com.emrekose.famula.databinding.FavoritesItemBinding;

public class FavoritesAdapter extends ListAdapter<CommonRestaurant, FavoritesAdapter.ViewHolder> {

    private FavoritesCallback callback;

    protected FavoritesAdapter(FavoritesCallback callback) {
        super(FAVORITES_DIFF_CALLBACK);
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(LayoutInflater.from(parent.getContext()), parent, callback);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        FavoritesItemBinding binding;

        public ViewHolder(FavoritesItemBinding binding, FavoritesCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onFavoriteRestaurantClick(binding.getRestaurant()));
        }

        public static ViewHolder create(LayoutInflater inflater, ViewGroup parent, FavoritesCallback callback) {
            FavoritesItemBinding favoritesItemBinding = FavoritesItemBinding.inflate(inflater, parent, false);
            return new ViewHolder(favoritesItemBinding, callback);
        }

        public void bind(CommonRestaurant restaurant) {
            binding.setRestaurant(restaurant);
            binding.executePendingBindings();
        }
    }

    private static DiffUtil.ItemCallback<CommonRestaurant> FAVORITES_DIFF_CALLBACK = new DiffUtil.ItemCallback<CommonRestaurant>() {
        @Override
        public boolean areItemsTheSame(CommonRestaurant oldItem, CommonRestaurant newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(CommonRestaurant oldItem, CommonRestaurant newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };
}
