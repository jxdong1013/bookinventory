package com.jxd.android.bookinventtory.shelfsearch;

import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.DataBase;
import com.jxd.android.bookinventtory.bean.Page;
import com.jxd.android.bookinventtory.bean.ResultCodeEnum;
import com.jxd.android.bookinventtory.bean.ShelfBean;
import com.jxd.android.bookinventtory.bean.ShelfCondition;
import com.jxd.android.bookinventtory.config.Constants;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;

import static android.R.attr.data;

/**
 * Created by Administrator on 2017/10/9.
 */

public class ShelfSearchPresenter implements IShelfSearchPresenter ,Observer<DataBase<ShelfBean>> {

    private IShelfSearchModel iShelfSearchModel;
    private IShelfSearchView iShelfSearchView;

    public ShelfSearchPresenter(IShelfSearchView iShelfSearchView , IShelfSearchModel iShelfSearchModel){
        this.iShelfSearchModel = iShelfSearchModel;
        this.iShelfSearchView = iShelfSearchView;
    }

    @Override
    public void getShelfList(ShelfCondition condition) {
        iShelfSearchModel.getShelfList(condition,this);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        iShelfSearchView.showProgress(Constants.TIP_WAITING);
    }

    @Override
    public void onNext(@NonNull DataBase<ShelfBean> shelfBeanDataBase) {
        if(shelfBeanDataBase.getCode() == ResultCodeEnum.SUCCESS.getCode()) {
            iShelfSearchView.callback(shelfBeanDataBase.getData());
        }else if(  shelfBeanDataBase.getCode() == ResultCodeEnum.LOGIN.getCode()){
            iShelfSearchView.login();
        }else{
            iShelfSearchView.toast(shelfBeanDataBase.getCode()+ shelfBeanDataBase.getMessage());
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        iShelfSearchView.hideProgress();
        iShelfSearchView.error(Constants.MESSAGE_ERROR);
    }

    @Override
    public void onComplete() {
        iShelfSearchView.hideProgress();
    }

    @Override
    public void onDestory() {
        if(iShelfSearchView!=null){
            iShelfSearchView= null;
        }
        if(iShelfSearchModel!=null){
            iShelfSearchModel.onDestory();
            iShelfSearchModel=null;
        }
    }
}
