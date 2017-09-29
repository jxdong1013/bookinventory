package com.jxd.android.bookinventtory.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jxd.android.bookinventtory.mvp.IPresenter;
import com.jxd.android.bookinventtory.mvp.IView;
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
 * Created by Administrator on 2017/9/8.
 */
public class BaseActivity<P extends IPresenter>
        extends AppCompatActivity
        implements IView , LifecycleProvider<ActivityEvent>{

    protected Unbinder unbinder;
    BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Inject
    P iPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lifecycleSubject.onNext(ActivityEvent.CREATE);
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
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void toast(String msg) {

    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return null;
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
