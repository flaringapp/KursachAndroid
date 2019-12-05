package com.flaringapp.kursach.presentation.mvp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.List;

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
    public void onBackPressed() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        for (Fragment fragment : fragments) {
            if (fragment instanceof BackClickListener) {
                if (((BackClickListener) fragment).onBackClicked()) return;
            }
        }

        super.onBackPressed();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
