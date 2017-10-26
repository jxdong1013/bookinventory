package com.jxd.android.bookinventtory.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.bean.BookLevelItem;
import com.jxd.android.bookinventtory.bean.BookStatusEnum;
import com.jxd.android.bookinventtory.bean.ShelfLevelItem;
import com.jxd.android.bookinventtory.utils.DateUtils;

import java.security.PublicKey;
import java.util.List;

/**
 * Created by Administrator on 2017/10/21.
 */

public class ShelfArrageAdapter
        extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static int ITEM_TYPE_SHELF=1;
    public static int ITEM_TYPE_BOOK=2;

    public ShelfArrageAdapter(List<MultiItemEntity> data) {
        super(data);

        this.addItemType(ITEM_TYPE_SHELF , R.layout.layout_shelfarrage_shelf_item);
        this.addItemType(ITEM_TYPE_BOOK , R.layout.layout_shelfarrage_book_item);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        if(helper.getItemViewType() == ITEM_TYPE_SHELF){
            final ShelfLevelItem shelfLevelItem = (ShelfLevelItem)item;
            helper.setImageResource( R.id.shelfarrage_shelf_item_level ,
                    shelfLevelItem.isExpanded() ? R.mipmap.jian : R.mipmap.jia );
            helper.setText(R.id.shelfarrage_shelf_item_shelfno , shelfLevelItem.getShelf().getShelfno() );

            helper.setText(R.id.shelfarrage_shelf_item_scandatetime , DateUtils.formatDate( shelfLevelItem.getShelf().getScanDatetime().getTime()));

            helper.itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = helper.getAdapterPosition();
                            if (shelfLevelItem.isExpanded()) {
                                collapse(pos);
                            } else {
                                expand(pos);
                            }
                        }
                    }
            );

        }else if(helper.getItemViewType() == ITEM_TYPE_BOOK){
            BookLevelItem bookLevelItem = (BookLevelItem)item;

            helper.setImageResource( R.id.shelfarrage_book_item_scanstatus ,
                    bookLevelItem.getBook().getScanStatus() == null? 0:
                    bookLevelItem.getBook().getScanStatus().equals( BookStatusEnum.IN.getCode())
                            ? R.mipmap.right:R.mipmap.newone);

            helper.setText( R.id.shelfarrage_book_item_title , bookLevelItem.getBook().getTitle());
        }
    }
}
