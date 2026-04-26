package com.fufufu.katrina.backup;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.android.material.color.DynamicColors;

public class XKatrinaApplication extends Application {
    private static Context mApplicationContext;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public static Context getContext() {
        return mApplicationContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.wrap(base));
    }

    @Override
    public void onCreate() {
        mApplicationContext = getApplicationContext();
        this.uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler((thread, th) -> {
            Intent intent = new Intent(XKatrinaApplication.this.getApplicationContext(), MaterialDebugActivity.class);
            intent.setFlags(32768);
            intent.putExtra("mode", "crash");
            intent.putExtra("error", Log.getStackTraceString(th));
            intent.putExtra("title", "XKatrina Crash");
            ((AlarmManager) XKatrinaApplication.this.getSystemService(NotificationCompat.CATEGORY_ALARM))
                    .set(2, 1000L, PendingIntent.getActivity(XKatrinaApplication.this.getApplicationContext(), 11111, intent, 1073741824));
            Process.killProcess(Process.myPid());
            System.exit(1);
        });
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}