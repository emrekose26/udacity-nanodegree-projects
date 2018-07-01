package com.emrekose.famula.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.emrekose.famula.databinding.CuisinesMainItemBinding;
import com.emrekose.famula.model.cuisines.Cuisine;

public class CuisinesRecyclerAdapter extends ListAdapter<Cuisine, CuisinesRecyclerAdapter.ViewHolder> {

    private final CuisinesCallback callback;

    public CuisinesRecyclerAdapter(@NonNull DiffUtil.ItemCallback<Cuisine> diffCallback, CuisinesCallback callback) {
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

        public static ViewHolder create(LayoutInflater inflater, ViewGroup parent, CuisinesCallback callback) {
            CuisinesMainItemBinding cuisinesMainItemBinding = CuisinesMainItemBinding.inflate(inflater, parent, false);
            return new ViewHolder(cuisinesMainItemBinding, callback);
        }

        CuisinesMainItemBinding binding;

        public ViewHolder(CuisinesMainItemBinding binding, CuisinesCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onMainCuisineClick(binding.getCuisine()));
        }

        public void bind(Cuisine cuisine) {
            binding.setCuisine(cuisine);
            binding.executePendingBindings();
        }
    }

    static class CuisineDiffCallback extends DiffUtil.ItemCallback<Cuisine> {

        @Override
        public boolean areItemsTheSame(Cuisine oldItem, Cuisine newItem) {
            return oldItem.getCuisine().getCuisineId() == newItem.getCuisine().getCuisineId();
        }

        @Override
        public boolean areContentsTheSame(Cuisine oldItem, Cuisine newItem) {
            return oldItem.getCuisine().getCuisineName() == oldItem.getCuisine().getCuisineName();
        }
    }
}
