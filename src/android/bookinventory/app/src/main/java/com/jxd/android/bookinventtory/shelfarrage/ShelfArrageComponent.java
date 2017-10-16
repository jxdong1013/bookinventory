package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.di.AppComponent;
import com.jxd.android.bookinventtory.mvp.PerFragment;
import com.jxd.android.bookinventtory.shelfsearch.ShelfSearchModule;

import dagger.Component;

/**
 * Created by jinxiangdong on 2017/10/14.
 */
@PerFragment
@Component(modules = {ShelfArrageModule.class},dependencies = {AppComponent.class})
public interface ShelfArrageComponent {

    void inject(ShelfArrageFragment shelfArrageFragment);

    void inject(ShelfArrageUIActivity shelfArrageUIActivity);

    IShelfArragePresenter getShelfArragePresenter();

    IShelfArrageUIPresenter getShelfArrageUIPresenter();
}
