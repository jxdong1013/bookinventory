package com.jxd.android.bookinventtory.mvp;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by jinxiangdong on 2017/9/30.
 */
@Scope
@Retention(RUNTIME)
public @interface PerFragment {
}
