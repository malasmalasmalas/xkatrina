package com.fufufu.katrina.backup;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.topjohnwu.superuser.Shell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ShortcutExecutorActivity extends AppCompatActivity {
    private AlertDialog UNIVERSAL;
    public ActivityManager amanager;
    private LinearLayout ln_base_shortcut;
    private MyMODPESOFF myMODPESOFF;
    private MyMODPESON myMODPESON;
    private MyRESETO myRESETO;
    private MyTIMEPICKTASK myTIMEPICKTASK;
    private MyWIPEGMS myWIPEGMS;
    private MyWIPEMASS myWIPEMASS;
    private MyWIPEROOT myWIPEROOT;
    private NestedScrollView nestscroll;
    private ProgressBar pbar_command;
    private SharedPreferences prefwipemass;
    private Runnable runnableCommand;
    private TextView tv_cleaning_subtitle;
    private TextView tv_cleaning_title;
    private TextView tv_command_result;
    private Vibrator vibrator;
    private String s_url = "";
    private String s_wipe = "";
    private String s_command = "";
    private String s_commandResult = "";
    private String s_exitCode = "";
    private boolean b_command = false;
    private String s_universal_progress = "";
    private String s_commandBase = "";
    private String s_timepick = "";
    private String s_open = "";
    private HashMap<String, Object> m_prefwipemass = new HashMap<>();
    private String s_extra = "";
    private boolean b_dialog = false;
    private ArrayList<HashMap<String, Object>> lm_prefwipemass = new ArrayList<>();
    private Intent iShortcut = new Intent();
    private Intent intent_ritual = new Intent();
    private Handler Command = new Handler();

    public void _EXTRA() {
    }

    @Override
    protected void attachBaseContext(android.content.Context base) {
        super.attachBaseContext(LocaleHelper.wrap(base));
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0978R.layout.shortcut_executor);
        initialize(bundle);
        initializeLogic();
    }

    private void initialize(Bundle bundle) {
        this.ln_base_shortcut = (LinearLayout) findViewById(C0978R.id.ln_base_shortcut);
        this.tv_cleaning_title = (TextView) findViewById(C0978R.id.tv_cleaning_title);
        this.tv_cleaning_subtitle = (TextView) findViewById(C0978R.id.tv_cleaning_subtitle);
        this.pbar_command = (ProgressBar) findViewById(C0978R.id.pbar_command);
        this.nestscroll = (NestedScrollView) findViewById(C0978R.id.nestscroll);
        this.tv_command_result = (TextView) findViewById(C0978R.id.tv_command_result);
        this.vibrator = (Vibrator) getSystemService("vibrator");
        this.prefwipemass = getSharedPreferences("wipemass_preferences", 0);
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
        this.b_dialog = false;
        _setFirstUI();
        _getExtra();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        _hideUniversalProgress();
    }

    public void _getExtra() {
        this.tv_command_result.setText("");
        if (getIntent().hasExtra("shortcut_command")) {
            if (getIntent().hasExtra("shortcut_command")) {
                if (getIntent().getStringExtra("shortcut_desc").equals("openlink")) {
                    _intentOpenLink();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("wipe_manual")) {
                    _intentWipeManual();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("wipe_root")) {
                    _intentWipeRoot();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("open_app")) {
                    _intentOpenApp();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("wipegms")) {
                    _intentWipeGms();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("timepick")) {
                    _intentTimepick();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("fastreboot")) {
                    this.amanager = (ActivityManager) getSystemService("activity");
                    _intentFastReboot();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("reseto")) {
                    _intentReseto();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("killall")) {
                    this.iShortcut.setClass(getApplicationContext(), KillActivity.class);
                    startActivity(this.iShortcut);
                    finish();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("modpes")) {
                    _intentModePesawat();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("katrina")) {
                    this.iShortcut.setClass(getApplicationContext(), MainActivity.class);
                    startActivity(this.iShortcut);
                    finish();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("refufu")) {
                    try {
                        startActivity(new Intent().setClassName("com.google.android.apps.googleassistantx", "com.google.android.apps.googleassistantx.MainActivityPro"));
                        finish();
                        return;
                    } catch (ActivityNotFoundException unused) {
                        SketchwareUtil.showMessage(getApplicationContext(), getString(C0978R.string.toast_app_not_found));
                        finish();
                        return;
                    }
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("fakegps")) {
                    this.iShortcut.setClass(getApplicationContext(), FakeGpsActivity.class);
                    this.iShortcut.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(this.iShortcut);
                    finish();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("editprop")) {
                    this.iShortcut.setClass(getApplicationContext(), SystemPropActivity.class);
                    startActivity(this.iShortcut);
                    finish();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("wipemass")) {
                    _intentWipeMass();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("wipemass_dialog")) {
                    _intentWipeMassDialog();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("ritual")) {
                    _intentRitualFragment();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("backup")) {
                    _intentBackup();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("restore")) {
                    _intentRestore();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("finish")) {
                    finish();
                    return;
                }
                if (getIntent().getStringExtra("shortcut_desc").equals("launch_act")) {
                    _intentLaunchActivity();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("dynamic")) {
                    _intentDynamic();
                    return;
                } else {
                    finish();
                    return;
                }
            }
            return;
        }
        startActivity(new Intent(getApplicationContext(), (Class<?>) MainActivity.class));
        finish();
    }

    public void _intentOpenLink() {
        String stringExtra = getIntent().getStringExtra("shortcut_command");
        this.s_url = stringExtra;
        if (!TextUtils.isEmpty(stringExtra) && !this.s_url.startsWith("http://") && !this.s_url.startsWith("https://")) {
            this.s_url = "http://" + this.s_url;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.s_url));
        this.iShortcut = intent;
        intent.addFlags(268435456);
        if (getPackageManager().queryIntentActivities(this.iShortcut, 0).size() > 0) {
            startActivity(this.iShortcut);
        } else {
            Toast.makeText(this, "Tidak ada browser untuk membuka link", 0).show();
        }
        finish();
    }

    public void updateTextRitual() {
        this.nestscroll.post(new Runnable() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.11
            @Override // java.lang.Runnable
            public void run() {
                ShortcutExecutorActivity.this.nestscroll.fullScroll(130);
            }
        });
    }

    public void _finish() {
        finish();
    }

    public void _intentWipeManual() {
        this.s_wipe = getIntent().getStringExtra("shortcut_command");
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        this.iShortcut = intent;
        intent.setData(Uri.parse("package:" + this.s_wipe));
        this.iShortcut.addFlags(268435456);
        startActivity(this.iShortcut);
        finish();
    }

    public void _intentWipeRoot() {
        MyWIPEROOT myWIPEROOT = this.myWIPEROOT;
        if (myWIPEROOT != null && myWIPEROOT.isRunning) {
            this.myWIPEROOT.cancelWIPEROOTTask();
        }
        MyWIPEROOT myWIPEROOT2 = new MyWIPEROOT();
        this.myWIPEROOT = myWIPEROOT2;
        myWIPEROOT2.execute(new Void[0]);
    }

    public class MyWIPEROOT extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyWIPEROOT() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_wipe = shortcutExecutorActivity.getIntent().getStringExtra("shortcut_command");
            ShortcutExecutorActivity shortcutExecutorActivity2 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity2.s_universal_progress = "Wipe data aplikasi \n".concat(shortcutExecutorActivity2.s_wipe);
            ShortcutExecutorActivity.this._showUniversalProgress();
            ShortcutExecutorActivity.this.s_command = "app_package=\"fupackagename\"\nandroid_data=\"/storage/emulated/0/Android/data\"\nam force-stop $app_package > /dev/null 2>&1\n\nif pm clear $app_package > /dev/null 2>&1; then\nrm -rf $android_data/$app_package > /dev/null 2>&1;\necho -n \"Sukses menghapus data dan cache aplikasi\"\nelse\necho -n \"Gagal membersihkan data dan cache aplikasi\"\nfi";
            ShortcutExecutorActivity shortcutExecutorActivity3 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity3.s_command = shortcutExecutorActivity3.s_command.replace("fupackagename", ShortcutExecutorActivity.this.s_wipe);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_command;
            ShortcutExecutorActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(ShortcutExecutorActivity.this.s_command).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            ShortcutExecutorActivity.this.b_command = resultExec.isSuccess();
            ShortcutExecutorActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            ShortcutExecutorActivity.this._onFinishDialog();
        }

        public void cancelWIPEROOTTask() {
            cancel(true);
        }
    }

    public void _showUniversalProgress() {
        showUNIVERSAL();
    }

    private void showUNIVERSAL() {
        View viewInflate = getLayoutInflater().inflate(C0978R.layout.backup_dialog_uni_no_button, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_uni_dialog);
        textView.setTextSize(10.0f);
        textView.setText(this.s_universal_progress);
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

    public void _intentFastReboot() {
        this.vibrator.vibrate(100L);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Restarting apps...");
        progressDialog.show();
        final List<String> apparray = getApparray(this);
        final StringBuilder sb = new StringBuilder();
        final List<ApplicationInfo> applist = getApplist(this);
        final List<ActivityManager.RunningServiceInfo> runningServices = this.amanager.getRunningServices(2000);
        new Thread(new Runnable() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.12
            @Override // java.lang.Runnable
            public void run() {
                for (ApplicationInfo applicationInfo : applist) {
                    if (!apparray.contains(applicationInfo.packageName)) {
                        ShortcutExecutorActivity.this.amanager.killBackgroundProcesses(applicationInfo.processName);
                        StringBuilder sb2 = sb;
                        sb2.append("• ");
                        sb2.append(applicationInfo.loadLabel(ShortcutExecutorActivity.this.getPackageManager()).toString());
                        sb2.append("\n");
                    }
                }
                for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                    if (!apparray.contains(runningServiceInfo.process)) {
                        try {
                            PackageInfo packageInfo = ShortcutExecutorActivity.this.getPackageManager().getPackageInfo(runningServiceInfo.process, 0);
                            ShortcutExecutorActivity.this.amanager.killBackgroundProcesses(runningServiceInfo.process);
                            StringBuilder sb3 = sb;
                            sb3.append("• ");
                            sb3.append(packageInfo.applicationInfo.loadLabel(ShortcutExecutorActivity.this.getPackageManager()).toString());
                            sb3.append("\n");
                        } catch (PackageManager.NameNotFoundException unused) {
                        }
                    }
                }
                ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
                final ProgressDialog progressDialog2 = progressDialog;
                final StringBuilder sb4 = sb;
                shortcutExecutorActivity.runOnUiThread(new Runnable() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.12.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (progressDialog2.isShowing()) {
                            progressDialog2.cancel();
                        }
                        ShortcutExecutorActivity.this.resultDialog(true, sb4.toString());
                    }
                });
            }
        }).start();
    }

    public static List<ApplicationInfo> getApplist(Context context) {
        return context.getPackageManager().getInstalledApplications(128);
    }

    public static List<String> getApparray(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(context.getPackageName());
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        if (!listQueryIntentActivities.isEmpty()) {
            Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().activityInfo.packageName);
            }
        }
        return arrayList;
    }

    public void resultDialog(boolean z, String str) {
        new MaterialAlertDialogBuilder(this).setMessage((CharSequence) str).setPositiveButton((CharSequence) "Oke", new DialogInterface.OnClickListener() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ShortcutExecutorActivity.this.finish();
            }
        }).setNegativeButton((CharSequence) "Ulangi", new DialogInterface.OnClickListener() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.14
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ShortcutExecutorActivity.this._intentFastReboot();
                dialogInterface.dismiss();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.15
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                ShortcutExecutorActivity.this.finish();
            }
        }).setTitle((CharSequence) (z ? "Restart App:" : null)).show();
    }

    public void _intentModePesawat() {
        displaySignalStatus(this);
    }

    private boolean isAirplaneModeOn(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0;
    }

    private void displaySignalStatus(Context context) {
        if (isAirplaneModeOn(context)) {
            _onModpesOn();
        } else {
            _onModpesOff();
        }
    }

    public void _intentTimepick() {
        MyTIMEPICKTASK myTIMEPICKTASK = this.myTIMEPICKTASK;
        if (myTIMEPICKTASK != null && myTIMEPICKTASK.isRunning) {
            this.myTIMEPICKTASK.cancelTIMEPICKTASKTask();
        }
        MyTIMEPICKTASK myTIMEPICKTASK2 = new MyTIMEPICKTASK();
        this.myTIMEPICKTASK = myTIMEPICKTASK2;
        myTIMEPICKTASK2.execute(new Void[0]);
    }

    public class MyTIMEPICKTASK extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyTIMEPICKTASK() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            ShortcutExecutorActivity.this.ln_base_shortcut.setVisibility(0);
            ShortcutExecutorActivity.this.nestscroll.setVisibility(8);
            ShortcutExecutorActivity.this.tv_cleaning_title.setTextColor(-565927);
            ShortcutExecutorActivity.this.tv_cleaning_subtitle.setTextColor(-565927);
            ShortcutExecutorActivity.this.tv_cleaning_title.setText(getString(C0978R.string.cleaning_title_running));
            ShortcutExecutorActivity.this.tv_cleaning_subtitle.setText(getString(C0978R.string.cleaning_subtitle_mapping));
            ShortcutExecutorActivity.this.s_commandResult = "";
            ShortcutExecutorActivity.this.b_command = false;
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_commandBase.concat("\nritualcln");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_command;
            ShortcutExecutorActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(ShortcutExecutorActivity.this.s_command).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            ShortcutExecutorActivity.this.b_command = resultExec.isSuccess();
            ShortcutExecutorActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r3) {
            this.isRunning = false;
            if (ShortcutExecutorActivity.this.b_command) {
                ShortcutExecutorActivity.this.nestscroll.setVisibility(0);
                ShortcutExecutorActivity.this.pbar_command.setVisibility(8);
                ShortcutExecutorActivity.this.tv_cleaning_title.setTextColor(-11417769);
                ShortcutExecutorActivity.this.tv_cleaning_subtitle.setTextColor(-11417769);
                ShortcutExecutorActivity.this.tv_cleaning_title.setText(getString(C0978R.string.cleaning_title_done));
                ShortcutExecutorActivity.this.tv_cleaning_subtitle.setText(getString(C0978R.string.cleaning_subtitle_done));
                ShortcutExecutorActivity.this.tv_command_result.setText(ShortcutExecutorActivity.this.s_commandResult);
                ShortcutExecutorActivity.this.updateTextRitual();
                ShortcutExecutorActivity.this.vibrator.vibrate(100L);
            }
        }

        public void cancelTIMEPICKTASKTask() {
            cancel(true);
        }
    }

    public void _intentWipeGms() {
        MyWIPEGMS myWIPEGMS = this.myWIPEGMS;
        if (myWIPEGMS != null && myWIPEGMS.isRunning) {
            this.myWIPEGMS.cancelWIPEGMSTask();
        }
        MyWIPEGMS myWIPEGMS2 = new MyWIPEGMS();
        this.myWIPEGMS = myWIPEGMS2;
        myWIPEGMS2.execute(new Void[0]);
    }

    public class MyWIPEGMS extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyWIPEGMS() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            ShortcutExecutorActivity.this.s_wipe = "All GMS";
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_universal_progress = "Wipe data aplikasi \n".concat(shortcutExecutorActivity.s_wipe);
            ShortcutExecutorActivity.this._showUniversalProgress();
            ShortcutExecutorActivity shortcutExecutorActivity2 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity2.s_command = shortcutExecutorActivity2.s_commandBase;
            ShortcutExecutorActivity shortcutExecutorActivity3 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity3.s_command = shortcutExecutorActivity3.s_command.concat("\nowipegms");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_command;
            ShortcutExecutorActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(ShortcutExecutorActivity.this.s_command).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            ShortcutExecutorActivity.this.b_command = resultExec.isSuccess();
            ShortcutExecutorActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            ShortcutExecutorActivity.this._onFinishDialog();
        }

        public void cancelWIPEGMSTask() {
            cancel(true);
        }
    }

    public void _intentReseto() {
        MyRESETO myRESETO = this.myRESETO;
        if (myRESETO != null && myRESETO.isRunning) {
            this.myRESETO.cancelRESETOTask();
        }
        MyRESETO myRESETO2 = new MyRESETO();
        this.myRESETO = myRESETO2;
        myRESETO2.execute(new Void[0]);
    }

    public class MyRESETO extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyRESETO() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            ShortcutExecutorActivity.this.s_wipe = "dan reboot perangkat";
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_universal_progress = "Reset 0 ".concat(shortcutExecutorActivity.s_wipe);
            ShortcutExecutorActivity.this._showUniversalProgress();
            ShortcutExecutorActivity shortcutExecutorActivity2 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity2.s_command = shortcutExecutorActivity2.s_commandBase;
            ShortcutExecutorActivity shortcutExecutorActivity3 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity3.s_command = shortcutExecutorActivity3.s_command.replace("fupackagename", ShortcutExecutorActivity.this.s_wipe);
            ShortcutExecutorActivity shortcutExecutorActivity4 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity4.s_command = shortcutExecutorActivity4.s_command.concat("\nooooonol\nooreboot");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_command;
            ShortcutExecutorActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(ShortcutExecutorActivity.this.s_command).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            ShortcutExecutorActivity.this.b_command = resultExec.isSuccess();
            ShortcutExecutorActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            ShortcutExecutorActivity.this._onFinish();
        }

        public void cancelRESETOTask() {
            cancel(true);
        }
    }

    public void _setFirstUI() {
        this.ln_base_shortcut.setVisibility(8);
        try {
            String strCopyFromInputStream = SketchwareUtil.copyFromInputStream(getAssets().open("prop.sh"));
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
            this.s_commandBase = strReplace61.replace("futhispackage", getApplicationContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.lm_prefwipemass.clear();
        Map<String, ?> all = this.prefwipemass.getAll();
        for (String str : all.keySet()) {
            HashMap<String, Object> map = new HashMap<>();
            this.m_prefwipemass = map;
            map.put("key_app", str);
            this.m_prefwipemass.put("value_app", all.get(str).toString());
            this.lm_prefwipemass.add(this.m_prefwipemass);
        }
    }

    public void _intentOpenApp() {
        String stringExtra = getIntent().getStringExtra("shortcut_command");
        this.s_open = stringExtra;
        if (stringExtra != null) {
            try {
                Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(this.s_open);
                if (launchIntentForPackage != null) {
                    startActivity(launchIntentForPackage);
                } else {
                    Toast.makeText(this, "Aplikasi tidak ditemukan", 0).show();
                }
            } catch (Exception unused) {
                Toast.makeText(this, "Terjadi kesalahan saat mencoba membuka aplikasi", 0).show();
            }
        } else {
            Toast.makeText(this, "Package name tidak ditemukan", 0).show();
        }
        finish();
    }

    public void _intentWipeMass() {
        MyWIPEMASS myWIPEMASS = this.myWIPEMASS;
        if (myWIPEMASS != null && myWIPEMASS.isRunning) {
            this.myWIPEMASS.cancelWIPEMASSTask();
        }
        MyWIPEMASS myWIPEMASS2 = new MyWIPEMASS();
        this.myWIPEMASS = myWIPEMASS2;
        myWIPEMASS2.execute(new Void[0]);
    }

    public class MyWIPEMASS extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyWIPEMASS() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.prefwipemass.getString(ShortcutExecutorActivity.this.getIntent().getStringExtra("shortcut_command"), "");
            ShortcutExecutorActivity shortcutExecutorActivity2 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity2.s_universal_progress = shortcutExecutorActivity2.s_command.replace(" am force-stop ", "");
            ShortcutExecutorActivity shortcutExecutorActivity3 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity3.s_universal_progress = shortcutExecutorActivity3.s_universal_progress.replace(" pm clear ", "");
            ShortcutExecutorActivity shortcutExecutorActivity4 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity4.s_universal_progress = shortcutExecutorActivity4.s_universal_progress.replace(" ", "");
            ShortcutExecutorActivity shortcutExecutorActivity5 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity5.s_universal_progress = shortcutExecutorActivity5.s_universal_progress.replace(";", "\n");
            ShortcutExecutorActivity.this._showUniversalProgress();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_command;
            ShortcutExecutorActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(ShortcutExecutorActivity.this.s_command).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            ShortcutExecutorActivity.this.b_command = resultExec.isSuccess();
            ShortcutExecutorActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            ShortcutExecutorActivity.this._onFinishDialog();
        }

        public void cancelWIPEMASSTask() {
            cancel(true);
        }
    }

    public void _intentWipeMassDialog() {
        this.s_extra = new Gson().toJson(this.lm_prefwipemass);
        WipemassDialogFragmentActivity wipemassDialogFragmentActivity = new WipemassDialogFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("extrakey", this.s_extra);
        wipemassDialogFragmentActivity.setArguments(bundle);
        wipemassDialogFragmentActivity.show(getSupportFragmentManager(), "WipemassDialogFragmentActivity1");
    }

    public void _intentRitualFragment() {
        this.intent_ritual.setClass(getApplicationContext(), AddShortcutActivity.class);
        this.intent_ritual.putExtra("ritual_editor", "ritual_editor");
        startActivity(this.intent_ritual);
        finish();
    }

    public void _onFinish() {
        while (this.b_command) {
            this.b_command = false;
            this.vibrator.vibrate(100L);
            finish();
        }
    }

    public void _onFinishDialog() {
        Runnable runnable = new Runnable() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.16
            @Override // java.lang.Runnable
            public void run() {
                if (ShortcutExecutorActivity.this.b_command) {
                    ShortcutExecutorActivity.this.Command.removeCallbacks(ShortcutExecutorActivity.this.runnableCommand);
                    ShortcutExecutorActivity.this.b_command = false;
                    ShortcutExecutorActivity.this.vibrator.vibrate(100L);
                    ShortcutExecutorActivity.this._hideUniversalProgress();
                    ShortcutExecutorActivity.this.finish();
                    return;
                }
                ShortcutExecutorActivity.this.Command.postDelayed(ShortcutExecutorActivity.this.runnableCommand, 100L);
            }
        };
        this.runnableCommand = runnable;
        this.Command.postDelayed(runnable, 0L);
    }

    public void _onModpesOn() {
        MyMODPESON myMODPESON = this.myMODPESON;
        if (myMODPESON != null && myMODPESON.isRunning) {
            this.myMODPESON.cancelMODPESONTask();
        }
        MyMODPESON myMODPESON2 = new MyMODPESON();
        this.myMODPESON = myMODPESON2;
        myMODPESON2.execute(new Void[0]);
    }

    public class MyMODPESON extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyMODPESON() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            ShortcutExecutorActivity.this.s_command = "settings put global airplane_mode_on 0\nam broadcast -a android.intent.action.AIRPLANE_MODE";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_command;
            ShortcutExecutorActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(ShortcutExecutorActivity.this.s_command).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            ShortcutExecutorActivity.this.b_command = resultExec.isSuccess();
            ShortcutExecutorActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            ShortcutExecutorActivity.this._onFinish();
        }

        public void cancelMODPESONTask() {
            cancel(true);
        }
    }

    public void _onModpesOff() {
        MyMODPESOFF myMODPESOFF = this.myMODPESOFF;
        if (myMODPESOFF != null && myMODPESOFF.isRunning) {
            this.myMODPESOFF.cancelMODPESOFFTask();
        }
        MyMODPESOFF myMODPESOFF2 = new MyMODPESOFF();
        this.myMODPESOFF = myMODPESOFF2;
        myMODPESOFF2.execute(new Void[0]);
    }

    public class MyMODPESOFF extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyMODPESOFF() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            ShortcutExecutorActivity.this.s_command = "settings put global airplane_mode_on 1\nam broadcast -a android.intent.action.AIRPLANE_MODE";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_command;
            ShortcutExecutorActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(ShortcutExecutorActivity.this.s_command).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            ShortcutExecutorActivity.this.b_command = resultExec.isSuccess();
            ShortcutExecutorActivity.this.s_commandResult = String.join("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            ShortcutExecutorActivity.this._onFinish();
        }

        public void cancelMODPESOFFTask() {
            cancel(true);
        }
    }

    public void _intentBackup() {
        ScbackupDialogFragmentActivity scbackupDialogFragmentActivity = new ScbackupDialogFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("extrakey", "");
        scbackupDialogFragmentActivity.setArguments(bundle);
        scbackupDialogFragmentActivity.show(getSupportFragmentManager(), "ScbackupDialogFragmentActivity1");
    }

    public void _intentRestore() {
        ScrestoreDialogFragmentActivity screstoreDialogFragmentActivity = new ScrestoreDialogFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("extrakey", "");
        screstoreDialogFragmentActivity.setArguments(bundle);
        screstoreDialogFragmentActivity.show(getSupportFragmentManager(), "ScrestoreDialogFragmentActivity1");
    }

    public void _intentLaunchActivity() {
        String strConcat = "su -c am start ".concat(getIntent().getStringExtra("shortcut_command"));
        this.s_command = strConcat;
        this.b_command = false;
        Shell.Result resultExec = Shell.cmd(strConcat).exec();
        List<String> out = resultExec.getOut();
        resultExec.getCode();
        this.b_command = resultExec.isSuccess();
        this.s_commandResult = String.join("\n", out);
        finish();
    }

    public void _intentDynamic() {
        if (getIntent().getStringExtra("shortcut_command").equals("reboot")) {
            this.s_command = "am start -a android.intent.action.REBOOT";
        } else if (getIntent().getStringExtra("shortcut_command").equals("ssaid")) {
            this.s_command = "rm -rf /data/system/users/0/settings_ssaid.xml\nrm -rf /data/system/users/0/settings_ssaid.xml.fallback";
        } else if (getIntent().getStringExtra("shortcut_command").equals("recovery")) {
            this.s_command = "reboot recovery";
        } else {
            finish();
        }
        String str = this.s_command;
        this.s_command = str;
        this.b_command = false;
        Shell.Result resultExec = Shell.cmd(str).exec();
        List<String> out = resultExec.getOut();
        resultExec.getCode();
        this.b_command = resultExec.isSuccess();
        this.s_commandResult = String.join("\n", out);
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
