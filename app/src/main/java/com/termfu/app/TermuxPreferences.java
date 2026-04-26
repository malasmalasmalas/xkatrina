package com.termfu.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;
import com.termux.terminal.EmulatorDebug;
import com.termux.terminal.TerminalSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.json.JSONException;

/* JADX INFO: loaded from: classes91.dex */
final class TermuxPreferences {
    static final int BELL_BEEP = 2;
    static final int BELL_IGNORE = 3;
    static final int BELL_VIBRATE = 1;
    private static final String CURRENT_SESSION_KEY = "current_session";
    private static final String FONTSIZE_KEY = "fontsize";
    private static final int MAX_FONTSIZE = 256;
    private static final String SCREEN_ALWAYS_ON_KEY = "screen_always_on";
    static final int SHORTCUT_ACTION_CREATE_SESSION = 1;
    static final int SHORTCUT_ACTION_NEXT_SESSION = 2;
    static final int SHORTCUT_ACTION_PREVIOUS_SESSION = 3;
    static final int SHORTCUT_ACTION_RENAME_SESSION = 4;
    private static final String SHOW_EXTRA_KEYS_KEY = "show_extra_keys";
    private final int MIN_FONTSIZE;
    boolean mBackIsEscape;
    String mDefaultWorkingDir;
    boolean mDisableVolumeVirtualKeys;
    ExtraKeysInfos mExtraKeys;
    private int mFontSize;
    private boolean mScreenAlwaysOn;
    boolean mShowExtraKeys;
    private boolean mUseDarkUI;
    private boolean mUseFullScreen;
    private boolean mUseFullScreenWorkAround;
    int mBellBehaviour = 1;
    final List<KeyboardShortcut> shortcuts = new ArrayList();

    @Retention(RetentionPolicy.SOURCE)
    @interface AsciiBellBehaviour {
    }

    static final class KeyboardShortcut {
        final int codePoint;
        final int shortcutAction;

        KeyboardShortcut(int i, int i2) {
            this.codePoint = i;
            this.shortcutAction = i2;
        }
    }

    static int clamp(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    TermuxPreferences(Context context) {
        reloadFromProperties(context);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        float fApplyDimension = TypedValue.applyDimension(1, 1.0f, context.getResources().getDisplayMetrics());
        this.MIN_FONTSIZE = (int) (4.0f * fApplyDimension);
        this.mShowExtraKeys = defaultSharedPreferences.getBoolean(SHOW_EXTRA_KEYS_KEY, true);
        this.mScreenAlwaysOn = defaultSharedPreferences.getBoolean(SCREEN_ALWAYS_ON_KEY, false);
        int iRound = Math.round(fApplyDimension * 12.0f);
        iRound = iRound % 2 == 1 ? iRound - 1 : iRound;
        try {
            this.mFontSize = Integer.parseInt(defaultSharedPreferences.getString(FONTSIZE_KEY, Integer.toString(iRound)));
        } catch (ClassCastException | NumberFormatException unused) {
            this.mFontSize = iRound;
        }
        this.mFontSize = clamp(this.mFontSize, this.MIN_FONTSIZE, 256);
    }

    boolean toggleShowExtraKeys(Context context) {
        this.mShowExtraKeys = !this.mShowExtraKeys;
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(SHOW_EXTRA_KEYS_KEY, this.mShowExtraKeys).apply();
        return this.mShowExtraKeys;
    }

    int getFontSize() {
        return this.mFontSize;
    }

    void changeFontSize(Context context, boolean z) {
        int i = this.mFontSize + ((z ? 1 : -1) * 2);
        this.mFontSize = i;
        this.mFontSize = Math.max(this.MIN_FONTSIZE, Math.min(i, 256));
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(FONTSIZE_KEY, Integer.toString(this.mFontSize)).apply();
    }

    boolean isScreenAlwaysOn() {
        return this.mScreenAlwaysOn;
    }

    boolean isUsingBlackUI() {
        return this.mUseDarkUI;
    }

    boolean isUsingFullScreen() {
        return this.mUseFullScreen;
    }

    boolean isUsingFullScreenWorkAround() {
        return this.mUseFullScreenWorkAround;
    }

    void setScreenAlwaysOn(Context context, boolean z) {
        this.mScreenAlwaysOn = z;
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(SCREEN_ALWAYS_ON_KEY, z).apply();
    }

    static void storeCurrentSession(Context context, TerminalSession terminalSession) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(CURRENT_SESSION_KEY, terminalSession.mHandle).apply();
    }

    static TerminalSession getCurrentSession(TermuxActivity termuxActivity) {
        String string = PreferenceManager.getDefaultSharedPreferences(termuxActivity).getString(CURRENT_SESSION_KEY, "");
        int size = termuxActivity.mTermService.getSessions().size();
        for (int i = 0; i < size; i++) {
            TerminalSession terminalSession = termuxActivity.mTermService.getSessions().get(i);
            if (terminalSession.mHandle.equals(string)) {
                return terminalSession;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x012a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void reloadFromProperties(Context context) {
        int iHashCode;
        int iHashCode2;
        File file;
        Throwable th;
        File file2 = new File("/data/data/com.fufufu.katrina.backup/files/home/.termfu/termfu.properties");
        if (!file2.exists()) {
            file2 = new File("/data/data/com.fufufu.katrina.backup/files/home/.config/termfu/termfu.properties");
        }
        Properties properties = new Properties();
        try {
            if (file2.isFile() && file2.canRead()) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file2);
                    try {
                        properties.load(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
                    } finally {
                        try { fileInputStream.close(); } catch (IOException ignored) {}
                    }
                } catch (IOException e4) {
                    Log.w(EmulatorDebug.LOG_TAG, "Error reading properties file", e4);
                }
            }
        } catch (Exception e) {
            Toast.makeText(context, "Could not open properties file termfu.properties: " + e.getMessage(), 1).show();
            Log.e(EmulatorDebug.LOG_TAG, "Error loading props", e);
        }
        String property2 = properties.getProperty("bell-character", "vibrate");
        iHashCode = property2.hashCode();
        if (iHashCode == -1190396462) {
            if (iHashCode == 3019822 && property2.equals("beep")) {
                this.mBellBehaviour = 2;
            } else {
                this.mBellBehaviour = 1;
            }
        } else if (property2.equals("ignore")) {
            this.mBellBehaviour = 3;
        }
        String lowerCase2 = properties.getProperty("use-black-ui", "").toLowerCase();
        iHashCode2 = lowerCase2.hashCode();
        if (iHashCode2 == 3569038) {
            if (iHashCode2 == 97196323 && lowerCase2.equals("false")) {
                this.mUseDarkUI = false;
            } else {
                this.mUseDarkUI = (context.getResources().getConfiguration().uiMode & 48) == 32;
            }
        } else if (lowerCase2.equals("true")) {
            this.mUseDarkUI = true;
        }
        this.mUseFullScreen = "true".equals(properties.getProperty("fullscreen", "false").toLowerCase());
        this.mUseFullScreenWorkAround = "true".equals(properties.getProperty("use-fullscreen-workaround", "false").toLowerCase());
        this.mDefaultWorkingDir = properties.getProperty("default-working-directory", TermuxService.HOME_PATH);
        file = new File(this.mDefaultWorkingDir);
        if (file.exists() || !file.isDirectory()) {
            this.mDefaultWorkingDir = TermuxService.HOME_PATH;
        }
        try {
            this.mExtraKeys = new ExtraKeysInfos(properties.getProperty("extra-keys", "[[ESC, TAB, CTRL, ALT, {key: '-', popup: '|'}, DOWN, UP]]"), properties.getProperty("extra-keys-style", "default"));
        } catch (JSONException e2) {
            Toast.makeText(context, "Could not load the extra-keys property from the config: " + e2.toString(), 1).show();
            Log.e(EmulatorDebug.LOG_TAG, "Error loading props", e2);
            try {
                this.mExtraKeys = new ExtraKeysInfos("[[ESC, TAB, CTRL, ALT, {key: '-', popup: '|'}, DOWN, UP]]", "default");
            } catch (JSONException e3) {
                e3.printStackTrace();
                Toast.makeText(context, "Can't create default extra keys", 1).show();
                this.mExtraKeys = null;
            }
        }
        this.mBackIsEscape = "escape".equals(properties.getProperty("back-key", "back"));
        this.mDisableVolumeVirtualKeys = "volume".equals(properties.getProperty("volume-keys", "virtual"));
        this.shortcuts.clear();
        parseAction("shortcut.create-session", 1, properties);
        parseAction("shortcut.next-session", 2, properties);
        parseAction("shortcut.previous-session", 3, properties);
        parseAction("shortcut.rename-session", 4, properties);
    }

    private void parseAction(String str, int i, Properties properties) {
        String property = properties.getProperty(str);
        if (property == null) {
            return;
        }
        String[] strArrSplit = property.toLowerCase().trim().split("\\+");
        String strTrim = strArrSplit.length == 2 ? strArrSplit[1].trim() : null;
        if (strArrSplit.length != 2 || !strArrSplit[0].trim().equals("ctrl") || strTrim.isEmpty() || strTrim.length() > 2) {
            Log.e(EmulatorDebug.LOG_TAG, "Keyboard shortcut '" + str + "' is not Ctrl+<something>");
            return;
        }
        char cCharAt = strTrim.charAt(0);
        boolean zIsLowSurrogate = Character.isLowSurrogate(cCharAt);
        int codePoint = cCharAt;
        if (zIsLowSurrogate) {
            if (strTrim.length() != 2 || Character.isHighSurrogate(strTrim.charAt(1))) {
                Log.e(EmulatorDebug.LOG_TAG, "Keyboard shortcut '" + str + "' is not Ctrl+<something>");
                return;
            }
            codePoint = Character.toCodePoint(strTrim.charAt(1), cCharAt);
        }
        this.shortcuts.add(new KeyboardShortcut(codePoint, i));
    }
}
