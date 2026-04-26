package com.termfu.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import androidx.core.view.ViewCompat;
import com.termux.terminal.TerminalBuffer;
import com.termux.terminal.TerminalEmulator;
import com.termux.terminal.TerminalRow;
import com.termux.terminal.TextStyle;
import com.termux.terminal.WcWidth;

/* JADX INFO: loaded from: classes91.dex */
public final class TerminalRenderer {
    private final float[] asciiMeasures;
    private final int mFontAscent;
    final int mFontLineSpacing;
    final int mFontLineSpacingAndAscent;
    final float mFontWidth;
    private final Paint mTextPaint;
    final int mTextSize;
    final Typeface mTypeface;

    public TerminalRenderer(int i, Typeface typeface) {
        Paint paint = new Paint();
        this.mTextPaint = paint;
        this.asciiMeasures = new float[127];
        this.mTextSize = i;
        this.mTypeface = typeface;
        paint.setTypeface(typeface);
        paint.setAntiAlias(true);
        paint.setTextSize(i);
        int iCeil = (int) Math.ceil(paint.getFontSpacing());
        this.mFontLineSpacing = iCeil;
        int iCeil2 = (int) Math.ceil(paint.ascent());
        this.mFontAscent = iCeil2;
        this.mFontLineSpacingAndAscent = iCeil + iCeil2;
        this.mFontWidth = paint.measureText("X");
        StringBuilder sb = new StringBuilder(" ");
        for (int i2 = 0; i2 < this.asciiMeasures.length; i2++) {
            sb.setCharAt(0, (char) i2);
            this.asciiMeasures[i2] = this.mTextPaint.measureText(sb, 0, 1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v11, types: [int] */
    /* JADX WARN: Type inference failed for: r7v5, types: [char] */
    /* JADX WARN: Type inference failed for: r7v6, types: [int] */
    public final void render(TerminalEmulator terminalEmulator, Canvas canvas, int i, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        boolean z;
        boolean z2;
        int i13;
        int i14;
        char[] cArr;
        int i15;
        float f;
        boolean z3;
        char[] cArr2;
        TerminalRenderer terminalRenderer = this;
        TerminalEmulator terminalEmulator2 = terminalEmulator;
        int i16 = i2;
        int i17 = i3;
        boolean zIsReverseVideo = terminalEmulator.isReverseVideo();
        int i18 = i + terminalEmulator2.mRows;
        int i19 = terminalEmulator2.mColumns;
        int cursorCol = terminalEmulator.getCursorCol();
        int cursorRow = terminalEmulator.getCursorRow();
        boolean zIsShowingCursor = terminalEmulator.isShowingCursor();
        TerminalBuffer screen = terminalEmulator.getScreen();
        int[] iArr = terminalEmulator2.mColors.mCurrentColors;
        int cursorStyle = terminalEmulator.getCursorStyle();
        if (zIsReverseVideo) {
            canvas.drawColor(iArr[256], PorterDuff.Mode.SRC);
        }
        float f2 = terminalRenderer.mFontLineSpacingAndAscent;
        int i20 = i;
        while (i20 < i18) {
            float f3 = f2 + terminalRenderer.mFontLineSpacing;
            int i21 = (i20 == cursorRow && zIsShowingCursor) ? cursorCol : -1;
            if (i20 < i16 || i20 > i17) {
                i6 = -1;
                i7 = -1;
            } else {
                int i22 = i20 == i16 ? i4 : -1;
                i6 = i20 == i17 ? i5 : terminalEmulator2.mColumns;
                i7 = i22;
            }
            TerminalRow terminalRowAllocateFullLineIfNecessary = screen.allocateFullLineIfNecessary(screen.externalToInternalRow(i20));
            char[] cArr3 = terminalRowAllocateFullLineIfNecessary.mText;
            boolean z4 = false;
            int i23 = i20;
            int spaceUsed = terminalRowAllocateFullLineIfNecessary.getSpaceUsed();
            long j = 0;
            int i24 = 0;
            boolean z5 = false;
            boolean z6 = false;
            int i25 = 0;
            int i26 = -1;
            int i27 = 0;
            float f4 = 0.0f;
            boolean z7 = false;
            while (i24 < i19) {
                int[] iArr2 = iArr;
                TerminalBuffer terminalBuffer = screen;
                int i28 = cursorRow;
                int i29 = i19;
                int i30 = i18;
                char cArr3Char = cArr3[i25];
                int codePoint = cArr3Char;
                boolean zIsHighSurrogate = Character.isHighSurrogate(cArr3Char);
                int i31 = zIsHighSurrogate ? 2 : 1;
                if (zIsHighSurrogate) {
                    codePoint = Character.toCodePoint(cArr3Char, cArr3[i25 + 1]);
                }
                int iWidth = WcWidth.width(codePoint);
                boolean z8 = i21 == i24 || (iWidth == 2 && i21 == i24 + 1);
                boolean z9 = i24 >= i7 && i24 <= i6;
                long style = terminalRowAllocateFullLineIfNecessary.getStyle(i24);
                float[] fArr = terminalRenderer.asciiMeasures;
                TerminalRow terminalRow = terminalRowAllocateFullLineIfNecessary;
                float fMeasureText = codePoint < fArr.length ? fArr[codePoint] : terminalRenderer.mTextPaint.measureText(cArr3, i25, i31);
                int i32 = i6;
                boolean z10 = ((double) Math.abs((fMeasureText / terminalRenderer.mFontWidth) - ((float) iWidth))) > 0.01d;
                if (style == j && z8 == z5 && z9 == z6 && !z10 && !z7) {
                    i8 = spaceUsed;
                    i9 = i24;
                    i10 = i7;
                    i11 = i21;
                    i12 = i32;
                    cArr = cArr3;
                    i15 = i25;
                    f = f4;
                    style = j;
                    z10 = z7;
                    i13 = iWidth;
                    i14 = i31;
                } else {
                    if (i24 != 0) {
                        int i33 = i24 - i26;
                        int i34 = i25 - i27;
                        int i35 = z5 ? terminalEmulator.mColors.mCurrentColors[258] : 0;
                        if (zIsReverseVideo || z6) {
                            i9 = i24;
                            z3 = true;
                        } else {
                            i9 = i24;
                            z3 = false;
                        }
                        i12 = i32;
                        i10 = i7;
                        i11 = i21;
                        int i36 = i26;
                        int i37 = i27;
                        z = z9;
                        z2 = z8;
                        float f5 = f4;
                        i13 = iWidth;
                        int i38 = i35;
                        i14 = i31;
                        i8 = spaceUsed;
                        i15 = i25;
                        cArr = cArr3;
                        drawTextRun(canvas, cArr3, iArr2, f3, i36, i33, i37, i34, f5, i38, cursorStyle, j, z3);
                    } else {
                        i8 = spaceUsed;
                        i9 = i24;
                        i10 = i7;
                        i11 = i21;
                        i12 = i32;
                        z = z9;
                        z2 = z8;
                        i13 = iWidth;
                        i14 = i31;
                        cArr = cArr3;
                        i15 = i25;
                    }
                    z6 = z;
                    z5 = z2;
                    i27 = i15;
                    i26 = i9;
                    f = 0.0f;
                }
                float f6 = f + fMeasureText;
                int i39 = i9 + i13;
                i25 = i15 + i14;
                while (true) {
                    cArr2 = cArr;
                    if (i25 >= i8 || WcWidth.width(cArr2, i25) > 0) {
                        break;
                    }
                    i25 += Character.isHighSurrogate(cArr2[i25]) ? 2 : 1;
                    cArr = cArr2;
                }
                terminalEmulator2 = terminalEmulator;
                f4 = f6;
                i24 = i39;
                cArr3 = cArr2;
                spaceUsed = i8;
                iArr = iArr2;
                screen = terminalBuffer;
                cursorRow = i28;
                i19 = i29;
                i18 = i30;
                j = style;
                terminalRowAllocateFullLineIfNecessary = terminalRow;
                z7 = z10;
                i6 = i12;
                i7 = i10;
                i21 = i11;
                terminalRenderer = this;
            }
            int i40 = i19 - i26;
            int i41 = i25 - i27;
            int i42 = z5 ? terminalEmulator2.mColors.mCurrentColors[258] : 0;
            if (zIsReverseVideo || z6) {
                z4 = true;
            }
            drawTextRun(canvas, cArr3, iArr, f3, i26, i40, i27, i41, f4, i42, cursorStyle, j, z4);
            i20 = i23 + 1;
            terminalEmulator2 = terminalEmulator;
            i16 = i2;
            i17 = i3;
            f2 = f3;
            iArr = iArr;
            screen = screen;
            cursorRow = cursorRow;
            i19 = i19;
            i18 = i18;
        }
    }

    private void drawTextRun(Canvas canvas, char[] cArr, int[] iArr, float f, int i, int i2, int i3, int i4, float f2, int i5, int i6, long j, boolean z) {
        int i7;
        float f3;
        float f4;
        boolean z2;
        int i8;
        boolean z3;
        int i9;
        float f5;
        int iDecodeForeColor = TextStyle.decodeForeColor(j);
        int iDecodeEffect = TextStyle.decodeEffect(j);
        int iDecodeBackColor = TextStyle.decodeBackColor(j);
        boolean z4 = (iDecodeEffect & 9) != 0;
        boolean z5 = (iDecodeEffect & 4) != 0;
        boolean z6 = (iDecodeEffect & 2) != 0;
        boolean z7 = (iDecodeEffect & 64) != 0;
        boolean z8 = (iDecodeEffect & 256) != 0;
        if ((iDecodeForeColor & ViewCompat.MEASURED_STATE_MASK) != -16777216) {
            if (z4 && iDecodeForeColor >= 0 && iDecodeForeColor < 8) {
                iDecodeForeColor += 8;
            }
            iDecodeForeColor = iArr[iDecodeForeColor];
        }
        if ((iDecodeBackColor & ViewCompat.MEASURED_STATE_MASK) != -16777216) {
            iDecodeBackColor = iArr[iDecodeBackColor];
        }
        if (z ^ ((iDecodeEffect & 16) != 0)) {
            i7 = iDecodeBackColor;
        } else {
            i7 = iDecodeForeColor;
            iDecodeForeColor = iDecodeBackColor;
        }
        float f6 = this.mFontWidth;
        float f7 = i * f6;
        float f8 = i2;
        float f9 = f7 + (f8 * f6);
        float f10 = f2 / f6;
        boolean z9 = z4;
        if (Math.abs(f10 - f8) > 0.01d) {
            canvas.save();
            canvas.scale(f8 / f10, 1.0f);
            float f11 = f10 / f8;
            f3 = f7 * f11;
            f4 = f9 * f11;
            z2 = true;
        } else {
            f3 = f7;
            f4 = f9;
            z2 = false;
        }
        if (iDecodeForeColor != iArr[257]) {
            this.mTextPaint.setColor(iDecodeForeColor);
            Paint paint = this.mTextPaint;
            i8 = i7;
            z3 = z2;
            i9 = ViewCompat.MEASURED_STATE_MASK;
            f5 = f4;
            canvas.drawRect(f3, (f - this.mFontLineSpacingAndAscent) + this.mFontAscent, f4, f, paint);
        } else {
            i8 = i7;
            z3 = z2;
            i9 = ViewCompat.MEASURED_STATE_MASK;
            f5 = f4;
        }
        if (i5 != 0) {
            this.mTextPaint.setColor(i5);
            float f12 = this.mFontLineSpacingAndAscent - this.mFontAscent;
            if (i6 == 1) {
                f12 = (float) (((double) f12) / 4.0d);
            } else if (i6 == 2) {
                f5 = (float) (((double) f5) - (((double) ((f5 - f3) * 3.0f)) / 4.0d));
            }
            canvas.drawRect(f3, f - f12, f5, f, this.mTextPaint);
        }
        if ((iDecodeEffect & 32) == 0) {
            int i10 = z8 ? (((((i8 >> 16) & 255) * 2) / 3) << 16) + i9 + (((((i8 >> 8) & 255) * 2) / 3) << 8) + (((i8 & 255) * 2) / 3) : i8;
            this.mTextPaint.setFakeBoldText(z9);
            this.mTextPaint.setUnderlineText(z5);
            this.mTextPaint.setTextSkewX(z6 ? -0.35f : 0.0f);
            this.mTextPaint.setStrikeThruText(z7);
            this.mTextPaint.setColor(i10);
            canvas.drawText(cArr, i3, i4, f3, f - this.mFontLineSpacingAndAscent, this.mTextPaint);
        }
        if (z3) {
            canvas.restore();
        }
    }
}
