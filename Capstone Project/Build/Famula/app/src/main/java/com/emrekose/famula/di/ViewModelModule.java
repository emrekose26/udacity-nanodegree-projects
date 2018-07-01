package com.emrekose.famula.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.emrekose.famula.ui.main.MainViewModel;
import com.emrekose.famula.viewmodel.VMFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindsCuisinesViewModel(MainViewModel cuisinesViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(VMFactory vmFactory);
}
