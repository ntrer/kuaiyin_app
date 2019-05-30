package com.shushang.shushangjiatui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.adapter.SouSuoAdapter;
import com.shushang.shushangjiatui.base.BaseActivity2;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.bean.SouSuoJieGuo;
import com.shushang.shushangjiatui.bean.TianJIaHaoYou;
import com.shushang.shushangjiatui.ui.ExtAlertDialog;
import com.shushang.shushangjiatui.utils.Json.JSONUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.CallBackUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.OkhttpUtil;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class SouSuoJIeGuoActivity extends BaseActivity2 {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_recycleview)
    RecyclerView mRvRecycleview;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.ll_no_data)
    LinearLayout mLlNoData;
    private SouSuoAdapter mSouSuoAdapter;
    private List<String> data = new ArrayList<>();
    private String number;
    private String token_id;
    private List<SouSuoJieGuo.DataBean.MerchantUserBean> merchantUser = new ArrayList<>();
    private Dialog mRequestDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRequestDialog = ExtAlertDialog.creatRequestDialog(this,"请求中...");
        mRequestDialog.setCancelable(false);
        number = getIntent().getStringExtra("number");
        token_id = PreferencesUtils.getString(SouSuoJIeGuoActivity.this, "token_id");
        getData(number);
    }


    private void getData(String number) {
        merchantUser.clear();
        mSrlRefresh.setRefreshing(true);
        String url = BaseUrl.BASE_URL + "user/search/friend?token_id=" + token_id + "&loginOS=1&mobileNumber="+ number;
        Log.d("创建活动", url);
        HashMap<String, String> paramsMap = new HashMap<>();
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
                        SouSuoJieGuo yiXiangJin = JSONUtil.fromJson(response, SouSuoJieGuo.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            if(yiXiangJin.getData()!=null){
                                merchantUser.add(yiXiangJin.getData().getMerchantUser());
                                Log.d("创建活动",merchantUser.size()+"");
                                if (merchantUser.size() > 0) {
                                    shouData(merchantUser);
                                    mLlNoData.setVisibility(View.GONE);
                                } else {
                                    shouData(merchantUser);
                                    mLlNoData.setVisibility(View.VISIBLE);
                                }
                            }
                            else {
                                shouData(merchantUser);
                                mLlNoData.setVisibility(View.VISIBLE);
                            }
                        } else if (yiXiangJin.getRet().equals("201")) {
                            mLlNoData.setVisibility(View.VISIBLE);
                            Toast.makeText(SouSuoJIeGuoActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });
    }

    private void shouData(final List<SouSuoJieGuo.DataBean.MerchantUserBean> data) {
        try {
            mSouSuoAdapter = new SouSuoAdapter(R.layout.item_haoyousousuo, data);
            final LinearLayoutManager manager = new LinearLayoutManager(this);
            mRvRecycleview.setLayoutManager(manager);
            mRvRecycleview.setAdapter(mSouSuoAdapter);
            mRvRecycleview.addOnItemTouchListener(new OnItemChildClickListener() {
                @Override
                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                }

                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                    super.onItemChildClick(adapter, view, position);
                    int itemViewId = view.getId();
                    try {
                        if (itemViewId == R.id.jiahaoyou) {
                            tianjiahaoyou(data.get(position).getId());
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

    private void tianjiahaoyou(int id) {
        mRequestDialog.show();
        String url = BaseUrl.BASE_URL + "user/friend/req?token_id=" + token_id + "&loginOS=1&merchantUserId="+id;
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
                            ToastUtils.showLong(yiXiangJin.getMsg());
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(SouSuoJIeGuoActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        else if (yiXiangJin.getRet().equals("202")) {
                            Toast.makeText(SouSuoJIeGuoActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
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


    @Override
    public int setLayout() {
        return R.layout.activity_sou_suo_jie_guo;
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
}
