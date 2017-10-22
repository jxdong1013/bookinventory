package com.jxd.android.bookinventtory.login;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huotu.android.library.libedittext.EditText;
import com.jxd.android.bookinventtory.main.MainActivity;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.base.BaseActivity;
import com.jxd.android.bookinventtory.base.BaseApplication;
import com.jxd.android.bookinventtory.bean.UserBean;
import com.jxd.android.bookinventtory.config.Constants;
import com.jxd.android.bookinventtory.di.DaggerAppComponent;
import com.jxd.android.bookinventtory.utils.GsonUtil;
import com.jxd.android.bookinventtory.utils.KeyWordUtil;
import com.jxd.android.bookinventtory.utils.PreferenceHelper;
import com.jxd.android.bookinventtory.widgets.ErrorWidget;
import com.jxd.android.bookinventtory.widgets.ProgressWidget;
import com.jxd.android.bookinventtory.widgets.TipAlertDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
    @BindView(R.id.layConfig)
    LinearLayout layConfig;
    @BindView(R.id.edUrl)
    EditText etUrl;
    @BindView(R.id.btnSaveUrl)
    TextView tvSaveConfig;
    @BindView(R.id.btnConfigUrl)
    TextView tvConfig;


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
        baseApplication.setUserBean(user);

        Intent intent = new Intent();
        intent.setClass( this, MainActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    @OnClick(R.id.btnLogin)
    void login(View v ){

        String uName = username.getText().toString();
        String pword = password.getText().toString();

        KeyWordUtil.closeKeybord( username ,  this);
        KeyWordUtil.closeKeybord(password , this);

        iPresenter.login(uName , pword );
    }

    @OnClick(R.id.btnConfigUrl)
    void openConfig(View v){
        layConfig.setVisibility(View.VISIBLE);
        etUrl.setText(Constants.BASE_URL );
        tvConfig.setVisibility(View.GONE);
    }
    @OnClick(R.id.btnSaveUrl)
    void saveConfig(View v){

        String url =etUrl.getText().toString();
        url = url.toLowerCase();

        if( url.endsWith("/")==false){
            url +="/";
        }
        if( !url.startsWith("http://") ){
            url ="http://"+url;
        }

        Constants.BASE_URL = url;
        PreferenceHelper.writeString( this , Constants.PREF_FILENAME , Constants.PREF_BASE_URL ,url );

        tvConfig.setVisibility(View.VISIBLE);
        layConfig.setVisibility(View.GONE);

        final TipAlertDialog tipAlertDialog = new TipAlertDialog(this , false);

        tipAlertDialog.show("提示", "请重新打开App，才能是配置生效!", "", R.color.black, false, true, null,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoginActivity.this.finish();
                        System.exit(0);
//                        tipAlertDialog.dismiss();
//                        Intent intent = LoginActivity.this.getPackageManager().getLaunchIntentForPackage(LoginActivity.this.getPackageName());
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        LoginActivity.this.startActivity(intent);
                    }
                });
    }


    @Override
    public void showProgress(String msg ) {
        //errorWidget.setVisibility(View.GONE);
        progressWidget.setVisibility(View.VISIBLE);
        progressWidget.setProgressMessage(msg);
    }

    @Override
    public void hideProgress() {
        //errorWidget.setVisibility(View.GONE);
        progressWidget.setVisibility(View.GONE);
    }

    @Override
    public void toast(String msg) {
        hideProgress();
        super.toast(msg);
    }

    @Override
    public void error(String msg) {
        progressWidget.setVisibility(View.GONE);
        //errorWidget.setVisibility(View.VISIBLE);
        //errorText.setText(msg);
        toast(msg);
    }

    @Override
    public void callback(UserBean user) {

        PreferenceHelper.writeString( baseApplication , Constants.PREF_FILENAME , Constants.PREF_USER , GsonUtil.getGson().toJson( user));
        baseApplication.setUserBean(user);

        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        this.startActivity(intent);
        this.finish();
    }
}
