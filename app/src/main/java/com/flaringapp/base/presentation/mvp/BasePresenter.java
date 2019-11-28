package com.flaringapp.base.presentation.mvp;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BasePresenter<T extends IBaseView> implements IBasePresenter<T> {

    @Nullable
    protected T view;

    @Override
    public final void initView(@NonNull T view) {
        this.view = view;
    }

    @Override
    public void onCreate() {}

    @Override
    public void onStart() {}

    @Override
    @CallSuper
    public void onDestroy() {
        view = null;
    }
}
