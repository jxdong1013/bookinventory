package com.jxd.android.bookinventtory.search;

import android.util.Log;

import com.jxd.android.bookinventtory.bean.SearchKeyBean;
import com.jxd.android.bookinventtory.mvp.PreActivity;

import java.util.List;

import io.realm.Realm;

/**
 * Created by jinxiangdong on 2017/9/28.
 */
@PreActivity
public class SearchPresenter<V extends ISearchView>
        implements ISearchPresenter ,Realm.Transaction.OnSuccess , Realm.Transaction.OnError {
    public static String TAG = SearchPresenter.class.getName();
    private ISearchView searchView;
    private SearchModel searchModel;

    public SearchPresenter(ISearchView searchView , SearchModel searchModel ){
        this.searchView = searchView;
        this.searchModel = searchModel;
    }

    @Override
    public void getSearchKeysListAsync( String key ) {
        searchModel.getSearchKeysAsync( key , searchView);
    }

    @Override
    public void addSearchKey(SearchKeyBean key) {
        searchModel.addSearchKey(key , this,this);
    }

    @Override
    public void deleteSearkey() {
        searchView.showProgress();
        searchModel.deleteSearkey(this , this );
    }

    @Override
    public void onError(Throwable error) {
        Log.e( TAG , error.getMessage());
        searchView.hideProgress();
    }

    @Override
    public void onSuccess() {
        Log.i(TAG, "success");
        searchView.showProgress();
        searchView.onClear();
    }

    @Override
    public void onDestory() {

    }
}
