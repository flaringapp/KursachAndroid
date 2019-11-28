package com.flaringapp.base.presentation.mvp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity
        implements IBaseActivity {

    protected T presenter;

    @NonNull
    protected abstract T providePresenter();

    protected void onInitPresenter() {
        presenter.initView(this);
    }

    @Override
    public Context provideContext() {
        return this;
    }

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = providePresenter();
        presenter.onCreate();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
