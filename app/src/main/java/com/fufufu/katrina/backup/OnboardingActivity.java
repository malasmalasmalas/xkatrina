package com.fufufu.katrina.backup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

/**
 * Onboarding pertama-launch.
 * Menampilkan welcome + language picker. Locale disimpan via LocaleHelper.
 * Untuk menambah bahasa baru, edit LocaleHelper.SUPPORTED_TAGS / SUPPORTED_NAMES
 * dan tambah `values-<lang>/strings.xml`.
 */
public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.wrap(base));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding);

        final ChipGroup cg = findViewById(R.id.cg_lang);
        MaterialButton btnContinue = findViewById(R.id.btn_continue);

        final String currentTag = LocaleHelper.getSavedTag(this);
        final int[] checkedId = {-1};
        if (cg != null) {
            for (int i = 0; i < LocaleHelper.SUPPORTED_TAGS.length; i++) {
                final String tag = LocaleHelper.SUPPORTED_TAGS[i];
                Chip chip = new Chip(this);
                chip.setId(View.generateViewId());
                chip.setText(LocaleHelper.SUPPORTED_NAMES[i]);
                chip.setCheckable(true);
                chip.setClickable(true);
                cg.addView(chip);
                if (tag.equals(currentTag)) {
                    chip.setChecked(true);
                    checkedId[0] = chip.getId();
                }
                chip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LocaleHelper.setLocale(OnboardingActivity.this, tag);
                        recreate();
                    }
                });
            }
            if (checkedId[0] != -1) {
                cg.check(checkedId[0]);
            }
        }

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleHelper.setOnboardingDone(OnboardingActivity.this, true);
                Intent it = new Intent(OnboardingActivity.this, PermissionActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(it);
                finish();
            }
        });
    }
}
