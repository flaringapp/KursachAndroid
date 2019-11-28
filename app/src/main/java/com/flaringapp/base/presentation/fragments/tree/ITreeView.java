package com.flaringapp.base.presentation.fragments.tree;

import com.flaringapp.base.data.treeSplitter.TextTreeSplitter.ISplitNode;
import com.flaringapp.base.presentation.mvp.IBaseFragment;

public interface ITreeView extends IBaseFragment {

    void onTreeReady(ISplitNode data);

}
