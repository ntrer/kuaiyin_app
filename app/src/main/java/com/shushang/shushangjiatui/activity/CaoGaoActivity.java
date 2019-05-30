package com.shushang.shushangjiatui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shushang.shushangjiatui.LoginActivity;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.adapter.GenJinJiLuAdapter;
import com.shushang.shushangjiatui.base.BaseActivity2;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.bean.GenJInLieBiao;
import com.shushang.shushangjiatui.bean.TianJIaHaoYou;
import com.shushang.shushangjiatui.ui.ActionSheetDialog;
import com.shushang.shushangjiatui.ui.ExtAlertDialog;
import com.shushang.shushangjiatui.utils.Json.JSONUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.CallBackUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.OkhttpUtil;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class CaoGaoActivity extends BaseActivity2 implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_recycleview)
    RecyclerView mRvRecycleview;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.ll_no_data)
    LinearLayout mLlNoData;
    @BindView(R.id.shanchu)
    ImageView mShanchu;
    private String token_id, id, openId;
    private List<GenJInLieBiao.DataBean> data = new ArrayList<>();
    private GenJinJiLuAdapter mGenJinJiLuAdapter;
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
        token_id = PreferencesUtils.getString(CaoGaoActivity.this, "token_id");
        id = PreferencesUtils.getString(CaoGaoActivity.this, "UserId");
        openId = PreferencesUtils.getString(CaoGaoActivity.this, "openId");
        mSrlRefresh.setOnRefreshListener(this);
        getData();
    }

    private void getData() {
        mSrlRefresh.setRefreshing(true);
        String url = BaseUrl.BASE_URL + "user/followup/list?token_id=" + token_id + "&loginOS=1&draftRecord=1";
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
                        GenJInLieBiao yiXiangJin = JSONUtil.fromJson(response, GenJInLieBiao.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            data = yiXiangJin.getData();
                            if (data.size() != 0) {
                                shouData(data);
                                mLlNoData.setVisibility(View.GONE);
                            } else {
                                shouData(data);
                                mLlNoData.setVisibility(View.VISIBLE);
                            }
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(CaoGaoActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });
    }

    private void shouData(final List<GenJInLieBiao.DataBean> data) {
        try {
            mGenJinJiLuAdapter = new GenJinJiLuAdapter(R.layout.item_genjinjilu, data);
            final LinearLayoutManager manager = new LinearLayoutManager(CaoGaoActivity.this);
            mRvRecycleview.setLayoutManager(manager);
            mRvRecycleview.setAdapter(mGenJinJiLuAdapter);
            mGenJinJiLuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                    new ActionSheetDialog(CaoGaoActivity.this)
                            .builder()
                            .setCancelable(false)
                            .setCanceledOnTouchOutside(true)
                            .addSheetItem("删除", ActionSheetDialog.SheetItemColor.Blue,
                                    new ActionSheetDialog.OnSheetItemClickListener() {
                                        @Override
                                        public void onClick(int which) {
                                            ExtAlertDialog.showSureDlg(CaoGaoActivity.this, "提醒", "确定删除该草稿吗", "确定", new ExtAlertDialog.IExtDlgClick() {
                                                @Override
                                                public void Oclick(int result) {
                                                    if (result == 1) {
                                                        shanchu(data.get(position).getId());
                                                    }

                                                }
                                            });
                                        }
                                    })
                            .addSheetItem("提交", ActionSheetDialog.SheetItemColor.Blue,
                                    new ActionSheetDialog.OnSheetItemClickListener() {
                                        @Override
                                        public void onClick(int which) {
                                          reSubmit(data.get(position).getId(),data.get(position).getCustomerId(),data.get(position).getOpenId());
                                        }
                                    })
                            .show();
                }
            });
        } catch (Exception e) {
            ToastUtils.showLong(e.toString());
        }
    }

    private void reSubmit(int id, int customerId, String openId) {
        mRequestDialog.show();
        String url = BaseUrl.BASE_URL + "user/followup/submit?token_id=" + token_id + "&loginOS=1&customerId="+customerId+"&id="+id+"&openId="+openId+"&draftRecord=0";
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
                            getData();
                            EventBus.getDefault().post(new MessageEvent("草稿"));
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(CaoGaoActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        else if(yiXiangJin.getMsg().equals("202")){
                            PreferencesUtils.putString(CaoGaoActivity.this, "token_id", null);
                            EventBus.getDefault().post(new MessageEvent("退出"));
                            startActivity(new Intent(CaoGaoActivity.this, LoginActivity.class));
                            finish();
                            Toast.makeText(CaoGaoActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
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


    @OnClick(R.id.shanchu)
    void delete() {
        ExtAlertDialog.showSureDlg(CaoGaoActivity.this, "提醒", "确定删除所有草稿吗", "确定", new ExtAlertDialog.IExtDlgClick() {
            @Override
            public void Oclick(int result) {
                if (result == 1) {
                    shanchu(-10);
                }

            }
        });
    }


    private void shanchu(int id) {
        mSrlRefresh.setRefreshing(true);
        String url = BaseUrl.BASE_URL + "user/followup/del?token_id=" + token_id + "&loginOS=1";
        Log.d("创建活动", url);
        HashMap<String, String> paramsMap = new HashMap<>();
        if(id!=-10){
            paramsMap.put("id",id+"");
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
                        TianJIaHaoYou yiXiangJin = JSONUtil.fromJson(response, TianJIaHaoYou.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            getData();
                            EventBus.getDefault().post(new MessageEvent("草稿"));
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(CaoGaoActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        else if(yiXiangJin.getMsg().equals("202")){
                            PreferencesUtils.putString(CaoGaoActivity.this, "token_id", null);
                            EventBus.getDefault().post(new MessageEvent("退出"));
                            startActivity(new Intent(CaoGaoActivity.this, LoginActivity.class));
                            finish();
                            Toast.makeText(CaoGaoActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });
    }

    @Override
    public int setLayout() {
        return R.layout.activity_cao_gao;
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
        getData();
    }
}
