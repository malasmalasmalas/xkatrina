package com.fufufu.katrina.backup;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

public class ScreenMonitorService extends Service {
    private BroadcastReceiver screenOnReceiver = new BroadcastReceiver() {         @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            return;
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }
}
