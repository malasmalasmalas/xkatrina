package com.fufufu.katrina.backup;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import com.topjohnwu.superuser.Shell;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessItem<T> implements Comparable<ProcessItem<T>>, Parcelable {
    private static final String PREFS_NAME = "ProcessItem";
    private Context context;
    public Drawable icon;
    public String applicationName = "";
    public Boolean check = true;
    public Boolean isSystemApp = false;
    public String packageName = "";
    public Boolean saveChanges = false;
    public int versionCode = 0;
    public String versionName = "";

    public enum ApplicationTypes {
        AllApps,
        OnlyUserApps,
        OnlySystemApps;

                public static ApplicationTypes[] valuesCustom() {
            ApplicationTypes[] applicationTypesArrValuesCustom = values();
            int length = applicationTypesArrValuesCustom.length;
            ApplicationTypes[] applicationTypesArr = new ApplicationTypes[length];
            System.arraycopy(applicationTypesArrValuesCustom, 0, applicationTypesArr, 0, length);
            return applicationTypesArr;
        }
    }

    public enum FilterTypes {
        AllApps,
        OnlyCheckedApps,
        OnlyUncheckedApps;

                public static FilterTypes[] valuesCustom() {
            FilterTypes[] filterTypesArrValuesCustom = values();
            int length = filterTypesArrValuesCustom.length;
            FilterTypes[] filterTypesArr = new FilterTypes[length];
            System.arraycopy(filterTypesArrValuesCustom, 0, filterTypesArr, 0, length);
            return filterTypesArr;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public ProcessItem(Context context) {
        this.context = context;
    }

    @Override // java.lang.Comparable
    public int compareTo(ProcessItem<T> processItem) {
        return this.applicationName.compareToIgnoreCase(processItem.applicationName);
    }

    public static ArrayList<ProcessItem> getRunningApps(Context context, ApplicationTypes applicationTypes) {
        ArrayList<ProcessItem> arrayList = new ArrayList<>();
        ArrayList<ProcessItem> closableApps = getClosableApps(context, applicationTypes);
        ArrayList<String> runningPackages = getRunningPackages(context);
        for (int i = 0; i < closableApps.size(); i++) {
            ProcessItem processItem = closableApps.get(i);
            if (runningPackages.contains(processItem.packageName)) {
                arrayList.add(processItem);
            }
        }
        return arrayList;
    }

    public static ArrayList<ProcessItem> getClosableApps(Context context, ApplicationTypes applicationTypes) {
        ArrayList<ProcessItem> arrayList = new ArrayList<>();
        ArrayList<ProcessItem> installedApps = getInstalledApps(context, applicationTypes, FilterTypes.AllApps);
        ArrayList<String> whiteList = getWhiteList(context);
        ArrayList<String> blackList = getBlackList(context);
        for (int i = 0; i < installedApps.size(); i++) {
            ProcessItem processItem = installedApps.get(i);
            if ((processItem.isSystemApp.booleanValue() && blackList.contains(processItem.packageName)) || (!processItem.isSystemApp.booleanValue() && !whiteList.contains(processItem.packageName))) {
                arrayList.add(processItem);
            }
        }
        return arrayList;
    }

    public static ArrayList<ProcessItem> getInstalledApps(Context context, ApplicationTypes applicationTypes, FilterTypes filterTypes) {
        ArrayList<ProcessItem> arrayList = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        ArrayList<String> whiteList = getWhiteList(context);
        ArrayList<String> blackList = getBlackList(context);
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = installedPackages.get(i);
            boolean z = true;
            Boolean boolValueOf = Boolean.valueOf((packageInfo.applicationInfo.flags & 1) == 1);
            if ((!applicationTypes.equals(ApplicationTypes.OnlyUserApps) || !boolValueOf.booleanValue()) && ((!applicationTypes.equals(ApplicationTypes.OnlySystemApps) || boolValueOf.booleanValue()) && !packageInfo.packageName.equals(Statics.PACKAGE_NAME))) {
                ProcessItem processItem = new ProcessItem(context);
                processItem.applicationName = packageInfo.applicationInfo.loadLabel(packageManager).toString();
                processItem.packageName = packageInfo.packageName;
                processItem.versionName = packageInfo.versionName;
                processItem.versionCode = packageInfo.versionCode;
                processItem.icon = packageInfo.applicationInfo.loadIcon(packageManager);
                processItem.isSystemApp = boolValueOf;
                if ((!boolValueOf.booleanValue() || !blackList.contains(processItem.packageName)) && (processItem.isSystemApp.booleanValue() || whiteList.contains(processItem.packageName))) {
                    z = false;
                }
                processItem.check = Boolean.valueOf(z);
                if (filterTypes == FilterTypes.AllApps || ((filterTypes == FilterTypes.OnlyCheckedApps && processItem.check.booleanValue()) || (filterTypes == FilterTypes.OnlyUncheckedApps && !processItem.check.booleanValue()))) {
                    arrayList.add(processItem);
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static ArrayList<String> getRunningPackages(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Shell.Result result = Shell.cmd("ps -A -o NAME").exec();
            if (result.isSuccess()) {
                for (String line : result.getOut()) {
                    String trimmed = line.trim();
                    if (!trimmed.isEmpty()) {
                        arrayList.add(trimmed);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private static ArrayList<String> getWhiteList(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Cursor cursorSelect = new DBAdapter(context).select("whiteList", new String[]{"packageName"}, null, null, null, null, null, null);
            if (cursorSelect != null) {
                if (cursorSelect.moveToFirst()) {
                    do {
                        arrayList.add(cursorSelect.getString(0));
                    } while (cursorSelect.moveToNext());
                }
                cursorSelect.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private static ArrayList<String> getBlackList(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Cursor cursorSelect = new DBAdapter(context).select("blackList", new String[]{"packageName"}, null, null, null, null, null, null);
            if (cursorSelect != null) {
                if (cursorSelect.moveToFirst()) {
                    do {
                        arrayList.add(cursorSelect.getString(0));
                    } while (cursorSelect.moveToNext());
                }
                cursorSelect.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public boolean isExists(Context context) {
        Cursor cursorSelect = new DBAdapter(context).select(this.isSystemApp.booleanValue() ? "blackList" : "whiteList", new String[]{"packageName"}, "packageName=?", new String[]{this.packageName}, null, null, null, null);
        if (cursorSelect == null) {
            return false;
        }
        boolean z = cursorSelect.getCount() > 0;
        cursorSelect.close();
        return z;
    }

    private void insertToDb() {
        DBAdapter dBAdapter = new DBAdapter(this.context);
        ContentValues contentValues = new ContentValues();
        contentValues.put("packageName", this.packageName);
        dBAdapter.insert(this.isSystemApp.booleanValue() ? "blackList" : "whiteList", contentValues);
    }

    private void deleteFromDb() {
        DBAdapter dBAdapter = new DBAdapter(this.context);
        ContentValues contentValues = new ContentValues();
        contentValues.put("packageName", this.packageName);
        dBAdapter.delete(this.isSystemApp.booleanValue() ? "blackList" : "whiteList", contentValues);
    }

    public void save() {
        try {
            if (this.isSystemApp.booleanValue()) {
                if (this.check.booleanValue()) {
                    if (!isExists(this.context)) {
                        insertToDb();
                    }
                } else if (isExists(this.context)) {
                    deleteFromDb();
                }
            } else if (this.check.booleanValue()) {
                if (isExists(this.context)) {
                    deleteFromDb();
                }
            } else if (!isExists(this.context)) {
                insertToDb();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}