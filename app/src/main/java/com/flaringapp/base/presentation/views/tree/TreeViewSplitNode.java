package com.flaringapp.base.presentation.views.tree;

import android.widget.TextView;

import java.util.List;

class TreeViewSplitNode {

    private TextView nodeView;

    private List<TreeViewSplitNode> childViews;

    TreeViewSplitNode(TextView nodeView, List<TreeViewSplitNode> childViews) {
        this.nodeView = nodeView;
        this.childViews = childViews;
    }

    TextView getNodeView() {
        return nodeView;
    }

    List<TreeViewSplitNode> getChildViews() {
        return childViews;
    }

}
