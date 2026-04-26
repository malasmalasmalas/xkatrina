package com.termfu.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.fufufu.katrina.backup.C0978R;
import com.termfu.app.TermuxService;
import com.topjohnwu.superuser.nio.FileSystemManager;
import java.io.File;

/* JADX INFO: loaded from: classes91.dex */
public final class TermuxWidgetProvider extends AppWidgetProvider {
    public static final String ACTION_EXECUTE = "com.termfu.service_execute";
    public static final String EXTRA_CLICKED_FILE = "com.termfu.widgets.EXTRA_CLICKED_FILE";
    private static final String LIST_ITEM_CLICKED_ACTION = "com.termfu.widgets.LIST_ITEM_CLICKED_ACTION";
    private static final String REFRESH_WIDGET_ACTION = "com.termfu.widgets.REFRESH_WIDGET_ACTION";
    public static final String TERMUX_SERVICE = "com.termfu.app.TermuxService";

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        for (int i : iArr) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), C0978R.layout.termfu_widget_layout);
            remoteViews.setEmptyView(C0978R.id.widget_list, C0978R.id.empty_view);
            Intent intent = new Intent(context, (Class<?>) TermuxWidgetService.class);
            intent.putExtra("appWidgetId", i);
            intent.setData(Uri.parse(intent.toUri(1)));
            remoteViews.setRemoteAdapter(C0978R.id.widget_list, intent);
            Intent intent2 = new Intent(context, (Class<?>) TermuxWidgetProvider.class);
            intent2.setAction(REFRESH_WIDGET_ACTION);
            intent2.putExtra("appWidgetId", i);
            intent2.setData(Uri.parse(intent2.toUri(1)));
            remoteViews.setOnClickPendingIntent(C0978R.id.refresh_button, PendingIntent.getBroadcast(context, 0, intent2, FileSystemManager.MODE_CREATE));
            Intent intent3 = new Intent(context, (Class<?>) TermuxWidgetProvider.class);
            intent3.setAction(LIST_ITEM_CLICKED_ACTION);
            intent3.putExtra("appWidgetId", i);
            intent.setData(Uri.parse(intent.toUri(1)));
            remoteViews.setPendingIntentTemplate(C0978R.id.widget_list, PendingIntent.getBroadcast(context, 0, intent3, FileSystemManager.MODE_CREATE));
            appWidgetManager.updateAppWidget(i, remoteViews);
        }
    }

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        int iHashCode = action.hashCode();
        if (iHashCode != 88383294) {
            if (iHashCode == 302547944 && action.equals(REFRESH_WIDGET_ACTION)) {
                AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(intent.getIntExtra("appWidgetId", 0), C0978R.id.widget_list);
                Toast toastMakeText = Toast.makeText(context, C0978R.string.termfu_scripts_reloaded, 0);
                toastMakeText.setGravity(17, 0, 0);
                toastMakeText.show();
                return;
            }
            return;
        }
        if (action.equals(LIST_ITEM_CLICKED_ACTION)) {
            String stringExtra = intent.getStringExtra(EXTRA_CLICKED_FILE);
            File file = new File(stringExtra);
            if (file.isDirectory()) {
                return;
            }
            ensureFileReadableAndExecutable(file);
            Intent intent2 = new Intent("com.termfu.service_execute", new Uri.Builder().scheme("com.termfu.file").path(stringExtra).build());
            intent2.setClassName("com.fufufu.katrina.backup", TERMUX_SERVICE);
            if (file.getParentFile().getName().equals("tasks")) {
                intent2.putExtra(TermuxService.EXTRA_EXECUTE_IN_BACKGROUND, true);
                Toast toastMakeText2 = Toast.makeText(context, "Task executed: " + file.getName(), 0);
                toastMakeText2.setGravity(48, 0, 0);
                toastMakeText2.show();
            }
            startTermuxService(context, intent2);
        }
    }

    static void startTermuxService(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    static void ensureFileReadableAndExecutable(File file) {
        if (!file.canRead()) {
            file.setReadable(true);
        }
        if (file.canExecute()) {
            return;
        }
        file.setExecutable(true);
    }
}
