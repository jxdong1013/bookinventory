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
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jxd.android.bookinventtory.R;

import org.w3c.dom.Text;

/**
 * TODO: document your custom view class.
 */
public class ProgressTextWidget extends LinearLayout {
    TextView tvProgressText;
    ProgressBar progressBar;

    public ProgressTextWidget(Context context) {
        super(context);
        init(null, 0);
    }

    public ProgressTextWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ProgressTextWidget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_progresstext  , this , true);
        tvProgressText = findViewById(R.id.progressText_text);
        progressBar = findViewById(R.id.progressText_bar);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setProgress(int precent , String message){
        this.tvProgressText.setText(  precent +"/100" );
        this.progressBar.setProgress(precent);
    }

}
