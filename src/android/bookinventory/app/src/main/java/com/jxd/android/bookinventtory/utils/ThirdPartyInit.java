package com.jxd.android.bookinventtory.utils;

import android.app.Application;

/**
 * Created by Administrator on 2017/9/29.
 */

public class ThirdPartyInit {
    public static void init(Application application){
        RealmUtil.init( application);

    }
}
