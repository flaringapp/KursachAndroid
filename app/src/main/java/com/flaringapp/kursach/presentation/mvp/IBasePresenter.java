package com.flaringapp.kursach.presentation.mvp;

import androidx.annotation.NonNull;

public interface IBasePresenter<T extends IBaseView> {

    void initView(@NonNull T view);

    void onCreate();

    void onStart();

    void onDestroy();
}
