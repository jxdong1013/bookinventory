package com.jxd.android.bookinventtory.search;

import com.jxd.android.bookinventtory.utils.RealmUtil;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by jinxiangdong on 2017/9/28.
 */

@Module
public class SearchModule {

    ISearchView searchView;

    public SearchModule(ISearchView searchView){
        this.searchView = searchView;
    }

    @Provides
    public ISearchView provideSearchView(){
        return  searchView;
    }

    @Provides
    public ISearchPresenter provideSearchPresenter( ISearchView searchView , SearchModel searchModel){
        return new SearchPresenter( searchView ,searchModel);
    }

    @Provides
    public SearchModel provideSearchModel( Realm realm){
        return new SearchModel(realm);
    }

    @Provides
    public Realm provideRealm(){
        return new RealmUtil().getRealm();
    }

}
