package com.fufufu.katrina.backup;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Property;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;
import com.topjohnwu.superuser.Shell;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final int DURATION = 1000;
    private static final int IMAGE_COUNT = 12;
    private Button btn_getprop;
    private MaterialCardView cv_pbar;
    private ImageView im_01;
    private LinearLayout ln_offline;
    private LinearLayout ln_online;
    private ImageView logo01;
    private ImageView logo02;
    private ImageView logo03;
    private ImageView logo04;
    private ImageView logo05;
    private ImageView logo06;
    private ImageView logo07;
    private ImageView logo08;
    private ImageView logo09;
    private ImageView logo10;
    private ImageView logo11;
    private ImageView logo12;
    private ProgressBar pbar_loading;
    private SharedPreferences pref;
    private SharedPreferences prefuser;
    private SharedPreferences prefversion;
    private Runnable runnableIMAGEANIM;
    private TextView tv_app;
    private TextView tv_not_granted;
    private String s_channel_id = "";
    private String s_splash_loc = "";
    private String s_command = "";
    private String s_commandResult = "";
    private String s_exitCode = "";
    private boolean b_command = false;
    private String s_katrina_day = "";
    private String s_katrina_day2 = "";
    private Handler IMAGEANIM = new Handler();

    public void _ROOT() {
    }

    @Override
    protected void attachBaseContext(android.content.Context base) {
        super.attachBaseContext(LocaleHelper.wrap(base));
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!LocaleHelper.isOnboardingDone(this)) {
            startActivity(new Intent(this, OnboardingActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
            return;
        }
        setContentView(C0978R.layout.main);
        initialize(bundle);
        initializeLogic();
    }

    private void initialize(Bundle bundle) {
        this.ln_offline = (LinearLayout) findViewById(C0978R.id.ln_offline);
        this.ln_online = (LinearLayout) findViewById(C0978R.id.ln_online);
        this.logo01 = (ImageView) findViewById(C0978R.id.logo01);
        this.logo02 = (ImageView) findViewById(C0978R.id.logo02);
        this.logo03 = (ImageView) findViewById(C0978R.id.logo03);
        this.logo04 = (ImageView) findViewById(C0978R.id.logo04);
        this.logo05 = (ImageView) findViewById(C0978R.id.logo05);
        this.logo06 = (ImageView) findViewById(C0978R.id.logo06);
        this.logo07 = (ImageView) findViewById(C0978R.id.logo07);
        this.logo08 = (ImageView) findViewById(C0978R.id.logo08);
        this.logo09 = (ImageView) findViewById(C0978R.id.logo09);
        this.logo10 = (ImageView) findViewById(C0978R.id.logo10);
        this.logo11 = (ImageView) findViewById(C0978R.id.logo11);
        this.logo12 = (ImageView) findViewById(C0978R.id.logo12);
        this.tv_app = (TextView) findViewById(C0978R.id.tv_app);
        this.cv_pbar = (MaterialCardView) findViewById(C0978R.id.cv_pbar);
        this.tv_not_granted = (TextView) findViewById(C0978R.id.tv_not_granted);
        this.btn_getprop = (Button) findViewById(C0978R.id.btn_getprop);
        this.pbar_loading = (ProgressBar) findViewById(C0978R.id.pbar_loading);
        this.im_01 = (ImageView) findViewById(C0978R.id.im_01);
        this.pref = getSharedPreferences("preferences_ui", 0);
        this.prefuser = getSharedPreferences("user_preferences", 0);
        this.prefversion = getSharedPreferences("release_preference", 0);
        this.btn_getprop.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), (Class<?>) ScannerActivity.class));
            }
        });
    }

    private void initializeLogic() {
        if (Build.VERSION.SDK_INT >= 30) {
            getWindow().setDecorFitsSystemWindows(false);
            getWindow().setStatusBarColor(0);
            getWindow().setNavigationBarColor(0);
        } else if (Build.VERSION.SDK_INT >= 19) {
            getWindow().setFlags(512, 512);
        }
        this.s_channel_id = "notif_channel";
        _createNotificationChannel();
        _requestNotificationPermission();
        _setFirstUI();
    }

        public void checkAndRequestRootAccess() {
        if (Shell.getShell().isRoot()) {
            try {
                this.b_command = false;
                Shell.Result resultExec = Shell.cmd("pm grant com.fufufu.katrina.backup android.permission.SYSTEM_ALERT_WINDOW\nappops set com.fufufu.katrina.backup SYSTEM_ALERT_WINDOW allow\nsettings put secure enabled_accessibility_services com.fufufu.katrina.backup/.KatrinaIslandService").exec();
                List<String> out = resultExec.getOut();
                resultExec.getCode();
                this.b_command = resultExec.isSuccess();
                this.s_commandResult = String.join("\n", out);
            } catch (Exception e) {
                e.printStackTrace();
            }
            startActivity(new Intent(getApplicationContext(), (Class<?>) PermissionActivity.class));
            finish();
            return;
        }
        Boolean boolIsAppGrantedRoot = Shell.isAppGrantedRoot();
        if (boolIsAppGrantedRoot == null || !boolIsAppGrantedRoot.booleanValue()) {
            Shell.getShell(new Shell.GetShellCallback() {                 @Override // com.topjohnwu.superuser.Shell.GetShellCallback
                public void onShell(@NonNull Shell shell) {
                    if (shell.isRoot()) {
                        try {
                            MainActivity.this.b_command = false;
                            Shell.Result resultExec2 = Shell.cmd("pm grant com.fufufu.katrina.backup android.permission.SYSTEM_ALERT_WINDOW\nappops set com.fufufu.katrina.backup SYSTEM_ALERT_WINDOW allow\nsettings put secure enabled_accessibility_services com.fufufu.katrina.backup/.KatrinaIslandService").exec();
                            List<String> out2 = resultExec2.getOut();
                            resultExec2.getCode();
                            MainActivity.this.b_command = resultExec2.isSuccess();
                            MainActivity.this.s_commandResult = String.join("\n", out2);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), (Class<?>) PermissionActivity.class));
                        MainActivity.this.finish();
                        return;
                    }
                    MainActivity.this.ln_offline.setVisibility(0);
                    MainActivity.this.ln_online.setVisibility(8);
                    MainActivity.this.tv_not_granted.setText("Harap ijinkan akses root pada magisk\nLalu paksa henti XKatrina dan buka kembali aplikasi");
                    MainActivity.this.btn_getprop.setVisibility(0);
                }
            });
            return;
        }
        try {
            this.b_command = false;
            Shell.Result resultExec2 = Shell.cmd("pm grant com.fufufu.katrina.backup android.permission.SYSTEM_ALERT_WINDOW\nappops set com.fufufu.katrina.backup SYSTEM_ALERT_WINDOW allow\nsettings put secure enabled_accessibility_services com.fufufu.katrina.backup/.KatrinaIslandService").exec();
            List<String> out2 = resultExec2.getOut();
            resultExec2.getCode();
            this.b_command = resultExec2.isSuccess();
            this.s_commandResult = String.join("\n", out2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        startActivity(new Intent(getApplicationContext(), (Class<?>) PermissionActivity.class));
        finish();
    }

    public void _setFirstUI() {
        this.btn_getprop.setVisibility(8);
        try {
            this.prefversion.edit().putString("current", String.valueOf(getPackageManager().getPackageInfo(getPackageName(), 0).versionCode)).commit();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String strConcat = "/data/user/0/".concat(getApplicationContext().getPackageName().concat("/splash.jpg"));
        this.s_splash_loc = strConcat;
        if (FileUtil.isExistFile(strConcat)) {
            this.ln_online.setVisibility(0);
            this.ln_offline.setVisibility(8);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            this.im_01.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(this.s_splash_loc, displayMetrics.widthPixels, displayMetrics.heightPixels));
            new Handler().postDelayed(new Runnable() {                 @Override // java.lang.Runnable
                public void run() {
                    MainActivity.this.checkAndRequestRootAccess();
                }
            }, 2000L);
            return;
        }
        this.ln_online.setVisibility(8);
        this.ln_offline.setVisibility(0);
        if (this.prefuser.getString("emanresu", "").equals("")) {
            this.tv_app.setText("XKatrina");
        } else {
            this.tv_app.setText(this.prefuser.getString("emanresu", ""));
        }
        this.pbar_loading.setProgress(0);
        Runnable runnable = new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.pbar_loading.setProgress(MainActivity.this.pbar_loading.getProgress() + 10);
                if (MainActivity.this.pbar_loading.getProgress() == 100) {
                    MainActivity.this.IMAGEANIM.removeCallbacks(MainActivity.this.runnableIMAGEANIM);
                } else {
                    MainActivity.this.IMAGEANIM.postDelayed(MainActivity.this.runnableIMAGEANIM, 250L);
                }
            }
        };
        this.runnableIMAGEANIM = runnable;
        this.IMAGEANIM.postDelayed(runnable, 0L);
        ImageView[] imageViewArr = {(ImageView) findViewById(C0978R.id.logo01), (ImageView) findViewById(C0978R.id.logo03), (ImageView) findViewById(C0978R.id.logo05), (ImageView) findViewById(C0978R.id.logo07), (ImageView) findViewById(C0978R.id.logo09), (ImageView) findViewById(C0978R.id.logo11), (ImageView) findViewById(C0978R.id.logo02), (ImageView) findViewById(C0978R.id.logo04), (ImageView) findViewById(C0978R.id.logo06), (ImageView) findViewById(C0978R.id.logo09), (ImageView) findViewById(C0978R.id.logo10), (ImageView) findViewById(C0978R.id.logo12)};
        ObjectAnimator[] objectAnimatorArr = new ObjectAnimator[6];
        for (int i = 0; i < 6; i++) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(imageViewArr[i], (Property<View, Float>) View.TRANSLATION_X, -500.0f, 0.0f);
            objectAnimatorArr[i] = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration((i * 150) + 1000);
            objectAnimatorArr[i].setInterpolator(new BounceInterpolator());
        }
        ObjectAnimator[] objectAnimatorArr2 = new ObjectAnimator[6];
        for (int i2 = 6; i2 < 12; i2++) {
            int i3 = i2 - 6;
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(imageViewArr[i2], (Property<View, Float>) View.TRANSLATION_X, 500.0f, 0.0f);
            objectAnimatorArr2[i3] = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration((i2 * 150) + 1000);
            objectAnimatorArr2[i3].setInterpolator(new BounceInterpolator());
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorArr);
        animatorSet.playTogether(objectAnimatorArr2);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {             @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                MainActivity.this.checkAndRequestRootAccess();
            }
        });
        this.pbar_loading.setMax(100);
    }

    public void _createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(this.s_channel_id, "Notification Channel", 3);
            notificationChannel.setDescription("Channel for displaying notifications");
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }

    public void _requestNotificationPermission() {
        NotificationManager notificationManager;
        if (Build.VERSION.SDK_INT < 26 || (notificationManager = (NotificationManager) getSystemService(NotificationManager.class)) == null) {
            return;
        }
        notificationManager.areNotificationsEnabled();
    }

    public void _sendToFloat(String str) {
        Intent intent = new Intent(this, (Class<?>) KatrinaIslandService.class);
        intent.setAction(KatrinaIslandService.ACTION_SHOW_TEXT);
        intent.putExtra(KatrinaIslandService.EXTRA_TEXT, str);
        startService(intent);
    }

    @Deprecated
    public void showMessage(String str) {
        Toast.makeText(getApplicationContext(), str, 0).show();
    }

    @Deprecated
    public int getLocationX(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr[0];
    }

    @Deprecated
    public int getLocationY(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr[1];
    }

    @Deprecated
    public int getRandom(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    @Deprecated
    public ArrayList<Double> getCheckedItemPositionsToArray(ListView listView) {
        ArrayList<Double> arrayList = new ArrayList<>();
        SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
        for (int i = 0; i < checkedItemPositions.size(); i++) {
            if (checkedItemPositions.valueAt(i)) {
                arrayList.add(Double.valueOf(checkedItemPositions.keyAt(i)));
            }
        }
        return arrayList;
    }

    @Deprecated
    public float getDip(int i) {
        return TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayWidthPixels() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getDisplayHeightPixels() {
        return getResources().getDisplayMetrics().heightPixels;
    }
}