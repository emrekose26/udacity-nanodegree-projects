package com.emrekose.famula.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.emrekose.famula.common.RxViewModel;
import com.emrekose.famula.model.cuisines.CuisinesResponse;
import com.emrekose.famula.model.geocode.GeocodeResponse;
import com.emrekose.famula.repository.MainActivityRepository;

import javax.inject.Inject;

public class MainViewModel extends RxViewModel {

    private MainActivityRepository repository;

    private MutableLiveData<CuisinesResponse> cuisinesLiveData = new MutableLiveData<>();
    private MutableLiveData<GeocodeResponse> nearbyRestaurantsLiveData = new MutableLiveData<>();

    @Inject
    public MainViewModel(MainActivityRepository repository) {
        this.repository = repository;
    }

    public LiveData<CuisinesResponse> getCuisines(int cityId, Double lat, Double lon, Integer take) {
        if (take != null) {
            disposable.add(repository.getCuisines(cityId, lat, lon)
                    .take(take)
                    .subscribe(response -> cuisinesLiveData.setValue(response)));
        } else {
            disposable.add(
                    repository.getCuisines(cityId, lat, lon)
                            .subscribe(response -> cuisinesLiveData.setValue(response)));
        }

        return cuisinesLiveData;
    }

    public LiveData<GeocodeResponse> getNearbyRestaurants(Double lat, Double lon, Integer take) {
        if (take != null) {
            disposable.add(repository.getNearbyRestaurants(lat, lon)
                    .take(take)
                    .subscribe(response -> nearbyRestaurantsLiveData.setValue(response)));
        } else {
            disposable.add(repository.getNearbyRestaurants(lat, lon)
                    .subscribe(response -> nearbyRestaurantsLiveData.setValue(response)));
        }

        return nearbyRestaurantsLiveData;
    }
}
