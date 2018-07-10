package com.emrekose.famula.repository;

import com.emrekose.famula.data.remote.ApiService;
import com.emrekose.famula.model.establisments.EstablismentsResponse;
import com.emrekose.famula.data.remote.datasource.EstablishmentListDataSourceFactory;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
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

    public EstablishmentListDataSourceFactory getDataSoureFactory(CompositeDisposable compositeDisposable,
                                                                  String establishmentId,
                                                                  int entityId,
                                                                  String entityType) {
        return new EstablishmentListDataSourceFactory(apiService, compositeDisposable, establishmentId, entityId, entityType);
    }
}
