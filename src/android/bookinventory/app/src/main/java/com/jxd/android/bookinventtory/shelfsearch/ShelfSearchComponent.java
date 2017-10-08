package com.jxd.android.bookinventtory.shelfsearch;

import com.jxd.android.bookinventtory.di.AppComponent;
import com.jxd.android.bookinventtory.mvp.PerFragment;

import dagger.Component;

/**
 * Created by jinxiangdong on 2017/9/30.
 */
@PerFragment
@Component(modules = {ShelfSearchModule.class},dependencies = {AppComponent.class})
public interface ShelfSearchComponent {

    void inject(ShelfSearchFragment shelfSearchFragment);
}
