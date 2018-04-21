package com.emrekose.bakingapp.ui.recipes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrekose.bakingapp.R;
import com.emrekose.bakingapp.model.RecipeResponse;
import com.emrekose.bakingapp.utils.RecipeImgUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class RecipesRecyclerAdapter extends RecyclerView.Adapter<RecipesRecyclerAdapter.ViewHolder> {

    private List<RecipeResponse> responseList = new ArrayList<>();
    private Context context;

    public RecipesRecyclerAdapter(List<RecipeResponse> responseList, Context context) {
        this.responseList = responseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipes, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeResponse recipeResponse = responseList.get(position);

        holder.recipeName.setText(recipeResponse.getName());
        holder.recipeServing.setText(recipeResponse.getServings() + " serving");
        holder.recipeImg.setImageResource(RecipeImgUtils.getRecipeImg(recipeResponse.getId()));

        holder.recipeCard.setOnClickListener(v -> {
            // TODO: 21.04.2018 listener ekle
            Timber.e(recipeResponse.getName());
        });
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recipeImg)
        ImageView recipeImg;

        @BindView(R.id.recipeName)
        TextView recipeName;

        @BindView(R.id.recipeServing)
        TextView recipeServing;

        @BindView(R.id.recipeCard)
        CardView recipeCard;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
