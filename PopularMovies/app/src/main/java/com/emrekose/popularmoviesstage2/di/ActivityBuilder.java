package com.emrekose.popularmoviesstage2.di;

import com.emrekose.popularmoviesstage2.ui.detail.DetailActivity;
import com.emrekose.popularmoviesstage2.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = FragmentBuilder.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = FragmentBuilder.class)
    abstract DetailActivity detailActivity();
}
