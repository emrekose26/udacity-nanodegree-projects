package com.emrekose.famula.ui.nearbyrestaurants;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.emrekose.famula.databinding.NearbyRestaurantsItemBinding;
import com.emrekose.famula.model.geocode.NearbyRestaurant;

public class NearbyRestaurantsAdapter extends ListAdapter<NearbyRestaurant, NearbyRestaurantsAdapter.ViewHolder> {

    private NearbyRestaurantsCallback callback;

    protected NearbyRestaurantsAdapter(NearbyRestaurantsCallback callback) {
        super(NEARBY_DIFF_CALLBACK);
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

        NearbyRestaurantsItemBinding binding;

        public ViewHolder(NearbyRestaurantsItemBinding binding, NearbyRestaurantsCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onNearbyRestaurantClick(binding.getRestaurant()));
            binding.nearbyRestaurantsMainMarker.setOnClickListener(v ->
                    callback.onNearbyRestaurantMarkerClick(binding.getRestaurant()));
        }

        public static ViewHolder create(LayoutInflater inflater, ViewGroup parent, NearbyRestaurantsCallback callback) {
            NearbyRestaurantsItemBinding nearbyRestaurantsItemBinding = NearbyRestaurantsItemBinding.inflate(inflater, parent, false);
            return new ViewHolder(nearbyRestaurantsItemBinding, callback);
        }

        public void bind(NearbyRestaurant restaurant) {
            binding.setRestaurant(restaurant);
            binding.executePendingBindings();
        }
    }

    private static DiffUtil.ItemCallback<NearbyRestaurant> NEARBY_DIFF_CALLBACK = new DiffUtil.ItemCallback<NearbyRestaurant>() {
        @Override
        public boolean areItemsTheSame(NearbyRestaurant oldItem, NearbyRestaurant newItem) {
            return oldItem.getRestaurant().getId().equals(newItem.getRestaurant().getId());
        }

        @Override
        public boolean areContentsTheSame(NearbyRestaurant oldItem, NearbyRestaurant newItem) {
            return oldItem.getRestaurant().getName().equals(newItem.getRestaurant().getName());
        }
    };
}
