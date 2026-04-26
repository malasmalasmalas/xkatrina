package com.fufufu.katrina.backup;

import android.app.AppOpsManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class FakeGpsActivity extends AppCompatActivity implements OnMapReadyCallback {
    @Override
    protected void attachBaseContext(android.content.Context base) {
        super.attachBaseContext(LocaleHelper.wrap(base));
    }

    private TextInputEditText et_lat;
    private TextInputEditText et_lon;
    private MaterialButton btn_toggle;
    private SharedPreferences sp;
    private GoogleMap map;
    private Marker marker;
    private boolean updatingFromMap = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0978R.layout.fake_gps);
        sp = getSharedPreferences(FakeGpsService.PREFS, MODE_PRIVATE);
        et_lat = findViewById(C0978R.id.et_lat);
        et_lon = findViewById(C0978R.id.et_lon);
        btn_toggle = findViewById(C0978R.id.btn_toggle);
        MaterialButton btnDevSettings = findViewById(C0978R.id.btn_dev_settings);
        MaterialButton btnPickPreset = findViewById(C0978R.id.btn_preset);

        et_lat.setText(String.valueOf(Double.longBitsToDouble(sp.getLong(FakeGpsService.KEY_LAT, Double.doubleToLongBits(-6.200000)))));
        et_lon.setText(String.valueOf(Double.longBitsToDouble(sp.getLong(FakeGpsService.KEY_LON, Double.doubleToLongBits(106.816666)))));
        refreshButton();

        TextWatcher etWatcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable e) {
                if (updatingFromMap || map == null) return;
                LatLng ll = readLatLng();
                if (ll != null) updateMarker(ll, false);
            }
        };
        et_lat.addTextChangedListener(etWatcher);
        et_lon.addTextChangedListener(etWatcher);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(C0978R.id.map);
        if (mapFragment != null) mapFragment.getMapAsync(this);

        btn_toggle.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { onToggle(); }
        });
        btnDevSettings.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                try {
                    startActivity(new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS));
                } catch (Exception e) {
                    startActivity(new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS));
                }
            }
        });
        btnPickPreset.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { showPresetDialog(); }
        });
    }

    private void refreshButton() {
        btn_toggle.setText(FakeGpsService.isRunning(this) ? getString(C0978R.string.fakegps_stop) : getString(C0978R.string.fakegps_start));
    }

    private boolean isMockLocationAllowed() {
        if (Build.VERSION.SDK_INT < 23) return true;
        try {
            AppOpsManager ops = (AppOpsManager) getSystemService(APP_OPS_SERVICE);
            int mode = ops.checkOp(AppOpsManager.OPSTR_MOCK_LOCATION, android.os.Process.myUid(), getPackageName());
            return mode == AppOpsManager.MODE_ALLOWED;
        } catch (Exception e) {
            return false;
        }
    }

    private void onToggle() {
        if (FakeGpsService.isRunning(this)) {
            startService(new Intent(this, FakeGpsService.class).setAction(FakeGpsService.ACTION_STOP));
            btn_toggle.postDelayed(new Runnable() { @Override public void run() { refreshButton(); } }, 250);
            return;
        }
        if (!isMockLocationAllowed()) {
            new MaterialAlertDialogBuilder(this)
                    .setTitle(getString(C0978R.string.fakegps_mock_disabled_title))
                    .setMessage(getString(C0978R.string.fakegps_mock_disabled_msg))
                    .setPositiveButton(getString(C0978R.string.fakegps_open), new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface d, int w) {
                            try {
                                startActivity(new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS));
                            } catch (Exception e) {
                                SketchwareUtil.showMessage(getApplicationContext(), getString(C0978R.string.fakegps_dev_unavailable));
                            }
                        }
                    })
                    .setNegativeButton(getString(C0978R.string.module_cancel), null)
                    .show();
            return;
        }
        double lat, lon;
        try {
            lat = Double.parseDouble(et_lat.getText().toString().trim());
            lon = Double.parseDouble(et_lon.getText().toString().trim());
        } catch (Exception e) {
            SketchwareUtil.showMessage(getApplicationContext(), getString(C0978R.string.fakegps_invalid_coord));
            return;
        }
        Intent svc = new Intent(this, FakeGpsService.class)
                .setAction(FakeGpsService.ACTION_START)
                .putExtra(FakeGpsService.EXTRA_LAT, lat)
                .putExtra(FakeGpsService.EXTRA_LON, lon);
        if (Build.VERSION.SDK_INT >= 26) startForegroundService(svc); else startService(svc);
        btn_toggle.postDelayed(new Runnable() { @Override public void run() { refreshButton(); } }, 350);
    }

    private void showPresetDialog() {
        final String[] names = new String[]{"Jakarta, ID", "Bandung, ID", "Tokyo, JP", "New York, US", "London, UK"};
        final double[][] coords = new double[][]{
                {-6.200000, 106.816666},
                {-6.914744, 107.609810},
                {35.689487, 139.691711},
                {40.712776, -74.005974},
                {51.507351, -0.127758}
        };
        new MaterialAlertDialogBuilder(this)
                .setTitle(getString(C0978R.string.fakegps_pick_title))
                .setItems(names, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface d, int idx) {
                        et_lat.setText(String.valueOf(coords[idx][0]));
                        et_lon.setText(String.valueOf(coords[idx][1]));
                    }
                })
                .show();
    }

    private LatLng readLatLng() {
        try {
            double la = Double.parseDouble(et_lat.getText().toString().trim());
            double lo = Double.parseDouble(et_lon.getText().toString().trim());
            if (la < -90 || la > 90 || lo < -180 || lo > 180) return null;
            return new LatLng(la, lo);
        } catch (Exception e) {
            return null;
        }
    }

    private void updateMarker(LatLng ll, boolean animate) {
        if (map == null) return;
        if (marker == null) {
            marker = map.addMarker(new MarkerOptions().position(ll).title(getString(C0978R.string.fakegps_title)).draggable(true));
        } else {
            marker.setPosition(ll);
        }
        if (animate) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(ll, 14f));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        LatLng ll = readLatLng();
        if (ll == null) ll = new LatLng(-6.2, 106.816666);
        updateMarker(ll, true);
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override public void onMapClick(LatLng pos) {
                updatingFromMap = true;
                et_lat.setText(String.valueOf(pos.latitude));
                et_lon.setText(String.valueOf(pos.longitude));
                updatingFromMap = false;
                updateMarker(pos, false);
            }
        });
        map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override public void onMarkerDragStart(Marker m) {}
            @Override public void onMarkerDrag(Marker m) {}
            @Override public void onMarkerDragEnd(Marker m) {
                updatingFromMap = true;
                et_lat.setText(String.valueOf(m.getPosition().latitude));
                et_lon.setText(String.valueOf(m.getPosition().longitude));
                updatingFromMap = false;
            }
        });
    }
}
