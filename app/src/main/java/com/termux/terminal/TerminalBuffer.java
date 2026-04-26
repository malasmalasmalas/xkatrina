package com.termux.terminal;

import java.util.Arrays;

/* JADX INFO: loaded from: classes91.dex */
public final class TerminalBuffer {
    int mColumns;
    TerminalRow[] mLines;
    int mScreenRows;
    int mTotalRows;
    private int mActiveTranscriptRows = 0;
    private int mScreenFirstRow = 0;

    public TerminalBuffer(int i, int i2, int i3) {
        this.mColumns = i;
        this.mTotalRows = i2;
        this.mScreenRows = i3;
        this.mLines = new TerminalRow[i2];
        blockSet(0, 0, i, i3, 32, TextStyle.NORMAL);
    }

    public String getTranscriptText() {
        return getSelectedText(0, -getActiveTranscriptRows(), this.mColumns, this.mScreenRows).trim();
    }

    public String getTranscriptTextWithoutJoinedLines() {
        return getSelectedText(0, -getActiveTranscriptRows(), this.mColumns, this.mScreenRows, false).trim();
    }

    public String getTranscriptTextWithFullLinesJoined() {
        return getSelectedText(0, -getActiveTranscriptRows(), this.mColumns, this.mScreenRows, true, true).trim();
    }

    public String getSelectedText(int i, int i2, int i3, int i4) {
        return getSelectedText(i, i2, i3, i4, true);
    }

    public String getSelectedText(int i, int i2, int i3, int i4, boolean z) {
        return getSelectedText(i, i2, i3, i4, true, false);
    }

    public String getSelectedText(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        int i5;
        int i6;
        StringBuilder sb = new StringBuilder();
        int i7 = this.mColumns;
        int i8 = i2 < (-getActiveTranscriptRows()) ? -getActiveTranscriptRows() : i2;
        int i9 = this.mScreenRows;
        int i10 = 1;
        int i11 = i4 >= i9 ? i9 - 1 : i4;
        int i12 = i8;
        while (i12 <= i11) {
            int i13 = i12 == i8 ? i : 0;
            if (i12 != i11 || (i5 = i3 + 1) > i7) {
                i5 = i7;
            }
            TerminalRow terminalRow = this.mLines[externalToInternalRow(i12)];
            int iFindStartOfColumn = terminalRow.findStartOfColumn(i13);
            int iFindStartOfColumn2 = i5 < this.mColumns ? terminalRow.findStartOfColumn(i5) : terminalRow.getSpaceUsed();
            if (iFindStartOfColumn2 == iFindStartOfColumn) {
                iFindStartOfColumn2 = terminalRow.findStartOfColumn(i5 + 1);
            }
            char[] cArr = terminalRow.mText;
            boolean lineWrap = getLineWrap(i12);
            if (lineWrap && i5 == i7) {
                i6 = iFindStartOfColumn2 - 1;
            } else {
                int i14 = iFindStartOfColumn;
                i6 = -1;
                while (i14 < iFindStartOfColumn2) {
                    if (cArr[i14] != ' ') {
                        i6 = i14;
                    }
                    i14++;
                    i10 = 1;
                }
            }
            if (i6 != -1) {
                sb.append(cArr, iFindStartOfColumn, (i6 - iFindStartOfColumn) + i10);
            }
            boolean z3 = i6 == iFindStartOfColumn2 + (-1);
            if ((!z || !lineWrap) && ((!z2 || !z3) && i12 < i11 && i12 < this.mScreenRows - i10)) {
                sb.append('\n');
            }
            i12++;
        }
        return sb.toString();
    }

    public int getActiveTranscriptRows() {
        return this.mActiveTranscriptRows;
    }

    public int getActiveRows() {
        return this.mActiveTranscriptRows + this.mScreenRows;
    }

    public int externalToInternalRow(int i) {
        if (i < (-this.mActiveTranscriptRows) || i > this.mScreenRows) {
            throw new IllegalArgumentException("extRow=" + i + ", mScreenRows=" + this.mScreenRows + ", mActiveTranscriptRows=" + this.mActiveTranscriptRows);
        }
        int i2 = this.mScreenFirstRow + i;
        int i3 = this.mTotalRows;
        return i2 < 0 ? i3 + i2 : i2 % i3;
    }

    public void setLineWrap(int i) {
        this.mLines[externalToInternalRow(i)].mLineWrap = true;
    }

    public boolean getLineWrap(int i) {
        return this.mLines[externalToInternalRow(i)].mLineWrap;
    }

    public void clearLineWrap(int i) {
        this.mLines[externalToInternalRow(i)].mLineWrap = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:138:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0151 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x015d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void resize(int i, int i2, int i3, int[] iArr, long j, boolean z) {
        TerminalRow[] terminalRowArr = null;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7;
        boolean z2;
        int i8;
        int spaceUsed;
        int i9 = 0;
        boolean z3 = false;
        int i10;
        long j2;
        int i11;
        int i12;
        int i13;
        int i14;
        int codePoint;
        int i15;
        int i16;
        int i17;
        int iMax;
        char c = 1;
        int i18 = 0;
        if (i == this.mColumns && i2 <= this.mTotalRows) {
            int i19 = this.mScreenRows;
            int i20 = i19 - i2;
            if (i20 > 0 && i20 < i19) {
                for (int i21 = i19 - 1; i21 > 0 && iArr[1] < i21; i21--) {
                    TerminalRow terminalRow = this.mLines[externalToInternalRow(i21)];
                    if ((terminalRow == null || terminalRow.isBlank()) && i20 - 1 == 0) {
                        break;
                    }
                }
            } else if (i20 < 0 && i20 != (iMax = Math.max(i20, -this.mActiveTranscriptRows))) {
                for (int i22 = 0; i22 < iMax - i20; i22++) {
                    allocateFullLineIfNecessary(((this.mScreenFirstRow + this.mScreenRows) + i22) % this.mTotalRows).clear(j);
                }
                i20 = iMax;
            }
            int i23 = this.mScreenFirstRow + i20;
            this.mScreenFirstRow = i23;
            int i24 = this.mTotalRows;
            this.mScreenFirstRow = i23 < 0 ? i23 + i24 : i23 % i24;
            this.mTotalRows = i3;
            this.mActiveTranscriptRows = z ? 0 : Math.max(0, this.mActiveTranscriptRows + i20);
            iArr[1] = iArr[1] - i20;
            this.mScreenRows = i2;
        } else {
            TerminalRow[] terminalRowArr2 = this.mLines;
            this.mLines = new TerminalRow[i3];
            int i25 = 0;
            while (i25 < i3) {
                this.mLines[i25] = new TerminalRow(i, j);
                i25++;
                c = 1;
                i18 = 0;
            }
            int i26 = this.mActiveTranscriptRows;
            int i27 = this.mScreenFirstRow;
            int i28 = this.mScreenRows;
            int i29 = this.mTotalRows;
            this.mTotalRows = i3;
            this.mScreenRows = i2;
            this.mScreenFirstRow = i18;
            this.mActiveTranscriptRows = i18;
            this.mColumns = i;
            int i30 = iArr[c];
            int i31 = iArr[i18];
            int i32 = -1;
            int i33 = -i26;
            int i34 = -1;
            boolean z4 = false;
            int i35 = 0;
            int i36 = 0;
            int i37 = 0;
            while (i33 < i28) {
                int i38 = i27 + i33;
                TerminalRow terminalRow2 = terminalRowArr2[i38 < 0 ? i29 + i38 : i38 % i29];
                boolean z5 = i33 == i30;
                if (terminalRow2 == null || ((z4 || !z5) && terminalRow2.isBlank())) {
                    terminalRowArr = terminalRowArr2;
                    i4 = i27;
                    i5 = i31;
                    i6 = i33;
                    i35++;
                    i34 = i34;
                    i32 = i32;
                    z4 = z4;
                } else {
                    int i39 = i34;
                    if (i35 > 0) {
                        int i40 = i36;
                        int i41 = 0;
                        while (i41 < i35) {
                            int i42 = i32;
                            int i43 = this.mScreenRows;
                            boolean z6 = z4;
                            if (i40 == i43 - 1) {
                                scrollDownOneLine(0, i43, j);
                            } else {
                                i40++;
                            }
                            i41++;
                            i32 = i42;
                            z4 = z6;
                            i37 = 0;
                        }
                        i7 = i32;
                        z2 = z4;
                        i36 = i40;
                        i8 = 0;
                    } else {
                        i7 = i32;
                        z2 = z4;
                        i8 = i35;
                    }
                    if (z5 || terminalRow2.mLineWrap) {
                        spaceUsed = terminalRow2.getSpaceUsed();
                        if (z5) {
                            i9 = spaceUsed;
                            z3 = true;
                        }
                        int i44 = i39;
                        boolean z7 = z2;
                        i10 = i8;
                        int i45 = 0;
                        int i46 = i36;
                        int i47 = i7;
                        j2 = 0;
                        i11 = i46;
                        i12 = 0;
                        while (true) {
                            if (i12 < i9) {
                                i13 = i11;
                                i34 = i44;
                                terminalRowArr = terminalRowArr2;
                                i4 = i27;
                                z4 = z7;
                                i5 = i31;
                                i6 = i33;
                                break;
                            }
                            int i48 = i9;
                            char c2 = terminalRow2.mText[i12];
                            if (Character.isHighSurrogate(c2)) {
                                i14 = i31;
                                i12++;
                                codePoint = Character.toCodePoint(c2, terminalRow2.mText[i12]);
                            } else {
                                i14 = i31;
                                codePoint = c2;
                            }
                            int i49 = i12;
                            int iWidth = WcWidth.width(codePoint);
                            long style = iWidth > 0 ? terminalRow2.getStyle(i45) : j2;
                            if (i37 + iWidth > this.mColumns) {
                                setLineWrap(i11);
                                int i50 = this.mScreenRows;
                                if (i11 == i50 - 1) {
                                    if (z7) {
                                        i44--;
                                    }
                                    scrollDownOneLine(0, i50, j);
                                } else {
                                    i11++;
                                }
                                i17 = i11;
                                i15 = i44;
                                i16 = 0;
                            } else {
                                i15 = i44;
                                i16 = i37;
                                i17 = i11;
                            }
                            terminalRowArr = terminalRowArr2;
                            i5 = i14;
                            i4 = i27;
                            i6 = i33;
                            setChar(i16 - ((iWidth > 0 || i16 <= 0) ? 0 : 1), i17, codePoint, style);
                            if (iWidth > 0) {
                                if (i30 == i6 && i5 == i45) {
                                    i44 = i17;
                                    i47 = i16;
                                    z7 = true;
                                } else {
                                    i44 = i15;
                                }
                                i45 += iWidth;
                                i16 += iWidth;
                                if (z3 && z7) {
                                    i34 = i44;
                                    i13 = i17;
                                    z4 = z7;
                                    i37 = i16;
                                    break;
                                }
                            } else {
                                i44 = i15;
                            }
                            i12 = i49 + 1;
                            i31 = i5;
                            i33 = i6;
                            i11 = i17;
                            i9 = i48;
                            i27 = i4;
                            j2 = style;
                            i37 = i16;
                            terminalRowArr2 = terminalRowArr;
                        }
                        if (i6 != i28 - 1 || terminalRow2.mLineWrap) {
                            i35 = i10;
                        } else {
                            int i51 = this.mScreenRows;
                            if (i13 == i51 - 1) {
                                if (z4) {
                                    i34--;
                                }
                                scrollDownOneLine(0, i51, j);
                            } else {
                                i13++;
                            }
                            i35 = i10;
                            i37 = 0;
                        }
                        int i52 = i47;
                        i36 = i13;
                        i32 = i52;
                    } else {
                        spaceUsed = 0;
                        for (int i53 = 0; i53 < terminalRow2.getSpaceUsed(); i53++) {
                            if (terminalRow2.mText[i53] != ' ') {
                                spaceUsed = i53 + 1;
                            }
                        }
                    }
                    i9 = spaceUsed;
                    z3 = false;
                }
                i33 = i6 + 1;
                i31 = i5;
                i27 = i4;
                terminalRowArr2 = terminalRowArr;
                c = 1;
                i18 = 0;
            }
            iArr[i18] = i32;
            iArr[c] = i34;
        }
        if (iArr[i18] < 0 || iArr[c] < 0) {
            iArr[c] = i18;
            iArr[i18] = i18;
        }
    }

    private void blockCopyLinesDown(int i, int i2) {
        if (i2 == 0) {
            return;
        }
        int i3 = this.mTotalRows;
        int i4 = i2 - 1;
        TerminalRow terminalRow = this.mLines[((i + i4) + 1) % i3];
        while (i4 >= 0) {
            TerminalRow[] terminalRowArr = this.mLines;
            int i5 = i + i4;
            terminalRowArr[(i5 + 1) % i3] = terminalRowArr[i5 % i3];
            i4--;
        }
        this.mLines[i % i3] = terminalRow;
    }

    public void scrollDownOneLine(int i, int i2, long j) {
        int i3 = i2 - 1;
        if (i > i3 || i < 0 || i2 > this.mScreenRows) {
            throw new IllegalArgumentException("topMargin=" + i + ", bottomMargin=" + i2 + ", mScreenRows=" + this.mScreenRows);
        }
        blockCopyLinesDown(this.mScreenFirstRow, i);
        blockCopyLinesDown(externalToInternalRow(i2), this.mScreenRows - i2);
        int i4 = this.mScreenFirstRow + 1;
        int i5 = this.mTotalRows;
        this.mScreenFirstRow = i4 % i5;
        int i6 = this.mActiveTranscriptRows;
        if (i6 < i5 - this.mScreenRows) {
            this.mActiveTranscriptRows = i6 + 1;
        }
        int iExternalToInternalRow = externalToInternalRow(i3);
        TerminalRow[] terminalRowArr = this.mLines;
        TerminalRow terminalRow = terminalRowArr[iExternalToInternalRow];
        if (terminalRow == null) {
            terminalRowArr[iExternalToInternalRow] = new TerminalRow(this.mColumns, j);
        } else {
            terminalRow.clear(j);
        }
    }

    public void blockCopy(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        if (i3 == 0) {
            return;
        }
        if (i >= 0 && (i7 = i + i3) <= (i8 = this.mColumns) && i2 >= 0) {
            int i9 = i2 + i4;
            int i10 = this.mScreenRows;
            if (i9 <= i10 && i5 >= 0 && i3 + i5 <= i8 && i6 >= 0 && i6 + i4 <= i10) {
                boolean z = i2 > i6;
                for (int i11 = 0; i11 < i4; i11++) {
                    int i12 = z ? i11 : i4 - (i11 + 1);
                    allocateFullLineIfNecessary(externalToInternalRow(i12 + i6)).copyInterval(allocateFullLineIfNecessary(externalToInternalRow(i2 + i12)), i, i7, i5);
                }
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public void blockSet(int i, int i2, int i3, int i4, int i5, long j) {
        if (i >= 0 && i + i3 <= this.mColumns && i2 >= 0 && i2 + i4 <= this.mScreenRows) {
            for (int i6 = 0; i6 < i4; i6++) {
                for (int i7 = 0; i7 < i3; i7++) {
                    setChar(i + i7, i2 + i6, i5, j);
                }
            }
            return;
        }
        throw new IllegalArgumentException("Illegal arguments! blockSet(" + i + ", " + i2 + ", " + i3 + ", " + i4 + ", " + i5 + ", " + this.mColumns + ", " + this.mScreenRows + ")");
    }

    public TerminalRow allocateFullLineIfNecessary(int i) {
        TerminalRow[] terminalRowArr = this.mLines;
        TerminalRow terminalRow = terminalRowArr[i];
        if (terminalRow != null) {
            return terminalRow;
        }
        TerminalRow terminalRow2 = new TerminalRow(this.mColumns, 0L);
        terminalRowArr[i] = terminalRow2;
        return terminalRow2;
    }

    public void setChar(int i, int i2, int i3, long j) {
        if (i2 >= this.mScreenRows || i >= this.mColumns) {
            throw new IllegalArgumentException("row=" + i2 + ", column=" + i + ", mScreenRows=" + this.mScreenRows + ", mColumns=" + this.mColumns);
        }
        allocateFullLineIfNecessary(externalToInternalRow(i2)).setChar(i, i3, j);
    }

    public long getStyleAt(int i, int i2) {
        return allocateFullLineIfNecessary(externalToInternalRow(i)).getStyle(i2);
    }

    public void setOrClearEffect(int i, boolean z, boolean z2, boolean z3, int i2, int i3, int i4, int i5, int i6, int i7) {
        int i8 = i4;
        while (i8 < i6) {
            TerminalRow terminalRow = this.mLines[externalToInternalRow(i8)];
            int i9 = (z3 || i8 + 1 == i6) ? i7 : i3;
            for (int i10 = (z3 || i8 == i4) ? i5 : i2; i10 < i9; i10++) {
                long style = terminalRow.getStyle(i10);
                int iDecodeForeColor = TextStyle.decodeForeColor(style);
                int iDecodeBackColor = TextStyle.decodeBackColor(style);
                int iDecodeEffect = TextStyle.decodeEffect(style);
                terminalRow.mStyle[i10] = TextStyle.encode(iDecodeForeColor, iDecodeBackColor, z2 ? ((~iDecodeEffect) & i) | ((~i) & iDecodeEffect) : z ? iDecodeEffect | i : iDecodeEffect & (~i));
            }
            i8++;
        }
    }

    public void clearTranscript() {
        int i = this.mScreenFirstRow;
        int i2 = this.mActiveTranscriptRows;
        if (i < i2) {
            TerminalRow[] terminalRowArr = this.mLines;
            int i3 = this.mTotalRows;
            Arrays.fill(terminalRowArr, (i + i3) - i2, i3, (Object) null);
            Arrays.fill(this.mLines, 0, this.mScreenFirstRow, (Object) null);
        } else {
            Arrays.fill(this.mLines, i - i2, i, (Object) null);
        }
        this.mActiveTranscriptRows = 0;
    }
}
