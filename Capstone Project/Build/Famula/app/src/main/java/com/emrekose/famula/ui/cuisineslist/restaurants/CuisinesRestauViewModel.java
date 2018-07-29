package com.emrekose.famula.ui.cuisineslist.restaurants;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.emrekose.famula.common.RxViewModel;
import com.emrekose.famula.model.restaurant.search.Restaurant;
import com.emrekose.famula.model.restaurant.search.SearchResponse;
import com.emrekose.famula.repository.CuisinesRepository;

import java.util.List;

import javax.inject.Inject;

public class CuisinesRestauViewModel extends RxViewModel {

    private CuisinesRepository repository;

    private MutableLiveData<List<Restaurant>> restaurantsLiveData = new MutableLiveData<>();

    @Inject
    public CuisinesRestauViewModel(CuisinesRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Restaurant>> getRestaurants(int entityId, String entityType, String cuisineId) {
        disposable.add(repository.getRestaurants(entityId, entityType, cuisineId)
                .map(SearchResponse::getRestaurants)
                .subscribe(restaurants -> restaurantsLiveData.setValue(restaurants)));

        return restaurantsLiveData;
    }

}
