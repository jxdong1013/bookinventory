package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.BookShelfAdptBean;
import com.jxd.android.bookinventtory.bean.ShelfScanBean;
import com.jxd.android.bookinventtory.config.Constants;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by jinxiangdong on 2017/10/14.
 */

public class ShelfArragePresenter
        implements IShelfArragePresenter,
        RealmChangeListener<RealmResults<ShelfScanBean>> {
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
    public void onChange(RealmResults<ShelfScanBean> shelfScanBeen) {

        shelfScanBeen = shelfScanBeen.sort("scanDatetime" , Sort.DESCENDING);
        iShelfArrageView.getCallback(shelfScanBeen);
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
