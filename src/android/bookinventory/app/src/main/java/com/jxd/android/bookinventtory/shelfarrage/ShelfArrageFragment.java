package com.jxd.android.bookinventtory.shelfarrage;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompatBase;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.adapter.ShelfArrageAdapter;
import com.jxd.android.bookinventtory.base.BaseFragment;
import com.jxd.android.bookinventtory.bean.BookLevelItem;
import com.jxd.android.bookinventtory.bean.LogoutEvent;
import com.jxd.android.bookinventtory.bean.ShelfBookScanBean;
import com.jxd.android.bookinventtory.bean.ShelfLevelItem;
import com.jxd.android.bookinventtory.bean.ShelfScanBean;
import com.jxd.android.bookinventtory.widgets.ErrorWidget;
import com.jxd.android.bookinventtory.widgets.ProgressWidget;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * 架位盘点
 *
 * A simple {@link Fragment} subclass.
 * Use the {@link ShelfArrageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShelfArrageFragment
        extends BaseFragment<IShelfArragePresenter>
        implements SwipeRefreshLayout.OnRefreshListener , IShelfArrageView{

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progress)
    ProgressWidget progressWidget;
    @BindView(R.id.error)
    ErrorWidget errorWidget;
    @BindView(R.id.shelfarrage_footer_summary)
    TextView tvSummary;


    ShelfArrageAdapter shelfArrageAdapter;
    List<MultiItemEntity> data;
    View emptyView;


    public ShelfArrageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShelfArrageFragment.
     */
    public static ShelfArrageFragment newInstance( ) {
        ShelfArrageFragment fragment = new ShelfArrageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerShelfArrageComponent
                .builder()
                .appComponent(application.getAppComponent())
                .shelfArrageModule(new ShelfArrageModule(this))
                .build()
                .inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shelf_arrage, container, false);
        initView(rootView);
        return rootView;
    }

    protected void initView(View view){
        unbinder = ButterKnife.bind(this,view);

        tvUserName.setText( application.getUserBean()==null?"":application.getUserBean().getUserName() );


        LayoutInflater layoutInflater =LayoutInflater.from(getContext());
        emptyView = layoutInflater.inflate(R.layout.layout_shelf_empty,(ViewGroup)recyclerView.getParent(),false);
        TextView empty = emptyView.findViewById(R.id.emptyText);
        empty.setText(getString(R.string.tipmessage4));

        data = new ArrayList<>();
        shelfArrageAdapter = new ShelfArrageAdapter(data);
        shelfArrageAdapter.isUseEmpty(false);
        shelfArrageAdapter.setEmptyView(emptyView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext() ,1 );
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return shelfArrageAdapter.getItemViewType(position) == ShelfArrageAdapter.ITEM_TYPE_SHELF ? 1 : gridLayoutManager.getSpanCount();
//            }
//        });
        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setAdapter( shelfArrageAdapter);
        //shelfArrageAdapter.

        swipeRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    public int getNavigateMenuId() {
        return R.id.navigation_shelfarrage;
    }

    @Override
    protected void fetchData() {
        shelfArrageAdapter.isUseEmpty(false);
        iPresenter.getDataFromLocal();
    }

    @OnClick({R.id.scan,R.id.uplaod,R.id.exist , R.id.error})
    public void onClick(View v){
        if(v.getId()==R.id.scan ){
            Intent intent =new Intent(getContext(),ShelfArrageUIActivity.class);
            getContext().startActivity(intent);
        }else if(v.getId()==R.id.uplaod){
            upload();
        }else if(v.getId()==R.id.exist){
            EventBus.getDefault().post(new LogoutEvent());
        }else if(v.getId()==R.id.error){
            fetchData();
        }
    }


    void upload(){

    }

    @Override
    public void getCallback(List<ShelfScanBean> list) {
        hideProgress();

        shelfArrageAdapter.isUseEmpty(true);

        data.clear();

        for(ShelfScanBean item : list){

            ShelfLevelItem shelfLevelItem=new ShelfLevelItem(item);
            data.add(shelfLevelItem);
            for(ShelfBookScanBean subItem : item.getBooks()){
                BookLevelItem bookLevelItem = new BookLevelItem(subItem);
                shelfLevelItem.addSubItem(bookLevelItem);
            }
        }

        shelfArrageAdapter.notifyDataSetChanged();

//        if(data.size()>0) {
//            shelfArrageAdapter.expand(0);
//        }

        String summary="共"+ data.size()+"条记录";
        tvSummary.setText(summary);
    }

    @Override
    public void onRefresh() {
        fetchData();
    }

    @Override
    public void showProgress(String msg) {
        super.showProgress(msg);

        errorWidget.setVisibility(View.GONE);

        if(swipeRefreshLayout.isRefreshing()){
            progressWidget.setVisibility(View.GONE);
        }else{
            progressWidget.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        progressWidget.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void toast(String msg) {
        super.toast(msg);
    }

    @Override
    public void error(String msg) {
        super.error(msg);
        errorWidget.setVisibility(View.VISIBLE);
        errorWidget.setError(msg);
    }
}
