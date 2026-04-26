package com.termux.terminal;

/* JADX INFO: loaded from: classes91.dex */
final class ByteQueue {
    private final byte[] mBuffer;
    private int mHead;
    private boolean mOpen = true;
    private int mStoredBytes;

    public ByteQueue(int i) {
        this.mBuffer = new byte[i];
    }

    public synchronized void close() {
        this.mOpen = false;
        notify();
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0015, code lost:
    
        if (r8.mOpen != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0019, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x001a, code lost:
    
        r10 = r8.mBuffer.length;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x001d, code lost:
    
        if (r10 != r0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x001f, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0021, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0022, code lost:
    
        r2 = r9.length;
        r3 = 0;
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0025, code lost:
    
        if (r2 <= 0) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0027, code lost:
    
        r5 = r8.mStoredBytes;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0029, code lost:
    
        if (r5 > 0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x002c, code lost:
    
        r5 = java.lang.Math.min(r2, java.lang.Math.min(r10 - r8.mHead, r5));
        java.lang.System.arraycopy(r8.mBuffer, r8.mHead, r9, r4, r5);
        r6 = r8.mHead + r5;
        r8.mHead = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0044, code lost:
    
        if (r6 < r10) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0046, code lost:
    
        r8.mHead = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0048, code lost:
    
        r8.mStoredBytes -= r5;
        r2 = r2 - r5;
        r4 = r4 + r5;
        r3 = r3 + r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0051, code lost:
    
        if (r0 == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0053, code lost:
    
        notify();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0057, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized int read(byte[] bArr, boolean z) {
        while (this.mStoredBytes == 0 && this.mOpen) {
            if (!z) {
                return 0;
            }
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
        if (!this.mOpen) {
            return -1;
        }
        int bufLen = this.mBuffer.length;
        boolean shouldNotify = bufLen == this.mStoredBytes;
        int remaining = bArr.length;
        int totalRead = 0;
        int offset = 0;
        while (remaining > 0 && this.mStoredBytes > 0) {
            int available = Math.min(remaining, Math.min(bufLen - this.mHead, this.mStoredBytes));
            System.arraycopy(this.mBuffer, this.mHead, bArr, offset, available);
            int newHead = this.mHead + available;
            this.mHead = newHead;
            if (newHead >= bufLen) {
                this.mHead = 0;
            }
            this.mStoredBytes -= available;
            remaining -= available;
            offset += available;
            totalRead += available;
        }
        if (shouldNotify) {
            notify();
        }
        return totalRead;
    }

    public boolean write(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        if (i2 + i > bArr.length) {
            throw new IllegalArgumentException("length + offset > buffer.length");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("length <= 0");
        }
        int length = this.mBuffer.length;
        synchronized (this) {
            while (true) {
                boolean z = true;
                if (i2 <= 0) {
                    return true;
                }
                while (true) {
                    i3 = this.mStoredBytes;
                    if (length != i3 || !this.mOpen) {
                        break;
                    }
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                    }
                }
                if (!this.mOpen) {
                    return false;
                }
                if (i3 != 0) {
                    z = false;
                }
                int iMin = Math.min(i2, length - i3);
                i2 -= iMin;
                while (iMin > 0) {
                    int i5 = this.mHead;
                    int i6 = this.mStoredBytes + i5;
                    if (i6 >= length) {
                        i6 -= length;
                        i4 = i5 - i6;
                    } else {
                        i4 = length - i6;
                    }
                    int iMin2 = Math.min(i4, iMin);
                    System.arraycopy(bArr, i, this.mBuffer, i6, iMin2);
                    i += iMin2;
                    iMin -= iMin2;
                    this.mStoredBytes += iMin2;
                }
                if (z) {
                    notify();
                }
            }
        }
    }
}
