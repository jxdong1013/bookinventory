package com.jxd.android.bookinventtory.booksearch;

import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.mvp.IPresenter;

import io.reactivex.Observer;

/**
 * Created by Administrator on 2017/9/8.
 */

public interface IBookSearchPresenter extends IPresenter {
    void getBookList(BookBean condition);
}
