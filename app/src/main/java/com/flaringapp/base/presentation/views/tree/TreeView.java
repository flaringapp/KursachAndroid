package com.flaringapp.base.presentation.views.tree;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.flaringapp.base.R;
import com.flaringapp.base.app.utils.ViewUtils;
import com.flaringapp.base.data.treeSplitter.TextTreeSplitter.ISplitNode;

import java.util.ArrayList;
import java.util.List;

public class TreeView extends ConstraintLayout {

    private ISplitNode data;

    public TreeView(Context context) {
        super(context);
        init();
    }

    public TreeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        invalidateData();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        ViewUtils.setClipHierarchy(this, false);

        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        setLayoutParams(params);
    }

    public void setData(ISplitNode data) {
        this.data = data;
        invalidateData();
    }

    private void invalidateData() {
        removeAllViewsInLayout();

        if (data != null) {
            drawData(data);
        }
    }

    private void drawData(ISplitNode data) {
        TextView dataView = inflateDataView();
        dataView.setText(data.getData());
        addView(dataView);

        ConstraintSet set = new ConstraintSet();
        set.clone(this);

        set.connect(dataView.getId(), ConstraintSet.TOP, this.getId(), ConstraintSet.TOP);
        set.connect(dataView.getId(), ConstraintSet.START, this.getId(), ConstraintSet.START);
        set.connect(dataView.getId(), ConstraintSet.END, this.getId(), ConstraintSet.END);

        set.applyTo(this);

        drawData(this, data.childNodes());
    }

    private void drawData(View topView, List<ISplitNode> data) {
        List<TextView> dataViews = new ArrayList<>();

        for (ISplitNode node : data) {
            TextView dataView = inflateDataView();
            dataView.setText(node.getData());
            addView(dataView);
            dataViews.add(dataView);
        }

        ConstraintSet set = new ConstraintSet();
        set.clone(this);

        if (dataViews.size() == 1) {
            TextView dataView = dataViews.get(0);
            set.connect(dataView.getId(), ConstraintSet.TOP, topView.getId(), ConstraintSet.BOTTOM);
            set.connect(dataView.getId(), ConstraintSet.START, topView.getId(), ConstraintSet.START);
            set.connect(dataView.getId(), ConstraintSet.END, topView.getId(), ConstraintSet.END);
        } else {
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

            for (int i = 1; i < dataViews.size() - 1; i++) {
                TextView dataView = dataViews.get(i);
                TextView previousDataView = dataViews.get(i - 1);
                TextView nextDataView = dataViews.get(i + 1);

                set.connect(dataView.getId(), ConstraintSet.TOP, topView.getId(), ConstraintSet.BOTTOM);
                set.connect(dataView.getId(), ConstraintSet.START, previousDataView.getId(), ConstraintSet.END);
                set.connect(dataView.getId(), ConstraintSet.END, nextDataView.getId(), ConstraintSet.START);
            }
        }

        set.applyTo(this);

        for (int i = 0; i < dataViews.size(); i++) {
            List<ISplitNode> childNodes = data.get(i).childNodes();

            if (childNodes.size() > 0) {
                drawData(dataViews.get(i), childNodes);
            }
        }
    }

    private TextView inflateDataView() {
        TextView textView = (TextView) LayoutInflater.from(getContext())
                .inflate(R.layout.item_treeview_data, this, false);
        textView.setId(View.generateViewId());
        return textView;
    }
}
