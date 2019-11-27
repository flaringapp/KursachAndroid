package com.flaringapp.base.app.di.modules;

import com.flaringapp.base.presentation.activities.MainContract;
import com.flaringapp.base.presentation.activities.impl.MainPresenter;
import com.flaringapp.base.presentation.fragments.home.HomeContract;
import com.flaringapp.base.presentation.fragments.home.impl.HomePresenter;

public final class PresentationModule extends DiModule {

    @Override
    protected void initFactories() {
        addFactory(
                MainPresenter.class, MainPresenter::new
        );

        addFactory(
                HomePresenter.class, HomePresenter::new
        );
    }
}
