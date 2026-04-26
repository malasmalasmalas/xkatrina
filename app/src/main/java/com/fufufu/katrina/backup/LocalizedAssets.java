package com.fufufu.katrina.backup;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Buka asset markdown sesuai bahasa terpilih.
 * Mencoba urutan: <base>_<lang>.md → <base>.md (fallback default Indonesia).
 *
 * Contoh: loadMarkdown(ctx, "xkatrina.md")
 *  - lang=ja → buka "xkatrina_ja.md", fallback "xkatrina.md"
 *  - lang=en → buka "xkatrina_en.md", fallback "xkatrina.md"
 *  - lang=id → buka "xkatrina.md"
 */
public final class LocalizedAssets {
    private LocalizedAssets() {}

    public static String loadMarkdown(Context ctx, String baseName) {
        String tag = LocaleHelper.getSavedTag(ctx);
        String localized = injectSuffix(baseName, tag);
        AssetManager am = ctx.getAssets();

        // 1) coba localized version
        if (!tag.equals(LocaleHelper.DEFAULT_TAG)) {
            String content = tryRead(am, localized);
            if (content != null) return content;
        }
        // 2) fallback default
        String content = tryRead(am, baseName);
        return content != null ? content : "";
    }

    private static String injectSuffix(String name, String suffix) {
        int dot = name.lastIndexOf('.');
        if (dot < 0) return name + "_" + suffix;
        return name.substring(0, dot) + "_" + suffix + name.substring(dot);
    }

    private static String tryRead(AssetManager am, String name) {
        try (InputStream in = am.open(name)) {
            byte[] buf = new byte[in.available()];
            in.read(buf);
            return new String(buf);
        } catch (IOException e) {
            return null;
        }
    }
}
