package com.jxd.android.bookinventtory.bean;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * 架位信息
 * Created by jinxiangdong on 2017/9/30.
 */
public class ShelfBean implements Cloneable{

    private String shelfCode;
    private String shelfName;
    private List<BookBean> books;


    public String getShelfCode() {
        return shelfCode;
    }

    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public List<BookBean> getBooks() {
        return books;
    }

    public void setBooks(List<BookBean> books) {
        this.books = books;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        Object clone = null;
        try {
            //Object中的clone()识别出你要复制的是哪一个对象。
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
        return clone;
    }
}
