package com.termfu.view;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.PopupWindow;
import android.widget.Scroller;
import androidx.annotation.RequiresApi;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import com.fufufu.katrina.backup.C0978R;
import com.termfu.view.GestureAndScaleRecognizer;
import com.termux.terminal.EmulatorDebug;
import com.termux.terminal.KeyHandler;
import com.termux.terminal.TerminalBuffer;
import com.termux.terminal.TerminalEmulator;
import com.termux.terminal.TerminalSession;
import com.termux.terminal.WcWidth;
import com.topjohnwu.superuser.nio.FileSystemManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/* JADX INFO: loaded from: classes91.dex */
public final class TerminalView extends View {
    private static final boolean LOG_KEY_EVENTS = false;
    private boolean mAccessibilityEnabled;
    private ActionMode mActionMode;
    TerminalViewClient mClient;
    int mCombiningAccent;
    TerminalEmulator mEmulator;
    final GestureAndScaleRecognizer mGestureRecognizer;
    boolean mIsSelectingText;
    private int mMouseScrollStartX;
    private int mMouseScrollStartY;
    private long mMouseStartDownTime;
    TerminalRenderer mRenderer;
    float mScaleFactor;
    float mScrollRemainder;
    final Scroller mScroller;
    int mSelX1;
    int mSelX2;
    int mSelY1;
    int mSelY2;
    Drawable mSelectHandleLeft;
    Drawable mSelectHandleRight;
    private SelectionModifierCursorController mSelectionModifierCursorController;
    private final Runnable mShowFloatingToolbar;
    final int[] mTempCoords;
    Rect mTempRect;
    TerminalSession mTermSession;
    int mTopRow;

    private interface CursorController extends ViewTreeObserver.OnTouchModeChangeListener {
        void hide();

        boolean isActive();

        void onDetached();

        boolean onTouchEvent(MotionEvent motionEvent);

        void show();

        void updatePosition();

        void updatePosition(HandleView handleView, int i, int i2);
    }

    @Override // android.view.View
    @RequiresApi(api = 26)
    public int getAutofillType() {
        return 1;
    }

    @Override // android.view.View
    public boolean isOpaque() {
        return true;
    }

    @Override // android.view.View
    public boolean onCheckIsTextEditor() {
        return true;
    }

    public TerminalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsSelectingText = false;
        this.mSelX1 = -1;
        this.mSelX2 = -1;
        this.mSelY1 = -1;
        this.mSelY2 = -1;
        this.mTempCoords = new int[2];
        this.mScaleFactor = 1.0f;
        this.mMouseScrollStartX = -1;
        this.mMouseScrollStartY = -1;
        this.mMouseStartDownTime = -1L;
        this.mShowFloatingToolbar = new Runnable() { // from class: com.termfu.view.TerminalView.1
            @Override // java.lang.Runnable
            public void run() {
                if (TerminalView.this.mActionMode != null) {
                    TerminalView.this.mActionMode.hide(0L);
                }
            }
        };
        this.mGestureRecognizer = new GestureAndScaleRecognizer(context, new GestureAndScaleRecognizer.Listener() { // from class: com.termfu.view.TerminalView.2
            boolean scrolledWithFinger;

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                return false;
            }

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public boolean onDown(float f, float f2) {
                return false;
            }

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public boolean onUp(MotionEvent motionEvent) {
                TerminalView.this.mScrollRemainder = 0.0f;
                if (TerminalView.this.mEmulator != null && TerminalView.this.mEmulator.isMouseTrackingActive() && !TerminalView.this.mIsSelectingText && !this.scrolledWithFinger) {
                    TerminalView.this.sendMouseEventCode(motionEvent, 0, true);
                    TerminalView.this.sendMouseEventCode(motionEvent, 0, false);
                    return true;
                }
                this.scrolledWithFinger = false;
                return false;
            }

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                if (TerminalView.this.mEmulator == null) {
                    return true;
                }
                if (!TerminalView.this.mIsSelectingText) {
                    TerminalView.this.requestFocus();
                    if (TerminalView.this.mEmulator.isMouseTrackingActive() || motionEvent.isFromSource(8194)) {
                        return false;
                    }
                    TerminalView.this.mClient.onSingleTapUp(motionEvent);
                    return true;
                }
                TerminalView.this.stopTextSelectionMode();
                return true;
            }

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public boolean onScroll(MotionEvent motionEvent, float f, float f2) {
                if (TerminalView.this.mEmulator == null) {
                    return true;
                }
                if (TerminalView.this.mEmulator.isMouseTrackingActive() && motionEvent.isFromSource(8194)) {
                    TerminalView.this.sendMouseEventCode(motionEvent, 32, true);
                } else {
                    this.scrolledWithFinger = true;
                    float f3 = f2 + TerminalView.this.mScrollRemainder;
                    int scrollLines = (int) (f3 / TerminalView.this.mRenderer.mFontLineSpacing);
                    TerminalView.this.mScrollRemainder = f3 - (TerminalView.this.mRenderer.mFontLineSpacing * scrollLines);
                    TerminalView.this.doScroll(motionEvent, scrollLines);
                }
                return true;
            }

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public boolean onScale(float f, float f2, float f3) {
                if (TerminalView.this.mEmulator != null && !TerminalView.this.mIsSelectingText) {
                    TerminalView.this.mScaleFactor *= f3;
                    TerminalView terminalView = TerminalView.this;
                    terminalView.mScaleFactor = terminalView.mClient.onScale(TerminalView.this.mScaleFactor);
                }
                return true;
            }

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public boolean onFling(final MotionEvent motionEvent, float f, float f2) {
                if (TerminalView.this.mEmulator == null || !TerminalView.this.mScroller.isFinished()) {
                    return true;
                }
                final boolean zIsMouseTrackingActive = TerminalView.this.mEmulator.isMouseTrackingActive();
                if (zIsMouseTrackingActive) {
                    TerminalView.this.mScroller.fling(0, 0, 0, -((int) (f2 * 0.25f)), 0, 0, (-TerminalView.this.mEmulator.mRows) / 2, TerminalView.this.mEmulator.mRows / 2);
                } else {
                    TerminalView.this.mScroller.fling(0, TerminalView.this.mTopRow, 0, -((int) (f2 * 0.25f)), 0, 0, -TerminalView.this.mEmulator.getScreen().getActiveTranscriptRows(), 0);
                }
                TerminalView.this.post(new Runnable() { // from class: com.termfu.view.TerminalView.2.1
                    private int mLastY = 0;

                    @Override // java.lang.Runnable
                    public void run() {
                        if (zIsMouseTrackingActive != TerminalView.this.mEmulator.isMouseTrackingActive()) {
                            TerminalView.this.mScroller.abortAnimation();
                            return;
                        }
                        if (TerminalView.this.mScroller.isFinished()) {
                            return;
                        }
                        boolean zComputeScrollOffset = TerminalView.this.mScroller.computeScrollOffset();
                        int currY = TerminalView.this.mScroller.getCurrY();
                        TerminalView.this.doScroll(motionEvent, currY - (zIsMouseTrackingActive ? this.mLastY : TerminalView.this.mTopRow));
                        this.mLastY = currY;
                        if (zComputeScrollOffset) {
                            TerminalView.this.post(this);
                        }
                    }
                });
                return true;
            }

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public void onLongPress(MotionEvent motionEvent) {
                if (TerminalView.this.mGestureRecognizer.isInProgress() || TerminalView.this.mClient.onLongPress(motionEvent) || TerminalView.this.mIsSelectingText) {
                    return;
                }
                TerminalView.this.performHapticFeedback(0);
                TerminalView.this.startSelectingText(motionEvent);
            }
        });
        this.mScroller = new Scroller(context);
        this.mAccessibilityEnabled = ((AccessibilityManager) context.getSystemService("accessibility")).isEnabled();
    }

    public void setOnKeyListener(TerminalViewClient terminalViewClient) {
        this.mClient = terminalViewClient;
    }

    public boolean attachSession(TerminalSession terminalSession) {
        if (terminalSession == this.mTermSession) {
            return false;
        }
        this.mTopRow = 0;
        this.mTermSession = terminalSession;
        this.mEmulator = null;
        this.mCombiningAccent = 0;
        updateSize();
        setVerticalScrollBarEnabled(true);
        return true;
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        if (getProperties().getProperty("enforce-char-based-input", "false").equals("true")) {
            editorInfo.inputType = 524432;
        } else {
            editorInfo.inputType = 0;
        }
        editorInfo.imeOptions = FileSystemManager.MODE_APPEND;
        return new BaseInputConnection(this, true) { // from class: com.termfu.view.TerminalView.3
            @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
            public boolean finishComposingText() {
                super.finishComposingText();
                sendTextToTerminal(getEditable());
                getEditable().clear();
                return true;
            }

            @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
            public boolean commitText(CharSequence charSequence, int i) {
                super.commitText(charSequence, i);
                if (TerminalView.this.mEmulator == null) {
                    return true;
                }
                Editable editable = getEditable();
                sendTextToTerminal(editable);
                editable.clear();
                return true;
            }

            @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
            public boolean deleteSurroundingText(int i, int i2) {
                KeyEvent keyEvent = new KeyEvent(0, 67);
                for (int i3 = 0; i3 < i; i3++) {
                    sendKeyEvent(keyEvent);
                }
                return super.deleteSurroundingText(i, i2);
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            void sendTextToTerminal(CharSequence charSequence) {
                boolean z;
                TerminalView.this.stopTextSelectionMode();
                int length = charSequence.length();
                int i = 0;
                while (i < length) {
                    char cCharAt = charSequence.charAt(i);
                    boolean zIsHighSurrogate = Character.isHighSurrogate(cCharAt);
                    int codePoint = cCharAt;
                    if (zIsHighSurrogate) {
                        i++;
                        codePoint = i < length ? Character.toCodePoint(cCharAt, charSequence.charAt(i)) : TerminalEmulator.UNICODE_REPLACEMENT_CHAR;
                    }
                    if (codePoint > 31 || codePoint == 27) {
                        z = false;
                    } else {
                        int i2 = codePoint;
                        if (codePoint == 10) {
                            i2 = 13;
                        }
                        switch (i2) {
                            case 28:
                                codePoint = 92;
                                break;
                            case 29:
                                codePoint = 93;
                                break;
                            case 30:
                                codePoint = 94;
                                break;
                            case 31:
                                codePoint = 95;
                                break;
                            default:
                                codePoint = i2 + 96;
                                break;
                        }
                        z = true;
                    }
                    TerminalView.this.inputCodePoint(codePoint, z, false);
                    i++;
                }
            }
        };
    }

    @Override // android.view.View
    protected int computeVerticalScrollRange() {
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            return 1;
        }
        return terminalEmulator.getScreen().getActiveRows();
    }

    @Override // android.view.View
    protected int computeVerticalScrollExtent() {
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            return 1;
        }
        return terminalEmulator.mRows;
    }

    @Override // android.view.View
    protected int computeVerticalScrollOffset() {
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            return 1;
        }
        return (terminalEmulator.getScreen().getActiveRows() + this.mTopRow) - this.mEmulator.mRows;
    }

    public void onScreenUpdated() {
        boolean z;
        int i;
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            return;
        }
        int activeTranscriptRows = terminalEmulator.getScreen().getActiveTranscriptRows();
        int i2 = -activeTranscriptRows;
        if (this.mTopRow < i2) {
            this.mTopRow = i2;
        }
        if (this.mIsSelectingText) {
            int scrollCounter = this.mEmulator.getScrollCounter();
            int i3 = this.mTopRow;
            if ((-i3) + scrollCounter > activeTranscriptRows) {
                stopTextSelectionMode();
                z = false;
            } else {
                z = true;
                this.mTopRow = i3 - scrollCounter;
                this.mSelY1 -= scrollCounter;
                this.mSelY2 -= scrollCounter;
            }
        } else {
            z = false;
        }
        if (!z && (i = this.mTopRow) != 0) {
            if (i < -3) {
                awakenScrollBars();
            }
            this.mTopRow = 0;
        }
        this.mEmulator.clearScrollCounter();
        invalidate();
        if (this.mAccessibilityEnabled) {
            setContentDescription(getText());
        }
    }

    public void setTextSize(int i) {
        TerminalRenderer terminalRenderer = this.mRenderer;
        this.mRenderer = new TerminalRenderer(i, terminalRenderer == null ? Typeface.MONOSPACE : terminalRenderer.mTypeface);
        updateSize();
    }

    public void setTypeface(Typeface typeface) {
        this.mRenderer = new TerminalRenderer(this.mRenderer.mTextSize, typeface);
        updateSize();
        invalidate();
    }

    void sendMouseEventCode(MotionEvent motionEvent, int i, boolean z) {
        int x = ((int) (motionEvent.getX() / this.mRenderer.mFontWidth)) + 1;
        int y = ((int) ((motionEvent.getY() - this.mRenderer.mFontLineSpacingAndAscent) / this.mRenderer.mFontLineSpacing)) + 1;
        if (z && (i == 65 || i == 64)) {
            if (this.mMouseStartDownTime == motionEvent.getDownTime()) {
                x = this.mMouseScrollStartX;
                y = this.mMouseScrollStartY;
            } else {
                this.mMouseStartDownTime = motionEvent.getDownTime();
                this.mMouseScrollStartX = x;
                this.mMouseScrollStartY = y;
            }
        }
        this.mEmulator.sendMouseEvent(i, x, y, z);
    }

    void doScroll(MotionEvent motionEvent, int i) {
        boolean z = i < 0;
        int iAbs = Math.abs(i);
        for (int i2 = 0; i2 < iAbs; i2++) {
            if (this.mEmulator.isMouseTrackingActive()) {
                sendMouseEventCode(motionEvent, z ? 64 : 65, true);
            } else if (this.mEmulator.isAlternateBufferActive()) {
                handleKeyCode(z ? 19 : 20, 0);
            } else {
                this.mTopRow = Math.min(0, Math.max(-this.mEmulator.getScreen().getActiveTranscriptRows(), this.mTopRow + (z ? -1 : 1)));
                if (!awakenScrollBars()) {
                    invalidate();
                }
            }
        }
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (this.mEmulator == null || !motionEvent.isFromSource(8194) || motionEvent.getAction() != 8) {
            return false;
        }
        doScroll(motionEvent, motionEvent.getAxisValue(9) > 0.0f ? -3 : 3);
        return true;
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    @TargetApi(23)
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mEmulator == null) {
            return true;
        }
        int action = motionEvent.getAction();
        if (this.mIsSelectingText) {
            updateFloatingToolbarVisibility(motionEvent);
            this.mGestureRecognizer.onTouchEvent(motionEvent);
            return true;
        }
        if (motionEvent.isFromSource(8194)) {
            if (motionEvent.isButtonPressed(2)) {
                if (action == 0) {
                    showContextMenu();
                }
                return true;
            }
            if (motionEvent.isButtonPressed(4)) {
                ClipData primaryClip = ((ClipboardManager) getContext().getSystemService("clipboard")).getPrimaryClip();
                if (primaryClip != null) {
                    CharSequence charSequenceCoerceToText = primaryClip.getItemAt(0).coerceToText(getContext());
                    if (!TextUtils.isEmpty(charSequenceCoerceToText)) {
                        this.mEmulator.paste(charSequenceCoerceToText.toString());
                    }
                }
            } else if (this.mEmulator.isMouseTrackingActive()) {
                int action2 = motionEvent.getAction();
                if (action2 == 0 || action2 == 1) {
                    sendMouseEventCode(motionEvent, 0, motionEvent.getAction() == 0);
                } else if (action2 == 2) {
                    sendMouseEventCode(motionEvent, 32, true);
                }
                return true;
            }
        }
        this.mGestureRecognizer.onTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.View
    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        Properties properties = getProperties();
        if (i == 4) {
            if (this.mIsSelectingText) {
                stopTextSelectionMode();
                return true;
            }
            if (this.mClient.shouldBackButtonBeMappedToEscape()) {
                int action = keyEvent.getAction();
                if (action == 0) {
                    return onKeyDown(i, keyEvent);
                }
                if (action == 1) {
                    return onKeyUp(i, keyEvent);
                }
            }
        } else if (properties.getProperty("ctrl-space-workaround", "false").equals("true") && i == 62 && keyEvent.isCtrlPressed()) {
            return onKeyDown(i, keyEvent);
        }
        return super.onKeyPreIme(i, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mEmulator == null) {
            return true;
        }
        stopTextSelectionMode();
        if (this.mClient.onKeyDown(i, keyEvent, this.mTermSession)) {
            invalidate();
            return true;
        }
        if (keyEvent.isSystem() && (!this.mClient.shouldBackButtonBeMappedToEscape() || i != 4)) {
            return super.onKeyDown(i, keyEvent);
        }
        if (keyEvent.getAction() == 2 && i == 0) {
            this.mTermSession.write(keyEvent.getCharacters());
            return true;
        }
        int metaState = keyEvent.getMetaState();
        boolean z = keyEvent.isCtrlPressed() || this.mClient.readControlKey();
        boolean z2 = (metaState & 16) != 0 || this.mClient.readAltKey();
        boolean z3 = (metaState & 32) != 0;
        int i2 = z ? 1073741824 : 0;
        if (keyEvent.isAltPressed() || z2) {
            i2 |= Integer.MIN_VALUE;
        }
        if (keyEvent.isShiftPressed()) {
            i2 |= 536870912;
        }
        if (keyEvent.isNumLockOn()) {
            i2 |= 268435456;
        }
        if (!keyEvent.isFunctionPressed() && handleKeyCode(i, i2)) {
            return true;
        }
        int unicodeChar = keyEvent.getUnicodeChar((~(z3 ? 28672 : 28690)) & keyEvent.getMetaState());
        if (unicodeChar == 0) {
            return false;
        }
        int i3 = this.mCombiningAccent;
        if ((unicodeChar & Integer.MIN_VALUE) != 0) {
            if (i3 != 0) {
                inputCodePoint(i3, z, z2);
            }
            this.mCombiningAccent = unicodeChar & Integer.MAX_VALUE;
        } else {
            if (i3 != 0) {
                int deadChar = KeyCharacterMap.getDeadChar(i3, unicodeChar);
                if (deadChar > 0) {
                    unicodeChar = deadChar;
                }
                this.mCombiningAccent = 0;
            }
            inputCodePoint(unicodeChar, z, z2);
        }
        if (this.mCombiningAccent != i3) {
            invalidate();
        }
        return true;
    }

    public void inputCodePoint(int i, boolean z, boolean z2) {
        int i2;
        if (this.mTermSession == null) {
            return;
        }
        boolean z3 = z || this.mClient.readControlKey();
        boolean z4 = z2 || this.mClient.readAltKey();
        if (this.mClient.onCodePoint(i, z3, this.mTermSession)) {
            return;
        }
        if (z3) {
            if (i >= 97 && i <= 122) {
                i = (i - 97) + 1;
            } else if (i >= 65 && i <= 90) {
                i = (i - 65) + 1;
            } else if (i == 32 || i == 50) {
                i = 0;
            } else if (i == 91 || i == 51) {
                i = 27;
            } else if (i == 92 || i == 52) {
                i = 28;
            } else if (i == 93 || i == 53) {
                i = 29;
            } else if (i == 94 || i == 54) {
                i = 30;
            } else if (i == 95 || i == 55 || i == 47) {
                i = 31;
            } else if (i == 56) {
                i = 127;
            }
        }
        if (i > -1) {
            this.mTermSession.writeCodePoint(z4, i != 710 ? i != 715 ? i != 732 ? i : 126 : 96 : 94);
        }
    }

    public boolean handleKeyCode(int i, int i2) {
        TerminalEmulator emulator = this.mTermSession.getEmulator();
        String code = KeyHandler.getCode(i, i2, emulator.isCursorKeysApplicationMode(), emulator.isKeypadApplicationMode());
        if (code == null) {
            return false;
        }
        this.mTermSession.write(code);
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.mEmulator == null) {
            return true;
        }
        if (this.mClient.onKeyUp(i, keyEvent)) {
            invalidate();
            return true;
        }
        if (keyEvent.isSystem()) {
            return super.onKeyUp(i, keyEvent);
        }
        return true;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        updateSize();
    }

    public void updateSize() {
        int width = getWidth();
        int height = getHeight();
        if (width == 0 || height == 0 || this.mTermSession == null) {
            return;
        }
        int iMax = Math.max(4, (int) (width / this.mRenderer.mFontWidth));
        int iMax2 = Math.max(4, (height - this.mRenderer.mFontLineSpacingAndAscent) / this.mRenderer.mFontLineSpacing);
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator != null && iMax == terminalEmulator.mColumns && iMax2 == this.mEmulator.mRows) {
            return;
        }
        this.mTermSession.updateSize(iMax, iMax2);
        this.mEmulator = this.mTermSession.getEmulator();
        this.mTopRow = 0;
        scrollTo(0, 0);
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            canvas.drawColor(ViewCompat.MEASURED_STATE_MASK);
            return;
        }
        this.mRenderer.render(terminalEmulator, canvas, this.mTopRow, this.mSelY1, this.mSelY2, this.mSelX1, this.mSelX2);
        SelectionModifierCursorController selectionController = getSelectionController();
        if (selectionController == null || !selectionController.isActive()) {
            return;
        }
        selectionController.updatePosition();
    }

    @TargetApi(23)
    public void startSelectingText(MotionEvent motionEvent) {
        int x = (int) (motionEvent.getX() / this.mRenderer.mFontWidth);
        int y = ((int) ((motionEvent.getY() + (motionEvent.isFromSource(8194) ? 0 : -40)) / this.mRenderer.mFontLineSpacing)) + this.mTopRow;
        this.mSelX2 = x;
        this.mSelX1 = x;
        this.mSelY2 = y;
        this.mSelY1 = y;
        TerminalBuffer screen = this.mEmulator.getScreen();
        int i = this.mSelX1;
        int i2 = this.mSelY1;
        if (!" ".equals(screen.getSelectedText(i, i2, i, i2))) {
            while (true) {
                int i3 = this.mSelX1;
                if (i3 <= 0) {
                    break;
                }
                int i4 = this.mSelY1;
                if ("".equals(screen.getSelectedText(i3 - 1, i4, i3 - 1, i4))) {
                    break;
                } else {
                    this.mSelX1--;
                }
            }
            while (this.mSelX2 < this.mEmulator.mColumns - 1) {
                int i5 = this.mSelX2;
                int i6 = this.mSelY1;
                if ("".equals(screen.getSelectedText(i5 + 1, i6, i5 + 1, i6))) {
                    break;
                } else {
                    this.mSelX2++;
                }
            }
        }
        startTextSelectionMode();
    }

    public TerminalSession getCurrentSession() {
        return this.mTermSession;
    }

    private CharSequence getText() {
        return this.mEmulator.getScreen().getSelectedText(0, this.mTopRow, this.mEmulator.mColumns, this.mTopRow + this.mEmulator.mRows);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mSelectionModifierCursorController != null) {
            getViewTreeObserver().addOnTouchModeChangeListener(this.mSelectionModifierCursorController);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mSelectionModifierCursorController != null) {
            getViewTreeObserver().removeOnTouchModeChangeListener(this.mSelectionModifierCursorController);
            this.mSelectionModifierCursorController.onDetached();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCursorX(float f) {
        return (int) (f / this.mRenderer.mFontWidth);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCursorY(float f) {
        return (int) (((f - 40.0f) / this.mRenderer.mFontLineSpacing) + this.mTopRow);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPointX(int i) {
        if (i > this.mEmulator.mColumns) {
            i = this.mEmulator.mColumns;
        }
        return Math.round(i * this.mRenderer.mFontWidth);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPointY(int i) {
        return Math.round((i - this.mTopRow) * this.mRenderer.mFontLineSpacing);
    }

    private class HandleView extends View {
        public static final int LEFT = 0;
        public static final int RIGHT = 2;
        private PopupWindow mContainer;
        private CursorController mController;
        private Drawable mDrawable;
        private int mHandleHeight;
        int mHandleWidth;
        private float mHotspotX;
        private float mHotspotY;
        private boolean mIsDragging;
        private int mLastParentX;
        private int mLastParentY;
        private long mLastTime;
        private int mOrientation;
        private final int mOrigOrient;
        private int mPointX;
        private int mPointY;
        private float mTouchOffsetY;
        private float mTouchToWindowOffsetX;
        private float mTouchToWindowOffsetY;

        public HandleView(CursorController cursorController, int i) {
            super(TerminalView.this.getContext());
            this.mController = cursorController;
            PopupWindow popupWindow = new PopupWindow(TerminalView.this.getContext(), (AttributeSet) null, R.attr.textSelectHandleWindowStyle);
            this.mContainer = popupWindow;
            popupWindow.setSplitTouchEnabled(true);
            this.mContainer.setClippingEnabled(false);
            this.mContainer.setWindowLayoutType(PointerIconCompat.TYPE_HAND);
            this.mContainer.setWidth(-2);
            this.mContainer.setHeight(-2);
            this.mOrigOrient = i;
            setOrientation(i);
        }

        public void setOrientation(int i) {
            int intrinsicWidth;
            this.mOrientation = i;
            if (i == 0) {
                if (TerminalView.this.mSelectHandleLeft == null) {
                    TerminalView.this.mSelectHandleLeft = getContext().getDrawable(C0978R.drawable.termfu_text_select_handle_left_material);
                }
                Drawable drawable = TerminalView.this.mSelectHandleLeft;
                this.mDrawable = drawable;
                intrinsicWidth = drawable.getIntrinsicWidth();
                this.mHotspotX = (intrinsicWidth * 3) / 4;
            } else if (i != 2) {
                intrinsicWidth = 0;
            } else {
                if (TerminalView.this.mSelectHandleRight == null) {
                    TerminalView.this.mSelectHandleRight = getContext().getDrawable(C0978R.drawable.termfu_text_select_handle_right_material);
                }
                Drawable drawable2 = TerminalView.this.mSelectHandleRight;
                this.mDrawable = drawable2;
                intrinsicWidth = drawable2.getIntrinsicWidth();
                this.mHotspotX = intrinsicWidth / 4;
            }
            this.mHandleHeight = this.mDrawable.getIntrinsicHeight();
            this.mHandleWidth = intrinsicWidth;
            this.mTouchOffsetY = (-this.mHandleHeight) * 0.3f;
            this.mHotspotY = 0.0f;
            invalidate();
        }

        public void changeOrientation(int i) {
            if (this.mOrientation != i) {
                setOrientation(i);
            }
        }

        @Override // android.view.View
        public void onMeasure(int i, int i2) {
            setMeasuredDimension(this.mDrawable.getIntrinsicWidth(), this.mDrawable.getIntrinsicHeight());
        }

        public void show() {
            if (!isPositionVisible()) {
                hide();
                return;
            }
            this.mContainer.setContentView(this);
            int[] iArr = TerminalView.this.mTempCoords;
            TerminalView.this.getLocationInWindow(iArr);
            int i = iArr[0] + this.mPointX;
            iArr[0] = i;
            int i2 = iArr[1] + this.mPointY;
            iArr[1] = i2;
            this.mContainer.showAtLocation(TerminalView.this, 0, i, i2);
        }

        public void hide() {
            this.mIsDragging = false;
            this.mContainer.dismiss();
        }

        public boolean isShowing() {
            return this.mContainer.isShowing();
        }

        private void checkChangedOrientation(int i, boolean z) {
            if (this.mIsDragging || z) {
                long jCurrentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
                if (jCurrentThreadTimeMillis - this.mLastTime >= 50 || z) {
                    this.mLastTime = jCurrentThreadTimeMillis;
                    TerminalView terminalView = TerminalView.this;
                    int left = terminalView.getLeft();
                    int width = terminalView.getWidth();
                    int top = terminalView.getTop();
                    int height = terminalView.getHeight();
                    if (TerminalView.this.mTempRect == null) {
                        TerminalView.this.mTempRect = new Rect();
                    }
                    Rect rect = TerminalView.this.mTempRect;
                    rect.left = left + TerminalView.this.getPaddingLeft();
                    rect.top = top + TerminalView.this.getPaddingTop();
                    rect.right = width - TerminalView.this.getPaddingRight();
                    rect.bottom = height - TerminalView.this.getPaddingBottom();
                    ViewParent parent = terminalView.getParent();
                    if (parent == null || !parent.getChildVisibleRect(terminalView, rect, null)) {
                        return;
                    }
                    if (i - this.mHandleWidth < rect.left) {
                        changeOrientation(2);
                    } else if (i + this.mHandleWidth > rect.right) {
                        changeOrientation(0);
                    } else {
                        changeOrientation(this.mOrigOrient);
                    }
                }
            }
        }

        private boolean isPositionVisible() {
            if (this.mIsDragging) {
                return true;
            }
            TerminalView terminalView = TerminalView.this;
            int width = terminalView.getWidth();
            int height = terminalView.getHeight();
            if (TerminalView.this.mTempRect == null) {
                TerminalView.this.mTempRect = new Rect();
            }
            Rect rect = TerminalView.this.mTempRect;
            rect.left = TerminalView.this.getPaddingLeft() + 0;
            rect.top = TerminalView.this.getPaddingTop() + 0;
            rect.right = width - TerminalView.this.getPaddingRight();
            rect.bottom = height - TerminalView.this.getPaddingBottom();
            ViewParent parent = terminalView.getParent();
            if (parent != null && parent.getChildVisibleRect(terminalView, rect, null)) {
                int[] iArr = TerminalView.this.mTempCoords;
                terminalView.getLocationInWindow(iArr);
                int i = iArr[0] + this.mPointX + ((int) this.mHotspotX);
                int i2 = iArr[1] + this.mPointY + ((int) this.mHotspotY);
                if (i >= rect.left && i <= rect.right && i2 >= rect.top && i2 <= rect.bottom) {
                    return true;
                }
            }
            return false;
        }

        private void moveTo(int i, int i2, boolean z) {
            float f = this.mHotspotX;
            checkChangedOrientation(i, z);
            float f2 = i;
            if (!isShowing()) {
                f = this.mHotspotX;
            }
            this.mPointX = (int) (f2 - f);
            this.mPointY = i2;
            if (isPositionVisible()) {
                int[] iArr = null;
                if (isShowing()) {
                    iArr = TerminalView.this.mTempCoords;
                    TerminalView.this.getLocationInWindow(iArr);
                    this.mContainer.update(iArr[0] + this.mPointX, iArr[1] + this.mPointY, getWidth(), getHeight());
                } else {
                    show();
                }
                if (this.mIsDragging) {
                    if (iArr == null) {
                        iArr = TerminalView.this.mTempCoords;
                        TerminalView.this.getLocationInWindow(iArr);
                    }
                    int i3 = iArr[0];
                    if (i3 == this.mLastParentX && iArr[1] == this.mLastParentY) {
                        return;
                    }
                    this.mTouchToWindowOffsetX += i3 - this.mLastParentX;
                    float f3 = this.mTouchToWindowOffsetY;
                    int i4 = iArr[1];
                    this.mTouchToWindowOffsetY = f3 + (i4 - this.mLastParentY);
                    this.mLastParentX = i3;
                    this.mLastParentY = i4;
                    return;
                }
                return;
            }
            if (isShowing()) {
                hide();
            }
        }

        @Override // android.view.View
        public void onDraw(Canvas canvas) {
            this.mDrawable.setBounds(0, 0, this.mDrawable.getIntrinsicWidth(), this.mDrawable.getIntrinsicHeight());
            this.mDrawable.draw(canvas);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x003b  */
        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean onTouchEvent(MotionEvent motionEvent) {
            TerminalView.this.updateFloatingToolbarVisibility(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                float rawX = motionEvent.getRawX();
                float rawY = motionEvent.getRawY();
                this.mTouchToWindowOffsetX = rawX - this.mPointX;
                this.mTouchToWindowOffsetY = rawY - this.mPointY;
                int[] iArr = TerminalView.this.mTempCoords;
                TerminalView.this.getLocationInWindow(iArr);
                this.mLastParentX = iArr[0];
                this.mLastParentY = iArr[1];
                this.mIsDragging = true;
            } else if (actionMasked == 1) {
                this.mIsDragging = false;
            } else if (actionMasked == 2) {
                this.mController.updatePosition(this, Math.round((motionEvent.getRawX() - this.mTouchToWindowOffsetX) + this.mHotspotX), Math.round((motionEvent.getRawY() - this.mTouchToWindowOffsetY) + this.mHotspotY + this.mTouchOffsetY));
            } else if (actionMasked == 3) {
            }
            return true;
        }

        public boolean isDragging() {
            return this.mIsDragging;
        }

        void positionAtCursor(int i, int i2, boolean z) {
            moveTo(TerminalView.this.getPointX(i), TerminalView.this.getPointY(i2 + 1), z);
        }
    }

    private class SelectionModifierCursorController implements CursorController {
        private HandleView mEndHandle;
        private final int mHandleHeight;
        private boolean mIsShowing;
        private HandleView mStartHandle;

        @Override // com.termfu.view.TerminalView.CursorController
        public void onDetached() {
        }

        @Override // com.termfu.view.TerminalView.CursorController
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        SelectionModifierCursorController() {
            this.mStartHandle = TerminalView.this.new HandleView(this, 0);
            this.mEndHandle = TerminalView.this.new HandleView(this, 2);
            this.mHandleHeight = Math.max(this.mStartHandle.mHandleHeight, this.mEndHandle.mHandleHeight);
        }

        @Override // com.termfu.view.TerminalView.CursorController
        public void show() {
            this.mIsShowing = true;
            this.mStartHandle.positionAtCursor(TerminalView.this.mSelX1, TerminalView.this.mSelY1, true);
            this.mEndHandle.positionAtCursor(TerminalView.this.mSelX2 + 1, TerminalView.this.mSelY2, true);
            final ActionMode.Callback callback = new ActionMode.Callback() { // from class: com.termfu.view.TerminalView.SelectionModifierCursorController.1
                @Override // android.view.ActionMode.Callback
                public void onDestroyActionMode(ActionMode actionMode) {
                }

                @Override // android.view.ActionMode.Callback
                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                @Override // android.view.ActionMode.Callback
                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    ClipboardManager clipboardManager = (ClipboardManager) TerminalView.this.getContext().getSystemService("clipboard");
                    menu.add(0, 1, 0, C0978R.string.termfu_copy_text).setShowAsAction(5);
                    menu.add(0, 2, 0, C0978R.string.termfu_paste_text).setEnabled(clipboardManager.hasPrimaryClip()).setShowAsAction(5);
                    menu.add(0, 3, 0, C0978R.string.termfu_text_selection_more);
                    return true;
                }

                @Override // android.view.ActionMode.Callback
                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                    if (!TerminalView.this.mIsSelectingText) {
                        return true;
                    }
                    int itemId = menuItem.getItemId();
                    if (itemId == 1) {
                        TerminalView.this.mTermSession.clipboardText(TerminalView.this.mEmulator.getSelectedText(TerminalView.this.mSelX1, TerminalView.this.mSelY1, TerminalView.this.mSelX2, TerminalView.this.mSelY2).trim());
                    } else if (itemId == 2) {
                        ClipData primaryClip = ((ClipboardManager) TerminalView.this.getContext().getSystemService("clipboard")).getPrimaryClip();
                        if (primaryClip != null) {
                            CharSequence charSequenceCoerceToText = primaryClip.getItemAt(0).coerceToText(TerminalView.this.getContext());
                            if (!TextUtils.isEmpty(charSequenceCoerceToText)) {
                                TerminalView.this.mEmulator.paste(charSequenceCoerceToText.toString());
                            }
                        }
                    } else if (itemId == 3) {
                        TerminalView.this.showContextMenu();
                    }
                    TerminalView.this.stopTextSelectionMode();
                    return true;
                }
            };
            TerminalView terminalView = TerminalView.this;
            terminalView.mActionMode = terminalView.startActionMode(new ActionMode.Callback2() { // from class: com.termfu.view.TerminalView.SelectionModifierCursorController.2
                @Override // android.view.ActionMode.Callback
                public void onDestroyActionMode(ActionMode actionMode) {
                }

                @Override // android.view.ActionMode.Callback
                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                @Override // android.view.ActionMode.Callback
                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    return callback.onCreateActionMode(actionMode, menu);
                }

                @Override // android.view.ActionMode.Callback
                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                    return callback.onActionItemClicked(actionMode, menuItem);
                }

                @Override // android.view.ActionMode.Callback2
                public void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
                    int iRound = Math.round(TerminalView.this.mSelX1 * TerminalView.this.mRenderer.mFontWidth);
                    int iRound2 = Math.round(TerminalView.this.mSelX2 * TerminalView.this.mRenderer.mFontWidth);
                    int iRound3 = Math.round(((TerminalView.this.mSelY1 - 1) - TerminalView.this.mTopRow) * TerminalView.this.mRenderer.mFontLineSpacing);
                    int iRound4 = Math.round(((TerminalView.this.mSelY2 + 1) - TerminalView.this.mTopRow) * TerminalView.this.mRenderer.mFontLineSpacing);
                    if (iRound > iRound2) {
                        iRound2 = iRound;
                        iRound = iRound2;
                    }
                    rect.set(iRound, iRound3 + SelectionModifierCursorController.this.mHandleHeight, iRound2, iRound4 + SelectionModifierCursorController.this.mHandleHeight);
                }
            }, 1);
        }

        @Override // com.termfu.view.TerminalView.CursorController
        public void hide() {
            this.mStartHandle.hide();
            this.mEndHandle.hide();
            this.mIsShowing = false;
            if (TerminalView.this.mActionMode != null) {
                TerminalView.this.mActionMode.finish();
            }
        }

        @Override // com.termfu.view.TerminalView.CursorController
        public boolean isActive() {
            return this.mIsShowing;
        }

        @Override // com.termfu.view.TerminalView.CursorController
        public void updatePosition(HandleView handleView, int i, int i2) {
            TerminalBuffer screen = TerminalView.this.mEmulator.getScreen();
            int activeRows = screen.getActiveRows() - TerminalView.this.mEmulator.mRows;
            if (handleView == this.mStartHandle) {
                TerminalView terminalView = TerminalView.this;
                terminalView.mSelX1 = terminalView.getCursorX(i);
                TerminalView terminalView2 = TerminalView.this;
                terminalView2.mSelY1 = terminalView2.getCursorY(i2);
                if (TerminalView.this.mSelX1 < 0) {
                    TerminalView.this.mSelX1 = 0;
                }
                int i3 = -activeRows;
                if (TerminalView.this.mSelY1 < i3) {
                    TerminalView.this.mSelY1 = i3;
                } else if (TerminalView.this.mSelY1 > TerminalView.this.mEmulator.mRows - 1) {
                    TerminalView.this.mSelY1 = TerminalView.this.mEmulator.mRows - 1;
                }
                if (TerminalView.this.mSelY1 > TerminalView.this.mSelY2) {
                    TerminalView terminalView3 = TerminalView.this;
                    terminalView3.mSelY1 = terminalView3.mSelY2;
                }
                if (TerminalView.this.mSelY1 == TerminalView.this.mSelY2 && TerminalView.this.mSelX1 > TerminalView.this.mSelX2) {
                    TerminalView terminalView4 = TerminalView.this;
                    terminalView4.mSelX1 = terminalView4.mSelX2;
                }
                if (!TerminalView.this.mEmulator.isAlternateBufferActive()) {
                    if (TerminalView.this.mSelY1 <= TerminalView.this.mTopRow) {
                        TerminalView terminalView5 = TerminalView.this;
                        terminalView5.mTopRow--;
                        if (TerminalView.this.mTopRow < i3) {
                            TerminalView.this.mTopRow = i3;
                        }
                    } else if (TerminalView.this.mSelY1 >= TerminalView.this.mTopRow + TerminalView.this.mEmulator.mRows) {
                        TerminalView.this.mTopRow++;
                        if (TerminalView.this.mTopRow > 0) {
                            TerminalView.this.mTopRow = 0;
                        }
                    }
                }
                TerminalView terminalView6 = TerminalView.this;
                terminalView6.mSelX1 = getValidCurX(screen, terminalView6.mSelY1, TerminalView.this.mSelX1);
            } else {
                TerminalView terminalView7 = TerminalView.this;
                terminalView7.mSelX2 = terminalView7.getCursorX(i);
                TerminalView terminalView8 = TerminalView.this;
                terminalView8.mSelY2 = terminalView8.getCursorY(i2);
                if (TerminalView.this.mSelX2 < 0) {
                    TerminalView.this.mSelX2 = 0;
                }
                int i4 = -activeRows;
                if (TerminalView.this.mSelY2 < i4) {
                    TerminalView.this.mSelY2 = i4;
                } else if (TerminalView.this.mSelY2 > TerminalView.this.mEmulator.mRows - 1) {
                    TerminalView.this.mSelY2 = TerminalView.this.mEmulator.mRows - 1;
                }
                if (TerminalView.this.mSelY1 > TerminalView.this.mSelY2) {
                    TerminalView terminalView9 = TerminalView.this;
                    terminalView9.mSelY2 = terminalView9.mSelY1;
                }
                if (TerminalView.this.mSelY1 == TerminalView.this.mSelY2 && TerminalView.this.mSelX1 > TerminalView.this.mSelX2) {
                    TerminalView terminalView10 = TerminalView.this;
                    terminalView10.mSelX2 = terminalView10.mSelX1;
                }
                if (!TerminalView.this.mEmulator.isAlternateBufferActive()) {
                    if (TerminalView.this.mSelY2 <= TerminalView.this.mTopRow) {
                        TerminalView terminalView11 = TerminalView.this;
                        terminalView11.mTopRow--;
                        if (TerminalView.this.mTopRow < i4) {
                            TerminalView.this.mTopRow = i4;
                        }
                    } else if (TerminalView.this.mSelY2 >= TerminalView.this.mTopRow + TerminalView.this.mEmulator.mRows) {
                        TerminalView.this.mTopRow++;
                        if (TerminalView.this.mTopRow > 0) {
                            TerminalView.this.mTopRow = 0;
                        }
                    }
                }
                TerminalView terminalView12 = TerminalView.this;
                terminalView12.mSelX2 = getValidCurX(screen, terminalView12.mSelY2, TerminalView.this.mSelX2);
            }
            TerminalView.this.invalidate();
        }

        private int getValidCurX(TerminalBuffer terminalBuffer, int i, int i2) {
            int i3;
            int iWidth;
            int i4 = 0;
            String selectedText = terminalBuffer.getSelectedText(0, i, i2, i);
            if (!TextUtils.isEmpty(selectedText)) {
                int length = selectedText.length();
                int i5 = 0;
                while (i4 < length) {
                    char cCharAt = selectedText.charAt(i4);
                    if (cCharAt == 0) {
                        break;
                    }
                    if (Character.isHighSurrogate(cCharAt) && (i3 = i4 + 1) < length) {
                        iWidth = WcWidth.width(Character.toCodePoint(cCharAt, selectedText.charAt(i3)));
                    } else {
                        i3 = i4;
                        iWidth = WcWidth.width(cCharAt);
                    }
                    int i6 = iWidth + i5;
                    if (i2 > i5 && i2 < i6) {
                        return i6;
                    }
                    if (i6 == i5) {
                        return i5;
                    }
                    i5 = i6;
                    i4 = i3 + 1;
                }
            }
            return i2;
        }

        @Override // com.termfu.view.TerminalView.CursorController
        public void updatePosition() {
            if (isActive()) {
                this.mStartHandle.positionAtCursor(TerminalView.this.mSelX1, TerminalView.this.mSelY1, false);
                this.mEndHandle.positionAtCursor(TerminalView.this.mSelX2 + 1, TerminalView.this.mSelY2, false);
                if (TerminalView.this.mActionMode != null) {
                    TerminalView.this.mActionMode.invalidate();
                }
            }
        }

        public boolean isSelectionStartDragged() {
            return this.mStartHandle.isDragging();
        }

        public boolean isSelectionEndDragged() {
            return this.mEndHandle.isDragging();
        }

        @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
        public void onTouchModeChanged(boolean z) {
            if (z) {
                return;
            }
            hide();
        }
    }

    SelectionModifierCursorController getSelectionController() {
        if (this.mSelectionModifierCursorController == null) {
            this.mSelectionModifierCursorController = new SelectionModifierCursorController();
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.addOnTouchModeChangeListener(this.mSelectionModifierCursorController);
            }
        }
        return this.mSelectionModifierCursorController;
    }

    private void hideSelectionModifierCursorController() {
        SelectionModifierCursorController selectionModifierCursorController = this.mSelectionModifierCursorController;
        if (selectionModifierCursorController == null || !selectionModifierCursorController.isActive()) {
            return;
        }
        this.mSelectionModifierCursorController.hide();
    }

    private void startTextSelectionMode() {
        if (requestFocus()) {
            getSelectionController().show();
            this.mIsSelectingText = true;
            this.mClient.copyModeChanged(true);
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopTextSelectionMode() {
        if (this.mIsSelectingText) {
            hideSelectionModifierCursorController();
            this.mSelY2 = -1;
            this.mSelX2 = -1;
            this.mSelY1 = -1;
            this.mSelX1 = -1;
            this.mIsSelectingText = false;
            this.mClient.copyModeChanged(false);
            invalidate();
        }
    }

    void hideFloatingToolbar(int i) {
        if (this.mActionMode != null) {
            removeCallbacks(this.mShowFloatingToolbar);
            this.mActionMode.hide(i);
        }
    }

    private void showFloatingToolbar() {
        if (this.mActionMode != null) {
            postDelayed(this.mShowFloatingToolbar, ViewConfiguration.getDoubleTapTimeout());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFloatingToolbarVisibility(MotionEvent motionEvent) {
        if (this.mActionMode != null) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    hideFloatingToolbar(-1);
                    return;
                } else if (actionMasked != 3) {
                    return;
                }
            }
            showFloatingToolbar();
        }
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        String[] strArr = {getContext().getFilesDir() + "/home/.termfu/termfu.properties", getContext().getFilesDir() + "/home/.config/termfu/termfu.properties"};
        File file = new File(strArr[0]);
        for (int i = 0; !file.exists() && i < 2; i++) {
            file = new File(strArr[i]);
        }
        try {
            if (file.isFile() && file.canRead()) {
                Throwable th = null;
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        properties.load(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        fileInputStream.close();
                        throw th2;
                    }
                } catch (Throwable th3) {
                    if (0 == 0) {
                        throw th3;
                    }
                    if (null == th3) {
                        throw null;
                    }
                    th.addSuppressed(th3);
                    throw null;
                }
            }
        } catch (Exception e) {
            Log.e(EmulatorDebug.LOG_TAG, "Error loading props", e);
        }
        return properties;
    }

    @Override // android.view.View
    @RequiresApi(api = 26)
    public void autofill(AutofillValue autofillValue) {
        if (autofillValue.isText()) {
            this.mTermSession.write(autofillValue.getTextValue().toString());
        }
    }

    @Override // android.view.View
    @RequiresApi(api = 26)
    public AutofillValue getAutofillValue() {
        return AutofillValue.forText("");
    }
}
