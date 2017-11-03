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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.adapter.ShelfArrageAdapter;
import com.jxd.android.bookinventtory.base.BaseFragment;
import com.jxd.android.bookinventtory.bean.BookLevelItem;
import com.jxd.android.bookinventtory.bean.LogoutEvent;
import com.jxd.android.bookinventtory.bean.ShelfBookScanBean;
import com.jxd.android.bookinventtory.bean.ShelfLevelItem;
import com.jxd.android.bookinventtory.bean.ShelfScanBean;
import com.jxd.android.bookinventtory.bean.UpdateInventory;
import com.jxd.android.bookinventtory.utils.ToastUtils;
import com.jxd.android.bookinventtory.widgets.ErrorWidget;
import com.jxd.android.bookinventtory.widgets.ProgressWidget;
import com.jxd.android.bookinventtory.widgets.TipAlertDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * 架位盘点界面
 */
public class ShelfArrageFragment
        extends BaseFragment<IShelfArragePresenter>
        implements SwipeRefreshLayout.OnRefreshListener ,
        IShelfArrageView , BaseQuickAdapter.OnItemChildClickListener{

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
    @BindView(R.id.shelfarrage_footer_operate)
    LinearLayout lay_footer_operate;
    @BindView(R.id.shelfarrage_footer_delete)
     TextView tvDelete;
    @BindView(R.id.shelfarrage_footer_uplaod)
     TextView tvUpload;

    ShelfArrageAdapter shelfArrageAdapter;
    List<MultiItemEntity> data;
    View emptyView;
    int uploadPerCount= 20;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shelf_arrage, container, false);
        initView(rootView);
        return rootView;
    }

    protected void initView(View view){
        unbinder = ButterKnife.bind(this,view);

        tvUserName.setText( application.getUserBean()==null?"":application.getUserBean().getUsername() );

        LayoutInflater layoutInflater =LayoutInflater.from(getContext());
        emptyView = layoutInflater.inflate(R.layout.layout_shelf_empty,(ViewGroup)recyclerView.getParent(),false);
        TextView empty = emptyView.findViewById(R.id.emptyText);
        empty.setText(getString(R.string.tipmessage4));

        data = new ArrayList<>();
        shelfArrageAdapter = new ShelfArrageAdapter(data);
        shelfArrageAdapter.isUseEmpty(false);
        shelfArrageAdapter.setEmptyView(emptyView);
        shelfArrageAdapter.isUseEmpty(false);
        shelfArrageAdapter.setOnItemChildClickListener(this);
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

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public int getNavigateMenuId() {
        return R.id.navigation_shelfarrage;
    }

    @Override
    protected void fetchData() {
        shelfArrageAdapter.isUseEmpty(false);
        shelfArrageAdapter.setShowOperate(false);
        iPresenter.getDataFromLocal();
    }

    @OnClick({R.id.scan,R.id.uplaod,R.id.exist , R.id.error, R.id.shelfarrage_footer_selectall, R.id.shelfarrage_footer_delete , R.id.delete })
    public void onClick(View v){
        if(v.getId()==R.id.scan ){
            Intent intent =new Intent(getContext(),ShelfArrageUIActivity.class);
            getContext().startActivity(intent);
        }else if(v.getId()==R.id.uplaod){
            operate();
        }else if(v.getId()==R.id.exist){
            EventBus.getDefault().post(new LogoutEvent());
        }else if(v.getId()==R.id.error){
            fetchData();
        }else if(v.getId() == R.id.shelfarrage_footer_selectall){
            selectAllOrNot( ((CheckBox)v).isChecked());
        }else if(v.getId() == R.id.shelfarrage_footer_delete){
            deleteData();
        }else if( v.getId() == R.id.delete){
            operateDelete();
        }
    }

    private void operateDelete( ){
        if(data==null || data.size()<1 )return;
        lay_footer_operate.setVisibility( lay_footer_operate.getVisibility() == View.GONE? View.VISIBLE:View.GONE );
        shelfArrageAdapter.setShowOperate( !shelfArrageAdapter.isShowOperate() );
        shelfArrageAdapter.notifyDataSetChanged();
        tvDelete.setVisibility(View.VISIBLE);
        tvUpload.setVisibility(View.GONE);
    }

    /**
     * 删除盘点数据
     */
    protected void deleteData(){
        if( data==null||data.size()<1 ) return;
        int count=0;
        final List<String> list =new ArrayList<String>();
        for(MultiItemEntity item : data){
            ShelfLevelItem shelfLevelItem = (ShelfLevelItem) item;
            if(shelfLevelItem.isChecked()){
                count++;
                list.add(shelfLevelItem.getShelf().getId());
            }
        }
        if( count<1 ){
            ToastUtils.showLongToast( "请勾选需要删除的记录");
            return;
        }

        final TipAlertDialog tipAlertDialog = new TipAlertDialog(this.getContext() , false);
        tipAlertDialog.show("询问", "确定要删除记录吗?", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipAlertDialog.dismiss();
                iPresenter.deleteLocalData(list);
            }
        });
    }

    protected void selectAllOrNot( boolean ischeck ){
        if(data==null) return;
        for(MultiItemEntity item : data){
            ShelfLevelItem shelfLevelItem=(ShelfLevelItem) item;
            shelfLevelItem.setChecked( ischeck );
            shelfArrageAdapter.notifyDataSetChanged();
        }
    }

    private void operate( ){
        if(data==null || data.size()<1 )return;
        lay_footer_operate.setVisibility( lay_footer_operate.getVisibility() == View.GONE? View.VISIBLE:View.GONE );
        shelfArrageAdapter.setShowOperate( !shelfArrageAdapter.isShowOperate() );
        shelfArrageAdapter.notifyDataSetChanged();
        tvDelete.setVisibility(View.GONE);
        tvUpload.setVisibility(View.VISIBLE);
    }

    void upload(){
        if( data ==null || data.size()<1){
            toast("请盘点数据以后，再上传数据");
            return;
        }

        List<UpdateInventory> updateInventories = new ArrayList<>();
        int index=0;
        for( MultiItemEntity entity : data){
            ShelfLevelItem shelfLevelItem= (ShelfLevelItem)entity;
            if( index % uploadPerCount ==0 ){

            }
        }
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

        lay_footer_operate.setVisibility(View.GONE);
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

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if( view.getId() == R.id.shelfarrage_shelf_item_check){
            ShelfLevelItem shelfLevelItem=(ShelfLevelItem) adapter.getItem(position);
            shelfLevelItem.setChecked( !shelfLevelItem.isChecked() );
            view.setBackgroundResource( shelfLevelItem.isChecked() ? R.mipmap.check : R.mipmap.check_no );
        }
    }

    @Override
    public void deleteCallback() {
        lay_footer_operate.setVisibility(View.GONE);
        iPresenter.getDataFromLocal();
    }
}
