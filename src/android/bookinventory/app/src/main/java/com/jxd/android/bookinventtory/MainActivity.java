package com.jxd.android.bookinventtory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxd.android.bookinventtory.adapter.FragmentAdapter;
import com.jxd.android.bookinventtory.base.BaseActivity;
import com.jxd.android.bookinventtory.base.BaseFragment;
import com.jxd.android.bookinventtory.booksearch.BookSearchFragment;
import com.jxd.android.bookinventtory.shelfadapt.ShelfAdaptFragment;
import com.jxd.android.bookinventtory.shelfarrage.ShelfArrageFragment;
import com.jxd.android.bookinventtory.shelfsearch.ShelfSearchFragment;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.fragment;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener{
    public static int REQUEST_CODE_SEARCH=1000;

    private List<BaseFragment> fragmentList = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;
    private Hashtable<Integer,Integer> menuList=new Hashtable<>();

    //@BindView(R.id.message)
    //TextView mTextMessage;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.laySearchbar)
    LinearLayout laySearchbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_booksearch:
                    //mTextMessage.setText(R.string.title_booksearch);
                    viewPager.setCurrentItem(0,true);
                    return true;
                case R.id.navigation_shelfsearch:
                    //mTextMessage.setText(R.string.title_shelfsearch);
                    viewPager.setCurrentItem(1,true);
                    return true;
                case R.id.navigation_shelfadapt:
                    //mTextMessage.setText(R.string.title_shelfadapt);
                    viewPager.setCurrentItem(2,true);
                    return true;
                case R.id.navigation_shelfarrage:
                    //mTextMessage.setText(R.string.title_shelfarrage);
                    return true;
                case R.id.navigation_differencemanage:
                    //mTextMessage.setText(R.string.title_differencemanage);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
        //mTextMessage = (TextView) findViewById(R.id.message);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentList.add(BookSearchFragment.newInstance());
        fragmentList.add(ShelfSearchFragment.newInstance());
        fragmentList.add(ShelfAdaptFragment.newInstance());
        fragmentList.add(ShelfArrageFragment.newInstance());
        fragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(this);

        for (int i = 0; i < fragmentList.size(); i++) {
            BaseFragment fragment = fragmentList.get(i);
            menuList.put(i, fragment.getNavigateMenuId());
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigationView.setSelectedItemId( menuList.get(position) );
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @OnClick({R.id.laySearchbar})
    public void onClick(View view){
        if(view.getId() == R.id.laySearchbar){
            Intent intent = new Intent(MainActivity.this,SearchActivity.class);
            this.startActivityForResult(intent , REQUEST_CODE_SEARCH);
        }
    }

}
