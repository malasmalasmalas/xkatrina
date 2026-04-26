package com.fufufu.katrina.backup;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import com.topjohnwu.superuser.nio.FileSystemManager;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ApkUtils {
    public String SHA1;
    public String SHA256;

        private ApplicationInfo f794ai;
    public String className;
    public Context cntx;
    public String dataDir;
    public Drawable drawable;
    private boolean fromPackage;
    public int installedMinSdk;
    public String installedName;
    public int installedTargetSdk;
    public int installedVerCode;
    public String installedVerName;
    public String manageSpaceActivityName;
    public int minSdkVersion;
    public String name;
    private PackageInfo packageInfo;
    public String path;
    private PackageInfo pckgInfo;
    public String pkg;
    public String publicSourceDir;
    private Signature[] signatures;
    public String sourceDir;
    public int targetSdkVersion;
    public int uid;
    public int versionCode;
    public String versionName;
    public ArrayList activities = new ArrayList();
    public boolean isInstalledWithSameSignature = false;
    public ArrayList names = new ArrayList();
    public ArrayList permissions = new ArrayList();
    public ArrayList providers = new ArrayList();
    public ArrayList publicSources = new ArrayList();
    public ArrayList receivers = new ArrayList();
    public ArrayList services = new ArrayList();
    public ArrayList sources = new ArrayList();

    public ApkUtils(Context context, String str) {
        if (str == null || context == null) {
            return;
        }
        if (!new File(str).exists()) {
            throw new RuntimeException(new Exception("unable to find the apk file , are you sure the path is correct and you gave us the permissions"));
        }
        if (context == null) {
            throw new RuntimeException(new Exception("Context cannot be null , you must add correct context to the ApkUtils constructor (definition)"));
        }
        this.path = str;
        this.cntx = context;
        doSomething();
    }

    private void doSomething() {
        try {
            if (!this.fromPackage) {
                if (Build.VERSION.SDK_INT >= 28 && Build.VERSION.SDK_INT >= 33) {
                    this.pckgInfo = this.cntx.getPackageManager().getPackageArchiveInfo(this.path, PackageManager.PackageInfoFlags.of(0L));
                } else {
                    this.pckgInfo = this.cntx.getPackageManager().getPackageArchiveInfo(this.path, 0);
                }
            }
            this.f794ai = this.pckgInfo.applicationInfo;
        } catch (Exception unused) {
        }
        try {
            this.f794ai.sourceDir = this.path;
        } catch (Exception unused2) {
        }
        try {
            this.f794ai.publicSourceDir = this.path;
        } catch (Exception unused3) {
        }
        try {
            this.drawable = this.cntx.getPackageManager().getApplicationIcon(this.pckgInfo.applicationInfo);
        } catch (Exception unused4) {
        }
        try {
            this.name = "" + this.cntx.getPackageManager().getApplicationLabel(this.pckgInfo.applicationInfo);
        } catch (Exception unused5) {
        }
        try {
            this.versionName = this.pckgInfo.versionName;
        } catch (Exception unused6) {
        }
        try {
            if (Build.VERSION.SDK_INT < 28) {
                this.versionCode = this.pckgInfo.versionCode;
            } else {
                this.versionCode = (int) this.pckgInfo.getLongVersionCode();
            }
        } catch (Exception unused7) {
            this.versionCode = this.pckgInfo.versionCode;
        }
        try {
            this.pkg = this.pckgInfo.packageName;
            try {
                this.targetSdkVersion = this.f794ai.targetSdkVersion;
            } catch (Exception unused8) {
            }
            try {
                this.minSdkVersion = this.f794ai.minSdkVersion;
            } catch (Exception unused9) {
            }
            try {
                this.dataDir = this.f794ai.dataDir;
            } catch (Exception unused10) {
            }
            try {
                this.manageSpaceActivityName = this.f794ai.manageSpaceActivityName;
            } catch (Exception unused11) {
            }
            try {
                this.className = this.f794ai.className;
            } catch (Exception unused12) {
            }
            try {
                this.uid = this.f794ai.uid;
            } catch (Exception unused13) {
            }
            try {
                if (Build.VERSION.SDK_INT >= 28 && Build.VERSION.SDK_INT >= 33) {
                    this.pckgInfo = this.cntx.getPackageManager().getPackageInfo(this.pkg, PackageManager.PackageInfoFlags.of(0L));
                } else {
                    this.pckgInfo = this.cntx.getPackageManager().getPackageInfo(this.pkg, 0);
                }
                this.f794ai = this.pckgInfo.applicationInfo;
            } catch (Exception unused14) {
            }
            try {
                this.publicSourceDir = this.f794ai.publicSourceDir;
            } catch (Exception unused15) {
            }
            try {
                this.sourceDir = this.f794ai.sourceDir;
            } catch (Exception unused16) {
            }
            try {
                this.installedVerName = this.pckgInfo.versionName;
            } catch (Exception unused17) {
            }
            try {
                if (Build.VERSION.SDK_INT < 28) {
                    this.versionCode = this.pckgInfo.versionCode;
                } else {
                    this.versionCode = (int) this.pckgInfo.getLongVersionCode();
                }
            } catch (Exception unused18) {
                this.versionCode = this.pckgInfo.versionCode;
            }
            try {
                this.installedTargetSdk = this.f794ai.targetSdkVersion;
            } catch (Exception unused19) {
            }
            try {
                this.installedMinSdk = this.f794ai.minSdkVersion;
            } catch (Exception unused20) {
            }
            try {
                this.installedName = "" + this.cntx.getPackageManager().getApplicationLabel(this.f794ai);
            } catch (Exception unused21) {
            }
            try {
                this.names = new ArrayList();
                for (String str : this.f794ai.splitNames) {
                    this.names.add(str);
                }
            } catch (Exception unused22) {
            }
            try {
                this.sources = new ArrayList();
                for (String str2 : this.f794ai.splitSourceDirs) {
                    this.sources.add(str2);
                }
            } catch (Exception unused23) {
            }
            try {
                this.publicSources = new ArrayList();
                for (String str3 : this.f794ai.splitPublicSourceDirs) {
                    this.publicSources.add(str3);
                }
            } catch (Exception unused24) {
            }
            try {
                this.SHA1 = getSignture("SHA1");
                this.SHA256 = getSignture("SHA256");
            } catch (Exception unused25) {
            }
            try {
                this.isInstalledWithSameSignature = getSignture("SHA1").equals(getSignture("SHA1", this.pkg)) || getSignture("SHA256").equals(getSignture("SHA256", this.pkg));
                if (Build.VERSION.SDK_INT >= 28 && Build.VERSION.SDK_INT >= 33) {
                    this.pckgInfo = this.cntx.getPackageManager().getPackageArchiveInfo(this.path, PackageManager.PackageInfoFlags.of(4096L));
                } else {
                    this.pckgInfo = this.cntx.getPackageManager().getPackageArchiveInfo(this.path, 4096);
                }
                try {
                    this.permissions = new ArrayList();
                    if (this.pckgInfo.requestedPermissions != null) {
                        for (String str4 : this.pckgInfo.requestedPermissions) {
                            this.permissions.add(str4);
                        }
                    }
                } catch (Exception unused26) {
                }
                if (Build.VERSION.SDK_INT >= 28 && Build.VERSION.SDK_INT >= 33) {
                    this.pckgInfo = this.cntx.getPackageManager().getPackageArchiveInfo(this.path, PackageManager.PackageInfoFlags.of(1L));
                } else {
                    this.pckgInfo = this.cntx.getPackageManager().getPackageArchiveInfo(this.path, 1);
                }
                try {
                    this.activities = new ArrayList();
                    if (this.pckgInfo.activities != null) {
                        for (ActivityInfo activityInfo : this.pckgInfo.activities) {
                            this.activities.add(activityInfo.name);
                        }
                    }
                } catch (Exception unused27) {
                }
                if (Build.VERSION.SDK_INT >= 28 && Build.VERSION.SDK_INT >= 33) {
                    this.pckgInfo = this.cntx.getPackageManager().getPackageArchiveInfo(this.path, PackageManager.PackageInfoFlags.of(4L));
                } else {
                    this.pckgInfo = this.cntx.getPackageManager().getPackageArchiveInfo(this.path, 4);
                }
                try {
                    this.services = new ArrayList();
                    if (this.pckgInfo.services != null) {
                        for (ServiceInfo serviceInfo : this.pckgInfo.services) {
                            this.services.add(serviceInfo.name);
                        }
                    }
                } catch (Exception unused28) {
                }
                if (Build.VERSION.SDK_INT >= 28 && Build.VERSION.SDK_INT >= 33) {
                    this.pckgInfo = this.cntx.getPackageManager().getPackageArchiveInfo(this.path, PackageManager.PackageInfoFlags.of(2L));
                } else {
                    this.pckgInfo = this.cntx.getPackageManager().getPackageArchiveInfo(this.path, 2);
                }
                try {
                    this.receivers = new ArrayList();
                    if (this.pckgInfo.receivers != null) {
                        for (ActivityInfo activityInfo2 : this.pckgInfo.receivers) {
                            this.receivers.add(activityInfo2.name);
                        }
                    }
                } catch (Exception unused29) {
                }
                if (Build.VERSION.SDK_INT >= 28 && Build.VERSION.SDK_INT >= 33) {
                    this.pckgInfo = this.cntx.getPackageManager().getPackageArchiveInfo(this.path, PackageManager.PackageInfoFlags.of(8L));
                } else {
                    this.pckgInfo = this.cntx.getPackageManager().getPackageArchiveInfo(this.path, 8);
                }
                try {
                    this.providers = new ArrayList();
                    if (this.pckgInfo.providers != null) {
                        for (ProviderInfo providerInfo : this.pckgInfo.providers) {
                            this.providers.add(providerInfo.name);
                        }
                    }
                } catch (Exception unused30) {
                }
                try {
                    if (Build.VERSION.SDK_INT < 28) {
                        this.pckgInfo = this.cntx.getPackageManager().getPackageInfo(this.pkg, 0);
                    } else {
                        try {
                            if (Build.VERSION.SDK_INT >= 33) {
                                this.pckgInfo = this.cntx.getPackageManager().getPackageInfo(this.pkg, PackageManager.PackageInfoFlags.of(0L));
                            } else {
                                try {
                                    this.pckgInfo = this.cntx.getPackageManager().getPackageInfo(this.pkg, 0);
                                } catch (Exception unused31) {
                                    return;
                                }
                            }
                        } catch (Exception unused32) {
                            return;
                        }
                    }
                    this.f794ai = this.pckgInfo.applicationInfo;
                } catch (Exception unused33) {
                }
            } catch (Exception e) {
                throw new RuntimeException(new Exception(e));
            }
        } catch (Exception e2) {
            throw new RuntimeException(new Exception(e2));
        }
    }

    public static ArrayList getInstalledAppPackages(Context context) {
        List<PackageInfo> installedPackages;
        ArrayList arrayList = new ArrayList();
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                installedPackages = context.getPackageManager().getInstalledPackages(PackageManager.PackageInfoFlags.of(128L));
            } else {
                installedPackages = context.getPackageManager().getInstalledPackages(128);
            }
            Iterator<PackageInfo> it = installedPackages.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().packageName);
            }
            return arrayList;
        } catch (Exception unused) {
            throw new RuntimeException(new Exception("system refused to give your app permission to list apps , are you sure your app has QUERY_ALL_PACKAGES permission?"));
        }
    }

    public static ArrayList getInstalledSystemAppPackages(Context context) {
        List<PackageInfo> installedPackages;
        ArrayList arrayList = new ArrayList();
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                installedPackages = context.getPackageManager().getInstalledPackages(PackageManager.PackageInfoFlags.of(128L));
            } else {
                installedPackages = context.getPackageManager().getInstalledPackages(128);
            }
            for (PackageInfo packageInfo : installedPackages) {
                if ((packageInfo.applicationInfo.flags & 1) != 0) {
                    arrayList.add(packageInfo.packageName);
                }
            }
            return arrayList;
        } catch (Exception unused) {
            throw new RuntimeException(new Exception("system refused to give your app permission to list apps , are you sure your app has QUERY_ALL_PACKAGES permission?"));
        }
    }

    public static ArrayList getInstalledUserAppPackages(Context context) {
        List<PackageInfo> installedPackages;
        ArrayList arrayList = new ArrayList();
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                installedPackages = context.getPackageManager().getInstalledPackages(PackageManager.PackageInfoFlags.of(128L));
            } else {
                installedPackages = context.getPackageManager().getInstalledPackages(128);
            }
            for (PackageInfo packageInfo : installedPackages) {
                if ((packageInfo.applicationInfo.flags & 1) == 0) {
                    arrayList.add(packageInfo.packageName);
                }
            }
            return arrayList;
        } catch (Exception unused) {
            throw new RuntimeException(new Exception("system refused to give your app permission to list apps , are you sure your app has QUERY_ALL_PACKAGES permission?"));
        }
    }

    private boolean isAndroid11() {
        return Build.VERSION.SDK_INT == 30 || Build.VERSION.SDK_INT > 30;
    }

    private boolean isAndroid6() {
        return Build.VERSION.SDK_INT == 23 || Build.VERSION.SDK_INT > 23;
    }

    private boolean isFullAccessFiles(Context context) {
        if (isAndroid11()) {
            return Environment.isExternalStorageManager();
        }
        if (isAndroid6()) {
            return (context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == -1 || context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == -1) ? false : true;
        }
        return true;
    }

    public String bytes_To_Hex_(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr2[i3] = cArr[i2 >>> 4];
            cArr2[i3 + 1] = cArr[i2 & 15];
        }
        return new String(cArr2);
    }

    public long firstInstallTime() {
        return this.pckgInfo.firstInstallTime;
    }

    public ArrayList getActivities() {
        if (this.activities == null) {
            this.activities = new ArrayList();
        }
        return this.activities;
    }

    public String getClassName() {
        String str = this.className;
        return str == null ? "" : str;
    }

    public String getDataDir() {
        return this.dataDir;
    }

    public ApkUtils getFromPackageName(Context context, String str) {
        this.cntx = context;
        this.pkg = str;
        this.fromPackage = true;
        try {
            if (Build.VERSION.SDK_INT >= 28 && Build.VERSION.SDK_INT >= 33) {
                this.pckgInfo = context.getPackageManager().getPackageInfo(str, PackageManager.PackageInfoFlags.of(0L));
            } else {
                this.pckgInfo = context.getPackageManager().getPackageInfo(str, 0);
            }
            ApplicationInfo applicationInfo = this.pckgInfo.applicationInfo;
            this.f794ai = applicationInfo;
            return new ApkUtils(context, applicationInfo.publicSourceDir);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Drawable getIcon() {
        return this.drawable;
    }

    public String getInstallTime(String str) {
        return new SimpleDateFormat(str).format(new Date(this.pckgInfo.firstInstallTime));
    }

    public String getInstalledMinSdk() {
        return String.valueOf(this.installedMinSdk);
    }

    public String getInstalledName() {
        String str = this.installedName;
        return str == null ? "" : str;
    }

    public String getInstalledTargetSdk() {
        return String.valueOf(this.installedTargetSdk);
    }

    public String getInstalledVerCode() {
        return String.valueOf(this.installedVerCode);
    }

    public String getInstalledVerName() {
        String str = this.installedVerName;
        return str == null ? "" : str;
    }

    public String getManageSpaceActivityName() {
        String str = this.manageSpaceActivityName;
        return str == null ? "" : str;
    }

    public String getMinSdkVersion() {
        return String.valueOf(this.minSdkVersion);
    }

    public String getName() {
        return this.name;
    }

    public String getPackage() {
        return this.pkg;
    }

    public ArrayList getPermissions() {
        if (this.permissions == null) {
            this.permissions = new ArrayList();
        }
        return this.permissions;
    }

    public ArrayList getProviders() {
        if (this.providers == null) {
            this.providers = new ArrayList();
        }
        return this.providers;
    }

    public String getPublicSourceDir() {
        String str = this.publicSourceDir;
        return str == null ? "" : str;
    }

    public ArrayList getReceivers() {
        if (this.receivers == null) {
            this.receivers = new ArrayList();
        }
        return this.receivers;
    }

    public String getSHA1() {
        String str = this.SHA1;
        return str == null ? "" : str;
    }

    public String getSHA256() {
        String str = this.SHA256;
        return str == null ? "" : str;
    }

    public String getSHAOfType_(byte[] bArr, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            return bytes_To_Hex_(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(new NoSuchAlgorithmException(e));
        }
    }

    public ArrayList getServices() {
        if (this.services == null) {
            this.services = new ArrayList();
        }
        return this.services;
    }

    public String getSignture(String str) {
        try {
            if (Build.VERSION.SDK_INT < 28) {
                this.packageInfo = this.cntx.getPackageManager().getPackageArchiveInfo(this.path, 64);
            } else if (Build.VERSION.SDK_INT >= 33) {
                this.packageInfo = this.cntx.getPackageManager().getPackageArchiveInfo(this.path, PackageManager.PackageInfoFlags.of(134217728L));
            } else {
                this.packageInfo = this.cntx.getPackageManager().getPackageArchiveInfo(this.path, FileSystemManager.MODE_CREATE);
            }
            try {
                if (this.packageInfo.signatures == null || this.packageInfo.signatures.length == 0) {
                    this.signatures = this.packageInfo.signingInfo.getApkContentsSigners();
                } else {
                    this.signatures = this.packageInfo.signatures;
                }
            } catch (Exception unused) {
                this.signatures = this.packageInfo.signingInfo.getApkContentsSigners();
            }
            Signature[] signatureArr = this.signatures;
            return signatureArr.length <= 0 ? "" : getSHAOfType_(signatureArr[0].toByteArray(), str);
        } catch (Exception e) {
            throw new RuntimeException(new Exception(e));
        }
    }

    public String getSignture(String str, String str2) {
        try {
            if (Build.VERSION.SDK_INT < 28) {
                this.packageInfo = this.cntx.getPackageManager().getPackageInfo(str2, 64);
            } else if (Build.VERSION.SDK_INT >= 33) {
                this.packageInfo = this.cntx.getPackageManager().getPackageInfo(str2, PackageManager.PackageInfoFlags.of(134217728L));
            } else {
                this.packageInfo = this.cntx.getPackageManager().getPackageInfo(str2, FileSystemManager.MODE_CREATE);
            }
            try {
                if (this.packageInfo.signatures == null || this.packageInfo.signatures.length == 0) {
                    this.signatures = this.packageInfo.signingInfo.getApkContentsSigners();
                } else {
                    this.signatures = this.packageInfo.signatures;
                }
            } catch (Exception unused) {
                this.signatures = this.packageInfo.signingInfo.getApkContentsSigners();
            }
            Signature[] signatureArr = this.signatures;
            return signatureArr.length <= 0 ? "" : getSHAOfType_(signatureArr[0].toByteArray(), str);
        } catch (Exception e) {
            throw new RuntimeException(new Exception(e));
        }
    }

    public String getSourceDir() {
        String str = this.sourceDir;
        return str == null ? "" : str;
    }

    public ArrayList getSplitNamesIfFound() {
        ArrayList arrayList = this.names;
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        this.names = arrayList2;
        return arrayList2;
    }

    public ArrayList getSplitPublicSourcesIfFound() {
        ArrayList arrayList = this.publicSources;
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        this.publicSources = arrayList2;
        return arrayList2;
    }

    public ArrayList getSplitSourcesIfFound() {
        ArrayList arrayList = this.sources;
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        this.sources = arrayList2;
        return arrayList2;
    }

    public String getTargetSdkVersion() {
        return String.valueOf(this.targetSdkVersion);
    }

    public String getUID() {
        return String.valueOf(this.uid);
    }

    public String getUpdateTime(String str) {
        return new SimpleDateFormat(str).format(new Date(this.pckgInfo.lastUpdateTime));
    }

    public String getVersionCode() {
        return String.valueOf(this.versionCode);
    }

    public String getVersionName() {
        return this.versionName;
    }

    public boolean installedWithSameSign() {
        return this.isInstalledWithSameSignature;
    }

    public long lastUpdateTime() {
        return this.pckgInfo.lastUpdateTime;
    }
}