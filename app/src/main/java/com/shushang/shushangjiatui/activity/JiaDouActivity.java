package com.shushang.shushangjiatui.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.adapter.JiaDouQieHuanAdapter;
import com.shushang.shushangjiatui.base.BaseActivity2;
import com.shushang.shushangjiatui.fragment.JiaDouFragment.FenXiangGeiWoDdFragment;
import com.shushang.shushangjiatui.fragment.JiaDouFragment.WoFenXiangDeFragment;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JiaDouActivity extends BaseActivity2 {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.sliding_tabs)
    TabLayout mSlidingTabs;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSlidingTabs.setupWithViewPager(mViewpager);
        mSlidingTabs.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mSlidingTabs, 60, 60);
            }
        });
        SetUpViewPager(mViewpager);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_jia_dou;
    }

    @Override
    public void init() {

    }



    private void SetUpViewPager(ViewPager bookViewpager) {
        JiaDouQieHuanAdapter adapter = new JiaDouQieHuanAdapter(getSupportFragmentManager());
        adapter.addFragment(WoFenXiangDeFragment.newInstance(), "我分享的");
        adapter.addFragment(FenXiangGeiWoDdFragment.newInstance(), "分给我的");
        bookViewpager.setAdapter(adapter);
        bookViewpager.setCurrentItem(0, true);
        bookViewpager.setOffscreenPageLimit(2);
    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);

            child.invalidate();
        }

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 退出activity的动画效果不起作用，要在java代码里写
     * 复写activity的finish方法，在overridePendingTransition中写入自己的动画效果
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }
}
