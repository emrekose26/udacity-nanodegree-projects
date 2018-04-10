package com.emrekose.bakingapp.base;


public interface MvpView {

    void showLoading();

    void hideLoading();

    void showErrorMessage();

    void showNetworkConnectionError();

    void showServerError();
}
