package com.emrekose.famula.data.remote.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.emrekose.famula.data.remote.ApiService;
import com.emrekose.famula.model.restaurant.search.Restaurant;

import io.reactivex.disposables.CompositeDisposable;

public class EstablishmentListDataSourceFactory extends DataSource.Factory<Integer, Restaurant> {

    private ApiService apiService;
    private CompositeDisposable compositeDisposable;

    private MutableLiveData<EstablishmentListDataSource> establishmentListDataSourceLiveData = new MutableLiveData<>();
    private EstablishmentListDataSource establishmentListDataSource;

    public EstablishmentListDataSourceFactory(ApiService apiService,
                                              CompositeDisposable compositeDisposable,
                                              String establishmentId,
                                              int entityId,
                                              String entityType) {
        this.compositeDisposable = compositeDisposable;
        this.apiService = apiService;
        establishmentListDataSource = new EstablishmentListDataSource(apiService, compositeDisposable,establishmentId, entityId, entityType);
    }

    @Override
    public DataSource<Integer, Restaurant> create() {
        establishmentListDataSourceLiveData.postValue(establishmentListDataSource);
        return establishmentListDataSource;
    }
}
