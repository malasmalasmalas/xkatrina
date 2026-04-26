package com.termfu.widget;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.fufufu.katrina.backup.C0978R;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: loaded from: classes91.dex */
public class TermuxCreateShortcutActivity extends Activity {
    private File mCurrentDirectory;
    private File[] mCurrentFiles;
    private ListView mListView;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0978R.layout.termfu_shortcuts_listview);
        this.mListView = (ListView) findViewById(C0978R.id.list);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        updateListview(TermuxWidgetService.SHORTCUTS_DIR);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.termfu.widget.TermuxCreateShortcutActivity$$ExternalSyntheticLambda0
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                TermuxCreateShortcutActivity.this.m300lambda$0$comtermfuwidgetTermuxCreateShortcutActivity(adapterView, view, i, j);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$0$com-termfu-widget-TermuxCreateShortcutActivity, reason: not valid java name */
    /* synthetic */ void m300lambda$0$comtermfuwidgetTermuxCreateShortcutActivity(AdapterView adapterView, View view, int i, long j) {
        File file = this.mCurrentFiles[i];
        if (file.isDirectory()) {
            updateListview(file);
            return;
        }
        Parcelable parcelableFromContext = Intent.ShortcutIconResource.fromContext(this, C0978R.drawable.termfu_ic_term_shortcut);
        Uri uriBuild = new Uri.Builder().scheme("com.termfu.file").path(file.getAbsolutePath()).build();
        Intent intent = new Intent(this, (Class<?>) TermuxLaunchShortcutActivity.class);
        intent.setData(uriBuild);
        intent.putExtra("com.termfu.shortcut.token", TermuxLaunchShortcutActivity.getGeneratedToken(this));
        Intent intent2 = new Intent();
        intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
        intent2.putExtra("android.intent.extra.shortcut.NAME", file.getName());
        File file2 = new File("/data/data/com.fufufu.katrina.backup/files/home/.shortcuts/icons/" + file.getName() + ".png");
        if (file2.exists()) {
            intent2.putExtra("android.intent.extra.shortcut.ICON", ((BitmapDrawable) Drawable.createFromPath(file2.getAbsolutePath())).getBitmap());
        } else {
            intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", parcelableFromContext);
        }
        setResult(-1, intent2);
        finish();
    }

    private void updateListview(File file) {
        this.mCurrentDirectory = file;
        File[] fileArrListFiles = file.listFiles(TermuxWidgetService.SHORTCUT_FILES_FILTER);
        this.mCurrentFiles = fileArrListFiles;
        if (fileArrListFiles == null) {
            this.mCurrentFiles = new File[0];
        }
        Arrays.sort(this.mCurrentFiles, new Comparator() { // from class: com.termfu.widget.TermuxCreateShortcutActivity$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((File) obj).getName().compareTo(((File) obj2).getName());
            }
        });
        boolean zEquals = file.equals(TermuxWidgetService.SHORTCUTS_DIR);
        getActionBar().setDisplayHomeAsUpEnabled(!zEquals);
        if (zEquals && this.mCurrentFiles.length == 0) {
            TermuxWidgetService.SHORTCUTS_DIR.mkdirs();
            new AlertDialog.Builder(this).setMessage(C0978R.string.termfu_no_shortcut_scripts).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.termfu.widget.TermuxCreateShortcutActivity$$ExternalSyntheticLambda2
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    TermuxCreateShortcutActivity.this.m301lambda$2$comtermfuwidgetTermuxCreateShortcutActivity(dialogInterface);
                }
            }).show();
            return;
        }
        int length = this.mCurrentFiles.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            StringBuilder sb = new StringBuilder(String.valueOf(this.mCurrentFiles[i].getName()));
            sb.append(this.mCurrentFiles[i].isDirectory() ? "/" : "");
            strArr[i] = sb.toString();
        }
        this.mListView.setAdapter((ListAdapter) new ArrayAdapter(this, R.layout.simple_list_item_1, R.id.text1, strArr));
    }

    /* JADX INFO: renamed from: lambda$2$com-termfu-widget-TermuxCreateShortcutActivity, reason: not valid java name */
    /* synthetic */ void m301lambda$2$comtermfuwidgetTermuxCreateShortcutActivity(DialogInterface dialogInterface) {
        finish();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            updateListview(this.mCurrentDirectory.getParentFile());
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
