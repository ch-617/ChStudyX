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

public class TrimHorizontalButton extends View {
    static final int MAXVALUE = 32;// 最大值
    static final int CENTREVALUE = 16;// 中间值
    private float portionX;// 每次点击控件的X偏移量

    private int mWidth;//控件宽
    private int mHeigh;//控件高

    private Drawable mTrackDrawable;//背景部分
    private int mTrackTop;
    private int mTrackBottom;
    private int mTrackLeft;
    private int mTrackRight;

    private Drawable mThumbDrawable;//中心按钮
    private int mThumbWidth;//中心按钮宽
    private int mThumbLeft;
    private int mThumbRight;
    private int mThumbTop;
    private int mThumbBottom;

    private int currentThumbLeft;//当前按钮位置
    private int currentThumbRight;//当前按钮位置

    private int mOffsetX = 0;//X方向偏移量
    private int tempOffsetX;

    public TrimHorizontalButton(Context context) {
        this(context, null);
    }

    public TrimHorizontalButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TrimHorizontalButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TrimHorizontalButton);
        mThumbDrawable = typedArray.getDrawable(R.styleable.TrimHorizontalButton_thumbTrimH);
        mTrackDrawable = typedArray.getDrawable(R.styleable.TrimHorizontalButton_trackTrimH);

        invalidate();

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int trackHeight = mTrackDrawable.getIntrinsicHeight();
        final int trackWidth = mTrackDrawable.getIntrinsicWidth();
        final int thumbHeight = mThumbDrawable.getIntrinsicHeight();
        final int thumbWidth = mThumbDrawable.getIntrinsicWidth();

        mHeigh = Math.max(trackHeight, thumbHeight);
        mWidth = Math.max(trackWidth, thumbWidth);

        mTrackTop = -trackHeight / 2;
        mTrackBottom = mTrackTop + trackHeight;
        mTrackLeft = -trackWidth / 2;
        mTrackRight = mTrackLeft + trackWidth;

        mHeigh = thumbHeight;
        mThumbTop = -thumbHeight / 2;
        mThumbBottom = mThumbTop + thumbHeight;
        mThumbLeft = -thumbWidth / 2;
        mThumbRight = mThumbLeft + thumbWidth;

        portionX = (float) mWidth / MAXVALUE;

        setMeasuredDimension(mWidth, mHeigh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mTrackDrawable != null && mThumbDrawable != null) {
            canvas.save();
            canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
            mTrackDrawable.setBounds(mTrackLeft, mTrackTop, mTrackRight, mTrackBottom);
            mTrackDrawable.draw(canvas);

            int left = mThumbLeft + tempOffsetX;
            int right = mThumbRight + tempOffsetX;

            if (currentThumbLeft != 0 && currentThumbRight != 0) {
                if (left < currentThumbLeft) {
                    left = (int) (currentThumbLeft - portionX);
                    right = (int) (currentThumbRight - portionX);
                } else if (left > currentThumbLeft){
                    left = (int) (currentThumbLeft + portionX);
                    right = (int) (currentThumbRight + portionX);
                }
            }

            if (left < mTrackLeft) {
                left = mTrackLeft;
                right = mTrackLeft + mThumbWidth;
            } else if (right > mTrackRight) {
                right = mTrackRight;
                left = right - mThumbWidth;
            }
            mOffsetX = tempOffsetX = left - mThumbLeft;
            currentThumbLeft = left;
            currentThumbRight = right;
            mThumbDrawable.setBounds(left, mThumbTop, right, mThumbBottom);
            mThumbDrawable.draw(canvas);
            canvas.restore();

            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            tempOffsetX = (int) (event.getX() - mWidth / 2 + 0.5f);
        }
        return true;
    }

    public int getmOffsetX() {
        int result;
        float portion = ((float) MAXVALUE) / mWidth;
        result = (int) (CENTREVALUE + mOffsetX * portion);
        if (mOffsetX < 0 && result < 0) {
            result = 0;
        } else if (mOffsetX > 0 && result > MAXVALUE) {
            result = MAXVALUE;
        }
        return result;
    }
}
