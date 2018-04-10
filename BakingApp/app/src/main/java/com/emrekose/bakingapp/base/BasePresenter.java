package com.emrekose.bakingapp.base;


public interface BasePresenter<V> {

    void setView(V view);

    void detachView();
}
