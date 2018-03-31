package com.emrekose.popularmoviesstage2.base;


public interface MvpView {

    void showLoading();

    void hideLoading();

    void showErrorMessage();

    void showNetworkConnectionError();

    void showServerError();
}
