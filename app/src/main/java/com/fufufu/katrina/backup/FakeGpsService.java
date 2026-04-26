package com.fufufu.katrina.backup;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.location.provider.ProviderProperties;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.core.app.NotificationCompat;

/**
 * Foreground service yang inject mock GPS location secara periodik
 * via LocationManager.setTestProviderLocation.
 *
 * Memerlukan: aplikasi ini diset sebagai "Mock location app" di
 * Developer Options -> Select mock location app.
 */
public class FakeGpsService extends Service {
    public static final String ACTION_START = "com.fufufu.katrina.backup.FAKE_GPS_START";
    public static final String ACTION_STOP = "com.fufufu.katrina.backup.FAKE_GPS_STOP";
    public static final String EXTRA_LAT = "lat";
    public static final String EXTRA_LON = "lon";
    public static final String PREFS = "fake_gps_prefs";
    public static final String KEY_RUNNING = "running";
    public static final String KEY_LAT = "lat";
    public static final String KEY_LON = "lon";
    private static final String CHANNEL_ID = "fake_gps_channel";
    private static final int NOTIF_ID = 9412;

    private LocationManager lm;
    private Thread worker;
    private volatile boolean running;
    private volatile double lat;
    private volatile double lon;

    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
    public void onCreate() {
        super.onCreate();
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ensureChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) { stopSelf(); return START_NOT_STICKY; }
        String action = intent.getAction();
        if (ACTION_STOP.equals(action)) {
            stopMocking();
            stopForeground(true);
            stopSelf();
            return START_NOT_STICKY;
        }
        SharedPreferences sp = getSharedPreferences(PREFS, MODE_PRIVATE);
        lat = intent.getDoubleExtra(EXTRA_LAT, Double.longBitsToDouble(sp.getLong(KEY_LAT, Double.doubleToLongBits(-6.2))));
        lon = intent.getDoubleExtra(EXTRA_LON, Double.longBitsToDouble(sp.getLong(KEY_LON, Double.doubleToLongBits(106.8))));
        sp.edit().putBoolean(KEY_RUNNING, true)
                .putLong(KEY_LAT, Double.doubleToLongBits(lat))
                .putLong(KEY_LON, Double.doubleToLongBits(lon))
                .apply();

        startForeground(NOTIF_ID, buildNotification());
        startMocking();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        stopMocking();
        getSharedPreferences(PREFS, MODE_PRIVATE).edit().putBoolean(KEY_RUNNING, false).apply();
        super.onDestroy();
    }

    private void ensureChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (nm.getNotificationChannel(CHANNEL_ID) == null) {
                NotificationChannel c = new NotificationChannel(CHANNEL_ID, getString(C0978R.string.fakegps_title), NotificationManager.IMPORTANCE_LOW);
                nm.createNotificationChannel(c);
            }
        }
    }

    private Notification buildNotification() {
        Intent stop = new Intent(this, FakeGpsService.class).setAction(ACTION_STOP);
        int piFlags = PendingIntent.FLAG_UPDATE_CURRENT | (Build.VERSION.SDK_INT >= 23 ? PendingIntent.FLAG_IMMUTABLE : 0);
        PendingIntent piStop = PendingIntent.getService(this, 0, stop, piFlags);
        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(C0978R.drawable.ic_tool_fakegps)
                .setContentTitle(getString(C0978R.string.fakegps_running_notif_title))
                .setContentText(String.format("%.6f, %.6f", lat, lon))
                .addAction(0, getString(C0978R.string.fakegps_notif_stop), piStop)
                .setOngoing(true)
                .build();
    }

    private void setupProvider(String name) {
        try { lm.removeTestProvider(name); } catch (Exception ignored) {}
        try {
            if (Build.VERSION.SDK_INT >= 31) {
                ProviderProperties props = new ProviderProperties.Builder()
                        .setHasNetworkRequirement(false)
                        .setHasSatelliteRequirement(false)
                        .setHasCellRequirement(false)
                        .setPowerUsage(ProviderProperties.POWER_USAGE_LOW)
                        .setAccuracy(ProviderProperties.ACCURACY_FINE)
                        .build();
                lm.addTestProvider(name, props);
            } else {
                lm.addTestProvider(name, false, false, false, false, true, true, true, 1, 1);
            }
            lm.setTestProviderEnabled(name, true);
        } catch (SecurityException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pushLocation(String provider, double la, double lo) {
        try {
            Location loc = new Location(provider);
            loc.setLatitude(la);
            loc.setLongitude(lo);
            loc.setAltitude(10);
            loc.setAccuracy(1.0f);
            loc.setTime(System.currentTimeMillis());
            loc.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
            if (Build.VERSION.SDK_INT >= 26) {
                loc.setBearingAccuracyDegrees(0.1f);
                loc.setVerticalAccuracyMeters(0.1f);
                loc.setSpeedAccuracyMetersPerSecond(0.01f);
            }
            lm.setTestProviderLocation(provider, loc);
        } catch (SecurityException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startMocking() {
        setupProvider(LocationManager.GPS_PROVIDER);
        setupProvider(LocationManager.NETWORK_PROVIDER);
        running = true;
        worker = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    pushLocation(LocationManager.GPS_PROVIDER, lat, lon);
                    pushLocation(LocationManager.NETWORK_PROVIDER, lat, lon);
                    try { Thread.sleep(1000L); } catch (InterruptedException e) { return; }
                }
            }
        }, "FakeGpsWorker");
        worker.start();
    }

    private void stopMocking() {
        running = false;
        if (worker != null) worker.interrupt();
        try { lm.removeTestProvider(LocationManager.GPS_PROVIDER); } catch (Exception ignored) {}
        try { lm.removeTestProvider(LocationManager.NETWORK_PROVIDER); } catch (Exception ignored) {}
    }

    public static boolean isRunning(Context ctx) {
        return ctx.getSharedPreferences(PREFS, MODE_PRIVATE).getBoolean(KEY_RUNNING, false);
    }
}
