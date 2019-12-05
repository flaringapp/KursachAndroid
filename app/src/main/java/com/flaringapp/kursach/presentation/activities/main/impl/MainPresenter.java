package com.flaringapp.kursach.presentation.activities.main.impl;

import androidx.annotation.Nullable;

import com.flaringapp.kursach.presentation.activities.main.IMainPresenter;
import com.flaringapp.kursach.presentation.activities.main.IMainView;
import com.flaringapp.kursach.presentation.activities.main.navigation.Navigator;
import com.flaringapp.kursach.presentation.activities.main.navigation.Screen;
import com.flaringapp.kursach.presentation.mvp.BasePresenter;

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter, Navigator {

    @Override
    public void navigateTo(Screen screen, @Nullable Object... data) {
        if (view != null) {
            view.openScreen(screen, data);
        }
    }
}
