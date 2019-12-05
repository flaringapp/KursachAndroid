package com.flaringapp.kursach.presentation.fragments.tree;

import com.flaringapp.kursach.data.treeSplitter.TextTreeSplitter.ISplitNode;
import com.flaringapp.kursach.presentation.mvp.IBaseFragment;

public interface ITreeView extends IBaseFragment {

    void onTreeReady(ISplitNode data);

}
