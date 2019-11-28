package com.flaringapp.base.app.utils;

import android.view.View;
import android.view.ViewGroup;

public final class ViewUtils {

    public static void setClipHierarchy(View view, boolean clip) {
        if (view instanceof ViewGroup) {
            ((ViewGroup) view).setClipChildren(clip);
            ((ViewGroup) view).setClipToPadding(clip);
        }
        while (view.getParent() != null && view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            viewGroup.setClipChildren(clip);
            viewGroup.setClipToPadding(clip);
            view = viewGroup;
        }
    }

}
