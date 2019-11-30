package com.flaringapp.base.app.di.modules;

import com.flaringapp.base.data.treeSplitter.TextTreeSplitter;
import com.flaringapp.base.presentation.activities.main.impl.MainPresenter;
import com.flaringapp.base.presentation.activities.main.navigation.Navigator;
import com.flaringapp.base.presentation.fragments.home.impl.HomePresenter;
import com.flaringapp.base.presentation.fragments.inputData.impl.InputDataPresenter;
import com.flaringapp.base.presentation.fragments.tree.impl.TreePresenter;

public final class PresentationModule extends DiModule {

    @Override
    protected void initSingles() {
        addSingle(new MainPresenter());
    }

    @Override
    protected void initFactories() {
        addFactory(
                HomePresenter.class, args -> new HomePresenter(get(Navigator.class))
        );

        addFactory(
                InputDataPresenter.class, args -> new InputDataPresenter(get(Navigator.class))
        );

        addFactory(
                TreePresenter.class, args -> new TreePresenter(get(TextTreeSplitter.class))
        );
    }
}
