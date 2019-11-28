package com.flaringapp.base.presentation.fragments.home.impl

import com.flaringapp.base.app.di.Di
import com.flaringapp.base.data.treeSplitter.TextTreeSplitter
import com.flaringapp.base.presentation.activities.navigation.AppNavigation
import com.flaringapp.base.presentation.fragments.home.HomeContract
import com.flaringapp.base.presentation.mvp.BasePresenter

class HomePresenter: BasePresenter<HomeContract.ViewContract>(), HomeContract.PresenterContract {

    private lateinit var appNavigation: AppNavigation

    private val splitter: TextTreeSplitter = Di.inject(TextTreeSplitter::class.java)

    override fun init(appNavigation: AppNavigation) {
        this.appNavigation = appNavigation
    }

    override fun onStart() {
        super.onStart()

        val node = splitter.split(
            "a(bc(d(e))fg(h)ij)k(l(m)n(o(p)r)s)(tu)",
            "(",
            ")"
        )

        println(node)
    }
}