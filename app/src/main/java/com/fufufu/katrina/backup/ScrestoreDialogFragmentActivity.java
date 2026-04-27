package com.fufufu.katrina.backup;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.elevation.SurfaceColors;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topjohnwu.superuser.Shell;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ScrestoreDialogFragmentActivity extends DialogFragment {
    private AutoCompleteTextView auto_editor;
    private Button btn_batal;
    private Button btn_batal_editor;
    private MaterialButton btn_next;
    private Button btn_oke;
    private Button btn_open;
    private MaterialButton btn_prev;
    private Button btn_restore;
    private Button btn_save_editor;
    private MaterialCardView cv_backup;
    private HorizontalScrollView hscr_1;
    private ImageView im_icon;
    private LinearLayout ln_01;
    private LinearLayout ln_backup;
    private LinearLayout ln_backup_content;
    private LinearLayout ln_base;
    private LinearLayout ln_base_top;
    private LinearLayout ln_button;
    private LinearLayout ln_button_bottom;
    private LinearLayout ln_button_editor;
    private LinearLayout ln_content;
    private LinearLayout ln_editor;
    private LinearLayout ln_lottie;
    private LinearLayout ln_mark;
    private LinearLayout ln_proses;
    private LinearLayout ln_proses_content;
    private LinearLayout ln_switch;
    private LinearLayout ln_top;
    private LottieAnimationView lottie1;
    private MaterialCardView mvc_00;
    private MaterialCardView mvc_01;
    private MaterialCardView mvc_02;
    private MaterialCardView mvc_03;
    private MaterialCardView mvc_04;
    private MaterialCardView mvc_05;
    private MaterialCardView mvc_06;
    private MaterialCardView mvc_07;
    private MaterialCardView mvc_08;
    private MaterialCardView mvc_09;
    private MaterialCardView mvc_10;
    private MaterialCardView mvc_base;
    private MaterialCardView mvc_rv;
    private MyBackgroundAction myBackgroundAction;
    private SharedPreferences preflast;
    private SharedPreferences prefui;
    private Runnable runnableOnRestore;
    private RecyclerView rv_1;
    private MaterialSwitch switch_prop;
    private MaterialSwitch switch_ssaid;
    private TextInputLayout til_1;
    private TextView tv_date;
    private TextView tv_desc_prop;
    private TextView tv_desc_ssaid;
    private TextView tv_folder;
    private TextView tv_note;
    private TextView tv_package;
    private TextView tv_proses_backup;
    private TextView tv_proses_title;
    private TextView tv_title;
    private Vibrator vibrate;
    private String s_backup_app = "";
    private String s_backup_loc = "";
    private String s_backupLocation = "";
    private HashMap<String, Object> m_filebackup = new HashMap<>();
    private String s_json_backup = "";
    private HashMap<String, Object> m_json_backup = new HashMap<>();
    private double n_restore_position = 0.0d;
    private String s_commandResult = "";
    private boolean b_command = false;
    private String s_command = "";
    private String s_restore_loc = "";
    private String s_restore_ssaid = "";
    private String s_restore_prop = "";
    private String s_restore_sdk = "";
    private String s_sdk = "";
    private boolean b_ssaid = false;
    private boolean b_prop = false;
    private double n_loop = 0.0d;
    private String s_commandBase = "";
    private String s_scbase = "";
    private String s_exe1 = "";
    private String s_exe2 = "";
    private String s_exe3 = "";
    private String s_exitCode = "";
    private String s_note = "";
    private ArrayList<String> ls_filebackup = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_filebackup = new ArrayList<>();
    private ArrayList<String> ls_sorter = new ArrayList<>();
    private Intent intentFinish = new Intent();
    private Handler OnRestore = new Handler();

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C0978R.layout.screstore_dialog_fragment, viewGroup, false);
        initialize(bundle, viewInflate);
        initializeLogic();
        return viewInflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.mvc_base = (MaterialCardView) view.findViewById(C0978R.id.mvc_base);
        this.ln_base = (LinearLayout) view.findViewById(C0978R.id.ln_base);
        this.ln_base_top = (LinearLayout) view.findViewById(C0978R.id.ln_base_top);
        this.ln_proses = (LinearLayout) view.findViewById(C0978R.id.ln_proses);
        this.ln_top = (LinearLayout) view.findViewById(C0978R.id.ln_top);
        this.mvc_rv = (MaterialCardView) view.findViewById(C0978R.id.mvc_rv);
        this.ln_content = (LinearLayout) view.findViewById(C0978R.id.ln_content);
        this.ln_editor = (LinearLayout) view.findViewById(C0978R.id.ln_editor);
        this.ln_switch = (LinearLayout) view.findViewById(C0978R.id.ln_switch);
        this.ln_button = (LinearLayout) view.findViewById(C0978R.id.ln_button);
        this.im_icon = (ImageView) view.findViewById(C0978R.id.im_icon);
        this.ln_01 = (LinearLayout) view.findViewById(C0978R.id.ln_01);
        this.tv_title = (TextView) view.findViewById(C0978R.id.tv_title);
        this.tv_package = (TextView) view.findViewById(C0978R.id.tv_package);
        this.rv_1 = (RecyclerView) view.findViewById(C0978R.id.rv_1);
        this.btn_prev = (MaterialButton) view.findViewById(C0978R.id.btn_prev);
        this.ln_backup = (LinearLayout) view.findViewById(C0978R.id.ln_backup);
        this.btn_next = (MaterialButton) view.findViewById(C0978R.id.btn_next);
        this.cv_backup = (MaterialCardView) view.findViewById(C0978R.id.cv_backup);
        this.ln_backup_content = (LinearLayout) view.findViewById(C0978R.id.ln_backup_content);
        this.tv_folder = (TextView) view.findViewById(C0978R.id.tv_folder);
        this.tv_date = (TextView) view.findViewById(C0978R.id.tv_date);
        this.tv_note = (TextView) view.findViewById(C0978R.id.tv_note);
        this.til_1 = (TextInputLayout) view.findViewById(C0978R.id.til_1);
        this.hscr_1 = (HorizontalScrollView) view.findViewById(C0978R.id.hscr_1);
        this.ln_button_editor = (LinearLayout) view.findViewById(C0978R.id.ln_button_editor);
        this.auto_editor = (AutoCompleteTextView) view.findViewById(C0978R.id.auto_editor);
        this.ln_mark = (LinearLayout) view.findViewById(C0978R.id.ln_mark);
        this.mvc_00 = (MaterialCardView) view.findViewById(C0978R.id.mvc_00);
        this.mvc_01 = (MaterialCardView) view.findViewById(C0978R.id.mvc_01);
        this.mvc_02 = (MaterialCardView) view.findViewById(C0978R.id.mvc_02);
        this.mvc_03 = (MaterialCardView) view.findViewById(C0978R.id.mvc_03);
        this.mvc_04 = (MaterialCardView) view.findViewById(C0978R.id.mvc_04);
        this.mvc_05 = (MaterialCardView) view.findViewById(C0978R.id.mvc_05);
        this.mvc_06 = (MaterialCardView) view.findViewById(C0978R.id.mvc_06);
        this.mvc_07 = (MaterialCardView) view.findViewById(C0978R.id.mvc_07);
        this.mvc_08 = (MaterialCardView) view.findViewById(C0978R.id.mvc_08);
        this.mvc_09 = (MaterialCardView) view.findViewById(C0978R.id.mvc_09);
        this.mvc_10 = (MaterialCardView) view.findViewById(C0978R.id.mvc_10);
        this.btn_batal_editor = (Button) view.findViewById(C0978R.id.btn_batal_editor);
        this.btn_save_editor = (Button) view.findViewById(C0978R.id.btn_save_editor);
        this.tv_desc_ssaid = (TextView) view.findViewById(C0978R.id.tv_desc_ssaid);
        this.switch_ssaid = (MaterialSwitch) view.findViewById(C0978R.id.switch_ssaid);
        this.tv_desc_prop = (TextView) view.findViewById(C0978R.id.tv_desc_prop);
        this.switch_prop = (MaterialSwitch) view.findViewById(C0978R.id.switch_prop);
        this.btn_batal = (Button) view.findViewById(C0978R.id.btn_batal);
        this.btn_restore = (Button) view.findViewById(C0978R.id.btn_restore);
        this.tv_proses_title = (TextView) view.findViewById(C0978R.id.tv_proses_title);
        this.ln_proses_content = (LinearLayout) view.findViewById(C0978R.id.ln_proses_content);
        this.ln_button_bottom = (LinearLayout) view.findViewById(C0978R.id.ln_button_bottom);
        this.ln_lottie = (LinearLayout) view.findViewById(C0978R.id.ln_lottie);
        this.tv_proses_backup = (TextView) view.findViewById(C0978R.id.tv_proses_backup);
        this.lottie1 = (LottieAnimationView) view.findViewById(C0978R.id.lottie1);
        this.btn_oke = (Button) view.findViewById(C0978R.id.btn_oke);
        this.btn_open = (Button) view.findViewById(C0978R.id.btn_open);
        this.prefui = getContext().getSharedPreferences("preferences_ui", 0);
        this.preflast = getContext().getSharedPreferences("preferences_last", 0);
        this.vibrate = (Vibrator) getContext().getSystemService("vibrator");
        this.btn_prev.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity.this._onPrev();
            }
        });
        this.btn_next.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity.this._onNext();
            }
        });
        this.cv_backup.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity.this._showEditor();
                ScrestoreDialogFragmentActivity.this.ln_switch.setVisibility(8);
                ScrestoreDialogFragmentActivity.this.ln_button.setVisibility(8);
                ScrestoreDialogFragmentActivity.this.ln_editor.setVisibility(0);
            }
        });
        this.btn_batal_editor.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity.this._hideEditor();
            }
        });
        this.btn_save_editor.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity.this._saveEditor();
            }
        });
        this.switch_ssaid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScrestoreDialogFragmentActivity.this.b_ssaid = true;
                } else {
                    ScrestoreDialogFragmentActivity.this.b_ssaid = false;
                }
            }
        });
        this.switch_prop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScrestoreDialogFragmentActivity.this.b_prop = true;
                } else {
                    ScrestoreDialogFragmentActivity.this.b_prop = false;
                }
            }
        });
        this.btn_batal.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity.this._finishDialog();
            }
        });
        this.btn_restore.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity.this._onRestore();
            }
        });
        this.btn_oke.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (ScrestoreDialogFragmentActivity.this.btn_oke.getText().toString().equals("Reboot")) {
                    ScrestoreDialogFragmentActivity.this.s_command = "am start -a android.intent.action.REBOOT";
                    ScrestoreDialogFragmentActivity.this.b_command = false;
                    Shell.Result resultExec = Shell.cmd(ScrestoreDialogFragmentActivity.this.s_command).exec();
                    List<String> out = resultExec.getOut();
                    resultExec.getCode();
                    ScrestoreDialogFragmentActivity.this.b_command = resultExec.isSuccess();
                    ScrestoreDialogFragmentActivity.this.s_commandResult = String.join("\n", out);
                    return;
                }
                ScrestoreDialogFragmentActivity.this._finishDialog();
            }
        });
        this.btn_open.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity screstoreDialogFragmentActivity = ScrestoreDialogFragmentActivity.this;
                screstoreDialogFragmentActivity._openApp(screstoreDialogFragmentActivity.prefui.getString("backup_app_package", ""));
            }
        });
    }

    private void initializeLogic() {
        _setFirstUI();
        _setBackupPosition();
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
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        _finishDialog();
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x03fb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x030d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void _setFirstUI() {
        this.ln_base_top.setBackgroundColor(Color.parseColor("#80ffffff"));
        this.rv_1.setBackgroundColor(Color.parseColor("#80ffffff"));
        this.ln_proses.setVisibility(8);
        this.ln_editor.setVisibility(8);
        try {
            this.im_icon.setImageDrawable(getActivity().getPackageManager().getApplicationIcon(this.prefui.getString("backup_app_package", "")));
        } catch (PackageManager.NameNotFoundException unused) {
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
            this.s_commandBase = strReplace60.replace("ⅱ", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.tv_package.setText(this.prefui.getString("backup_app_package", ""));
        this.btn_prev.setIcon(getResources().getDrawable(C0978R.drawable.ic_sc_prev));
        this.btn_next.setIcon(getResources().getDrawable(C0978R.drawable.ic_sc_next));
        this.s_backup_app = this.prefui.getString("backup_app_package", "");
        String string = this.prefui.getString("backup_sdcard_location", "");
        this.s_backup_loc = string;
        String strConcat = string.concat("/".concat(this.s_backup_app));
        this.s_backupLocation = strConcat;
        FileUtil.listDir(strConcat, this.ls_filebackup);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.ls_filebackup.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            File file = new File(next);
            if (file.isFile()) {
                this.ls_filebackup.remove(next);
            } else if (file.isDirectory()) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (File file2 : fileArrListFiles) {
                        if (file2.isFile() && file2.getName().endsWith(".tar.gz")) {
                            break;
                        }
                    }
                    z = false;
                    if (z) {
                        arrayList.add(next);
                    }
                } else {
                    z = false;
                    if (z) {
                    }
                }
            }
        }
        this.ls_filebackup.removeAll(arrayList);
        for (String str : this.ls_filebackup) {
            HashMap<String, Object> map = new HashMap<>();
            this.m_filebackup = map;
            map.put("file", str);
            FileUtil.listDir(str, this.ls_sorter);
            Iterator<String> it2 = this.ls_sorter.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                String next2 = it2.next();
                if (next2.endsWith(".tar.gz")) {
                    this.m_filebackup.put("backup", next2);
                    break;
                }
            }
            this.lm_filebackup.add(this.m_filebackup);
        }
        SketchwareUtil.sortListMap(this.lm_filebackup, "file", false, true);
        this.rv_1.setAdapter(new Rv_1Adapter(this.lm_filebackup));
        this.rv_1.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        if (this.lm_filebackup.size() == 0) {
            this.mvc_rv.setVisibility(8);
            this.ln_content.setVisibility(8);
            this.btn_restore.setVisibility(8);
            this.ln_switch.setVisibility(8);
            return;
        }
        this.mvc_rv.setVisibility(0);
        this.ln_content.setVisibility(0);
        this.btn_restore.setVisibility(0);
        this.ln_switch.setVisibility(0);
    }

    public void _finishDialog() {
        this.intentFinish.setClass(getContext().getApplicationContext(), ShortcutExecutorActivity.class);
        this.intentFinish.putExtra("shortcut_command", "finish");
        this.intentFinish.putExtra("shortcut_desc", "finish");
        startActivity(this.intentFinish);
        getActivity().finish();
    }

    public void _onChooseFile(String str, String str2) {
        _hideEditor();
        this.s_restore_loc = str2;
        String strReplace = str2.replace(".tar.gz", ".json");
        this.s_json_backup = strReplace;
        if (FileUtil.isExistFile(strReplace)) {
            this.s_json_backup = FileUtil.readFile(this.s_json_backup);
            this.m_json_backup = (HashMap) new Gson().fromJson(this.s_json_backup, new TypeToken<HashMap<String, Object>>() {             }.getType());
            this.tv_folder.setText(str);
            this.ln_backup.setVisibility(0);
            this.ln_switch.setVisibility(0);
            this.btn_restore.setVisibility(0);
            this.ln_button.setVisibility(0);
            _setDialogUI();
            return;
        }
        this.ln_backup.setVisibility(4);
        this.ln_switch.setVisibility(4);
        this.ln_button.setVisibility(0);
        this.btn_restore.setVisibility(8);
    }

    public void _setBackupPosition() {
        String string = this.preflast.getString(this.s_backup_app, "");
        this.s_restore_loc = string;
        if (!string.equals("")) {
            this.n_restore_position = 0.0d;
            for (int i = 0; i < this.lm_filebackup.size(); i++) {
                if (this.lm_filebackup.get((int) this.n_restore_position).get("backup").toString().equals(this.preflast.getString(this.s_backup_app, ""))) {
                    ((LinearLayoutManager) this.rv_1.getLayoutManager()).scrollToPositionWithOffset((int) this.n_restore_position, 0);
                    String strReplace = this.lm_filebackup.get((int) this.n_restore_position).get("backup").toString().replace(".tar.gz", ".json");
                    this.s_json_backup = strReplace;
                    if (FileUtil.isExistFile(strReplace)) {
                        this.s_json_backup = FileUtil.readFile(this.s_json_backup);
                        this.m_json_backup = (HashMap) new Gson().fromJson(this.s_json_backup, new TypeToken<HashMap<String, Object>>() {                         }.getType());
                        this.tv_folder.setText(Uri.parse(this.lm_filebackup.get((int) this.n_restore_position).get("file").toString()).getLastPathSegment());
                        this.ln_backup.setVisibility(0);
                        this.ln_switch.setVisibility(0);
                        this.btn_restore.setVisibility(0);
                        _setDialogUI();
                        return;
                    }
                    this.ln_backup.setVisibility(4);
                    this.ln_switch.setVisibility(4);
                    this.btn_restore.setVisibility(8);
                }
                this.n_restore_position += 1.0d;
            }
            return;
        }
        if (this.lm_filebackup.size() != 0) {
            this.s_restore_loc = this.lm_filebackup.get(0).get("backup").toString();
            String strReplace2 = this.lm_filebackup.get(0).get("backup").toString().replace(".tar.gz", ".json");
            this.s_json_backup = strReplace2;
            if (FileUtil.isExistFile(strReplace2)) {
                this.s_json_backup = FileUtil.readFile(this.s_json_backup);
                this.m_json_backup = (HashMap) new Gson().fromJson(this.s_json_backup, new TypeToken<HashMap<String, Object>>() {                 }.getType());
                this.tv_folder.setText(Uri.parse(this.lm_filebackup.get(0).get("file").toString()).getLastPathSegment());
                this.ln_backup.setVisibility(0);
                this.ln_switch.setVisibility(0);
                this.btn_restore.setVisibility(0);
                _setDialogUI();
                return;
            }
            this.ln_backup.setVisibility(4);
            this.ln_switch.setVisibility(8);
            this.btn_restore.setVisibility(8);
        }
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
            ScrestoreDialogFragmentActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(ScrestoreDialogFragmentActivity.this.s_command).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            ScrestoreDialogFragmentActivity.this.b_command = resultExec.isSuccess();
            ScrestoreDialogFragmentActivity.this.s_commandResult = String.join("\n", out);
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

    public void _setDialogUI() {
        this.b_ssaid = false;
        this.b_prop = false;
        this.b_command = false;
        if (this.m_json_backup.containsKey("NOTE")) {
            String string = this.m_json_backup.get("NOTE").toString();
            this.s_note = string;
            this.tv_note.setText(string);
        } else {
            this.s_note = "";
            this.tv_note.setText(getString(C0978R.string.restore_note_empty));
        }
        if (this.m_json_backup.containsKey("DATE")) {
            this.tv_date.setText(this.m_json_backup.get("DATE").toString());
        } else {
            this.tv_date.setText(getString(C0978R.string.restore_date_empty));
        }
        if (this.m_json_backup.containsKey("settings_ssaid")) {
            this.s_restore_ssaid = this.m_json_backup.get("settings_ssaid").toString();
        } else {
            this.s_restore_ssaid = "false";
        }
        if (this.m_json_backup.containsKey("system.prop")) {
            this.s_restore_prop = this.m_json_backup.get("system.prop").toString();
        } else {
            this.s_restore_prop = "false";
        }
        if (this.m_json_backup.containsKey("SDK")) {
            this.s_restore_sdk = this.m_json_backup.get("SDK").toString();
        } else {
            this.s_restore_sdk = String.valueOf(Build.VERSION.SDK_INT);
        }
        if (this.m_json_backup.containsKey("MARK")) {
            if (this.m_json_backup.get("MARK").toString().equals("true")) {
                if (this.m_json_backup.containsKey("COLOR")) {
                    this.cv_backup.setCardBackgroundColor(Color.parseColor(this.m_json_backup.get("COLOR").toString()));
                }
            } else {
                this.cv_backup.setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(requireContext()));
            }
        }
        this.s_sdk = String.valueOf(Build.VERSION.SDK_INT);
        if (this.s_restore_prop.equals("true")) {
            this.tv_desc_prop.setText(getString(C0978R.string.restore_allowed));
            this.switch_prop.setEnabled(true);
            this.switch_prop.setAlpha(1.0f);
        } else {
            this.tv_desc_prop.setText(getString(C0978R.string.restore_not_allowed));
            this.switch_prop.setEnabled(false);
            this.switch_prop.setAlpha(0.4f);
        }
        if (this.s_restore_ssaid.equals("true")) {
            this.tv_desc_ssaid.setText(getString(C0978R.string.restore_allowed));
            this.switch_ssaid.setEnabled(true);
            this.switch_ssaid.setAlpha(1.0f);
        } else {
            this.tv_desc_ssaid.setText(getString(C0978R.string.restore_not_allowed));
            this.switch_ssaid.setEnabled(false);
            this.switch_ssaid.setAlpha(0.4f);
        }
        if (this.s_sdk.equals(this.s_restore_sdk)) {
            return;
        }
        this.tv_desc_ssaid.setText(getString(C0978R.string.restore_not_allowed));
        this.switch_ssaid.setEnabled(false);
        this.tv_desc_prop.setText(getString(C0978R.string.restore_not_allowed));
        this.switch_prop.setEnabled(false);
        this.switch_ssaid.setAlpha(0.4f);
        this.switch_prop.setAlpha(0.4f);
    }

    public void _onPrev() {
        this.n_loop = 0.0d;
        for (int i = 0; i < this.lm_filebackup.size(); i++) {
            if (this.lm_filebackup.get((int) this.n_loop).get("backup").toString().equals(this.s_restore_loc)) {
                try {
                    ((LinearLayoutManager) this.rv_1.getLayoutManager()).scrollToPositionWithOffset(((int) this.n_loop) - 1, 0);
                    _onChooseFile(Uri.parse(this.lm_filebackup.get(((int) this.n_loop) - 1).get("file").toString()).getLastPathSegment(), this.lm_filebackup.get(((int) this.n_loop) - 1).get("backup").toString());
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    SketchwareUtil.showMessage(getContext().getApplicationContext(), getString(C0978R.string.restore_first_item));
                }
            }
            this.n_loop += 1.0d;
        }
    }

    public void _onNext() {
        this.n_loop = 0.0d;
        for (int i = 0; i < this.lm_filebackup.size(); i++) {
            if (this.lm_filebackup.get((int) this.n_loop).get("backup").toString().equals(this.s_restore_loc)) {
                try {
                    ((LinearLayoutManager) this.rv_1.getLayoutManager()).scrollToPositionWithOffset(((int) this.n_loop) + 1, 0);
                    _onChooseFile(Uri.parse(this.lm_filebackup.get(((int) this.n_loop) + 1).get("file").toString()).getLastPathSegment(), this.lm_filebackup.get(((int) this.n_loop) + 1).get("backup").toString());
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    SketchwareUtil.showMessage(getContext().getApplicationContext(), getString(C0978R.string.restore_last_item));
                }
            }
            this.n_loop += 1.0d;
        }
    }

    public void _onRestore() {
        this.ln_base_top.setVisibility(8);
        this.ln_proses.setVisibility(0);
        String strConcat = this.s_commandBase.concat("\nonrestore");
        this.s_scbase = strConcat;
        this.s_exe1 = "tar -zxf $thisfile -C / > /dev/null 2>&1\nif [[ $? -eq 0 ]]; then\n  echo \"Sukses restore $thisapp\"\nelse\n  echo \"Gagal restore $thisapp\"\nfi";
        this.s_exe2 = "tar -zxf $thispath/fileprop.tar.gz -C / > /dev/null 2>&1\nif [[ $? -eq 0 ]]; then\n  echo \"Sukses restore system.prop\"\nelse\n  echo  \"Gagal restore system.prop\"\nfi";
        this.s_exe3 = "tar -zxf $thispath/filessaid.tar.gz -C / > /dev/null 2>&1\nif [[ $? -eq 0 ]]; then\n  echo \"Sukses restore settings_ssaid\"\nelse\n  echo \"Gagal restore settings_ssaid\"\nfi";
        String strReplace = strConcat.replace("futhisapp", this.prefui.getString("backup_app_name", ""));
        this.s_scbase = strReplace;
        String strReplace2 = strReplace.replace("futhispackage", this.prefui.getString("backup_app_package", ""));
        this.s_scbase = strReplace2;
        String strReplace3 = strReplace2.replace("futhisfile", this.s_restore_loc);
        this.s_scbase = strReplace3;
        String strReplace4 = strReplace3.replace("#exe1", this.s_exe1);
        this.s_scbase = strReplace4;
        this.s_commandResult = "";
        this.s_exitCode = "";
        if (this.b_prop) {
            this.s_scbase = strReplace4.replace("#exe2", this.s_exe2);
        }
        if (this.b_ssaid) {
            this.s_scbase = this.s_scbase.replace("#exe3", this.s_exe3);
        }
        this.s_command = this.s_scbase;
        this.tv_proses_title.setText(getString(C0978R.string.restore_process_title));
        this.tv_proses_backup.setText(getString(C0978R.string.restore_backup_progress, this.prefui.getString("backup_app_name", "")));
        this.ln_lottie.setVisibility(0);
        this.btn_oke.setVisibility(8);
        this.btn_open.setVisibility(8);
        _OnBackgroundAction();
        Runnable runnable = new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                if (ScrestoreDialogFragmentActivity.this.b_command) {
                    ScrestoreDialogFragmentActivity.this.OnRestore.removeCallbacks(ScrestoreDialogFragmentActivity.this.runnableOnRestore);
                    ScrestoreDialogFragmentActivity.this.b_command = false;
                    ScrestoreDialogFragmentActivity.this.tv_proses_title.setText(getString(C0978R.string.restore_process_done));
                    ScrestoreDialogFragmentActivity.this.tv_proses_backup.setText(ScrestoreDialogFragmentActivity.this.s_commandResult);
                    ScrestoreDialogFragmentActivity.this.ln_lottie.setVisibility(8);
                    ScrestoreDialogFragmentActivity.this.btn_oke.setVisibility(0);
                    ScrestoreDialogFragmentActivity.this.btn_open.setVisibility(0);
                    ScrestoreDialogFragmentActivity.this.preflast.edit().putString(ScrestoreDialogFragmentActivity.this.prefui.getString("backup_app_package", ""), ScrestoreDialogFragmentActivity.this.s_restore_loc).commit();
                    ScrestoreDialogFragmentActivity.this._onVibrate();
                    return;
                }
                ScrestoreDialogFragmentActivity.this.OnRestore.postDelayed(ScrestoreDialogFragmentActivity.this.runnableOnRestore, 100L);
            }
        };
        this.runnableOnRestore = runnable;
        this.OnRestore.postDelayed(runnable, 0L);
        if (this.b_ssaid || this.b_prop) {
            this.btn_oke.setText(getString(C0978R.string.btn_reboot));
        } else {
            this.btn_open.setVisibility(8);
            this.btn_oke.setText(getString(C0978R.string.common_close));
        }
    }

    public void _openApp(String str) {
        Intent launchIntentForPackage = getActivity().getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            startActivity(launchIntentForPackage);
            dismiss();
        } else {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), getString(C0978R.string.toast_app_not_found));
        }
    }

    public void _onVibrate() {
        this.vibrate.vibrate(100L);
    }

    public void _showEditor() {
        this.ln_switch.setVisibility(8);
        this.ln_button.setVisibility(8);
        this.ln_editor.setVisibility(0);
        this.auto_editor.setText(this.s_note);
        this.mvc_01.setCardBackgroundColor(Color.parseColor("#FFE8DFF5"));
        this.mvc_02.setCardBackgroundColor(Color.parseColor("#FFFCE1E4"));
        this.mvc_03.setCardBackgroundColor(Color.parseColor("#FFFCF4DD"));
        this.mvc_04.setCardBackgroundColor(Color.parseColor("#FFDDEDEA"));
        this.mvc_05.setCardBackgroundColor(Color.parseColor("#FFDAEAF6"));
        this.mvc_06.setCardBackgroundColor(Color.parseColor("#FFC8B2EB"));
        this.mvc_07.setCardBackgroundColor(Color.parseColor("#FFFBADAB"));
        this.mvc_08.setCardBackgroundColor(Color.parseColor("#FFFADF7E"));
        this.mvc_09.setCardBackgroundColor(Color.parseColor("#FFBBD9C1"));
        this.mvc_10.setCardBackgroundColor(Color.parseColor("#FF80B7FF"));
        this.mvc_00.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "false");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFFFFFFF");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_01.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFE8DFF5");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_02.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFFCE1E4");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_03.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFFCF4DD");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_04.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFDDEDEA");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_05.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFDAEAF6");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_06.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFC8B2EB");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_07.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFFBADAB");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_08.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFFADF7E");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_09.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFBBD9C1");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_10.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FF80B7FF");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
    }

    public void _saveEditor() {
        this.m_json_backup.put("NOTE", this.auto_editor.getText().toString());
        this.tv_note.setText(this.auto_editor.getText().toString());
        FileUtil.writeFile(this.s_restore_loc.replace(".tar.gz", ".json"), new Gson().toJson(this.m_json_backup));
        this.ln_switch.setVisibility(0);
        this.ln_button.setVisibility(0);
        this.ln_editor.setVisibility(8);
    }

    public void _hideEditor() {
        this.ln_switch.setVisibility(0);
        this.ln_button.setVisibility(0);
        this.ln_editor.setVisibility(8);
    }

    public class Rv_1Adapter extends RecyclerView.Adapter<Rv_1Adapter.ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;

        public Rv_1Adapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View viewInflate = ScrestoreDialogFragmentActivity.this.getActivity().getLayoutInflater().inflate(C0978R.layout.shortcut_restore_view, (ViewGroup) null);
            viewInflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(viewInflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            View view = viewHolder.itemView;
            MaterialCardView materialCardView = (MaterialCardView) view.findViewById(C0978R.id.mvc_base);
            TextView textView = (TextView) view.findViewById(C0978R.id.tv_number);
            view.setLayoutParams(new RecyclerView.LayoutParams(-2, -2));
            textView.setText(Uri.parse(this._data.get(i).get("file").toString()).getLastPathSegment());
            materialCardView.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScrestoreDialogFragmentActivity.this._onChooseFile(Uri.parse(Rv_1Adapter.this._data.get(i).get("file").toString()).getLastPathSegment(), Rv_1Adapter.this._data.get(i).get("backup").toString());
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
