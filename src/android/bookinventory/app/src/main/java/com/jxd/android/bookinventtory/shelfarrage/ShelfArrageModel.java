package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.BookShelfAdptBean;
import com.jxd.android.bookinventtory.bean.ShelfLevelItem;
import com.jxd.android.bookinventtory.bean.ShelfScanBean;
import com.jxd.android.bookinventtory.http.ApiService;

import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
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
    public void deleteLocalData(final List<String> data, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                String[] temp = new String[data.size()];
                data.toArray(temp);
                realm.where(ShelfScanBean.class).in("id" ,  temp ).findAll().deleteAllFromRealm();
            }
        } , onSuccess, onError);
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
