package com.fufufu.katrina.backup;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class SketchwareUtil {
    public static int BOTTOM = 3;
    public static int CENTER = 2;
    public static int TOP = 1;

    public static void CustomToast(Context context, String str, int i, int i2, int i3, int i4, int i5) {
        Toast toastMakeText = Toast.makeText(context, str, 0);
        View view = toastMakeText.getView();
        TextView textView = (TextView) view.findViewById(android.R.id.message);
        textView.setTextSize(i2);
        textView.setTextColor(i);
        textView.setGravity(17);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i3);
        gradientDrawable.setCornerRadius(i4);
        view.setBackgroundDrawable(gradientDrawable);
        view.setPadding(15, 10, 15, 10);
        view.setElevation(10.0f);
        if (i5 == 1) {
            toastMakeText.setGravity(48, 0, 150);
        } else if (i5 == 2) {
            toastMakeText.setGravity(17, 0, 0);
        } else if (i5 == 3) {
            toastMakeText.setGravity(80, 0, 150);
        }
        toastMakeText.show();
    }

    public static void CustomToastWithIcon(Context context, String str, int i, int i2, int i3, int i4, int i5, int i6) {
        Toast toastMakeText = Toast.makeText(context, str, 0);
        View view = toastMakeText.getView();
        TextView textView = (TextView) view.findViewById(android.R.id.message);
        textView.setTextSize(i2);
        textView.setTextColor(i);
        textView.setCompoundDrawablesWithIntrinsicBounds(i6, 0, 0, 0);
        textView.setGravity(17);
        textView.setCompoundDrawablePadding(10);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i3);
        gradientDrawable.setCornerRadius(i4);
        view.setBackgroundDrawable(gradientDrawable);
        view.setPadding(10, 10, 10, 10);
        view.setElevation(10.0f);
        if (i5 == 1) {
            toastMakeText.setGravity(48, 0, 150);
        } else if (i5 == 2) {
            toastMakeText.setGravity(17, 0, 0);
        } else if (i5 == 3) {
            toastMakeText.setGravity(80, 0, 150);
        }
        toastMakeText.show();
    }

    public static void sortListMap(ArrayList<HashMap<String, Object>> arrayList, final String str, final boolean z, final boolean z2) {
        Collections.sort(arrayList, new Comparator<HashMap<String, Object>>() {             @Override // java.util.Comparator
            public int compare(HashMap<String, Object> map, HashMap<String, Object> map2) {
                if (z) {
                    int iIntValue = Integer.valueOf(map.get(str).toString()).intValue();
                    int iIntValue2 = Integer.valueOf(map2.get(str).toString()).intValue();
                    if (z2) {
                        if (iIntValue < iIntValue2) {
                            return -1;
                        }
                        return iIntValue < iIntValue2 ? 1 : 0;
                    }
                    if (iIntValue > iIntValue2) {
                        return -1;
                    }
                    return iIntValue > iIntValue2 ? 1 : 0;
                }
                if (z2) {
                    return map.get(str).toString().compareTo(map2.get(str).toString());
                }
                return map2.get(str).toString().compareTo(map.get(str).toString());
            }
        });
    }

    public static void CropImage(Activity activity, String str, int i) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(Uri.fromFile(new File(str)), "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 280);
            intent.putExtra("outputY", 280);
            intent.putExtra("return-data", false);
            activity.startActivityForResult(intent, i);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(activity, "Your device doesn't support the crop action!", 0).show();
        }
    }

    public static boolean isConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String copyFromInputStream(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            } catch (IOException unused) {
            }
            return byteArrayOutputStream.toString();
        }
        try { byteArrayOutputStream.close(); } catch (IOException ignored) {}
        try { inputStream.close(); } catch (IOException ignored2) {}
        return byteArrayOutputStream.toString();
    }

    public static void hideKeyboard(Context context) {
        ((InputMethodManager) context.getSystemService("input_method")).toggleSoftInput(1, 0);
    }

    public static void showKeyboard(Context context) {
        ((InputMethodManager) context.getSystemService("input_method")).toggleSoftInput(2, 0);
    }

    public static void showMessage(Context context, String str) {
        Toast.makeText(context, str, 0).show();
    }

    public static int getLocationX(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr[0];
    }

    public static int getLocationY(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr[1];
    }

    public static int getRandom(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    public static ArrayList<Double> getCheckedItemPositionsToArray(ListView listView) {
        ArrayList<Double> arrayList = new ArrayList<>();
        SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
        for (int i = 0; i < checkedItemPositions.size(); i++) {
            if (checkedItemPositions.valueAt(i)) {
                arrayList.add(Double.valueOf(checkedItemPositions.keyAt(i)));
            }
        }
        return arrayList;
    }

    public static float getDip(Context context, int i) {
        return TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    public static int getDisplayWidthPixels(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getDisplayHeightPixels(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static void getAllKeysFromMap(Map<String, Object> map, ArrayList<String> arrayList) {
        if (arrayList == null) {
            return;
        }
        arrayList.clear();
        if (map == null || map.size() < 1) {
            return;
        }
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getKey());
        }
    }
}