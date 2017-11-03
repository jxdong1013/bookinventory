package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.BookShelfAdptBean;
import com.jxd.android.bookinventtory.bean.ShelfScanBean;
import com.jxd.android.bookinventtory.config.Constants;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by jinxiangdong on 2017/10/14.
 */

public class ShelfArragePresenter
        implements IShelfArragePresenter,
        RealmChangeListener<RealmResults<ShelfScanBean>>,
        Realm.Transaction.OnSuccess,
        Realm.Transaction.OnError{
    IShelfArrageView iShelfArrageView;
    IShelfArrageModel iShelfArrageModel;
    public ShelfArragePresenter(IShelfArrageView iShelfArrageView,IShelfArrageModel iShelfArrageModel){
        this.iShelfArrageView= iShelfArrageView;
        this.iShelfArrageModel = iShelfArrageModel;
    }

    public void getDataFromLocal(){
        this.iShelfArrageView.showProgress(Constants.TIP_LOGINNG);
        this.iShelfArrageModel.getDataFromLocal(this);
    }

    @Override
    public void deleteLocalData(List<String> data ) {
        this.iShelfArrageView.showProgress(Constants.TIP_PROCESSING);
        this.iShelfArrageModel.deleteLocalData( data , this , this  );
    }

    @Override
    public void onChange(RealmResults<ShelfScanBean> shelfScanBeen) {

        shelfScanBeen = shelfScanBeen.sort("scanDatetime" , Sort.DESCENDING);
        iShelfArrageView.getCallback(shelfScanBeen);
    }

    @Override
    public void onError(Throwable error) {
        this.iShelfArrageView.hideProgress();
        this.iShelfArrageView.error(error.getMessage());
    }

    @Override
    public void onSuccess() {
        this.iShelfArrageView.hideProgress();
        this.iShelfArrageView.deleteCallback();
    }

    @Override
    public void onDestory() {
        if(iShelfArrageView!=null){
            iShelfArrageView=null;
        }
        if(iShelfArrageModel!=null){
            iShelfArrageModel.onDestory();
            iShelfArrageModel=null;
        }
    }
}
