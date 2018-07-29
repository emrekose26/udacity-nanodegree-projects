package com.emrekose.famula.repository;

import com.emrekose.famula.data.remote.ApiService;
import com.emrekose.famula.model.restaurant.search.SearchResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CuisinesRepository {

    private ApiService apiService;

    @Inject
    public CuisinesRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Flowable<SearchResponse> getRestaurants(int entityId, String entityType, String cuisineId) {
        return apiService.getSearchDatas(null, null, entityId,
                entityType, null, null, cuisineId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
