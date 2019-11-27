package com.flaringapp.base.presentation.activities

import androidx.fragment.app.Fragment
import com.flaringapp.base.presentation.activities.navigation.AppNavigation
import com.flaringapp.base.presentation.activities.navigation.Screen
import com.flaringapp.base.presentation.mvp.IBaseActivity
import com.flaringapp.base.presentation.mvp.IBasePresenter

interface MainContract {

    interface ViewContract: IBaseActivity, AppNavigation {
        fun openScreen(fragment: Fragment)
    }

    interface PresenterContract: IBasePresenter<ViewContract> {
        fun onNavigate(screen: Screen, data: Any?)
    }

}