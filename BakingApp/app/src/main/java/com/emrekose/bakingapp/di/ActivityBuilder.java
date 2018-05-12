package com.emrekose.bakingapp.di;

import com.emrekose.bakingapp.ui.detail.RecipeDetailActivity;
import com.emrekose.bakingapp.ui.recipes.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = FragmentBuilder.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = FragmentBuilder.class)
    abstract RecipeDetailActivity recipeDetailActivity();
}
