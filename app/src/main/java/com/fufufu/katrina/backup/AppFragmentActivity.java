package com.fufufu.katrina.backup;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.elevation.SurfaceColors;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.topjohnwu.superuser.Shell;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppFragmentActivity extends Fragment {
    private AlertDialog BOT;
    private AlertDialog EDITNOTE;
    private AlertDialog FILTER;
    private AlertDialog INFOBACKUP;
    private AlertDialog PROSESRESTOR;
    private AlertDialog RAPP;
    private AlertDialog RESTORE;
    private AlertDialog SHOWFOLDER;
    private AlertDialog UAPP;
    private AlertDialog UNIVERSAL;
    private AlertDialog UPDATE;
    private AlertDialog WAPP;
    private FloatingActionButton _fab;
    private AutoCompleteTextView auto_app;
    private AutoCompleteTextView auto_backup;
    private Button btn_download;
    private AlertDialog customDialog3;
    private MaterialCardView cv_favorite;
    private MaterialCardView cv_search_type;
    private FloatingActionButton fab2;
    private FloatingActionButton fabeternal;
    private ImageView im_app_ritual;
    private ImageView im_empty;
    private ImageView im_favorite;
    private ImageView im_search_type;
    private LinearLayout ln_02;
    private LinearLayout ln_03;
    private LinearLayout ln_04;
    private LinearLayout ln_06;
    private LinearLayout ln_07;
    private LinearLayout ln_backup;
    private LinearLayout ln_backup_empty;
    private LinearLayout ln_base;
    private LinearLayout ln_progressbar;
    private LinearLayout ln_recycle_view;
    private LinearLayout ln_ritual;
    private LinearLayout ln_ritual_top;
    private LinearLayout ln_search_bar;
    private LinearLayout ln_search_view;
    private LottieAnimationView lottie1;
    private ListView lv_app;
    private ListView lv_backup;
    private ListView lv_fav;
    private MaterialCardView materialcardview1;
    private Chip mchip_info;
    private Chip mchip_loc;
    private Chip mchip_slot;
    private Chip mchip_title;
    private Chip mchip_total;
    private MaterialCardView mcv_ritual;
    private MyAPPLIST myAPPLIST;
    private MyBackgroundAction myBackgroundAction;
    private MyREADBACKUP myREADBACKUP;
    private MyREADFOLDER myREADFOLDER;
    private MyRITUALREINSTALL myRITUALREINSTALL;
    private MyRITUALTIMEPICK myRITUALTIMEPICK;
    private MyRITUALWIPEDATA myRITUALWIPEDATA;
    private MyRITUALWIPEGMS myRITUALWIPEGMS;
    private MySTARTSORTIR mySTARTSORTIR;
    private ProgressBar pbar_ritual;
    private SharedPreferences prefall;
    private SharedPreferences preffav;
    private SharedPreferences preflast;
    private SharedPreferences prefrelease;
    private SharedPreferences prefui;
    private SharedPreferences prefuser;
    private ProgressBar progressBar;
    private Runnable runnableOnRestore;
    private Runnable runnableREINSTALLAPP;
    private Runnable runnableUNINSTALLAPP;
    private Runnable runnableWIPEAPP;
    private Runnable runnablefolderone;
    private Runnable runnablefoldertri;
    private Runnable runnablefoldertwo;
    private MaterialSwitch switch_gms;
    private MaterialSwitch switch_reinstall;
    private MaterialSwitch switch_timepick;
    private MaterialSwitch switch_wipe;
    private TextInputLayout til_app;
    private TextInputLayout til_backup;
    private TextView tv_name_ritual;
    private TextView tv_ritual_result;
    private TextView tv_status;
    private TextView tv_versi_ritual;
    private Vibrator vibrate;
    private ScrollView vscr_ritual;
    private HashMap<String, Object> m_app = new HashMap<>();
    private double n_pos = 0.0d;
    private HashMap<String, Object> m_search_backup = new HashMap<>();
    private HashMap<String, Object> m_fav = new HashMap<>();
    private boolean b_fav = false;
    private String s_fav_app = "";
    private String s_backup_app = "";
    private String s_backup_loc = "";
    private HashMap<String, Object> m_sort_result = new HashMap<>();
    private String s_fname = "";

        private double f796n1 = 0.0d;

        private double f797n2 = 0.0d;

        private double f798n3 = 0.0d;
    private HashMap<String, Object> m_json_app = new HashMap<>();
    private String s_filePath = "";
    private HashMap<String, Object> m_search_app = new HashMap<>();
    private String s_path_json = "";
    private HashMap<String, Object> m_path_json = new HashMap<>();
    private String s_folder_picker = "";
    private boolean b_folder_scan = false;
    private String s_note = "";
    private double n_position = 0.0d;
    private double n_backup_number = 0.0d;
    private String s_command = "";
    private boolean b_command = false;
    private String s_commandResult = "";
    private String s_scbase = "";
    private String s_exe1 = "";
    private String s_exe2 = "";
    private String s_exe3 = "";
    private String s_universal_progress = "";
    private double n_position_app = 0.0d;
    private String s_sdk = "";
    private String s_restore_loc = "";
    private String s_restore_ssaid = "";
    private String s_restore_prop = "";
    private String s_restore_sdk = "";
    private boolean b_ssaid = false;
    private boolean b_prop = false;
    private double n_restore_position = 0.0d;
    private String s_null = "";
    private String s_top_appname = "";
    private boolean b_update_force = false;
    private String s_url = "";
    private String s_commandBase = "";
    private boolean b_ritual = false;
    private String s_ritual_app = "";
    private String s_package_ritual = "";
    private String s_commandBaseRestore = "";
    private boolean b_rebackup = false;
    private String s_usia_backup = "";
    private String s_file_size = "";
    private String s_date_backup = "";
    private String s_backupLocation = "";
    private HashMap<String, Object> m_export = new HashMap<>();
    private boolean b_custom = false;
    private double n_restore_pos_app = 0.0d;
    private String s_extra = "";
    private HashMap<String, Object> m_extra = new HashMap<>();
    private String s_progressfile = "";
    private String s_totalfile = "";
    private String s_filename = "";
    private String s_exitCode = "";
    private String s_totalsize = "";
    private String s_namefile = "";
    private boolean b_restore = false;
    private String s_filterbackup = "";
    private String s_cek_folder = "";
    private String s_cek_sortir = "";
    private String s_backup_number = "";
    private double n_filter = 0.0d;
    private double n_shortfilter = 0.0d;
    private String s_string = "";
    private String s_string_folder = "";
    private String s_shortslot = "";
    private String s_markcolor = "";
    private ArrayList<HashMap<String, Object>> lm_all_app = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_search_backup = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_fav_app = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_backup_data = new ArrayList<>();
    private ArrayList<String> ls_sort_1 = new ArrayList<>();
    private ArrayList<String> ls_sort_2 = new ArrayList<>();
    private ArrayList<String> ls_sort_3 = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_search_app = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_folder_picker = new ArrayList<>();
    private ArrayList<String> ls_folder_picker = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_release = new ArrayList<>();
    private ArrayList<String> ls_allapps = new ArrayList<>();
    private ArrayList<String> ls_eternal = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_eternal = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_picked = new ArrayList<>();
    private ArrayList<String> ls_backupbot = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_eternal_app = new ArrayList<>();

        private Calendar f795c = Calendar.getInstance();
    private ObjectAnimator oa1 = new ObjectAnimator();
    private ObjectAnimator oa2 = new ObjectAnimator();
    private Intent intentcustom = new Intent();
    private Handler folderone = new Handler();
    private Handler foldertwo = new Handler();
    private Handler foldertri = new Handler();
    private Handler UNINSTALLAPP = new Handler();
    private Handler REINSTALLAPP = new Handler();
    private Handler WIPEAPP = new Handler();
    private Handler OnRestore = new Handler();

    public void _EXTRA() {
    }

    public void _onStartDownload() {
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C0978R.layout.app_fragment, viewGroup, false);
        initialize(bundle, viewInflate);
        initializeLogic();
        return viewInflate;
    }

    private void initialize(Bundle bundle, View view) {
        this._fab = (FloatingActionButton) view.findViewById(C0978R.id._fab);
        this.ln_base = (LinearLayout) view.findViewById(C0978R.id.ln_base);
        this.ln_progressbar = (LinearLayout) view.findViewById(C0978R.id.ln_progressbar);
        this.ln_recycle_view = (LinearLayout) view.findViewById(C0978R.id.ln_recycle_view);
        this.ln_04 = (LinearLayout) view.findViewById(C0978R.id.ln_04);
        this.ln_ritual = (LinearLayout) view.findViewById(C0978R.id.ln_ritual);
        this.ln_backup = (LinearLayout) view.findViewById(C0978R.id.ln_backup);
        this.cv_favorite = (MaterialCardView) view.findViewById(C0978R.id.cv_favorite);
        this.lv_app = (ListView) view.findViewById(C0978R.id.lv_app);
        this.lv_fav = (ListView) view.findViewById(C0978R.id.lv_fav);
        this.im_favorite = (ImageView) view.findViewById(C0978R.id.im_favorite);
        this.ln_ritual_top = (LinearLayout) view.findViewById(C0978R.id.ln_ritual_top);
        this.switch_wipe = (MaterialSwitch) view.findViewById(C0978R.id.switch_wipe);
        this.switch_reinstall = (MaterialSwitch) view.findViewById(C0978R.id.switch_reinstall);
        this.switch_gms = (MaterialSwitch) view.findViewById(C0978R.id.switch_gms);
        this.switch_timepick = (MaterialSwitch) view.findViewById(C0978R.id.switch_timepick);
        this.tv_status = (TextView) view.findViewById(C0978R.id.tv_status);
        this.pbar_ritual = (ProgressBar) view.findViewById(C0978R.id.pbar_ritual);
        this.vscr_ritual = (ScrollView) view.findViewById(C0978R.id.vscr_ritual);
        this.ln_06 = (LinearLayout) view.findViewById(C0978R.id.ln_06);
        this.mcv_ritual = (MaterialCardView) view.findViewById(C0978R.id.mcv_ritual);
        this.ln_07 = (LinearLayout) view.findViewById(C0978R.id.ln_07);
        this.im_app_ritual = (ImageView) view.findViewById(C0978R.id.im_app_ritual);
        this.tv_name_ritual = (TextView) view.findViewById(C0978R.id.tv_name_ritual);
        this.tv_versi_ritual = (TextView) view.findViewById(C0978R.id.tv_versi_ritual);
        this.tv_ritual_result = (TextView) view.findViewById(C0978R.id.tv_ritual_result);
        this.ln_search_bar = (LinearLayout) view.findViewById(C0978R.id.ln_search_bar);
        this.ln_03 = (LinearLayout) view.findViewById(C0978R.id.ln_03);
        this.ln_02 = (LinearLayout) view.findViewById(C0978R.id.ln_02);
        this.lv_backup = (ListView) view.findViewById(C0978R.id.lv_backup);
        this.ln_backup_empty = (LinearLayout) view.findViewById(C0978R.id.ln_backup_empty);
        this.materialcardview1 = (MaterialCardView) view.findViewById(C0978R.id.materialcardview1);
        this.ln_search_view = (LinearLayout) view.findViewById(C0978R.id.ln_search_view);
        this.til_app = (TextInputLayout) view.findViewById(C0978R.id.til_app);
        this.til_backup = (TextInputLayout) view.findViewById(C0978R.id.til_backup);
        this.cv_search_type = (MaterialCardView) view.findViewById(C0978R.id.cv_search_type);
        this.auto_app = (AutoCompleteTextView) view.findViewById(C0978R.id.auto_app);
        this.auto_backup = (AutoCompleteTextView) view.findViewById(C0978R.id.auto_backup);
        this.im_search_type = (ImageView) view.findViewById(C0978R.id.im_search_type);
        this.mchip_title = (Chip) view.findViewById(C0978R.id.mchip_title);
        this.mchip_info = (Chip) view.findViewById(C0978R.id.mchip_info);
        this.mchip_slot = (Chip) view.findViewById(C0978R.id.mchip_slot);
        this.mchip_loc = (Chip) view.findViewById(C0978R.id.mchip_loc);
        this.mchip_total = (Chip) view.findViewById(C0978R.id.mchip_total);
        this.im_empty = (ImageView) view.findViewById(C0978R.id.im_empty);
        this.lottie1 = (LottieAnimationView) view.findViewById(C0978R.id.lottie1);
        this.fab2 = (FloatingActionButton) view.findViewById(C0978R.id.fab2);
        this.fabeternal = (FloatingActionButton) view.findViewById(C0978R.id.fabeternal);
        this.prefui = getContext().getSharedPreferences("preferences_ui", 0);
        this.preffav = getContext().getSharedPreferences("preferences_fav", 0);
        this.preflast = getContext().getSharedPreferences("preferences_last", 0);
        this.prefall = getContext().getSharedPreferences("all_app_preferences", 0);
        this.prefrelease = getContext().getSharedPreferences("release_preference", 0);
        this.vibrate = (Vibrator) getContext().getSystemService("vibrator");
        this.prefuser = getContext().getSharedPreferences("user_preferences", 0);
        this.cv_favorite.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._setFavoriteApp();
            }
        });
        this.switch_wipe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_wipe_app", "true").commit();
                } else {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_wipe_app", "false").commit();
                }
            }
        });
        this.switch_reinstall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_reinstall_app", "true").commit();
                } else {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_reinstall_app", "false").commit();
                }
            }
        });
        this.switch_gms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_wipe_gms", "true").commit();
                } else {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_wipe_gms", "false").commit();
                }
            }
        });
        this.switch_timepick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_clean_timepick", "true").commit();
                } else {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_clean_timepick", "false").commit();
                }
            }
        });
        this.im_app_ritual.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity._actionOpenPackage(appFragmentActivity.prefui.getString("backup_app_package", ""));
            }
        });
        this.cv_search_type.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._setSearchType();
            }
        });
        this.auto_app.addTextChangedListener(new TextWatcher() {             @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                AppFragmentActivity.this._onSearchApp(charSequence.toString());
            }
        });
        this.auto_backup.addTextChangedListener(new TextWatcher() {             @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                AppFragmentActivity.this._onSearchBackup(charSequence.toString());
            }
        });
        this.mchip_title.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity._actionOpenPackage(appFragmentActivity.prefui.getString("backup_app_package", ""));
            }
        });
        this.mchip_info.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity._actionInfoPackage(appFragmentActivity.prefui.getString("backup_app_package", ""));
            }
        });
        this.mchip_slot.setOnLongClickListener(new View.OnLongClickListener() {             @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view2) {
                AppFragmentActivity.this._startSorterBackup();
                return true;
            }
        });
        this.mchip_slot.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SketchwareUtil.showMessage(AppFragmentActivity.this.getContext().getApplicationContext(), "Klik lama untuk mengurutkan ulang nomer slot backup dan mengisi slot kosong.");
            }
        });
        this.mchip_loc.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._showFolderPicker();
            }
        });
        this.mchip_total.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._showDialogFilter();
            }
        });
        this.fab2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (AppFragmentActivity.this.b_ritual) {
                    AppFragmentActivity.this.b_rebackup = false;
                    AppFragmentActivity.this._onAppRitual();
                } else {
                    AppFragmentActivity.this.b_rebackup = false;
                    AppFragmentActivity.this._onCreateBackup();
                }
            }
        });
        this.fabeternal.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (AppFragmentActivity.this.getActivity() instanceof KatrinaActivity) {
                    ((KatrinaActivity) AppFragmentActivity.this.getActivity())._fragmentEternal();
                }
            }
        });
        this._fab.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._showDialogRitual();
            }
        });
        this.oa1.addListener(new Animator.AnimatorListener() {             @Override // android.animation.Animator.AnimatorListener
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
                AppFragmentActivity.this.oa2.cancel();
                AppFragmentActivity.this.oa2.setTarget(AppFragmentActivity.this.cv_favorite);
                AppFragmentActivity.this.oa2.setPropertyName("alpha");
                AppFragmentActivity.this.oa2.setFloatValues(0.0f, 1.0f);
                AppFragmentActivity.this.oa2.setDuration(300L);
                AppFragmentActivity.this.oa2.start();
            }
        });
        this.oa2.addListener(new Animator.AnimatorListener() {             @Override // android.animation.Animator.AnimatorListener
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
                AppFragmentActivity.this.cv_favorite.setVisibility(0);
            }
        });
    }

    private void initializeLogic() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {             @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
            }
        });
        _setFirstUI();
        _getAppList();
        _setFromPreferences();
        new Handler().postDelayed(new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                if (AppFragmentActivity.this.prefrelease.getString("release", "").equals("")) {
                    return;
                }
                AppFragmentActivity.this.lm_release = (ArrayList) new Gson().fromJson(AppFragmentActivity.this.prefrelease.getString("release", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {                 }.getType());
                AppFragmentActivity.this._showDialogUpdate();
            }
        }, 3000L);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        _setAppPosition();
        _setBackupPosition();
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
            LayoutInflater layoutInflater = AppFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(C0978R.layout.backup_dialog_listview_folder_picker, (ViewGroup) null);
            }
            TextView textView = (TextView) view.findViewById(C0978R.id.tv_01);
            ImageView imageView = (ImageView) view.findViewById(C0978R.id.im_01);
            textView.setText(Uri.parse(((HashMap) AppFragmentActivity.this.lm_folder_picker.get(i)).get("folder").toString()).getLastPathSegment());
            if (FileUtil.isDirectory(((HashMap) AppFragmentActivity.this.lm_folder_picker.get(i)).get("folder").toString())) {
                imageView.setImageResource(C0978R.drawable.ic_folder);
            }
            if (FileUtil.isFile(((HashMap) AppFragmentActivity.this.lm_folder_picker.get(i)).get("folder").toString())) {
                imageView.setImageResource(C0978R.drawable.ic_file);
            }
            return view;
        }
    }

    public void updateTextRitual() {
        this.vscr_ritual.post(new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                AppFragmentActivity.this.vscr_ritual.fullScroll(130);
            }
        });
    }

        public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
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

    public void _getAppList() {
        _showLoadingMain("Memuat Aplikasi...");
        this.ls_allapps = ApkUtils.getInstalledAppPackages(requireContext());
        this.ls_eternal = new ArrayList<>(GetEternal.findAppsByActivity(requireContext(), "com.fufufu.katrina.eternal.MainActivity"));
        MyAPPLIST myAPPLIST = this.myAPPLIST;
        if (myAPPLIST != null && myAPPLIST.isRunning) {
            this.myAPPLIST.cancelAPPLISTTask();
        }
        MyAPPLIST myAPPLIST2 = new MyAPPLIST(requireContext());
        this.myAPPLIST = myAPPLIST2;
        myAPPLIST2.execute(new Void[0]);
    }

    public class MyAPPLIST extends AsyncTask<Void, Void, Void> {
        private WeakReference<Context> contextReference;
        private boolean isRunning = false;

        public MyAPPLIST(Context context) {
            this.contextReference = new WeakReference<>(context);
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            if (this.contextReference.get() != null) {
                AppFragmentActivity.this.ln_base.setVisibility(8);
                AppFragmentActivity.this.ln_progressbar.setVisibility(0);
                AppFragmentActivity.this.lm_all_app.clear();
                if (AppFragmentActivity.this.preffav.getString("favorite_app_list", "").equals("")) {
                    return;
                }
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity.s_fav_app = appFragmentActivity.preffav.getString("favorite_app_list", "");
                AppFragmentActivity.this.lm_fav_app = (ArrayList) new Gson().fromJson(AppFragmentActivity.this.s_fav_app, new TypeToken<ArrayList<HashMap<String, Object>>>() {                 }.getType());
                SketchwareUtil.sortListMap(AppFragmentActivity.this.lm_fav_app, "appsort", false, true);
            }
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            String str;
            String str2;
            String str3;
            while (!isCancelled()) {
                Context context = this.contextReference.get();
                if (context != null) {
                    PackageManager packageManager = context.getPackageManager();
                    int size = AppFragmentActivity.this.ls_allapps.size();
                    int i = 0;
                    while (i < size) {
                        int i2 = i + 10;
                        int iMin = Math.min(i2, size);
                        while (i < iMin) {
                            String str4 = (String) AppFragmentActivity.this.ls_allapps.get(i);
                            try {
                                str2 = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(str4, 0));
                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                                str2 = str4;
                            }
                            try {
                                str3 = packageManager.getPackageInfo(str4, 0).versionName;
                            } catch (PackageManager.NameNotFoundException e2) {
                                e2.printStackTrace();
                                str3 = "";
                            }
                            AppFragmentActivity.this.m_app = new HashMap();
                            AppFragmentActivity.this.m_app.put("appname", str2);
                            AppFragmentActivity.this.m_app.put("appsort", str2.toLowerCase());
                            AppFragmentActivity.this.m_app.put("apppackage", str4);
                            AppFragmentActivity.this.m_app.put("appversion", str3);
                            AppFragmentActivity.this.lm_all_app.add(AppFragmentActivity.this.m_app);
                            i++;
                        }
                        i = i2;
                    }
                    PackageManager packageManager2 = context.getPackageManager();
                    int size2 = AppFragmentActivity.this.ls_eternal.size();
                    int i3 = 0;
                    while (i3 < size2) {
                        int i4 = i3 + 10;
                        int iMin2 = Math.min(i4, size2);
                        while (i3 < iMin2) {
                            String str5 = (String) AppFragmentActivity.this.ls_eternal.get(i3);
                            try {
                                str = (String) packageManager2.getApplicationLabel(packageManager2.getApplicationInfo(str5, 0));
                            } catch (PackageManager.NameNotFoundException e3) {
                                e3.printStackTrace();
                                str = str5;
                            }
                            AppFragmentActivity.this.m_app = new HashMap();
                            AppFragmentActivity.this.m_app.put("appname", str);
                            AppFragmentActivity.this.m_app.put("apppackage", str5);
                            AppFragmentActivity.this.lm_eternal.add(AppFragmentActivity.this.m_app);
                            i3++;
                        }
                        i3 = i4;
                    }
                    return null;
                }
            }
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r4) {
            this.isRunning = false;
            if (this.contextReference.get() != null) {
                SketchwareUtil.sortListMap(AppFragmentActivity.this.lm_all_app, "appsort", false, true);
                AppFragmentActivity.this.prefall.edit().putString("all_app_list", new Gson().toJson(AppFragmentActivity.this.lm_all_app)).commit();
                AppFragmentActivity.this.prefall.edit().putString("all_app_eternal", new Gson().toJson(AppFragmentActivity.this.lm_eternal)).commit();
                AppFragmentActivity.this.ln_base.setVisibility(0);
                AppFragmentActivity.this.ln_progressbar.setVisibility(8);
            }
        }

        public void cancelAPPLISTTask() {
            cancel(true);
        }
    }

    public void _setFirstUI() {
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
            this.s_commandBaseRestore = strReplace61;
            this.s_commandBase = strReplace61.replace("futhispackage", getContext().getApplicationContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.auto_app.setOnEditorActionListener(new TextView.OnEditorActionListener() {             @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                AppFragmentActivity.this.auto_app.clearFocus();
                return false;
            }
        });
        this.auto_backup.setOnEditorActionListener(new TextView.OnEditorActionListener() {             @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                AppFragmentActivity.this.auto_backup.clearFocus();
                return false;
            }
        });
        this.lv_app.setVerticalScrollBarEnabled(false);
        this.lv_fav.setVerticalScrollBarEnabled(false);
        this.vscr_ritual.setVerticalScrollBarEnabled(false);
        this.lv_backup.setVerticalScrollBarEnabled(false);
        this._fab.setImageResource(C0978R.drawable.ic_fab_list_backup);
        this.fab2.setImageResource(C0978R.drawable.ic_fab_backup);
        this.fabeternal.setImageResource(C0978R.drawable.ic_eternal);
        this.ln_backup_empty.setVisibility(8);
        this.ln_ritual.setVisibility(8);
        this.fabeternal.setVisibility(8);
        this._fab.setVisibility(8);
        this.fab2.setVisibility(8);
        this.lv_app.setVisibility(8);
        this.lv_fav.setVisibility(8);
        this.im_empty.setImageResource(C0978R.drawable.img_not_found);
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf");
        this.mchip_title.setTypeface(typefaceCreateFromAsset);
        this.mchip_info.setTypeface(typefaceCreateFromAsset);
        this.mchip_loc.setTypeface(typefaceCreateFromAsset);
        this.mchip_total.setTypeface(typefaceCreateFromAsset);
        this.mchip_slot.setTypeface(typefaceCreateFromAsset);
    }

    public void _setFromPreferences() {
        if (this.prefui.getString("backup_show_favorite", "").equals("")) {
            this.prefui.edit().putString("backup_show_favorite", "false").commit();
        }
        if (this.prefui.getString("backup_show_favorite", "").equals("true")) {
            this.cv_favorite.setCardBackgroundColor(getResources().getColor(C0978R.color.m3_ref_palette_tertiary80));
            this.lv_fav.setVisibility(0);
            this.lv_app.setVisibility(8);
        } else {
            this.cv_favorite.setCardBackgroundColor(getResources().getColor(C0978R.color.highlighted_text_material_dark));
            this.lv_fav.setVisibility(8);
            this.lv_app.setVisibility(0);
        }
        this.lv_fav.setAdapter((ListAdapter) new Lv_favAdapter(this.lm_fav_app));
        this.lv_app.setAdapter((ListAdapter) new Lv_appAdapter(this.lm_all_app));
        _setFavoriteAppVisibility();
        if (this.prefui.getString("backup_search_type", "").equals("backup")) {
            this.im_search_type.setImageResource(C0978R.drawable.ic_sbackup);
            this.til_app.setVisibility(8);
            this.til_backup.setVisibility(0);
        } else {
            this.prefui.edit().putString("backup_search_type", "application").commit();
            this.im_search_type.setImageResource(C0978R.drawable.ic_sapplication);
            this.til_app.setVisibility(0);
            this.til_backup.setVisibility(8);
        }
        if (this.prefui.getString("backup_sdcard_location", "").equals("")) {
            this.prefui.edit().putString("backup_sdcard_location", "/storage/emulated/0/XKatrina").commit();
            this.mchip_loc.setText(this.prefui.getString("backup_sdcard_location", "").replace("/storage/emulated/0", ""));
        } else {
            this.mchip_loc.setText(this.prefui.getString("backup_sdcard_location", "").replace("/storage/emulated/0", ""));
        }
        if (!FileUtil.isExistFile("/storage/emulated/0/XKatrina")) {
            FileUtil.makeDir("/storage/emulated/0/XKatrina");
        }
        if (this.prefui.getString("backup_app_package", "").equals("")) {
            _hideLoadingMain();
            return;
        }
        this.tv_name_ritual.setText(this.prefui.getString("backup_app_name", ""));
        try {
            String string = this.prefui.getString("backup_app_package", "");
            if (!string.isEmpty()) {
                PackageManager packageManager = getActivity().getPackageManager();
                this.tv_versi_ritual.setText(packageManager.getPackageInfo(string, 0).versionName);
                this.im_app_ritual.setImageDrawable(packageManager.getApplicationIcon(packageManager.getApplicationInfo(string, 0)));
            } else {
                this.tv_versi_ritual.setText("Unknown");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            this.tv_versi_ritual.setText("Unknown");
        }
        _onReadBackup();
        _setBackupTopChip();
    }

    public void _setFavoriteApp() {
        if (this.prefui.getString("backup_show_favorite", "").equals("true")) {
            this.prefui.edit().putString("backup_show_favorite", "false").commit();
            this.cv_favorite.setCardBackgroundColor(getResources().getColor(C0978R.color.highlighted_text_material_dark));
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(requireContext(), C0978R.anim.m3_motion_fade_exit);
            this.cv_favorite.startAnimation(animationLoadAnimation);
            this.cv_favorite.setVisibility(4);
            this.lv_fav.startAnimation(animationLoadAnimation);
            this.lv_fav.setVisibility(8);
            Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(requireContext(), C0978R.anim.m3_motion_fade_enter);
            this.lv_app.startAnimation(animationLoadAnimation2);
            this.lv_app.setVisibility(0);
            this.cv_favorite.startAnimation(animationLoadAnimation2);
            this.cv_favorite.setVisibility(0);
            _setFavoriteAppVisibility();
            return;
        }
        this.prefui.edit().putString("backup_show_favorite", "true").commit();
        this.cv_favorite.setCardBackgroundColor(getResources().getColor(C0978R.color.m3_ref_palette_tertiary80));
        Animation animationLoadAnimation3 = AnimationUtils.loadAnimation(requireContext(), C0978R.anim.m3_motion_fade_exit);
        this.cv_favorite.startAnimation(animationLoadAnimation3);
        this.cv_favorite.setVisibility(4);
        this.lv_app.startAnimation(animationLoadAnimation3);
        this.lv_app.setVisibility(8);
        Animation animationLoadAnimation4 = AnimationUtils.loadAnimation(requireContext(), C0978R.anim.m3_motion_fade_enter);
        this.lv_fav.startAnimation(animationLoadAnimation4);
        this.lv_fav.setVisibility(0);
        this.cv_favorite.startAnimation(animationLoadAnimation4);
        this.cv_favorite.setVisibility(0);
        _setFavoriteAppVisibility();
    }

    public void _setFavoriteAppVisibility() {
        if (this.lv_fav.getVisibility() == 0) {
            this.im_favorite.setImageResource(C0978R.drawable.ic_dislike);
        } else {
            this.im_favorite.setImageResource(C0978R.drawable.ic_like);
        }
    }

    public void _setBackupTopChip() {
        if (this.prefui.getString("backup_app_name", "").equals("")) {
            this.mchip_title.setText("Belum dipilih");
            this.mchip_title.setChipIcon(ContextCompat.getDrawable(requireContext(), C0978R.drawable.ic_application));
        } else {
            String string = this.prefui.getString("backup_app_name", "");
            this.s_top_appname = string;
            if (string.length() > 10) {
                this.s_top_appname = this.s_top_appname.substring(0, 10);
            }
            this.mchip_title.setText(this.s_top_appname);
            try {
                this.mchip_title.setChipIcon(getActivity().getPackageManager().getApplicationIcon(this.prefui.getString("backup_app_package", "")));
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        this.mchip_loc.setText(this.prefui.getString("backup_sdcard_location", "").replace("/storage/emulated/0", ""));
        _setAppPosition();
    }

    public void _setSearchType() {
        this.auto_app.setText("");
        this.auto_backup.setText("");
        if (this.prefui.getString("backup_search_type", "").equals("backup")) {
            this.prefui.edit().putString("backup_search_type", "application").commit();
            this.im_search_type.setImageResource(C0978R.drawable.ic_sapplication);
            this.til_backup.setVisibility(8);
            this.til_app.setVisibility(0);
            this.auto_backup.setEnabled(false);
            this.auto_app.setEnabled(true);
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Type pencarian aplikasi");
        } else {
            this.prefui.edit().putString("backup_search_type", "backup").commit();
            this.im_search_type.setImageResource(C0978R.drawable.ic_sbackup);
            this.til_backup.setVisibility(0);
            this.til_app.setVisibility(8);
            this.auto_app.setEnabled(false);
            this.auto_backup.setEnabled(true);
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Type pencarian backup");
        }
        if (this.prefui.getString("backup_show_favorite", "").equals("true")) {
            this.im_favorite.setImageResource(C0978R.drawable.ic_dislike);
        } else {
            this.im_favorite.setImageResource(C0978R.drawable.ic_like);
        }
    }

    public void _setInvalidateBackup() {
        this.lv_backup.invalidateViews();
    }

    public void _showPopupFav(final double d, final String str, final String str2, final String str3, View view) {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_popup_app, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(viewInflate, -2, -2, true);
        ((TextView) viewInflate.findViewById(C0978R.id.tv_title_popup)).setText(str2.concat(" (".concat(str3.concat(")"))));
        ((TextView) viewInflate.findViewById(C0978R.id.tv_subtitle_popup)).setText(str);
        ImageView imageView = (ImageView) viewInflate.findViewById(C0978R.id.im_01);
        ImageView imageView2 = (ImageView) viewInflate.findViewById(C0978R.id.im_02);
        ImageView imageView3 = (ImageView) viewInflate.findViewById(C0978R.id.im_03);
        ImageView imageView4 = (ImageView) viewInflate.findViewById(C0978R.id.im_04);
        ImageView imageView5 = (ImageView) viewInflate.findViewById(C0978R.id.im_05);
        ImageView imageView6 = (ImageView) viewInflate.findViewById(C0978R.id.im_06);
        ImageView imageView7 = (ImageView) viewInflate.findViewById(C0978R.id.im_07);
        ImageView imageView8 = (ImageView) viewInflate.findViewById(C0978R.id.im_08);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_im5);
        LinearLayout linearLayout2 = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_im6);
        MaterialCardView materialCardView = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv1);
        MaterialCardView materialCardView2 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv2);
        MaterialCardView materialCardView3 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv3);
        MaterialCardView materialCardView4 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv4);
        MaterialCardView materialCardView5 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv5);
        MaterialCardView materialCardView6 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv6);
        MaterialCardView materialCardView7 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv7);
        MaterialCardView materialCardView8 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv8);
        imageView.setImageResource(C0978R.drawable.ic_dislike);
        imageView2.setImageResource(C0978R.drawable.ic_extract);
        imageView3.setImageResource(C0978R.drawable.ic_share);
        imageView4.setImageResource(C0978R.drawable.ic_open_app);
        imageView5.setImageResource(C0978R.drawable.ic_uninstall);
        imageView6.setImageResource(C0978R.drawable.ic_reinstall);
        imageView7.setImageResource(C0978R.drawable.ic_wipedata);
        imageView8.setImageResource(C0978R.drawable.ic_info);
        try {
            if ((requireContext().getPackageManager().getApplicationInfo(str, 0).flags & 1) != 0) {
                linearLayout.setAlpha(0.4f);
                linearLayout2.setAlpha(0.4f);
                linearLayout.setEnabled(false);
                linearLayout2.setEnabled(false);
            } else {
                linearLayout.setAlpha(1.0f);
                linearLayout2.setAlpha(1.0f);
                linearLayout.setEnabled(true);
                linearLayout2.setEnabled(true);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.showAsDropDown(view, 120, -230);
        materialCardView.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (!AppFragmentActivity.this.auto_app.getText().toString().equals("")) {
                    AppFragmentActivity.this.n_pos = 0.0d;
                    int i = 0;
                    while (true) {
                        if (i >= AppFragmentActivity.this.lm_fav_app.size()) {
                            break;
                        }
                        if (((HashMap) AppFragmentActivity.this.lm_search_app.get((int) d)).get("apppackage").toString().equals(((HashMap) AppFragmentActivity.this.lm_fav_app.get((int) AppFragmentActivity.this.n_pos)).get("apppackage").toString())) {
                            AppFragmentActivity.this.lm_fav_app.remove((int) AppFragmentActivity.this.n_pos);
                            AppFragmentActivity.this.lm_search_app.remove((int) d);
                            AppFragmentActivity.this.preffav.edit().putString("favorite_app_list", new Gson().toJson(AppFragmentActivity.this.lm_fav_app)).commit();
                            AppFragmentActivity.this.lv_fav.invalidateViews();
                            break;
                        }
                        AppFragmentActivity.this.n_pos += 1.0d;
                        i++;
                    }
                    popupWindow.dismiss();
                    return;
                }
                AppFragmentActivity.this.lm_fav_app.remove((int) d);
                AppFragmentActivity.this.preffav.edit().putString("favorite_app_list", new Gson().toJson(AppFragmentActivity.this.lm_fav_app)).commit();
                AppFragmentActivity.this.lv_fav.invalidateViews();
                popupWindow.dismiss();
            }
        });
        materialCardView2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionSavePackage(str, str2, str3);
                popupWindow.dismiss();
            }
        });
        materialCardView3.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionSharePackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView4.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionOpenPackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView5.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this.n_position_app = d;
                AppFragmentActivity.this._actionUninstallPackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView6.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionReinstallPackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView7.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionWipePackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView8.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionInfoPackage(str);
                popupWindow.dismiss();
            }
        });
    }

    public void _showPopupApp(final double d, final String str, final String str2, final String str3, View view) {
        ImageView imageView;
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_popup_app, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(viewInflate, -2, -2, true);
        ((TextView) viewInflate.findViewById(C0978R.id.tv_title_popup)).setText(str2.concat(" (".concat(str3.concat(")"))));
        ((TextView) viewInflate.findViewById(C0978R.id.tv_subtitle_popup)).setText(str);
        ImageView imageView2 = (ImageView) viewInflate.findViewById(C0978R.id.im_01);
        ImageView imageView3 = (ImageView) viewInflate.findViewById(C0978R.id.im_02);
        ImageView imageView4 = (ImageView) viewInflate.findViewById(C0978R.id.im_03);
        ImageView imageView5 = (ImageView) viewInflate.findViewById(C0978R.id.im_04);
        ImageView imageView6 = (ImageView) viewInflate.findViewById(C0978R.id.im_05);
        ImageView imageView7 = (ImageView) viewInflate.findViewById(C0978R.id.im_06);
        ImageView imageView8 = (ImageView) viewInflate.findViewById(C0978R.id.im_07);
        ImageView imageView9 = (ImageView) viewInflate.findViewById(C0978R.id.im_08);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_im5);
        LinearLayout linearLayout2 = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_im6);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_011);
        MaterialCardView materialCardView = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv1);
        MaterialCardView materialCardView2 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv2);
        MaterialCardView materialCardView3 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv3);
        MaterialCardView materialCardView4 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv4);
        MaterialCardView materialCardView5 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv5);
        MaterialCardView materialCardView6 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv6);
        MaterialCardView materialCardView7 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv7);
        MaterialCardView materialCardView8 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv8);
        if (this.lm_fav_app.isEmpty()) {
            imageView2.setImageResource(C0978R.drawable.ic_like);
            this.b_fav = false;
            textView.setText("Pin");
            imageView = imageView3;
        } else {
            imageView = imageView3;
            this.n_pos = 0.0d;
            int i = 0;
            while (true) {
                if (i >= this.lm_fav_app.size()) {
                    break;
                }
                TextView textView2 = textView;
                if (this.lm_fav_app.get((int) this.n_pos).get("apppackage").toString().equals(str)) {
                    imageView2.setImageResource(C0978R.drawable.ic_dislike);
                    textView2.setText("Unpin");
                    this.b_fav = true;
                    break;
                } else {
                    imageView2.setImageResource(C0978R.drawable.ic_like);
                    textView2.setText("Pin");
                    this.b_fav = false;
                    this.n_pos += 1.0d;
                    i++;
                    textView = textView2;
                }
            }
        }
        imageView.setImageResource(C0978R.drawable.ic_extract);
        imageView4.setImageResource(C0978R.drawable.ic_share);
        imageView5.setImageResource(C0978R.drawable.ic_open_app);
        imageView6.setImageResource(C0978R.drawable.ic_uninstall);
        imageView7.setImageResource(C0978R.drawable.ic_reinstall);
        imageView8.setImageResource(C0978R.drawable.ic_wipedata);
        imageView9.setImageResource(C0978R.drawable.ic_info);
        try {
            if ((requireContext().getPackageManager().getApplicationInfo(str, 0).flags & 1) != 0) {
                linearLayout.setAlpha(0.4f);
                linearLayout2.setAlpha(0.4f);
                linearLayout.setEnabled(false);
                linearLayout2.setEnabled(false);
            } else {
                linearLayout.setAlpha(1.0f);
                linearLayout2.setAlpha(1.0f);
                linearLayout.setEnabled(true);
                linearLayout2.setEnabled(true);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.showAsDropDown(view, 120, -170);
        materialCardView.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                int i2 = 0;
                if (AppFragmentActivity.this.b_fav) {
                    AppFragmentActivity.this.n_pos = 0.0d;
                    while (true) {
                        if (i2 >= AppFragmentActivity.this.lm_fav_app.size()) {
                            break;
                        }
                        if (((HashMap) AppFragmentActivity.this.lm_fav_app.get((int) AppFragmentActivity.this.n_pos)).get("apppackage").toString().equals(str)) {
                            AppFragmentActivity.this.lm_fav_app.remove((int) AppFragmentActivity.this.n_pos);
                            AppFragmentActivity.this.preffav.edit().putString("favorite_app_list", new Gson().toJson(AppFragmentActivity.this.lm_fav_app)).commit();
                            ListView listView = AppFragmentActivity.this.lv_fav;
                            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                            listView.setAdapter((ListAdapter) appFragmentActivity.new Lv_favAdapter(appFragmentActivity.lm_fav_app));
                            break;
                        }
                        AppFragmentActivity.this.n_pos += 1.0d;
                        i2++;
                    }
                } else {
                    AppFragmentActivity.this.m_fav = new HashMap();
                    AppFragmentActivity.this.m_fav.put("appname", str2);
                    AppFragmentActivity.this.m_fav.put("apppackage", str);
                    AppFragmentActivity.this.m_fav.put("appversion", str3);
                    AppFragmentActivity.this.m_fav.put("appsort", str2.toLowerCase());
                    AppFragmentActivity.this.lm_fav_app.add(AppFragmentActivity.this.m_fav);
                    SketchwareUtil.sortListMap(AppFragmentActivity.this.lm_fav_app, "appsort", false, true);
                    AppFragmentActivity.this.preffav.edit().putString("favorite_app_list", new Gson().toJson(AppFragmentActivity.this.lm_fav_app)).commit();
                    ListView listView2 = AppFragmentActivity.this.lv_fav;
                    AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
                    listView2.setAdapter((ListAdapter) appFragmentActivity2.new Lv_favAdapter(appFragmentActivity2.lm_fav_app));
                }
                popupWindow.dismiss();
            }
        });
        materialCardView2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionSavePackage(str, str2, str3);
                popupWindow.dismiss();
            }
        });
        materialCardView3.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionSharePackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView4.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionOpenPackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView5.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this.n_position_app = d;
                AppFragmentActivity.this._actionUninstallPackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView6.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionReinstallPackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView7.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionWipePackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView8.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionInfoPackage(str);
                popupWindow.dismiss();
            }
        });
    }

    public void _showPopupBackup(final double d, final ArrayList<HashMap<String, Object>> arrayList, View view) {
        int i = (int) d;
        this.s_path_json = FileUtil.readFile(arrayList.get(i).get("appjson").toString());
        this.m_path_json = (HashMap) new Gson().fromJson(this.s_path_json, new TypeToken<HashMap<String, Object>>() {         }.getType());
        if (arrayList.get(i).get("appmark").toString().equals("false")) {
            View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_popup_mark, (ViewGroup) null);
            final PopupWindow popupWindow = new PopupWindow(viewInflate, -2, -2, true);
            ((TextView) viewInflate.findViewById(C0978R.id.tv_title_mark)).setText("Tandai");
            ImageView imageView = (ImageView) viewInflate.findViewById(C0978R.id.im_01);
            ImageView imageView2 = (ImageView) viewInflate.findViewById(C0978R.id.im_02);
            ImageView imageView3 = (ImageView) viewInflate.findViewById(C0978R.id.im_03);
            ImageView imageView4 = (ImageView) viewInflate.findViewById(C0978R.id.im_04);
            ImageView imageView5 = (ImageView) viewInflate.findViewById(C0978R.id.im_05);
            ImageView imageView6 = (ImageView) viewInflate.findViewById(C0978R.id.im_06);
            ImageView imageView7 = (ImageView) viewInflate.findViewById(C0978R.id.im_07);
            ImageView imageView8 = (ImageView) viewInflate.findViewById(C0978R.id.im_08);
            ImageView imageView9 = (ImageView) viewInflate.findViewById(C0978R.id.im_09);
            ImageView imageView10 = (ImageView) viewInflate.findViewById(C0978R.id.im_010);
            ImageView imageView11 = (ImageView) viewInflate.findViewById(C0978R.id.im_011);
            ImageView imageView12 = (ImageView) viewInflate.findViewById(C0978R.id.im_012);
            ImageView imageView13 = (ImageView) viewInflate.findViewById(C0978R.id.im_013);
            ImageView imageView14 = (ImageView) viewInflate.findViewById(C0978R.id.im_014);
            ImageView imageView15 = (ImageView) viewInflate.findViewById(C0978R.id.im_015);
            imageView.setVisibility(4);
            imageView2.setVisibility(4);
            imageView3.setVisibility(4);
            imageView4.setVisibility(4);
            imageView5.setVisibility(4);
            imageView6.setVisibility(4);
            imageView7.setVisibility(4);
            imageView8.setVisibility(4);
            imageView9.setVisibility(4);
            imageView10.setVisibility(4);
            imageView11.setVisibility(4);
            imageView12.setVisibility(4);
            imageView13.setVisibility(4);
            imageView14.setVisibility(4);
            imageView15.setVisibility(4);
            MaterialCardView materialCardView = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv1);
            MaterialCardView materialCardView2 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv2);
            MaterialCardView materialCardView3 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv3);
            MaterialCardView materialCardView4 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv4);
            MaterialCardView materialCardView5 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv5);
            MaterialCardView materialCardView6 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv6);
            MaterialCardView materialCardView7 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv7);
            MaterialCardView materialCardView8 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv8);
            MaterialCardView materialCardView9 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv9);
            MaterialCardView materialCardView10 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv10);
            MaterialCardView materialCardView11 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv11);
            MaterialCardView materialCardView12 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv12);
            MaterialCardView materialCardView13 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv13);
            MaterialCardView materialCardView14 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv14);
            MaterialCardView materialCardView15 = (MaterialCardView) viewInflate.findViewById(C0978R.id.mcv15);
            popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
            popupWindow.showAsDropDown(view, 0, -160);
            materialCardView.setCardBackgroundColor(Color.parseColor("#FFE8DFF5"));
            materialCardView2.setCardBackgroundColor(Color.parseColor("#FFFCE1E4"));
            materialCardView3.setCardBackgroundColor(Color.parseColor("#FFFCF4DD"));
            materialCardView4.setCardBackgroundColor(Color.parseColor("#FFDDEDEA"));
            materialCardView5.setCardBackgroundColor(Color.parseColor("#FFDAEAF6"));
            materialCardView6.setCardBackgroundColor(Color.parseColor("#FFC8B2EB"));
            materialCardView7.setCardBackgroundColor(Color.parseColor("#FFFBADAB"));
            materialCardView8.setCardBackgroundColor(Color.parseColor("#FFFADF7E"));
            materialCardView9.setCardBackgroundColor(Color.parseColor("#FFBBD9C1"));
            materialCardView10.setCardBackgroundColor(Color.parseColor("#FF80B7FF"));
            materialCardView11.setCardBackgroundColor(Color.parseColor("#FF9866E8"));
            materialCardView12.setCardBackgroundColor(Color.parseColor("#FFF9635F"));
            materialCardView13.setCardBackgroundColor(Color.parseColor("#FFFAD241"));
            materialCardView14.setCardBackgroundColor(Color.parseColor("#FF5CD574"));
            materialCardView15.setCardBackgroundColor(Color.parseColor("#FF4294FF"));
            materialCardView.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFE8DFF5");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFE8DFF5");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView2.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFFCE1E4");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFFCE1E4");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView3.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFFCF4DD");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFFCF4DD");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView4.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFDDEDEA");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFDDEDEA");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView5.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFDAEAF6");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFDAEAF6");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView6.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFC8B2EB");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFC8B2EB");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView7.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFFBADAB");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFFBADAB");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView8.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFFADF7E");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFFADF7E");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView9.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFBBD9C1");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFBBD9C1");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView10.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FF80B7FF");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FF80B7FF");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView11.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FF9866E8");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FF9866E8");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView12.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFF9635F");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFF9635F");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView13.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFFAD241");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFFAD241");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView14.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FF5CD574");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FF5CD574");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView15.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FF4294FF");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FF4294FF");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            return;
        }
        this.m_path_json.put("MARK", "false");
        arrayList.get(i).put("appmark", "false");
        this.s_path_json = new Gson().toJson(this.m_path_json);
        Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
        FileUtil.writeFile(arrayList.get(i).get("appjson").toString(), gsonCreate.toJson(gsonCreate.fromJson(this.s_path_json, Object.class)));
        _setInvalidateBackup();
    }

    public void _showInfoBackup() {
        showINFOBACKUP();
    }

    private void showINFOBACKUP() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_dialog_backup_info, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(true);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_01);
        ScrollView scrollView = (ScrollView) viewInflate.findViewById(C0978R.id.vscr_1);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_1);
        TableLayout tableLayout = new TableLayout(getActivity());
        scrollView.setVerticalScrollBarEnabled(false);
        Map<?,?> map = (Map<?,?>) new Gson().fromJson(this.s_path_json, TreeMap.class);
        tableLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        for (Map.Entry<?,?> entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            TableRow tableRow = new TableRow(getActivity());
            tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -2));
            TextView textView = new TextView(getActivity());
            textView.setText(str);
            textView.setPadding(0, 10, 0, 10);
            textView.setLayoutParams(new TableRow.LayoutParams(-2, -2));
            textView.setTextSize(12.0f);
            tableRow.addView(textView);
            TextView textView2 = new TextView(getActivity());
            textView2.setText("  : ");
            textView2.setPadding(0, 10, 0, 10);
            textView2.setLayoutParams(new TableRow.LayoutParams(-2, -2));
            textView2.setTextSize(12.0f);
            tableRow.addView(textView2);
            TextView textView3 = new TextView(getActivity());
            textView3.setText(str2);
            textView3.setPadding(0, 10, 0, 10);
            textView3.setLayoutParams(new TableRow.LayoutParams(-2, -2));
            textView3.setTextSize(12.0f);
            tableRow.addView(textView3);
            tableLayout.addView(tableRow);
        }
        linearLayout.addView(tableLayout);
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.INFOBACKUP.dismiss();
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.INFOBACKUP = alertDialogCreate;
        alertDialogCreate.show();
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
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_back);
        Button button2 = (Button) viewInflate.findViewById(C0978R.id.btn_cancel);
        Button button3 = (Button) viewInflate.findViewById(C0978R.id.btn_oke);
        final LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_folder);
        final LinearLayout linearLayout2 = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_loading);
        this.b_folder_scan = false;
        this.s_folder_picker = FileUtil.getExternalStorageDir();
        linearLayout.setVisibility(8);
        linearLayout2.setVisibility(0);
        _onTaskFolderPicker();
        Runnable runnable = new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                if (AppFragmentActivity.this.b_folder_scan) {
                    AppFragmentActivity.this.b_folder_scan = false;
                    listView.setDivider(null);
                    listView.setDividerHeight(0);
                    ListView listView2 = listView;
                    AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                    listView2.setAdapter((ListAdapter) appFragmentActivity.new lv_folder_pickerAdapter(appFragmentActivity.lm_folder_picker));
                    ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                    listView.setVerticalScrollBarEnabled(false);
                    textView.setText(AppFragmentActivity.this.s_folder_picker);
                    linearLayout.setVisibility(0);
                    linearLayout2.setVisibility(8);
                    AppFragmentActivity.this.folderone.removeCallbacks(AppFragmentActivity.this.runnablefolderone);
                    return;
                }
                AppFragmentActivity.this.folderone.postDelayed(AppFragmentActivity.this.runnablefolderone, 100L);
            }
        };
        this.runnablefolderone = runnable;
        this.folderone.postDelayed(runnable, 0L);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {             @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (FileUtil.isDirectory((String) AppFragmentActivity.this.ls_folder_picker.get(i))) {
                    File file = new File((String) AppFragmentActivity.this.ls_folder_picker.get(i));
                    if (file.exists() && file.isDirectory()) {
                        File[] fileArrListFiles = file.listFiles();
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                            appFragmentActivity.s_folder_picker = (String) appFragmentActivity.ls_folder_picker.get(i);
                            linearLayout.setVisibility(8);
                            linearLayout2.setVisibility(0);
                            AppFragmentActivity.this._onTaskFolderPicker();
                            AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
                            final ListView listView2 = listView;
                            final TextView textView2 = textView;
                            final LinearLayout linearLayout3 = linearLayout;
                            final LinearLayout linearLayout4 = linearLayout2;
                            appFragmentActivity2.runnablefoldertwo = new Runnable() {                                 @Override // java.lang.Runnable
                                public void run() {
                                    if (AppFragmentActivity.this.b_folder_scan) {
                                        AppFragmentActivity.this.b_folder_scan = false;
                                        listView2.setDivider(null);
                                        listView2.setDividerHeight(0);
                                        listView2.setAdapter((ListAdapter) AppFragmentActivity.this.new lv_folder_pickerAdapter(AppFragmentActivity.this.lm_folder_picker));
                                        ((BaseAdapter) listView2.getAdapter()).notifyDataSetChanged();
                                        listView2.setVerticalScrollBarEnabled(false);
                                        textView2.setText(AppFragmentActivity.this.s_folder_picker);
                                        linearLayout3.setVisibility(0);
                                        linearLayout4.setVisibility(8);
                                        AppFragmentActivity.this.foldertwo.removeCallbacks(AppFragmentActivity.this.runnablefoldertwo);
                                        return;
                                    }
                                    AppFragmentActivity.this.foldertwo.postDelayed(AppFragmentActivity.this.runnablefoldertwo, 100L);
                                }
                            };
                            AppFragmentActivity.this.foldertwo.postDelayed(AppFragmentActivity.this.runnablefoldertwo, 0L);
                            return;
                        }
                        SketchwareUtil.showMessage(AppFragmentActivity.this.getContext().getApplicationContext(), "Folder ini kosong");
                        return;
                    }
                    return;
                }
                SketchwareUtil.showMessage(AppFragmentActivity.this.getContext().getApplicationContext(), "Hanya bisa memilih folder");
            }
        });
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AppFragmentActivity.this.s_folder_picker.equals("/storage/emulated/0")) {
                    SketchwareUtil.showMessage(AppFragmentActivity.this.getContext().getApplicationContext(), "Kamu sudah sampe di awal");
                    return;
                }
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity.s_folder_picker = appFragmentActivity.s_folder_picker.substring(0, (AppFragmentActivity.this.s_folder_picker.length() - Uri.parse(AppFragmentActivity.this.s_folder_picker).getLastPathSegment().length()) - 1);
                linearLayout.setVisibility(8);
                linearLayout2.setVisibility(0);
                AppFragmentActivity.this._onTaskFolderPicker();
                AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
                final ListView listView2 = listView;
                final TextView textView2 = textView;
                final LinearLayout linearLayout3 = linearLayout;
                final LinearLayout linearLayout4 = linearLayout2;
                appFragmentActivity2.runnablefoldertri = new Runnable() {                     @Override // java.lang.Runnable
                    public void run() {
                        if (AppFragmentActivity.this.b_folder_scan) {
                            AppFragmentActivity.this.b_folder_scan = false;
                            listView2.setDivider(null);
                            listView2.setDividerHeight(0);
                            listView2.setAdapter((ListAdapter) AppFragmentActivity.this.new lv_folder_pickerAdapter(AppFragmentActivity.this.lm_folder_picker));
                            ((BaseAdapter) listView2.getAdapter()).notifyDataSetChanged();
                            listView2.setVerticalScrollBarEnabled(false);
                            textView2.setText(AppFragmentActivity.this.s_folder_picker);
                            linearLayout3.setVisibility(0);
                            linearLayout4.setVisibility(8);
                            AppFragmentActivity.this.foldertri.removeCallbacks(AppFragmentActivity.this.runnablefoldertri);
                            return;
                        }
                        AppFragmentActivity.this.foldertri.postDelayed(AppFragmentActivity.this.runnablefoldertri, 100L);
                    }
                };
                AppFragmentActivity.this.foldertri.postDelayed(AppFragmentActivity.this.runnablefoldertri, 0L);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.SHOWFOLDER.dismiss();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.prefui.edit().putString("backup_sdcard_location", AppFragmentActivity.this.s_folder_picker).commit();
                AppFragmentActivity.this.SHOWFOLDER.dismiss();
                AppFragmentActivity.this._onReadBackup();
                AppFragmentActivity.this._setBackupTopChip();
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.SHOWFOLDER = alertDialogCreate;
        alertDialogCreate.show();
    }

    public void _showEditNote() {
        showEDITNOTE();
    }

    private void showEDITNOTE() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_dialog_editnote, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(true);
        if (this.auto_backup.getText().toString().equals("")) {
            this.s_note = this.lm_backup_data.get((int) this.n_position).get("appnote").toString();
            this.s_path_json = FileUtil.readFile(this.lm_backup_data.get((int) this.n_position).get("appjson").toString());
        } else {
            this.s_note = this.lm_search_backup.get((int) this.n_position).get("appnote").toString();
            this.s_path_json = FileUtil.readFile(this.lm_search_backup.get((int) this.n_position).get("appjson").toString());
        }
        this.m_path_json = (HashMap) new Gson().fromJson(this.s_path_json, new TypeToken<HashMap<String, Object>>() {         }.getType());
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) viewInflate.findViewById(C0978R.id.auto_1);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_cancel);
        Button button2 = (Button) viewInflate.findViewById(C0978R.id.btn_oke);
        autoCompleteTextView.setFocusable(true);
        autoCompleteTextView.setFocusableInTouchMode(true);
        autoCompleteTextView.setText(this.s_note);
        autoCompleteTextView.setHint("Catatan");
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.EDITNOTE.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AppFragmentActivity.this.auto_backup.getText().toString().equals("")) {
                    AppFragmentActivity.this.m_path_json.put("NOTE", autoCompleteTextView.getText().toString());
                    ((HashMap) AppFragmentActivity.this.lm_backup_data.get((int) AppFragmentActivity.this.n_position)).put("appnote", autoCompleteTextView.getText().toString());
                } else {
                    AppFragmentActivity.this.m_path_json.put("NOTE", autoCompleteTextView.getText().toString());
                    ((HashMap) AppFragmentActivity.this.lm_search_backup.get((int) AppFragmentActivity.this.n_position)).put("appnote", autoCompleteTextView.getText().toString());
                    Iterator it = AppFragmentActivity.this.lm_backup_data.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        HashMap map = (HashMap) it.next();
                        Object obj = map.get("appfile");
                        if (obj != null && obj.toString().equals(((HashMap) AppFragmentActivity.this.lm_search_backup.get((int) AppFragmentActivity.this.n_position)).get("appfile").toString())) {
                            map.put("appnote", autoCompleteTextView.getText().toString());
                            break;
                        }
                    }
                }
                AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                String json = gsonCreate.toJson(gsonCreate.fromJson(AppFragmentActivity.this.s_path_json, Object.class));
                if (AppFragmentActivity.this.auto_backup.getText().toString().equals("")) {
                    FileUtil.writeFile(((HashMap) AppFragmentActivity.this.lm_backup_data.get((int) AppFragmentActivity.this.n_position)).get("appjson").toString(), json);
                } else {
                    FileUtil.writeFile(((HashMap) AppFragmentActivity.this.lm_search_backup.get((int) AppFragmentActivity.this.n_position)).get("appjson").toString(), json);
                }
                AppFragmentActivity.this._setInvalidateBackup();
                AppFragmentActivity.this.EDITNOTE.dismiss();
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.EDITNOTE = alertDialogCreate;
        alertDialogCreate.show();
    }

    public void _showDialogRitual() {
        this.tv_ritual_result.setText("");
        this.tv_status.setText("");
        this.tv_status.setVisibility(4);
        this.pbar_ritual.setVisibility(4);
        if (this.ln_ritual.getVisibility() == 8) {
            this.b_ritual = true;
            if (this.prefui.getString("ritual_wipe_app", "").equals("true")) {
                this.switch_wipe.setChecked(true);
            } else {
                this.switch_wipe.setChecked(false);
            }
            if (this.prefui.getString("ritual_reinstall_app", "").equals("true")) {
                this.switch_reinstall.setChecked(true);
            } else {
                this.switch_reinstall.setChecked(false);
            }
            if (this.prefui.getString("ritual_wipe_gms", "").equals("true")) {
                this.switch_gms.setChecked(true);
            } else {
                this.switch_gms.setChecked(false);
            }
            if (this.prefui.getString("ritual_clean_timepick", "").equals("true")) {
                this.switch_timepick.setChecked(true);
            } else {
                this.switch_timepick.setChecked(false);
            }
            this._fab.setImageResource(C0978R.drawable.ic_fab_list_ritual);
            this.fab2.setImageResource(C0978R.drawable.ic_fab_ritual);
            this.ln_backup.startAnimation(AnimationUtils.loadAnimation(requireContext(), C0978R.anim.m3_motion_fade_exit));
            this.ln_backup.setVisibility(8);
            this.ln_ritual.startAnimation(AnimationUtils.loadAnimation(requireContext(), C0978R.anim.m3_motion_fade_enter));
            this.ln_ritual.setVisibility(0);
            return;
        }
        this.b_ritual = false;
        this._fab.setImageResource(C0978R.drawable.ic_fab_list_backup);
        this.fab2.setImageResource(C0978R.drawable.ic_fab_backup);
        this.ln_ritual.startAnimation(AnimationUtils.loadAnimation(requireContext(), C0978R.anim.m3_motion_fade_exit));
        this.ln_ritual.setVisibility(8);
        this.ln_backup.startAnimation(AnimationUtils.loadAnimation(requireContext(), C0978R.anim.m3_motion_fade_enter));
        this.ln_backup.setVisibility(0);
    }

    public void _showUniversalProgress() {
        showUNIVERSAL();
    }

    private void showUNIVERSAL() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_dialog_uni_no_button, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        ((TextView) viewInflate.findViewById(C0978R.id.tv_uni_dialog)).setText(this.s_universal_progress);
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.UNIVERSAL = alertDialogCreate;
        alertDialogCreate.show();
    }

    public void _hideUniversalProgress() {
        AlertDialog alertDialog = this.UNIVERSAL;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.UNIVERSAL.dismiss();
    }

    public void _actionUninstallPackage(String str) {
        this.s_universal_progress = str;
        showUAPP();
    }

    private void showUAPP() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_dialog_uni_two_button, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_title);
        TextView textView2 = (TextView) viewInflate.findViewById(C0978R.id.tv_message);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_cancel);
        Button button2 = (Button) viewInflate.findViewById(C0978R.id.btn_oke);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_button);
        LinearLayout linearLayout2 = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_progress);
        textView.setText("UNINSTALL");
        textView2.setText("Uninstall aplikasi \n".concat(this.s_universal_progress));
        linearLayout2.setVisibility(8);
        linearLayout.setVisibility(0);
        button.setVisibility(0);
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.UAPP.dismiss();
            }
        });
        button2.setOnClickListener(new ViewOnClickListenerC068170(linearLayout2, linearLayout, textView2, textView, button, button2));
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.UAPP = alertDialogCreate;
        alertDialogCreate.show();
    }

        class ViewOnClickListenerC068170 implements View.OnClickListener {
        private final /* synthetic */ Button val$btn_cancel;
        private final /* synthetic */ Button val$btn_oke;
        private final /* synthetic */ LinearLayout val$ln_button;
        private final /* synthetic */ LinearLayout val$ln_progress;
        private final /* synthetic */ TextView val$tv_message;
        private final /* synthetic */ TextView val$tv_title;

        ViewOnClickListenerC068170(LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, Button button, Button button2) {
            this.val$ln_progress = linearLayout;
            this.val$ln_button = linearLayout2;
            this.val$tv_message = textView;
            this.val$tv_title = textView2;
            this.val$btn_cancel = button;
            this.val$btn_oke = button2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.val$ln_progress.setVisibility(0);
            this.val$ln_button.setVisibility(8);
            this.val$tv_message.setText("Menguninstall aplikasi \n".concat(AppFragmentActivity.this.s_universal_progress));
            AppFragmentActivity.this.s_command = "app_package=\"fupackagename\"\n\nif pm list packages | grep -q $app_package; then\nif pm uninstall $app_package > /dev/null 2>&1; then\necho -n \"Sukses menguninstal aplikasi\"\nelse\necho -n \"Gagal menguninstal aplikasi\"\nfi\nelse\necho -n \"Aplikasi tidak ditemukan\"\nfi";
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_command = appFragmentActivity.s_command.replace("fupackagename", AppFragmentActivity.this.s_universal_progress);
            AppFragmentActivity.this.b_command = false;
            AppFragmentActivity.this.s_commandResult = "";
            AppFragmentActivity.this.s_exitCode = "";
            AppFragmentActivity.this._OnBackgroundAction();
            AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
            final TextView textView = this.val$tv_title;
            final TextView textView2 = this.val$tv_message;
            final LinearLayout linearLayout = this.val$ln_button;
            final Button button = this.val$btn_cancel;
            final LinearLayout linearLayout2 = this.val$ln_progress;
            final Button button2 = this.val$btn_oke;
            appFragmentActivity2.runnableUNINSTALLAPP = new Runnable() {                 @Override // java.lang.Runnable
                public void run() {
                    if (AppFragmentActivity.this.b_command) {
                        AppFragmentActivity.this.UNINSTALLAPP.removeCallbacks(AppFragmentActivity.this.runnableUNINSTALLAPP);
                        AppFragmentActivity.this.b_command = false;
                        if (AppFragmentActivity.this.s_commandResult.contains("Sukses")) {
                            AppFragmentActivity.this.n_pos = 0.0d;
                            int i = 0;
                            while (true) {
                                if (i >= AppFragmentActivity.this.lm_all_app.size()) {
                                    break;
                                }
                                if (((HashMap) AppFragmentActivity.this.lm_all_app.get((int) AppFragmentActivity.this.n_pos)).get("apppackage").toString().equals(AppFragmentActivity.this.s_universal_progress)) {
                                    AppFragmentActivity.this.lm_all_app.remove((int) AppFragmentActivity.this.n_pos);
                                    break;
                                } else {
                                    AppFragmentActivity.this.n_pos += 1.0d;
                                    i++;
                                }
                            }
                            AppFragmentActivity.this.n_pos = 0.0d;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= AppFragmentActivity.this.lm_fav_app.size()) {
                                    break;
                                }
                                if (((HashMap) AppFragmentActivity.this.lm_fav_app.get((int) AppFragmentActivity.this.n_pos)).get("apppackage").toString().equals(AppFragmentActivity.this.s_universal_progress)) {
                                    AppFragmentActivity.this.lm_fav_app.remove((int) AppFragmentActivity.this.n_pos);
                                    break;
                                } else {
                                    AppFragmentActivity.this.n_pos += 1.0d;
                                    i2++;
                                }
                            }
                            AppFragmentActivity.this.n_pos = 0.0d;
                            int i3 = 0;
                            while (true) {
                                if (i3 >= AppFragmentActivity.this.lm_search_app.size()) {
                                    break;
                                }
                                if (((HashMap) AppFragmentActivity.this.lm_search_app.get((int) AppFragmentActivity.this.n_pos)).get("apppackage").toString().equals(AppFragmentActivity.this.s_universal_progress)) {
                                    AppFragmentActivity.this.lm_search_app.remove((int) AppFragmentActivity.this.n_pos);
                                    break;
                                } else {
                                    AppFragmentActivity.this.n_pos += 1.0d;
                                    i3++;
                                }
                            }
                            if (AppFragmentActivity.this.prefui.getString("backup_app_package", "").equals(AppFragmentActivity.this.s_universal_progress)) {
                                AppFragmentActivity.this.prefui.edit().putString("backup_app_name", "").commit();
                                AppFragmentActivity.this.prefui.edit().putString("backup_app_package", "").commit();
                                AppFragmentActivity.this._onReadBackup();
                                AppFragmentActivity.this._setBackupTopChip();
                            }
                            AppFragmentActivity.this.preffav.edit().putString("favorite_app_list", new Gson().toJson(AppFragmentActivity.this.lm_fav_app)).commit();
                            AppFragmentActivity.this.lv_app.invalidateViews();
                            textView.setText("UNINSTALL SUKSES");
                            textView2.setText(AppFragmentActivity.this.s_commandResult.concat("\n".concat(AppFragmentActivity.this.s_universal_progress)));
                        } else {
                            textView.setText("UNINSTALL GAGAL");
                            textView2.setText("Error tidak diketahui");
                        }
                        linearLayout.setVisibility(0);
                        button.setVisibility(8);
                        linearLayout2.setVisibility(8);
                        button2.setOnClickListener(new View.OnClickListener() {                             @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                AppFragmentActivity.this.UAPP.dismiss();
                            }
                        });
                        return;
                    }
                    AppFragmentActivity.this.UNINSTALLAPP.postDelayed(AppFragmentActivity.this.runnableUNINSTALLAPP, 100L);
                }
            };
            AppFragmentActivity.this.UNINSTALLAPP.postDelayed(AppFragmentActivity.this.runnableUNINSTALLAPP, 0L);
        }
    }

    public void _actionReinstallPackage(String str) {
        this.s_universal_progress = str;
        showRAPP();
    }

    private void showRAPP() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_dialog_uni_two_button, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_title);
        TextView textView2 = (TextView) viewInflate.findViewById(C0978R.id.tv_message);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_cancel);
        Button button2 = (Button) viewInflate.findViewById(C0978R.id.btn_oke);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_button);
        LinearLayout linearLayout2 = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_progress);
        textView.setText("REINSTALL");
        textView2.setText("Reinstall aplikasi \n".concat(this.s_universal_progress));
        linearLayout2.setVisibility(8);
        linearLayout.setVisibility(0);
        button.setVisibility(0);
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.RAPP.dismiss();
            }
        });
        button2.setOnClickListener(new ViewOnClickListenerC068372(linearLayout2, linearLayout, textView2, textView, button, button2));
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.RAPP = alertDialogCreate;
        alertDialogCreate.show();
    }

        class ViewOnClickListenerC068372 implements View.OnClickListener {
        private final /* synthetic */ Button val$btn_cancel;
        private final /* synthetic */ Button val$btn_oke;
        private final /* synthetic */ LinearLayout val$ln_button;
        private final /* synthetic */ LinearLayout val$ln_progress;
        private final /* synthetic */ TextView val$tv_message;
        private final /* synthetic */ TextView val$tv_title;

        ViewOnClickListenerC068372(LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, Button button, Button button2) {
            this.val$ln_progress = linearLayout;
            this.val$ln_button = linearLayout2;
            this.val$tv_message = textView;
            this.val$tv_title = textView2;
            this.val$btn_cancel = button;
            this.val$btn_oke = button2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.val$ln_progress.setVisibility(0);
            this.val$ln_button.setVisibility(8);
            this.val$tv_message.setText("Menguninstall dan menginstall aplikasi \n".concat(AppFragmentActivity.this.s_universal_progress));
            AppFragmentActivity.this.s_command = "app_package=\"fupackagename\"\nbase_path=$(pm path $app_package)\nfull_path=${base_path#*:}\ntmp_path=\"/data/local/tmp/\"\n\nmkdir -p $tmp_path > /dev/null 2>&1;\ncp \"$full_path\" \"$tmp_path\" > /dev/null 2>&1;\n\nif [ -f \"$tmp_path/${full_path##*/}\" ] > /dev/null 2>&1; then\n\nif pm uninstall $app_package > /dev/null 2>&1; then\n\nif pm install -t -i com.android.vending -r \"$tmp_path/base.apk\" > /dev/null 2>&1; then\necho -n \"Sukses menginstal ulang aplikasi\"\nelse\necho -n \"Gagal menginstal aplikasi \"\nfi\n\nelse\necho -n \"Gagal menguninstall aplikasi\"\nfi\n\nelse\necho -n \"Gagal menguninstall aplikasi\"\nfi";
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_command = appFragmentActivity.s_command.replace("fupackagename", AppFragmentActivity.this.s_universal_progress);
            AppFragmentActivity.this.b_command = false;
            AppFragmentActivity.this.s_commandResult = "";
            AppFragmentActivity.this.s_exitCode = "";
            AppFragmentActivity.this._OnBackgroundAction();
            AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
            final TextView textView = this.val$tv_title;
            final TextView textView2 = this.val$tv_message;
            final LinearLayout linearLayout = this.val$ln_button;
            final Button button = this.val$btn_cancel;
            final LinearLayout linearLayout2 = this.val$ln_progress;
            final Button button2 = this.val$btn_oke;
            appFragmentActivity2.runnableREINSTALLAPP = new Runnable() {                 @Override // java.lang.Runnable
                public void run() {
                    if (AppFragmentActivity.this.b_command) {
                        AppFragmentActivity.this.REINSTALLAPP.removeCallbacks(AppFragmentActivity.this.runnableREINSTALLAPP);
                        AppFragmentActivity.this.b_command = false;
                        if (AppFragmentActivity.this.s_commandResult.contains("Sukses")) {
                            textView.setText("REINSTALL SUKSES");
                            textView2.setText(AppFragmentActivity.this.s_commandResult.concat("\n".concat(AppFragmentActivity.this.s_universal_progress)));
                        } else {
                            textView.setText("REINSTALL GAGAL");
                            textView2.setText("Error tidak diketahui");
                        }
                        linearLayout.setVisibility(0);
                        button.setVisibility(8);
                        linearLayout2.setVisibility(8);
                        button2.setOnClickListener(new View.OnClickListener() {                             @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                AppFragmentActivity.this.RAPP.dismiss();
                            }
                        });
                        return;
                    }
                    AppFragmentActivity.this.REINSTALLAPP.postDelayed(AppFragmentActivity.this.runnableREINSTALLAPP, 100L);
                }
            };
            AppFragmentActivity.this.REINSTALLAPP.postDelayed(AppFragmentActivity.this.runnableREINSTALLAPP, 0L);
        }
    }

    public void _actionWipePackage(String str) {
        this.s_universal_progress = str;
        showWAPP();
    }

    private void showWAPP() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_dialog_uni_two_button, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_title);
        TextView textView2 = (TextView) viewInflate.findViewById(C0978R.id.tv_message);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_cancel);
        Button button2 = (Button) viewInflate.findViewById(C0978R.id.btn_oke);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_button);
        LinearLayout linearLayout2 = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_progress);
        textView.setText("WIPE DATA");
        textView2.setText("Wipe data aplikasi \n".concat(this.s_universal_progress));
        linearLayout2.setVisibility(8);
        linearLayout.setVisibility(0);
        button.setVisibility(0);
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.WAPP.dismiss();
            }
        });
        button2.setOnClickListener(new ViewOnClickListenerC068574(linearLayout2, linearLayout, textView2, textView, button, button2));
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.WAPP = alertDialogCreate;
        alertDialogCreate.show();
    }

        class ViewOnClickListenerC068574 implements View.OnClickListener {
        private final /* synthetic */ Button val$btn_cancel;
        private final /* synthetic */ Button val$btn_oke;
        private final /* synthetic */ LinearLayout val$ln_button;
        private final /* synthetic */ LinearLayout val$ln_progress;
        private final /* synthetic */ TextView val$tv_message;
        private final /* synthetic */ TextView val$tv_title;

        ViewOnClickListenerC068574(LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, Button button, Button button2) {
            this.val$ln_progress = linearLayout;
            this.val$ln_button = linearLayout2;
            this.val$tv_message = textView;
            this.val$tv_title = textView2;
            this.val$btn_cancel = button;
            this.val$btn_oke = button2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.val$ln_progress.setVisibility(0);
            this.val$ln_button.setVisibility(8);
            this.val$tv_message.setText("Menghapus data dan cache aplikasi \n".concat(AppFragmentActivity.this.s_universal_progress));
            AppFragmentActivity.this.s_command = "app_package=\"fupackagename\"\nandroid_data=\"/storage/emulated/0/Android/data\"\nam force-stop $app_package > /dev/null 2>&1\n\nif pm clear $app_package > /dev/null 2>&1; then\nrm -rf $android_data/$app_package > /dev/null 2>&1;\necho -n \"Sukses menghapus data dan cache aplikasi\"\nelse\necho -n \"Gagal membersihkan data dan cache aplikasi\"\nfi";
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_command = appFragmentActivity.s_command.replace("fupackagename", AppFragmentActivity.this.s_universal_progress);
            AppFragmentActivity.this.b_command = false;
            AppFragmentActivity.this.s_commandResult = "";
            AppFragmentActivity.this.s_exitCode = "";
            AppFragmentActivity.this._OnBackgroundAction();
            AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
            final TextView textView = this.val$tv_title;
            final TextView textView2 = this.val$tv_message;
            final LinearLayout linearLayout = this.val$ln_button;
            final Button button = this.val$btn_cancel;
            final LinearLayout linearLayout2 = this.val$ln_progress;
            final Button button2 = this.val$btn_oke;
            appFragmentActivity2.runnableWIPEAPP = new Runnable() {                 @Override // java.lang.Runnable
                public void run() {
                    if (AppFragmentActivity.this.b_command) {
                        AppFragmentActivity.this.WIPEAPP.removeCallbacks(AppFragmentActivity.this.runnableWIPEAPP);
                        AppFragmentActivity.this.b_command = false;
                        if (AppFragmentActivity.this.s_commandResult.contains("Sukses")) {
                            textView.setText("WIPE DATA SUKSES");
                            textView2.setText(AppFragmentActivity.this.s_commandResult.concat("\n".concat(AppFragmentActivity.this.s_universal_progress)));
                        } else {
                            textView.setText("WIPE DATA GAGAL");
                            textView2.setText("Error tidak diketahui");
                        }
                        linearLayout.setVisibility(0);
                        button.setVisibility(8);
                        linearLayout2.setVisibility(8);
                        button2.setOnClickListener(new View.OnClickListener() {                             @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                AppFragmentActivity.this.WAPP.dismiss();
                            }
                        });
                        return;
                    }
                    AppFragmentActivity.this.WIPEAPP.postDelayed(AppFragmentActivity.this.runnableWIPEAPP, 100L);
                }
            };
            AppFragmentActivity.this.WIPEAPP.postDelayed(AppFragmentActivity.this.runnableWIPEAPP, 0L);
        }
    }

    public void _actionOpenPackage(String str) {
        OpenAppDialogFragmentActivity openAppDialogFragmentActivity = new OpenAppDialogFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("package", str);
        openAppDialogFragmentActivity.setArguments(bundle);
        openAppDialogFragmentActivity.show(getActivity().getSupportFragmentManager(), "OpenAppDialogFragmentActivity12");
    }

    public void _actionSharePackage(String str) {
        String str2;
        try {
            str2 = getActivity().getPackageManager().getPackageInfo(str, 1).applicationInfo.publicSourceDir;
        } catch (Exception unused) {
            str2 = "";
        }
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("*/*");
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str2)));
        startActivity(Intent.createChooser(intent, "Share Via"));
    }

    public void _actionSavePackage(final String str, final String str2, final String str3) {
        this.s_universal_progress = "Menyimpan Aplikasi";
        _showUniversalProgress();
        new Handler().postDelayed(new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                try {
                    String str4 = AppFragmentActivity.this.requireActivity().getPackageManager().getApplicationInfo(str, 0).sourceDir;
                    File file = new File(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()) + File.separator + "XKatrina" + File.separator + str2);
                    file.mkdirs();
                    File file2 = new File(file, String.valueOf(str2) + "_" + str3 + ".apk");
                    FileChannel channel = new FileInputStream(str4).getChannel();
                    FileChannel channel2 = new FileOutputStream(file2).getChannel();
                    channel2.transferFrom(channel, 0L, channel.size());
                    channel.close();
                    channel2.close();
                    AppFragmentActivity.this._hideUniversalProgress();
                    Toast.makeText(AppFragmentActivity.this.getContext(), "Aplikasi telah disimpan di folder XKatrina", 0).show();
                } catch (PackageManager.NameNotFoundException unused) {
                    AppFragmentActivity.this._hideUniversalProgress();
                    Toast.makeText(AppFragmentActivity.this.getContext(), "Aplikasi tidak ditemukan", 0).show();
                } catch (IOException e) {
                    AppFragmentActivity.this._hideUniversalProgress();
                    Toast.makeText(AppFragmentActivity.this.getContext(), "Gagal menyimpan aplikasi", 0).show();
                    e.printStackTrace();
                }
            }
        }, 1000L);
    }

    public void _actionInfoPackage(String str) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + str));
        startActivity(intent);
    }

    public void _onReadBackup() {
        MyREADBACKUP myREADBACKUP = this.myREADBACKUP;
        if (myREADBACKUP != null && myREADBACKUP.isRunning) {
            this.myREADBACKUP.cancelREADBACKUPTask();
        }
        MyREADBACKUP myREADBACKUP2 = new MyREADBACKUP();
        this.myREADBACKUP = myREADBACKUP2;
        myREADBACKUP2.execute(new Void[0]);
    }

    public class MyREADBACKUP extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyREADBACKUP() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            AppFragmentActivity.this._showLoadingMain("Memuat file backup...");
            AppFragmentActivity.this._fab.setVisibility(8);
            AppFragmentActivity.this.fab2.setVisibility(8);
            AppFragmentActivity.this.fabeternal.setVisibility(8);
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_backup_app = appFragmentActivity.prefui.getString("backup_app_package", "");
            AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
            appFragmentActivity2.s_backup_loc = appFragmentActivity2.prefui.getString("backup_sdcard_location", "");
            AppFragmentActivity appFragmentActivity3 = AppFragmentActivity.this;
            appFragmentActivity3.s_backupLocation = appFragmentActivity3.s_backup_loc.concat("/".concat(AppFragmentActivity.this.s_backup_app));
            AppFragmentActivity.this.ls_sort_1.clear();
            AppFragmentActivity.this.ls_sort_2.clear();
            AppFragmentActivity.this.ls_sort_3.clear();
            AppFragmentActivity.this.lm_backup_data.clear();
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            FileUtil.listDir(AppFragmentActivity.this.s_backupLocation, AppFragmentActivity.this.ls_sort_1);
            for (String str : AppFragmentActivity.this.ls_sort_1) {
                if (FileUtil.isDirectory(str)) {
                    AppFragmentActivity.this.ls_sort_2.add(str);
                }
            }
            for (String str2 : AppFragmentActivity.this.ls_sort_2) {
                AppFragmentActivity.this.ls_sort_3.clear();
                FileUtil.listDir(str2, AppFragmentActivity.this.ls_sort_3);
                AppFragmentActivity.this.s_fname = str2;
                for (String str3 : AppFragmentActivity.this.ls_sort_3) {
                    if (str3.endsWith("tar.gz") && FileUtil.isExistFile(str3.replace("tar.gz", "json"))) {
                        AppFragmentActivity.this.s_filePath = str3;
                        AppFragmentActivity.this.s_file_size = String.format("%.2f MB", Double.valueOf((new File(AppFragmentActivity.this.s_filePath).length() / 1024) / 1024.0d));
                        String file = FileUtil.readFile(AppFragmentActivity.this.s_filePath.replace("tar.gz", "json"));
                        AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                        appFragmentActivity.s_date_backup = Uri.parse(appFragmentActivity.s_filePath).getLastPathSegment();
                        if (!AppFragmentActivity.this.jsonIsValid(file)) {
                            file = "{}";
                        }
                        AppFragmentActivity.this.m_json_app = (HashMap) new Gson().fromJson(file, new TypeToken<HashMap<String, Object>>() {                         }.getType());
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("UNIX")) {
                            AppFragmentActivity.this.s_usia_backup = "?? hari yang lalu";
                        } else {
                            try {
                                long jCurrentTimeMillis = ((System.currentTimeMillis() / 1000) - Long.parseLong(AppFragmentActivity.this.m_json_app.get("UNIX").toString())) / 86400;
                                AppFragmentActivity.this.s_usia_backup = String.valueOf(jCurrentTimeMillis) + " hari yang lalu";
                            } catch (NumberFormatException unused) {
                                AppFragmentActivity.this.s_usia_backup = "?? hari yang lalu";
                            }
                        }
                        AppFragmentActivity.this.m_sort_result = new HashMap();
                        AppFragmentActivity.this.m_sort_result.put("appfolder", AppFragmentActivity.this.s_fname);
                        AppFragmentActivity.this.m_sort_result.put("appsize", AppFragmentActivity.this.s_file_size);
                        AppFragmentActivity.this.m_sort_result.put("appbuild", AppFragmentActivity.this.s_usia_backup);
                        AppFragmentActivity.this.m_sort_result.put("appname", AppFragmentActivity.this.s_filePath);
                        AppFragmentActivity.this.m_sort_result.put("appfile", AppFragmentActivity.this.s_filePath);
                        AppFragmentActivity.this.m_sort_result.put("appjson", AppFragmentActivity.this.s_filePath.replace("tar.gz", "json"));
                        if (AppFragmentActivity.this.m_json_app != null && AppFragmentActivity.this.m_json_app.containsKey("DATE")) {
                            AppFragmentActivity.this.m_sort_result.put("appdate", AppFragmentActivity.this.m_json_app.get("DATE").toString());
                        } else {
                            Matcher matcher = Pattern.compile("-(\\d+-\\d+)\\.").matcher(AppFragmentActivity.this.s_date_backup);
                            if (matcher.find()) {
                                AppFragmentActivity.this.s_date_backup = matcher.group(1);
                            } else {
                                AppFragmentActivity.this.s_date_backup = "Tanggal null";
                            }
                            AppFragmentActivity.this.m_sort_result.put("appdate", AppFragmentActivity.this.s_date_backup);
                        }
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("MARK")) {
                            AppFragmentActivity.this.m_sort_result.put("appmark", "false");
                        } else {
                            AppFragmentActivity.this.m_sort_result.put("appmark", AppFragmentActivity.this.m_json_app.get("MARK").toString());
                        }
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("COLOR")) {
                            AppFragmentActivity.this.m_sort_result.put("appcolor", "#FFFFFFFF");
                        } else {
                            AppFragmentActivity.this.m_sort_result.put("appcolor", AppFragmentActivity.this.m_json_app.get("COLOR").toString());
                        }
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("NOTE")) {
                            AppFragmentActivity.this.m_sort_result.put("appnote", "");
                        } else {
                            AppFragmentActivity.this.m_sort_result.put("appnote", AppFragmentActivity.this.m_json_app.get("NOTE").toString());
                        }
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("SDK")) {
                            AppFragmentActivity.this.m_sort_result.put("appsdk", "");
                        } else {
                            AppFragmentActivity.this.m_sort_result.put("appsdk", AppFragmentActivity.this.m_json_app.get("SDK").toString());
                        }
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("UNIX")) {
                            AppFragmentActivity.this.m_sort_result.put("appunix", "");
                        } else {
                            AppFragmentActivity.this.m_sort_result.put("appunix", AppFragmentActivity.this.m_json_app.get("UNIX").toString());
                        }
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("settings_ssaid")) {
                            AppFragmentActivity.this.m_sort_result.put("settings_ssaid", "false");
                        } else {
                            AppFragmentActivity.this.m_sort_result.put("settings_ssaid", AppFragmentActivity.this.m_json_app.get("settings_ssaid").toString());
                        }
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("system.prop")) {
                            AppFragmentActivity.this.m_sort_result.put("system.prop", "false");
                        } else {
                            AppFragmentActivity.this.m_sort_result.put("system.prop", AppFragmentActivity.this.m_json_app.get("system.prop").toString());
                        }
                        AppFragmentActivity.this.lm_backup_data.add(AppFragmentActivity.this.m_sort_result);
                    }
                }
            }
            AppFragmentActivity.deleteEmptyFolders(AppFragmentActivity.this.s_backupLocation);
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r6) {
            this.isRunning = false;
            SketchwareUtil.sortListMap(AppFragmentActivity.this.lm_backup_data, "appfolder", false, true);
            if (AppFragmentActivity.this.lm_backup_data.size() > 0) {
                AppFragmentActivity.this.mchip_total.setText(String.valueOf(AppFragmentActivity.this.lm_backup_data.size()).concat(" Backup"));
                AppFragmentActivity.this.lv_backup.setVisibility(0);
                AppFragmentActivity.this.ln_backup_empty.setVisibility(8);
                ListView listView = AppFragmentActivity.this.lv_backup;
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                listView.setAdapter((ListAdapter) appFragmentActivity.new Lv_backupAdapter(appFragmentActivity.lm_backup_data));
                ((BaseAdapter) AppFragmentActivity.this.lv_backup.getAdapter()).notifyDataSetChanged();
                AppFragmentActivity.this._setBackupPosition();
            } else {
                AppFragmentActivity.this.mchip_total.setText(String.valueOf(AppFragmentActivity.this.lm_backup_data.size()).concat(" Backup"));
                AppFragmentActivity.this.lv_backup.setVisibility(8);
                AppFragmentActivity.this.ln_backup_empty.setVisibility(0);
            }
            AppFragmentActivity.this._fab.setVisibility(0);
            AppFragmentActivity.this.fab2.setVisibility(0);
            AppFragmentActivity.this._showFabEternal();
            AppFragmentActivity.this._hideLoadingMain();
            AppFragmentActivity.this._setAppPosition();
        }

        public void cancelREADBACKUPTask() {
            cancel(true);
        }
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
            AppFragmentActivity.this.lm_folder_picker.clear();
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            FileUtil.listDir(AppFragmentActivity.this.s_folder_picker, AppFragmentActivity.this.ls_folder_picker);
            Collections.sort(AppFragmentActivity.this.ls_folder_picker, new Comparator<String>() {                 @Override // java.util.Comparator
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
            AppFragmentActivity.this.n_pos = 0.0d;
            for (int i = 0; i < AppFragmentActivity.this.ls_folder_picker.size(); i++) {
                HashMap map = new HashMap();
                map.put("folder", AppFragmentActivity.this.ls_folder_picker.get((int) AppFragmentActivity.this.n_pos));
                AppFragmentActivity.this.lm_folder_picker.add(map);
                AppFragmentActivity.this.n_pos += 1.0d;
            }
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            this.isRunning = false;
            AppFragmentActivity.this.b_folder_scan = true;
        }

        public void cancelREADFOLDERTask() {
            cancel(true);
        }
    }

    public void _onSearchApp(String str) {
        this.lm_search_app.clear();
        this.n_pos = 0.0d;
        int i = 0;
        if (this.prefui.getString("backup_show_favorite", "").equals("true")) {
            while (i < this.lm_fav_app.size()) {
                if (this.lm_fav_app.get((int) this.n_pos).get("appname").toString().toLowerCase().contains(str.toLowerCase())) {
                    HashMap<String, Object> map = this.lm_fav_app.get((int) this.n_pos);
                    this.m_search_app = map;
                    this.lm_search_app.add(map);
                }
                this.n_pos += 1.0d;
                i++;
            }
            this.lv_fav.setAdapter((ListAdapter) new Lv_favAdapter(this.lm_search_app));
            ((BaseAdapter) this.lv_fav.getAdapter()).notifyDataSetChanged();
            return;
        }
        while (i < this.lm_all_app.size()) {
            if (this.lm_all_app.get((int) this.n_pos).get("appname").toString().toLowerCase().contains(str.toLowerCase())) {
                HashMap<String, Object> map2 = this.lm_all_app.get((int) this.n_pos);
                this.m_search_app = map2;
                this.lm_search_app.add(map2);
            }
            this.n_pos += 1.0d;
            i++;
        }
        this.lv_app.setAdapter((ListAdapter) new Lv_appAdapter(this.lm_search_app));
        ((BaseAdapter) this.lv_app.getAdapter()).notifyDataSetChanged();
    }

    public void _onDeleteFileBackup(double d, final String str, String str2, String str3) {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_dialog_delete_backup, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_01);
        TextView textView2 = (TextView) viewInflate.findViewById(C0978R.id.tv_02);
        TextView textView3 = (TextView) viewInflate.findViewById(C0978R.id.tv_03);
        final LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_progress);
        final LinearLayout linearLayout2 = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_info);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_cancel);
        Button button2 = (Button) viewInflate.findViewById(C0978R.id.btn_oke);
        textView.setText(str);
        textView2.setText(str2);
        textView3.setText(str3);
        linearLayout.setVisibility(8);
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.customDialog3.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i = 0;
                linearLayout.setVisibility(0);
                linearLayout2.setVisibility(8);
                FileUtil.deleteFile(str);
                AppFragmentActivity.this.n_pos = 0.0d;
                int i2 = 0;
                while (true) {
                    if (i2 >= AppFragmentActivity.this.lm_backup_data.size()) {
                        break;
                    }
                    if (((HashMap) AppFragmentActivity.this.lm_backup_data.get((int) AppFragmentActivity.this.n_pos)).get("appfolder").toString().equals(str)) {
                        AppFragmentActivity.this.lm_backup_data.remove((int) AppFragmentActivity.this.n_pos);
                        break;
                    } else {
                        AppFragmentActivity.this.n_pos += 1.0d;
                        i2++;
                    }
                }
                AppFragmentActivity.this.n_pos = 0.0d;
                while (true) {
                    if (i >= AppFragmentActivity.this.lm_search_backup.size()) {
                        break;
                    }
                    if (((HashMap) AppFragmentActivity.this.lm_search_backup.get((int) AppFragmentActivity.this.n_pos)).get("appfolder").toString().equals(str)) {
                        AppFragmentActivity.this.lm_search_backup.remove((int) AppFragmentActivity.this.n_pos);
                        break;
                    } else {
                        AppFragmentActivity.this.n_pos += 1.0d;
                        i++;
                    }
                }
                AppFragmentActivity.this.lv_backup.invalidateViews();
                AppFragmentActivity.this.mchip_total.setText(String.valueOf(AppFragmentActivity.this.lm_backup_data.size()).concat(" Backup"));
                AppFragmentActivity.this._onVibrate();
                AppFragmentActivity.this.customDialog3.dismiss();
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.customDialog3 = alertDialogCreate;
        alertDialogCreate.show();
    }

    public void _showDialogRestore(String str, String str2, String str3, String str4) {
        this.s_restore_loc = str;
        this.s_restore_ssaid = str2;
        this.s_restore_prop = str3;
        this.s_restore_sdk = str4;
        showRESTORE();
    }

    private void showRESTORE() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_dialog_restore, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(true);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_title);
        TextView textView2 = (TextView) viewInflate.findViewById(C0978R.id.tv_message);
        TextView textView3 = (TextView) viewInflate.findViewById(C0978R.id.tv_desc_ssaid);
        TextView textView4 = (TextView) viewInflate.findViewById(C0978R.id.tv_desc_prop);
        MaterialSwitch materialSwitch = (MaterialSwitch) viewInflate.findViewById(C0978R.id.switch_ssaid);
        MaterialSwitch materialSwitch2 = (MaterialSwitch) viewInflate.findViewById(C0978R.id.switch_prop);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_oke);
        Button button2 = (Button) viewInflate.findViewById(C0978R.id.btn_cancel);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_progress);
        this.b_command = false;
        this.b_ssaid = false;
        this.b_prop = false;
        linearLayout.setVisibility(8);
        textView.setText(this.prefui.getString("backup_app_name", ""));
        textView2.setText(Uri.parse(this.s_restore_loc).getLastPathSegment());
        this.s_sdk = String.valueOf(Build.VERSION.SDK_INT);
        if (this.s_restore_prop.equals("true")) {
            textView4.setText("Diijinkan :");
            materialSwitch2.setEnabled(true);
            materialSwitch2.setAlpha(1.0f);
        } else {
            textView4.setText("Tidak diijinkan :");
            materialSwitch2.setEnabled(false);
            materialSwitch2.setAlpha(0.4f);
        }
        if (this.s_restore_ssaid.equals("true")) {
            textView3.setText("Diijinkan :");
            materialSwitch.setEnabled(true);
            materialSwitch.setAlpha(1.0f);
        } else {
            textView3.setText("Tidak diijinkan :");
            materialSwitch.setEnabled(false);
            materialSwitch.setAlpha(0.4f);
        }
        if (!this.s_sdk.equals(this.s_restore_sdk)) {
            textView3.setText("Tidak diijinkan :");
            materialSwitch.setEnabled(false);
            textView4.setText("Tidak diijinkan :");
            materialSwitch2.setEnabled(false);
            materialSwitch.setAlpha(0.4f);
            materialSwitch2.setAlpha(0.4f);
        }
        materialSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AppFragmentActivity.this.b_ssaid = true;
                } else {
                    AppFragmentActivity.this.b_ssaid = false;
                }
            }
        });
        materialSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AppFragmentActivity.this.b_prop = true;
                } else {
                    AppFragmentActivity.this.b_prop = false;
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.RESTORE.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity.s_scbase = appFragmentActivity.s_commandBaseRestore.concat("\nonrestore");
                AppFragmentActivity.this.s_exe1 = "tar -zxf $thisfile -C / > /dev/null 2>&1\nif [[ $? -eq 0 ]]; then\n  echo \"Sukses restore $thisapp\"\nelse\n  echo \"Gagal restore $thisapp\"\nfi";
                AppFragmentActivity.this.s_exe2 = "tar -zxf $thispath/fileprop.tar.gz -C / > /dev/null 2>&1\nif [[ $? -eq 0 ]]; then\n  echo \"Sukses restore system.prop\"\nelse\n  echo  \"Gagal restore system.prop\"\nfi";
                AppFragmentActivity.this.s_exe3 = "tar -zxf $thispath/filessaid.tar.gz -C / > /dev/null 2>&1\nif [[ $? -eq 0 ]]; then\n  echo \"Sukses restore settings_ssaid\"\nelse\n  echo \"Gagal restore settings_ssaid\"\nfi";
                AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
                appFragmentActivity2.s_scbase = appFragmentActivity2.s_scbase.replace("futhisapp", AppFragmentActivity.this.prefui.getString("backup_app_name", ""));
                AppFragmentActivity appFragmentActivity3 = AppFragmentActivity.this;
                appFragmentActivity3.s_scbase = appFragmentActivity3.s_scbase.replace("futhispackage", AppFragmentActivity.this.prefui.getString("backup_app_package", ""));
                AppFragmentActivity appFragmentActivity4 = AppFragmentActivity.this;
                appFragmentActivity4.s_scbase = appFragmentActivity4.s_scbase.replace("futhisfile", AppFragmentActivity.this.s_restore_loc);
                AppFragmentActivity appFragmentActivity5 = AppFragmentActivity.this;
                appFragmentActivity5.s_scbase = appFragmentActivity5.s_scbase.replace("#exe1", AppFragmentActivity.this.s_exe1);
                AppFragmentActivity.this.s_commandResult = "";
                AppFragmentActivity.this.s_exitCode = "";
                if (AppFragmentActivity.this.b_prop) {
                    AppFragmentActivity appFragmentActivity6 = AppFragmentActivity.this;
                    appFragmentActivity6.s_scbase = appFragmentActivity6.s_scbase.replace("#exe2", AppFragmentActivity.this.s_exe2);
                }
                if (AppFragmentActivity.this.b_ssaid) {
                    AppFragmentActivity appFragmentActivity7 = AppFragmentActivity.this;
                    appFragmentActivity7.s_scbase = appFragmentActivity7.s_scbase.replace("#exe3", AppFragmentActivity.this.s_exe3);
                }
                AppFragmentActivity appFragmentActivity8 = AppFragmentActivity.this;
                appFragmentActivity8.s_command = appFragmentActivity8.s_scbase;
                AppFragmentActivity.this._onRestoreBackup();
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.RESTORE = alertDialogCreate;
        alertDialogCreate.show();
    }

    public void _onRestoreBackup() {
        AlertDialog alertDialog = this.RESTORE;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.RESTORE.dismiss();
        }
        showPROSESRESTOR();
    }

    private void showPROSESRESTOR() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_dialog_restore_new, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        final TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_proses_title);
        final TextView textView2 = (TextView) viewInflate.findViewById(C0978R.id.tv_proses_backup);
        final Button button = (Button) viewInflate.findViewById(C0978R.id.btn_oke);
        final Button button2 = (Button) viewInflate.findViewById(C0978R.id.btn_open);
        final LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_lottie);
        textView.setText("Proses");
        textView2.setText("Restore backup\n".concat(this.prefui.getString("backup_app_name", "")));
        linearLayout.setVisibility(0);
        button.setVisibility(8);
        button2.setVisibility(8);
        this.b_restore = false;
        _OnBackgroundAction();
        Runnable runnable = new Runnable() {             @Override // java.lang.Runnable
            public void run() {
                if (AppFragmentActivity.this.b_restore) {
                    AppFragmentActivity.this.b_restore = false;
                    AppFragmentActivity.this.OnRestore.removeCallbacks(AppFragmentActivity.this.runnableOnRestore);
                    textView.setText("Selesai");
                    textView2.setText(AppFragmentActivity.this.s_commandResult);
                    linearLayout.setVisibility(8);
                    button.setVisibility(0);
                    button2.setVisibility(0);
                    AppFragmentActivity.this._onVibrate();
                    AppFragmentActivity.this.preflast.edit().putString(AppFragmentActivity.this.prefui.getString("backup_app_package", ""), AppFragmentActivity.this.s_restore_loc).commit();
                }
                AppFragmentActivity.this.OnRestore.postDelayed(AppFragmentActivity.this.runnableOnRestore, 100L);
            }
        };
        this.runnableOnRestore = runnable;
        this.OnRestore.postDelayed(runnable, 0L);
        if (this.b_ssaid || this.b_prop) {
            button.setText("Reboot");
            button.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AppFragmentActivity.this.s_command = "am start -a android.intent.action.REBOOT";
                    AppFragmentActivity.this.b_command = false;
                    Shell.Result resultExec = Shell.cmd(AppFragmentActivity.this.s_command).exec();
                    List<String> out = resultExec.getOut();
                    resultExec.getCode();
                    AppFragmentActivity.this.b_command = resultExec.isSuccess();
                    AppFragmentActivity.this.s_commandResult = String.join("\n", out);
                }
            });
        } else {
            button.setText("Tutup");
            button.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AppFragmentActivity.this.PROSESRESTOR.dismiss();
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                    appFragmentActivity._actionOpenPackage(appFragmentActivity.prefui.getString("backup_app_package", ""));
                    AppFragmentActivity.this.PROSESRESTOR.dismiss();
                }
            });
        }
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.PROSESRESTOR = alertDialogCreate;
        alertDialogCreate.show();
    }

    public void _setBackupPosition() {
        String string = this.prefui.getString("backup_app_package", "");
        this.s_universal_progress = string;
        if (this.preflast.getString(string, "").equals("")) {
            return;
        }
        this.n_restore_position = 0.0d;
        for (int i = 0; i < this.lm_backup_data.size(); i++) {
            if (this.lm_backup_data.get((int) this.n_restore_position).get("appfile").toString().equals(this.preflast.getString(this.s_universal_progress, ""))) {
                this.lv_backup.setSelection((int) this.n_restore_position);
            }
            this.n_restore_position += 1.0d;
        }
        _setAppPosition();
    }

    public void _onSearchBackup(String str) {
        this.lm_search_backup.clear();
        this.n_pos = 0.0d;
        for (int i = 0; i < this.lm_backup_data.size(); i++) {
            if (this.lm_backup_data.get((int) this.n_pos).get("appnote").toString().toLowerCase().contains(str.toLowerCase())) {
                HashMap<String, Object> map = this.lm_backup_data.get((int) this.n_pos);
                this.m_search_backup = map;
                this.lm_search_backup.add(map);
            } else if (this.lm_backup_data.get((int) this.n_pos).get("appfolder").toString().toLowerCase().contains(str.toLowerCase())) {
                HashMap<String, Object> map2 = this.lm_backup_data.get((int) this.n_pos);
                this.m_search_backup = map2;
                this.lm_search_backup.add(map2);
            }
            this.n_pos += 1.0d;
        }
        if (this.lm_search_backup.size() > 0) {
            this.lv_backup.setVisibility(0);
            this.ln_backup_empty.setVisibility(8);
            this.lv_backup.setAdapter((ListAdapter) new Lv_backupAdapter(this.lm_search_backup));
            ((BaseAdapter) this.lv_backup.getAdapter()).notifyDataSetChanged();
            return;
        }
        this.lv_backup.setVisibility(8);
        this.ln_backup_empty.setVisibility(0);
    }

    public void _setSearchNull() {
        this.tv_name_ritual.setText(this.prefui.getString("backup_app_name", ""));
        try {
            String string = this.prefui.getString("backup_app_package", "");
            if (!string.isEmpty()) {
                PackageManager packageManager = getActivity().getPackageManager();
                this.tv_versi_ritual.setText(packageManager.getPackageInfo(string, 0).versionName);
                this.im_app_ritual.setImageDrawable(packageManager.getApplicationIcon(packageManager.getApplicationInfo(string, 0)));
            } else {
                this.tv_versi_ritual.setText("Unknown");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            this.tv_versi_ritual.setText("Unknown");
        }
        this.ln_backup.setVisibility(0);
        this.ln_ritual.setVisibility(8);
        this.auto_backup.setText("");
        this.auto_backup.clearFocus();
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
        this.s_filename = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/xkatrina.apk"));
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AppFragmentActivity.this.b_update_force) {
                    return;
                }
                AppFragmentActivity.this.UPDATE.dismiss();
            }
        });
        this.btn_download.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity.s_url = ((HashMap) appFragmentActivity.lm_release.get(0)).get("release_url").toString();
                new DownloadTask(AppFragmentActivity.this, null).execute(AppFragmentActivity.this.s_url);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Context context = AppFragmentActivity.this.getContext();
                AppFragmentActivity.this.getContext().getApplicationContext();
                ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("clipboard", textView4.getText().toString()));
            }
        });
        textView5.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.UPDATE.dismiss();
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

        /* synthetic */ DownloadTask(AppFragmentActivity appFragmentActivity, DownloadTask downloadTask) {
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
                        fileOutputStream = new FileOutputStream(AppFragmentActivity.this.s_filename);
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
                                        inputStream.close();
                                    }
                                } catch (IOException unused2) {
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                return true;
                            }
                            if (isCancelled()) {
                                inputStream.close();
                                try {
                                    fileOutputStream.close();
                                    if (inputStream != null) {
                                        inputStream.close();
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
                            inputStream.close();
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
                try { inputStream.close(); } catch (IOException unused9) {}
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return false;
        }

                @Override // android.os.AsyncTask
        public void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate(numArr);
            AppFragmentActivity.this.progressBar.setProgress(numArr[0].intValue());
            AppFragmentActivity.this.btn_download.setText(numArr[0] + "%");
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (bool.booleanValue()) {
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity.s_command = "pm install ".concat(appFragmentActivity.s_filename);
                AppFragmentActivity.this.s_commandResult = "";
                AppFragmentActivity.this.s_exitCode = "";
                AppFragmentActivity.this.b_command = false;
                AppFragmentActivity.this.b_command = false;
                Shell.Result resultExec = Shell.cmd(AppFragmentActivity.this.s_command).exec();
                List<String> out = resultExec.getOut();
                resultExec.getCode();
                AppFragmentActivity.this.b_command = resultExec.isSuccess();
                AppFragmentActivity.this.s_commandResult = String.join("\n", out);
                return;
            }
            AppFragmentActivity.this.fushowToast("Download failed");
        }
    }

        public void fushowToast(String str) {
        Toast.makeText(requireContext(), str, 0).show();
    }

    public void _onAppRitual() {
        this.s_package_ritual = this.prefui.getString("backup_app_package", "");
        this.tv_status.setText("\nProses...");
        this.tv_status.setVisibility(0);
        this.pbar_ritual.setVisibility(0);
        this.s_commandResult = "";
        _cekWipeData();
    }

    public void _cekWipeData() {
        if (this.prefui.getString("ritual_wipe_app", "").equals("true")) {
            _onRitualWipeData();
        } else {
            _cekReinstallApp();
        }
    }

    public void _cekReinstallApp() {
        if (this.prefui.getString("ritual_reinstall_app", "").equals("true")) {
            _onRitualReinstallApp();
        } else {
            _cekWipeGms();
        }
    }

    public void _cekWipeGms() {
        if (this.prefui.getString("ritual_wipe_gms", "").equals("true")) {
            _onRitualWipeGms();
        } else {
            _cekCleanTimepick();
        }
    }

    public void _cekCleanTimepick() {
        if (this.prefui.getString("ritual_clean_timepick", "").equals("true")) {
            _onRitualTimepick();
        } else {
            this.pbar_ritual.setVisibility(4);
            this.tv_status.setText("Selesai");
        }
    }

    public void _onVibrate() {
        this.vibrate.vibrate(100L);
    }

    public void _sendFileToYourBot(ArrayList<String> arrayList) {
        new TelegramFileSender().execute(this.prefuser.getString("token_bot", ""), this.prefuser.getString("chat_id", ""), arrayList);
    }

    public class TelegramFileSender extends AsyncTask<Object, Integer, Void> {
        public TelegramFileSender() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            ProgressBar progressBar = (ProgressBar) AppFragmentActivity.this.BOT.findViewById(C0978R.id.pbar_2);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            ((LinearLayout) AppFragmentActivity.this.BOT.findViewById(C0978R.id.ln_sendfile)).setVisibility(0);
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Object... objArr) {
            String str = (String) objArr[0];
            String str2 = (String) objArr[1];
            ArrayList arrayList = (ArrayList) objArr[2];
            try {
                int size = arrayList.size();
                AppFragmentActivity.this.s_totalfile = String.valueOf(size);
                int i = 0;
                while (i < size) {
                    String str3 = (String) arrayList.get(i);
                    long totalFileSize = getTotalFileSize(str3);
                    AppFragmentActivity.this.s_namefile = str3;
                    int i2 = i + 1;
                    AppFragmentActivity.this.s_progressfile = String.valueOf(i2);
                    AppFragmentActivity.this.s_totalsize = String.format("%.2f", Double.valueOf(totalFileSize / 1048576.0d));
                    publishProgress(0);
                    sendFileToTelegram(str, str2, str3, totalFileSize);
                    publishProgress(Integer.valueOf((i2 * 100) / size));
                    i = i2;
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        private void sendFileToTelegram(String str, String str2, String str3, long j) throws IOException {
            String.valueOf(j);
            String str4 = String.format("https://api.telegram.org/bot%s/sendDocument?chat_id=%s", str, str2);
            File file = new File(str3);
            String hexString = Long.toHexString(System.currentTimeMillis());
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str4).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + hexString);
            Throwable th = null;
            try {
                OutputStream outputStream = httpURLConnection.getOutputStream();
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        outputStream.write(("--" + hexString + "\r\n").getBytes());
                        outputStream.write(("Content-Disposition: form-data; name=\"document\"; filename=\"" + file.getName() + "\"\r\n").getBytes());
                        outputStream.write("Content-Type: application/octet-stream\r\n\r\n".getBytes());
                        byte[] bArr = new byte[8192];
                        long j2 = 0;
                        while (true) {
                            int i = fileInputStream.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            outputStream.write(bArr, 0, i);
                            j2 += (long) i;
                            publishProgress(Integer.valueOf((int) ((100 * j2) / j)));
                        }
                        outputStream.write(("\r\n--" + hexString + "--\r\n").getBytes());
                        fileInputStream.close();
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        int responseCode = httpURLConnection.getResponseCode();
                        System.out.println("Response Code: " + responseCode);
                    } catch (Throwable th2) {
                        fileInputStream.close();
                        throw th2;
                    }
                } finally {
                }
            } finally {
            }
        }

                @Override // android.os.AsyncTask
        public void onProgressUpdate(Integer... numArr) {
            int iIntValue = numArr[0].intValue();
            TextView textView = (TextView) AppFragmentActivity.this.BOT.findViewById(C0978R.id.tv_namefile);
            if (textView != null) {
                textView.setText(Uri.parse(AppFragmentActivity.this.s_namefile).getLastPathSegment());
            }
            TextView textView2 = (TextView) AppFragmentActivity.this.BOT.findViewById(C0978R.id.tv_progressfile);
            if (textView2 != null) {
                textView2.setText(String.valueOf(AppFragmentActivity.this.s_progressfile) + "/" + AppFragmentActivity.this.s_totalfile);
            }
            TextView textView3 = (TextView) AppFragmentActivity.this.BOT.findViewById(C0978R.id.tv_totalfile);
            if (textView3 != null) {
                textView3.setText(String.valueOf(AppFragmentActivity.this.s_totalsize) + "MB");
            }
            ProgressBar progressBar = (ProgressBar) AppFragmentActivity.this.BOT.findViewById(C0978R.id.pbar_2);
            if (progressBar != null) {
                progressBar.setProgress(iIntValue);
            }
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r3) {
            TextView textView = (TextView) AppFragmentActivity.this.BOT.findViewById(C0978R.id.tv_picklist);
            if (textView != null) {
                textView.setText(String.valueOf(AppFragmentActivity.this.s_totalfile) + " File Terkirim");
            }
            Button button = (Button) AppFragmentActivity.this.BOT.findViewById(C0978R.id.btn_oke);
            if (button != null) {
                button.setText("SELESAI");
            }
            ((LinearLayout) AppFragmentActivity.this.BOT.findViewById(C0978R.id.ln_sendfile)).setVisibility(8);
            AppFragmentActivity.this.ls_backupbot.clear();
        }

        private long getTotalFileSize(String str) {
            return new File(str).length();
        }
    }

    public void _openDialogBot() {
        showBOT();
    }

    private void showBOT() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_send_to_bot, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_sendfile);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_batal);
        final Button button2 = (Button) viewInflate.findViewById(C0978R.id.btn_oke);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_picklist);
        linearLayout.setBackgroundColor(Color.parseColor("#20000000"));
        linearLayout.setVisibility(8);
        ArrayList arrayList = new ArrayList();
        for (String strSubstring : this.ls_backupbot) {
            int iLastIndexOf = strSubstring.lastIndexOf(47);
            if (iLastIndexOf != -1) {
                strSubstring = strSubstring.substring(iLastIndexOf + 1);
            }
            arrayList.add(strSubstring);
        }
        textView.setText(String.join("\n", arrayList));
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.BOT.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (button2.getText().toString().equals("KIRIM")) {
                    if (!AppFragmentActivity.this.prefuser.getString("token_bot", "").equals("") && !AppFragmentActivity.this.prefuser.getString("chat_id", "").equals("")) {
                        if (AppFragmentActivity.this.ls_backupbot.size() != 0) {
                            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                            appFragmentActivity._sendFileToYourBot(appFragmentActivity.ls_backupbot);
                            return;
                        } else {
                            SketchwareUtil.showMessage(AppFragmentActivity.this.getContext().getApplicationContext(), "Tidak ada file untuk dikirim");
                            return;
                        }
                    }
                    SketchwareUtil.showMessage(AppFragmentActivity.this.getContext().getApplicationContext(), "Bot belum disetting");
                    return;
                }
                AppFragmentActivity.this.BOT.dismiss();
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.BOT = alertDialogCreate;
        alertDialogCreate.show();
    }

    public void _showFabEternal() {
        ArrayList<HashMap<String, Object>> arrayList = (ArrayList) new Gson().fromJson(this.prefall.getString("all_app_eternal", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {         }.getType());
        this.lm_eternal_app = arrayList;
        if (arrayList.size() == 0) {
            this.fabeternal.setVisibility(8);
            return;
        }
        Iterator<HashMap<String, Object>> it = this.lm_eternal_app.iterator();
        while (it.hasNext()) {
            Object obj = it.next().get("apppackage");
            if (obj != null && obj.toString().equals(this.prefui.getString("backup_app_package", ""))) {
                this.fabeternal.setVisibility(0);
                return;
            }
            this.fabeternal.setVisibility(8);
        }
    }

    public void _onRitualWipeData() {
        MyRITUALWIPEDATA myRITUALWIPEDATA = this.myRITUALWIPEDATA;
        if (myRITUALWIPEDATA != null && myRITUALWIPEDATA.isRunning) {
            this.myRITUALWIPEDATA.cancelRITUALWIPEDATATask();
        }
        MyRITUALWIPEDATA myRITUALWIPEDATA2 = new MyRITUALWIPEDATA();
        this.myRITUALWIPEDATA = myRITUALWIPEDATA2;
        myRITUALWIPEDATA2.execute(new Void[0]);
    }

    public class MyRITUALWIPEDATA extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyRITUALWIPEDATA() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            AppFragmentActivity.this.tv_status.setText("Wipe Data App\nProses...");
            AppFragmentActivity.this.updateTextRitual();
            AppFragmentActivity.this.s_ritual_app = "app_package=\"fupackagename\"\nandroid_data=\"/storage/emulated/0/Android/data\"\nam force-stop $app_package > /dev/null 2>&1\necho \"Sukses force stop aplikasi $app_package\"\nif pm clear $app_package > /dev/null 2>&1; then\necho \"Sukses menghapus data dan cache aplikasi\"\nrm -rf $android_data/$app_package > /dev/null 2>&1;\necho \"Sukses menghapus $android_data/$app_package\"\nelse\necho \"Gagal menghapus $android_data/$app_package\"\nfi";
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_ritual_app = appFragmentActivity.s_ritual_app.replace("fupackagename", AppFragmentActivity.this.s_package_ritual);
            AppFragmentActivity.this.b_command = false;
            AppFragmentActivity.this.s_exitCode = "";
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            AppFragmentActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(AppFragmentActivity.this.s_ritual_app).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            AppFragmentActivity.this.b_command = resultExec.isSuccess();
            AppFragmentActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            AppFragmentActivity.this._cekReinstallApp();
        }

        public void cancelRITUALWIPEDATATask() {
            cancel(true);
        }
    }

    public void _onRitualReinstallApp() {
        MyRITUALREINSTALL myRITUALREINSTALL = this.myRITUALREINSTALL;
        if (myRITUALREINSTALL != null && myRITUALREINSTALL.isRunning) {
            this.myRITUALREINSTALL.cancelRITUALREINSTALLTask();
        }
        MyRITUALREINSTALL myRITUALREINSTALL2 = new MyRITUALREINSTALL();
        this.myRITUALREINSTALL = myRITUALREINSTALL2;
        myRITUALREINSTALL2.execute(new Void[0]);
    }

    public class MyRITUALREINSTALL extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyRITUALREINSTALL() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            AppFragmentActivity.this.tv_status.setText("Install Ulang App\nProses...");
            AppFragmentActivity.this.tv_ritual_result.setText(AppFragmentActivity.this.s_commandResult);
            AppFragmentActivity.this.updateTextRitual();
            AppFragmentActivity.this.s_ritual_app = "app_package=\"fupackagename\"\nbase_path=$(pm path $app_package)\nfull_path=${base_path#*:}\ntmp_path=\"/data/local/tmp/\"\n\nmkdir -p $tmp_path > /dev/null 2>&1;\necho \"Persiapan proses reinstall aplikasi\"\ncp \"$full_path\" \"$tmp_path\" > /dev/null 2>&1;\n\nif [ -f \"$tmp_path/${full_path##*/}\" ] > /dev/null 2>&1; then\n\nif pm uninstall $app_package > /dev/null 2>&1; then\necho \"Sukses menguninstal aplikasi\"\nif pm install -r \"$tmp_path/base.apk\" > /dev/null 2>&1; then\necho \"Sukses menginstal ulang aplikasi\"\nelse\necho \"Gagal menginstal aplikasi \"\nfi\n\nelse\necho \"Gagal menguninstall aplikasi\"\nfi\n\nelse\necho \"Gagal menguninstall aplikasi\"\nfi";
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_ritual_app = appFragmentActivity.s_ritual_app.replace("fupackagename", AppFragmentActivity.this.s_package_ritual);
            AppFragmentActivity.this.b_command = false;
            AppFragmentActivity.this.s_exitCode = "";
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            AppFragmentActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(AppFragmentActivity.this.s_ritual_app).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            AppFragmentActivity.this.b_command = resultExec.isSuccess();
            AppFragmentActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            AppFragmentActivity.this._cekWipeGms();
        }

        public void cancelRITUALREINSTALLTask() {
            cancel(true);
        }
    }

    public void _onRitualWipeGms() {
        MyRITUALWIPEGMS myRITUALWIPEGMS = this.myRITUALWIPEGMS;
        if (myRITUALWIPEGMS != null && myRITUALWIPEGMS.isRunning) {
            this.myRITUALWIPEGMS.cancelRITUALWIPEGMSTask();
        }
        MyRITUALWIPEGMS myRITUALWIPEGMS2 = new MyRITUALWIPEGMS();
        this.myRITUALWIPEGMS = myRITUALWIPEGMS2;
        myRITUALWIPEGMS2.execute(new Void[0]);
    }

    public class MyRITUALWIPEGMS extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyRITUALWIPEGMS() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            AppFragmentActivity.this.tv_status.setText("Wipe Data GMS\nProses...");
            AppFragmentActivity.this.tv_ritual_result.setText(AppFragmentActivity.this.s_commandResult);
            AppFragmentActivity.this.updateTextRitual();
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_ritual_app = appFragmentActivity.s_commandBase.concat("\nritualgms");
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            AppFragmentActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(AppFragmentActivity.this.s_ritual_app).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            AppFragmentActivity.this.b_command = resultExec.isSuccess();
            AppFragmentActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            AppFragmentActivity.this._cekCleanTimepick();
        }

        public void cancelRITUALWIPEGMSTask() {
            cancel(true);
        }
    }

    public void _onRitualTimepick() {
        MyRITUALTIMEPICK myRITUALTIMEPICK = this.myRITUALTIMEPICK;
        if (myRITUALTIMEPICK != null && myRITUALTIMEPICK.isRunning) {
            this.myRITUALTIMEPICK.cancelRITUALTIMEPICKTask();
        }
        MyRITUALTIMEPICK myRITUALTIMEPICK2 = new MyRITUALTIMEPICK();
        this.myRITUALTIMEPICK = myRITUALTIMEPICK2;
        myRITUALTIMEPICK2.execute(new Void[0]);
    }

    public class MyRITUALTIMEPICK extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyRITUALTIMEPICK() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            AppFragmentActivity.this.tv_status.setText("Cleaning\nProses... (wait)");
            AppFragmentActivity.this.tv_ritual_result.setText(AppFragmentActivity.this.s_commandResult);
            AppFragmentActivity.this.s_commandResult = "";
            AppFragmentActivity.this.b_command = false;
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_ritual_app = appFragmentActivity.s_commandBase.concat("\nritualcln");
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            AppFragmentActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(AppFragmentActivity.this.s_ritual_app).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            AppFragmentActivity.this.b_command = resultExec.isSuccess();
            AppFragmentActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            this.isRunning = false;
            AppFragmentActivity.this.pbar_ritual.setVisibility(4);
            AppFragmentActivity.this.tv_status.setText("\nSelesai");
            AppFragmentActivity.this.tv_ritual_result.setText(AppFragmentActivity.this.s_commandResult);
            AppFragmentActivity.this.updateTextRitual();
        }

        public void cancelRITUALTIMEPICKTask() {
            cancel(true);
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
            AppFragmentActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(AppFragmentActivity.this.s_command).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            AppFragmentActivity.this.b_command = resultExec.isSuccess();
            AppFragmentActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            this.isRunning = false;
            AppFragmentActivity.this.b_command = true;
            AppFragmentActivity.this.b_restore = true;
        }

        public void cancelBackgroundActionTask() {
            cancel(true);
        }
    }

    public void _setAppPosition() {
        if (this.prefui.getString("backup_app_package", "").equals("")) {
            return;
        }
        int i = 0;
        if (this.lv_app.getVisibility() == 0) {
            this.n_restore_pos_app = 0.0d;
            while (i < this.lm_all_app.size()) {
                if (this.lm_all_app.get((int) this.n_restore_pos_app).get("apppackage").toString().equals(this.prefui.getString("backup_app_package", ""))) {
                    this.lv_app.setSelection((int) this.n_restore_pos_app);
                    return;
                } else {
                    this.n_restore_pos_app += 1.0d;
                    i++;
                }
            }
            return;
        }
        if (this.lv_fav.getVisibility() == 0) {
            this.n_restore_pos_app = 0.0d;
            while (i < this.lm_fav_app.size()) {
                if (this.lm_fav_app.get((int) this.n_restore_pos_app).get("apppackage").toString().equals(this.prefui.getString("backup_app_package", ""))) {
                    this.lv_fav.setSelection((int) this.n_restore_pos_app);
                    return;
                } else {
                    this.n_restore_pos_app += 1.0d;
                    i++;
                }
            }
        }
    }

    public void _onCreateBackup() {
        if (this.b_rebackup) {
            BackupDialogFragmentActivity backupDialogFragmentActivity = new BackupDialogFragmentActivity();
            Bundle bundle = new Bundle();
            bundle.putString("extrakey", this.s_extra);
            backupDialogFragmentActivity.setArguments(bundle);
            backupDialogFragmentActivity.show(getActivity().getSupportFragmentManager(), "BackupDialogFragmentActivity1");
            return;
        }
        BackupDialogFragmentActivity backupDialogFragmentActivity2 = new BackupDialogFragmentActivity();
        Bundle bundle2 = new Bundle();
        bundle2.putString("extrakey", "");
        backupDialogFragmentActivity2.setArguments(bundle2);
        backupDialogFragmentActivity2.show(getActivity().getSupportFragmentManager(), "BackupDialogFragmentActivity1");
    }

    public void _showLoadingMain(String str) {
        if (getActivity() instanceof KatrinaActivity) {
            ((KatrinaActivity) getActivity())._showLoadingMain(str);
        }
    }

    public void _hideLoadingMain() {
        if (getActivity() instanceof KatrinaActivity) {
            ((KatrinaActivity) getActivity())._hideLoadingMain();
        }
    }

    public void _startSorterBackup() {
        if (getActivity() instanceof KatrinaActivity) {
            ((KatrinaActivity) getActivity())._startFiltering();
        }
    }

    public void _showDialogFilter() {
        showFILTER();
    }

    private void showFILTER() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.backup_dialog_filter, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(true);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_oke);
        Button button2 = (Button) viewInflate.findViewById(C0978R.id.btn_cancel);
        final CheckBox checkBox = (CheckBox) viewInflate.findViewById(C0978R.id.cb_move);
        final CheckBox checkBox2 = (CheckBox) viewInflate.findViewById(C0978R.id.cb_delete);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) viewInflate.findViewById(C0978R.id.auto_01);
        autoCompleteTextView.setFocusable(true);
        autoCompleteTextView.setFocusableInTouchMode(true);
        autoCompleteTextView.setText(this.prefui.getString("short_filter", ""));
        autoCompleteTextView.setHint("Cari catatan");
        if (this.prefui.getString("short_action", "").equals("move")) {
            checkBox.setChecked(true);
            checkBox2.setChecked(false);
        } else if (this.prefui.getString("short_action", "").equals("delete")) {
            checkBox2.setChecked(true);
            checkBox.setChecked(false);
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z) {
                    AppFragmentActivity.this.prefui.edit().putString("short_action", "").commit();
                } else {
                    checkBox2.setChecked(false);
                    AppFragmentActivity.this.prefui.edit().putString("short_action", "move").commit();
                }
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z) {
                    AppFragmentActivity.this.prefui.edit().putString("short_action", "").commit();
                } else {
                    checkBox.setChecked(false);
                    AppFragmentActivity.this.prefui.edit().putString("short_action", "delete").commit();
                }
            }
        });
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {             @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                AppFragmentActivity.this.s_filterbackup = charSequence.toString();
                AppFragmentActivity.this.prefui.edit().putString("short_filter", AppFragmentActivity.this.s_filterbackup).commit();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AppFragmentActivity.this.FILTER == null || !AppFragmentActivity.this.FILTER.isShowing()) {
                    return;
                }
                AppFragmentActivity.this.FILTER.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AppFragmentActivity.this.prefui.getString("short_filter", "").equals("") || AppFragmentActivity.this.prefui.getString("short_action", "").equals("")) {
                    SketchwareUtil.showMessage(AppFragmentActivity.this.getContext().getApplicationContext(), "Data tidak lengkap");
                    return;
                }
                AppFragmentActivity.this._onStartFilter();
                if (AppFragmentActivity.this.FILTER == null || !AppFragmentActivity.this.FILTER.isShowing()) {
                    return;
                }
                AppFragmentActivity.this.FILTER.dismiss();
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.FILTER = alertDialogCreate;
        alertDialogCreate.show();
    }

    public void _onStartFilter() {
        MySTARTSORTIR mySTARTSORTIR = this.mySTARTSORTIR;
        if (mySTARTSORTIR != null && mySTARTSORTIR.isRunning) {
            this.mySTARTSORTIR.cancelSTARTSORTIRTask();
        }
        MySTARTSORTIR mySTARTSORTIR2 = new MySTARTSORTIR();
        this.mySTARTSORTIR = mySTARTSORTIR2;
        mySTARTSORTIR2.execute(new Void[0]);
    }

    public class MySTARTSORTIR extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MySTARTSORTIR() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            AppFragmentActivity.this._showLoadingMain("Melakukan pencarian dan sortir");
            AppFragmentActivity.this.s_cek_sortir = FileUtil.getExternalStorageDir().concat("/".concat("XKatrinaSortir"));
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_cek_folder = appFragmentActivity.s_cek_sortir.concat("/".concat(AppFragmentActivity.this.prefui.getString("backup_app_package", "")));
            if (!FileUtil.isExistFile(AppFragmentActivity.this.s_cek_sortir)) {
                FileUtil.makeDir(AppFragmentActivity.this.s_cek_sortir);
            }
            if (!FileUtil.isExistFile(AppFragmentActivity.this.s_cek_folder)) {
                FileUtil.makeDir(AppFragmentActivity.this.s_cek_folder);
            }
            AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
            appFragmentActivity2.s_filterbackup = appFragmentActivity2.prefui.getString("short_filter", "");
            AppFragmentActivity.this.n_shortfilter = 0.0d;
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            int i;
            if (isCancelled()) {
                return null;
            }
            for (int i2 = 0; i2 < AppFragmentActivity.this.lm_backup_data.size(); i2++) {
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity.s_string = ((HashMap) appFragmentActivity.lm_backup_data.get((int) AppFragmentActivity.this.n_shortfilter)).get("appnote").toString();
                if (!AppFragmentActivity.this.prefui.getString("short_action", "").equals("move")) {
                    if (AppFragmentActivity.this.s_string.contains(AppFragmentActivity.this.s_filterbackup)) {
                        AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
                        appFragmentActivity2.s_string_folder = ((HashMap) appFragmentActivity2.lm_backup_data.get((int) AppFragmentActivity.this.n_shortfilter)).get("appfolder").toString();
                        FileUtil.deleteFile(AppFragmentActivity.this.s_string_folder);
                    }
                } else {
                    if (!FileUtil.isExistFile(AppFragmentActivity.this.s_cek_folder)) {
                        AppFragmentActivity.this.s_backup_number = "001";
                    } else {
                        String[] list = new File(AppFragmentActivity.this.s_cek_folder).list();
                        Arrays.sort(list);
                        int i3 = 0;
                        for (String str : list) {
                            if (AppFragmentActivity.this.isInteger(str) && (i = Integer.parseInt(str)) > i3) {
                                i3 = i;
                            }
                        }
                        AppFragmentActivity.this.s_backup_number = String.format("%03d", Integer.valueOf(i3 + 1));
                    }
                    if (AppFragmentActivity.this.s_string.contains(AppFragmentActivity.this.s_filterbackup)) {
                        AppFragmentActivity appFragmentActivity3 = AppFragmentActivity.this;
                        appFragmentActivity3.s_shortslot = appFragmentActivity3.s_cek_folder.concat("/".concat(AppFragmentActivity.this.s_backup_number));
                        FileUtil.makeDir(AppFragmentActivity.this.s_shortslot);
                        AppFragmentActivity appFragmentActivity4 = AppFragmentActivity.this;
                        appFragmentActivity4.s_string_folder = ((HashMap) appFragmentActivity4.lm_backup_data.get((int) AppFragmentActivity.this.n_shortfilter)).get("appfolder").toString();
                        File file = new File(AppFragmentActivity.this.s_string_folder);
                        File file2 = new File(AppFragmentActivity.this.s_shortslot);
                        if (file.isDirectory()) {
                            if (!file2.exists()) {
                                file2.mkdirs();
                            }
                            File[] fileArrListFiles = file.listFiles();
                            if (fileArrListFiles != null) {
                                for (File file3 : fileArrListFiles) {
                                    file3.renameTo(new File(file2, file3.getName()));
                                }
                            }
                            file.delete();
                            System.out.println("Folder berhasil dipindahkan.");
                        } else {
                            System.out.println("Sumber direktori tidak ditemukan atau bukan direktori.");
                        }
                    }
                }
                AppFragmentActivity.this.n_shortfilter += 1.0d;
            }
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            AppFragmentActivity.this._hideLoadingMain();
            AppFragmentActivity.this._onReadBackup();
        }

        public void cancelSTARTSORTIRTask() {
            cancel(true);
        }
    }

    public class Lv_appAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_appAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = AppFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(C0978R.layout.backup_listview_app, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(C0978R.id.ln_01);
            TextView textView = (TextView) view.findViewById(C0978R.id.tv_appname);
            final ImageView imageView = (ImageView) view.findViewById(C0978R.id.civ_icon);
            try {
                imageView.setImageDrawable(AppFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(this._data.get(i).get("apppackage").toString()));
                textView.setText(this._data.get(i).get("appname").toString());
            } catch (PackageManager.NameNotFoundException unused) {
                textView.setText(this._data.get(i).get("appname").toString());
            }
            linearLayout.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.prefui.edit().putString("backup_app_name", Lv_appAdapter.this._data.get(i).get("appname").toString()).commit();
                    AppFragmentActivity.this.prefui.edit().putString("backup_app_package", Lv_appAdapter.this._data.get(i).get("apppackage").toString()).commit();
                    AppFragmentActivity.this._setSearchNull();
                    AppFragmentActivity.this._onReadBackup();
                    AppFragmentActivity.this._setBackupTopChip();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this._showPopupApp(i, Lv_appAdapter.this._data.get(i).get("apppackage").toString(), Lv_appAdapter.this._data.get(i).get("appname").toString(), Lv_appAdapter.this._data.get(i).get("appversion").toString(), imageView);
                }
            });
            return view;
        }
    }

    public class Lv_favAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_favAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = AppFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(C0978R.layout.backup_listview_app, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(C0978R.id.ln_01);
            TextView textView = (TextView) view.findViewById(C0978R.id.tv_appname);
            final ImageView imageView = (ImageView) view.findViewById(C0978R.id.civ_icon);
            try {
                imageView.setImageDrawable(AppFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(this._data.get(i).get("apppackage").toString()));
                textView.setText(this._data.get(i).get("appname").toString());
            } catch (PackageManager.NameNotFoundException unused) {
                textView.setText(this._data.get(i).get("appname").toString());
            }
            linearLayout.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.prefui.edit().putString("backup_app_name", Lv_favAdapter.this._data.get(i).get("appname").toString()).commit();
                    AppFragmentActivity.this.prefui.edit().putString("backup_app_package", Lv_favAdapter.this._data.get(i).get("apppackage").toString()).commit();
                    AppFragmentActivity.this._setSearchNull();
                    AppFragmentActivity.this._onReadBackup();
                    AppFragmentActivity.this._setBackupTopChip();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this._showPopupFav(i, Lv_favAdapter.this._data.get(i).get("apppackage").toString(), Lv_favAdapter.this._data.get(i).get("appname").toString(), Lv_favAdapter.this._data.get(i).get("appversion").toString(), imageView);
                }
            });
            return view;
        }
    }

    public class Lv_backupAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_backupAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            MaterialButton materialButton;
            View viewInflate = view == null ? AppFragmentActivity.this.getActivity().getLayoutInflater().inflate(C0978R.layout.backup_listview_backup, (ViewGroup) null) : view;
            MaterialCardView materialCardView = (MaterialCardView) viewInflate.findViewById(C0978R.id.cv_backup);
            LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_shadow);
            TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_date);
            TextView textView2 = (TextView) viewInflate.findViewById(C0978R.id.tv_nomer);
            MaterialButton materialButton2 = (MaterialButton) viewInflate.findViewById(C0978R.id.btn_hapus);
            final MaterialButton materialButton3 = (MaterialButton) viewInflate.findViewById(C0978R.id.btn_tandai);
            MaterialButton materialButton4 = (MaterialButton) viewInflate.findViewById(C0978R.id.btn_info);
            MaterialButton materialButton5 = (MaterialButton) viewInflate.findViewById(C0978R.id.btn_rebackup);
            MaterialButton materialButton6 = (MaterialButton) viewInflate.findViewById(C0978R.id.btn_restore);
            TextView textView3 = (TextView) viewInflate.findViewById(C0978R.id.tv_build);
            TextView textView4 = (TextView) viewInflate.findViewById(C0978R.id.tv_size);
            TextView textView5 = (TextView) viewInflate.findViewById(C0978R.id.tv_note);
            MaterialButton materialButton7 = (MaterialButton) viewInflate.findViewById(C0978R.id.btn_sharebot);
            View view2 = viewInflate;
            textView2.setText(Uri.parse(this._data.get(i).get("appfolder").toString()).getLastPathSegment());
            textView3.setText(this._data.get(i).get("appbuild").toString());
            textView.setText(this._data.get(i).get("appdate").toString());
            textView4.setText(this._data.get(i).get("appsize").toString());
            if (this._data.get(i).get("appnote").toString().equals("")) {
                textView5.setText("Buat catatan disini");
            } else {
                textView5.setText(this._data.get(i).get("appnote").toString());
            }
            if (this._data.get(i).get("appmark").toString().equals("true")) {
                AppFragmentActivity.this.s_markcolor = this._data.get(i).get("appcolor").toString();
                materialButton3.setIcon(AppFragmentActivity.this.getResources().getDrawable(C0978R.drawable.ic_mark_off));
                materialCardView.setCardBackgroundColor(Color.parseColor(AppFragmentActivity.this.s_markcolor));
                if (AppFragmentActivity.this.s_markcolor.equals("#FF9866E8") || AppFragmentActivity.this.s_markcolor.equals("#FFF9635F") || AppFragmentActivity.this.s_markcolor.equals("#FFF9635F") || AppFragmentActivity.this.s_markcolor.equals("#FF5CD574") || AppFragmentActivity.this.s_markcolor.equals("#FF4294FF")) {
                    materialButton = materialButton6;
                    materialButton2.setIconTintResource(C0978R.color.light80);
                    materialButton3.setIconTintResource(C0978R.color.light80);
                    materialButton4.setIconTintResource(C0978R.color.light80);
                    materialButton5.setIconTintResource(C0978R.color.light80);
                    materialButton7.setIconTintResource(C0978R.color.light80);
                    textView2.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.light80));
                    textView.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.light80));
                    textView3.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.light80));
                    textView5.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.light80));
                    textView4.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.light80));
                    materialButton.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.light80));
                } else {
                    materialButton2.setIconTintResource(C0978R.color.dark80);
                    materialButton3.setIconTintResource(C0978R.color.dark80);
                    materialButton4.setIconTintResource(C0978R.color.dark80);
                    materialButton5.setIconTintResource(C0978R.color.dark80);
                    materialButton7.setIconTintResource(C0978R.color.dark80);
                    textView2.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.dark80));
                    textView.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.dark80));
                    textView3.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.dark80));
                    textView5.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.dark80));
                    textView4.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.dark80));
                    materialButton = materialButton6;
                    materialButton.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.dark80));
                }
            } else {
                materialButton = materialButton6;
                materialButton3.setIcon(AppFragmentActivity.this.getResources().getDrawable(C0978R.drawable.ic_mark_on));
                materialCardView.setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(AppFragmentActivity.this.requireContext()));
                materialButton2.setIconTintResource(C0978R.color.dark80);
                materialButton3.setIconTintResource(C0978R.color.dark80);
                materialButton4.setIconTintResource(C0978R.color.dark80);
                materialButton5.setIconTintResource(C0978R.color.dark80);
                materialButton7.setIconTintResource(C0978R.color.dark80);
                textView2.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.dark80));
                textView.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.dark80));
                textView3.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.dark80));
                textView5.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.dark80));
                textView4.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.dark80));
                materialButton.setTextColor(AppFragmentActivity.this.getResources().getColor(C0978R.color.dark80));
            }
            if (i == this._data.size() - 1) {
                linearLayout.setVisibility(0);
            } else {
                linearLayout.setVisibility(8);
            }
            materialButton3.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    AppFragmentActivity.this._showPopupBackup(i, Lv_backupAdapter.this._data, materialButton3);
                }
            });
            materialButton2.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    AppFragmentActivity.this._onDeleteFileBackup(i, Lv_backupAdapter.this._data.get(i).get("appfolder").toString(), Lv_backupAdapter.this._data.get(i).get("appdate").toString(), Lv_backupAdapter.this._data.get(i).get("appsize").toString());
                }
            });
            materialButton4.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    AppFragmentActivity.this.s_path_json = FileUtil.readFile(Lv_backupAdapter.this._data.get(i).get("appjson").toString());
                    AppFragmentActivity.this._showInfoBackup();
                }
            });
            textView5.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    AppFragmentActivity.this.n_position = i;
                    AppFragmentActivity.this._showEditNote();
                }
            });
            materialButton.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    AppFragmentActivity.this._showDialogRestore(Lv_backupAdapter.this._data.get(i).get("appfile").toString(), Lv_backupAdapter.this._data.get(i).get("settings_ssaid").toString(), Lv_backupAdapter.this._data.get(i).get("system.prop").toString(), Lv_backupAdapter.this._data.get(i).get("appsdk").toString());
                }
            });
            materialButton5.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    AppFragmentActivity.this.b_rebackup = true;
                    AppFragmentActivity.this.m_extra = new HashMap();
                    AppFragmentActivity.this.m_extra.put("appfolder", Lv_backupAdapter.this._data.get(i).get("appfolder").toString());
                    AppFragmentActivity.this.m_extra.put("appfile", Lv_backupAdapter.this._data.get(i).get("appfile").toString());
                    AppFragmentActivity.this.s_extra = new Gson().toJson(AppFragmentActivity.this.m_extra);
                    AppFragmentActivity.this._onCreateBackup();
                }
            });
            materialButton7.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    AppFragmentActivity.this.ls_backupbot.clear();
                    AppFragmentActivity.this.ls_backupbot.add(Lv_backupAdapter.this._data.get(i).get("appfile").toString());
                    AppFragmentActivity.this.ls_backupbot.add(Lv_backupAdapter.this._data.get(i).get("appjson").toString());
                    AppFragmentActivity.this._openDialogBot();
                }
            });
            return view2;
        }
    }
}
