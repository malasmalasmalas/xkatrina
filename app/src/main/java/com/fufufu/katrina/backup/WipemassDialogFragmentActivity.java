package com.fufufu.katrina.backup;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topjohnwu.superuser.Shell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WipemassDialogFragmentActivity extends DialogFragment {
    private MaterialButton btn_close;
    private LinearLayout ln_01;
    private LinearLayout ln_02;
    private LottieAnimationView lottie1;
    private ListView lv_wipe_mass;
    private MaterialCardView mcv_base;
    private MyWIPEMASS myWIPEMASS;
    private SharedPreferences prefwipemass;
    private TextView tv_empty;
    private TextView tv_universal;
    private Vibrator vibrator;
    private String s_extra = "";
    private String s_command = "";
    private String s_universal_progress = "";
    private String s_commandResult = "";
    private String s_exitCode = "";
    private boolean b_command = false;
    private double n_delete = 0.0d;
    private ArrayList<HashMap<String, Object>> lm_wipemass = new ArrayList<>();
    private Intent intentFinish = new Intent();
    private Intent intent_editor = new Intent();

    public void _EXTRA() {
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C0978R.layout.wipemass_dialog_fragment, viewGroup, false);
        initialize(bundle, viewInflate);
        initializeLogic();
        return viewInflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.mcv_base = (MaterialCardView) view.findViewById(C0978R.id.mcv_base);
        this.ln_01 = (LinearLayout) view.findViewById(C0978R.id.ln_01);
        this.ln_02 = (LinearLayout) view.findViewById(C0978R.id.ln_02);
        this.tv_empty = (TextView) view.findViewById(C0978R.id.tv_empty);
        this.lv_wipe_mass = (ListView) view.findViewById(C0978R.id.lv_wipe_mass);
        this.btn_close = (MaterialButton) view.findViewById(C0978R.id.btn_close);
        this.lottie1 = (LottieAnimationView) view.findViewById(C0978R.id.lottie1);
        this.tv_universal = (TextView) view.findViewById(C0978R.id.tv_universal);
        this.vibrator = (Vibrator) getContext().getSystemService("vibrator");
        this.prefwipemass = getContext().getSharedPreferences("wipemass_preferences", 0);
        this.btn_close.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                WipemassDialogFragmentActivity.this._finishDialog();
            }
        });
    }

    private void initializeLogic() {
        this.lottie1.setVisibility(8);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.s_extra = arguments.getString("extrakey", "");
            this.lm_wipemass = (ArrayList) new Gson().fromJson(this.s_extra, new TypeToken<ArrayList<HashMap<String, Object>>>() {             }.getType());
            this.lv_wipe_mass.setAdapter((ListAdapter) new Lv_wipe_massAdapter(this.lm_wipemass));
        } else {
            _finishDialog();
        }
        _cekLenghtListWipe();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            dialog.getWindow().setLayout(-1, -2);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        getDialog().setCancelable(false);
    }

    public void _finishDialog() {
        this.intentFinish.setClass(getContext().getApplicationContext(), ShortcutExecutorActivity.class);
        this.intentFinish.putExtra("shortcut_command", "finish");
        this.intentFinish.putExtra("shortcut_desc", "finish");
        startActivity(this.intentFinish);
        getActivity().finish();
    }

    public void _onWipeMass(String str, String str2) {
        this.s_command = str;
        MyWIPEMASS myWIPEMASS = this.myWIPEMASS;
        if (myWIPEMASS != null && myWIPEMASS.isRunning) {
            this.myWIPEMASS.cancelWIPEMASSTask();
        }
        MyWIPEMASS myWIPEMASS2 = new MyWIPEMASS();
        this.myWIPEMASS = myWIPEMASS2;
        myWIPEMASS2.execute(new Void[0]);
    }

    public class MyWIPEMASS extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyWIPEMASS() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            WipemassDialogFragmentActivity wipemassDialogFragmentActivity = WipemassDialogFragmentActivity.this;
            wipemassDialogFragmentActivity.s_universal_progress = wipemassDialogFragmentActivity.s_command.replace("am force-stop", "");
            WipemassDialogFragmentActivity wipemassDialogFragmentActivity2 = WipemassDialogFragmentActivity.this;
            wipemassDialogFragmentActivity2.s_universal_progress = wipemassDialogFragmentActivity2.s_universal_progress.replace("pm clear", "");
            WipemassDialogFragmentActivity wipemassDialogFragmentActivity3 = WipemassDialogFragmentActivity.this;
            wipemassDialogFragmentActivity3.s_universal_progress = wipemassDialogFragmentActivity3.s_universal_progress.replace(" ", "");
            WipemassDialogFragmentActivity wipemassDialogFragmentActivity4 = WipemassDialogFragmentActivity.this;
            wipemassDialogFragmentActivity4.s_universal_progress = wipemassDialogFragmentActivity4.s_universal_progress.replace(";", "\n");
            WipemassDialogFragmentActivity.this.tv_universal.setTextSize(10.0f);
            WipemassDialogFragmentActivity.this.tv_universal.setText(WipemassDialogFragmentActivity.this.s_universal_progress);
        }

                @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            WipemassDialogFragmentActivity.this.b_command = false;
            Shell.Result resultExec = Shell.cmd(WipemassDialogFragmentActivity.this.s_command).exec();
            List<String> out = resultExec.getOut();
            resultExec.getCode();
            WipemassDialogFragmentActivity.this.b_command = resultExec.isSuccess();
            WipemassDialogFragmentActivity.this.s_commandResult = String.join("\n", out);
            WipemassDialogFragmentActivity.this._afterWipeMass();
            return null;
        }

                @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
        }

        public void cancelWIPEMASSTask() {
            cancel(true);
        }
    }

    public void _afterWipeMass() {
        this.vibrator.vibrate(100L);
        _finishDialog();
    }

    public void _deleteWipeMassData(String str) {
        this.prefwipemass.edit().remove(str).commit();
        this.n_delete = 0.0d;
        int i = 0;
        while (true) {
            if (i >= this.lm_wipemass.size()) {
                break;
            }
            if (this.lm_wipemass.get((int) this.n_delete).get("key_app").toString().equals(str)) {
                this.lm_wipemass.remove((int) this.n_delete);
                break;
            } else {
                this.n_delete += 1.0d;
                i++;
            }
        }
        this.lv_wipe_mass.setAdapter((ListAdapter) new Lv_wipe_massAdapter(this.lm_wipemass));
        ((BaseAdapter) this.lv_wipe_mass.getAdapter()).notifyDataSetChanged();
        _cekLenghtListWipe();
    }

    public void _gotoEditorActivity(String str) {
        this.intent_editor.setClass(getContext().getApplicationContext(), AddShortcutActivity.class);
        this.intent_editor.putExtra("wipemass_editor", str);
        startActivity(this.intent_editor);
        getActivity().finish();
    }

    public void _cekLenghtListWipe() {
        if (this.lm_wipemass.size() == 0) {
            this.lv_wipe_mass.setVisibility(8);
            this.tv_empty.setVisibility(0);
        } else {
            this.lv_wipe_mass.setVisibility(0);
            this.tv_empty.setVisibility(8);
        }
    }

    public class Lv_wipe_massAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_wipe_massAdapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this._data.size();
        }

        @Override // android.widget.Adapter
        public HashMap<String, Object> getItem(int i) {
            return this._data.get(i);
        }

        @Override // android.widget.Adapter
        public View getView(final int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = WipemassDialogFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(C0978R.layout.dialog_wipemass_view, (ViewGroup) null);
            }
            MaterialCardView materialCardView = (MaterialCardView) view.findViewById(C0978R.id.mcv_title);
            TextView textView = (TextView) view.findViewById(C0978R.id.tv_wipemass_title);
            ImageView imageView = (ImageView) view.findViewById(C0978R.id.im_wipemass_edit);
            ImageView imageView2 = (ImageView) view.findViewById(C0978R.id.im_wipemass_delete);
            textView.setText(this._data.get(i).get("key_app").toString());
            materialCardView.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    WipemassDialogFragmentActivity.this._onWipeMass(Lv_wipe_massAdapter.this._data.get(i).get("value_app").toString(), Lv_wipe_massAdapter.this._data.get(i).get("key_app").toString());
                }
            });
            imageView2.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    WipemassDialogFragmentActivity.this._deleteWipeMassData(Lv_wipe_massAdapter.this._data.get(i).get("key_app").toString());
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {                 @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    WipemassDialogFragmentActivity.this._gotoEditorActivity(Lv_wipe_massAdapter.this._data.get(i).get("key_app").toString());
                }
            });
            return view;
        }
    }
}
