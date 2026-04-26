package com.termfu.app;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.Process;
import android.os.UserManager;
import android.system.Os;
import android.util.Log;
import android.util.Pair;
import com.fufufu.katrina.backup.C0978R;
import com.termfu.app.TermuxInstaller;
import com.termux.terminal.EmulatorDebug;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* JADX INFO: loaded from: classes91.dex */
final class TermuxInstaller {
    public static native byte[] getZip();

    TermuxInstaller() {
    }

    static void setupIfNeeded(Activity activity, Runnable runnable) {
        if (!(((UserManager) activity.getSystemService("user")).getSerialNumberForUser(Process.myUserHandle()) == 0)) {
            new AlertDialog.Builder(activity).setTitle(C0978R.string.termfu_bootstrap_error_title).setMessage(C0978R.string.termfu_bootstrap_error_not_primary_user_message).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.termfu.app.TermuxInstaller$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    System.exit(0);
                }
            }).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).show();
            return;
        }
        File file = new File(TermuxService.PREFIX_PATH);
        if (file.isDirectory()) {
            runnable.run();
        } else {
            new C21541(activity, ProgressDialog.show(activity, null, activity.getString(C0978R.string.termfu_bootstrap_installer_body), true, false), file, runnable).start();
        }
    }

    /* JADX INFO: renamed from: com.termfu.app.TermuxInstaller$1 */
    static class C21541 extends Thread {
        private final /* synthetic */ File val$PREFIX_FILE;
        private final /* synthetic */ Activity val$activity;
        private final /* synthetic */ ProgressDialog val$progress;
        private final /* synthetic */ Runnable val$whenDone;

        C21541(Activity activity, ProgressDialog progressDialog, File file, Runnable runnable) {
            this.val$activity = activity;
            this.val$progress = progressDialog;
            this.val$PREFIX_FILE = file;
            this.val$whenDone = runnable;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Activity activity;
            Runnable runnable = null;
            try {
                try {
                    File file = new File("/data/data/com.fufufu.katrina.backup/files/usr-staging");
                    if (file.exists()) {
                        TermuxInstaller.deleteFolder(file);
                    }
                    byte[] bArr = new byte[8096];
                    ArrayList<Pair> arrayList = new ArrayList(50);
                    Throwable th = null;
                    try {
                        ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(TermuxInstaller.loadZipBytes()));
                        while (true) {
                            try {
                                ZipEntry nextEntry = zipInputStream.getNextEntry();
                                if (nextEntry != null) {
                                    if (nextEntry.getName().equals("SYMLINKS.txt")) {
                                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zipInputStream));
                                        while (true) {
                                            String line = bufferedReader.readLine();
                                            if (line == null) {
                                                break;
                                            }
                                            String[] strArrSplit = line.split("←");
                                            if (strArrSplit.length != 2) {
                                                throw new RuntimeException("Malformed symlink line: " + line);
                                            }
                                            String str = strArrSplit[0];
                                            String str2 = "/data/data/com.fufufu.katrina.backup/files/usr-staging/" + strArrSplit[1];
                                            arrayList.add(Pair.create(str, str2));
                                            TermuxInstaller.ensureDirectoryExists(new File(str2).getParentFile());
                                        }
                                    } else {
                                        String name = nextEntry.getName();
                                        File file2 = new File("/data/data/com.fufufu.katrina.backup/files/usr-staging", name);
                                        boolean zIsDirectory = nextEntry.isDirectory();
                                        TermuxInstaller.ensureDirectoryExists(zIsDirectory ? file2 : file2.getParentFile());
                                        if (zIsDirectory) {
                                            continue;
                                        } else {
                                            try {
                                                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                                                while (true) {
                                                    try {
                                                        int i = zipInputStream.read(bArr);
                                                        if (i == -1) {
                                                            break;
                                                        } else {
                                                            fileOutputStream.write(bArr, 0, i);
                                                        }
                                                    } catch (Throwable th2) {
                                                        th = th2;
                                                        fileOutputStream.close();
                                                        throw th;
                                                    }
                                                }
                                                fileOutputStream.close();
                                                if (name.startsWith("bin/") || name.startsWith("libexec") || name.startsWith("lib/apt/methods")) {
                                                    Os.chmod(file2.getAbsolutePath(), 448);
                                                }
                                            } finally {
                                            }
                                        }
                                    }
                                } else {
                                    if (arrayList.isEmpty()) {
                                        throw new RuntimeException("No SYMLINKS.txt encountered");
                                    }
                                    for (Pair pair : arrayList) {
                                        Os.symlink((String) pair.first, (String) pair.second);
                                    }
                                    if (!file.renameTo(this.val$PREFIX_FILE)) {
                                        throw new RuntimeException("Unable to rename staging folder");
                                    }
                                    this.val$activity.runOnUiThread(this.val$whenDone);
                                    activity = this.val$activity;
                                    final ProgressDialog progressDialog = this.val$progress;
                                    runnable = new Runnable() { // from class: com.termfu.app.TermuxInstaller$1$$ExternalSyntheticLambda3
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            progressDialog.dismiss();
                                        }
                                    };
                                }
                            } finally {
                                zipInputStream.close();
                            }
                        }
                    } finally {
                    }
                } catch (Throwable th3) {
                    Activity activity2 = this.val$activity;
                    final ProgressDialog progressDialog2 = this.val$progress;
                    activity2.runOnUiThread(new Runnable() { // from class: com.termfu.app.TermuxInstaller$1$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            progressDialog2.dismiss();
                        }
                    });
                    throw new RuntimeException(th3);
                }
            } catch (Exception e) {
                Log.e(EmulatorDebug.LOG_TAG, "Bootstrap error", e);
                final Activity activity3 = this.val$activity;
                final Runnable runnable2 = this.val$whenDone;
                activity3.runOnUiThread(new Runnable() { // from class: com.termfu.app.TermuxInstaller$1$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        Activity activity4 = activity3;
                        new AlertDialog.Builder(activity4).setTitle(C0978R.string.termfu_bootstrap_error_title).setMessage(C0978R.string.termfu_bootstrap_error_body).setNegativeButton(C0978R.string.termfu_bootstrap_error_abort, new DialogInterface.OnClickListener() { // from class: com.termfu.app.TermuxInstaller$1$$ExternalSyntheticLambda0
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i2) {
                                TermuxInstaller.C21541.lambda$2(activity4, dialogInterface, i2);
                            }
                        }).setPositiveButton(C0978R.string.termfu_bootstrap_error_try_again, new DialogInterface.OnClickListener() { // from class: com.termfu.app.TermuxInstaller$1$$ExternalSyntheticLambda1
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i2) {
                                TermuxInstaller.C21541.lambda$3(activity4, runnable2, dialogInterface, i2);
                            }
                        }).show();
                    }
                });
                activity = this.val$activity;
                final ProgressDialog progressDialog3 = this.val$progress;
                runnable = new Runnable() { // from class: com.termfu.app.TermuxInstaller$1$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        progressDialog3.dismiss();
                    }
                };
            }
            activity.runOnUiThread(runnable);
        }

        static /* synthetic */ void lambda$2(Activity activity, DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            activity.finish();
        }

        static /* synthetic */ void lambda$3(Activity activity, Runnable runnable, DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            TermuxInstaller.setupIfNeeded(activity, runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void ensureDirectoryExists(File file) {
        if (file.isDirectory() || file.mkdirs()) {
            return;
        }
        throw new RuntimeException("Unable to create directory: " + file.getAbsolutePath());
    }

    public static byte[] loadZipBytes() {
        System.loadLibrary("termfu-bootstrap");
        return getZip();
    }

    static void deleteFolder(File file) throws IOException {
        File[] fileArrListFiles;
        if (file.getCanonicalPath().equals(file.getAbsolutePath()) && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                deleteFolder(file2);
            }
        }
        if (file.delete()) {
            return;
        }
        StringBuilder sb = new StringBuilder("Unable to delete ");
        sb.append(file.isDirectory() ? "directory " : "file ");
        sb.append(file.getAbsolutePath());
        throw new RuntimeException(sb.toString());
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.termfu.app.TermuxInstaller$2] */
    static void setupStorageSymlinks(final Context context) {
        new Thread() { // from class: com.termfu.app.TermuxInstaller.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    File file = new File(TermuxService.HOME_PATH, "storage");
                    if (file.exists()) {
                        try {
                            TermuxInstaller.deleteFolder(file);
                        } catch (IOException e) {
                            Log.e("termfu-storage", "Could not delete old $HOME/storage, " + e.getMessage());
                            return;
                        }
                    }
                    if (!file.mkdirs()) {
                        Log.e("termfu-storage", "Unable to mkdirs() for $HOME/storage");
                        return;
                    }
                    Os.symlink(Environment.getExternalStorageDirectory().getAbsolutePath(), new File(file, "shared").getAbsolutePath());
                    Os.symlink(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), new File(file, "downloads").getAbsolutePath());
                    Os.symlink(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath(), new File(file, "dcim").getAbsolutePath());
                    Os.symlink(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath(), new File(file, "pictures").getAbsolutePath());
                    Os.symlink(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath(), new File(file, "music").getAbsolutePath());
                    Os.symlink(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath(), new File(file, "movies").getAbsolutePath());
                    File[] externalFilesDirs = context.getExternalFilesDirs(null);
                    if (externalFilesDirs != null) {
                        if (externalFilesDirs.length > 1) {
                            for (int i = 1; i < externalFilesDirs.length; i++) {
                                File file2 = externalFilesDirs[i];
                                if (file2 != null) {
                                    Os.symlink(file2.getAbsolutePath(), new File(file, "external-" + i).getAbsolutePath());
                                }
                            }
                        }
                    }
                } catch (Exception e2) {
                    Log.e("termfu-storage", "Error setting up link", e2);
                }
            }
        }.start();
    }
}
