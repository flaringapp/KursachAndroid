
package com.flaringapp.base.presentation.activities.impl

import com.flaringapp.base.presentation.activities.MainContract
import com.flaringapp.base.presentation.activities.navigation.Screen
import com.flaringapp.base.presentation.fragments.home.impl.HomeFragment
import com.flaringapp.base.presentation.mvp.BasePresenter

class MainPresenter: BasePresenter<MainContract.ViewContract>(), MainContract.PresenterContract {

    override fun onNavigate(screen: Screen, data: Any?) {
        val fragment = when(screen) {
            Screen.HOME -> HomeFragment.newInstance()
        }
        view?.openScreen(fragment)
    }
}