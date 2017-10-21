package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.BookShelfAdptBean;
import com.jxd.android.bookinventtory.bean.ShelfScanBean;
import com.jxd.android.bookinventtory.http.ApiService;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by jinxiangdong on 2017/10/14.
 */

public class ShelfArrageModel implements IShelfArrageModel {
    Realm realm;
    ApiService apiService;

    public ShelfArrageModel(ApiService apiService , Realm realm){
        this.apiService=apiService;
        this.realm= realm;
    }

    @Override
    public void getDataFromLocal(RealmChangeListener<RealmResults<ShelfScanBean>> realmResultsRealmChangeListener) {
        RealmResults<ShelfScanBean> realmResults = realm.where(ShelfScanBean.class).findAllAsync();
        realmResults.addChangeListener( realmResultsRealmChangeListener );
    }

    @Override
    public void onDestory() {
        if(realm!=null){
            realm.removeAllChangeListeners();
            realm.close();
            realm=null;
        }
    }
}
