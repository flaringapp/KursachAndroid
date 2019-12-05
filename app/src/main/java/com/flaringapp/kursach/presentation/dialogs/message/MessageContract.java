package com.flaringapp.kursach.presentation.dialogs.message;

import androidx.annotation.Nullable;

import com.flaringapp.kursach.presentation.mvp.IBaseDialog;
import com.flaringapp.kursach.presentation.mvp.IBasePresenter;

public interface MessageContract {

    interface ViewContract extends IBaseDialog {
        void setHeaderVisible(boolean visible);
        void setIconVisible(boolean visible);

        void setHeader(String header);
        void setMessage(String message);
        void setIcon(int iconRes);
        void setActionButtonText(String actionButtonText);
    }

    interface PresenterContract extends IBasePresenter<ViewContract> {
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

    interface MessageDialogParent {
        void onMessageClosed(@Nullable String tag);
    }
}
