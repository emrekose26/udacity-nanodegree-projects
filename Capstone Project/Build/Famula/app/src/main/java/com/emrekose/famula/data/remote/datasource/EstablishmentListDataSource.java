package com.emrekose.famula.data.remote.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.emrekose.famula.data.remote.ApiService;
import com.emrekose.famula.model.restaurant.search.Restaurant;
import com.emrekose.famula.model.restaurant.search.SearchResponse;
import com.emrekose.famula.util.NetworkState;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class EstablishmentListDataSource extends PageKeyedDataSource<Integer, Restaurant> {

    private ApiService apiService;
    private CompositeDisposable compositeDisposable;

    private MutableLiveData<NetworkState> networkState = new MutableLiveData<>();
    private MutableLiveData<NetworkState> initialLoad = new MutableLiveData<>();

    private Completable retryCompletable;

    private int entityId;
    private String entityType, establishmentId;

    public EstablishmentListDataSource(ApiService apiService,
                                       CompositeDisposable compositeDisposable,
                                       String establishmentId,
                                       int entityId,
                                       String entityType) {
        this.apiService = apiService;
        this.compositeDisposable = compositeDisposable;
        this.establishmentId = establishmentId;
        this.entityId = entityId;
        this.entityType = entityType;
    }

    public void retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                    }, Timber::e));
        }
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Restaurant> callback) {
        Timber.e("load initial running");

        networkState.postValue(NetworkState.LOADING);
        initialLoad.postValue(NetworkState.LOADING);

        compositeDisposable.add(apiService.getSearchDatas(null, establishmentId, entityId, entityType, null, null, 1, params.requestedLoadSize)
                .map(SearchResponse::getRestaurants)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchResponse -> {

                    Timber.e("loadInitial" + searchResponse.size());

                    setRetry(null);
                    networkState.postValue(NetworkState.LOADED);
                    initialLoad.postValue(NetworkState.LOADED);
                    callback.onResult(searchResponse, null, 2);
                }, t -> {
                    setRetry(() -> loadInitial(params, callback));
                    NetworkState error = NetworkState.error(t.getMessage());
                    networkState.postValue(error);
                    initialLoad.postValue(error);
                }));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Restaurant> callback) {
        Timber.e("load before running");
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Restaurant> callback) {
        Timber.e("load after running");

        networkState.postValue(NetworkState.LOADING);

        compositeDisposable.add(apiService.getSearchDatas(null, establishmentId, entityId, entityType, null, null, params.key, params.requestedLoadSize)
                .map(SearchResponse::getRestaurants)
                .subscribe(searchResponse -> {
                            Timber.e("loadAfter" + searchResponse.size());
                            setRetry(null);
                            networkState.postValue(NetworkState.LOADED);
                            callback.onResult(searchResponse, params.key + 1);
                        },
                        throwable -> {
                            setRetry(() -> loadAfter(params, callback));
                            networkState.postValue(NetworkState.error(throwable.getMessage()));
                        }));
    }

    @NonNull
    public MutableLiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    @NonNull
    public MutableLiveData<NetworkState> getInitialLoad() {
        return initialLoad;
    }

    private void setRetry(final Action action) {
        if (action == null) {
            this.retryCompletable = null;
        } else {
            this.retryCompletable = Completable.fromAction(action);
        }
    }
}
