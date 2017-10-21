package com.jxd.android.bookinventtory.shelfarrage;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.adapter.ShelfBookScanAdapter;
import com.jxd.android.bookinventtory.base.BaseActivity;
import com.jxd.android.bookinventtory.base.BaseApplication;
import com.jxd.android.bookinventtory.bean.BookStatusEnum;
import com.jxd.android.bookinventtory.bean.ShelfBookScanBean;
import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.LogoutEvent;
import com.jxd.android.bookinventtory.bean.ShelfBean;
import com.jxd.android.bookinventtory.bean.ShelfScanBeam;
import com.jxd.android.bookinventtory.config.Constants;
import com.jxd.android.bookinventtory.utils.ToastUtils;
import com.jxd.android.bookinventtory.widgets.ErrorWidget;
import com.jxd.android.bookinventtory.widgets.ProgressWidget;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmList;

import static android.R.attr.data;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.jxd.android.bookinventtory.R.mipmap.shelf;
import static com.jxd.android.bookinventtory.R.mipmap.shelfs;

/**
 *
 */
public class ShelfArrageUIActivity extends BaseActivity<IShelfArrageUIPresenter>
            implements IShelfArrageUIView {

    @BindView(R.id.right)
    TextView tvRight;
    @BindView(R.id.rightImage)
    ImageView ivRightImage;
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
    ShelfScanBeam shelfScanBeam;
    //List<ShelfBookScanBean> data;

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
        ivRightImage.setImageResource( R.drawable.vector_drawable_save_24);
        emptyView = LayoutInflater.from(this).inflate(R.layout.layout_shelf_empty,(ViewGroup)recyclerView.getParent(),false);
        shelfScanBeam = new ShelfScanBeam();
        shelfScanBeam.setBooks(new RealmList<ShelfBookScanBean>());
        shelfBookScanAdapter=new ShelfBookScanAdapter(shelfScanBeam.getBooks());
        shelfBookScanAdapter.setEmptyView(emptyView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(shelfBookScanAdapter);
        tvTitle.setText("架位整理");
        //errorWidget.setError("请扫描架位标签,进行架位整理");
        //progressWidget.setVisibility(View.GONE);
        //errorWidget.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.scanshelf, R.id.scanbook, R.id.tvBack,R.id.rightImage})
    public void onClick(View v){
        if(v.getId()==R.id.scanshelf){
            //TODO
            String shelfCode = UUID.randomUUID().toString();
            getShelfInfo(shelfCode);
        }else if(v.getId()==R.id.tvBack){
            this.finish();
        }else if(v.getId()==R.id.rightImage){
            save();
        }else if(v.getId()==R.id.scanbook){
            mockBook();
        }
    }

    void mockBook(){
        if( shelfScanBeam.getBooks().size()<1) return;
        Random random =new Random();
        int r = random.nextInt(2);
        if( r == 0){//mock 在库
            int r2 = random.nextInt( shelfScanBeam.getBooks().size() );
            ShelfBookScanBean shelfBookScanBean = shelfScanBeam.getBooks().get(  r2 );
            shelfBookScanBean.setScanStatus(BookStatusEnum.IN.getCode());
            shelfBookScanAdapter.notifyItemChanged(r2);
            recyclerView.smoothScrollToPosition( r2 );
        }else if(r==1){//mock 新书
            //int r2 = random.nextInt( shelfScanBeam.getBooks().size() );
            //ShelfBookScanBean shelfBookScanBean = shelfScanBeam.getBooks().get(  r2 );
            //shelfBookScanBean.setScanStatus(BookStatusEnum.NEW.getCode());
            //shelfBookScanAdapter.notifyItemChanged(r2);
            //recyclerView.smoothScrollToPosition(r2);
            ShelfBookScanBean newShelfBookScanBean = new ShelfBookScanBean();
            newShelfBookScanBean.setScanStatus( BookStatusEnum.NEW.getCode() );
            newShelfBookScanBean.setStatus(BookStatusEnum.IN.getCode());
            newShelfBookScanBean.setBookName("new book");
            newShelfBookScanBean.setBookcode(UUID.randomUUID().toString());
            newShelfBookScanBean.setAuthor("author");
            newShelfBookScanBean.setPosition("position");
            newShelfBookScanBean.setBookId(UUID.randomUUID().toString());
            newShelfBookScanBean.setPublish("publish");
            newShelfBookScanBean.setPublishDate("2017-10-21");

            shelfBookScanAdapter.addData( 0, newShelfBookScanBean );
            recyclerView.smoothScrollToPosition(0);
        }

        setSummary();
    }

    void setSummary(){
        if(shelfScanBeam==null) tvSummary.setText("");


        int incount=0;
        int newcount=0;
        int outcount=0;
        for(int i=0;i< shelfScanBeam.getBooks().size();i++){
            if(  shelfScanBeam.getBooks().get(i).getScanStatus()== BookStatusEnum.IN.getCode()){
                incount++;
            }else if(shelfScanBeam.getBooks().get(i).getScanStatus()==BookStatusEnum.NEW.getCode()){
                newcount++;
            }else if(shelfScanBeam.getBooks().get(i).getScanStatus()==null || shelfScanBeam.getBooks().get(i).getScanStatus().isEmpty()){
                outcount++;
            }
        }

        String summary="共"+ shelfScanBeam.getBooks().size() +"条记录，在库"+ incount+"条,新增"+newcount+"条,借出"+ outcount+"条";

        tvSummary.setText(summary);
    }

    void save(){
        if( shelfScanBeam.getShelfCode() ==null || shelfScanBeam.getShelfCode().isEmpty() ){
            ToastUtils.showLongToast("请扫描架位标签");
            return;
        }
        iPresenter.saveShelfData( shelfScanBeam );
    }

    void getShelfInfo(String shelfCode){
        iPresenter.getShelfInfoByShelfCode( shelfCode );
    }

    @Override
    public void getShelfInfoCallback(ShelfBean shelfBean) {
        transfor(shelfBean);
        tvShelfCode.setText( shelfScanBeam.getShelfCode() );
        tvShelfName.setText(shelfScanBeam.getShelfName());

        shelfBookScanAdapter.setNewData( shelfScanBeam.getBooks() );

        setSummary();
    }

    protected  void transfor(ShelfBean shelfBean){
        shelfScanBeam.getBooks().clear();
        shelfScanBeam.setId( UUID.randomUUID().toString() );
        shelfScanBeam.setUserId( BaseApplication.single.getUserBean().getUserId() );
        shelfScanBeam.setUserName( BaseApplication.single.getUserBean().getUserName() );
        shelfScanBeam.setShelfCode(shelfBean.getShelfCode());
        shelfScanBeam.setDescription("");
        shelfScanBeam.setScanDatetime(new Date());
        shelfScanBeam.setShelfName( shelfBean.getShelfName());

        if(shelfBean.getBooks()!=null){
            for( BookBean item : shelfBean.getBooks()){
                ShelfBookScanBean bookScanBean = new ShelfBookScanBean();
                bookScanBean.transfor(item);
                shelfScanBeam.getBooks().add(bookScanBean);
            }
        }

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

    @Override
    public void saveCallback() {
        ToastUtils.showLongToast("保存成功");
    }
}
