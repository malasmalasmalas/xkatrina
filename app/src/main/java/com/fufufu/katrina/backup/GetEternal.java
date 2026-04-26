package com.fufufu.katrina.backup;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.ArrayList;

public class GetEternal {
    public static ArrayList<String> findAppsByActivity(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        ArrayList<String> arrayList = new ArrayList<>();
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
            if (resolveInfo.activityInfo.name.equals(str)) {
                arrayList.add(resolveInfo.activityInfo.packageName);
            }
        }
        return arrayList;
    }
}