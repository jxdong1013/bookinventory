package com.jxd.android.bookinventtory.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.huotu.android.library.libedittext.EditText;
import com.jxd.android.bookinventtory.MainActivity;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.base.BaseActivity;
import com.jxd.android.bookinventtory.base.BaseApplication;
import com.jxd.android.bookinventtory.bean.UserBean;
import com.jxd.android.bookinventtory.config.Constants;
import com.jxd.android.bookinventtory.utils.GsonUtil;
import com.jxd.android.bookinventtory.utils.PreferenceHelper;
import com.jxd.android.bookinventtory.widgets.ProgressWidget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jxd.android.bookinventtory.utils.PreferenceHelper.readString;

public class LoginActivity extends BaseActivity<ILoginPresenter>
                implements ILoginView{

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.progress)
    ProgressWidget progressWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);

        DaggerLoginComponent.builder()
                .appComponent(((BaseApplication)this.getApplication()).getAppComponent() )
                .loginModule(new LoginModule(this))
                .build()
                .Inject(this);

        isLogin();
    }

    protected void isLogin(){
        String userString = PreferenceHelper.readString( this , Constants.PREF_FILENAME , Constants.PREF_USER , "" );
        UserBean user = GsonUtil.getGson().fromJson(userString , UserBean.class);
        if(user==null){
            return;
        }

        Intent intent = new Intent();
        intent.setClass( this, MainActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    @OnClick(R.id.btnLogin)
    void login(View v ){

        String uName = username.getText().toString();
        String pword = password.getText().toString();

        iPresenter.login(uName , pword );
    }

    @Override
    public void showProgress() {
        progressWidget.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressWidget.setVisibility(View.GONE);
    }

    @Override
    public void toast(String msg) {
        hideProgress();
        super.toast(msg);
    }

    @Override
    public void error(String msg) {
        super.error(msg);
    }

    @Override
    public void callback(UserBean user) {

        PreferenceHelper.writeString( baseApplication , Constants.PREF_FILENAME , Constants.PREF_USER , GsonUtil.getGson().toJson( user));

        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        this.startActivity(intent);
    }
}
