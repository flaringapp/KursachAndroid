package com.flaringapp.base.presentation.activities.main;

import com.flaringapp.base.presentation.activities.main.navigation.Screen;
import com.flaringapp.base.presentation.mvp.IBaseActivity;

public interface IMainView extends IBaseActivity {
    void openScreen(Screen screen, Object... data);
}
