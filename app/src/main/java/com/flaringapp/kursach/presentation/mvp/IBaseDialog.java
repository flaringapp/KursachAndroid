package com.flaringapp.kursach.presentation.mvp;

public interface IBaseDialog extends IBaseView {
    String getDialogTag();

    void close();
}
