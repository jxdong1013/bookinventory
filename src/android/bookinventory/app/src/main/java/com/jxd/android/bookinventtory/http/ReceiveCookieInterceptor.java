package com.jxd.android.bookinventtory.http;

import android.content.Context;

import com.jxd.android.bookinventtory.config.Constants;
import com.jxd.android.bookinventtory.utils.PreferenceHelper;

import java.io.IOException;
import java.util.HashSet;
import java.util.prefs.Preferences;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 此拦截器是用于接受 http返回的cookie，并保存到本地
 * Created by Administrator on 2017/10/11.
 */
public class ReceiveCookieInterceptor implements Interceptor {
    private Context context;

    public ReceiveCookieInterceptor(Context context){
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = chain.proceed(  chain.request() );
        if( response.headers("Set-Cookie").isEmpty() ) return response;

        HashSet<String> cookies = new HashSet<>();
        for (String header : response.headers("Set-Cookie")) {
            cookies.add(header);
        }

        PreferenceHelper.writeStringSet( context , Constants.PREF_FILENAME , Constants.PREF_COOKIE , cookies);

        return response;
    }
}
