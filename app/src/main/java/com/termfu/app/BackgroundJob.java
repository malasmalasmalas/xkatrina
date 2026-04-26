package com.termfu.app;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes91.dex */
public final class BackgroundJob {
    private static final String LOG_TAG = "termfu-task";
    Process mProcess = null;

    public BackgroundJob(String str, String str2, String[] strArr, TermuxService termuxService) {
        this(str, str2, strArr, termuxService, null);
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.termfu.app.BackgroundJob$2] */
    public BackgroundJob(String str, String str2, String[] strArr, final TermuxService termuxService, final PendingIntent pendingIntent) {
        String str3 = str;
        String[] strArrBuildEnvironment = buildEnvironment(false, str);
        str3 = str3 == null ? TermuxService.HOME_PATH : str3;
        String[] strArr2 = setupProcessArgs(str2, strArr);
        final String string = Arrays.toString(strArr2);
        try {
            Process processExec = Runtime.getRuntime().exec(strArr2, strArrBuildEnvironment, new File(str3));
            this.mProcess = processExec;
            final int pid = getPid(processExec);
            final Bundle bundle = new Bundle();
            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();
            final Thread thread = new Thread() { // from class: com.termfu.app.BackgroundJob.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(BackgroundJob.this.mProcess.getErrorStream(), StandardCharsets.UTF_8));
                    while (true) {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                return;
                            }
                            StringBuilder sb3 = sb2;
                            sb3.append(line);
                            sb3.append('\n');
                            Log.i(BackgroundJob.LOG_TAG, "[" + pid + "] stderr: " + line);
                        } catch (IOException unused) {
                            return;
                        }
                    }
                }
            };
            thread.start();
            new Thread() { // from class: com.termfu.app.BackgroundJob.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Log.i(BackgroundJob.LOG_TAG, "[" + pid + "] starting: " + string);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(BackgroundJob.this.mProcess.getInputStream(), StandardCharsets.UTF_8));
                    while (true) {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            Log.i(BackgroundJob.LOG_TAG, "[" + pid + "] stdout: " + line);
                            StringBuilder sb3 = sb;
                            sb3.append(line);
                            sb3.append('\n');
                        } catch (IOException e) {
                            Log.e(BackgroundJob.LOG_TAG, "Error reading output", e);
                        }
                    }
                    try {
                        int iWaitFor = BackgroundJob.this.mProcess.waitFor();
                        termuxService.onBackgroundJobExited(BackgroundJob.this);
                        if (iWaitFor == 0) {
                            Log.i(BackgroundJob.LOG_TAG, "[" + pid + "] exited normally");
                        } else {
                            Log.w(BackgroundJob.LOG_TAG, "[" + pid + "] exited with code: " + iWaitFor);
                        }
                        bundle.putString("stdout", sb.toString());
                        bundle.putInt("exitCode", iWaitFor);
                        thread.join();
                        bundle.putString("stderr", sb2.toString());
                        Intent intent = new Intent();
                        intent.putExtra("result", bundle);
                        PendingIntent pendingIntent2 = pendingIntent;
                        if (pendingIntent2 != null) {
                            pendingIntent2.send(termuxService.getApplicationContext(), -1, intent);
                        }
                    } catch (PendingIntent.CanceledException | InterruptedException unused) {
                    }
                }
            }.start();
        } catch (IOException e) {
            this.mProcess = null;
            Log.e(LOG_TAG, "Failed running background job: " + string, e);
        }
    }

    private static void addToEnvIfPresent(List<String> list, String str) {
        String str2 = System.getenv(str);
        if (str2 != null) {
            list.add(String.valueOf(str) + "=" + str2);
        }
    }

    static String[] buildEnvironment(boolean z, String str) {
        new File(TermuxService.HOME_PATH).mkdirs();
        if (str == null) {
            str = TermuxService.HOME_PATH;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("TERMUX_VERSION=0.108");
        arrayList.add("TERM=xterm-256color");
        arrayList.add("COLORTERM=truecolor");
        arrayList.add("HOME=/data/data/com.fufufu.katrina.backup/files/home");
        arrayList.add("PREFIX=/data/data/com.fufufu.katrina.backup");
        arrayList.add("BOOTCLASSPATH=" + System.getenv("BOOTCLASSPATH"));
        arrayList.add("ANDROID_ROOT=" + System.getenv("ANDROID_ROOT"));
        arrayList.add("ANDROID_DATA=" + System.getenv("ANDROID_DATA"));
        arrayList.add("EXTERNAL_STORAGE=" + System.getenv("EXTERNAL_STORAGE"));
        addToEnvIfPresent(arrayList, "ANDROID_ART_ROOT");
        addToEnvIfPresent(arrayList, "DEX2OATBOOTCLASSPATH");
        addToEnvIfPresent(arrayList, "ANDROID_I18N_ROOT");
        addToEnvIfPresent(arrayList, "ANDROID_RUNTIME_ROOT");
        addToEnvIfPresent(arrayList, "ANDROID_TZDATA_ROOT");
        if (z) {
            arrayList.add("PATH= " + System.getenv("PATH"));
        } else {
            arrayList.add("LANG=en_US.UTF-8");
            arrayList.add("PATH=/system/bin");
            arrayList.add("PWD=" + str);
            arrayList.add("TMPDIR=/data/data/com.fufufu.katrina.backup/tmp");
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static int getPid(Process process) {
        try {
            Field declaredField = process.getClass().getDeclaredField("pid");
            declaredField.setAccessible(true);
            try {
                return declaredField.getInt(process);
            } finally {
                declaredField.setAccessible(false);
            }
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x005a, code lost:
    
        r2 = r5.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0064, code lost:
    
        if (r2.startsWith("/usr") != false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006c, code lost:
    
        if (r2.startsWith("/bin") == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006e, code lost:
    
        r2 = r2.split("/");
        r2 = "/system/bin/" + r2[r2.length - 1];
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008d A[EDGE_INSN: B:22:0x0043->B:39:0x008d BREAK  A[LOOP:0: B:21:0x0041->B:36:0x0087]] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static String[] setupProcessArgs(String str, String[] strArr) {
        String str2 = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            try {
                byte[] bArr = new byte[256];
                int i = fileInputStream.read(bArr);
                if (i > 4) {
                    byte b = bArr[0];
                    if (b == 127 && bArr[1] == 69 && bArr[2] == 76 && bArr[3] == 70) {
                        str2 = null;
                    } else if (b == 35 && bArr[1] == 33) {
                        StringBuilder sb = new StringBuilder();
                        for (int i2 = 2; i2 < i; i2++) {
                            char c = (char) bArr[i2];
                            if (c == ' ' || c == '\n') {
                                if (sb.length() != 0) break;
                            } else {
                                sb.append(c);
                            }
                        }
                        str2 = sb.length() > 0 ? sb.toString() : null;
                    } else {
                        str2 = "/system/bin/sh";
                    }
                }
                fileInputStream.close();
            } catch (IOException e) {
                try { fileInputStream.close(); } catch (IOException ignored) {}
            }
        } catch (IOException unused) {
        }
        ArrayList arrayList = new ArrayList();
        if (str2 != null) {
            arrayList.add(str2);
        }
        arrayList.add(str);
        if (strArr != null) {
            Collections.addAll(arrayList, strArr);
        }
        return (String[]) arrayList.toArray(new String[0]);
    }
}
