package com.fufufu.katrina.backup;

import android.app.assist.AssistStructure;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;

public class Error {

        public static final String f803a = m55c(128522);

        public static final String f804b = m55c(128557);

        public static final String f805c = m55c(128531);

        public static final String f806d = m55c(128584);

        public static final String f807e = m55c(128561);

        public static final String f808f = m55c(128534);

        public static final String f809g = m55c(10084);

        public static boolean m58f(int i) {
        return (i & 2) != 0;
    }

        public static boolean m59g(int i) {
        return (i & 1) != 0;
    }

        public static String m53a(Context context, int i, String str) {
        return String.valueOf(context.getString(i)) + " " + str;
    }

        public static String m54b(AssistStructure.ViewNode viewNode) {
        CharSequence text = viewNode.getText();
        String strTrim = text != null ? text.toString().trim() : "";
        if (text == null || strTrim.isEmpty()) {
            String hint = viewNode.getHint();
            if (hint != null && !hint.trim().isEmpty()) {
                return hint.trim();
            }
            CharSequence contentDescription = viewNode.getContentDescription();
            if (contentDescription == null || contentDescription.toString().trim().isEmpty()) {
                return null;
            }
            return contentDescription.toString().trim();
        }
        return strTrim.trim();
    }

        public static String m55c(int i) {
        return new String(Character.toChars(i));
    }

        public static boolean m56d(Context context) {
        ComponentName componentName = new ComponentName(context, (Class<?>) AssistService.class);
        String string = Settings.Secure.getString(context.getContentResolver(), "voice_interaction_service");
        return string != null && string.contains(componentName.flattenToShortString());
    }

        public static boolean m57e(Context context) {
        if (Build.VERSION.SDK_INT < 29) {
            return false;
        }
        try {
            Resources resources = context.getResources();
            return resources.getInteger(resources.getIdentifier("config_navBarInteractionMode", "integer", "android")) == 2;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

        public static boolean m60h(AssistStructure.ViewNode viewNode) {
        return viewNode.getVisibility() == 0 && viewNode.getAlpha() > 0.0f && viewNode.getWidth() > 0 && viewNode.getHeight() > 0;
    }

        public static boolean m61i(Context context, int i, int i2, int i3, int i4) {
        return i3 + i >= 0 && i <= context.getResources().getDisplayMetrics().widthPixels && i4 + i2 >= 0 && i2 <= context.getResources().getDisplayMetrics().heightPixels;
    }

        public static String m62j(String str) {
        if (str.length() <= 64) {
            return str;
        }
        return String.valueOf(str.substring(0, 64)) + "...";
    }
}