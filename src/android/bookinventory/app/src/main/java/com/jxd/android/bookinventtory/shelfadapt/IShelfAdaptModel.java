package com.jxd.android.bookinventtory.shelfadapt;

import com.jxd.android.bookinventtory.bean.BookShelfAdptBean;
import com.jxd.android.bookinventtory.bean.SearchKeyBean;
import com.jxd.android.bookinventtory.mvp.IModel;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Administrator on 2017/10/12.
 */

public interface IShelfAdaptModel extends IModel {
    void saveBookShelfAdaptResult(BookShelfAdptBean bookShelfAdptBean , Realm.Transaction.OnSuccess onSuccess , Realm.Transaction.OnError onError);

    void getBookShelfScanList(RealmChangeListener<RealmResults<BookShelfAdptBean>> resultsRealmChangeListener );

    void deleteAll( Realm.Transaction.OnSuccess onSuccess , Realm.Transaction.OnError onError);
}
