package com.jxd.android.bookinventtory.search;

import com.jxd.android.bookinventtory.di.AppComponent;
import com.jxd.android.bookinventtory.mvp.PerActivity;

import dagger.Component;
import io.realm.Realm;

/**
 * Created by jinxiangdong on 2017/9/28.
 */
@PerActivity
@Component(modules = {SearchModule.class},dependencies = {AppComponent.class})
public interface SearchComponent {


    void inject(SearchActivity activity);


    ISearchPresenter getSearchPresenter();

    Realm getRealm();

}
