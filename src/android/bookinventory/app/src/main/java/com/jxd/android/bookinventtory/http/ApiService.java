package com.jxd.android.bookinventtory.http;

import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.DataBase;
import com.jxd.android.bookinventtory.bean.Page;
import com.jxd.android.bookinventtory.bean.ShelfBean;
import com.jxd.android.bookinventtory.bean.UpdateInventory;
import com.jxd.android.bookinventtory.bean.UserBean;

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
    @POST("login/login")
    Observable<DataBase<UserBean>> login(@Field("username") String userName , @Field("password") String password);

    @GET("login/logout")
    Observable<DataBase<Object>> logout();

    //@FormUrlEncoded
    @GET("book/getbooklist")
    Observable<DataBase<Page<BookBean>>> getBookList(@Query("title") String title , @Query("barcode") String barcode , @Query("shelfno") String shelfno , @Query("pageidx") int pageidx );

    @GET("book/getshelflist")
    Observable<DataBase<ShelfBean>> getShelfList(@Query("shelfno") String shelfno);

    /***
     * 上传架位调整数据
     * @return
     */
    @FormUrlEncoded
    @POST("shelf/uploadshelfadptlist")
    Observable<DataBase<Object>> uploadShelfAdaptList(List<UpdateInventory> data );

}
