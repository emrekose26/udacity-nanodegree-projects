package com.emrekose.famula.repository;

import com.emrekose.famula.data.remote.ApiService;
import com.emrekose.famula.model.establisments.EstablismentsResponse;
import com.emrekose.famula.model.restaurant.search.SearchResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EstablismentsRepository {

    private final ApiService apiService;

    @Inject
    public EstablismentsRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Flowable<EstablismentsResponse> getEstablismentTypes(int cityId, Double lat, Double lon) {
        return apiService.getEstablisments(cityId, lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<SearchResponse> getEstablistments(String query,
                                                      String entityId,
                                                      String entityType,
                                                      Double lat,
                                                      Double lon,
                                                      int page) {

        return apiService.getSearchDatas(query, entityId, entityType, lat, lon, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
