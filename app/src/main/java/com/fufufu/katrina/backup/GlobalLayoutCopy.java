package com.fufufu.katrina.backup;

import android.app.assist.AssistContent;
import android.app.assist.AssistStructure;
import android.text.TextUtils;
import android.view.ViewTreeObserver;
import android.widget.Toast;
import java.util.Arrays;

class GlobalLayoutCopy implements ViewTreeObserver.OnGlobalLayoutListener {
    final AssistContent assistContent;
    final AssistStructure assistStructure;
    final CopyService copyService;
    final int windowIndex;

    GlobalLayoutCopy(CopyService copyService, AssistStructure assistStructure, int i, AssistContent assistContent) {
        this.copyService = copyService;
        this.assistStructure = assistStructure;
        this.windowIndex = i;
        this.assistContent = assistContent;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        this.copyService.rl_top.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        int[] iArr = new int[2];
        this.copyService.rl_top.getLocationOnScreen(iArr);
        int i = iArr[0];
        boolean z = true;
        int i2 = iArr[1];
        for (int windowNodeCount = this.assistStructure.getWindowNodeCount() - 1; windowNodeCount >= 0; windowNodeCount--) {
            AssistStructure.WindowNode windowNodeAt = this.assistStructure.getWindowNodeAt(windowNodeCount);
            CopyService copyService = this.copyService;
            copyService.processView(copyService.rl_top, windowNodeAt.getRootViewNode(), windowNodeAt.getLeft() - i, windowNodeAt.getTop() - i2);
            if (this.copyService.rl_top.getChildCount() > 0) {
                break;
            }
        }
        if (this.assistStructure.getActivityComponent() != null && Arrays.asList(Web.f876b).contains(this.assistStructure.getActivityComponent().getPackageName())) {
            this.copyService.tv_warning.setVisibility(0);
            this.copyService.tv_warning.setSelected(true);
        }
        boolean z2 = (Error.m58f(this.windowIndex) || this.copyService.bitmapview == null) ? false : true;
        AssistContent assistContent = this.assistContent;
        if (assistContent == null || assistContent.getWebUri() == null || this.assistContent.getWebUri().toString().isEmpty()) {
            z = z2;
        } else {
            this.copyService.s_fufufu = this.assistContent.getWebUri().toString();
        }
        CopyService copyService2 = this.copyService;
        copyService2.s_alltext = copyService2.s_alltext.trim();
        if (!TextUtils.isEmpty(this.copyService.s_alltext)) {
            this.copyService.tv_alltext.setText(this.copyService.s_alltext);
        }
        if (z || this.copyService.rl_top.getChildCount() > 0) {
            return;
        }
        Toast.makeText(this.copyService.mycontext, Error.m53a(this.copyService.mycontext, C0978R.string.error_no_text_found, Error.f805c), 0).show();
        this.copyService.finish();
    }
}