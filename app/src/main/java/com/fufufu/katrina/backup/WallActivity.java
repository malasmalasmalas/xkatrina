package com.fufufu.katrina.backup;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.Key;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WallActivity extends AppCompatActivity {
    private Button btn_gg;
    private Button btn_tt;
    private Button btn_ww;
    private FrameLayout fr_wall;
    private ImageView im_back;
    private ImageView im_mask;
    private ImageView im_wall;
    private LinearLayout ln_base;
    private LinearLayout ln_code;
    private FrameLayout ln_frame;
    private LinearLayout ln_mode;
    private LinearLayout ln_top;
    private LinearLayout ln_vertical;
    private LinearLayout ln_wall;
    private SharedPreferences prefwall;
    private TextView tv_code;
    private TextView tv_vertical;

        private double f874n = 0.0d;
    private String s_interstelar = "";

        private String f872c1 = "";

        private String f873c2 = "";
    private HashMap<String, Object> m_color = new HashMap<>();
    private String drawableName = "";
    private ArrayList<String> ls_interstelar = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_color = new ArrayList<>();

        private Calendar f871c = Calendar.getInstance();
    private Intent intent = new Intent();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0978R.layout.wall);
        initialize(bundle);
        initializeLogic();
    }

    private void initialize(Bundle bundle) {
        this.ln_base = (LinearLayout) findViewById(C0978R.id.ln_base);
        this.ln_frame = (FrameLayout) findViewById(C0978R.id.ln_frame);
        this.ln_top = (LinearLayout) findViewById(C0978R.id.ln_top);
        this.ln_mode = (LinearLayout) findViewById(C0978R.id.ln_mode);
        this.ln_wall = (LinearLayout) findViewById(C0978R.id.ln_wall);
        this.fr_wall = (FrameLayout) findViewById(C0978R.id.fr_wall);
        this.im_back = (ImageView) findViewById(C0978R.id.im_back);
        this.im_mask = (ImageView) findViewById(C0978R.id.im_mask);
        this.im_wall = (ImageView) findViewById(C0978R.id.im_wall);
        this.ln_vertical = (LinearLayout) findViewById(C0978R.id.ln_vertical);
        this.ln_code = (LinearLayout) findViewById(C0978R.id.ln_code);
        this.tv_vertical = (TextView) findViewById(C0978R.id.tv_vertical);
        this.tv_code = (TextView) findViewById(C0978R.id.tv_code);
        this.btn_gg = (Button) findViewById(C0978R.id.btn_gg);
        this.btn_tt = (Button) findViewById(C0978R.id.btn_tt);
        this.btn_ww = (Button) findViewById(C0978R.id.btn_ww);
        this.prefwall = getSharedPreferences("preferences_wall", 0);
        this.btn_gg.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WallActivity.this.prefwall.edit().putString("mode", "gg").commit();
                WallActivity.this._setFirstUI();
            }
        });
        this.btn_tt.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WallActivity.this.prefwall.edit().putString("mode", "tt").commit();
                WallActivity.this._setFirstUI();
            }
        });
        this.btn_ww.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WallActivity.this.prefwall.edit().putString("mode", "ww").commit();
                WallActivity.this._setFirstUI();
            }
        });
    }

    private void initializeLogic() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(Color.parseColor("#FF000000"));
        }
        _setFirstUI();
    }

    public void _setHighLighter(TextView textView) {
        String str = this.f872c1;
        TextView textView2 = new TextView(this);
        TextView textView3 = new TextView(this);
        TextView textView4 = new TextView(this);
        TextView textView5 = new TextView(this);
        TextView textView6 = new TextView(this);
        TextView textView7 = new TextView(this);
        TextView textView8 = new TextView(this);
        TextView textView9 = new TextView(this);
        TextView textView10 = new TextView(this);
        TextView textView11 = new TextView(this);
        TextView textView12 = new TextView(this);
        textView2.setText("\\b(out|print|println|valueOf|toString|concat|equals|for|while|switch|getText");
        textView3.setText("|println|printf|print|out|parseInt|round|sqrt|charAt|compareTo|compareToIgnoreCase|concat|contains|contentEquals|equals|length|toLowerCase|trim|toUpperCase|toString|valueOf|substring|startsWith|split|replace|replaceAll|lastIndexOf|size)\\b");
        textView4.setText("\\b(public|private|protected|void|switch|case|class|import|package|extends|Activity|TextView|EditText|LinearLayout|CharSequence|String|int|onCreate|ArrayList|float|if|else|static|Intent|Button|SharedPreferences");
        textView5.setText("|abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|extends|final|finally|float|for|goto|if|implements|import|instanceof|interface|long|native|new|package|private|protected|");
        textView6.setText("public|return|short|static|strictfp|super|switch|synchronized|this|throw|throws|transient|try|void|volatile|while|when|true|false|null)\\b");
        textView7.setText("\\b([0-9]+)\\b");
        textView8.setText("(\\w+)(\\()+");
        textView9.setText("\\@\\s*(\\w+)");
        textView10.setText("\"(.*?)\"|'(.*?)'");
        textView11.setText("/\\*(?:.|[\\n\\r])*?\\*/|//.*");
        textView12.setText("\\b(Uzuakoli|Amoji|Bright|Ndudirim|Ezinwanne|Lightworker|Isuochi|Abia|Ngodo)\\b");
        textView.addTextChangedListener(new TextWatcher() {             ColorScheme keywords1;
            ColorScheme keywords2;
            ColorScheme keywords3;
            ColorScheme keywords4;
            ColorScheme keywords5;
            ColorScheme keywords6;
            ColorScheme keywords7;
            ColorScheme keywords8;
            final ColorScheme[] schemes;

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            {
                this.keywords1 = new ColorScheme(Pattern.compile(textView2.getText().toString().concat(textView3.getText().toString())), Color.parseColor(str));
                this.keywords2 = new ColorScheme(Pattern.compile(textView4.getText().toString().concat(textView5.getText().toString().concat(textView6.getText().toString()))), Color.parseColor("#9fe481"));
                this.keywords3 = new ColorScheme(Pattern.compile(textView7.getText().toString()), Color.parseColor("#f6e785"));
                this.keywords4 = new ColorScheme(Pattern.compile(textView8.getText().toString()), Color.parseColor(str));
                this.keywords5 = new ColorScheme(Pattern.compile(textView10.getText().toString()), Color.parseColor("#ff1744"));
                this.keywords6 = new ColorScheme(Pattern.compile(textView11.getText().toString()), Color.parseColor("#6a6a6a"));
                this.keywords7 = new ColorScheme(Pattern.compile(textView9.getText().toString()), Color.parseColor("#f6e785"));
                ColorScheme colorScheme = new ColorScheme(Pattern.compile(textView12.getText().toString()), Color.parseColor("#ff5722"));
                this.keywords8 = colorScheme;
                this.schemes = new ColorScheme[]{this.keywords1, this.keywords2, this.keywords3, this.keywords4, this.keywords5, this.keywords6, this.keywords7, colorScheme};
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                removeSpans(editable, ForegroundColorSpan.class);
                for (ColorScheme colorScheme : this.schemes) {
                    Matcher matcher = colorScheme.pattern.matcher(editable);
                    while (matcher.find()) {
                        if (colorScheme == this.keywords4) {
                            editable.setSpan(new ForegroundColorSpan(colorScheme.color), matcher.start(), matcher.end() - 1, 33);
                        } else {
                            editable.setSpan(new ForegroundColorSpan(colorScheme.color), matcher.start(), matcher.end(), 33);
                        }
                    }
                }
            }

            void removeSpans(Editable editable, Class cls) {
                for (CharacterStyle characterStyle : (CharacterStyle[]) editable.getSpans(0, editable.length(), cls)) {
                    editable.removeSpan(characterStyle);
                }
            }

                        class ColorScheme {
                final int color;
                final Pattern pattern;

                ColorScheme(Pattern pattern, int i) {
                    this.pattern = pattern;
                    this.color = i;
                }
            }
        });
    }

    public void _setWallpaper() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(displayMetrics.widthPixels, displayMetrics.heightPixels, Bitmap.Config.ARGB_8888);
        this.ln_wall.draw(new Canvas(bitmapCreateBitmap));
        try {
            WallpaperManager.getInstance(getApplicationContext()).setBitmap(bitmapCreateBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finish();
    }

    private boolean is24HourFormat() {
        return DateFormat.is24HourFormat(this);
    }

    private int getRandomDrawableWW() {
        this.drawableName = "ww" + (new Random().nextInt(36) + 1);
        return getResources().getIdentifier(this.drawableName, "drawable", getPackageName());
    }

    private int getRandomDrawableGG() {
        return getResources().getIdentifier("gg" + (new Random().nextInt(50) + 1), "drawable", getPackageName());
    }

    public void _setFirstUI() {
        int i;
        HashMap<String, Object> map = new HashMap<>();
        this.m_color = map;
        map.put("c1", "#FFBE7D7C");
        this.m_color.put("c2", "#FFDB9796");
        this.m_color.put("c3", "#FFF8B2B0");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map2 = new HashMap<>();
        this.m_color = map2;
        map2.put("c1", "#FF97C1A9");
        this.m_color.put("c2", "#FFB2DDC4");
        this.m_color.put("c3", "#FFCEFAE0");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map3 = new HashMap<>();
        this.m_color = map3;
        map3.put("c1", "#FFBFB6A3");
        this.m_color.put("c2", "#FFDBD2BE");
        this.m_color.put("c3", "#FFF7EEDA");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map4 = new HashMap<>();
        this.m_color = map4;
        map4.put("c1", "#FFA6C4FF");
        this.m_color.put("c2", "#FFC0D5FF");
        this.m_color.put("c3", "#FFD9E6FF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map5 = new HashMap<>();
        this.m_color = map5;
        map5.put("c1", "#FF8ED2AA");
        this.m_color.put("c2", "#FFA9EEC5");
        this.m_color.put("c3", "#FFC5FFE1");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map6 = new HashMap<>();
        this.m_color = map6;
        map6.put("c1", "#FFE2B6E2");
        this.m_color.put("c2", "#FFFFD2FF");
        this.m_color.put("c3", "#FFFFEEFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map7 = new HashMap<>();
        this.m_color = map7;
        map7.put("c1", "#FFBCC2EA");
        this.m_color.put("c2", "#FFD8DEFF");
        this.m_color.put("c3", "#FFF5FAFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map8 = new HashMap<>();
        this.m_color = map8;
        map8.put("c1", "#FFCEC8A1");
        this.m_color.put("c2", "#FFEAE4BC");
        this.m_color.put("c3", "#FFFFFFD8");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map9 = new HashMap<>();
        this.m_color = map9;
        map9.put("c1", "#FFB99B84");
        this.m_color.put("c2", "#FFD5B69E");
        this.m_color.put("c3", "#FFF2D1B9");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map10 = new HashMap<>();
        this.m_color = map10;
        map10.put("c1", "#FFF8863D");
        this.m_color.put("c2", "#FFFFA157");
        this.m_color.put("c3", "#FFFFBC71");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map11 = new HashMap<>();
        this.m_color = map11;
        map11.put("c1", "#FF4DC1AA");
        this.m_color.put("c2", "#FF6BDDC5");
        this.m_color.put("c3", "#FF89FAE1");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map12 = new HashMap<>();
        this.m_color = map12;
        map12.put("c1", "#FF76B3F4");
        this.m_color.put("c2", "#FF93CEFF");
        this.m_color.put("c3", "#FFB0EBFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map13 = new HashMap<>();
        this.m_color = map13;
        map13.put("c1", "#FFEF755D");
        this.m_color.put("c2", "#FFFF9076");
        this.m_color.put("c3", "#FFFFAB90");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map14 = new HashMap<>();
        this.m_color = map14;
        map14.put("c1", "#FFFF6666");
        this.m_color.put("c2", "#FFFF827F");
        this.m_color.put("c3", "#FFFF9E99");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map15 = new HashMap<>();
        this.m_color = map15;
        map15.put("c1", "#FFA2997C");
        this.m_color.put("c2", "#FFBDB496");
        this.m_color.put("c3", "#FFD9CFB1");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map16 = new HashMap<>();
        this.m_color = map16;
        map16.put("c1", "#FFA0BBA2");
        this.m_color.put("c2", "#FFBBD7BD");
        this.m_color.put("c3", "#FFD7F3D9");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map17 = new HashMap<>();
        this.m_color = map17;
        map17.put("c1", "#FF9AFF85");
        this.m_color.put("c2", "#FFB7FFA0");
        this.m_color.put("c3", "#FFD5FFBC");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map18 = new HashMap<>();
        this.m_color = map18;
        map18.put("c1", "#FFFF72C8");
        this.m_color.put("c2", "#FFFF8EE4");
        this.m_color.put("c3", "#FFFFABFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map19 = new HashMap<>();
        this.m_color = map19;
        map19.put("c1", "#FFE156AD");
        this.m_color.put("c2", "#FFFF72C8");
        this.m_color.put("c3", "#FFFF8FE5");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map20 = new HashMap<>();
        this.m_color = map20;
        map20.put("c1", "#FFC989FC");
        this.m_color.put("c2", "#FFE7A4FF");
        this.m_color.put("c3", "#FFFFC0FF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map21 = new HashMap<>();
        this.m_color = map21;
        map21.put("c1", "#FFB785DF");
        this.m_color.put("c2", "#FFD3A0FC");
        this.m_color.put("c3", "#FFF0BBFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map22 = new HashMap<>();
        this.m_color = map22;
        map22.put("c1", "#FFB2BCA8");
        this.m_color.put("c2", "#FFCED8C3");
        this.m_color.put("c3", "#FFEAF4DF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map23 = new HashMap<>();
        this.m_color = map23;
        map23.put("c1", "#FF9B99E0");
        this.m_color.put("c2", "#FFB7B4FC");
        this.m_color.put("c3", "#FFD3CFFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map24 = new HashMap<>();
        this.m_color = map24;
        map24.put("c1", "#FFD092B7");
        this.m_color.put("c2", "#FFD0A6C0");
        this.m_color.put("c3", "#FFD0BBC8");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map25 = new HashMap<>();
        this.m_color = map25;
        map25.put("c1", "#FF64B8FF");
        this.m_color.put("c2", "#FF84D3FF");
        this.m_color.put("c3", "#FFA3EFFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map26 = new HashMap<>();
        this.m_color = map26;
        map26.put("c1", "#FFB3E3FF");
        this.m_color.put("c2", "#FFCFFFFF");
        this.m_color.put("c3", "#FFECFFFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map27 = new HashMap<>();
        this.m_color = map27;
        map27.put("c1", "#FFADACAB");
        this.m_color.put("c2", "#FFBEB1AB");
        this.m_color.put("c3", "#FFCFB7AB");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map28 = new HashMap<>();
        this.m_color = map28;
        map28.put("c1", "#FFD5A87F");
        this.m_color.put("c2", "#FFF3C399");
        this.m_color.put("c3", "#FFFFDFB4");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map29 = new HashMap<>();
        this.m_color = map29;
        map29.put("c1", "#FFFFB7E1");
        this.m_color.put("c2", "#FFFFD3FE");
        this.m_color.put("c3", "#FFFFF0FF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map30 = new HashMap<>();
        this.m_color = map30;
        map30.put("c1", "#ffcbcd69");
        this.m_color.put("c2", "#ffeced87");
        this.m_color.put("c3", "#ffeced87");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map31 = new HashMap<>();
        this.m_color = map31;
        map31.put("c1", "#ffa9844d");
        this.m_color.put("c2", "#ffc3a269");
        this.m_color.put("c3", "#ffd8c087");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map32 = new HashMap<>();
        this.m_color = map32;
        map32.put("c1", "#ffaf965f");
        this.m_color.put("c2", "#ffcfb47b");
        this.m_color.put("c3", "#ffe4cb9a");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map33 = new HashMap<>();
        this.m_color = map33;
        map33.put("c1", "#ffa39761");
        this.m_color.put("c2", "#ffc3b67e");
        this.m_color.put("c3", "#ffe3d59c");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> map34 = new HashMap<>();
        this.m_color = map34;
        map34.put("c1", "#ffc94139");
        this.m_color.put("c2", "#ffed5c53");
        this.m_color.put("c3", "#ffff7c70");
        this.lm_color.add(this.m_color);
        Collections.shuffle(this.lm_color);
        HashMap<String, Object> map35 = this.lm_color.get(0);
        double random = SketchwareUtil.getRandom(1, 3);
        this.f874n = random;
        if (1.0d == random) {
            this.f872c1 = map35.get("c1").toString();
            this.f873c2 = map35.get("c1").toString();
        } else if (2.0d == random) {
            this.f872c1 = map35.get("c2").toString();
            this.f873c2 = map35.get("c1").toString();
        } else {
            this.f872c1 = map35.get("c3").toString();
            this.f873c2 = map35.get("c1").toString();
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.ln_wall.setLayoutParams(new LinearLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels));
        float f = (-displayMetrics.heightPixels) / 2.7f;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.tv_vertical, Key.ROTATION, 0.0f, 90.0f);
        objectAnimatorOfFloat.setDuration(10L);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.tv_vertical, "translationX", 0.0f, f);
        objectAnimatorOfFloat2.setDuration(10L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        animatorSet.start();
        this.tv_vertical.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/line.ttf"), 0);
        this.tv_vertical.setTextSize(40.0f);
        this.tv_code.setText("");
        this.im_back.setImageResource(C0978R.drawable.wallpaper0);
        if (this.prefwall.getString("mode", "").equals("gg")) {
            this.btn_gg.setAlpha(0.5f);
            this.btn_ww.setAlpha(1.0f);
            this.btn_tt.setAlpha(1.0f);
            this.ln_code.setVisibility(4);
            this.ln_vertical.setVisibility(0);
            this.s_interstelar = "ACCRETION DISK\nANDROMEDA GALAXY\nAPHELION\nASTEROID\nASTROBIOLOGY\nASTROLABE\nASTROMETRY\nASTRONOMICAL UNIT\nASTROPARTICLE PHYSICS\nAURORA AUSTRALIS\nAURORA BOREALIS\nBLACK HOLE\nBLUESHIFT\nBROWN DWARF\nCELESTIAL BODY\nCELESTIAL COORDINATES\nCELESTIAL EQUATOR\nCELESTIAL MECHANICS\nCELESTIAL SPHERE\nCHANDRASEKHAR LIMIT\nCOMET\nCOMOVING DISTANCE\nCONSTELLATION\nCORONA\nCORONAL MASS EJECTION\nCOSMIC DUST\nCOSMIC DUST GRAIN\nCOSMIC INFLATION\nCOSMIC MICROWAVE\nCOSMIC RAY\nCOSMIC STRINGS\nCOSMIC WEB\nCOSMOGONY\nCOSMOLOGICAL CONSTANT\nCOSMOLOGY\nDARK ENERGY\nDARK MATTER\nDARK NEBULA\nDARK SKY RESERVE\nDWARF PLANET\nECCENTRICITY\nELECTROMAGNETIC SPECTRUM\nELLIPTICAL GALAXY\nELLIPTICAL ORBIT\nEVENT HORIZON\nEVENT HORIZON TELESCOPE\nEXOPLANET\nGALACTIC BULGE\nGALACTIC CLUSTER\nGALACTIC HALO\nGALAXY\nGAMMA-RAY BURST\nGAS GIANT\nGEOCENTRIC MODEL\nGEOSYNCHRONOUS ORBIT\nGIANT IMPACT HYPOTHESIS\nGIANT MOLECULAR CLOUD\nGRAVITATIONAL CONSTANT\nGRAVITATIONAL LENS\nGRAVITATIONAL WAVE\nGREAT RED SPOT\nHAWKING RADIATION\nHELIOCENTRIC MODEL\nHELIOCHROMOLOGY\nHELIOSEISMOLOGY\nHELIOSPHERE\nHELIOSYNCHRONOUS ORBIT\nHERTZSPRUNG-RUSSELL DIAGRAM\nHUBBLE SPACE TELESCOPE\nINTERFEROMETRY\nINTERPLANETARY DUST\nINTERPLANETARY MAGNETIC\nINTERPLANETARY MEDIUM\nINTERSTELLAR MEDIUM\nINTERSTELLAR REDDENING\nKUIPER BELT\nLAGRANGE POINT\nLIGHT POLLUTION\nLIGHT-YEAR\nLOCAL GROUP\nLUMINOSITY\nLUMINOSITY CLASS\nLUNAR ECLIPSE\nLYMAN-ALPHA FOREST\nMAGNETAR\nMAGNETOHYDRODYNAMICS\nMAGNETOPAUSE\nMAGNETOSPHERE\nMICROGRAVITY\nMILKY WAY\nNEBULA\nOORT CLOUD\nORBIT\nPARALLAX\nPARSEC\nPENUMBRAL ECLIPSE\nPERIHELION\nPERSEID METEOR SHOWER\nPHOTOSPHERE\nPLANETARY DIFFERENTIATION\nPLANETARY NEBULA\nPLANETARY PROTECTION\nPLANETARY RING\nPLANETARY SYSTEM\nPOLARIMETER\nPROGRADE MOTION\nPROTOPLANETARY DISK\nPULSAR\nQUASAR\nRADIO GALAXY\nRED GIANT\nREDSHIFT\nRETROGRADE MOTION\nROCHE LIMIT\nROCHE LOBE\nSOLAR CYCLE\nSOLAR ECLIPSE\nSOLAR FLARE\nSOLAR SYSTEM\nSOLAR WIND\nSPECTROSCOPY\nSPIRAL GALAXY\nSTELLAR EVOLUTION\nSTELLAR NURSERY\nSTELLAR PARALLAX\nSUPERMASSIVE BLACK HOLE\nSUPERNOVA\nSYNCHROTRON RADIATION\nTELESCOPE\nTERRESTRIAL PLANET\nTIDAL FORCE\nTIDAL LOCKING\nTRANS-NEPTUNIAN OBJECT\nTRANSITS OF VENUS\nTYCHO SUPERNOVA REMNANT\nVARIABLE STAR\nWHITE DWARF\nX-RAY BINARY\nZENITH\nZODIACAL LIGHT\nBACKGROUND RADIATION\nPLANETARY MOTION";
            this.ls_interstelar = new ArrayList<>(Arrays.asList(this.s_interstelar.split("\n")));
            this.s_interstelar = this.ls_interstelar.get(new Random().nextInt(this.ls_interstelar.size()));
            this.im_wall.setImageResource(getRandomDrawableGG());
            this.im_back.setColorFilter(Color.parseColor("#FF000000"), PorterDuff.Mode.MULTIPLY);
            this.im_wall.setColorFilter(Color.parseColor(this.f872c1), PorterDuff.Mode.MULTIPLY);
            this.tv_vertical.setText(this.s_interstelar);
            this.tv_vertical.setTranslationY(0.0f);
        } else if (this.prefwall.getString("mode", "").equals("tt")) {
            this.btn_tt.setAlpha(0.5f);
            this.btn_gg.setAlpha(1.0f);
            this.btn_ww.setAlpha(1.0f);
            _setHighLighter(this.tv_code);
            this.ln_code.setVisibility(0);
            this.ln_vertical.setVisibility(4);
            this.s_interstelar = "xxxxxx\n\n// life motto\n\nwhile (me.Alive) {\n\tme.WakeUp();\n\tme.Eat();\n\tme.DoCode();\n\tme.Sleep();\n}\nxxxxxx\n\n// life motto\n\nif (sad() = true) {\n\tsad().stop();\n\tbeAwesome();\n}\nxxxxxx\n\n// life motto\n\nwhile (true) {\n\tbitches.fuck() ;\n\tmoney.get();\n}\nxxxxxx\n\n// life motto\n\nif (me != success) {\n\tpoverty.get() ;\n} else {\n\tlife.great();\n\tmoney.get();\n\tlive.myDream();\n}\nxxxxxx\n\n// life motto\n\nLife myLife = new Life();\nmyLife.startLife();\nwhile(!myLife.makeSuccess()) {\n\tmyLife.tryAgain();\n\tif (myLife.death()) {\n\t\tbreak;\n\t}\n}\nxxxxxx\n\n// time is running\n\nt = 0\nwhile True:\nprint(\" Nothing lasts forever. \")\nt += 1\nxxxxxx\n\n// inspiration\n\n#include <inspiration.h>\nart me(her) {\n\tif(sheSmiles == true) {\n\t\treturn poetry;\n\t}\n}\nxxxxxx\n\n// bug\n\nwhile (fixingBugs()) {\n\tcode();\n\tdeepBreath();\n\tkeepCalm();\n}\nxxxxxx\n\n// i am tired\n\nif (tired) {\n\ttakeNap();\n\tcode();\n\trepeat();\n}\nxxxxxx\n\n// i am hungry\n\nif (hungry) {\n\torderPizza();\n\tcode();\n\tenjoySlice();\n}\nxxxxxx\n\n// i am on fire\n\nif (feeling Productive()) {\n\tcode();\n\tpatSelfOnBack();\n\tplanWorldDomination();\n}\nxxxxxx\n\n// ngelu\n\nwhile (brainstorming()) {\n\tcode();\n\ttakeCups0fCoffee();\n\twaitForIdeas();\n}\nxxxxxx\n\n// mumet\n\nwhile (ndasMumet()) {\n\ttry {\n\t\tududDulu();\n\t\tngopiDulu();\n\t\tnungguIde();\n\t}\n}\nxxxxxx\n\n// jangan sedih\n\nwhile (sedih = true) {\n\ttry {\n\t\tsedih.stop();\n\t\tsenyum.start();\n\t\tberbahagialah();\n\t}\n}\nxxxxxx\n\n// ngantuk\n\nwhile (levelKopi == 0) {\n\ttry {\n\t\tambilCangkir();\n\t\tbikinLagi();\n\t\tsruput.start();\n\t}\n}\nxxxxxx\n\n// kepising\n\nwhile (mules2 == 0) {\n\ttry {\n\t\tngising();\n\t\tngising();\n\t\tngising();\n\t\tndang_ngising();\n\t}\n}\nxxxxxx\n\n// it's up to you\n\nif (hardwork) == true) {\n\tsuccess();\n} else { \n\tstruggle();\n}\nxxxxxx\n\n// algorithm of success\n\n#include <life.h>\nwhile (!success) {\n\ttryAgain();\n\tif (success) {\n\t\timprove();\n\t}\n}\nxxxxxx\n\n// life motto\n\nwhile (noSuccess) {\n\ttryAgain();\n\tif (dead) {\n\t\tbreak;\n\t}\n}\nxxxxxx\n\n// about coffee\n\nCoffee coffee = new Coffee();\nif (coffee.Empty) {\n\tcoffee.Refill();\n} else {\n\tcoffee.Drink();\n}\nxxxxxx\n\npublic class algorithmOfSuccess() {\n\t// algorithm of success\n\tpublic static void main(String[] args) {\n\t\twhile (!success) {\n\t\t\ttryAgain();\n\t\t\tif (dead)\n\t\t\tbreak;\n\t\t}\n\t}\n}\npublic static void tryAgain() {\n\tsuccess = confidence && hardwork;\n}\nxxxxxx\n\n// happiness is\n\nwhen (your.code()) {\n\truns.without();\n\terrors();\n}\nxxxxxx\n\n// bahagia adalah\n\nwhen (rejeki.kamu()) {\n\tmengalir.dengan();\n\tlancar();\n}\nxxxxxx\n\n// life motto\n\nif (saldo.equals, \"nol\") {\n    sad = false;\n    kerja.lagi();\n\tbissmillah();\n}\nxxxxxx\n\n// life motto\n\nif (feeling.equals, \"positive\") {\n    be = yourSelf;\n}\nxxxxxx\n\nalways:\ntry {\n\tyour best and;\n\tdo {\n\t\twhat you need to do;\n\t} while (you still have the time);\n\tfor (opportunity; comes; only once) {\n\t\tso grab the chance;\n\t}\n}\nif (you fail)\nthrow \"all your worries\";\ncatch (yourself) {\n\teverytime you fall;\n\tgoto always;\n}\n";
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(this.s_interstelar.split("xxxxxx")));
            this.ls_interstelar = arrayList;
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                if (it.next().isEmpty()) {
                    it.remove();
                }
            }
            this.s_interstelar = this.ls_interstelar.get(new Random().nextInt(this.ls_interstelar.size()));
            getRandomDrawableGG();
            this.im_wall.setImageResource(C0978R.drawable.wallpaper0);
            this.im_back.setColorFilter(Color.parseColor(this.f873c2), PorterDuff.Mode.MULTIPLY);
            this.im_wall.setColorFilter(Color.parseColor("#dd000000"), PorterDuff.Mode.MULTIPLY);
            this.tv_code.setText(this.s_interstelar);
        } else {
            this.btn_ww.setAlpha(0.5f);
            this.btn_gg.setAlpha(1.0f);
            this.btn_tt.setAlpha(1.0f);
            this.ln_code.setVisibility(4);
            this.ln_vertical.setVisibility(0);
            this.im_wall.setImageResource(getRandomDrawableWW());
            this.im_back.setColorFilter(Color.parseColor(this.f872c1), PorterDuff.Mode.MULTIPLY);
            this.im_wall.setColorFilter(Color.parseColor(this.f872c1), PorterDuff.Mode.MULTIPLY);
            if (this.drawableName.equals("ww1")) {
                this.tv_vertical.setText("Province Aceh");
            }
            if (this.drawableName.equals("ww2")) {
                this.tv_vertical.setText("Province Bali");
            }
            if (this.drawableName.equals("ww3")) {
                this.tv_vertical.setText("Province Banten");
            }
            if (this.drawableName.equals("ww4")) {
                this.tv_vertical.setText("Province Gorontalo");
            }
            if (this.drawableName.equals("ww5")) {
                this.tv_vertical.setText("Province Jakarta");
            }
            if (this.drawableName.equals("ww6")) {
                this.tv_vertical.setText("Province Jambi");
            }
            if (this.drawableName.equals("ww7")) {
                this.tv_vertical.setText("Jawa Barat");
            }
            if (this.drawableName.equals("ww8")) {
                this.tv_vertical.setText("Jawa Tengah");
            }
            if (this.drawableName.equals("ww9")) {
                this.tv_vertical.setText("Jawa Timur");
            }
            if (this.drawableName.equals("ww10")) {
                this.tv_vertical.setText("Kalimantan Selatan");
            }
            if (this.drawableName.equals("ww11")) {
                this.tv_vertical.setText("Kalimantan Tengah");
            }
            if (this.drawableName.equals("ww12")) {
                this.tv_vertical.setText("Kalimantan Timur");
            }
            if (this.drawableName.equals("ww13")) {
                this.tv_vertical.setText("Bangka Belitung");
            }
            if (this.drawableName.equals("ww14")) {
                this.tv_vertical.setText("Province Lampung");
            }
            if (this.drawableName.equals("ww15")) {
                this.tv_vertical.setText("Maluku Utara");
            }
            if (this.drawableName.equals("ww16")) {
                this.tv_vertical.setText("Province Maluku");
            }
            if (this.drawableName.equals("ww17")) {
                this.tv_vertical.setText("Nusa Tenggara Barat");
            }
            if (this.drawableName.equals("ww18")) {
                this.tv_vertical.setText("Nusa Tenggara Timur");
            }
            if (this.drawableName.equals("ww19")) {
                this.tv_vertical.setText("Papua Barat");
            }
            if (this.drawableName.equals("ww20")) {
                this.tv_vertical.setText("Province Riau");
            }
            if (this.drawableName.equals("ww21")) {
                this.tv_vertical.setText("Sulawesi Barat");
            }
            if (this.drawableName.equals("ww22")) {
                this.tv_vertical.setText("Sulawesi Selatan");
            }
            if (this.drawableName.equals("ww23")) {
                this.tv_vertical.setText("Sulawesi Tengah");
            }
            if (this.drawableName.equals("ww24")) {
                this.tv_vertical.setText("Sulawesi Tenggara");
            }
            if (this.drawableName.equals("ww25")) {
                this.tv_vertical.setText("Sulawesi Utara");
            }
            if (this.drawableName.equals("ww26")) {
                this.tv_vertical.setText("Sumatera Barat");
            }
            if (this.drawableName.equals("ww27")) {
                this.tv_vertical.setText("Sumatera Selatan");
            }
            if (this.drawableName.equals("ww28")) {
                this.tv_vertical.setText("Province Yogyakarta");
            }
            if (this.drawableName.equals("ww29")) {
                this.tv_vertical.setText("Kalimantan Utara");
            }
            if (this.drawableName.equals("ww30")) {
                this.tv_vertical.setText("Pulau Kalimantan");
            }
            if (this.drawableName.equals("ww31")) {
                this.tv_vertical.setText("Province Bengkulu");
            }
            if (this.drawableName.equals("ww32")) {
                this.tv_vertical.setText("Western Indonesia");
            }
            if (this.drawableName.equals("ww33")) {
                this.tv_vertical.setText("Middle Indonesia");
            }
            if (this.drawableName.equals("ww34")) {
                this.tv_vertical.setText("Eastern Indonesia");
            }
            if (this.drawableName.equals("ww35")) {
                this.tv_vertical.setText("Republic Of Indonesia");
            }
            if (this.drawableName.equals("ww36")) {
                this.tv_vertical.setText("Republic Of Indonesia");
            }
        }
        Calendar calendar = Calendar.getInstance();
        if (is24HourFormat()) {
            i = calendar.get(11);
        } else {
            int i2 = calendar.get(10);
            i = calendar.get(9) == 1 ? i2 + 12 : i2;
        }
        if (i >= 4 && i < 9) {
            this.im_mask.setImageResource(C0978R.drawable.wallpaper4);
        } else if (i >= 9 && i < 16) {
            this.im_mask.setImageResource(C0978R.drawable.wallpaper9);
        } else if (i >= 16 && i < 18) {
            this.im_mask.setImageResource(C0978R.drawable.wallpaper16);
        } else {
            this.im_mask.setImageResource(C0978R.drawable.wallpaper1);
        }
        if (getIntent().hasExtra("setwall")) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            startActivity(intent);
            this.ln_mode.setVisibility(8);
            new Handler().postDelayed(new Runnable() {                 @Override // java.lang.Runnable
                public void run() {
                    WallActivity.this._setWallpaper();
                }
            }, 200L);
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