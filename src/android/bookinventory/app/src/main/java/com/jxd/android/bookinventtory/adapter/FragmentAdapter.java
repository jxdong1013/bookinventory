package com.jxd.android.bookinventtory.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jxd.android.bookinventtory.base.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/9/28.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
    List<BaseFragment> fragmentList;

    public FragmentAdapter(FragmentManager fm , List<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
