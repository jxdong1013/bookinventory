package com.jxd.android.bookinventtory.search;

import android.app.Activity;

import com.jxd.android.bookinventtory.di.AppComponent;
import com.jxd.android.bookinventtory.mvp.PreActivity;

import dagger.Component;
import dagger.Provides;

/**
 * Created by jinxiangdong on 2017/9/28.
 */
@PreActivity
@Component(modules = {SearchModule.class},dependencies = {AppComponent.class})
public interface SearchComponent {


    void inject(SearchActivity activity);


    ISearchPresenter getSearchPresenter();

}
