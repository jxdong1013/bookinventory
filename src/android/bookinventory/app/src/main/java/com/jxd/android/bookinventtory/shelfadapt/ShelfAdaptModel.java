package com.jxd.android.bookinventtory.shelfadapt;

import com.jxd.android.bookinventtory.bean.BookShelfAdptBean;
import com.jxd.android.bookinventtory.http.ApiService;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import static android.R.attr.key;
import static com.jxd.android.bookinventtory.R.mipmap.book;

/**
 * Created by Administrator on 2017/10/12.
 */

public class ShelfAdaptModel implements IShelfAdaptModel{
    ApiService apiService;
    LifecycleProvider lifecycleProvider;
    Realm realm;

    public ShelfAdaptModel(ApiService apiService , LifecycleProvider lifecycleProvider , Realm realm){
        this.apiService=apiService;
        this.lifecycleProvider = lifecycleProvider;
        this.realm = realm;
    }

    @Override
    public void saveBookShelfAdaptResult(final BookShelfAdptBean bookShelfAdptBean , Realm.Transaction.OnSuccess onSuccess , Realm.Transaction.OnError onError) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate( bookShelfAdptBean );
            }
        } , onSuccess , onError);
    }

    @Override
    public void getBookShelfScanList(RealmChangeListener<RealmResults<BookShelfAdptBean>> resultsRealmChangeListener) {
        RealmResults<BookShelfAdptBean>  realmResults = realm.where(BookShelfAdptBean.class).findAllAsync();
        realmResults.addChangeListener(resultsRealmChangeListener);
    }

    @Override
    public void deleteAll(Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(BookShelfAdptBean.class);
            }
        } , onSuccess, onError);
    }

    @Override
    public void deleteOne(final BookShelfAdptBean bookShelfAdptBean , Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {

        final String id = bookShelfAdptBean.getId();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<BookShelfAdptBean> results = realm.where(BookShelfAdptBean.class).equalTo("id" , id ).findAll();
                if( results.size()>0) {
                    results.deleteAllFromRealm();
                }
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
