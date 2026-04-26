package com.fufufu.katrina.backup;

import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.elevation.SurfaceColors;
import java.util.ArrayList;
import java.util.Random;

public class ScannerActivity extends AppCompatActivity {
    private FrameLayout fragment_container;
    private LinearLayout ln_base;
    private BottomNavigationView nav_view;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0978R.layout.scanner);
        initialize(bundle);
        initializeLogic();
    }

    private void initialize(Bundle bundle) {
        this.ln_base = (LinearLayout) findViewById(C0978R.id.ln_base);
        this.fragment_container = (FrameLayout) findViewById(C0978R.id.fragment_container);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(C0978R.id.nav_view);
        this.nav_view = bottomNavigationView;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {             @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case C0978R.id.navigation_create /* 2131362759 */:
                        ScannerActivity.this._loadFragment(new CreateCodeFragmentActivity());
                        break;
                    case C0978R.id.navigation_getprop /* 2131362760 */:
                        ScannerActivity.this._loadFragment(new GetpropFragmentActivity());
                        break;
                    case C0978R.id.navigation_scan /* 2131362762 */:
                        ScannerActivity.this._loadFragment(new ScanFragmentActivity());
                        break;
                }
                return true;
            }
        });
    }

    private void initializeLogic() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(0);
        }
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));
        getWindow().getDecorView().setSystemUiVisibility(8208);
        _loadFragment(new ScanFragmentActivity());
        if (Build.VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.CAMERA") != -1) {
            return;
        }
        requestPermissions(new String[]{"android.permission.CAMERA"}, 1000);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    public void _loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(C0978R.id.fragment_container, fragment).addToBackStack(null).commit();
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