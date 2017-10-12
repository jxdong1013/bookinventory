package com.jxd.android.bookinventtory.shelfadapt;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.adapter.ShelfAdaptAdapter;
import com.jxd.android.bookinventtory.adapter.ShelfSearchAdapter;
import com.jxd.android.bookinventtory.base.BaseFragment;
import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.BookShelfAdptBean;
import com.jxd.android.bookinventtory.bean.ShelfBean;
import com.jxd.android.bookinventtory.widgets.ShelfAdaptWidget;

import java.security.Guard;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jxd.android.bookinventtory.R.id.recyclerView;

/**
 * 架位更新 界面
 * Use the {@link ShelfAdaptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShelfAdaptFragment
        extends BaseFragment<IShelfAdaptPresenter>
        implements IShelfAdaptView ,SwipeRefreshLayout.OnRefreshListener , BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.shelfadaptscan)
    ShelfAdaptWidget shelfAdaptScanWidget;

    List<BookShelfAdptBean> data;
    ShelfAdaptAdapter shelfAdaptAdapter;

    public ShelfAdaptFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShelfAdaptFragment.
     */
    public static ShelfAdaptFragment newInstance() {
        ShelfAdaptFragment fragment = new ShelfAdaptFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerShelfAdaptComponent
                .builder()
                .appComponent(application.getAppComponent())
                .shelfAdaptModule(new ShelfAdaptModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shelf_adapt, container, false);
        initView( rootView );
        return rootView;
    }

    protected void initView( View rootView ){
        unbinder = ButterKnife.bind(this , rootView);

        data = new ArrayList<>();
        shelfAdaptAdapter = new ShelfAdaptAdapter(data);
        recyclerView.setLayoutManager( new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(shelfAdaptAdapter);

        //emptyView = LayoutInflater.from(getContext()).inflate(R.layout.layout_shelf_empty,(ViewGroup)recyclerView.getParent(),false);
        //shelfSearchAdapter.setEmptyView(emptyView);
        //shelfSearchAdapter.setOnLoadMoreListener(this , recyclerView);
        //shelfSearchAdapter.setEnableLoadMore(false);
        //bookSearchAdapter.isUseEmpty(false);
    }

    @OnClick({R.id.scanbook,R.id.scanshelf})
    public void onClick(View v){
        if(v.getId()==R.id.scanbook){
            BookBean bookBean=new BookBean();
            bookBean.setBookcode("sssss");
            bookBean.setBookName("ssss");
            shelfAdaptScanWidget.setBookInfo(bookBean);
        }else if(v.getId()==R.id.scanshelf){
            ShelfBean shelfBean=new ShelfBean();
            shelfBean.setShelfCode("ddd");
            shelfBean.setShelfName("ddd");
            shelfAdaptScanWidget.setShelfInfo(shelfBean);
        }
    }

    @Override
    public int getNavigateMenuId() {
        return R.id.navigation_shelfadapt;
    }


    @Override
    public void onRefresh() {
        fetchData();
    }

    @Override
    public void onLoadMoreRequested() {
        fetchData();
    }

    @Override
    protected void fetchData() {

    }
}
