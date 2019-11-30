package com.flaringapp.base.presentation.fragments.inputData.impl;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.flaringapp.base.R;
import com.flaringapp.base.app.Const;
import com.flaringapp.base.app.di.Di;
import com.flaringapp.base.app.utils.ViewUtils;
import com.flaringapp.base.presentation.fragments.inputData.IInputDataPresenter;
import com.flaringapp.base.presentation.fragments.inputData.IInputDataView;
import com.flaringapp.base.presentation.mvp.BaseFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class InputDataFragment extends BaseFragment<IInputDataPresenter>
        implements IInputDataView {

    @Nullable
    private TextInputLayout layoutInputStartSeparator;
    @Nullable
    private TextInputEditText inputStartSeparator;
    @Nullable
    private TextInputLayout layoutInputEndSeparator;
    @Nullable
    private TextInputEditText inputEndSeparator;
    @Nullable
    private TextInputLayout layoutInputText;
    @Nullable
    private TextInputEditText inputText;
    @Nullable
    private Button buttonContinue;

    public static InputDataFragment newInstance() {
        return new InputDataFragment();
    }

    @NonNull
    @Override
    protected IInputDataPresenter providePresenter() {
        return Di.inject(IInputDataPresenter.class);
    }

    @Override
    protected int provideLayoutRes() {
        return R.layout.fragment_input_data;
    }

    @Override
    protected void initViews(View view) {
        layoutInputStartSeparator = view.findViewById(R.id.layoutStartSeparatorInput);
        inputStartSeparator = view.findViewById(R.id.inputStartSeparator);
        layoutInputStartSeparator = view.findViewById(R.id.layoutEndSeparatorInput);
        inputEndSeparator = view.findViewById(R.id.inputEndSeparator);
        layoutInputText = view.findViewById(R.id.layoutTextInput);
        inputText = view.findViewById(R.id.inputText);
        buttonContinue = view.findViewById(R.id.buttonContinue);
    }

    @Override
    protected void init() {
        super.init();
        ViewUtils.setTextChangeListener(inputStartSeparator, presenter::onStartSeparatorChanged);
        ViewUtils.setTextChangeListener(inputEndSeparator, presenter::onEndSeparatorChanged);
        ViewUtils.setTextChangeListener(inputText, presenter::onTextChanged);

        buttonContinue.setOnClickListener(view -> presenter.onContinueClick());

        buttonContinue.post(() -> buttonContinue.setTranslationY(buttonContinue.getHeight() * 2));
    }

    @Override
    protected void releaseViews() {
        layoutInputStartSeparator = null;
        inputStartSeparator = null;
        layoutInputEndSeparator = null;
        inputEndSeparator = null;
        layoutInputText = null;
        inputText = null;
        buttonContinue = null;
    }

    @Override
    public void setInputTextError(int errorRes) {
        if (layoutInputText != null) {
            layoutInputText.setError(getString(errorRes));
        }
    }

    @Override
    public void setInputStartSeparatorError(int errorRes) {
        if (layoutInputStartSeparator != null) {
            layoutInputStartSeparator.setError(getString(errorRes));
        }
    }

    @Override
    public void setInputEndSeparatorError(int errorRes) {
        if (layoutInputEndSeparator != null) {
            layoutInputEndSeparator.setError(getString(errorRes));
        }
    }

    @Override
    public void onAllDataEntered() {
        if (buttonContinue == null) return;

        buttonContinue.animate()
                .setDuration(Const.ANIM_DURATION)
                .setInterpolator(new DecelerateInterpolator())
                .translationY(0)
                .alpha(1f);
    }

    @Override
    public void onNotAllDataEntered() {
        if (buttonContinue == null) return;

        buttonContinue.animate()
                .setDuration(Const.ANIM_DURATION)
                .setInterpolator(new AccelerateInterpolator())
                .translationY(buttonContinue.getHeight() * 2)
                .alpha(0f);
    }
}
