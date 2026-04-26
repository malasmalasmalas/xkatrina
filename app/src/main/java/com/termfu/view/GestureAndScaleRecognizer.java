package com.termfu.view;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

/* JADX INFO: loaded from: classes91.dex */
final class GestureAndScaleRecognizer {
    boolean isAfterLongPress;
    private final GestureDetector mGestureDetector;
    final Listener mListener;
    private final ScaleGestureDetector mScaleDetector;

    public interface Listener {
        boolean onDoubleTap(MotionEvent motionEvent);

        boolean onDown(float f, float f2);

        boolean onFling(MotionEvent motionEvent, float f, float f2);

        void onLongPress(MotionEvent motionEvent);

        boolean onScale(float f, float f2, float f3);

        boolean onScroll(MotionEvent motionEvent, float f, float f2);

        boolean onSingleTapUp(MotionEvent motionEvent);

        boolean onUp(MotionEvent motionEvent);
    }

    public GestureAndScaleRecognizer(Context context, Listener listener) {
        this.mListener = listener;
        GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: com.termfu.view.GestureAndScaleRecognizer.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return GestureAndScaleRecognizer.this.mListener.onScroll(motionEvent2, f, f2);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return GestureAndScaleRecognizer.this.mListener.onFling(motionEvent2, f, f2);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return GestureAndScaleRecognizer.this.mListener.onDown(motionEvent.getX(), motionEvent.getY());
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                GestureAndScaleRecognizer.this.mListener.onLongPress(motionEvent);
                GestureAndScaleRecognizer.this.isAfterLongPress = true;
            }
        }, null, true);
        this.mGestureDetector = gestureDetector;
        gestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() { // from class: com.termfu.view.GestureAndScaleRecognizer.2
            @Override // android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                return true;
            }

            @Override // android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                return GestureAndScaleRecognizer.this.mListener.onSingleTapUp(motionEvent);
            }

            @Override // android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                return GestureAndScaleRecognizer.this.mListener.onDoubleTap(motionEvent);
            }
        });
        ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.SimpleOnScaleGestureListener() { // from class: com.termfu.view.GestureAndScaleRecognizer.3
            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector2) {
                return true;
            }

            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector scaleGestureDetector2) {
                return GestureAndScaleRecognizer.this.mListener.onScale(scaleGestureDetector2.getFocusX(), scaleGestureDetector2.getFocusY(), scaleGestureDetector2.getScaleFactor());
            }
        });
        this.mScaleDetector = scaleGestureDetector;
        scaleGestureDetector.setQuickScaleEnabled(false);
    }

    public void onTouchEvent(MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
        this.mScaleDetector.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.isAfterLongPress = false;
        } else if (action == 1 && !this.isAfterLongPress) {
            this.mListener.onUp(motionEvent);
        }
    }

    public boolean isInProgress() {
        return this.mScaleDetector.isInProgress();
    }
}
