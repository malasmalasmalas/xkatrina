package com.termfu.app;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.Vibrator;

/* JADX INFO: loaded from: classes91.dex */
public class BellUtil {
    private static final long DURATION = 50;
    private static final long MIN_PAUSE = 150;
    private static BellUtil instance;
    private static final Object lock = new Object();
    private final Runnable bellRunnable;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private long lastBell = 0;

    public static BellUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new BellUtil((Vibrator) context.getApplicationContext().getSystemService("vibrator"));
                }
            }
        }
        return instance;
    }

    private BellUtil(final Vibrator vibrator) {
        this.bellRunnable = new Runnable() { // from class: com.termfu.app.BellUtil.1
            @Override // java.lang.Runnable
            public void run() {
                Vibrator vibrator2 = vibrator;
                if (vibrator2 != null) {
                    vibrator2.vibrate(BellUtil.DURATION);
                }
            }
        };
    }

    public synchronized void doBell() {
        long jNow = now();
        long j = jNow - this.lastBell;
        if (j >= 0) {
            if (j < 150) {
                this.handler.postDelayed(this.bellRunnable, 150 - j);
                this.lastBell += 150;
            } else {
                this.bellRunnable.run();
                this.lastBell = jNow;
            }
        }
    }

    private long now() {
        return SystemClock.uptimeMillis();
    }
}
