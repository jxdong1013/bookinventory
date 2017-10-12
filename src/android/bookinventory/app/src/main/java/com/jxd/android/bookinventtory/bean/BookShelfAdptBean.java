package com.jxd.android.bookinventtory.bean;

import java.util.Date;

import io.realm.RealmObject;

/**
 * 图书架位调整bean
 * Created by Administrator on 2017/10/12.
 */
public class BookShelfAdptBean extends RealmObject{
    /***
     * 图书编号
     */
    private String bookCode;
    /**
     * 图书名称
     */
    private String bookName;
    /**
     * 架编号
     */
    private String shelfCode;
    /**
     * 架名称
     */
    private String shelfName;
    /**
     * 调整时间
     */
    private Date adaptTime;
    /**
     * 操作人
     */
    private String userName;
    /**
     *操作人
     */
    private String userId;
}
