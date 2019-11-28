package com.flaringapp.base.presentation.views.scrollingLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class ScrollingLayout extends RelativeLayout {

    private float mPosX = 0, mPosY = 0;

    public ScrollingLayout(Context context) {
        super(context);
    }

    public ScrollingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollingLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                mPosX = getX() - event.getRawX();
                mPosY = getY() - event.getRawY();
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                setX(event.getRawX() + mPosX);
                setY(event.getRawY() + mPosY);
                invalidate();
                break;
            }
        }
        return true;
    }
}