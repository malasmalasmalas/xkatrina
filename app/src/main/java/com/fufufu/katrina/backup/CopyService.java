package com.fufufu.katrina.backup;

import android.app.assist.AssistContent;
import android.app.assist.AssistStructure;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.ViewCompat;

public class CopyService extends VoiceInteractionSession {
    public Bitmap bitmapview;
    public ImageView im_handle;
    private ImageView im_top;
    public LinearLayout ln_handle;
    public final Context mycontext;
    private SharedPreferences pref_assist;
    public RelativeLayout rl_top;
    public String s_alltext;
    private String s_fififi;
    public String s_fufufu;
    private String s_key;
    private String s_toolbar;
    public ScrollView scrl_handle;
    public EditText tv_alltext;
    public TextView tv_match;
    public TextView tv_setting;
    public TextView tv_warning;

    private void handleMatchingKey() {
    }

    CopyService(Context context) {
        super(context);
        this.s_fufufu = "";
        this.bitmapview = null;
        this.s_alltext = "";
        this.s_fififi = "";
        this.s_key = "";
        this.s_toolbar = "";
        this.mycontext = context;
    }

    public void processView(RelativeLayout relativeLayout, AssistStructure.ViewNode viewNode, int i, int i2) {
        int left;
        int top;
        if (!isAccessible(viewNode) || viewNode.isAssistBlocked()) {
            return;
        }
        if (viewNode.getChildCount() == 0) {
            left = i + viewNode.getLeft();
            top = i2 + viewNode.getTop();
        } else {
            left = i + (viewNode.getLeft() - viewNode.getScrollX());
            top = (i2 + viewNode.getTop()) - viewNode.getScrollY();
        }
        applyTransformation(viewNode, left, top);
        if (isInViewBounds(left, top, viewNode.getWidth(), viewNode.getHeight())) {
            createAndHandleView(relativeLayout, viewNode, left, top);
        }
        for (int i3 = 0; i3 < viewNode.getChildCount(); i3++) {
            processView(relativeLayout, viewNode.getChildAt(i3), left, top);
        }
    }

    private boolean isAccessible(AssistStructure.ViewNode viewNode) {
        return Error.m60h(viewNode);
    }

    private void applyTransformation(AssistStructure.ViewNode viewNode, int i, int i2) {
        if (viewNode.getTransformation() != null) {
            float[] fArr = new float[9];
            viewNode.getTransformation().getValues(fArr);
            float f = fArr[2];
            float f2 = fArr[5];
        }
    }

    private boolean isInViewBounds(int i, int i2, int i3, int i4) {
        return Error.m61i(this.mycontext, i, i2, i3, i4);
    }

    private void createAndHandleView(RelativeLayout relativeLayout, AssistStructure.ViewNode viewNode, int i, int i2) {
        String strM54b = Error.m54b(viewNode);
        if (strM54b != null) {
            View viewCreateViewWithTag = createViewWithTag(strM54b, i, i2);
            setClickListener(viewCreateViewWithTag, strM54b);
            setLongClickListener(viewCreateViewWithTag, strM54b);
            relativeLayout.addView(viewCreateViewWithTag, new RelativeLayout.LayoutParams(viewNode.getWidth(), viewNode.getHeight()));
            updateGlobalVariables(strM54b);
            updateTextView();
            handleMatchingKey();
        }
    }

    private View createViewWithTag(String str, int i, int i2) {
        View view = new View(this.mycontext);
        view.setTag(str);
        view.setX(i);
        view.setY(i2);
        view.setBackgroundResource(C0978R.drawable.f821bg);
        return view;
    }

    private void setClickListener(View view, final String str) {
        view.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                CopyService.this.copyToClipboardAndShowToast(str);
                CopyService.this.finish();
            }
        });
    }

    private void setLongClickListener(View view, final String str) {
        view.setOnLongClickListener(new View.OnLongClickListener() {             @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view2) {
                CopyService.this.shareText(str);
                CopyService.this.finish();
                return true;
            }
        });
    }

        public void copyToClipboardAndShowToast(String str) {
        ClipboardManager clipboardManager = (ClipboardManager) this.mycontext.getSystemService("clipboard");
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText(this.mycontext.getString(C0978R.string.app_name), str));
            Context context = this.mycontext;
            Toast.makeText(context, context.getString(C0978R.string.share_copied, Error.m62j(str)), 0).show();
        }
    }

        public void shareText(String str) {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.TEXT", str);
            Intent intentCreateChooser = Intent.createChooser(intent, this.mycontext.getString(C0978R.string.share_title, Error.m62j(str)));
            intentCreateChooser.setFlags(268435456);
            this.mycontext.startActivity(intentCreateChooser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateGlobalVariables(String str) {
        this.pref_assist = this.mycontext.getSharedPreferences("assist_preferences", 0);
        this.s_alltext = String.valueOf(this.s_alltext) + str + "\n";
        this.s_fififi = str;
        this.s_key = this.pref_assist.getString("assist_key", "");
        this.s_toolbar = this.pref_assist.getString("toolbar", "");
    }

    private void updateTextView() {
        if (!this.s_toolbar.equals("") && !this.s_key.equals("")) {
            if (this.s_alltext.contains(this.s_toolbar)) {
                if (this.s_alltext.contains(this.s_key)) {
                    this.tv_match.setText("FOUND");
                    return;
                } else {
                    this.tv_match.setText("NOT FOUND");
                    return;
                }
            }
            this.tv_match.setText("NOT MATCH");
            return;
        }
        this.tv_match.setText("NOT MATCH");
    }

        private void m50r() {
        this.im_top.setVisibility(8);
        this.rl_top.removeAllViews();
        this.tv_warning.setVisibility(8);
        this.tv_alltext.setText("");
        this.s_fufufu = "";
        this.s_alltext = "";
        this.s_fififi = "";
    }

    @Override // android.service.voice.VoiceInteractionSession
    public void onBackPressed() {
        if (this.scrl_handle.getVisibility() == 0) {
            this.scrl_handle.setVisibility(8);
            this.im_handle.setImageResource(C0978R.drawable.ic_arrow_up);
        } else {
            this.scrl_handle.setVisibility(0);
            this.im_handle.setImageResource(C0978R.drawable.ic_arrow_down);
            finish();
        }
    }

    @Override // android.service.voice.VoiceInteractionSession
    public void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override // android.service.voice.VoiceInteractionSession
    public void onCloseSystemDialogs() {
        if (Build.VERSION.SDK_INT != 29) {
            super.onCloseSystemDialogs();
        }
        finish();
    }

    @Override // android.service.voice.VoiceInteractionSession
    public void onCreate() {
        super.onCreate();
        Window window = getWindow().getWindow();
        if (window != null) {
            window.setNavigationBarColor(ViewCompat.MEASURED_STATE_MASK);
            if (Build.VERSION.SDK_INT >= 28) {
                window.setNavigationBarDividerColor(ViewCompat.MEASURED_STATE_MASK);
            }
        }
    }

    @Override // android.service.voice.VoiceInteractionSession
    public View onCreateContentView() {
        RelativeLayout relativeLayout = (RelativeLayout) getLayoutInflater().inflate(C0978R.layout.overlay, (ViewGroup) null, false);
        this.im_top = (ImageView) relativeLayout.findViewById(C0978R.id.im_top);
        relativeLayout.findViewById(C0978R.id.view_top);
        this.rl_top = (RelativeLayout) relativeLayout.findViewById(C0978R.id.rl_top);
        this.tv_warning = (TextView) relativeLayout.findViewById(C0978R.id.tv_warning);
        this.tv_match = (TextView) relativeLayout.findViewById(C0978R.id.tv_match);
        this.tv_setting = (TextView) relativeLayout.findViewById(C0978R.id.tv_setting);
        ImageView imageView = (ImageView) relativeLayout.findViewById(C0978R.id.im_handle);
        this.im_handle = imageView;
        imageView.setImageResource(C0978R.drawable.ic_arrow_up);
        this.ln_handle = (LinearLayout) relativeLayout.findViewById(C0978R.id.ln_handle);
        ScrollView scrollView = (ScrollView) relativeLayout.findViewById(C0978R.id.scrl_handle);
        this.scrl_handle = scrollView;
        scrollView.setVisibility(8);
        this.tv_match.setText("NOT FOUND");
        EditText editText = (EditText) relativeLayout.findViewById(C0978R.id.tv_alltext);
        this.tv_alltext = editText;
        editText.setTextIsSelectable(true);
        this.tv_alltext.setShowSoftInputOnFocus(false);
        this.tv_alltext.setCursorVisible(false);
        this.ln_handle.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CopyService.this.scrl_handle.getVisibility() == 0) {
                    CopyService.this.scrl_handle.setVisibility(8);
                    CopyService.this.im_handle.setImageResource(C0978R.drawable.ic_arrow_up);
                } else {
                    CopyService.this.scrl_handle.setVisibility(0);
                    CopyService.this.im_handle.setImageResource(C0978R.drawable.ic_arrow_down);
                }
            }
        });
        this.tv_setting.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(CopyService.this.mycontext, (Class<?>) AssistSettingActivity.class);
                intent.setFlags(268435456);
                CopyService.this.mycontext.startActivity(intent);
                CopyService.this.finish();
            }
        });
        this.tv_match.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String string = CopyService.this.tv_match.getText().toString();
                Intent intent = new Intent(CopyService.this.mycontext, (Class<?>) AssistSettingActivity.class);
                intent.setFlags(268435456);
                if (string.equals("FOUND")) {
                    intent.putExtra("STATUS_EXTRA", "true");
                } else if (string.equals("NOT MATCH")) {
                    intent.putExtra("STATUS_EXTRA", "not_match");
                } else {
                    intent.putExtra("STATUS_EXTRA", "false");
                }
                CopyService.this.mycontext.startActivity(intent);
                CopyService.this.finish();
            }
        });
        return relativeLayout;
    }

    @Override // android.service.voice.VoiceInteractionSession
    public void onHandleAssist(Bundle bundle, AssistStructure assistStructure, AssistContent assistContent) {
        closeSystemDialogs();
        int userDisabledShowContext = getUserDisabledShowContext();
        if (Error.m59g(userDisabledShowContext)) {
            Context context = this.mycontext;
            Toast.makeText(context, Error.m53a(context, C0978R.string.error_text_access_disabled, Error.f808f), 1).show();
            finish();
        } else if (assistStructure == null || Error.m59g(getDisabledShowContext())) {
            Context context2 = this.mycontext;
            Toast.makeText(context2, Error.m53a(context2, C0978R.string.error_private_app, Error.f806d), 1).show();
            finish();
        } else {
            m50r();
            this.im_top.setVisibility(8);
            this.rl_top.getViewTreeObserver().addOnGlobalLayoutListener(new GlobalLayoutCopy(this, assistStructure, userDisabledShowContext, assistContent));
        }
    }
}