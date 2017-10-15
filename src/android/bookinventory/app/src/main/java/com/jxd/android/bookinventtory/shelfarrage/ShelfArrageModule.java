package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.http.ApiService;
import com.jxd.android.bookinventtory.shelfsearch.IShelfSearchView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jinxiangdong on 2017/10/14.
 */
@Module
public class ShelfArrageModule {
    ShelfArrageFragment shelfArrageFragment;
    public ShelfArrageModule(ShelfArrageFragment shelfArrageFragment){
        this.shelfArrageFragment=shelfArrageFragment;
    }

    @Provides
    public IShelfArrageView provideShelfArrageView(){
        return shelfArrageFragment;
    }

    @Provides
    public IShelfArrageModel provideShelfArrageModel(ApiService apiService){
        return new ShelfArrageModel(apiService);
    }


    @Provides
    public IShelfArragePresenter provideShelfArragePresenter(IShelfArrageView iShelfArrageView,IShelfArrageModel iShelfArrageModel){
        return new ShelfArragePresenter(iShelfArrageView,iShelfArrageModel);
    }
}
