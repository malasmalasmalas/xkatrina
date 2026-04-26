package com.fufufu.katrina.backup;

import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.EnumMap;

public class CreateCodeFragmentActivity extends Fragment {
    private TextInputEditText et_input_qrcode;
    private ImageView im_qrcode;
    private LinearLayout ln_base;
    private LinearLayout ln_qrcode;
    private LinearLayout ln_qrcode_base;
    private LinearLayout ln_top;
    private MaterialCardView mc_qrcode;
    private TextInputLayout til_input_qrcode;
    private EditText tv_qrcode;

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C0978R.layout.create_code_fragment, viewGroup, false);
        initialize(bundle, viewInflate);
        initializeLogic();
        return viewInflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.ln_base = (LinearLayout) view.findViewById(C0978R.id.ln_base);
        this.ln_top = (LinearLayout) view.findViewById(C0978R.id.ln_top);
        this.ln_qrcode_base = (LinearLayout) view.findViewById(C0978R.id.ln_qrcode_base);
        this.til_input_qrcode = (TextInputLayout) view.findViewById(C0978R.id.til_input_qrcode);
        this.et_input_qrcode = (TextInputEditText) view.findViewById(C0978R.id.et_input_qrcode);
        this.tv_qrcode = (EditText) view.findViewById(C0978R.id.tv_qrcode);
        this.mc_qrcode = (MaterialCardView) view.findViewById(C0978R.id.mc_qrcode);
        this.ln_qrcode = (LinearLayout) view.findViewById(C0978R.id.ln_qrcode);
        ImageView imageView = (ImageView) view.findViewById(C0978R.id.im_qrcode);
        this.im_qrcode = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                CreateCodeFragmentActivity.this._createQrCode();
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.fufufu.katrina.backup.CreateCodeFragmentActivity$2] */
    private void initializeLogic() {
        this.tv_qrcode.setBackground(new GradientDrawable() {             public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(30, -1));
        this.im_qrcode.setImageResource(C0978R.drawable.ic_create_qr);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {             @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                CreateCodeFragmentActivity.this.requireActivity().onBackPressed();
            }
        });
    }

    public void _createQrCode() {
        if (this.et_input_qrcode.getText().toString().equals("")) {
            this.til_input_qrcode.setError("Enter content!");
            return;
        }
        try {
            Toast.makeText(requireContext(), "Mohon tunggu...", 0).show();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int i = (int) (((double) displayMetrics.widthPixels) * 0.9d);
            EnumMap enumMap = new EnumMap(EncodeHintType.class);
            enumMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrixEncode = new QRCodeWriter().encode(this.et_input_qrcode.getText().toString(), BarcodeFormat.QR_CODE, i, i, enumMap);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.RGB_565);
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    bitmapCreateBitmap.setPixel(i2, i3, bitMatrixEncode.get(i2, i3) ? ViewCompat.MEASURED_STATE_MASK : -1);
                }
            }
            this.im_qrcode.setImageBitmap(bitmapCreateBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
