package com.fufufu.katrina.backup;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.topjohnwu.superuser.Shell;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RitualResultActivity extends AppCompatActivity {
    private LinearLayout ln_base;
    private LinearLayout ln_cleaner;
    private LinearLayout ln_prop;
    private LinearLayout ln_reboot;
    private LinearLayout ln_ssaid;
    private LinearLayout ln_wipe;
    private Runnable runnableModpes;
    private Runnable runnableReboot;
    private Runnable runnableStartReboot;
    private TextView tv_01;
    private TextView tv_02;
    private TextView tv_03;
    private TextView tv_04;
    private TextView tv_05;
    private TextView tv_cleaner;
    private TextView tv_prop;
    private TextView tv_reboot;
    private TextView tv_ssaid;
    private TextView tv_wipe;
    private ScrollView vscleaner;
    private ScrollView vsprop;
    private ScrollView vsssaid;
    private ScrollView vswipe;
    private String s_universal = "";
    private String s_extra = "";
    private boolean b_prop = false;
    private boolean b_cleaner = false;
    private boolean b_wipe = false;
    private boolean b_ssaid = false;
    private String s_command = "";
    private String s_commandResult = "";
    private String s_exitCode = "";
    private boolean b_command = false;
    private String s_commandBase = "";
    private boolean b_modpes = false;
    private String s_commandModpes = "";

        private Intent f861i = new Intent();
    private ObjectAnimator oacleaner = new ObjectAnimator();
    private ObjectAnimator oaprop = new ObjectAnimator();
    private ObjectAnimator oawipe = new ObjectAnimator();
    private ObjectAnimator oassaid = new ObjectAnimator();
    private Handler Reboot = new Handler();
    private Handler Modpes = new Handler();
    private Handler StartReboot = new Handler();

    public void _EXTRA() {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0978R.layout.ritual_result);
        initialize(bundle);
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == -1) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1000);
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
        this.ln_base = (LinearLayout) findViewById(C0978R.id.ln_base);
        this.ln_prop = (LinearLayout) findViewById(C0978R.id.ln_prop);
        this.ln_cleaner = (LinearLayout) findViewById(C0978R.id.ln_cleaner);
        this.ln_wipe = (LinearLayout) findViewById(C0978R.id.ln_wipe);
        this.ln_ssaid = (LinearLayout) findViewById(C0978R.id.ln_ssaid);
        this.ln_reboot = (LinearLayout) findViewById(C0978R.id.ln_reboot);
        this.tv_01 = (TextView) findViewById(C0978R.id.tv_01);
        this.vsprop = (ScrollView) findViewById(C0978R.id.vsprop);
        this.tv_prop = (TextView) findViewById(C0978R.id.tv_prop);
        this.tv_02 = (TextView) findViewById(C0978R.id.tv_02);
        this.vscleaner = (ScrollView) findViewById(C0978R.id.vscleaner);
        this.tv_cleaner = (TextView) findViewById(C0978R.id.tv_cleaner);
        this.tv_03 = (TextView) findViewById(C0978R.id.tv_03);
        this.vswipe = (ScrollView) findViewById(C0978R.id.vswipe);
        this.tv_wipe = (TextView) findViewById(C0978R.id.tv_wipe);
        this.tv_04 = (TextView) findViewById(C0978R.id.tv_04);
        this.vsssaid = (ScrollView) findViewById(C0978R.id.vsssaid);
        this.tv_ssaid = (TextView) findViewById(C0978R.id.tv_ssaid);
        this.tv_05 = (TextView) findViewById(C0978R.id.tv_05);
        this.tv_reboot = (TextView) findViewById(C0978R.id.tv_reboot);
        this.oacleaner.addListener(new C10951());
        this.oaprop.addListener(new C10992());
        this.oawipe.addListener(new C11003());
        this.oassaid.addListener(new C11014());
    }

        class C10951 implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        C10951() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            RitualResultActivity ritualResultActivity = RitualResultActivity.this;
            ritualResultActivity.s_universal = FileUtil.readFile("/data/data/".concat(ritualResultActivity.getApplicationContext().getPackageName().concat("/cleaner.xml")));
            if (RitualResultActivity.this.s_universal.isEmpty()) {
                RitualResultActivity.this.s_universal = "cant read result";
            }
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {                 int lineIndex = 0;
                String[] lines;

                {
                    this.lines = RitualResultActivity.this.s_universal.split("\\n");
                }

                @Override // java.lang.Runnable
                public void run() {
                    RitualResultActivity.this.tv_cleaner.append(String.valueOf(this.lines[this.lineIndex]) + "\n");
                    RitualResultActivity.this.runOnUiThread(new Runnable() {                         @Override // java.lang.Runnable
                        public void run() {
                            RitualResultActivity.this.updateTextCleaner();
                        }
                    });
                    int i = this.lineIndex + 1;
                    this.lineIndex = i;
                    if (i >= this.lines.length) {
                        RitualResultActivity.this.tv_02.setBackgroundColor(-11417769);
                        RitualResultActivity.this.b_cleaner = true;
                    } else {
                        handler.postDelayed(this, 10L);
                    }
                }
            }, 10L);
        }
    }

        class C10992 implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        C10992() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!FileUtil.isExistFile("/data/data/".concat(RitualResultActivity.this.getApplicationContext().getPackageName().concat("/system.prop")))) {
                RitualResultActivity.this.s_universal = "";
            } else {
                RitualResultActivity ritualResultActivity = RitualResultActivity.this;
                ritualResultActivity.s_universal = FileUtil.readFile("/data/data/".concat(ritualResultActivity.getApplicationContext().getPackageName().concat("/system.prop")));
            }
            if (RitualResultActivity.this.s_universal.isEmpty()) {
                RitualResultActivity.this.s_universal = "cant read result";
            }
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {                 int lineIndex = 0;
                String[] lines;

                {
                    this.lines = RitualResultActivity.this.s_universal.split("\\n");
                }

                @Override // java.lang.Runnable
                public void run() {
                    RitualResultActivity.this.tv_prop.append(String.valueOf(this.lines[this.lineIndex]) + "\n");
                    RitualResultActivity.this.runOnUiThread(new Runnable() {                         @Override // java.lang.Runnable
                        public void run() {
                            RitualResultActivity.this.updateTextProp();
                        }
                    });
                    int i = this.lineIndex + 1;
                    this.lineIndex = i;
                    if (i >= this.lines.length) {
                        RitualResultActivity.this.tv_01.setBackgroundColor(-11417769);
                        RitualResultActivity.this.b_prop = true;
                    } else {
                        handler.postDelayed(this, 30L);
                    }
                }
            }, 30L);
        }
    }

        class C11003 implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        C11003() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            RitualResultActivity ritualResultActivity = RitualResultActivity.this;
            ritualResultActivity.s_universal = FileUtil.readFile("/data/data/".concat(ritualResultActivity.getApplicationContext().getPackageName().concat("/wipegms.xml")));
            if (RitualResultActivity.this.s_universal.isEmpty()) {
                RitualResultActivity.this.s_universal = "cant read result";
            }
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {                 int lineIndex = 0;
                String[] lines;

                {
                    this.lines = RitualResultActivity.this.s_universal.split("\\n");
                }

                @Override // java.lang.Runnable
                public void run() {
                    RitualResultActivity.this.tv_wipe.append(String.valueOf(this.lines[this.lineIndex]) + "\n");
                    RitualResultActivity.this.runOnUiThread(new Runnable() {                         @Override // java.lang.Runnable
                        public void run() {
                            RitualResultActivity.this.updateTextWipe();
                        }
                    });
                    int i = this.lineIndex + 1;
                    this.lineIndex = i;
                    if (i >= this.lines.length) {
                        RitualResultActivity.this.tv_03.setBackgroundColor(-11417769);
                        RitualResultActivity.this.b_wipe = true;
                    } else {
                        handler.postDelayed(this, 50L);
                    }
                }
            }, 50L);
        }
    }

        class C11014 implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        C11014() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            RitualResultActivity ritualResultActivity = RitualResultActivity.this;
            ritualResultActivity.s_universal = FileUtil.readFile("/data/data/".concat(ritualResultActivity.getApplicationContext().getPackageName().concat("/reseto.xml")));
            if (RitualResultActivity.this.s_universal.isEmpty()) {
                RitualResultActivity.this.s_universal = "cant read result";
            }
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {                 int lineIndex = 0;
                String[] lines;

                {
                    this.lines = RitualResultActivity.this.s_universal.split("\\n");
                }

                @Override // java.lang.Runnable
                public void run() {
                    RitualResultActivity.this.tv_ssaid.append(String.valueOf(this.lines[this.lineIndex]) + "\n");
                    RitualResultActivity.this.runOnUiThread(new Runnable() {                         @Override // java.lang.Runnable
                        public void run() {
                            RitualResultActivity.this.updateTextSsaid();
                        }
                    });
                    int i = this.lineIndex + 1;
                    this.lineIndex = i;
                    if (i >= this.lines.length) {
                        RitualResultActivity.this.tv_04.setBackgroundColor(-11417769);
                        RitualResultActivity.this.b_ssaid = true;
                    } else {
                        handler.postDelayed(this, 50L);
                    }
                }
            }, 50L);
        }
    }

    private void initializeLogic() {
        _firstSetUI();
    }

    public void _firstSetUI() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(ViewCompat.MEASURED_STATE_MASK);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.setNavigationBarColor(ViewCompat.MEASURED_STATE_MASK);
        }
        this.ln_reboot.setVisibility(8);
        this.tv_prop.setText("");
        this.tv_cleaner.setText("");
        this.tv_wipe.setText("");
        this.tv_ssaid.setText("");
        this.tv_reboot.setText("");
        this.tv_01.setBackgroundColor(-151225);
        this.tv_02.setBackgroundColor(-151225);
        this.tv_03.setBackgroundColor(-151225);
        this.tv_04.setBackgroundColor(-151225);
        this.vsprop.setVerticalScrollBarEnabled(false);
        this.vscleaner.setVerticalScrollBarEnabled(false);
        this.vswipe.setVerticalScrollBarEnabled(false);
        this.vsssaid.setVerticalScrollBarEnabled(false);
        this.b_prop = false;
        this.b_cleaner = false;
        this.b_wipe = false;
        this.b_ssaid = false;
        this.b_modpes = false;
        try {
            String strCopyFromInputStream = SketchwareUtil.copyFromInputStream(getAssets().open("prop.sh"));
            this.s_commandBase = strCopyFromInputStream;
            String strReplace = strCopyFromInputStream.replace("Ĩ", "a");
            this.s_commandBase = strReplace;
            String strReplace2 = strReplace.replace("ĩ", "b");
            this.s_commandBase = strReplace2;
            String strReplace3 = strReplace2.replace("Ī", "c");
            this.s_commandBase = strReplace3;
            String strReplace4 = strReplace3.replace("ī", "d");
            this.s_commandBase = strReplace4;
            String strReplace5 = strReplace4.replace("Ĭ", "e");
            this.s_commandBase = strReplace5;
            String strReplace6 = strReplace5.replace("ĭ", "f");
            this.s_commandBase = strReplace6;
            String strReplace7 = strReplace6.replace("Į", "g");
            this.s_commandBase = strReplace7;
            String strReplace8 = strReplace7.replace("į", "h");
            this.s_commandBase = strReplace8;
            String strReplace9 = strReplace8.replace("ĺ", "i");
            this.s_commandBase = strReplace9;
            String strReplace10 = strReplace9.replace("ļ", "j");
            this.s_commandBase = strReplace10;
            String strReplace11 = strReplace10.replace("ľ", "k");
            this.s_commandBase = strReplace11;
            String strReplace12 = strReplace11.replace("ŀ", "l");
            this.s_commandBase = strReplace12;
            String strReplace13 = strReplace12.replace("Ǐ", "m");
            this.s_commandBase = strReplace13;
            String strReplace14 = strReplace13.replace("ǐ", "n");
            this.s_commandBase = strReplace14;
            String strReplace15 = strReplace14.replace("Ȉ", "o");
            this.s_commandBase = strReplace15;
            String strReplace16 = strReplace15.replace("ȉ", "p");
            this.s_commandBase = strReplace16;
            String strReplace17 = strReplace16.replace("Ȋ", "q");
            this.s_commandBase = strReplace17;
            String strReplace18 = strReplace17.replace("ȋ", "r");
            this.s_commandBase = strReplace18;
            String strReplace19 = strReplace18.replace("ɭ", "s");
            this.s_commandBase = strReplace19;
            String strReplace20 = strReplace19.replace("ΐ", "t");
            this.s_commandBase = strReplace20;
            String strReplace21 = strReplace20.replace("ᴉ", "u");
            this.s_commandBase = strReplace21;
            String strReplace22 = strReplace21.replace("Ḭ", "v");
            this.s_commandBase = strReplace22;
            String strReplace23 = strReplace22.replace("ḭ", "w");
            this.s_commandBase = strReplace23;
            String strReplace24 = strReplace23.replace("Ḯ", "x");
            this.s_commandBase = strReplace24;
            String strReplace25 = strReplace24.replace("ḯ", "y");
            this.s_commandBase = strReplace25;
            String strReplace26 = strReplace25.replace("ḷ", "z");
            this.s_commandBase = strReplace26;
            String strReplace27 = strReplace26.replace("ḹ", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
            this.s_commandBase = strReplace27;
            String strReplace28 = strReplace27.replace("ḻ", "B");
            this.s_commandBase = strReplace28;
            String strReplace29 = strReplace28.replace("ḽ", "C");
            this.s_commandBase = strReplace29;
            String strReplace30 = strReplace29.replace("Ỉ", "D");
            this.s_commandBase = strReplace30;
            String strReplace31 = strReplace30.replace("ỉ", ExifInterface.LONGITUDE_EAST);
            this.s_commandBase = strReplace31;
            String strReplace32 = strReplace31.replace("Ị", "F");
            this.s_commandBase = strReplace32;
            String strReplace33 = strReplace32.replace("ị", "G");
            this.s_commandBase = strReplace33;
            String strReplace34 = strReplace33.replace("ἰ", "H");
            this.s_commandBase = strReplace34;
            String strReplace35 = strReplace34.replace("ἱ", "I");
            this.s_commandBase = strReplace35;
            String strReplace36 = strReplace35.replace("ἲ", "J");
            this.s_commandBase = strReplace36;
            String strReplace37 = strReplace36.replace("ἳ", "K");
            this.s_commandBase = strReplace37;
            String strReplace38 = strReplace37.replace("ἴ", "L");
            this.s_commandBase = strReplace38;
            String strReplace39 = strReplace38.replace("ἵ", "M");
            this.s_commandBase = strReplace39;
            String strReplace40 = strReplace39.replace("ἶ", "N");
            this.s_commandBase = strReplace40;
            String strReplace41 = strReplace40.replace("ἷ", "O");
            this.s_commandBase = strReplace41;
            String strReplace42 = strReplace41.replace("Ἱ", "P");
            this.s_commandBase = strReplace42;
            String strReplace43 = strReplace42.replace("Ἲ", "Q");
            this.s_commandBase = strReplace43;
            String strReplace44 = strReplace43.replace("Ἳ", "R");
            this.s_commandBase = strReplace44;
            String strReplace45 = strReplace44.replace("Ἴ", ExifInterface.LATITUDE_SOUTH);
            this.s_commandBase = strReplace45;
            String strReplace46 = strReplace45.replace("Ἵ", ExifInterface.GPS_DIRECTION_TRUE);
            this.s_commandBase = strReplace46;
            String strReplace47 = strReplace46.replace("Ἶ", "U");
            this.s_commandBase = strReplace47;
            String strReplace48 = strReplace47.replace("Ἷ", ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
            this.s_commandBase = strReplace48;
            String strReplace49 = strReplace48.replace("ὶ", ExifInterface.LONGITUDE_WEST);
            this.s_commandBase = strReplace49;
            String strReplace50 = strReplace49.replace("ί", "X");
            this.s_commandBase = strReplace50;
            String strReplace51 = strReplace50.replace("ῐ", "Y");
            this.s_commandBase = strReplace51;
            String strReplace52 = strReplace51.replace("ῑ", "Z");
            this.s_commandBase = strReplace52;
            String strReplace53 = strReplace52.replace("ῒ", "_");
            this.s_commandBase = strReplace53;
            String strReplace54 = strReplace53.replace("ΐ", "=");
            this.s_commandBase = strReplace54;
            String strReplace55 = strReplace54.replace("ῖ", "#");
            this.s_commandBase = strReplace55;
            String strReplace56 = strReplace55.replace("ῗ", "-");
            this.s_commandBase = strReplace56;
            String strReplace57 = strReplace56.replace("Ῐ", "/");
            this.s_commandBase = strReplace57;
            String strReplace58 = strReplace57.replace("Ῑ", "+");
            this.s_commandBase = strReplace58;
            String strReplace59 = strReplace58.replace("Ὶ", "&");
            this.s_commandBase = strReplace59;
            String strReplace60 = strReplace59.replace("Ί", ".");
            this.s_commandBase = strReplace60;
            String strReplace61 = strReplace60.replace("ⅱ", "");
            this.s_commandBase = strReplace61;
            this.s_commandBase = strReplace61.replace("futhispackage", getApplicationContext().getPackageName());
            _getExtraRitual();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTextProp() {
        this.vsprop.post(new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                RitualResultActivity.this.vsprop.fullScroll(130);
            }
        });
    }

    public void updateTextCleaner() {
        this.vscleaner.post(new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                RitualResultActivity.this.vscleaner.fullScroll(130);
            }
        });
    }

    public void updateTextWipe() {
        this.vswipe.post(new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                RitualResultActivity.this.vswipe.fullScroll(130);
            }
        });
    }

    public void updateTextSsaid() {
        this.vsssaid.post(new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                RitualResultActivity.this.vsssaid.fullScroll(130);
            }
        });
    }

    public void _onExtraProp() {
        String stringExtra = getIntent().getStringExtra("PROP");
        this.s_extra = stringExtra;
        if (stringExtra.equals("true")) {
            this.oaprop.cancel();
            this.oaprop.setTarget(this.ln_prop);
            this.oaprop.setPropertyName("alpha");
            this.oaprop.setFloatValues(0.0f, 1.0f);
            this.oaprop.setDuration(500L);
            this.oaprop.start();
            return;
        }
        this.ln_prop.setVisibility(8);
    }

    public void _onExtraClean() {
        String stringExtra = getIntent().getStringExtra("CLEANER");
        this.s_extra = stringExtra;
        if (stringExtra.equals("true")) {
            this.oacleaner.cancel();
            this.oacleaner.setTarget(this.ln_cleaner);
            this.oacleaner.setPropertyName("alpha");
            this.oacleaner.setFloatValues(0.0f, 1.0f);
            this.oacleaner.setDuration(500L);
            this.oacleaner.start();
            return;
        }
        this.ln_cleaner.setVisibility(8);
        this.b_cleaner = true;
    }

    public void _onExtraSsaid() {
        String stringExtra = getIntent().getStringExtra("SSAID");
        this.s_extra = stringExtra;
        if (stringExtra.equals("true")) {
            this.oassaid.cancel();
            this.oassaid.setTarget(this.ln_ssaid);
            this.oassaid.setPropertyName("alpha");
            this.oassaid.setFloatValues(0.0f, 1.0f);
            this.oassaid.setDuration(500L);
            this.oassaid.start();
            return;
        }
        String stringExtra2 = getIntent().getStringExtra("RESET0");
        this.s_extra = stringExtra2;
        if (stringExtra2.equals("true")) {
            this.oassaid.cancel();
            this.oassaid.setTarget(this.ln_ssaid);
            this.oassaid.setPropertyName("alpha");
            this.oassaid.setFloatValues(0.0f, 1.0f);
            this.oassaid.setDuration(500L);
            this.oassaid.start();
            return;
        }
        this.ln_ssaid.setVisibility(8);
        this.b_ssaid = true;
    }

    public void _onExtraReboot() {
        Runnable runnable = new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                if (!RitualResultActivity.this.b_prop || !RitualResultActivity.this.b_cleaner || !RitualResultActivity.this.b_ssaid || !RitualResultActivity.this.b_wipe || !RitualResultActivity.this.b_modpes) {
                    RitualResultActivity.this.Reboot.postDelayed(RitualResultActivity.this.runnableReboot, 500L);
                    return;
                }
                RitualResultActivity.this.Reboot.removeCallbacks(RitualResultActivity.this.runnableReboot);
                if (!RitualResultActivity.this.getIntent().getStringExtra("NORESTART").equals("true")) {
                    RitualResultActivity.this.ln_prop.setVisibility(4);
                    RitualResultActivity.this.ln_cleaner.setVisibility(4);
                    RitualResultActivity.this.ln_wipe.setVisibility(4);
                    RitualResultActivity.this.ln_ssaid.setVisibility(4);
                    RitualResultActivity.this.ln_reboot.setVisibility(0);
                    RitualResultActivity.this._onStartReboot();
                    return;
                }
                RitualResultActivity.this.finish();
            }
        };
        this.runnableReboot = runnable;
        this.Reboot.postDelayed(runnable, 0L);
    }

    public void _onStartReboot() {
        this.s_command = this.s_commandBase;
        this.s_commandResult = "";
        this.s_exitCode = "";
        String stringExtra = getIntent().getStringExtra("REBOOT");
        this.s_extra = stringExtra;
        if (stringExtra.equals("true")) {
            this.tv_05.setText("Reboot");
            this.tv_reboot.setText("Restart phone...");
            this.s_command = this.s_command.concat("\ndelfile\n\nooreboot");
            new Handler().postDelayed(new Runnable() {                 @Override // java.lang.Runnable
                public void run() {
                    RitualResultActivity.this.b_command = false;
                    Shell.Result resultExec = Shell.cmd(RitualResultActivity.this.s_command).exec();
                    List<String> out = resultExec.getOut();
                    resultExec.getCode();
                    RitualResultActivity.this.b_command = resultExec.isSuccess();
                    RitualResultActivity.this.s_commandResult = String.join("\n", out);
                }
            }, 2000L);
            return;
        }
        String stringExtra2 = getIntent().getStringExtra("DALVIC");
        this.s_extra = stringExtra2;
        if (stringExtra2.equals("true")) {
            this.tv_05.setText("Reboot Recovery");
            this.tv_reboot.setText("Wipe dalvic dan cache...");
            this.s_command = this.s_command.concat("\ndelfile\n\noodalvic");
            new Handler().postDelayed(new Runnable() {                 @Override // java.lang.Runnable
                public void run() {
                    RitualResultActivity.this.b_command = false;
                    Shell.Result resultExec = Shell.cmd(RitualResultActivity.this.s_command).exec();
                    List<String> out = resultExec.getOut();
                    resultExec.getCode();
                    RitualResultActivity.this.b_command = resultExec.isSuccess();
                    RitualResultActivity.this.s_commandResult = String.join("\n", out);
                }
            }, 2000L);
            return;
        }
        finish();
    }

    public void _onExtraGms() {
        String stringExtra = getIntent().getStringExtra("WIPEGMS");
        this.s_extra = stringExtra;
        if (stringExtra.equals("true")) {
            this.oawipe.cancel();
            this.oawipe.setTarget(this.ln_wipe);
            this.oawipe.setPropertyName("alpha");
            this.oawipe.setFloatValues(0.0f, 1.0f);
            this.oawipe.setDuration(500L);
            this.oawipe.start();
            return;
        }
        this.ln_wipe.setVisibility(8);
        this.b_wipe = true;
    }

    public void _getExtraRitual() {
        _onExtraProp();
        _onExtraClean();
        _onExtraGms();
        _onExtraSsaid();
        _onExtraModpes();
        _onExtraReboot();
    }

    public void _onExtraModpes() {
        Runnable runnable = new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                if (!RitualResultActivity.this.b_prop || !RitualResultActivity.this.b_cleaner || !RitualResultActivity.this.b_ssaid || !RitualResultActivity.this.b_wipe) {
                    RitualResultActivity.this.Modpes.postDelayed(RitualResultActivity.this.runnableModpes, 500L);
                    return;
                }
                RitualResultActivity.this.Modpes.removeCallbacks(RitualResultActivity.this.runnableModpes);
                if (RitualResultActivity.this.getIntent().getStringExtra("MODPES").equals("false")) {
                    RitualResultActivity.this.b_modpes = true;
                    return;
                }
                RitualResultActivity.this.ln_prop.setVisibility(4);
                RitualResultActivity.this.ln_cleaner.setVisibility(4);
                RitualResultActivity.this.ln_wipe.setVisibility(4);
                RitualResultActivity.this.ln_ssaid.setVisibility(4);
                RitualResultActivity.this.ln_reboot.setVisibility(0);
                RitualResultActivity.this.tv_05.setText("Mode Pesawat");
                RitualResultActivity.this.tv_reboot.setText("Mematikan mode pesawat..");
                RitualResultActivity.this._onStartModpes();
            }
        };
        this.runnableModpes = runnable;
        this.Modpes.postDelayed(runnable, 0L);
    }

    public void _onStartModpes() {
        this.s_commandModpes = "settings put global airplane_mode_on 0\nam broadcast -a android.intent.action.AIRPLANE_MODE";
        this.b_command = false;
        Shell.Result resultExec = Shell.cmd("settings put global airplane_mode_on 0\nam broadcast -a android.intent.action.AIRPLANE_MODE").exec();
        List<String> out = resultExec.getOut();
        resultExec.getCode();
        this.b_command = resultExec.isSuccess();
        this.s_commandResult = String.join("\n", out);
        this.b_modpes = true;
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