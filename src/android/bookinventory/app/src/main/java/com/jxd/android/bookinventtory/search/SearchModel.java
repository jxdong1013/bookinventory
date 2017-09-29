package com.jxd.android.bookinventtory.search;

import com.jxd.android.bookinventtory.bean.SearchKeyBean;
import com.jxd.android.bookinventtory.mvp.IModel;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by jinxiangdong on 2017/9/28.
 */

public class SearchModel implements IModel {

    Realm realm;

    public SearchModel(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void onDestory() {

    }

    public void getSearchKeysAsync(String key , RealmChangeListener<RealmResults<SearchKeyBean>> resultsRealmChangeListener ){
         RealmResults<SearchKeyBean> realmResults = realm.where( SearchKeyBean.class  ).contains(  "key" , key ).findAllAsync();
        realmResults.addChangeListener( resultsRealmChangeListener);
    }

    public void addSearchKey(final SearchKeyBean key , Realm.Transaction.OnSuccess onSuccess , Realm.Transaction.OnError onError){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate( key );
            }
        } , onSuccess , onError );
    }

    public void deleteSearkey(Realm.Transaction.OnSuccess onSuccess , Realm.Transaction.OnError onError){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(SearchKeyBean.class);
            }
        }, onSuccess,onError );

    }

}
