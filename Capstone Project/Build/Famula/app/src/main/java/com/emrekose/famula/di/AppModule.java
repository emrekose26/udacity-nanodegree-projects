package com.emrekose.famula.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.emrekose.famula.BuildConfig;
import com.emrekose.famula.data.local.FamulaDatabase;
import com.emrekose.famula.data.local.dao.FavRestaurantDao;
import com.emrekose.famula.data.remote.ApiService;
import com.emrekose.famula.data.remote.RequestInterceptor;
import com.emrekose.famula.util.Constants;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {ViewModelModule.class})
class AppModule {

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
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
        }
        okHttpClient.addInterceptor(new RequestInterceptor());
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    FamulaDatabase provideFamulaDatabase(Application application) {
        return Room.databaseBuilder(application, FamulaDatabase.class, "famula.db").build();
    }

    @Provides
    @Singleton
    FavRestaurantDao provideFavRestaurantDao(FamulaDatabase database) {
        return database.favRestaurantDao();
    }
}
