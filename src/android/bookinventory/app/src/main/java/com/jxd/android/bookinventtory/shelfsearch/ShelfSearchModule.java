package com.jxd.android.bookinventtory.shelfsearch;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jinxiangdong on 2017/9/30.
 */
@Module
public class ShelfSearchModule {

    @Provides
    public IShelfSearchPresenter provideShelfSearchPresenter(){
        return new ShelfSearchPresenter();
    }
}
