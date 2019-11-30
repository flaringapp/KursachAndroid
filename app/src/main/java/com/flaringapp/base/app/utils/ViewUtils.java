package com.flaringapp.base.app.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;

public final class ViewUtils {

    public static void setClipHierarchy(View view, boolean clip) {
        if (view instanceof ViewGroup) {
            ((ViewGroup) view).setClipChildren(clip);
            ((ViewGroup) view).setClipToPadding(clip);
        }
        while (view.getParent() != null && view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            viewGroup.setClipChildren(clip);
            viewGroup.setClipToPadding(clip);
            view = viewGroup;
        }
    }

    public interface TextChangeListener {
        void onTextChanged(@NonNull String text);
    }
    public static void setTextChangeListener(EditText editText, TextChangeListener listener) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                Editable text = editText.getText();
                if (text == null) listener.onTextChanged("");
                else listener.onTextChanged(text.toString());
            }
        });
    }

}
