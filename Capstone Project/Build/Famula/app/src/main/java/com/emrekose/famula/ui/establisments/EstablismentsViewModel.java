package com.emrekose.famula.ui.establisments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.emrekose.famula.common.BaseViewModel;
import com.emrekose.famula.model.establisments.Establishment;
import com.emrekose.famula.model.establisments.EstablismentsResponse;
import com.emrekose.famula.model.restaurant.search.Restaurant;
import com.emrekose.famula.model.restaurant.search.SearchResponse;
import com.emrekose.famula.repository.EstablismentsRepository;

import java.util.List;

import javax.inject.Inject;

public class EstablismentsViewModel extends BaseViewModel {

    private EstablismentsRepository repository;

    private MutableLiveData<List<Establishment>> establismentsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Restaurant>> restaurantLiveList = new MutableLiveData<>();

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

    public LiveData<List<Restaurant>> getEstablishmentList(String establishmentId, int entityId, String entityType) {
        disposable.add(repository.getRestaurants(establishmentId, entityId, entityType)
                .map(SearchResponse::getRestaurants)
                .subscribe(restaurants -> restaurantLiveList.setValue(restaurants)));

        return restaurantLiveList;
    }
}
