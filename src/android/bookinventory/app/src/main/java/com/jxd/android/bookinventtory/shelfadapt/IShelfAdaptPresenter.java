package com.jxd.android.bookinventtory.shelfadapt;

import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.BookShelfAdptBean;
import com.jxd.android.bookinventtory.bean.ShelfBean;
import com.jxd.android.bookinventtory.mvp.IPresenter;

/**
 * Created by Administrator on 2017/10/12.
 */

public interface IShelfAdaptPresenter extends IPresenter {
    /**
     * 保存图书架位调整记录到本地数据库
     * @param bookShelfAdptBean
     */
    void saveBookShelfAdaptResult(BookShelfAdptBean bookShelfAdptBean);

    /***
     * 从本地数据库获取图书架位调整扫描记录列表
     */
    void getBookShelfScanList();

    void deleteAll();

    void deleteOne(BookShelfAdptBean bookShelfAdptBean);

}
