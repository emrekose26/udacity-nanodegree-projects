package com.emrekose.famula.di;

import com.emrekose.famula.ui.establisments.EstablismentTypesListFragment;
import com.emrekose.famula.ui.establisments.EstablistmentListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract EstablismentTypesListFragment establismentTypesListFragment();

    @ContributesAndroidInjector
    abstract EstablistmentListFragment establismentListFragment();

}
