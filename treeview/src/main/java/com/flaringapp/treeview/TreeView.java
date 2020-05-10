package com.flaringapp.treeview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.ConstraintSet.PARENT_ID;

public class TreeView extends ConstraintLayout {

    private static final float LINE_MARGIN = 8f;
    private static final float LINE_WIDTH = 4f;

    private static final int BLOCK_MARGIN_HORIZONTAL = 12;

    private ISplitNodeData data = null;

    private ViewSplitNode viewData = null;

    private Paint linePaint;

    private float lineMargin;

    public TreeView(Context context) {
        super(context);
        initSelf();
    }

    public TreeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSelf();
    }

    public TreeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSelf();
    }

    private void initSelf() {
        setWillNotDraw(false);

        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(ViewUtils.dp(getContext(), LINE_WIDTH));
        linePaint.setDither(true);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeJoin(Paint.Join.ROUND);
        linePaint.setStrokeCap(Paint.Cap.ROUND);

        lineMargin = ViewUtils.dp(getContext(), LINE_MARGIN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (viewData != null) {
            drawDataLines(canvas, viewData);
        }
    }

    public void setData(ISplitNodeData node) {
        this.data = node;
        invalidateData();
    }

    private void invalidateData() {
        removeAllViewsInLayout();

        if (data != null) {
            drawData(data);
        }

        post(this::invalidate);
    }

    private void drawData(ISplitNodeData data) {
        TextView dataView = inflateDataView();
        dataView.setText(data.getData());
        this.addView(dataView);

        ConstraintSet set = new ConstraintSet();
        set.clone(this);

        set.connect(dataView.getId(), ConstraintSet.TOP, PARENT_ID, ConstraintSet.TOP);
        set.connect(dataView.getId(), ConstraintSet.START, PARENT_ID, ConstraintSet.START);
        set.connect(dataView.getId(), ConstraintSet.END, PARENT_ID, ConstraintSet.END);

        set.applyTo(this);

        viewData = new ViewSplitNode(dataView, drawData(dataView, data.childNodes()));

        post(() -> measureNodeWidth(viewData));
    }

    private List<ViewSplitNode> drawData(View topView, List<? extends ISplitNodeData> data) {
        List<TextView> dataViews = new ArrayList<>();

        List<ViewSplitNode> splitViewNodes = new ArrayList<>();

        if (data.isEmpty()) return splitViewNodes;

        for (ISplitNodeData node : data) {
            TextView dataView = inflateDataView();
            dataView.setText(node.getData());
            this.addView(dataView);
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

        set.applyTo(this);

        for (int i = 0; i < dataViews.size(); i++) {
            List<? extends ISplitNodeData> childNodes = data.get(i).childNodes();

            List<ViewSplitNode> childViewNodes = new ArrayList<>();

            if (childNodes.size() > 0) {
                childViewNodes.addAll(drawData(dataViews.get(i), childNodes));
            }

            splitViewNodes.add(new ViewSplitNode(dataViews.get(i), childViewNodes));
        }

        return splitViewNodes;
    }

    private int measureNodeWidth(ViewSplitNode node) {
        View nodeView = node.getNodeView();

        int childWidth = 0;
        for (int i = 0; i < node.getChildViews().size(); i++) {
            childWidth += measureNodeWidth(node.getChildViews().get(i));
        }

        int targetWidth = Math.max(nodeView.getWidth(), childWidth);
        targetWidth += ViewUtils.dp(getContext(), BLOCK_MARGIN_HORIZONTAL) * 2;

        ViewGroup.LayoutParams params = nodeView.getLayoutParams();
        params.width = targetWidth;
        nodeView.setLayoutParams(params);

        return targetWidth;
    }

    private TextView inflateDataView() {
        TextView textView = (TextView) LayoutInflater.from(this.getContext())
                .inflate(R.layout.item_treeview_data, this, false);
        textView.setId(View.generateViewId());
        return textView;
    }

    private void drawDataLines(Canvas canvas, ViewSplitNode node) {
        TextView root = node.getNodeView();
        for (int i = 0; i < node.getChildViews().size(); i++) {
            ViewSplitNode childNode = node.getChildViews().get(i);
            TextView child = childNode.getNodeView();

            canvas.drawLine(
                    root.getX() + (root.getWidth() / 2f),
                    root.getY() + root.getHeight() + lineMargin,
                    child.getX() + (child.getWidth() / 2f),
                    child.getY() - lineMargin,
                    linePaint
            );

            drawDataLines(canvas, childNode);
        }
    }
}
