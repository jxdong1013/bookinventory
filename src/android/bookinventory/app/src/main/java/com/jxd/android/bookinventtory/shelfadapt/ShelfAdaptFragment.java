package com.jxd.android.bookinventtory.shelfadapt;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.adapter.ShelfAdaptAdapter;
import com.jxd.android.bookinventtory.adapter.ShelfSearchAdapter;
import com.jxd.android.bookinventtory.base.BaseFragment;
import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.BookShelfAdptBean;
import com.jxd.android.bookinventtory.bean.LogoutEvent;
import com.jxd.android.bookinventtory.bean.ShelfBean;
import com.jxd.android.bookinventtory.mvp.IPresenter;
import com.jxd.android.bookinventtory.utils.DateUtils;
import com.jxd.android.bookinventtory.utils.ToastUtils;
import com.jxd.android.bookinventtory.widgets.ProgressWidget;
import com.jxd.android.bookinventtory.widgets.ShelfAdaptWidget;
import com.jxd.android.bookinventtory.widgets.TipAlertDialog;

import org.greenrobot.eventbus.EventBus;

import java.security.Guard;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 架位更新 界面
 * Use the {@link ShelfAdaptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShelfAdaptFragment
        extends BaseFragment<IShelfAdaptPresenter>
        implements IShelfAdaptView
        ,SwipeRefreshLayout.OnRefreshListener
        , BaseQuickAdapter.RequestLoadMoreListener
        ,BaseQuickAdapter.OnItemChildClickListener
        ,ShelfAdaptWidget.onSaveScanResultListener{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.shelfadaptscan)
    ShelfAdaptWidget shelfAdaptScanWidget;
    @BindView(R.id.progress)
    ProgressWidget progressWidget;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvSummary)
    TextView tvSummary;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    List<BookShelfAdptBean> data;
    ShelfAdaptAdapter shelfAdaptAdapter;
    Handler handler;
    View emptyView;

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(handler!=null){
            this.handler.removeCallbacksAndMessages(null);
        }

    }

    protected void initView(View rootView ){
        unbinder = ButterKnife.bind(this , rootView);

        tvUserName.setText( application.getUserBean() ==null? "": application.getUserBean().getUsername() );

        data = new ArrayList<>();
        shelfAdaptAdapter = new ShelfAdaptAdapter(data);
        shelfAdaptAdapter.setOnItemChildClickListener(this);
        recyclerView.setLayoutManager( new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(shelfAdaptAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        shelfAdaptScanWidget.setOnSaveScanResultListener(this);
        tvSummary.setText("");

        emptyView = LayoutInflater.from(getContext()).inflate(R.layout.layout_shelf_empty,(ViewGroup)recyclerView.getParent(),false);
        TextView empty = emptyView.findViewById(R.id.emptyText);
        empty.setText(getString(R.string.tipmessage1));
        shelfAdaptAdapter.setEmptyView(emptyView);
        //shelfSearchAdapter.setOnLoadMoreListener(this , recyclerView);
        //shelfSearchAdapter.setEnableLoadMore(false);
        //bookSearchAdapter.isUseEmpty(false);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if( view.getId()== R.id.shelfadapte_delete){
            deleteOne( (BookShelfAdptBean) adapter.getItem(position) );
        }
    }

    void deleteOne(final BookShelfAdptBean bookShelfAdptBean  ){
        final TipAlertDialog tipAlertDialog=new TipAlertDialog(this.getContext() , false);
        tipAlertDialog.show("询问", "您确定要选择的数据吗？", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipAlertDialog.dismiss();
                iPresenter.deleteOne( bookShelfAdptBean );
            }
        });
    }

    @OnClick({R.id.scanbook,R.id.scanshelf,R.id.tvDeleteAll,R.id.tvLogout,R.id.tvUpload})
    public void onClick(View v){
        if(v.getId()==R.id.scanbook){
            BookBean bookBean=new BookBean();
            bookBean.setBarcode(UUID.randomUUID().toString());
            bookBean.setTitle(bookBean.getBarcode());
            shelfAdaptScanWidget.setBookInfo(bookBean);
        }else if(v.getId()==R.id.scanshelf){
            ShelfBean shelfBean=new ShelfBean();
            shelfBean.setShelfno(UUID.randomUUID().toString());
            shelfAdaptScanWidget.setShelfInfo(shelfBean);
        }else if( v.getId()==R.id.tvLogout){
            EventBus.getDefault().post(new LogoutEvent());
        }else if(v.getId() == R.id.tvDeleteAll){
            deleteAll();
        }else if(v.getId()==R.id.tvUpload){
            upload();    
        }
    }

    protected void deleteAll(){
        TipAlertDialog tipAlertDialog=new TipAlertDialog(this.getContext() , false);
        tipAlertDialog.show("询问", "您确定要删除所有数据吗？", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenter.deleteAll();
            }
        });
    }
    
    protected void upload(){
        //// TODO: 2017/10/13
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
        iPresenter.getBookShelfScanList();
    }

    @Override
    public void saveScanResult(final BookBean bookBean, final ShelfBean ShelfBean) {

        BookShelfAdptBean bookShelfAdptBean = new BookShelfAdptBean();
        bookShelfAdptBean.setShelfno(ShelfBean.getShelfno());
        bookShelfAdptBean.setBarcode(bookBean.getBarcode());
        bookShelfAdptBean.setAdaptTime(Calendar.getInstance().getTime());
        bookShelfAdptBean.setTitle(bookBean.getTitle());
        bookShelfAdptBean.setUserId(application.getUserBean().getUserid());
        bookShelfAdptBean.setUserName(application.getUserBean().getUsername());
        bookShelfAdptBean.setId(UUID.randomUUID().toString());
        iPresenter.saveBookShelfAdaptResult(bookShelfAdptBean);

    }

    @Override
    public void saveCallback() {

        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showLongToast("架位调整信息已经保存成功");
                shelfAdaptScanWidget.reset();
                //fetchData();
            }
        }, 1000);
    }

    @Override
    public void getCallback(List<BookShelfAdptBean> bookShelfAdptBeanList) {
        hideProgress();
        swipeRefreshLayout.setRefreshing(false);
        data = bookShelfAdptBeanList;
        shelfAdaptAdapter.setNewData(data);

        String summary = "共"+ String.valueOf( bookShelfAdptBeanList.size())+"条记录";
        tvSummary.setText(summary);
        if( bookShelfAdptBeanList.size()<1){
            tvSummary.setVisibility(View.GONE);
        }else{
            tvSummary.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void deleteCallback() {
        hideProgress();
        swipeRefreshLayout.setRefreshing(false);
        toast("删除成功");
        fetchData();
    }

    @Override
    public void showProgress(String msg ) {
        super.showProgress(msg);
        progressWidget.setProgressMessage(msg);
        progressWidget.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        progressWidget.setVisibility(View.GONE);
    }

    @Override
    public void toast(String msg) {
        super.toast(msg);
    }

    @Override
    public void error(String msg) {
        super.error(msg);
    }
}
