package com.flaringapp.kursach.presentation.activities.main.navigation;

import androidx.annotation.Nullable;

public interface Navigator {

    void navigateTo(Screen screen, @Nullable Object... data);
}
