package com.fufufu.katrina.backup;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fufufu.katrina.backup.RequestNetwork;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.elevation.SurfaceColors;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.topjohnwu.superuser.Shell;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EternalFragmentActivity extends Fragment {
    private AlertDialog COMPLETE;
    private AlertDialog ETERNAL;
    private AlertDialog ETERNALREAD;
    private RequestNetwork.RequestListener _get_branch_all_request_listener;
    private RequestNetwork.RequestListener _get_branch_child1_request_listener;
    private RequestNetwork.RequestListener _get_branch_child2_request_listener;
    private AutoCompleteTextView auto_input_fp;
    private AutoCompleteTextView auto_input_model;
    private MaterialButton btn_apply;
    private Button btn_back;
    private MaterialButton btn_collapse_input;
    private Button btn_dump;
    private Button btn_dumpall;
    private MaterialButton btn_get_dump;
    private Button btn_prop;
    private AutoCompleteTextView et_input_dump;
    private ExtendedFloatingActionButton extendedfab_eternal;
    private ExtendedFloatingActionButton extendedfab_inject;
    private ExtendedFloatingActionButton extendedfab_mode;
    private RequestNetwork get_branch_all;
    private RequestNetwork get_branch_child1;
    private RequestNetwork get_branch_child2;
    private MaterialButton im_eternal;
    private ImageView im_icon_app;
    private LinearLayout ln_01;
    private LinearLayout ln_02;
    private LinearLayout ln_03;
    private LinearLayout ln_04;
    private LinearLayout ln_05;
    private LinearLayout ln_06;
    private LinearLayout ln_07;
    private LinearLayout ln_08;
    private LinearLayout ln_base_top;
    private LinearLayout ln_input_dump;
    private ExpandableLayout ln_input_fp;
    private LinearLayout ln_input_prop;
    private LinearLayout ln_left;
    private LinearLayout ln_title_input;
    private ListView lv_1;
    private ListView lv_branch_all;
    private MaterialCardView materialcardview1;
    private MaterialCardView materialcardview2;
    private MaterialCardView mcv_icon_app;
    private FloatingActionButton mfab_app;
    private MyPROPDUMP myPROPDUMP;
    private ProgressBar pbar_eternal;
    private ProgressBar pbar_prop;
    private SharedPreferences pref;
    private SharedPreferences pref_eid;
    private SharedPreferences prefall;
    private SharedPreferences prefeternal;
    private SharedPreferences prefui;
    private RecyclerView rv_1;
    private TextInputLayout til_dump;
    private TextInputLayout til_input_fp;
    private TextInputLayout til_input_model;
    private TextView tv_eternal_subtitle;
    private TextView tv_eternal_title;
    private TextView tv_input_fingerprint;
    private TextView tv_prop_type;
    private TextView tv_response;
    private TextView tv_title;
    private HashMap<String, Object> mProp = new HashMap<>();
    private String s_click_brand = "";
    private String s_rv = "";
    private boolean eternal = false;
    private String s_update_prop = "";
    private String s_new_prop = "";
    private String s_command = "";
    private String s_commandResult = "";
    private String s_exitCode = "";
    private String s_prop_result = "";
    private String s_source = "";
    private String s_target = "";
    private String s_eternal_title = "";
    private String s_eternal_message = "";
    private boolean b_command = false;
    private String s_feed_dump = "";
    private String s_add_prop = "";
    private String s_custom_prop = "";
    private String s_dump_model = "";
    private String s_rv2 = "";
    private HashMap<String, Object> m_input = new HashMap<>();
    private String s_parsemodel = "";
    private String s_parsebrand = "";
    private String s_parseproduct = "";
    private String s_parsedevice = "";
    private String s_parserelease = "";
    private String s_parsebuildid = "";
    private String s_parseincremental = "";
    private String s_json_old = "";
    private String s_data_old = "";
    private String s_data_new = "";
    private String s_json_result = "";
    private String s_input_json = "";
    private String s_input_prop = "";
    private String s_add_prop_base = "";
    private String s_input_model = "";
    private String s_dump_all = "";
    private String s_raw_model = "";
    private String s_dump_raw = "";
    private String s_dump_head = "";
    private String s_prop_incremental = "";
    private String s_prop_release = "";
    private String s_prop_buildid = "";
    private String s_prop_name = "";
    private String s_prop_description = "";
    private String s_prop_display = "";
    private String s_response_result = "";
    private HashMap<String, Object> m_prop = new HashMap<>();

        private double f810n = 0.0d;
    private String s_prop_match = "";
    private HashMap<String, Object> m_branch_all = new HashMap<>();
    private String s_dump_prop2 = "";
    private String s_dump_prop1 = "";
    private String s_url_prop2 = "";
    private String s_url_prop1 = "";
    private String s_prop_branch_all = "";
    private String s_loc = "";
    private String s_commandBase = "";
    private String s_fufufu_dump_online = "";
    private ArrayList<HashMap<String, Object>> lm_prop = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_brand = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_model = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_eternal_app = new ArrayList<>();
    private ArrayList<String> ls_feed_dump = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_asset = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_input = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_old_prop = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_final = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_dump_prop = new ArrayList<>();
    private ArrayList<String> ls_dump_prop = new ArrayList<>();
    private ArrayList<String> ls_branch_all = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_branch_all = new ArrayList<>();
    private ObjectAnimator oa1 = new ObjectAnimator();

    public void _EXTRA() {
    }

    public void _EXTRA2() {
    }

    public void _EXTRARANDOM() {
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C0978R.layout.eternal_fragment, viewGroup, false);
        initialize(bundle, viewInflate);
        initializeLogic();
        return viewInflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.ln_04 = (LinearLayout) view.findViewById(C0978R.id.ln_04);
        this.ln_base_top = (LinearLayout) view.findViewById(C0978R.id.ln_base_top);
        this.ln_01 = (LinearLayout) view.findViewById(C0978R.id.ln_01);
        this.ln_03 = (LinearLayout) view.findViewById(C0978R.id.ln_03);
        this.mcv_icon_app = (MaterialCardView) view.findViewById(C0978R.id.mcv_icon_app);
        this.ln_05 = (LinearLayout) view.findViewById(C0978R.id.ln_05);
        this.im_eternal = (MaterialButton) view.findViewById(C0978R.id.im_eternal);
        this.pbar_eternal = (ProgressBar) view.findViewById(C0978R.id.pbar_eternal);
        this.im_icon_app = (ImageView) view.findViewById(C0978R.id.im_icon_app);
        this.tv_eternal_title = (TextView) view.findViewById(C0978R.id.tv_eternal_title);
        this.tv_eternal_subtitle = (TextView) view.findViewById(C0978R.id.tv_eternal_subtitle);
        this.ln_title_input = (LinearLayout) view.findViewById(C0978R.id.ln_title_input);
        this.ln_input_fp = (ExpandableLayout) view.findViewById(C0978R.id.ln_input_fp);
        this.ln_06 = (LinearLayout) view.findViewById(C0978R.id.ln_06);
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
        this.ln_07 = (LinearLayout) view.findViewById(C0978R.id.ln_07);
        this.ln_08 = (LinearLayout) view.findViewById(C0978R.id.ln_08);
        this.btn_apply = (MaterialButton) view.findViewById(C0978R.id.btn_apply);
        this.til_input_model = (TextInputLayout) view.findViewById(C0978R.id.til_input_model);
        this.til_input_fp = (TextInputLayout) view.findViewById(C0978R.id.til_input_fp);
        this.auto_input_model = (AutoCompleteTextView) view.findViewById(C0978R.id.auto_input_model);
        this.auto_input_fp = (AutoCompleteTextView) view.findViewById(C0978R.id.auto_input_fp);
        this.ln_left = (LinearLayout) view.findViewById(C0978R.id.ln_left);
        this.lv_1 = (ListView) view.findViewById(C0978R.id.lv_1);
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
        this.mfab_app = (FloatingActionButton) view.findViewById(C0978R.id.mfab_app);
        this.extendedfab_eternal = (ExtendedFloatingActionButton) view.findViewById(C0978R.id.extendedfab_eternal);
        this.extendedfab_mode = (ExtendedFloatingActionButton) view.findViewById(C0978R.id.extendedfab_mode);
        this.extendedfab_inject = (ExtendedFloatingActionButton) view.findViewById(C0978R.id.extendedfab_inject);
        this.prefall = getContext().getSharedPreferences("all_app_preferences", 0);
        this.prefeternal = getContext().getSharedPreferences("eternal_preferences", 0);
        this.get_branch_all = new RequestNetwork((Activity) getContext());
        this.get_branch_child1 = new RequestNetwork((Activity) getContext());
        this.get_branch_child2 = new RequestNetwork((Activity) getContext());
        this.pref = getContext().getSharedPreferences("eternal", 0);
        this.pref_eid = getContext().getSharedPreferences("eternal_id", 0);
        this.prefui = getContext().getSharedPreferences("preferences_ui", 0);
        this.im_eternal.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._readEternalFile();
            }
        });
        this.btn_prop.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._setPropType("Termux Prop");
            }
        });
        this.btn_dump.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._setPropType("Android Dump");
            }
        });
        this.btn_dumpall.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._setPropType("Online Dump");
            }
        });
        this.btn_get_dump.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onGetDumpOnline();
            }
        });
        this.btn_collapse_input.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onCollapseFingerInput("clickbutton");
            }
        });
        this.btn_apply.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onConvertProp();
            }
        });
        this.btn_back.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onBackButton();
            }
        });
        this.mfab_app.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._showDialogEternalPicker();
            }
        });
        this.extendedfab_eternal.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onFabEternal();
            }
        });
        this.extendedfab_mode.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onFabMode();
            }
        });
        this.extendedfab_inject.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onInjectEternal();
            }
        });
        this._get_branch_all_request_listener = new RequestNetwork.RequestListener() {             @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> map) {
                if (!str2.contains("Sign in · GitLab")) {
                    EternalFragmentActivity.this.ls_branch_all.clear();
                    EternalFragmentActivity.this.lm_branch_all.clear();
                    EternalFragmentActivity.this.s_response_result = EternalFragmentActivity.removeLinesList(str2);
                    EternalFragmentActivity eternalFragmentActivity = EternalFragmentActivity.this;
                    eternalFragmentActivity.s_response_result = eternalFragmentActivity.s_response_result.replaceAll(".*data-branch-name=\"", "");
                    EternalFragmentActivity eternalFragmentActivity2 = EternalFragmentActivity.this;
                    eternalFragmentActivity2.s_response_result = eternalFragmentActivity2.s_response_result.replaceAll(".*data-default-branch=\"", "");
                    EternalFragmentActivity eternalFragmentActivity3 = EternalFragmentActivity.this;
                    eternalFragmentActivity3.s_response_result = eternalFragmentActivity3.s_response_result.replaceAll("\".*", "");
                    EternalFragmentActivity.this.ls_branch_all = new ArrayList(Arrays.asList(EternalFragmentActivity.this.s_response_result.split("\n")));
                    EternalFragmentActivity.this.f810n = 0.0d;
                    for (int i = 0; i < EternalFragmentActivity.this.ls_branch_all.size(); i++) {
                        EternalFragmentActivity eternalFragmentActivity4 = EternalFragmentActivity.this;
                        eternalFragmentActivity4.s_prop_branch_all = (String) eternalFragmentActivity4.ls_branch_all.get((int) EternalFragmentActivity.this.f810n);
                        String[] strArrSplit = EternalFragmentActivity.this.s_prop_branch_all.split("-", 6);
                        String str3 = strArrSplit[0];
                        String str4 = strArrSplit[1];
                        String str5 = strArrSplit[2];
                        String str6 = strArrSplit[3];
                        String str7 = strArrSplit[4];
                        String str8 = strArrSplit[5];
                        EternalFragmentActivity.this.m_branch_all = new HashMap();
                        EternalFragmentActivity.this.m_branch_all.put("device_url1", EternalFragmentActivity.this.s_dump_head.concat(EternalFragmentActivity.this.s_raw_model.concat(EternalFragmentActivity.this.s_prop_branch_all.concat(EternalFragmentActivity.this.s_dump_prop2))));
                        EternalFragmentActivity.this.m_branch_all.put("device_url2", EternalFragmentActivity.this.s_dump_head.concat(EternalFragmentActivity.this.s_raw_model.concat(EternalFragmentActivity.this.s_prop_branch_all.concat(EternalFragmentActivity.this.s_dump_prop1))));
                        EternalFragmentActivity.this.m_branch_all.put("device", str3);
                        EternalFragmentActivity.this.m_branch_all.put("release", str5);
                        EternalFragmentActivity.this.m_branch_all.put("buildid", str6);
                        EternalFragmentActivity.this.m_branch_all.put("incremental", str7);
                        EternalFragmentActivity.this.lm_branch_all.add(EternalFragmentActivity.this.m_branch_all);
                        EternalFragmentActivity.this.f810n += 1.0d;
                    }
                    SketchwareUtil.sortListMap(EternalFragmentActivity.this.lm_branch_all, "release", false, true);
                    ListView listView = EternalFragmentActivity.this.lv_branch_all;
                    EternalFragmentActivity eternalFragmentActivity5 = EternalFragmentActivity.this;
                    listView.setAdapter((ListAdapter) eternalFragmentActivity5.new Lv_branch_allAdapter(eternalFragmentActivity5.lm_branch_all));
                    EternalFragmentActivity.this._onLoadingOnline("clickafterget");
                    return;
                }
                EternalFragmentActivity.this._onLoadingOnline("clicknotfound");
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
                EternalFragmentActivity.this._onResponseError("Tidak ada koneksi internet");
            }
        };
        this._get_branch_child1_request_listener = new RequestNetwork.RequestListener() {             @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> map) {
                if (!str2.contains("Not Found")) {
                    EternalFragmentActivity.this.s_response_result = str2;
                    EternalFragmentActivity.this._getPropResult();
                } else {
                    EternalFragmentActivity.this.get_branch_child2.startRequestNetwork("GET", EternalFragmentActivity.this.s_url_prop2, "a", EternalFragmentActivity.this._get_branch_child2_request_listener);
                }
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
                EternalFragmentActivity.this._onResponseError(str2);
            }
        };
        this._get_branch_child2_request_listener = new RequestNetwork.RequestListener() {             @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> map) {
                if (!str2.contains("Not Found")) {
                    EternalFragmentActivity.this.s_response_result = str2;
                    EternalFragmentActivity.this._getPropResult();
                } else {
                    EternalFragmentActivity.this.tv_response.setVisibility(0);
                    EternalFragmentActivity.this.tv_response.setText("fufufu tidak dapat menemukan prop");
                }
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
                EternalFragmentActivity.this._onResponseError(str2);
            }
        };
    }

    private void initializeLogic() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {             @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                if (EternalFragmentActivity.this.getActivity() instanceof KatrinaActivity) {
                    ((KatrinaActivity) EternalFragmentActivity.this.getActivity())._fragmentApp();
                }
            }
        });
        _setFirstUI();
        _setFabAppIcon();
        _checkEternalFile();
        _onLoadBrand();
        _createRandomProp();
    }

    public static String acak(int i) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

    protected static String acakString(String str, int i) {
        Random random = new Random();
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = str.charAt(random.nextInt(str.length()));
        }
        return new String(cArr);
    }

    public static String RandomBOARD() {
        return new String[]{acak(4), acak(5), acak(6), acak(7)}[new Random().nextInt(4)];
    }

    public static String RandomBOOTLOADER() {
        return new String[]{acak(6), acak(7), acak(8), acak(9)}[new Random().nextInt(4)];
    }

    public static String RandomBRAND() {
        return new String[]{acak(5), acak(6), acak(7), acak(8)}[new Random().nextInt(4)];
    }

    public static String RandomBUILDID() {
        return new String[]{acak(9), acak(10), acak(11), acak(12)}[new Random().nextInt(4)];
    }

    public static String RandomDEVICE() {
        return new String[]{acak(5), acak(6), acak(7), acak(8)}[new Random().nextInt(4)];
    }

    public static String RandomFLAVOR() {
        return new String[]{acak(7), acak(8), acak(9), acak(10)}[new Random().nextInt(4)];
    }

    public static String RandomHOST() {
        return new String[]{acak(8), acak(9), acak(10), acak(11)}[new Random().nextInt(4)];
    }

    public static String RandomINCREMENTAL() {
        return new String[]{acak(7), acak(8), acak(9), acak(10)}[new Random().nextInt(4)];
    }

    public static String RandomMODEL() {
        return new String[]{acak(7), acak(8), acak(6), acak(5)}[new Random().nextInt(4)];
    }

    public static String RandomPRODUCT() {
        return new String[]{acak(8), acak(5), acak(6), acak(7)}[new Random().nextInt(4)];
    }

    public static String RandomRELEASE() {
        return new String[]{"9", "10", "11", "12", "13", "14"}[new Random().nextInt(6)];
    }

    public static String RandomUSER() {
        return new String[]{acak(10), acak(7), acak(8), acak(9)}[new Random().nextInt(4)];
    }

    public static String RandomMANUFACTURER() {
        return new String[]{acak(10), acak(7), acak(8), acak(9)}[new Random().nextInt(4)];
    }

    public static String RandomSERIAL() {
        return new String[]{acak(7), acak(8), acak(9), acak(10)}[new Random().nextInt(4)];
    }

    public static String RandomSERIAL2() {
        return new String[]{acak(8), acak(9), acak(10), acak(11)}[new Random().nextInt(4)];
    }

    public static String RandomBLUETHOOTNAME() {
        return new String[]{acak(8), acak(9), acak(10), acak(11)}[new Random().nextInt(4)];
    }

    public static String RandomDEVICENAME() {
        return new String[]{acak(8), acak(9), acak(10), acak(11)}[new Random().nextInt(4)];
    }

    public static String RandomHARDWARE() {
        return new String[]{acak(6), acak(4), acak(5)}[new Random().nextInt(3)];
    }

    public static String RandomANDROIDID() {
        return acakString("0123456789abcdef", 16);
    }

    public static String RandomTIME() {
        String str = new String[]{"15", "16"}[new Random().nextInt(2)];
        return String.valueOf(str) + (String.valueOf(acakString("0123456789", 8)) + "000");
    }

    public static String RandomIMEI() {
        String str = new String[]{"35", "86"}[new Random().nextInt(2)];
        return String.valueOf(str) + acakString("0123456789", 13);
    }

    public static String RandomIKLANID() {
        return UUID.randomUUID().toString();
    }

    public void _createRandomProp() {
        this.lm_prop.clear();
        HashMap<String, Object> map = new HashMap<>();
        this.mProp = map;
        map.put("IDIKLAN", RandomIKLANID());
        this.mProp.put("IMEI", RandomIMEI());
        this.mProp.put("MODEL", RandomMODEL());
        this.mProp.put("BOARD", RandomBOARD());
        this.mProp.put("TIME", RandomTIME());
        this.mProp.put("ANDROIDID", RandomANDROIDID());
        this.mProp.put("BLUETHOOTNAME", RandomBLUETHOOTNAME());
        this.mProp.put("DEVICENAME", RandomDEVICENAME());
        this.mProp.put("SERIAL", RandomSERIAL());
        this.mProp.put("SERIAL2", RandomSERIAL2());
        this.mProp.put("MANUFACTURER", RandomMANUFACTURER());
        this.mProp.put("USER", RandomUSER());
        this.mProp.put("RELEASE", RandomRELEASE());
        this.mProp.put("DEVICE", RandomDEVICE());
        this.mProp.put("BUILDID", RandomBUILDID());
        this.mProp.put("HARDWARE", RandomHARDWARE());
        this.mProp.put("BOOT", RandomBOOTLOADER());
        this.mProp.put("BRAND", RandomBRAND());
        this.mProp.put("PRODUCT", RandomPRODUCT());
        this.mProp.put("HOST", RandomHOST());
        this.mProp.put("INCREMENTAL", RandomINCREMENTAL());
        this.mProp.put("SDK", Build.VERSION.SDK);
        HashMap<String, Object> map2 = this.mProp;
        map2.put("NAME", map2.get("PRODUCT").toString());
        HashMap<String, Object> map3 = this.mProp;
        map3.put("DISPLAY", map3.get("BUILDID").toString().concat(".".concat(this.mProp.get("INCREMENTAL").toString())));
        HashMap<String, Object> map4 = this.mProp;
        map4.put("RADIOVERSION", map4.get("INCREMENTAL").toString().concat(",".concat(this.mProp.get("INCREMENTAL").toString())));
        this.mProp.put("HTTPAGENT", "Dalvik/2.1.0 (Linux; U; Android " + this.mProp.get("RELEASE") + "; " + this.mProp.get("MODEL") + " Build/" + this.mProp.get("BUILDID") + ")");
        this.mProp.put("USERAGENT", "Mozilla/5.0 (Linux; Android " + this.mProp.get("RELEASE") + "; " + this.mProp.get("MODEL") + " Build/" + this.mProp.get("BUILDID") + "; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/109.0.5414.118 Mobile Safari/537.36");
        this.mProp.put("FINGERPRINT", this.mProp.get("BRAND") + "/" + this.mProp.get("PRODUCT") + "/" + this.mProp.get("DEVICE") + ":" + this.mProp.get("RELEASE") + "/" + this.mProp.get("BUILDID") + "/" + this.mProp.get("INCREMENTAL") + ":user/release-keys");
        StringBuilder sb = new StringBuilder();
        sb.append(this.mProp.get("PRODUCT"));
        sb.append("-user ");
        sb.append(this.mProp.get("RELEASE"));
        sb.append(" ");
        sb.append(this.mProp.get("BUILDID"));
        sb.append(this.mProp.get("INCREMENTAL"));
        sb.append(" release-keys");
        this.mProp.put("DESCRIPTION", sb.toString());
        this.lm_prop.add(this.mProp);
        this.lv_1.setAdapter((ListAdapter) new Lv_1Adapter(this.lm_prop));
        ((BaseAdapter) this.lv_1.getAdapter()).notifyDataSetChanged();
    }

    public void _setFirstUI() {
        this.ln_input_fp.setExpansion(false);
        this.ln_input_fp.setDuration(350);
        this.ln_input_fp.setOrientation(1);
        this.lv_branch_all.setVerticalScrollBarEnabled(false);
        this.eternal = true;
        this.extendedfab_eternal.setText(R.string.eternal_mode_prop);
        this.extendedfab_mode.setText(R.string.eternal_mode_random);
        this.ln_left.setVisibility(8);
        this.pbar_eternal.setVisibility(8);
        this.ln_base_top.setVisibility(8);
        this.lv_branch_all.setVisibility(8);
        this.tv_response.setVisibility(8);
        this.pbar_prop.setVisibility(8);
        this.ln_input_dump.setVisibility(8);
        this.ln_input_prop.setVisibility(0);
        this.auto_input_model.setSingleLine(true);
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
        this.et_input_dump.setSingleLine(true);
        this.et_input_dump.setOnEditorActionListener(new TextView.OnEditorActionListener() {             @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 2) {
                    return false;
                }
                EternalFragmentActivity.this._onGetDumpOnline();
                return true;
            }
        });
    }

    public void _onLoadBrand() {
        this.tv_prop_type.setText(R.string.feature_buildprop);
        this.lm_json_brand.clear();
        try {
            this.lm_json_brand = RitualAssetRepository.loadPropData(getContext());
            this.rv_1.setAdapter(new Rv_1Adapter(this.lm_json_brand));
            this.rv_1.setLayoutManager(new LinearLayoutManager(getContext()));
            this.btn_back.setVisibility(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void _onLoadModel() {
        this.lm_json_model.clear();
        this.lm_json_model = (ArrayList) new Gson().fromJson(this.s_click_brand, new TypeToken<ArrayList<HashMap<String, Object>>>() {         }.getType());
        this.rv_1.setAdapter(new Rv_1Adapter(this.lm_json_model));
        this.rv_1.setLayoutManager(new LinearLayoutManager(getContext()));
        this.btn_back.setText(R.string.ritual_back_to_brand);
        this.tv_title.setText(this.s_rv);
        this.btn_back.setVisibility(0);
    }

    public void _onAdvanceBindBrand(View view, TextView textView, TextView textView2, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        textView.setText(arrayList.get((int) d).get("MEREK").toString());
        textView2.setVisibility(8);
        view.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this.s_click_brand = ((HashMap) arrayList.get((int) d)).get("MEREK").toString();
                EternalFragmentActivity.this.s_rv = ((HashMap) arrayList.get((int) d)).get("MEREK").toString();
                EternalFragmentActivity.this.s_click_brand = new Gson().toJson(((HashMap) arrayList.get((int) d)).get("DATA"));
                EternalFragmentActivity.this._onLoadModel();
            }
        });
    }

    public void _onAdvanceBindModel(View view, TextView textView, TextView textView2, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        int i = (int) d;
        textView.setText(arrayList.get(i).get("DEVICENAME").toString());
        textView2.setText(getString(R.string.ritual_os_format, arrayList.get(i).get("RELEASE").toString()));
        view.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onCreateJsonProp(d, arrayList);
            }
        });
    }

    public void _onBackButton() {
        if (this.btn_back.getText().toString().equals("BACK TO BRAND")) {
            this.rv_1.setAdapter(new Rv_1Adapter(this.lm_json_brand));
            this.rv_1.setLayoutManager(new LinearLayoutManager(getContext()));
            this.tv_title.setText(R.string.ritual_title_brand);
            this.btn_back.setVisibility(8);
            this.lm_prop.clear();
            ((BaseAdapter) this.lv_1.getAdapter()).notifyDataSetChanged();
            return;
        }
        if (this.btn_back.getText().toString().equals("BACK TO MODEL")) {
            this.rv_1.setAdapter(new Rv_1Adapter(this.lm_json_model));
            this.rv_1.setLayoutManager(new LinearLayoutManager(getContext()));
            this.btn_back.setText(R.string.ritual_back_to_brand);
            this.tv_title.setText(this.s_rv);
            this.rv_1.setVisibility(0);
            this.btn_back.setVisibility(0);
        }
    }

    public void _onCreateJsonProp(double d, ArrayList<HashMap<String, Object>> arrayList) {
        this.lm_prop.clear();
        HashMap<String, Object> map = new HashMap<>();
        this.mProp = map;
        map.put("IDIKLAN", RandomIKLANID());
        this.mProp.put("IMEI", RandomIMEI());
        int i = (int) d;
        this.mProp.put("MODEL", arrayList.get(i).get("MODEL").toString());
        this.mProp.put("BOARD", RandomBOARD());
        this.mProp.put("TIME", RandomTIME());
        this.mProp.put("ANDROIDID", RandomANDROIDID());
        this.mProp.put("BLUETHOOTNAME", RandomBLUETHOOTNAME());
        this.mProp.put("DEVICENAME", RandomDEVICENAME());
        this.mProp.put("SERIAL", RandomSERIAL());
        this.mProp.put("SERIAL2", RandomSERIAL2());
        this.mProp.put("MANUFACTURER", arrayList.get(i).get("MANUFACTURER").toString());
        this.mProp.put("USER", RandomUSER());
        this.mProp.put("RELEASE", arrayList.get(i).get("RELEASE").toString());
        this.mProp.put("DEVICE", arrayList.get(i).get("DEVICE").toString());
        this.mProp.put("BUILDID", arrayList.get(i).get("BUILDID").toString());
        this.mProp.put("HARDWARE", RandomHARDWARE());
        this.mProp.put("BOOT", arrayList.get(i).get("INCREMENTAL").toString());
        this.mProp.put("BRAND", arrayList.get(i).get("BRAND").toString());
        this.mProp.put("PRODUCT", arrayList.get(i).get("PRODUCT").toString());
        this.mProp.put("HOST", RandomHOST());
        this.mProp.put("INCREMENTAL", arrayList.get(i).get("INCREMENTAL").toString());
        this.mProp.put("SDK", Build.VERSION.SDK);
        this.mProp.put("NAME", arrayList.get(i).get("PRODUCT").toString());
        HashMap<String, Object> map2 = this.mProp;
        map2.put("DISPLAY", map2.get("BUILDID").toString().concat(".".concat(this.mProp.get("INCREMENTAL").toString())));
        HashMap<String, Object> map3 = this.mProp;
        map3.put("RADIOVERSION", map3.get("INCREMENTAL").toString().concat(",".concat(this.mProp.get("INCREMENTAL").toString())));
        this.mProp.put("HTTPAGENT", "Dalvik/2.1.0 (Linux; U; Android " + this.mProp.get("RELEASE") + "; " + this.mProp.get("MODEL") + " Build/" + this.mProp.get("BUILDID") + ")");
        this.mProp.put("USERAGENT", "Mozilla/5.0 (Linux; Android " + this.mProp.get("RELEASE") + "; " + this.mProp.get("MODEL") + " Build/" + this.mProp.get("BUILDID") + "; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/109.0.5414.118 Mobile Safari/537.36");
        this.mProp.put("FINGERPRINT", this.mProp.get("BRAND") + "/" + this.mProp.get("PRODUCT") + "/" + this.mProp.get("DEVICE") + ":" + this.mProp.get("RELEASE") + "/" + this.mProp.get("BUILDID") + "/" + this.mProp.get("INCREMENTAL") + ":user/release-keys");
        StringBuilder sb = new StringBuilder();
        sb.append(this.mProp.get("PRODUCT"));
        sb.append("-user ");
        sb.append(this.mProp.get("RELEASE"));
        sb.append(" ");
        sb.append(this.mProp.get("BUILDID"));
        sb.append(this.mProp.get("INCREMENTAL"));
        sb.append(" release-keys");
        this.mProp.put("DESCRIPTION", sb.toString());
        this.lm_prop.add(this.mProp);
        this.lv_1.setAdapter((ListAdapter) new Lv_1Adapter(this.lm_prop));
    }

    public void _onFabEternal() {
        if (this.eternal) {
            this.eternal = false;
            this.extendedfab_eternal.setText("AUTO");
            this.extendedfab_mode.setText("UPDATE");
            this.ln_base_top.setVisibility(0);
            this.ln_left.setVisibility(0);
            this.oa1.cancel();
            this.oa1.setTarget(this.ln_left);
            this.oa1.setPropertyName("translationX");
            this.oa1.setFloatValues(-500.0f, 0.0f);
            this.oa1.setDuration(300L);
            this.oa1.start();
            _setPropType("Termux Prop");
            return;
        }
        this.eternal = true;
        this.extendedfab_eternal.setText("PROP");
        this.extendedfab_mode.setText("ACAK");
        this.ln_base_top.setVisibility(8);
        this.ln_left.setVisibility(8);
        _createRandomProp();
    }

    public void _onFabMode() {
        if (this.eternal) {
            _createRandomProp();
        } else if (this.lm_prop.size() == 0) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Harap setting prop");
        } else {
            _onUpdateProp();
        }
    }

    public void _onUpdateProp() {
        String strReplace = new Gson().toJson(this.lm_prop).replace(this.lm_prop.get(0).get("INCREMENTAL").toString(), RandomINCREMENTAL());
        this.s_update_prop = strReplace;
        this.s_new_prop = strReplace.replace(this.lm_prop.get(0).get("HOST").toString(), RandomHOST());
        this.lm_prop.clear();
        this.lm_prop = (ArrayList) new Gson().fromJson(this.s_new_prop, new TypeToken<ArrayList<HashMap<String, Object>>>() {         }.getType());
        this.lv_1.setAdapter((ListAdapter) new Lv_1Adapter(this.lm_prop));
        ((BaseAdapter) this.lv_1.getAdapter()).notifyDataSetChanged();
    }

    public class lv_app_pickerAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public lv_app_pickerAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = EternalFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(C0978R.layout.eternal_app_picker_view, (ViewGroup) null);
            }
            TextView textView = (TextView) view.findViewById(C0978R.id.tv_eternal_list);
            ImageView imageView = (ImageView) view.findViewById(C0978R.id.im_eternal_list);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(C0978R.id.ln_eternal_list);
            try {
                imageView.setImageDrawable(EternalFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(((HashMap) EternalFragmentActivity.this.lm_eternal_app.get(i)).get("apppackage").toString()));
            } catch (PackageManager.NameNotFoundException unused) {
            }
            textView.setText(((HashMap) EternalFragmentActivity.this.lm_eternal_app.get(i)).get("appname").toString());
            linearLayout.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    EternalFragmentActivity.this.prefeternal.edit().putString("choose_app_eternal", ((HashMap) EternalFragmentActivity.this.lm_eternal_app.get(i)).get("appname").toString()).commit();
                    EternalFragmentActivity.this.prefeternal.edit().putString("choose_package_eternal", ((HashMap) EternalFragmentActivity.this.lm_eternal_app.get(i)).get("apppackage").toString()).commit();
                    EternalFragmentActivity.this._setFabAppIcon();
                    EternalFragmentActivity.this._checkEternalFile();
                    if (EternalFragmentActivity.this.ETERNAL == null || !EternalFragmentActivity.this.ETERNAL.isShowing()) {
                        return;
                    }
                    EternalFragmentActivity.this.ETERNAL.dismiss();
                }
            });
            return view;
        }
    }

    public void _showDialogEternalPicker() {
        showETERNAL();
    }

    private void showETERNAL() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.eternal_app_picker, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_close);
        TextView textView2 = (TextView) viewInflate.findViewById(C0978R.id.tv_title_eternal_picker);
        ListView listView = (ListView) viewInflate.findViewById(C0978R.id.lv_app_picker);
        ArrayList<HashMap<String, Object>> arrayList = (ArrayList) new Gson().fromJson(this.prefall.getString("all_app_eternal", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {         }.getType());
        this.lm_eternal_app = arrayList;
        if (arrayList.size() == 0) {
            textView2.setText(R.string.eternal_empty_apps);
            listView.setVisibility(8);
        } else {
            textView2.setText(R.string.eternal_list_title);
            listView.setVisibility(0);
            listView.setDivider(null);
            listView.setDividerHeight(0);
            listView.setAdapter((ListAdapter) new lv_app_pickerAdapter(this.lm_eternal_app));
            ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
            listView.setVerticalScrollBarEnabled(false);
        }
        textView.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EternalFragmentActivity.this.ETERNAL.dismiss();
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.ETERNAL = alertDialogCreate;
        alertDialogCreate.show();
    }

    public void _setFabAppIcon() {
        this.mcv_icon_app.setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(requireContext()));
        if (this.prefeternal.getString("choose_app_eternal", "").equals("")) {
            this.im_icon_app.setImageResource(C0978R.drawable.ic_application);
            return;
        }
        try {
            this.im_icon_app.setImageDrawable(getActivity().getPackageManager().getApplicationIcon(this.prefeternal.getString("choose_package_eternal", "")));
        } catch (PackageManager.NameNotFoundException unused) {
        }
        this.tv_eternal_title.setText(this.prefeternal.getString("choose_app_eternal", ""));
        this.tv_eternal_subtitle.setText(this.prefeternal.getString("choose_package_eternal", ""));
    }

    public void _onInjectEternal() {
        if (this.lm_prop.size() == 0) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), getString(R.string.eternal_need_prop));
            return;
        }
        if (this.prefeternal.getString("choose_package_eternal", "").equals("")) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), getString(R.string.eternal_need_app));
            return;
        }
        this.extendedfab_inject.setText(R.string.eternal_action_loading);
        this.s_prop_result = new GsonBuilder().setPrettyPrinting().create().toJson(this.lm_prop);
        try {
            JSONArray jSONArray = new JSONArray(this.s_prop_result);
            SharedPreferences.Editor editorEdit = this.pref_eid.edit();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    editorEdit.putString(next, jSONObject.getString(next));
                }
            }
            editorEdit.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s_source = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/eternal_id"));
        this.s_target = "/data/user/0/".concat(this.prefeternal.getString("choose_package_eternal", "").concat("/eternal_id"));
        this.s_loc = "/data/user/0/".concat(this.prefeternal.getString("choose_package_eternal", ""));
        FileUtil.writeFile(this.s_source, this.s_prop_result);
        String str = this.s_commandBase;
        this.s_command = str;
        String strReplace = str.replace("s_loc", this.s_loc);
        this.s_command = strReplace;
        String strReplace2 = strReplace.replace("s_source", this.s_source);
        this.s_command = strReplace2;
        String strReplace3 = strReplace2.replace("s_target", this.s_target);
        this.s_command = strReplace3;
        String strConcat = strReplace3.concat("\neternal");
        this.s_command = strConcat;
        this.b_command = false;
        Shell.Result resultExec = Shell.cmd(strConcat).exec();
        List<String> out = resultExec.getOut();
        resultExec.getCode();
        this.b_command = resultExec.isSuccess();
        this.s_commandResult = String.join("\n", out);
        this.extendedfab_inject.setText("INJECT");
        _checkEternalFile();
        _showDialogComplete();
    }

    public void _showDialogComplete() {
        showCOMPLETE();
    }

    private void showCOMPLETE() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.eternal_dialog_complete, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_command_result);
        TextView textView2 = (TextView) viewInflate.findViewById(C0978R.id.tv_result);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_1);
        Button button2 = (Button) viewInflate.findViewById(C0978R.id.btn_2);
        if (this.s_commandResult.equals("\n") || this.s_commandResult.equals("")) {
            textView.setText("Berhasil");
            textView2.setText(this.s_prop_result);
        } else {
            textView.setText("Gagal");
            textView2.setText("// Gagal memproses");
        }
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EternalFragmentActivity.this.COMPLETE.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EternalFragmentActivity eternalFragmentActivity = EternalFragmentActivity.this;
                eternalFragmentActivity._actionOpenPackage(eternalFragmentActivity.prefui.getString("backup_app_package", ""));
                EternalFragmentActivity.this.COMPLETE.dismiss();
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.COMPLETE = alertDialogCreate;
        alertDialogCreate.show();
    }

    public void _checkEternalFile() {
        if (this.prefeternal.getString("choose_package_eternal", "").equals("")) {
            return;
        }
        this.s_target = "/data/user/0/".concat(this.prefeternal.getString("choose_package_eternal", "").concat("/eternal_id"));
        if (executeCommand(new String[]{"su", "-c", "ls " + this.s_target})) {
            this.im_eternal.setAlpha(1.0f);
        } else {
            this.im_eternal.setAlpha(0.3f);
        }
    }

    private boolean executeCommand(String[] strArr) {
        try {
            return Runtime.getRuntime().exec(strArr).waitFor() == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void _readEternalFile() {
        if (this.prefeternal.getString("choose_package_eternal", "").equals("")) {
            return;
        }
        this.pbar_eternal.setVisibility(0);
        this.im_eternal.setVisibility(8);
        this.s_target = "/data/user/0/".concat(this.prefeternal.getString("choose_package_eternal", "").concat("/eternal_id"));
        if (executeCommand(new String[]{"su", "-c", "ls " + this.s_target})) {
            this.s_eternal_title = "Is Eternal";
            this.b_command = false;
            Shell.Result resultExec = Shell.cmd("cat ".concat(this.s_target)).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            this.b_command = resultExec.isSuccess();
            String strM63m = String.join("\n", out);
            this.s_commandResult = strM63m;
            this.s_eternal_message = strM63m;
            this.pbar_eternal.setVisibility(8);
            this.im_eternal.setVisibility(0);
            _showDialogReadEternal();
            return;
        }
        this.s_eternal_title = "Not Eternal";
        this.s_eternal_message = "Eternal belum di inject";
        this.pbar_eternal.setVisibility(8);
        this.im_eternal.setVisibility(0);
        _showDialogReadEternal();
    }

    public void _showDialogReadEternal() {
        showETERNALREAD();
    }

    private void showETERNALREAD() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C0978R.layout.eternal_dialog_complete, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_command_result);
        TextView textView2 = (TextView) viewInflate.findViewById(C0978R.id.tv_result);
        Button button = (Button) viewInflate.findViewById(C0978R.id.btn_1);
        textView.setText(this.s_eternal_title);
        textView2.setText(this.s_eternal_message);
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EternalFragmentActivity.this.ETERNALREAD.dismiss();
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.ETERNALREAD = alertDialogCreate;
        alertDialogCreate.show();
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

    public void _setPropType(String str) {
        this.lm_prop.clear();
        ((BaseAdapter) this.lv_1.getAdapter()).notifyDataSetChanged();
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
            EternalFragmentActivity.this.rv_1.setVisibility(0);
            EternalFragmentActivity.this.pbar_prop.setVisibility(0);
            EternalFragmentActivity.this.btn_prop.setEnabled(false);
            EternalFragmentActivity.this.btn_dump.setEnabled(false);
            EternalFragmentActivity.this.btn_dumpall.setEnabled(false);
            EternalFragmentActivity.this.tv_title.setText("BRAND");
            EternalFragmentActivity.this.lm_json_brand.clear();
            if (FileUtil.isExistFile(EternalFragmentActivity.this.s_add_prop)) {
                EternalFragmentActivity eternalFragmentActivity = EternalFragmentActivity.this;
                eternalFragmentActivity.s_custom_prop = FileUtil.readFile(eternalFragmentActivity.s_add_prop);
                EternalFragmentActivity eternalFragmentActivity2 = EternalFragmentActivity.this;
                if (eternalFragmentActivity2.jsonIsValid(eternalFragmentActivity2.s_custom_prop)) {
                    EternalFragmentActivity.this.lm_json_brand = (ArrayList) new Gson().fromJson(EternalFragmentActivity.this.s_custom_prop, new TypeToken<ArrayList<HashMap<String, Object>>>() {                     }.getType());
                }
            }
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            try {
                InputStream inputStreamOpen = EternalFragmentActivity.this.getContext().getAssets().open("dump.json");
                EternalFragmentActivity.this.lm_json_asset = (ArrayList) new Gson().fromJson(SketchwareUtil.copyFromInputStream(inputStreamOpen), new TypeToken<ArrayList<HashMap<String, Object>>>() {                 }.getType());
                EternalFragmentActivity.this.lm_json_brand.addAll(EternalFragmentActivity.this.lm_json_asset);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r4) {
            this.isRunning = false;
            RecyclerView recyclerView = EternalFragmentActivity.this.rv_1;
            EternalFragmentActivity eternalFragmentActivity = EternalFragmentActivity.this;
            recyclerView.setAdapter(eternalFragmentActivity.new Rv_1Adapter(eternalFragmentActivity.lm_json_brand));
            EternalFragmentActivity.this.rv_1.setLayoutManager(new LinearLayoutManager(EternalFragmentActivity.this.getContext()));
            EternalFragmentActivity.this.btn_back.setVisibility(8);
            EternalFragmentActivity.this.btn_prop.setEnabled(true);
            EternalFragmentActivity.this.btn_dump.setEnabled(true);
            EternalFragmentActivity.this.btn_dumpall.setEnabled(true);
            EternalFragmentActivity.this.pbar_prop.setVisibility(8);
        }

        public void cancelPROPDUMPTask() {
            cancel(true);
        }
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
            this.btn_back.setVisibility(8);
            this.tv_response.setVisibility(8);
            this.pbar_prop.setVisibility(8);
            this.lv_branch_all.setVisibility(0);
            return;
        }
        if (str.equals("clicknotfound")) {
            _onResponseError("Prop tidak ditemukan");
        }
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
            return;
        }
        this.til_input_fp.setError("Fingerprint tidak sesuai format");
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
        if (!this.lm_dump_prop.get(0).containsKey("IDIKLAN")) {
            this.lm_dump_prop.get(0).put("IDIKLAN", RandomIKLANID());
        }
        if (!this.lm_dump_prop.get(0).containsKey("RADIOVERSION")) {
            this.lm_dump_prop.get(0).put("RADIOVERSION", this.lm_dump_prop.get(0).get("INCREMENTAL").toString().concat(",".concat(this.lm_dump_prop.get(0).get("INCREMENTAL").toString())));
        }
        if (!this.lm_dump_prop.get(0).containsKey("BLUETHOOTNAME")) {
            this.lm_dump_prop.get(0).put("BLUETHOOTNAME", RandomBLUETHOOTNAME());
        }
        if (!this.lm_dump_prop.get(0).containsKey("SERIAL")) {
            this.lm_dump_prop.get(0).put("SERIAL", RandomSERIAL());
        }
        if (!this.lm_dump_prop.get(0).containsKey("DEVICENAME")) {
            this.lm_dump_prop.get(0).put("DEVICENAME", RandomDEVICENAME());
        }
        if (!this.lm_dump_prop.get(0).containsKey("ANDROIDID")) {
            this.lm_dump_prop.get(0).put("ANDROIDID", RandomANDROIDID());
        }
        if (!this.lm_dump_prop.get(0).containsKey("IMEI")) {
            this.lm_dump_prop.get(0).put("IMEI", RandomIMEI());
        }
        if (!this.lm_dump_prop.get(0).containsKey("TIME")) {
            this.lm_dump_prop.get(0).put("TIME", this.lm_dump_prop.get(0).get("UTC").toString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("SERIAL2")) {
            this.lm_dump_prop.get(0).put("SERIAL2", RandomSERIAL2());
        }
        if (!this.lm_dump_prop.get(0).containsKey("USERAGENT")) {
            this.lm_dump_prop.get(0).put("USERAGENT", "Mozilla/5.0 (Linux; Android " + this.mProp.get("RELEASE") + "; " + this.mProp.get("MODEL") + " Build/" + this.mProp.get("BUILDID") + "; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/109.0.5414.118 Mobile Safari/537.36");
        }
        if (!this.lm_dump_prop.get(0).containsKey("HTTPAGENT")) {
            this.lm_dump_prop.get(0).put("HTTPAGENT", "Dalvik/2.1.0 (Linux; U; Android " + this.mProp.get("RELEASE") + "; " + this.mProp.get("MODEL") + " Build/" + this.mProp.get("BUILDID") + ")");
        }
        this.lm_prop.clear();
        this.lm_prop.addAll(this.lm_dump_prop);
        this.s_rv2 = Uri.parse(this.s_dump_model).getLastPathSegment();
        this.lv_1.setAdapter((ListAdapter) new Lv_1Adapter(this.lm_prop));
        ((BaseAdapter) this.lv_1.getAdapter()).notifyDataSetChanged();
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
        this.f810n = 0.0d;
        for (int i = 0; i < this.ls_dump_prop.size(); i++) {
            if (this.ls_dump_prop.get((int) this.f810n).contains("manufacturer=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("MANUFACTURER", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("model=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("MODEL", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("release=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("RELEASE", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("hardware=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("HARDWARE", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("date=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("DATE", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("date.utc=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("UTC", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("fingerprint=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("FINGERPRINT", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("build.id=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("BUILDID", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("incremental=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("INCREMENTAL", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("bootloader=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("BOOT", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("description=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("DESCRIPTION", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("display.id=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("DISPLAY", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("flavor=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("FLAVOR", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("product=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("PRODUCT", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("host=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("HOST", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("user=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("USER", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("board=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("BOARD", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("brand=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("BRAND", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("device=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("DEVICE", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.f810n).contains("name=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.f810n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("NAME", this.s_prop_match);
            }
            this.f810n += 1.0d;
        }
        _onLoadingOnline("clickafterchild");
        _onConvertOnline();
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

    public void _actionOpenPackage(String str) {
        OpenAppDialogFragmentActivity openAppDialogFragmentActivity = new OpenAppDialogFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("package", str);
        openAppDialogFragmentActivity.setArguments(bundle);
        openAppDialogFragmentActivity.show(getActivity().getSupportFragmentManager(), "OpenAppDialogFragmentActivity12");
    }

    public class Lv_1Adapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_1Adapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            View viewInflate = view == null ? EternalFragmentActivity.this.getActivity().getLayoutInflater().inflate(C0978R.layout.eternal_random_view, (ViewGroup) null) : view;
            TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_device);
            TextView textView2 = (TextView) viewInflate.findViewById(C0978R.id.tv_model);
            TextView textView3 = (TextView) viewInflate.findViewById(C0978R.id.tv_product);
            TextView textView4 = (TextView) viewInflate.findViewById(C0978R.id.tv_name);
            TextView textView5 = (TextView) viewInflate.findViewById(C0978R.id.tv_manufacturer);
            TextView textView6 = (TextView) viewInflate.findViewById(C0978R.id.tv_brand);
            TextView textView7 = (TextView) viewInflate.findViewById(C0978R.id.tv_boot);
            TextView textView8 = (TextView) viewInflate.findViewById(C0978R.id.tv_build);
            TextView textView9 = (TextView) viewInflate.findViewById(C0978R.id.tv_release);
            TextView textView10 = (TextView) viewInflate.findViewById(C0978R.id.tv_incremental);
            TextView textView11 = (TextView) viewInflate.findViewById(C0978R.id.tv_sdk);
            TextView textView12 = (TextView) viewInflate.findViewById(C0978R.id.tv_display);
            TextView textView13 = (TextView) viewInflate.findViewById(C0978R.id.tv_fingerprint);
            TextView textView14 = (TextView) viewInflate.findViewById(C0978R.id.tv_desc);
            TextView textView15 = (TextView) viewInflate.findViewById(C0978R.id.tv_board);
            TextView textView16 = (TextView) viewInflate.findViewById(C0978R.id.tv_hardware);
            TextView textView17 = (TextView) viewInflate.findViewById(C0978R.id.tv_host);
            TextView textView18 = (TextView) viewInflate.findViewById(C0978R.id.tv_user);
            TextView textView19 = (TextView) viewInflate.findViewById(C0978R.id.tv_time);
            TextView textView20 = (TextView) viewInflate.findViewById(C0978R.id.tv_iklan);
            TextView textView21 = (TextView) viewInflate.findViewById(C0978R.id.tv_imei);
            TextView textView22 = (TextView) viewInflate.findViewById(C0978R.id.tv_androidid);
            TextView textView23 = (TextView) viewInflate.findViewById(C0978R.id.tv_bluethoot);
            TextView textView24 = (TextView) viewInflate.findViewById(C0978R.id.tv_devicename);
            TextView textView25 = (TextView) viewInflate.findViewById(C0978R.id.tv_serial);
            TextView textView26 = (TextView) viewInflate.findViewById(C0978R.id.tv_serial2);
            TextView textView27 = (TextView) viewInflate.findViewById(C0978R.id.tv_radio);
            TextView textView28 = (TextView) viewInflate.findViewById(C0978R.id.tv_http);
            TextView textView29 = (TextView) viewInflate.findViewById(C0978R.id.tv_useragent);
            View view2 = viewInflate;
            textView.setText(this._data.get(i).get("DEVICE").toString());
            textView2.setText(this._data.get(i).get("MODEL").toString());
            textView3.setText(this._data.get(i).get("PRODUCT").toString());
            textView4.setText(this._data.get(i).get("NAME").toString());
            textView5.setText(this._data.get(i).get("MANUFACTURER").toString());
            textView6.setText(this._data.get(i).get("BRAND").toString());
            textView7.setText(this._data.get(i).get("BOOT").toString());
            textView8.setText(this._data.get(i).get("BUILDID").toString());
            textView9.setText(this._data.get(i).get("RELEASE").toString());
            textView10.setText(this._data.get(i).get("INCREMENTAL").toString());
            textView11.setText(this._data.get(i).get("SDK").toString());
            textView12.setText(this._data.get(i).get("DISPLAY").toString());
            textView13.setText(this._data.get(i).get("FINGERPRINT").toString());
            textView14.setText(this._data.get(i).get("DESCRIPTION").toString());
            textView15.setText(this._data.get(i).get("BOARD").toString());
            textView16.setText(this._data.get(i).get("HARDWARE").toString());
            textView17.setText(this._data.get(i).get("HOST").toString());
            textView18.setText(this._data.get(i).get("USER").toString());
            textView20.setText(this._data.get(i).get("IDIKLAN").toString());
            textView19.setText(this._data.get(i).get("TIME").toString());
            textView21.setText(this._data.get(i).get("IMEI").toString());
            textView22.setText(this._data.get(i).get("ANDROIDID").toString());
            textView23.setText(this._data.get(i).get("BLUETHOOTNAME").toString());
            textView24.setText(this._data.get(i).get("DEVICENAME").toString());
            textView29.setText(this._data.get(i).get("USERAGENT").toString());
            textView26.setText(this._data.get(i).get("SERIAL2").toString());
            textView25.setText(this._data.get(i).get("SERIAL").toString());
            textView27.setText(this._data.get(i).get("RADIOVERSION").toString());
            textView28.setText(this._data.get(i).get("HTTPAGENT").toString());
            return view2;
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
            LayoutInflater layoutInflater = EternalFragmentActivity.this.getActivity().getLayoutInflater();
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
                    EternalFragmentActivity.this._onLoadingOnline("clickchild");
                    EternalFragmentActivity.this.s_url_prop1 = Lv_branch_allAdapter.this._data.get(i).get("device_url1").toString();
                    EternalFragmentActivity.this.s_url_prop2 = Lv_branch_allAdapter.this._data.get(i).get("device_url2").toString();
                    EternalFragmentActivity.this.get_branch_child1.startRequestNetwork("GET", EternalFragmentActivity.this.s_url_prop1, "a", EternalFragmentActivity.this._get_branch_child1_request_listener);
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
            View viewInflate = EternalFragmentActivity.this.getActivity().getLayoutInflater().inflate(C0978R.layout.ritual_listview_brand_prop, (ViewGroup) null);
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
            materialCardView.setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(EternalFragmentActivity.this.requireContext()));
            if (this._data.get(i).containsKey("MEREK")) {
                EternalFragmentActivity.this._onAdvanceBindBrand(linearLayout, textView, textView2, i, this._data);
            } else {
                EternalFragmentActivity.this._onAdvanceBindModel(linearLayout, textView, textView2, i, this._data);
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
}
