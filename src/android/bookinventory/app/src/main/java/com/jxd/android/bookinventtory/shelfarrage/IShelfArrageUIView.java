package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.ShelfBean;
import com.jxd.android.bookinventtory.mvp.IView;

/**
 * Created by Administrator on 2017/10/16.
 */
public interface IShelfArrageUIView extends IView {
    void getShelfInfoCallback(ShelfBean shelfBean);
    void login();
    void saveCallback();
}
