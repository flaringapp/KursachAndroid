package com.flaringapp.base.presentation.fragments.tree.impl;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.flaringapp.base.R;
import com.flaringapp.base.data.treeSplitter.TextTreeSplitter.ISplitNode;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.ConstraintSet.PARENT_ID;

class TreeDrawMediator {

    private ConstraintLayout container;

    private ISplitNode data;

    TreeDrawMediator(ConstraintLayout container) {
        this.container = container;
    }

    void setData(ISplitNode node) {
        this.data = node;
        invalidateData();
    }

    private void invalidateData() {
        container.removeAllViewsInLayout();

        if (data != null) {
            drawData(data);
        }
    }

    private void drawData(ISplitNode data) {
        TextView dataView = inflateDataView();
        dataView.setText(data.getData());
        container.addView(dataView);

        ConstraintSet set = new ConstraintSet();
        set.clone(container);

        set.connect(dataView.getId(), ConstraintSet.TOP, PARENT_ID, ConstraintSet.TOP);
        set.connect(dataView.getId(), ConstraintSet.START, PARENT_ID, ConstraintSet.START);
        set.connect(dataView.getId(), ConstraintSet.END, PARENT_ID, ConstraintSet.END);

        set.applyTo(container);

        drawData(dataView, data.childNodes());
    }

    private void drawData(View topView, List<ISplitNode> data) {
        List<TextView> dataViews = new ArrayList<>();

        for (ISplitNode node : data) {
            TextView dataView = inflateDataView();
            dataView.setText(node.getData());
            container.addView(dataView);
            dataViews.add(dataView);
        }

        ConstraintSet set = new ConstraintSet();
        set.clone(container);

        if (dataViews.size() == 1) {
            TextView dataView = dataViews.get(0);
            set.connect(dataView.getId(), ConstraintSet.TOP, topView.getId(), ConstraintSet.BOTTOM);
            set.connect(dataView.getId(), ConstraintSet.START, topView.getId(), ConstraintSet.START);
            set.connect(dataView.getId(), ConstraintSet.END, topView.getId(), ConstraintSet.END);
        } else {
            for (int i = 1; i < dataViews.size() - 1; i++) {
                TextView dataView = dataViews.get(i);
                TextView previousDataView = dataViews.get(i - 1);
                TextView nextDataView = dataViews.get(i + 1);

                set.connect(dataView.getId(), ConstraintSet.TOP, topView.getId(), ConstraintSet.BOTTOM);
                set.connect(dataView.getId(), ConstraintSet.START, previousDataView.getId(), ConstraintSet.END);
                set.connect(dataView.getId(), ConstraintSet.END, nextDataView.getId(), ConstraintSet.START);
            }

            TextView firstDataView = dataViews.get(0);
            TextView secondFirstDataView = dataViews.get(1);
            set.connect(firstDataView.getId(), ConstraintSet.TOP, topView.getId(), ConstraintSet.BOTTOM);
            set.connect(firstDataView.getId(), ConstraintSet.START, topView.getId(), ConstraintSet.START);
            set.connect(firstDataView.getId(), ConstraintSet.END, secondFirstDataView.getId(), ConstraintSet.START);

            TextView lastDataView = dataViews.get(dataViews.size() - 1);
            TextView secondLastDataView = dataViews.get(dataViews.size() - 2);
            set.connect(lastDataView.getId(), ConstraintSet.TOP, topView.getId(), ConstraintSet.BOTTOM);
            set.connect(lastDataView.getId(), ConstraintSet.START, secondLastDataView.getId(), ConstraintSet.END);
            set.connect(lastDataView.getId(), ConstraintSet.END, topView.getId(), ConstraintSet.END);
        }

        set.applyTo(container);

        for (int i = 0; i < dataViews.size(); i++) {
            List<ISplitNode> childNodes = data.get(i).childNodes();

            if (childNodes.size() > 0) {
                drawData(dataViews.get(i), childNodes);
            }
        }
    }

    private TextView inflateDataView() {
        TextView textView = (TextView) LayoutInflater.from(container.getContext())
                .inflate(R.layout.item_treeview_data, container, false);
        textView.setId(View.generateViewId());
        return textView;
    }

}
