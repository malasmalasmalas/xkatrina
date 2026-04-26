package com.fufufu.katrina.backup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.fufufu.katrina.backup.RequestNetwork;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class MaterialDebugActivity extends AppCompatActivity {
    private RequestNetwork.RequestListener _sendBot_request_listener;
    private Button btn_close_crash;
    private Button btn_send_crash;
    private LinearLayout ln_lottie_crash;
    private LottieAnimationView lottie_crash;
    private SharedPreferences prefuser;
    private RequestNetwork sendBot;
    private TextView tv_message_crash;
    private TextView tv_title_crash;
    private ScrollView vscr_crash;
    private String[] exceptionTypes = {"StringIndexOutOfBoundsException", "IndexOutOfBoundsException", "ArithmeticException", "NumberFormatException", "ActivityNotFoundException"};
    private String[] exceptionMessages = {"Invalid string operation\n", "Invalid list operation\n", "Invalid arithmetical operation\n", "Invalid toNumber block operation\n", "Invalid intent operation"};

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0978R.layout.material_debug);
        initialize(bundle);
        initializeLogic();
    }

    private void initialize(Bundle bundle) {
        this.prefuser = getSharedPreferences("user_preferences", 0);
        this.lottie_crash = (LottieAnimationView) findViewById(C0978R.id.lottie_crash);
        this.ln_lottie_crash = (LinearLayout) findViewById(C0978R.id.ln_lottie_crash);
        this.tv_title_crash = (TextView) findViewById(C0978R.id.tv_title_crash);
        this.vscr_crash = (ScrollView) findViewById(C0978R.id.vscr_crash);
        this.btn_close_crash = (Button) findViewById(C0978R.id.btn_close_crash);
        this.btn_send_crash = (Button) findViewById(C0978R.id.btn_send_crash);
        this.tv_message_crash = (TextView) findViewById(C0978R.id.tv_message_crash);
        this.sendBot = new RequestNetwork(this);
        this.btn_close_crash.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MaterialDebugActivity.this.finish();
            }
        });
        this.btn_send_crash.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String strSubstring = String.valueOf("USER : ".concat(MaterialDebugActivity.this.prefuser.getString("emanresu", "").concat("\nEMAIL : ".concat(MaterialDebugActivity.this.prefuser.getString("liamresu", ""))))) + "\nVERSION : " + MaterialDebugActivity.getVersionName(MaterialDebugActivity.this) + "\n\n\n" + MaterialDebugActivity.this.tv_message_crash.getText().toString();
                if (strSubstring.length() > 3000) {
                    strSubstring = strSubstring.substring(0, PathInterpolatorCompat.MAX_NUM_POINTS);
                }
                MaterialDebugActivity.this._sendToYourBot(strSubstring);
            }
        });
        this._sendBot_request_listener = new RequestNetwork.RequestListener() {             @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> map) {
                if (str2.contains("\"ok\":true")) {
                    MaterialDebugActivity.this.btn_send_crash.setVisibility(8);
                    MaterialDebugActivity.this.tv_message_crash.setGravity(17);
                    MaterialDebugActivity.this.tv_message_crash.setTextSize(30.0f);
                    MaterialDebugActivity.this.tv_message_crash.setText("Terima Kasih !");
                    return;
                }
                MaterialDebugActivity.this.btn_send_crash.setText("Gagal, kirim ulang");
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
                MaterialDebugActivity.this.btn_send_crash.setText("Terjadi Error, kirim ulang");
            }
        };
    }

    private void initializeLogic() {
        _setFirstUI();
    }

    public void _setFirstUI() {
        String stringExtra;
        String stringExtra2;
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(0);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.setNavigationBarColor(0);
        }
        getWindow().getDecorView().setSystemUiVisibility(8208);
        this.ln_lottie_crash.setVisibility(8);
        Intent intent = getIntent();
        String str = "";
        if (intent != null) {
            stringExtra = intent.getStringExtra("error");
            stringExtra2 = intent.getStringExtra("title");
            String stringExtra3 = intent.getStringExtra("mode");
            String[] strArrSplit = stringExtra.split("\n");
            int i = 0;
            while (true) {
                try {
                    String[] strArr = this.exceptionTypes;
                    if (i >= strArr.length) {
                        break;
                    }
                    if (strArrSplit[0].contains(strArr[i])) {
                        String str2 = this.exceptionMessages[i];
                        int iIndexOf = strArrSplit[0].indexOf(this.exceptionTypes[i]) + this.exceptionTypes[i].length();
                        StringBuilder sb = new StringBuilder(String.valueOf(str2));
                        String str3 = strArrSplit[0];
                        sb.append(str3.substring(iIndexOf, str3.length()));
                        str = String.valueOf(sb.toString()) + "\n\nDetailed error message:\n" + stringExtra;
                        break;
                    }
                    i++;
                } catch (Exception e) {
                    str = String.valueOf(str) + "\n\nError while getting error: " + Log.getStackTraceString(e);
                }
            }
            if (!str.isEmpty()) {
                stringExtra = str;
            }
            str = stringExtra3;
        } else {
            stringExtra = "";
            stringExtra2 = stringExtra;
        }
        if (str.equals("crash")) {
            this.lottie_crash.setAnimation("katrina_crash.json");
        } else {
            this.lottie_crash.setAnimation("katrina_logout.json");
        }
        this.ln_lottie_crash.setVisibility(0);
        this.tv_title_crash.setText(stringExtra2);
        this.tv_message_crash.setText(stringExtra);
        this.tv_message_crash.setTextIsSelectable(true);
    }

    public void _sendToYourBot(String str) {
        this.btn_send_crash.setText("Mengirim... Wait..");
        try {
            this.sendBot.startRequestNetwork("GET", "https://api.telegram.org/bot6499801397:AAHGtjQMX44XTVapofA-WEssmFX1Kw6CreE/sendMessage?chat_id=741550746&text=" + URLEncoder.encode(str, StandardCharsets.UTF_8.toString()), "a", this._sendBot_request_listener);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            this.btn_send_crash.setText("Error encoding message.");
        }
    }

        public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "Tidak dapat mengambil versi nama";
        }
    }
}