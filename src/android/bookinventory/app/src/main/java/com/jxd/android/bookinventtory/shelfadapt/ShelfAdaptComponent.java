package com.jxd.android.bookinventtory.shelfadapt;

import com.jxd.android.bookinventtory.di.AppComponent;
import com.jxd.android.bookinventtory.mvp.PerFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/10/12.
 */
@PerFragment
@Component(modules={ShelfAdaptModule.class}, dependencies = {AppComponent.class})
public interface ShelfAdaptComponent {

    void inject(ShelfAdaptFragment shelfAdaptFragment);


    IShelfAdaptPresenter getShelfAdaptPresenter();

}
