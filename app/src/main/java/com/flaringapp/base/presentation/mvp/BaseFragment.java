package com.flaringapp.base.presentation.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<T extends IBasePresenter> extends Fragment
        implements IBaseFragment {

    protected T presenter;

    @NonNull
    protected abstract T providePresenter();

    protected  void onInitPresenter() {
        presenter.initView(this);
    }

    @Nullable
    @Override
    public Context provideContext() {
        return getContext();
    }

    @Override
    @CallSuper
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        presenter = providePresenter();
    }

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate();
    }

    @Nullable
    @Override
    public final View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(provideLayoutRes(), container, false);
    }

    @LayoutRes
    protected abstract int provideLayoutRes();

    @Override
    @CallSuper
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        onInitPresenter();
        super.onViewCreated(view, savedInstanceState);
        init();
        view.setOnClickListener(clickedView -> {
        });
        presenter.onStart();
    }

    protected void init() {
    }

    @Override
    public void onDestroyView() {
        presenter.onDestroy();
        super.onDestroyView();
    }
}
