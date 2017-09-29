package com.jxd.android.bookinventtory.utils;

import android.content.Context;

import com.jxd.android.bookinventtory.config.Constants;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Administrator on 2017/9/29.
 */

public class RealmUtil {
    private Realm realm;
    public  static  void init(Context context){
        Realm.init( context );
    }
    public Realm getRealm(){
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("jxd.realm")
                .schemaVersion(Constants.REALM_VERSION)
                .migration(new RealmVersionMigration())
                .build();
        return getRealm(realmConfiguration);
    }

    public  Realm getRealm(RealmConfiguration realmConfiguration){
        if(realm==null){
            realm = Realm.getInstance( realmConfiguration);
        }
        return realm;
    }

}
