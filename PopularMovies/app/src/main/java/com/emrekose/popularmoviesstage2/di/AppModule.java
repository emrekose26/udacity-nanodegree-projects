package com.emrekose.popularmoviesstage2.di;

import android.app.Application;
import android.content.Context;

import com.emrekose.popularmoviesstage2.BuildConfig;
import com.emrekose.popularmoviesstage2.data.remote.ApiSource;
import com.emrekose.popularmoviesstage2.data.remote.RequestInterceptor;
import com.emrekose.popularmoviesstage2.ui.detail.DetailActivity;
import com.emrekose.popularmoviesstage2.ui.detail.favorite.FavoriteInteractor;
import com.emrekose.popularmoviesstage2.ui.detail.favorite.FavoriteMvpView;
import com.emrekose.popularmoviesstage2.ui.detail.favorite.FavoritePresenter;
import com.emrekose.popularmoviesstage2.ui.detail.overview.OverviewFragment;
import com.emrekose.popularmoviesstage2.ui.detail.overview.TrailerInteractor;
import com.emrekose.popularmoviesstage2.ui.detail.overview.TrailerMvpView;
import com.emrekose.popularmoviesstage2.ui.detail.overview.TrailerPresenter;
import com.emrekose.popularmoviesstage2.ui.detail.reviews.ReviewInteractor;
import com.emrekose.popularmoviesstage2.ui.detail.reviews.ReviewMvpView;
import com.emrekose.popularmoviesstage2.ui.detail.reviews.ReviewPresenter;
import com.emrekose.popularmoviesstage2.ui.detail.reviews.ReviewsFragment;
import com.emrekose.popularmoviesstage2.ui.main.MainFragment;
import com.emrekose.popularmoviesstage2.ui.main.MovieInteractor;
import com.emrekose.popularmoviesstage2.ui.main.MovieMvpView;
import com.emrekose.popularmoviesstage2.ui.main.MoviePresenter;
import com.emrekose.popularmoviesstage2.util.Constants;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class AppModule {

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            okHttpClient.addNetworkInterceptor(new StethoInterceptor());
            okHttpClient.addInterceptor(httpLoggingInterceptor);
            okHttpClient.addInterceptor(new RequestInterceptor());
        }
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    ApiSource provideApiSource(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(ApiSource.class);
    }

    @Provides
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    MovieMvpView provideMovieMvpView() {
        return new MainFragment();
    }

    @Provides
    MovieInteractor provideMovieInteractor(ApiSource apiSource) {
        return new MovieInteractor(apiSource);
    }

    @Provides
    MoviePresenter provideMoviePresenter(MovieMvpView view, MovieInteractor interactor) {
        return new MoviePresenter(view, interactor);
    }

    @Provides
    TrailerMvpView provideTrailerMvpView() {
        return new OverviewFragment();
    }

    @Provides
    TrailerInteractor provideTrailerInteractor(ApiSource apiSource) {
        return new TrailerInteractor(apiSource);
    }

    @Provides
    TrailerPresenter provideTrailerPresenter(TrailerMvpView view, TrailerInteractor interactor) {
        return new TrailerPresenter(view, interactor);
    }

    @Provides
    ReviewMvpView provideReviewMvpView() {
        return new ReviewsFragment();
    }

    @Provides
    ReviewInteractor provideReviewInteractor(ApiSource apiSource) {
        return new ReviewInteractor(apiSource);
    }

    @Provides
    ReviewPresenter provideReviewPresenter(ReviewMvpView view, ReviewInteractor interactor) {
        return new ReviewPresenter(view, interactor);
    }

    @Provides
    FavoriteMvpView provideFavoriteMvpView() {
        return new DetailActivity();
    }

    @Provides
    FavoriteInteractor provideFavoriteInteractor(Context context) {
        return new FavoriteInteractor(context);
    }

    @Provides
    FavoritePresenter provideFavoritePresenter(FavoriteMvpView view, FavoriteInteractor interactor) {
        return new FavoritePresenter(view, interactor);
    }
}
