package com.jxd.android.bookinventtory.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.jxd.android.bookinventtory.R;

/**
 *  错误页面
 */
public class ErrorWidget extends LinearLayout {

    public ErrorWidget(Context context) {
        super(context);
        init(null, 0);
    }

    public ErrorWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ErrorWidget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
//        final TypedArray a = getContext().obtainStyledAttributes(
//                attrs, R.styleable.ErrorWidget, defStyle, 0);
//
//
//        a.recycle();

        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext() );
        layoutInflater.inflate(R.layout.layout_error  , this , true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

    }

}
