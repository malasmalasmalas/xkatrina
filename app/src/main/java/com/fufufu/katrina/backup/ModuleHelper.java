package com.fufufu.katrina.backup;

import android.content.Context;
import android.content.DialogInterface;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.topjohnwu.superuser.Shell;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Helper untuk cek status & install module XKatrina (Magisk/KSU/APatch).
 * Module asli berupa zip yang disimpan di assets sebagai main.dex.
 */
public final class ModuleHelper {
    public static final String MODULE_DIR = "/data/adb/modules/XKatrina";
    public static final String SENSITIVE_PROPS_DIR = "/data/adb/modules/xkatrina_snstv_prps";
    public static final String ZIP_ASSET = "main.dex";
    public static final String ZIP_OUT = "/storage/emulated/0/xkatrina.zip";

    private ModuleHelper() {}

    /** Cek sinkron apakah module XKatrina terpasang & aktif (bukan disable). */
    public static boolean isInstalled() {
        Shell.Result r = Shell.cmd(
            "if [ -d '" + MODULE_DIR + "' ] && [ ! -f '" + MODULE_DIR + "/disable' ]; then echo OK; fi"
        ).exec();
        if (!r.isSuccess()) return false;
        for (String l : r.getOut()) if (l.contains("OK")) return true;
        return false;
    }

    /** Copy main.dex (zip) dari assets ke /sdcard/xkatrina.zip. */
    public static boolean extractZip(Context ctx) {
        try {
            InputStream in = ctx.getAssets().open(ZIP_ASSET);
            FileOutputStream out = new FileOutputStream(ZIP_OUT);
            byte[] buf = new byte[4096];
            int n;
            while ((n = in.read(buf)) > 0) out.write(buf, 0, n);
            out.flush(); out.close(); in.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Jalankan installer module via root manager yang tersedia. */
    public static void install(final Context ctx, final Runnable onSuccess) {
        if (!extractZip(ctx)) {
            SketchwareUtil.showMessage(ctx.getApplicationContext(), ctx.getString(C0978R.string.module_extract_failed));
            return;
        }
        Shell.cmd(
            "if [ -d /data/adb/ksu ] || command -v ksud >/dev/null 2>&1; then " +
            "  ksud module install '" + ZIP_OUT + "'; " +
            "elif command -v magisk >/dev/null 2>&1; then " +
            "  magisk --install-module '" + ZIP_OUT + "'; " +
            "elif [ -d /data/adb/ap ] || command -v apd >/dev/null 2>&1; then " +
            "  apd module install '" + ZIP_OUT + "'; " +
            "else " +
            "  echo 'NO_ROOT_MANAGER'; exit 1; " +
            "fi"
        ).submit(new Shell.ResultCallback() {
            @Override
            public void onResult(Shell.Result result) {
                List<String> outAll = result.getOut();
                String joined = String.join("\n", outAll) + "\n" + String.join("\n", result.getErr());
                if (result.isSuccess()) {
                    SketchwareUtil.showMessage(ctx.getApplicationContext(), ctx.getString(C0978R.string.module_install_success));
                    if (onSuccess != null) onSuccess.run();
                } else if (joined.contains("NO_ROOT_MANAGER")) {
                    SketchwareUtil.showMessage(ctx.getApplicationContext(), ctx.getString(C0978R.string.module_install_no_manager));
                } else {
                    SketchwareUtil.showMessage(ctx.getApplicationContext(), ctx.getString(C0978R.string.module_install_failed, joined));
                }
            }
        });
    }

    public static void showInstallDialog(final Context ctx, String featureName, final Runnable onCancel) {
        new MaterialAlertDialogBuilder(ctx)
            .setTitle(ctx.getString(C0978R.string.module_not_installed_title))
            .setMessage(ctx.getString(C0978R.string.module_not_installed_msg, featureName))
            .setPositiveButton(ctx.getString(C0978R.string.module_install), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface d, int w) {
                    install(ctx, new Runnable() {
                        @Override
                        public void run() {
                            new MaterialAlertDialogBuilder(ctx)
                                .setTitle(ctx.getString(C0978R.string.module_reboot_title))
                                .setMessage(ctx.getString(C0978R.string.module_reboot_msg))
                                .setPositiveButton(ctx.getString(C0978R.string.module_reboot_yes), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface d2, int w2) {
                                        Shell.cmd("svc power reboot || reboot").submit();
                                    }
                                })
                                .setNegativeButton(ctx.getString(C0978R.string.module_reboot_later), null)
                                .show();
                        }
                    });
                }
            })
            .setNegativeButton(ctx.getString(C0978R.string.module_cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface d, int w) {
                    if (onCancel != null) onCancel.run();
                }
            })
            .setCancelable(false)
            .show();
    }
}
