package com.emrekose.famula.ui.cuisineslist.restaurants;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.emrekose.famula.databinding.CuisinesRestaurantItemBinding;
import com.emrekose.famula.model.restaurant.search.Restaurant;

public class CuisinesRestauAdapter extends ListAdapter<Restaurant, CuisinesRestauAdapter.ViewHolder> {

    private CuisinesRestauCallback callback;

    public CuisinesRestauAdapter(CuisinesRestauCallback callback) {
        super(CUISINES_RESTAU_DIFF_CALLBACK);
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

        CuisinesRestaurantItemBinding binding;

        public ViewHolder(CuisinesRestaurantItemBinding binding, CuisinesRestauCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onRestaurantClick(binding.getRestaurant()));
            binding.cuisinesRestauMainMarker.setOnClickListener(v ->
                    callback.onRestaurantMarkerClick(binding.getRestaurant()));
        }

        public static ViewHolder create(LayoutInflater inflater, ViewGroup parent, CuisinesRestauCallback callback) {
            CuisinesRestaurantItemBinding cuisinesRestaurantItemBinding = CuisinesRestaurantItemBinding.inflate(inflater, parent, false);
            return new ViewHolder(cuisinesRestaurantItemBinding, callback);
        }

        public void bind(Restaurant restaurant) {
            binding.setRestaurant(restaurant);
            binding.executePendingBindings();
        }
    }

    private static DiffUtil.ItemCallback<Restaurant> CUISINES_RESTAU_DIFF_CALLBACK = new DiffUtil.ItemCallback<Restaurant>() {

        @Override
        public boolean areItemsTheSame(Restaurant oldItem, Restaurant newItem) {
            return oldItem.getRestaurant().getId().equals(newItem.getRestaurant().getId());
        }

        @Override
        public boolean areContentsTheSame(Restaurant oldItem, Restaurant newItem) {
            return oldItem.getRestaurant().getName().equals(newItem.getRestaurant().getName());
        }
    };
}
