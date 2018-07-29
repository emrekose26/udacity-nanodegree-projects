package com.emrekose.famula.ui.establisments;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.emrekose.famula.databinding.EstablismentTypesItemBinding;
import com.emrekose.famula.model.establisments.Establishment;

public class EstablismentTypesAdapter extends ListAdapter<Establishment, EstablismentTypesAdapter.ViewHolder> {

    private final EstablismentCallback.TypesCalback callback;

    protected EstablismentTypesAdapter(@NonNull DiffUtil.ItemCallback<Establishment> diffCallback, EstablismentCallback.TypesCalback callback) {
        super(diffCallback);
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(LayoutInflater.from(parent.getContext()),parent, callback);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        EstablismentTypesItemBinding binding;

        public ViewHolder(EstablismentTypesItemBinding binding, EstablismentCallback.TypesCalback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onEstablismentTypesClick(binding.getEstablisment()));
        }

        public static ViewHolder create(LayoutInflater inflater, ViewGroup parent, EstablismentCallback.TypesCalback callback) {
            EstablismentTypesItemBinding establismentTypesItemBinding = EstablismentTypesItemBinding.inflate(inflater, parent, false);
            return new ViewHolder(establismentTypesItemBinding, callback);
        }

        public void bind(Establishment establishment)  {
            binding.setEstablisment(establishment);
            binding.executePendingBindings();
        }
    }

    static class EstablismentTypesDiffCallback extends DiffUtil.ItemCallback<Establishment> {

        @Override
        public boolean areItemsTheSame(Establishment oldItem, Establishment newItem) {
            return oldItem.getEstablishment().getId() == newItem.getEstablishment().getId();
        }

        @Override
        public boolean areContentsTheSame(Establishment oldItem, Establishment newItem) {
            return oldItem.getEstablishment().getName().equals(newItem.getEstablishment().getName());
        }
    }
}
