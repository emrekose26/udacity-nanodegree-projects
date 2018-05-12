package com.emrekose.bakingapp.di;

import android.app.Application;
import android.content.Context;

import com.emrekose.bakingapp.BuildConfig;
import com.emrekose.bakingapp.data.remote.ApiSource;
import com.emrekose.bakingapp.ui.detail.RecipeDetailFragment;
import com.emrekose.bakingapp.ui.detail.RecipeDetailInteractor;
import com.emrekose.bakingapp.ui.detail.RecipeDetailPresenter;
import com.emrekose.bakingapp.ui.detail.RecipeDetailView;
import com.emrekose.bakingapp.ui.recipes.RecipeListFragment;
import com.emrekose.bakingapp.ui.recipes.RecipesInteractor;
import com.emrekose.bakingapp.ui.recipes.RecipesMvpView;
import com.emrekose.bakingapp.ui.recipes.RecipesPresenter;
import com.emrekose.bakingapp.utils.Constants;
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
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    ApiSource provideApiSource(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
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
    RecipesMvpView provideRecipesMvpView() {
        return new RecipeListFragment();
    }

    @Provides
    RecipesInteractor provideRecipesInteractor(ApiSource apiSource) {
        return new RecipesInteractor(apiSource);
    }

    @Provides
    RecipesPresenter provideRecipesPresenter(RecipesMvpView view, RecipesInteractor interactor) {
        return new RecipesPresenter(view, interactor);
    }

    @Provides
    RecipeDetailView provideRecipeDetailView() {
        return new RecipeDetailFragment();
    }

    @Provides
    RecipeDetailInteractor provideRecipeDetailInteractor(Context context) {
        return new RecipeDetailInteractor(context);
    }

    @Provides
    RecipeDetailPresenter provideRecipeDetailPresenter(RecipeDetailView view, RecipeDetailInteractor interactor) {
        return new RecipeDetailPresenter(view, interactor);
    }

}
