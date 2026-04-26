package com.termux.terminal;

import android.util.Log;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Stack;

/* JADX INFO: loaded from: classes91.dex */
public final class TerminalEmulator {
    public static final int CURSOR_STYLE_BAR = 2;
    public static final int CURSOR_STYLE_BLOCK = 0;
    public static final int CURSOR_STYLE_UNDERLINE = 1;
    private static final int DECSET_BIT_APPLICATION_CURSOR_KEYS = 1;
    private static final int DECSET_BIT_APPLICATION_KEYPAD = 32;
    private static final int DECSET_BIT_AUTOWRAP = 8;
    private static final int DECSET_BIT_BRACKETED_PASTE_MODE = 1024;
    private static final int DECSET_BIT_LEFTRIGHT_MARGIN_MODE = 2048;
    private static final int DECSET_BIT_MOUSE_PROTOCOL_SGR = 512;
    private static final int DECSET_BIT_MOUSE_TRACKING_BUTTON_EVENT = 128;
    private static final int DECSET_BIT_MOUSE_TRACKING_PRESS_RELEASE = 64;
    private static final int DECSET_BIT_ORIGIN_MODE = 4;
    private static final int DECSET_BIT_RECTANGULAR_CHANGEATTRIBUTE = 4096;
    private static final int DECSET_BIT_REVERSE_VIDEO = 2;
    private static final int DECSET_BIT_SEND_FOCUS_EVENTS = 256;
    private static final int DECSET_BIT_SHOWING_CURSOR = 16;
    private static final int ESC = 1;
    private static final int ESC_CSI = 6;
    private static final int ESC_CSI_ARGS_ASTERIX = 16;
    private static final int ESC_CSI_ARGS_SPACE = 15;
    private static final int ESC_CSI_BIGGERTHAN = 12;
    private static final int ESC_CSI_DOLLAR = 8;
    private static final int ESC_CSI_DOUBLE_QUOTE = 17;
    private static final int ESC_CSI_EXCLAMATION = 19;
    private static final int ESC_CSI_QUESTIONMARK = 7;
    private static final int ESC_CSI_QUESTIONMARK_ARG_DOLLAR = 14;
    private static final int ESC_CSI_SINGLE_QUOTE = 18;
    private static final int ESC_NONE = 0;
    private static final int ESC_OSC = 10;
    private static final int ESC_OSC_ESC = 11;
    private static final int ESC_P = 13;
    private static final int ESC_PERCENT = 9;
    private static final int ESC_POUND = 2;
    private static final int ESC_SELECT_LEFT_PAREN = 3;
    private static final int ESC_SELECT_RIGHT_PAREN = 4;
    private static final boolean LOG_ESCAPE_SEQUENCES = false;
    private static final int MAX_ESCAPE_PARAMETERS = 16;
    private static final int MAX_OSC_STRING_LENGTH = 8192;
    public static final int MOUSE_LEFT_BUTTON = 0;
    public static final int MOUSE_LEFT_BUTTON_MOVED = 32;
    public static final int MOUSE_WHEELDOWN_BUTTON = 65;
    public static final int MOUSE_WHEELUP_BUTTON = 64;
    public static final int UNICODE_REPLACEMENT_CHAR = 65533;
    private boolean mAboutToAutoWrap;
    final TerminalBuffer mAltBuffer;
    private int mArgIndex;
    int mBackColor;
    private int mBottomMargin;
    public int mColumns;
    private boolean mContinueSequence;
    private int mCurrentDecSetFlags;
    private int mCursorCol;
    private int mCursorRow;
    private int mEffect;
    private int mEscapeState;
    int mForeColor;
    private boolean mInsertMode;
    private int mLeftMargin;
    private final TerminalBuffer mMainBuffer;
    private int mRightMargin;
    public int mRows;
    private int mSavedDecSetFlags;
    private TerminalBuffer mScreen;
    private final TerminalOutput mSession;
    private boolean[] mTabStop;
    private String mTitle;
    private int mTopMargin;
    private boolean mUseLineDrawingG0;
    private boolean mUseLineDrawingG1;
    private byte mUtf8Index;
    private byte mUtf8ToFollow;
    private final Stack<String> mTitleStack = new Stack<>();
    private int mCursorStyle = 0;
    private final int[] mArgs = new int[16];
    private final StringBuilder mOSCOrDeviceControlArgs = new StringBuilder();
    private final SavedScreenState mSavedStateMain = new SavedScreenState();
    private final SavedScreenState mSavedStateAlt = new SavedScreenState();
    private boolean mUseLineDrawingUsesG0 = true;
    private int mScrollCounter = 0;
    private final byte[] mUtf8InputBuffer = new byte[4];
    private int mLastEmittedCodePoint = -1;
    public final TerminalColors mColors = new TerminalColors();

    private void logError(String str) {
    }

    static int mapDecSetBitToInternalBit(int i) {
        if (i == 1) {
            return 1;
        }
        if (i == 25) {
            return 16;
        }
        if (i == 66) {
            return 32;
        }
        if (i == 69) {
            return 2048;
        }
        if (i == 1000) {
            return 64;
        }
        if (i == 1002) {
            return 128;
        }
        if (i == 1004) {
            return 256;
        }
        if (i == 1006) {
            return 512;
        }
        if (i == 2004) {
            return 1024;
        }
        if (i == 5) {
            return 2;
        }
        if (i != 6) {
            return i != 7 ? -1 : 8;
        }
        return 4;
    }

    private boolean isDecsetInternalBitSet(int i) {
        return (i & this.mCurrentDecSetFlags) != 0;
    }

    private void setDecsetinternalBit(int i, boolean z) {
        if (z) {
            if (i == 64) {
                setDecsetinternalBit(128, false);
            } else if (i == 128) {
                setDecsetinternalBit(64, false);
            }
        }
        if (z) {
            this.mCurrentDecSetFlags = i | this.mCurrentDecSetFlags;
        } else {
            this.mCurrentDecSetFlags = (~i) & this.mCurrentDecSetFlags;
        }
    }

    public TerminalEmulator(TerminalOutput terminalOutput, int i, int i2, int i3) {
        this.mSession = terminalOutput;
        TerminalBuffer terminalBuffer = new TerminalBuffer(i, i3, i2);
        this.mMainBuffer = terminalBuffer;
        this.mScreen = terminalBuffer;
        this.mAltBuffer = new TerminalBuffer(i, i2, i2);
        this.mRows = i2;
        this.mColumns = i;
        this.mTabStop = new boolean[i];
        reset();
    }

    public TerminalBuffer getScreen() {
        return this.mScreen;
    }

    public boolean isAlternateBufferActive() {
        return this.mScreen == this.mAltBuffer;
    }

    public void sendMouseEvent(int i, int i2, int i3, boolean z) {
        if (i2 < 1) {
            i2 = 1;
        }
        int i4 = this.mColumns;
        if (i2 > i4) {
            i2 = i4;
        }
        if (i3 < 1) {
            i3 = 1;
        }
        int i5 = this.mRows;
        if (i3 > i5) {
            i3 = i5;
        }
        if (i != 32 || isDecsetInternalBitSet(128)) {
            if (isDecsetInternalBitSet(512)) {
                TerminalOutput terminalOutput = this.mSession;
                StringBuilder sb = new StringBuilder("\u001b[<%d;%d;%d");
                sb.append(z ? 'M' : 'm');
                terminalOutput.write(String.format(sb.toString(), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
                return;
            }
            if (!z) {
                i = 3;
            }
            if (i2 > 223 || i3 > 223) {
                return;
            }
            this.mSession.write(new byte[]{27, 91, 77, (byte) (i + 32), (byte) (i2 + 32), (byte) (i3 + 32)}, 0, 6);
        }
    }

    public void resize(int i, int i2) {
        int i3 = this.mRows;
        if (i3 == i2 && this.mColumns == i) {
            return;
        }
        if (i < 2 || i2 < 2) {
            throw new IllegalArgumentException("rows=" + i2 + ", columns=" + i);
        }
        if (i3 != i2) {
            this.mRows = i2;
            this.mTopMargin = 0;
            this.mBottomMargin = i2;
        }
        int i4 = this.mColumns;
        if (i4 != i) {
            this.mColumns = i;
            boolean[] zArr = this.mTabStop;
            this.mTabStop = new boolean[i];
            setDefaultTabStops();
            System.arraycopy(zArr, 0, this.mTabStop, 0, Math.min(i4, i));
            this.mLeftMargin = 0;
            this.mRightMargin = this.mColumns;
        }
        resizeScreen();
    }

    private void resizeScreen() {
        int[] iArr = {this.mCursorCol, this.mCursorRow};
        this.mScreen.resize(this.mColumns, this.mRows, this.mScreen == this.mAltBuffer ? this.mRows : this.mMainBuffer.mTotalRows, iArr, getStyle(), isAlternateBufferActive());
        this.mCursorCol = iArr[0];
        this.mCursorRow = iArr[1];
    }

    public int getCursorRow() {
        return this.mCursorRow;
    }

    public int getCursorCol() {
        return this.mCursorCol;
    }

    public int getCursorStyle() {
        return this.mCursorStyle;
    }

    public boolean isReverseVideo() {
        return isDecsetInternalBitSet(2);
    }

    public boolean isShowingCursor() {
        return isDecsetInternalBitSet(16);
    }

    public boolean isKeypadApplicationMode() {
        return isDecsetInternalBitSet(32);
    }

    public boolean isCursorKeysApplicationMode() {
        return isDecsetInternalBitSet(1);
    }

    public boolean isMouseTrackingActive() {
        return isDecsetInternalBitSet(64) || isDecsetInternalBitSet(128);
    }

    private void setDefaultTabStops() {
        int i = 0;
        while (i < this.mColumns) {
            this.mTabStop[i] = (i & 7) == 0 && i != 0;
            i++;
        }
    }

    public void append(byte[] bArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            processByte(bArr[i2]);
        }
    }

    private void processByte(byte b) {
        byte b2;
        byte b3 = this.mUtf8ToFollow;
        int i = UNICODE_REPLACEMENT_CHAR;
        if (b3 <= 0) {
            if ((b & 128) == 0) {
                processCodePoint(b);
                return;
            }
            if ((b & 224) == 192) {
                this.mUtf8ToFollow = (byte) 1;
            } else if ((b & 240) == 224) {
                this.mUtf8ToFollow = (byte) 2;
            } else if ((b & 248) == 240) {
                this.mUtf8ToFollow = (byte) 3;
            } else {
                processCodePoint(UNICODE_REPLACEMENT_CHAR);
                return;
            }
            byte[] bArr = this.mUtf8InputBuffer;
            byte b4 = this.mUtf8Index;
            this.mUtf8Index = (byte) (b4 + 1);
            bArr[b4] = b;
            return;
        }
        if ((b & 192) == 128) {
            byte[] bArr2 = this.mUtf8InputBuffer;
            byte b5 = this.mUtf8Index;
            byte b6 = (byte) (b5 + 1);
            this.mUtf8Index = b6;
            bArr2[b5] = b;
            byte b7 = (byte) (b3 - 1);
            this.mUtf8ToFollow = b7;
            if (b7 == 0) {
                int i2 = ((byte) (b6 == 2 ? 31 : b6 == 3 ? 15 : 7)) & bArr2[0];
                int i3 = 1;
                while (true) {
                    b2 = this.mUtf8Index;
                    if (i3 >= b2) {
                        break;
                    }
                    i2 = (i2 << 6) | (this.mUtf8InputBuffer[i3] & 63);
                    i3++;
                }
                if ((i2 <= 127 && b2 > 1) || ((i2 < 2047 && b2 > 2) || (i2 < 65535 && b2 > 3))) {
                    i2 = UNICODE_REPLACEMENT_CHAR;
                }
                this.mUtf8ToFollow = (byte) 0;
                this.mUtf8Index = (byte) 0;
                if (i2 < 128 || i2 > 159) {
                    int type = Character.getType(i2);
                    if (type != 0 && type != 19) {
                        i = i2;
                    }
                    processCodePoint(i);
                    return;
                }
                return;
            }
            return;
        }
        this.mUtf8ToFollow = (byte) 0;
        this.mUtf8Index = (byte) 0;
        emitCodePoint(UNICODE_REPLACEMENT_CHAR);
        processByte(b);
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0119  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void processCodePoint(int i) {
        int i2;
        boolean z;
        int arg;
        int i3;
        int i4;
        if (i != 0) {
            if (i == 24 || i == 26) {
                if (this.mEscapeState != 0) {
                    this.mEscapeState = 0;
                    emitCodePoint(127);
                    return;
                }
                return;
            }
            if (i != 27) {
                switch (i) {
                    case 7:
                        if (this.mEscapeState == 10) {
                            doOsc(i);
                        } else {
                            this.mSession.onBell();
                        }
                        break;
                    case 8:
                        int i5 = this.mLeftMargin;
                        int i6 = this.mCursorCol;
                        if (i5 == i6) {
                            int i7 = this.mCursorRow - 1;
                            if (i7 >= 0 && this.mScreen.getLineWrap(i7)) {
                                this.mScreen.clearLineWrap(i7);
                                setCursorRowCol(i7, this.mRightMargin - 1);
                                break;
                            }
                        } else {
                            setCursorCol(i6 - 1);
                            break;
                        }
                        break;
                    case 9:
                        this.mCursorCol = nextTabStop(1);
                        break;
                    case 10:
                    case 11:
                    case 12:
                        doLinefeed();
                        break;
                    case 13:
                        setCursorCol(this.mLeftMargin);
                        break;
                    case 14:
                        this.mUseLineDrawingUsesG0 = false;
                        break;
                    case 15:
                        this.mUseLineDrawingUsesG0 = true;
                        break;
                    default:
                        this.mContinueSequence = false;
                        switch (this.mEscapeState) {
                            case 0:
                                if (i >= 32) {
                                    emitCodePoint(i);
                                }
                                break;
                            case 1:
                                doEsc(i);
                                break;
                            case 2:
                                doEscPound(i);
                                break;
                            case 3:
                                this.mUseLineDrawingG0 = i == 48;
                                break;
                            case 4:
                                this.mUseLineDrawingG1 = i == 48;
                                break;
                            case 5:
                            default:
                                unknownSequence(i);
                                break;
                            case 6:
                                doCsi(i);
                                break;
                            case 7:
                                doCsiQuestionMark(i);
                                break;
                            case 8:
                                boolean zIsDecsetInternalBitSet = isDecsetInternalBitSet(4);
                                int i8 = zIsDecsetInternalBitSet ? this.mTopMargin : 0;
                                int i9 = zIsDecsetInternalBitSet ? this.mBottomMargin : this.mRows;
                                int i10 = zIsDecsetInternalBitSet ? this.mLeftMargin : 0;
                                int i11 = zIsDecsetInternalBitSet ? this.mRightMargin : this.mColumns;
                                if (i == 114 || i == 116) {
                                    boolean z2 = i == 116;
                                    z = z2;
                                    int iMin = Math.min(getArg(0, 1, true) - 1, i9) + i8;
                                    int iMin2 = Math.min(getArg(1, 1, true) - 1, i11) + i10;
                                    int iMin3 = Math.min(getArg(2, this.mRows, true) + 1, i9 - 1) + i8;
                                    int iMin4 = Math.min(getArg(3, this.mColumns, true) + 1, i11 - 1) + i10;
                                    int i12 = this.mArgIndex;
                                    if (i12 >= 4) {
                                        int[] iArr = this.mArgs;
                                        if (i12 >= iArr.length) {
                                            this.mArgIndex = iArr.length - 1;
                                        }
                                        for (int i13 = 4; i13 <= this.mArgIndex; i13++) {
                                            int arg2 = getArg(i13, 0, false);
                                            if (arg2 == 0) {
                                                i2 = 29;
                                                z = z2;
                                            } else if (arg2 == 1) {
                                                i2 = 1;
                                            } else if (arg2 == 4) {
                                                i2 = 4;
                                            } else if (arg2 == 5) {
                                                i2 = 8;
                                            } else if (arg2 == 7) {
                                                i2 = 16;
                                            } else if (arg2 == 22) {
                                                i2 = 1;
                                            } else if (arg2 == 27) {
                                                i2 = 16;
                                            } else if (arg2 == 24) {
                                                i2 = 4;
                                            } else if (arg2 != 25) {
                                                i2 = 0;
                                            } else {
                                                i2 = 8;
                                            }
                                            if (!z2 || z) {
                                                this.mScreen.setOrClearEffect(i2, z, z2, isDecsetInternalBitSet(4096), i10, i11, iMin, iMin2, iMin3, iMin4);
                                            }
                                        }
                                    }
                                } else if (i == 118) {
                                    int iMin5 = Math.min((getArg(0, 1, true) - 1) + i8, this.mRows);
                                    int iMin6 = Math.min((getArg(1, 1, true) - 1) + i10, this.mColumns);
                                    int iMin7 = Math.min(Math.max(getArg(2, this.mRows, true) + i8, iMin5), this.mRows);
                                    int iMin8 = Math.min(Math.max(getArg(3, this.mColumns, true) + i10, iMin6), this.mColumns);
                                    int iMin9 = Math.min((getArg(5, 1, true) - 1) + i8, this.mRows);
                                    int iMin10 = Math.min((getArg(6, 1, true) - 1) + i10, this.mColumns);
                                    this.mScreen.blockCopy(iMin6, iMin5, Math.min(this.mColumns - iMin10, iMin8 - iMin6), Math.min(this.mRows - iMin9, iMin7 - iMin5), iMin10, iMin9);
                                } else if (i == 120 || i == 122 || i == 123) {
                                    boolean z3 = i != 120;
                                    boolean z4 = i == 123;
                                    boolean z5 = z3 && z4;
                                    if (z3) {
                                        arg = 32;
                                        i3 = 0;
                                    } else {
                                        arg = getArg(0, -1, true);
                                        i3 = 1;
                                    }
                                    if ((arg >= 32 && arg <= 126) || (arg >= 160 && arg <= 255)) {
                                        int i14 = i3 + 1;
                                        int iMin11 = Math.min(getArg(i3, 1, true) + i8, i9 + 1);
                                        int i15 = i14 + 1;
                                        int iMin12 = Math.min(getArg(i14, 1, true) + i10, i11 + 1);
                                        int iMin13 = Math.min(getArg(i15, this.mRows, true) + i8, i9);
                                        int iMin14 = Math.min(getArg(i15 + 1, this.mColumns, true) + i10, i11);
                                        long style = getStyle();
                                        for (int i16 = iMin11 - 1; i16 < iMin13; i16++) {
                                            for (int i17 = iMin12 - 1; i17 < iMin14; i17++) {
                                                if (!z4 || (TextStyle.decodeEffect(this.mScreen.getStyleAt(i16, i17)) & 128) == 0) {
                                                    TerminalBuffer terminalBuffer = this.mScreen;
                                                    terminalBuffer.setChar(i17, i16, arg, z5 ? terminalBuffer.getStyleAt(i16, i17) : style);
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    unknownSequence(i);
                                }
                                break;
                            case 9:
                                break;
                            case 10:
                                doOsc(i);
                                break;
                            case 11:
                                doOscEsc(i);
                                break;
                            case 12:
                                doCsiBiggerThan(i);
                                break;
                            case 13:
                                doDeviceControl(i);
                                break;
                            case 14:
                                if (i == 112) {
                                    int arg0 = getArg0(0);
                                    if (arg0 == 47 || arg0 == 1047 || arg0 == 1049) {
                                        i4 = this.mScreen == this.mAltBuffer ? 1 : 2;
                                    } else {
                                        int iMapDecSetBitToInternalBit = mapDecSetBitToInternalBit(arg0);
                                        if (iMapDecSetBitToInternalBit == -1) {
                                            Log.e(EmulatorDebug.LOG_TAG, "Got DECRQM for unrecognized private DEC mode=" + arg0);
                                            i4 = 0;
                                        } else if (isDecsetInternalBitSet(iMapDecSetBitToInternalBit)) {
                                            i4 = 1;
                                        } else {
                                            i4 = 2;
                                        }
                                    }
                                    this.mSession.write(String.format(Locale.US, "\u001b[?%d;%d$y", Integer.valueOf(arg0), Integer.valueOf(i4)));
                                } else {
                                    unknownSequence(i);
                                }
                                break;
                            case 15:
                                int arg02 = getArg0(0);
                                if (i == 113) {
                                    switch (arg02) {
                                        case 0:
                                        case 1:
                                        case 2:
                                            this.mCursorStyle = 0;
                                            break;
                                        case 3:
                                        case 4:
                                            this.mCursorStyle = 1;
                                            break;
                                        case 5:
                                        case 6:
                                            this.mCursorStyle = 2;
                                            break;
                                    }
                                } else {
                                    if (i != 116 && i != 117) {
                                        unknownSequence(i);
                                    }
                                    break;
                                }
                                break;
                            case 16:
                                int arg03 = getArg0(0);
                                if (i == 120 && arg03 >= 0 && arg03 <= 2) {
                                    setDecsetinternalBit(4096, arg03 == 2);
                                } else {
                                    unknownSequence(i);
                                }
                                break;
                            case 17:
                                if (i == 113) {
                                    int arg04 = getArg0(0);
                                    if (arg04 == 0 || arg04 == 2) {
                                        this.mEffect &= -129;
                                    } else if (arg04 != 1) {
                                        unknownSequence(i);
                                    } else {
                                        this.mEffect |= 128;
                                    }
                                } else {
                                    unknownSequence(i);
                                }
                                break;
                            case 18:
                                if (i == 125) {
                                    int i18 = this.mRightMargin - this.mCursorCol;
                                    int iMin15 = Math.min(getArg0(1), i18);
                                    TerminalBuffer terminalBuffer2 = this.mScreen;
                                    int i19 = this.mCursorCol;
                                    terminalBuffer2.blockCopy(i19, 0, i18 - iMin15, this.mRows, i19 + iMin15, 0);
                                    blockClear(this.mCursorCol, 0, iMin15, this.mRows);
                                } else if (i == 126) {
                                    int i20 = this.mRightMargin - this.mCursorCol;
                                    int iMin16 = Math.min(getArg0(1), i20);
                                    int i21 = i20 - iMin16;
                                    TerminalBuffer terminalBuffer3 = this.mScreen;
                                    int i22 = this.mCursorCol;
                                    terminalBuffer3.blockCopy(i22 + iMin16, 0, i21, this.mRows, i22, 0);
                                    blockClear(this.mCursorRow + i21, 0, iMin16, this.mRows);
                                } else {
                                    unknownSequence(i);
                                }
                                break;
                            case 19:
                                if (i == 112) {
                                    reset();
                                } else {
                                    unknownSequence(i);
                                }
                                break;
                        }
                        if (!this.mContinueSequence) {
                            this.mEscapeState = 0;
                        }
                        break;
                }
                return;
            }
            int i23 = this.mEscapeState;
            if (i23 == 13) {
                return;
            }
            if (i23 != 10) {
                startEscapeSequence();
            } else {
                doOsc(i);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void doDeviceControl(int i) {
        String codeFromTermcap = null;
        if (i == 92) {
            String string = this.mOSCOrDeviceControlArgs.toString();
            if (string.startsWith("$q")) {
                if (string.equals("$q\"p")) {
                    this.mSession.write("\u001bP1$r64;1\"p\u001b\\");
                } else {
                    finishSequenceAndLogError("Unrecognized DECRQSS string: '" + string + "'");
                }
            } else if (string.startsWith("+q")) {
                for (String str : string.substring(2).split(";")) {
                    if (str.length() % 2 == 0) {
                        StringBuilder sb = new StringBuilder();
                        for (int i2 = 0; i2 < str.length(); i2 += 2) {
                            sb.append((char) Long.decode("0x" + str.charAt(i2) + str.charAt(i2 + 1)).longValue());
                        }
                        String string2 = sb.toString();
                        int iHashCode = string2.hashCode();
                        if (iHashCode != -1354842768) {
                            if (iHashCode != 2188) {
                                if (iHashCode == 2682 ? string2.equals("TN") : iHashCode == 3373707 && string2.equals("name")) {
                                    codeFromTermcap = "xterm";
                                }
                            } else if (string2.equals("Co")) {
                                codeFromTermcap = "256";
                            }
                            codeFromTermcap = KeyHandler.getCodeFromTermcap(string2, isDecsetInternalBitSet(1), isDecsetInternalBitSet(32));
                        } else if (!string2.equals("colors")) {
                            codeFromTermcap = KeyHandler.getCodeFromTermcap(string2, isDecsetInternalBitSet(1), isDecsetInternalBitSet(32));
                        }
                        String str2 = codeFromTermcap;
                        if (str2 == null) {
                            int iHashCode2 = string2.hashCode();
                            if (iHashCode2 == 1196 ? !string2.equals("%1") : !(iHashCode2 == 1234 && string2.equals("&8"))) {
                                Log.w(EmulatorDebug.LOG_TAG, "Unhandled termcap/terminfo name: '" + string2 + "'");
                            }
                            this.mSession.write("\u001bP0+r" + str + "\u001b\\");
                        } else {
                            StringBuilder sb2 = new StringBuilder();
                            for (int i3 = 0; i3 < str2.length(); i3++) {
                                sb2.append(String.format("%02X", Integer.valueOf(str2.charAt(i3))));
                            }
                            this.mSession.write("\u001bP1+r" + str + "=" + ((Object) sb2) + "\u001b\\");
                        }
                    } else {
                        Log.e(EmulatorDebug.LOG_TAG, "Invalid device termcap/terminfo name of odd length: " + str);
                    }
                }
            }
            finishSequence();
            return;
        }
        if (this.mOSCOrDeviceControlArgs.length() > 8192) {
            this.mOSCOrDeviceControlArgs.setLength(0);
            finishSequence();
        } else {
            this.mOSCOrDeviceControlArgs.appendCodePoint(i);
            continueSequence(this.mEscapeState);
        }
    }

    private int nextTabStop(int i) {
        int i2 = this.mCursorCol;
        while (true) {
            i2++;
            if (i2 < this.mColumns) {
                if (this.mTabStop[i2] && i - 1 == 0) {
                    return Math.min(i2, this.mRightMargin);
                }
            } else {
                return this.mRightMargin - 1;
            }
        }
    }

    private void doCsiQuestionMark(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (i == 36) {
            continueSequence(14);
            return;
        }
        if (i == 104 || i == 108) {
            int i6 = this.mArgIndex;
            int[] iArr = this.mArgs;
            if (i6 >= iArr.length) {
                this.mArgIndex = iArr.length - 1;
            }
            for (int i7 = 0; i7 <= this.mArgIndex; i7++) {
                doDecSetOrReset(i == 104, this.mArgs[i7]);
            }
            return;
        }
        int i8 = -1;
        if (i == 110) {
            if (getArg0(-1) == 6) {
                this.mSession.write(String.format(Locale.US, "\u001b[?%d;%d;1R", Integer.valueOf(this.mCursorRow + 1), Integer.valueOf(this.mCursorCol + 1)));
                return;
            } else {
                finishSequence();
                return;
            }
        }
        if (i != 74 && i != 75) {
            if (i == 114 || i == 115) {
                int i9 = this.mArgIndex;
                int[] iArr2 = this.mArgs;
                if (i9 >= iArr2.length) {
                    this.mArgIndex = iArr2.length - 1;
                }
                for (int i10 = 0; i10 <= this.mArgIndex; i10++) {
                    int i11 = this.mArgs[i10];
                    int iMapDecSetBitToInternalBit = mapDecSetBitToInternalBit(i11);
                    if (iMapDecSetBitToInternalBit == -1) {
                        Log.w(EmulatorDebug.LOG_TAG, "Ignoring request to save/recall decset bit=" + i11);
                    } else if (i == 115) {
                        this.mSavedDecSetFlags |= iMapDecSetBitToInternalBit;
                    } else {
                        doDecSetOrReset((iMapDecSetBitToInternalBit & this.mSavedDecSetFlags) != 0, i11);
                    }
                }
                return;
            }
            parseArg(i);
            return;
        }
        this.mAboutToAutoWrap = false;
        boolean z = i == 75;
        int arg0 = getArg0(0);
        if (arg0 != 0) {
            if (arg0 == 1) {
                i8 = z ? this.mCursorRow : 0;
                i2 = this.mCursorCol + 1;
                i5 = 1 + this.mCursorRow;
            } else if (arg0 == 2) {
                i8 = z ? this.mCursorRow : 0;
                i2 = this.mColumns;
                i5 = z ? this.mCursorRow + 1 : this.mRows;
            } else {
                unknownSequence(i);
                i2 = -1;
                i3 = -1;
                i4 = -1;
                i5 = -1;
            }
            i4 = i5;
            i3 = 0;
        } else {
            int i12 = this.mCursorCol;
            i8 = this.mCursorRow;
            i2 = this.mColumns;
            int i13 = z ? i8 + 1 : this.mRows;
            i3 = i12;
            i4 = i13;
        }
        long style = getStyle();
        for (int i14 = i8; i14 < i4; i14++) {
            for (int i15 = i3; i15 < i2; i15++) {
                if ((TextStyle.decodeEffect(this.mScreen.getStyleAt(i14, i15)) & 128) == 0) {
                    this.mScreen.setChar(i15, i14, 32, style);
                }
            }
        }
    }

    public void doDecSetOrReset(boolean z, int i) {
        int iMapDecSetBitToInternalBit = mapDecSetBitToInternalBit(i);
        if (iMapDecSetBitToInternalBit != -1) {
            setDecsetinternalBit(iMapDecSetBitToInternalBit, z);
        }
        switch (i) {
            case 1:
            case 12:
            case 25:
            case 40:
            case 45:
            case 66:
            case PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW /* 1015 */:
            case 1034:
            case 2004:
                return;
            case 47:
                break;
            case 69:
                if (z) {
                    return;
                }
                this.mLeftMargin = 0;
                this.mRightMargin = this.mColumns;
                return;
            default:
                switch (i) {
                    case 3:
                        this.mTopMargin = 0;
                        this.mLeftMargin = 0;
                        this.mBottomMargin = this.mRows;
                        this.mRightMargin = this.mColumns;
                        setDecsetinternalBit(2048, false);
                        blockClear(0, 0, this.mColumns, this.mRows);
                        setCursorRowCol(0, 0);
                        return;
                    case 4:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                        return;
                    case 6:
                        if (z) {
                            setCursorPosition(0, 0);
                            return;
                        }
                        return;
                    default:
                        switch (i) {
                            case 1000:
                            case PointerIconCompat.TYPE_CONTEXT_MENU /* 1001 */:
                            case PointerIconCompat.TYPE_HAND /* 1002 */:
                            case PointerIconCompat.TYPE_HELP /* 1003 */:
                            case PointerIconCompat.TYPE_WAIT /* 1004 */:
                            case 1005:
                            case PointerIconCompat.TYPE_CELL /* 1006 */:
                                return;
                            default:
                                switch (i) {
                                    case 1047:
                                    case 1049:
                                        break;
                                    case 1048:
                                        if (z) {
                                            saveCursor();
                                        } else {
                                            restoreCursor();
                                        }
                                        break;
                                    default:
                                        unknownParameter(i);
                                        break;
                                }
                        }
                }
        }
        TerminalBuffer terminalBuffer = z ? this.mAltBuffer : this.mMainBuffer;
        if (terminalBuffer != this.mScreen) {
            boolean z2 = (terminalBuffer.mColumns == this.mColumns && terminalBuffer.mScreenRows == this.mRows) ? false : true;
            if (z) {
                saveCursor();
            }
            this.mScreen = terminalBuffer;
            if (!z) {
                int i2 = this.mSavedStateMain.mSavedCursorCol;
                int i3 = this.mSavedStateMain.mSavedCursorRow;
                restoreCursor();
                if (z2) {
                    this.mCursorCol = i2;
                    this.mCursorRow = i3;
                }
            }
            if (z2) {
                resizeScreen();
            }
            if (terminalBuffer == this.mAltBuffer) {
                terminalBuffer.blockSet(0, 0, this.mColumns, this.mRows, 32, getStyle());
            }
        }
    }

    private void doCsiBiggerThan(int i) {
        if (i == 99) {
            this.mSession.write("\u001b[>41;320;0c");
            return;
        }
        if (i == 109) {
            Log.e(EmulatorDebug.LOG_TAG, "(ignored) CSI > MODIFY RESOURCE: " + getArg0(-1) + " to " + getArg1(-1));
            return;
        }
        parseArg(i);
    }

    private void startEscapeSequence() {
        this.mEscapeState = 1;
        this.mArgIndex = 0;
        Arrays.fill(this.mArgs, -1);
    }

    private void doLinefeed() {
        int i = this.mCursorRow;
        int i2 = this.mBottomMargin;
        int i3 = i + 1;
        if (i >= i2) {
            if (i != this.mRows - 1) {
                setCursorRow(i3);
            }
        } else {
            if (i3 == i2) {
                scrollDownOneLine();
                i3 = this.mBottomMargin - 1;
            }
            setCursorRow(i3);
        }
    }

    private void continueSequence(int i) {
        this.mEscapeState = i;
        this.mContinueSequence = true;
    }

    private void doEscPound(int i) {
        if (i == 56) {
            this.mScreen.blockSet(0, 0, this.mColumns, this.mRows, 69, getStyle());
        } else {
            unknownSequence(i);
        }
    }

    private void doEsc(int i) {
        if (i == 35) {
            continueSequence(2);
        }
        if (i != 48) {
            if (i == 72) {
                this.mTabStop[this.mCursorCol] = true;
                return;
            }
            if (i == 80) {
                this.mOSCOrDeviceControlArgs.setLength(0);
                continueSequence(13);
                return;
            }
            if (i == 91) {
                continueSequence(6);
                return;
            }
            if (i == 93) {
                this.mOSCOrDeviceControlArgs.setLength(0);
                continueSequence(10);
                return;
            }
            if (i == 99) {
                reset();
                this.mMainBuffer.clearTranscript();
                blockClear(0, 0, this.mColumns, this.mRows);
                setCursorPosition(0, 0);
                return;
            }
            if (i == 40) {
                continueSequence(3);
                return;
            }
            if (i == 41) {
                continueSequence(4);
                return;
            }
            if (i == 61) {
                setDecsetinternalBit(32, true);
                return;
            }
            if (i == 62) {
                setDecsetinternalBit(32, false);
                return;
            }
            if (i == 77) {
                int i2 = this.mCursorRow;
                int i3 = this.mTopMargin;
                if (i2 <= i3) {
                    this.mScreen.blockCopy(0, i3, this.mColumns, this.mBottomMargin - (i3 + 1), 0, i3 + 1);
                    blockClear(0, this.mTopMargin, this.mColumns);
                    return;
                } else {
                    this.mCursorRow = i2 - 1;
                    return;
                }
            }
            if (i != 78) {
                switch (i) {
                    case 54:
                        int i4 = this.mCursorCol;
                        int i5 = this.mLeftMargin;
                        if (i4 > i5) {
                            this.mCursorCol = i4 - 1;
                        } else {
                            int i6 = this.mBottomMargin;
                            int i7 = this.mTopMargin;
                            int i8 = i6 - i7;
                            this.mScreen.blockCopy(i5, i7, (this.mRightMargin - i5) - 1, i8, i5 + 1, i7);
                            this.mScreen.blockSet(this.mLeftMargin, this.mTopMargin, 1, i8, 32, TextStyle.encode(this.mForeColor, this.mBackColor, 0));
                        }
                        break;
                    case 55:
                        saveCursor();
                        break;
                    case 56:
                        restoreCursor();
                        break;
                    case 57:
                        int i9 = this.mCursorCol;
                        if (i9 < this.mRightMargin - 1) {
                            this.mCursorCol = i9 + 1;
                        } else {
                            int i10 = this.mBottomMargin;
                            int i11 = this.mTopMargin;
                            int i12 = i10 - i11;
                            TerminalBuffer terminalBuffer = this.mScreen;
                            int i13 = this.mLeftMargin;
                            terminalBuffer.blockCopy(i13 + 1, i11, (this.mRightMargin - i13) - 1, i12, i13, i11);
                            this.mScreen.blockSet(this.mRightMargin - 1, this.mTopMargin, 1, i12, 32, TextStyle.encode(this.mForeColor, this.mBackColor, 0));
                        }
                        break;
                    default:
                        switch (i) {
                            case 68:
                                doLinefeed();
                                break;
                            case 69:
                                setCursorCol(isDecsetInternalBitSet(4) ? this.mLeftMargin : 0);
                                doLinefeed();
                                break;
                            case 70:
                                setCursorRowCol(0, this.mBottomMargin - 1);
                                break;
                            default:
                                unknownSequence(i);
                                break;
                        }
                        break;
                }
            }
        }
    }

    private void saveCursor() {
        SavedScreenState savedScreenState = this.mScreen == this.mMainBuffer ? this.mSavedStateMain : this.mSavedStateAlt;
        savedScreenState.mSavedCursorRow = this.mCursorRow;
        savedScreenState.mSavedCursorCol = this.mCursorCol;
        savedScreenState.mSavedEffect = this.mEffect;
        savedScreenState.mSavedForeColor = this.mForeColor;
        savedScreenState.mSavedBackColor = this.mBackColor;
        savedScreenState.mSavedDecFlags = this.mCurrentDecSetFlags;
        savedScreenState.mUseLineDrawingG0 = this.mUseLineDrawingG0;
        savedScreenState.mUseLineDrawingG1 = this.mUseLineDrawingG1;
        savedScreenState.mUseLineDrawingUsesG0 = this.mUseLineDrawingUsesG0;
    }

    private void restoreCursor() {
        SavedScreenState savedScreenState = this.mScreen == this.mMainBuffer ? this.mSavedStateMain : this.mSavedStateAlt;
        setCursorRowCol(savedScreenState.mSavedCursorRow, savedScreenState.mSavedCursorCol);
        this.mEffect = savedScreenState.mSavedEffect;
        this.mForeColor = savedScreenState.mSavedForeColor;
        this.mBackColor = savedScreenState.mSavedBackColor;
        this.mCurrentDecSetFlags = (this.mCurrentDecSetFlags & (-13)) | (savedScreenState.mSavedDecFlags & 12);
        this.mUseLineDrawingG0 = savedScreenState.mUseLineDrawingG0;
        this.mUseLineDrawingG1 = savedScreenState.mUseLineDrawingG1;
        this.mUseLineDrawingUsesG0 = savedScreenState.mUseLineDrawingUsesG0;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0343  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void doCsi(int i) {
        if (i == 36) {
            continueSequence(8);
        }
        if (i == 39) {
            continueSequence(18);
            return;
        }
        if (i == 42) {
            continueSequence(16);
            return;
        }
        int i2 = 0;
        if (i == 80) {
            this.mAboutToAutoWrap = false;
            int i3 = this.mColumns - this.mCursorCol;
            int iMin = Math.min(getArg0(1), i3);
            int i4 = i3 - iMin;
            TerminalBuffer terminalBuffer = this.mScreen;
            int i5 = this.mCursorCol;
            int i6 = this.mCursorRow;
            terminalBuffer.blockCopy(i5 + iMin, i6, i4, 1, i5, i6);
            blockClear(this.mCursorCol + i4, this.mCursorRow, iMin);
            return;
        }
        if (i == 88) {
            this.mAboutToAutoWrap = false;
            this.mScreen.blockSet(this.mCursorCol, this.mCursorRow, Math.min(getArg0(1), this.mColumns - this.mCursorCol), 1, 32, getStyle());
            return;
        }
        if (i == 90) {
            int arg0 = getArg0(1);
            int iMax = this.mLeftMargin;
            int i7 = this.mCursorCol - 1;
            while (true) {
                if (i7 < 0) {
                    break;
                }
                if (this.mTabStop[i7] && arg0 - 1 == 0) {
                    iMax = Math.max(i7, this.mLeftMargin);
                    break;
                }
                i7--;
            }
            this.mCursorCol = iMax;
            return;
        }
        if (i == 83) {
            int arg02 = getArg0(1);
            while (i2 < arg02) {
                scrollDownOneLine();
                i2++;
            }
            return;
        }
        if (i == 84) {
            if (this.mArgIndex == 0) {
                int arg03 = getArg0(1);
                int i8 = this.mBottomMargin - this.mTopMargin;
                int iMin2 = Math.min(i8, arg03);
                TerminalBuffer terminalBuffer2 = this.mScreen;
                int i9 = this.mTopMargin;
                terminalBuffer2.blockCopy(0, i9, this.mColumns, i8 - iMin2, 0, i9 + iMin2);
                blockClear(0, this.mTopMargin, this.mColumns, iMin2);
                return;
            }
            unimplementedSequence(i);
            return;
        }
        switch (i) {
            case 32:
                continueSequence(15);
                break;
            case 33:
                continueSequence(19);
                break;
            case 34:
                continueSequence(17);
                break;
            default:
                switch (i) {
                    case 62:
                        continueSequence(12);
                        break;
                    case 63:
                        continueSequence(7);
                        break;
                    case 64:
                        this.mAboutToAutoWrap = false;
                        int i10 = this.mColumns - this.mCursorCol;
                        int iMin3 = Math.min(getArg0(1), i10);
                        TerminalBuffer terminalBuffer3 = this.mScreen;
                        int i11 = this.mCursorCol;
                        int i12 = this.mCursorRow;
                        terminalBuffer3.blockCopy(i11, i12, i10 - iMin3, 1, i11 + iMin3, i12);
                        blockClear(this.mCursorCol, this.mCursorRow, iMin3);
                        break;
                    case 65:
                        setCursorRow(Math.max(0, this.mCursorRow - getArg0(1)));
                        break;
                    case 66:
                        setCursorRow(Math.min(this.mRows - 1, this.mCursorRow + getArg0(1)));
                        break;
                    case 67:
                        setCursorCol(Math.min(this.mRightMargin - 1, this.mCursorCol + getArg0(1)));
                        break;
                    case 68:
                        setCursorCol(Math.max(this.mLeftMargin, this.mCursorCol - getArg0(1)));
                        break;
                    case 69:
                        setCursorPosition(0, this.mCursorRow + getArg0(1));
                        break;
                    case 70:
                        setCursorPosition(0, this.mCursorRow - getArg0(1));
                        break;
                    case 71:
                        setCursorCol(Math.min(Math.max(1, getArg0(1)), this.mColumns) - 1);
                        break;
                    case 72:
                        setCursorPosition(getArg1(1) - 1, getArg0(1) - 1);
                        break;
                    case 73:
                        setCursorCol(nextTabStop(getArg0(1)));
                        break;
                    case 74:
                        int arg04 = getArg0(0);
                        if (arg04 == 0) {
                            int i13 = this.mCursorCol;
                            blockClear(i13, this.mCursorRow, this.mColumns - i13);
                            int i14 = this.mCursorRow;
                            blockClear(0, i14 + 1, this.mColumns, this.mRows - (i14 + 1));
                        } else if (arg04 == 1) {
                            blockClear(0, 0, this.mColumns, this.mCursorRow);
                            blockClear(0, this.mCursorRow, this.mCursorCol + 1);
                        } else if (arg04 == 2) {
                            blockClear(0, 0, this.mColumns, this.mRows);
                        } else if (arg04 == 3) {
                            this.mMainBuffer.clearTranscript();
                        } else {
                            unknownSequence(i);
                        }
                        this.mAboutToAutoWrap = false;
                        break;
                    case 75:
                        int arg05 = getArg0(0);
                        if (arg05 == 0) {
                            int i15 = this.mCursorCol;
                            blockClear(i15, this.mCursorRow, this.mColumns - i15);
                        } else if (arg05 == 1) {
                            blockClear(0, this.mCursorRow, this.mCursorCol + 1);
                        } else if (arg05 == 2) {
                            blockClear(0, this.mCursorRow, this.mColumns);
                        } else {
                            unknownSequence(i);
                        }
                        this.mAboutToAutoWrap = false;
                        break;
                    case 76:
                        int i16 = this.mBottomMargin - this.mCursorRow;
                        int iMin4 = Math.min(getArg0(1), i16);
                        TerminalBuffer terminalBuffer4 = this.mScreen;
                        int i17 = this.mCursorRow;
                        terminalBuffer4.blockCopy(0, i17, this.mColumns, i16 - iMin4, 0, i17 + iMin4);
                        blockClear(0, this.mCursorRow, this.mColumns, iMin4);
                        break;
                    case 77:
                        this.mAboutToAutoWrap = false;
                        int i18 = this.mBottomMargin - this.mCursorRow;
                        int iMin5 = Math.min(getArg0(1), i18);
                        int i19 = i18 - iMin5;
                        TerminalBuffer terminalBuffer5 = this.mScreen;
                        int i20 = this.mCursorRow;
                        terminalBuffer5.blockCopy(0, i20 + iMin5, this.mColumns, i19, 0, i20);
                        blockClear(0, this.mCursorRow + i19, this.mColumns, iMin5);
                        break;
                    default:
                        switch (i) {
                            case 96:
                                setCursorColRespectingOriginMode(getArg0(1) - 1);
                                break;
                            case 97:
                                break;
                            case 98:
                                if (this.mLastEmittedCodePoint != -1) {
                                    int arg06 = getArg0(1);
                                    while (i2 < arg06) {
                                        emitCodePoint(this.mLastEmittedCodePoint);
                                        i2++;
                                    }
                                    break;
                                }
                                break;
                            case 99:
                                if (getArg0(0) == 0) {
                                    this.mSession.write("\u001b[?64;1;2;6;9;15;18;21;22c");
                                }
                                break;
                            case 100:
                                setCursorRow(Math.min(Math.max(1, getArg0(1)), this.mRows) - 1);
                                break;
                            case 101:
                                setCursorPosition(this.mCursorCol, this.mCursorRow + getArg0(1));
                                break;
                            case 102:
                                break;
                            case 103:
                                int arg07 = getArg0(0);
                                if (arg07 == 0) {
                                    this.mTabStop[this.mCursorCol] = false;
                                    break;
                                } else if (arg07 == 3) {
                                    for (int i21 = 0; i21 < this.mColumns; i21++) {
                                        this.mTabStop[i21] = false;
                                    }
                                    break;
                                }
                                break;
                            case 104:
                                doSetMode(true);
                                break;
                            default:
                                switch (i) {
                                    case 108:
                                        doSetMode(false);
                                        break;
                                    case 109:
                                        selectGraphicRendition();
                                        break;
                                    case 110:
                                        int arg08 = getArg0(0);
                                        if (arg08 == 5) {
                                            this.mSession.write(new byte[]{27, 91, 48, 110}, 0, 4);
                                            break;
                                        } else if (arg08 == 6) {
                                            this.mSession.write(String.format(Locale.US, "\u001b[%d;%dR", Integer.valueOf(this.mCursorRow + 1), Integer.valueOf(this.mCursorCol + 1)));
                                            break;
                                        }
                                        break;
                                    default:
                                        switch (i) {
                                            case 114:
                                                int iMax2 = Math.max(0, Math.min(getArg0(1) - 1, this.mRows - 2));
                                                this.mTopMargin = iMax2;
                                                this.mBottomMargin = Math.max(iMax2 + 2, Math.min(getArg1(this.mRows), this.mRows));
                                                setCursorPosition(0, 0);
                                                break;
                                            case 115:
                                                if (isDecsetInternalBitSet(2048)) {
                                                    int iMin6 = Math.min(getArg0(1) - 1, this.mColumns - 2);
                                                    this.mLeftMargin = iMin6;
                                                    this.mRightMargin = Math.max(iMin6 + 1, Math.min(getArg1(this.mColumns), this.mColumns));
                                                    setCursorPosition(0, 0);
                                                } else {
                                                    saveCursor();
                                                }
                                                break;
                                            case 116:
                                                int arg09 = getArg0(0);
                                                if (arg09 == 11) {
                                                    this.mSession.write("\u001b[1t");
                                                    break;
                                                } else if (arg09 == 13) {
                                                    this.mSession.write("\u001b[3;0;0t");
                                                    break;
                                                } else if (arg09 == 14) {
                                                    this.mSession.write(String.format(Locale.US, "\u001b[4;%d;%dt", Integer.valueOf(this.mRows * 12), Integer.valueOf(this.mColumns * 12)));
                                                    break;
                                                } else {
                                                    switch (arg09) {
                                                        case 18:
                                                            this.mSession.write(String.format(Locale.US, "\u001b[8;%d;%dt", Integer.valueOf(this.mRows), Integer.valueOf(this.mColumns)));
                                                            break;
                                                        case 19:
                                                            this.mSession.write(String.format(Locale.US, "\u001b[9;%d;%dt", Integer.valueOf(this.mRows), Integer.valueOf(this.mColumns)));
                                                            break;
                                                        case 20:
                                                            this.mSession.write("\u001b]LIconLabel\u001b\\");
                                                            break;
                                                        case 21:
                                                            this.mSession.write("\u001b]l\u001b\\");
                                                            break;
                                                        case 22:
                                                            this.mTitleStack.push(this.mTitle);
                                                            if (this.mTitleStack.size() > 20) {
                                                                this.mTitleStack.remove(0);
                                                            }
                                                            break;
                                                        case 23:
                                                            if (!this.mTitleStack.isEmpty()) {
                                                                setTitle(this.mTitleStack.pop());
                                                            }
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 117:
                                                restoreCursor();
                                                break;
                                            default:
                                                parseArg(i);
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void selectGraphicRendition() {
        int i = this.mArgIndex;
        int[] iArr = this.mArgs;
        if (i >= iArr.length) {
            this.mArgIndex = iArr.length - 1;
        }
        int i2 = 0;
        while (true) {
            int i3 = this.mArgIndex;
            if (i2 > i3) {
                return;
            }
            int[] iArr2 = this.mArgs;
            int i4 = iArr2[i2];
            if (i4 >= 0) {
                if (i4 != 0) {
                    this.mForeColor = 256;
                    this.mBackColor = 257;
                    this.mEffect = 0;
                } else if (i4 == 1) {
                    this.mEffect |= 1;
                } else if (i4 == 2) {
                    this.mEffect |= 256;
                } else if (i4 == 3) {
                    this.mEffect |= 2;
                } else if (i4 == 4) {
                    this.mEffect |= 4;
                } else if (i4 == 5) {
                    this.mEffect |= 8;
                } else if (i4 == 7) {
                    this.mEffect |= 16;
                } else if (i4 == 8) {
                    this.mEffect |= 32;
                } else if (i4 == 9) {
                    this.mEffect |= 64;
                } else if (i4 != 10 && i4 != 11) {
                    if (i4 == 22) {
                        this.mEffect &= -258;
                    } else if (i4 == 23) {
                        this.mEffect &= -3;
                    } else if (i4 == 24) {
                        this.mEffect &= -5;
                    } else if (i4 == 25) {
                        this.mEffect &= -9;
                    } else if (i4 == 27) {
                        this.mEffect &= -17;
                    } else if (i4 == 28) {
                        this.mEffect &= -33;
                    } else if (i4 == 29) {
                        this.mEffect &= -65;
                    } else if (i4 >= 30 && i4 <= 37) {
                        this.mForeColor = i4 - 30;
                    } else if (i4 == 38 || i4 == 48) {
                        int i5 = i2 + 2;
                        if (i5 <= i3) {
                            int i6 = iArr2[i2 + 1];
                            if (i6 == 2) {
                                int i7 = i2 + 4;
                                if (i7 > i3) {
                                    Log.w(EmulatorDebug.LOG_TAG, "Too few CSI" + i4 + ";2 RGB arguments");
                                } else {
                                    int i8 = iArr2[i5];
                                    int i9 = iArr2[i2 + 3];
                                    int i10 = iArr2[i7];
                                    if (i8 < 0 || i9 < 0 || i10 < 0 || i8 > 255 || i9 > 255 || i10 > 255) {
                                        finishSequenceAndLogError("Invalid RGB: " + i8 + "," + i9 + "," + i10);
                                    } else {
                                        int i11 = (i9 << 8) | (i8 << 16) | ViewCompat.MEASURED_STATE_MASK | i10;
                                        if (i4 == 38) {
                                            this.mForeColor = i11;
                                        } else {
                                            this.mBackColor = i11;
                                        }
                                    }
                                    i2 = i7;
                                }
                            } else if (i6 == 5) {
                                int i12 = iArr2[i5];
                                if (i12 >= 0 && i12 < 259) {
                                    if (i4 == 38) {
                                        this.mForeColor = i12;
                                    } else {
                                        this.mBackColor = i12;
                                    }
                                }
                                i2 = i5;
                            } else {
                                finishSequenceAndLogError("Invalid ISO-8613-3 SGR first argument: " + i6);
                            }
                        }
                    } else if (i4 == 39) {
                        this.mForeColor = 256;
                    } else if (i4 >= 40 && i4 <= 47) {
                        this.mBackColor = i4 - 40;
                    } else if (i4 == 49) {
                        this.mBackColor = 257;
                    } else if (i4 >= 90 && i4 <= 97) {
                        this.mForeColor = (i4 - 90) + 8;
                    } else if (i4 >= 100 && i4 <= 107) {
                        this.mBackColor = (i4 - 100) + 8;
                    }
                }
            } else if (i3 <= 0) {
                i4 = 0;
                if (i4 != 0) {
                }
            }
            i2++;
        }
    }

    private void doOsc(int i) {
        if (i == 7) {
            doOscSetTextParameters("\u0007");
        } else if (i == 27) {
            continueSequence(11);
        } else {
            collectOSCArgs(i);
        }
    }

    private void doOscEsc(int i) {
        if (i == 92) {
            doOscSetTextParameters("\u001b\\");
            return;
        }
        collectOSCArgs(27);
        collectOSCArgs(i);
        continueSequence(10);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01f4, code lost:
    
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01f6, code lost:
    
        r4 = r4 * 10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01f8, code lost:
    
        r4 = r4 + (r6 - '0');
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01fc, code lost:
    
        unknownSequence(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01ff, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0200, code lost:
    
        if (r17 == false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0203, code lost:
    
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0206, code lost:
    
        setTitle(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x002c, code lost:
    
        if (r6 == 1) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002f, code lost:
    
        if (r6 == 2) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0031, code lost:
    
        r12 = 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0034, code lost:
    
        if (r6 == 4) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
    
        if (r6 == 52) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003c, code lost:
    
        if (r6 == 104) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0040, code lost:
    
        if (r6 == 119) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0042, code lost:
    
        switch(r6) {
            case 10: goto L25;
            case 11: goto L25;
            case 12: goto L25;
            default: goto L22;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0045, code lost:
    
        switch(r6) {
            case 110: goto L24;
            case 111: goto L24;
            case 112: goto L24;
            default: goto L23;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0048, code lost:
    
        unknownParameter(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004d, code lost:
    
        r19.mColors.reset((r6 - 110) + 256);
        r19.mSession.onColorsChanged();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005d, code lost:
    
        r3 = (r6 - 10) + 256;
        r7 = 0;
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0138, code lost:
    
        if (r5.isEmpty() == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x013a, code lost:
    
        r19.mColors.reset();
        r19.mSession.onColorsChanged();
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0146, code lost:
    
        r1 = 0;
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x014c, code lost:
    
        if (r1 != r5.length()) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x014e, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0150, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0151, code lost:
    
        if (r3 != false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0159, code lost:
    
        if (r5.charAt(r1) != ';') goto L146;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x015b, code lost:
    
        r19.mColors.reset(java.lang.Integer.parseInt(r5.substring(r2, r1)));
        r19.mSession.onColorsChanged();
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x016d, code lost:
    
        if (r3 == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0171, code lost:
    
        r1 = r1 + 1;
        r2 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x017d, code lost:
    
        r19.mSession.clipboardText(new java.lang.String(android.util.Base64.decode(r5.substring(r5.indexOf(";") + 1), 0), java.nio.charset.StandardCharsets.UTF_8));
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0194, code lost:
    
        android.util.Log.e(com.termux.terminal.EmulatorDebug.LOG_TAG, "OSC Manipulate selection, invalid string '" + r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01a9, code lost:
    
        r1 = 0;
        r2 = -1;
        r4 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01b1, code lost:
    
        if (r1 != r5.length()) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01b3, code lost:
    
        r17 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01b6, code lost:
    
        r17 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01b8, code lost:
    
        if (r17 == false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01ba, code lost:
    
        r6 = ';';
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01bd, code lost:
    
        r6 = r5.charAt(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01c3, code lost:
    
        if (r6 != ';') goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01c5, code lost:
    
        if (r2 >= 0) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01c7, code lost:
    
        r2 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01cc, code lost:
    
        if (r4 < 0) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01d0, code lost:
    
        if (r4 <= 255) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01d3, code lost:
    
        r19.mColors.tryParseColor(r4, r5.substring(r2, r1));
        r19.mSession.onColorsChanged();
        r2 = -1;
        r4 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01e4, code lost:
    
        unknownSequence(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01e7, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01ea, code lost:
    
        if (r2 >= 0) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01ec, code lost:
    
        if (r2 >= 0) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01ee, code lost:
    
        if (r6 < '0') goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01f0, code lost:
    
        if (r6 > '9') goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01f2, code lost:
    
        if (r4 >= 0) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002a, code lost:
    
        if (r6 == 0) goto L108;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void doOscSetTextParameters(String str) {
        char c;
        String strSubstring = "";
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        char cCharAt;
        int i6 = 0;
        int i7 = -1;
        while (true) {
            c = ';';
            if (i6 >= this.mOSCOrDeviceControlArgs.length()) {
                strSubstring = "";
                break;
            }
            cCharAt = this.mOSCOrDeviceControlArgs.charAt(i6);
            if (cCharAt == ';') {
                strSubstring = this.mOSCOrDeviceControlArgs.substring(i6 + 1);
                break;
            } else {
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i7 = (i7 < 0 ? 0 : i7 * 10) + (cCharAt - '0');
                i6++;
            }
        }
        if (i7 < 0) {
            unknownSequence(i6 < this.mOSCOrDeviceControlArgs.length() ? this.mOSCOrDeviceControlArgs.charAt(i6) : ';');
            return;
        }
        i = 255;
        i2 = i7;
        i3 = 0;
        i4 = 0;
        while (true) {
            boolean z = i3 == strSubstring.length();
            if (z || strSubstring.charAt(i3) == c) {
                try {
                    String strSubstring2 = strSubstring.substring(i4, i3);
                    if ("?".equals(strSubstring2)) {
                        int i9 = this.mColors.mCurrentColors[i2];
                        int i10 = (((16711680 & i9) >> 16) * 65535) / i;
                        int i11 = (((i9 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8) * 65535) / i;
                        int i12 = ((i9 & 255) * 65535) / i;
                        TerminalOutput terminalOutput = this.mSession;
                        StringBuilder sb = new StringBuilder("\u001b]");
                        sb.append(i7);
                        sb.append(";rgb:");
                        i5 = i4;
                        try {
                            sb.append(String.format(Locale.US, "%04x", Integer.valueOf(i10)));
                            sb.append("/");
                            sb.append(String.format(Locale.US, "%04x", Integer.valueOf(i11)));
                            sb.append("/");
                            sb.append(String.format(Locale.US, "%04x", Integer.valueOf(i12)));
                        } catch (NumberFormatException unused) {
                            i4 = i5;
                            i3++;
                            c = ';';
                            i = 255;
                        }
                        try {
                            sb.append(str);
                            terminalOutput.write(sb.toString());
                        } catch (NumberFormatException unused2) {
                            i4 = i5;
                            i3++;
                            c = ';';
                            i = 255;
                        }
                    } else {
                        i5 = i4;
                        this.mColors.tryParseColor(i2, strSubstring2);
                        this.mSession.onColorsChanged();
                    }
                    i2++;
                    if (z || i2 > 258) {
                        break;
                    }
                    i3++;
                    i4 = i3;
                } catch (NumberFormatException unused3) {
                    i5 = i4;
                }
            } else {
                i3++;
            }
            if (i3 > strSubstring.length()) {
                break;
            }
        }
        finishSequence();
    }

    private void blockClear(int i, int i2, int i3) {
        blockClear(i, i2, i3, 1);
    }

    private void blockClear(int i, int i2, int i3, int i4) {
        this.mScreen.blockSet(i, i2, i3, i4, 32, getStyle());
    }

    private long getStyle() {
        return TextStyle.encode(this.mForeColor, this.mBackColor, this.mEffect);
    }

    private void doSetMode(boolean z) {
        int arg0 = getArg0(0);
        if (arg0 == 4) {
            this.mInsertMode = z;
        } else if (arg0 == 20) {
            unknownParameter(arg0);
        } else if (arg0 != 34) {
            unknownParameter(arg0);
        }
    }

    private void setCursorPosition(int i, int i2) {
        boolean zIsDecsetInternalBitSet = isDecsetInternalBitSet(4);
        int i3 = zIsDecsetInternalBitSet ? this.mTopMargin : 0;
        int i4 = zIsDecsetInternalBitSet ? this.mBottomMargin : this.mRows;
        int i5 = zIsDecsetInternalBitSet ? this.mLeftMargin : 0;
        setCursorRowCol(Math.max(i3, Math.min(i2 + i3, i4 - 1)), Math.max(i5, Math.min(i + i5, (zIsDecsetInternalBitSet ? this.mRightMargin : this.mColumns) - 1)));
    }

    private void scrollDownOneLine() {
        this.mScrollCounter++;
        int i = this.mLeftMargin;
        if (i != 0 || this.mRightMargin != this.mColumns) {
            TerminalBuffer terminalBuffer = this.mScreen;
            int i2 = this.mTopMargin;
            terminalBuffer.blockCopy(i, i2 + 1, this.mRightMargin - i, (this.mBottomMargin - i2) - 1, i, i2);
            TerminalBuffer terminalBuffer2 = this.mScreen;
            int i3 = this.mLeftMargin;
            terminalBuffer2.blockSet(i3, this.mBottomMargin - 1, this.mRightMargin - i3, 1, 32, this.mEffect);
            return;
        }
        this.mScreen.scrollDownOneLine(this.mTopMargin, this.mBottomMargin, getStyle());
    }

    private void parseArg(int i) {
        if (i < 48 || i > 57) {
            if (i == 59) {
                int i2 = this.mArgIndex;
                if (i2 < this.mArgs.length) {
                    this.mArgIndex = i2 + 1;
                }
                continueSequence(this.mEscapeState);
                return;
            }
            unknownSequence(i);
            return;
        }
        int i3 = this.mArgIndex;
        int[] iArr = this.mArgs;
        if (i3 < iArr.length) {
            int i4 = iArr[i3];
            int i5 = i - 48;
            if (i4 >= 0) {
                i5 += i4 * 10;
            }
            iArr[i3] = i5;
        }
        continueSequence(this.mEscapeState);
    }

    private int getArg0(int i) {
        return getArg(0, i, true);
    }

    private int getArg1(int i) {
        return getArg(1, i, true);
    }

    private int getArg(int i, int i2, boolean z) {
        int i3 = this.mArgs[i];
        return i3 >= 0 ? (i3 == 0 && z) ? i2 : i3 : i2;
    }

    private void collectOSCArgs(int i) {
        if (this.mOSCOrDeviceControlArgs.length() < 8192) {
            this.mOSCOrDeviceControlArgs.appendCodePoint(i);
            continueSequence(this.mEscapeState);
        } else {
            unknownSequence(i);
        }
    }

    private void unimplementedSequence(int i) {
        logError("Unimplemented sequence char '" + ((char) i) + "' (U+" + String.format("%04x", Integer.valueOf(i)) + ")");
        finishSequence();
    }

    private void unknownSequence(int i) {
        logError("Unknown sequence char '" + ((char) i) + "' (numeric value=" + i + ")");
        finishSequence();
    }

    private void unknownParameter(int i) {
        logError("Unknown parameter: " + i);
        finishSequence();
    }

    private void finishSequenceAndLogError(String str) {
        finishSequence();
    }

    private void finishSequence() {
        this.mEscapeState = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void emitCodePoint(int i) {
        int i2 = i;
        int i3;
        int i4;
        int i5;
        this.mLastEmittedCodePoint = i;
        if (this.mUseLineDrawingUsesG0) {
            if (!this.mUseLineDrawingG0) {
                i2 = i;
            } else if (i != 48) {
                switch (i) {
                    case 95:
                        i2 = 32;
                        break;
                    case 96:
                        i2 = 9670;
                        break;
                    case 97:
                        i2 = 9618;
                        break;
                    case 98:
                        i2 = 9225;
                        break;
                    case 99:
                        i2 = 9228;
                        break;
                    case 100:
                        i2 = 13;
                        break;
                    case 101:
                        i2 = 9226;
                        break;
                    case 102:
                        i2 = 176;
                        break;
                    case 103:
                        i2 = 177;
                        break;
                    case 104:
                        i2 = 10;
                        break;
                    case 105:
                        i2 = 9227;
                        break;
                    case 106:
                        i2 = 9496;
                        break;
                    case 107:
                        i2 = 9488;
                        break;
                    case 108:
                        i2 = 9484;
                        break;
                    case 109:
                        i2 = 9492;
                        break;
                    case 110:
                        i2 = 9532;
                        break;
                    case 111:
                        i2 = 9146;
                        break;
                    case 112:
                        i2 = 9147;
                        break;
                    case 113:
                        i2 = 9472;
                        break;
                    case 114:
                        i2 = 9148;
                        break;
                    case 115:
                        i2 = 9149;
                        break;
                    case 116:
                        i2 = 9500;
                        break;
                    case 117:
                        i2 = 9508;
                        break;
                    case 118:
                        i2 = 9524;
                        break;
                    case 119:
                        i2 = 9516;
                        break;
                    case 120:
                        i2 = 9474;
                        break;
                    case 121:
                        i2 = 8804;
                        break;
                    case 122:
                        i2 = 8805;
                        break;
                    case 123:
                        i2 = 960;
                        break;
                    case 124:
                        i2 = 8800;
                        break;
                    case 125:
                        i2 = 163;
                        break;
                    case 126:
                        i2 = 183;
                        break;
                    default:
                        i2 = i;
                        break;
                }
            } else {
                i2 = 9608;
            }
        } else if (!this.mUseLineDrawingG1) {
            i2 = i;
        }
        boolean zIsDecsetInternalBitSet = isDecsetInternalBitSet(8);
        int iWidth = WcWidth.width(i2);
        boolean z = this.mCursorCol == this.mRightMargin - 1;
        if (zIsDecsetInternalBitSet) {
            if (z && ((this.mAboutToAutoWrap && iWidth == 1) || iWidth == 2)) {
                this.mScreen.setLineWrap(this.mCursorRow);
                this.mCursorCol = this.mLeftMargin;
                int i6 = this.mCursorRow;
                if (i6 + 1 < this.mBottomMargin) {
                    this.mCursorRow = i6 + 1;
                } else {
                    scrollDownOneLine();
                }
            }
        } else if (z && iWidth == 2) {
            return;
        }
        if (this.mInsertMode && iWidth > 0 && (i4 = (i3 = this.mCursorCol) + iWidth) < (i5 = this.mRightMargin)) {
            TerminalBuffer terminalBuffer = this.mScreen;
            int i7 = this.mCursorRow;
            terminalBuffer.blockCopy(i3, i7, i5 - i4, 1, i4, i7);
        }
        this.mScreen.setChar(this.mCursorCol - ((iWidth > 0 || this.mCursorCol <= 0 || this.mAboutToAutoWrap) ? 0 : 1), this.mCursorRow, i2, getStyle());
        if (zIsDecsetInternalBitSet && iWidth > 0) {
            this.mAboutToAutoWrap = this.mCursorCol == this.mRightMargin - iWidth;
        }
        this.mCursorCol = Math.min(this.mCursorCol + iWidth, this.mRightMargin - 1);
    }

    private void setCursorRow(int i) {
        this.mCursorRow = i;
        this.mAboutToAutoWrap = false;
    }

    private void setCursorCol(int i) {
        this.mCursorCol = i;
        this.mAboutToAutoWrap = false;
    }

    private void setCursorColRespectingOriginMode(int i) {
        setCursorPosition(i, this.mCursorRow);
    }

    private void setCursorRowCol(int i, int i2) {
        this.mCursorRow = Math.max(0, Math.min(i, this.mRows - 1));
        this.mCursorCol = Math.max(0, Math.min(i2, this.mColumns - 1));
        this.mAboutToAutoWrap = false;
    }

    public int getScrollCounter() {
        return this.mScrollCounter;
    }

    public void clearScrollCounter() {
        this.mScrollCounter = 0;
    }

    public void reset() {
        this.mCursorStyle = 0;
        this.mArgIndex = 0;
        this.mContinueSequence = false;
        this.mEscapeState = 0;
        this.mInsertMode = false;
        this.mLeftMargin = 0;
        this.mTopMargin = 0;
        this.mBottomMargin = this.mRows;
        this.mRightMargin = this.mColumns;
        this.mAboutToAutoWrap = false;
        SavedScreenState savedScreenState = this.mSavedStateMain;
        this.mSavedStateAlt.mSavedForeColor = 256;
        savedScreenState.mSavedForeColor = 256;
        this.mForeColor = 256;
        SavedScreenState savedScreenState2 = this.mSavedStateMain;
        this.mSavedStateAlt.mSavedBackColor = 257;
        savedScreenState2.mSavedBackColor = 257;
        this.mBackColor = 257;
        setDefaultTabStops();
        this.mUseLineDrawingG1 = false;
        this.mUseLineDrawingG0 = false;
        this.mUseLineDrawingUsesG0 = true;
        SavedScreenState savedScreenState3 = this.mSavedStateMain;
        savedScreenState3.mSavedDecFlags = 0;
        savedScreenState3.mSavedEffect = 0;
        savedScreenState3.mSavedCursorCol = 0;
        savedScreenState3.mSavedCursorRow = 0;
        SavedScreenState savedScreenState4 = this.mSavedStateAlt;
        savedScreenState4.mSavedDecFlags = 0;
        savedScreenState4.mSavedEffect = 0;
        savedScreenState4.mSavedCursorCol = 0;
        savedScreenState4.mSavedCursorRow = 0;
        this.mCurrentDecSetFlags = 0;
        setDecsetinternalBit(8, true);
        setDecsetinternalBit(16, true);
        SavedScreenState savedScreenState5 = this.mSavedStateMain;
        SavedScreenState savedScreenState6 = this.mSavedStateAlt;
        int i = this.mCurrentDecSetFlags;
        savedScreenState6.mSavedDecFlags = i;
        savedScreenState5.mSavedDecFlags = i;
        this.mSavedDecSetFlags = i;
        this.mUtf8ToFollow = (byte) 0;
        this.mUtf8Index = (byte) 0;
        this.mColors.reset();
        this.mSession.onColorsChanged();
    }

    public String getSelectedText(int i, int i2, int i3, int i4) {
        return this.mScreen.getSelectedText(i, i2, i3, i4);
    }

    public String getTitle() {
        return this.mTitle;
    }

    private void setTitle(String str) {
        String str2 = this.mTitle;
        this.mTitle = str;
        if (Objects.equals(str2, str)) {
            return;
        }
        this.mSession.titleChanged(str2, str);
    }

    public void paste(String str) {
        String strReplaceAll = str.replaceAll("(\u001b|[\u0080-\u009f])", "").replaceAll("\r?\n", "\r");
        boolean zIsDecsetInternalBitSet = isDecsetInternalBitSet(1024);
        if (zIsDecsetInternalBitSet) {
            this.mSession.write("\u001b[200~");
        }
        this.mSession.write(strReplaceAll);
        if (zIsDecsetInternalBitSet) {
            this.mSession.write("\u001b[201~");
        }
    }

    static final class SavedScreenState {
        int mSavedBackColor;
        int mSavedCursorCol;
        int mSavedCursorRow;
        int mSavedDecFlags;
        int mSavedEffect;
        int mSavedForeColor;
        boolean mUseLineDrawingG0;
        boolean mUseLineDrawingG1;
        boolean mUseLineDrawingUsesG0 = true;

        SavedScreenState() {
        }
    }

    public String toString() {
        return "TerminalEmulator[size=" + this.mScreen.mColumns + "x" + this.mScreen.mScreenRows + ", margins={" + this.mTopMargin + "," + this.mRightMargin + "," + this.mBottomMargin + "," + this.mLeftMargin + "}]";
    }
}
