package com.jxd.android.bookinventtory.shelfsearch;

import com.jxd.android.bookinventtory.bean.ShelfCondition;

/**
 * Created by Administrator on 2017/10/9.
 */

public class ShelfSearchPresenter implements IShelfSearchPresenter {

    private IShelfSearchModel iShelfSearchModel;
    private IShelfSearchView iShelfSearchView;

    public ShelfSearchPresenter(IShelfSearchView iShelfSearchView , IShelfSearchModel iShelfSearchModel){
        this.iShelfSearchModel = iShelfSearchModel;
        this.iShelfSearchView = iShelfSearchView;
    }

    @Override
    public void getShelfList(ShelfCondition condition) {

    }

    @Override
    public void onDestory() {

    }
}
