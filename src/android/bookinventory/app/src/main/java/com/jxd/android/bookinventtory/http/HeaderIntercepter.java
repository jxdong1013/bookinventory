package com.jxd.android.bookinventtory.http;

import com.jxd.android.bookinventtory.base.BaseApplication;
import com.jxd.android.bookinventtory.bean.UserBean;
import com.jxd.android.bookinventtory.config.Constants;
import com.jxd.android.bookinventtory.utils.GsonUtil;
import com.jxd.android.bookinventtory.utils.PreferenceHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.jxd.android.bookinventtory.utils.GsonUtil.getGson;
import static com.jxd.android.bookinventtory.utils.PreferenceHelper.readString;

/**
 * Created by Administrator on 2017/10/10.
 */

public class HeaderIntercepter implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request newRequest = request.newBuilder()
                .addHeader("token" , getToken() ).build();

        return chain.proceed(newRequest);
    }

    protected String getToken(){
        String userString  = PreferenceHelper.readString(BaseApplication.single , Constants.PREF_FILENAME , Constants.PREF_USER, "");
        if(userString==null|| userString.isEmpty()){
            return "";
        }
        UserBean user = GsonUtil.getGson().fromJson( userString ,UserBean.class );
        if(user==null) return "";
        return user.getToken();
    }

}
