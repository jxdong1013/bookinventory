package com.jxd.android.bookinventtory.shelfadapt;

import com.jxd.android.bookinventtory.bean.BookShelfAdptBean;
import com.jxd.android.bookinventtory.bean.DataBase;
import com.jxd.android.bookinventtory.bean.OperateTypeEnum;
import com.jxd.android.bookinventtory.config.Constants;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Administrator on 2017/10/12.
 */

public class ShelfAdaptPresenter implements IShelfAdaptPresenter
        , Observer<DataBase<Object>>
        , Realm.Transaction.OnSuccess
        , Realm.Transaction.OnError
        , RealmChangeListener<RealmResults<BookShelfAdptBean>>{
    IShelfAdaptView iShelfAdaptView;
    IShelfAdaptModel iShelfAdaptModel;
    OperateTypeEnum operateTypeEnum = OperateTypeEnum.None;

    public ShelfAdaptPresenter(IShelfAdaptView iShelfAdaptView, IShelfAdaptModel iShelfAdaptModel){
        this.iShelfAdaptModel=iShelfAdaptModel;
        this.iShelfAdaptView=iShelfAdaptView;
    }

    @Override
    public void saveBookShelfAdaptResult(BookShelfAdptBean bookShelfAdptBean) {
        iShelfAdaptView.showProgress(Constants.TIP_PROCESSING);
        operateTypeEnum = OperateTypeEnum.Insert;
        iShelfAdaptModel.saveBookShelfAdaptResult( bookShelfAdptBean , this , this );
    }

    @Override
    public void getBookShelfScanList() {
        iShelfAdaptView.showProgress(Constants.TIP_WAITING);
        operateTypeEnum = OperateTypeEnum.Query;
        iShelfAdaptModel.getBookShelfScanList(this);
    }

    @Override
    public void deleteAll() {
        iShelfAdaptView.showProgress(Constants.TIP_PROCESSING);
        operateTypeEnum= OperateTypeEnum.Delete;
        iShelfAdaptModel.deleteAll(this, this);
    }

    @Override
    public void deleteOne(BookShelfAdptBean bookShelfAdptBean) {
        iShelfAdaptView.showProgress(Constants.TIP_PROCESSING);
        operateTypeEnum= OperateTypeEnum.Delete;
        iShelfAdaptModel.deleteOne( bookShelfAdptBean , this, this);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
       // iShelfAdaptView.showProgress();
    }

    @Override
    public void onNext(@NonNull DataBase<Object> objectDataBase) {

    }

    @Override
    public void onError(@NonNull Throwable e) {
        iShelfAdaptView.hideProgress();
        //if(operateTypeEnum== OperateTypeEnum.Insert) {
            iShelfAdaptView.error(e.getMessage());
        //}else if(operateTypeEnum==Op)
    }

    @Override
    public void onComplete() {
        iShelfAdaptView.hideProgress();
    }

    @Override
    public void onSuccess() {
        iShelfAdaptView.hideProgress();
        if( operateTypeEnum== OperateTypeEnum.Insert) {
            iShelfAdaptView.saveCallback();
        }else if(operateTypeEnum== OperateTypeEnum.Delete){
            iShelfAdaptView.deleteCallback();
        }
    }


    @Override
    public void onChange(RealmResults<BookShelfAdptBean> realmResults  ) {
         realmResults = realmResults.sort("adaptTime" , Sort.DESCENDING);
        iShelfAdaptView.getCallback(realmResults);
    }

    @Override
    public void onDestory() {
        if( iShelfAdaptView !=null){
            iShelfAdaptView=null;
        }
        if(iShelfAdaptModel!=null){
            iShelfAdaptModel.onDestory();
            iShelfAdaptModel=null;
        }
    }
}
