package com.termux.terminal;

import java.util.Arrays;

/* JADX INFO: loaded from: classes91.dex */
public final class TerminalRow {
    private static final float SPARE_CAPACITY_FACTOR = 1.5f;
    private final int mColumns;
    boolean mHasNonOneWidthOrSurrogateChars;
    boolean mLineWrap;
    private short mSpaceUsed;
    final long[] mStyle;
    public char[] mText;

    public TerminalRow(int i, long j) {
        this.mColumns = i;
        this.mText = new char[(int) (i * SPARE_CAPACITY_FACTOR)];
        this.mStyle = new long[i];
        clear(j);
    }

    public void copyInterval(TerminalRow terminalRow, int i, int i2, int i3) {
        this.mHasNonOneWidthOrSurrogateChars |= terminalRow.mHasNonOneWidthOrSurrogateChars;
        int iFindStartOfColumn = terminalRow.findStartOfColumn(i);
        int iFindStartOfColumn2 = terminalRow.findStartOfColumn(i2);
        boolean z = i > 0 && terminalRow.wideDisplayCharacterStartingAt(i + (-1));
        char[] cArrCopyOf = terminalRow.mText;
        if (this == terminalRow) {
            cArrCopyOf = Arrays.copyOf(cArrCopyOf, cArrCopyOf.length);
        }
        int i4 = 0;
        while (iFindStartOfColumn < iFindStartOfColumn2) {
            char c = cArrCopyOf[iFindStartOfColumn];
            boolean zIsHighSurrogate = Character.isHighSurrogate(c);
            int codePoint = c;
            if (zIsHighSurrogate) {
                iFindStartOfColumn++;
                codePoint = Character.toCodePoint(c, cArrCopyOf[iFindStartOfColumn]);
            }
            if (z) {
                codePoint = 32;
                z = false;
            }
            int iWidth = WcWidth.width(codePoint);
            if (iWidth > 0) {
                i3 += i4;
                i += i4;
                i4 = iWidth;
            }
            setChar(i3, codePoint, terminalRow.getStyle(i));
            iFindStartOfColumn++;
        }
    }

    public int getSpaceUsed() {
        return this.mSpaceUsed;
    }

    public int findStartOfColumn(int i) {
        if (i == this.mColumns) {
            return getSpaceUsed();
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i2 + 1;
            char c = this.mText[i2];
            boolean zIsHighSurrogate = Character.isHighSurrogate(c);
            int i5 = c;
            if (zIsHighSurrogate) {
                int codePoint = Character.toCodePoint(c, this.mText[i4]);
                i4++;
                i5 = codePoint;
            }
            int iWidth = WcWidth.width(i5);
            if (iWidth > 0) {
                i3 += iWidth;
                if (i3 == i) {
                    while (i4 < this.mSpaceUsed) {
                        if (Character.isHighSurrogate(this.mText[i4])) {
                            char[] cArr = this.mText;
                            if (WcWidth.width(Character.toCodePoint(cArr[i4], cArr[i4 + 1])) > 0) {
                                break;
                            }
                            i4 += 2;
                        } else {
                            if (WcWidth.width(this.mText[i4]) > 0) {
                                break;
                            }
                            i4++;
                        }
                    }
                    return i4;
                }
                if (i3 > i) {
                    return i2;
                }
            }
            i2 = i4;
        }
    }

    private boolean wideDisplayCharacterStartingAt(int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.mSpaceUsed) {
            int i4 = i2 + 1;
            char c = this.mText[i2];
            boolean zIsHighSurrogate = Character.isHighSurrogate(c);
            int i5 = c;
            if (zIsHighSurrogate) {
                int codePoint = Character.toCodePoint(c, this.mText[i4]);
                i4++;
                i5 = codePoint;
            }
            int iWidth = WcWidth.width(i5);
            if (iWidth > 0) {
                if (i3 == i && iWidth == 2) {
                    return true;
                }
                i3 += iWidth;
                if (i3 > i) {
                    return false;
                }
            }
            i2 = i4;
        }
        return false;
    }

    public void clear(long j) {
        Arrays.fill(this.mText, ' ');
        Arrays.fill(this.mStyle, j);
        this.mSpaceUsed = (short) this.mColumns;
        this.mHasNonOneWidthOrSurrogateChars = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setChar(int i, int i2, long j) {
        int i3;
        int iWidth;
        int i4;
        int iFindStartOfColumn;
        int i5;
        this.mStyle[i] = j;
        int iWidth2 = WcWidth.width(i2);
        if (!this.mHasNonOneWidthOrSurrogateChars) {
            if (i2 >= 65536 || iWidth2 != 1) {
                this.mHasNonOneWidthOrSurrogateChars = true;
            } else {
                this.mText[i] = (char) i2;
                return;
            }
        }
        boolean z = iWidth2 <= 0;
        boolean z2 = i > 0 && wideDisplayCharacterStartingAt(i + (-1));
        if (!z) {
            if (z2) {
                setChar(i - 1, 32, j);
            }
            if (iWidth2 == 2 && wideDisplayCharacterStartingAt(i + 1)) {
                setChar(i + 1, 32, j);
            }
        } else {
            if (z2) {
                i3 = i - 1;
            } else {
                i3 = i;
            }
            char[] cArr = this.mText;
            int iFindStartOfColumn2 = findStartOfColumn(i3);
            iWidth = WcWidth.width(cArr, iFindStartOfColumn2);
            i4 = i3 + iWidth;
            if (i4 >= this.mColumns) {
                iFindStartOfColumn = findStartOfColumn(i4);
            } else {
                iFindStartOfColumn = this.mSpaceUsed;
            }
            int i6 = iFindStartOfColumn - iFindStartOfColumn2;
            int iCharCount = Character.charCount(i2);
            if (z) {
                iCharCount += i6;
            }
            int i7 = iFindStartOfColumn2 + i6;
            int i8 = iFindStartOfColumn2 + iCharCount;
            i5 = iCharCount - i6;
            if (i5 <= 0) {
                short s = this.mSpaceUsed;
                int i9 = s - i7;
                if (s + i5 > cArr.length) {
                    char[] cArr2 = new char[cArr.length + this.mColumns];
                    System.arraycopy(cArr, 0, cArr2, 0, i7);
                    System.arraycopy(cArr, i7, cArr2, i8, i9);
                    this.mText = cArr2;
                    cArr = cArr2;
                } else {
                    System.arraycopy(cArr, i7, cArr, i8, i9);
                }
            } else if (i5 < 0) {
                System.arraycopy(cArr, i7, cArr, i8, this.mSpaceUsed - i7);
            }
            this.mSpaceUsed = (short) (this.mSpaceUsed + i5);
            if (!z) {
                i6 = 0;
            }
            Character.toChars(i2, cArr, iFindStartOfColumn2 + i6);
            if (iWidth != 2 && iWidth2 == 1) {
                short s2 = this.mSpaceUsed;
                if (s2 + 1 > cArr.length) {
                    char[] cArr3 = new char[cArr.length + this.mColumns];
                    System.arraycopy(cArr, 0, cArr3, 0, i8);
                    System.arraycopy(cArr, i8, cArr3, i8 + 1, this.mSpaceUsed - i8);
                    this.mText = cArr3;
                    cArr = cArr3;
                } else {
                    System.arraycopy(cArr, i8, cArr, i8 + 1, s2 - i8);
                }
                cArr[i8] = ' ';
                this.mSpaceUsed = (short) (this.mSpaceUsed + 1);
                return;
            }
            if (iWidth == 1 || iWidth2 != 2) {
            }
            int i10 = this.mColumns;
            if (i3 == i10 - 1) {
                throw new IllegalArgumentException("Cannot put wide character in last column");
            }
            if (i3 == i10 - 2) {
                this.mSpaceUsed = (short) i8;
                return;
            }
            int i11 = (Character.isHighSurrogate(this.mText[i8]) ? 2 : 1) + i8;
            System.arraycopy(cArr, i11, cArr, i8, this.mSpaceUsed - i11);
            this.mSpaceUsed = (short) (this.mSpaceUsed - (i11 - i8));
            return;
        }
        i3 = i;
        char[] cArr4 = this.mText;
        int iFindStartOfColumn22 = findStartOfColumn(i3);
        iWidth = WcWidth.width(cArr4, iFindStartOfColumn22);
        i4 = i3 + iWidth;
        if (i4 >= this.mColumns) {
            iFindStartOfColumn = findStartOfColumn(i4);
        } else {
            iFindStartOfColumn = this.mSpaceUsed;
        }
        int i62 = iFindStartOfColumn - iFindStartOfColumn22;
        int iCharCount2 = Character.charCount(i2);
        if (z) {
        }
        int i72 = iFindStartOfColumn22 + i62;
        int i82 = iFindStartOfColumn22 + iCharCount2;
        i5 = iCharCount2 - i62;
        if (i5 <= 0) {
        }
        this.mSpaceUsed = (short) (this.mSpaceUsed + i5);
        if (!z) {
        }
        Character.toChars(i2, cArr4, iFindStartOfColumn22 + i62);
        if (iWidth != 2) {
        }
        if (iWidth == 1) {
        }
    }

    boolean isBlank() {
        int spaceUsed = getSpaceUsed();
        for (int i = 0; i < spaceUsed; i++) {
            if (this.mText[i] != ' ') {
                return false;
            }
        }
        return true;
    }

    public final long getStyle(int i) {
        return this.mStyle[i];
    }
}
