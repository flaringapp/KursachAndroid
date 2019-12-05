package com.flaringapp.kursach.presentation.dialogs.message;


import com.flaringapp.kursach.presentation.mvp.IBasePresenter;

public interface IMessagePresenter extends IBasePresenter<IMessageView> {
    void init(
            MessageDialogParent listener,
            String header,
            String message,
            int iconRes,
            String actionButtonText
    );

    void onActionButtonClick();

    void onClose();
}
