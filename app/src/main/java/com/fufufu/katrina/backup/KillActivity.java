package com.fufufu.katrina.backup;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import com.fufufu.katrina.backup.ProcessItem;
import com.fufufu.katrina.backup.Statics;
import java.util.Timer;
import java.util.TimerTask;

public class KillActivity extends Activity {
    private static boolean isActive = false;
    private static InternalBroadcastReceiver receiver;
    private Timer _timer = new Timer();
    private SharedPreferences prefkill;
    private TimerTask timer_command;
    private Vibrator vibrator;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.vibrator = (Vibrator) getSystemService("vibrator");
        SharedPreferences sharedPreferences = getSharedPreferences("katrina_kill_status", 0);
        this.prefkill = sharedPreferences;
        sharedPreferences.edit().putString("kill_status", "").commit();
        Statics.PACKAGE_NAME = getPackageName();
        setInternalReceiver(true);
        TimerTask timerTask = new TimerTask() {             @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                KillActivity.this.runOnUiThread(new Runnable() {                     @Override // java.lang.Runnable
                    public void run() {
                        if (KillActivity.this.prefkill.getString("kill_status", "").equals("finish")) {
                            KillActivity.this.prefkill.edit().putString("kill_status", "").commit();
                            KillActivity.this.vibrator.vibrate(100L);
                            KillActivity.this.timer_command.cancel();
                            KillActivity.this.finish();
                        }
                    }
                });
            }
        };
        this.timer_command = timerTask;
        this._timer.scheduleAtFixedRate(timerTask, 0L, 100L);
        SendBroadcast_ForceStopItems(ProcessItem.ApplicationTypes.AllApps);
    }

    @Override // android.app.Activity
    public void onResume() {
        Statics.LogD("KillActivity -> onResume");
        isActive = true;
        setInternalReceiver(true);
        super.onResume();
    }

    @Override // android.app.Activity
    public void onPause() {
        Statics.LogD("KillActivity -> onPause");
        setInternalReceiver(false);
        super.onPause();
    }

    @Override // android.app.Activity
    public void onStop() {
        Statics.LogD("KillActivity -> onStop");
        isActive = false;
        setInternalReceiver(false);
        super.onStop();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        Statics.LogD("KillActivity -> onDestroy");
        setInternalReceiver(false);
        super.onDestroy();
    }

    public void ShowToast(String str) {
        Boast.showText(this, str);
    }

    private void SentBroadcast_ShowToast(String str) {
        Intent intent = new Intent();
        intent.setAction(Statics.ACTION.ShowToast);
        intent.putExtra(Statics.ACTION.ShowToast_Message, str);
        sendBroadcast(intent);
    }

    public void SendBroadcast_ForceStopItems(ProcessItem.ApplicationTypes applicationTypes) {
        Intent intent = new Intent();
        intent.setAction(Statics.ACTION.ForceStopItems);
        intent.putExtra(Statics.ACTION.ForceStopItems_AppType, applicationTypes);
        sendBroadcast(intent);
    }

    private void setInternalReceiver(Boolean bool) {
        if (bool.booleanValue()) {
            if (receiver == null) {
                receiver = new InternalBroadcastReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(Statics.ACTION.ForceStopItems);
                intentFilter.addAction(Statics.ACTION.ShowToast);
                registerReceiver(receiver, intentFilter);
                return;
            }
            return;
        }
        InternalBroadcastReceiver internalBroadcastReceiver = receiver;
        if (internalBroadcastReceiver != null) {
            try {
                unregisterReceiver(internalBroadcastReceiver);
                receiver = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class InternalBroadcastReceiver extends BroadcastReceiver {
        public InternalBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Statics.LogD("KillActivity -> InternalBroadcastReceiver -> onReceive -> " + intent.getAction());
            String action = intent.getAction();
            Bundle extras = intent.getExtras();
            if (action.equals(Statics.ACTION.ForceStopItems)) {
                ProcessItem.ApplicationTypes applicationTypes = (ProcessItem.ApplicationTypes) extras.get(Statics.ACTION.ForceStopItems_AppType);
                String string = extras.getString(Statics.ACTION.ForceStopItems_ProcessItems);
                if (applicationTypes != null) {
                    Statics.ForceStopItems(context, applicationTypes, (Boolean) true, (Boolean) false);
                    return;
                } else {
                    if (string != null) {
                        Statics.ForceStopItems(context, string, (Boolean) false, (Boolean) true);
                        return;
                    }
                    return;
                }
            }
            if (action.equals(Statics.ACTION.ShowToast)) {
                KillActivity.this.ShowToast(extras.getString(Statics.ACTION.ShowToast_Message));
            }
        }
    }
}