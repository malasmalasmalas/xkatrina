package com.fufufu.katrina.backup;

import android.content.Context;
import android.graphics.Typeface;

public final class TypefaceCache {
    private static Typeface sansTypeface;

    private TypefaceCache() {
    }

    public static synchronized Typeface getSans(Context context) {
        if (sansTypeface == null) {
            sansTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/sans.ttf");
        }
        return sansTypeface;
    }
}
