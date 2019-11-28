package com.flaringapp.base.presentation.fragments.tree.impl;

import com.flaringapp.base.data.treeSplitter.TextTreeSplitter;
import com.flaringapp.base.data.treeSplitter.TextTreeSplitter.ISplitNode;
import com.flaringapp.base.presentation.fragments.tree.ITreeView;
import com.flaringapp.base.presentation.mvp.BasePresenter;
import com.flaringapp.base.presentation.fragments.tree.ITreePresenter;

public class TreePresenter extends BasePresenter<ITreeView> implements ITreePresenter {

    private final TextTreeSplitter splitter;

    private String text;
    private String separatorStart;
    private String separatorEnd;

    public TreePresenter(TextTreeSplitter splitter) {
        this.splitter = splitter;
    }

    @Override
    public void initData(String text, String separatorStart, String separatorEnd) {
        this.text = text;
        this.separatorStart = separatorStart;
        this.separatorEnd = separatorEnd;
    }

    @Override
    public void onStart() {
        try {
            ISplitNode result = splitter.split(
                    text,
                    separatorStart,
                    separatorEnd
            );

            result.getData();
        } catch (Exception e) {
            if (view != null) {
                view.handleError(e);
            }
        }
    }
}
