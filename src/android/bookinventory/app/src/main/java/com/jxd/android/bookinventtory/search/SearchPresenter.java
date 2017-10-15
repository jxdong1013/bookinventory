package com.jxd.android.bookinventtory.search;

import android.util.Log;

import com.jxd.android.bookinventtory.adapter.SearchKeySection;
import com.jxd.android.bookinventtory.bean.OperateTypeEnum;
import com.jxd.android.bookinventtory.bean.SearchKeyBean;
import com.jxd.android.bookinventtory.config.Constants;
import com.jxd.android.bookinventtory.mvp.PerActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by jinxiangdong on 2017/9/28.
 */
@PerActivity
public class SearchPresenter<V extends ISearchView>
        implements ISearchPresenter
        ,Realm.Transaction.OnSuccess
        ,Realm.Transaction.OnError
        ,RealmChangeListener<RealmResults<SearchKeyBean>> {
    public static String TAG = SearchPresenter.class.getName();
    private ISearchView searchView;
    private SearchModel searchModel;
    private OperateTypeEnum operateTypeEnum = OperateTypeEnum.None;
    private SearchKeyBean searchKey;

    public SearchPresenter(ISearchView searchView , SearchModel searchModel ){
        this.searchView = searchView;
        this.searchModel = searchModel;
    }

    @Override
    public void getSearchKeysListAsync( String key ) {
        searchView.showProgress();
        searchModel.getSearchKeysAsync( key , this);
    }

    @Override
    public void addSearchKey(SearchKeyBean key) {
        searchView.showProgress();
        operateTypeEnum = OperateTypeEnum.Insert;
        searchKey = key;
        searchModel.addSearchKey(key , this,this);
    }

    @Override
    public void deleteSearchKey() {
        searchView.showProgress();
        operateTypeEnum = OperateTypeEnum.Delete;
        searchModel.deleteSearchKey(this , this );
    }

    @Override
    public void onError(Throwable error) {
        Log.e( TAG , error.getMessage());
        searchView.hideProgress();
        searchView.toast(error.getMessage());
    }

    @Override
    public void onSuccess() {
        Log.i(TAG, "success");
        searchView.hideProgress();
        if(operateTypeEnum== OperateTypeEnum.Delete) {
            searchView.onClear();
        }else if(operateTypeEnum == OperateTypeEnum.Insert){
            searchView.onAdded(searchKey);
        }
    }

    @Override
    public void onChange(RealmResults<SearchKeyBean> searchKeyBeen) {
        searchKeyBeen=searchKeyBeen.sort("hot", Sort.DESCENDING);
        int maxCount = searchKeyBeen.size()> Constants.MAX_SEARCHKEY_COUNT ? Constants.MAX_SEARCHKEY_COUNT :searchKeyBeen.size();
        List<SearchKeyBean> list = searchKeyBeen.subList(0 , maxCount);

        List<SearchKeySection> data = transfer( list );

        if(searchView!=null) {
            searchView.onRefresh(data);
        }
    }


    protected List<SearchKeySection> transfer(List<SearchKeyBean> list){
        List<SearchKeySection> data= new ArrayList<>();

        data.add( new SearchKeySection(true,"最近搜索") );
        for(SearchKeyBean item :  list){
            SearchKeySection section = new SearchKeySection(item);
            data.add(section);
        }

        return data;
    }


    @Override
    public void onDestory() {
        if(searchModel!=null){
            searchModel.onDestory();
            searchModel=null;
        }
        if(searchView!=null){
            searchView =null;
        }
    }
}
