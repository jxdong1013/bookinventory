package com.jxd.android.bookinventtory.base;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jxd.android.bookinventtory.mvp.IPresenter;
import com.jxd.android.bookinventtory.mvp.IView;
import com.jxd.android.bookinventtory.widgets.ProgressWidget;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import javax.inject.Inject;

import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by jinxiangdong on 2017/9/8.
 */
public class BaseActivity<P extends IPresenter>
        extends AppCompatActivity
        implements IView , LifecycleProvider<ActivityEvent>{

    protected Unbinder unbinder;
    @Inject
    protected  BaseApplication baseApplication;

    BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Inject
    protected P iPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lifecycleSubject.onNext(ActivityEvent.CREATE);

        setImmerseLayout();
    }

    @Override
    protected void onStart() {
        super.onStart();

        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    protected void onStop() {
        super.onStop();

        lifecycleSubject.onNext(ActivityEvent.STOP);
    }

    @Override
    protected void onPause() {
        super.onPause();

        lifecycleSubject.onNext(ActivityEvent.PAUSE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        lifecycleSubject.onNext(ActivityEvent.DESTROY);

        if(unbinder!=null){
            unbinder.unbind();
            unbinder=null;
        }

        if(iPresenter!=null){
            iPresenter.onDestory();
            iPresenter=null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if( keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    public void setImmerseLayout(){
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    @Override
    public void showProgress(String msg ) {
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void toast(String msg) {
        Snackbar.make(this.getWindow().getDecorView(),msg,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void error(String msg) {

    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    //@Nonnull
    @Override
    public Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    //@Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    //@Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }
}
