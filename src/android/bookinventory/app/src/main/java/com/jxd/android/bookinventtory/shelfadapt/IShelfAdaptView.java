package com.jxd.android.bookinventtory.shelfadapt;

import com.jxd.android.bookinventtory.bean.BookShelfAdptBean;
import com.jxd.android.bookinventtory.mvp.IView;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public interface IShelfAdaptView extends IView {

    void saveCallback();

    void getCallback(List<BookShelfAdptBean> bookShelfAdptBeanList);

    void deleteCallback();

}
