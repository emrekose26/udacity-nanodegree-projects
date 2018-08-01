package com.emrekose.famula.ui.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.emrekose.famula.common.RxViewModel;
import com.emrekose.famula.model.restaurant.search.Restaurant;
import com.emrekose.famula.model.restaurant.search.SearchResponse;
import com.emrekose.famula.repository.SearchRepository;

import java.util.List;

import javax.inject.Inject;

public class SearchViewModel extends RxViewModel {

    private SearchRepository repository;

    private MutableLiveData<List<Restaurant>> restaurantsLiveData = new MutableLiveData<>();

    @Inject
    public SearchViewModel(SearchRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Restaurant>> getRestaurants(String query, int entityId, String entityType) {
        disposable.add(repository.getSearchResults(query, entityId, entityType)
                .map(SearchResponse::getRestaurants)
                .subscribe(restaurants -> restaurantsLiveData.setValue(restaurants)));

        return restaurantsLiveData;
    }
}
