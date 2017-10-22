package com.jxd.android.bookinventtory.main;

import com.jxd.android.bookinventtory.di.AppComponent;
import com.jxd.android.bookinventtory.mvp.PerActivity;
import com.jxd.android.bookinventtory.utils.AnDeDeviceReader;

import dagger.Component;

/**
 * Created by jinxiangdong on 2017/10/22.
 */
@PerActivity
@Component(modules = {MainModule.class}, dependencies = {AppComponent.class})
public interface MainComponent {

    //void inject(MainActivity mainActivity);

}
