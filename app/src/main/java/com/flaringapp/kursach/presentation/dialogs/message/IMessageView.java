package com.flaringapp.kursach.presentation.dialogs.message;

import com.flaringapp.kursach.presentation.mvp.IBaseDialog;

public interface IMessageView extends IBaseDialog {
    void setHeaderVisible(boolean visible);

    void setHeader(String header);
    void setMessage(String message);
    void setIcon(int iconRes);
    void setActionButtonText(String actionButtonText);
}
