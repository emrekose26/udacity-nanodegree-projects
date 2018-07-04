package com.emrekose.famula.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.emrekose.famula.common.RxViewModel;
import com.emrekose.famula.model.cuisines.Cuisine;
import com.emrekose.famula.model.cuisines.CuisinesResponse;
import com.emrekose.famula.model.geocode.GeocodeResponse;
import com.emrekose.famula.model.geocode.NearbyRestaurant;
import com.emrekose.famula.repository.MainActivityRepository;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends RxViewModel {

    private MainActivityRepository repository;

    private MutableLiveData<List<Cuisine>> cuisinesLiveData = new MutableLiveData<>();
    private MutableLiveData<List<NearbyRestaurant>> nearbyRestaurantsLiveData = new MutableLiveData<>();

    @Inject
    public MainViewModel(MainActivityRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Cuisine>> getCuisines(int cityId, Double lat, Double lon, Integer takeCount) {
        if (takeCount != null) {
            disposable.add(repository.getCuisines(cityId, lat, lon)
                    .map(CuisinesResponse::getCuisines)
                    .flatMapIterable(response -> response)
                    .take(takeCount)
                    .toList()
                    .subscribe(response -> cuisinesLiveData.setValue(response)));
        } else {
            disposable.add(
                    repository.getCuisines(cityId, lat, lon)
                            .map(CuisinesResponse::getCuisines)
                            .subscribe(response -> cuisinesLiveData.setValue(response)));
        }

        return cuisinesLiveData;
    }

    public LiveData<List<NearbyRestaurant>> getNearbyRestaurants(Double lat, Double lon, Integer takeCount) {
        if (takeCount != null) {
            disposable.add(repository.getNearbyRestaurants(lat, lon)
                    .map(GeocodeResponse::getNearbyRestaurants)
                    .flatMapIterable(response -> response)
                    .take(takeCount)
                    .toList()
                    .subscribe(response -> nearbyRestaurantsLiveData.setValue(response)));
        } else {
            disposable.add(repository.getNearbyRestaurants(lat, lon)
                    .map(GeocodeResponse::getNearbyRestaurants)
                    .subscribe(response -> nearbyRestaurantsLiveData.setValue(response)));
        }

        return nearbyRestaurantsLiveData;
    }
}
