package com.flaringapp.kursach.presentation.fragments.home.impl;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.flaringapp.kursach.R;
import com.flaringapp.kursach.app.di.Di;
import com.flaringapp.kursach.presentation.fragments.home.IHomePresenter;
import com.flaringapp.kursach.presentation.mvp.BaseFragment;
import com.flaringapp.kursach.presentation.fragments.home.IHomeView;

public class HomeFragment extends BaseFragment<IHomePresenter> implements IHomeView {

    @Nullable
    private Button buttonStart;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @NonNull
    @Override
    protected IHomePresenter providePresenter() {
        return Di.inject(IHomePresenter.class);
    }

    @Override
    protected int provideLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View view) {
        buttonStart = view.findViewById(R.id.buttonStart);
    }

    @Override
    protected void init() {
        super.init();
        buttonStart.setOnClickListener(view -> presenter.onNextClicked());
    }

    @Override
    protected void releaseViews() {
        buttonStart = null;
    }
}
