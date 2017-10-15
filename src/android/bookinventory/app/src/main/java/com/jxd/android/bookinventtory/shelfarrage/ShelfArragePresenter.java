package com.jxd.android.bookinventtory.shelfarrage;

/**
 * Created by jinxiangdong on 2017/10/14.
 */

public class ShelfArragePresenter implements IShelfArragePresenter {
    IShelfArrageView iShelfArrageView;
    IShelfArrageModel iShelfArrageModel;
    public ShelfArragePresenter(IShelfArrageView iShelfArrageView,IShelfArrageModel iShelfArrageModel){
        this.iShelfArrageView= iShelfArrageView;
        this.iShelfArrageModel = iShelfArrageModel;
    }


    @Override
    public void onDestory() {

    }
}
