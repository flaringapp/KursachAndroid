package com.flaringapp.base.presentation.fragments.home.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flaringapp.base.R
import com.flaringapp.base.app.di.Di
import com.flaringapp.base.presentation.activities.navigation.AppNavigation
import com.flaringapp.base.presentation.fragments.home.HomeContract
import com.flaringapp.base.presentation.mvp.BaseFragment

class HomeFragment: BaseFragment<HomeContract.PresenterContract>(), HomeContract.ViewContract {

    override val presenter: HomeContract.PresenterContract = Di.inject(HomePresenter::class.java)

    override fun onInitPresenter() {
        presenter.view = this
        presenter.init(activity as AppNavigation)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews() {

    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}