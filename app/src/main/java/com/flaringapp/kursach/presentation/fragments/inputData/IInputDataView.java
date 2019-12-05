package com.flaringapp.kursach.presentation.fragments.inputData;

import androidx.annotation.StringRes;

import com.flaringapp.kursach.presentation.mvp.IBaseFragment;

public interface IInputDataView extends IBaseFragment {

    void setInputTextError(@StringRes int errorRes, Object... params);
    void setInputStartSeparatorError(@StringRes int errorRes, Object... params);
    void setInputEndSeparatorError(@StringRes int errorRes, Object... params);

    void onAllDataEntered();
    void onNotAllDataEntered();
}
