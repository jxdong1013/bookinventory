package com.jxd.android.bookinventtory.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.bean.BookModel;
import com.jxd.android.bookinventtory.bean.ShelfModel;

import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */

public class ShelfSearchAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity ,BaseViewHolder> {
    public static final int ITEM_TYPE_SHELF=1;
    public static final  int ITEM_TYPE_BOOK =2;

    public ShelfSearchAdapter(List<MultiItemEntity> data) {
        super(data);

        this.addItemType( ITEM_TYPE_SHELF , R.layout.layout_shelfsearch_shelfitem);
        this.addItemType(ITEM_TYPE_BOOK,R.layout.layout_shelfsearch_bookitem);

    }


    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        if(helper.getItemViewType() == ITEM_TYPE_SHELF){
            ShelfModel shelfModel= (ShelfModel)item;
            helper.setText( R.id.shelf_item_shelfname , shelfModel.getShelfName());
        }else if(helper.getItemViewType() == ITEM_TYPE_BOOK){
            BookModel bookModel=(BookModel)item;
            helper.setText(R.id.bookitem_bookid , bookModel.getBookId());
            helper.setText(R.id.bookitem_bookname , bookModel.getBookName());
            helper.setText(R.id.bookitem_author,bookModel.getAuthor());
            helper.setText(R.id.bookitem_bookcode , bookModel.getBookcode());
            helper.setText(R.id.bookitem_position,bookModel.getPosition());
        }
    }
}
