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
                                     @Query("lat") double lat,
                                     @Query("lon") double lon);

    // cuisines
    @GET("cuisines")
    Single<CuisinesResponse> getCuisines(@Query("city_id") int cityId,
                                         @Query("lat") double lat,
                                         @Query("lon") double lon);

    // establisments
    @GET("establisments")
    Single<EstablismentsResponse> getEstablisments(@Query("city_id") int cityId,
                                                   @Query("lat") double lat,
                                                   @Query("lon") double lon);

    // geocode
    @GET("geocode")
    Single<GeocodeResponse> getGeoCode(@Query("lat") double lat,
                                       @Query("lon") double lon);

    // location_details
    @GET("location_details")
    Single<LocationDetailsResponse> getLocationDetails(@Query("entity_id") int entityId,
                                                       @Query("entity_type") String entityType);

    // locations
    @GET("locations")
    Single<LocationsResponse> getLocations(@Query("query") String query,
                                           @Query("lat") double lat,
                                           @Query("lon") double lon);
    // restaurant details
    @GET("restaurant")
    Single<RestaurantDetailResponse> getRestaurantDetails(@Query("res_id") int restaurantId);

    // reviews
    @GET("reviews")
    Single<ReviewsResponse> getReviews(@Query("res_id") int restaurantId);

    // search
    @GET("search")
    Single<SearchResponse> getSearchDatas(@Query("q") String query,
                                          @Query("entity_id") String entityId,
                                          @Query("lat") double lat,
                                          @Query("lon") double lon,
                                          @Query("start") int page);
}
