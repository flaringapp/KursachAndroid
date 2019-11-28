package com.flaringapp.base.presentation.mvp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import es.dmoral.toasty.Toasty;

public interface IBaseView {

    @Nullable
    Context provideContext();

    default void showInfoToast(@StringRes int textRes) {
        Context context = provideContext();
        if (context != null) {
            Toasty.info(context, textRes).show();
        }
    }

    default void showInfoToast(String text) {
        Context context = provideContext();
        if (context != null) {
            Toasty.info(context, text).show();
        }
    }

    default void showWarningToast(@StringRes int textRes) {
        Context context = provideContext();
        if (context != null) {
            Toasty.warning(context, textRes).show();
        }
    }

    default void showWarningToast(String text) {
        Context context = provideContext();
        if (context != null) {
            Toasty.warning(context, text).show();
        }
    }
    default void showErrorToast(@StringRes int textRes) {
        Context context = provideContext();
        if (context != null) {
            Toasty.error(context, textRes).show();
        }
    }

    default void showErrorToast(String text) {
        Context context = provideContext();
        if (context != null) {
            Toasty.error(context, text).show();
        }
    }

    default void handleError(@NonNull Exception e) {
        showErrorToast(e.getMessage());
    }
}
