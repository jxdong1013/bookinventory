package com.jxd.android.bookinventtory.http;

import android.content.Context;
import android.util.Log;

import com.jxd.android.bookinventtory.config.Constants;
import com.jxd.android.bookinventtory.utils.PreferenceHelper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.prefs.Preferences;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 此拦截器是用于在http请求头添加cookie
 * Created by Administrator on 2017/10/11.
 */

public class AddCookieIntercepter implements Interceptor {
    private Context context;

    public AddCookieIntercepter (Context context){
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences =(HashSet<String>) PreferenceHelper.readStringSet( context , Constants.PREF_FILENAME , Constants.PREF_COOKIE , new HashSet<String>());
        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
            Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
        }

        return chain.proceed(builder.build());
    }
}
