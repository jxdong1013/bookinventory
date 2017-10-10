package com.jxd.android.bookinventtory.login;

import com.jxd.android.bookinventtory.http.ApiService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/10/9.
 */
@Module
public class LoginModule {
    LoginActivity loginActivity;

    public LoginModule(LoginActivity loginActivity){
        this.loginActivity=loginActivity;
    }

    @Provides
    public ILoginView provideLoginView(){
        return loginActivity;
    }

    @Provides
    public ILoginModel provideLoginModel(ApiService apiService){
        return new LoginModel(apiService , loginActivity);
    }

    @Provides
    public ILoginPresenter  provideLoginPresenter ( ILoginView iLoginView , ILoginModel iLoginModel){
        return new LoginPresenter(iLoginView,iLoginModel);
    }

}
