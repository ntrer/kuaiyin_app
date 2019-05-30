package com.shushang.shushangjiatui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.shushang.shushangjiatui.base.BaseViewPagerAdapter;

public class JiaDouQieHuanAdapter extends BaseViewPagerAdapter {

    public JiaDouQieHuanAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }
}
