package com.flaringapp.base.presentation.mvp;

public interface IBaseDialog extends IBaseView {
    String getDialogTag();

    void close();
}
