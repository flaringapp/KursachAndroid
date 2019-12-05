package com.flaringapp.kursach.presentation.fragments.tree;

import com.flaringapp.kursach.presentation.mvp.IBasePresenter;

public interface ITreePresenter extends IBasePresenter<ITreeView> {
    void initData(String text, String separatorStart, String separatorEnd);

    void onSplitterErrorClosed();
}
