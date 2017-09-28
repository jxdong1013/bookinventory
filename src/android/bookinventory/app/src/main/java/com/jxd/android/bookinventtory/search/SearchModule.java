package com.jxd.android.bookinventtory.search;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jinxiangdong on 2017/9/28.
 */

@Module
public class SearchModule {



    @Provides
    public ISearchPresenter provideSearchPresenter(){
        return new SearchPresenter();
    }
}
