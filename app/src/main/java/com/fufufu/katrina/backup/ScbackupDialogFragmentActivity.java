package com.fufufu.katrina.backup;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.DialogFragment;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.topjohnwu.superuser.Shell;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ScbackupDialogFragmentActivity extends DialogFragment {
    private String advertisingId;
    private MaterialButton btn_add_rules;
    private Button btn_batal;
    private Button btn_close;
    private Button btn_oke;
    private LinearLayout ln_backup_add;
    private LinearLayout ln_base;
    private LinearLayout ln_bottom;
    private ChipGroup ln_chip_group;
    private LinearLayout ln_dialog;
    private LinearLayout ln_lottie;
    private LinearLayout ln_proses;
    private LinearLayout ln_proses_content;
    private LinearLayout ln_top_backup;
    private LottieAnimationView lottie1;
    private Chip m_custom;
    private Chip m_normal;
    private MaterialSwitch msc_prop;
    private MaterialSwitch msc_ssaid;
    private MaterialCardView mvc_base;
    private MyBackgroundAction myBackgroundAction;
    private SharedPreferences prefui;
    private Runnable runnableOnBackup;
    private TextInputLayout til_1;
    private TextInputLayout til_2;
    private TextInputLayout til_3;
    private AutoCompleteTextView tv_app;
    private TextView tv_desc;
    private AutoCompleteTextView tv_location;
    private AutoCompleteTextView tv_number;
    private TextView tv_proses_backup;
    private TextView tv_proses_title;
    private TextView tv_rebackup_title;
    private Vibrator vibrate;
    public final int REQ_CD_FP = 101;
    private boolean b_rebackup = false;
    private String s_cek_folder = "";
    private String s_backup_number = "";
    private String s_cat_eternal = "";
    private String s_prop_json = "";
    private boolean b_command = false;
    private HashMap<String, Object> mProp = new HashMap<>();
    private boolean b_custom = false;
    private HashMap<String, Object> m_export = new HashMap<>();
    private String s_scbase = "";
    private String s_exe1 = "";
    private String s_exe2 = "";
    private String s_exe3 = "";
    private String s_backup_name = "";
    private String s_json_loc = "";
    private String s_command = "";
    private String s_commandResult = "";
    private String s_extra = "";
    private String s_commandBase = "";
    private String userAgent = "";
    private ArrayList<HashMap<String, Object>> lm_picked = new ArrayList<>();

        private Intent f863fp = new Intent("android.intent.action.GET_CONTENT");
    private Intent intentcustom = new Intent();

        private Calendar f862c = Calendar.getInstance();
    private Intent intentFinish = new Intent();
    private Handler OnBackup = new Handler();

    public void _EXTRA() {
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C0978R.layout.scbackup_dialog_fragment, viewGroup, false);
        initialize(bundle, viewInflate);
        initializeLogic();
        return viewInflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.mvc_base = (MaterialCardView) view.findViewById(C0978R.id.mvc_base);
        this.ln_base = (LinearLayout) view.findViewById(C0978R.id.ln_base);
        this.ln_dialog = (LinearLayout) view.findViewById(C0978R.id.ln_dialog);
        this.ln_proses = (LinearLayout) view.findViewById(C0978R.id.ln_proses);
        this.tv_rebackup_title = (TextView) view.findViewById(C0978R.id.tv_rebackup_title);
        this.ln_top_backup = (LinearLayout) view.findViewById(C0978R.id.ln_top_backup);
        this.til_1 = (TextInputLayout) view.findViewById(C0978R.id.til_1);
        this.til_2 = (TextInputLayout) view.findViewById(C0978R.id.til_2);
        this.til_3 = (TextInputLayout) view.findViewById(C0978R.id.til_3);
        this.ln_backup_add = (LinearLayout) view.findViewById(C0978R.id.ln_backup_add);
        this.ln_bottom = (LinearLayout) view.findViewById(C0978R.id.ln_bottom);
        this.ln_chip_group = (ChipGroup) view.findViewById(C0978R.id.ln_chip_group);
        this.btn_add_rules = (MaterialButton) view.findViewById(C0978R.id.btn_add_rules);
        this.m_normal = (Chip) view.findViewById(C0978R.id.m_normal);
        this.m_custom = (Chip) view.findViewById(C0978R.id.m_custom);
        this.tv_number = (AutoCompleteTextView) view.findViewById(C0978R.id.tv_number);
        this.tv_location = (AutoCompleteTextView) view.findViewById(C0978R.id.tv_location);
        this.tv_app = (AutoCompleteTextView) view.findViewById(C0978R.id.tv_app);
        this.tv_desc = (TextView) view.findViewById(C0978R.id.tv_desc);
        this.msc_ssaid = (MaterialSwitch) view.findViewById(C0978R.id.msc_ssaid);
        this.msc_prop = (MaterialSwitch) view.findViewById(C0978R.id.msc_prop);
        this.btn_batal = (Button) view.findViewById(C0978R.id.btn_batal);
        this.btn_oke = (Button) view.findViewById(C0978R.id.btn_oke);
        this.tv_proses_title = (TextView) view.findViewById(C0978R.id.tv_proses_title);
        this.ln_proses_content = (LinearLayout) view.findViewById(C0978R.id.ln_proses_content);
        this.btn_close = (Button) view.findViewById(C0978R.id.btn_close);
        this.ln_lottie = (LinearLayout) view.findViewById(C0978R.id.ln_lottie);
        this.tv_proses_backup = (TextView) view.findViewById(C0978R.id.tv_proses_backup);
        this.lottie1 = (LottieAnimationView) view.findViewById(C0978R.id.lottie1);
        this.f863fp.setType("*/*");
        this.f863fp.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        this.prefui = getContext().getSharedPreferences("preferences_ui", 0);
        this.vibrate = (Vibrator) getContext().getSystemService("vibrator");
        this.btn_add_rules.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScbackupDialogFragmentActivity.this._onAddRules();
            }
        });
        this.msc_ssaid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScbackupDialogFragmentActivity.this.prefui.edit().putString("backup_plus_settings_ssaid", "true").commit();
                } else {
                    ScbackupDialogFragmentActivity.this.prefui.edit().putString("backup_plus_settings_ssaid", "false").commit();
                }
            }
        });
        this.msc_prop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScbackupDialogFragmentActivity.this.prefui.edit().putString("backup_plus_system_prop", "true").commit();
                } else {
                    ScbackupDialogFragmentActivity.this.prefui.edit().putString("backup_plus_system_prop", "false").commit();
                }
            }
        });
        this.btn_batal.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScbackupDialogFragmentActivity.this._finishDialog();
            }
        });
        this.btn_oke.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScbackupDialogFragmentActivity.this._onCreateBackup();
            }
        });
        this.btn_close.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScbackupDialogFragmentActivity.this._finishDialog();
            }
        });
    }

    private void initializeLogic() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.s_extra = arguments.getString("extrakey", "");
            _setFirstUI();
        } else {
            _finishDialog();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == -1) {
            ArrayList arrayList = new ArrayList();
            if (intent != null) {
                if (intent.getClipData() != null) {
                    for (int i3 = 0; i3 < intent.getClipData().getItemCount(); i3++) {
                        arrayList.add(FileUtil.convertUriToFilePath(getContext().getApplicationContext(), intent.getClipData().getItemAt(i3).getUri()));
                    }
                } else {
                    arrayList.add(FileUtil.convertUriToFilePath(getContext().getApplicationContext(), intent.getData()));
                }
            }
            try {
                ArrayList<HashMap<String, Object>> arrayList2 = (ArrayList) new Gson().fromJson(FileUtil.readFile((String) arrayList.get(0)), new TypeToken<ArrayList<HashMap<String, Object>>>() {                 }.getType());
                this.lm_picked = arrayList2;
                String string = arrayList2.get(0).get("title").toString();
                FileUtil.writeFile("/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/files/".concat(string.concat(".fu")))), this.lm_picked.get(0).get("config").toString());
                SketchwareUtil.showMessage(getContext().getApplicationContext(), "Berhasil menyimpan config");
            } catch (Exception e) {
                e.printStackTrace();
                SketchwareUtil.showMessage(getContext().getApplicationContext(), "Gagal menyimpan config");
            }
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            dialog.getWindow().setLayout(-1, -2);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        getDialog().setCancelable(false);
        _onCustomBackup();
    }

    public void _onPrepareProsesBackup() {
        this.f862c = Calendar.getInstance();
        String strConcat = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/files/".concat(this.prefui.getString("backup_app_package", "").concat(".fu"))));
        String strConcat2 = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/files/".concat("custom_backup".concat(".fu"))));
        this.s_scbase = this.s_commandBase.concat("\nonbackup");
        this.s_exe1 = "if tar -czf $filedata/filessaid.tar.gz $filessaid > /dev/null 2>&1; then\necho \"Backup settings_ssaid sukses\"\nelse\necho \"Backup settings_ssaid gagal\nkode : $?\"\nfi";
        this.s_exe2 = "if tar -czf $filedata/fileprop.tar.gz $fileprop > /dev/null 2>&1; then\necho \"Backup system.prop sukses\"\nelse\necho \"Backup system.prop gagal\nkode : $?\"\nfi";
        if (this.b_custom) {
            this.s_exe3 = "fufufufilelist=\"getconfig\"\nfufufuresultlist=\"resultconfig\"\n> \"$fufufuresultlist\"\n\nfiles=($(<\"$fufufufilelist\"))\n\nfor file in \"${files[@]}\"; do\n    if [ -e \"$file\" ]; then\n        echo \"$file\" >> \"$fufufuresultlist\"\n    fi\ndone\n\ntar -czf $thisbackuploc/$thisbackupname -T \"$fufufuresultlist\" > /dev/null 2>&1\n\n# Uji arsip untuk memeriksa apakah rusak\ntar -tf \"$thisbackuploc/$thisbackupname\" >/dev/null 2>&1\nif [ $? -eq 0 ]; then\necho \"Backup data $thisapp sukses\"\nelse\nrm -rf $thisbackuploc/$thisbackupname > /dev/null 2>&1\necho \"Backup data $thisapp gagal\nkode : $?\"\nfi\n> \"$fufufuresultlist\"";
            String strReplace = "fufufufilelist=\"getconfig\"\nfufufuresultlist=\"resultconfig\"\n> \"$fufufuresultlist\"\n\nfiles=($(<\"$fufufufilelist\"))\n\nfor file in \"${files[@]}\"; do\n    if [ -e \"$file\" ]; then\n        echo \"$file\" >> \"$fufufuresultlist\"\n    fi\ndone\n\ntar -czf $thisbackuploc/$thisbackupname -T \"$fufufuresultlist\" > /dev/null 2>&1\n\n# Uji arsip untuk memeriksa apakah rusak\ntar -tf \"$thisbackuploc/$thisbackupname\" >/dev/null 2>&1\nif [ $? -eq 0 ]; then\necho \"Backup data $thisapp sukses\"\nelse\nrm -rf $thisbackuploc/$thisbackupname > /dev/null 2>&1\necho \"Backup data $thisapp gagal\nkode : $?\"\nfi\n> \"$fufufuresultlist\"".replace("getconfig", strConcat);
            this.s_exe3 = strReplace;
            this.s_exe3 = strReplace.replace("resultconfig", strConcat2);
        } else {
            this.s_exe3 = "tar -czf $thisbackuploc/$thisbackupname --exclude=\"$filedata/lib\" --exclude=\"$filedata/cache\" $filedata > /dev/null 2>&1\n\n# Uji arsip untuk memeriksa apakah rusak\ntar -tf \"$thisbackuploc/$thisbackupname\" >/dev/null 2>&1\nif [ $? -eq 0 ]; then\necho \"Backup data $thisapp sukses\"\nelse\nrm -rf $thisbackuploc/$thisbackupname > /dev/null 2>&1\necho \"Backup data $thisapp gagal\nkode : $?\"\nfi";
        }
        this.s_backup_name = this.prefui.getString("backup_app_package", "").concat("-".concat(new SimpleDateFormat("yyyyMMdd-HHmmss").format(this.f862c.getTime())));
        String strReplace2 = this.s_scbase.replace("futhisapp", this.prefui.getString("backup_app_name", ""));
        this.s_scbase = strReplace2;
        String strReplace3 = strReplace2.replace("futhispackage", this.prefui.getString("backup_app_package", ""));
        this.s_scbase = strReplace3;
        String strReplace4 = strReplace3.replace("futhisbackupname", this.s_backup_name);
        this.s_scbase = strReplace4;
        String strReplace5 = strReplace4.replace("futhisbackuppath", this.prefui.getString("backup_sdcard_location", "").concat("/".concat(this.prefui.getString("backup_app_package", ""))));
        this.s_scbase = strReplace5;
        this.s_scbase = strReplace5.replace("futhisbackuploc", this.prefui.getString("backup_sdcard_location", "").concat("/".concat(this.prefui.getString("backup_app_package", "").concat("/".concat(this.s_backup_number)))));
        this.s_json_loc = this.prefui.getString("backup_sdcard_location", "").concat("/".concat(this.prefui.getString("backup_app_package", "").concat("/".concat(this.s_backup_number.concat("/".concat(this.s_backup_name.concat(".json")))))));
        if (this.prefui.getString("backup_plus_settings_ssaid", "").equals("true")) {
            this.s_scbase = this.s_scbase.replace("#exe1", this.s_exe1);
            this.mProp.put("settings_ssaid", "true");
        } else {
            this.mProp.put("settings_ssaid", "false");
        }
        if (this.prefui.getString("backup_plus_system_prop", "").equals("true")) {
            this.s_scbase = this.s_scbase.replace("#exe2", this.s_exe2);
            this.mProp.put("system.prop", "true");
        } else {
            this.mProp.put("system.prop", "false");
        }
        String strReplace6 = this.s_scbase.replace("#exe3", this.s_exe3);
        this.s_scbase = strReplace6;
        this.s_command = strReplace6;
        this.mProp.put("NOTE", "");
        this.mProp.put("IDIKLAN", this.prefui.getString("advertisingid", ""));
        this.mProp.put("DATE", new SimpleDateFormat("EEEE dd MMMM yyyy HH:mm:ss", new Locale("id", "ID")).format(this.f862c.getTime()));
        this.mProp.put("MARK", "false");
        this.mProp.put("COLOR", "#FFFFFFFF");
        this.mProp.put("UNIX", String.valueOf(this.f862c.getTime().getTime() / 1000));
        _onFinalProsesBackup();
    }

    public void _onFinalProsesBackup() {
        this.tv_proses_title.setText("Proses");
        this.tv_proses_backup.setText("Membuat backup\n".concat(this.prefui.getString("backup_app_name", "")));
        this.ln_lottie.setVisibility(0);
        this.btn_close.setVisibility(8);
        _OnBackgroundAction();
        Runnable runnable = new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                if (ScbackupDialogFragmentActivity.this.b_command) {
                    ScbackupDialogFragmentActivity.this.OnBackup.removeCallbacks(ScbackupDialogFragmentActivity.this.runnableOnBackup);
                    if (!ScbackupDialogFragmentActivity.this.s_commandResult.contains("sukses")) {
                        String strReplace = ScbackupDialogFragmentActivity.this.s_json_loc.replace(".json", ".tar.gz");
                        String str = ScbackupDialogFragmentActivity.this.s_json_loc;
                        String strConcat = ScbackupDialogFragmentActivity.this.prefui.getString("backup_sdcard_location", "").concat("/".concat(ScbackupDialogFragmentActivity.this.prefui.getString("backup_app_package", "").concat("/")));
                        ScbackupDialogFragmentActivity.this.tv_proses_title.setText("Gagal");
                        ScbackupDialogFragmentActivity.this.tv_proses_backup.setText(ScbackupDialogFragmentActivity.this.s_commandResult);
                        File file = new File(strReplace);
                        File file2 = new File(str);
                        if (!file.exists() || !file2.exists()) {
                            if (file.exists()) {
                                file.delete();
                            }
                            if (file2.exists()) {
                                file2.delete();
                            }
                            ScbackupDialogFragmentActivity.deleteEmptyFolders(strConcat);
                        }
                    } else {
                        ScbackupDialogFragmentActivity.this.tv_proses_title.setText("Selesai");
                        ScbackupDialogFragmentActivity.this.tv_proses_backup.setText(ScbackupDialogFragmentActivity.this.s_commandResult);
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.setPrettyPrinting();
                        Gson gsonCreate = gsonBuilder.create();
                        JsonParser jsonParser = new JsonParser();
                        ScbackupDialogFragmentActivity.this.s_prop_json = new Gson().toJson(ScbackupDialogFragmentActivity.this.mProp);
                        ScbackupDialogFragmentActivity scbackupDialogFragmentActivity = ScbackupDialogFragmentActivity.this;
                        scbackupDialogFragmentActivity.s_prop_json = gsonCreate.toJson(jsonParser.parse(scbackupDialogFragmentActivity.s_prop_json));
                        FileUtil.writeFile(ScbackupDialogFragmentActivity.this.s_json_loc, ScbackupDialogFragmentActivity.this.s_prop_json);
                    }
                    ScbackupDialogFragmentActivity.this.b_command = false;
                    ScbackupDialogFragmentActivity.this.ln_lottie.setVisibility(8);
                    ScbackupDialogFragmentActivity.this.btn_close.setVisibility(0);
                    ScbackupDialogFragmentActivity.this._onVibrate();
                    return;
                }
                ScbackupDialogFragmentActivity.this.OnBackup.postDelayed(ScbackupDialogFragmentActivity.this.runnableOnBackup, 100L);
            }
        };
        this.runnableOnBackup = runnable;
        this.OnBackup.postDelayed(runnable, 0L);
    }

        public static void deleteEmptyFolders(String str) {
        File[] fileArrListFiles;
        File file = new File(str);
        if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory() && file2.listFiles().length == 0) {
                    file2.delete();
                }
            }
        }
    }

    public void _onCreateBackup() {
        if (this.m_custom.isChecked()) {
            this.b_custom = true;
        } else {
            this.b_custom = false;
        }
        this.ln_dialog.setVisibility(8);
        this.ln_proses.setVisibility(0);
        getAdvertisingId();
    }

    public void _onAddRules() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.popup_menu_custom_backup, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(viewInflate, -2, -2, true);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_menu1);
        TextView textView2 = (TextView) viewInflate.findViewById(C0978R.id.tv_menu2);
        TextView textView3 = (TextView) viewInflate.findViewById(C0978R.id.tv_menu3);
        TextView textView4 = (TextView) viewInflate.findViewById(C0978R.id.tv_menu4);
        final String strConcat = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/files/".concat(this.prefui.getString("backup_app_package", "").concat(".fu"))));
        textView.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScbackupDialogFragmentActivity.this.intentcustom.setClass(ScbackupDialogFragmentActivity.this.getContext().getApplicationContext(), CstBackupActivity.class);
                ScbackupDialogFragmentActivity.this.intentcustom.putExtra("package", ScbackupDialogFragmentActivity.this.prefui.getString("backup_app_package", ""));
                ScbackupDialogFragmentActivity scbackupDialogFragmentActivity = ScbackupDialogFragmentActivity.this;
                scbackupDialogFragmentActivity.startActivity(scbackupDialogFragmentActivity.intentcustom);
                popupWindow.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FileUtil.isExistFile(strConcat)) {
                    FileUtil.deleteFile(strConcat);
                    ScbackupDialogFragmentActivity.this.m_normal.setVisibility(4);
                    ScbackupDialogFragmentActivity.this.m_custom.setVisibility(4);
                    popupWindow.dismiss();
                    return;
                }
                SketchwareUtil.showMessage(ScbackupDialogFragmentActivity.this.getContext().getApplicationContext(), "Tidak ada file");
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScbackupDialogFragmentActivity scbackupDialogFragmentActivity = ScbackupDialogFragmentActivity.this;
                scbackupDialogFragmentActivity.startActivityForResult(scbackupDialogFragmentActivity.f863fp, 101);
                popupWindow.dismiss();
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FileUtil.isExistFile(strConcat)) {
                    ScbackupDialogFragmentActivity.this.lm_picked.clear();
                    ScbackupDialogFragmentActivity.this.m_export = new HashMap();
                    ScbackupDialogFragmentActivity.this.m_export.put("title", ScbackupDialogFragmentActivity.this.prefui.getString("backup_app_package", ""));
                    ScbackupDialogFragmentActivity.this.m_export.put("config", FileUtil.readFile(strConcat));
                    ScbackupDialogFragmentActivity.this.lm_picked.add(ScbackupDialogFragmentActivity.this.m_export);
                    String strConcat2 = FileUtil.getExternalStorageDir().concat("/csb_".concat(ScbackupDialogFragmentActivity.this.prefui.getString("backup_app_package", "").concat(".fu")));
                    FileUtil.writeFile(strConcat2, new Gson().toJson(ScbackupDialogFragmentActivity.this.lm_picked));
                    SketchwareUtil.showMessage(ScbackupDialogFragmentActivity.this.getContext().getApplicationContext(), "Berhasil disimpan di ".concat(strConcat2));
                    popupWindow.dismiss();
                    return;
                }
                SketchwareUtil.showMessage(ScbackupDialogFragmentActivity.this.getContext().getApplicationContext(), "Tidak ada file");
            }
        });
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.showAsDropDown(this.btn_add_rules, -100, 0);
    }

    public void _setFirstUI() {
        int i;
        this.ln_proses.setVisibility(8);
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
            this.s_commandBase = strReplace60.replace("ⅱ", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            WebSettings settings = new WebView(getActivity()).getSettings();
            if (settings != null) {
                this.userAgent = settings.getUserAgentString();
            } else {
                this.userAgent = "User Agent null";
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            this.userAgent = "User Agent null";
        }
        this.mProp.clear();
        HashMap<String, Object> map = new HashMap<>();
        this.mProp = map;
        map.put("DEVICE", Build.DEVICE);
        this.mProp.put("MODEL", Build.MODEL);
        this.mProp.put("PRODUCT", Build.PRODUCT);
        this.mProp.put("MANUFACTURER", Build.MANUFACTURER);
        this.mProp.put("BRAND", Build.BRAND);
        this.mProp.put("SDK", String.valueOf(Build.VERSION.SDK_INT));
        this.mProp.put("BOARD", Build.BOARD);
        this.mProp.put("BOOT", Build.BOOTLOADER);
        this.mProp.put("DISPLAY", Build.DISPLAY);
        this.mProp.put("FINGERPRINT", Build.FINGERPRINT);
        this.mProp.put("HARDWARE", Build.HARDWARE);
        this.mProp.put("BUILDID", Build.ID);
        this.mProp.put("HOST", Build.HOST);
        this.mProp.put("USER", Build.USER);
        this.mProp.put("RELEASE", Build.VERSION.RELEASE);
        this.mProp.put("INCREMENTAL", Build.VERSION.INCREMENTAL);
        this.mProp.put("USERAGENT", this.userAgent);
        this.mProp.put("HTTPAGENT", System.getProperty("http.agent"));
        this.mProp.put("RADIOVERSION", Build.getRadioVersion());
        this.mProp.put("TIME", Long.toString(Build.TIME));
        this.mProp.put("ANDROIDID", "NOT ETERNAL");
        this.mProp.put("BLUETHOOTNAME", "NOT ETERNAL");
        this.mProp.put("DEVICENAME", "NOT ETERNAL");
        this.mProp.put("IMEI", "NOT ETERNAL");
        this.mProp.put("SERIAL", "NOT ETERNAL");
        this.mProp.put("SERIAL2", "NOT ETERNAL");
        String strConcat = this.prefui.getString("backup_sdcard_location", "").concat("/".concat(this.prefui.getString("backup_app_package", "")));
        this.s_cek_folder = strConcat;
        if (FileUtil.isExistFile(strConcat)) {
            String[] list = new File(this.s_cek_folder).list();
            Arrays.sort(list);
            int i2 = 0;
            for (String str : list) {
                if (isInteger(str) && (i = Integer.parseInt(str)) > i2) {
                    i2 = i;
                }
            }
            this.s_backup_number = String.format("%03d", Integer.valueOf(i2 + 1));
        } else {
            this.s_backup_number = "001";
        }
        String strConcat2 = "cat /data/user/0/".concat(this.prefui.getString("backup_app_package", "").concat("/eternal_id"));
        this.s_cat_eternal = strConcat2;
        this.b_command = false;
        Shell.Result resultExec = Shell.cmd(strConcat2).exec();
        List<String> out = resultExec.getOut();
        resultExec.getCode();
        this.b_command = resultExec.isSuccess();
        String strM78m = String.join("\n", out);
        this.s_prop_json = strM78m;
        if (!strM78m.contains("MANUFACTURER")) {
            this.s_prop_json = new Gson().toJson(this.mProp);
        } else {
            String strReplace61 = this.s_prop_json.replace("[", "");
            this.s_prop_json = strReplace61;
            this.s_prop_json = strReplace61.replace("]", "");
            this.mProp = (HashMap) new Gson().fromJson(this.s_prop_json, new TypeToken<HashMap<String, Object>>() {             }.getType());
        }
        this.m_normal.setChecked(true);
        this.tv_number.setText(this.s_backup_number);
        this.tv_location.setText(this.prefui.getString("backup_sdcard_location", "").replace("/storage/emulated/0", ""));
        this.tv_app.setText(this.prefui.getString("backup_app_package", ""));
        if (this.prefui.getString("backup_plus_settings_ssaid", "").equals("")) {
            this.prefui.edit().putString("backup_plus_settings_ssaid", "false").commit();
        }
        if (this.prefui.getString("backup_plus_system_prop", "").equals("")) {
            this.prefui.edit().putString("backup_plus_system_prop", "false").commit();
        }
        if (this.prefui.getString("backup_plus_settings_ssaid", "").equals("false")) {
            this.msc_ssaid.setChecked(false);
        } else {
            this.msc_ssaid.setChecked(true);
        }
        if (this.prefui.getString("backup_plus_system_prop", "").equals("false")) {
            this.msc_prop.setChecked(false);
        } else {
            this.msc_prop.setChecked(true);
        }
        _onCustomBackup();
    }

    private class GetAdvertisingIdTask extends AsyncTask<Void, Void, String> {
        private GetAdvertisingIdTask() {
        }

        /* synthetic */ GetAdvertisingIdTask(ScbackupDialogFragmentActivity scbackupDialogFragmentActivity, GetAdvertisingIdTask getAdvertisingIdTask) {
            this();
        }

                @Override // android.os.AsyncTask
        public String doInBackground(Void... voidArr) {
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            if (str != null) {
                ScbackupDialogFragmentActivity.this.prefui.edit().putString("advertisingid", str).commit();
            } else {
                ScbackupDialogFragmentActivity.this.prefui.edit().putString("advertisingid", "").commit();
            }
            ScbackupDialogFragmentActivity.this._onPrepareProsesBackup();
        }
    }

    private void getAdvertisingId() {
        new GetAdvertisingIdTask(this, null).execute(new Void[0]);
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public void _finishDialog() {
        this.intentFinish.setClass(getContext().getApplicationContext(), ShortcutExecutorActivity.class);
        this.intentFinish.putExtra("shortcut_command", "finish");
        this.intentFinish.putExtra("shortcut_desc", "finish");
        startActivity(this.intentFinish);
        getActivity().finish();
    }

    public void _OnBackgroundAction() {
        MyBackgroundAction myBackgroundAction = this.myBackgroundAction;
        if (myBackgroundAction != null && myBackgroundAction.isRunning) {
            this.myBackgroundAction.cancelBackgroundActionTask();
        }
        MyBackgroundAction myBackgroundAction2 = new MyBackgroundAction();
        this.myBackgroundAction = myBackgroundAction2;
        myBackgroundAction2.execute(new Void[0]);
    }

    public class MyBackgroundAction extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyBackgroundAction() {
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
            ScbackupDialogFragmentActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(ScbackupDialogFragmentActivity.this.s_command).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            ScbackupDialogFragmentActivity.this.b_command = resultExec.isSuccess();
            ScbackupDialogFragmentActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
        }

        public void cancelBackgroundActionTask() {
            cancel(true);
        }
    }

    public void _onVibrate() {
        this.vibrate.vibrate(100L);
    }

    public void _onCustomBackup() {
        if (FileUtil.isExistFile("/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/files/".concat(this.prefui.getString("backup_app_package", "").concat(".fu")))))) {
            this.m_custom.setVisibility(0);
            this.m_normal.setVisibility(0);
        } else {
            this.m_custom.setVisibility(4);
            this.m_normal.setVisibility(4);
        }
    }
}
