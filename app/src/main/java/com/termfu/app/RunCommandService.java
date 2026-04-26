package com.termfu.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import com.fufufu.katrina.backup.C0978R;
import com.termux.terminal.EmulatorDebug;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/* JADX INFO: loaded from: classes91.dex */
public class RunCommandService extends Service {
    private static final String NOTIFICATION_CHANNEL_ID = "termfu_run_command_notification_channel";
    private static final int NOTIFICATION_ID = 1338;
    public static final String RUN_COMMAND_ACTION = "com.termfu.RUN_COMMAND";
    public static final String RUN_COMMAND_ARGUMENTS = "com.termfu.RUN_COMMAND_ARGUMENTS";
    public static final String RUN_COMMAND_BACKGROUND = "com.termfu.RUN_COMMAND_BACKGROUND";
    public static final String RUN_COMMAND_PATH = "com.termfu.RUN_COMMAND_PATH";
    public static final String RUN_COMMAND_WORKDIR = "com.termfu.RUN_COMMAND_WORKDIR";
    private final IBinder mBinder = new LocalBinder();

    class LocalBinder extends Binder {
        public final RunCommandService service;

        LocalBinder() {
            this.service = RunCommandService.this;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        runStartForeground();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        runStartForeground();
        if (allowExternalApps() && RUN_COMMAND_ACTION.equals(intent.getAction())) {
            Intent intent2 = new Intent("com.termfu.service_execute", new Uri.Builder().scheme("com.termfu.file").path(parsePath(intent.getStringExtra(RUN_COMMAND_PATH))).build());
            intent2.setClass(this, TermuxService.class);
            intent2.putExtra(TermuxService.EXTRA_ARGUMENTS, intent.getStringArrayExtra(RUN_COMMAND_ARGUMENTS));
            intent2.putExtra(TermuxService.EXTRA_CURRENT_WORKING_DIRECTORY, parsePath(intent.getStringExtra(RUN_COMMAND_WORKDIR)));
            intent2.putExtra(TermuxService.EXTRA_EXECUTE_IN_BACKGROUND, intent.getBooleanExtra(RUN_COMMAND_BACKGROUND, false));
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(intent2);
            } else {
                startService(intent2);
            }
        }
        runStopForeground();
        return 2;
    }

    private void runStartForeground() {
        if (Build.VERSION.SDK_INT >= 26) {
            setupNotificationChannel();
            startForeground(NOTIFICATION_ID, buildNotification());
        }
    }

    private void runStopForeground() {
        if (Build.VERSION.SDK_INT >= 26) {
            stopForeground(true);
        }
    }

    private Notification buildNotification() {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(((Object) getText(C0978R.string.termfu_application_name)) + " Run Command");
        builder.setSmallIcon(C0978R.drawable.termfu_ic_service_notification);
        builder.setPriority(-1);
        builder.setShowWhen(false);
        builder.setColor(-10453621);
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        }
        return builder.build();
    }

    private void setupNotificationChannel() {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Termux Run Command", 2));
    }

    private boolean allowExternalApps() {
        File file = new File("/data/data/com.fufufu.katrina.backup/files/home/.termfu/termfu.properties");
        if (!file.exists()) {
            file = new File("/data/data/com.fufufu.katrina.backup/files/home/.config/termfu/termfu.properties");
        }
        Properties properties = new Properties();
        try {
            if (file.isFile() && file.canRead()) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        properties.load(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
                        fileInputStream.close();
                    } catch (Throwable th) {
                        fileInputStream.close();
                        throw th;
                    }
                } finally {
                }
            }
        } catch (Exception e) {
            Log.e(EmulatorDebug.LOG_TAG, "Error loading props", e);
        }
        return properties.getProperty("allow-external-apps", "false").equals("true");
    }

    private String parsePath(String str) {
        return (str == null || str.isEmpty()) ? str : str.replaceAll("^\\$PREFIX\\/", "/data/data/com.fufufu.katrina.backup/").replaceAll("^~\\/", "/data/data/com.fufufu.katrina.backup/files/home/");
    }
}
