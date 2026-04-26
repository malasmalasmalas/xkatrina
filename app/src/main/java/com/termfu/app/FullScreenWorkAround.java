package com.termfu.app;

import android.R;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes91.dex */
public class FullScreenWorkAround {
    private View mChildOfContent;
    private int mNavBarHeight;
    private int mUsableHeightPrevious;
    private ViewGroup.LayoutParams mViewGroupLayoutParams;

    public static void apply(TermuxActivity termuxActivity) {
        new FullScreenWorkAround(termuxActivity);
    }

    private FullScreenWorkAround(TermuxActivity termuxActivity) {
        View childAt = ((ViewGroup) termuxActivity.findViewById(R.id.content)).getChildAt(0);
        this.mChildOfContent = childAt;
        this.mViewGroupLayoutParams = childAt.getLayoutParams();
        this.mNavBarHeight = termuxActivity.getNavBarHeight();
        this.mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.termfu.app.FullScreenWorkAround$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                FullScreenWorkAround.this.possiblyResizeChildOfContent();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void possiblyResizeChildOfContent() {
        int iComputeUsableHeight = computeUsableHeight();
        if (iComputeUsableHeight != this.mUsableHeightPrevious) {
            int height = this.mChildOfContent.getRootView().getHeight();
            int i = height - iComputeUsableHeight;
            if (i > height / 4) {
                this.mViewGroupLayoutParams.height = (height - i) + getNavBarHeight();
            } else {
                this.mViewGroupLayoutParams.height = height;
            }
            this.mChildOfContent.requestLayout();
            this.mUsableHeightPrevious = iComputeUsableHeight;
        }
    }

    private int getNavBarHeight() {
        return this.mNavBarHeight;
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return rect.bottom - rect.top;
    }
}
