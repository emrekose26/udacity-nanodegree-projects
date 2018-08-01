package com.emrekose.famula.repository;

import com.emrekose.famula.data.remote.ApiService;
import com.emrekose.famula.model.restaurant.search.SearchResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchRepository {

    private ApiService apiService;

    @Inject
    public SearchRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Flowable<SearchResponse> getSearchResults(String query, int entityId, String entityType) {
        return apiService.getSearchDatas(query, null, entityId, entityType, null, null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
