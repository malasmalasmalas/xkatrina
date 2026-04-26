package com.fufufu.katrina.backup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import java.io.FileNotFoundException;

public class ScanFragmentActivity extends Fragment {
    private static final int PICK_IMAGE_REQUEST_CODE = 101;
    private Intent intent = new Intent();
    private Intent intentpic = new Intent();
    private CodeScannerView scanner_view;
    private TextView tv_galery;
    private CodeScanner yourcomponentname;

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C0978R.layout.scan_fragment, viewGroup, false);
        initialize(bundle, viewInflate);
        initializeLogic();
        return viewInflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.tv_galery = (TextView) view.findViewById(C0978R.id.tv_galery);
        this.scanner_view = (CodeScannerView) view.findViewById(C0978R.id.scanner_view);
        this.tv_galery.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScanFragmentActivity.this._galeryQrCode();
            }
        });
    }

    private void initializeLogic() {
        _setFirstUI();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {             @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                ScanFragmentActivity.this.requireActivity().onBackPressed();
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.yourcomponentname.startPreview();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.yourcomponentname.releaseResources();
    }

    public void _setFirstUI() {
        CodeScanner codeScanner = new CodeScanner(requireActivity(), this.scanner_view);
        this.yourcomponentname = codeScanner;
        codeScanner.setCamera(-1);
        this.yourcomponentname.setFormats(CodeScanner.ALL_FORMATS);
        this.yourcomponentname.setScanMode(ScanMode.SINGLE);
        this.yourcomponentname.setAutoFocusMode(AutoFocusMode.SAFE);
        this.yourcomponentname.setAutoFocusEnabled(true);
        this.yourcomponentname.setFlashEnabled(false);
        this.yourcomponentname.setDecodeCallback(new DecodeCallback() {             @Override // com.budiyev.android.codescanner.DecodeCallback
            public void onDecoded(@NonNull final Result result) {
                ScanFragmentActivity.this.requireActivity().runOnUiThread(new Runnable() {                     @Override // java.lang.Runnable
                    public void run() {
                        ScanFragmentActivity.this.intent.setClass(ScanFragmentActivity.this.requireContext(), ScanResultActivity.class);
                        ScanFragmentActivity.this.intent.putExtra("key", result.getText());
                        ScanFragmentActivity.this.startActivity(ScanFragmentActivity.this.intent);
                    }
                });
            }
        });
        this.yourcomponentname.startPreview();
    }

        public void _galeryQrCode() {
        startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 101);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == -1 && intent != null) {
            try {
                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(requireActivity().getContentResolver().openInputStream(intent.getData()));
                if (bitmapDecodeStream != null) {
                    int width = bitmapDecodeStream.getWidth();
                    int height = bitmapDecodeStream.getHeight();
                    int[] iArr = new int[width * height];
                    bitmapDecodeStream.getPixels(iArr, 0, width, 0, 0, width, height);
                    try {
                        String text = new MultiFormatReader().decode(new BinaryBitmap(new HybridBinarizer(new RGBLuminanceSource(width, height, iArr)))).getText();
                        this.intent.setClass(requireContext(), ScanResultActivity.class);
                        this.intent.putExtra("key", text);
                        startActivity(this.intent);
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
        }
    }
}