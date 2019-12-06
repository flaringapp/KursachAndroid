package com.flaringapp.kursach.presentation.fragments.tree.impl;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.flaringapp.kursach.R;
import com.flaringapp.kursach.app.di.Di;
import com.flaringapp.kursach.data.treeSplitter.TextTreeSplitter.ISplitNode;
import com.flaringapp.kursach.data.treeSplitter.exceptions.EmptySeparatorException;
import com.flaringapp.kursach.data.treeSplitter.exceptions.EmptyTextException;
import com.flaringapp.kursach.data.treeSplitter.exceptions.InvalidSeparatorsCountException;
import com.flaringapp.kursach.data.treeSplitter.exceptions.InvalidSeparatorsOrderException;
import com.flaringapp.kursach.data.treeSplitter.exceptions.SameSeparatorsException;
import com.flaringapp.kursach.data.treeSplitter.exceptions.SeparatorsSubIncludedException;
import com.flaringapp.kursach.presentation.dialogs.message.MessageDialogParent;
import com.flaringapp.kursach.presentation.dialogs.message.impl.MessageDialog;
import com.flaringapp.kursach.presentation.fragments.tree.ITreePresenter;
import com.flaringapp.kursach.presentation.mvp.BaseFragment;
import com.flaringapp.kursach.presentation.fragments.tree.ITreeView;
import com.flaringapp.kursach.presentation.views.tree.TreeView;

import java.util.ArrayList;
import java.util.List;

public class TreeFragment extends BaseFragment<ITreePresenter> implements ITreeView,
        MessageDialogParent {

    private static final String TEXT_KEY = "key_text";
    private static final String SEPARATOR_START_KEY = "key_separator_start";
    private static final String SEPARATOR_END_KEY = "key_separator_end";

    private static final String SPLITTER_ERROR_DIALOG_TAG = "dialog_splitter_error";

    public static TreeFragment newInstance(
            String text,
            String separatorStart,
            String separatorEnd
    ) {
        TreeFragment treeFragment = new TreeFragment();

        Bundle arguments = new Bundle();
        arguments.putString(TEXT_KEY, text);
        arguments.putString(SEPARATOR_START_KEY, separatorStart);
        arguments.putString(SEPARATOR_END_KEY, separatorEnd);

        treeFragment.setArguments(arguments);

        return treeFragment;
    }

    @Nullable
    private TreeView treeView;

    @NonNull
    @Override
    protected ITreePresenter providePresenter() {
        return Di.inject(ITreePresenter.class);
    }

    @Override
    protected void onInitPresenter() {
        super.onInitPresenter();
        presenter.initData(
                requireArguments().getString(TEXT_KEY),
                requireArguments().getString(SEPARATOR_START_KEY),
                requireArguments().getString(SEPARATOR_END_KEY)
        );
    }

    @Override
    protected int provideLayoutRes() {
        return R.layout.fragment_tree;
    }

    @Override
    protected void initViews(View view) {
        treeView = view.findViewById(R.id.treeView);
    }

    @Override
    protected void releaseViews() {
        treeView = null;
    }

    @Override
    public void onTreeReady(ISplitNode data) {
        if (treeView != null) {
            treeView.setData(data);
        }
    }

    @Override
    public void handleError(@NonNull Exception exception) {
        int messageRes = -1;

        List<Object> params = new ArrayList<>();

        if (exception instanceof EmptySeparatorException) {
            messageRes = R.string.error_separator_input_empty;
        } else if (exception instanceof EmptyTextException) {
            messageRes = R.string.error_text_input_empty;
        } else if (exception instanceof SameSeparatorsException) {
            messageRes = R.string.error_separators_same;
            params.add(((SameSeparatorsException) exception).getSeparator());
        } else if (exception instanceof InvalidSeparatorsCountException) {
            messageRes = R.string.error_separators_wrong_count;
            params.add(((InvalidSeparatorsCountException) exception).getStartSeparatorCount());
            params.add(((InvalidSeparatorsCountException) exception).getEndSeparatorCount());
        } else if (exception instanceof InvalidSeparatorsOrderException) {
            messageRes = R.string.error_separators_wrong_order;
        } else if (exception instanceof SeparatorsSubIncludedException) {
            messageRes = R.string.error_separators_sub_included;
            params.add(((SeparatorsSubIncludedException) exception).getSeparatorMain());
            params.add(((SeparatorsSubIncludedException) exception).getSeparatorSubIncluded());
        } else {
            super.handleError(exception);
        }

        Object[] paramsArr = new Object[params.size()];
        params.toArray(paramsArr);

        if (messageRes != -1) {
            MessageDialog.newInstance(
                    getString(R.string.error),
                    getString(messageRes, paramsArr)
            ).withIcon(R.drawable.ic_error)
                    .show(getChildFragmentManager(), SPLITTER_ERROR_DIALOG_TAG);
        }
    }

    @Override
    public void onMessageClosed(@Nullable String tag) {
        if (tag == null) return;

        switch (tag) {
            case SPLITTER_ERROR_DIALOG_TAG:
                presenter.onSplitterErrorClosed();
                break;
        }
    }
}
