package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.ShelfScanBeam;
import com.jxd.android.bookinventtory.mvp.IModel;

import io.reactivex.Observer;
import io.realm.Realm;

/**
 * Created by Administrator on 2017/10/16.
 */
public interface IShelfArrageUIModel extends IModel {

    void getShelfInfoByShelfCode(String shelfCode , Observer observer);

    void saveShelfScanData(ShelfScanBeam shelfScanBeam , Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError);
}
