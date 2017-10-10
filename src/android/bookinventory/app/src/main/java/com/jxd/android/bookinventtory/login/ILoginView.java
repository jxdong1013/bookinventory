package com.jxd.android.bookinventtory.login;

import com.jxd.android.bookinventtory.bean.UserBean;
import com.jxd.android.bookinventtory.mvp.IView;

/**
 * Created by Administrator on 2017/10/9.
 */

public interface ILoginView extends IView{
    void callback(UserBean user);
}
