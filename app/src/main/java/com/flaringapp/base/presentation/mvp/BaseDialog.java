package com.flaringapp.base.presentation.mvp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.security.MessageDigest;

public abstract class BaseDialog<T extends IBasePresenter> extends DialogFragment
        implements IBaseDialog {

    protected T presenter;

    @NonNull
    protected abstract T providePresenter();

    protected void onInitPresenter() {
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

    @CallSuper
    protected void init() {
        initViews(getView());
    }

    protected void initViews(View view) {
    }

    @Override
    public void onResume() {
        super.onResume();

        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.width = ViewGroup.LayoutParams.MATCH_PARENT;
                attributes.height = ViewGroup.LayoutParams.WRAP_CONTENT;

                window.setAttributes(attributes);
            }
        }
    }

    @Override
    public void onDestroyView() {
        presenter.onDestroy();
        release();
        super.onDestroyView();
    }

    @CallSuper
    protected void release() {
        releaseViews();
    }

    protected void releaseViews() {
    }

    @Override
    public String getDialogTag() {
        return getTag();
    }

    @Override
    public void close() {
        dismiss();
    }
}
