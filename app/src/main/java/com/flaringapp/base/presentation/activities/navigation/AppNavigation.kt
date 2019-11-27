package com.flaringapp.base.presentation.activities.navigation

interface AppNavigation {

    fun navigateTo(
        screen: Screen,
        data: Any? = null
    )

}