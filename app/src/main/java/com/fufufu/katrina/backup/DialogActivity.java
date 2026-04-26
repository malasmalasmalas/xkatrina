package com.fufufu.katrina.backup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import com.fufufu.katrina.backup.RequestNetwork;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DialogActivity extends AppCompatActivity {
    private AlertDialog BOT;
    private RequestNetwork.RequestListener _sendBot_request_listener;
    private SharedPreferences prefuser;
    private RequestNetwork sendBot;
    public final int REQ_CD_PICK = 101;
    private String s_message = "";
    private boolean b_mode = false;
    private String s_namefile = "";
    private String s_progressfile = "";
    private String s_totalfile = "";
    private String s_listfile = "";
    private String s_totalsize = "";
    private ArrayList<String> ls_pickfile = new ArrayList<>();
    private ArrayList<String> ls_text = new ArrayList<>();
    private Intent pick = new Intent("android.intent.action.GET_CONTENT");

    public void _EXTRA() {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0978R.layout.dialog);
        initialize(bundle);
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == -1) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1000);
        } else {
            initializeLogic();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1000) {
            initializeLogic();
        }
    }

    private void initialize(Bundle bundle) {
        this.prefuser = getSharedPreferences("user_preferences", 0);
        this.sendBot = new RequestNetwork(this);
        this.pick.setType("*/*");
        this.pick.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        this._sendBot_request_listener = new RequestNetwork.RequestListener() {             @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> map) {
            }
        };
    }

    private void initializeLogic() {
        this.b_mode = false;
        _createDialogBot();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == -1) {
            ArrayList arrayList = new ArrayList();
            if (intent != null) {
                if (intent.getClipData() != null) {
                    for (int i3 = 0; i3 < intent.getClipData().getItemCount(); i3++) {
                        arrayList.add(FileUtil.convertUriToFilePath(getApplicationContext(), intent.getClipData().getItemAt(i3).getUri()));
                    }
                } else {
                    arrayList.add(FileUtil.convertUriToFilePath(getApplicationContext(), intent.getData()));
                }
            }
            this.ls_pickfile.clear();
            this.ls_pickfile.addAll(arrayList);
            ArrayList arrayList2 = new ArrayList();
            ArrayList<String> arrayList3 = this.ls_pickfile;
            if (arrayList3 != null) {
                for (String strSubstring : arrayList3) {
                    if (strSubstring != null) {
                        int iLastIndexOf = strSubstring.lastIndexOf(47);
                        if (iLastIndexOf != -1) {
                            strSubstring = strSubstring.substring(iLastIndexOf + 1);
                        }
                        arrayList2.add(strSubstring);
                    }
                }
            }
            this.s_listfile = String.join("\n", arrayList2);
            updateDialogUI();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.b_mode) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            super.finishAndRemoveTask();
        } else {
            super.finish();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= 21) {
            super.finishAndRemoveTask();
        } else {
            super.finish();
        }
    }

    public void _createDialogBot() {
        showBOT();
    }

    private void showBOT() {
        View viewInflate = getLayoutInflater().inflate(C0978R.layout.dialog_send_to_bot, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
        materialAlertDialogBuilder.setView(viewInflate);
        materialAlertDialogBuilder.setCancelable(false);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_sendfile);
        final LinearLayout linearLayout2 = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_pickfile);
        final LinearLayout linearLayout3 = (LinearLayout) viewInflate.findViewById(C0978R.id.ln_result);
        final NestedScrollView nestedScrollView = (NestedScrollView) viewInflate.findViewById(C0978R.id.vscr_1);
        final Button button = (Button) viewInflate.findViewById(C0978R.id.btn_mode);
        Button button2 = (Button) viewInflate.findViewById(C0978R.id.btn_batal);
        final Button button3 = (Button) viewInflate.findViewById(C0978R.id.btn_oke);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) viewInflate.findViewById(C0978R.id.auto_message);
        TextView textView = (TextView) viewInflate.findViewById(C0978R.id.tv_picklist);
        final TextView textView2 = (TextView) viewInflate.findViewById(C0978R.id.tv_result_bot);
        final TextInputLayout textInputLayout = (TextInputLayout) viewInflate.findViewById(C0978R.id.til_message);
        final ProgressBar progressBar = (ProgressBar) viewInflate.findViewById(C0978R.id.pbar_1);
        linearLayout.setVisibility(8);
        linearLayout3.setVisibility(8);
        linearLayout2.setVisibility(8);
        nestedScrollView.setVisibility(8);
        textView.setText("");
        autoCompleteTextView.setMaxLines(10);
        autoCompleteTextView.setFocusable(true);
        autoCompleteTextView.setFocusableInTouchMode(true);
        textView.setText(this.s_listfile);
        button.setText("SEND FILE");
        linearLayout.setBackgroundColor(Color.parseColor("#20000000"));
        button.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DialogActivity.this.b_mode) {
                    DialogActivity.this.b_mode = false;
                    button.setText("SEND FILE");
                    textInputLayout.setVisibility(0);
                    linearLayout2.setVisibility(8);
                    nestedScrollView.setVisibility(8);
                    return;
                }
                DialogActivity.this.b_mode = true;
                button.setText("SEND TEXT");
                textInputLayout.setVisibility(8);
                linearLayout2.setVisibility(0);
                nestedScrollView.setVisibility(8);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 21) {
                    DialogActivity.this.finishAndRemoveTask();
                } else {
                    DialogActivity.this.finish();
                }
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DialogActivity dialogActivity = DialogActivity.this;
                dialogActivity.startActivityForResult(dialogActivity.pick, 101);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (button3.getText().toString().equals("KIRIM")) {
                    if (DialogActivity.this.b_mode) {
                        if (DialogActivity.this.ls_pickfile != null && DialogActivity.this.ls_pickfile.size() != 0) {
                            DialogActivity dialogActivity = DialogActivity.this;
                            dialogActivity._sendFileToYourBot(dialogActivity.ls_pickfile);
                            return;
                        } else {
                            SketchwareUtil.showMessage(DialogActivity.this.getApplicationContext(), "Tidak ada file untuk dikirim");
                            return;
                        }
                    }
                    if (autoCompleteTextView.getText().toString().length() >= 2) {
                        String string = autoCompleteTextView.getText().toString();
                        if (DialogActivity.this.prefuser.getString("token_bot", "").equals("")) {
                            SketchwareUtil.showMessage(DialogActivity.this.getApplicationContext(), "Bot belum di setting.");
                            return;
                        }
                        linearLayout3.setVisibility(0);
                        progressBar.setVisibility(0);
                        textView2.setText("Mengirim...");
                        try {
                            String strEncode = URLEncoder.encode(string, StandardCharsets.UTF_8.toString());
                            String str = "https://api.telegram.org/bot" + DialogActivity.this.prefuser.getString("token_bot", "") + "/sendMessage?chat_id=" + DialogActivity.this.prefuser.getString("chat_id", "") + "&text=" + strEncode;
                            DialogActivity dialogActivity2 = DialogActivity.this;
                            final ProgressBar progressBar2 = progressBar;
                            final TextView textView3 = textView2;
                            final AutoCompleteTextView autoCompleteTextView2 = autoCompleteTextView;
                            dialogActivity2._sendBot_request_listener = new RequestNetwork.RequestListener() {                                 @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
                                public void onResponse(String str2, String str3, HashMap<String, Object> map) {
                                    if (str3.contains("\"ok\":true")) {
                                        progressBar2.setVisibility(8);
                                        textView3.setText("Terkirim");
                                        autoCompleteTextView2.setText("");
                                    } else {
                                        progressBar2.setVisibility(8);
                                        textView3.setText("Gagal Terkirim");
                                    }
                                }

                                @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
                                public void onErrorResponse(String str2, String str3) {
                                    progressBar2.setVisibility(8);
                                    textView3.setText("Terjadi Error");
                                }
                            };
                            DialogActivity.this.sendBot.startRequestNetwork("GET", str, "a", DialogActivity.this._sendBot_request_listener);
                            return;
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            textView2.setText("Error encoding message.");
                            return;
                        }
                    }
                    SketchwareUtil.showMessage(DialogActivity.this.getApplicationContext(), "Tidak ada text untuk dikirim");
                    return;
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    DialogActivity.this.finishAndRemoveTask();
                } else {
                    DialogActivity.this.finish();
                }
            }
        });
        AlertDialog alertDialogCreate = materialAlertDialogBuilder.create();
        this.BOT = alertDialogCreate;
        alertDialogCreate.show();
    }

    private void updateDialogUI() {
        TextView textView = (TextView) this.BOT.findViewById(C0978R.id.tv_picklist);
        if (textView != null) {
            textView.setText(this.s_listfile);
        }
        NestedScrollView nestedScrollView = (NestedScrollView) this.BOT.findViewById(C0978R.id.vscr_1);
        if (nestedScrollView != null) {
            nestedScrollView.setVisibility(0);
        }
    }

    public void _sendFileToYourBot(ArrayList<String> arrayList) {
        new TelegramFileSender().execute(this.prefuser.getString("token_bot", ""), this.prefuser.getString("chat_id", ""), arrayList);
    }

    public class TelegramFileSender extends AsyncTask<Object, Integer, Void> {
        public TelegramFileSender() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            ProgressBar progressBar = (ProgressBar) DialogActivity.this.BOT.findViewById(C0978R.id.pbar_2);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            ((LinearLayout) DialogActivity.this.BOT.findViewById(C0978R.id.ln_sendfile)).setVisibility(0);
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Object... objArr) {
            String str = (String) objArr[0];
            String str2 = (String) objArr[1];
            ArrayList arrayList = (ArrayList) objArr[2];
            try {
                int size = arrayList.size();
                DialogActivity.this.s_totalfile = String.valueOf(size);
                int i = 0;
                while (i < size) {
                    String str3 = (String) arrayList.get(i);
                    long totalFileSize = getTotalFileSize(str3);
                    DialogActivity.this.s_namefile = str3;
                    int i2 = i + 1;
                    DialogActivity.this.s_progressfile = String.valueOf(i2);
                    DialogActivity.this.s_totalsize = String.format("%.2f", Double.valueOf(totalFileSize / 1048576.0d));
                    publishProgress(0);
                    sendFileToTelegram(str, str2, str3, totalFileSize);
                    publishProgress(Integer.valueOf((i2 * 100) / size));
                    i = i2;
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        private void sendFileToTelegram(String str, String str2, String str3, long j) throws IOException {
            String.valueOf(j);
            String str4 = String.format("https://api.telegram.org/bot%s/sendDocument?chat_id=%s", str, str2);
            File file = new File(str3);
            String hexString = Long.toHexString(System.currentTimeMillis());
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str4).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + hexString);
            Throwable th = null;
            try {
                OutputStream outputStream = httpURLConnection.getOutputStream();
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        outputStream.write(("--" + hexString + "\r\n").getBytes());
                        outputStream.write(("Content-Disposition: form-data; name=\"document\"; filename=\"" + file.getName() + "\"\r\n").getBytes());
                        outputStream.write("Content-Type: application/octet-stream\r\n\r\n".getBytes());
                        byte[] bArr = new byte[8192];
                        long j2 = 0;
                        while (true) {
                            int i = fileInputStream.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            outputStream.write(bArr, 0, i);
                            j2 += (long) i;
                            publishProgress(Integer.valueOf((int) ((100 * j2) / j)));
                        }
                        outputStream.write(("\r\n--" + hexString + "--\r\n").getBytes());
                        fileInputStream.close();
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        int responseCode = httpURLConnection.getResponseCode();
                        System.out.println("Response Code: " + responseCode);
                    } catch (Throwable th2) {
                        fileInputStream.close();
                        throw th2;
                    }
                } finally {
                }
            } finally {
            }
        }

                @Override // android.os.AsyncTask
        public void onProgressUpdate(Integer... numArr) {
            int iIntValue = numArr[0].intValue();
            TextView textView = (TextView) DialogActivity.this.BOT.findViewById(C0978R.id.tv_namefile);
            if (textView != null) {
                textView.setText(Uri.parse(DialogActivity.this.s_namefile).getLastPathSegment());
            }
            TextView textView2 = (TextView) DialogActivity.this.BOT.findViewById(C0978R.id.tv_progressfile);
            if (textView2 != null) {
                textView2.setText(String.valueOf(DialogActivity.this.s_progressfile) + "/" + DialogActivity.this.s_totalfile);
            }
            TextView textView3 = (TextView) DialogActivity.this.BOT.findViewById(C0978R.id.tv_totalfile);
            if (textView3 != null) {
                textView3.setText(String.valueOf(DialogActivity.this.s_totalsize) + "MB");
            }
            ProgressBar progressBar = (ProgressBar) DialogActivity.this.BOT.findViewById(C0978R.id.pbar_2);
            if (progressBar != null) {
                progressBar.setProgress(iIntValue);
            }
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r3) {
            TextView textView = (TextView) DialogActivity.this.BOT.findViewById(C0978R.id.tv_picklist);
            if (textView != null) {
                textView.setText(String.valueOf(DialogActivity.this.s_totalfile) + " File Terkirim");
            }
            Button button = (Button) DialogActivity.this.BOT.findViewById(C0978R.id.btn_oke);
            if (button != null) {
                button.setText("SELESAI");
            }
            ((LinearLayout) DialogActivity.this.BOT.findViewById(C0978R.id.ln_sendfile)).setVisibility(8);
            DialogActivity.this.ls_pickfile.clear();
        }

        private long getTotalFileSize(String str) {
            if (str != null) {
                return new File(str).length();
            }
            return 0L;
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