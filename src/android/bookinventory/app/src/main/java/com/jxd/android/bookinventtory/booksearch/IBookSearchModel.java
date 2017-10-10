package com.jxd.android.bookinventtory.booksearch;

import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.BookCondition;
import com.jxd.android.bookinventtory.mvp.IModel;

import io.reactivex.Observer;

/**
 * Created by Administrator on 2017/9/8.
 */

public interface IBookSearchModel extends IModel {

    void getBookList(BookCondition condition , Observer subscriber);

}
