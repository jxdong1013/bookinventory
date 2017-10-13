package com.jxd.android.bookinventtory.shelfadapt;

import com.jxd.android.bookinventtory.http.ApiService;
import com.jxd.android.bookinventtory.utils.RealmUtil;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

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
    public Realm provideRealm(){
        return new RealmUtil().getRealm();
    }

    @Provides
    public IShelfAdaptModel provideShelfAdaptModel(ApiService apiService , Realm realm){
        return new ShelfAdaptModel(apiService , shelfAdaptFragment , realm);
    }

    @Provides
    public IShelfAdaptPresenter provideShelfAdaptPresenter(IShelfAdaptView iShelfAdaptView , IShelfAdaptModel iShelfAdaptModel){
        return new ShelfAdaptPresenter( iShelfAdaptView , iShelfAdaptModel);
    }



}
