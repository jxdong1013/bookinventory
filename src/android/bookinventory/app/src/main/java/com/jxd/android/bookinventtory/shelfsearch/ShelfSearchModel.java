package com.jxd.android.bookinventtory.shelfsearch;

import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.DataBase;
import com.jxd.android.bookinventtory.bean.Page;
import com.jxd.android.bookinventtory.bean.ShelfBean;
import com.jxd.android.bookinventtory.bean.ShelfCondition;
import com.jxd.android.bookinventtory.http.ApiService;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.R.attr.key;

/**
 * Created by jinxiangdong on 2017/9/30.
 */

public class ShelfSearchModel implements IShelfSearchModel {
     ApiService apiService;
    LifecycleProvider lifecycleProvider;

    public ShelfSearchModel(ApiService apiService , LifecycleProvider lifecycleProvider) {
        this.apiService=apiService;
        this.lifecycleProvider=lifecycleProvider;
    }

    @Override
    public void getShelfList(ShelfCondition condition, Observer subscriber) {
        Observable<DataBase<ShelfBean>> observable = apiService.getShelfList( condition.getShelfcode() );
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose( lifecycleProvider.<DataBase<List<BookBean>>>bindToLifecycle() )
                .subscribe(subscriber);
    }

    @Override
    public void onDestory() {

    }
}
