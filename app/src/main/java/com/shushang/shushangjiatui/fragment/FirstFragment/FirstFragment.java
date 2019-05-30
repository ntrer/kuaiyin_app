package com.shushang.shushangjiatui.fragment.FirstFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.adapter.BaoBiaoAdapter;
import com.shushang.shushangjiatui.base.BaseFragment;
import com.shushang.shushangjiatui.fragment.FirstFragment.ChildFragment.ShuJuTongJiFragment;
import com.shushang.shushangjiatui.fragment.FirstFragment.ChildFragment.XiaoShouPaiHangFragment;
import com.shushang.shushangjiatui.ui.segmentView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FirstFragment extends BaseFragment {
    @BindView(R.id.segmentView)
    segmentView mSegmentView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    Unbinder unbinder;
    Unbinder unbinder1;
    @BindView(R.id.status_bar_fix)
    View mStatusBarFix;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        initStateBar();
        SetUpViewPager(mViewpager);
        mSegmentView.setOnSegmentViewClickListener(new segmentView.onSegmentViewClickListener() {
            @Override
            public void onSegmentViewClick(View view, int position) {
                if (position == 0) {
                    mViewpager.setCurrentItem(0, true);
                } else if (position == 1) {
                    mViewpager.setCurrentItem(1, true);
                }
            }
        });

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mSegmentView.setSelectTextColor(0);
                } else {
                    mSegmentView.setSelectTextColor(1);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_one, null);
        return view;
    }

    /**
     * 设置状态栏
     */
    private void initStateBar() {
        mStatusBarFix.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStateBarHeight(getActivity())));//填充状态栏
    }


    /**
     * 得到状态栏高度
     *
     * @param a
     * @return
     */
    public static int getStateBarHeight(Activity a) {
        int result = 0;
        int resourceId = a.getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = a.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    private void SetUpViewPager(ViewPager bookViewpager) {
        BaoBiaoAdapter adapter = new BaoBiaoAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(ShuJuTongJiFragment.newInstance(), "");
        adapter.addFragment(XiaoShouPaiHangFragment.newInstance(), "");
        bookViewpager.setAdapter(adapter);
        bookViewpager.setCurrentItem(0, true);
        bookViewpager.setOffscreenPageLimit(2);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
