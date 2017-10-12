package com.jxd.android.bookinventtory.shelfadapt;

/**
 * Created by Administrator on 2017/10/12.
 */

public class ShelfAdaptPresenter implements IShelfAdaptPresenter {
    IShelfAdaptView iShelfAdaptView;
    IShelfAdaptModel iShelfAdaptModel;

    public ShelfAdaptPresenter(IShelfAdaptView iShelfAdaptView, IShelfAdaptModel iShelfAdaptModel){
        this.iShelfAdaptModel=iShelfAdaptModel;
        this.iShelfAdaptView=iShelfAdaptView;
    }

    @Override
    public void onDestory() {

    }
}
