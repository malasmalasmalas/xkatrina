package com.fufufu.katrina.backup;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.card.MaterialCardView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.topjohnwu.superuser.Shell;
import java.util.EnumMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class GetpropFragmentActivity extends Fragment {
    private ImageView im_qrcode;
    private LinearLayout ln_base;
    private LinearLayout ln_qrcode;
    private LinearLayout ln_qrcode_base;
    private LinearLayout ln_top;
    private MaterialCardView mc_qrcode;
    private TextView tv_desc;
    private TextView tv_qrcode;
    private TextView tv_subtitle;
    private TextView tv_title;
    private String base64String = "";
    private String allProp = "";
    private String s_date = "";
    private boolean b_date = false;

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C0978R.layout.getprop_fragment, viewGroup, false);
        initialize(bundle, viewInflate);
        initializeLogic();
        return viewInflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.ln_base = (LinearLayout) view.findViewById(C0978R.id.ln_base);
        this.ln_top = (LinearLayout) view.findViewById(C0978R.id.ln_top);
        this.ln_qrcode_base = (LinearLayout) view.findViewById(C0978R.id.ln_qrcode_base);
        this.tv_title = (TextView) view.findViewById(C0978R.id.tv_title);
        this.tv_subtitle = (TextView) view.findViewById(C0978R.id.tv_subtitle);
        this.tv_qrcode = (TextView) view.findViewById(C0978R.id.tv_qrcode);
        this.mc_qrcode = (MaterialCardView) view.findViewById(C0978R.id.mc_qrcode);
        this.tv_desc = (TextView) view.findViewById(C0978R.id.tv_desc);
        this.ln_qrcode = (LinearLayout) view.findViewById(C0978R.id.ln_qrcode);
        ImageView imageView = (ImageView) view.findViewById(C0978R.id.im_qrcode);
        this.im_qrcode = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                GetpropFragmentActivity.this._createQrCode();
            }
        });
    }

    private void initializeLogic() {
        this.tv_title.setText("Get Prop");
        this.tv_subtitle.setText("by fufufu");
        this.im_qrcode.setImageResource(C0978R.drawable.ic_getprop);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {             @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                GetpropFragmentActivity.this.requireActivity().onBackPressed();
            }
        });
    }

    public String _Encode(String str) {
        try {
            return Base64.encodeToString(str.getBytes("UTF-8"), 0);
        } catch (Exception unused) {
            return "";
        }
    }

    public void _createQrCode() {
        GetpropFragmentActivity getpropFragmentActivity;
        this.b_date = false;
        Shell.Result resultExec = Shell.cmd("getprop ro.build.date").exec();
        List<String> out = resultExec.getOut();
        resultExec.getCode();
        this.b_date = resultExec.isSuccess();
        String strM64m = String.join("\n", out);
        this.s_date = strM64m;
        this.s_date = strM64m.replace("\n", "");
        String str = Build.BOARD;
        String str2 = Build.BOOTLOADER;
        String str3 = Build.BRAND;
        String str4 = Build.ID;
        String str5 = Build.DEVICE;
        String str6 = Build.HOST;
        String str7 = Build.VERSION.INCREMENTAL;
        String str8 = Build.MODEL;
        String str9 = Build.PRODUCT;
        String str10 = Build.VERSION.RELEASE;
        String str11 = Build.USER;
        String str12 = Build.MANUFACTURER;
        String str13 = Build.HARDWARE;
        String str14 = Build.DISPLAY;
        String str15 = Build.FINGERPRINT;
        String strValueOf = String.valueOf(Build.TIME);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("BOARD", str);
            jSONObject.put("BOOT", str2);
            jSONObject.put("BRAND", str3);
            jSONObject.put("BUILDID", str4);
            jSONObject.put("DEVICE", str5);
            jSONObject.put("HOST", str6);
            jSONObject.put("INCREMENTAL", str7);
            jSONObject.put("MODEL", str8);
            jSONObject.put("PRODUCT", str9);
            jSONObject.put("RELEASE", str10);
            jSONObject.put("USER", str11);
            jSONObject.put("MANUFACTURER", str12);
            jSONObject.put("HARDWARE", str13);
            jSONObject.put("DISPLAYID", str14);
            jSONObject.put("FINGERPRINT", str15);
            getpropFragmentActivity = this;
            try {
                jSONObject.put("DATE", getpropFragmentActivity.s_date);
                jSONObject.put("UTC", strValueOf);
                String string = jSONObject.toString();
                getpropFragmentActivity.allProp = string;
                String str_Encode = getpropFragmentActivity._Encode(string);
                getpropFragmentActivity.base64String = str_Encode;
                getpropFragmentActivity.allProp = String.valueOf(str_Encode) + "\n#GetProp by fufufu";
                try {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    int i = (int) (((double) displayMetrics.widthPixels) * 0.9d);
                    EnumMap enumMap = new EnumMap(EncodeHintType.class);
                    enumMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                    BitMatrix bitMatrixEncode = new QRCodeWriter().encode(getpropFragmentActivity.allProp, BarcodeFormat.QR_CODE, i, i, enumMap);
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.RGB_565);
                    for (int i2 = 0; i2 < i; i2++) {
                        for (int i3 = 0; i3 < i; i3++) {
                            bitmapCreateBitmap.setPixel(i2, i3, bitMatrixEncode.get(i2, i3) ? ViewCompat.MEASURED_STATE_MASK : -1);
                        }
                    }
                    getpropFragmentActivity.im_qrcode.setImageBitmap(bitmapCreateBitmap);
                    getpropFragmentActivity.tv_qrcode.setText(str8);
                } catch (Exception e) {
                    e.printStackTrace();
                    getpropFragmentActivity.im_qrcode.setImageResource(C0978R.drawable.im_error);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                getpropFragmentActivity.im_qrcode.setImageResource(C0978R.drawable.im_error);
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
            getpropFragmentActivity = this;
        }
        getpropFragmentActivity.tv_desc.setText("Scan menggunakan app XKatrina diperangkat lain, maka prop akan di terapkan.");
    }
}