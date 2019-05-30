package com.shushang.shushangjiatui.fragment.SecondFragment;

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
import com.shushang.shushangjiatui.adapter.LeiDaYeAdapter;
import com.shushang.shushangjiatui.base.BaseFragment;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.fragment.SecondFragment.ChildFragment.LeiDaFragment;
import com.shushang.shushangjiatui.fragment.SecondFragment.ChildFragment.XianSuoFragment;
import com.shushang.shushangjiatui.ui.segmentView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SecondFragment extends BaseFragment {
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
    @BindView(R.id.shaixun)
    LinearLayout mShaixun;
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        initStateBar();
        SetUpViewPager(mViewpager);
        mSegmentView.setSegmentText("雷达", 0);
        mSegmentView.setSegmentText("线索", 1);
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
                    mShaixun.setVisibility(View.GONE);
                } else {
                    mSegmentView.setSelectTextColor(1);
                    mShaixun.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void SetUpViewPager(ViewPager bookViewpager) {
        LeiDaYeAdapter adapter = new LeiDaYeAdapter(getChildFragmentManager());
        adapter.addFragment(LeiDaFragment.newInstance(), "");
        adapter.addFragment(XianSuoFragment.newInstance(), "");
        bookViewpager.setAdapter(adapter);
        bookViewpager.setCurrentItem(0, true);
        bookViewpager.setOffscreenPageLimit(2);
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


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_two, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @OnClick(R.id.shaixun)
    void shaixuan(){
        EventBus.getDefault().post(new MessageEvent("筛选"));
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
