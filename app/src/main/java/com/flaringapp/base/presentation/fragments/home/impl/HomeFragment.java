package com.flaringapp.base.presentation.fragments.home.impl;

import androidx.annotation.NonNull;

import com.flaringapp.base.R;
import com.flaringapp.base.app.di.Di;
import com.flaringapp.base.presentation.fragments.home.IHomePresenter;
import com.flaringapp.base.presentation.mvp.BaseFragment;
import com.flaringapp.base.presentation.fragments.home.IHomeView;

public class HomeFragment extends BaseFragment<IHomePresenter> implements IHomeView {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @NonNull
    @Override
    protected IHomePresenter providePresenter() {
        return Di.inject(IHomePresenter.class);
    }

    @Override
    protected int provideLayoutRes() {
        return R.layout.fragment_home;
    }
}
