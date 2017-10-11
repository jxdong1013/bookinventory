package com.jxd.android.bookinventtory.login;

import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.DataBase;
import com.jxd.android.bookinventtory.bean.ResultCodeEnum;
import com.jxd.android.bookinventtory.bean.UserBean;
import com.jxd.android.bookinventtory.config.Constants;

import java.net.SocketTimeoutException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static android.R.attr.data;

/**
 * Created by Administrator on 2017/10/9.
 */

public class LoginPresenter implements ILoginPresenter , Observer<DataBase<UserBean>> {
    ILoginView iLoginView;
    ILoginModel iLoginModel;
    public LoginPresenter(ILoginView iLoginView,ILoginModel iLoginModel){
        this.iLoginView=iLoginView;
        this.iLoginModel=iLoginModel;
    }

    @Override
    public void login(String userName, String password) {

        iLoginModel.login( userName , password , this);

    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        iLoginView.showProgress();
    }

    @Override
    public void onNext(@NonNull DataBase<UserBean> userBeanDataBase) {
        if(userBeanDataBase.getCode() == ResultCodeEnum.SUCCESS.getCode()) {
            iLoginView.callback(userBeanDataBase.getData());
        }else{
            iLoginView.toast(userBeanDataBase.getCode()+ userBeanDataBase.getMessage());
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        iLoginView.hideProgress();
        if(e instanceof SocketTimeoutException){
            iLoginView.error(Constants.MESSAGE_TIMEOUT);
        }else {
            iLoginView.error(Constants.MESSAGE_ERROR);
        }
    }

    @Override
    public void onComplete() {
        iLoginView.hideProgress();
    }

    @Override
    public void onDestory() {

    }
}
