package com.termfu.widget;

/* JADX INFO: loaded from: classes91.dex */
public class NaturalOrderComparator {
    private static int compareRight(String str, String str2) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            char cCharAt = charAt(str, i);
            char cCharAt2 = charAt(str2, i2);
            if (!isDigit(cCharAt) && !isDigit(cCharAt2)) {
                return i3;
            }
            if (!isDigit(cCharAt)) {
                return -1;
            }
            if (!isDigit(cCharAt2)) {
                return 1;
            }
            if (cCharAt == 0 && cCharAt2 == 0) {
                return i3;
            }
            if (i3 == 0) {
                if (cCharAt < cCharAt2) {
                    i3 = -1;
                } else if (cCharAt > cCharAt2) {
                    i3 = 1;
                }
            }
            i++;
            i2++;
        }
    }

    public static int compare(Object obj, Object obj2) {
        int iCompareRight;
        String string = obj.toString();
        String string2 = obj2.toString();
        int i = 0;
        int i2 = 0;
        while (true) {
            char cCharAt = charAt(string, i);
            char cCharAt2 = charAt(string2, i2);
            int i3 = 0;
            while (true) {
                if (!Character.isSpaceChar(cCharAt) && cCharAt != '0') {
                    break;
                }
                i3 = cCharAt == '0' ? i3 + 1 : 0;
                i++;
                cCharAt = charAt(string, i);
            }
            int i4 = 0;
            while (true) {
                if (!Character.isSpaceChar(cCharAt2) && cCharAt2 != '0') {
                    break;
                }
                i4 = cCharAt2 == '0' ? i4 + 1 : 0;
                i2++;
                cCharAt2 = charAt(string2, i2);
            }
            if (Character.isDigit(cCharAt) && Character.isDigit(cCharAt2) && (iCompareRight = compareRight(string.substring(i), string2.substring(i2))) != 0) {
                return iCompareRight;
            }
            if (cCharAt == 0 && cCharAt2 == 0) {
                return compareEqual(string, string2, i3, i4);
            }
            if (cCharAt < cCharAt2) {
                return -1;
            }
            if (cCharAt > cCharAt2) {
                return 1;
            }
            i++;
            i2++;
        }
    }

    private static boolean isDigit(char c) {
        return Character.isDigit(c) || c == '.' || c == ',';
    }

    private static char charAt(String str, int i) {
        if (i >= str.length()) {
            return (char) 0;
        }
        return Character.toLowerCase(str.charAt(i));
    }

    private static int compareEqual(String str, String str2, int i, int i2) {
        int i3 = i - i2;
        if (i3 != 0) {
            return i3;
        }
        if (str.length() == str2.length()) {
            return str.compareToIgnoreCase(str2);
        }
        return str.length() - str2.length();
    }
}
