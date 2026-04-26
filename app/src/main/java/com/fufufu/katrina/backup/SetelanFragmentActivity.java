package com.fufufu.katrina.backup;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import io.noties.markwon.Markwon;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

public class SetelanFragmentActivity extends Fragment {
    private AlertDialog CONVERTDIALOG;
    private AlertDialog SHOWFOLDER;
    private Button btn_address_belakang;
    private Button btn_address_depan;
    private Button btn_address_tengah;
    private MaterialButton btn_choose_folder;
    private MaterialButton btn_convert;
    private Button btn_margin;
    private Button btn_margin_down;
    private Button btn_margin_up;
    private Button btn_nama_dua;
    private Button btn_nama_tiga;
    private Button btn_save_address;
    private MaterialButton btn_save_bot;
    private Button btn_save_email;
    private Button btn_save_name;
    private Button btn_save_password;
    private Button btn_save_telepon;
    private Button btn_scale;
    private Button btn_scale_down;
    private Button btn_scale_up;
    private MaterialCardView cv_1;
    private MaterialCardView cv_2;
    private MaterialCardView cv_3;
    private MaterialCardView cv_4;
    private MaterialCardView cv_5;
    private MaterialCardView cv_7;
    private EditText et_chat_id;
    private EditText et_code_address;
    private EditText et_code_email;
    private EditText et_code_name;
    private EditText et_code_password;
    private EditText et_code_telepon;
    private EditText et_token_bot;
    private EditText et_total_address;
    private MaterialButtonToggleGroup group_address;
    private MaterialButtonToggleGroup group_margin;
    private MaterialButtonToggleGroup group_name;
    private MaterialButtonToggleGroup group_scale;
    private LinearLayout ln_012;
    private LinearLayout ln_013;
    private LinearLayout ln_014;
    private LinearLayout ln_015;
    private LinearLayout ln_016;
    private LinearLayout ln_017;
    private LinearLayout ln_018;
    private LinearLayout ln_019;
    private LinearLayout ln_020;
    private LinearLayout ln_026;
    private LinearLayout ln_028;
    private LinearLayout ln_029;
    private LinearLayout ln_03;
    private LinearLayout ln_030;
    private LinearLayout ln_031;
    private LinearLayout ln_032;
    private LinearLayout ln_04;
    private LinearLayout ln_05;
    private LinearLayout ln_06;
    private LinearLayout ln_07;
    private LinearLayout ln_08;
    private LinearLayout ln_09;
    private LinearLayout ln_33;
    private LinearLayout ln_base_island;
    private ExpandableLayout ln_bot_contain;
    private ExpandableLayout ln_changelog_contain;
    private ExpandableLayout ln_convert_contain;
    private ExpandableLayout ln_island_contain;
    private ExpandableLayout ln_random_contain;
    private LinearLayout ln_set_convert;
    private ScrollView ln_sett;
    private LinearLayout ln_sett_contain;
    private LinearLayout ln_sett_island;
    private LinearLayout ln_sett_random;
    private MyCONVERT myCONVERT;
    private MyREADFOLDER myREADFOLDER;
    private SharedPreferences pref;
    private SharedPreferences prefrandom;
    private SharedPreferences prefuser;
    private Runnable runnablefolderone;
    private Runnable runnablefoldertri;
    private Runnable runnablefoldertwo;
    private TextView tv_010;
    private TextView tv_013;
    private TextView tv_015;
    private TextView tv_018;
    private TextView tv_022;
    private TextView tv_023;
    private TextView tv_024;
    private TextView tv_04;
    private TextView tv_05;
    private TextView tv_06;
    private TextView tv_09;
    private TextView tv_27;
    private TextView tv_about;
    private TextView tv_loc_code_name;
    private TextView tv_margin;
    private TextView tv_sampel_address;
    private TextView tv_sampel_email;
    private TextView tv_sampel_name;
    private TextView tv_sampel_password;
    private TextView tv_sampel_telepon;
    private TextView tv_scale;
    private TextView tv_title_address;
    private TextView tv_title_email;
    private TextView tv_title_name;
    private TextView tv_title_password;
    private TextView tv_title_set_about;
    private TextView tv_title_set_bot;
    private TextView tv_title_set_convert;
    private TextView tv_title_set_kill;
    private TextView tv_title_sett_island;
    private TextView tv_title_sett_random;
    private TextView tv_title_telepon;
    private TextView tv_umail;
    private TextView tv_uname;
    private String s_location_address_code = "";
    private String s_code_address = "";
    private String s_total_address = "";
    private String s_location_name_code = "";
    private String s_code_name = "";
    private String s_total_name = "";
    private String s_code_email = "";
    private String s_front_address = "";
    private String s_result_address = "";
    private double n_total_address = 0.0d;
    private String s_insert = "";
    private String s_result_name = "";
    private double n_total_name = 0.0d;
    private String s_result_email = "";
    private String s_folder_picker = "";
    private double n_pos = 0.0d;
    private boolean b_folder_scan = false;
    private String s_targetpath = "";
    private String s_package = "";

        private double f867n = 0.0d;
    private double n_folder = 0.0d;
    private String s_loop_tar = "";
    private String s_loop_prop = "";
    private String s_loop_tar_name = "";
    private String s_loop_prop_name = "";
    private String s_folder = "";
    private String s_finaltarget = "";
    private String s_file_properties = "";
    private double n_properties = 0.0d;
    private String s_file_note = "";
    private String s_file_date = "";
    private String s_file_unix = "";
    private HashMap<String, Object> m_json = new HashMap<>();
    private String s_json = "";
    private String s_sort = "";
    private HashMap<String, Object> m_unix = new HashMap<>();
    private String s_path = "";
    private String s_ext = "";
    private String s_code_password = "";
    private HashMap<String, Object> m_request = new HashMap<>();
    private ArrayList<String> ls_random_address = new ArrayList<>();
    private ArrayList<String> ls_random_name = new ArrayList<>();
    private ArrayList<String> ls_folder_picker = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_folder_picker = new ArrayList<>();
    private ArrayList<String> ls_convert_backup = new ArrayList<>();
    private ArrayList<String> ls_file_properties = new ArrayList<>();
    private ArrayList<String> ls_unix_backup = new ArrayList<>();
    private ArrayList<String> ls_all_file_backup = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_unix_backup = new ArrayList<>();

        private Calendar f866c = Calendar.getInstance();
    private Intent i_kill = new Intent();
    int width_value = 0;
    int margin_value = 0;
    int new_width_value = 0;
    int new_margin_value = 0;
    private Handler folderone = new Handler();
    private Handler foldertwo = new Handler();
    private Handler foldertri = new Handler();

    public void _EXTRA() {
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C0978R.layout.setelan_fragment, viewGroup, false);
        initialize(bundle, viewInflate);
        initializeLogic();
        return viewInflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.ln_base_island = (LinearLayout) view.findViewById(C0978R.id.ln_base_island);
        this.ln_018 = (LinearLayout) view.findViewById(C0978R.id.ln_018);
        this.ln_sett = (ScrollView) view.findViewById(C0978R.id.ln_sett);
        this.ln_019 = (LinearLayout) view.findViewById(C0978R.id.ln_019);
        this.ln_020 = (LinearLayout) view.findViewById(C0978R.id.ln_020);
        this.tv_umail = (TextView) view.findViewById(C0978R.id.tv_umail);
        this.tv_uname = (TextView) view.findViewById(C0978R.id.tv_uname);
        this.ln_sett_contain = (LinearLayout) view.findViewById(C0978R.id.ln_sett_contain);
        this.cv_1 = (MaterialCardView) view.findViewById(C0978R.id.cv_1);
        this.cv_2 = (MaterialCardView) view.findViewById(C0978R.id.cv_2);
        this.cv_3 = (MaterialCardView) view.findViewById(C0978R.id.cv_3);
        this.cv_4 = (MaterialCardView) view.findViewById(C0978R.id.cv_4);
        this.cv_7 = (MaterialCardView) view.findViewById(C0978R.id.cv_7);
        this.cv_5 = (MaterialCardView) view.findViewById(C0978R.id.cv_5);
        this.ln_sett_island = (LinearLayout) view.findViewById(C0978R.id.ln_sett_island);
        this.tv_title_sett_island = (TextView) view.findViewById(C0978R.id.tv_title_sett_island);
        this.ln_island_contain = (ExpandableLayout) view.findViewById(C0978R.id.ln_island_contain);
        this.ln_08 = (LinearLayout) view.findViewById(C0978R.id.ln_08);
        this.tv_scale = (TextView) view.findViewById(C0978R.id.tv_scale);
        this.group_scale = (MaterialButtonToggleGroup) view.findViewById(C0978R.id.group_scale);
        this.tv_margin = (TextView) view.findViewById(C0978R.id.tv_margin);
        this.group_margin = (MaterialButtonToggleGroup) view.findViewById(C0978R.id.group_margin);
        this.btn_scale_down = (Button) view.findViewById(C0978R.id.btn_scale_down);
        this.btn_scale = (Button) view.findViewById(C0978R.id.btn_scale);
        this.btn_scale_up = (Button) view.findViewById(C0978R.id.btn_scale_up);
        this.btn_margin_down = (Button) view.findViewById(C0978R.id.btn_margin_down);
        this.btn_margin = (Button) view.findViewById(C0978R.id.btn_margin);
        this.btn_margin_up = (Button) view.findViewById(C0978R.id.btn_margin_up);
        this.ln_sett_random = (LinearLayout) view.findViewById(C0978R.id.ln_sett_random);
        this.tv_title_sett_random = (TextView) view.findViewById(C0978R.id.tv_title_sett_random);
        this.ln_random_contain = (ExpandableLayout) view.findViewById(C0978R.id.ln_random_contain);
        this.ln_07 = (LinearLayout) view.findViewById(C0978R.id.ln_07);
        this.tv_title_address = (TextView) view.findViewById(C0978R.id.tv_title_address);
        this.tv_sampel_address = (TextView) view.findViewById(C0978R.id.tv_sampel_address);
        this.ln_03 = (LinearLayout) view.findViewById(C0978R.id.ln_03);
        this.ln_04 = (LinearLayout) view.findViewById(C0978R.id.ln_04);
        this.tv_06 = (TextView) view.findViewById(C0978R.id.tv_06);
        this.group_address = (MaterialButtonToggleGroup) view.findViewById(C0978R.id.group_address);
        this.btn_save_address = (Button) view.findViewById(C0978R.id.btn_save_address);
        this.tv_title_name = (TextView) view.findViewById(C0978R.id.tv_title_name);
        this.tv_sampel_name = (TextView) view.findViewById(C0978R.id.tv_sampel_name);
        this.ln_06 = (LinearLayout) view.findViewById(C0978R.id.ln_06);
        this.ln_05 = (LinearLayout) view.findViewById(C0978R.id.ln_05);
        this.group_name = (MaterialButtonToggleGroup) view.findViewById(C0978R.id.group_name);
        this.btn_save_name = (Button) view.findViewById(C0978R.id.btn_save_name);
        this.tv_title_email = (TextView) view.findViewById(C0978R.id.tv_title_email);
        this.tv_sampel_email = (TextView) view.findViewById(C0978R.id.tv_sampel_email);
        this.ln_09 = (LinearLayout) view.findViewById(C0978R.id.ln_09);
        this.btn_save_email = (Button) view.findViewById(C0978R.id.btn_save_email);
        this.tv_title_password = (TextView) view.findViewById(C0978R.id.tv_title_password);
        this.tv_sampel_password = (TextView) view.findViewById(C0978R.id.tv_sampel_password);
        this.ln_015 = (LinearLayout) view.findViewById(C0978R.id.ln_015);
        this.btn_save_password = (Button) view.findViewById(C0978R.id.btn_save_password);
        this.tv_title_telepon = (TextView) view.findViewById(C0978R.id.tv_title_telepon);
        this.tv_sampel_telepon = (TextView) view.findViewById(C0978R.id.tv_sampel_telepon);
        this.ln_33 = (LinearLayout) view.findViewById(C0978R.id.ln_33);
        this.btn_save_telepon = (Button) view.findViewById(C0978R.id.btn_save_telepon);
        this.tv_04 = (TextView) view.findViewById(C0978R.id.tv_04);
        this.et_total_address = (EditText) view.findViewById(C0978R.id.et_total_address);
        this.tv_05 = (TextView) view.findViewById(C0978R.id.tv_05);
        this.et_code_address = (EditText) view.findViewById(C0978R.id.et_code_address);
        this.btn_address_depan = (Button) view.findViewById(C0978R.id.btn_address_depan);
        this.btn_address_tengah = (Button) view.findViewById(C0978R.id.btn_address_tengah);
        this.btn_address_belakang = (Button) view.findViewById(C0978R.id.btn_address_belakang);
        this.tv_010 = (TextView) view.findViewById(C0978R.id.tv_010);
        this.tv_loc_code_name = (TextView) view.findViewById(C0978R.id.tv_loc_code_name);
        this.tv_09 = (TextView) view.findViewById(C0978R.id.tv_09);
        this.et_code_name = (EditText) view.findViewById(C0978R.id.et_code_name);
        this.btn_nama_dua = (Button) view.findViewById(C0978R.id.btn_nama_dua);
        this.btn_nama_tiga = (Button) view.findViewById(C0978R.id.btn_nama_tiga);
        this.tv_013 = (TextView) view.findViewById(C0978R.id.tv_013);
        this.et_code_email = (EditText) view.findViewById(C0978R.id.et_code_email);
        this.tv_018 = (TextView) view.findViewById(C0978R.id.tv_018);
        this.et_code_password = (EditText) view.findViewById(C0978R.id.et_code_password);
        this.tv_27 = (TextView) view.findViewById(C0978R.id.tv_27);
        this.et_code_telepon = (EditText) view.findViewById(C0978R.id.et_code_telepon);
        this.ln_set_convert = (LinearLayout) view.findViewById(C0978R.id.ln_set_convert);
        this.tv_title_set_convert = (TextView) view.findViewById(C0978R.id.tv_title_set_convert);
        this.ln_convert_contain = (ExpandableLayout) view.findViewById(C0978R.id.ln_convert_contain);
        this.ln_012 = (LinearLayout) view.findViewById(C0978R.id.ln_012);
        this.tv_015 = (TextView) view.findViewById(C0978R.id.tv_015);
        this.ln_013 = (LinearLayout) view.findViewById(C0978R.id.ln_013);
        this.ln_014 = (LinearLayout) view.findViewById(C0978R.id.ln_014);
        this.btn_choose_folder = (MaterialButton) view.findViewById(C0978R.id.btn_choose_folder);
        this.btn_convert = (MaterialButton) view.findViewById(C0978R.id.btn_convert);
        this.ln_016 = (LinearLayout) view.findViewById(C0978R.id.ln_016);
        this.tv_title_set_kill = (TextView) view.findViewById(C0978R.id.tv_title_set_kill);
        this.ln_026 = (LinearLayout) view.findViewById(C0978R.id.ln_026);
        this.tv_title_set_bot = (TextView) view.findViewById(C0978R.id.tv_title_set_bot);
        this.ln_bot_contain = (ExpandableLayout) view.findViewById(C0978R.id.ln_bot_contain);
        this.ln_028 = (LinearLayout) view.findViewById(C0978R.id.ln_028);
        this.tv_022 = (TextView) view.findViewById(C0978R.id.tv_022);
        this.ln_029 = (LinearLayout) view.findViewById(C0978R.id.ln_029);
        this.ln_030 = (LinearLayout) view.findViewById(C0978R.id.ln_030);
        this.ln_031 = (LinearLayout) view.findViewById(C0978R.id.ln_031);
        this.ln_032 = (LinearLayout) view.findViewById(C0978R.id.ln_032);
        this.tv_023 = (TextView) view.findViewById(C0978R.id.tv_023);
        this.et_token_bot = (EditText) view.findViewById(C0978R.id.et_token_bot);
        this.tv_024 = (TextView) view.findViewById(C0978R.id.tv_024);
        this.et_chat_id = (EditText) view.findViewById(C0978R.id.et_chat_id);
        this.btn_save_bot = (MaterialButton) view.findViewById(C0978R.id.btn_save_bot);
        this.ln_017 = (LinearLayout) view.findViewById(C0978R.id.ln_017);
        this.tv_title_set_about = (TextView) view.findViewById(C0978R.id.tv_title_set_about);
        this.ln_changelog_contain = (ExpandableLayout) view.findViewById(C0978R.id.ln_changelog_contain);
        this.tv_about = (TextView) view.findViewById(C0978R.id.tv_about);
        this.pref = getContext().getSharedPreferences("floating_island_pref", 0);
        this.prefrandom = getContext().getSharedPreferences("random_preferences", 0);
        this.prefuser = getContext().getSharedPreferences("user_preferences", 0);
        new TextWatcher() {             @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        };
        this.tv_title_sett_island.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetelanFragmentActivity.this.ln_island_contain.isExpanded()) {
                    SetelanFragmentActivity.this.ln_island_contain.collapse();
                } else {
                    SetelanFragmentActivity.this.ln_island_contain.expand();
                }
            }
        });
        this.btn_scale_down.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity.width_value = setelanFragmentActivity.pref.getInt("width_shape", 80);
                SetelanFragmentActivity.this.new_width_value = SetelanFragmentActivity.this.width_value - 1;
                SharedPreferences.Editor editorEdit = SetelanFragmentActivity.this.pref.edit();
                editorEdit.putInt("width_shape", SetelanFragmentActivity.this.new_width_value);
                editorEdit.apply();
                SetelanFragmentActivity setelanFragmentActivity2 = SetelanFragmentActivity.this;
                setelanFragmentActivity2.width_value = setelanFragmentActivity2.pref.getInt("width_shape", 80);
                SetelanFragmentActivity.this.btn_scale.setText(String.valueOf(SetelanFragmentActivity.this.width_value));
            }
        });
        this.btn_scale_up.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity.width_value = setelanFragmentActivity.pref.getInt("width_shape", 80);
                SetelanFragmentActivity setelanFragmentActivity2 = SetelanFragmentActivity.this;
                setelanFragmentActivity2.new_width_value = setelanFragmentActivity2.width_value + 1;
                SharedPreferences.Editor editorEdit = SetelanFragmentActivity.this.pref.edit();
                editorEdit.putInt("width_shape", SetelanFragmentActivity.this.new_width_value);
                editorEdit.apply();
                SetelanFragmentActivity setelanFragmentActivity3 = SetelanFragmentActivity.this;
                setelanFragmentActivity3.width_value = setelanFragmentActivity3.pref.getInt("width_shape", 80);
                SetelanFragmentActivity.this.btn_scale.setText(String.valueOf(SetelanFragmentActivity.this.width_value));
            }
        });
        this.btn_margin_down.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity.margin_value = setelanFragmentActivity.pref.getInt("margin_shape", 0);
                SetelanFragmentActivity.this.new_margin_value = SetelanFragmentActivity.this.margin_value - 1;
                SharedPreferences.Editor editorEdit = SetelanFragmentActivity.this.pref.edit();
                editorEdit.putInt("margin_shape", SetelanFragmentActivity.this.new_margin_value);
                editorEdit.apply();
                SetelanFragmentActivity setelanFragmentActivity2 = SetelanFragmentActivity.this;
                setelanFragmentActivity2.margin_value = setelanFragmentActivity2.pref.getInt("margin_shape", 0);
                SetelanFragmentActivity.this.btn_margin.setText(String.valueOf(SetelanFragmentActivity.this.margin_value));
            }
        });
        this.btn_margin_up.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity.margin_value = setelanFragmentActivity.pref.getInt("margin_shape", 0);
                SetelanFragmentActivity setelanFragmentActivity2 = SetelanFragmentActivity.this;
                setelanFragmentActivity2.new_margin_value = setelanFragmentActivity2.margin_value + 1;
                SharedPreferences.Editor editorEdit = SetelanFragmentActivity.this.pref.edit();
                editorEdit.putInt("margin_shape", SetelanFragmentActivity.this.new_margin_value);
                editorEdit.apply();
                SetelanFragmentActivity setelanFragmentActivity3 = SetelanFragmentActivity.this;
                setelanFragmentActivity3.margin_value = setelanFragmentActivity3.pref.getInt("margin_shape", 80);
                SetelanFragmentActivity.this.btn_margin.setText(String.valueOf(SetelanFragmentActivity.this.margin_value));
            }
        });
        this.tv_title_sett_random.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetelanFragmentActivity.this.ln_random_contain.isExpanded()) {
                    SetelanFragmentActivity.this.ln_random_contain.collapse();
                } else {
                    SetelanFragmentActivity.this.ln_random_contain.expand();
                }
            }
        });
        this.btn_save_address.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this._previewSaveAddress();
            }
        });
        this.btn_save_name.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this._previewSaveName();
            }
        });
        this.btn_save_email.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this._previewSaveEmail();
            }
        });
        this.btn_save_password.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this._previewSavePassword();
            }
        });
        this.btn_save_telepon.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this._previewSaveTelepon();
            }
        });
        this.et_total_address.addTextChangedListener(new TextWatcher() {             @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SetelanFragmentActivity.this.s_total_address = charSequence.toString();
            }
        });
        this.et_code_address.addTextChangedListener(new TextWatcher() {             @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SetelanFragmentActivity.this.s_code_address = charSequence.toString();
            }
        });
        this.btn_address_depan.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this.s_location_address_code = "d";
            }
        });
        this.btn_address_tengah.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this.s_location_address_code = "t";
            }
        });
        this.btn_address_belakang.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this.s_location_address_code = "b";
            }
        });
        this.tv_loc_code_name.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetelanFragmentActivity.this.tv_loc_code_name.getText().toString().equals("Depan")) {
                    SetelanFragmentActivity.this.s_location_name_code = "b";
                    SetelanFragmentActivity.this.tv_loc_code_name.setText("Belakang");
                } else {
                    SetelanFragmentActivity.this.s_location_name_code = "d";
                    SetelanFragmentActivity.this.tv_loc_code_name.setText("Depan");
                }
            }
        });
        this.et_code_name.addTextChangedListener(new TextWatcher() {             @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SetelanFragmentActivity.this.s_code_name = charSequence.toString();
            }
        });
        this.btn_nama_dua.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this.s_total_name = ExifInterface.GPS_MEASUREMENT_2D;
            }
        });
        this.btn_nama_tiga.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this.s_total_name = ExifInterface.GPS_MEASUREMENT_3D;
            }
        });
        this.et_code_email.addTextChangedListener(new TextWatcher() {             @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SetelanFragmentActivity.this.s_code_email = charSequence.toString();
            }
        });
        this.tv_title_set_convert.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetelanFragmentActivity.this.ln_convert_contain.isExpanded()) {
                    SetelanFragmentActivity.this.ln_convert_contain.collapse();
                } else {
                    SetelanFragmentActivity.this.ln_convert_contain.expand();
                }
            }
        });
        this.btn_choose_folder.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this._showFolderPicker();
            }
        });
        this.btn_convert.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetelanFragmentActivity.this.btn_choose_folder.getText().toString().equals(getString(C0978R.string.btn_pilih_folder))) {
                    SketchwareUtil.showMessage(SetelanFragmentActivity.this.getContext().getApplicationContext(), getString(C0978R.string.toast_select_folder_first));
                } else {
                    SetelanFragmentActivity.this._showDialogConvert();
                }
            }
        });
        this.tv_title_set_kill.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this.i_kill.setClass(SetelanFragmentActivity.this.getContext().getApplicationContext(), AppListAll.class);
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity.startActivity(setelanFragmentActivity.i_kill);
            }
        });
        this.tv_title_set_bot.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetelanFragmentActivity.this.ln_bot_contain.isExpanded()) {
                    SetelanFragmentActivity.this.ln_bot_contain.collapse();
                } else {
                    SetelanFragmentActivity.this.ln_bot_contain.expand();
                }
            }
        });
        this.btn_save_bot.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity._saveBotSetting(setelanFragmentActivity.et_token_bot.getText().toString(), SetelanFragmentActivity.this.et_chat_id.getText().toString());
            }
        });
        this.tv_title_set_about.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetelanFragmentActivity.this.ln_changelog_contain.isExpanded()) {
                    SetelanFragmentActivity.this.ln_changelog_contain.collapse();
                } else {
                    SetelanFragmentActivity.this.ln_changelog_contain.expand();
                }
            }
        });
    }

    private void initializeLogic() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {             @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                SetelanFragmentActivity.this.ln_island_contain.setExpansion(false);
                SetelanFragmentActivity.this.ln_island_contain.setDuration(350);
                SetelanFragmentActivity.this.ln_island_contain.setOrientation(1);
                SetelanFragmentActivity.this.ln_random_contain.setExpansion(false);
                SetelanFragmentActivity.this.ln_random_contain.setDuration(750);
                SetelanFragmentActivity.this.ln_random_contain.setOrientation(1);
                SetelanFragmentActivity.this.ln_convert_contain.setExpansion(false);
                SetelanFragmentActivity.this.ln_convert_contain.setDuration(350);
                SetelanFragmentActivity.this.ln_convert_contain.setOrientation(1);
                SetelanFragmentActivity.this.ln_changelog_contain.setExpansion(false);
                SetelanFragmentActivity.this.ln_changelog_contain.setDuration(350);
                SetelanFragmentActivity.this.ln_changelog_contain.setOrientation(1);
                SetelanFragmentActivity.this.ln_bot_contain.setExpansion(false);
                SetelanFragmentActivity.this.ln_bot_contain.setDuration(350);
                SetelanFragmentActivity.this.ln_bot_contain.setOrientation(1);
            }
        });
        _firstSetUI();
        this.group_margin.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {             @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
            public void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z) {
                if (z) {
                    materialButtonToggleGroup.clearChecked();
                }
            }
        });
        this.group_scale.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {             @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
            public void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z) {
                if (z) {
                    materialButtonToggleGroup.clearChecked();
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    public void _firstSetUI() {
        new File("/data/user/0/" + requireActivity().getPackageName() + "/app_HOME/").mkdirs();
        int i = this.pref.getInt("width_shape", 80);
        this.width_value = i;
        this.btn_scale.setText(String.valueOf(i));
        int i2 = this.pref.getInt("margin_shape", 90);
        this.margin_value = i2;
        this.btn_margin.setText(String.valueOf(i2));
        this.group_address.setSingleSelection(true);
        this.group_name.setSingleSelection(true);
        this.ln_sett.setVerticalScrollBarEnabled(false);
        this.ln_island_contain.setVisibility(8);
        this.ln_random_contain.setVisibility(8);
        this.tv_uname.setText(this.prefuser.getString("emanresu", "XKatrina"));
        this.tv_umail.setText(this.prefuser.getString("liamresu", ""));
        if (this.prefrandom.getString("s_location_address_code", "").equals("")) {
            this.s_location_address_code = "b";
            this.prefrandom.edit().putString("s_location_address_code", this.s_location_address_code).commit();
            this.group_address.check(this.btn_address_belakang.getId());
        } else {
            String string = this.prefrandom.getString("s_location_address_code", "");
            this.s_location_address_code = string;
            if (string.equals("d")) {
                this.group_address.check(this.btn_address_depan.getId());
            } else if (this.s_location_address_code.equals("t")) {
                this.group_address.check(this.btn_address_tengah.getId());
            } else {
                this.group_address.check(this.btn_address_belakang.getId());
            }
        }
        if (this.prefrandom.getString("s_code_address", "").equals("")) {
            this.s_code_address = "";
            this.prefrandom.edit().putString("s_code_address", this.s_code_address).commit();
        } else {
            this.s_code_address = this.prefrandom.getString("s_code_address", "");
        }
        this.et_code_address.setText(this.s_code_address);
        if (this.prefrandom.getString("s_total_address", "").equals("0") || this.prefrandom.getString("s_total_address", "").equals("")) {
            this.s_total_address = "4";
            this.prefrandom.edit().putString("s_total_address", this.s_total_address).commit();
        } else {
            this.s_total_address = this.prefrandom.getString("s_total_address", "");
        }
        this.et_total_address.setText(this.s_total_address);
        if (this.prefrandom.getString("s_location_name_code", "").equals("")) {
            this.s_location_name_code = "d";
            this.prefrandom.edit().putString("s_location_name_code", this.s_location_name_code).commit();
            this.tv_loc_code_name.setText("Depan");
        } else {
            String string2 = this.prefrandom.getString("s_location_name_code", "");
            this.s_location_name_code = string2;
            if (string2.equals("d")) {
                this.tv_loc_code_name.setText("Depan");
            } else {
                this.tv_loc_code_name.setText("Belakang");
            }
        }
        if (this.prefrandom.getString("s_code_name", "").equals("")) {
            this.s_code_name = "";
            this.prefrandom.edit().putString("s_code_name", this.s_code_name).commit();
        } else {
            this.s_code_name = this.prefrandom.getString("s_code_name", "");
        }
        this.et_code_name.setText(this.s_code_name);
        if (this.prefrandom.getString("s_total_name", "").equals("")) {
            this.s_total_name = ExifInterface.GPS_MEASUREMENT_2D;
            this.prefrandom.edit().putString("s_total_name", this.s_total_name).commit();
            this.group_name.check(this.btn_nama_dua.getId());
        } else {
            String string3 = this.prefrandom.getString("s_total_name", "");
            this.s_total_name = string3;
            if (string3.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                this.group_name.check(this.btn_nama_dua.getId());
            } else {
                this.group_name.check(this.btn_nama_tiga.getId());
            }
        }
        if (this.prefrandom.getString("s_code_email", "").equals("")) {
            this.s_code_email = "";
            this.prefrandom.edit().putString("s_code_email", this.s_code_email).commit();
        } else {
            this.s_code_email = this.prefrandom.getString("s_code_email", "");
        }
        this.et_code_email.setText(this.s_code_email);
        if (this.prefrandom.getString("s_code_password", "").equals("")) {
            this.s_code_password = "Password belum disetting";
            this.prefrandom.edit().putString("s_code_password", this.s_code_password).commit();
        } else {
            this.s_code_password = this.prefrandom.getString("s_code_password", "");
        }
        this.tv_sampel_password.setText(this.s_code_password);
        this.et_code_password.setText("");
        if (this.prefuser.getString("token_bot", "").equals("")) {
            this.et_token_bot.setText("");
        } else {
            this.et_token_bot.setText(this.prefuser.getString("token_bot", ""));
            if (this.prefuser.getString("chat_id", "").equals("")) {
                this.et_chat_id.setText("");
            } else {
                this.et_chat_id.setText(this.prefuser.getString("chat_id", ""));
            }
        }
        this.ln_island_contain.setExpansion(false);
        this.ln_island_contain.setDuration(350);
        this.ln_island_contain.setOrientation(1);
        this.ln_random_contain.setExpansion(false);
        this.ln_random_contain.setDuration(750);
        this.ln_random_contain.setOrientation(1);
        this.ln_convert_contain.setExpansion(false);
        this.ln_convert_contain.setDuration(350);
        this.ln_convert_contain.setOrientation(1);
        this.ln_changelog_contain.setExpansion(false);
        this.ln_changelog_contain.setDuration(350);
        this.ln_changelog_contain.setOrientation(1);
        this.ln_bot_contain.setExpansion(false);
        this.ln_bot_contain.setDuration(350);
        this.ln_bot_contain.setOrientation(1);
        Markwon.builder(requireContext()).build().setMarkdown(this.tv_about, LocalizedAssets.loadMarkdown(requireContext(), "xkatrina.md"));
    }

    public void _previewSaveAddress() {
        this.prefrandom.edit().putString("s_location_address_code", this.s_location_address_code).commit();
        this.prefrandom.edit().putString("s_code_address", this.s_code_address).commit();
        if (this.s_total_address.equals("0")) {
            this.prefrandom.edit().putString("s_total_address", "4").commit();
        } else {
            this.prefrandom.edit().putString("s_total_address", this.s_total_address).commit();
        }
        if (this.ls_random_address.size() == 0) {
            try {
                this.ls_random_address = new ArrayList<>(Arrays.asList(SketchwareUtil.copyFromInputStream(getContext().getAssets().open("a.json")).split(",")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.s_front_address = new String[]{"Jalan", "Jln", "Jl", "Perumahan", "Perum", "Griya", "Toko", "Warung", "Rumah", "Gang", "Gg", "RT", "RW"}[new Random().nextInt(13)];
        this.s_result_address = "";
        this.n_total_address = Double.parseDouble(this.prefrandom.getString("s_total_address", ""));
        for (int i = 0; i < ((int) this.n_total_address); i++) {
            String str = this.s_result_address;
            ArrayList<String> arrayList = this.ls_random_address;
            this.s_result_address = str.concat(" ".concat(arrayList.get(SketchwareUtil.getRandom(0, arrayList.size()))));
        }
        if (this.prefrandom.getString("s_code_address", "").equals("")) {
            this.s_result_address = this.s_front_address.concat(this.s_result_address);
        } else if (this.prefrandom.getString("s_location_address_code", "").equals("d")) {
            this.s_result_address = this.s_front_address.concat(" ".concat(this.prefrandom.getString("s_code_address", "").concat(this.s_result_address)));
        } else if (this.prefrandom.getString("s_location_address_code", "").equals("t")) {
            this.s_insert = " ".concat(this.prefrandom.getString("s_code_address", "").concat(" "));
            int length = this.s_result_address.length() / 2;
            this.s_result_address = this.s_front_address.concat(String.valueOf(this.s_result_address.substring(0, length)) + this.s_insert + this.s_result_address.substring(length));
        } else {
            this.s_result_address = this.s_front_address.concat(this.s_result_address.concat(" ".concat(this.prefrandom.getString("s_code_address", ""))));
        }
        this.tv_sampel_address.setText(this.s_result_address);
    }

    public void _previewSaveName() {
        this.prefrandom.edit().putString("s_location_name_code", this.s_location_name_code).commit();
        this.prefrandom.edit().putString("s_code_name", this.s_code_name).commit();
        this.prefrandom.edit().putString("s_total_name", this.s_total_name).commit();
        if (this.ls_random_name.size() == 0) {
            try {
                this.ls_random_name = new ArrayList<>(Arrays.asList(SketchwareUtil.copyFromInputStream(getContext().getAssets().open("b.json")).split(",")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.s_result_name = "";
        this.n_total_name = Double.parseDouble(this.prefrandom.getString("s_total_name", ""));
        for (int i = 0; i < ((int) this.n_total_name); i++) {
            String str = this.s_result_name;
            ArrayList<String> arrayList = this.ls_random_name;
            this.s_result_name = str.concat(" ".concat(arrayList.get(SketchwareUtil.getRandom(0, arrayList.size()))));
        }
        this.s_result_name = this.s_result_name.substring(1);
        if (!this.prefrandom.getString("s_code_name", "").equals("")) {
            if (this.prefrandom.getString("s_location_name_code", "").equals("d")) {
                this.s_result_name = this.s_result_name.substring(this.s_result_name.indexOf(" ") + 1);
                this.s_result_name = this.prefrandom.getString("s_code_name", "").concat(" ".concat(this.s_result_name));
            } else {
                String strSubstring = this.s_result_name.substring(0, this.s_result_name.lastIndexOf(" "));
                this.s_result_name = strSubstring;
                this.s_result_name = strSubstring.concat(" ".concat(this.prefrandom.getString("s_code_name", "")));
            }
        }
        this.tv_sampel_name.setText(this.s_result_name);
    }

    public void _previewSaveEmail() {
        this.prefrandom.edit().putString("s_code_email", this.s_code_email).commit();
        if (this.ls_random_name.size() == 0) {
            try {
                this.ls_random_name = new ArrayList<>(Arrays.asList(SketchwareUtil.copyFromInputStream(getContext().getAssets().open("b.json")).split(",")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayList<String> arrayList = this.ls_random_name;
        String str = arrayList.get(SketchwareUtil.getRandom(0, arrayList.size()));
        this.s_result_email = str;
        String strConcat = str.concat(String.valueOf(SketchwareUtil.getRandom(99, 9999)));
        this.s_result_email = strConcat;
        String strConcat2 = strConcat.concat(this.prefrandom.getString("s_code_email", ""));
        this.s_result_email = strConcat2;
        this.tv_sampel_email.setText(strConcat2);
    }

    public class lv_folder_pickerAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public lv_folder_pickerAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = SetelanFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(C0978R.layout.backup_dialog_listview_folder_picker, (ViewGroup) null);
            }
            TextView textView = (TextView) view.findViewById(C0978R.id.tv_01);
            ImageView imageView = (ImageView) view.findViewById(C0978R.id.im_01);
            textView.setText(Uri.parse(((HashMap) SetelanFragmentActivity.this.lm_folder_picker.get(i)).get("folder").toString()).getLastPathSegment());
            if (FileUtil.isDirectory(((HashMap) SetelanFragmentActivity.this.lm_folder_picker.get(i)).get("folder").toString())) {
                imageView.setImageResource(C0978R.drawable.ic_folder);
            }
            if (FileUtil.isFile(((HashMap) SetelanFragmentActivity.this.lm_folder_picker.get(i)).get("folder").toString())) {
                imageView.setImageResource(C0978R.drawable.ic_file);
            }
            return view;
        }
    }

    public class lv_unix_backupAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public lv_unix_backupAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = SetelanFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(C0978R.layout.listview_unix_backup, (ViewGroup) null);
            }
            TextView textView = (TextView) view.findViewById(C0978R.id.tv_unix_backup);
            ImageView imageView = (ImageView) view.findViewById(C0978R.id.im_unix_backup);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(C0978R.id.ln_unix_backup);
            textView.setText(((HashMap) SetelanFragmentActivity.this.lm_unix_backup.get(i)).get("apppackage").toString());
            try {
                imageView.setImageDrawable(SetelanFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(this._data.get(i).get("apppackage").toString()));
            } catch (PackageManager.NameNotFoundException unused) {
                imageView.setImageResource(C0978R.drawable.ic_application);
            }
            linearLayout.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SetelanFragmentActivity.this.f866c = Calendar.getInstance();
                    SetelanFragmentActivity.this.s_targetpath = "/storage/emulated/0/XKatrina".concat(new SimpleDateFormat("HHmmss").format(SetelanFragmentActivity.this.f866c.getTime()).concat("/"));
                    SetelanFragmentActivity.this.s_package = ((HashMap) SetelanFragmentActivity.this.lm_unix_backup.get(i)).get("apppackage").toString();
                    SetelanFragmentActivity.this._onConvertProses();
                    SetelanFragmentActivity.this.CONVERTDIALOG.dismiss();
                }
            });
            return view;
        }
    }

    private void setAppFont(ViewGroup viewGroup, Typeface typeface) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                setAppFont((ViewGroup) childAt, typeface);
            } else if (childAt instanceof TextView) {
                ((TextView) childAt).setTypeface(typeface);
            }
        }
    }

    private String loadMarkdownFromAssets(String str) {
        try {
            InputStream inputStreamOpen = getContext().getAssets().open(str);
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            inputStreamOpen.close();
            return new String(bArr);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void _onTaskFolderPicker() {
        MyREADFOLDER myREADFOLDER = this.myREADFOLDER;
        if (myREADFOLDER != null && myREADFOLDER.isRunning) {
            this.myREADFOLDER.cancelREADFOLDERTask();
        }
        MyREADFOLDER myREADFOLDER2 = new MyREADFOLDER();
        this.myREADFOLDER = myREADFOLDER2;
        myREADFOLDER2.execute(new Void[0]);
    }

    public class MyREADFOLDER extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyREADFOLDER() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            SetelanFragmentActivity.this.lm_folder_picker.clear();
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            FileUtil.listDir(SetelanFragmentActivity.this.s_folder_picker, SetelanFragmentActivity.this.ls_folder_picker);
            Collections.sort(SetelanFragmentActivity.this.ls_folder_picker, new Comparator<String>() {                 @Override // java.util.Comparator
                public int compare(String str, String str2) {
                    if (str == str2) {
                        return 0;
                    }
                    if (FileUtil.isDirectory(str) && FileUtil.isFile(str2)) {
                        return -1;
                    }
                    if (FileUtil.isFile(str) && FileUtil.isDirectory(str2)) {
                        return 1;
                    }
                    return str.compareToIgnoreCase(str2);
                }
            });
            SetelanFragmentActivity.this.n_pos = 0.0d;
            for (int i = 0; i < SetelanFragmentActivity.this.ls_folder_picker.size(); i++) {
                HashMap map = new HashMap();
                map.put("folder", SetelanFragmentActivity.this.ls_folder_picker.get((int) SetelanFragmentActivity.this.n_pos));
                SetelanFragmentActivity.this.lm_folder_picker.add(map);
                SetelanFragmentActivity.this.n_pos += 1.0d;
            }
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            this.isRunning = false;
            SetelanFragmentActivity.this.b_folder_scan = true;
        }

        public void cancelREADFOLDERTask() {
            cancel(true);
        }
    }

    public void _showFolderPicker() {
        showSHOWFOLDER();
    }

    private void showSHOWFOLDER() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_dialog_folder_picker, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        final ListView listView = (ListView) viewInflate.findViewById(C0978R.id.lv_folder_picker);
        final TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_01);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_cancel);
        Button button2 = (Button) viewInflate.findViewById(C0978R.id.btn_oke);
        Button button3 = (Button) viewInflate.findViewById(C0978R.id.btn_back);
        final LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_folder);
        final LinearLayout linearLayout2 = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_loading);
        this.b_folder_scan = false;
        this.s_folder_picker = FileUtil.getExternalStorageDir();
        linearLayout.setVisibility(8);
        linearLayout2.setVisibility(0);
        _onTaskFolderPicker();
        Runnable runnable = new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                if (SetelanFragmentActivity.this.b_folder_scan) {
                    SetelanFragmentActivity.this.folderone.removeCallbacks(SetelanFragmentActivity.this.runnablefolderone);
                    SetelanFragmentActivity.this.b_folder_scan = false;
                    listView.setDivider(null);
                    listView.setDividerHeight(0);
                    ListView listView2 = listView;
                    SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                    listView2.setAdapter((ListAdapter) setelanFragmentActivity.new lv_folder_pickerAdapter(setelanFragmentActivity.lm_folder_picker));
                    ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                    listView.setVerticalScrollBarEnabled(false);
                    textView.setText(SetelanFragmentActivity.this.s_folder_picker);
                    linearLayout.setVisibility(0);
                    linearLayout2.setVisibility(8);
                    return;
                }
                SetelanFragmentActivity.this.folderone.postDelayed(SetelanFragmentActivity.this.runnablefolderone, 100L);
            }
        };
        this.runnablefolderone = runnable;
        this.folderone.postDelayed(runnable, 0L);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {             @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (FileUtil.isDirectory((String) SetelanFragmentActivity.this.ls_folder_picker.get(i))) {
                    File file = new File((String) SetelanFragmentActivity.this.ls_folder_picker.get(i));
                    if (file.exists() && file.isDirectory()) {
                        File[] fileArrListFiles = file.listFiles();
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                            setelanFragmentActivity.s_folder_picker = (String) setelanFragmentActivity.ls_folder_picker.get(i);
                            linearLayout.setVisibility(8);
                            linearLayout2.setVisibility(0);
                            SetelanFragmentActivity.this._onTaskFolderPicker();
                            SetelanFragmentActivity setelanFragmentActivity2 = SetelanFragmentActivity.this;
                            final ListView listView2 = listView;
                            final TextView textView2 = textView;
                            final LinearLayout linearLayout3 = linearLayout;
                            final LinearLayout linearLayout4 = linearLayout2;
                            setelanFragmentActivity2.runnablefoldertwo = new Runnable() {                                 @Override // java.lang.Runnable
                                public void run() {
                                    if (SetelanFragmentActivity.this.b_folder_scan) {
                                        SetelanFragmentActivity.this.foldertwo.removeCallbacks(SetelanFragmentActivity.this.runnablefoldertwo);
                                        SetelanFragmentActivity.this.b_folder_scan = false;
                                        listView2.setDivider(null);
                                        listView2.setDividerHeight(0);
                                        listView2.setAdapter((ListAdapter) SetelanFragmentActivity.this.new lv_folder_pickerAdapter(SetelanFragmentActivity.this.lm_folder_picker));
                                        ((BaseAdapter) listView2.getAdapter()).notifyDataSetChanged();
                                        listView2.setVerticalScrollBarEnabled(false);
                                        textView2.setText(SetelanFragmentActivity.this.s_folder_picker);
                                        linearLayout3.setVisibility(0);
                                        linearLayout4.setVisibility(8);
                                        return;
                                    }
                                    SetelanFragmentActivity.this.foldertwo.postDelayed(SetelanFragmentActivity.this.runnablefoldertwo, 100L);
                                }
                            };
                            SetelanFragmentActivity.this.foldertwo.postDelayed(SetelanFragmentActivity.this.runnablefoldertwo, 0L);
                            return;
                        }
                        SketchwareUtil.showMessage(SetelanFragmentActivity.this.getContext().getApplicationContext(), getString(C0978R.string.toast_folder_kosong));
                        return;
                    }
                    return;
                }
                SketchwareUtil.showMessage(SetelanFragmentActivity.this.getContext().getApplicationContext(), getString(C0978R.string.toast_hanya_bisa_pilih_folder));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SetelanFragmentActivity.this.s_folder_picker.equals("/storage/emulated/0")) {
                    SketchwareUtil.showMessage(SetelanFragmentActivity.this.getContext().getApplicationContext(), getString(C0978R.string.toast_kamu_sudah_sampe_di_awal));
                    return;
                }
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity.s_folder_picker = setelanFragmentActivity.s_folder_picker.substring(0, (SetelanFragmentActivity.this.s_folder_picker.length() - Uri.parse(SetelanFragmentActivity.this.s_folder_picker).getLastPathSegment().length()) - 1);
                linearLayout.setVisibility(8);
                linearLayout2.setVisibility(0);
                SetelanFragmentActivity.this._onTaskFolderPicker();
                SetelanFragmentActivity setelanFragmentActivity2 = SetelanFragmentActivity.this;
                final ListView listView2 = listView;
                final TextView textView2 = textView;
                final LinearLayout linearLayout3 = linearLayout;
                final LinearLayout linearLayout4 = linearLayout2;
                setelanFragmentActivity2.runnablefoldertri = new Runnable() {                     @Override // java.lang.Runnable
                    public void run() {
                        if (SetelanFragmentActivity.this.b_folder_scan) {
                            SetelanFragmentActivity.this.foldertri.removeCallbacks(SetelanFragmentActivity.this.runnablefoldertri);
                            SetelanFragmentActivity.this.b_folder_scan = false;
                            listView2.setDivider(null);
                            listView2.setDividerHeight(0);
                            listView2.setAdapter((ListAdapter) SetelanFragmentActivity.this.new lv_folder_pickerAdapter(SetelanFragmentActivity.this.lm_folder_picker));
                            ((BaseAdapter) listView2.getAdapter()).notifyDataSetChanged();
                            listView2.setVerticalScrollBarEnabled(false);
                            textView2.setText(SetelanFragmentActivity.this.s_folder_picker);
                            linearLayout3.setVisibility(0);
                            linearLayout4.setVisibility(8);
                            return;
                        }
                        SetelanFragmentActivity.this.foldertri.postDelayed(SetelanFragmentActivity.this.runnablefoldertri, 100L);
                    }
                };
                SetelanFragmentActivity.this.foldertri.postDelayed(SetelanFragmentActivity.this.runnablefoldertri, 0L);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SetelanFragmentActivity.this.SHOWFOLDER.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SetelanFragmentActivity.this.btn_choose_folder.setText(SetelanFragmentActivity.this.s_folder_picker);
                SetelanFragmentActivity.this.SHOWFOLDER.dismiss();
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.SHOWFOLDER = alertDialogCreate;
        alertDialogCreate.show();
    }

    public void _onConvertSearch(String str, String str2) {
        File[] fileArrListFiles;
        File file = new File(str);
        if (!file.exists() || (fileArrListFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isFile() && file2.getName().endsWith(str2)) {
                this.ls_all_file_backup.add(file2.getAbsolutePath());
            } else if (file2.isDirectory()) {
                _onConvertSearch(file2.getAbsolutePath(), str2);
            }
        }
    }

    public void _onConvertProses() {
        MyCONVERT myCONVERT = this.myCONVERT;
        if (myCONVERT != null && myCONVERT.isRunning) {
            this.myCONVERT.cancelCONVERTTask();
        }
        MyCONVERT myCONVERT2 = new MyCONVERT();
        this.myCONVERT = myCONVERT2;
        myCONVERT2.execute(new Void[0]);
    }

    public class MyCONVERT extends AsyncTask<Void, Integer, Void> {
        Button btn_close;
        AlertDialog dialog;
        private boolean isRunning = false;
        LinearLayout ln_pbar;
        ProgressBar pbar_unix;
        TextView tv_message;
        TextView tv_size;
        TextView tv_total;

        public MyCONVERT() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(SetelanFragmentActivity.this.requireContext());
            View viewInflate = SetelanFragmentActivity.this.getActivity().getLayoutInflater().inflate(C0978R.layout.convert_progress_view, (ViewGroup) null);
            this.pbar_unix = (ProgressBar) viewInflate.findViewById(C0978R.id.pbar_unix);
            this.tv_size = (TextView) viewInflate.findViewById(C0978R.id.tv_size);
            this.tv_total = (TextView) viewInflate.findViewById(C0978R.id.tv_total);
            this.tv_message = (TextView) viewInflate.findViewById(C0978R.id.tv_message);
            this.btn_close = (Button) viewInflate.findViewById(C0978R.id.btn_close);
            this.ln_pbar = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_pbar);
            materialAlertDialogBuilder.setView(viewInflate);
            materialAlertDialogBuilder.setCancelable(false);
            AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
            this.dialog = alertDialogCreate;
            alertDialogCreate.show();
            this.tv_message.setText("Sedang mengkonversi...");
            this.btn_close.setVisibility(8);
            if (!FileUtil.isExistFile(SetelanFragmentActivity.this.s_targetpath)) {
                FileUtil.makeDir(SetelanFragmentActivity.this.s_targetpath);
            }
            if (FileUtil.isExistFile(SetelanFragmentActivity.this.s_targetpath.concat(SetelanFragmentActivity.this.s_package))) {
                return;
            }
            FileUtil.makeDir(SetelanFragmentActivity.this.s_targetpath.concat(SetelanFragmentActivity.this.s_package));
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            SetelanFragmentActivity.this.ls_convert_backup.clear();
            for (String str : SetelanFragmentActivity.this.ls_all_file_backup) {
                if (str.contains(SetelanFragmentActivity.this.s_package)) {
                    SetelanFragmentActivity.this.ls_convert_backup.add(str);
                }
            }
            Collections.sort(SetelanFragmentActivity.this.ls_convert_backup);
            SetelanFragmentActivity.this.f867n = 0.0d;
            SetelanFragmentActivity.this.n_folder = 10001.0d;
            for (int i = 0; i < SetelanFragmentActivity.this.ls_convert_backup.size(); i++) {
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity.s_loop_tar = (String) setelanFragmentActivity.ls_convert_backup.get((int) SetelanFragmentActivity.this.f867n);
                SetelanFragmentActivity setelanFragmentActivity2 = SetelanFragmentActivity.this;
                setelanFragmentActivity2.s_loop_prop = setelanFragmentActivity2.s_loop_tar.replace(".tar.gz", ".properties");
                SetelanFragmentActivity setelanFragmentActivity3 = SetelanFragmentActivity.this;
                setelanFragmentActivity3.s_loop_tar_name = Uri.parse(setelanFragmentActivity3.s_loop_tar).getLastPathSegment();
                SetelanFragmentActivity setelanFragmentActivity4 = SetelanFragmentActivity.this;
                setelanFragmentActivity4.s_loop_prop_name = setelanFragmentActivity4.s_loop_tar_name.replace(".tar.gz", ".json");
                SetelanFragmentActivity setelanFragmentActivity5 = SetelanFragmentActivity.this;
                setelanFragmentActivity5.s_folder = String.valueOf((long) setelanFragmentActivity5.n_folder);
                SetelanFragmentActivity setelanFragmentActivity6 = SetelanFragmentActivity.this;
                setelanFragmentActivity6.s_folder = setelanFragmentActivity6.s_folder.substring(2);
                SetelanFragmentActivity setelanFragmentActivity7 = SetelanFragmentActivity.this;
                setelanFragmentActivity7.s_finaltarget = setelanFragmentActivity7.s_targetpath.concat(SetelanFragmentActivity.this.s_package.concat("/".concat(SetelanFragmentActivity.this.s_folder.concat("/"))));
                FileUtil.makeDir(SetelanFragmentActivity.this.s_finaltarget);
                FileUtil.copyFile(SetelanFragmentActivity.this.s_loop_tar, SetelanFragmentActivity.this.s_finaltarget.concat(SetelanFragmentActivity.this.s_loop_tar_name));
                if (FileUtil.isExistFile(SetelanFragmentActivity.this.s_loop_prop)) {
                    SetelanFragmentActivity.this.ls_file_properties.clear();
                    SetelanFragmentActivity setelanFragmentActivity8 = SetelanFragmentActivity.this;
                    setelanFragmentActivity8.s_file_properties = FileUtil.readFile(setelanFragmentActivity8.s_loop_prop);
                    SetelanFragmentActivity.this.ls_file_properties = new ArrayList(Arrays.asList(SetelanFragmentActivity.this.s_file_properties.split("\n")));
                    SetelanFragmentActivity.this.n_properties = 0.0d;
                    for (int i2 = 0; i2 < SetelanFragmentActivity.this.ls_file_properties.size(); i2++) {
                        if (((String) SetelanFragmentActivity.this.ls_file_properties.get((int) SetelanFragmentActivity.this.n_properties)).contains("personal_note")) {
                            SetelanFragmentActivity setelanFragmentActivity9 = SetelanFragmentActivity.this;
                            setelanFragmentActivity9.s_file_note = (String) setelanFragmentActivity9.ls_file_properties.get((int) SetelanFragmentActivity.this.n_properties);
                            SetelanFragmentActivity setelanFragmentActivity10 = SetelanFragmentActivity.this;
                            setelanFragmentActivity10.s_file_note = setelanFragmentActivity10.s_file_note.replace("personal_note=", "");
                        }
                        if (((String) SetelanFragmentActivity.this.ls_file_properties.get((int) SetelanFragmentActivity.this.n_properties)).contains("GMT+")) {
                            SetelanFragmentActivity setelanFragmentActivity11 = SetelanFragmentActivity.this;
                            setelanFragmentActivity11.s_file_date = (String) setelanFragmentActivity11.ls_file_properties.get((int) SetelanFragmentActivity.this.n_properties);
                            SetelanFragmentActivity setelanFragmentActivity12 = SetelanFragmentActivity.this;
                            setelanFragmentActivity12.s_file_date = setelanFragmentActivity12.s_file_date.replace("#", "");
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEEE dd MMMM yyyy HH:mm:ss", new Locale("id", "ID"));
                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
                            try {
                                SetelanFragmentActivity.this.s_file_date = simpleDateFormat2.format(simpleDateFormat.parse(SetelanFragmentActivity.this.s_file_date));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                SetelanFragmentActivity.this.s_file_unix = String.valueOf(new SimpleDateFormat("EEEE dd MMMM yyyy HH:mm:ss", new Locale("id", "ID")).parse(SetelanFragmentActivity.this.s_file_date).getTime() / 1000);
                            } catch (ParseException e2) {
                                e2.printStackTrace();
                            }
                        }
                        SetelanFragmentActivity.this.n_properties += 1.0d;
                    }
                    SetelanFragmentActivity.this.m_json.clear();
                    SetelanFragmentActivity.this.m_json = new HashMap();
                    SetelanFragmentActivity.this.m_json.put("NOTE", SetelanFragmentActivity.this.s_file_note);
                    SetelanFragmentActivity.this.m_json.put("DATE", SetelanFragmentActivity.this.s_file_date);
                    SetelanFragmentActivity.this.m_json.put("UNIX", SetelanFragmentActivity.this.s_file_unix);
                    SetelanFragmentActivity.this.m_json.put("system.prop", "false");
                    SetelanFragmentActivity.this.m_json.put("settings_ssaid", "false");
                    SetelanFragmentActivity.this.m_json.put("DEVICE", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("MODEL", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("PRODUCT", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("MANUFACTURER", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("BRAND", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("SDK", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("BOARD", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("BOOT", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("DISPLAY", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("FINGERPRINT", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("HARDWARE", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("BUILDID", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("HOST", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("USER", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("RELEASE", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("INCREMENTAL", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("USERAGENT", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("HTTPAGENT", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("RADIOVERSION", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("TIME", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("MARK", "false");
                    SetelanFragmentActivity.this.m_json.put("COLOR", "#FFFFFFFF");
                    SetelanFragmentActivity.this.m_json.put("ANDROIDID", "NOT ETERNAL");
                    SetelanFragmentActivity.this.m_json.put("BLUETHOOTNAME", "NOT ETERNAL");
                    SetelanFragmentActivity.this.m_json.put("DEVICENAME", "NOT ETERNAL");
                    SetelanFragmentActivity.this.m_json.put("IMEI", "NOT ETERNAL");
                    SetelanFragmentActivity.this.m_json.put("SERIAL", "NOT ETERNAL");
                    SetelanFragmentActivity.this.m_json.put("SERIAL2", "NOT ETERNAL");
                    SetelanFragmentActivity.this.s_json = new Gson().toJson(SetelanFragmentActivity.this.m_json);
                    FileUtil.writeFile(SetelanFragmentActivity.this.s_finaltarget.concat(SetelanFragmentActivity.this.s_loop_prop_name), SetelanFragmentActivity.this.s_json);
                }
                SetelanFragmentActivity.this.f867n += 1.0d;
                SetelanFragmentActivity.this.n_folder += 1.0d;
                if (SetelanFragmentActivity.this.f867n > 0.0d) {
                    publishProgress(Integer.valueOf((int) SetelanFragmentActivity.this.f867n));
                }
            }
            return null;
        }

                @Override // android.os.AsyncTask
        public void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate(numArr);
            this.pbar_unix.setMax(SetelanFragmentActivity.this.ls_convert_backup.size());
            this.pbar_unix.setProgress((int) SetelanFragmentActivity.this.f867n);
            this.tv_size.setText(String.valueOf((long) SetelanFragmentActivity.this.f867n));
            this.tv_total.setText(String.valueOf(SetelanFragmentActivity.this.ls_convert_backup.size()));
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r4) {
            this.isRunning = false;
            this.btn_close.setVisibility(0);
            this.ln_pbar.setVisibility(8);
            this.tv_message.setText("Selesai mengkonversi ".concat(String.valueOf(SetelanFragmentActivity.this.ls_convert_backup.size()).concat("file backup\nDisimpan di ".concat(Uri.parse(SetelanFragmentActivity.this.s_targetpath).getLastPathSegment()))));
            this.btn_close.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MyCONVERT.this.dialog.dismiss();
                }
            });
        }

        public void cancelCONVERTTask() {
            cancel(true);
        }
    }

    public void _showDialogConvert() {
        showCONVERTDIALOG();
    }

    private void showCONVERTDIALOG() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.listview_convert_dialog, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        ListView listView = (ListView) viewInflate.findViewById(C0978R.id.lv_unix_backup);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_unix_close);
        String string = this.btn_choose_folder.getText().toString();
        this.s_path = string;
        this.s_ext = ".tar.gz";
        _onConvertSearch(string, ".tar.gz");
        Collections.sort(this.ls_unix_backup);
        this.f867n = 0.0d;
        for (int i = 0; i < this.ls_all_file_backup.size(); i++) {
            String lastPathSegment = Uri.parse(this.ls_all_file_backup.get((int) this.f867n)).getLastPathSegment();
            this.s_sort = lastPathSegment;
            String strSubstring = lastPathSegment.substring(0, lastPathSegment.indexOf("-"));
            this.s_sort = strSubstring;
            this.ls_unix_backup.add(strSubstring);
            this.f867n += 1.0d;
        }
        HashSet hashSet = new HashSet(this.ls_unix_backup);
        this.ls_unix_backup.clear();
        ArrayList arrayList = new ArrayList(hashSet);
        this.lm_unix_backup.clear();
        this.f867n = 0.0d;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            HashMap<String, Object> map = new HashMap<>();
            this.m_unix = map;
            map.put("apppackage", arrayList.get((int) this.f867n));
            this.lm_unix_backup.add(this.m_unix);
            this.f867n += 1.0d;
        }
        listView.setDivider(null);
        listView.setDividerHeight(0);
        listView.setAdapter((ListAdapter) new lv_unix_backupAdapter(this.lm_unix_backup));
        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
        listView.setVerticalScrollBarEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SetelanFragmentActivity.this.CONVERTDIALOG.dismiss();
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.CONVERTDIALOG = alertDialogCreate;
        alertDialogCreate.show();
    }

    public void _previewSavePassword() {
        this.prefrandom.edit().putString("s_code_password", this.et_code_password.getText().toString()).commit();
        this.tv_sampel_password.setText(this.prefrandom.getString("s_code_password", ""));
        this.et_code_password.setText(this.prefrandom.getString("s_code_password", ""));
    }

    public void _saveBotSetting(String str, String str2) {
        if (str.length() >= 5 && str2.length() >= 5) {
            this.prefuser.edit().putString("token_bot", str).commit();
            this.prefuser.edit().putString("chat_id", str2).commit();
            SketchwareUtil.showMessage(getContext().getApplicationContext(), getString(C0978R.string.toast_berhasil_disimpan));
            return;
        }
        SketchwareUtil.showMessage(getContext().getApplicationContext(), getString(C0978R.string.toast_ada_yang_salah));
    }

    public void _previewSaveTelepon() {
        String strConcat;
        this.prefrandom.edit().putString("s_code_telepon", this.et_code_telepon.getText().toString()).commit();
        Random random = new Random();
        String str = "";
        for (int i = 0; i < 12; i++) {
            str = String.valueOf(str) + String.valueOf(random.nextInt(10));
        }
        if (this.prefrandom.getString("s_code_telepon", "").equals("")) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("0852");
            arrayList.add("0853");
            arrayList.add("0811");
            arrayList.add("0812");
            arrayList.add("0813");
            arrayList.add("0821");
            arrayList.add("0822");
            arrayList.add("0851");
            arrayList.add("0814");
            arrayList.add("0815");
            arrayList.add("0816");
            arrayList.add("0855");
            arrayList.add("0856");
            arrayList.add("0857");
            arrayList.add("0858");
            arrayList.add("0896");
            arrayList.add("0895");
            arrayList.add("0897");
            arrayList.add("0898");
            arrayList.add("0899");
            arrayList.add("0817");
            arrayList.add("0818");
            arrayList.add("0819");
            arrayList.add("0859");
            arrayList.add("0877");
            arrayList.add("0878");
            arrayList.add("0813");
            arrayList.add("0832");
            arrayList.add("0833");
            arrayList.add("0838");
            arrayList.add("0881");
            arrayList.add("0882");
            arrayList.add("0883");
            arrayList.add("0884");
            arrayList.add("0885");
            arrayList.add("0886");
            arrayList.add("0887");
            arrayList.add("0888");
            arrayList.add("0889");
            Collections.shuffle(arrayList);
            strConcat = ((String) arrayList.get(new Random().nextInt(arrayList.size()))).concat(str);
        } else {
            strConcat = this.prefrandom.getString("s_code_telepon", "").concat(str);
        }
        this.tv_sampel_telepon.setText(strConcat.substring(0, 12));
        this.et_code_telepon.setText(this.prefrandom.getString("s_code_telepon", ""));
    }

}
