package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.ShelfScanBean;
import com.jxd.android.bookinventtory.mvp.IView;

import java.util.List;

/**
 * Created by jinxiangdong on 2017/10/14.
 */

public interface IShelfArrageView extends IView {

    void getCallback(List<ShelfScanBean> data);

    void deleteCallback();
    /**
     * 设置ui上传进度
     * @param percent
     * @param message
     */
    void setUploadProgress(int percent , String message);

    /**
     * 隐藏上传进度条
     */
    void hideUploadProgress();
    /**
     * 上传成功回调
     * @param invertoryResult
     */
    void uploadSuccessCallback(InvertoryResult invertoryResult);

    /**
     * 上传错误回调
     * @param invertoryResult
     */
    void uploadFailCallback(InvertoryResult invertoryResult);

    /**
     * 上传异常回调
     */
    void uploadErrorCallback(String mesage);

    /**
     * 上传完成回调
     */
    void uploadFinishCallback();

}
