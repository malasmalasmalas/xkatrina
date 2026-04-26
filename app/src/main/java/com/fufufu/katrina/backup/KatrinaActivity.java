package com.fufufu.katrina.backup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.elevation.SurfaceColors;
import com.termfu.app.TermuxActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class KatrinaActivity extends AppCompatActivity {
    private BottomNavigationView bottom_nav;
    private MaterialButton btn_oke;
    private FrameLayout frame;
    private FrameLayout framebase;

    private FuFragmentAdapter f812fu;
    private LinearLayout ln_1;
    private LinearLayout ln_2;
    private LinearLayout ln_loadingmain;
    private MyFILTER myFILTER;
    private MyRENAME myRENAME;
    private ProgressBar pbar_loadingmain;
    private SharedPreferences pref;
    private SharedPreferences.OnSharedPreferenceChangeListener prefappListener;
    private SharedPreferences prefui;
    private SharedPreferences prefuser;
    private TextView tv_subtitle_loadingmain;
    private TextView tv_title_loadingmain;
    int nilaiTertinggi = 0;
    int highestFolderNumber = 0;
    private String s_pos = "";
    private String s_version_code = "";
    private HashMap<String, Object> m_account = new HashMap<>();
    private HashMap<String, Object> m_getnetwork = new HashMap<>();
    private HashMap<String, Object> m_banned = new HashMap<>();
    private boolean b_username = false;
    private boolean b_date = false;
    private String s_preferenceloc = "";
    private String s_result = "";
    private String s_url_splash = "";
    private String s_files_path = "";
    private String s_fufufush_path = "";
    private String s_sc_path = "";
    private String s_home_path = "";
    private String s_sh_path = "";
    private ArrayList<HashMap<String, Object>> lm_app_data = new ArrayList<>();
    private ArrayList<String> ls_check = new ArrayList<>();
    private Calendar cal = Calendar.getInstance();

    public void _EXTRA() {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0978R.layout.katrina);
        initialize(bundle);
        initializeLogic();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1000) {
            initializeLogic();
        }
    }

    private void initialize(Bundle bundle) {
        this.frame = (FrameLayout) findViewById(C0978R.id.frame);
        this.ln_loadingmain = (LinearLayout) findViewById(C0978R.id.ln_loadingmain);
        this.bottom_nav = (BottomNavigationView) findViewById(C0978R.id.bottom_nav);
        this.framebase = (FrameLayout) findViewById(C0978R.id.framebase);
        this.pbar_loadingmain = (ProgressBar) findViewById(C0978R.id.pbar_loadingmain);
        this.ln_2 = (LinearLayout) findViewById(C0978R.id.ln_2);
        this.ln_1 = (LinearLayout) findViewById(C0978R.id.ln_1);
        this.btn_oke = (MaterialButton) findViewById(C0978R.id.btn_oke);
        this.tv_title_loadingmain = (TextView) findViewById(C0978R.id.tv_title_loadingmain);
        this.tv_subtitle_loadingmain = (TextView) findViewById(C0978R.id.tv_subtitle_loadingmain);
        this.f812fu = new FuFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
        this.pref = getSharedPreferences("release_preference", 0);
        this.prefuser = getSharedPreferences("user_preferences", 0);
        this.prefui = getSharedPreferences("preferences_ui", 0);
        this.bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {             @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == 0 && !KatrinaActivity.this.s_pos.equals("0")) {
                    KatrinaActivity.this.s_pos = "0";
                    KatrinaActivity.this._fragmentApp();
                }
                if (itemId == 1 && !KatrinaActivity.this.s_pos.equals("1")) {
                    KatrinaActivity.this.s_pos = "1";
                    KatrinaActivity.this._fragmentRitual();
                }
                if (itemId == 2 && !KatrinaActivity.this.s_pos.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                    KatrinaActivity.this.s_pos = ExifInterface.GPS_MEASUREMENT_2D;
                    KatrinaActivity.this._fragmentBlocker();
                }
                if (itemId == 3 && !KatrinaActivity.this.s_pos.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    KatrinaActivity.this.s_pos = ExifInterface.GPS_MEASUREMENT_3D;
                    KatrinaActivity.this._fragmentSetelan();
                }
                return true;
            }
        });
        this.btn_oke.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KatrinaActivity.this._hideLoadingMain();
                KatrinaActivity.this._fragmentApp();
            }
        });
    }

    private void initializeLogic() {
        _dinamycShortcut();
        _setFirstUI();
        if (getIntent().hasExtra("setelan")) {
            _fragmentSetelan();
        } else {
            _EXTRASH();
            _fragmentApp();
        }
    }

    public class FuFragmentAdapter extends FragmentStatePagerAdapter {
        Context context;
        int tabCount;

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int i) {
            return null;
        }

        public FuFragmentAdapter(Context context, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.context = context;
        }

        public void setTabCount(int i) {
            this.tabCount = i;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.tabCount;
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int i) {
            if (i == 0) {
                return new AppFragmentActivity();
            }
            if (i == 1) {
                return new RitualFragmentActivity();
            }
            if (i == 2) {
                return new BlockFragmentActivity();
            }
            if (i == 3) {
                return new SetelanFragmentActivity();
            }
            return null;
        }
    }

    public void _fragmentApp() {
        this.s_pos = "0";
        getSupportFragmentManager().beginTransaction().setCustomAnimations(C0978R.anim.m3_motion_fade_enter, C0978R.anim.m3_motion_fade_exit).replace(C0978R.id.framebase, new AppFragmentActivity()).commit();
    }

    public void _fragmentRitual() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(C0978R.anim.m3_motion_fade_enter, C0978R.anim.m3_motion_fade_exit).replace(C0978R.id.framebase, new RitualFragmentActivity()).commit();
    }

    public void _fragmentSetelan() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(C0978R.anim.m3_motion_fade_enter, C0978R.anim.m3_motion_fade_exit).replace(C0978R.id.framebase, new SetelanFragmentActivity()).commit();
    }

    public void _fragmentEternal() {
        this.s_pos = "ETERNAL";
        getSupportFragmentManager().beginTransaction().setCustomAnimations(C0978R.anim.m3_motion_fade_enter, C0978R.anim.m3_motion_fade_exit).replace(C0978R.id.framebase, new EternalFragmentActivity()).commit();
    }

    public void _fragmentBlocker() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(C0978R.anim.m3_motion_fade_enter, C0978R.anim.m3_motion_fade_exit).replace(C0978R.id.framebase, new BlockFragmentActivity()).commit();
    }

    public void _setFirstUI() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(0);
        }
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));
        getWindow().getDecorView().setSystemUiVisibility(8208);
        this.ln_loadingmain.setBackgroundColor(SurfaceColors.SURFACE_2.getColor(this));
        this.ln_loadingmain.setVisibility(8);
        this.bottom_nav.getMenu().add(0, 0, 0, "App").setIcon(C0978R.drawable.ic_app);
        this.bottom_nav.getMenu().add(0, 1, 0, "Ritual").setIcon(C0978R.drawable.ic_ritual);
        this.bottom_nav.getMenu().add(0, 2, 0, "Activity").setIcon(C0978R.drawable.ic_blocker);
        this.bottom_nav.getMenu().add(0, 3, 0, "Setelan").setIcon(C0978R.drawable.ic_setelan);
        if (!this.prefui.getString("agreement", "").equals("1")) {
            finish();
        }
        this.s_pos = "0";
    }

    public void _showLoadingMain(String str) {
        this.ln_loadingmain.setVisibility(0);
        this.frame.setVisibility(4);
        this.bottom_nav.setVisibility(8);
        this.btn_oke.setVisibility(8);
        this.tv_title_loadingmain.setText("Loading");
        this.tv_subtitle_loadingmain.setText(str);
    }

    public void _hideLoadingMain() {
        this.ln_loadingmain.setVisibility(8);
        this.frame.setVisibility(0);
        this.bottom_nav.setVisibility(0);
    }

    public void _startFiltering() {
        this.tv_title_loadingmain.setText("Pelacakan slot backup kosong");
        this.tv_subtitle_loadingmain.setText("...");
        this.s_preferenceloc = this.prefui.getString("backup_sdcard_location", "").concat("/".concat(this.prefui.getString("backup_app_package", "").concat("/")));
        MyFILTER myFILTER = this.myFILTER;
        if (myFILTER != null && myFILTER.isRunning) {
            this.myFILTER.cancelFILTERTask();
        }
        MyFILTER myFILTER2 = new MyFILTER();
        this.myFILTER = myFILTER2;
        myFILTER2.execute(new Void[0]);
    }

    public class MyFILTER extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyFILTER() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            KatrinaActivity.this.ln_loadingmain.setVisibility(0);
            KatrinaActivity.this.frame.setVisibility(4);
            KatrinaActivity.this.bottom_nav.setVisibility(8);
            KatrinaActivity.this.btn_oke.setVisibility(8);
            File[] fileArrListFiles = new File(KatrinaActivity.this.s_preferenceloc).listFiles();
            if (fileArrListFiles != null) {
                for (File file : fileArrListFiles) {
                    if (file.isDirectory()) {
                        try {
                            int i = Integer.parseInt(file.getName());
                            KatrinaActivity katrinaActivity = KatrinaActivity.this;
                            katrinaActivity.nilaiTertinggi = Math.max(katrinaActivity.nilaiTertinggi, i);
                        } catch (NumberFormatException unused) {
                        }
                    }
                }
            }
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            for (int i = 1; i <= KatrinaActivity.this.nilaiTertinggi; i++) {
                String str = String.format("%03d", Integer.valueOf(i));
                String str2 = String.valueOf(KatrinaActivity.this.s_preferenceloc) + str;
                File file = new File(str2);
                if (file.exists() && file.isDirectory()) {
                    System.out.println("Folder ditemukan: " + str);
                    KatrinaActivity.this.ls_check.clear();
                    FileUtil.listDir(str2, KatrinaActivity.this.ls_check);
                    boolean z = false;
                    boolean z2 = false;
                    for (String str3 : KatrinaActivity.this.ls_check) {
                        if (str3.endsWith(".tar.gz")) {
                            z = true;
                        }
                        if (str3.endsWith(".json")) {
                            z2 = true;
                        }
                    }
                    if (!z) {
                        File file2 = new File(str2);
                        if (file2.exists() && file2.isDirectory()) {
                            if (file2.renameTo(new File(str2.concat("NOTAR")))) {
                                System.out.println("Folder renamed successfully.");
                            } else {
                                System.out.println("Failed to rename the folder.");
                            }
                        }
                    }
                    if (!z2) {
                        File file3 = new File(str2);
                        if (file3.exists() && file3.isDirectory()) {
                            if (file3.renameTo(new File(str2.concat("NOJSON")))) {
                                System.out.println("Folder renamed successfully.");
                            } else {
                                System.out.println("Failed to rename the folder.");
                            }
                        }
                    }
                } else {
                    System.out.println("Folder tidak ditemukan: " + str);
                }
                KatrinaActivity.this.s_result = str;
                publishProgress(new Void[0]);
            }
            return null;
        }

                @Override // android.os.AsyncTask
        public void onProgressUpdate(Void... voidArr) {
            super.onProgressUpdate(voidArr);
            KatrinaActivity.this.tv_subtitle_loadingmain.setText(KatrinaActivity.this.s_result);
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            this.isRunning = false;
            KatrinaActivity.this.tv_title_loadingmain.setText("Mengurutkan ulang slot backup");
            KatrinaActivity.this.tv_subtitle_loadingmain.setText("...");
            KatrinaActivity.this._startRenaming();
        }

        public void cancelFILTERTask() {
            cancel(true);
        }
    }

    public void _startRenaming() {
        MyRENAME myRENAME = this.myRENAME;
        if (myRENAME != null && myRENAME.isRunning) {
            this.myRENAME.cancelRENAMETask();
        }
        MyRENAME myRENAME2 = new MyRENAME();
        this.myRENAME = myRENAME2;
        myRENAME2.execute(new Void[0]);
    }

    public class MyRENAME extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyRENAME() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            KatrinaActivity katrinaActivity = KatrinaActivity.this;
            katrinaActivity.highestFolderNumber = KatrinaActivity.findHighestFolderNumber(katrinaActivity.s_preferenceloc);
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            if (KatrinaActivity.this.highestFolderNumber != -1) {
                int i = 1;
                for (int i2 = 1; i2 <= KatrinaActivity.this.highestFolderNumber; i2++) {
                    String str = String.format("%03d", Integer.valueOf(i2));
                    KatrinaActivity.this.s_result = str;
                    File file = new File(String.valueOf(KatrinaActivity.this.s_preferenceloc) + str);
                    if (file.exists()) {
                        String str2 = String.format("%03d", Integer.valueOf(i));
                        if (!new File(String.valueOf(KatrinaActivity.this.s_preferenceloc) + str2).exists()) {
                            if (file.renameTo(new File(String.valueOf(KatrinaActivity.this.s_preferenceloc) + str2))) {
                                System.out.println("Folder " + str + " renamed to " + str2);
                            } else {
                                System.out.println("Failed to rename folder " + str);
                            }
                        }
                        i++;
                        publishProgress(new Void[0]);
                    }
                }
                return null;
            }
            System.out.println("No folders found in the directory.");
            return null;
        }

                @Override // android.os.AsyncTask
        public void onProgressUpdate(Void... voidArr) {
            super.onProgressUpdate(voidArr);
            KatrinaActivity.this.tv_subtitle_loadingmain.setText(KatrinaActivity.this.s_result);
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r3) {
            this.isRunning = false;
            KatrinaActivity.this.tv_title_loadingmain.setText("Selesai");
            KatrinaActivity.this.btn_oke.setVisibility(0);
        }

        public void cancelRENAMETask() {
            cancel(true);
        }
    }

        public static int findHighestFolderNumber(String str) {
        File[] fileArrListFiles = new File(str).listFiles();
        int iMax = -1;
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (file.isDirectory()) {
                    try {
                        iMax = Math.max(iMax, Integer.parseInt(file.getName()));
                    } catch (NumberFormatException unused) {
                    }
                }
            }
        }
        return iMax;
    }

    public void _onDownloadSplash() {
        this.s_url_splash = this.pref.getString("splash", "");
        new DownloadImageTask(this, null).execute(this.s_url_splash);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Void> {
                @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
        }

        private DownloadImageTask() {
        }

        /* synthetic */ DownloadImageTask(KatrinaActivity katrinaActivity, DownloadImageTask downloadImageTask) {
            this();
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(String... strArr) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
                httpURLConnection.setRequestMethod("GET");
                InputStream inputStream = httpURLConnection.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream("/data/user/0/" + KatrinaActivity.this.getPackageName() + "/splash.jpg");
                byte[] bArr = new byte[4096];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i != -1) {
                        fileOutputStream.write(bArr, 0, i);
                    } else {
                        inputStream.close();
                        fileOutputStream.close();
                        return null;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void _EXTRASH() {
        this.s_files_path = "/data/data/".concat(getApplicationContext().getPackageName().concat("/files"));
        this.s_fufufush_path = FileUtil.getExternalStorageDir().concat("/fufufush/");
        String strConcat = this.s_files_path.concat("/home");
        this.s_home_path = strConcat;
        this.s_sc_path = strConcat.concat("/.shortcuts/");
        this.s_sh_path = this.s_home_path.concat("/.sh/");
        if (!FileUtil.isExistFile(this.s_files_path)) {
            FileUtil.makeDir(this.s_files_path);
        }
        if (!FileUtil.isExistFile(this.s_home_path)) {
            FileUtil.makeDir(this.s_home_path);
        }
        if (!FileUtil.isExistFile(this.s_sc_path)) {
            FileUtil.makeDir(this.s_sc_path);
        }
        if (!FileUtil.isExistFile(this.s_sh_path)) {
            FileUtil.makeDir(this.s_sh_path);
        }
        if (!FileUtil.isExistFile(this.s_fufufush_path)) {
            FileUtil.makeDir(this.s_fufufush_path);
        }
        copyFilesWithExtension(this.s_fufufush_path, this.s_sh_path, ".sh");
        getAllSHFilesAndWriteToNewDirectory(this.s_sh_path, this.s_sc_path);
        setPermissions(this.s_sc_path);
        setPermissions(this.s_sh_path);
    }

    public static void copyFilesWithExtension(String str, String str2, final String str3) {
        try {
            final Path path = Paths.get(str, new String[0]);
            final Path path2 = Paths.get(str2, new String[0]);
            Files.createDirectories(path2, new FileAttribute[0]);
            Files.walk(path, new FileVisitOption[0]).filter(new Predicate() {                 @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Files.isRegularFile((Path) obj, new LinkOption[0]);
                }
            }).filter(new Predicate() {                 @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((Path) obj).toString().endsWith(str3);
                }
            }).forEach(new Consumer() {                 @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    KatrinaActivity.lambda$2(path2, path, (Path) obj);
                }
            });
            System.out.println("Files copied successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ void lambda$2(Path path, Path path2, Path path3) {
        try {
            Files.copy(path3, path.resolve(path2.relativize(path3)), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getAllSHFilesAndWriteToNewDirectory(String str, String str2) {
        File[] fileArrListFiles = new File(str).listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (file.isFile() && file.getName().endsWith(".sh")) {
                    writeToNewDirectory(file.getName(), file.getAbsolutePath(), str2);
                }
            }
            return;
        }
        System.out.println("Directory is empty or does not exist.");
    }

    public static void writeToNewDirectory(String str, String str2, String str3) {
        try {
            FileWriter fileWriter = new FileWriter(String.valueOf(str3) + str);
            fileWriter.write("su -c " + str2);
            fileWriter.close();
            System.out.println("File " + str + " has been created with command in " + str3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPermissions(String str) {
        File[] fileArrListFiles = new File(str).listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (file.isFile()) {
                    try {
                        HashSet hashSet = new HashSet();
                        hashSet.add(PosixFilePermission.OWNER_READ);
                        hashSet.add(PosixFilePermission.OWNER_WRITE);
                        hashSet.add(PosixFilePermission.OWNER_EXECUTE);
                        hashSet.add(PosixFilePermission.GROUP_READ);
                        hashSet.add(PosixFilePermission.GROUP_EXECUTE);
                        hashSet.add(PosixFilePermission.OTHERS_READ);
                        hashSet.add(PosixFilePermission.OTHERS_EXECUTE);
                        Files.setPosixFilePermissions(file.toPath(), hashSet);
                        System.out.println("Permissions set for: " + file.getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return;
        }
        System.out.println("Directory is empty or does not exist.");
    }

    public void _dinamycShortcut() {
        ShortcutManager shortcutManager = Build.VERSION.SDK_INT >= 25 ? (ShortcutManager) getSystemService(ShortcutManager.class) : null;
        if (Build.VERSION.SDK_INT < 26) {
            Toast.makeText(getApplicationContext(), "Pinned shortcuts are not supported!", 0).show();
            return;
        }
        if (shortcutManager != null) {
            Intent intent = new Intent(this, (Class<?>) ShortcutExecutorActivity.class);
            intent.setAction("android.intent.action.MAIN");
            intent.putExtra("shortcut_command", "reboot");
            intent.putExtra("shortcut_desc", "dynamic");
            ShortcutInfo shortcutInfoBuild = new ShortcutInfo.Builder(this, "1").setShortLabel("Reboot").setLongLabel("Reboot").setRank(0).setIntent(intent).setIcon(Icon.createWithResource(this, C0978R.drawable.ds_reboot)).build();
            Intent intent2 = new Intent(this, (Class<?>) ShortcutExecutorActivity.class);
            intent2.setAction("android.intent.action.MAIN");
            intent2.putExtra("shortcut_command", "ssaid");
            intent2.putExtra("shortcut_desc", "dynamic");
            ShortcutInfo shortcutInfoBuild2 = new ShortcutInfo.Builder(this, ExifInterface.GPS_MEASUREMENT_2D).setShortLabel("Reset SSAID").setLongLabel("Reset SSAID").setRank(1).setIntent(intent2).setIcon(Icon.createWithResource(this, C0978R.drawable.ds_ssaid)).build();
            Intent intent3 = new Intent(this, (Class<?>) ShortcutExecutorActivity.class);
            intent3.setAction("android.intent.action.MAIN");
            intent3.putExtra("shortcut_command", "recovery");
            intent3.putExtra("shortcut_desc", "dynamic");
            ShortcutInfo shortcutInfoBuild3 = new ShortcutInfo.Builder(this, ExifInterface.GPS_MEASUREMENT_3D).setShortLabel("Recovery").setLongLabel("Recovery").setRank(2).setIntent(intent3).setIcon(Icon.createWithResource(this, C0978R.drawable.ds_reco)).build();
            Intent intent4 = new Intent(this, (Class<?>) TermuxActivity.class);
            intent4.setAction("android.intent.action.MAIN");
            shortcutManager.setDynamicShortcuts(Arrays.asList(shortcutInfoBuild, shortcutInfoBuild2, shortcutInfoBuild3, new ShortcutInfo.Builder(this, "4").setShortLabel("XTermod").setLongLabel("XTermod").setRank(3).setIntent(intent4).setIcon(Icon.createWithResource(this, C0978R.drawable.ic_terminal)).build()));
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