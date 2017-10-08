package com.jxd.android.bookinventtory.booksearch;

import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.mvp.IView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public interface IBookSearchView extends IView {

    void callback(List<BookBean> data);

}
