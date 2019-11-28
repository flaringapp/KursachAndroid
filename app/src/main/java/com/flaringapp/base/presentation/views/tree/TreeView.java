package com.flaringapp.base.presentation.views.tree;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.flaringapp.base.R;
import com.flaringapp.base.app.utils.ViewUtils;
import com.flaringapp.base.data.treeSplitter.TextTreeSplitter.ISplitNode;

public class TreeView extends HorizontalScrollView {

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
            drawDataOnLevel(data);
        }
    }

    private void drawDataOnLevel(ISplitNode data) {
        drawDataOnLevel(this, data, 0);
    }

    private void drawDataOnLevel(ViewGroup levelView, ISplitNode data, int index) {
        ViewGroup levelLayout = getLevelLayout(levelView, index);

        TextView dataText = levelLayout.findViewById(R.id.textData);
        dataText.setText(data.getData());

        ViewGroup nextLevelContainer = levelLayout.findViewById(R.id.layoutContainer);

        for (int i = 0; i < data.childNodes().size(); i++) {
            drawDataOnLevel(nextLevelContainer, data.childNodes().get(i), i);
        }
    }

    private ViewGroup getLevelLayout(ViewGroup levelLayout, int index) {
        while (index >= levelLayout.getChildCount()) {
            levelLayout.addView(inflateLevelView(levelLayout));
        }

        return (ViewGroup) levelLayout.getChildAt(index);
    }

    private View inflateLevelView(ViewGroup parent) {
        return LayoutInflater.from(getContext())
                .inflate(R.layout.item_treeview_level, parent, false);
    }
}
