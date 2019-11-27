package com.flaringapp.base.presentation.fragments.home.impl

import com.flaringapp.base.presentation.activities.navigation.AppNavigation
import com.flaringapp.base.presentation.fragments.home.HomeContract
import com.flaringapp.base.presentation.mvp.BasePresenter

class HomePresenter: BasePresenter<HomeContract.ViewContract>(), HomeContract.PresenterContract {

    private lateinit var appNavigation: AppNavigation

    override fun init(appNavigation: AppNavigation) {
        this.appNavigation = appNavigation
    }
}