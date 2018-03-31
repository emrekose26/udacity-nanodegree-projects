package com.emrekose.popularmoviesstage2.di;

import com.emrekose.popularmoviesstage2.ui.detail.overview.OverviewFragment;
import com.emrekose.popularmoviesstage2.ui.detail.reviews.ReviewsFragment;
import com.emrekose.popularmoviesstage2.ui.main.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract MainFragment contributeMainFragment();

    @ContributesAndroidInjector
    abstract OverviewFragment contributeOverviewFragment();

    @ContributesAndroidInjector
    abstract ReviewsFragment contributeReviewsFragment();
}
