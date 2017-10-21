package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.BookShelfAdptBean;
import com.jxd.android.bookinventtory.bean.ShelfScanBean;
import com.jxd.android.bookinventtory.mvp.IModel;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by jinxiangdong on 2017/10/14.
 */

public interface IShelfArrageModel extends IModel{

    void getDataFromLocal(RealmChangeListener<RealmResults<ShelfScanBean>> realmResultsRealmChangeListener);
}
