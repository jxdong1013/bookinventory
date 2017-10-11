package com.jxd.android.bookinventtory.shelfsearch;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.adapter.BookSearchAdapter;
import com.jxd.android.bookinventtory.adapter.ShelfSearchAdapter;
import com.jxd.android.bookinventtory.base.BaseFragment;
import com.jxd.android.bookinventtory.bean.BookCondition;
import com.jxd.android.bookinventtory.bean.ShelfCondition;
import com.jxd.android.bookinventtory.widgets.ErrorWidget;
import com.jxd.android.bookinventtory.widgets.ProgressWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jxd.android.bookinventtory.R.id.tvSearchBar;

/**
 * 架位搜索界面
 * Use the {@link ShelfSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShelfSearchFragment
        extends BaseFragment<IShelfSearchPresenter>
        implements IShelfSearchView , SwipeRefreshLayout.OnRefreshListener , BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progress)
    ProgressWidget progressWidget;
    @BindView(R.id.error)
    ErrorWidget errorWidget;
    @BindView(R.id.errorText)
    TextView tvErrorText;
    @BindView(R.id.tvSearchBar)
    TextView tvSearchBar;

    List<MultiItemEntity> data;
    ShelfSearchAdapter shelfSearchAdapter;
    int currentPageIndex=-1;
    View emptyView;

    public ShelfSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShelfSearchFragment.
     */
    public static ShelfSearchFragment newInstance() {
        ShelfSearchFragment fragment = new ShelfSearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerShelfSearchComponent.builder()
                .appComponent(application.getAppComponent())
                .shelfSearchModule( new ShelfSearchModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shelf_search, container, false);

        initView(rootView);

        return rootView;
    }

    protected void initView( View rootView ){
        unbinder = ButterKnife.bind(this , rootView);
        swipeRefreshLayout.setOnRefreshListener(this);

        data = new ArrayList<>();
        shelfSearchAdapter = new ShelfSearchAdapter(data);
        recyclerView.setLayoutManager( new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(shelfSearchAdapter);

        emptyView = LayoutInflater.from(getContext()).inflate(R.layout.layout_empty,(ViewGroup)recyclerView.getParent(),false);
        shelfSearchAdapter.setEmptyView(emptyView);
        shelfSearchAdapter.setOnLoadMoreListener(this , recyclerView);
        //bookSearchAdapter.isUseEmpty(false);
    }

    @Override
    public void onRefresh() {
        //刷新的时候，移除底部的视图
//        if( noDataView!=null && noDataView.getParent()!=null){
//            ((ViewGroup)noDataView.getParent()).removeView(noDataView);
//        }
        currentPageIndex = -1;
        shelfSearchAdapter.isUseEmpty(false);
        fetchData();
    }

    @Override
    public void onLoadMoreRequested() {
        fetchData();
    }

    @Override
    public int getNavigateMenuId() {
        return R.id.navigation_shelfsearch;
    }


    @Override
    protected void fetchData() {
        shelfSearchAdapter.isUseEmpty(false);
        ShelfCondition condition =new ShelfCondition();
        String key = tvSearchBar.getText().toString();
        condition.setShelfName(key);
        condition.setPageIdx( currentPageIndex +1 );
        iPresenter.getShelfList(  condition );
    }

    @Override
    public void showProgress() {
        errorWidget.setVisibility(View.GONE);
       // if(isShowProgress){
       //    progressWidget.setVisibility(View.VISIBLE);
       // }
    }

    @Override
    public void hideProgress() {
        errorWidget.setVisibility(View.GONE);
        progressWidget.setVisibility(View.GONE);
    }

    @Override
    public void toast(String msg) {
        hideProgress();
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(recyclerView,msg,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void error(String msg) {
        progressWidget.setVisibility(View.GONE);
        errorWidget.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
        tvErrorText.setText(msg);
        //isShowProgress=true;
    }
}
