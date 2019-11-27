package com.flaringapp.base.presentation.fragments.home

import com.flaringapp.base.presentation.activities.navigation.AppNavigation
import com.flaringapp.base.presentation.mvp.IBaseFragment
import com.flaringapp.base.presentation.mvp.IBasePresenter

interface HomeContract {

    interface ViewContract: IBaseFragment {

    }

    interface PresenterContract: IBasePresenter<ViewContract> {
        fun init(appNavigation: AppNavigation)
    }

}