package com.fufufu.katrina.backup;

import androidx.annotation.NonNull;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class IfwUtils {
    private static final String IFW_FOLDER = "/ifw";
    private static final String SYSTEM_PROPERTY_EFS_ENABLED_KEY = "persist.security.efs.enabled";
    private static final File DATA_DIRECTORY = getDir("ANDROID_DATA", "/data");
    private static final File SECURE_DATA_DIRECTORY = getDir("ANDROID_SECURE_DATA", "/data/secure");

    private static File getDir(String str, String str2) {
        String str3 = System.getenv(str);
        return str3 == null ? new File(str2) : new File(str3);
    }

    public static File getSecureDir() {
        return new File(isEncrypted() ? SECURE_DATA_DIRECTORY : DATA_DIRECTORY, "system");
    }

    public static boolean isEncrypted() {
        try {
            return ((Boolean) Class.forName("android.os.SystemProperties").getMethod("getBoolean", String.class, Boolean.TYPE).invoke(null, SYSTEM_PROPERTY_EFS_ENABLED_KEY, false)).booleanValue();
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }

    @NonNull
    public static String getIfw() {
        return getSecureDir() + IFW_FOLDER + File.separator;
    }
}