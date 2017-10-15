package com.jxd.android.bookinventtory.booksearch;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jxd.android.bookinventtory.MainActivity;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.adapter.BookSearchAdapter;
import com.jxd.android.bookinventtory.base.BaseApplication;
import com.jxd.android.bookinventtory.base.BaseFragment;
import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.BookCondition;
import com.jxd.android.bookinventtory.bean.DataBase;
import com.jxd.android.bookinventtory.bean.LogoutEvent;
import com.jxd.android.bookinventtory.bean.Page;
import com.jxd.android.bookinventtory.bean.SearchKeyBean;
import com.jxd.android.bookinventtory.config.Constants;
import com.jxd.android.bookinventtory.login.LoginActivity;
import com.jxd.android.bookinventtory.search.SearchActivity;
import com.jxd.android.bookinventtory.search.SearchModule;
import com.jxd.android.bookinventtory.utils.GsonUtil;
import com.jxd.android.bookinventtory.utils.PreferenceHelper;
import com.jxd.android.bookinventtory.widgets.ErrorWidget;
import com.jxd.android.bookinventtory.widgets.ProgressWidget;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.jxd.android.bookinventtory.MainActivity.REQUEST_CODE_SEARCH;

/**
 * 图书检索界面
 * Use the {@link BookSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookSearchFragment
        extends BaseFragment<IBookSearchPresenter>
        implements IBookSearchView , SwipeRefreshLayout.OnRefreshListener , BaseQuickAdapter.RequestLoadMoreListener {

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
    @BindView(R.id.laySearchbar)
    LinearLayout laySearchbar;
    @BindView(R.id.tvSearchBar)
    TextView tvSearchBar;
    @BindView(R.id.tvUserName)
    TextView tvUserName;

    View noDataView;
    View emptyView;

    BookSearchAdapter bookSearchAdapter;
    List<BookBean> bookBeanList;
    int currentPageIndex=-1;
    boolean isShowProgress = true;


    public BookSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment BookSearchFragment.
     */
    public static BookSearchFragment newInstance() {
        BookSearchFragment fragment = new BookSearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = (BaseApplication) getActivity().getApplication();
        DaggerBookSearchComponent
                .builder()
                .appComponent( application.getAppComponent() )
                .bookSearchModule( new BookSearchModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_book_search, container, false);

        unbinder = ButterKnife.bind(this , rootView );

        //EventBus.getDefault().register(this);

        initView();

        return rootView;
    }

    protected void initView(){

        swipeRefreshLayout.setOnRefreshListener(this);

        bookBeanList = new ArrayList<>();
        bookSearchAdapter = new BookSearchAdapter(bookBeanList);
        recyclerView.setLayoutManager( new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(bookSearchAdapter);

        emptyView = LayoutInflater.from(getContext()).inflate(R.layout.layout_empty,(ViewGroup)recyclerView.getParent(),false);
        bookSearchAdapter.setEmptyView(emptyView);
        bookSearchAdapter.setOnLoadMoreListener(this , recyclerView);
        //bookSearchAdapter.isUseEmpty(false);

        tvUserName.setText( application.getUserBean() ==null? "": application.getUserBean().getUserName() );
    }

    @Override
    public void onRefresh() {
        //刷新的时候，移除底部的视图
        if( noDataView!=null && noDataView.getParent()!=null){
            ((ViewGroup)noDataView.getParent()).removeView(noDataView);
        }
        currentPageIndex = -1;
        bookSearchAdapter.isUseEmpty(false);
        fetchData();
    }

    @Override
    public void onLoadMoreRequested() {
        fetchData();
    }

    @Override
    public void onStart() {
        super.onStart();

        //EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();

        //EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //EventBus.getDefault().unregister(this);
    }

    @Override
    public int getNavigateMenuId() {
        return R.id.navigation_booksearch;
    }

    @Override
    public void showProgress() {
        errorWidget.setVisibility(View.GONE);
        if(isShowProgress){
            progressWidget.setVisibility(View.VISIBLE);
        }
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
        isShowProgress=true;
    }

    @Override
    protected void fetchData() {
        bookSearchAdapter.isUseEmpty(false);
        BookCondition condition =new BookCondition();
        String key = tvSearchBar.getText().toString();
        condition.setBookName(key);
        condition.setPageIdx( currentPageIndex +1 );
        iPresenter.getBookList(  condition );
    }

    @OnClick({R.id.layError,R.id.laySearchbar,R.id.tvLogout})
    public void onClick(View v){
        if( v.getId()==R.id.layError){
            fetchData();
        }else if(v.getId() == R.id.laySearchbar){
            Intent intent = new Intent(this.getActivity(),SearchActivity.class);
            this.startActivityForResult(intent , REQUEST_CODE_SEARCH);
        }else if(v.getId()==R.id.tvLogout){
            EventBus.getDefault().post(new LogoutEvent());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if( resultCode== RESULT_OK && requestCode == REQUEST_CODE_SEARCH ){
            SearchKeyBean key= (SearchKeyBean) data.getExtras().getSerializable(Constants.Key_SearchKey);
            searchBook( key );
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //@Subscribe(threadMode = ThreadMode.MAIN)
    public void searchBook( SearchKeyBean key ) {
        tvSearchBar.setText(key.getKey());
        fetchData();
    }

    @Override
    public void callback(Page<BookBean> data) {
        isShowProgress=false;
        bookSearchAdapter.isUseEmpty(true);
        swipeRefreshLayout.setRefreshing(false);

        if(data.getPageIdx() == 0 ){
            currentPageIndex=data.getPageIdx();
            bookSearchAdapter.setNewData( data.getData());
        }else {
            if (data.getData() == null || data.getData().size() < 1) {
                if (noDataView == null) {
                    noDataView = getActivity().getLayoutInflater().inflate(R.layout.layout_nodata, (ViewGroup) recyclerView.getParent(), false);
                    bookSearchAdapter.addFooterView(noDataView);
                } else {
                    if (noDataView.getParent() != null) {
                        ((ViewGroup) noDataView.getParent()).removeView(noDataView);
                    }
                    bookSearchAdapter.addFooterView(noDataView);
                }
                bookSearchAdapter.loadMoreEnd(true);
                return;
            }
            bookSearchAdapter.addData(data.getData());
            bookSearchAdapter.loadMoreComplete();
            currentPageIndex=data.getPageIdx();
        }
    }

    @Override
    public void login() {
        PreferenceHelper.remove( BaseApplication.single , Constants.PREF_FILENAME, Constants.PREF_USER);

        Intent intent =new Intent();
        intent.setClass(this.getActivity() , LoginActivity.class);
        getActivity().startActivity(intent);
    }
}
