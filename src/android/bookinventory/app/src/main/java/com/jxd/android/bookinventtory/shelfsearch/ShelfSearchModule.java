package com.jxd.android.bookinventtory.shelfsearch;

import com.jxd.android.bookinventtory.http.ApiService;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jinxiangdong on 2017/9/30.
 */
@Module
public class ShelfSearchModule {
    private ShelfSearchFragment shelfSearchFragment;

    public ShelfSearchModule(ShelfSearchFragment shelfSearchFragment){
        this.shelfSearchFragment = shelfSearchFragment;
    }

    @Provides
    public  IShelfSearchView provideShelfSearchView(){
        return shelfSearchFragment;
    }
    @Provides
    public IShelfSearchModel provideShelfSearchModel(ApiService apiService ){
        return  new ShelfSearchModel( apiService , shelfSearchFragment);
    }

    @Provides
    public IShelfSearchPresenter provideShelfSearchPresenter(IShelfSearchView iShelfSearchView , IShelfSearchModel iShelfSearchModel){
        return new ShelfSearchPresenter(iShelfSearchView, iShelfSearchModel);
    }
}
