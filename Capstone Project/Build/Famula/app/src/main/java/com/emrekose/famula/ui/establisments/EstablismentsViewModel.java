package com.emrekose.famula.ui.establisments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.emrekose.famula.common.RxViewModel;
import com.emrekose.famula.model.establisments.Establishment;
import com.emrekose.famula.model.establisments.EstablismentsResponse;
import com.emrekose.famula.repository.EstablismentsRepository;

import java.util.List;

import javax.inject.Inject;

public class EstablismentsViewModel extends RxViewModel {

    private EstablismentsRepository repository;

    private MutableLiveData<List<Establishment>> establismentsLiveData = new MutableLiveData<>();

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

    // TODO: 6.07.2018 establisments will using Paging
}
