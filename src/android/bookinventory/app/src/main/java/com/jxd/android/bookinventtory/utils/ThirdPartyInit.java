package com.jxd.android.bookinventtory.utils;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

/**
 * Created by Administrator on 2017/9/29.
 */

public class ThirdPartyInit {
    public static void init(Application application){
        CrashHandler.getInstance().init(application);

        RealmUtil.init( application);

        Stetho.initialize( Stetho.newInitializerBuilder(application)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(application))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(application).build()).build());

    }
}
