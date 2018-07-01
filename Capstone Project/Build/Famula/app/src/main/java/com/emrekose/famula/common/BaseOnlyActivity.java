package com.emrekose.famula.common;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public abstract class BaseOnlyActivity<DB extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public DB dataBinding;
    public VM viewModel;

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract Class<VM> getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
    }
}
