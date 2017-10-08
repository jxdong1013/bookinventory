package com.jxd.android.bookinventtory.http;

import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.BookBeanResult;
import com.jxd.android.bookinventtory.bean.DataBase;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by jinxiangdong on 2017/10/8.
 */

public interface ApiService {

    @FormUrlEncoded
    @GET("book/search")
    Observable<DataBase<List<BookBean>>> getBookList(@Query("key") String key , @Query("code") String code);
}
