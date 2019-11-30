package com.flaringapp.base.presentation.fragments.inputData;

import com.flaringapp.base.presentation.mvp.IBasePresenter;

public interface IInputDataPresenter extends IBasePresenter<IInputDataView> {

    void onContinueClick();

    void onStartSeparatorChanged(String startSeparator);
    void onEndSeparatorChanged(String endSeparator);
    void onTextChanged(String text);
}
