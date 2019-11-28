package com.flaringapp.base.app.di.modules;

import com.flaringapp.base.presentation.activities.impl.MainPresenter;
import com.flaringapp.base.presentation.fragments.home.impl.HomePresenter;

public final class PresentationModule extends DiModule {

    @Override
    protected void initFactories() {
        addFactory(
                MainPresenter.class, args -> new MainPresenter()
        );

        addFactory(
                HomePresenter.class, args -> new HomePresenter()
        );
    }
}
