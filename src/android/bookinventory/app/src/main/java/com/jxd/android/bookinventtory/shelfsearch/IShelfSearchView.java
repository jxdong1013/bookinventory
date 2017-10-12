package com.jxd.android.bookinventtory.shelfsearch;

import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.Page;
import com.jxd.android.bookinventtory.bean.ShelfBean;
import com.jxd.android.bookinventtory.mvp.IView;

import static android.R.attr.data;

/**
 * Created by jinxiangdong on 2017/9/30.
 */

public interface IShelfSearchView extends IView {

    void callback( ShelfBean data);

    void login();

}
