package com.flaringapp.base.app.di.modules;

import com.flaringapp.base.presentation.activities.main.impl.MainPresenter;
import com.flaringapp.base.presentation.activities.main.navigation.Navigator;
import com.flaringapp.base.presentation.fragments.home.impl.HomePresenter;

public final class PresentationModule extends DiModule {

    @Override
    protected void initSingles() {
        addSingle(new MainPresenter());

        addSingle(get(Navigator.class));
    }

    @Override
    protected void initFactories() {
        addFactory(
                HomePresenter.class, args -> new HomePresenter(get(Navigator.class))
        );
    }
}
