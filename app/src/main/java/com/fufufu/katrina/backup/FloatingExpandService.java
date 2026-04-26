package com.fufufu.katrina.backup;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.IBinder;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FloatingExpandService extends Service {
    private SharedPreferences.Editor editor;
    private View floatingExpand;
    private FrameLayout fr_base_expand;
    private LinearLayout ln_01;
    private LinearLayout ln_02;
    private LinearLayout ln_03;
    private LinearLayout ln_04;
    private LinearLayout ln_05;
    private LinearLayout ln_06;
    private LinearLayout ln_07;
    private LinearLayout ln_08;
    private LinearLayout ln_content_expand;
    private LinearLayout ln_top_expand;
    private WindowManager windowManager;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        showFloatingExpand();
        return super.onStartCommand(intent, i, i2);
    }

    /* JADX WARN: Type inference failed for: r5v3, types: [com.fufufu.katrina.backup.FloatingExpandService$1] */
    private void showFloatingExpand() {
        WindowManager.LayoutParams layoutParams;
        View view = this.floatingExpand;
        if (view == null || !view.isShown()) {
            this.floatingExpand = LayoutInflater.from(this).inflate(C0978R.layout.popup_expanded, (ViewGroup) null);
            this.windowManager = (WindowManager) getSystemService("window");
            SharedPreferences sharedPreferences = getSharedPreferences("preferences_xy", 0);
            this.editor = sharedPreferences.edit();
            this.ln_content_expand = (LinearLayout) this.floatingExpand.findViewById(C0978R.id.ln_content_expand);
            this.ln_top_expand = (LinearLayout) this.floatingExpand.findViewById(C0978R.id.ln_top_expand);
            this.fr_base_expand = (FrameLayout) this.floatingExpand.findViewById(C0978R.id.fr_base_expand);
            this.ln_01 = (LinearLayout) this.floatingExpand.findViewById(C0978R.id.ln_timepick);
            this.ln_02 = (LinearLayout) this.floatingExpand.findViewById(C0978R.id.ln_fast);
            this.ln_03 = (LinearLayout) this.floatingExpand.findViewById(C0978R.id.ln_kill);
            this.ln_04 = (LinearLayout) this.floatingExpand.findViewById(C0978R.id.ln_modpes);
            this.ln_05 = (LinearLayout) this.floatingExpand.findViewById(C0978R.id.ln_wipeapp);
            this.ln_06 = (LinearLayout) this.floatingExpand.findViewById(C0978R.id.ln_wipegms);
            this.ln_07 = (LinearLayout) this.floatingExpand.findViewById(C0978R.id.ln_kosong);
            this.ln_08 = (LinearLayout) this.floatingExpand.findViewById(C0978R.id.ln_collapse);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.fr_base_expand.getLayoutParams();
            layoutParams2.width = -2;
            layoutParams2.height = -2;
            layoutParams2.gravity = 17;
            this.fr_base_expand.setLayoutParams(layoutParams2);
            FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-2, -2);
            layoutParams3.gravity = 49;
            this.ln_content_expand.setLayoutParams(layoutParams3);
            this.ln_content_expand.setBackground(new GradientDrawable() {                 public GradientDrawable getIns(int i, int i2, int i3, int i4) {
                    setCornerRadius(i);
                    setStroke(i2, i3);
                    setColor(i4);
                    return this;
                }
            }.getIns(100, 0, 0, -1157627904));
            rippleViewChip(this.ln_01);
            rippleViewChip(this.ln_02);
            rippleViewChip(this.ln_03);
            rippleViewChip(this.ln_04);
            rippleViewChip(this.ln_05);
            rippleViewChip(this.ln_06);
            rippleViewChip(this.ln_07);
            rippleViewChip(this.ln_08);
            if (sharedPreferences.getString("show_expand", "").equals("show")) {
                this.ln_top_expand.setVisibility(0);
                this.fr_base_expand.setAlpha(1.0f);
            } else {
                this.ln_top_expand.setVisibility(8);
                this.fr_base_expand.setAlpha(0.7f);
            }
            if (Build.VERSION.SDK_INT >= 26) {
                layoutParams = new WindowManager.LayoutParams(-2, -2, 2038, 65800, -3);
            } else {
                layoutParams = new WindowManager.LayoutParams(-2, -2, 2002, 65800, -3);
            }
            layoutParams.gravity = 49;
            layoutParams.x = sharedPreferences.getInt("posX", 0);
            layoutParams.y = sharedPreferences.getInt("posY", 160);
            this.windowManager.addView(this.floatingExpand, layoutParams);
            initializeListeners();
            return;
        }
        closeFloatingExpand();
    }

    private void closeFloatingExpand() {
        View view = this.floatingExpand;
        if (view == null || !view.isShown()) {
            return;
        }
        this.windowManager.removeView(this.floatingExpand);
    }

    private void initializeListeners() {
        this.ln_01.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(FloatingExpandService.this, (Class<?>) ShortcutExecutorActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("shortcut_command", "timepick");
                intent.putExtra("shortcut_desc", "timepick");
                FloatingExpandService.this.startActivity(intent);
            }
        });
        this.ln_02.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(FloatingExpandService.this, (Class<?>) ShortcutExecutorActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("shortcut_command", "fastreboot");
                intent.putExtra("shortcut_desc", "fastreboot");
                FloatingExpandService.this.startActivity(intent);
            }
        });
        this.ln_03.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(FloatingExpandService.this, (Class<?>) ShortcutExecutorActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("shortcut_command", "killall");
                intent.putExtra("shortcut_desc", "killall");
                FloatingExpandService.this.startActivity(intent);
            }
        });
        this.ln_04.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(FloatingExpandService.this, (Class<?>) ShortcutExecutorActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("shortcut_command", "modpes");
                intent.putExtra("shortcut_desc", "modpes");
                FloatingExpandService.this.startActivity(intent);
            }
        });
        this.ln_05.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(FloatingExpandService.this, (Class<?>) ShortcutExecutorActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("shortcut_command", "wipemass_dialog");
                intent.putExtra("shortcut_desc", "wipemass_dialog");
                FloatingExpandService.this.startActivity(intent);
            }
        });
        this.ln_06.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(FloatingExpandService.this, (Class<?>) ShortcutExecutorActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("shortcut_command", "wipegms");
                intent.putExtra("shortcut_desc", "wipegms");
                FloatingExpandService.this.startActivity(intent);
            }
        });
        this.ln_07.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(FloatingExpandService.this, (Class<?>) ScannerActivity.class);
                intent.addFlags(268435456);
                FloatingExpandService.this.startActivity(intent);
            }
        });
        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {             @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (FloatingExpandService.this.ln_top_expand.getVisibility() == 0) {
                    FloatingExpandService.this.ln_top_expand.setVisibility(8);
                    FloatingExpandService.this.fr_base_expand.setAlpha(0.5f);
                    FloatingExpandService.this.editor.putString("show_expand", "hide").apply();
                    return true;
                }
                FloatingExpandService.this.ln_top_expand.setVisibility(0);
                FloatingExpandService.this.fr_base_expand.setAlpha(1.0f);
                FloatingExpandService.this.editor.putString("show_expand", "show").apply();
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                Toast.makeText(FloatingExpandService.this, "Long Press", 0).show();
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                Toast.makeText(FloatingExpandService.this, "Double Tapped", 0).show();
                return true;
            }
        });
        this.ln_08.setOnTouchListener(new View.OnTouchListener() {             int initialTouchX;
            int initialTouchY;
            float initialX;
            float initialY;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) FloatingExpandService.this.floatingExpand.getLayoutParams();
                if (gestureDetector.onTouchEvent(motionEvent)) {
                    return true;
                }
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.initialX = layoutParams.x;
                    this.initialY = layoutParams.y;
                    this.initialTouchX = (int) motionEvent.getRawX();
                    this.initialTouchY = (int) motionEvent.getRawY();
                    return true;
                }
                if (action != 2) {
                    return false;
                }
                int rawX = (int) (motionEvent.getRawX() - this.initialTouchX);
                int rawY = (int) (motionEvent.getRawY() - this.initialTouchY);
                layoutParams.x = (int) (this.initialX + rawX);
                layoutParams.y = (int) (this.initialY + rawY);
                FloatingExpandService.this.windowManager.updateViewLayout(FloatingExpandService.this.floatingExpand, layoutParams);
                FloatingExpandService.this.editor.putInt("posX", layoutParams.x);
                FloatingExpandService.this.editor.putInt("posY", layoutParams.y);
                FloatingExpandService.this.editor.apply();
                return true;
            }
        });
    }

    public void rippleViewChip(View view) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor("#30FFFFFF"));
        gradientDrawable.setCornerRadius((float) 100.0d);
        view.setBackground(new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{Color.parseColor("#4DFFFFFF")}), gradientDrawable, null));
    }
}