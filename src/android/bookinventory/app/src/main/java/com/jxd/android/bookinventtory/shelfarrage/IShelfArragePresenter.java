package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.ShelfScanBean;
import com.jxd.android.bookinventtory.mvp.IPresenter;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by jinxiangdong on 2017/10/14.
 */

public interface IShelfArragePresenter extends IPresenter {
    void getDataFromLocal();

    void deleteLocalData(List<String> data );
}
