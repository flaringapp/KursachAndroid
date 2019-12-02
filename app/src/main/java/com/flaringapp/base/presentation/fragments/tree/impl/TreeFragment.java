package com.flaringapp.base.presentation.fragments.tree.impl;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.flaringapp.base.R;
import com.flaringapp.base.app.di.Di;
import com.flaringapp.base.data.treeSplitter.TextTreeSplitter.ISplitNode;
import com.flaringapp.base.presentation.fragments.tree.ITreePresenter;
import com.flaringapp.base.presentation.mvp.BaseFragment;
import com.flaringapp.base.presentation.fragments.tree.ITreeView;
import com.flaringapp.base.presentation.views.tree.TreeView;

public class TreeFragment extends BaseFragment<ITreePresenter> implements ITreeView {

    private static final String TEXT_KEY = "key_text";
    private static final String SEPARATOR_START_KEY = "key_separator_start";
    private static final String SEPARATOR_END_KEY = "key_separator_end";

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
}
