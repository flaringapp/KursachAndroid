package com.flaringapp.base.presentation.fragments.tree;

import com.flaringapp.base.presentation.mvp.IBasePresenter;

public interface ITreePresenter extends IBasePresenter<ITreeView> {
    void initData(String text, String separatorStart, String separatorEnd);
}
