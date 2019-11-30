package com.flaringapp.base.presentation.fragments.inputData;

import androidx.annotation.StringRes;

import com.flaringapp.base.presentation.mvp.IBaseFragment;

public interface IInputDataView extends IBaseFragment {

    void setInputTextError(@StringRes int errorRes);
    void setInputStartSeparatorError(@StringRes int errorRes);
    void setInputEndSeparatorError(@StringRes int errorRes);

    void onAllDataEntered();
    void onNotAllDataEntered();
}
