package com.jxd.android.bookinventtory.di;

import com.jxd.android.bookinventtory.base.BaseApplciation;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/9/8.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    /**
     * 提供全局唯一Application
     * @return
     */
    @Singleton
    BaseApplciation getApplication();

    @Singleton
    Retrofit getRetrofit();
}
