package com.flaringapp.base.presentation.fragments.home.impl;

import com.flaringapp.base.presentation.activities.main.navigation.Navigator;
import com.flaringapp.base.presentation.fragments.home.IHomeView;
import com.flaringapp.base.presentation.mvp.BasePresenter;
import com.flaringapp.base.presentation.fragments.home.IHomePresenter;

public class HomePresenter extends BasePresenter<IHomeView> implements IHomePresenter {

    private Navigator navigator;

    public HomePresenter(Navigator navigator) {
        this.navigator = navigator;
    }
}
