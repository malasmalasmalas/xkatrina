package com.termfu.app;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.net.http.Headers;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.webkit.MimeTypeMap;
import androidx.annotation.NonNull;
import com.termux.terminal.EmulatorDebug;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes91.dex */
public class TermuxOpenReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        if (data == null) {
            Log.e(EmulatorDebug.LOG_TAG, "termfu-open: Called without intent data");
            return;
        }
        String path = data.getPath();
        String stringExtra = intent.getStringExtra(Headers.CONTENT_TYPE);
        boolean z = false;
        boolean booleanExtra = intent.getBooleanExtra("chooser", false);
        String action = intent.getAction() == null ? "android.intent.action.VIEW" : intent.getAction();
        int iHashCode = action.hashCode();
        if (iHashCode == -1173264947 ? !action.equals("android.intent.action.SEND") : !(iHashCode == -1173171990 && action.equals("android.intent.action.VIEW"))) {
            Log.e(EmulatorDebug.LOG_TAG, "Invalid action '" + action + "', using 'view'");
        }
        if (data.getScheme() != null && !data.getScheme().equals("file")) {
            z = true;
        }
        if (z) {
            Intent intent2 = new Intent(action, data);
            if (action.equals("android.intent.action.SEND")) {
                intent2.putExtra("android.intent.extra.TEXT", data.toString());
                intent2.setData(null);
            } else if (stringExtra != null) {
                intent2.setDataAndType(data, stringExtra);
            }
            intent2.addFlags(268435456);
            try {
                context.startActivity(intent2);
                return;
            } catch (ActivityNotFoundException unused) {
                Log.e(EmulatorDebug.LOG_TAG, "termfu-open: No app handles the url " + data);
                return;
            }
        }
        File file = new File(path);
        if (!file.isFile() || !file.canRead()) {
            Log.e(EmulatorDebug.LOG_TAG, "termfu-open: Not a readable file: '" + file.getAbsolutePath() + "'");
            return;
        }
        Intent intent3 = new Intent();
        intent3.setAction(action);
        intent3.addFlags(268435457);
        if (stringExtra == null) {
            String name = file.getName();
            stringExtra = MimeTypeMap.getSingleton().getMimeTypeFromExtension(name.substring(name.lastIndexOf(46) + 1).toLowerCase());
            if (stringExtra == null) {
                stringExtra = "application/octet-stream";
            }
        }
        Uri uri = Uri.parse("content://com.termfu.files" + file.getAbsolutePath());
        if ("android.intent.action.SEND".equals(action)) {
            intent3.putExtra("android.intent.extra.STREAM", uri);
            intent3.setType(stringExtra);
        } else {
            intent3.setDataAndType(uri, stringExtra);
        }
        if (booleanExtra) {
            intent3 = Intent.createChooser(intent3, null).addFlags(268435456);
        }
        try {
            context.startActivity(intent3);
        } catch (ActivityNotFoundException unused2) {
            Log.e(EmulatorDebug.LOG_TAG, "termfu-open: No app handles the url " + data);
        }
    }

    public static class ContentProvider extends android.content.ContentProvider {
        @Override // android.content.ContentProvider
        public int delete(@NonNull Uri uri, String str, String[] strArr) {
            return 0;
        }

        @Override // android.content.ContentProvider
        public String getType(@NonNull Uri uri) {
            return null;
        }

        @Override // android.content.ContentProvider
        public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
            return null;
        }

        @Override // android.content.ContentProvider
        public boolean onCreate() {
            return true;
        }

        @Override // android.content.ContentProvider
        public int update(@NonNull Uri uri, ContentValues contentValues, String str, String[] strArr) {
            return 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x005f  */
        @Override // android.content.ContentProvider
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Cursor query(@NonNull Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
            Object name = null;
            File file = new File(uri.getPath());
            if (strArr == null) {
                strArr = new String[]{"_display_name", "_size", "_id"};
            }
            Object[] objArr = new Object[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                String str3 = strArr[i];
                int iHashCode = str3.hashCode();
                if (iHashCode != -488395321) {
                    if (iHashCode == 94650) {
                        if (str3.equals("_id")) {
                            name = 1;
                        }
                    } else {
                        name = (iHashCode == 91265248 && str3.equals("_size")) ? Integer.valueOf((int) file.length()) : null;
                    }
                } else if (str3.equals("_display_name")) {
                    name = file.getName();
                }
                objArr[i] = name;
            }
            MatrixCursor matrixCursor = new MatrixCursor(strArr);
            matrixCursor.addRow(objArr);
            return matrixCursor;
        }

        @Override // android.content.ContentProvider
        public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String str) throws FileNotFoundException {
            File file = new File(uri.getPath());
            try {
                String canonicalPath = file.getCanonicalPath();
                String canonicalPath2 = Environment.getExternalStorageDirectory().getCanonicalPath();
                if (!canonicalPath.startsWith(TermuxService.FILES_PATH) && !canonicalPath.startsWith(canonicalPath2)) {
                    throw new IllegalArgumentException("Invalid path: " + canonicalPath);
                }
                return ParcelFileDescriptor.open(file, 268435456);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
