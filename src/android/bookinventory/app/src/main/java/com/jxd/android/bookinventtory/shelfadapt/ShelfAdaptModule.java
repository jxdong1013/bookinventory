package com.jxd.android.bookinventtory.shelfadapt;

import com.jxd.android.bookinventtory.http.ApiService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/10/12.
 */
@Module
public class ShelfAdaptModule {
    ShelfAdaptFragment  shelfAdaptFragment;
    public ShelfAdaptModule(ShelfAdaptFragment shelfAdaptFragment){
        this.shelfAdaptFragment=shelfAdaptFragment;
    }

    @Provides
    public IShelfAdaptView provideShelfAdaptView(){
        return shelfAdaptFragment;
    }

    @Provides
    public IShelfAdaptModel provideShelfAdaptModel(ApiService apiService){
        return new ShelfAdaptModel(apiService , shelfAdaptFragment);
    }

    @Provides
    public IShelfAdaptPresenter provideShelfAdaptPresenter(IShelfAdaptView iShelfAdaptView , IShelfAdaptModel iShelfAdaptModel){
        return new ShelfAdaptPresenter( iShelfAdaptView , iShelfAdaptModel);
    }

}
