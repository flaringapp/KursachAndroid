package com.flaringapp.kursach.presentation.dialogs.message;

import androidx.annotation.Nullable;

public interface MessageDialogParent {
    void onMessageClosed(@Nullable String tag);
}