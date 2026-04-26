package com.termfu.app;

import android.media.AudioManager;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import com.termfu.app.ExtraKeysView;
import com.termfu.app.TermuxPreferences;
import com.termfu.view.TerminalViewClient;
import com.termux.terminal.KeyHandler;
import com.termux.terminal.TerminalEmulator;
import com.termux.terminal.TerminalSession;
import java.util.List;

/* JADX INFO: loaded from: classes91.dex */
public final class TermuxViewClient implements TerminalViewClient {
    final TermuxActivity mActivity;
    boolean mVirtualControlKeyDown;
    boolean mVirtualFnKeyDown;

    @Override // com.termfu.view.TerminalViewClient
    public boolean onLongPress(MotionEvent motionEvent) {
        return false;
    }

    public TermuxViewClient(TermuxActivity termuxActivity) {
        this.mActivity = termuxActivity;
    }

    @Override // com.termfu.view.TerminalViewClient
    public float onScale(float f) {
        if (f >= 0.9f && f <= 1.1f) {
            return f;
        }
        this.mActivity.changeFontSize(f > 1.0f);
        return 1.0f;
    }

    @Override // com.termfu.view.TerminalViewClient
    public void onSingleTapUp(MotionEvent motionEvent) {
        ((InputMethodManager) this.mActivity.getSystemService("input_method")).showSoftInput(this.mActivity.mTerminalView, 1);
    }

    @Override // com.termfu.view.TerminalViewClient
    public boolean shouldBackButtonBeMappedToEscape() {
        return this.mActivity.mSettings.mBackIsEscape;
    }

    @Override // com.termfu.view.TerminalViewClient
    public void copyModeChanged(boolean z) {
        this.mActivity.getDrawer().setDrawerLockMode(z ? 1 : 0);
    }

    @Override // com.termfu.view.TerminalViewClient
    public boolean onKeyDown(int i, KeyEvent keyEvent, TerminalSession terminalSession) {
        if (handleVirtualKeys(i, keyEvent, true)) {
            return true;
        }
        if (i == 66 && !terminalSession.isRunning()) {
            this.mActivity.removeFinishedSession(terminalSession);
            return true;
        }
        if (!keyEvent.isCtrlPressed() || !keyEvent.isAltPressed()) {
            return false;
        }
        int unicodeChar = keyEvent.getUnicodeChar(0);
        if (i == 20 || unicodeChar == 110) {
            this.mActivity.switchToSession(true);
        } else if (i == 19 || unicodeChar == 112) {
            this.mActivity.switchToSession(false);
        } else if (i == 22) {
            this.mActivity.getDrawer().openDrawer(3);
        } else if (i == 21) {
            this.mActivity.getDrawer().closeDrawers();
        } else if (unicodeChar == 107) {
            ((InputMethodManager) this.mActivity.getSystemService("input_method")).toggleSoftInput(2, 0);
        } else if (unicodeChar == 109) {
            this.mActivity.mTerminalView.showContextMenu();
        } else if (unicodeChar == 114) {
            this.mActivity.renameSession(terminalSession);
        } else if (unicodeChar == 99) {
            this.mActivity.addNewSession(false, null);
        } else if (unicodeChar == 117) {
            this.mActivity.showUrlSelection();
        } else if (unicodeChar == 118) {
            this.mActivity.doPaste();
        } else if (unicodeChar == 43 || keyEvent.getUnicodeChar(1) == 43) {
            this.mActivity.changeFontSize(true);
        } else if (unicodeChar == 45) {
            this.mActivity.changeFontSize(false);
        } else if (unicodeChar >= 49 && unicodeChar <= 57) {
            int i2 = unicodeChar - 49;
            TermuxService termuxService = this.mActivity.mTermService;
            if (termuxService.getSessions().size() > i2) {
                this.mActivity.switchToSession(termuxService.getSessions().get(i2));
            }
        }
        return true;
    }

    @Override // com.termfu.view.TerminalViewClient
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return handleVirtualKeys(i, keyEvent, false);
    }

    @Override // com.termfu.view.TerminalViewClient
    public boolean readControlKey() {
        return (this.mActivity.mExtraKeysView != null && this.mActivity.mExtraKeysView.readSpecialButton(ExtraKeysView.SpecialButton.CTRL)) || this.mVirtualControlKeyDown;
    }

    @Override // com.termfu.view.TerminalViewClient
    public boolean readAltKey() {
        return this.mActivity.mExtraKeysView != null && this.mActivity.mExtraKeysView.readSpecialButton(ExtraKeysView.SpecialButton.ALT);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x0035. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x0038. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:26:0x003b. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007e A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a0  */
    @Override // com.termfu.view.TerminalViewClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onCodePoint(int i, boolean z, TerminalSession terminalSession) {
        boolean z2 = false;
        if (!this.mVirtualFnKeyDown) {
            if (z) {
                if (i == 106 && !terminalSession.isRunning()) {
                    this.mActivity.removeFinishedSession(terminalSession);
                    return true;
                }
                List<TermuxPreferences.KeyboardShortcut> list = this.mActivity.mSettings.shortcuts;
                if (!list.isEmpty()) {
                    int lowerCase = Character.toLowerCase(i);
                    for (int size = list.size() - 1; size >= 0; size--) {
                        TermuxPreferences.KeyboardShortcut keyboardShortcut = list.get(size);
                        if (lowerCase == keyboardShortcut.codePoint) {
                            int i2 = keyboardShortcut.shortcutAction;
                            if (i2 == 1) {
                                this.mActivity.addNewSession(false, null);
                                return true;
                            }
                            if (i2 == 2) {
                                this.mActivity.switchToSession(true);
                                return true;
                            }
                            if (i2 == 3) {
                                this.mActivity.switchToSession(false);
                                return true;
                            }
                            if (i2 == 4) {
                                TermuxActivity termuxActivity = this.mActivity;
                                termuxActivity.renameSession(termuxActivity.getCurrentTermSession());
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
        int lowerCase2 = Character.toLowerCase(i);
        int i3 = 124;
        if (lowerCase2 != 46) {
            if (lowerCase2 == 110) {
                i3 = 93;
            } else {
                if (lowerCase2 != 97) {
                    if (lowerCase2 == 98) {
                        z2 = true;
                        i3 = -1;
                    } else if (lowerCase2 == 104) {
                        lowerCase2 = 126;
                    } else if (lowerCase2 != 105) {
                        if (lowerCase2 == 107) {
                            this.mActivity.toggleShowExtraKeys();
                            z2 = false;
                            lowerCase2 = -1;
                            i3 = -1;
                        } else if (lowerCase2 == 108) {
                            z2 = false;
                            lowerCase2 = 124;
                            i3 = -1;
                        } else if (lowerCase2 != 112) {
                            if (lowerCase2 != 113) {
                                switch (lowerCase2) {
                                    case 48:
                                        i3 = 140;
                                        break;
                                    case 49:
                                    case 50:
                                    case 51:
                                    case 52:
                                    case 53:
                                    case 54:
                                    case 55:
                                    case 56:
                                    case 57:
                                        i3 = (i - 49) + 131;
                                        break;
                                    default:
                                        switch (lowerCase2) {
                                            case 100:
                                                i3 = 22;
                                                break;
                                            case 101:
                                                lowerCase2 = 27;
                                                break;
                                            default:
                                                switch (lowerCase2) {
                                                    case 115:
                                                        i3 = 20;
                                                        break;
                                                    case 116:
                                                        i3 = 61;
                                                        break;
                                                    case 117:
                                                        lowerCase2 = 95;
                                                        break;
                                                    case 118:
                                                        ((AudioManager) this.mActivity.getSystemService("audio")).adjustSuggestedStreamVolume(0, Integer.MIN_VALUE, 1);
                                                        break;
                                                    case 119:
                                                        i3 = 19;
                                                        break;
                                                }
                                            case 102:
                                                break;
                                        }
                                        break;
                                }
                            }
                            z2 = false;
                            lowerCase2 = -1;
                            i3 = -1;
                        } else {
                            i3 = 92;
                        }
                    }
                    if (i3 != -1) {
                        TerminalEmulator emulator = terminalSession.getEmulator();
                        terminalSession.write(KeyHandler.getCode(i3, 0, emulator.isCursorKeysApplicationMode(), emulator.isKeypadApplicationMode()));
                    } else if (lowerCase2 != -1) {
                        terminalSession.writeCodePoint(z2, lowerCase2);
                    }
                    return true;
                }
                i3 = 21;
            }
            z2 = false;
            lowerCase2 = -1;
            if (i3 != -1) {
            }
            return true;
        }
        lowerCase2 = 28;
        z2 = false;
        i3 = -1;
        if (i3 != -1) {
        }
        return true;
    }

    private boolean handleVirtualKeys(int i, KeyEvent keyEvent, boolean z) {
        InputDevice device = keyEvent.getDevice();
        if (this.mActivity.mSettings.mDisableVolumeVirtualKeys) {
            return false;
        }
        if (device != null && device.getKeyboardType() == 2) {
            return false;
        }
        if (i == 25) {
            this.mVirtualControlKeyDown = z;
            return true;
        }
        if (i != 24) {
            return false;
        }
        this.mVirtualFnKeyDown = z;
        return true;
    }
}
