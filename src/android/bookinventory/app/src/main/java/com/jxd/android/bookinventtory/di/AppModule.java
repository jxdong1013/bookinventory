package com.jxd.android.bookinventtory.di;

import com.google.gson.Gson;
import com.jxd.android.bookinventtory.config.Constants;
import com.jxd.android.bookinventtory.base.BaseApplciation;
import com.jxd.android.bookinventtory.http.RequestInterceptor;
import com.jxd.android.bookinventtory.utils.RealmUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/9/8.
 */
@Singleton
@Module
public class AppModule {
    BaseApplciation application;
    public AppModule(BaseApplciation application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public BaseApplciation provideApplication(){
        return application;
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(Interceptor interceptor){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(Constants.READ_TIMEOUT , TimeUnit.SECONDS)
                .connectTimeout( Constants.CONNECT_TIMEOUT , TimeUnit.SECONDS )
                .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor( interceptor)
                .build();
        return  okHttpClient;
    }

    @Provides
    public Interceptor provideInterceptor(){
        return new RequestInterceptor(null, RequestInterceptor.Level.ALL);
    }

    @Singleton
    @Provides
    public Gson provideGson(){
        return new Gson();
    }
    @Singleton
    @Provides
    public GsonConverterFactory provideGsonConverter(Gson gson ){
        return  GsonConverterFactory.create( gson );
    }
    @Singleton
    @Provides
    public RxJava2CallAdapterFactory provideRxJava2CallAdapter(){
        return  RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public Retrofit provideRetroft(GsonConverterFactory gsonConverterFactory , RxJava2CallAdapterFactory rxJava2CallAdapterFactory){
        Retrofit retrofit = new Retrofit.Builder().baseUrl( Constants.BASE_URL )
                .addConverterFactory( gsonConverterFactory )
                .addCallAdapterFactory( rxJava2CallAdapterFactory)
                .build();
        return retrofit;
    }

}
