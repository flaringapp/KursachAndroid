package com.flaringapp.base.app.di.modules;

import com.flaringapp.base.app.di.DiModule;
import com.flaringapp.base.presentation.activities.MainContract;
import com.flaringapp.base.presentation.activities.impl.MainPresenter;
import com.flaringapp.base.presentation.fragments.home.HomeContract;
import com.flaringapp.base.presentation.fragments.home.impl.HomePresenter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

public final class PresentationModule extends DiModule {

    public PresentationModule() {
        super();
    }

    @Override
    protected List<Object> provideSingles() {
        return Arrays.asList(

        );
    }

    @Override
    protected HashMap<Class, Callable<Object>> provideFactories() {
        HashMap<Class, Callable<Object>> hashMap = new HashMap<>();
        hashMap.put(HomeContract.PresenterContract.class, HomePresenter::new);
        hashMap.put(MainContract.PresenterContract.class, MainPresenter::new);
        return hashMap;
    }
}
