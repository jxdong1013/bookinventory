package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.DataBase;
import com.jxd.android.bookinventtory.bean.OperateTypeEnum;
import com.jxd.android.bookinventtory.bean.ResultCodeEnum;
import com.jxd.android.bookinventtory.bean.ShelfBean;
import com.jxd.android.bookinventtory.bean.ShelfScanBean;
import com.jxd.android.bookinventtory.config.Constants;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;

/**
 * Created by Administrator on 2017/10/16.
 */

public class ShelfArrageUIPresenter
        implements IShelfArrageUIPresenter , Observer<DataBase<ShelfBean>> , Realm.Transaction.OnSuccess,Realm.Transaction.OnError {
    IShelfArrageUIModel iShelfArrageUIModel;
    IShelfArrageUIView iShelfArrageUIView;
    OperateTypeEnum operateTypeEnum = OperateTypeEnum.None;

    public ShelfArrageUIPresenter(IShelfArrageUIView iShelfArrageUIView , IShelfArrageUIModel iShelfArrageUIModel    ){
        this.iShelfArrageUIView = iShelfArrageUIView;
        this.iShelfArrageUIModel=iShelfArrageUIModel;
    }

    @Override
    public void getShelfInfoByShelfCode(String shelfCode) {
        operateTypeEnum =OperateTypeEnum.Query;
        iShelfArrageUIModel.getShelfInfoByShelfCode( shelfCode , this);
    }

    @Override
    public void saveShelfData(ShelfScanBean shelfScanBean) {
        operateTypeEnum = OperateTypeEnum.Update;
        iShelfArrageUIModel.saveShelfScanData( shelfScanBean , this , this );
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
        if(operateTypeEnum==OperateTypeEnum.Query){
            iShelfArrageUIView.error(e.getMessage());
        }else if(operateTypeEnum==OperateTypeEnum.Update){
            iShelfArrageUIView.error(e.getMessage());
        }
    }

    @Override
    public void onComplete() {
        iShelfArrageUIView.hideProgress();
    }

    @Override
    public void onSuccess() {
        iShelfArrageUIView.hideProgress();
        iShelfArrageUIView.saveCallback();
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
