package com.emrekose.famula.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.emrekose.famula.common.RxViewModel;
import com.emrekose.famula.model.restaurant.reviews.ReviewsResponse;
import com.emrekose.famula.model.restaurant.reviews.UserReview;
import com.emrekose.famula.repository.RestaurantDetailRepository;

import java.util.List;

import javax.inject.Inject;

public class RestaurantDetailViewModel extends RxViewModel {

    private RestaurantDetailRepository repository;

    private MutableLiveData<List<UserReview>> reviewsLiveData = new MutableLiveData<>();

    @Inject
    public RestaurantDetailViewModel(RestaurantDetailRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<UserReview>> getReviews(int restaurantId) {
        disposable.add(repository.getReviews(restaurantId)
                .map(ReviewsResponse::getUserReviews)
                .flatMapIterable(response -> response)
                .toList()
                .subscribe(response -> reviewsLiveData.postValue(response)));
        return reviewsLiveData;
    }
}
