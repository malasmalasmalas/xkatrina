package com.fufufu.katrina.backup;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

/**
 * Helper untuk apply locale yang dipilih user.
 * Gunakan di setiap Activity:
 *   protected void attachBaseContext(Context base) {
 *       super.attachBaseContext(LocaleHelper.wrap(base));
 *   }
 */
public final class LocaleHelper {
    public static final String PREFS = "app_locale";
    public static final String KEY_LANG = "lang";
    public static final String KEY_DONE = "onboarding_done";

    /** Daftar bahasa yang didukung. Tambahkan di sini jika menambah bahasa baru. */
    public static final String[] SUPPORTED_TAGS = new String[]{"id", "en", "ja"};
    public static final String[] SUPPORTED_NAMES = new String[]{"Bahasa Indonesia", "English", "日本語 (Japanese)"};
    public static final String DEFAULT_TAG = "id";

    private LocaleHelper() {}

    public static String getSavedTag(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        return sp.getString(KEY_LANG, DEFAULT_TAG);
    }

    public static void setLocale(Context ctx, String tag) {
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit().putString(KEY_LANG, tag).apply();
    }

    public static boolean isOnboardingDone(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getBoolean(KEY_DONE, false);
    }

    public static void setOnboardingDone(Context ctx, boolean done) {
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit().putBoolean(KEY_DONE, done).apply();
    }

    /** Bungkus context dengan locale tersimpan. */
    public static Context wrap(Context base) {
        if (base == null) return null;
        String tag = getSavedTag(base);
        Locale locale = Locale.forLanguageTag(tag);
        Locale.setDefault(locale);

        Resources res = base.getResources();
        Configuration cfg = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 24) {
            cfg.setLocale(locale);
            return base.createConfigurationContext(cfg);
        } else {
            cfg.setLocale(locale);
            res.updateConfiguration(cfg, res.getDisplayMetrics());
            return base;
        }
    }
}
