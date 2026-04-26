package com.termfu.app;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes91.dex */
public class ExtraKeysInfos {
    private ExtraKeyButton[][] buttons;
    private String style;
    static final CharDisplayMap classicArrowsDisplay = new CharDisplayMap() { // from class: com.termfu.app.ExtraKeysInfos.1
        {
            put("LEFT", "←");
            put("RIGHT", "→");
            put("UP", "↑");
            put("DOWN", "↓");
        }
    };
    static final CharDisplayMap wellKnownCharactersDisplay = new CharDisplayMap() { // from class: com.termfu.app.ExtraKeysInfos.2
        {
            put("ENTER", "↲");
            put("TAB", "↹");
            put("BKSP", "⌫");
            put("DEL", "⌦");
            put("DRAWER", "☰");
            put("KEYBOARD", "⌨");
        }
    };
    static final CharDisplayMap lessKnownCharactersDisplay = new CharDisplayMap() { // from class: com.termfu.app.ExtraKeysInfos.3
        {
            put("HOME", "⇱");
            put("END", "⇲");
            put("PGUP", "⇑");
            put("PGDN", "⇓");
        }
    };
    static final CharDisplayMap arrowTriangleVariationDisplay = new CharDisplayMap() { // from class: com.termfu.app.ExtraKeysInfos.4
        {
            put("LEFT", "◀");
            put("RIGHT", "▶");
            put("UP", "▲");
            put("DOWN", "▼");
        }
    };
    static final CharDisplayMap notKnownIsoCharacters = new CharDisplayMap() { // from class: com.termfu.app.ExtraKeysInfos.5
        {
            put("CTRL", "⎈");
            put("ALT", "⎇");
            put("ESC", "⎋");
        }
    };
    static final CharDisplayMap nicerLookingDisplay = new CharDisplayMap() { // from class: com.termfu.app.ExtraKeysInfos.6
        {
            put("-", "―");
        }
    };
    private static final CharDisplayMap defaultCharDisplay = new CharDisplayMap() { // from class: com.termfu.app.ExtraKeysInfos.7
        {
            putAll(ExtraKeysInfos.classicArrowsDisplay);
            putAll(ExtraKeysInfos.wellKnownCharactersDisplay);
            putAll(ExtraKeysInfos.nicerLookingDisplay);
        }
    };
    private static final CharDisplayMap lotsOfArrowsCharDisplay = new CharDisplayMap() { // from class: com.termfu.app.ExtraKeysInfos.8
        {
            putAll(ExtraKeysInfos.classicArrowsDisplay);
            putAll(ExtraKeysInfos.wellKnownCharactersDisplay);
            putAll(ExtraKeysInfos.lessKnownCharactersDisplay);
            putAll(ExtraKeysInfos.nicerLookingDisplay);
        }
    };
    private static final CharDisplayMap arrowsOnlyCharDisplay = new CharDisplayMap() { // from class: com.termfu.app.ExtraKeysInfos.9
        {
            putAll(ExtraKeysInfos.classicArrowsDisplay);
            putAll(ExtraKeysInfos.nicerLookingDisplay);
        }
    };
    private static final CharDisplayMap fullIsoCharDisplay = new CharDisplayMap() { // from class: com.termfu.app.ExtraKeysInfos.10
        {
            putAll(ExtraKeysInfos.classicArrowsDisplay);
            putAll(ExtraKeysInfos.wellKnownCharactersDisplay);
            putAll(ExtraKeysInfos.lessKnownCharactersDisplay);
            putAll(ExtraKeysInfos.nicerLookingDisplay);
            putAll(ExtraKeysInfos.notKnownIsoCharacters);
        }
    };
    private static final CharDisplayMap controlCharsAliases = new CharDisplayMap() { // from class: com.termfu.app.ExtraKeysInfos.11
        {
            put("ESCAPE", "ESC");
            put("CONTROL", "CTRL");
            put("RETURN", "ENTER");
            put("FUNCTION", "FN");
            put("LT", "LEFT");
            put("RT", "RIGHT");
            put("DN", "DOWN");
            put("PAGEUP", "PGUP");
            put("PAGE_UP", "PGUP");
            put("PAGE UP", "PGUP");
            put("PAGE-UP", "PGUP");
            put("PAGEDOWN", "PGDN");
            put("PAGE_DOWN", "PGDN");
            put("PAGE-DOWN", "PGDN");
            put("DELETE", "DEL");
            put("BACKSPACE", "BKSP");
            put("BACKSLASH", "\\");
            put("QUOTE", "\"");
            put("APOSTROPHE", "'");
        }
    };

    public ExtraKeysInfos(String str, String str2) throws JSONException {
        ExtraKeyButton extraKeyButton;
        this.style = str2;
        JSONArray jSONArray = new JSONArray(str);
        int length = jSONArray.length();
        Object[][] objArr = new Object[length][];
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONArray jSONArray2 = jSONArray.getJSONArray(i);
            objArr[i] = new Object[jSONArray2.length()];
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                objArr[i][i2] = jSONArray2.get(i2);
            }
        }
        this.buttons = new ExtraKeyButton[length][];
        for (int i3 = 0; i3 < length; i3++) {
            this.buttons[i3] = new ExtraKeyButton[objArr[i3].length];
            int i4 = 0;
            while (true) {
                Object[] objArr2 = objArr[i3];
                if (i4 >= objArr2.length) {
                    break;
                }
                JSONObject jSONObjectNormalizeKeyConfig = normalizeKeyConfig(objArr2[i4]);
                if (!jSONObjectNormalizeKeyConfig.has("popup")) {
                    extraKeyButton = new ExtraKeyButton(getSelectedCharMap(), jSONObjectNormalizeKeyConfig);
                } else {
                    extraKeyButton = new ExtraKeyButton(getSelectedCharMap(), jSONObjectNormalizeKeyConfig, new ExtraKeyButton(getSelectedCharMap(), normalizeKeyConfig(jSONObjectNormalizeKeyConfig.get("popup"))));
                }
                this.buttons[i3][i4] = extraKeyButton;
                i4++;
            }
        }
    }

    private static JSONObject normalizeKeyConfig(Object obj) throws JSONException {
        if (obj instanceof String) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("key", obj);
            return jSONObject;
        }
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        throw new JSONException("An key in the extra-key matrix must be a string or an object");
    }

    public ExtraKeyButton[][] getMatrix() {
        return this.buttons;
    }

    static class CleverMap<K, V> extends HashMap<K, V> {
        CleverMap() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public V get(K k, V v) {
            return containsKey(k) ? get(k) : v;
        }
    }

    static class CharDisplayMap extends CleverMap<String, String> {
        CharDisplayMap() {
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    CharDisplayMap getSelectedCharMap() {
        String str = this.style;
        switch (str.hashCode()) {
            case -1713232162:
                if (str.equals("arrows-all")) {
                    return lotsOfArrowsCharDisplay;
                }
                break;
            case -1570170353:
                if (str.equals("arrows-only")) {
                    return arrowsOnlyCharDisplay;
                }
                break;
            case 96673:
                if (str.equals("all")) {
                    return fullIsoCharDisplay;
                }
                break;
            case 3387192:
                if (str.equals("none")) {
                    return new CharDisplayMap();
                }
                break;
        }
        return defaultCharDisplay;
    }

    public static String replaceAlias(String str) {
        return controlCharsAliases.get(str, str);
    }
}
