package com.flaringapp.base.presentation.activities.main.impl;

import androidx.annotation.Nullable;

import com.flaringapp.base.presentation.activities.main.IMainPresenter;
import com.flaringapp.base.presentation.activities.main.IMainView;
import com.flaringapp.base.presentation.activities.main.navigation.Navigator;
import com.flaringapp.base.presentation.activities.main.navigation.Screen;
import com.flaringapp.base.presentation.mvp.BasePresenter;

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter, Navigator {

    @Override
    public void navigateTo(Screen screen, @Nullable Object... data) {
        if (view != null) {
            view.openScreen(screen, data);
        }
    }
}
