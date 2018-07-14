package com.emrekose.famula.repository;

import com.emrekose.famula.data.remote.ApiService;
import com.emrekose.famula.model.restaurant.reviews.ReviewsResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RestaurantDetailRepository {

    private ApiService apiService;

    @Inject
    public RestaurantDetailRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Flowable<ReviewsResponse> getReviews(int restauntId) {
        return apiService.getReviews(restauntId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
