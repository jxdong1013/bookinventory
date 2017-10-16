package com.jxd.android.bookinventtory.shelfarrage;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 *
 * A simple {@link Fragment} subclass.
 * Use the {@link ShelfArrageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShelfArrageFragment
        extends BaseFragment<IShelfArragePresenter>
        implements IShelfArrageView{

    @BindView(R.id.right)
    TextView tvRight;
    @BindView(R.id.header)
    TextView tvTitle;
    @BindView(R.id.tvUserName)
    TextView tvUserName;


    public ShelfArrageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShelfArrageFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        tvTitle.setText("");
        Drawable leftDrawable = ContextCompat.getDrawable( getContext() , R.mipmap.ic_launcher );
        tvRight.setCompoundDrawables( leftDrawable ,null,null,null);
        tvRight.setText("架位整理");
        tvUserName.setText( application.getUserBean()==null?"":application.getUserBean().getUserName() );

    }

    @Override
    public int getNavigateMenuId() {
        return R.id.navigation_shelfarrage;
    }

    @Override
    protected void fetchData() {

    }

    @OnClick({R.id.right})
    public void onClick(View v){
        if(v.getId()==R.id.right){
            Intent intent =new Intent(getContext(),ShelfArrageUIActivity.class);
            getContext().startActivity(intent);
        }
    }





}
