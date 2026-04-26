package com.termfu.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import com.fufufu.katrina.backup.C0978R;
import com.termfu.app.TermuxService;
import com.termux.terminal.EmulatorDebug;
import java.io.File;
import java.util.UUID;

/* JADX INFO: loaded from: classes91.dex */
public class TermuxLaunchShortcutActivity extends Activity {
    static final String TOKEN_NAME = "com.termfu.shortcut.token";

    public static String getGeneratedToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("token", 0);
        String string = sharedPreferences.getString("token", null);
        if (string != null) {
            return string;
        }
        String string2 = UUID.randomUUID().toString();
        sharedPreferences.edit().putString("token", string2).apply();
        return string2;
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(TOKEN_NAME);
        if (stringExtra == null || !stringExtra.equals(getGeneratedToken(this))) {
            Log.w(EmulatorDebug.LOG_TAG, "Strange token: " + stringExtra);
            Toast.makeText(this, C0978R.string.termfu_bad_token_message, 1).show();
            finish();
            return;
        }
        File file = new File(intent.getData().getPath());
        TermuxWidgetProvider.ensureFileReadableAndExecutable(file);
        Intent intent2 = new Intent("com.termfu.service_execute", new Uri.Builder().scheme("com.termfu.file").path(file.getAbsolutePath()).build());
        intent2.setClassName("com.fufufu.katrina.backup", TermuxWidgetProvider.TERMUX_SERVICE);
        if (file.getParentFile().getName().equals("tasks")) {
            intent2.putExtra(TermuxService.EXTRA_EXECUTE_IN_BACKGROUND, true);
            Toast toastMakeText = Toast.makeText(this, "Task executed: " + file.getName(), 0);
            toastMakeText.setGravity(17, 0, 0);
            toastMakeText.show();
        }
        TermuxWidgetProvider.startTermuxService(this, intent2);
        finish();
    }
}
