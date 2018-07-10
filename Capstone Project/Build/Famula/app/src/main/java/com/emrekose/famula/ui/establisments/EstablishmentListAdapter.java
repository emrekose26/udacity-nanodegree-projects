package com.emrekose.famula.ui.establisments;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.emrekose.famula.R;
import com.emrekose.famula.common.RetryCallback;
import com.emrekose.famula.databinding.EstablishmentListItemBinding;
import com.emrekose.famula.databinding.NetworkStateItemBinding;
import com.emrekose.famula.model.restaurant.search.Restaurant;
import com.emrekose.famula.util.NetworkState;

public class EstablishmentListAdapter extends PagedListAdapter<Restaurant, RecyclerView.ViewHolder> {

    private NetworkState networkState;
    private RetryCallback retryCallback;

    public EstablishmentListAdapter(RetryCallback retryCallback) {
        super(ESTABLISHMENT_LIST_DIFF_CALLBACK);
        this.retryCallback = retryCallback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case R.layout.establishment_list_item:
                return EstablishmentListViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
            case R.layout.network_state_item:
                return NetworkStateViewHolder.create(LayoutInflater.from(parent.getContext()), parent, retryCallback);
            default:
                throw new IllegalArgumentException("unknown view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.establishment_list_item:
                ((EstablishmentListViewHolder)holder).bind(getItem(position));
                break;
            case R.layout.network_state_item:
                ((NetworkStateViewHolder)holder).bind(networkState);
        }
    }

    private boolean hasExtraRow() {
        return networkState != null && networkState != NetworkState.LOADED;
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return R.layout.network_state_item;
        } else {
            return R.layout.establishment_list_item;
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + (hasExtraRow() ? 1 : 0);
    }

    public void setNetworkState(NetworkState newNetworkState) {
        if (getCurrentList() != null) {
            if (getCurrentList().size() != 0) {
                NetworkState previousState = this.networkState;
                boolean hadExtraRow = hasExtraRow();
                this.networkState = newNetworkState;
                boolean hasExtraRow = hasExtraRow();
                if (hadExtraRow != hasExtraRow) {
                    if (hadExtraRow) {
                        notifyItemRemoved(super.getItemCount());
                    } else {
                        notifyItemInserted(super.getItemCount());
                    }
                } else if (hasExtraRow && previousState != newNetworkState) {
                    notifyItemChanged(getItemCount() - 1);
                }
            }
        }
    }

    static class EstablishmentListViewHolder extends RecyclerView.ViewHolder {

        EstablishmentListItemBinding binding;

        public EstablishmentListViewHolder(EstablishmentListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public static EstablishmentListViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            EstablishmentListItemBinding establishmentListItemBinding = EstablishmentListItemBinding.inflate(inflater, parent, false);
            return new EstablishmentListViewHolder(establishmentListItemBinding);
        }

        public void bind(Restaurant restaurant) {
            binding.setRestaurant(restaurant);
            binding.executePendingBindings();
        }
    }

    static class NetworkStateViewHolder extends RecyclerView.ViewHolder {

        NetworkStateItemBinding binding;

        public NetworkStateViewHolder(NetworkStateItemBinding binding, RetryCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.retryLoadingButton.setOnClickListener(v -> callback.retry());
        }

        public static NetworkStateViewHolder create(LayoutInflater inflater, ViewGroup parent, RetryCallback callback) {
            NetworkStateItemBinding networkStateItemBinding = NetworkStateItemBinding.inflate(inflater, parent, false);
            return new NetworkStateViewHolder(networkStateItemBinding, callback);
        }

        public void bind(NetworkState networkState) {
            binding.setState(networkState);
            binding.executePendingBindings();
        }
    }

    private static DiffUtil.ItemCallback<Restaurant> ESTABLISHMENT_LIST_DIFF_CALLBACK = new DiffUtil.ItemCallback<Restaurant>() {

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
