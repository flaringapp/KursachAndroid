package com.flaringapp.kursach.presentation.fragments.home.impl;

import com.flaringapp.kursach.presentation.activities.main.navigation.Navigator;
import com.flaringapp.kursach.presentation.activities.main.navigation.Screen;
import com.flaringapp.kursach.presentation.fragments.home.IHomePresenter;
import com.flaringapp.kursach.presentation.fragments.home.IHomeView;
import com.flaringapp.kursach.presentation.mvp.BasePresenter;

public class HomePresenter extends BasePresenter<IHomeView> implements IHomePresenter {

    private Navigator navigator;

    public HomePresenter(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void onNextClicked() {
        navigator.navigateTo(
                Screen.INPUT_DATA
        );
    }
}
