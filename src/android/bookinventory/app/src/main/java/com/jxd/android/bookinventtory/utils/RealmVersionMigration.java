package com.jxd.android.bookinventtory.utils;

import android.provider.SyncStateContract;
import android.util.Log;

import com.jxd.android.bookinventtory.config.Constants;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/**
 * Created by Administrator on 2017/9/29.
 */

public class RealmVersionMigration implements RealmMigration {
    public static String TAG = RealmVersionMigration.class.getSimpleName();

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        Log.i(TAG , "realm old version = " + oldVersion + " new version =" + newVersion);
        if( oldVersion == Constants.REALM_VERSION) {
            //// TODO: 2017/9/29
        }
    }

    @Override
    public int hashCode() {
        return RealmVersionMigration.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj!=null && obj instanceof RealmVersionMigration;
    }

}
