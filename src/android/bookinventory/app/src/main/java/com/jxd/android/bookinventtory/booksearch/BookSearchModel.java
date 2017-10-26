package com.jxd.android.bookinventtory.booksearch;

import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.BookCondition;
import com.jxd.android.bookinventtory.bean.DataBase;
import com.jxd.android.bookinventtory.bean.Page;
import com.jxd.android.bookinventtory.http.ApiService;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.Observer;

/**
 * Created by jinxiangdong on 2017/9/30.
 */

public class BookSearchModel implements IBookSearchModel {

    ApiService apiService;

    LifecycleProvider lifecycleProvider;

    public BookSearchModel(  ApiService apiService ,  LifecycleProvider lifecycleProvider){
        this.apiService= apiService;
        this.lifecycleProvider = lifecycleProvider;
    }

    @Override
    public void getBookList(BookCondition key , Observer subscriber ) {
        Observable<DataBase<Page<BookBean>>> observable = apiService.getBookList( key.getTitle(),key.getBarcode() , "" , key.getPageIdx() );
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose( lifecycleProvider.<DataBase<List<BookBean>>>bindToLifecycle() )
                .subscribe(subscriber);
    }

    @Override
    public void onDestory() {
        apiService=null;
        lifecycleProvider=null;
    }
}
