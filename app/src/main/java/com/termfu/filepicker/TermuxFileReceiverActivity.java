package com.termfu.filepicker;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.util.Patterns;
import com.fufufu.katrina.backup.C0978R;
import com.termfu.app.DialogUtils;
import com.termfu.app.TermuxService;
import com.termux.terminal.EmulatorDebug;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes91.dex */
public class TermuxFileReceiverActivity extends Activity {
    static final String EDITOR_PROGRAM = "/data/data/com.fufufu.katrina.backup/files/home/bin/termfu-file-editor";
    static final String TERMUX_RECEIVEDIR = "/data/data/com.fufufu.katrina.backup/files/home/.sh";
    static final String URL_OPENER_PROGRAM = "/data/data/com.fufufu.katrina.backup/files/home/bin/termfu-url-opener";
    boolean mFinishOnDismissNameDialog = true;

    static boolean isSharedTextAnUrl(String str) {
        return Patterns.WEB_URL.matcher(str).matches() || Pattern.matches("magnet:\\?xt=urn:btih:.*?", str);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        String scheme = intent.getScheme();
        if ("android.intent.action.SEND".equals(action) && type != null) {
            String stringExtra = intent.getStringExtra("android.intent.extra.TEXT");
            Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
            if (stringExtra == null) {
                if (uri != null) {
                    handleContentUri(uri, intent.getStringExtra("android.intent.extra.TITLE"));
                    return;
                } else {
                    showErrorDialogAndQuit("Send action without content - nothing to save.");
                    return;
                }
            }
            if (isSharedTextAnUrl(stringExtra)) {
                handleUrlAndFinish(stringExtra);
                return;
            }
            String stringExtra2 = intent.getStringExtra("android.intent.extra.SUBJECT");
            if (stringExtra2 == null) {
                stringExtra2 = intent.getStringExtra("android.intent.extra.TITLE");
            }
            if (stringExtra2 != null) {
                stringExtra2 = String.valueOf(stringExtra2) + ".txt";
            }
            promptNameAndSave(new ByteArrayInputStream(stringExtra.getBytes(StandardCharsets.UTF_8)), stringExtra2);
            return;
        }
        if ("content".equals(scheme)) {
            handleContentUri(intent.getData(), intent.getStringExtra("android.intent.extra.TITLE"));
            return;
        }
        if ("file".equals(scheme)) {
            File file = new File(intent.getData().getPath());
            try {
                promptNameAndSave(new FileInputStream(file), file.getName());
                return;
            } catch (FileNotFoundException e) {
                showErrorDialogAndQuit("Cannot open file: " + e.getMessage() + ".");
                return;
            }
        }
        showErrorDialogAndQuit("Unable to receive any file or URL.");
    }

    void showErrorDialogAndQuit(String str) {
        this.mFinishOnDismissNameDialog = false;
        new AlertDialog.Builder(this).setMessage(str).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.termfu.filepicker.TermuxFileReceiverActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                TermuxFileReceiverActivity.this.m294lambda$0$comtermfufilepickerTermuxFileReceiverActivity(dialogInterface);
            }
        }).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.termfu.filepicker.TermuxFileReceiverActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                TermuxFileReceiverActivity.this.m295lambda$1$comtermfufilepickerTermuxFileReceiverActivity(dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: renamed from: lambda$0$com-termfu-filepicker-TermuxFileReceiverActivity, reason: not valid java name */
    /* synthetic */ void m294lambda$0$comtermfufilepickerTermuxFileReceiverActivity(DialogInterface dialogInterface) {
        finish();
    }

    /* JADX INFO: renamed from: lambda$1$com-termfu-filepicker-TermuxFileReceiverActivity, reason: not valid java name */
    /* synthetic */ void m295lambda$1$comtermfufilepickerTermuxFileReceiverActivity(DialogInterface dialogInterface, int i) {
        finish();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void handleContentUri(Uri uri, String str) {
        int columnIndex;
        String string = null;
        try {
            try {
                Cursor cursorQuery = getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
                if (cursorQuery != null) {
                    try {
                        string = (!cursorQuery.moveToFirst() || (columnIndex = cursorQuery.getColumnIndex("_display_name")) < 0) ? null : cursorQuery.getString(columnIndex);
                    } finally {
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                if (string != null) {
                    str = string;
                }
                promptNameAndSave(getContentResolver().openInputStream(uri), str);
            } finally {
            }
        } catch (Exception e) {
            showErrorDialogAndQuit("Unable to handle shared content:\n\n" + e.getMessage());
            Log.e(EmulatorDebug.LOG_TAG, "handleContentUri(uri=" + uri + ") failed", e);
        }
    }

    void promptNameAndSave(final InputStream inputStream, String str) {
        DialogUtils.textInput(this, C0978R.string.termfu_file_received_title, str, C0978R.string.termfu_file_received_edit_button, new DialogUtils.TextSetListener() { // from class: com.termfu.filepicker.TermuxFileReceiverActivity$$ExternalSyntheticLambda2
            @Override // com.termfu.app.DialogUtils.TextSetListener
            public final void onTextSet(String str2) {
                TermuxFileReceiverActivity.this.m296lambda$2$comtermfufilepickerTermuxFileReceiverActivity(inputStream, str2);
            }
        }, C0978R.string.termfu_file_received_open_folder_button, new DialogUtils.TextSetListener() { // from class: com.termfu.filepicker.TermuxFileReceiverActivity$$ExternalSyntheticLambda3
            @Override // com.termfu.app.DialogUtils.TextSetListener
            public final void onTextSet(String str2) {
                TermuxFileReceiverActivity.this.m297lambda$3$comtermfufilepickerTermuxFileReceiverActivity(inputStream, str2);
            }
        }, R.string.cancel, new DialogUtils.TextSetListener() { // from class: com.termfu.filepicker.TermuxFileReceiverActivity$$ExternalSyntheticLambda4
            @Override // com.termfu.app.DialogUtils.TextSetListener
            public final void onTextSet(String str2) {
                TermuxFileReceiverActivity.this.m298lambda$4$comtermfufilepickerTermuxFileReceiverActivity(str2);
            }
        }, new DialogInterface.OnDismissListener() { // from class: com.termfu.filepicker.TermuxFileReceiverActivity$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                TermuxFileReceiverActivity.this.m299lambda$5$comtermfufilepickerTermuxFileReceiverActivity(dialogInterface);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$2$com-termfu-filepicker-TermuxFileReceiverActivity, reason: not valid java name */
    /* synthetic */ void m296lambda$2$comtermfufilepickerTermuxFileReceiverActivity(InputStream inputStream, String str) {
        if (saveStreamWithName(inputStream, str) == null) {
            return;
        }
        new File(EDITOR_PROGRAM);
        finish();
    }

    /* JADX INFO: renamed from: lambda$3$com-termfu-filepicker-TermuxFileReceiverActivity, reason: not valid java name */
    /* synthetic */ void m297lambda$3$comtermfufilepickerTermuxFileReceiverActivity(InputStream inputStream, String str) {
        if (saveStreamWithName(inputStream, str) == null) {
            return;
        }
        getAllSHFilesAndWriteToNewDirectory("/data/user/0/com.fufufu.katrina.backup/files/home/.sh/", "/data/user/0/com.fufufu.katrina.backup/files/home/.shortcuts/");
        setPermissions("/data/user/0/com.fufufu.katrina.backup/files/home/.sh/");
        setPermissions("/data/user/0/com.fufufu.katrina.backup/files/home/.shortcuts/");
        finish();
    }

    /* JADX INFO: renamed from: lambda$4$com-termfu-filepicker-TermuxFileReceiverActivity, reason: not valid java name */
    /* synthetic */ void m298lambda$4$comtermfufilepickerTermuxFileReceiverActivity(String str) {
        finish();
    }

    /* JADX INFO: renamed from: lambda$5$com-termfu-filepicker-TermuxFileReceiverActivity, reason: not valid java name */
    /* synthetic */ void m299lambda$5$comtermfufilepickerTermuxFileReceiverActivity(DialogInterface dialogInterface) {
        if (this.mFinishOnDismissNameDialog) {
            finish();
        }
    }

    public File saveStreamWithName(InputStream inputStream, String str) {
        File file = new File("/data/data/com.fufufu.katrina.backup/files/home/.sh");
        if (!file.isDirectory() && !file.mkdirs()) {
            showErrorDialogAndQuit("Cannot create directory: " + file.getAbsolutePath());
            return null;
        }
        File file2 = new File(file, str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i > 0) {
                        fileOutputStream.write(bArr, 0, i);
                    } else {
                        fileOutputStream.close();
                        return file2;
                    }
                }
            } catch (IOException e) {
                try { fileOutputStream.close(); } catch (IOException ignored) {}
                throw e;
            }
        } catch (IOException e) {
            showErrorDialogAndQuit("Error saving file:\n\n" + e);
            Log.e(EmulatorDebug.LOG_TAG, "Error saving file", e);
        }
        return null;
    }

    void handleUrlAndFinish(String str) {
        File file = new File(URL_OPENER_PROGRAM);
        if (!file.isFile()) {
            showErrorDialogAndQuit("The following file does not exist:\n$HOME/bin/termfu-url-opener\n\nCreate this file as a script or a symlink - it will be called with the shared URL as only argument.");
            return;
        }
        file.setExecutable(true);
        Intent intent = new Intent("com.termfu.service_execute", new Uri.Builder().scheme("file").path(URL_OPENER_PROGRAM).build());
        intent.setClass(this, TermuxService.class);
        intent.putExtra(TermuxService.EXTRA_ARGUMENTS, new String[]{str});
        startService(intent);
        finish();
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
}
