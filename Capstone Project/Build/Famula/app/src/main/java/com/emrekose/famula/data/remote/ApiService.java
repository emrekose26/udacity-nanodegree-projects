package com.emrekose.famula.data.remote;

import com.emrekose.famula.model.categories.CategoryResponse;
import com.emrekose.famula.model.cities.CitiesResponse;
import com.emrekose.famula.model.cuisines.CuisinesResponse;
import com.emrekose.famula.model.establisments.EstablismentsResponse;
import com.emrekose.famula.model.geocode.GeocodeResponse;
import com.emrekose.famula.model.locationDetails.LocationDetailsResponse;
import com.emrekose.famula.model.locations.LocationsResponse;
import com.emrekose.famula.model.restaurant.detail.RestaurantDetailResponse;
import com.emrekose.famula.model.restaurant.reviews.ReviewsResponse;
import com.emrekose.famula.model.restaurant.search.SearchResponse;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    // categories
    @GET("categories")
    Single<CategoryResponse> getCategories();

    // cities
    @GET("cities")
    Single<CitiesResponse> getCities(@Query("q") String cityName,
                                     @Query("lat") Double lat,
                                     @Query("lon") Double lon);

    // cuisines
    @GET("cuisines")
    Flowable<CuisinesResponse> getCuisines(@Query("city_id") int cityId,
                                           @Query("lat") Double lat,
                                           @Query("lon") Double lon);

    // establisments
    @GET("establishments")
    Flowable<EstablismentsResponse> getEstablisments(@Query("city_id") int cityId,
                                                     @Query("lat") Double lat,
                                                     @Query("lon") Double lon);

    // geocode
    @GET("geocode")
    Flowable<GeocodeResponse> getGeoCode(@Query("lat") Double lat,
                                         @Query("lon") Double lon);

    // location_details
    @GET("location_details")
    Single<LocationDetailsResponse> getLocationDetails(@Query("entity_id") int entityId,
                                                       @Query("entity_type") String entityType);

    // locations
    @GET("locations")
    Flowable<LocationsResponse> getLocations(@Query("query") String query);

    // restaurant details
    @GET("restaurant")
    Single<RestaurantDetailResponse> getRestaurantDetails(@Query("res_id") int restaurantId);

    // reviews
    @GET("reviews")
    Flowable<ReviewsResponse> getReviews(@Query("res_id") int restaurantId);

    // search
    @GET("search")
    Flowable<SearchResponse> getSearchDatas(@Query("q") String query,
                                            @Query("establishment_type") String establishmentType,
                                            @Query("entity_id") Integer entityId,
                                            @Query("entity_type") String entityType,
                                            @Query("lat") Double lat,
                                            @Query("lon") Double lon,
                                            @Query("cuisines") String cuisinesId);
}
