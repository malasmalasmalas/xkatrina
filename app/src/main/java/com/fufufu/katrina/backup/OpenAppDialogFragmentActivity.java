package com.fufufu.katrina.backup;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;

public class OpenAppDialogFragmentActivity extends DialogFragment {
    private Button btn_batal;
    private Button btn_save;
    private EditText et_input;
    private EditText et_title;
    private ImageView im_appicon;
    private LinearLayout ln_app;
    private LinearLayout ln_base;
    private LinearLayout ln_button;
    private LinearLayout ln_desc;
    private LinearLayout ln_input;
    private MaterialCardView mvc_base;
    private NestedScrollView nestscroll_1;
    private SharedPreferences preflink;
    private RecyclerView rv_1;
    private TextView tv_appname;
    private TextView tv_apppackage;
    private TextView tv_dialog_link;
    private TextView tv_dialog_title;
    private String s_extra = "";
    private HashMap<String, Object> m_add = new HashMap<>();
    private String s_url = "";

        private double f815n = 0.0d;
    private ArrayList<HashMap<String, Object>> lm_link = new ArrayList<>();
    private Intent iShortcut = new Intent();

    public void _EXTRA() {
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C0978R.layout.open_app_dialog_fragment, viewGroup, false);
        initialize(bundle, viewInflate);
        initializeLogic();
        return viewInflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.mvc_base = (MaterialCardView) view.findViewById(C0978R.id.mvc_base);
        this.ln_base = (LinearLayout) view.findViewById(C0978R.id.ln_base);
        this.tv_dialog_title = (TextView) view.findViewById(C0978R.id.tv_dialog_title);
        this.ln_app = (LinearLayout) view.findViewById(C0978R.id.ln_app);
        this.tv_dialog_link = (TextView) view.findViewById(C0978R.id.tv_dialog_link);
        this.nestscroll_1 = (NestedScrollView) view.findViewById(C0978R.id.nestscroll_1);
        this.ln_input = (LinearLayout) view.findViewById(C0978R.id.ln_input);
        this.ln_button = (LinearLayout) view.findViewById(C0978R.id.ln_button);
        this.im_appicon = (ImageView) view.findViewById(C0978R.id.im_appicon);
        this.ln_desc = (LinearLayout) view.findViewById(C0978R.id.ln_desc);
        this.tv_appname = (TextView) view.findViewById(C0978R.id.tv_appname);
        this.tv_apppackage = (TextView) view.findViewById(C0978R.id.tv_apppackage);
        this.rv_1 = (RecyclerView) view.findViewById(C0978R.id.rv_1);
        this.et_title = (EditText) view.findViewById(C0978R.id.et_title);
        this.et_input = (EditText) view.findViewById(C0978R.id.et_input);
        this.btn_batal = (Button) view.findViewById(C0978R.id.btn_batal);
        this.btn_save = (Button) view.findViewById(C0978R.id.btn_save);
        this.preflink = getContext().getSharedPreferences("openapp_preferences", 0);
        this.ln_app.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                OpenAppDialogFragmentActivity openAppDialogFragmentActivity = OpenAppDialogFragmentActivity.this;
                openAppDialogFragmentActivity._openApp(openAppDialogFragmentActivity.s_extra);
            }
        });
        this.btn_batal.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                OpenAppDialogFragmentActivity.this._cancelAdd();
            }
        });
        this.btn_save.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                OpenAppDialogFragmentActivity.this._addLink();
            }
        });
    }

    private void initializeLogic() {
        this.rv_1.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.s_extra = arguments.getString("package", "");
            _cancelAdd();
            try {
                this.im_appicon.setImageDrawable(getActivity().getPackageManager().getApplicationIcon(this.s_extra));
                PackageManager packageManager = getActivity().getPackageManager();
                this.tv_appname.setText(packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.s_extra, 0)).toString());
            } catch (PackageManager.NameNotFoundException unused) {
            }
            this.tv_apppackage.setText(this.s_extra);
            if (this.preflink.getString(this.s_extra, "").equals("")) {
                return;
            }
            this.lm_link = (ArrayList) new Gson().fromJson(this.preflink.getString(this.s_extra, ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {             }.getType());
            this.rv_1.setAdapter(new Rv_1Adapter(this.lm_link));
            this.rv_1.setHasFixedSize(true);
            return;
        }
        getActivity().finish();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        dialog.getWindow().setLayout(-1, -2);
        window.setBackgroundDrawable(new ColorDrawable(0));
    }

    public void _addLink() {
        if (this.nestscroll_1.getVisibility() == 0) {
            this.nestscroll_1.setVisibility(8);
            this.ln_input.setVisibility(0);
            this.btn_batal.setVisibility(0);
            this.btn_save.setText("Tambahkan");
            return;
        }
        if (!this.et_title.getText().toString().equals("") && !this.et_input.getText().toString().equals("")) {
            String string = this.et_input.getText().toString();
            if (!string.startsWith("http://") && !string.startsWith("https://")) {
                string = "https://" + string;
            }
            HashMap<String, Object> map = new HashMap<>();
            this.m_add = map;
            map.put("link_title", this.et_title.getText().toString());
            this.m_add.put("link_content", string);
            this.m_add.put("link_deep", this.et_input.getText().toString());
            this.lm_link.add(this.m_add);
            this.rv_1.setAdapter(new Rv_1Adapter(this.lm_link));
            this.rv_1.getAdapter().notifyDataSetChanged();
            this.preflink.edit().putString(this.s_extra, new Gson().toJson(this.lm_link)).commit();
            _cancelAdd();
            return;
        }
        SketchwareUtil.showMessage(getContext().getApplicationContext(), "Harap lengkapi data");
    }

    public void _cancelAdd() {
        this.nestscroll_1.setVisibility(0);
        this.ln_input.setVisibility(8);
        this.btn_batal.setVisibility(8);
        this.btn_save.setText("Tambah Link");
        this.et_title.setText("");
        this.et_input.setText("");
    }

    public void _openApp(String str) {
        Intent launchIntentForPackage = getActivity().getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            startActivity(launchIntentForPackage);
            dismiss();
        } else {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Aplikasi tidak ditemukan");
        }
    }

    public void _openAppLink(String str, String str2) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
            intent.setFlags(268435456);
            startActivity(intent);
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
            this.s_url = str;
            if (!TextUtils.isEmpty(str) && !this.s_url.startsWith("http://") && !this.s_url.startsWith("https://")) {
                this.s_url = "http://" + this.s_url;
            }
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(this.s_url));
            intent2.addFlags(268435456);
            if (requireContext().getPackageManager().queryIntentActivities(intent2, 0).size() > 0) {
                startActivity(intent2);
                dismiss();
            } else {
                Toast.makeText(requireContext(), "Tidak ada Aplikasi untuk membuka link", 0).show();
            }
        }
    }

    public void _deleteListURL(double d, String str, ArrayList<HashMap<String, Object>> arrayList) {
        arrayList.remove((int) d);
        this.rv_1.setAdapter(new Rv_1Adapter(this.lm_link));
        this.rv_1.getAdapter().notifyDataSetChanged();
        this.preflink.edit().putString(this.s_extra, new Gson().toJson(this.lm_link)).commit();
        SketchwareUtil.showMessage(getContext().getApplicationContext(), "Link dihapus");
    }

    public class Rv_1Adapter extends RecyclerView.Adapter<Rv_1Adapter.ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;

        public Rv_1Adapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View viewInflate = OpenAppDialogFragmentActivity.this.getActivity().getLayoutInflater().inflate(C0978R.layout.backup_listview_app, (ViewGroup) null);
            viewInflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(viewInflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            View view = viewHolder.itemView;
            MaterialCardView materialCardView = (MaterialCardView) view.findViewById(C0978R.id.materialcardview1);
            TextView textView = (TextView) view.findViewById(C0978R.id.tv_appname);
            ImageView imageView = (ImageView) view.findViewById(C0978R.id.civ_icon);
            textView.setTypeface(Typeface.createFromAsset(OpenAppDialogFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 1);
            try {
                imageView.setImageDrawable(OpenAppDialogFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(OpenAppDialogFragmentActivity.this.s_extra));
            } catch (PackageManager.NameNotFoundException unused) {
                imageView.setImageResource(C0978R.drawable.app_icon);
            }
            textView.setText(this._data.get(i).get("link_title").toString());
            materialCardView.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    OpenAppDialogFragmentActivity.this._openAppLink(Rv_1Adapter.this._data.get(i).get("link_content").toString(), Rv_1Adapter.this._data.get(i).get("link_deep").toString());
                }
            });
            materialCardView.setOnLongClickListener(new View.OnLongClickListener() {                 @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view2) {
                    OpenAppDialogFragmentActivity.this._deleteListURL(i, "link_tilte", Rv_1Adapter.this._data);
                    return true;
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
}
