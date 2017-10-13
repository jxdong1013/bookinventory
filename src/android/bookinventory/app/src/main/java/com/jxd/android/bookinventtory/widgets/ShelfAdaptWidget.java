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
import android.widget.TextView;

import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.ShelfBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.R.attr.paddingLeft;
import static android.R.attr.paddingRight;
import static android.R.attr.paddingTop;

/**
 * TODO: document your custom view class.
 */
public class ShelfAdaptWidget extends LinearLayout {

    public interface onSaveScanResultListener{
        void saveScanResult(BookBean bookBean , ShelfBean ShelfBean);
    }

    Unbinder unbinder;

    @BindView(R.id.laybook)
    LinearLayout layBook;
    @BindView(R.id.layshelf)
    LinearLayout layShelf;
    @BindView(R.id.bookshelfscan_bookcode)
    TextView tvbookcode;
    @BindView(R.id.bookshelfscan_bookname)
    TextView tvbookName;
    @BindView(R.id.bookshelfscan_shelfcode)
    TextView tvshelfcode;
    @BindView(R.id.bookshelfscan_shelfname)
    TextView tvshelfName;
    @BindView(R.id.layTip)
    LinearLayout layTip;
    @BindView(R.id.tip)
    TextView tvTip;

    BookBean bookBean;
    ShelfBean shelfBean;
    onSaveScanResultListener onSaveScanResultListener;

    public void setOnSaveScanResultListener(ShelfAdaptWidget.onSaveScanResultListener onSaveScanResultListener) {
        this.onSaveScanResultListener = onSaveScanResultListener;
    }

    public ShelfAdaptWidget(Context context) {
        super(context);
        init(null, 0);
    }

    public ShelfAdaptWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ShelfAdaptWidget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ShelfAdaptWidget, defStyle, 0);

        a.recycle();

        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext() );
        layoutInflater.inflate(R.layout.layout_shelfadapt_scan  , this , true);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        unbinder = ButterKnife.bind(this  );
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setBookInfo(BookBean bookBean){
        layBook.setVisibility(VISIBLE);
        tvbookcode.setText(bookBean.getBookcode());
        tvbookName.setText(bookBean.getBookName());
        this.bookBean=bookBean;

        saveScanResult();
    }
    public void setShelfInfo(ShelfBean shelfBean){
        layShelf.setVisibility(VISIBLE);
        tvshelfcode.setText(shelfBean.getShelfCode());
        tvshelfName.setText(shelfBean.getShelfName());
        this.shelfBean = shelfBean;

        saveScanResult();
    }

    protected void saveScanResult(){
        if(bookBean!=null && shelfBean!=null){
            layTip.setVisibility(GONE);
            if( onSaveScanResultListener !=null){
                onSaveScanResultListener.saveScanResult( bookBean , shelfBean );
            }else{
                throw new RuntimeException("lost onSaveScanResultListener");
            }
        }else{
            layTip.setVisibility(VISIBLE);
            if( bookBean ==null && shelfBean ==null ) {
                tvTip.setText( this.getContext().getString(R.string.tipmessage1) );
            }else if(bookBean ==null ){
                tvTip.setText(this.getContext().getString(R.string.tipmessage2));
            }else if(shelfBean==null){
                tvTip.setText(this.getContext().getString(R.string.tipmessage3));
            }
        }
    }

    public void reset(){
        layBook.setVisibility(GONE);
        tvbookName.setText("");
        tvbookcode.setText("");
        layShelf.setVisibility(GONE);
        tvshelfcode.setText("");
        tvshelfName.setText("");
        layTip.setVisibility(VISIBLE);
        tvTip.setText(getContext().getString(R.string.tipmessage1));
        bookBean=null;
        shelfBean=null;
    }
}
