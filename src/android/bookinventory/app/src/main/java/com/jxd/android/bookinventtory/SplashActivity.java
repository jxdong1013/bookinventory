package com.jxd.android.bookinventtory;

import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;
import com.jxd.android.bookinventtory.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarUtil.setTransparent(this);


    }
}
