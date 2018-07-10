package com.emrekose.famula.ui.establisments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.emrekose.famula.common.RxViewModel;
import com.emrekose.famula.data.remote.datasource.EstablishmentListDataSourceFactory;
import com.emrekose.famula.model.establisments.Establishment;
import com.emrekose.famula.model.establisments.EstablismentsResponse;
import com.emrekose.famula.model.restaurant.search.Restaurant;
import com.emrekose.famula.repository.EstablismentsRepository;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class EstablismentsViewModel extends RxViewModel {

    private EstablismentsRepository repository;
    private EstablishmentListDataSourceFactory dataSourceFactory;

    private MutableLiveData<List<Establishment>> establismentsLiveData = new MutableLiveData<>();
    private LiveData<PagedList<Restaurant>> restaurantLiveList;

    private static final int pageSize = 20;

    @Inject
    public EstablismentsViewModel(EstablismentsRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Establishment>> getEstablismentTypes(int cityId, Double lat, Double lon) {
        disposable.add(repository.getEstablismentTypes(cityId, lat, lon)
                .map(EstablismentsResponse::getEstablishments)
                .flatMapIterable(response -> response)
                .toList()
                .subscribe(response -> establismentsLiveData.setValue(response)));

        return establismentsLiveData;
    }

    public LiveData<PagedList<Restaurant>> getEstablishmentList(String establishmentId, int entityId, String entityType) {
        dataSourceFactory = repository.getDataSoureFactory(disposable, establishmentId, entityId, entityType);
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setEnablePlaceholders(false)
                .build();

        restaurantLiveList = new LivePagedListBuilder<>(dataSourceFactory, config).build();

        try{
            Timber.e(" viemodel içi %s ", restaurantLiveList.getValue().size());
            Timber.e("viewmodel içi dataSource factory %s ", dataSourceFactory);

        } catch (NullPointerException e) {
            Timber.e("view model hata " + e);
        }

        return restaurantLiveList;
    }
}
