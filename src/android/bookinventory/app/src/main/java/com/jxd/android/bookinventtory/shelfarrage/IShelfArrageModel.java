package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.ShelfScanBean;
import com.jxd.android.bookinventtory.bean.UpdateInventory;
import com.jxd.android.bookinventtory.mvp.IModel;

import java.util.List;

import io.reactivex.Observer;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by jinxiangdong on 2017/10/14.
 */
public interface IShelfArrageModel extends IModel{
    /**
     * 从本地realm数据库获取盘点数据
     * @param realmResultsRealmChangeListener
     */
    void getDataFromLocal(RealmChangeListener<RealmResults<ShelfScanBean>> realmResultsRealmChangeListener);

    /**
     * 删除本地盘点数据
     * @param data
     * @param onSuccess
     * @param onError
     */
    void deleteLocalData(List<String> data , Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError);

    /**
     * 上传盘点数据
     * @param uploadData
     * @param userId
     * @param userName
     */
    void uploadData(List<UpdateInventory> uploadData , int pageIndex , int userId , String userName , Observer observer);
}
