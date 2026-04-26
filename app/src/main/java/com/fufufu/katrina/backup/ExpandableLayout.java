package com.fufufu.katrina.backup;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;

public class ExpandableLayout extends FrameLayout {
    private static final int DEFAULT_DURATION = 1000;
    public static final int HORIZONTAL = 0;
    public static final String KEY_EXPANSION = "expansion";
    public static final String KEY_SUPER_STATE = "super_state";
    public static final int VERTICAL = 1;
    private ValueAnimator animator;
    private int duration;
    private float expansion;

        private final float[] f811ff;
    private Interpolator interpolator;
    private OnExpansionUpdateListener listener;
    private int orientation;
    private float parallax;
    private int state;

    public interface OnExpansionUpdateListener {
        void onExpansionUpdate(float f, int i);
    }

    public interface State {
        public static final int COLLAPSED = 0;
        public static final int COLLAPSING = 1;
        public static final int EXPANDED = 3;
        public static final int EXPANDING = 2;
    }

    public ExpandableLayout(Context context) {
        this(context, null);
    }

    public ExpandableLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.duration = 1000;
        float[] fArr = {0.0f, 1.0E-4f, 2.0E-4f, 5.0E-4f, 9.0E-4f, 0.0014f, 0.002f, 0.0027f, 0.0036f, 0.0046f, 0.0058f, 0.0071f, 0.0085f, 0.0101f, 0.0118f, 0.0137f, 0.0158f, 0.018f, 0.0205f, 0.0231f, 0.0259f, 0.0289f, 0.0321f, 0.0355f, 0.0391f, 0.043f, 0.0471f, 0.0514f, 0.056f, 0.0608f, 0.066f, 0.0714f, 0.0771f, 0.083f, 0.0893f, 0.0959f, 0.1029f, 0.1101f, 0.1177f, 0.1257f, 0.1339f, 0.1426f, 0.1516f, 0.161f, 0.1707f, 0.1808f, 0.1913f, 0.2021f, 0.2133f, 0.2248f, 0.2366f, 0.2487f, 0.2611f, 0.2738f, 0.2867f, 0.2998f, 0.3131f, 0.3265f, 0.34f, 0.3536f, 0.3673f, 0.381f, 0.3946f, 0.4082f, 0.4217f, 0.4352f, 0.4485f, 0.4616f, 0.4746f, 0.4874f, 0.5f, 0.5124f, 0.5246f, 0.5365f, 0.5482f, 0.5597f, 0.571f, 0.582f, 0.5928f, 0.6033f, 0.6136f, 0.6237f, 0.6335f, 0.6431f, 0.6525f, 0.6616f, 0.6706f, 0.6793f, 0.6878f, 0.6961f, 0.7043f, 0.7122f, 0.7199f, 0.7275f, 0.7349f, 0.7421f, 0.7491f, 0.7559f, 0.7626f, 0.7692f, 0.7756f, 0.7818f, 0.7879f, 0.7938f, 0.7996f, 0.8053f, 0.8108f, 0.8162f, 0.8215f, 0.8266f, 0.8317f, 0.8366f, 0.8414f, 0.8461f, 0.8507f, 0.8551f, 0.8595f, 0.8638f, 0.8679f, 0.872f, 0.876f, 0.8798f, 0.8836f, 0.8873f, 0.8909f, 0.8945f, 0.8979f, 0.9013f, 0.9046f, 0.9078f, 0.9109f, 0.9139f, 0.9169f, 0.9198f, 0.9227f, 0.9254f, 0.9281f, 0.9307f, 0.9333f, 0.9358f, 0.9382f, 0.9406f, 0.9429f, 0.9452f, 0.9474f, 0.9495f, 0.9516f, 0.9536f, 0.9556f, 0.9575f, 0.9594f, 0.9612f, 0.9629f, 0.9646f, 0.9663f, 0.9679f, 0.9695f, 0.971f, 0.9725f, 0.9739f, 0.9753f, 0.9766f, 0.9779f, 0.9791f, 0.9803f, 0.9815f, 0.9826f, 0.9837f, 0.9848f, 0.9858f, 0.9867f, 0.9877f, 0.9885f, 0.9894f, 0.9902f, 0.991f, 0.9917f, 0.9924f, 0.9931f, 0.9937f, 0.9944f, 0.9949f, 0.9955f, 0.996f, 0.9964f, 0.9969f, 0.9973f, 0.9977f, 0.998f, 0.9984f, 0.9986f, 0.9989f, 0.9991f, 0.9993f, 0.9995f, 0.9997f, 0.9998f, 0.9999f, 0.9999f, 1.0f, 1.0f};
        this.f811ff = fArr;
        this.interpolator = new FastOutSlowInInterpolator(fArr);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        float f = isExpanded() ? 1.0f : 0.0f;
        this.expansion = f;
        bundle.putFloat("expansion", f);
        bundle.putParcelable("super_state", parcelableOnSaveInstanceState);
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        float f = bundle.getFloat("expansion");
        this.expansion = f;
        this.state = f == 1.0f ? 3 : 0;
        super.onRestoreInstanceState(bundle.getParcelable("super_state"));
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int i3 = this.orientation == 0 ? measuredWidth : measuredHeight;
        setVisibility((this.expansion == 0.0f && i3 == 0) ? 8 : 0);
        int iRound = i3 - Math.round(i3 * this.expansion);
        float f = this.parallax;
        if (f > 0.0f) {
            float f2 = iRound * f;
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (this.orientation == 0) {
                    int i5 = -1;
                    if (Build.VERSION.SDK_INT >= 17 && getLayoutDirection() == 1) {
                        i5 = 1;
                    }
                    childAt.setTranslationX(i5 * f2);
                } else {
                    childAt.setTranslationY(-f2);
                }
            }
        }
        if (this.orientation == 0) {
            setMeasuredDimension(measuredWidth - iRound, measuredHeight);
        } else {
            setMeasuredDimension(measuredWidth, measuredHeight - iRound);
        }
    }

    public void setOrientatin(int i) {
        this.orientation = i;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        super.onConfigurationChanged(configuration);
    }

    public int getState() {
        return this.state;
    }

    public boolean isExpanded() {
        int i = this.state;
        return i == 2 || i == 3;
    }

    public void toggle() {
        toggle(true);
    }

    public void toggle(boolean z) {
        if (isExpanded()) {
            collapse(z);
        } else {
            expand(z);
        }
    }

    public void expand() {
        expand(true);
    }

    public void expand(boolean z) {
        setExpanded(true, z);
    }

    public void collapse() {
        collapse(true);
    }

    public void collapse(boolean z) {
        setExpanded(false, z);
    }

    public void setExpanded(boolean z) {
        setExpanded(z, true);
    }

    public void setExpanded(boolean z, boolean z2) {
        if (z == isExpanded()) {
            return;
        }
        if (z2) {
            animateSize(z ? 1 : 0);
        } else {
            setExpansion(z ? 1.0f : 0.0f);
        }
    }

    public int getDuration() {
        return this.duration;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public float getExpansion() {
        return this.expansion;
    }

    public void setExpansion(boolean z) {
        float f;
        if (z) {
            this.state = 3;
            f = 1.0f;
        } else {
            this.state = 0;
            f = 0.0f;
        }
        setVisibility(this.state == 0 ? 8 : 0);
        this.expansion = f;
        requestLayout();
        OnExpansionUpdateListener onExpansionUpdateListener = this.listener;
        if (onExpansionUpdateListener != null) {
            onExpansionUpdateListener.onExpansionUpdate(f, this.state);
        }
    }

    public void setExpansion(float f) {
        float f2 = this.expansion;
        if (f2 == f) {
            return;
        }
        float f3 = f - f2;
        if (f == 0.0f) {
            this.state = 0;
        } else if (f == 1.0f) {
            this.state = 3;
        } else if (f3 < 0.0f) {
            this.state = 1;
        } else if (f3 > 0.0f) {
            this.state = 2;
        }
        setVisibility(this.state == 0 ? 8 : 0);
        this.expansion = f;
        requestLayout();
        OnExpansionUpdateListener onExpansionUpdateListener = this.listener;
        if (onExpansionUpdateListener != null) {
            onExpansionUpdateListener.onExpansionUpdate(f, this.state);
        }
    }

    public float getParallax() {
        return this.parallax;
    }

    public void setParallax(float f) {
        this.parallax = Math.min(1.0f, Math.max(0.0f, f));
    }

    public int getOrientation() {
        return this.orientation;
    }

    public void setOrientation(int i) {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("Orientation must be either 0 (horizontal) or 1 (vertical)");
        }
        this.orientation = i;
    }

    public void setOnExpansionUpdateListener(OnExpansionUpdateListener onExpansionUpdateListener) {
        this.listener = onExpansionUpdateListener;
    }

    private void animateSize(int i) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.animator = null;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.expansion, i);
        this.animator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setInterpolator(this.interpolator);
        this.animator.setDuration(this.duration);
        this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {             @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                ExpandableLayout.this.setExpansion(((Float) valueAnimator2.getAnimatedValue()).floatValue());
            }
        });
        this.animator.addListener(new ExpansionListener(i));
        this.animator.start();
    }

    private class ExpansionListener implements Animator.AnimatorListener {
        private boolean canceled;
        private int targetExpansion;

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        public ExpansionListener(int i) {
            this.targetExpansion = i;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            ExpandableLayout.this.state = this.targetExpansion == 0 ? 1 : 2;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.canceled) {
                return;
            }
            ExpandableLayout.this.state = this.targetExpansion == 0 ? 0 : 3;
            ExpandableLayout.this.setExpansion(this.targetExpansion);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.canceled = true;
        }
    }

    public class FastOutSlowInInterpolator extends LookupTableInterpolator {
        @Override // com.fufufu.katrina.backup.ExpandableLayout.LookupTableInterpolator, android.animation.TimeInterpolator
        public /* bridge */ /* synthetic */ float getInterpolation(float f) {
            return super.getInterpolation(f);
        }

        public FastOutSlowInInterpolator(float[] fArr) {
            super(fArr);
        }
    }

    abstract class LookupTableInterpolator implements Interpolator {
        private float mStepSize;
        private float[] mValues;

        public LookupTableInterpolator(float[] fArr) {
            this.mValues = fArr;
            this.mStepSize = 1.0f / (fArr.length - 1);
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            if (f >= 1.0f) {
                return 1.0f;
            }
            if (f <= 0.0f) {
                return 0.0f;
            }
            float[] fArr = this.mValues;
            int iMin = Math.min((int) ((fArr.length - 1) * f), fArr.length - 2);
            float f2 = this.mStepSize;
            float f3 = (f - (iMin * f2)) / f2;
            float[] fArr2 = this.mValues;
            float f4 = fArr2[iMin];
            return f4 + (f3 * (fArr2[iMin + 1] - f4));
        }
    }
}