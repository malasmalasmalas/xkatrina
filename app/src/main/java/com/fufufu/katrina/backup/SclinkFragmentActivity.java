package com.fufufu.katrina.backup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class SclinkFragmentActivity extends Fragment {
    private FloatingActionButton _fab;
    private AutoCompleteTextView auto_link;
    private AutoCompleteTextView auto_shortcut;
    private LinearLayout color_0;
    private LinearLayout color_1;
    private LinearLayout color_10;
    private LinearLayout color_11;
    private LinearLayout color_12;
    private LinearLayout color_13;
    private LinearLayout color_14;
    private LinearLayout color_2;
    private LinearLayout color_3;
    private LinearLayout color_4;
    private LinearLayout color_5;
    private LinearLayout color_6;
    private LinearLayout color_7;
    private LinearLayout color_8;
    private LinearLayout color_9;
    private FrameLayout fr_icon;
    private HorizontalScrollView hscr_1;
    private HorizontalScrollView hscr_2;
    private HorizontalScrollView hscr_3;
    private ImageView icon_0;
    private ImageView icon_1;
    private ImageView icon_2;
    private ImageView icon_3;
    private ImageView icon_4;
    private ImageView icon_5;
    private ImageView icon_6;
    private ImageView icon_7;
    private ImageView icon_8;
    private ImageView im_icon;
    private ImageView im_more;
    private ImageView im_shape;
    private LinearLayout ln_changecolor;
    private LinearLayout ln_changeicon;
    private LinearLayout ln_changeshape;
    private LinearLayout ln_customicon;
    private LinearLayout ln_icon;
    private LinearLayout ln_link_base;
    private LinearLayout ln_url;
    private ImageView shape_0;
    private ImageView shape_1;
    private ImageView shape_2;
    private ImageView shape_3;
    private ImageView shape_4;
    private ImageView shape_5;
    private ImageView shape_6;
    private ImageView shape_7;
    private ImageView shape_8;
    private TextInputLayout til_1;
    private TextInputLayout til_2;
    private TextView tv_shortcut_tool;
    private String s_text = "";
    private String s_name = "";
    private String s_desc = "";
    private String s_icon = "";
    private String s_tint = "";

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C0978R.layout.sclink_fragment, viewGroup, false);
        initialize(bundle, viewInflate);
        initializeLogic();
        return viewInflate;
    }

    private void initialize(Bundle bundle, View view) {
        this._fab = (FloatingActionButton) view.findViewById(C0978R.id._fab);
        this.ln_link_base = (LinearLayout) view.findViewById(C0978R.id.ln_link_base);
        this.ln_icon = (LinearLayout) view.findViewById(C0978R.id.ln_icon);
        this.tv_shortcut_tool = (TextView) view.findViewById(C0978R.id.tv_shortcut_tool);
        this.ln_customicon = (LinearLayout) view.findViewById(C0978R.id.ln_customicon);
        this.ln_url = (LinearLayout) view.findViewById(C0978R.id.ln_url);
        this.fr_icon = (FrameLayout) view.findViewById(C0978R.id.fr_icon);
        this.im_more = (ImageView) view.findViewById(C0978R.id.im_more);
        this.im_shape = (ImageView) view.findViewById(C0978R.id.im_shape);
        this.im_icon = (ImageView) view.findViewById(C0978R.id.im_icon);
        this.hscr_2 = (HorizontalScrollView) view.findViewById(C0978R.id.hscr_2);
        this.hscr_3 = (HorizontalScrollView) view.findViewById(C0978R.id.hscr_3);
        this.hscr_1 = (HorizontalScrollView) view.findViewById(C0978R.id.hscr_1);
        this.ln_changeicon = (LinearLayout) view.findViewById(C0978R.id.ln_changeicon);
        this.icon_0 = (ImageView) view.findViewById(C0978R.id.icon_0);
        this.icon_1 = (ImageView) view.findViewById(C0978R.id.icon_1);
        this.icon_2 = (ImageView) view.findViewById(C0978R.id.icon_2);
        this.icon_3 = (ImageView) view.findViewById(C0978R.id.icon_3);
        this.icon_4 = (ImageView) view.findViewById(C0978R.id.icon_4);
        this.icon_5 = (ImageView) view.findViewById(C0978R.id.icon_5);
        this.icon_6 = (ImageView) view.findViewById(C0978R.id.icon_6);
        this.icon_7 = (ImageView) view.findViewById(C0978R.id.icon_7);
        this.icon_8 = (ImageView) view.findViewById(C0978R.id.icon_8);
        this.ln_changeshape = (LinearLayout) view.findViewById(C0978R.id.ln_changeshape);
        this.shape_0 = (ImageView) view.findViewById(C0978R.id.shape_0);
        this.shape_1 = (ImageView) view.findViewById(C0978R.id.shape_1);
        this.shape_2 = (ImageView) view.findViewById(C0978R.id.shape_2);
        this.shape_3 = (ImageView) view.findViewById(C0978R.id.shape_3);
        this.shape_4 = (ImageView) view.findViewById(C0978R.id.shape_4);
        this.shape_5 = (ImageView) view.findViewById(C0978R.id.shape_5);
        this.shape_6 = (ImageView) view.findViewById(C0978R.id.shape_6);
        this.shape_7 = (ImageView) view.findViewById(C0978R.id.shape_7);
        this.shape_8 = (ImageView) view.findViewById(C0978R.id.shape_8);
        this.ln_changecolor = (LinearLayout) view.findViewById(C0978R.id.ln_changecolor);
        this.color_0 = (LinearLayout) view.findViewById(C0978R.id.color_0);
        this.color_1 = (LinearLayout) view.findViewById(C0978R.id.color_1);
        this.color_2 = (LinearLayout) view.findViewById(C0978R.id.color_2);
        this.color_3 = (LinearLayout) view.findViewById(C0978R.id.color_3);
        this.color_4 = (LinearLayout) view.findViewById(C0978R.id.color_4);
        this.color_5 = (LinearLayout) view.findViewById(C0978R.id.color_5);
        this.color_6 = (LinearLayout) view.findViewById(C0978R.id.color_6);
        this.color_7 = (LinearLayout) view.findViewById(C0978R.id.color_7);
        this.color_8 = (LinearLayout) view.findViewById(C0978R.id.color_8);
        this.color_9 = (LinearLayout) view.findViewById(C0978R.id.color_9);
        this.color_10 = (LinearLayout) view.findViewById(C0978R.id.color_10);
        this.color_11 = (LinearLayout) view.findViewById(C0978R.id.color_11);
        this.color_12 = (LinearLayout) view.findViewById(C0978R.id.color_12);
        this.color_13 = (LinearLayout) view.findViewById(C0978R.id.color_13);
        this.color_14 = (LinearLayout) view.findViewById(C0978R.id.color_14);
        this.til_2 = (TextInputLayout) view.findViewById(C0978R.id.til_2);
        this.til_1 = (TextInputLayout) view.findViewById(C0978R.id.til_1);
        this.auto_shortcut = (AutoCompleteTextView) view.findViewById(C0978R.id.auto_shortcut);
        this.auto_link = (AutoCompleteTextView) view.findViewById(C0978R.id.auto_link);
        this.im_more.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SclinkFragmentActivity.this.ln_customicon.getVisibility() == 8) {
                    SclinkFragmentActivity.this.ln_customicon.setVisibility(0);
                    SclinkFragmentActivity.this.ln_url.setVisibility(8);
                } else {
                    SclinkFragmentActivity.this.ln_customicon.setVisibility(8);
                    SclinkFragmentActivity.this.ln_url.setVisibility(0);
                }
            }
        });
        this.icon_0.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_icon.setImageResource(C0978R.drawable.ic_link_0);
            }
        });
        this.icon_1.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_icon.setImageResource(C0978R.drawable.ic_link_1);
            }
        });
        this.icon_2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_icon.setImageResource(C0978R.drawable.ic_link_2);
            }
        });
        this.icon_3.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_icon.setImageResource(C0978R.drawable.ic_link_3);
            }
        });
        this.icon_4.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_icon.setImageResource(C0978R.drawable.ic_link_4);
            }
        });
        this.icon_5.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_icon.setImageResource(C0978R.drawable.ic_link_5);
            }
        });
        this.icon_6.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_icon.setImageResource(C0978R.drawable.ic_link_6);
            }
        });
        this.icon_7.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_icon.setImageResource(C0978R.drawable.ic_link_7);
            }
        });
        this.icon_8.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_icon.setImageResource(C0978R.drawable.ic_link_8);
            }
        });
        this.shape_0.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_shape.setImageResource(C0978R.drawable.shape_0);
            }
        });
        this.shape_1.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_shape.setImageResource(C0978R.drawable.shape_1);
            }
        });
        this.shape_2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_shape.setImageResource(C0978R.drawable.shape_2);
            }
        });
        this.shape_3.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_shape.setImageResource(C0978R.drawable.shape_3);
            }
        });
        this.shape_4.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_shape.setImageResource(C0978R.drawable.shape_4);
            }
        });
        this.shape_5.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_shape.setImageResource(C0978R.drawable.shape_5);
            }
        });
        this.shape_6.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_shape.setImageResource(C0978R.drawable.shape_6);
            }
        });
        this.shape_7.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_shape.setImageResource(C0978R.drawable.shape_7);
            }
        });
        this.shape_8.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.im_shape.setImageResource(C0978R.drawable.shape_8);
            }
        });
        this.color_1.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.s_tint = "#FFAECAFD";
                SclinkFragmentActivity.this._refreshIconShape();
            }
        });
        this.color_0.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.s_tint = "#FFFFFFFF";
                SclinkFragmentActivity.this._refreshIconShape();
            }
        });
        this.color_2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.s_tint = "#FF8BCDEF";
                SclinkFragmentActivity.this._refreshIconShape();
            }
        });
        this.color_3.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.s_tint = "#FF8ECCD7";
                SclinkFragmentActivity.this._refreshIconShape();
            }
        });
        this.color_4.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.s_tint = "#FFC6F1CB";
                SclinkFragmentActivity.this._refreshIconShape();
            }
        });
        this.color_5.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.s_tint = "#FFFFECB7";
                SclinkFragmentActivity.this._refreshIconShape();
            }
        });
        this.color_6.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.s_tint = "#FFF1BC99";
                SclinkFragmentActivity.this._refreshIconShape();
            }
        });
        this.color_7.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.s_tint = "#FFFFB2B2";
                SclinkFragmentActivity.this._refreshIconShape();
            }
        });
        this.color_8.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.s_tint = "#FFFEC5E9";
                SclinkFragmentActivity.this._refreshIconShape();
            }
        });
        this.color_9.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.s_tint = "#FFB7BDFB";
                SclinkFragmentActivity.this._refreshIconShape();
            }
        });
        this.color_10.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.s_tint = "#FFD1B7FB";
                SclinkFragmentActivity.this._refreshIconShape();
            }
        });
        this.color_11.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.s_tint = "#FFE4D7A9";
                SclinkFragmentActivity.this._refreshIconShape();
            }
        });
        this.color_12.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.s_tint = "#FFD7D7D7";
                SclinkFragmentActivity.this._refreshIconShape();
            }
        });
        this.color_13.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.s_tint = "#FFD3B684";
                SclinkFragmentActivity.this._refreshIconShape();
            }
        });
        this.color_14.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this.s_tint = "#FFFAF0E0";
                SclinkFragmentActivity.this._refreshIconShape();
            }
        });
        this.auto_shortcut.addTextChangedListener(new TextWatcher() {             @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SclinkFragmentActivity.this.tv_shortcut_tool.setText(charSequence.toString());
            }
        });
        this._fab.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SclinkFragmentActivity.this._createShortcutLink();
            }
        });
    }

    private void initializeLogic() {
        _setFirstUI();
    }

    public void _createShortcut(String str, String str2, String str3, String str4) {
        Intent intent = new Intent(requireContext(), (Class<?>) ShortcutExecutorActivity.class);
        intent.setAction("android.intent.action.MAIN");
        intent.putExtra("shortcut_command", str);
        intent.putExtra("shortcut_desc", str3);
        Intent intent2 = new Intent();
        intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
        intent2.putExtra("android.intent.extra.shortcut.NAME", str2);
        try {
            this.fr_icon.setDrawingCacheEnabled(true);
            this.fr_icon.buildDrawingCache(true);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.fr_icon.getDrawingCache());
            this.fr_icon.setDrawingCacheEnabled(false);
            intent2.putExtra("android.intent.extra.shortcut.ICON", bitmapCreateBitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
            intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getContext().getApplicationContext(), C0978R.drawable.app_icon));
        }
        intent2.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        requireContext().sendBroadcast(intent2);
        getActivity().setResult(-1, intent2);
        getActivity().finish();
    }

    public void _createShortcutLink() {
        if (this.auto_link.getText().toString().equals("") || this.auto_shortcut.getText().toString().equals("")) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Mohon lengkapi data");
            return;
        }
        this.s_text = this.auto_link.getText().toString();
        String string = this.auto_shortcut.getText().toString();
        this.s_name = string;
        this.s_desc = "openlink";
        this.s_icon = "link";
        _createShortcut(this.s_text, string, "openlink", "link");
    }

    /* JADX WARN: Type inference failed for: r1v11, types: [com.fufufu.katrina.backup.SclinkFragmentActivity$39] */
    /* JADX WARN: Type inference failed for: r1v13, types: [com.fufufu.katrina.backup.SclinkFragmentActivity$40] */
    /* JADX WARN: Type inference failed for: r1v15, types: [com.fufufu.katrina.backup.SclinkFragmentActivity$41] */
    /* JADX WARN: Type inference failed for: r1v17, types: [com.fufufu.katrina.backup.SclinkFragmentActivity$42] */
    /* JADX WARN: Type inference failed for: r1v19, types: [com.fufufu.katrina.backup.SclinkFragmentActivity$43] */
    /* JADX WARN: Type inference failed for: r1v21, types: [com.fufufu.katrina.backup.SclinkFragmentActivity$44] */
    /* JADX WARN: Type inference failed for: r1v23, types: [com.fufufu.katrina.backup.SclinkFragmentActivity$45] */
    /* JADX WARN: Type inference failed for: r1v25, types: [com.fufufu.katrina.backup.SclinkFragmentActivity$46] */
    /* JADX WARN: Type inference failed for: r1v27, types: [com.fufufu.katrina.backup.SclinkFragmentActivity$47] */
    /* JADX WARN: Type inference failed for: r1v29, types: [com.fufufu.katrina.backup.SclinkFragmentActivity$48] */
    /* JADX WARN: Type inference failed for: r1v31, types: [com.fufufu.katrina.backup.SclinkFragmentActivity$49] */
    /* JADX WARN: Type inference failed for: r1v33, types: [com.fufufu.katrina.backup.SclinkFragmentActivity$50] */
    /* JADX WARN: Type inference failed for: r1v35, types: [com.fufufu.katrina.backup.SclinkFragmentActivity$51] */
    /* JADX WARN: Type inference failed for: r1v9, types: [com.fufufu.katrina.backup.SclinkFragmentActivity$38] */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.fufufu.katrina.backup.SclinkFragmentActivity$37] */
    public void _setFirstUI() {
        this.s_tint = "#FFFFFFFF";
        this.ln_customicon.setVisibility(8);
        this.ln_url.setVisibility(0);
        this.im_icon.setScaleX(0.7f);
        this.im_icon.setScaleY(0.7f);
        this.im_icon.setImageResource(C0978R.drawable.ic_link_5);
        this.im_shape.setImageResource(C0978R.drawable.shape_1);
        this.auto_link.setInputType(1);
        this.auto_shortcut.setInputType(1);
        this.color_0.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFFFFFFF")));
        this.color_1.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFAECAFD")));
        this.color_2.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FF8BCDEF")));
        this.color_3.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FF8ECCD7")));
        this.color_4.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFC6F1CB")));
        this.color_5.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFFFECB7")));
        this.color_6.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFF1BC99")));
        this.color_7.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFFFB2B2")));
        this.color_8.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFFEC5E9")));
        this.color_9.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFB7BDFB")));
        this.color_10.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFD1B7FB")));
        this.color_11.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFE4D7A9")));
        this.color_12.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFD7D7D7")));
        this.color_13.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFD3B684")));
        this.color_14.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFFAF0E0")));
        this.icon_0.setImageResource(C0978R.drawable.ic_link_0);
        this.icon_1.setImageResource(C0978R.drawable.ic_link_1);
        this.icon_2.setImageResource(C0978R.drawable.ic_link_2);
        this.icon_3.setImageResource(C0978R.drawable.ic_link_3);
        this.icon_4.setImageResource(C0978R.drawable.ic_link_4);
        this.icon_5.setImageResource(C0978R.drawable.ic_link_5);
        this.icon_6.setImageResource(C0978R.drawable.ic_link_6);
        this.icon_7.setImageResource(C0978R.drawable.ic_link_7);
        this.icon_8.setImageResource(C0978R.drawable.ic_link_8);
        this.shape_0.setImageResource(C0978R.drawable.shape_0);
        this.shape_1.setImageResource(C0978R.drawable.shape_1);
        this.shape_2.setImageResource(C0978R.drawable.shape_2);
        this.shape_3.setImageResource(C0978R.drawable.shape_3);
        this.shape_4.setImageResource(C0978R.drawable.shape_4);
        this.shape_5.setImageResource(C0978R.drawable.shape_5);
        this.shape_6.setImageResource(C0978R.drawable.shape_6);
        this.shape_7.setImageResource(C0978R.drawable.shape_7);
        this.shape_8.setImageResource(C0978R.drawable.shape_8);
        this.shape_0.setColorFilter(Color.parseColor("#80000000"), PorterDuff.Mode.MULTIPLY);
        this.shape_1.setColorFilter(Color.parseColor("#80000000"), PorterDuff.Mode.MULTIPLY);
        this.shape_2.setColorFilter(Color.parseColor("#80000000"), PorterDuff.Mode.MULTIPLY);
        this.shape_3.setColorFilter(Color.parseColor("#80000000"), PorterDuff.Mode.MULTIPLY);
        this.shape_4.setColorFilter(Color.parseColor("#80000000"), PorterDuff.Mode.MULTIPLY);
        this.shape_5.setColorFilter(Color.parseColor("#80000000"), PorterDuff.Mode.MULTIPLY);
        this.shape_6.setColorFilter(Color.parseColor("#80000000"), PorterDuff.Mode.MULTIPLY);
        this.shape_7.setColorFilter(Color.parseColor("#80000000"), PorterDuff.Mode.MULTIPLY);
        this.shape_8.setColorFilter(Color.parseColor("#80000000"), PorterDuff.Mode.MULTIPLY);
    }

    public void _refreshIconShape() {
        this.im_shape.setColorFilter(Color.parseColor(this.s_tint), PorterDuff.Mode.MULTIPLY);
    }
}
