package com.xi.gua.chstudyx.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xi.gua.chstudyx.R;

import androidx.annotation.Nullable;

public class RockerVerticalButton extends View {

    static final int MAXVALUE = 255;
    static final int CENTREVALUE = 127;

    private Drawable mThumbDrawable;//中心按钮
    private Drawable mTrackDrawable;//背景部分

    private int mThumbHeight;//中心按钮宽
    private int mThumbLeft;
    private int mThumbRight;
    private int mThumbTop;
    private int mThumbBottom;

    private int mRockerWidth;
    private int mRockerHeigh;
    private int mTrackTop;
    private int mTrackBottom;
    private int mTrackLeft;
    private int mTrackRight;
    private int mOffsetY = 0;//Y方向偏移量

    public RockerVerticalButton(Context context) {
        this(context, null);
    }

    public RockerVerticalButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RockerVerticalButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RockerVerticalButton);
        mThumbDrawable = typedArray.getDrawable(R.styleable.RockerVerticalButton_myThumbV);
        mTrackDrawable = typedArray.getDrawable(R.styleable.RockerVerticalButton_myTrackV);

        if (mTrackDrawable != null) {
            mTrackDrawable.setCallback(this);
        }

        invalidate();

        typedArray.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int trackHeight = mTrackDrawable.getIntrinsicHeight();
        final int trackWidth = mTrackDrawable.getIntrinsicWidth();
        final int thumbHeight = mThumbDrawable.getIntrinsicHeight();
        final int thumbWidth = mThumbDrawable.getIntrinsicWidth();

        mRockerHeigh = Math.max(trackHeight, thumbHeight);
        mRockerWidth = Math.max(trackWidth, thumbWidth);

        mTrackTop = -trackHeight / 2;
        mTrackBottom = mTrackTop + trackHeight;
        mTrackLeft = -trackWidth / 2;
        mTrackRight = mTrackLeft + trackWidth;

        mThumbHeight = thumbHeight;
        mThumbTop = -thumbHeight / 2;
        mThumbBottom = mThumbTop + thumbHeight;
        mThumbLeft = -thumbWidth / 2;
        mThumbRight = mThumbLeft + thumbWidth;

        setMeasuredDimension(mRockerWidth, mRockerHeigh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mTrackDrawable != null && mThumbDrawable != null) {
            canvas.save();
            canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
            mTrackDrawable.setBounds(mTrackLeft, mTrackTop, mTrackRight, mTrackBottom);
            mTrackDrawable.draw(canvas);

            int top = mThumbTop + mOffsetY;
            int bottom = mThumbBottom + mOffsetY;
            if (top < mTrackTop) {
                top = mTrackTop;
                bottom = top + mThumbHeight;
            } else if (bottom > mTrackBottom) {
                bottom = mTrackBottom;
                top = bottom - mThumbHeight;
            }
            mThumbDrawable.setBounds(mThumbLeft, top, mThumbRight, bottom);
            mThumbDrawable.draw(canvas);
            canvas.restore();

            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mOffsetY = (int) (event.getY() - mRockerHeigh / 2 + 0.5f);
                break;
            case MotionEvent.ACTION_UP:
                mOffsetY = 0;
                break;
        }
        return true;
    }

    public int getmOffsetY() {
        int result = CENTREVALUE;
        float portion = ((float) MAXVALUE) / mRockerHeigh;
        if (mOffsetY < 0) {
            result = (int) (-mOffsetY * portion + CENTREVALUE);
            if (result > MAXVALUE) result = MAXVALUE;
        } else if (mOffsetY > 0) {
            result = (int) ((((float) mRockerHeigh) / 2 - mOffsetY) * portion);
            if (result < 0) result = 0;
        }
        return result;
    }
}
