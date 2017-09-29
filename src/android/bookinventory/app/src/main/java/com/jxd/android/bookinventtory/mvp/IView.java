package com.jxd.android.bookinventtory.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by Administrator on 2017/9/8.
 */

public interface IView {
    void showProgress();

    void hideProgress();

    void toast(String msg);

    LifecycleTransformer bindLifecycle();
}
