package com.jxd.android.bookinventtory.booksearch;

import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.DataBase;
import com.jxd.android.bookinventtory.bean.ResultCodeEnum;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by jinxiangdong on 2017/9/30.
 */

public class BookSearchPresenter implements IBookSearchPresenter ,Observer<DataBase<List<BookBean>>>{

    IBookSearchView iBookSearchView;
    IBookSearchModel iBookSearchModel;

    public BookSearchPresenter(IBookSearchView iBookSearchView , IBookSearchModel iBookSearchModel){
        this.iBookSearchView = iBookSearchView;
        this.iBookSearchModel=  iBookSearchModel;
    }


    @Override
    public void getBookList(BookBean key ) {

        iBookSearchView.showProgress();

        this.iBookSearchModel.getBookList( key , this );
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull DataBase<List<BookBean>> data) {
        if(data.getCode() == ResultCodeEnum.SUCCESS.getCode()) {
            iBookSearchView.callback(data.getData());
        }else{
            iBookSearchView.toast(data.getCode()+ data.getMessage());
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        iBookSearchView.toast(e.getMessage());
    }

    @Override
    public void onComplete() {
        iBookSearchView.hideProgress();
    }

    @Override
    public void onDestory() {
        if( iBookSearchView!=null ){
            iBookSearchView=null;
        }
        if(iBookSearchModel!=null){
            iBookSearchModel.onDestory();
            iBookSearchModel=null;
        }
    }
}
