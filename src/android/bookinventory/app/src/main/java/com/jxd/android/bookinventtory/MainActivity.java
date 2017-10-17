package com.jxd.android.bookinventtory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxd.android.bookinventtory.adapter.FragmentAdapter;
import com.jxd.android.bookinventtory.base.BaseActivity;
import com.jxd.android.bookinventtory.base.BaseFragment;
import com.jxd.android.bookinventtory.bean.BookBean;
import com.jxd.android.bookinventtory.bean.BookCondition;
import com.jxd.android.bookinventtory.bean.LogoutEvent;
import com.jxd.android.bookinventtory.bean.SearchKeyBean;
import com.jxd.android.bookinventtory.bean.ShelfBean;
import com.jxd.android.bookinventtory.booksearch.BookSearchFragment;
import com.jxd.android.bookinventtory.config.Constants;
import com.jxd.android.bookinventtory.login.LoginActivity;
import com.jxd.android.bookinventtory.search.SearchActivity;
import com.jxd.android.bookinventtory.shelfadapt.ShelfAdaptFragment;
import com.jxd.android.bookinventtory.shelfarrage.ShelfArrageFragment;
import com.jxd.android.bookinventtory.shelfsearch.ShelfSearchFragment;
import com.jxd.android.bookinventtory.utils.PreferenceHelper;
import com.jxd.android.bookinventtory.utils.ToastUtils;
import com.jxd.android.bookinventtory.widgets.TipAlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.fragment;

/**
 *
 *@author  jinxiangdong
 * @date 2017-10-16
 */
public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener{
    public static int REQUEST_CODE_SEARCH=1000;

    private List<BaseFragment> fragmentList = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;
    private Hashtable<Integer,Integer> menuList=new Hashtable<>();

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;
    long exitTime = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_booksearch:
                    viewPager.setCurrentItem(0,true);
                    return true;
                case R.id.navigation_shelfsearch:
                    viewPager.setCurrentItem(1,true);
                    return true;
                case R.id.navigation_shelfadapt:
                    viewPager.setCurrentItem(2,true);
                    return true;
                case R.id.navigation_shelfarrage:
                    viewPager.setCurrentItem(3,true);
                    return true;
                case R.id.navigation_differencemanage:
                    //mTextMessage.setText(R.string.title_differencemanage);
                    return true;
                default:
                    return false;
            }
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

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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

    @Subscribe( threadMode= ThreadMode.MAIN)
    public void  onLogoutEvent(LogoutEvent logoutEvent){
        TipAlertDialog tipAlertDialog = new TipAlertDialog(this , false);
        tipAlertDialog.show("询问", "您确定要退出吗?", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceHelper.remove(MainActivity.this , Constants.PREF_FILENAME , Constants.PREF_COOKIE);
                PreferenceHelper.remove(MainActivity.this , Constants.PREF_FILENAME , Constants.PREF_USER);
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 2秒以内按两次推出程序
        if (keyCode == KeyEvent.KEYCODE_BACK  && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtils.showLongToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
