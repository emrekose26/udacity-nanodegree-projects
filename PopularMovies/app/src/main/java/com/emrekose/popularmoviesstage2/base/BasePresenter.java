package com.emrekose.popularmoviesstage2.base;


public interface BasePresenter<V> {

    void setView(V view);

    void detachView();
}
