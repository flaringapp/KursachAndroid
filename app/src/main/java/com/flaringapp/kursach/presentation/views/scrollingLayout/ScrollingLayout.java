package com.flaringapp.kursach.presentation.views.scrollingLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class ScrollingLayout extends RelativeLayout {

    private float mPosX = 0, mPosY = 0;

    private View child;

    public ScrollingLayout(Context context) {
        super(context);
    }

    public ScrollingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollingLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        child = getChildAt(0);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mPosX = child.getX() - event.getRawX();
                mPosY = child.getY() - event.getRawY();
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                float newX = event.getRawX() + mPosX;
                float newY = event.getRawY() + mPosY;

                if (newX < 0) {
                    child.setX(0);
                } else if (newX > this.getWidth() - childWidth()) {
                    child.setX(this.getWidth() - childWidth());
                } else {
                    child.setX(newX);
                }

                if (newY < 0) {
                    child.setY(0);
                } else if (newY > this.getHeight() - childHeight()) {
                    child.setY(this.getHeight() - childHeight());
                } else {
                    child.setY(newY);
                }

                child.invalidate();
                break;
            }
        }
        return true;
    }

    private float childWidth() {
        return child.getWidth() * child.getScaleX();
    }

    private float childHeight() {
        return child.getHeight() * child.getScaleY();
    }
}