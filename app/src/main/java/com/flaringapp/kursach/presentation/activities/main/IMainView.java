package com.flaringapp.kursach.presentation.activities.main;

import com.flaringapp.kursach.presentation.activities.main.navigation.Screen;
import com.flaringapp.kursach.presentation.mvp.IBaseActivity;

public interface IMainView extends IBaseActivity {
    void openScreen(Screen screen, Object... data);
}
