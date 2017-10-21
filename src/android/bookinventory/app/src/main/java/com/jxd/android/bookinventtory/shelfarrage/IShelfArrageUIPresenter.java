package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.ShelfBean;
import com.jxd.android.bookinventtory.bean.ShelfScanBean;
import com.jxd.android.bookinventtory.mvp.IPresenter;

/**
 * Created by Administrator on 2017/10/16.
 */

public interface IShelfArrageUIPresenter  extends IPresenter{
    /**
     *
     * @param shelfCode
     */
    void getShelfInfoByShelfCode(String shelfCode);

    /**
     *  保存架位整理数据
     */
    void saveShelfData(ShelfScanBean shelfScanBean);
}
