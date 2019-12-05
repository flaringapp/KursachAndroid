package com.flaringapp.kursach.presentation.dialogs.message.impl;

import androidx.annotation.Nullable;

import com.flaringapp.kursach.presentation.dialogs.message.IMessagePresenter;
import com.flaringapp.kursach.presentation.dialogs.message.IMessageView;
import com.flaringapp.kursach.presentation.dialogs.message.MessageDialogParent;
import com.flaringapp.kursach.presentation.mvp.BasePresenter;

public class MessagePresenter extends BasePresenter<IMessageView>
        implements IMessagePresenter {

    @Nullable
    private MessageDialogParent listener;

    @Nullable
    private String header;
    private String message;
    private int iconRes;
    @Nullable
    private String actionButtonText;

    @Override
    public void init(
            MessageDialogParent listener,
            String header,
            String message,
            int iconRes,
            String actionButtonText
    ) {
        this.listener = listener;
        this.header = header;
        this.message = message;
        this.iconRes = iconRes;
        this.actionButtonText = actionButtonText;
    }

    @Override
    public void onStart() {
        if (header != null) {
            view.setHeaderVisible(true);
            view.setHeader(header);
        } else {
            view.setHeaderVisible(false);
        }

        view.setMessage(message);

        view.setIcon(iconRes);

        if (actionButtonText != null) {
            view.setActionButtonText(actionButtonText);
        }
    }

    @Override
    public void onActionButtonClick() {
        onClose();
        view.close();
    }

    @Override
    public void onClose() {
        if (listener != null) listener.onMessageClosed(view.getDialogTag());
    }
}
