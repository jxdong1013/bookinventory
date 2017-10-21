package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.http.ApiService;
import com.jxd.android.bookinventtory.shelfsearch.IShelfSearchView;
import com.jxd.android.bookinventtory.utils.RealmUtil;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by jinxiangdong on 2017/10/14.
 */
@Module
public class ShelfArrageModule {
    ShelfArrageFragment shelfArrageFragment;
    ShelfArrageUIActivity shelfArrageUIActivity;

    public ShelfArrageModule(ShelfArrageFragment shelfArrageFragment){
        this.shelfArrageFragment=shelfArrageFragment;
    }

    public ShelfArrageModule(ShelfArrageUIActivity shelfArrageUIActivity){
        this.shelfArrageUIActivity=shelfArrageUIActivity;
    }

    @Provides
    public IShelfArrageView provideShelfArrageView(){
        return shelfArrageFragment;
    }

    @Provides
    public IShelfArrageModel provideShelfArrageModel(ApiService apiService , Realm realm ){
        return new ShelfArrageModel(apiService , realm );
    }


    @Provides
    public IShelfArragePresenter provideShelfArragePresenter(IShelfArrageView iShelfArrageView,IShelfArrageModel iShelfArrageModel){
        return new ShelfArragePresenter(iShelfArrageView,iShelfArrageModel);
    }

    @Provides
    public IShelfArrageUIView provideShelfArrageUIView(){
        return shelfArrageUIActivity;
    }

    @Provides
    public IShelfArrageUIModel provideShelfArrageUIModel(ApiService apiService , Realm realm){
        return new ShelfArrageUIModel(apiService , realm , shelfArrageUIActivity);
    }

    @Provides
    public IShelfArrageUIPresenter provideShelfArrageUIPresenter(IShelfArrageUIView iShelfArrageUIView , IShelfArrageUIModel iShelfArrageUIModel){
        return new ShelfArrageUIPresenter(iShelfArrageUIView , iShelfArrageUIModel);
    }

    @Provides
    public Realm provideRealm(){
        return new RealmUtil().getRealm();
    }

}
