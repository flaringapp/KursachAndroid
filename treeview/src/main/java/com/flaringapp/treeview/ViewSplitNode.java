package com.flaringapp.treeview;

import android.widget.TextView;

import java.util.List;

class ViewSplitNode {

    private TextView nodeView;

    private List<ViewSplitNode> childViews;

    ViewSplitNode(TextView nodeView, List<ViewSplitNode> childViews) {
        this.nodeView = nodeView;
        this.childViews = childViews;
    }

    TextView getNodeView() {
        return nodeView;
    }

    List<ViewSplitNode> getChildViews() {
        return childViews;
    }

}
