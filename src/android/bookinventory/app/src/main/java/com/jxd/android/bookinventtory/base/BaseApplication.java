package com.jxd.android.bookinventtory.base;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.jxd.android.bookinventtory.di.AppComponent;
import com.jxd.android.bookinventtory.di.AppModule;
import com.jxd.android.bookinventtory.di.DaggerAppComponent;
import com.jxd.android.bookinventtory.utils.ThirdPartyInit;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

/**
 * Created by Administrator on 2017/9/8.
 */

public class BaseApplication extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        //appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        ThirdPartyInit.init(this);


        Stetho.initialize( Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build()).build());

    }

    public AppComponent getAppComponent(){
        return  appComponent;
    }

}