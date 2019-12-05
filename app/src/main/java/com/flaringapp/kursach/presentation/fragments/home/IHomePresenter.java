package com.flaringapp.kursach.presentation.fragments.home;

import com.flaringapp.kursach.presentation.mvp.IBasePresenter;

public interface IHomePresenter extends IBasePresenter<IHomeView> {
    void onNextClicked();
}
