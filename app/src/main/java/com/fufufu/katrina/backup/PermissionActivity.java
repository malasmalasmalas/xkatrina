package com.fufufu.katrina.backup;

import android.accessibilityservice.AccessibilityService;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import io.noties.markwon.Markwon;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/* JADX INFO: loaded from: classes90.dex */
public class PermissionActivity extends AppCompatActivity {
    private static final int ACCESSIBILITY_REQUEST_CODE = 100;
    private static final int OVERLAY_PERMISSION_REQUEST_CODE = 1000;
    private AlertDialog AGREE;
    private ValueAnimator anim;
    private ValueAnimator anim1;
    private ObjectAnimator anim2;
    private ObjectAnimator anim3;
    private ValueAnimator anim4;
    private ValueAnimator anim5;
    private Button btn_access;
    private Button btn_notif;
    private Button btn_overlay;
    private LinearLayout ln_base_island;
    private LinearLayout ln_image1;
    private LinearLayout ln_image2;
    private LinearLayout ln_image_bg;
    private LinearLayout ln_island_img;
    private LinearLayout ln_permission_access;
    private LinearLayout ln_permission_notif;
    private LinearLayout ln_permission_overlay;
    private SharedPreferences pref;
    private TextView tv_01;
    private TextView tv_02;
    private TextView tv_03;
    private TextView tv_katrina;
    private String s_channel_id = "";
    private Intent i_auth = new Intent();
    int width_value = 0;
    int margin_value = 0;
    int new_width_value = 0;
    int new_margin_value = 0;

    public void _EXTRA() {
    }

    @Override
    protected void attachBaseContext(android.content.Context base) {
        super.attachBaseContext(LocaleHelper.wrap(base));
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0978R.layout.permission);
        initialize(bundle);
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == -1 || ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == -1) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 1000);
        } else {
            initializeLogic();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1000) {
            initializeLogic();
        }
    }

    private void initialize(Bundle bundle) {
        this.ln_base_island = (LinearLayout) findViewById(C0978R.id.ln_base_island);
        this.ln_image_bg = (LinearLayout) findViewById(C0978R.id.ln_image_bg);
        this.ln_permission_notif = (LinearLayout) findViewById(C0978R.id.ln_permission_notif);
        this.ln_permission_overlay = (LinearLayout) findViewById(C0978R.id.ln_permission_overlay);
        this.ln_permission_access = (LinearLayout) findViewById(C0978R.id.ln_permission_access);
        this.ln_image1 = (LinearLayout) findViewById(C0978R.id.ln_image1);
        this.ln_image2 = (LinearLayout) findViewById(C0978R.id.ln_image2);
        this.ln_island_img = (LinearLayout) findViewById(C0978R.id.ln_island_img);
        this.tv_katrina = (TextView) findViewById(C0978R.id.tv_katrina);
        this.tv_03 = (TextView) findViewById(C0978R.id.tv_03);
        this.btn_notif = (Button) findViewById(C0978R.id.btn_notif);
        this.tv_01 = (TextView) findViewById(C0978R.id.tv_01);
        this.btn_overlay = (Button) findViewById(C0978R.id.btn_overlay);
        this.tv_02 = (TextView) findViewById(C0978R.id.tv_02);
        this.btn_access = (Button) findViewById(C0978R.id.btn_access);
        this.pref = getSharedPreferences("preferences_ui", 0);
        this.btn_notif.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PermissionActivity.this.startActivity(new Intent("android.settings.APP_NOTIFICATION_SETTINGS").putExtra("android.provider.extra.APP_PACKAGE", PermissionActivity.this.getPackageName()));
            }
        });
        this.btn_overlay.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PermissionActivity.this.checkOverlayPermission();
            }
        });
        this.btn_access.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PermissionActivity.this.initializeAccessibilityService();
            }
        });
    }

    private void initializeLogic() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(0);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.setNavigationBarColor(0);
        }
        getWindow().getDecorView().setSystemUiVisibility(8208);
        this.s_channel_id = "notif_channel";
        _createNotificationChannel();
        _firstSetUI();
        if (this.pref.getString("agreement", "").equals("")) {
            _showAgreement();
        } else {
            _checkPermission();
        }
        _playKatrinaAnimation();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        _checkPermission();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkOverlayPermission() {
        if (Build.VERSION.SDK_INT < 23 || Settings.canDrawOverlays(this)) {
            return;
        }
        startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())), 1000);
    }

    private boolean checkDrawOverlay() {
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.canDrawOverlays(this);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeAccessibilityService() {
        if (isAccessibilityServiceEnabled(getApplicationContext(), KatrinaIslandService.class)) {
            return;
        }
        startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
    }

    private boolean isAccessibilityServiceEnabled(Context context, Class<? extends AccessibilityService> cls) {
        ComponentName componentName = new ComponentName(context, cls);
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services");
        TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
        if (string == null) {
            return false;
        }
        simpleStringSplitter.setString(string);
        while (simpleStringSplitter.hasNext()) {
            ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(simpleStringSplitter.next());
            if (componentNameUnflattenFromString != null && componentNameUnflattenFromString.equals(componentName)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkAccessibilityServiceEnabled() {
        ComponentName componentName = new ComponentName(this, (Class<?>) KatrinaIslandService.class);
        String string = Settings.Secure.getString(getContentResolver(), "enabled_accessibility_services");
        return string != null && string.contains(componentName.flattenToString());
    }

    private void requestAccessibilityService() {
        startActivityForResult(new Intent("android.settings.ACCESSIBILITY_SETTINGS"), 100);
    }

    private void startFloatingService() {
        startService(new Intent(this, (Class<?>) KatrinaIslandService.class));
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            boolean zIsAccessibilityServiceEnabled = isAccessibilityServiceEnabled(getApplicationContext(), KatrinaIslandService.class);
            boolean zCheckDrawOverlay = checkDrawOverlay();
            if (zIsAccessibilityServiceEnabled && zCheckDrawOverlay) {
                startFloatingService();
            }
        }
    }

    public void _firstSetUI() {
        this.tv_katrina.setAlpha(0.0f);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadii(new float[]{60.0f, 60.0f, 60.0f, 60.0f, 0.0f, 0.0f, 0.0f, 0.0f});
        gradientDrawable.setStroke(0, ViewCompat.MEASURED_STATE_MASK);
        gradientDrawable.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.ln_image1.setBackground(gradientDrawable);
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(0);
        gradientDrawable2.setCornerRadii(new float[]{60.0f, 60.0f, 60.0f, 60.0f, 0.0f, 0.0f, 0.0f, 0.0f});
        gradientDrawable2.setColor(-1);
        this.ln_image2.setBackground(gradientDrawable2);
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setShape(0);
        gradientDrawable3.setCornerRadius(100.0f);
        gradientDrawable3.setStroke(0, ViewCompat.MEASURED_STATE_MASK);
        gradientDrawable3.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.ln_island_img.setBackground(gradientDrawable3);
    }

    public void _playKatrinaAnimation() {
        this.tv_katrina.setText(getString(C0978R.string.permission_island_intro));
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.ln_island_img.getMeasuredWidth(), 600);
        this.anim = valueAnimatorOfInt;
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.14
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.width = iIntValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim.setDuration(800L);
        this.anim.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim.start();
        ValueAnimator valueAnimatorOfInt2 = ValueAnimator.ofInt(this.ln_island_img.getMeasuredHeight(), 125);
        this.anim1 = valueAnimatorOfInt2;
        valueAnimatorOfInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.15
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.height = iIntValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim1.setDuration(1000L);
        this.anim1.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim1.addListener(new C095316());
        this.anim1.start();
    }

    /* JADX INFO: renamed from: com.fufufu.katrina.backup.PermissionActivity$16 */
    class C095316 extends AnimatorListenerAdapter {
        C095316() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            PermissionActivity permissionActivity = PermissionActivity.this;
            permissionActivity.anim2 = ObjectAnimator.ofFloat(permissionActivity.tv_katrina, "alpha", 0.0f, 1.0f);
            PermissionActivity.this.anim2.setDuration(500L);
            PermissionActivity.this.anim2.addListener(new AnonymousClass1());
            PermissionActivity.this.anim2.start();
        }

        /* JADX INFO: renamed from: com.fufufu.katrina.backup.PermissionActivity$16$1, reason: invalid class name */
        class AnonymousClass1 extends AnimatorListenerAdapter {
            AnonymousClass1() {
            }

            /* JADX INFO: renamed from: com.fufufu.katrina.backup.PermissionActivity$16$1$1, reason: invalid class name and collision with other inner class name */
            class RunnableC22741 implements Runnable {
                RunnableC22741() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    PermissionActivity.this.anim3 = ObjectAnimator.ofFloat(PermissionActivity.this.tv_katrina, "alpha", 1.0f, 0.0f);
                    PermissionActivity.this.anim3.setDuration(500L);
                    PermissionActivity.this.anim3.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.16.1.1.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            PermissionActivity.this.anim4 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredWidth(), 50);
                            PermissionActivity.this.anim4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.16.1.1.1.1
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.width = iIntValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim4.setDuration(800L);
                            PermissionActivity.this.anim4.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim4.start();
                            PermissionActivity.this.anim5 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredHeight(), 50);
                            PermissionActivity.this.anim5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.16.1.1.1.2
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.height = iIntValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim5.setDuration(1000L);
                            PermissionActivity.this.anim5.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim5.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.16.1.1.1.3
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    PermissionActivity.this._playKatrinaAnimation1();
                                }
                            });
                            PermissionActivity.this.anim5.start();
                        }
                    });
                    PermissionActivity.this.anim3.start();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                new Handler().postDelayed(new RunnableC22741(), 2000L);
            }
        }
    }

    public void _playKatrinaAnimation1() {
        this.tv_katrina.setText(getString(C0978R.string.permission_island_tip_menu));
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.ln_island_img.getMeasuredWidth(), 600);
        this.anim = valueAnimatorOfInt;
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.17
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.width = iIntValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim.setDuration(800L);
        this.anim.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim.start();
        ValueAnimator valueAnimatorOfInt2 = ValueAnimator.ofInt(this.ln_island_img.getMeasuredHeight(), 125);
        this.anim1 = valueAnimatorOfInt2;
        valueAnimatorOfInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.18
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.height = iIntValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim1.setDuration(1000L);
        this.anim1.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim1.addListener(new C095619());
        this.anim1.start();
    }

    /* JADX INFO: renamed from: com.fufufu.katrina.backup.PermissionActivity$19 */
    class C095619 extends AnimatorListenerAdapter {
        C095619() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            PermissionActivity permissionActivity = PermissionActivity.this;
            permissionActivity.anim2 = ObjectAnimator.ofFloat(permissionActivity.tv_katrina, "alpha", 0.0f, 1.0f);
            PermissionActivity.this.anim2.setDuration(500L);
            PermissionActivity.this.anim2.addListener(new AnonymousClass1());
            PermissionActivity.this.anim2.start();
        }

        /* JADX INFO: renamed from: com.fufufu.katrina.backup.PermissionActivity$19$1, reason: invalid class name */
        class AnonymousClass1 extends AnimatorListenerAdapter {
            AnonymousClass1() {
            }

            /* JADX INFO: renamed from: com.fufufu.katrina.backup.PermissionActivity$19$1$1, reason: invalid class name and collision with other inner class name */
            class RunnableC22771 implements Runnable {
                RunnableC22771() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    PermissionActivity.this.anim3 = ObjectAnimator.ofFloat(PermissionActivity.this.tv_katrina, "alpha", 1.0f, 0.0f);
                    PermissionActivity.this.anim3.setDuration(500L);
                    PermissionActivity.this.anim3.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.19.1.1.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            PermissionActivity.this.anim4 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredWidth(), 50);
                            PermissionActivity.this.anim4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.19.1.1.1.1
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.width = iIntValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim4.setDuration(800L);
                            PermissionActivity.this.anim4.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim4.start();
                            PermissionActivity.this.anim5 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredHeight(), 50);
                            PermissionActivity.this.anim5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.19.1.1.1.2
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.height = iIntValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim5.setDuration(1000L);
                            PermissionActivity.this.anim5.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim5.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.19.1.1.1.3
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    PermissionActivity.this._playKatrinaAnimation2();
                                }
                            });
                            PermissionActivity.this.anim5.start();
                        }
                    });
                    PermissionActivity.this.anim3.start();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                new Handler().postDelayed(new RunnableC22771(), 2000L);
            }
        }
    }

    public void _playKatrinaAnimation2() {
        this.tv_katrina.setText(getString(C0978R.string.permission_island_tip_script));
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.ln_island_img.getMeasuredWidth(), 600);
        this.anim = valueAnimatorOfInt;
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.20
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.width = iIntValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim.setDuration(800L);
        this.anim.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim.start();
        ValueAnimator valueAnimatorOfInt2 = ValueAnimator.ofInt(this.ln_island_img.getMeasuredHeight(), 125);
        this.anim1 = valueAnimatorOfInt2;
        valueAnimatorOfInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.21
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.height = iIntValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim1.setDuration(1000L);
        this.anim1.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim1.addListener(new C096022());
        this.anim1.start();
    }

    /* JADX INFO: renamed from: com.fufufu.katrina.backup.PermissionActivity$22 */
    class C096022 extends AnimatorListenerAdapter {
        C096022() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            PermissionActivity permissionActivity = PermissionActivity.this;
            permissionActivity.anim2 = ObjectAnimator.ofFloat(permissionActivity.tv_katrina, "alpha", 0.0f, 1.0f);
            PermissionActivity.this.anim2.setDuration(500L);
            PermissionActivity.this.anim2.addListener(new AnonymousClass1());
            PermissionActivity.this.anim2.start();
        }

        /* JADX INFO: renamed from: com.fufufu.katrina.backup.PermissionActivity$22$1, reason: invalid class name */
        class AnonymousClass1 extends AnimatorListenerAdapter {
            AnonymousClass1() {
            }

            /* JADX INFO: renamed from: com.fufufu.katrina.backup.PermissionActivity$22$1$1, reason: invalid class name and collision with other inner class name */
            class RunnableC22801 implements Runnable {
                RunnableC22801() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    PermissionActivity.this.anim3 = ObjectAnimator.ofFloat(PermissionActivity.this.tv_katrina, "alpha", 1.0f, 0.0f);
                    PermissionActivity.this.anim3.setDuration(500L);
                    PermissionActivity.this.anim3.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.22.1.1.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            PermissionActivity.this.anim4 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredWidth(), 50);
                            PermissionActivity.this.anim4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.22.1.1.1.1
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.width = iIntValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim4.setDuration(800L);
                            PermissionActivity.this.anim4.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim4.start();
                            PermissionActivity.this.anim5 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredHeight(), 50);
                            PermissionActivity.this.anim5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.22.1.1.1.2
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.height = iIntValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim5.setDuration(1000L);
                            PermissionActivity.this.anim5.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim5.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.22.1.1.1.3
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    PermissionActivity.this._playKatrinaAnimation3();
                                }
                            });
                            PermissionActivity.this.anim5.start();
                        }
                    });
                    PermissionActivity.this.anim3.start();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                new Handler().postDelayed(new RunnableC22801(), 2000L);
            }
        }
    }

    public void _playKatrinaAnimation3() {
        this.tv_katrina.setText(getString(C0978R.string.permission_island_tip_ritual));
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.ln_island_img.getMeasuredWidth(), 600);
        this.anim = valueAnimatorOfInt;
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.23
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.width = iIntValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim.setDuration(800L);
        this.anim.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim.start();
        ValueAnimator valueAnimatorOfInt2 = ValueAnimator.ofInt(this.ln_island_img.getMeasuredHeight(), 125);
        this.anim1 = valueAnimatorOfInt2;
        valueAnimatorOfInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.24
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.height = iIntValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim1.setDuration(1000L);
        this.anim1.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim1.addListener(new C096325());
        this.anim1.start();
    }

    /* JADX INFO: renamed from: com.fufufu.katrina.backup.PermissionActivity$25 */
    class C096325 extends AnimatorListenerAdapter {
        C096325() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            PermissionActivity permissionActivity = PermissionActivity.this;
            permissionActivity.anim2 = ObjectAnimator.ofFloat(permissionActivity.tv_katrina, "alpha", 0.0f, 1.0f);
            PermissionActivity.this.anim2.setDuration(500L);
            PermissionActivity.this.anim2.addListener(new AnonymousClass1());
            PermissionActivity.this.anim2.start();
        }

        /* JADX INFO: renamed from: com.fufufu.katrina.backup.PermissionActivity$25$1, reason: invalid class name */
        class AnonymousClass1 extends AnimatorListenerAdapter {
            AnonymousClass1() {
            }

            /* JADX INFO: renamed from: com.fufufu.katrina.backup.PermissionActivity$25$1$1, reason: invalid class name and collision with other inner class name */
            class RunnableC22831 implements Runnable {
                RunnableC22831() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    PermissionActivity.this.anim3 = ObjectAnimator.ofFloat(PermissionActivity.this.tv_katrina, "alpha", 1.0f, 0.0f);
                    PermissionActivity.this.anim3.setDuration(500L);
                    PermissionActivity.this.anim3.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.25.1.1.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            PermissionActivity.this.anim4 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredWidth(), 50);
                            PermissionActivity.this.anim4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.25.1.1.1.1
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.width = iIntValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim4.setDuration(800L);
                            PermissionActivity.this.anim4.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim4.start();
                            PermissionActivity.this.anim5 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredHeight(), 50);
                            PermissionActivity.this.anim5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.25.1.1.1.2
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.height = iIntValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim5.setDuration(1000L);
                            PermissionActivity.this.anim5.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim5.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.25.1.1.1.3
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    PermissionActivity.this._playKatrinaAnimation4();
                                }
                            });
                            PermissionActivity.this.anim5.start();
                        }
                    });
                    PermissionActivity.this.anim3.start();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                new Handler().postDelayed(new RunnableC22831(), 2000L);
            }
        }
    }

    public void _playKatrinaAnimation4() {
        this.tv_katrina.setText(getString(C0978R.string.permission_island_tip_address));
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.ln_island_img.getMeasuredWidth(), 600);
        this.anim = valueAnimatorOfInt;
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.26
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.width = iIntValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim.setDuration(800L);
        this.anim.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim.start();
        ValueAnimator valueAnimatorOfInt2 = ValueAnimator.ofInt(this.ln_island_img.getMeasuredHeight(), 125);
        this.anim1 = valueAnimatorOfInt2;
        valueAnimatorOfInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.27
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.height = iIntValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim1.setDuration(1000L);
        this.anim1.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim1.addListener(new C096628());
        this.anim1.start();
    }

    /* JADX INFO: renamed from: com.fufufu.katrina.backup.PermissionActivity$28 */
    class C096628 extends AnimatorListenerAdapter {
        C096628() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            PermissionActivity permissionActivity = PermissionActivity.this;
            permissionActivity.anim2 = ObjectAnimator.ofFloat(permissionActivity.tv_katrina, "alpha", 0.0f, 1.0f);
            PermissionActivity.this.anim2.setDuration(500L);
            PermissionActivity.this.anim2.addListener(new AnonymousClass1());
            PermissionActivity.this.anim2.start();
        }

        /* JADX INFO: renamed from: com.fufufu.katrina.backup.PermissionActivity$28$1, reason: invalid class name */
        class AnonymousClass1 extends AnimatorListenerAdapter {
            AnonymousClass1() {
            }

            /* JADX INFO: renamed from: com.fufufu.katrina.backup.PermissionActivity$28$1$1, reason: invalid class name and collision with other inner class name */
            class RunnableC22861 implements Runnable {
                RunnableC22861() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    PermissionActivity.this.anim3 = ObjectAnimator.ofFloat(PermissionActivity.this.tv_katrina, "alpha", 1.0f, 0.0f);
                    PermissionActivity.this.anim3.setDuration(500L);
                    PermissionActivity.this.anim3.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.28.1.1.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            PermissionActivity.this.anim4 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredWidth(), 50);
                            PermissionActivity.this.anim4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.28.1.1.1.1
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.width = iIntValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim4.setDuration(800L);
                            PermissionActivity.this.anim4.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim4.start();
                            PermissionActivity.this.anim5 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredHeight(), 50);
                            PermissionActivity.this.anim5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.28.1.1.1.2
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.height = iIntValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim5.setDuration(1000L);
                            PermissionActivity.this.anim5.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim5.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.28.1.1.1.3
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    PermissionActivity.this._playKatrinaAnimation();
                                }
                            });
                            PermissionActivity.this.anim5.start();
                        }
                    });
                    PermissionActivity.this.anim3.start();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                new Handler().postDelayed(new RunnableC22861(), 2000L);
            }
        }
    }

    public void _createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(this.s_channel_id, getString(C0978R.string.permission_channel_name), 3);
            notificationChannel.setDescription(getString(C0978R.string.permission_channel_desc));
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }

    public void _checkPermission() {
        NotificationManager notificationManager;
        boolean zIsAccessibilityServiceEnabled = isAccessibilityServiceEnabled(getApplicationContext(), KatrinaIslandService.class);
        if (Build.VERSION.SDK_INT >= 23 && Settings.canDrawOverlays(this)) {
            this.btn_overlay.setText(getString(C0978R.string.permission_button_allowed));
        } else {
            this.btn_overlay.setText(getString(C0978R.string.permission_button_grant));
        }
        if (zIsAccessibilityServiceEnabled) {
            this.btn_access.setText(getString(C0978R.string.permission_button_allowed));
        } else {
            this.btn_access.setText(getString(C0978R.string.permission_button_grant));
        }
        if (Build.VERSION.SDK_INT >= 26 && (notificationManager = (NotificationManager) getSystemService(NotificationManager.class)) != null) {
            if (!notificationManager.areNotificationsEnabled()) {
                this.btn_notif.setText(getString(C0978R.string.permission_button_grant));
            } else {
                this.btn_notif.setText(getString(C0978R.string.permission_button_allowed));
            }
        }
        String allowedText = getString(C0978R.string.permission_button_allowed);
        if (this.pref.getString("agreement", "").equals("1") && this.btn_notif.getText().toString().equals(allowedText) && this.btn_overlay.getText().toString().equals(allowedText) && this.btn_access.getText().toString().equals(allowedText)) {
            startActivity(new Intent(getApplicationContext(), (Class<?>) KatrinaActivity.class));
            finish();
        }
    }

    public void _showAgreement() {
        showAGREE();
    }

    private void showAGREE() {
        View viewInflate = getLayoutInflater().inflate(C0978R.layout.agreement, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_agree);
        TextView textPrompt = (TextView) viewInflate.findViewById(C0978R.id.tv_agree2);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_agree);
        final EditText editText = (EditText) viewInflate.findViewById(C0978R.id.et_agree);
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        final String agreeKeyword = getString(C0978R.string.agree_keyword);
        if (textPrompt != null) {
            textPrompt.setText(getString(C0978R.string.agree_prompt, agreeKeyword));
        }
        Markwon.builder(this).build().setMarkdown(textView, LocalizedAssets.loadMarkdown(this, "agreement.md"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().trim().equalsIgnoreCase(agreeKeyword)) {
                    PermissionActivity.this.pref.edit().putString("agreement", "1").commit();
                    PermissionActivity.this._checkPermission();
                    PermissionActivity.this.AGREE.dismiss();
                    return;
                }
                SketchwareUtil.showMessage(PermissionActivity.this.getApplicationContext(), getString(C0978R.string.agree_rejected));
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.AGREE = alertDialogCreate;
        alertDialogCreate.show();
    }

    private String loadMarkdownFromAssets(String str) {
        try {
            InputStream inputStreamOpen = getAssets().open(str);
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            inputStreamOpen.close();
            return new String(bArr);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
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
