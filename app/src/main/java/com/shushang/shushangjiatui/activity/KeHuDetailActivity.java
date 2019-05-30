package com.shushang.shushangjiatui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.adapter.JiaDouQieHuanAdapter;
import com.shushang.shushangjiatui.base.BaseActivity2;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.bean.KeHu;
import com.shushang.shushangjiatui.fragment.KehuXiangQingFragment.GenJinJiLuFragment;
import com.shushang.shushangjiatui.fragment.KehuXiangQingFragment.KeHuXinXiFragment;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class KeHuDetailActivity extends BaseActivity2 {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.shenfen)
    TextView mShenfen;
    @BindView(R.id.firstline)
    LinearLayout mFirstline;
    @BindView(R.id.laizi)
    TextView mLaizi;
    @BindView(R.id.secondline)
    LinearLayout mSecondline;
    @BindView(R.id.shijian)
    TextView mShijian;
    @BindView(R.id.thirdline)
    LinearLayout mThirdline;
    @BindView(R.id.header)
    RelativeLayout mHeader;
    @BindView(R.id.sliding_tabs)
    TabLayout mSlidingTabs;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.content)
    RelativeLayout mContent;
    @BindView(R.id.bianji)
    Button mBianji;
    private  KeHu.DataBean.CustomerListBean mCustomerListBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCustomerListBean = (KeHu.DataBean.CustomerListBean) getIntent().getSerializableExtra("data");
        try {
            String url=BaseUrl.BASE_URL+ "image/show/?token_id="+PreferencesUtils.getString(this,"token_id")+"&fileId="+mCustomerListBean.getLocalHeadPortraitImage()+"&loginOS=1&imgType=1";
            Picasso.with(this).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(mAvatar);
            mName.setText(mCustomerListBean.getUserName());
            mShenfen.setText(mCustomerListBean.getMobileNumber());
            mLaizi.setText(mCustomerListBean.getSourceDesc());
            mShijian.setText(mCustomerListBean.getLastVisitTime());
        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }
        mSlidingTabs.setupWithViewPager(mViewpager);
        mSlidingTabs.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mSlidingTabs, 60, 60);
            }
        });
        SetUpViewPager(mViewpager);
        EventBus.getDefault().register(this);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_ke_hu_detail;
    }

    @Override
    public void init() {

    }



    private void SetUpViewPager(ViewPager bookViewpager) {
        JiaDouQieHuanAdapter adapter = new JiaDouQieHuanAdapter(getSupportFragmentManager());
        adapter.addFragment(KeHuXinXiFragment.newInstance(), "客户信息");
        adapter.addFragment(GenJinJiLuFragment.newInstance(), "跟进记录");
        bookViewpager.setAdapter(adapter);
        bookViewpager.setCurrentItem(0, true);
        bookViewpager.setOffscreenPageLimit(2);
    }



    @OnClick(R.id.bianji)
    void bianji() {
        startActivity(new Intent(KeHuDetailActivity.this,EditKehuXinXiActivity.class));
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if(messageEvent.getMessage().equals("退出客户")){
            EventBus.getDefault().post(new MessageEvent("退出"));
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
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
