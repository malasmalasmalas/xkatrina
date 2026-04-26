package com.termfu.app;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.termfu.app.ExtraKeysInfos;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ExtraKeysInfos.java */
/* JADX INFO: loaded from: classes91.dex */
class ExtraKeyButton {
    private String display;
    private String key;
    private boolean macro;

    @Nullable
    private ExtraKeyButton popup;

    public ExtraKeyButton(ExtraKeysInfos.CharDisplayMap charDisplayMap, JSONObject jSONObject) throws JSONException {
        this(charDisplayMap, jSONObject, null);
    }

    public ExtraKeyButton(final ExtraKeysInfos.CharDisplayMap charDisplayMap, JSONObject jSONObject, ExtraKeyButton extraKeyButton) throws JSONException {
        String[] strArrSplit;
        this.popup = null;
        String strOptString = jSONObject.optString("key", null);
        String strOptString2 = jSONObject.optString("macro", null);
        if (strOptString != null && strOptString2 != null) {
            throw new JSONException("Both key and macro can't be set for the same key");
        }
        if (strOptString != null) {
            strArrSplit = new String[]{strOptString};
            this.macro = false;
        } else if (strOptString2 != null) {
            strArrSplit = strOptString2.split(" ");
            this.macro = true;
        } else {
            throw new JSONException("All keys have to specify either key or macro");
        }
        for (int i = 0; i < strArrSplit.length; i++) {
            strArrSplit[i] = ExtraKeysInfos.replaceAlias(strArrSplit[i]);
        }
        this.key = TextUtils.join(" ", strArrSplit);
        String strOptString3 = jSONObject.optString("display", null);
        if (strOptString3 != null) {
            this.display = strOptString3;
        } else {
            this.display = (String) Arrays.stream(strArrSplit).map(new Function() { // from class: com.termfu.app.ExtraKeyButton$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String str = (String) obj;
                    return charDisplayMap.get(str, str);
                }
            }).collect(Collectors.joining(" "));
        }
        this.popup = extraKeyButton;
    }

    public String getKey() {
        return this.key;
    }

    public boolean isMacro() {
        return this.macro;
    }

    public String getDisplay() {
        return this.display;
    }

    @Nullable
    public ExtraKeyButton getPopup() {
        return this.popup;
    }
}
