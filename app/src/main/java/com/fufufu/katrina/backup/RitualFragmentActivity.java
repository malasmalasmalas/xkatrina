package com.fufufu.katrina.backup;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.NestedScrollView;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.fufufu.katrina.backup.RequestNetwork;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.elevation.SurfaceColors;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.topjohnwu.superuser.Shell;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;

public class RitualFragmentActivity extends Fragment {
    private AlertDialog EDITOR;
    private AlertDialog LOADING;
    private AlertDialog UPDATE;
    private FloatingActionButton _fab;
    private RequestNetwork.RequestListener _get_branch_all_request_listener;
    private RequestNetwork.RequestListener _get_branch_child1_request_listener;
    private RequestNetwork.RequestListener _get_branch_child2_request_listener;
    private AutoCompleteTextView auto_input_fp;
    private AutoCompleteTextView auto_input_model;
    private MaterialButton btn_apply;
    private Button btn_back;
    private MaterialButton btn_collapse_input;
    private Button btn_download;
    private Button btn_dump;
    private Button btn_dumpall;
    private MaterialButton btn_get_dump;
    private MaterialButton btn_install_module;
    private Button btn_prop;
    private MaterialCardView cv_3;
    private AutoCompleteTextView et_input_dump;
    private FloatingActionButton fab_editor;
    private ExtendedFloatingActionButton fab_random;
    private RequestNetwork get_branch_all;
    private RequestNetwork get_branch_child1;
    private RequestNetwork get_branch_child2;
    private ChipGroup ln_01;
    private ChipGroup ln_012;
    private LinearLayout ln_02;
    private LinearLayout ln_03;
    private LinearLayout ln_04;
    private LinearLayout ln_05;
    private ChipGroup ln_06;
    private ChipGroup ln_08;
    private LinearLayout ln_6;
    private LinearLayout ln_7;
    private LinearLayout ln_base;
    private LinearLayout ln_base_top;
    private LinearLayout ln_bottom;
    private FrameLayout ln_editor;
    private LinearLayout ln_input_dump;
    private ExpandableLayout ln_input_fp;
    private LinearLayout ln_input_prop;
    private LinearLayout ln_left;
    private LinearLayout ln_module_install;
    private LinearLayout ln_not_active;
    private LinearLayout ln_right;
    private LinearLayout ln_set_convert;
    private LinearLayout ln_setting_editor;
    private LinearLayout ln_title_input;
    private LottieAnimationView lottie1;
    private ListView lv_branch_all;
    private Chip m_dalvic;
    private Chip m_gms;
    private Chip m_nol;
    private Chip m_norestart;
    private Chip m_reboot;
    private Chip m_ssaid;
    private Chip m_timepick;
    private MaterialCardView materialcardview1;
    private MaterialCardView materialcardview2;
    private Chip mchip_modpes_end;
    private Chip mchip_modpes_start;
    private MyCEKMODULE myCEKMODULE;
    private MyCLEANERWIPE myCLEANERWIPE;
    private MyPROP myPROP;
    private MyPROPDUMP myPROPDUMP;
    private MyREADEXISTPROP myREADEXISTPROP;
    private MyREPLACEPROP myREPLACEPROP;
    private NestedScrollView nestscroll_1;
    private ProgressBar pbar_prop;
    private SharedPreferences pref;
    private SharedPreferences prefos;
    private SharedPreferences prefprop;
    private SharedPreferences prefrelease;
    private ProgressBar progressBar;
    private RecyclerView rv_1;
    private RecyclerView rv_2;
    private RecyclerView rv_3;
    private RecyclerView rv_4;
    private MaterialSwitch switch_editor;
    private TextInputLayout til_dump;
    private TextInputLayout til_input_fp;
    private TextInputLayout til_input_model;
    private TextView tv_02;
    private TextView tv_03;
    private TextView tv_05;
    private TextView tv_input_fingerprint;
    private TextView tv_not_active;
    private TextView tv_note;
    private TextView tv_prop_type;
    private TextView tv_response;
    private TextView tv_title;
    private String s_click_brand = "";
    private String s_click_model = "";
    private HashMap<String, Object> m_prop = new HashMap<>();
    private String s_json_prop = "";
    private String s_prop_final = "";
    private HashMap<String, Object> m_editor = new HashMap<>();
    private String s_command = "";
    private String s_commandResult = "";
    private String s_exitCode = "";
    private boolean b_command = false;
    private String s_propvalue = "";
    private String s_prop_device = "";
    private String s_prop_model = "";
    private String s_prop_product = "";
    private String s_prop_manufacturer = "";
    private String s_prop_brand = "";
    private String s_prop_boot = "";
    private String s_prop_buildid = "";
    private String s_prop_release = "";
    private String s_prop_incremental = "";
    private String s_prop_display = "";
    private String s_prop_board = "";
    private String s_prop_hardware = "";
    private String s_prop_host = "";
    private String s_prop_user = "";
    private String s_prop_fingerprint = "";
    private String s_systemprop = "";
    private HashMap<String, Object> m_random = new HashMap<>();
    private HashMap<String, Object> m_ori = new HashMap<>();
    private String s_defaultprop = "";
    private String s_prop_flavor = "";
    private String s_prop_dateutc = "";
    private String s_prop_description = "";
    private String s_prop_sdk = "";
    private String s_prop_date = "";
    private String s_prop_name = "";
    private String s_universal = "";

        private double f860n = 0.0d;
    private String s_rv = "";
    private String s_rv2 = "";
    private String extracleaner = "";
    private String extraprop = "";
    private String extrassaid = "";
    private String extrareset = "";
    private String extrareboot = "";
    private String extradalvic = "";
    private String extranorestart = "";
    private String extrawipe = "";
    private HashMap<String, Object> m_input = new HashMap<>();
    private String s_parsemodel = "";
    private String s_parsedevice = "";
    private String s_parserelease = "";
    private String s_parsebrand = "";
    private String s_parseproduct = "";
    private String s_parsebuildid = "";
    private String s_parseincremental = "";
    private String s_input_prop = "";
    private String s_add_prop = "";
    private String s_add_prop_base = "";
    private String s_input_json = "";
    private String s_json_old = "";
    private String s_data_old = "";
    private String s_json_result = "";
    private String s_data_new = "";
    private String s_custom_prop = "";
    private String s_response_result = "";
    private String s_prop_match = "";
    private String s_dump_head = "";
    private String s_dump_prop1 = "";
    private String s_dump_prop2 = "";
    private String s_dump_all = "";
    private String s_dump_raw = "";
    private String s_url_prop1 = "";
    private String s_url_prop2 = "";
    private String s_input_model = "";
    private String s_raw_model = "";
    private String s_prop_branch_all = "";
    private HashMap<String, Object> m_branch_all = new HashMap<>();
    private String s_url_prop = "";
    private String s_dump_model = "";
    private String s_feed_dump = "";
    private boolean b_update_force = false;
    private String s_filename = "";
    private String s_url = "";
    private String s_component = "";
    private String s_commandBase = "";
    private double n_os = 0.0d;
    private String s_os_plus = "";
    private String extramodpes = "";
    private String s_commandModpes = "";
    private HashMap<String, Object> m_modpes = new HashMap<>();
    private String s_json_pref = "";
    private double editor_pos = 0.0d;
    private String s_editor = "";
    private String s_dialog_editor = "";
    private String s_result_editor = "";
    private String s_title_editor = "";
    private String s_card_random = "";
    private String s_random_desc = "";
    private String s_random_release = "";
    private double n_random_editor = 0.0d;
    private String s_random_pref = "";
    private String s_random_fing = "";
    private String s_prop_title = "";
    private String s_prop_value = "";
    private String s_prop_new = "";
    private String s_commandGetProp = "";
    private boolean b_commandModule = false;
    private String s_commandModule = "";
    private String s_resultModule = "";
    private String s_post_fs_data = "";
    private String s_sensitiveprop = "";
    private String s_printprop = "";
    private HashMap<String, Object> m_systemprop = new HashMap<>();
    private String s_getkatrinaprop = "";
    private String s_fufufu_dump_online = "";
    private String s_remove_old = "";
    private ArrayList<HashMap<String, Object>> lm_json_model = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_brand = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_prop = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_editor = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_random = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_ori = new ArrayList<>();
    private ArrayList<String> ls_prop = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_input = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_old_prop = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_final = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_asset = new ArrayList<>();
    private ArrayList<String> ls_dump_prop = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_dump_prop = new ArrayList<>();
    private ArrayList<String> ls_branch_all = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_branch_all = new ArrayList<>();
    private ArrayList<String> ls_feed_dump = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_release = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_editor = new ArrayList<>();
    private ObjectAnimator oa1 = new ObjectAnimator();
    private ObjectAnimator oa2 = new ObjectAnimator();
    private ObjectAnimator oa3 = new ObjectAnimator();
    private ObjectAnimator oa4 = new ObjectAnimator();

        private Intent f859i = new Intent();

    public void _EXTRA() {
    }

    public void _onStartDownload() {
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C0978R.layout.ritual_fragment, viewGroup, false);
        initialize(bundle, viewInflate);
        initializeLogic();
        return viewInflate;
    }

    private void initialize(Bundle bundle, View view) {
        this._fab = (FloatingActionButton) view.findViewById(C0978R.id._fab);
        this.lottie1 = (LottieAnimationView) view.findViewById(C0978R.id.lottie1);
        this.ln_setting_editor = (LinearLayout) view.findViewById(C0978R.id.ln_setting_editor);
        this.ln_not_active = (LinearLayout) view.findViewById(C0978R.id.ln_not_active);
        this.ln_editor = (FrameLayout) view.findViewById(C0978R.id.ln_editor);
        this.ln_bottom = (LinearLayout) view.findViewById(C0978R.id.ln_bottom);
        this.switch_editor = (MaterialSwitch) view.findViewById(C0978R.id.switch_editor);
        this.ln_module_install = (LinearLayout) view.findViewById(C0978R.id.ln_module_install);
        this.cv_3 = (MaterialCardView) view.findViewById(C0978R.id.cv_3);
        this.tv_note = (TextView) view.findViewById(C0978R.id.tv_note);
        this.ln_set_convert = (LinearLayout) view.findViewById(C0978R.id.ln_set_convert);
        this.tv_not_active = (TextView) view.findViewById(C0978R.id.tv_not_active);
        this.btn_install_module = (MaterialButton) view.findViewById(C0978R.id.btn_install_module);
        this.nestscroll_1 = (NestedScrollView) view.findViewById(C0978R.id.nestscroll_1);
        this.ln_6 = (LinearLayout) view.findViewById(C0978R.id.ln_6);
        this.ln_7 = (LinearLayout) view.findViewById(C0978R.id.ln_7);
        this.rv_4 = (RecyclerView) view.findViewById(C0978R.id.rv_4);
        this.tv_05 = (TextView) view.findViewById(C0978R.id.tv_05);
        this.ln_01 = (ChipGroup) view.findViewById(C0978R.id.ln_01);
        this.tv_02 = (TextView) view.findViewById(C0978R.id.tv_02);
        this.ln_06 = (ChipGroup) view.findViewById(C0978R.id.ln_06);
        this.tv_03 = (TextView) view.findViewById(C0978R.id.tv_03);
        this.ln_08 = (ChipGroup) view.findViewById(C0978R.id.ln_08);
        this.mchip_modpes_start = (Chip) view.findViewById(C0978R.id.mchip_modpes_start);
        this.mchip_modpes_end = (Chip) view.findViewById(C0978R.id.mchip_modpes_end);
        this.m_timepick = (Chip) view.findViewById(C0978R.id.m_timepick);
        this.m_gms = (Chip) view.findViewById(C0978R.id.m_gms);
        this.ln_012 = (ChipGroup) view.findViewById(C0978R.id.ln_012);
        this.m_ssaid = (Chip) view.findViewById(C0978R.id.m_ssaid);
        this.m_nol = (Chip) view.findViewById(C0978R.id.m_nol);
        this.m_reboot = (Chip) view.findViewById(C0978R.id.m_reboot);
        this.m_dalvic = (Chip) view.findViewById(C0978R.id.m_dalvic);
        this.m_norestart = (Chip) view.findViewById(C0978R.id.m_norestart);
        this.fab_random = (ExtendedFloatingActionButton) view.findViewById(C0978R.id.fab_random);
        this.fab_editor = (FloatingActionButton) view.findViewById(C0978R.id.fab_editor);
        this.ln_base_top = (LinearLayout) view.findViewById(C0978R.id.ln_base_top);
        this.ln_base = (LinearLayout) view.findViewById(C0978R.id.ln_base);
        this.ln_title_input = (LinearLayout) view.findViewById(C0978R.id.ln_title_input);
        this.ln_input_fp = (ExpandableLayout) view.findViewById(C0978R.id.ln_input_fp);
        this.ln_05 = (LinearLayout) view.findViewById(C0978R.id.ln_05);
        this.ln_input_dump = (LinearLayout) view.findViewById(C0978R.id.ln_input_dump);
        this.ln_input_prop = (LinearLayout) view.findViewById(C0978R.id.ln_input_prop);
        this.btn_prop = (Button) view.findViewById(C0978R.id.btn_prop);
        this.btn_dump = (Button) view.findViewById(C0978R.id.btn_dump);
        this.btn_dumpall = (Button) view.findViewById(C0978R.id.btn_dumpall);
        this.til_dump = (TextInputLayout) view.findViewById(C0978R.id.til_dump);
        this.btn_get_dump = (MaterialButton) view.findViewById(C0978R.id.btn_get_dump);
        this.et_input_dump = (AutoCompleteTextView) view.findViewById(C0978R.id.et_input_dump);
        this.tv_input_fingerprint = (TextView) view.findViewById(C0978R.id.tv_input_fingerprint);
        this.btn_collapse_input = (MaterialButton) view.findViewById(C0978R.id.btn_collapse_input);
        this.ln_04 = (LinearLayout) view.findViewById(C0978R.id.ln_04);
        this.ln_03 = (LinearLayout) view.findViewById(C0978R.id.ln_03);
        this.btn_apply = (MaterialButton) view.findViewById(C0978R.id.btn_apply);
        this.til_input_model = (TextInputLayout) view.findViewById(C0978R.id.til_input_model);
        this.til_input_fp = (TextInputLayout) view.findViewById(C0978R.id.til_input_fp);
        this.auto_input_model = (AutoCompleteTextView) view.findViewById(C0978R.id.auto_input_model);
        this.auto_input_fp = (AutoCompleteTextView) view.findViewById(C0978R.id.auto_input_fp);
        this.ln_left = (LinearLayout) view.findViewById(C0978R.id.ln_left);
        this.ln_right = (LinearLayout) view.findViewById(C0978R.id.ln_right);
        this.ln_02 = (LinearLayout) view.findViewById(C0978R.id.ln_02);
        this.btn_back = (Button) view.findViewById(C0978R.id.btn_back);
        this.tv_response = (TextView) view.findViewById(C0978R.id.tv_response);
        this.pbar_prop = (ProgressBar) view.findViewById(C0978R.id.pbar_prop);
        this.tv_prop_type = (TextView) view.findViewById(C0978R.id.tv_prop_type);
        this.materialcardview1 = (MaterialCardView) view.findViewById(C0978R.id.materialcardview1);
        this.materialcardview2 = (MaterialCardView) view.findViewById(C0978R.id.materialcardview2);
        this.tv_title = (TextView) view.findViewById(C0978R.id.tv_title);
        this.lv_branch_all = (ListView) view.findViewById(C0978R.id.lv_branch_all);
        this.rv_1 = (RecyclerView) view.findViewById(C0978R.id.rv_1);
        this.rv_2 = (RecyclerView) view.findViewById(C0978R.id.rv_2);
        this.rv_3 = (RecyclerView) view.findViewById(C0978R.id.rv_3);
        this.pref = getContext().getSharedPreferences("preferences_editor", 0);
        this.prefprop = getContext().getSharedPreferences("input_fp_preferences", 0);
        this.get_branch_all = new RequestNetwork((Activity) getContext());
        this.get_branch_child1 = new RequestNetwork((Activity) getContext());
        this.get_branch_child2 = new RequestNetwork((Activity) getContext());
        this.prefrelease = getContext().getSharedPreferences("release_preference", 0);
        this.prefos = getContext().getSharedPreferences("os_preferences", 0);
        this.switch_editor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    RitualFragmentActivity.this.pref.edit().putString("show_fragment", "show_editor").commit();
                } else {
                    RitualFragmentActivity.this.pref.edit().putString("show_fragment", "hide_editor").commit();
                }
                RitualFragmentActivity.this._onCheckSwitchSetting();
            }
        });
        this.mchip_modpes_start.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_modpes_start", "true").commit();
                } else {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_modpes_start", "false").commit();
                }
            }
        });
        this.mchip_modpes_end.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_modpes_end", "true").commit();
                } else {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_modpes_end", "false").commit();
                }
            }
        });
        this.m_timepick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_timepick", "true").commit();
                } else {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_timepick", "false").commit();
                }
            }
        });
        this.m_gms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_gms", "true").commit();
                } else {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_gms", "false").commit();
                }
            }
        });
        this.m_ssaid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_ssaid", "true").commit();
                } else {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_ssaid", "false").commit();
                }
            }
        });
        this.m_nol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_nol", "true").commit();
                } else {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_nol", "false").commit();
                }
            }
        });
        this.m_reboot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_reboot", "true").commit();
                } else {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_reboot", "false").commit();
                }
            }
        });
        this.m_dalvic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_dalvic", "true").commit();
                } else {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_dalvic", "false").commit();
                }
            }
        });
        this.m_norestart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_norestart", "true").commit();
                } else {
                    RitualFragmentActivity.this.pref.edit().putString("chip_editor_norestart", "false").commit();
                }
            }
        });
        this.fab_random.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RitualFragmentActivity.this._onRandomAllEditor();
            }
        });
        this.fab_editor.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RitualFragmentActivity.this._onStartRitualEditor();
            }
        });
        this.btn_prop.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RitualFragmentActivity.this._setPropType("Termux Prop");
            }
        });
        this.btn_dump.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RitualFragmentActivity.this._setPropType("Android Dump");
            }
        });
        this.btn_dumpall.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RitualFragmentActivity.this._setPropType("Online Dump");
            }
        });
        this.btn_get_dump.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RitualFragmentActivity.this._onGetDumpOnline();
            }
        });
        this.btn_collapse_input.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RitualFragmentActivity.this._onCollapseFingerInput("clickbutton");
            }
        });
        this.btn_apply.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RitualFragmentActivity.this._onConvertProp();
            }
        });
        this.auto_input_fp.addTextChangedListener(new TextWatcher() {             @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                charSequence.toString();
                RitualFragmentActivity.this.til_input_fp.setError(null);
            }
        });
        this.btn_back.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RitualFragmentActivity.this._onBackButton();
            }
        });
        this._fab.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RitualFragmentActivity.this._onGetAllProp();
            }
        });
        this.oa1.addListener(new Animator.AnimatorListener() {             @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                RitualFragmentActivity.this.rv_3.setVisibility(0);
                RitualFragmentActivity.this._fab.setVisibility(0);
            }
        });
        this.oa3.addListener(new Animator.AnimatorListener() {             @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                RitualFragmentActivity.this.rv_3.setVisibility(8);
                RitualFragmentActivity.this._fab.setVisibility(8);
            }
        });
        this._get_branch_all_request_listener = new RequestNetwork.RequestListener() {             @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> map) {
                if (!str2.contains("Sign in · GitLab")) {
                    RitualFragmentActivity.this.ls_branch_all.clear();
                    RitualFragmentActivity.this.lm_branch_all.clear();
                    RitualFragmentActivity.this.s_response_result = RitualFragmentActivity.removeLinesList(str2);
                    RitualFragmentActivity ritualFragmentActivity = RitualFragmentActivity.this;
                    ritualFragmentActivity.s_response_result = ritualFragmentActivity.s_response_result.replaceAll(".*data-branch-name=\"", "");
                    RitualFragmentActivity ritualFragmentActivity2 = RitualFragmentActivity.this;
                    ritualFragmentActivity2.s_response_result = ritualFragmentActivity2.s_response_result.replaceAll(".*data-default-branch=\"", "");
                    RitualFragmentActivity ritualFragmentActivity3 = RitualFragmentActivity.this;
                    ritualFragmentActivity3.s_response_result = ritualFragmentActivity3.s_response_result.replaceAll("\".*", "");
                    RitualFragmentActivity.this.ls_branch_all = new ArrayList(Arrays.asList(RitualFragmentActivity.this.s_response_result.split("\n")));
                    RitualFragmentActivity.this.f860n = 0.0d;
                    for (int i = 0; i < RitualFragmentActivity.this.ls_branch_all.size(); i++) {
                        RitualFragmentActivity ritualFragmentActivity4 = RitualFragmentActivity.this;
                        ritualFragmentActivity4.s_prop_branch_all = (String) ritualFragmentActivity4.ls_branch_all.get((int) RitualFragmentActivity.this.f860n);
                        String[] strArrSplit = RitualFragmentActivity.this.s_prop_branch_all.split("-", 6);
                        String str3 = strArrSplit[0];
                        String str4 = strArrSplit[1];
                        String str5 = strArrSplit[2];
                        String str6 = strArrSplit[3];
                        String str7 = strArrSplit[4];
                        String str8 = strArrSplit[5];
                        RitualFragmentActivity.this.m_branch_all = new HashMap();
                        RitualFragmentActivity.this.m_branch_all.put("device_url1", RitualFragmentActivity.this.s_dump_head.concat(RitualFragmentActivity.this.s_raw_model.concat(RitualFragmentActivity.this.s_prop_branch_all.concat(RitualFragmentActivity.this.s_dump_prop2))));
                        RitualFragmentActivity.this.m_branch_all.put("device_url2", RitualFragmentActivity.this.s_dump_head.concat(RitualFragmentActivity.this.s_raw_model.concat(RitualFragmentActivity.this.s_prop_branch_all.concat(RitualFragmentActivity.this.s_dump_prop1))));
                        RitualFragmentActivity.this.m_branch_all.put("device", str3);
                        RitualFragmentActivity.this.m_branch_all.put("release", str5);
                        RitualFragmentActivity.this.m_branch_all.put("buildid", str6);
                        RitualFragmentActivity.this.m_branch_all.put("incremental", str7);
                        RitualFragmentActivity.this.lm_branch_all.add(RitualFragmentActivity.this.m_branch_all);
                        RitualFragmentActivity.this.f860n += 1.0d;
                    }
                    SketchwareUtil.sortListMap(RitualFragmentActivity.this.lm_branch_all, "release", false, true);
                    ListView listView = RitualFragmentActivity.this.lv_branch_all;
                    RitualFragmentActivity ritualFragmentActivity5 = RitualFragmentActivity.this;
                    listView.setAdapter((ListAdapter) ritualFragmentActivity5.new Lv_branch_allAdapter(ritualFragmentActivity5.lm_branch_all));
                    RitualFragmentActivity.this._onLoadingOnline("clickafterget");
                    return;
                }
                RitualFragmentActivity.this._onLoadingOnline("clicknotfound");
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
                RitualFragmentActivity.this._onResponseError("Tidak ada koneksi internet");
            }
        };
        this._get_branch_child1_request_listener = new RequestNetwork.RequestListener() {             @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> map) {
                if (!str2.contains("Not Found")) {
                    RitualFragmentActivity.this.s_response_result = str2;
                    RitualFragmentActivity.this._getPropResult();
                } else {
                    RitualFragmentActivity.this.get_branch_child2.startRequestNetwork("GET", RitualFragmentActivity.this.s_url_prop2, "a", RitualFragmentActivity.this._get_branch_child2_request_listener);
                }
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
                RitualFragmentActivity.this._onResponseError(str2);
            }
        };
        this._get_branch_child2_request_listener = new RequestNetwork.RequestListener() {             @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> map) {
                if (!str2.contains("Not Found")) {
                    RitualFragmentActivity.this.s_response_result = str2;
                    RitualFragmentActivity.this._getPropResult();
                } else {
                    RitualFragmentActivity.this.tv_response.setVisibility(0);
                    RitualFragmentActivity.this.tv_response.setText("fufufu tidak dapat menemukan prop");
                }
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
                RitualFragmentActivity.this._onResponseError(str2);
            }
        };
    }

    private void initializeLogic() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {             @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
            }
        });
        this.ln_not_active.setVisibility(8);
        this.ln_setting_editor.setVisibility(8);
        this.ln_editor.setVisibility(8);
        this.ln_bottom.setVisibility(8);
        _onCheckModule();
        _setFirstUI();
        new Handler().postDelayed(new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                if (RitualFragmentActivity.this.prefrelease.getString("release", "").equals("")) {
                    return;
                }
                RitualFragmentActivity.this.lm_release = (ArrayList) new Gson().fromJson(RitualFragmentActivity.this.prefrelease.getString("release", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {                 }.getType());
                RitualFragmentActivity.this._showDialogUpdate();
            }
        }, 3000L);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    public static String generateRandomString() {
        int randomNumberInRange = getRandomNumberInRange(4, 9);
        StringBuilder sb = new StringBuilder(randomNumberInRange);
        Random random = new Random();
        for (int i = 0; i < randomNumberInRange; i++) {
            sb.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

    public static int getRandomNumberInRange(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    private static long getRandomUnixTime(long j, long j2) {
        return j + ((long) (Math.random() * (j2 - j)));
    }

    private static String removeLines(String str) {
        StringBuilder sb = new StringBuilder();
        for (String str2 : str.split("\n")) {
            if (((!str2.contains(".release=") && !str2.contains(".model=") && !str2.contains(".hardware=") && !str2.contains(".date=") && !str2.contains(".date.utc=") && !str2.contains(".fingerprint=") && !str2.contains(".build.id=") && !str2.contains(".incremental=") && !str2.contains(".bootloader=") && !str2.contains(".description=") && !str2.contains(".display.id=") && !str2.contains(".flavor=") && !str2.contains(".host=") && !str2.contains(".product=") && !str2.contains(".user=") && !str2.contains(".board=") && !str2.contains(".brand=") && !str2.contains(".device=") && !str2.contains(".manufacturer=") && !str2.contains(".name=")) || str2.contains(".vendor") || str2.contains("bt.name")) ? false : true) {
                sb.append(str2);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

        public static String removeLinesList(String str) {
        StringBuilder sb = new StringBuilder();
        for (String str2 : str.split("\n")) {
            if (str2.contains("data-default-branch=") || str2.contains("data-branch-name=")) {
                sb.append(str2);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private static String generateRandomDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        calendar.setTimeZone(TimeZone.getTimeZone("KST"));
        calendar.set(getRandomNumberInRange(2000, 2023), getRandomNumberInRange(0, 11), getRandomNumberInRange(1, 28), getRandomNumberInRange(0, 23), getRandomNumberInRange(0, 59), getRandomNumberInRange(0, 59));
        return simpleDateFormat.format(calendar.getTime());
    }

    public boolean jsonIsValid(String str) {
        new HashMap();
        new ArrayList();
        try {
            try {
                return true;
            } catch (Exception unused) {
                return true;
            }
        } catch (Exception unused2) {
            return false;
        }
    }

    public void _setFirstUI() {
        this.m_timepick.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.m_gms.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.m_ssaid.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.m_nol.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.m_reboot.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.m_dalvic.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.m_norestart.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.mchip_modpes_start.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.mchip_modpes_end.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this._fab.setImageResource(C0978R.drawable.ic_fab_ritual);
        this.fab_editor.setImageResource(C0978R.drawable.ic_fab_ritual);
        this.ln_input_fp.setExpansion(false);
        this.ln_input_fp.setDuration(350);
        this.ln_input_fp.setOrientation(1);
        this.btn_collapse_input.setRotation(0.0f);
        this.btn_back.setVisibility(8);
        this.rv_2.setVisibility(8);
        this.rv_3.setVisibility(8);
        this._fab.setVisibility(8);
        this.pbar_prop.setVisibility(8);
        this.tv_response.setVisibility(8);
        this.ln_input_prop.setVisibility(0);
        this.ln_input_dump.setVisibility(8);
        this.lv_branch_all.setVisibility(8);
        this.tv_title.setText("BRAND");
        this.auto_input_model.setSingleLine(true);
        if (this.prefos.getString("OSPLUS", "").equals("")) {
            this.prefos.edit().putString("OSPLUS", "15").commit();
        }
        try {
            String strCopyFromInputStream = SketchwareUtil.copyFromInputStream(getContext().getAssets().open("prop.sh"));
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
            this.s_commandBase = strReplace61.replace("futhispackage", getContext().getApplicationContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.s_systemprop = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/system.prop"));
        this.s_sensitiveprop = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/post-fs-data.sh"));
        this.s_defaultprop = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/default.prop"));
        this.s_add_prop = getContext().getFilesDir().getPath() + "/custom_fp.json";
        this.s_add_prop_base = "[{\"MEREK\":\"KATRINA\", \"DATA\": []}]";
        this.s_dump_head = "https://dumps.tadiphone.dev/dumps/";
        this.s_dump_prop2 = "/system/system/build.prop?ref_type=heads";
        this.s_dump_prop1 = "/system/build.prop?ref_type=heads";
        this.s_dump_all = "/-/branches/all";
        this.s_dump_raw = "/-/raw/";
        this.s_fufufu_dump_online = FileUtil.getExternalStorageDir().concat("/fufufu_dump_online.txt");
        if (!FileUtil.isExistFile(this.s_add_prop)) {
            FileUtil.writeFile(this.s_add_prop, this.s_add_prop_base);
        }
        this.et_input_dump.setSingleLine(true);
        this.et_input_dump.setOnEditorActionListener(new TextView.OnEditorActionListener() {             @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 2) {
                    return false;
                }
                RitualFragmentActivity.this._onGetDumpOnline();
                return true;
            }
        });
    }

    public void _onLoadBrand() {
        MyPROP myPROP = this.myPROP;
        if (myPROP != null && myPROP.isRunning) {
            this.myPROP.cancelPROPTask();
        }
        MyPROP myPROP2 = new MyPROP();
        this.myPROP = myPROP2;
        myPROP2.execute(new Void[0]);
    }

    public class MyPROP extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyPROP() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            RitualFragmentActivity.this.rv_1.setVisibility(0);
            RitualFragmentActivity.this.rv_2.setVisibility(8);
            RitualFragmentActivity.this.rv_3.setVisibility(8);
            RitualFragmentActivity.this._fab.setVisibility(8);
            RitualFragmentActivity.this.pbar_prop.setVisibility(0);
            RitualFragmentActivity.this.btn_prop.setEnabled(false);
            RitualFragmentActivity.this.btn_dump.setEnabled(false);
            RitualFragmentActivity.this.btn_dumpall.setEnabled(false);
            RitualFragmentActivity.this.tv_title.setText("BRAND");
            RitualFragmentActivity.this.lm_json_brand.clear();
            if (FileUtil.isExistFile(RitualFragmentActivity.this.s_add_prop)) {
                RitualFragmentActivity ritualFragmentActivity = RitualFragmentActivity.this;
                ritualFragmentActivity.s_custom_prop = FileUtil.readFile(ritualFragmentActivity.s_add_prop);
                RitualFragmentActivity ritualFragmentActivity2 = RitualFragmentActivity.this;
                if (ritualFragmentActivity2.jsonIsValid(ritualFragmentActivity2.s_custom_prop)) {
                    RitualFragmentActivity.this.lm_json_brand = (ArrayList) new Gson().fromJson(RitualFragmentActivity.this.s_custom_prop, new TypeToken<ArrayList<HashMap<String, Object>>>() {                     }.getType());
                }
            }
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            try {
                InputStream inputStreamOpen = RitualFragmentActivity.this.getContext().getAssets().open("prop.json");
                RitualFragmentActivity.this.lm_json_asset = (ArrayList) new Gson().fromJson(SketchwareUtil.copyFromInputStream(inputStreamOpen), new TypeToken<ArrayList<HashMap<String, Object>>>() {                 }.getType());
                RitualFragmentActivity.this.lm_json_brand.addAll(RitualFragmentActivity.this.lm_json_asset);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r4) {
            this.isRunning = false;
            RecyclerView recyclerView = RitualFragmentActivity.this.rv_1;
            RitualFragmentActivity ritualFragmentActivity = RitualFragmentActivity.this;
            recyclerView.setAdapter(ritualFragmentActivity.new Rv_1Adapter(ritualFragmentActivity.lm_json_brand));
            RitualFragmentActivity.this.rv_1.setLayoutManager(new LinearLayoutManager(RitualFragmentActivity.this.getContext()));
            RitualFragmentActivity.this.btn_back.setVisibility(8);
            RitualFragmentActivity.this.btn_prop.setEnabled(true);
            RitualFragmentActivity.this.btn_dump.setEnabled(true);
            RitualFragmentActivity.this.btn_dumpall.setEnabled(true);
            RitualFragmentActivity.this.pbar_prop.setVisibility(8);
        }

        public void cancelPROPTask() {
            cancel(true);
        }
    }

    public void _onLoadModel() {
        this.lm_json_model.clear();
        this.lm_json_model = (ArrayList) new Gson().fromJson(this.s_click_brand, new TypeToken<ArrayList<HashMap<String, Object>>>() {         }.getType());
        this.rv_1.setAdapter(new Rv_1Adapter(this.lm_json_model));
        this.rv_1.setLayoutManager(new LinearLayoutManager(getContext()));
        this.btn_back.setText("BACK TO BRAND");
        this.tv_title.setText(this.s_rv);
        this.btn_back.setVisibility(0);
    }

    public void _onAdvanceBindBrand(View view, TextView textView, TextView textView2, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        textView.setText(arrayList.get((int) d).get("MEREK").toString());
        textView2.setVisibility(8);
        view.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RitualFragmentActivity.this.s_click_brand = ((HashMap) arrayList.get((int) d)).get("MEREK").toString();
                RitualFragmentActivity.this.s_rv = ((HashMap) arrayList.get((int) d)).get("MEREK").toString();
                RitualFragmentActivity.this.s_click_brand = new Gson().toJson(((HashMap) arrayList.get((int) d)).get("DATA"));
                RitualFragmentActivity.this._onLoadModel();
            }
        });
    }

    public void _onAdvanceBindModel(View view, TextView textView, TextView textView2, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        int i = (int) d;
        textView.setText(arrayList.get(i).get("DEVICENAME").toString());
        textView2.setText("OS : ".concat(arrayList.get(i).get("RELEASE").toString()));
        view.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RitualFragmentActivity.this.s_rv2 = ((HashMap) arrayList.get((int) d)).get("DEVICENAME").toString();
                RitualFragmentActivity.this._onCreateJsonProp(d, arrayList);
                RitualFragmentActivity.this._onCreateJsonOri(d, arrayList);
                RitualFragmentActivity.this._onCreateJsonRandom(d, arrayList);
                RitualFragmentActivity.this._onModelLoadProp();
            }
        });
    }

    public void _onBackButton() {
        if (this.lm_json_prop.size() > 0 && this.lm_json_prop.get(0).containsKey("ONLINE")) {
            if (this.btn_back.getText().toString().equals("BACK TO MODEL")) {
                this.lv_branch_all.setVisibility(0);
                this.rv_2.setVisibility(8);
                this.btn_back.setVisibility(8);
            }
        } else if (this.btn_back.getText().toString().equals("BACK TO BRAND")) {
            this.rv_1.setAdapter(new Rv_1Adapter(this.lm_json_brand));
            this.rv_1.setLayoutManager(new LinearLayoutManager(getContext()));
            this.tv_title.setText("BRAND");
            this.btn_back.setVisibility(8);
        } else if (this.btn_back.getText().toString().equals("BACK TO MODEL")) {
            this.rv_1.setAdapter(new Rv_1Adapter(this.lm_json_model));
            this.rv_1.setLayoutManager(new LinearLayoutManager(getContext()));
            this.btn_back.setText("BACK TO BRAND");
            this.tv_title.setText(this.s_rv);
            this.rv_1.setVisibility(0);
            this.btn_back.setVisibility(0);
            this.rv_2.setVisibility(8);
        }
        this.oa3.cancel();
        this.oa3.setTarget(this.rv_3);
        this.oa3.setPropertyName("alpha");
        this.oa3.setFloatValues(1.0f, 0.0f);
        this.oa3.setDuration(300L);
        this.oa3.start();
        this.oa4.cancel();
        this.oa4.setTarget(this._fab);
        this.oa4.setPropertyName("alpha");
        this.oa4.setFloatValues(1.0f, 0.0f);
        this.oa4.setDuration(300L);
        this.oa4.start();
    }

    public void _onModelLoadProp() {
        this.btn_back.setText("BACK TO MODEL");
        this.tv_title.setText(this.s_rv2);
        this.rv_1.setVisibility(8);
        this.rv_2.setVisibility(0);
        _onModelLoadEditor();
    }

    public void _onModelLoadEditor() {
        if (this.pref.getString("preferences_editor", "").equals("")) {
            _createJsonEditor();
        } else {
            this.lm_json_editor.clear();
            String string = this.pref.getString("preferences_editor", "");
            this.s_json_pref = string;
            if (!string.equals("[]") && jsonIsValid(this.s_json_pref)) {
                ArrayList<HashMap<String, Object>> arrayList = (ArrayList) new Gson().fromJson(this.s_json_pref, new TypeToken<ArrayList<HashMap<String, Object>>>() {                 }.getType());
                this.lm_json_editor = arrayList;
                String[] strArr = {"DEVICE", "MODEL", "PRODUCT", "MANUFACTURER", "BRAND", "BOOT", "BUILDID", "RELEASE", "INCREMENTAL", "DISPLAY", "FINGERPRINT", "DESCRIPTION", "NAME", "TIMEPICK", "WIPEGMS", "SSAID", "RESET0", "REBOOT", "DALVIC", "NORESTART", "OS9", "OS10", "OS11", "OS12", "OS12L", "OS13", "OS14", "OSPLUS", "MODPESSTART", "MODPESEND"};
                for (HashMap<String, Object> map : arrayList) {
                    int i = 0;
                    if (map.isEmpty()) {
                        while (i < 30) {
                            map.put(strArr[i], "false");
                            i++;
                        }
                    } else {
                        while (i < 30) {
                            String str = strArr[i];
                            if (!map.containsKey(str)) {
                                map.put(str, "false");
                            }
                            i++;
                        }
                    }
                }
            } else {
                _createJsonEditor();
            }
            _onModelFromEditor();
        }
        this.rv_2.setAdapter(new Rv_2Adapter(this.lm_json_prop));
        this.rv_2.setLayoutManager(new LinearLayoutManager(getContext()));
        this.rv_3.setAdapter(new Rv_3Adapter(this.lm_json_editor));
        this.rv_3.setLayoutManager(new LinearLayoutManager(getContext()));
        this.oa1.cancel();
        this.oa1.setTarget(this.rv_3);
        this.oa1.setPropertyName("alpha");
        this.oa1.setFloatValues(0.0f, 1.0f);
        this.oa1.setDuration(1000L);
        this.oa1.start();
        this.oa2.cancel();
        this.oa2.setTarget(this._fab);
        this.oa2.setPropertyName("alpha");
        this.oa2.setFloatValues(0.0f, 1.0f);
        this.oa2.setDuration(1000L);
        this.oa2.start();
    }

    public void _onModelFromEditor() {
        if (this.lm_json_editor.get(0).get("DEVICE").toString().equals("true")) {
            this.lm_json_prop.get(0).put("DEVICE", this.lm_json_random.get(0).get("DEVICE").toString());
        }
        if (this.lm_json_editor.get(0).get("MODEL").toString().equals("true")) {
            this.lm_json_prop.get(0).put("MODEL", this.lm_json_random.get(0).get("MODEL").toString());
        }
        if (this.lm_json_editor.get(0).get("PRODUCT").toString().equals("true")) {
            this.lm_json_prop.get(0).put("PRODUCT", this.lm_json_random.get(0).get("PRODUCT").toString());
        }
        if (this.lm_json_editor.get(0).get("MANUFACTURER").toString().equals("true")) {
            this.lm_json_prop.get(0).put("MANUFACTURER", this.lm_json_random.get(0).get("MANUFACTURER").toString());
        }
        if (this.lm_json_editor.get(0).get("BRAND").toString().equals("true")) {
            this.lm_json_prop.get(0).put("BRAND", this.lm_json_random.get(0).get("BRAND").toString());
        }
        if (this.lm_json_editor.get(0).get("BOOT").toString().equals("true")) {
            this.lm_json_prop.get(0).put("BOOT", this.lm_json_random.get(0).get("BOOT").toString());
        }
        if (this.lm_json_editor.get(0).get("BUILDID").toString().equals("true")) {
            this.lm_json_prop.get(0).put("BUILDID", this.lm_json_random.get(0).get("BUILDID").toString());
        }
        if (this.lm_json_editor.get(0).get("INCREMENTAL").toString().equals("true")) {
            this.lm_json_prop.get(0).put("INCREMENTAL", this.lm_json_random.get(0).get("INCREMENTAL").toString());
        }
        if (this.lm_json_editor.get(0).get("DISPLAY").toString().equals("true")) {
            this.lm_json_prop.get(0).put("DISPLAY", this.lm_json_random.get(0).get("DISPLAY").toString());
        }
        if (this.lm_json_editor.get(0).get("FINGERPRINT").toString().equals("true")) {
            _onGetValueRelease();
            this.s_universal = this.lm_json_random.get(0).get("FINGERPRINT").toString().replace("FURELEASEFU", this.s_prop_release);
            this.lm_json_prop.get(0).put("FINGERPRINT", this.s_universal);
        }
        if (this.lm_json_editor.get(0).get("DESCRIPTION").toString().equals("true")) {
            _onGetValueRelease();
            this.s_universal = this.lm_json_random.get(0).get("DESCRIPTION").toString().replace("FURELEASEFU", this.s_prop_release);
            this.lm_json_prop.get(0).put("DESCRIPTION", this.s_universal);
        }
        if (this.lm_json_editor.get(0).get("NAME").toString().equals("true")) {
            this.lm_json_prop.get(0).put("NAME", this.lm_json_random.get(0).get("NAME").toString());
        }
        if (this.lm_json_editor.get(0).get("OS9").toString().equals("9")) {
            this.lm_json_prop.get(0).put("RELEASE", "9");
        }
        if (this.lm_json_editor.get(0).get("OS10").toString().equals("10")) {
            this.lm_json_prop.get(0).put("RELEASE", "10");
        }
        if (this.lm_json_editor.get(0).get("OS11").toString().equals("11")) {
            this.lm_json_prop.get(0).put("RELEASE", "11");
        }
        if (this.lm_json_editor.get(0).get("OS12").toString().equals("12")) {
            this.lm_json_prop.get(0).put("RELEASE", "12");
        }
        if (this.lm_json_editor.get(0).get("OS12L").toString().equals("12.1")) {
            this.lm_json_prop.get(0).put("RELEASE", "12.1");
        }
        if (this.lm_json_editor.get(0).get("OS13").toString().equals("13")) {
            this.lm_json_prop.get(0).put("RELEASE", "13");
        }
        if (this.lm_json_editor.get(0).get("OS14").toString().equals("14")) {
            this.lm_json_prop.get(0).put("RELEASE", "14");
        }
        if (this.lm_json_editor.get(0).get("OSPLUS").toString().equals("false")) {
            return;
        }
        this.lm_json_prop.get(0).put("RELEASE", this.prefos.getString("OSPLUS", ""));
    }

    public void _onCreateJsonProp(double d, ArrayList<HashMap<String, Object>> arrayList) {
        this.lm_json_prop.clear();
        this.m_prop.clear();
        this.m_prop = new HashMap<>();
        int i = (int) d;
        String string = arrayList.get(i).get("DEVICE").toString();
        this.s_prop_device = string;
        this.m_prop.put("DEVICE", string);
        String string2 = arrayList.get(i).get("MODEL").toString();
        this.s_prop_model = string2;
        this.m_prop.put("MODEL", string2);
        String string3 = arrayList.get(i).get("PRODUCT").toString();
        this.s_prop_product = string3;
        this.m_prop.put("PRODUCT", string3);
        String str = this.s_prop_product;
        this.s_prop_name = str;
        this.m_prop.put("NAME", str);
        String string4 = arrayList.get(i).get("MANUFACTURER").toString();
        this.s_prop_manufacturer = string4;
        this.m_prop.put("MANUFACTURER", string4);
        String string5 = arrayList.get(i).get("BRAND").toString();
        this.s_prop_brand = string5;
        this.m_prop.put("BRAND", string5);
        String string6 = arrayList.get(i).get("INCREMENTAL").toString();
        this.s_prop_boot = string6;
        this.m_prop.put("BOOT", string6);
        String string7 = arrayList.get(i).get("BUILDID").toString();
        this.s_prop_buildid = string7;
        this.m_prop.put("BUILDID", string7);
        String string8 = arrayList.get(i).get("RELEASE").toString();
        this.s_prop_release = string8;
        this.m_prop.put("RELEASE", string8);
        String string9 = arrayList.get(i).get("INCREMENTAL").toString();
        this.s_prop_incremental = string9;
        this.m_prop.put("INCREMENTAL", string9);
        String strConcat = this.s_prop_buildid.concat(".".concat(this.s_prop_incremental));
        this.s_prop_display = strConcat;
        this.m_prop.put("DISPLAY", strConcat);
        String strConcat2 = this.s_prop_brand.concat("/".concat(this.s_prop_product.concat("/".concat(this.s_prop_device.concat(":".concat(this.s_prop_release.concat("/".concat(this.s_prop_buildid.concat("/".concat(this.s_prop_incremental.concat(":user/release-keys")))))))))));
        this.s_prop_fingerprint = strConcat2;
        this.m_prop.put("FINGERPRINT", strConcat2);
        String strConcat3 = this.s_prop_name.concat("-user ".concat(this.s_prop_release.concat(" ".concat(this.s_prop_buildid.concat(this.s_prop_incremental.concat(" release-keys"))))));
        this.s_prop_description = strConcat3;
        this.m_prop.put("DESCRIPTION", strConcat3);
        String str2 = Build.VERSION.SDK;
        this.s_prop_sdk = str2;
        this.m_prop.put("SDK", str2);
        String strGenerateRandomString = generateRandomString();
        this.s_prop_board = strGenerateRandomString;
        this.m_prop.put("BOARD", strGenerateRandomString);
        String strGenerateRandomString2 = generateRandomString();
        this.s_prop_hardware = strGenerateRandomString2;
        this.m_prop.put("HARDWARE", strGenerateRandomString2);
        String strGenerateRandomString3 = generateRandomString();
        this.s_prop_host = strGenerateRandomString3;
        this.m_prop.put("HOST", strGenerateRandomString3);
        String strGenerateRandomString4 = generateRandomString();
        this.s_prop_user = strGenerateRandomString4;
        this.m_prop.put("USER", strGenerateRandomString4);
        String strGenerateRandomString5 = generateRandomString();
        this.s_prop_flavor = strGenerateRandomString5;
        this.m_prop.put("FLAVOR", strGenerateRandomString5);
        long randomUnixTime = getRandomUnixTime(1623239806L, new Date().getTime() / 1000);
        Date date = new Date(1000 * randomUnixTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        this.s_prop_date = simpleDateFormat.format(date);
        this.s_prop_dateutc = String.valueOf(randomUnixTime);
        String strReplaceAll = this.s_prop_date.replaceAll("GMT.* ", "KST ");
        this.s_prop_date = strReplaceAll;
        this.m_prop.put("DATE", strReplaceAll);
        this.m_prop.put("UTC", this.s_prop_dateutc);
        this.lm_json_prop.add(this.m_prop);
    }

    public void _onCreateJsonOri(double d, ArrayList<HashMap<String, Object>> arrayList) {
        this.lm_json_ori.clear();
        this.m_ori.clear();
        HashMap<String, Object> map = new HashMap<>();
        this.m_ori = map;
        map.put("DEVICE", this.s_prop_device);
        this.m_ori.put("MODEL", this.s_prop_model);
        this.m_ori.put("PRODUCT", this.s_prop_product);
        this.m_ori.put("NAME", this.s_prop_name);
        this.m_ori.put("MANUFACTURER", this.s_prop_manufacturer);
        this.m_ori.put("BRAND", this.s_prop_brand);
        this.m_ori.put("BOOT", this.s_prop_boot);
        this.m_ori.put("BUILDID", this.s_prop_buildid);
        this.m_ori.put("RELEASE", this.s_prop_release);
        this.m_ori.put("INCREMENTAL", this.s_prop_incremental);
        this.m_ori.put("DISPLAY", this.s_prop_display);
        this.m_ori.put("FINGERPRINT", this.s_prop_fingerprint);
        this.m_ori.put("DESCRIPTION", this.s_prop_description);
        this.m_ori.put("SDK", this.s_prop_sdk);
        this.m_ori.put("BOARD", this.s_prop_board);
        this.m_ori.put("HARDWARE", this.s_prop_hardware);
        this.m_ori.put("HOST", this.s_prop_host);
        this.m_ori.put("USER", this.s_prop_user);
        this.m_ori.put("DATE", this.s_prop_date);
        this.m_ori.put("UTC", this.s_prop_dateutc);
        this.m_ori.put("FLAVOR", this.s_prop_flavor);
        this.lm_json_ori.add(this.m_ori);
    }

    public void _onCreateJsonRandom(double d, ArrayList<HashMap<String, Object>> arrayList) {
        this.lm_json_random.clear();
        this.m_random.clear();
        this.m_random = new HashMap<>();
        String strGenerateRandomString = generateRandomString();
        this.s_prop_device = strGenerateRandomString;
        this.m_random.put("DEVICE", strGenerateRandomString);
        String strGenerateRandomString2 = generateRandomString();
        this.s_prop_model = strGenerateRandomString2;
        this.m_random.put("MODEL", strGenerateRandomString2);
        String strGenerateRandomString3 = generateRandomString();
        this.s_prop_product = strGenerateRandomString3;
        this.m_random.put("PRODUCT", strGenerateRandomString3);
        String str = this.s_prop_product;
        this.s_prop_name = str;
        this.m_random.put("NAME", str);
        String strGenerateRandomString4 = generateRandomString();
        this.s_prop_manufacturer = strGenerateRandomString4;
        this.m_random.put("MANUFACTURER", strGenerateRandomString4);
        String strGenerateRandomString5 = generateRandomString();
        this.s_prop_brand = strGenerateRandomString5;
        this.m_random.put("BRAND", strGenerateRandomString5);
        String strGenerateRandomString6 = generateRandomString();
        this.s_prop_boot = strGenerateRandomString6;
        this.m_random.put("BOOT", strGenerateRandomString6);
        String strGenerateRandomString7 = generateRandomString();
        this.s_prop_buildid = strGenerateRandomString7;
        this.m_random.put("BUILDID", strGenerateRandomString7);
        String strGenerateRandomString8 = generateRandomString();
        this.s_prop_incremental = strGenerateRandomString8;
        this.m_random.put("INCREMENTAL", strGenerateRandomString8);
        this.m_random.put("DISPLAY", this.s_prop_buildid.concat(".".concat(this.s_prop_incremental)));
        this.m_random.put("FINGERPRINT", this.s_prop_brand.concat("/".concat(this.s_prop_product.concat("/".concat(this.s_prop_device.concat(":".concat("FURELEASEFU".concat("/".concat(this.s_prop_buildid.concat("/".concat(this.s_prop_incremental.concat(":user/release-keys"))))))))))));
        String strConcat = this.s_prop_name.concat("-user FURELEASEFU ".concat(this.s_prop_buildid.concat(this.s_prop_incremental.concat(" release-keys"))));
        this.s_prop_description = strConcat;
        this.m_random.put("DESCRIPTION", strConcat);
        String strGenerateRandomString9 = generateRandomString();
        this.s_prop_board = strGenerateRandomString9;
        this.m_random.put("BOARD", strGenerateRandomString9);
        String strGenerateRandomString10 = generateRandomString();
        this.s_prop_hardware = strGenerateRandomString10;
        this.m_random.put("HARDWARE", strGenerateRandomString10);
        String strGenerateRandomString11 = generateRandomString();
        this.s_prop_host = strGenerateRandomString11;
        this.m_random.put("HOST", strGenerateRandomString11);
        String strGenerateRandomString12 = generateRandomString();
        this.s_prop_user = strGenerateRandomString12;
        this.m_random.put("USER", strGenerateRandomString12);
        this.m_random.put("DATE", this.s_prop_date);
        this.m_random.put("UTC", this.s_prop_dateutc);
        String strGenerateRandomString13 = generateRandomString();
        this.s_prop_flavor = strGenerateRandomString13;
        this.m_random.put("FLAVOR", strGenerateRandomString13);
        this.lm_json_random.add(this.m_random);
    }

    public void _onChipProp(CheckBox checkBox, final String str, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ((HashMap) arrayList.get((int) d)).put(str, "true");
                    if (str.equals("FINGERPRINT")) {
                        RitualFragmentActivity.this._onGetValueRelease();
                        RitualFragmentActivity ritualFragmentActivity = RitualFragmentActivity.this;
                        ritualFragmentActivity.s_universal = ((HashMap) ritualFragmentActivity.lm_json_random.get((int) d)).get(str).toString().replace("FURELEASEFU", RitualFragmentActivity.this.s_prop_release);
                        ((HashMap) RitualFragmentActivity.this.lm_json_prop.get((int) d)).put(str, RitualFragmentActivity.this.s_universal);
                    } else if (!str.equals("DESCRIPTION")) {
                        ((HashMap) RitualFragmentActivity.this.lm_json_prop.get((int) d)).put(str, ((HashMap) RitualFragmentActivity.this.lm_json_random.get((int) d)).get(str).toString());
                    } else {
                        RitualFragmentActivity.this._onGetValueRelease();
                        RitualFragmentActivity ritualFragmentActivity2 = RitualFragmentActivity.this;
                        ritualFragmentActivity2.s_universal = ((HashMap) ritualFragmentActivity2.lm_json_random.get((int) d)).get(str).toString().replace("FURELEASEFU", RitualFragmentActivity.this.s_prop_release);
                        ((HashMap) RitualFragmentActivity.this.lm_json_prop.get((int) d)).put(str, RitualFragmentActivity.this.s_universal);
                    }
                    ((Rv_2Adapter) RitualFragmentActivity.this.rv_2.getAdapter()).notifyDataSetChanged();
                    return;
                }
                ((HashMap) arrayList.get((int) d)).put(str, "false");
                ((HashMap) RitualFragmentActivity.this.lm_json_prop.get((int) d)).put(str, ((HashMap) RitualFragmentActivity.this.lm_json_ori.get((int) d)).get(str).toString());
                ((Rv_2Adapter) RitualFragmentActivity.this.rv_2.getAdapter()).notifyDataSetChanged();
            }
        });
    }

    public void _onChipRelease(CheckBox checkBox, final String str, final ArrayList<HashMap<String, Object>> arrayList, final double d, final String str2) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ((HashMap) arrayList.get((int) d)).put("OS9", "false");
                    ((HashMap) arrayList.get((int) d)).put("OS10", "false");
                    ((HashMap) arrayList.get((int) d)).put("OS11", "false");
                    ((HashMap) arrayList.get((int) d)).put("OS12", "false");
                    ((HashMap) arrayList.get((int) d)).put("OS12L", "false");
                    ((HashMap) arrayList.get((int) d)).put("OS13", "false");
                    ((HashMap) arrayList.get((int) d)).put("OS14", "false");
                    ((HashMap) arrayList.get((int) d)).put("OSPLUS", "false");
                    ((HashMap) arrayList.get((int) d)).put(str, str2);
                    ((HashMap) RitualFragmentActivity.this.lm_json_prop.get((int) d)).put("RELEASE", str2);
                    if (((HashMap) RitualFragmentActivity.this.lm_json_editor.get(0)).get("FINGERPRINT").toString().equals("true")) {
                        RitualFragmentActivity ritualFragmentActivity = RitualFragmentActivity.this;
                        ritualFragmentActivity.s_universal = ((HashMap) ritualFragmentActivity.lm_json_random.get(0)).get("FINGERPRINT").toString().replace("FURELEASEFU", str2);
                        ((HashMap) RitualFragmentActivity.this.lm_json_prop.get(0)).put("FINGERPRINT", RitualFragmentActivity.this.s_universal);
                    }
                    if (((HashMap) RitualFragmentActivity.this.lm_json_editor.get(0)).get("DESCRIPTION").toString().equals("true")) {
                        RitualFragmentActivity ritualFragmentActivity2 = RitualFragmentActivity.this;
                        ritualFragmentActivity2.s_universal = ((HashMap) ritualFragmentActivity2.lm_json_random.get(0)).get("DESCRIPTION").toString().replace("FURELEASEFU", str2);
                        ((HashMap) RitualFragmentActivity.this.lm_json_prop.get(0)).put("DESCRIPTION", RitualFragmentActivity.this.s_universal);
                    }
                    ((Rv_2Adapter) RitualFragmentActivity.this.rv_2.getAdapter()).notifyDataSetChanged();
                    return;
                }
                ((HashMap) arrayList.get((int) d)).put(str, "false");
                ((HashMap) RitualFragmentActivity.this.lm_json_prop.get((int) d)).put("RELEASE", ((HashMap) RitualFragmentActivity.this.lm_json_ori.get((int) d)).get("RELEASE").toString());
                ((Rv_2Adapter) RitualFragmentActivity.this.rv_2.getAdapter()).notifyDataSetChanged();
            }
        });
    }

    public void _onChipClean(CheckBox checkBox, final String str, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ((HashMap) arrayList.get((int) d)).put("SSAID", "false");
                    ((HashMap) arrayList.get((int) d)).put("RESET0", "false");
                    ((HashMap) arrayList.get((int) d)).put(str, "true");
                    return;
                }
                ((HashMap) arrayList.get((int) d)).put(str, "false");
            }
        });
    }

    public void _onChipReboot(CheckBox checkBox, final String str, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ((HashMap) arrayList.get((int) d)).put("REBOOT", "false");
                    ((HashMap) arrayList.get((int) d)).put("DALVIC", "false");
                    ((HashMap) arrayList.get((int) d)).put("NORESTART", "false");
                    ((HashMap) arrayList.get((int) d)).put(str, "true");
                    return;
                }
                ((HashMap) arrayList.get((int) d)).put(str, "false");
            }
        });
    }

    public void _onGetAllProp() {
        this.pref.edit().putString("preferences_editor", new Gson().toJson(this.lm_json_editor)).commit();
        this.b_command = false;
        _showProgressDialog();
        if (this.lm_json_editor.get(0).get("MODPESSTART").toString().equals("true")) {
            this.s_commandModpes = "settings put global airplane_mode_on 1\nam broadcast -a android.intent.action.AIRPLANE_MODE";
            this.b_command = false;
            Shell.Result resultExec = Shell.cmd("settings put global airplane_mode_on 1\nam broadcast -a android.intent.action.AIRPLANE_MODE").exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            this.b_command = resultExec.isSuccess();
            this.s_commandResult = String.join("\n", out);
            _onGetAllProp2();
            return;
        }
        _onGetAllProp2();
    }

    public void _onGetAllProp2() {
        String str = this.s_commandBase;
        this.s_command = str;
        String strConcat = str.concat("\nogetprop");
        this.s_command = strConcat;
        this.b_command = false;
        Shell.Result resultExec = Shell.cmd(strConcat).exec();
        List<String> out = resultExec.getOut();
        resultExec.getCode();
        this.b_command = resultExec.isSuccess();
        this.s_commandResult = String.join("\n", out);
        _onGetValueProp();
    }

    public void _onGetValueProp() {
        this.s_prop_device = this.lm_json_prop.get(0).get("DEVICE").toString();
        this.s_prop_model = this.lm_json_prop.get(0).get("MODEL").toString();
        this.s_prop_product = this.lm_json_prop.get(0).get("PRODUCT").toString();
        this.s_prop_name = this.lm_json_prop.get(0).get("NAME").toString();
        this.s_prop_manufacturer = this.lm_json_prop.get(0).get("MANUFACTURER").toString();
        this.s_prop_brand = this.lm_json_prop.get(0).get("BRAND").toString();
        this.s_prop_boot = this.lm_json_prop.get(0).get("BOOT").toString();
        this.s_prop_buildid = this.lm_json_prop.get(0).get("BUILDID").toString();
        this.s_prop_fingerprint = this.lm_json_prop.get(0).get("FINGERPRINT").toString();
        this.s_prop_description = this.lm_json_prop.get(0).get("DESCRIPTION").toString();
        this.s_prop_incremental = this.lm_json_prop.get(0).get("INCREMENTAL").toString();
        this.s_prop_display = this.lm_json_prop.get(0).get("DISPLAY").toString();
        this.s_prop_board = this.lm_json_prop.get(0).get("BOARD").toString();
        this.s_prop_hardware = this.lm_json_prop.get(0).get("HARDWARE").toString();
        this.s_prop_host = this.lm_json_prop.get(0).get("HOST").toString();
        this.s_prop_user = this.lm_json_prop.get(0).get("USER").toString();
        this.s_prop_flavor = this.lm_json_prop.get(0).get("FLAVOR").toString();
        this.s_prop_date = this.lm_json_prop.get(0).get("DATE").toString();
        this.s_prop_dateutc = this.lm_json_prop.get(0).get("UTC").toString();
        if (this.lm_json_editor.get(0).get("OS9").toString().equals("9")) {
            this.s_prop_release = "9";
        } else if (this.lm_json_editor.get(0).get("OS10").toString().equals("10")) {
            this.s_prop_release = "10";
        } else if (this.lm_json_editor.get(0).get("OS11").toString().equals("11")) {
            this.s_prop_release = "11";
        } else if (this.lm_json_editor.get(0).get("OS12").toString().equals("12")) {
            this.s_prop_release = "12";
        } else if (this.lm_json_editor.get(0).get("OS12L").toString().equals("12.1")) {
            this.s_prop_release = "12.1";
        } else if (this.lm_json_editor.get(0).get("OS13").toString().equals("13")) {
            this.s_prop_release = "13";
        } else if (this.lm_json_editor.get(0).get("OS14").toString().equals("14")) {
            this.s_prop_release = "14";
        } else if (!this.lm_json_editor.get(0).get("OSPLUS").toString().equals("false")) {
            this.s_prop_release = this.prefos.getString("OSPLUS", "");
        } else {
            this.s_prop_release = this.lm_json_prop.get(0).get("RELEASE").toString();
        }
        _onReplaceProp();
    }

    public void _onReplaceProp() {
        MyREPLACEPROP myREPLACEPROP = this.myREPLACEPROP;
        if (myREPLACEPROP != null && myREPLACEPROP.isRunning) {
            this.myREPLACEPROP.cancelREPLACEPROPTask();
        }
        MyREPLACEPROP myREPLACEPROP2 = new MyREPLACEPROP();
        this.myREPLACEPROP = myREPLACEPROP2;
        myREPLACEPROP2.execute(new Void[0]);
    }

    public class MyREPLACEPROP extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyREPLACEPROP() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            RitualFragmentActivity.this.m_systemprop = new HashMap();
            RitualFragmentActivity.this.m_systemprop.put("BOARD", RitualFragmentActivity.this.s_prop_board);
            RitualFragmentActivity.this.m_systemprop.put("BUILDID", RitualFragmentActivity.this.s_prop_buildid);
            RitualFragmentActivity.this.m_systemprop.put("DEVICE", RitualFragmentActivity.this.s_prop_device);
            RitualFragmentActivity.this.m_systemprop.put("MODEL", RitualFragmentActivity.this.s_prop_model);
            RitualFragmentActivity.this.m_systemprop.put("MANUFACTURER", RitualFragmentActivity.this.s_prop_manufacturer);
            RitualFragmentActivity.this.m_systemprop.put("RELEASE", RitualFragmentActivity.this.s_prop_release);
            RitualFragmentActivity.this.m_systemprop.put("USER", RitualFragmentActivity.this.s_prop_user);
            RitualFragmentActivity.this.m_systemprop.put("BRAND", RitualFragmentActivity.this.s_prop_brand);
            RitualFragmentActivity.this.m_systemprop.put("DISPLAYID", RitualFragmentActivity.this.s_prop_display);
            RitualFragmentActivity.this.m_systemprop.put("FINGERPRINT", RitualFragmentActivity.this.s_prop_fingerprint);
            RitualFragmentActivity.this.m_systemprop.put("DESCRIPTION", RitualFragmentActivity.this.s_prop_description);
            RitualFragmentActivity.this.m_systemprop.put("PRODUCT", RitualFragmentActivity.this.s_prop_product);
            RitualFragmentActivity.this.m_systemprop.put("NAME", RitualFragmentActivity.this.s_prop_name);
            RitualFragmentActivity.this.m_systemprop.put("INCREMENTAL", RitualFragmentActivity.this.s_prop_incremental);
            RitualFragmentActivity.this.m_systemprop.put("HOST", RitualFragmentActivity.this.s_prop_host);
            RitualFragmentActivity.this.m_systemprop.put("BOOTLOADER", RitualFragmentActivity.this.s_prop_boot);
            RitualFragmentActivity.this.m_systemprop.put("HARDWARE", RitualFragmentActivity.this.s_prop_hardware);
            RitualFragmentActivity.this.m_systemprop.put("DATE", RitualFragmentActivity.this.s_prop_date);
            RitualFragmentActivity.this.m_systemprop.put("UTC", RitualFragmentActivity.this.s_prop_dateutc);
            RitualFragmentActivity.this.b_command = false;
            RitualFragmentActivity ritualFragmentActivity = RitualFragmentActivity.this;
            ritualFragmentActivity.s_propvalue = FileUtil.readFile(ritualFragmentActivity.s_defaultprop);
            RitualFragmentActivity ritualFragmentActivity2 = RitualFragmentActivity.this;
            ritualFragmentActivity2.s_propvalue = ritualFragmentActivity2.s_propvalue.replace("ro.boot.hardware=", "#");
            RitualFragmentActivity ritualFragmentActivity3 = RitualFragmentActivity.this;
            ritualFragmentActivity3.s_propvalue = ritualFragmentActivity3.s_propvalue.replace("ro.build.flavor=", "#");
            RitualFragmentActivity ritualFragmentActivity4 = RitualFragmentActivity.this;
            ritualFragmentActivity4.s_propvalue = ritualFragmentActivity4.s_propvalue.replace(".display.id=", ".displayid=");
            RitualFragmentActivity ritualFragmentActivity5 = RitualFragmentActivity.this;
            ritualFragmentActivity5.s_propvalue = ritualFragmentActivity5.s_propvalue.replace(".board=", ".board ".concat(RitualFragmentActivity.this.s_prop_board));
            RitualFragmentActivity ritualFragmentActivity6 = RitualFragmentActivity.this;
            ritualFragmentActivity6.s_propvalue = ritualFragmentActivity6.s_propvalue.replace(".id=", ".id ".concat(RitualFragmentActivity.this.s_prop_buildid));
            RitualFragmentActivity ritualFragmentActivity7 = RitualFragmentActivity.this;
            ritualFragmentActivity7.s_propvalue = ritualFragmentActivity7.s_propvalue.replace(".device=", ".device ".concat(RitualFragmentActivity.this.s_prop_device));
            RitualFragmentActivity ritualFragmentActivity8 = RitualFragmentActivity.this;
            ritualFragmentActivity8.s_propvalue = ritualFragmentActivity8.s_propvalue.replace(".model=", ".model ".concat(RitualFragmentActivity.this.s_prop_model));
            RitualFragmentActivity ritualFragmentActivity9 = RitualFragmentActivity.this;
            ritualFragmentActivity9.s_propvalue = ritualFragmentActivity9.s_propvalue.replace(".manufacturer=", ".manufacturer ".concat(RitualFragmentActivity.this.s_prop_manufacturer));
            RitualFragmentActivity ritualFragmentActivity10 = RitualFragmentActivity.this;
            ritualFragmentActivity10.s_propvalue = ritualFragmentActivity10.s_propvalue.replace(".release=", ".release ".concat(RitualFragmentActivity.this.s_prop_release));
            RitualFragmentActivity ritualFragmentActivity11 = RitualFragmentActivity.this;
            ritualFragmentActivity11.s_propvalue = ritualFragmentActivity11.s_propvalue.replace(".user=", ".user ".concat(RitualFragmentActivity.this.s_prop_user));
            RitualFragmentActivity ritualFragmentActivity12 = RitualFragmentActivity.this;
            ritualFragmentActivity12.s_propvalue = ritualFragmentActivity12.s_propvalue.replace(".brand=", ".brand ".concat(RitualFragmentActivity.this.s_prop_brand));
            RitualFragmentActivity ritualFragmentActivity13 = RitualFragmentActivity.this;
            ritualFragmentActivity13.s_propvalue = ritualFragmentActivity13.s_propvalue.replace(".displayid=", ".displayid ".concat(RitualFragmentActivity.this.s_prop_display));
            RitualFragmentActivity ritualFragmentActivity14 = RitualFragmentActivity.this;
            ritualFragmentActivity14.s_propvalue = ritualFragmentActivity14.s_propvalue.replace(".fingerprint=", ".fingerprint ".concat(RitualFragmentActivity.this.s_prop_fingerprint));
            RitualFragmentActivity ritualFragmentActivity15 = RitualFragmentActivity.this;
            ritualFragmentActivity15.s_propvalue = ritualFragmentActivity15.s_propvalue.replace(".description=", ".description ".concat(RitualFragmentActivity.this.s_prop_description));
            RitualFragmentActivity ritualFragmentActivity16 = RitualFragmentActivity.this;
            ritualFragmentActivity16.s_propvalue = ritualFragmentActivity16.s_propvalue.replace(".product=", ".product ".concat(RitualFragmentActivity.this.s_prop_product));
            RitualFragmentActivity ritualFragmentActivity17 = RitualFragmentActivity.this;
            ritualFragmentActivity17.s_propvalue = ritualFragmentActivity17.s_propvalue.replace(".name=", ".name ".concat(RitualFragmentActivity.this.s_prop_name));
            RitualFragmentActivity ritualFragmentActivity18 = RitualFragmentActivity.this;
            ritualFragmentActivity18.s_propvalue = ritualFragmentActivity18.s_propvalue.replace(".incremental=", ".incremental ".concat(RitualFragmentActivity.this.s_prop_incremental));
            RitualFragmentActivity ritualFragmentActivity19 = RitualFragmentActivity.this;
            ritualFragmentActivity19.s_propvalue = ritualFragmentActivity19.s_propvalue.replace(".host=", ".host ".concat(RitualFragmentActivity.this.s_prop_host));
            RitualFragmentActivity ritualFragmentActivity20 = RitualFragmentActivity.this;
            ritualFragmentActivity20.s_propvalue = ritualFragmentActivity20.s_propvalue.replace(".bootloader=", ".bootloader ".concat(RitualFragmentActivity.this.s_prop_boot));
            RitualFragmentActivity ritualFragmentActivity21 = RitualFragmentActivity.this;
            ritualFragmentActivity21.s_propvalue = ritualFragmentActivity21.s_propvalue.replace(".hardware=", ".hardware ".concat(RitualFragmentActivity.this.s_prop_hardware));
            RitualFragmentActivity ritualFragmentActivity22 = RitualFragmentActivity.this;
            ritualFragmentActivity22.s_propvalue = ritualFragmentActivity22.s_propvalue.replace(".date=", ".date ".concat(RitualFragmentActivity.this.s_prop_date));
            RitualFragmentActivity ritualFragmentActivity23 = RitualFragmentActivity.this;
            ritualFragmentActivity23.s_propvalue = ritualFragmentActivity23.s_propvalue.replace(".date.utc=", ".date.utc ".concat(RitualFragmentActivity.this.s_prop_dateutc));
            RitualFragmentActivity ritualFragmentActivity24 = RitualFragmentActivity.this;
            ritualFragmentActivity24.s_propvalue = ritualFragmentActivity24.s_propvalue.replace(".displayid=", ".display.id ");
            RitualFragmentActivity.this.s_printprop = new Gson().toJson(RitualFragmentActivity.this.m_systemprop);
            RitualFragmentActivity.this.s_printprop = new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement) new Gson().fromJson(RitualFragmentActivity.this.s_printprop, JsonObject.class));
            FileUtil.writeFile(RitualFragmentActivity.this.s_systemprop, RitualFragmentActivity.this.s_printprop);
            String[] strArrSplit = RitualFragmentActivity.this.s_propvalue.split("\n");
            StringBuilder sb = new StringBuilder();
            for (String strReplaceFirst : strArrSplit) {
                if (strReplaceFirst.startsWith("ro.")) {
                    strReplaceFirst = strReplaceFirst.replaceFirst("ro\\.", "check_resetprop ro.");
                }
                sb.append(strReplaceFirst);
                sb.append("\n");
            }
            RitualFragmentActivity.this.s_propvalue = sb.toString().trim();
            try {
                RitualFragmentActivity.this.s_post_fs_data = SketchwareUtil.copyFromInputStream(RitualFragmentActivity.this.getContext().getAssets().open("main2.dex"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            RitualFragmentActivity ritualFragmentActivity25 = RitualFragmentActivity.this;
            ritualFragmentActivity25.s_post_fs_data = ritualFragmentActivity25.s_post_fs_data.replace("#XKatrina", "#XKatrina\n".concat(RitualFragmentActivity.this.s_propvalue));
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            FileUtil.writeFile(RitualFragmentActivity.this.s_sensitiveprop, RitualFragmentActivity.this.s_post_fs_data);
            RitualFragmentActivity ritualFragmentActivity = RitualFragmentActivity.this;
            ritualFragmentActivity.s_command = "\ncp ".concat(ritualFragmentActivity.s_sensitiveprop.concat(" /data/adb/modules/xkatrina_snstv_prps/post-fs-data.sh\nchmod 0644 /data/adb/modules/xkatrina_snstv_prps/post-fs-data.sh"));
            RitualFragmentActivity ritualFragmentActivity2 = RitualFragmentActivity.this;
            ritualFragmentActivity2.s_command = ritualFragmentActivity2.s_command.concat("\ncp ".concat(RitualFragmentActivity.this.s_systemprop.concat(" /data/adb/modules/xkatrina_snstv_prps/fu.dex\nchmod 0644 /data/adb/modules/xkatrina_snstv_prps/fu.dex")));
            RitualFragmentActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(RitualFragmentActivity.this.s_command).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            RitualFragmentActivity.this.b_command = resultExec.isSuccess();
            RitualFragmentActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            if (RitualFragmentActivity.this.b_command) {
                RitualFragmentActivity.this._onCleanerWipe();
            }
        }

        public void cancelREPLACEPROPTask() {
            cancel(true);
        }
    }

    public void _showProgressDialog() {
        showLOADING();
    }

    private void showLOADING() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_dialog_uni_no_button, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        ((TextView) viewInflate.findViewById(C0978R.id.tv_uni_dialog)).setText("Mohon menunggu...");
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.LOADING = alertDialogCreate;
        alertDialogCreate.show();
    }

    public void _hideProgressDialog() {
        AlertDialog alertDialog = this.LOADING;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.LOADING.dismiss();
    }

    public void _onGetValueRelease() {
        if (this.lm_json_editor.get(0).get("OS9").toString().equals("9")) {
            this.s_prop_release = "9";
            return;
        }
        if (this.lm_json_editor.get(0).get("OS10").toString().equals("10")) {
            this.s_prop_release = "10";
            return;
        }
        if (this.lm_json_editor.get(0).get("OS11").toString().equals("11")) {
            this.s_prop_release = "11";
            return;
        }
        if (this.lm_json_editor.get(0).get("OS12").toString().equals("12")) {
            this.s_prop_release = "12";
            return;
        }
        if (this.lm_json_editor.get(0).get("OS12L").toString().equals("12.1")) {
            this.s_prop_release = "12.1";
            return;
        }
        if (this.lm_json_editor.get(0).get("OS13").toString().equals("13")) {
            this.s_prop_release = "13";
        } else if (this.lm_json_editor.get(0).get("OS14").toString().equals("14")) {
            this.s_prop_release = "14";
        } else {
            this.s_prop_release = this.lm_json_prop.get(0).get("RELEASE").toString();
        }
    }

    public void _onCleanerWipe() {
        this.b_command = false;
        this.extraprop = "true";
        this.s_command = this.s_commandBase;
        this.s_commandResult = "";
        this.s_exitCode = "";
        if (this.lm_json_editor.get(0).get("TIMEPICK").toString().equals("true")) {
            this.s_command = this.s_command.concat("\nocleaner");
            this.extracleaner = "true";
        } else {
            this.extracleaner = "false";
        }
        if (this.lm_json_editor.get(0).get("WIPEGMS").toString().equals("true")) {
            this.s_command = this.s_command.concat("\nowipegms");
            this.extrawipe = "true";
        } else {
            this.extrawipe = "false";
        }
        if (this.lm_json_editor.get(0).get("SSAID").toString().equals("true")) {
            this.s_command = this.s_command.concat("\nooossaid");
            this.extrassaid = "true";
        } else {
            this.extrassaid = "false";
        }
        if (this.lm_json_editor.get(0).get("RESET0").toString().equals("true")) {
            this.s_command = this.s_command.concat("\nooooonol");
            this.extrareset = "true";
        } else {
            this.extrareset = "false";
        }
        if (this.lm_json_editor.get(0).get("REBOOT").toString().equals("true")) {
            this.extrareboot = "true";
        } else {
            this.extrareboot = "false";
        }
        if (this.lm_json_editor.get(0).get("DALVIC").toString().equals("true")) {
            this.extradalvic = "true";
        } else {
            this.extradalvic = "false";
        }
        if (this.lm_json_editor.get(0).get("NORESTART").toString().equals("true")) {
            this.extranorestart = "true";
        } else {
            this.extranorestart = "false";
        }
        if (this.lm_json_editor.get(0).get("MODPESEND").toString().equals("true")) {
            this.extramodpes = "true";
        } else {
            this.extramodpes = "false";
        }
        MyCLEANERWIPE myCLEANERWIPE = this.myCLEANERWIPE;
        if (myCLEANERWIPE != null && myCLEANERWIPE.isRunning) {
            this.myCLEANERWIPE.cancelCLEANERWIPETask();
        }
        MyCLEANERWIPE myCLEANERWIPE2 = new MyCLEANERWIPE();
        this.myCLEANERWIPE = myCLEANERWIPE2;
        myCLEANERWIPE2.execute(new Void[0]);
    }

    public class MyCLEANERWIPE extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyCLEANERWIPE() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            RitualFragmentActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(RitualFragmentActivity.this.s_command).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            RitualFragmentActivity.this.b_command = resultExec.isSuccess();
            RitualFragmentActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            RitualFragmentActivity.this._goToResult();
        }

        public void cancelCLEANERWIPETask() {
            cancel(true);
        }
    }

    public void _onChipWipe(CheckBox checkBox, final String str, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ((HashMap) arrayList.get((int) d)).put(str, "true");
                } else {
                    ((HashMap) arrayList.get((int) d)).put(str, "false");
                }
            }
        });
    }

    public void _goToResult() {
        _hideProgressDialog();
        this.f859i.setClass(getContext().getApplicationContext(), RitualResultActivity.class);
        this.f859i.putExtra("PROP", this.extraprop);
        this.f859i.putExtra("RESET0", this.extrareset);
        this.f859i.putExtra("WIPEGMS", this.extrawipe);
        this.f859i.putExtra("SSAID", this.extrassaid);
        this.f859i.putExtra("CLEANER", this.extracleaner);
        this.f859i.putExtra("REBOOT", this.extrareboot);
        this.f859i.putExtra("DALVIC", this.extradalvic);
        this.f859i.putExtra("NORESTART", this.extranorestart);
        this.f859i.putExtra("MODPES", this.extramodpes);
        startActivity(this.f859i);
    }

    public void _onConvertProp() {
        this.s_input_prop = this.auto_input_fp.getText().toString();
        Matcher matcher = Pattern.compile("([^/]+)/(.*?)/(.*?):(.*?)/(.*?)/(.*?):(.*?)/(.*?)").matcher(this.s_input_prop);
        if (matcher.find()) {
            this.s_parsebrand = matcher.group(1);
            this.s_parsedevice = matcher.group(2);
            this.s_parseproduct = matcher.group(3);
            this.s_parserelease = matcher.group(4);
            this.s_parsebuildid = matcher.group(5);
            this.s_parseincremental = matcher.group(6);
            matcher.group(7);
            if (this.auto_input_model.getText().toString().equals("")) {
                this.s_parsemodel = this.s_parsedevice;
            } else {
                this.s_parsemodel = this.auto_input_model.getText().toString();
            }
            this.auto_input_fp.clearFocus();
            this.auto_input_model.clearFocus();
            KeyboardUtils.toggleKeyboardVisibility(requireContext(), this.auto_input_fp);
            KeyboardUtils.toggleKeyboardVisibility(requireContext(), this.auto_input_model);
            this.s_rv2 = this.s_parsemodel;
            this.lm_input.clear();
            this.m_input.clear();
            HashMap<String, Object> map = new HashMap<>();
            this.m_input = map;
            map.put("DEVICENAME", this.s_parsemodel);
            this.m_input.put("MANUFACTURER", this.s_parsebrand);
            this.m_input.put("MODEL", this.s_parsemodel);
            this.m_input.put("BRAND", this.s_parsebrand);
            this.m_input.put("PRODUCT", this.s_parseproduct);
            this.m_input.put("DEVICE", this.s_parsedevice);
            this.m_input.put("RELEASE", this.s_parserelease);
            this.m_input.put("BUILDID", this.s_parsebuildid);
            this.m_input.put("INCREMENTAL", this.s_parseincremental);
            this.lm_input.add(this.m_input);
            this.s_json_old = FileUtil.readFile(this.s_add_prop);
            String json = new Gson().toJson(this.lm_input);
            this.s_data_new = json;
            try {
                JSONArray jSONArray = new JSONArray(this.s_json_old);
                JSONArray jSONArray2 = new JSONArray(json);
                JSONArray jSONArray3 = jSONArray.getJSONObject(0).getJSONArray("DATA");
                for (int i = 0; i < jSONArray2.length(); i++) {
                    jSONArray3.put(jSONArray2.getJSONObject(i));
                }
                this.s_input_json = jSONArray.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String json2 = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(this.s_input_json));
            this.s_input_json = json2;
            FileUtil.writeFile(this.s_add_prop, json2);
            _onCreateJsonProp(0.0d, this.lm_input);
            _onCreateJsonOri(0.0d, this.lm_input);
            _onCreateJsonRandom(0.0d, this.lm_input);
            _onModelLoadProp();
            return;
        }
        this.til_input_fp.setError("Fingerprint tidak sesuai format");
    }

    public void _setPropType(String str) {
        KeyboardUtils.toggleKeyboardVisibility(requireContext(), this.et_input_dump);
        KeyboardUtils.toggleKeyboardVisibility(requireContext(), this.auto_input_fp);
        KeyboardUtils.toggleKeyboardVisibility(requireContext(), this.auto_input_model);
        this.tv_prop_type.setText(str);
        this.lv_branch_all.setVisibility(8);
        this.ln_input_dump.setVisibility(8);
        this.ln_input_prop.setVisibility(0);
        this.tv_response.setVisibility(8);
        this.rv_1.setVisibility(0);
        this.ln_input_fp.setVisibility(8);
        this.et_input_dump.clearFocus();
        this.auto_input_model.clearFocus();
        this.auto_input_fp.clearFocus();
        if (this.tv_prop_type.getText().toString().equals("Android Dump")) {
            _onLoadDump();
        } else if (this.tv_prop_type.getText().toString().contains("Online Dump")) {
            this.ls_feed_dump.clear();
            if (FileUtil.isExistFile(this.s_fufufu_dump_online)) {
                String file = FileUtil.readFile(this.s_fufufu_dump_online);
                this.s_feed_dump = file;
                ArrayList arrayList = new ArrayList(Arrays.asList(file.split("\n")));
                this.et_input_dump.dismissDropDown();
                this.et_input_dump.setThreshold(1);
                this.et_input_dump.setAdapter(new CustomAdapter(getActivity().getBaseContext(), C0978R.layout.dump_layout, arrayList));
                this.et_input_dump.showDropDown();
            } else {
                this.s_feed_dump = "• Data untuk VIP\n• Input manual";
                this.ls_feed_dump = new ArrayList<>(Arrays.asList(this.s_feed_dump.split("---")));
                this.et_input_dump.setAdapter(new CustomAdapter(getActivity().getBaseContext(), C0978R.layout.dump_layout, this.ls_feed_dump));
                this.et_input_dump.showDropDown();
            }
            _onLoadingOnline("clickmenu");
        } else {
            _onLoadBrand();
        }
        _onCollapseFingerInput("clickmenu");
    }

    public void _onLoadDump() {
        MyPROPDUMP myPROPDUMP = this.myPROPDUMP;
        if (myPROPDUMP != null && myPROPDUMP.isRunning) {
            this.myPROPDUMP.cancelPROPDUMPTask();
        }
        MyPROPDUMP myPROPDUMP2 = new MyPROPDUMP();
        this.myPROPDUMP = myPROPDUMP2;
        myPROPDUMP2.execute(new Void[0]);
    }

    public class MyPROPDUMP extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyPROPDUMP() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            RitualFragmentActivity.this.rv_1.setVisibility(0);
            RitualFragmentActivity.this.rv_2.setVisibility(8);
            RitualFragmentActivity.this.rv_3.setVisibility(8);
            RitualFragmentActivity.this._fab.setVisibility(8);
            RitualFragmentActivity.this.pbar_prop.setVisibility(0);
            RitualFragmentActivity.this.btn_prop.setEnabled(false);
            RitualFragmentActivity.this.btn_dump.setEnabled(false);
            RitualFragmentActivity.this.btn_dumpall.setEnabled(false);
            RitualFragmentActivity.this.tv_title.setText("BRAND");
            RitualFragmentActivity.this.lm_json_brand.clear();
            if (FileUtil.isExistFile(RitualFragmentActivity.this.s_add_prop)) {
                RitualFragmentActivity ritualFragmentActivity = RitualFragmentActivity.this;
                ritualFragmentActivity.s_custom_prop = FileUtil.readFile(ritualFragmentActivity.s_add_prop);
                RitualFragmentActivity ritualFragmentActivity2 = RitualFragmentActivity.this;
                if (ritualFragmentActivity2.jsonIsValid(ritualFragmentActivity2.s_custom_prop)) {
                    RitualFragmentActivity.this.lm_json_brand = (ArrayList) new Gson().fromJson(RitualFragmentActivity.this.s_custom_prop, new TypeToken<ArrayList<HashMap<String, Object>>>() {                     }.getType());
                }
            }
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            try {
                InputStream inputStreamOpen = RitualFragmentActivity.this.getContext().getAssets().open("dump.json");
                RitualFragmentActivity.this.lm_json_asset = (ArrayList) new Gson().fromJson(SketchwareUtil.copyFromInputStream(inputStreamOpen), new TypeToken<ArrayList<HashMap<String, Object>>>() {                 }.getType());
                RitualFragmentActivity.this.lm_json_brand.addAll(RitualFragmentActivity.this.lm_json_asset);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r4) {
            this.isRunning = false;
            RecyclerView recyclerView = RitualFragmentActivity.this.rv_1;
            RitualFragmentActivity ritualFragmentActivity = RitualFragmentActivity.this;
            recyclerView.setAdapter(ritualFragmentActivity.new Rv_1Adapter(ritualFragmentActivity.lm_json_brand));
            RitualFragmentActivity.this.rv_1.setLayoutManager(new LinearLayoutManager(RitualFragmentActivity.this.getContext()));
            RitualFragmentActivity.this.btn_back.setVisibility(8);
            RitualFragmentActivity.this.btn_prop.setEnabled(true);
            RitualFragmentActivity.this.btn_dump.setEnabled(true);
            RitualFragmentActivity.this.btn_dumpall.setEnabled(true);
            RitualFragmentActivity.this.pbar_prop.setVisibility(8);
        }

        public void cancelPROPDUMPTask() {
            cancel(true);
        }
    }

    public void _getPropResult() {
        this.lm_dump_prop.clear();
        this.ls_dump_prop.clear();
        this.s_response_result = removeLines(this.s_response_result);
        this.ls_dump_prop = new ArrayList<>(Arrays.asList(this.s_response_result.split("\n")));
        HashMap<String, Object> map = new HashMap<>();
        this.m_prop = map;
        map.put("ONLINE", "true");
        this.lm_dump_prop.add(this.m_prop);
        this.f860n = 0.0d;
        for (int i = 0; i < this.ls_dump_prop.size(); i++) {
            if (this.ls_dump_prop.get((int) this.f860n).contains("manufacturer=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("MANUFACTURER", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("model=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("MODEL", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("version.release=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("RELEASE", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("hardware=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("HARDWARE", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("date=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("DATE", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("date.utc=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("UTC", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("fingerprint=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("FINGERPRINT", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("build.id=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("BUILDID", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("incremental=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("INCREMENTAL", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("bootloader=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("BOOT", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("description=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("DESCRIPTION", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("display.id=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("DISPLAY", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("flavor=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("FLAVOR", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("product=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("PRODUCT", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("host=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("HOST", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("user=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("USER", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("board=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("BOARD", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("brand=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("BRAND", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("device=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("DEVICE", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f860n).contains("name=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f860n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("NAME", this.s_prop_match);
            }
            this.f860n += 1.0d;
        }
        _onLoadingOnline("clickafterchild");
        _onConvertOnline();
    }

    public void _onResponseError(String str) {
        this.tv_response.setVisibility(0);
        this.tv_response.setText(str);
        this.pbar_prop.setVisibility(8);
    }

    public void _onLoadingOnline(String str) {
        if (str.equals("clickmenu")) {
            this.tv_title.setText(Uri.parse(this.s_dump_model).getLastPathSegment());
            this.rv_1.setVisibility(8);
            this.rv_2.setVisibility(8);
            this.rv_3.setVisibility(8);
            this._fab.setVisibility(8);
            this.btn_back.setVisibility(8);
            this.lv_branch_all.setVisibility(8);
            this.tv_response.setVisibility(8);
            this.pbar_prop.setVisibility(8);
            this.ln_input_prop.setVisibility(8);
            this.ln_input_dump.setVisibility(0);
            return;
        }
        if (str.equals("clickget")) {
            this.et_input_dump.clearFocus();
            this.tv_title.setText(Uri.parse(this.s_dump_model).getLastPathSegment());
            this.pbar_prop.setVisibility(0);
            this.tv_response.setVisibility(8);
            this.btn_back.setVisibility(8);
            return;
        }
        if (str.equals("clickafterget")) {
            this.pbar_prop.setVisibility(8);
            this.btn_back.setVisibility(8);
            this.tv_response.setVisibility(8);
            this.lv_branch_all.setVisibility(0);
            return;
        }
        if (str.equals("clickchild")) {
            this.pbar_prop.setVisibility(0);
            this.btn_back.setVisibility(8);
            this.tv_response.setVisibility(8);
            this.lv_branch_all.setVisibility(8);
            return;
        }
        if (str.equals("clickafterchild")) {
            this.btn_back.setVisibility(0);
            this.tv_response.setVisibility(8);
            this.pbar_prop.setVisibility(8);
        } else if (str.equals("clicknotfound")) {
            _onResponseError("Prop tidak ditemukan");
        }
    }

    public void _onCollapseFingerInput(String str) {
        if (str.equals("clickbutton")) {
            if (this.ln_input_fp.isExpanded()) {
                this.ln_input_fp.collapse();
                this.btn_collapse_input.setRotation(0.0f);
                return;
            } else {
                this.ln_input_fp.expand();
                this.btn_collapse_input.setRotation(180.0f);
                return;
            }
        }
        if (this.ln_input_fp.isExpanded()) {
            this.ln_input_fp.collapse();
            this.btn_collapse_input.setRotation(0.0f);
        }
    }

    public void _onGetDumpOnline() {
        if (this.et_input_dump.getText().toString().contains("/")) {
            KeyboardUtils.toggleKeyboardVisibility(requireContext(), this.et_input_dump);
            String string = this.et_input_dump.getText().toString();
            this.s_dump_model = string;
            this.s_input_model = string.toLowerCase().concat(this.s_dump_all);
            this.s_raw_model = this.s_dump_model.concat(this.s_dump_raw);
            _onLoadingOnline("clickget");
            this.get_branch_all.startRequestNetwork("GET", this.s_dump_head.concat(this.s_input_model), "a", this._get_branch_all_request_listener);
            return;
        }
        SketchwareUtil.showMessage(getContext().getApplicationContext(), "Input tidak valid");
    }

    public void _onConvertOnline() {
        if (!this.lm_dump_prop.get(0).containsKey("MANUFACTURER")) {
            this.lm_dump_prop.get(0).put("MANUFACTURER", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("MODEL")) {
            this.lm_dump_prop.get(0).put("MODEL", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("RELEASE")) {
            this.lm_dump_prop.get(0).put("RELEASE", Build.VERSION.RELEASE);
        }
        if (!this.lm_dump_prop.get(0).containsKey("DATE")) {
            this.lm_dump_prop.get(0).put("DATE", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("UTC")) {
            this.lm_dump_prop.get(0).put("UTC", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("FINGERPRINT")) {
            this.lm_dump_prop.get(0).put("FINGERPRINT", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("BUILDID")) {
            this.lm_dump_prop.get(0).put("BUILDID", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("INCREMENTAL")) {
            this.lm_dump_prop.get(0).put("INCREMENTAL", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("HOST")) {
            this.lm_dump_prop.get(0).put("HOST", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("USER")) {
            this.lm_dump_prop.get(0).put("USER", Build.VERSION.RELEASE);
        }
        if (!this.lm_dump_prop.get(0).containsKey("BRAND")) {
            this.lm_dump_prop.get(0).put("BRAND", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("DEVICE")) {
            this.lm_dump_prop.get(0).put("DEVICE", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("PRODUCT")) {
            this.lm_dump_prop.get(0).put("PRODUCT", this.lm_dump_prop.get(0).get("DEVICE").toString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("NAME")) {
            this.lm_dump_prop.get(0).put("NAME", this.lm_dump_prop.get(0).get("PRODUCT").toString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("SDK")) {
            this.lm_dump_prop.get(0).put("SDK", Build.VERSION.SDK);
        }
        if (!this.lm_dump_prop.get(0).containsKey("HARDWARE")) {
            this.lm_dump_prop.get(0).put("HARDWARE", this.lm_dump_prop.get(0).get("INCREMENTAL").toString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("BOOT")) {
            this.lm_dump_prop.get(0).put("BOOT", this.lm_dump_prop.get(0).get("INCREMENTAL").toString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("FLAVOR")) {
            this.lm_dump_prop.get(0).put("FLAVOR", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("BOARD")) {
            this.lm_dump_prop.get(0).put("BOARD", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("DESCRIPTION")) {
            this.s_prop_incremental = this.lm_dump_prop.get(0).get("INCREMENTAL").toString();
            this.s_prop_release = this.lm_dump_prop.get(0).get("RELEASE").toString();
            this.s_prop_buildid = this.lm_dump_prop.get(0).get("BUILDID").toString();
            String string = this.lm_dump_prop.get(0).get("NAME").toString();
            this.s_prop_name = string;
            this.s_prop_description = string.concat("-user ".concat(this.s_prop_release.concat(" ".concat(this.s_prop_buildid.concat(this.s_prop_incremental.concat(" release-keys"))))));
            this.lm_dump_prop.get(0).put("DESCRIPTION", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("DISPLAY")) {
            this.s_prop_incremental = this.lm_dump_prop.get(0).get("INCREMENTAL").toString();
            String string2 = this.lm_dump_prop.get(0).get("BUILDID").toString();
            this.s_prop_buildid = string2;
            this.s_prop_display = string2.concat(".".concat(this.s_prop_incremental));
            this.lm_dump_prop.get(0).put("DISPLAY", generateRandomString());
        }
        this.lm_json_ori.clear();
        this.lm_json_prop.clear();
        this.lm_json_prop.addAll(this.lm_dump_prop);
        this.s_rv2 = Uri.parse(this.s_dump_model).getLastPathSegment();
        _onCreateJsonOri(0.0d, this.lm_dump_prop);
        _onCreateJsonRandom(0.0d, this.lm_dump_prop);
        _onModelLoadProp();
    }

    public void _showDialogUpdate() {
        showUPDATE();
    }

    private void showUPDATE() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.dialog_update, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_update_version);
        TextView textView2 = (TextView) viewInflate.findViewById(C0978R.id.tv_update_date);
        TextView textView3 = (TextView) viewInflate.findViewById(C0978R.id.tv_update_log);
        final TextView textView4 = (TextView) viewInflate.findViewById(C0978R.id.tv_link);
        TextView textView5 = (TextView) viewInflate.findViewById(C0978R.id.tv_01);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_close);
        Button button2 = (Button) viewInflate.findViewById(C0978R.id.im_copy);
        this.btn_download = (Button) viewInflate.findViewById(C0978R.id.btn_download);
        ProgressBar progressBar = (ProgressBar) viewInflate.findViewById(C0978R.id.pbar_1);
        this.progressBar = progressBar;
        progressBar.setVisibility(8);
        textView.setText(textView.getText().toString().concat(this.lm_release.get(0).get("release_version").toString()));
        textView2.setText(textView2.getText().toString().concat(this.lm_release.get(0).get("release_date").toString()));
        textView3.setText(this.lm_release.get(0).get("release_log").toString());
        textView4.setText(this.lm_release.get(0).get("release_url").toString());
        if (this.lm_release.get(0).get("release_force").toString().equals("true")) {
            this.b_update_force = true;
            button.setVisibility(8);
        } else {
            this.b_update_force = false;
            button.setVisibility(0);
            button.setText("Nanti");
        }
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RitualFragmentActivity.this.b_update_force) {
                    return;
                }
                RitualFragmentActivity.this.UPDATE.dismiss();
            }
        });
        this.btn_download.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RitualFragmentActivity ritualFragmentActivity = RitualFragmentActivity.this;
                ritualFragmentActivity.s_filename = "/data/user/0/".concat(ritualFragmentActivity.getContext().getApplicationContext().getPackageName().concat("/xkatrina.apk"));
                RitualFragmentActivity ritualFragmentActivity2 = RitualFragmentActivity.this;
                ritualFragmentActivity2.s_url = ((HashMap) ritualFragmentActivity2.lm_release.get(0)).get("release_url").toString();
                new DownloadTask(RitualFragmentActivity.this, null).execute(RitualFragmentActivity.this.s_url);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Context context = RitualFragmentActivity.this.getContext();
                RitualFragmentActivity.this.getContext().getApplicationContext();
                ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("clipboard", textView4.getText().toString()));
            }
        });
        textView5.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RitualFragmentActivity.this.UPDATE.dismiss();
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.UPDATE = alertDialogCreate;
        alertDialogCreate.show();
    }

    private class DownloadTask extends AsyncTask<String, Integer, Boolean> {
        private static final int BUFFER_SIZE = 1024;

        private DownloadTask() {
        }

        /* synthetic */ DownloadTask(RitualFragmentActivity ritualFragmentActivity, DownloadTask downloadTask) {
            this();
        }

                /* JADX WARN: Removed duplicated region for block: B:57:0x00aa  */
        /* JADX WARN: Removed duplicated region for block: B:88:? A[SYNTHETIC] */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Boolean doInBackground(String... strArr) {
            HttpURLConnection httpURLConnection;
            InputStream inputStream;
            FileOutputStream fileOutputStream = null;
            FileOutputStream fileOutputStream2 = null;
            Throwable th = null;
            try {
                httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
                try {
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() != 200) {
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return false;
                    }
                    int contentLength = httpURLConnection.getContentLength();
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        fileOutputStream = new FileOutputStream(RitualFragmentActivity.this.s_filename);
                    } catch (Exception unused) {
                    } catch (Throwable thIgnored) { }
                    try {
                        byte[] bArr = new byte[1024];
                        long j = 0;
                        while (true) {
                            int i = inputStream.read(bArr);
                            if (i == -1) {
                                try {
                                    fileOutputStream.close();
                                    if (inputStream != null) {
                try { inputStream.close(); } catch (IOException unusedX) {}
            }
                                } catch (IOException unused2) {
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                return true;
                            }
                            if (isCancelled()) {
                                try { inputStream.close(); } catch (IOException unusedY) {}
                                try {
                                    fileOutputStream.close();
                                    if (inputStream != null) {
                try { inputStream.close(); } catch (IOException unusedX) {}
            }
                                } catch (IOException unused3) {
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                return false;
                            }
                            j += (long) i;
                            if (contentLength > 0) {
                                publishProgress(Integer.valueOf((int) ((100 * j) / ((long) contentLength))));
                            }
                            fileOutputStream.write(bArr, 0, i);
                        }
                    } catch (Exception unused4) {
                        fileOutputStream2 = fileOutputStream;
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream2 = fileOutputStream;
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException unused5) {
                                if (httpURLConnection != null) {
                                    throw th;
                                }
                                httpURLConnection.disconnect();
                                throw th;
                            }
                        }
                        if (inputStream != null) {
                try { inputStream.close(); } catch (IOException unusedX) {}
            }
                        if (httpURLConnection != null) {
                        }
                    }
                } catch (Exception unused6) {
                    inputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = null;
                }
            } catch (Exception unused7) {
                httpURLConnection = null;
                inputStream = null;
            } catch (Throwable th4) {
                th = th4;
                httpURLConnection = null;
                inputStream = null;
            }
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException unused8) {
                }
            }
            if (inputStream != null) {
                try { inputStream.close(); } catch (IOException unusedX) {}
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return false;
        }

                @Override // android.os.AsyncTask
        public void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate(numArr);
            RitualFragmentActivity.this.progressBar.setProgress(numArr[0].intValue());
            RitualFragmentActivity.this.btn_download.setText(numArr[0] + "%");
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (bool.booleanValue()) {
                RitualFragmentActivity ritualFragmentActivity = RitualFragmentActivity.this;
                ritualFragmentActivity.s_command = "pm install ".concat(ritualFragmentActivity.s_filename);
                RitualFragmentActivity.this.s_commandResult = "";
                RitualFragmentActivity.this.s_exitCode = "";
                RitualFragmentActivity.this.b_command = false;
                RitualFragmentActivity.this.b_command = false;
                Shell.Result resultExec = Shell.cmd(RitualFragmentActivity.this.s_command).exec();
                List<String> out = resultExec.getOut();
                resultExec.getCode();
                RitualFragmentActivity.this.b_command = resultExec.isSuccess();
                RitualFragmentActivity.this.s_commandResult = String.join("\n", out);
                return;
            }
            RitualFragmentActivity.this.fushowToast("Download failed");
        }
    }

        public void fushowToast(String str) {
        Toast.makeText(requireContext(), str, 0).show();
    }

    public void _onChangeOsPlus(String str, CheckBox checkBox) {
        if (str.equals("down")) {
            this.n_os = Double.parseDouble(this.prefos.getString("OSPLUS", "")) - 1.0d;
            this.prefos.edit().putString("OSPLUS", String.valueOf((long) this.n_os)).commit();
        } else {
            this.n_os = Double.parseDouble(this.prefos.getString("OSPLUS", "")) + 1.0d;
            this.prefos.edit().putString("OSPLUS", String.valueOf((long) this.n_os)).commit();
        }
        ((Rv_3Adapter) this.rv_3.getAdapter()).notifyDataSetChanged();
        this.s_os_plus = this.prefos.getString("OSPLUS", "");
        if (checkBox.isChecked()) {
            this.lm_json_prop.get(0).put("RELEASE", this.s_os_plus);
            if (this.lm_json_editor.get(0).get("FINGERPRINT").toString().equals("true")) {
                this.s_universal = this.lm_json_random.get(0).get("FINGERPRINT").toString().replace("FURELEASEFU", this.s_os_plus);
                this.lm_json_prop.get(0).put("FINGERPRINT", this.s_universal);
            }
            if (this.lm_json_editor.get(0).get("DESCRIPTION").toString().equals("true")) {
                this.s_universal = this.lm_json_random.get(0).get("DESCRIPTION").toString().replace("FURELEASEFU", this.s_os_plus);
                this.lm_json_prop.get(0).put("DESCRIPTION", this.s_universal);
            }
            ((Rv_2Adapter) this.rv_2.getAdapter()).notifyDataSetChanged();
        }
    }

    public void _createJsonEditor() {
        this.lm_json_editor.clear();
        this.m_editor.clear();
        HashMap<String, Object> map = new HashMap<>();
        this.m_editor = map;
        map.put("DEVICE", "false");
        this.m_editor.put("MODEL", "false");
        this.m_editor.put("PRODUCT", "false");
        this.m_editor.put("MANUFACTURER", "false");
        this.m_editor.put("BRAND", "false");
        this.m_editor.put("BOOT", "false");
        this.m_editor.put("BUILDID", "false");
        this.m_editor.put("RELEASE", "false");
        this.m_editor.put("INCREMENTAL", "false");
        this.m_editor.put("DISPLAY", "false");
        this.m_editor.put("FINGERPRINT", "false");
        this.m_editor.put("DESCRIPTION", "false");
        this.m_editor.put("NAME", "false");
        this.m_editor.put("TIMEPICK", "false");
        this.m_editor.put("WIPEGMS", "false");
        this.m_editor.put("SSAID", "false");
        this.m_editor.put("RESET0", "false");
        this.m_editor.put("REBOOT", "false");
        this.m_editor.put("DALVIC", "false");
        this.m_editor.put("NORESTART", "false");
        this.m_editor.put("OS9", "false");
        this.m_editor.put("OS10", "false");
        this.m_editor.put("OS11", "false");
        this.m_editor.put("OS12", "false");
        this.m_editor.put("OS12L", "false");
        this.m_editor.put("OS13", "false");
        this.m_editor.put("OS14", "false");
        this.m_editor.put("OSPLUS", "false");
        this.m_editor.put("MODPESSTART", "false");
        this.m_editor.put("MODPESEND", "false");
        this.lm_json_editor.add(this.m_editor);
    }

    public void _onChipModePes(CheckBox checkBox, final String str, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ((HashMap) arrayList.get((int) d)).put(str, "true");
                } else {
                    ((HashMap) arrayList.get((int) d)).put(str, "false");
                }
            }
        });
    }

    public void _onCheckSwitchSetting() {
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(requireContext(), C0978R.anim.m3_motion_fade_enter);
        Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(requireContext(), C0978R.anim.m3_motion_fade_exit);
        if (this.pref.getString("show_fragment", "").equals("show_editor")) {
            this.switch_editor.setText("Hide Editor");
            this.switch_editor.setChecked(true);
            this.ln_bottom.startAnimation(animationLoadAnimation2);
            this.ln_bottom.setVisibility(8);
            this.ln_editor.startAnimation(animationLoadAnimation);
            this.ln_editor.setVisibility(0);
            if (this._fab.getVisibility() == 0) {
                this._fab.setVisibility(8);
            }
            _setFirstUIEditor();
            return;
        }
        this.switch_editor.setText("Show Editor");
        this.switch_editor.setChecked(false);
        this.ln_editor.startAnimation(animationLoadAnimation2);
        this.ln_editor.setVisibility(8);
        this.ln_bottom.startAnimation(animationLoadAnimation);
        this.ln_bottom.setVisibility(0);
        _onLoadBrand();
    }

    public void _setFirstUIEditor() {
        if (this.pref.getString("chip_editor_modpes_start", "").equals("true")) {
            this.mchip_modpes_start.setChecked(true);
        }
        if (this.pref.getString("chip_editor_modpes_end", "").equals("true")) {
            this.mchip_modpes_end.setChecked(true);
        }
        if (this.pref.getString("chip_editor_timepick", "").equals("true")) {
            this.m_timepick.setChecked(true);
        }
        if (this.pref.getString("chip_editor_gms", "").equals("true")) {
            this.m_gms.setChecked(true);
        }
        if (this.pref.getString("chip_editor_ssaid", "").equals("true")) {
            this.m_ssaid.setChecked(true);
        }
        if (this.pref.getString("chip_editor_nol", "").equals("true")) {
            this.m_nol.setChecked(true);
        }
        if (this.pref.getString("chip_editor_reboot", "").equals("true")) {
            this.m_reboot.setChecked(true);
        }
        if (this.pref.getString("chip_editor_dalvic", "").equals("true")) {
            this.m_dalvic.setChecked(true);
        }
        if (this.pref.getString("chip_editor_norestart", "").equals("true")) {
            this.m_norestart.setChecked(true);
        }
        MyREADEXISTPROP myREADEXISTPROP = this.myREADEXISTPROP;
        if (myREADEXISTPROP != null && myREADEXISTPROP.isRunning) {
            this.myREADEXISTPROP.cancelREADEXISTPROPTask();
        }
        MyREADEXISTPROP myREADEXISTPROP2 = new MyREADEXISTPROP();
        this.myREADEXISTPROP = myREADEXISTPROP2;
        myREADEXISTPROP2.execute(new Void[0]);
    }

    public class MyREADEXISTPROP extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyREADEXISTPROP() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            RitualFragmentActivity.this.lm_editor.clear();
            RitualFragmentActivity.this.m_systemprop.clear();
            RitualFragmentActivity ritualFragmentActivity = RitualFragmentActivity.this;
            ritualFragmentActivity.s_commandGetProp = ritualFragmentActivity.s_commandBase.concat("\ngetkatrinaprop");
            RitualFragmentActivity.this.rv_4.setVisibility(8);
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            RitualFragmentActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(RitualFragmentActivity.this.s_commandGetProp).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            RitualFragmentActivity.this.b_command = resultExec.isSuccess();
            RitualFragmentActivity.this.s_getkatrinaprop = String.join("\n", out);
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r5) {
            this.isRunning = false;
            if (RitualFragmentActivity.this.s_getkatrinaprop.equals("")) {
                return;
            }
            RitualFragmentActivity ritualFragmentActivity = RitualFragmentActivity.this;
            if (!ritualFragmentActivity.jsonIsValid(ritualFragmentActivity.s_getkatrinaprop)) {
                RitualFragmentActivity.this.rv_4.setVisibility(8);
                return;
            }
            RitualFragmentActivity.this.m_systemprop = (HashMap) new Gson().fromJson(RitualFragmentActivity.this.s_getkatrinaprop, new TypeToken<HashMap<String, Object>>() {             }.getType());
            RitualFragmentActivity.this.lm_editor.add(RitualFragmentActivity.this.m_systemprop);
            RecyclerView recyclerView = RitualFragmentActivity.this.rv_4;
            RitualFragmentActivity ritualFragmentActivity2 = RitualFragmentActivity.this;
            recyclerView.setAdapter(ritualFragmentActivity2.new Rv_4Adapter(ritualFragmentActivity2.lm_editor));
            RitualFragmentActivity.this.rv_4.setLayoutManager(new LinearLayoutManager(RitualFragmentActivity.this.getContext()));
            RitualFragmentActivity.this.rv_4.setVisibility(0);
        }

        public void cancelREADEXISTPROPTask() {
            cancel(true);
        }
    }

    public void _onCheckModule() {
        MyCEKMODULE myCEKMODULE = this.myCEKMODULE;
        if (myCEKMODULE != null && myCEKMODULE.isRunning) {
            this.myCEKMODULE.cancelCEKMODULETask();
        }
        MyCEKMODULE myCEKMODULE2 = new MyCEKMODULE();
        this.myCEKMODULE = myCEKMODULE2;
        myCEKMODULE2.execute(new Void[0]);
    }

    public class MyCEKMODULE extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyCEKMODULE() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            RitualFragmentActivity.this.s_commandModule = "if [ -d \"/data/adb/modules/xkatrina_snstv_prps\" ]; then\n    ls /data/adb/modules/xkatrina_snstv_prps\nelse\n    echo \"Direktori tidak ditemukan\"\nfi";
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            RitualFragmentActivity.this.b_commandModule = false;
            Shell.Result resultExec = Shell.cmd(RitualFragmentActivity.this.s_commandModule).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            RitualFragmentActivity.this.b_commandModule = resultExec.isSuccess();
            RitualFragmentActivity.this.s_resultModule = String.join("\n", out);
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            RitualFragmentActivity.this._onModuleInstalled();
        }

        public void cancelCEKMODULETask() {
            cancel(true);
        }
    }

    public void _onModuleInstalled() {
        if (getActivity() == null || !this.b_commandModule) {
            return;
        }
        if (this.s_resultModule.contains("resetprop")) {
            this.lottie1.setVisibility(8);
            this.ln_setting_editor.setVisibility(0);
            _onCheckSwitchSetting();
            return;
        }
        this.ln_not_active.setVisibility(0);
        this.ln_setting_editor.setVisibility(8);
        this.ln_editor.setVisibility(8);
        this.ln_bottom.setVisibility(8);
        this._fab.setVisibility(8);
        if (this.s_resultModule.contains("disable")) {
            this.tv_not_active.setText(getString(C0978R.string.module_disabled_title));
            this.tv_note.setText(getString(C0978R.string.module_disabled_msg));
            return;
        }
        this.s_remove_old = "rm -rf /data/adb/modules/XKatrina";
        this.b_command = false;
        Shell.Result resultExec = Shell.cmd("rm -rf /data/adb/modules/XKatrina").exec();
        List<String> out = resultExec.getOut();
        resultExec.getCode();
        this.b_command = resultExec.isSuccess();
        this.s_remove_old = String.join("\n", out);
        this.tv_not_active.setText(getString(C0978R.string.module_inactive_title));
        this.tv_note.setText(getString(C0978R.string.module_install_prompt));
        try {
            InputStream inputStreamOpen = getContext().getAssets().open("main.dex");
            FileOutputStream fileOutputStream = new FileOutputStream("/storage/emulated/0/xkatrina.zip");
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStreamOpen.read(bArr);
                if (i <= 0) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    inputStreamOpen.close();
                    break;
                }
                fileOutputStream.write(bArr, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.btn_install_module.setVisibility(0);
        this.btn_install_module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _autoInstallModule();
            }
        });
    }

    public void _autoInstallModule() {
        final String zipPath = "/storage/emulated/0/xkatrina.zip";
        this.btn_install_module.setEnabled(false);
        this.btn_install_module.setText(getString(C0978R.string.module_installing));
        Shell.cmd(
            "if [ -d /data/adb/ksu ] || command -v ksud >/dev/null 2>&1; then " +
            "  ksud module install '" + zipPath + "'; " +
            "elif command -v magisk >/dev/null 2>&1; then " +
            "  magisk --install-module '" + zipPath + "'; " +
            "elif [ -d /data/adb/ap ] || command -v apd >/dev/null 2>&1; then " +
            "  apd module install '" + zipPath + "'; " +
            "else " +
            "  echo 'NO_ROOT_MANAGER'; exit 1; " +
            "fi"
        ).submit(new Shell.ResultCallback() {
            @Override
            public void onResult(Shell.Result result) {
                btn_install_module.setEnabled(true);
                String out = String.join("\n", result.getOut()) + "\n" + String.join("\n", result.getErr());
                if (result.isSuccess()) {
                    btn_install_module.setText(getString(C0978R.string.module_install_done));
                    SketchwareUtil.showMessage(getContext().getApplicationContext(), getString(C0978R.string.module_install_success));
                    new MaterialAlertDialogBuilder(requireContext())
                        .setTitle(getString(C0978R.string.module_reboot_title))
                        .setMessage(getString(C0978R.string.module_reboot_msg))
                        .setPositiveButton(getString(C0978R.string.module_reboot_yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface d, int w) {
                                Shell.cmd("svc power reboot || reboot").submit();
                            }
                        })
                        .setNegativeButton(getString(C0978R.string.module_reboot_later), null)
                        .show();
                } else {
                    btn_install_module.setText(getString(C0978R.string.module_install_button));
                    if (out.contains("NO_ROOT_MANAGER")) {
                        SketchwareUtil.showMessage(getContext().getApplicationContext(), getString(C0978R.string.module_install_no_manager));
                    } else {
                        SketchwareUtil.showMessage(getContext().getApplicationContext(), getString(C0978R.string.module_install_failed, out));
                    }
                }
            }
        });
    }

    public void _onCardFavButton(String str) {
        if (this.pref.getString(str, "").equals("true")) {
            this.pref.edit().putString(str, "false").commit();
        } else {
            this.pref.edit().putString(str, "true").commit();
        }
        ((Rv_4Adapter) this.rv_4.getAdapter()).notifyDataSetChanged();
    }

    public void _onRandomAllEditor() {
        if (this.lm_editor.size() == 0) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Tidak ada data untuk di acak");
            return;
        }
        this.s_random_desc = "fufufuwww-user fufufuxxx fufufuyyy.fufufuzzz release-keys";
        this.s_random_fing = "fufufu1/fufufu2/fufufu3:fufufu4/fufufu5/fufufu6:user/release-keys";
        ArrayList arrayList = new ArrayList();
        arrayList.add("8");
        arrayList.add("9");
        arrayList.add("10");
        arrayList.add("11");
        arrayList.add("12");
        arrayList.add("12.1");
        arrayList.add("13");
        arrayList.add("14");
        Collections.shuffle(arrayList);
        this.s_random_release = (String) arrayList.get(new Random().nextInt(arrayList.size()));
        if (this.pref.getString("release", "").equals("true")) {
            this.lm_editor.get(0).put("RELEASE", this.s_random_release);
        }
        if (this.pref.getString("fingerprint", "").equals("true")) {
            String strReplace = this.s_random_fing.replace("fufufu1", generateRandomString());
            this.s_random_fing = strReplace;
            String strReplace2 = strReplace.replace("fufufu2", generateRandomString());
            this.s_random_fing = strReplace2;
            String strReplace3 = strReplace2.replace("fufufu3", generateRandomString());
            this.s_random_fing = strReplace3;
            String strReplace4 = strReplace3.replace("fufufu4", this.s_random_release);
            this.s_random_fing = strReplace4;
            String strReplace5 = strReplace4.replace("fufufu5", generateRandomString());
            this.s_random_fing = strReplace5;
            this.s_random_fing = strReplace5.replace("fufufu6", generateRandomString());
            this.lm_editor.get(0).put("FINGERPRINT", this.s_random_fing);
        }
        if (this.pref.getString("description", "").equals("true")) {
            String strReplace6 = this.s_random_desc.replace("fufufuwww", generateRandomString());
            this.s_random_desc = strReplace6;
            String strReplace7 = strReplace6.replace("fufufuxxx", this.s_random_release);
            this.s_random_desc = strReplace7;
            String strReplace8 = strReplace7.replace("fufufuyyy", generateRandomString());
            this.s_random_desc = strReplace8;
            this.s_random_desc = strReplace8.replace("fufufuzzz", generateRandomString());
            this.lm_editor.get(0).put("DESCRIPTION", this.s_random_desc);
        }
        if (this.pref.getString("displayid", "").equals("true")) {
            this.lm_editor.get(0).put("DISPLAYID", generateRandomString().concat(".".concat(generateRandomString())));
        }
        if (this.pref.getString("date", "").equals("true")) {
            this.lm_editor.get(0).put("DATE", generateRandomDate());
        }
        if (this.pref.getString("utc", "").equals("true")) {
            this.lm_editor.get(0).put("UTC", String.valueOf(SketchwareUtil.getRandom(1558918000, 1658918000)));
        }
        if (this.pref.getString("board", "").equals("true")) {
            this.lm_editor.get(0).put("BOARD", generateRandomString());
        }
        if (this.pref.getString("build.id", "").equals("true")) {
            this.lm_editor.get(0).put("BUILDID", generateRandomString());
        }
        if (this.pref.getString("device", "").equals("true")) {
            this.lm_editor.get(0).put("DEVICE", generateRandomString());
        }
        if (this.pref.getString("host", "").equals("true")) {
            this.lm_editor.get(0).put("HOST", generateRandomString());
        }
        if (this.pref.getString("model", "").equals("true")) {
            this.lm_editor.get(0).put("MODEL", generateRandomString());
        }
        if (this.pref.getString("manufacturer", "").equals("true")) {
            this.lm_editor.get(0).put("MANUFACTURER", generateRandomString());
        }
        if (this.pref.getString("user", "").equals("true")) {
            this.lm_editor.get(0).put("USER", generateRandomString());
        }
        if (this.pref.getString("incremental", "").equals("true")) {
            this.lm_editor.get(0).put("INCREMENTAL", generateRandomString());
        }
        if (this.pref.getString("brand", "").equals("true")) {
            this.lm_editor.get(0).put("BRAND", generateRandomString());
        }
        if (this.pref.getString("name", "").equals("true")) {
            this.lm_editor.get(0).put("NAME", generateRandomString());
        }
        if (this.pref.getString("hardware", "").equals("true")) {
            this.lm_editor.get(0).put("HARDWARE", generateRandomString());
        }
        if (this.pref.getString("product", "").equals("true")) {
            this.lm_editor.get(0).put("PRODUCT", generateRandomString());
        }
        if (this.pref.getString("bootloader", "").equals("true")) {
            this.lm_editor.get(0).put("BOOTLOADER", generateRandomString());
        }
        ((Rv_4Adapter) this.rv_4.getAdapter()).notifyDataSetChanged();
    }

    public void _onStartRitualEditor() {
        _showProgressDialog();
        this.lm_json_editor.clear();
        this.m_editor = new HashMap<>();
        if (this.pref.getString("chip_editor_modpes_end", "").equals("true")) {
            this.m_editor.put("MODPESEND", "true");
        } else {
            this.m_editor.put("MODPESEND", "false");
        }
        if (this.pref.getString("chip_editor_timepick", "").equals("true")) {
            this.m_editor.put("TIMEPICK", "true");
        } else {
            this.m_editor.put("TIMEPICK", "false");
        }
        if (this.pref.getString("chip_editor_gms", "").equals("true")) {
            this.m_editor.put("WIPEGMS", "true");
        } else {
            this.m_editor.put("WIPEGMS", "false");
        }
        if (this.pref.getString("chip_editor_ssaid", "").equals("true")) {
            this.m_editor.put("SSAID", "true");
        } else {
            this.m_editor.put("SSAID", "false");
        }
        if (this.pref.getString("chip_editor_nol", "").equals("true")) {
            this.m_editor.put("RESET0", "true");
        } else {
            this.m_editor.put("RESET0", "false");
        }
        if (this.pref.getString("chip_editor_reboot", "").equals("true")) {
            this.m_editor.put("REBOOT", "true");
        } else {
            this.m_editor.put("REBOOT", "false");
        }
        if (this.pref.getString("chip_editor_dalvic", "").equals("true")) {
            this.m_editor.put("DALVIC", "true");
        } else {
            this.m_editor.put("DALVIC", "false");
        }
        if (this.pref.getString("chip_editor_norestart", "").equals("true")) {
            this.m_editor.put("NORESTART", "true");
        } else {
            this.m_editor.put("NORESTART", "false");
        }
        this.lm_json_editor.add(this.m_editor);
        if (this.pref.getString("chip_editor_modpes_start", "").equals("true")) {
            this.s_commandModpes = "settings put global airplane_mode_on 1\nam broadcast -a android.intent.action.AIRPLANE_MODE";
            this.b_command = false;
            Shell.Result resultExec = Shell.cmd("settings put global airplane_mode_on 1\nam broadcast -a android.intent.action.AIRPLANE_MODE").exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            this.b_command = resultExec.isSuccess();
            this.s_commandResult = String.join("\n", out);
        }
        if (this.lm_editor.size() == 0) {
            _onCleanerWipe();
        } else {
            _onEditExistProp();
        }
    }

    public void _onEditExistProp() {
        this.s_prop_device = this.lm_editor.get(0).get("DEVICE").toString();
        this.s_prop_model = this.lm_editor.get(0).get("MODEL").toString();
        this.s_prop_product = this.lm_editor.get(0).get("PRODUCT").toString();
        this.s_prop_name = this.lm_editor.get(0).get("NAME").toString();
        this.s_prop_manufacturer = this.lm_editor.get(0).get("MANUFACTURER").toString();
        this.s_prop_brand = this.lm_editor.get(0).get("BRAND").toString();
        this.s_prop_boot = this.lm_editor.get(0).get("BOOTLOADER").toString();
        this.s_prop_buildid = this.lm_editor.get(0).get("BUILDID").toString();
        this.s_prop_fingerprint = this.lm_editor.get(0).get("FINGERPRINT").toString();
        this.s_prop_description = this.lm_editor.get(0).get("DESCRIPTION").toString();
        this.s_prop_incremental = this.lm_editor.get(0).get("INCREMENTAL").toString();
        this.s_prop_display = this.lm_editor.get(0).get("DISPLAYID").toString();
        this.s_prop_board = this.lm_editor.get(0).get("BOARD").toString();
        this.s_prop_hardware = this.lm_editor.get(0).get("HARDWARE").toString();
        this.s_prop_host = this.lm_editor.get(0).get("HOST").toString();
        this.s_prop_user = this.lm_editor.get(0).get("USER").toString();
        this.s_prop_date = this.lm_editor.get(0).get("DATE").toString();
        this.s_prop_dateutc = this.lm_editor.get(0).get("UTC").toString();
        this.s_prop_release = this.lm_editor.get(0).get("RELEASE").toString();
        _onReplaceProp();
    }

    public void _onCardEditButton(double d, String str, ArrayList<HashMap<String, Object>> arrayList) {
        this.editor_pos = d;
        this.s_editor = str;
        this.s_title_editor = str;
        this.s_dialog_editor = arrayList.get((int) d).get(str).toString();
        showEDITOR();
    }

    private void showEDITOR() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_dialog_editnote, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(true);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_01);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_cancel);
        Button button2 = (Button) viewInflate.findViewById(C0978R.id.btn_oke);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) viewInflate.findViewById(C0978R.id.auto_1);
        autoCompleteTextView.setFocusable(true);
        autoCompleteTextView.setFocusableInTouchMode(true);
        textView.setText(this.s_title_editor);
        autoCompleteTextView.setHint(this.s_dialog_editor);
        autoCompleteTextView.setText(this.s_dialog_editor);
        autoCompleteTextView.setSingleLine(true);
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RitualFragmentActivity.this.EDITOR.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RitualFragmentActivity.this.s_result_editor = autoCompleteTextView.getText().toString();
                ((HashMap) RitualFragmentActivity.this.lm_editor.get((int) RitualFragmentActivity.this.editor_pos)).put(RitualFragmentActivity.this.s_editor, RitualFragmentActivity.this.s_result_editor);
                ((Rv_4Adapter) RitualFragmentActivity.this.rv_4.getAdapter()).notifyDataSetChanged();
                RitualFragmentActivity.this.EDITOR.dismiss();
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.EDITOR = alertDialogCreate;
        alertDialogCreate.show();
    }

    public void _onCardRandomButton(double d, String str, ArrayList<HashMap<String, Object>> arrayList) {
        this.s_random_desc = "fufufuwww-user fufufuxxx fufufuyyy.fufufuzzz release-keys";
        this.s_random_fing = "fufufu1/fufufu2/fufufu3:fufufu4/fufufu5/fufufu6:user/release-keys";
        this.s_card_random = str;
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("8");
        arrayList2.add("9");
        arrayList2.add("10");
        arrayList2.add("11");
        arrayList2.add("12");
        arrayList2.add("12.1");
        arrayList2.add("13");
        arrayList2.add("14");
        Collections.shuffle(arrayList2);
        this.s_random_release = (String) arrayList2.get(new Random().nextInt(arrayList2.size()));
        if (str.equals("RELEASE")) {
            arrayList.get((int) d).put(str, this.s_random_release);
        } else if (str.equals("FINGERPRINT")) {
            String strReplace = this.s_random_fing.replace("fufufu1", generateRandomString());
            this.s_random_fing = strReplace;
            String strReplace2 = strReplace.replace("fufufu2", generateRandomString());
            this.s_random_fing = strReplace2;
            String strReplace3 = strReplace2.replace("fufufu3", generateRandomString());
            this.s_random_fing = strReplace3;
            String strReplace4 = strReplace3.replace("fufufu4", this.s_random_release);
            this.s_random_fing = strReplace4;
            String strReplace5 = strReplace4.replace("fufufu5", generateRandomString());
            this.s_random_fing = strReplace5;
            this.s_random_fing = strReplace5.replace("fufufu6", generateRandomString());
            arrayList.get((int) d).put(str, this.s_random_fing);
        } else if (str.equals("DESCRIPTION")) {
            String strReplace6 = this.s_random_desc.replace("fufufuwww", generateRandomString());
            this.s_random_desc = strReplace6;
            String strReplace7 = strReplace6.replace("fufufuxxx", this.s_random_release);
            this.s_random_desc = strReplace7;
            String strReplace8 = strReplace7.replace("fufufuyyy", generateRandomString());
            this.s_random_desc = strReplace8;
            this.s_random_desc = strReplace8.replace("fufufuzzz", generateRandomString());
            arrayList.get((int) d).put(str, this.s_random_desc);
        } else if (str.equals("DISPLAYID")) {
            arrayList.get((int) d).put(str, generateRandomString().concat(".".concat(generateRandomString())));
        } else if (str.equals("DATE")) {
            arrayList.get((int) d).put(str, generateRandomDate().replace("WIB", "UTC"));
        } else if (str.equals("UTC")) {
            arrayList.get((int) d).put(str, String.valueOf(SketchwareUtil.getRandom(1558918000, 1658918000)));
        } else {
            arrayList.get((int) d).put(str, generateRandomString());
        }
        ((Rv_4Adapter) this.rv_4.getAdapter()).notifyDataSetChanged();
    }

    public class Rv_4Adapter extends RecyclerView.Adapter<Rv_4Adapter.ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;

        public Rv_4Adapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View viewInflate = RitualFragmentActivity.this.getActivity().getLayoutInflater().inflate(C0978R.layout.new_ritual_editor_view, (ViewGroup) null);
            viewInflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(viewInflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            MaterialButton materialButton;
            MaterialButton materialButton2;
            MaterialButton materialButton3;
            MaterialButton materialButton4;
            MaterialButton materialButton5;
            MaterialButton materialButton6;
            MaterialButton materialButton7;
            MaterialButton materialButton8;
            View view = viewHolder.itemView;
            TextView textView = (TextView) view.findViewById(C0978R.id.tv_title_00);
            TextView textView2 = (TextView) view.findViewById(C0978R.id.tv_subtitle_00);
            MaterialButton materialButton9 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_00);
            MaterialButton materialButton10 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_00);
            MaterialButton materialButton11 = (MaterialButton) view.findViewById(C0978R.id.btn_random_00);
            TextView textView3 = (TextView) view.findViewById(C0978R.id.tv_title_01);
            TextView textView4 = (TextView) view.findViewById(C0978R.id.tv_subtitle_01);
            MaterialButton materialButton12 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_01);
            MaterialButton materialButton13 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_01);
            MaterialButton materialButton14 = (MaterialButton) view.findViewById(C0978R.id.btn_random_01);
            TextView textView5 = (TextView) view.findViewById(C0978R.id.tv_title_02);
            TextView textView6 = (TextView) view.findViewById(C0978R.id.tv_subtitle_02);
            MaterialButton materialButton15 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_02);
            MaterialButton materialButton16 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_02);
            MaterialButton materialButton17 = (MaterialButton) view.findViewById(C0978R.id.btn_random_02);
            TextView textView7 = (TextView) view.findViewById(C0978R.id.tv_title_03);
            TextView textView8 = (TextView) view.findViewById(C0978R.id.tv_subtitle_03);
            MaterialButton materialButton18 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_03);
            MaterialButton materialButton19 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_03);
            MaterialButton materialButton20 = (MaterialButton) view.findViewById(C0978R.id.btn_random_03);
            TextView textView9 = (TextView) view.findViewById(C0978R.id.tv_title_04);
            TextView textView10 = (TextView) view.findViewById(C0978R.id.tv_subtitle_04);
            MaterialButton materialButton21 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_04);
            MaterialButton materialButton22 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_04);
            MaterialButton materialButton23 = (MaterialButton) view.findViewById(C0978R.id.btn_random_04);
            TextView textView11 = (TextView) view.findViewById(C0978R.id.tv_title_05);
            TextView textView12 = (TextView) view.findViewById(C0978R.id.tv_subtitle_05);
            MaterialButton materialButton24 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_05);
            MaterialButton materialButton25 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_05);
            MaterialButton materialButton26 = (MaterialButton) view.findViewById(C0978R.id.btn_random_05);
            TextView textView13 = (TextView) view.findViewById(C0978R.id.tv_title_06);
            TextView textView14 = (TextView) view.findViewById(C0978R.id.tv_subtitle_06);
            MaterialButton materialButton27 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_06);
            MaterialButton materialButton28 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_06);
            MaterialButton materialButton29 = (MaterialButton) view.findViewById(C0978R.id.btn_random_06);
            TextView textView15 = (TextView) view.findViewById(C0978R.id.tv_title_07);
            TextView textView16 = (TextView) view.findViewById(C0978R.id.tv_subtitle_07);
            MaterialButton materialButton30 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_07);
            MaterialButton materialButton31 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_07);
            MaterialButton materialButton32 = (MaterialButton) view.findViewById(C0978R.id.btn_random_07);
            TextView textView17 = (TextView) view.findViewById(C0978R.id.tv_title_08);
            TextView textView18 = (TextView) view.findViewById(C0978R.id.tv_subtitle_08);
            MaterialButton materialButton33 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_08);
            MaterialButton materialButton34 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_08);
            MaterialButton materialButton35 = (MaterialButton) view.findViewById(C0978R.id.btn_random_08);
            TextView textView19 = (TextView) view.findViewById(C0978R.id.tv_title_09);
            TextView textView20 = (TextView) view.findViewById(C0978R.id.tv_subtitle_09);
            MaterialButton materialButton36 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_09);
            MaterialButton materialButton37 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_09);
            MaterialButton materialButton38 = (MaterialButton) view.findViewById(C0978R.id.btn_random_09);
            TextView textView21 = (TextView) view.findViewById(C0978R.id.tv_title_10);
            TextView textView22 = (TextView) view.findViewById(C0978R.id.tv_subtitle_10);
            MaterialButton materialButton39 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_10);
            MaterialButton materialButton40 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_10);
            MaterialButton materialButton41 = (MaterialButton) view.findViewById(C0978R.id.btn_random_10);
            TextView textView23 = (TextView) view.findViewById(C0978R.id.tv_title_11);
            TextView textView24 = (TextView) view.findViewById(C0978R.id.tv_subtitle_11);
            MaterialButton materialButton42 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_11);
            MaterialButton materialButton43 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_11);
            MaterialButton materialButton44 = (MaterialButton) view.findViewById(C0978R.id.btn_random_11);
            TextView textView25 = (TextView) view.findViewById(C0978R.id.tv_title_12);
            TextView textView26 = (TextView) view.findViewById(C0978R.id.tv_subtitle_12);
            MaterialButton materialButton45 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_12);
            MaterialButton materialButton46 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_12);
            MaterialButton materialButton47 = (MaterialButton) view.findViewById(C0978R.id.btn_random_12);
            TextView textView27 = (TextView) view.findViewById(C0978R.id.tv_title_13);
            TextView textView28 = (TextView) view.findViewById(C0978R.id.tv_subtitle_13);
            MaterialButton materialButton48 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_13);
            MaterialButton materialButton49 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_13);
            MaterialButton materialButton50 = (MaterialButton) view.findViewById(C0978R.id.btn_random_13);
            TextView textView29 = (TextView) view.findViewById(C0978R.id.tv_title_14);
            TextView textView30 = (TextView) view.findViewById(C0978R.id.tv_subtitle_14);
            MaterialButton materialButton51 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_14);
            MaterialButton materialButton52 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_14);
            MaterialButton materialButton53 = (MaterialButton) view.findViewById(C0978R.id.btn_random_14);
            TextView textView31 = (TextView) view.findViewById(C0978R.id.tv_title_15);
            TextView textView32 = (TextView) view.findViewById(C0978R.id.tv_subtitle_15);
            MaterialButton materialButton54 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_15);
            MaterialButton materialButton55 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_15);
            MaterialButton materialButton56 = (MaterialButton) view.findViewById(C0978R.id.btn_random_15);
            TextView textView33 = (TextView) view.findViewById(C0978R.id.tv_title_16);
            TextView textView34 = (TextView) view.findViewById(C0978R.id.tv_subtitle_16);
            MaterialButton materialButton57 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_16);
            MaterialButton materialButton58 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_16);
            MaterialButton materialButton59 = (MaterialButton) view.findViewById(C0978R.id.btn_random_16);
            TextView textView35 = (TextView) view.findViewById(C0978R.id.tv_title_17);
            TextView textView36 = (TextView) view.findViewById(C0978R.id.tv_subtitle_17);
            MaterialButton materialButton60 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_17);
            MaterialButton materialButton61 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_17);
            MaterialButton materialButton62 = (MaterialButton) view.findViewById(C0978R.id.btn_random_17);
            TextView textView37 = (TextView) view.findViewById(C0978R.id.tv_title_18);
            TextView textView38 = (TextView) view.findViewById(C0978R.id.tv_subtitle_18);
            MaterialButton materialButton63 = (MaterialButton) view.findViewById(C0978R.id.btn_fav_18);
            MaterialButton materialButton64 = (MaterialButton) view.findViewById(C0978R.id.btn_edit_18);
            MaterialButton materialButton65 = (MaterialButton) view.findViewById(C0978R.id.btn_random_18);
            if (RitualFragmentActivity.this.pref.getString("board", "").equals("true")) {
                materialButton9.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton9.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton10.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton11.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("build.id", "").equals("true")) {
                materialButton12.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton12.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton13.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton14.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("displayid", "").equals("true")) {
                materialButton15.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton15.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton16.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton17.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("device", "").equals("true")) {
                materialButton18.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton18.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton19.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton20.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("host", "").equals("true")) {
                materialButton = materialButton21;
                materialButton.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton = materialButton21;
                materialButton.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton22.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton23.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("model", "").equals("true")) {
                materialButton2 = materialButton24;
                materialButton2.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton2 = materialButton24;
                materialButton2.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton25.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton26.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("release", "").equals("true")) {
                materialButton3 = materialButton27;
                materialButton3.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton3 = materialButton27;
                materialButton3.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton28.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton29.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("manufacturer", "").equals("true")) {
                materialButton4 = materialButton30;
                materialButton4.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton4 = materialButton30;
                materialButton4.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton31.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton32.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("user", "").equals("true")) {
                materialButton5 = materialButton33;
                materialButton5.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton5 = materialButton33;
                materialButton5.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton34.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton35.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("brand", "").equals("true")) {
                materialButton6 = materialButton36;
                materialButton6.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton6 = materialButton36;
                materialButton6.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton37.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton38.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("name", "").equals("true")) {
                materialButton7 = materialButton39;
                materialButton7.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton7 = materialButton39;
                materialButton7.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton40.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton41.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("fingerprint", "").equals("true")) {
                materialButton42.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton42.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton43.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton44.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("description", "").equals("true")) {
                materialButton45.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton45.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton46.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton47.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("hardware", "").equals("true")) {
                materialButton48.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton48.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton49.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton50.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("product", "").equals("true")) {
                materialButton51.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton51.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton52.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton53.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("bootloader", "").equals("true")) {
                materialButton54.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton54.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton55.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton56.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("incremental", "").equals("true")) {
                materialButton57.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton57.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton58.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton59.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("date", "").equals("true")) {
                materialButton60.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton60.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton61.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton62.setIconResource(C0978R.drawable.ic_card_random);
            if (RitualFragmentActivity.this.pref.getString("utc", "").equals("true")) {
                materialButton8 = materialButton63;
                materialButton8.setIconResource(C0978R.drawable.ic_card_fav_on);
            } else {
                materialButton8 = materialButton63;
                materialButton8.setIconResource(C0978R.drawable.ic_card_fav_off);
            }
            materialButton64.setIconResource(C0978R.drawable.ic_card_edit);
            materialButton65.setIconResource(C0978R.drawable.ic_card_random);
            textView.setText("BOARD");
            textView2.setText(this._data.get(i).get("BOARD").toString());
            textView3.setText("BUILDID");
            textView4.setText(this._data.get(i).get("BUILDID").toString());
            textView5.setText("DISPLAYID");
            textView6.setText(this._data.get(i).get("DISPLAYID").toString());
            textView7.setText("DEVICE");
            textView8.setText(this._data.get(i).get("DEVICE").toString());
            textView9.setText("HOST");
            textView10.setText(this._data.get(i).get("HOST").toString());
            textView11.setText("MODEL");
            textView12.setText(this._data.get(i).get("MODEL").toString());
            textView13.setText("RELEASE");
            textView14.setText(this._data.get(i).get("RELEASE").toString());
            textView15.setText("MANUFACTURER");
            textView16.setText(this._data.get(i).get("MANUFACTURER").toString());
            textView17.setText("USER");
            textView18.setText(this._data.get(i).get("USER").toString());
            textView19.setText("BRAND");
            textView20.setText(this._data.get(i).get("BRAND").toString());
            textView21.setText("NAME");
            textView22.setText(this._data.get(i).get("NAME").toString());
            textView23.setText("FINGERPRINT");
            textView24.setText(this._data.get(i).get("FINGERPRINT").toString());
            textView25.setText("DESCRIPTION");
            textView26.setText(this._data.get(i).get("DESCRIPTION").toString());
            textView27.setText("HARDWARE");
            textView28.setText(this._data.get(i).get("HARDWARE").toString());
            textView29.setText("PRODUCT");
            textView30.setText(this._data.get(i).get("PRODUCT").toString());
            textView31.setText("BOOTLOADER");
            textView32.setText(this._data.get(i).get("BOOTLOADER").toString());
            textView33.setText("INCREMENTAL");
            textView34.setText(this._data.get(i).get("INCREMENTAL").toString());
            textView35.setText("DATE");
            textView36.setText(this._data.get(i).get("DATE").toString());
            textView37.setText("UTC");
            textView38.setText(this._data.get(i).get("UTC").toString());
            materialButton9.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("board");
                }
            });
            materialButton12.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("build.id");
                }
            });
            materialButton15.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("displayid");
                }
            });
            materialButton18.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("device");
                }
            });
            materialButton.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("host");
                }
            });
            materialButton2.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("model");
                }
            });
            materialButton3.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("release");
                }
            });
            materialButton4.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("manufacturer");
                }
            });
            materialButton5.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("user");
                }
            });
            materialButton6.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("brand");
                }
            });
            materialButton7.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("name");
                }
            });
            materialButton42.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("fingerprint");
                }
            });
            materialButton45.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("description");
                }
            });
            materialButton48.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("hardware");
                }
            });
            materialButton51.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("product");
                }
            });
            materialButton54.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("bootloader");
                }
            });
            materialButton57.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("incremental");
                }
            });
            materialButton60.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("date");
                }
            });
            materialButton8.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardFavButton("utc");
                }
            });
            materialButton10.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "BOARD", Rv_4Adapter.this._data);
                }
            });
            materialButton13.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "BUILDID", Rv_4Adapter.this._data);
                }
            });
            materialButton16.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "DISPLAYID", Rv_4Adapter.this._data);
                }
            });
            materialButton19.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "DEVICE", Rv_4Adapter.this._data);
                }
            });
            materialButton22.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "HOST", Rv_4Adapter.this._data);
                }
            });
            materialButton25.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "MODEL", Rv_4Adapter.this._data);
                }
            });
            materialButton28.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "RELEASE", Rv_4Adapter.this._data);
                }
            });
            materialButton31.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "MANUFACTURER", Rv_4Adapter.this._data);
                }
            });
            materialButton34.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "USER", Rv_4Adapter.this._data);
                }
            });
            materialButton37.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "BRAND", Rv_4Adapter.this._data);
                }
            });
            materialButton40.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "NAME", Rv_4Adapter.this._data);
                }
            });
            materialButton43.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "FINGERPRINT", Rv_4Adapter.this._data);
                }
            });
            materialButton46.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "DESCRIPTION", Rv_4Adapter.this._data);
                }
            });
            materialButton49.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "HARDWARE", Rv_4Adapter.this._data);
                }
            });
            materialButton52.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "PRODUCT", Rv_4Adapter.this._data);
                }
            });
            materialButton55.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "BOOTLOADER", Rv_4Adapter.this._data);
                }
            });
            materialButton58.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "INCREMENTAL", Rv_4Adapter.this._data);
                }
            });
            materialButton61.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "DATE", Rv_4Adapter.this._data);
                }
            });
            materialButton64.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardEditButton(i, "UTC", Rv_4Adapter.this._data);
                }
            });
            materialButton11.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "BOARD", Rv_4Adapter.this._data);
                }
            });
            materialButton14.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "BUILDID", Rv_4Adapter.this._data);
                }
            });
            materialButton17.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "DISPLAYID", Rv_4Adapter.this._data);
                }
            });
            materialButton20.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "DEVICE", Rv_4Adapter.this._data);
                }
            });
            materialButton23.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "HOST", Rv_4Adapter.this._data);
                }
            });
            materialButton26.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "MODEL", Rv_4Adapter.this._data);
                }
            });
            materialButton29.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "RELEASE", Rv_4Adapter.this._data);
                }
            });
            materialButton32.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "MANUFACTURER", Rv_4Adapter.this._data);
                }
            });
            materialButton35.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "USER", Rv_4Adapter.this._data);
                }
            });
            materialButton38.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "BRAND", Rv_4Adapter.this._data);
                }
            });
            materialButton41.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "NAME", Rv_4Adapter.this._data);
                }
            });
            materialButton44.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "FINGERPRINT", Rv_4Adapter.this._data);
                }
            });
            materialButton47.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "DESCRIPTION", Rv_4Adapter.this._data);
                }
            });
            materialButton50.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "HARDWARE", Rv_4Adapter.this._data);
                }
            });
            materialButton53.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "PRODUCT", Rv_4Adapter.this._data);
                }
            });
            materialButton56.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "BOOTLOADER", Rv_4Adapter.this._data);
                }
            });
            materialButton59.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "INCREMENTAL", Rv_4Adapter.this._data);
                }
            });
            materialButton62.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "DATE", Rv_4Adapter.this._data);
                }
            });
            materialButton65.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onCardRandomButton(i, "UTC", Rv_4Adapter.this._data);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this._data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View view) {
                super(view);
            }
        }
    }

    public class Lv_branch_allAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_branch_allAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = RitualFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(C0978R.layout.listview_branch_all, (ViewGroup) null);
            }
            MaterialCardView materialCardView = (MaterialCardView) view.findViewById(C0978R.id.cv_base);
            TextView textView = (TextView) view.findViewById(C0978R.id.tv_branch_device);
            TextView textView2 = (TextView) view.findViewById(C0978R.id.tv_branch_release);
            TextView textView3 = (TextView) view.findViewById(C0978R.id.tv_branch_buildid);
            TextView textView4 = (TextView) view.findViewById(C0978R.id.tv_branch_incremental);
            textView.setText(this._data.get(i).get("device").toString());
            textView2.setText("OS    : ".concat(this._data.get(i).get("release").toString()));
            textView3.setText("Build : ".concat(this._data.get(i).get("buildid").toString()));
            textView4.setText("Incre : ".concat(this._data.get(i).get("incremental").toString()));
            materialCardView.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onLoadingOnline("clickchild");
                    RitualFragmentActivity.this.s_url_prop1 = Lv_branch_allAdapter.this._data.get(i).get("device_url1").toString();
                    RitualFragmentActivity.this.s_url_prop2 = Lv_branch_allAdapter.this._data.get(i).get("device_url2").toString();
                    RitualFragmentActivity.this.get_branch_child1.startRequestNetwork("GET", RitualFragmentActivity.this.s_url_prop1, "a", RitualFragmentActivity.this._get_branch_child1_request_listener);
                }
            });
            return view;
        }
    }

    public class Rv_1Adapter extends RecyclerView.Adapter<Rv_1Adapter.ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;

        public Rv_1Adapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View viewInflate = RitualFragmentActivity.this.getActivity().getLayoutInflater().inflate(C0978R.layout.ritual_listview_brand_prop, (ViewGroup) null);
            viewInflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(viewInflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            View view = viewHolder.itemView;
            MaterialCardView materialCardView = (MaterialCardView) view.findViewById(C0978R.id.mcv_base);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(C0978R.id.ln_01);
            TextView textView = (TextView) view.findViewById(C0978R.id.tv_01);
            TextView textView2 = (TextView) view.findViewById(C0978R.id.tv_02);
            materialCardView.setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(RitualFragmentActivity.this.requireContext()));
            if (this._data.get(i).containsKey("MEREK")) {
                RitualFragmentActivity.this._onAdvanceBindBrand(linearLayout, textView, textView2, i, this._data);
            } else {
                RitualFragmentActivity.this._onAdvanceBindModel(linearLayout, textView, textView2, i, this._data);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this._data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View view) {
                super(view);
            }
        }
    }

    public class Rv_2Adapter extends RecyclerView.Adapter<Rv_2Adapter.ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;

        public Rv_2Adapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View viewInflate = RitualFragmentActivity.this.getActivity().getLayoutInflater().inflate(C0978R.layout.ritual_listview_detail_prop, (ViewGroup) null);
            viewInflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(viewInflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            View view = viewHolder.itemView;
            MaterialCardView materialCardView = (MaterialCardView) view.findViewById(C0978R.id.mcv_base);
            TextView textView = (TextView) view.findViewById(C0978R.id.tv_auto_random);
            TextView textView2 = (TextView) view.findViewById(C0978R.id.tv_device);
            TextView textView3 = (TextView) view.findViewById(C0978R.id.tv_model);
            TextView textView4 = (TextView) view.findViewById(C0978R.id.tv_product);
            TextView textView5 = (TextView) view.findViewById(C0978R.id.tv_name);
            TextView textView6 = (TextView) view.findViewById(C0978R.id.tv_manufacturer);
            TextView textView7 = (TextView) view.findViewById(C0978R.id.tv_brand);
            TextView textView8 = (TextView) view.findViewById(C0978R.id.tv_boot);
            TextView textView9 = (TextView) view.findViewById(C0978R.id.tv_build);
            TextView textView10 = (TextView) view.findViewById(C0978R.id.tv_release);
            TextView textView11 = (TextView) view.findViewById(C0978R.id.tv_incremental);
            TextView textView12 = (TextView) view.findViewById(C0978R.id.tv_sdk);
            TextView textView13 = (TextView) view.findViewById(C0978R.id.tv_display);
            TextView textView14 = (TextView) view.findViewById(C0978R.id.tv_fingerprint);
            TextView textView15 = (TextView) view.findViewById(C0978R.id.tv_desc);
            TextView textView16 = (TextView) view.findViewById(C0978R.id.tv_board);
            TextView textView17 = (TextView) view.findViewById(C0978R.id.tv_hardware);
            TextView textView18 = (TextView) view.findViewById(C0978R.id.tv_host);
            TextView textView19 = (TextView) view.findViewById(C0978R.id.tv_user);
            TextView textView20 = (TextView) view.findViewById(C0978R.id.tv_flavor);
            TextView textView21 = (TextView) view.findViewById(C0978R.id.tv_date);
            TextView textView22 = (TextView) view.findViewById(C0978R.id.tv_utc);
            materialCardView.setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(RitualFragmentActivity.this.requireContext()));
            if (this._data.get(i).containsKey("ONLINE")) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
            }
            textView2.setText(this._data.get(i).get("DEVICE").toString());
            textView3.setText(this._data.get(i).get("MODEL").toString());
            textView4.setText(this._data.get(i).get("PRODUCT").toString());
            textView5.setText(this._data.get(i).get("NAME").toString());
            textView6.setText(this._data.get(i).get("MANUFACTURER").toString());
            textView7.setText(this._data.get(i).get("BRAND").toString());
            textView8.setText(this._data.get(i).get("BOOT").toString());
            textView9.setText(this._data.get(i).get("BUILDID").toString());
            textView10.setText(this._data.get(i).get("RELEASE").toString());
            textView11.setText(this._data.get(i).get("INCREMENTAL").toString());
            textView12.setText(this._data.get(i).get("SDK").toString());
            textView13.setText(this._data.get(i).get("DISPLAY").toString());
            textView14.setText(this._data.get(i).get("FINGERPRINT").toString());
            textView15.setText(this._data.get(i).get("DESCRIPTION").toString());
            textView16.setText(this._data.get(i).get("BOARD").toString());
            textView17.setText(this._data.get(i).get("HARDWARE").toString());
            textView18.setText(this._data.get(i).get("HOST").toString());
            textView19.setText(this._data.get(i).get("USER").toString());
            textView20.setText(this._data.get(i).get("FLAVOR").toString());
            textView21.setText(this._data.get(i).get("DATE").toString());
            textView22.setText(this._data.get(i).get("UTC").toString());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this._data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View view) {
                super(view);
            }
        }
    }

    public class Rv_3Adapter extends RecyclerView.Adapter<Rv_3Adapter.ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;

        public Rv_3Adapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View viewInflate = RitualFragmentActivity.this.getActivity().getLayoutInflater().inflate(C0978R.layout.ritual_listview_editor_prop, (ViewGroup) null);
            viewInflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(viewInflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Chip chip;
            Chip chip2;
            Chip chip3;
            Chip chip4;
            Chip chip5;
            Chip chip6;
            Chip chip7;
            Chip chip8;
            Chip chip9;
            Chip chip10;
            Chip chip11;
            Chip chip12;
            Chip chip13;
            Chip chip14;
            Chip chip15;
            Chip chip16;
            Chip chip17;
            Chip chip18;
            Chip chip19;
            Chip chip20;
            View view = viewHolder.itemView;
            Chip chip21 = (Chip) view.findViewById(C0978R.id.mchip_modpes_start);
            Chip chip22 = (Chip) view.findViewById(C0978R.id.mchip_modpes_end);
            Chip chip23 = (Chip) view.findViewById(C0978R.id.m_device);
            Chip chip24 = (Chip) view.findViewById(C0978R.id.m_product);
            Chip chip25 = (Chip) view.findViewById(C0978R.id.m_name);
            Chip chip26 = (Chip) view.findViewById(C0978R.id.m_model);
            Chip chip27 = (Chip) view.findViewById(C0978R.id.m_brand);
            Chip chip28 = (Chip) view.findViewById(C0978R.id.m_manufacturer);
            Chip chip29 = (Chip) view.findViewById(C0978R.id.m_buildid);
            Chip chip30 = (Chip) view.findViewById(C0978R.id.m_incremental);
            Chip chip31 = (Chip) view.findViewById(C0978R.id.m_display);
            Chip chip32 = (Chip) view.findViewById(C0978R.id.m_fingerprint);
            Chip chip33 = (Chip) view.findViewById(C0978R.id.m_desc);
            Chip chip34 = (Chip) view.findViewById(C0978R.id.m_boot);
            MaterialButton materialButton = (MaterialButton) view.findViewById(C0978R.id.btn_down);
            MaterialButton materialButton2 = (MaterialButton) view.findViewById(C0978R.id.btn_up);
            Chip chip35 = (Chip) view.findViewById(C0978R.id.m_9);
            Chip chip36 = (Chip) view.findViewById(C0978R.id.m_10);
            Chip chip37 = (Chip) view.findViewById(C0978R.id.m_11);
            Chip chip38 = (Chip) view.findViewById(C0978R.id.m_12);
            Chip chip39 = (Chip) view.findViewById(C0978R.id.m_12l);
            Chip chip40 = (Chip) view.findViewById(C0978R.id.m_13);
            Chip chip41 = (Chip) view.findViewById(C0978R.id.m_14);
            Chip chip42 = (Chip) view.findViewById(C0978R.id.m_15);
            Chip chip43 = (Chip) view.findViewById(C0978R.id.m_timepick);
            Chip chip44 = (Chip) view.findViewById(C0978R.id.m_gms);
            Chip chip45 = (Chip) view.findViewById(C0978R.id.m_ssaid);
            Chip chip46 = (Chip) view.findViewById(C0978R.id.m_nol);
            Chip chip47 = (Chip) view.findViewById(C0978R.id.m_reboot);
            Chip chip48 = (Chip) view.findViewById(C0978R.id.m_dalvic);
            Chip chip49 = (Chip) view.findViewById(C0978R.id.m_norestart);
            chip23.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip24.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip25.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip26.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip27.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip28.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip29.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip30.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip31.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip32.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip33.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip34.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip35.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip36.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip37.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip38.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip39.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip40.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip41.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip42.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip43.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip44.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip45.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip46.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip47.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip48.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip49.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip21.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip22.setTypeface(Typeface.createFromAsset(RitualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip42.setText("  OS ".concat(RitualFragmentActivity.this.prefos.getString("OSPLUS", "").concat("  ")));
            if (this._data.get(i).get("DEVICE").toString().equals("true")) {
                chip23.setChecked(true);
            } else {
                chip23.setChecked(false);
            }
            if (this._data.get(i).get("MODEL").toString().equals("true")) {
                chip26.setChecked(true);
            } else {
                chip26.setChecked(false);
            }
            if (this._data.get(i).get("PRODUCT").toString().equals("true")) {
                chip24.setChecked(true);
            } else {
                chip24.setChecked(false);
            }
            if (this._data.get(i).get("MANUFACTURER").toString().equals("true")) {
                chip28.setChecked(true);
            } else {
                chip28.setChecked(false);
            }
            if (this._data.get(i).get("BRAND").toString().equals("true")) {
                chip27.setChecked(true);
            } else {
                chip27.setChecked(false);
            }
            if (this._data.get(i).get("BOOT").toString().equals("true")) {
                chip34.setChecked(true);
            } else {
                chip34.setChecked(false);
            }
            if (this._data.get(i).get("BUILDID").toString().equals("true")) {
                chip29.setChecked(true);
            } else {
                chip29.setChecked(false);
            }
            if (this._data.get(i).get("INCREMENTAL").toString().equals("true")) {
                chip30.setChecked(true);
            } else {
                chip30.setChecked(false);
            }
            if (this._data.get(i).get("DISPLAY").toString().equals("true")) {
                chip31.setChecked(true);
            } else {
                chip31.setChecked(false);
            }
            if (this._data.get(i).get("FINGERPRINT").toString().equals("true")) {
                chip32.setChecked(true);
            } else {
                chip32.setChecked(false);
            }
            if (this._data.get(i).get("DESCRIPTION").toString().equals("true")) {
                chip = chip33;
                chip.setChecked(true);
            } else {
                chip = chip33;
                chip.setChecked(false);
            }
            Chip chip50 = chip;
            if (this._data.get(i).get("NAME").toString().equals("true")) {
                chip2 = chip25;
                chip2.setChecked(true);
            } else {
                chip2 = chip25;
                chip2.setChecked(false);
            }
            if (this._data.get(i).get("OS9").toString().equals("false")) {
                chip3 = chip30;
                chip4 = chip35;
                chip4.setChecked(false);
            } else {
                chip3 = chip30;
                chip4 = chip35;
                chip4.setChecked(true);
            }
            Chip chip51 = chip4;
            if (this._data.get(i).get("OS10").toString().equals("false")) {
                chip5 = chip36;
                chip5.setChecked(false);
            } else {
                chip5 = chip36;
                chip5.setChecked(true);
            }
            Chip chip52 = chip5;
            if (this._data.get(i).get("OS11").toString().equals("false")) {
                chip6 = chip37;
                chip6.setChecked(false);
            } else {
                chip6 = chip37;
                chip6.setChecked(true);
            }
            Chip chip53 = chip6;
            if (this._data.get(i).get("OS12").toString().equals("false")) {
                chip7 = chip38;
                chip7.setChecked(false);
            } else {
                chip7 = chip38;
                chip7.setChecked(true);
            }
            Chip chip54 = chip7;
            if (this._data.get(i).get("OS12L").toString().equals("false")) {
                chip8 = chip39;
                chip8.setChecked(false);
            } else {
                chip8 = chip39;
                chip8.setChecked(true);
            }
            Chip chip55 = chip8;
            if (this._data.get(i).get("OS13").toString().equals("false")) {
                chip9 = chip40;
                chip9.setChecked(false);
            } else {
                chip9 = chip40;
                chip9.setChecked(true);
            }
            Chip chip56 = chip9;
            if (this._data.get(i).get("OS14").toString().equals("false")) {
                chip10 = chip41;
                chip10.setChecked(false);
            } else {
                chip10 = chip41;
                chip10.setChecked(true);
            }
            if (this._data.get(i).get("OSPLUS").toString().equals("false")) {
                chip11 = chip42;
                chip11.setChecked(false);
            } else {
                chip11 = chip42;
                chip11.setChecked(true);
            }
            Chip chip57 = chip10;
            if (this._data.get(i).get("TIMEPICK").toString().equals("true")) {
                chip12 = chip43;
                chip12.setChecked(true);
            } else {
                chip12 = chip43;
                chip12.setChecked(false);
            }
            Chip chip58 = chip12;
            if (this._data.get(i).get("WIPEGMS").toString().equals("true")) {
                chip13 = chip44;
                chip13.setChecked(true);
            } else {
                chip13 = chip44;
                chip13.setChecked(false);
            }
            Chip chip59 = chip13;
            if (this._data.get(i).get("RESET0").toString().equals("true")) {
                chip14 = chip46;
                chip14.setChecked(true);
            } else {
                chip14 = chip46;
                chip14.setChecked(false);
            }
            Chip chip60 = chip14;
            if (this._data.get(i).get("SSAID").toString().equals("true")) {
                chip15 = chip45;
                chip15.setChecked(true);
            } else {
                chip15 = chip45;
                chip15.setChecked(false);
            }
            Chip chip61 = chip15;
            if (this._data.get(i).get("REBOOT").toString().equals("true")) {
                chip16 = chip47;
                chip16.setChecked(true);
            } else {
                chip16 = chip47;
                chip16.setChecked(false);
            }
            Chip chip62 = chip16;
            if (this._data.get(i).get("DALVIC").toString().equals("true")) {
                chip17 = chip48;
                chip17.setChecked(true);
            } else {
                chip17 = chip48;
                chip17.setChecked(false);
            }
            Chip chip63 = chip17;
            if (this._data.get(i).get("NORESTART").toString().equals("true")) {
                chip18 = chip49;
                chip18.setChecked(true);
            } else {
                chip18 = chip49;
                chip18.setChecked(false);
            }
            Chip chip64 = chip18;
            if (this._data.get(i).get("MODPESSTART").toString().equals("true")) {
                chip19 = chip21;
                chip19.setChecked(true);
            } else {
                chip19 = chip21;
                chip19.setChecked(false);
            }
            Chip chip65 = chip19;
            if (this._data.get(i).get("MODPESEND").toString().equals("true")) {
                chip20 = chip22;
                chip20.setChecked(true);
            } else {
                chip20 = chip22;
                chip20.setChecked(false);
            }
            double d = i;
            RitualFragmentActivity.this._onChipProp(chip23, "DEVICE", d, this._data);
            RitualFragmentActivity.this._onChipProp(chip26, "MODEL", d, this._data);
            RitualFragmentActivity.this._onChipProp(chip24, "PRODUCT", d, this._data);
            RitualFragmentActivity.this._onChipProp(chip2, "NAME", d, this._data);
            RitualFragmentActivity.this._onChipProp(chip28, "MANUFACTURER", d, this._data);
            RitualFragmentActivity.this._onChipProp(chip27, "BRAND", d, this._data);
            RitualFragmentActivity.this._onChipProp(chip34, "BOOT", d, this._data);
            RitualFragmentActivity.this._onChipProp(chip29, "BUILDID", d, this._data);
            RitualFragmentActivity.this._onChipProp(chip3, "INCREMENTAL", d, this._data);
            RitualFragmentActivity.this._onChipProp(chip31, "DISPLAY", d, this._data);
            RitualFragmentActivity.this._onChipProp(chip32, "FINGERPRINT", d, this._data);
            RitualFragmentActivity.this._onChipProp(chip50, "DESCRIPTION", d, this._data);
            RitualFragmentActivity.this._onChipRelease(chip51, "OS9", this._data, d, "9");
            RitualFragmentActivity.this._onChipRelease(chip52, "OS10", this._data, d, "10");
            RitualFragmentActivity.this._onChipRelease(chip53, "OS11", this._data, d, "11");
            RitualFragmentActivity.this._onChipRelease(chip54, "OS12", this._data, d, "12");
            RitualFragmentActivity.this._onChipRelease(chip55, "OS12L", this._data, d, "12.1");
            RitualFragmentActivity.this._onChipRelease(chip56, "OS13", this._data, d, "13");
            RitualFragmentActivity.this._onChipRelease(chip57, "OS14", this._data, d, "14");
            RitualFragmentActivity ritualFragmentActivity = RitualFragmentActivity.this;
            ritualFragmentActivity._onChipRelease(chip11, "OSPLUS", this._data, d, ritualFragmentActivity.prefos.getString("OSPLUS", ""));
            RitualFragmentActivity.this._onChipClean(chip61, "SSAID", d, this._data);
            RitualFragmentActivity.this._onChipClean(chip60, "RESET0", d, this._data);
            RitualFragmentActivity.this._onChipReboot(chip62, "REBOOT", d, this._data);
            RitualFragmentActivity.this._onChipReboot(chip63, "DALVIC", d, this._data);
            RitualFragmentActivity.this._onChipReboot(chip64, "NORESTART", d, this._data);
            RitualFragmentActivity.this._onChipWipe(chip59, "WIPEGMS", d, this._data);
            RitualFragmentActivity.this._onChipWipe(chip58, "TIMEPICK", d, this._data);
            final Chip chip66 = chip11;
            RitualFragmentActivity.this._onChipModePes(chip65, "MODPESSTART", d, this._data);
            RitualFragmentActivity.this._onChipModePes(chip20, "MODPESEND", d, this._data);
            materialButton.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onChangeOsPlus("down", chip66);
                }
            });
            materialButton2.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    RitualFragmentActivity.this._onChangeOsPlus("up", chip66);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this._data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View view) {
                super(view);
            }
        }
    }
}
