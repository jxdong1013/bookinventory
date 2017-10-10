package com.jxd.android.bookinventtory.booksearch;

import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.BookCondition;
import com.jxd.android.bookinventtory.bean.DataBase;
import com.jxd.android.bookinventtory.bean.Page;
import com.jxd.android.bookinventtory.bean.ResultCodeEnum;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by jinxiangdong on 2017/9/30.
 */

public class BookSearchPresenter implements IBookSearchPresenter ,Observer<DataBase<Page<BookBean>>>{

    IBookSearchView iBookSearchView;
    IBookSearchModel iBookSearchModel;

    public BookSearchPresenter(IBookSearchView iBookSearchView , IBookSearchModel iBookSearchModel){
        this.iBookSearchView = iBookSearchView;
        this.iBookSearchModel=  iBookSearchModel;
    }


    @Override
    public void getBookList(BookCondition key ) {

        iBookSearchView.showProgress();

        this.iBookSearchModel.getBookList( key , this );
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull DataBase<Page<BookBean>> data) {
        if(data.getCode() == ResultCodeEnum.SUCCESS.getCode()) {
            iBookSearchView.callback(data.getData());
        }else if(  data.getCode() == ResultCodeEnum.LOGIN.getCode()){
            iBookSearchView.login();
        }else{
            iBookSearchView.toast(data.getCode()+ data.getMessage());
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        iBookSearchView.error(e.getMessage());
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
