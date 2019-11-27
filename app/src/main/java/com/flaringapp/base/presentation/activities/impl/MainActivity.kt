package com.flaringapp.base.presentation.activities.impl

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.flaringapp.base.R
import com.flaringapp.base.app.di.Di
import com.flaringapp.base.presentation.activities.MainContract
import com.flaringapp.base.presentation.activities.navigation.Screen
import com.flaringapp.base.presentation.fragments.home.impl.HomeFragment
import com.flaringapp.base.presentation.mvp.BaseActivity

class MainActivity : BaseActivity<MainContract.PresenterContract>(),
    MainContract.ViewContract {

    override val presenter: MainContract.PresenterContract = Di.inject(MainPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onInitPresenter()

        supportFragmentManager.commit {
            add(R.id.fragmentContainer, HomeFragment.newInstance())
        }

        presenter.onStart()
    }

    override fun onInitPresenter() {
        presenter.view = this
    }

    override fun navigateTo(screen: Screen, data: Any?) {
        presenter.onNavigate(screen, data)
    }

    override fun openScreen(fragment: Fragment) {
        supportFragmentManager.commit {
            addToBackStack(null)
            add(R.id.fragmentContainer, fragment)
        }
    }
}
