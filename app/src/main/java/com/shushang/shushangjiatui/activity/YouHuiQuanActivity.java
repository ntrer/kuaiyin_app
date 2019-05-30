package com.shushang.shushangjiatui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.shushang.shushangjiatui.LoginActivity;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.adapter.YouHuiQuanAdapter;
import com.shushang.shushangjiatui.base.BaseActivity2;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.bean.TianJIaHaoYou;
import com.shushang.shushangjiatui.bean.YouHuiQuan;
import com.shushang.shushangjiatui.ui.ExtAlertDialog;
import com.shushang.shushangjiatui.utils.Json.JSONUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.CallBackUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.OkhttpUtil;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class YouHuiQuanActivity extends BaseActivity2 implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener  {

    private static final int SCAN_CODE = 1006;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.search)
    TextView mSearch;
    @BindView(R.id.txt_shanghu)
    AppCompatEditText mTxtShanghu;
    @BindView(R.id.search_input)
    RelativeLayout mSearchInput;
    @BindView(R.id.rv_recycleview)
    RecyclerView mRvRecycleview;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.scan)
    ImageView mScan;
    @BindView(R.id.ll_no_data)
    LinearLayout mLlNoData;
    private YouHuiQuanAdapter mYouHuiQuanAdapter;
    private List<String> data = new ArrayList<>();
    private List<YouHuiQuan.DataBean.CouponListBean> couponList = new ArrayList<>();
    private String token_id;
    private int page = 1;
    private String customerCouponCode;
    private Dialog mRequestDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRequestDialog = ExtAlertDialog.creatRequestDialog(this, "请求中...");
        mRequestDialog.setCancelable(false);
        token_id = PreferencesUtils.getString(YouHuiQuanActivity.this, "token_id");
        mSrlRefresh.setOnRefreshListener(this);
        getData(customerCouponCode);
    }


    private void getData(String customerCouponCode) {
        mSrlRefresh.setRefreshing(true);
        String url = BaseUrl.BASE_URL + "user/couponList?token_id=" + token_id + "&loginOS=1&page=1&rows=10";
        Log.d("创建活动", url);
        HashMap<String, String> paramsMap = new HashMap<>();
        if(customerCouponCode!=null&&!customerCouponCode.equals("")){
            paramsMap.put("isSearch","1");
            paramsMap.put("customerCouponCode",customerCouponCode);
        }
        OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                mSrlRefresh.setRefreshing(false);
                ToastUtils.showLong(e.toString());
            }

            @Override
            public void onResponse(String response) {
                Log.d("创建活动", response);
                mSrlRefresh.setRefreshing(false);
                if (response != null) {
                    try {
                        YouHuiQuan yiXiangJin = JSONUtil.fromJson(response, YouHuiQuan.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            couponList = yiXiangJin.getData().getCouponList();
                            if (couponList.size() != 0) {
                                shouData(couponList);
                                mLlNoData.setVisibility(View.GONE);
                            } else {
                                shouData(couponList);
                                mLlNoData.setVisibility(View.VISIBLE);
                            }
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(YouHuiQuanActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });
    }

    private void shouData(final List<YouHuiQuan.DataBean.CouponListBean> data) {
        try {
            mYouHuiQuanAdapter = new YouHuiQuanAdapter(R.layout.item_youhuiquan, data);
            final LinearLayoutManager manager = new LinearLayoutManager(this);
            mYouHuiQuanAdapter.setOnLoadMoreListener(this, mRvRecycleview);
            mRvRecycleview.setLayoutManager(manager);
            mRvRecycleview.setAdapter(mYouHuiQuanAdapter);
            mRvRecycleview.addOnItemTouchListener(new OnItemChildClickListener() {
                @Override
                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                }

                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                    super.onItemChildClick(adapter, view, position);
                    int itemViewId = view.getId();
                    try {
                        if (itemViewId == R.id.weishiyong) {
                             DuiHuan(data.get(position).getCustomerCouponCode());
                        }
                    } catch (Exception e) {
                        ToastUtils.showLong(e.toString());
                    }
                }
            });

        } catch (Exception e) {
            ToastUtils.showLong(e.toString());
        }
    }

    private void DuiHuan(String customerCouponCode) {
        mRequestDialog.show();
        String url = BaseUrl.BASE_URL + "user/exchangeCoupon?token_id=" + token_id + "&loginOS=1&customerCouponCode="+customerCouponCode;
        Log.d("创建活动", url);
        HashMap<String, String> paramsMap = new HashMap<>();
        OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                if (mRequestDialog != null && mRequestDialog.isShowing()) {
                    mRequestDialog.dismiss();
                }
                ToastUtils.showLong(e.toString());
            }

            @Override
            public void onResponse(String response) {
                Log.d("创建活动", response);
                if (mRequestDialog != null && mRequestDialog.isShowing()) {
                    mRequestDialog.dismiss();
                }
                if (response != null) {
                    try {
                        TianJIaHaoYou yiXiangJin = JSONUtil.fromJson(response, TianJIaHaoYou.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            ToastUtils.showLong("使用成功");
                            getData("");
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(YouHuiQuanActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        } else if (yiXiangJin.getRet().equals("202")) {
                            PreferencesUtils.putString(YouHuiQuanActivity.this, "token_id", null);
                            EventBus.getDefault().post(new MessageEvent("退出客户"));
                            startActivity(new Intent(YouHuiQuanActivity.this, LoginActivity.class));
                            finish();
                            Toast.makeText(YouHuiQuanActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        if (mRequestDialog != null && mRequestDialog.isShowing()) {
                            mRequestDialog.dismiss();
                        }
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });
    }


    @OnClick(R.id.scan)
    void scan() {
        startActivityForResult(new Intent(YouHuiQuanActivity.this, CaptureActivity.class),SCAN_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        customerCouponCode=PreferencesUtils.getString(YouHuiQuanActivity.this,"code");
        if(customerCouponCode!=null&&!customerCouponCode.equals("")){
            getData(customerCouponCode);
        }
        else {
            ToastUtils.showLong("二维码无效");
        }
    }

    @OnClick(R.id.search)
    void search() {
        customerCouponCode=mTxtShanghu.getText().toString();
        if(customerCouponCode!=null&&!customerCouponCode.equals("")){
               getData(customerCouponCode);
        }
        else {
            ToastUtils.showLong("请输入搜索内容");
        }
    }

    @Override
    public int setLayout() {
        return R.layout.activity_you_hui_quan;
    }

    @Override
    public void init() {

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

    @Override
    public void onRefresh() {
        page=1;
        getData(customerCouponCode);
    }

    @Override
    public void onLoadMoreRequested() {
        loadMore();
    }

    private void loadMore() {
        try {
            page = page + 1;
            String url = BaseUrl.BASE_URL + "user/couponList?token_id=" + token_id + "&loginOS=1&page="+page+"&rows=10";
            HashMap<String, String> paramsMap = new HashMap<>();
            OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
                @Override
                public void onFailure(Call call, Exception e) {
                    Toast.makeText(YouHuiQuanActivity.this, "错误了", Toast.LENGTH_SHORT).show();
                    mYouHuiQuanAdapter.loadMoreComplete();
                    mYouHuiQuanAdapter.loadMoreEnd();
                }

                @Override
                public void onResponse(String response) {
                    Log.d("创建活动", response);
                    if (response != null) {
                        Log.d("nnnnnnn", response);
                        YouHuiQuan test = JSONUtil.fromJson(response, YouHuiQuan.class);
                        if (test.getRet().equals("200")) {
                            if (page > test.getData().getIntmaxPage()) {
                                page = 1;
                                mYouHuiQuanAdapter.loadMoreComplete();
                                mYouHuiQuanAdapter.loadMoreEnd();
                            } else if (test.getData().getCouponList().size() != 0) {
                                List<YouHuiQuan.DataBean.CouponListBean> userSaleInfo = test.getData().getCouponList();
                                LoadMoreData(userSaleInfo);
                                Log.d("33333333333", response);
                                mYouHuiQuanAdapter.loadMoreComplete();
                            } else if (test.getData().getCouponList().size() == 0) {
                                mYouHuiQuanAdapter.loadMoreComplete();
                                mYouHuiQuanAdapter.loadMoreEnd();
                            }
                        } else {
                            mYouHuiQuanAdapter.loadMoreComplete();
                            mYouHuiQuanAdapter.loadMoreEnd();
                        }
                    }

                }
            });
        } catch (Exception e) {
            ToastUtils.showLong(e.toString());
        }
    }

    private void LoadMoreData(List<YouHuiQuan.DataBean.CouponListBean> dataList) {
        if (dataList.size() != 0) {
            mYouHuiQuanAdapter.addData(dataList);
            couponList.addAll(dataList);
            mYouHuiQuanAdapter.loadMoreComplete();
        }

    }
}
