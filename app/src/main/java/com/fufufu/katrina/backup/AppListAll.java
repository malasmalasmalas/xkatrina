package com.fufufu.katrina.backup;

import android.app.TabActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TabHost;
import com.fufufu.katrina.backup.ProcessItem;
import java.util.ArrayList;
import java.util.Iterator;

public class AppListAll extends TabActivity {
    ListView mListViewSystem;
    ListView mListViewUser;

    @Override // android.app.ActivityGroup, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0978R.layout.app_list_all);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(0);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.setNavigationBarColor(0);
        }
        getWindow().getDecorView().setSystemUiVisibility(8208);
        if (Statics.PACKAGE_NAME == null) {
            Statics.PACKAGE_NAME = getPackageName();
        }
        this.mListViewUser = (ListView) findViewById(C0978R.id.listViewUser);
        this.mListViewSystem = (ListView) findViewById(C0978R.id.listViewSystem);
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        TabHost.TabSpec tabSpecNewTabSpec = tabHost.newTabSpec("tabUser");
        tabSpecNewTabSpec.setContent(C0978R.id.tab1);
        tabSpecNewTabSpec.setIndicator(getString(C0978R.string.tab_user_apps));
        tabHost.addTab(tabSpecNewTabSpec);
        TabHost.TabSpec tabSpecNewTabSpec2 = tabHost.newTabSpec("tabSystem");
        tabSpecNewTabSpec2.setContent(C0978R.id.tab2);
        tabSpecNewTabSpec2.setIndicator(getString(C0978R.string.tab_system_apps));
        tabHost.addTab(tabSpecNewTabSpec2);
        Spinner spinner = (Spinner) findViewById(C0978R.id.spinnerUser);
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(C0978R.array.spinner_filter_items)));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {             @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ProcessItem.FilterTypes filterTypes = ProcessItem.FilterTypes.AllApps;
                if (i == 1) {
                    filterTypes = ProcessItem.FilterTypes.OnlyCheckedApps;
                } else if (i == 2) {
                    filterTypes = ProcessItem.FilterTypes.OnlyUncheckedApps;
                }
                AppListAll.this.GetUserApps(filterTypes);
            }
        });
        Spinner spinner2 = (Spinner) findViewById(C0978R.id.spinnerSystem);
        spinner2.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(C0978R.array.spinner_filter_items)));
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {             @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ProcessItem.FilterTypes filterTypes = ProcessItem.FilterTypes.AllApps;
                if (i == 1) {
                    filterTypes = ProcessItem.FilterTypes.OnlyCheckedApps;
                } else if (i == 2) {
                    filterTypes = ProcessItem.FilterTypes.OnlyUncheckedApps;
                }
                AppListAll.this.GetSystemApps(filterTypes);
            }
        });
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        finish();
    }

    public void GetUserApps(ProcessItem.FilterTypes filterTypes) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(ProcessItem.getInstalledApps(this, ProcessItem.ApplicationTypes.OnlyUserApps, filterTypes));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((ProcessItem) it.next()).saveChanges = true;
        }
        this.mListViewUser.setAdapter((ListAdapter) new ProcessItemArrayAdapter(this, arrayList));
    }

    public void GetSystemApps(ProcessItem.FilterTypes filterTypes) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(ProcessItem.getInstalledApps(this, ProcessItem.ApplicationTypes.OnlySystemApps, filterTypes));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((ProcessItem) it.next()).saveChanges = true;
        }
        this.mListViewSystem.setAdapter((ListAdapter) new ProcessItemArrayAdapter(this, arrayList));
    }
}