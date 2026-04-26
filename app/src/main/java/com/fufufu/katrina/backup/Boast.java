package com.fufufu.katrina.backup;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.widget.Toast;

public class Boast {
    private static volatile Boast globalBoast;
    private Toast internalToast;

    private Boast(Context context, Toast toast) {
        if (context == null) {
            throw new NullPointerException("Context requires a non-null parameter.");
        }
        if (toast == null) {
            throw new NullPointerException("Boast.Boast(Toast) requires a non-null parameter.");
        }
        LayoutInflater.from(context);
        if (context instanceof Activity) {
        }
        this.internalToast = toast;
    }

    private static Boast makeText(Context context, CharSequence charSequence, int i) {
        return new Boast(context, Toast.makeText(context, charSequence, i));
    }

    private static Boast makeText(Context context, int i, int i2) throws Resources.NotFoundException {
        return new Boast(context, Toast.makeText(context, i, i2));
    }

    private static Boast makeText(Context context, CharSequence charSequence) {
        return new Boast(context, Toast.makeText(context, charSequence, 0));
    }

    private static Boast makeText(Context context, int i) throws Resources.NotFoundException {
        return new Boast(context, Toast.makeText(context, i, 0));
    }

    public static void showText(Context context, CharSequence charSequence, int i) {
        makeText(context, charSequence, i).show();
    }

    public static void showText(Context context, int i, int i2) throws Resources.NotFoundException {
        makeText(context, i, i2).show();
    }

    public static void showText(Context context, CharSequence charSequence) {
        makeText(context, charSequence, 0).show();
    }

    public static void showText(Context context, int i) throws Resources.NotFoundException {
        makeText(context, i, 0).show();
    }

    public void cancel() {
        this.internalToast.cancel();
    }

    public void show() {
        show(true);
    }

    public void show(boolean z) {
        if (z && globalBoast != null) {
            globalBoast.cancel();
        }
        globalBoast = this;
        this.internalToast.show();
    }
}