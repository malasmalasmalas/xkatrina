package com.fufufu.katrina.backup;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.topjohnwu.superuser.Shell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ScanResultActivity extends AppCompatActivity {
    private Button btn_copy;
    private LinearLayout ln_base;
    private LinearLayout ln_copy;
    private MyREPLACEPROP myREPLACEPROP;
    private TextView tv_result;
    private ScrollView vscr_result;
    private String s_key = "";
    private boolean b_key = false;
    private HashMap<String, Object> m_systemprop = new HashMap<>();
    private String s_prop_board = "";
    private String s_prop_buildid = "";
    private String s_prop_device = "";
    private String s_prop_model = "";
    private String s_prop_manufacturer = "";
    private String s_prop_release = "";
    private String s_prop_user = "";
    private String s_prop_brand = "";
    private String s_prop_display = "";
    private String s_prop_fingerprint = "";
    private String s_prop_description = "";
    private String s_prop_product = "";
    private String s_prop_name = "";
    private String s_prop_incremental = "";
    private String s_prop_host = "";
    private String s_prop_boot = "";
    private String s_prop_hardware = "";
    private String s_prop_date = "";
    private String s_prop_dateutc = "";
    private boolean b_command = false;
    private String s_propvalue = "";
    private String s_defaultprop = "";
    private String s_printprop = "";
    private String s_systemprop = "";
    private String s_post_fs_data = "";
    private String s_command = "";
    private String s_sensitiveprop = "";
    private String s_commandResult = "";
    private ArrayList<HashMap<String, Object>> lm_json_prop = new ArrayList<>();
    private Intent intent = new Intent();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0978R.layout.scan_result);
        initialize(bundle);
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == -1 || ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == -1) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 1000);
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
        this.vscr_result = (ScrollView) findViewById(C0978R.id.vscr_result);
        this.ln_copy = (LinearLayout) findViewById(C0978R.id.ln_copy);
        this.tv_result = (TextView) findViewById(C0978R.id.tv_result);
        Button button = (Button) findViewById(C0978R.id.btn_copy);
        this.btn_copy = button;
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScanResultActivity.this._onButtonClick();
            }
        });
    }

    private void initializeLogic() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(0);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.setNavigationBarColor(0);
        }
        getWindow().getDecorView().setSystemUiVisibility(8208);
        _setFirstUI();
    }

    public String _Decode(String str) {
        try {
            return new String(Base64.decode(str, 0), "UTF-8");
        } catch (Exception unused) {
            return "";
        }
    }

    public void _setFirstUI() {
        this.s_systemprop = "/data/user/0/".concat(getApplicationContext().getPackageName().concat("/system.prop"));
        this.s_sensitiveprop = "/data/user/0/".concat(getApplicationContext().getPackageName().concat("/post-fs-data.sh"));
        this.s_defaultprop = "/data/user/0/".concat(getApplicationContext().getPackageName().concat("/default.prop"));
        try {
            if (getIntent().hasExtra("key")) {
                this.b_key = true;
                String stringExtra = getIntent().getStringExtra("key");
                this.s_key = stringExtra;
                if (stringExtra.contains("#GetProp by fufufu")) {
                    String strReplace = this.s_key.replace("#GetProp by fufufu", "");
                    this.s_key = strReplace;
                    this.s_key = _Decode(strReplace);
                    Gson gsonCreate = new GsonBuilder().setPrettyPrinting().create();
                    this.s_key = gsonCreate.toJson(gsonCreate.fromJson(this.s_key, Object.class));
                    this.m_systemprop = (HashMap) new Gson().fromJson(this.s_key, new TypeToken<HashMap<String, Object>>() {                     }.getType());
                    this.lm_json_prop.clear();
                    this.lm_json_prop.add(this.m_systemprop);
                    this.tv_result.setText(this.s_key);
                    this.btn_copy.setText("PASANG PROP");
                } else {
                    this.b_key = false;
                    this.tv_result.setText(this.s_key);
                    this.btn_copy.setText("Copy");
                }
            } else {
                finish();
            }
        } catch (Exception unused) {
        }
    }

    public void _onButtonClick() {
        if (this.btn_copy.getText().toString().equals("REBOOT")) {
            this.s_command = "am start -a android.intent.action.REBOOT";
            this.b_command = false;
            Shell.Result resultExec = Shell.cmd("am start -a android.intent.action.REBOOT").exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            this.b_command = resultExec.isSuccess();
            this.s_commandResult = String.join("\n", out);
            return;
        }
        if (this.b_key) {
            this.s_prop_device = this.lm_json_prop.get(0).get("DEVICE").toString();
            this.s_prop_model = this.lm_json_prop.get(0).get("MODEL").toString();
            this.s_prop_product = this.lm_json_prop.get(0).get("PRODUCT").toString();
            this.s_prop_name = this.lm_json_prop.get(0).get("PRODUCT").toString();
            this.s_prop_manufacturer = this.lm_json_prop.get(0).get("MANUFACTURER").toString();
            this.s_prop_brand = this.lm_json_prop.get(0).get("BRAND").toString();
            this.s_prop_boot = this.lm_json_prop.get(0).get("BOOT").toString();
            this.s_prop_buildid = this.lm_json_prop.get(0).get("BUILDID").toString();
            this.s_prop_fingerprint = this.lm_json_prop.get(0).get("FINGERPRINT").toString();
            this.s_prop_incremental = this.lm_json_prop.get(0).get("INCREMENTAL").toString();
            this.s_prop_board = this.lm_json_prop.get(0).get("BOARD").toString();
            this.s_prop_hardware = this.lm_json_prop.get(0).get("HARDWARE").toString();
            this.s_prop_host = this.lm_json_prop.get(0).get("HOST").toString();
            this.s_prop_user = this.lm_json_prop.get(0).get("USER").toString();
            this.s_prop_date = this.lm_json_prop.get(0).get("DATE").toString();
            this.s_prop_dateutc = this.lm_json_prop.get(0).get("UTC").toString();
            String string = this.lm_json_prop.get(0).get("RELEASE").toString();
            this.s_prop_release = string;
            this.s_prop_description = this.s_prop_name.concat("-user ".concat(string.concat(" ".concat(this.s_prop_buildid.concat(this.s_prop_incremental.concat(" release-keys"))))));
            this.s_prop_display = this.s_prop_buildid.concat(".".concat(this.s_prop_incremental));
            _onReplaceProp();
            return;
        }
        getApplicationContext();
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("clipboard", this.tv_result.getText().toString()));
        SketchwareUtil.showMessage(getApplicationContext(), this.tv_result.getText().toString());
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
            ScanResultActivity.this.tv_result.setText("Harap Tunggu...");
            ScanResultActivity.this.m_systemprop = new HashMap();
            ScanResultActivity.this.m_systemprop.put("BOARD", ScanResultActivity.this.s_prop_board);
            ScanResultActivity.this.m_systemprop.put("BUILDID", ScanResultActivity.this.s_prop_buildid);
            ScanResultActivity.this.m_systemprop.put("DEVICE", ScanResultActivity.this.s_prop_device);
            ScanResultActivity.this.m_systemprop.put("MODEL", ScanResultActivity.this.s_prop_model);
            ScanResultActivity.this.m_systemprop.put("MANUFACTURER", ScanResultActivity.this.s_prop_manufacturer);
            ScanResultActivity.this.m_systemprop.put("RELEASE", ScanResultActivity.this.s_prop_release);
            ScanResultActivity.this.m_systemprop.put("USER", ScanResultActivity.this.s_prop_user);
            ScanResultActivity.this.m_systemprop.put("BRAND", ScanResultActivity.this.s_prop_brand);
            ScanResultActivity.this.m_systemprop.put("DISPLAYID", ScanResultActivity.this.s_prop_display);
            ScanResultActivity.this.m_systemprop.put("FINGERPRINT", ScanResultActivity.this.s_prop_fingerprint);
            ScanResultActivity.this.m_systemprop.put("DESCRIPTION", ScanResultActivity.this.s_prop_description);
            ScanResultActivity.this.m_systemprop.put("PRODUCT", ScanResultActivity.this.s_prop_product);
            ScanResultActivity.this.m_systemprop.put("NAME", ScanResultActivity.this.s_prop_name);
            ScanResultActivity.this.m_systemprop.put("INCREMENTAL", ScanResultActivity.this.s_prop_incremental);
            ScanResultActivity.this.m_systemprop.put("HOST", ScanResultActivity.this.s_prop_host);
            ScanResultActivity.this.m_systemprop.put("BOOTLOADER", ScanResultActivity.this.s_prop_boot);
            ScanResultActivity.this.m_systemprop.put("HARDWARE", ScanResultActivity.this.s_prop_hardware);
            ScanResultActivity.this.m_systemprop.put("DATE", ScanResultActivity.this.s_prop_date);
            ScanResultActivity.this.m_systemprop.put("UTC", ScanResultActivity.this.s_prop_dateutc);
            ScanResultActivity.this.b_command = false;
            ScanResultActivity scanResultActivity = ScanResultActivity.this;
            scanResultActivity.s_propvalue = FileUtil.readFile(scanResultActivity.s_defaultprop);
            ScanResultActivity scanResultActivity2 = ScanResultActivity.this;
            scanResultActivity2.s_propvalue = scanResultActivity2.s_propvalue.replace("ro.boot.hardware=", "#");
            ScanResultActivity scanResultActivity3 = ScanResultActivity.this;
            scanResultActivity3.s_propvalue = scanResultActivity3.s_propvalue.replace("ro.build.flavor=", "#");
            ScanResultActivity scanResultActivity4 = ScanResultActivity.this;
            scanResultActivity4.s_propvalue = scanResultActivity4.s_propvalue.replace(".display.id=", ".displayid=");
            ScanResultActivity scanResultActivity5 = ScanResultActivity.this;
            scanResultActivity5.s_propvalue = scanResultActivity5.s_propvalue.replace(".board=", ".board ".concat(ScanResultActivity.this.s_prop_board));
            ScanResultActivity scanResultActivity6 = ScanResultActivity.this;
            scanResultActivity6.s_propvalue = scanResultActivity6.s_propvalue.replace(".id=", ".id ".concat(ScanResultActivity.this.s_prop_buildid));
            ScanResultActivity scanResultActivity7 = ScanResultActivity.this;
            scanResultActivity7.s_propvalue = scanResultActivity7.s_propvalue.replace(".device=", ".device ".concat(ScanResultActivity.this.s_prop_device));
            ScanResultActivity scanResultActivity8 = ScanResultActivity.this;
            scanResultActivity8.s_propvalue = scanResultActivity8.s_propvalue.replace(".model=", ".model ".concat(ScanResultActivity.this.s_prop_model));
            ScanResultActivity scanResultActivity9 = ScanResultActivity.this;
            scanResultActivity9.s_propvalue = scanResultActivity9.s_propvalue.replace(".manufacturer=", ".manufacturer ".concat(ScanResultActivity.this.s_prop_manufacturer));
            ScanResultActivity scanResultActivity10 = ScanResultActivity.this;
            scanResultActivity10.s_propvalue = scanResultActivity10.s_propvalue.replace(".release=", ".release ".concat(ScanResultActivity.this.s_prop_release));
            ScanResultActivity scanResultActivity11 = ScanResultActivity.this;
            scanResultActivity11.s_propvalue = scanResultActivity11.s_propvalue.replace(".user=", ".user ".concat(ScanResultActivity.this.s_prop_user));
            ScanResultActivity scanResultActivity12 = ScanResultActivity.this;
            scanResultActivity12.s_propvalue = scanResultActivity12.s_propvalue.replace(".brand=", ".brand ".concat(ScanResultActivity.this.s_prop_brand));
            ScanResultActivity scanResultActivity13 = ScanResultActivity.this;
            scanResultActivity13.s_propvalue = scanResultActivity13.s_propvalue.replace(".displayid=", ".displayid ".concat(ScanResultActivity.this.s_prop_display));
            ScanResultActivity scanResultActivity14 = ScanResultActivity.this;
            scanResultActivity14.s_propvalue = scanResultActivity14.s_propvalue.replace(".fingerprint=", ".fingerprint ".concat(ScanResultActivity.this.s_prop_fingerprint));
            ScanResultActivity scanResultActivity15 = ScanResultActivity.this;
            scanResultActivity15.s_propvalue = scanResultActivity15.s_propvalue.replace(".description=", ".description ".concat(ScanResultActivity.this.s_prop_description));
            ScanResultActivity scanResultActivity16 = ScanResultActivity.this;
            scanResultActivity16.s_propvalue = scanResultActivity16.s_propvalue.replace(".product=", ".product ".concat(ScanResultActivity.this.s_prop_product));
            ScanResultActivity scanResultActivity17 = ScanResultActivity.this;
            scanResultActivity17.s_propvalue = scanResultActivity17.s_propvalue.replace(".name=", ".name ".concat(ScanResultActivity.this.s_prop_name));
            ScanResultActivity scanResultActivity18 = ScanResultActivity.this;
            scanResultActivity18.s_propvalue = scanResultActivity18.s_propvalue.replace(".incremental=", ".incremental ".concat(ScanResultActivity.this.s_prop_incremental));
            ScanResultActivity scanResultActivity19 = ScanResultActivity.this;
            scanResultActivity19.s_propvalue = scanResultActivity19.s_propvalue.replace(".host=", ".host ".concat(ScanResultActivity.this.s_prop_host));
            ScanResultActivity scanResultActivity20 = ScanResultActivity.this;
            scanResultActivity20.s_propvalue = scanResultActivity20.s_propvalue.replace(".bootloader=", ".bootloader ".concat(ScanResultActivity.this.s_prop_boot));
            ScanResultActivity scanResultActivity21 = ScanResultActivity.this;
            scanResultActivity21.s_propvalue = scanResultActivity21.s_propvalue.replace(".hardware=", ".hardware ".concat(ScanResultActivity.this.s_prop_hardware));
            ScanResultActivity scanResultActivity22 = ScanResultActivity.this;
            scanResultActivity22.s_propvalue = scanResultActivity22.s_propvalue.replace(".date=", ".date ".concat(ScanResultActivity.this.s_prop_date));
            ScanResultActivity scanResultActivity23 = ScanResultActivity.this;
            scanResultActivity23.s_propvalue = scanResultActivity23.s_propvalue.replace(".date.utc=", ".date.utc ".concat(ScanResultActivity.this.s_prop_dateutc));
            ScanResultActivity scanResultActivity24 = ScanResultActivity.this;
            scanResultActivity24.s_propvalue = scanResultActivity24.s_propvalue.replace(".displayid=", ".display.id ");
            ScanResultActivity.this.s_printprop = new Gson().toJson(ScanResultActivity.this.m_systemprop);
            ScanResultActivity.this.s_printprop = new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement) new Gson().fromJson(ScanResultActivity.this.s_printprop, JsonObject.class));
            FileUtil.writeFile(ScanResultActivity.this.s_systemprop, ScanResultActivity.this.s_printprop);
            String[] strArrSplit = ScanResultActivity.this.s_propvalue.split("\n");
            StringBuilder sb = new StringBuilder();
            for (String strReplaceFirst : strArrSplit) {
                if (strReplaceFirst.startsWith("ro.")) {
                    strReplaceFirst = strReplaceFirst.replaceFirst("ro\\.", "check_resetprop ro.");
                }
                sb.append(strReplaceFirst);
                sb.append("\n");
            }
            ScanResultActivity.this.s_propvalue = sb.toString().trim();
            try {
                ScanResultActivity.this.s_post_fs_data = SketchwareUtil.copyFromInputStream(ScanResultActivity.this.getAssets().open("main2.dex"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            ScanResultActivity scanResultActivity25 = ScanResultActivity.this;
            scanResultActivity25.s_post_fs_data = scanResultActivity25.s_post_fs_data.replace("#XKatrina", "#XKatrina\n".concat(ScanResultActivity.this.s_propvalue));
            ScanResultActivity scanResultActivity26 = ScanResultActivity.this;
            scanResultActivity26.s_command = "sensitiveprop=$(cat <<EOF\n".concat(scanResultActivity26.s_post_fs_data.concat("\nEOF\n)\necho -e \"$sensitiveprop\" > ".concat(ScanResultActivity.this.s_sensitiveprop.concat("\nchmod 644 ".concat(ScanResultActivity.this.s_sensitiveprop)))));
            ScanResultActivity scanResultActivity27 = ScanResultActivity.this;
            scanResultActivity27.s_command = scanResultActivity27.s_command.concat("\ncp ".concat(ScanResultActivity.this.s_sensitiveprop.concat(" /data/adb/modules/xkatrina_snstv_prps/post-fs-data.sh\nchmod 0644 /data/adb/modules/xkatrina_snstv_prps/post-fs-data.sh")));
            ScanResultActivity scanResultActivity28 = ScanResultActivity.this;
            scanResultActivity28.s_command = scanResultActivity28.s_command.concat("\ncp ".concat(ScanResultActivity.this.s_systemprop.concat(" /data/adb/modules/xkatrina_snstv_prps/fu.dex\nchmod 0644 /data/adb/modules/xkatrina_snstv_prps/fu.dex")));
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ScanResultActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(ScanResultActivity.this.s_command).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            ScanResultActivity.this.b_command = resultExec.isSuccess();
            ScanResultActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            this.isRunning = false;
            if (ScanResultActivity.this.b_command) {
                ScanResultActivity.this.tv_result.setText("SELESAI");
                ScanResultActivity.this.btn_copy.setText("REBOOT");
            }
        }

        public void cancelREPLACEPROPTask() {
            cancel(true);
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