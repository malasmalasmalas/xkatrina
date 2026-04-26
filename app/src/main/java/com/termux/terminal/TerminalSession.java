package com.termux.terminal;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/* JADX INFO: loaded from: classes91.dex */
public final class TerminalSession extends TerminalOutput {
    private static final int MSG_NEW_INPUT = 1;
    private static final int MSG_PROCESS_EXITED = 4;
    private final String[] mArgs;
    final SessionChangedCallback mChangeCallback;
    private final String mCwd;
    TerminalEmulator mEmulator;
    private final String[] mEnv;
    public String mSessionName;
    int mShellExitStatus;
    private final String mShellPath;
    int mShellPid;
    private int mTerminalFileDescriptor;
    public final String mHandle = UUID.randomUUID().toString();
    final ByteQueue mProcessToTerminalIOQueue = new ByteQueue(4096);
    final ByteQueue mTerminalToProcessIOQueue = new ByteQueue(4096);
    private final byte[] mUtf8InputBuffer = new byte[5];

    @SuppressLint({"HandlerLeak"})
    final Handler mMainThreadHandler = new Handler() { // from class: com.termux.terminal.TerminalSession.1
        final byte[] mReceiveBuffer = new byte[4096];

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = TerminalSession.this.mProcessToTerminalIOQueue.read(this.mReceiveBuffer, false);
            if (i > 0) {
                TerminalSession.this.mEmulator.append(this.mReceiveBuffer, i);
                TerminalSession.this.notifyScreenUpdate();
            }
            if (message.what == 4) {
                int iIntValue = ((Integer) message.obj).intValue();
                TerminalSession.this.cleanupResources(iIntValue);
                TerminalSession.this.mChangeCallback.onSessionFinished(TerminalSession.this);
                String str = "\r\n[Process completed";
                if (iIntValue > 0) {
                    str = "\r\n[Process completed (code " + iIntValue + ")";
                } else if (iIntValue < 0) {
                    str = "\r\n[Process completed (signal " + (-iIntValue) + ")";
                }
                byte[] bytes = (String.valueOf(str) + " - press Enter]").getBytes(StandardCharsets.UTF_8);
                TerminalSession.this.mEmulator.append(bytes, bytes.length);
                TerminalSession.this.notifyScreenUpdate();
            }
        }
    };

    public interface SessionChangedCallback {
        void onBell(TerminalSession terminalSession);

        void onClipboardText(TerminalSession terminalSession, String str);

        void onColorsChanged(TerminalSession terminalSession);

        void onSessionFinished(TerminalSession terminalSession);

        void onTextChanged(TerminalSession terminalSession);

        void onTitleChanged(TerminalSession terminalSession);
    }

    private static FileDescriptor wrapFileDescriptor(int i) {
        Field declaredField;
        FileDescriptor fileDescriptor = new FileDescriptor();
        try {
            try {
                declaredField = FileDescriptor.class.getDeclaredField("descriptor");
            } catch (NoSuchFieldException unused) {
                try {
                    declaredField = FileDescriptor.class.getDeclaredField("fd");
                } catch (NoSuchFieldException e) {
                    Log.wtf(EmulatorDebug.LOG_TAG, "Error accessing FileDescriptor field", e);
                    System.exit(1);
                    return fileDescriptor;
                }
            }
            declaredField.setAccessible(true);
            declaredField.set(fileDescriptor, Integer.valueOf(i));
        } catch (IllegalAccessException e2) {
            Log.wtf(EmulatorDebug.LOG_TAG, "Error accessing FileDescriptor#descriptor private field", e2);
            System.exit(1);
            return fileDescriptor;
        } catch (IllegalArgumentException e3) {
            Log.wtf(EmulatorDebug.LOG_TAG, "Error accessing FileDescriptor#descriptor private field", e3);
            System.exit(1);
            return fileDescriptor;
        }
        return fileDescriptor;
    }

    public TerminalSession(String str, String str2, String[] strArr, String[] strArr2, SessionChangedCallback sessionChangedCallback) {
        this.mChangeCallback = sessionChangedCallback;
        this.mShellPath = str;
        this.mCwd = str2;
        this.mArgs = strArr;
        this.mEnv = strArr2;
    }

    public void updateSize(int i, int i2) {
        if (this.mEmulator == null) {
            initializeEmulator(i, i2);
        } else {
            JNI.setPtyWindowSize(this.mTerminalFileDescriptor, i2, i);
            this.mEmulator.resize(i, i2);
        }
    }

    public String getTitle() {
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            return null;
        }
        return terminalEmulator.getTitle();
    }

    /* JADX WARN: Type inference failed for: r10v3, types: [com.termux.terminal.TerminalSession$2] */
    /* JADX WARN: Type inference failed for: r10v4, types: [com.termux.terminal.TerminalSession$3] */
    /* JADX WARN: Type inference failed for: r9v3, types: [com.termux.terminal.TerminalSession$4] */
    public void initializeEmulator(int i, int i2) {
        this.mEmulator = new TerminalEmulator(this, i, i2, 2000);
        int[] iArr = new int[1];
        int iCreateSubprocess = JNI.createSubprocess(this.mShellPath, this.mCwd, this.mArgs, this.mEnv, iArr, i2, i);
        this.mTerminalFileDescriptor = iCreateSubprocess;
        this.mShellPid = iArr[0];
        final FileDescriptor fileDescriptorWrapFileDescriptor = wrapFileDescriptor(iCreateSubprocess);
        new Thread("TermSessionInputReader[pid=" + this.mShellPid + "]") { // from class: com.termux.terminal.TerminalSession.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Throwable th = null;
                try {
                    FileInputStream fileInputStream = new FileInputStream(fileDescriptorWrapFileDescriptor);
                    try {
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int i3 = fileInputStream.read(bArr);
                            if (i3 == -1) {
                                return;
                            }
                            if (!TerminalSession.this.mProcessToTerminalIOQueue.write(bArr, 0, i3)) {
                                return;
                            } else {
                                TerminalSession.this.mMainThreadHandler.sendEmptyMessage(1);
                            }
                        }
                    } finally {
                        fileInputStream.close();
                    }
                } catch (Exception th2) {
                    Log.e(EmulatorDebug.LOG_TAG, "Error in TermSessionInputReader", th2);
                }
            }
        }.start();
        new Thread("TermSessionOutputWriter[pid=" + this.mShellPid + "]") { // from class: com.termux.terminal.TerminalSession.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                byte[] bArr = new byte[4096];
                Throwable th = null;
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(fileDescriptorWrapFileDescriptor);
                    while (true) {
                        try {
                            int i3 = TerminalSession.this.mTerminalToProcessIOQueue.read(bArr, true);
                            if (i3 == -1) {
                                return;
                            } else {
                                fileOutputStream.write(bArr, 0, i3);
                            }
                        } finally {
                            fileOutputStream.close();
                        }
                    }
                } catch (Exception th2) {
                    Log.e(EmulatorDebug.LOG_TAG, "Error in TermSessionOutputWriter", th2);
                }
            }
        }.start();
        new Thread("TermSessionWaiter[pid=" + this.mShellPid + "]") { // from class: com.termux.terminal.TerminalSession.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                TerminalSession.this.mMainThreadHandler.sendMessage(TerminalSession.this.mMainThreadHandler.obtainMessage(4, Integer.valueOf(JNI.waitFor(TerminalSession.this.mShellPid))));
            }
        }.start();
    }

    @Override // com.termux.terminal.TerminalOutput
    public void write(byte[] bArr, int i, int i2) {
        if (this.mShellPid > 0) {
            this.mTerminalToProcessIOQueue.write(bArr, i, i2);
        }
    }

    public void writeCodePoint(boolean z, int i) {
        int i2;
        int i3;
        int i4;
        if (i > 1114111 || (i >= 55296 && i <= 57343)) {
            throw new IllegalArgumentException("Invalid code point: " + i);
        }
        if (z) {
            this.mUtf8InputBuffer[0] = 27;
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (i <= 127) {
            i4 = i2 + 1;
            this.mUtf8InputBuffer[i2] = (byte) i;
        } else if (i <= 2047) {
            byte[] bArr = this.mUtf8InputBuffer;
            int i5 = i2 + 1;
            bArr[i2] = (byte) ((i >> 6) | 192);
            i4 = i5 + 1;
            bArr[i5] = (byte) ((i & 63) | 128);
        } else if (i <= 65535) {
            byte[] bArr2 = this.mUtf8InputBuffer;
            int i6 = i2 + 1;
            bArr2[i2] = (byte) ((i >> 12) | 224);
            int i7 = i6 + 1;
            bArr2[i6] = (byte) (((i >> 6) & 63) | 128);
            i4 = i7 + 1;
            bArr2[i7] = (byte) ((i & 63) | 128);
        } else {
            byte[] bArr3 = this.mUtf8InputBuffer;
            int i8 = i2 + 1;
            bArr3[i2] = (byte) ((i >> 18) | 240);
            int i9 = i8 + 1;
            bArr3[i8] = (byte) (((i >> 12) & 63) | 128);
            int i10 = i9 + 1;
            bArr3[i9] = (byte) (((i >> 6) & 63) | 128);
            i4 = i10 + 1;
            bArr3[i10] = (byte) ((i & 63) | 128);
        }
        write(this.mUtf8InputBuffer, 0, i4);
    }

    public TerminalEmulator getEmulator() {
        return this.mEmulator;
    }

    protected void notifyScreenUpdate() {
        this.mChangeCallback.onTextChanged(this);
    }

    public void reset() {
        this.mEmulator.reset();
        notifyScreenUpdate();
    }

    public void finishIfRunning() {
        if (isRunning()) {
            try {
                Os.kill(this.mShellPid, OsConstants.SIGKILL);
            } catch (ErrnoException e) {
                Log.w(EmulatorDebug.LOG_TAG, "Failed sending SIGKILL: " + e.getMessage());
            }
        }
    }

    void cleanupResources(int i) {
        synchronized (this) {
            this.mShellPid = -1;
            this.mShellExitStatus = i;
        }
        this.mTerminalToProcessIOQueue.close();
        this.mProcessToTerminalIOQueue.close();
        JNI.close(this.mTerminalFileDescriptor);
    }

    @Override // com.termux.terminal.TerminalOutput
    public void titleChanged(String str, String str2) {
        this.mChangeCallback.onTitleChanged(this);
    }

    public synchronized boolean isRunning() {
        return this.mShellPid != -1;
    }

    public synchronized int getExitStatus() {
        return this.mShellExitStatus;
    }

    @Override // com.termux.terminal.TerminalOutput
    public void clipboardText(String str) {
        this.mChangeCallback.onClipboardText(this, str);
    }

    @Override // com.termux.terminal.TerminalOutput
    public void onBell() {
        this.mChangeCallback.onBell(this);
    }

    @Override // com.termux.terminal.TerminalOutput
    public void onColorsChanged() {
        this.mChangeCallback.onColorsChanged(this);
    }

    public int getPid() {
        return this.mShellPid;
    }

    public String getCwd() {
        int i = this.mShellPid;
        if (i < 1) {
            return null;
        }
        try {
            String str = String.format("/proc/%s/cwd/", Integer.valueOf(i));
            String canonicalPath = new File(str).getCanonicalPath();
            String str2 = canonicalPath.endsWith("/") ? canonicalPath : (canonicalPath + '/');
            if (str.equals(str2)) {
                return null;
            }
            return canonicalPath;
        } catch (IOException | SecurityException e) {
            Log.e(EmulatorDebug.LOG_TAG, "Error getting current directory", e);
            return null;
        }
    }
}
