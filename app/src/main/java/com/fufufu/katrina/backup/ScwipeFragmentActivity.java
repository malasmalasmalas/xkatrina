package com.fufufu.katrina.backup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;

public class ScwipeFragmentActivity extends Fragment {
    private FloatingActionButton _fab;
    private AutoCompleteTextView auto_app_name;
    private AutoCompleteTextView auto_app_package;
    private Chip chip_wipe_manual;
    private Chip chip_wipe_root;
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
    private GridView grid_wipe_app;
    private HorizontalScrollView hscr_1;
    private ImageView im_icon;
    private ImageView im_more;
    private ImageView im_shape;
    private LinearLayout ln_button;
    private ChipGroup ln_chip_group;
    private LinearLayout ln_color;
    private LinearLayout ln_icon;
    private LinearLayout ln_wipe_desc;
    private SharedPreferences prefall;
    private TextInputLayout til_3;
    private TextInputLayout til_4;
    private TextView tv_shortcut_tool;
    private String s_text = "";
    private String s_name = "";
    private String s_desc = "";
    private String s_icon = "";
    private double n_pos = 0.0d;
    private String s_tint = "";
    private HashMap<String, Object> m_shape = new HashMap<>();
    private boolean b_grid = false;
    private String s_package = "";
    private boolean b_getapp = false;
    private boolean b_root = false;
    private String s_appname = "";
    private ArrayList<HashMap<String, Object>> lm_shape = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_allapp = new ArrayList<>();

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C0978R.layout.scwipe_fragment, viewGroup, false);
        initialize(bundle, viewInflate);
        initializeLogic();
        return viewInflate;
    }

    private void initialize(Bundle bundle, View view) {
        this._fab = (FloatingActionButton) view.findViewById(C0978R.id._fab);
        this.ln_wipe_desc = (LinearLayout) view.findViewById(C0978R.id.ln_wipe_desc);
        this.grid_wipe_app = (GridView) view.findViewById(C0978R.id.grid_wipe_app);
        this.hscr_1 = (HorizontalScrollView) view.findViewById(C0978R.id.hscr_1);
        this.ln_icon = (LinearLayout) view.findViewById(C0978R.id.ln_icon);
        this.tv_shortcut_tool = (TextView) view.findViewById(C0978R.id.tv_shortcut_tool);
        this.til_3 = (TextInputLayout) view.findViewById(C0978R.id.til_3);
        this.til_4 = (TextInputLayout) view.findViewById(C0978R.id.til_4);
        this.ln_button = (LinearLayout) view.findViewById(C0978R.id.ln_button);
        this.fr_icon = (FrameLayout) view.findViewById(C0978R.id.fr_icon);
        this.im_more = (ImageView) view.findViewById(C0978R.id.im_more);
        this.im_shape = (ImageView) view.findViewById(C0978R.id.im_shape);
        this.im_icon = (ImageView) view.findViewById(C0978R.id.im_icon);
        this.auto_app_name = (AutoCompleteTextView) view.findViewById(C0978R.id.auto_app_name);
        this.auto_app_package = (AutoCompleteTextView) view.findViewById(C0978R.id.auto_app_package);
        this.ln_chip_group = (ChipGroup) view.findViewById(C0978R.id.ln_chip_group);
        this.chip_wipe_manual = (Chip) view.findViewById(C0978R.id.chip_wipe_manual);
        this.chip_wipe_root = (Chip) view.findViewById(C0978R.id.chip_wipe_root);
        this.ln_color = (LinearLayout) view.findViewById(C0978R.id.ln_color);
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
        this.prefall = getContext().getSharedPreferences("all_app_preferences", 0);
        this.im_more.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (ScwipeFragmentActivity.this.b_grid) {
                    ScwipeFragmentActivity.this.b_grid = false;
                } else {
                    ScwipeFragmentActivity.this.b_grid = true;
                }
                ScwipeFragmentActivity.this._setGridData();
            }
        });
        this.chip_wipe_manual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScwipeFragmentActivity.this.b_root = false;
                } else {
                    ScwipeFragmentActivity.this.chip_wipe_root.setChecked(true);
                    ScwipeFragmentActivity.this.b_root = true;
                }
            }
        });
        this.chip_wipe_root.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScwipeFragmentActivity.this.b_root = true;
                } else {
                    ScwipeFragmentActivity.this.chip_wipe_manual.setChecked(true);
                    ScwipeFragmentActivity.this.b_root = false;
                }
            }
        });
        this.color_1.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScwipeFragmentActivity.this.s_tint = "#FFAECAFD";
                ScwipeFragmentActivity.this._refreshGridView();
            }
        });
        this.color_0.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScwipeFragmentActivity.this.s_tint = "#FFFFFFFF";
                ScwipeFragmentActivity.this._refreshGridView();
            }
        });
        this.color_2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScwipeFragmentActivity.this.s_tint = "#FF8BCDEF";
                ScwipeFragmentActivity.this._refreshGridView();
            }
        });
        this.color_3.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScwipeFragmentActivity.this.s_tint = "#FF8ECCD7";
                ScwipeFragmentActivity.this._refreshGridView();
            }
        });
        this.color_4.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScwipeFragmentActivity.this.s_tint = "#FFC6F1CB";
                ScwipeFragmentActivity.this._refreshGridView();
            }
        });
        this.color_5.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScwipeFragmentActivity.this.s_tint = "#FFFFECB7";
                ScwipeFragmentActivity.this._refreshGridView();
            }
        });
        this.color_6.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScwipeFragmentActivity.this.s_tint = "#FFF1BC99";
                ScwipeFragmentActivity.this._refreshGridView();
            }
        });
        this.color_7.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScwipeFragmentActivity.this.s_tint = "#FFFFB2B2";
                ScwipeFragmentActivity.this._refreshGridView();
            }
        });
        this.color_8.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScwipeFragmentActivity.this.s_tint = "#FFFEC5E9";
                ScwipeFragmentActivity.this._refreshGridView();
            }
        });
        this.color_9.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScwipeFragmentActivity.this.s_tint = "#FFB7BDFB";
                ScwipeFragmentActivity.this._refreshGridView();
            }
        });
        this.color_10.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScwipeFragmentActivity.this.s_tint = "#FFD1B7FB";
                ScwipeFragmentActivity.this._refreshGridView();
            }
        });
        this.color_11.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScwipeFragmentActivity.this.s_tint = "#FFE4D7A9";
                ScwipeFragmentActivity.this._refreshGridView();
            }
        });
        this.color_12.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScwipeFragmentActivity.this.s_tint = "#FFD7D7D7";
                ScwipeFragmentActivity.this._refreshGridView();
            }
        });
        this.color_13.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScwipeFragmentActivity.this.s_tint = "#FFD3B684";
                ScwipeFragmentActivity.this._refreshGridView();
            }
        });
        this.color_14.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScwipeFragmentActivity.this.s_tint = "#FFFAF0E0";
                ScwipeFragmentActivity.this._refreshGridView();
            }
        });
        this._fab.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (ScwipeFragmentActivity.this.b_getapp) {
                    ScwipeFragmentActivity.this._createShortcutWipe();
                } else {
                    SketchwareUtil.showMessage(ScwipeFragmentActivity.this.getContext().getApplicationContext(), getString(C0978R.string.toast_harap_pilih_aplikasi_dahulu));
                }
            }
        });
    }

    private void initializeLogic() {
        this.b_grid = false;
        this.b_getapp = false;
        _setFirstUI();
    }

    /* JADX WARN: Type inference failed for: r2v11, types: [com.fufufu.katrina.backup.ScwipeFragmentActivity$23] */
    /* JADX WARN: Type inference failed for: r2v13, types: [com.fufufu.katrina.backup.ScwipeFragmentActivity$24] */
    /* JADX WARN: Type inference failed for: r2v15, types: [com.fufufu.katrina.backup.ScwipeFragmentActivity$25] */
    /* JADX WARN: Type inference failed for: r2v17, types: [com.fufufu.katrina.backup.ScwipeFragmentActivity$26] */
    /* JADX WARN: Type inference failed for: r2v19, types: [com.fufufu.katrina.backup.ScwipeFragmentActivity$27] */
    /* JADX WARN: Type inference failed for: r2v21, types: [com.fufufu.katrina.backup.ScwipeFragmentActivity$28] */
    /* JADX WARN: Type inference failed for: r2v23, types: [com.fufufu.katrina.backup.ScwipeFragmentActivity$29] */
    /* JADX WARN: Type inference failed for: r2v25, types: [com.fufufu.katrina.backup.ScwipeFragmentActivity$30] */
    /* JADX WARN: Type inference failed for: r2v27, types: [com.fufufu.katrina.backup.ScwipeFragmentActivity$31] */
    /* JADX WARN: Type inference failed for: r2v29, types: [com.fufufu.katrina.backup.ScwipeFragmentActivity$32] */
    /* JADX WARN: Type inference failed for: r2v31, types: [com.fufufu.katrina.backup.ScwipeFragmentActivity$33] */
    /* JADX WARN: Type inference failed for: r2v33, types: [com.fufufu.katrina.backup.ScwipeFragmentActivity$34] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.fufufu.katrina.backup.ScwipeFragmentActivity$20] */
    /* JADX WARN: Type inference failed for: r2v7, types: [com.fufufu.katrina.backup.ScwipeFragmentActivity$21] */
    /* JADX WARN: Type inference failed for: r2v9, types: [com.fufufu.katrina.backup.ScwipeFragmentActivity$22] */
    public void _setFirstUI() {
        this.s_tint = "#FFFAF0E0";
        this.s_package = getContext().getApplicationContext().getPackageName();
        this.chip_wipe_root.setChecked(true);
        this.hscr_1.setVisibility(8);
        this.im_icon.setVisibility(4);
        this.im_icon.setScaleX(0.7f);
        this.im_icon.setScaleY(0.7f);
        this.im_shape.setImageResource(C0978R.drawable.shape_1);
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
        this.lm_shape.clear();
        this.lm_allapp.clear();
        this.lm_allapp = (ArrayList) new Gson().fromJson(this.prefall.getString("all_app_list", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {         }.getType());
        this.grid_wipe_app.setAdapter((ListAdapter) new Grid_wipe_appAdapter(this.lm_allapp));
        HashMap<String, Object> map = new HashMap<>();
        this.m_shape = map;
        map.put("shape", "0");
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> map2 = new HashMap<>();
        this.m_shape = map2;
        map2.put("shape", "1");
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> map3 = new HashMap<>();
        this.m_shape = map3;
        map3.put("shape", ExifInterface.GPS_MEASUREMENT_2D);
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> map4 = new HashMap<>();
        this.m_shape = map4;
        map4.put("shape", ExifInterface.GPS_MEASUREMENT_3D);
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> map5 = new HashMap<>();
        this.m_shape = map5;
        map5.put("shape", "4");
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> map6 = new HashMap<>();
        this.m_shape = map6;
        map6.put("shape", "5");
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> map7 = new HashMap<>();
        this.m_shape = map7;
        map7.put("shape", "6");
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> map8 = new HashMap<>();
        this.m_shape = map8;
        map8.put("shape", "7");
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> map9 = new HashMap<>();
        this.m_shape = map9;
        map9.put("shape", "8");
        this.lm_shape.add(this.m_shape);
    }

    public void _setTextWipeData(String str, String str2) {
        this.b_getapp = true;
        this.s_package = str2;
        this.s_appname = str;
        _setMoreImage(str2);
        this.tv_shortcut_tool.setText(str);
        this.im_icon.setVisibility(0);
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

    public void _createShortcutWipe() {
        String str = this.s_package;
        this.s_text = str;
        String str2 = this.s_appname;
        this.s_name = str2;
        if (this.b_root) {
            this.s_desc = "wipe_root";
        } else {
            this.s_desc = "wipe_manual";
        }
        this.s_icon = "wipedata";
        _createShortcut(str, str2, this.s_desc, "wipedata");
    }

    public void _setNewShape() {
        double d = this.n_pos;
        if (d == 0.0d) {
            this.im_shape.setImageResource(C0978R.drawable.shape_0);
        } else if (d == 1.0d) {
            this.im_shape.setImageResource(C0978R.drawable.shape_1);
        } else if (d == 2.0d) {
            this.im_shape.setImageResource(C0978R.drawable.shape_2);
        } else if (d == 3.0d) {
            this.im_shape.setImageResource(C0978R.drawable.shape_3);
        } else if (d == 4.0d) {
            this.im_shape.setImageResource(C0978R.drawable.shape_4);
        } else if (d == 5.0d) {
            this.im_shape.setImageResource(C0978R.drawable.shape_5);
        } else if (d == 6.0d) {
            this.im_shape.setImageResource(C0978R.drawable.shape_6);
        } else if (d == 7.0d) {
            this.im_shape.setImageResource(C0978R.drawable.shape_7);
        } else if (d == 8.0d) {
            this.im_shape.setImageResource(C0978R.drawable.shape_8);
        } else {
            this.im_shape.setImageResource(C0978R.drawable.shape_1);
        }
        this.im_shape.setColorFilter(Color.parseColor(this.s_tint), PorterDuff.Mode.MULTIPLY);
    }

    public void _refreshGridView() {
        this.im_shape.setColorFilter(Color.parseColor(this.s_tint), PorterDuff.Mode.MULTIPLY);
        Grid_wipe_appAdapter grid_wipe_appAdapter = new Grid_wipe_appAdapter(this.lm_shape);
        this.grid_wipe_app.setAdapter((ListAdapter) grid_wipe_appAdapter);
        grid_wipe_appAdapter.notifyDataSetChanged();
    }

    public void _setMoreImage(String str) {
        try {
            this.im_icon.setImageDrawable(getActivity().getPackageManager().getApplicationIcon(str));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            this.im_icon.setImageResource(C0978R.drawable.app_icon);
        }
        this.im_shape.setColorFilter(Color.parseColor(this.s_tint), PorterDuff.Mode.MULTIPLY);
    }

    public void _setGridData() {
        if (this.b_grid) {
            this.grid_wipe_app.setAdapter((ListAdapter) new Grid_wipe_appAdapter(this.lm_shape));
            this.hscr_1.setVisibility(0);
        } else {
            this.grid_wipe_app.setAdapter((ListAdapter) new Grid_wipe_appAdapter(this.lm_allapp));
            this.hscr_1.setVisibility(8);
        }
    }

    public class Grid_wipe_appAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Grid_wipe_appAdapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this._data.size();
        }

        @Override // android.widget.Adapter
        public HashMap<String, Object> getItem(int i) {
            return this._data.get(i);
        }

        @Override // android.widget.Adapter
        public View getView(final int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = ScwipeFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(C0978R.layout.shortcut_item_view, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(C0978R.id.ln_icon);
            TextView textView = (TextView) view.findViewById(C0978R.id.tv_name);
            ImageView imageView = (ImageView) view.findViewById(C0978R.id.im_shape);
            ImageView imageView2 = (ImageView) view.findViewById(C0978R.id.im_icon);
            if (ScwipeFragmentActivity.this.b_grid) {
                textView.setVisibility(8);
                imageView.setVisibility(0);
                imageView2.setScaleX(0.7f);
                imageView2.setScaleY(0.7f);
                try {
                    imageView2.setImageDrawable(ScwipeFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(ScwipeFragmentActivity.this.s_package));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    imageView2.setImageResource(C0978R.drawable.app_icon);
                }
                if (i == 0) {
                    imageView.setImageResource(C0978R.drawable.shape_0);
                } else if (i == 1) {
                    imageView.setImageResource(C0978R.drawable.shape_1);
                } else if (i == 2) {
                    imageView.setImageResource(C0978R.drawable.shape_2);
                } else if (i == 3) {
                    imageView.setImageResource(C0978R.drawable.shape_3);
                } else if (i == 4) {
                    imageView.setImageResource(C0978R.drawable.shape_4);
                } else if (i == 5) {
                    imageView.setImageResource(C0978R.drawable.shape_5);
                } else if (i == 6) {
                    imageView.setImageResource(C0978R.drawable.shape_6);
                } else if (i == 7) {
                    imageView.setImageResource(C0978R.drawable.shape_7);
                } else if (i == 8) {
                    imageView.setImageResource(C0978R.drawable.shape_8);
                } else {
                    imageView.setImageResource(C0978R.drawable.shape_1);
                }
                imageView.setColorFilter(Color.parseColor(ScwipeFragmentActivity.this.s_tint), PorterDuff.Mode.MULTIPLY);
                linearLayout.setOnClickListener(new View.OnClickListener() {                     @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        ScwipeFragmentActivity.this.n_pos = i;
                        ScwipeFragmentActivity.this._setNewShape();
                    }
                });
            } else {
                textView.setVisibility(0);
                imageView.setVisibility(8);
                imageView2.setScaleX(1.0f);
                imageView2.setScaleY(1.0f);
                try {
                    imageView2.setImageDrawable(ScwipeFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(this._data.get(i).get("apppackage").toString()));
                    textView.setText(this._data.get(i).get("appname").toString());
                } catch (PackageManager.NameNotFoundException unused) {
                    textView.setText(this._data.get(i).get("appname").toString());
                }
                linearLayout.setOnClickListener(new View.OnClickListener() {                     @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        ScwipeFragmentActivity.this._setTextWipeData(Grid_wipe_appAdapter.this._data.get(i).get("appname").toString(), Grid_wipe_appAdapter.this._data.get(i).get("apppackage").toString());
                    }
                });
            }
            return view;
        }
    }
}
