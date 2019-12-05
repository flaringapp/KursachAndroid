package com.flaringapp.base.presentation.fragments.tree.impl;

import com.flaringapp.base.data.treeSplitter.TextTreeSplitter;
import com.flaringapp.base.presentation.fragments.tree.ITreePresenter;
import com.flaringapp.base.presentation.fragments.tree.ITreeView;
import com.flaringapp.base.presentation.mvp.BasePresenter;

import io.reactivex.disposables.Disposable;

public class TreePresenter extends BasePresenter<ITreeView> implements ITreePresenter {

    private final TextTreeSplitter splitter;

    private String text;
    private String separatorStart;
    private String separatorEnd;

    private Disposable splitterDisposable = null;

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
        splitterDisposable = splitter.split(
                text,
                separatorStart,
                separatorEnd
        ).subscribe(
                (iSplitNode -> {
                    if (view != null) {
                        view.onTreeReady(iSplitNode);
                    }
                }),
                (error -> {
                    if (view != null) {
                        view.handleError((Exception) error);
                    }
                })
        );
    }

    @Override
    public void onDestroy() {
        if (splitterDisposable != null) {
            splitterDisposable.dispose();
        }
        super.onDestroy();
    }

    @Override
    public void onSplitterErrorClosed() {
        view.close();
    }
}
