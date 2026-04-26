package com.termfu.app;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.PopupWindow;
import androidx.drawerlayout.widget.DrawerLayout;
import com.fufufu.katrina.backup.C0978R;
import com.termfu.app.ExtraKeysView;
import com.termfu.view.TerminalView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes91.dex */
public final class ExtraKeysView extends GridLayout {
    private static final int BUTTON_COLOR = 0;
    private static final int BUTTON_PRESSED_COLOR = -8421505;
    private static final int INTERESTING_COLOR = -8331542;
    private static final int TEXT_COLOR = -1;
    static final Map<String, Integer> keyCodesForString = new HashMap<String, Integer>() { // from class: com.termfu.app.ExtraKeysView.1
        {
            put("SPACE", 62);
            put("ESC", 111);
            put("TAB", 61);
            put("HOME", 122);
            put("END", 123);
            put("PGUP", 92);
            put("PGDN", 93);
            put("INS", 124);
            put("DEL", 112);
            put("BKSP", 67);
            put("UP", 19);
            put("LEFT", 21);
            put("RIGHT", 22);
            put("DOWN", 20);
            put("ENTER", 66);
            put("F1", 131);
            put("F2", 132);
            put("F3", 133);
            put("F4", 134);
            put("F5", 135);
            put("F6", 136);
            put("F7", 137);
            put("F8", 138);
            put("F9", 139);
            put("F10", 140);
            put("F11", 141);
            put("F12", 142);
        }
    };
    private int longPressCount;
    private PopupWindow popupWindow;
    private ScheduledExecutorService scheduledExecutor;
    private final Map<SpecialButton, SpecialButtonState> specialButtons;
    private final Set<String> specialButtonsKeys;

    public enum SpecialButton {
        CTRL,
        ALT,
        FN;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static SpecialButton[] valuesCustom() {
            SpecialButton[] specialButtonArrValuesCustom = values();
            int length = specialButtonArrValuesCustom.length;
            SpecialButton[] specialButtonArr = new SpecialButton[length];
            System.arraycopy(specialButtonArrValuesCustom, 0, specialButtonArr, 0, length);
            return specialButtonArr;
        }
    }

    public ExtraKeysView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        HashMap<SpecialButton, SpecialButtonState> map = new HashMap<SpecialButton, SpecialButtonState>() { // from class: com.termfu.app.ExtraKeysView.2
            {
                SpecialButtonState specialButtonState = null;
                put(SpecialButton.CTRL, new SpecialButtonState(specialButtonState));
                put(SpecialButton.ALT, new SpecialButtonState(specialButtonState));
                put(SpecialButton.FN, new SpecialButtonState(specialButtonState));
            }
        };
        this.specialButtons = map;
        this.specialButtonsKeys = (Set) map.keySet().stream().map(new Function() { // from class: com.termfu.app.ExtraKeysView$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ExtraKeysView.SpecialButton) obj).name();
            }
        }).collect(Collectors.toSet());
    }

    private void sendKey(View view, String str, final boolean z, final boolean z2) {
        final TerminalView terminalView = (TerminalView) view.findViewById(C0978R.id.terminal_view);
        if ("KEYBOARD".equals(str)) {
            ((InputMethodManager) getContext().getSystemService("input_method")).toggleSoftInput(0, 0);
            return;
        }
        if ("DRAWER".equals(str)) {
            ((DrawerLayout) view.findViewById(C0978R.id.drawer_layout)).openDrawer(3);
            return;
        }
        Map<String, Integer> map = keyCodesForString;
        if (map.containsKey(str)) {
            int iIntValue = map.get(str).intValue();
            int i = z ? 12288 : 0;
            terminalView.onKeyDown(iIntValue, new KeyEvent(0L, 0L, 1, iIntValue, 0, z2 ? i | 18 : i));
            return;
        }
        str.codePoints().forEach(new IntConsumer() { // from class: com.termfu.app.ExtraKeysView$$ExternalSyntheticLambda1
            @Override // java.util.function.IntConsumer
            public final void accept(int i2) {
                terminalView.inputCodePoint(i2, z, z2);
            }
        });
    }

    private void sendKey(View view, ExtraKeyButton extraKeyButton) {
        if (extraKeyButton.isMacro()) {
            String[] strArrSplit = extraKeyButton.getKey().split(" ");
            boolean z = false;
            boolean z2 = false;
            for (String str : strArrSplit) {
                if ("CTRL".equals(str)) {
                    z = true;
                } else if ("ALT".equals(str)) {
                    z2 = true;
                } else {
                    sendKey(view, str, z, z2);
                    z = false;
                    z2 = false;
                }
            }
            return;
        }
        sendKey(view, extraKeyButton.getKey(), false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class SpecialButtonState {
        List<Button> buttons;
        boolean isActive;
        boolean isOn;

        private SpecialButtonState() {
            this.isOn = false;
            this.isActive = false;
            this.buttons = new ArrayList();
        }

        /* synthetic */ SpecialButtonState(SpecialButtonState specialButtonState) {
            this();
        }

        void setIsActive(final boolean z) {
            this.isActive = z;
            this.buttons.forEach(new Consumer() { // from class: com.termfu.app.ExtraKeysView$SpecialButtonState$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((Button) obj).setTextColor(z ? ExtraKeysView.INTERESTING_COLOR : -1);
                }
            });
        }
    }

    private boolean isSpecialButton(ExtraKeyButton extraKeyButton) {
        return this.specialButtonsKeys.contains(extraKeyButton.getKey());
    }

    public boolean readSpecialButton(SpecialButton specialButton) {
        SpecialButtonState specialButtonState = this.specialButtons.get(specialButton);
        if (specialButtonState == null) {
            throw new RuntimeException("Must be a valid special button (see source)");
        }
        if (!specialButtonState.isOn || !specialButtonState.isActive) {
            return false;
        }
        specialButtonState.setIsActive(false);
        return true;
    }

    private Button createSpecialButton(String str, boolean z) {
        SpecialButtonState specialButtonState = this.specialButtons.get(SpecialButton.valueOf(str));
        specialButtonState.isOn = true;
        Button button = new Button(getContext(), null, R.attr.buttonBarButtonStyle);
        button.setTextColor(specialButtonState.isActive ? INTERESTING_COLOR : -1);
        if (z) {
            specialButtonState.buttons.add(button);
        }
        return button;
    }

    void popup(View view, ExtraKeyButton extraKeyButton) {
        Button button;
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (isSpecialButton(extraKeyButton)) {
            button = createSpecialButton(extraKeyButton.getKey(), false);
        } else {
            button = new Button(getContext(), null, R.attr.buttonBarButtonStyle);
            button.setTextColor(-1);
        }
        button.setText(extraKeyButton.getDisplay());
        button.setPadding(0, 0, 0, 0);
        button.setMinHeight(0);
        button.setMinWidth(0);
        button.setMinimumWidth(0);
        button.setMinimumHeight(0);
        button.setWidth(measuredWidth);
        button.setHeight(measuredHeight);
        button.setBackgroundColor(BUTTON_PRESSED_COLOR);
        PopupWindow popupWindow = new PopupWindow(this);
        this.popupWindow = popupWindow;
        popupWindow.setWidth(-2);
        this.popupWindow.setHeight(-2);
        this.popupWindow.setContentView(button);
        this.popupWindow.setOutsideTouchable(true);
        this.popupWindow.setFocusable(false);
        this.popupWindow.showAsDropDown(view, 0, measuredHeight * (-2));
    }

    static int maximumLength(Object[][] objArr) {
        int iMax = 0;
        for (Object[] objArr2 : objArr) {
            iMax = Math.max(iMax, objArr2.length);
        }
        return iMax;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    void reload(ExtraKeysInfos extraKeysInfos) {
        Button button;
        if (extraKeysInfos == null) {
            return;
        }
        Iterator<SpecialButtonState> it = this.specialButtons.values().iterator();
        while (it.hasNext()) {
            it.next().buttons = new ArrayList();
        }
        removeAllViews();
        ExtraKeyButton[][] matrix = extraKeysInfos.getMatrix();
        setRowCount(matrix.length);
        setColumnCount(maximumLength(matrix));
        for (int i = 0; i < matrix.length; i++) {
            int i2 = 0;
            while (true) {
                ExtraKeyButton[] extraKeyButtonArr = matrix[i];
                if (i2 >= extraKeyButtonArr.length) {
                    break;
                }
                final ExtraKeyButton extraKeyButton = extraKeyButtonArr[i2];
                if (isSpecialButton(extraKeyButton)) {
                    button = createSpecialButton(extraKeyButton.getKey(), true);
                } else {
                    button = new Button(getContext(), null, R.attr.buttonBarButtonStyle);
                }
                final Button finalButton = button;
                button.setText(extraKeyButton.getDisplay());
                button.setTextColor(-1);
                button.setPadding(0, 0, 0, 0);
                button.setOnClickListener(new View.OnClickListener() { // from class: com.termfu.app.ExtraKeysView$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ExtraKeysView.this.m273lambda$2$comtermfuappExtraKeysView(finalButton, extraKeyButton, view);
                    }
                });
                button.setOnTouchListener(new View.OnTouchListener() { // from class: com.termfu.app.ExtraKeysView$$ExternalSyntheticLambda3
                    @Override // android.view.View.OnTouchListener
                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        return ExtraKeysView.this.m274lambda$3$comtermfuappExtraKeysView(extraKeyButton, view, motionEvent);
                    }
                });
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.width = 0;
                layoutParams.height = 0;
                layoutParams.setMargins(0, 0, 0, 0);
                layoutParams.columnSpec = GridLayout.spec(i2, GridLayout.FILL, 1.0f);
                layoutParams.rowSpec = GridLayout.spec(i, GridLayout.FILL, 1.0f);
                button.setLayoutParams(layoutParams);
                addView(button);
                i2++;
            }
        }
    }

    /* JADX INFO: renamed from: lambda$2$com-termfu-app-ExtraKeysView, reason: not valid java name */
    /* synthetic */ void m273lambda$2$comtermfuappExtraKeysView(Button button, ExtraKeyButton extraKeyButton, View view) {
        if (Settings.System.getInt(getContext().getContentResolver(), "haptic_feedback_enabled", 0) != 0 && (Build.VERSION.SDK_INT >= 28 || Settings.Global.getInt(getContext().getContentResolver(), "zen_mode", 0) != 2)) {
            button.performHapticFeedback(3);
        }
        View rootView = getRootView();
        if (isSpecialButton(extraKeyButton)) {
            SpecialButtonState state4 = this.specialButtons.get(SpecialButton.valueOf(extraKeyButton.getKey()));
            state4.setIsActive(!state4.isActive);
        } else {
            sendKey(rootView, extraKeyButton);
        }
    }

    /* JADX INFO: renamed from: lambda$3$com-termfu-app-ExtraKeysView, reason: not valid java name */
    /* synthetic */ boolean m274lambda$3$comtermfuappExtraKeysView(final ExtraKeyButton extraKeyButton, View view, MotionEvent motionEvent) {
        final View rootView = getRootView();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.longPressCount = 0;
            view.setBackgroundColor(BUTTON_PRESSED_COLOR);
            if (Arrays.asList("UP", "DOWN", "LEFT", "RIGHT", "BKSP", "DEL").contains(extraKeyButton.getKey())) {
                ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
                this.scheduledExecutor = scheduledExecutorServiceNewSingleThreadScheduledExecutor;
                scheduledExecutorServiceNewSingleThreadScheduledExecutor.scheduleWithFixedDelay(new Runnable() { // from class: com.termfu.app.ExtraKeysView$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ExtraKeysView.this.m275lambda$4$comtermfuappExtraKeysView(rootView, extraKeyButton);
                    }
                }, 400L, 80L, TimeUnit.MILLISECONDS);
            }
            return true;
        }
        if (action == 1) {
            view.setBackgroundColor(0);
            ScheduledExecutorService scheduledExecutorService = this.scheduledExecutor;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
                this.scheduledExecutor = null;
            }
            if (this.longPressCount == 0 || this.popupWindow != null) {
                PopupWindow popupWindow = this.popupWindow;
                if (popupWindow != null) {
                    popupWindow.setContentView(null);
                    this.popupWindow.dismiss();
                    this.popupWindow = null;
                    if (extraKeyButton.getPopup() != null) {
                        if (isSpecialButton(extraKeyButton.getPopup())) {
                            SpecialButtonState state13 = this.specialButtons.get(SpecialButton.valueOf(extraKeyButton.getPopup().getKey()));
                            state13.setIsActive(!state13.isActive);
                        } else {
                            sendKey(rootView, extraKeyButton.getPopup());
                        }
                    }
                } else {
                    view.performClick();
                }
            }
            return true;
        }
        if (action != 2) {
            if (action != 3) {
                return true;
            }
            view.setBackgroundColor(0);
            ScheduledExecutorService scheduledExecutorService2 = this.scheduledExecutor;
            if (scheduledExecutorService2 != null) {
                scheduledExecutorService2.shutdownNow();
                this.scheduledExecutor = null;
            }
            return true;
        }
        if (extraKeyButton.getPopup() != null) {
            if (this.popupWindow == null && motionEvent.getY() < 0.0f) {
                ScheduledExecutorService scheduledExecutorService3 = this.scheduledExecutor;
                if (scheduledExecutorService3 != null) {
                    scheduledExecutorService3.shutdownNow();
                    this.scheduledExecutor = null;
                }
                view.setBackgroundColor(0);
                popup(view, extraKeyButton.getPopup());
            }
            if (this.popupWindow != null && motionEvent.getY() > 0.0f) {
                view.setBackgroundColor(BUTTON_PRESSED_COLOR);
                this.popupWindow.dismiss();
                this.popupWindow = null;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: lambda$4$com-termfu-app-ExtraKeysView, reason: not valid java name */
    /* synthetic */ void m275lambda$4$comtermfuappExtraKeysView(View view, ExtraKeyButton extraKeyButton) {
        this.longPressCount++;
        sendKey(view, extraKeyButton);
    }
}
