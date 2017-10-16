package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.DataBase;
import com.jxd.android.bookinventtory.bean.ShelfBean;
import com.jxd.android.bookinventtory.http.ApiService;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/10/16.
 */
public class ShelfArrageUIModel implements IShelfArrageUIModel {
    ApiService apiService;
    LifecycleProvider lifecycleProvider;

    public ShelfArrageUIModel(ApiService apiService , LifecycleProvider lifecycleProvider ){
        this.apiService=apiService;
        this.lifecycleProvider=lifecycleProvider;
    }

    @Override
    public void getShelfInfoByShelfCode(String shelfCode, Observer observer) {

        Observable<DataBase<ShelfBean>> observable = apiService.getShelfList( shelfCode);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose( lifecycleProvider.<DataBase<List<BookBean>>>bindToLifecycle() )
                .subscribe(observer);
    }

    @Override
    public void onDestory() {
    }
}
