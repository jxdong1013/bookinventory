package com.jxd.android.bookinventtory.login;

import com.jxd.android.bookinventtory.bean.DataBase;
import com.jxd.android.bookinventtory.bean.UserBean;
import com.jxd.android.bookinventtory.mvp.IModel;

import io.reactivex.Observer;

/**
 * Created by Administrator on 2017/10/9.
 */

interface ILoginModel extends IModel {

    void login(String userName , String password , Observer<DataBase<UserBean>> observer);
}
