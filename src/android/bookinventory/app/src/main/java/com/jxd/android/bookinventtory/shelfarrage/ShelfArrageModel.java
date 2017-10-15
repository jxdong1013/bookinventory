package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.http.ApiService;

/**
 * Created by jinxiangdong on 2017/10/14.
 */

public class ShelfArrageModel implements IShelfArrageModel {
    ApiService apiService;

    public ShelfArrageModel(ApiService apiService){
        this.apiService=apiService;
    }

    @Override
    public void onDestory() {

    }
}
