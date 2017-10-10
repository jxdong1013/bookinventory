package com.jxd.android.bookinventtory.login;

import com.jxd.android.bookinventtory.mvp.IPresenter;

/**
 * Created by Administrator on 2017/10/9.
 */

public interface ILoginPresenter extends IPresenter {

    void login(String userName , String password);

}
