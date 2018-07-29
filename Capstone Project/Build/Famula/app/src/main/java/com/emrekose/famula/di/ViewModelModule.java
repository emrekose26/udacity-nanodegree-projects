package com.emrekose.famula.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.emrekose.famula.ui.cuisineslist.restaurants.CuisinesRestauViewModel;
import com.emrekose.famula.ui.detail.RestaurantDetailViewModel;
import com.emrekose.famula.ui.establisments.EstablismentsViewModel;
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
    @IntoMap
    @ViewModelKey(EstablismentsViewModel.class)
    abstract ViewModel bindsEstablismentsViewModel(EstablismentsViewModel establismentsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantDetailViewModel.class)
    abstract ViewModel bindsRestaurantDetailViewModel(RestaurantDetailViewModel restaurantDetailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CuisinesRestauViewModel.class)
    abstract ViewModel bindsCuisinesRestauViewModel(CuisinesRestauViewModel cuisinesRestauViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(VMFactory vmFactory);
}
