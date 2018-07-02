package com.emrekose.famula.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.emrekose.famula.databinding.NearbyRestaurantsMainItemBinding;
import com.emrekose.famula.model.geocode.NearbyRestaurant;

public class NearbyRestaurantRecylerAdapter extends ListAdapter<NearbyRestaurant, NearbyRestaurantRecylerAdapter.ViewHolder>{

    private final NearbyRestaurantsCallback callback;

    protected NearbyRestaurantRecylerAdapter(@NonNull DiffUtil.ItemCallback<NearbyRestaurant> diffCallback, NearbyRestaurantsCallback callback) {
        super(diffCallback);
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

        NearbyRestaurantsMainItemBinding binding;

        public ViewHolder(NearbyRestaurantsMainItemBinding binding, NearbyRestaurantsCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                callback.onMainNearbyRestaurantsClick(binding.getRestaurant()));
        }

        public static ViewHolder create(LayoutInflater inflater, ViewGroup parent, NearbyRestaurantsCallback callback) {
            NearbyRestaurantsMainItemBinding nearbyRestaurantsMainItemBinding = NearbyRestaurantsMainItemBinding.inflate(inflater, parent, false);
            return new ViewHolder(nearbyRestaurantsMainItemBinding, callback);
        }

        public void bind(NearbyRestaurant restaurant) {
            binding.setRestaurant(restaurant);
            binding.executePendingBindings();
        }
    }

    static class NearbyRestaurantsDiffCallback extends DiffUtil.ItemCallback<NearbyRestaurant> {

        @Override
        public boolean areItemsTheSame(NearbyRestaurant oldItem, NearbyRestaurant newItem) {
            return oldItem.getRestaurant().getId().equals(newItem.getRestaurant().getId());
        }

        @Override
        public boolean areContentsTheSame(NearbyRestaurant oldItem, NearbyRestaurant newItem) {
            return oldItem.getRestaurant().getName().equals(newItem.getRestaurant().getName());
        }
    }
}
