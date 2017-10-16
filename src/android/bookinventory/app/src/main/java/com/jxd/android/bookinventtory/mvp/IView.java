package com.jxd.android.bookinventtory.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by Administrator on 2017/9/8.
 */

public interface IView {
    void showProgress(String msg);

    void hideProgress();

    void toast(String msg);

    void error(String msg );

    LifecycleTransformer bindLifecycle();
}
