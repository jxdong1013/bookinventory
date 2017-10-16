package com.jxd.android.bookinventtory.shelfarrage;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.adapter.ShelfBookScanAdapter;
import com.jxd.android.bookinventtory.base.BaseActivity;
import com.jxd.android.bookinventtory.base.BaseApplication;
import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.LogoutEvent;
import com.jxd.android.bookinventtory.bean.ShelfBean;
import com.jxd.android.bookinventtory.config.Constants;
import com.jxd.android.bookinventtory.shelfsearch.DaggerShelfSearchComponent;
import com.jxd.android.bookinventtory.utils.ToastUtils;
import com.jxd.android.bookinventtory.widgets.ErrorWidget;
import com.jxd.android.bookinventtory.widgets.ProgressWidget;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class ShelfArrageUIActivity extends BaseActivity<IShelfArrageUIPresenter>
            implements IShelfArrageUIView {

    @BindView(R.id.right)
    TextView tvRight;
    @BindView(R.id.header)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.shelfCode)
    TextView tvShelfCode;
    @BindView(R.id.shelfName)
    TextView tvShelfName;
    @BindView(R.id.tvSummary)
    TextView tvSummary;
    @BindView(R.id.tvTip)
    TextView tvTip;
    @BindView(R.id.progress)
    ProgressWidget progressWidget;
    @BindView(R.id.progressText)
    TextView progressText;
    @BindView(R.id.error)
    ErrorWidget errorWidget;

    View emptyView;

    ShelfBookScanAdapter shelfBookScanAdapter;
    List<BookBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf_arrage);

        unbinder = ButterKnife.bind(this);

        baseApplication= (BaseApplication)this.getApplication();

        DaggerShelfArrageComponent.builder()
                .appComponent(baseApplication.getAppComponent())
                .shelfArrageModule(new ShelfArrageModule(this))
                .build()
                .inject(this);

        initView();

    }

    void initView(){
        Drawable drawableLeft = ContextCompat.getDrawable(this , R.drawable.vector_drawable_save_24);
        tvRight.setCompoundDrawables( drawableLeft , null,null,null);

        emptyView = LayoutInflater.from(this).inflate(R.layout.layout_shelf_empty,(ViewGroup)recyclerView.getParent(),false);
        data=new ArrayList<>();
        shelfBookScanAdapter=new ShelfBookScanAdapter(data);
        shelfBookScanAdapter.setEmptyView(emptyView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(shelfBookScanAdapter);

        tvTitle.setText("架位整理");
        //errorWidget.setError("请扫描架位标签,进行架位整理");
        //progressWidget.setVisibility(View.GONE);
        //errorWidget.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.scanshelf,R.id.tvBack,R.id.right})
    public void onClick(View v){
        if(v.getId()==R.id.scanshelf){
            getShelfInfo("22222");
        }else if(v.getId()==R.id.tvBack){
            this.finish();
        }else if(v.getId()==R.id.right){
            save();
        }
    }

    void save(){

    }

    void getShelfInfo(String shelfCode){
        iPresenter.getShelfInfoByShelfCode( shelfCode );
    }

    @Override
    public void getShelfInfoCallback(ShelfBean shelfBean) {
        tvShelfCode.setText( shelfBean.getShelfCode() );
        tvShelfName.setText(shelfBean.getShelfName());
        data = shelfBean.getBooks();
        shelfBookScanAdapter.setNewData( data );
    }

    @Override
    public void login() {
        EventBus.getDefault().post(new LogoutEvent());
        this.finish();
    }

    @Override
    public void showProgress(String msg) {
        errorWidget.setVisibility(View.GONE);
        progressWidget.setVisibility(View.VISIBLE);
        progressWidget.setProgressMessage(Constants.TIP_WAITING);
    }

    @Override
    public void hideProgress() {
        progressWidget.setVisibility(View.GONE);
        progressWidget.setProgressMessage("");
    }

    @Override
    public void toast(String msg) {
        super.toast(msg);
    }

    @Override
    public void error(String msg) {
        //super.error(msg);
        ToastUtils.showLongToast(msg);
    }
}