package com.jxd.android.bookinventtory.login;

import com.jxd.android.bookinventtory.di.AppComponent;
import com.jxd.android.bookinventtory.mvp.PerActivity;

import javax.inject.Inject;

import dagger.Component;
import dagger.Module;

/**
 * Created by Administrator on 2017/10/9.
 */
@PerActivity
@Component(modules={LoginModule.class},dependencies = {AppComponent.class})
public interface LoginComponent {

    void Inject(LoginActivity loginActivity);

    ILoginPresenter getLoginPresenter();

}
