package com.jxd.android.bookinventtory.shelfadapt;

import com.jxd.android.bookinventtory.http.ApiService;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;

/**
 * Created by Administrator on 2017/10/12.
 */

public class ShelfAdaptModel implements IShelfAdaptModel{
    ApiService apiService;
    LifecycleProvider lifecycleProvider;

    public ShelfAdaptModel(ApiService apiService , LifecycleProvider lifecycleProvider){
        this.apiService=apiService;
        this.lifecycleProvider = lifecycleProvider;
    }

    @Override
    public void onDestory() {

    }
}
