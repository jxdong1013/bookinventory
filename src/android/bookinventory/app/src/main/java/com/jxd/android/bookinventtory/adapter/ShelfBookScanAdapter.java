package com.jxd.android.bookinventtory.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.BookStatusEnum;
import com.jxd.android.bookinventtory.bean.ShelfBookScanBean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class ShelfBookScanAdapter extends BaseQuickAdapter<ShelfBookScanBean , BaseViewHolder> {

    public ShelfBookScanAdapter(@Nullable List<ShelfBookScanBean> data) {
        super(R.layout.layout_shelfarragescan_item , data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShelfBookScanBean item) {
        helper.setText(R.id.shelfarragescan_item_bookcode , item.getBookcode());
        helper.setText(R.id.shelfarragescan_item_bookname , item.getBookName());
        helper.setText(R.id.shelfarragescan_item_status , item.getStatus());
        if( item.getStatus() !=null && item.getStatus().equals(BookStatusEnum.IN.getCode())){
            helper.setTextColor(R.id.shelfarragescan_item_status , ContextCompat.getColor(this.mContext , R.color.green ));
        }else if(item.getStatus() !=null && item.getStatus().equals( BookStatusEnum.OUT.getCode())){
            helper.setTextColor( R.id.shelfarragescan_item_status , ContextCompat.getColor(this.mContext , R.color.red ) );
        }

        if(item.getScanStatus()!=null && item.getScanStatus().equals(BookStatusEnum.IN.getCode())){
            helper.setVisible(R.id.shelfarragescan_item_scanStatus,true);
            helper.setImageResource(R.id.shelfarragescan_item_scanStatus , R.mipmap.right);
        }else if( item.getScanStatus()!=null && item.getScanStatus().equals( BookStatusEnum.NEW .getCode())){
            helper.setVisible(R.id.shelfarragescan_item_scanStatus , true);
            helper.setImageResource(R.id.shelfarragescan_item_scanStatus , R.mipmap.newone);
        }else {
            helper.setVisible(R.id.shelfarragescan_item_scanStatus,false);
            helper.setImageResource(R.id.shelfarragescan_item_scanStatus , 0);
        }
    }
}
