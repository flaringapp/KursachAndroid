package com.flaringapp.kursach.app.di.modules;

import com.flaringapp.kursach.data.treeSplitter.TextTreeSplitter;
import com.flaringapp.kursach.presentation.activities.main.impl.MainPresenter;
import com.flaringapp.kursach.presentation.activities.main.navigation.Navigator;
import com.flaringapp.kursach.presentation.dialogs.message.impl.MessagePresenter;
import com.flaringapp.kursach.presentation.fragments.home.impl.HomePresenter;
import com.flaringapp.kursach.presentation.fragments.inputData.impl.InputDataPresenter;
import com.flaringapp.kursach.presentation.fragments.tree.impl.TreePresenter;

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
        addFactory(
                MessagePresenter.class, args -> new MessagePresenter()
        );
    }
}
