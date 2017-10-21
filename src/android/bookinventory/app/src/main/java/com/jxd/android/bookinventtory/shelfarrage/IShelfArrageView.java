package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.ShelfScanBean;
import com.jxd.android.bookinventtory.mvp.IView;

import java.util.List;

/**
 * Created by jinxiangdong on 2017/10/14.
 */

public interface IShelfArrageView extends IView {
    void getCallback(List<ShelfScanBean> data);
}
