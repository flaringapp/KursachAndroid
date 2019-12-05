package com.flaringapp.base.presentation.fragments.inputData.impl;

import android.text.TextUtils;

import com.flaringapp.base.R;
import com.flaringapp.base.presentation.activities.main.navigation.Navigator;
import com.flaringapp.base.presentation.activities.main.navigation.Screen;
import com.flaringapp.base.presentation.fragments.inputData.IInputDataView;
import com.flaringapp.base.presentation.mvp.BasePresenter;
import com.flaringapp.base.presentation.fragments.inputData.IInputDataPresenter;

public class InputDataPresenter extends BasePresenter<IInputDataView>
        implements IInputDataPresenter {

    private Navigator navigator;

    private String startSeparator = "";
    private String endSeparator = "";
    private String text = "";

    private boolean allDataEntered = false;

    public InputDataPresenter(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void onStartSeparatorChanged(String startSeparator) {
        this.startSeparator = startSeparator;
        checkAllDataEntered();
    }

    @Override
    public void onEndSeparatorChanged(String endSeparator) {
        this.endSeparator = endSeparator;
        checkAllDataEntered();
    }

    @Override
    public void onTextChanged(String text) {
        this.text = text;
        checkAllDataEntered();
    }

    private void checkAllDataEntered() {
        if (allDataEntered) {
            if (startSeparator.isEmpty() || endSeparator.isEmpty() || text.isEmpty()) {
                allDataEntered = false;
                view.onNotAllDataEntered();
            }
        } else {
            if (!startSeparator.isEmpty() && !endSeparator.isEmpty() && !text.isEmpty()) {
                allDataEntered = true;
                view.onAllDataEntered();
            }
        }
    }

    @Override
    public void onContinueClick() {
        if (!validateData()) return;

        navigator.navigateTo(Screen.TREE, text, startSeparator, endSeparator);
    }

    private boolean validateData() {
        boolean valid = true;

        if (TextUtils.isEmpty(text)) {
            view.setInputTextError(R.string.error_text_input_empty);
            valid = false;
        }

        if (TextUtils.isEmpty(startSeparator)) {
            view.setInputTextError(R.string.error_separator_input_empty);
            valid = false;
        }

        if (TextUtils.isEmpty(endSeparator)) {
            view.setInputTextError(R.string.error_separator_input_empty);
            valid = false;
        }

        if (startSeparator.equals(endSeparator)) {
            view.setInputStartSeparatorError(R.string.error_separators_same, startSeparator);
            view.setInputEndSeparatorError(R.string.error_separators_same);
            valid = false;
        }

        return valid;
    }
}
