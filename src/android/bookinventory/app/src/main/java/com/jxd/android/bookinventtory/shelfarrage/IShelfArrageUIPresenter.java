package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.mvp.IPresenter;

/**
 * Created by Administrator on 2017/10/16.
 */

public interface IShelfArrageUIPresenter  extends IPresenter{
    void getShelfInfoByShelfCode(String shelfCode);
}
