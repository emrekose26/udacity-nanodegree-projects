package com.emrekose.bakingapp.di;

import com.emrekose.bakingapp.ui.detail.RecipeDetailFragment;
import com.emrekose.bakingapp.ui.recipes.RecipeListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract RecipeListFragment contributeRecipeListFragment();

    @ContributesAndroidInjector
    abstract RecipeDetailFragment contributeRecipeDetailFragment();
}
