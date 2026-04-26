package com.fufufu.katrina.backup;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.carousel.MaskableFrameLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AddShortcutActivity extends AppCompatActivity {
    private FragmentshortcutFragmentAdapter fragmentshortcut;
    private FrameLayout frame_base;
    private LinearLayout ln_base;
    private RecyclerView rv_corousel_banner;
    private TextView tv_title_main;
    private HashMap<String, Object> m_fragment = new HashMap<>();
    private String s_extra = "";
    private ArrayList<HashMap<String, Object>> lm_fragment = new ArrayList<>();

    public void _EXTRA() {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0978R.layout.add_shortcut);
        initialize(bundle);
        initializeLogic();
    }

    private void initialize(Bundle bundle) {
        this.ln_base = (LinearLayout) findViewById(C0978R.id.ln_base);
        this.rv_corousel_banner = (RecyclerView) findViewById(C0978R.id.rv_corousel_banner);
        this.tv_title_main = (TextView) findViewById(C0978R.id.tv_title_main);
        this.frame_base = (FrameLayout) findViewById(C0978R.id.frame_base);
        this.fragmentshortcut = new FragmentshortcutFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
    }

    private void initializeLogic() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(0);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.setNavigationBarColor(0);
        }
        getWindow().getDecorView().setSystemUiVisibility(8208);
        _setFirstUI();
    }

    public class FragmentshortcutFragmentAdapter extends FragmentStatePagerAdapter {
        Context context;
        int tabCount;

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int i) {
            return null;
        }

        public FragmentshortcutFragmentAdapter(Context context, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.context = context;
        }

        public void setTabCount(int i) {
            this.tabCount = i;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.tabCount;
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int i) {
            if (i == 0) {
                return new SctoolFragmentActivity();
            }
            if (i == 1) {
                return new ScopenFragmentActivity();
            }
            if (i == 2) {
                return new ScwipeFragmentActivity();
            }
            if (i == 3) {
                return new ScwipemassFragmentActivity();
            }
            if (i == 4) {
                return new SclinkFragmentActivity();
            }
            return null;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        finish();
    }

    public void _setFirstUI() {
        if (true) {
            if (getIntent().hasExtra("wipemass_editor")) {
                HashMap<String, Object> map = new HashMap<>();
                this.m_fragment = map;
                map.put("fragment", "wipemass");
                this.m_fragment.put("title", "Shortcut\nMulti Wipe");
                this.lm_fragment.add(this.m_fragment);
                _onFragmentWipeMassEditor(getIntent().getStringExtra("wipemass_editor"));
                _setBannerCarousel("show");
                return;
            }
            if (getIntent().hasExtra("ritual_editor")) {
                _onFragmentRitual(getIntent().getStringExtra("ritual_editor"));
                _setBannerCarousel("hide");
                return;
            }
            HashMap<String, Object> map2 = new HashMap<>();
            this.m_fragment = map2;
            map2.put("fragment", "tools");
            this.m_fragment.put("title", "Shortcut\nTools");
            this.lm_fragment.add(this.m_fragment);
            HashMap<String, Object> map3 = new HashMap<>();
            this.m_fragment = map3;
            map3.put("fragment", "openapp");
            this.m_fragment.put("title", "Shortcut\nOpen App");
            this.lm_fragment.add(this.m_fragment);
            HashMap<String, Object> map4 = new HashMap<>();
            this.m_fragment = map4;
            map4.put("fragment", "wipeapp");
            this.m_fragment.put("title", "Shortcut\nWipe App");
            this.lm_fragment.add(this.m_fragment);
            HashMap<String, Object> map5 = new HashMap<>();
            this.m_fragment = map5;
            map5.put("fragment", "wipemass");
            this.m_fragment.put("title", "Shortcut\nMulti Wipe");
            this.lm_fragment.add(this.m_fragment);
            HashMap<String, Object> map6 = new HashMap<>();
            this.m_fragment = map6;
            map6.put("fragment", "openlink");
            this.m_fragment.put("title", "Shortcut\nLink");
            this.lm_fragment.add(this.m_fragment);
            _onFragmentTool();
            _setBannerCarousel("show");
            return;
        }
        startActivity(new Intent(getApplicationContext(), (Class<?>) MainActivity.class));
        finish();
    }

    public void _selectFragment(String str) {
        if (str.equals("tools")) {
            this.tv_title_main.setText("Shortcut Tools");
            _onFragmentTool();
            return;
        }
        if (str.equals("openapp")) {
            this.tv_title_main.setText("Shortcut Open App");
            _onFragmentOpenApp();
            return;
        }
        if (str.equals("wipeapp")) {
            this.tv_title_main.setText("Shortcut Wipe App");
            _onFragmentWipe();
        } else if (str.equals("wipemass")) {
            this.tv_title_main.setText("Shortcut Wipe Mass");
            _onFragmentWipeMass();
        } else if (str.equals("openlink")) {
            this.tv_title_main.setText("Shortcut Link");
            _onFragmentLink();
        } else {
            this.tv_title_main.setText("Shortcut");
            _onFragmentTool();
        }
    }

    public void _onFragmentTool() {
        getSupportFragmentManager().beginTransaction().replace(C0978R.id.frame_base, new SctoolFragmentActivity()).commit();
    }

    public void _onFragmentOpenApp() {
        getSupportFragmentManager().beginTransaction().replace(C0978R.id.frame_base, new ScopenFragmentActivity()).commit();
    }

    public void _onFragmentWipe() {
        getSupportFragmentManager().beginTransaction().replace(C0978R.id.frame_base, new ScwipeFragmentActivity()).commit();
    }

    public void _onFragmentWipeMass() {
        getSupportFragmentManager().beginTransaction().replace(C0978R.id.frame_base, new ScwipemassFragmentActivity()).commit();
    }

    public void _onFragmentLink() {
        getSupportFragmentManager().beginTransaction().replace(C0978R.id.frame_base, new SclinkFragmentActivity()).commit();
    }

    public void _createShortcut(String str, String str2, String str3, String str4) {
        if (str4.equals("timepick")) {
            Intent intent = new Intent(getApplicationContext(), (Class<?>) ShortcutExecutorActivity.class);
            intent.setAction("android.intent.action.MAIN");
            intent.putExtra("shortcut_command", str);
            intent.putExtra("shortcut_desc", str3);
            Intent intent2 = new Intent();
            intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
            intent2.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), C0978R.drawable.ic_shortcut_timepick));
            intent2.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent2);
            setResult(-1, intent2);
            finish();
            return;
        }
        if (str4.equals("killall")) {
            Intent intent3 = new Intent(getApplicationContext(), (Class<?>) ShortcutExecutorActivity.class);
            intent3.setAction("android.intent.action.MAIN");
            intent3.putExtra("shortcut_command", str);
            intent3.putExtra("shortcut_desc", str3);
            Intent intent4 = new Intent();
            intent4.putExtra("android.intent.extra.shortcut.INTENT", intent3);
            intent4.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent4.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), C0978R.drawable.ic_shortcut_killall));
            intent4.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent4);
            setResult(-1, intent4);
            finish();
            return;
        }
        if (str4.equals("reseto")) {
            Intent intent5 = new Intent(getApplicationContext(), (Class<?>) ShortcutExecutorActivity.class);
            intent5.setAction("android.intent.action.MAIN");
            intent5.putExtra("shortcut_command", str);
            intent5.putExtra("shortcut_desc", str3);
            Intent intent6 = new Intent();
            intent6.putExtra("android.intent.extra.shortcut.INTENT", intent5);
            intent6.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent6.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), C0978R.drawable.ic_shortcut_reseto));
            intent6.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent6);
            setResult(-1, intent6);
            finish();
            return;
        }
        if (str4.equals("modpes")) {
            Intent intent7 = new Intent(getApplicationContext(), (Class<?>) ShortcutExecutorActivity.class);
            intent7.setAction("android.intent.action.MAIN");
            intent7.putExtra("shortcut_command", str);
            intent7.putExtra("shortcut_desc", str3);
            Intent intent8 = new Intent();
            intent8.putExtra("android.intent.extra.shortcut.INTENT", intent7);
            intent8.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent8.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), C0978R.drawable.ic_shortcut_modpes));
            intent8.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent8);
            setResult(-1, intent8);
            finish();
            return;
        }
        if (str4.equals("refufu")) {
            Intent intent9 = new Intent(getApplicationContext(), (Class<?>) ShortcutExecutorActivity.class);
            intent9.setAction("android.intent.action.MAIN");
            intent9.putExtra("shortcut_command", str);
            intent9.putExtra("shortcut_desc", str3);
            Intent intent10 = new Intent();
            intent10.putExtra("android.intent.extra.shortcut.INTENT", intent9);
            intent10.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent10.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), C0978R.drawable.ic_shortcut_refufu));
            intent10.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent10);
            setResult(-1, intent10);
            finish();
            return;
        }
        if (str4.equals("editprop")) {
            Intent intent11 = new Intent(getApplicationContext(), (Class<?>) ShortcutExecutorActivity.class);
            intent11.setAction("android.intent.action.MAIN");
            intent11.putExtra("shortcut_command", str);
            intent11.putExtra("shortcut_desc", str3);
            Intent intent12 = new Intent();
            intent12.putExtra("android.intent.extra.shortcut.INTENT", intent11);
            intent12.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent12.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), C0978R.drawable.ic_shortcut_editprop));
            intent12.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent12);
            setResult(-1, intent12);
            finish();
            return;
        }
        if (str4.equals("fakegps")) {
            Intent intent13 = new Intent(getApplicationContext(), (Class<?>) ShortcutExecutorActivity.class);
            intent13.setAction("android.intent.action.MAIN");
            intent13.putExtra("shortcut_command", str);
            intent13.putExtra("shortcut_desc", str3);
            Intent intent14 = new Intent();
            intent14.putExtra("android.intent.extra.shortcut.INTENT", intent13);
            intent14.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent14.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), C0978R.drawable.ic_shortcut_fakegps));
            intent14.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent14);
            setResult(-1, intent14);
            finish();
            return;
        }
        if (str4.equals("fastreboot")) {
            Intent intent17 = new Intent(getApplicationContext(), (Class<?>) ShortcutExecutorActivity.class);
            intent17.setAction("android.intent.action.MAIN");
            intent17.putExtra("shortcut_command", str);
            intent17.putExtra("shortcut_desc", str3);
            Intent intent18 = new Intent();
            intent18.putExtra("android.intent.extra.shortcut.INTENT", intent17);
            intent18.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent18.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), C0978R.drawable.ic_shortcut_fastreboot));
            intent18.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent18);
            setResult(-1, intent18);
            finish();
            return;
        }
        if (str4.equals("wipegms")) {
            Intent intent19 = new Intent(getApplicationContext(), (Class<?>) ShortcutExecutorActivity.class);
            intent19.setAction("android.intent.action.MAIN");
            intent19.putExtra("shortcut_command", str);
            intent19.putExtra("shortcut_desc", str3);
            Intent intent20 = new Intent();
            intent20.putExtra("android.intent.extra.shortcut.INTENT", intent19);
            intent20.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent20.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), C0978R.drawable.ic_shortcut_wipegms));
            intent20.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent20);
            setResult(-1, intent20);
            finish();
            return;
        }
        if (str4.equals("katrina")) {
            Intent intent21 = new Intent(getApplicationContext(), (Class<?>) ShortcutExecutorActivity.class);
            intent21.setAction("android.intent.action.MAIN");
            intent21.putExtra("shortcut_command", str);
            intent21.putExtra("shortcut_desc", str3);
            Intent intent22 = new Intent();
            intent22.putExtra("android.intent.extra.shortcut.INTENT", intent21);
            intent22.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent22.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), C0978R.drawable.ic_shortcut_katrina));
            intent22.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent22);
            setResult(-1, intent22);
            finish();
            return;
        }
        new Intent().putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), C0978R.drawable.app_icon));
    }

    public void _onFragmentWipeMassEditor(String str) {
        this.s_extra = str;
        ScwipemassFragmentActivity scwipemassFragmentActivity = new ScwipemassFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("wipemass_editor", this.s_extra);
        scwipemassFragmentActivity.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(C0978R.id.frame_base, scwipemassFragmentActivity).commit();
    }

    public void _onFragmentRitual(String str) {
        this.s_extra = str;
        ScritualFragmentActivity scritualFragmentActivity = new ScritualFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("wipemass_editor", this.s_extra);
        scritualFragmentActivity.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(C0978R.id.frame_base, scritualFragmentActivity).commit();
    }

    public void _setBannerCarousel(String str) {
        if (str.equals("show")) {
            this.rv_corousel_banner.setVisibility(0);
            this.rv_corousel_banner.setAdapter(new Rv_corousel_bannerAdapter(this.lm_fragment));
            this.tv_title_main.setText("Shortcut Tools");
        } else {
            this.rv_corousel_banner.setVisibility(8);
            this.tv_title_main.setText("Shortcut Ritual");
        }
    }

    public class Rv_corousel_bannerAdapter extends RecyclerView.Adapter<Rv_corousel_bannerAdapter.ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;

        public Rv_corousel_bannerAdapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View viewInflate = AddShortcutActivity.this.getLayoutInflater().inflate(C0978R.layout.corousel_image_view, (ViewGroup) null);
            viewInflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(viewInflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            View view = viewHolder.itemView;
            MaskableFrameLayout maskableFrameLayout = (MaskableFrameLayout) view.findViewById(C0978R.id.carousel_item_container);
            ImageView imageView = (ImageView) view.findViewById(C0978R.id.carousel_image_view);
            TextView textView = (TextView) view.findViewById(C0978R.id.tv_title_carousel);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins((int) AddShortcutActivity.this.getDip(4), (int) AddShortcutActivity.this.getDip(0), (int) AddShortcutActivity.this.getDip(4), (int) AddShortcutActivity.this.getDip(0));
            maskableFrameLayout.setLayoutParams(layoutParams);
            if (this._data.get(i).get("fragment").toString().equals("tools")) {
                imageView.setImageResource(C0978R.drawable.f_banner_tool);
            } else if (this._data.get(i).get("fragment").toString().equals("openapp")) {
                imageView.setImageResource(C0978R.drawable.f_banner_open);
            } else if (this._data.get(i).get("fragment").toString().equals("wipeapp")) {
                imageView.setImageResource(C0978R.drawable.f_banner_wipe);
            } else if (this._data.get(i).get("fragment").toString().equals("wipemass")) {
                imageView.setImageResource(C0978R.drawable.f_banner_wipemass);
            } else if (this._data.get(i).get("fragment").toString().equals("openlink")) {
                imageView.setImageResource(C0978R.drawable.f_banner_link);
            } else {
                imageView.setImageResource(C0978R.drawable.f_banner_tool);
            }
            textView.setText(this._data.get(i).get("title").toString());
            maskableFrameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AddShortcutActivity.Rv_corousel_bannerAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AddShortcutActivity.this._selectFragment(Rv_corousel_bannerAdapter.this._data.get(i).get("fragment").toString());
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this._data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View view) {
                super(view);
            }
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
