package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.DataBase;
import com.jxd.android.bookinventtory.bean.ResultCodeEnum;
import com.jxd.android.bookinventtory.bean.ShelfBean;
import com.jxd.android.bookinventtory.config.Constants;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/10/16.
 */

public class ShelfArrageUIPresenter implements IShelfArrageUIPresenter , Observer<DataBase<ShelfBean>> {
    IShelfArrageUIModel iShelfArrageUIModel;
    IShelfArrageUIView iShelfArrageUIView;
    public ShelfArrageUIPresenter(IShelfArrageUIView iShelfArrageUIView , IShelfArrageUIModel iShelfArrageUIModel    ){
        this.iShelfArrageUIView = iShelfArrageUIView;
        this.iShelfArrageUIModel=iShelfArrageUIModel;
    }

    @Override
    public void getShelfInfoByShelfCode(String shelfCode) {
        iShelfArrageUIModel.getShelfInfoByShelfCode( shelfCode , this);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        iShelfArrageUIView.showProgress(Constants.TIP_WAITING);
    }

    @Override
    public void onNext(@NonNull DataBase<ShelfBean> shelfBeanDataBase) {
        if( shelfBeanDataBase.getCode() == ResultCodeEnum.SUCCESS.getCode() ) {
            iShelfArrageUIView.getShelfInfoCallback(shelfBeanDataBase.getData());
        }else if(shelfBeanDataBase.getCode() == ResultCodeEnum.LOGIN.getCode()){
            iShelfArrageUIView.login();
        }else{
            iShelfArrageUIView.error(Constants.MESSAGE_ERROR);
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        iShelfArrageUIView.hideProgress();
    }

    @Override
    public void onComplete() {
        iShelfArrageUIView.hideProgress();
    }

    @Override
    public void onDestory() {
        if(iShelfArrageUIView!=null){
            iShelfArrageUIView=null;
        }
        if(iShelfArrageUIModel!=null){
            iShelfArrageUIModel.onDestory();
            iShelfArrageUIModel=null;
        }
    }
}
