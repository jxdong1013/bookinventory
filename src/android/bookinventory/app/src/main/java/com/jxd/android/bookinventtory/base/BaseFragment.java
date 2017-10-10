package com.jxd.android.bookinventtory.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jxd.android.bookinventtory.mvp.IPresenter;
import com.jxd.android.bookinventtory.mvp.IView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Administrator on 2017/9/8.
 */

public abstract class BaseFragment<P extends IPresenter>
        extends Fragment
        implements IView , LifecycleProvider<FragmentEvent>{

    BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();

    protected BaseApplication application;
    @Inject
    protected P iPresenter;
    protected Unbinder unbinder;

    protected boolean isViewPrepared=false;
    protected boolean isVisiableToUser = false;
    protected boolean isDataInited=false;


    public BaseFragment() {
        super();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        lifecycleSubject.onNext(FragmentEvent.ATTACH);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lifecycleSubject.onNext(FragmentEvent.CREATE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isViewPrepared = true;
        lazyLoadData();
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    public void onPause() {
        super.onPause();

        lifecycleSubject.onNext(FragmentEvent.PAUSE);
    }

    @Override
    public void onStop() {
        super.onStop();

        lifecycleSubject.onNext(FragmentEvent.STOP);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);

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
    public void onDestroy() {
        super.onDestroy();

        lifecycleSubject.onNext(FragmentEvent.DESTROY);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        lifecycleSubject.onNext(FragmentEvent.DETACH);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        this.isVisiableToUser=isVisibleToUser;
        lazyLoadData();
    }

    /**
     * 懒加载数据
     */
    protected boolean lazyLoadData(){
        if(isViewPrepared && isVisiableToUser && !isDataInited){
            fetchData();
            isDataInited=true;
            return true;
        }
        return false;
    }

    /**
     *
     */
    protected abstract void fetchData();

    /***
     * 定义fragment对应的底部菜单的Id
     * @return
     */
    public abstract int getNavigateMenuId();

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void toast(String msg) {
        Snackbar.make(this.getView(),msg,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void error(String msg) {

    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    @Nonnull
    @Override
    public Observable<FragmentEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(@Nonnull FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject,event);
    }

    @Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(lifecycleSubject);
    }
}
