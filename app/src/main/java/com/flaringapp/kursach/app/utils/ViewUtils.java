package com.flaringapp.kursach.app.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.widget.EditText;

import androidx.annotation.NonNull;

public final class ViewUtils {

    public static float dp(Context context, float dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.getResources().getDisplayMetrics()
        );
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
