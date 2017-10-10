package com.jxd.android.bookinventtory.login;

import com.jxd.android.bookinventtory.bean.DataBase;
import com.jxd.android.bookinventtory.bean.UserBean;
import com.jxd.android.bookinventtory.http.ApiService;
import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/10/9.
 */

public class LoginModel implements ILoginModel {
    ApiService apiService;
    LifecycleProvider lifecycleProvider;

    public LoginModel(ApiService apiService , LifecycleProvider lifecycleProvider ){
        this.apiService=apiService;
        this.lifecycleProvider=lifecycleProvider;
    }

    @Override
    public void login(String userName, String password , Observer<DataBase<UserBean>> observer ) {

        Observable<DataBase<UserBean>> observable = apiService.login( userName, password );
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose( lifecycleProvider.<DataBase<UserBean>>bindToLifecycle() )
                .subscribe( observer );
    }

    @Override
    public void onDestory() {

    }
}
