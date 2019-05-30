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
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.adapter.NewFriendAdapter;
import com.shushang.shushangjiatui.base.BaseActivity2;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.bean.TianJIaHaoYou;
import com.shushang.shushangjiatui.bean.XinPengYou;
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
import okhttp3.Call;

public class NewFriendActivity extends BaseActivity2 implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_recycleview)
    RecyclerView mRvRecycleview;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.ll_no_data)
    LinearLayout mLlNoData;
    private NewFriendAdapter mNewFriendAdapter;
    private List<String> data = new ArrayList<>();
    private List<XinPengYou.DataBean.UserSaleInfoBean> userSaleInfo = new ArrayList<>();
    private String token_id;
    private String status;
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
        mSrlRefresh.setOnRefreshListener(this);
        token_id = PreferencesUtils.getString(NewFriendActivity.this, "token_id");
        getData();
    }


    private void getData() {
        mSrlRefresh.setRefreshing(true);
        String url = BaseUrl.BASE_URL + "user/friendreq/list?token_id=" + token_id + "&loginOS=1&page=1&rows=10";
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
                        XinPengYou yiXiangJin = JSONUtil.fromJson(response, XinPengYou.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            userSaleInfo = yiXiangJin.getData().getUserSaleInfo();
                            if (userSaleInfo.size() != 0) {
                                shouData(userSaleInfo);
                                mLlNoData.setVisibility(View.GONE);
                            } else {
                                shouData(userSaleInfo);
                                mLlNoData.setVisibility(View.VISIBLE);
                            }
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(NewFriendActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e) {
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });
    }

    private void shouData(final List<XinPengYou.DataBean.UserSaleInfoBean> data) {
        try {
            mNewFriendAdapter = new NewFriendAdapter(R.layout.item_haoyou, data);
            final LinearLayoutManager manager = new LinearLayoutManager(this);
            mRvRecycleview.setLayoutManager(manager);
            mRvRecycleview.setAdapter(mNewFriendAdapter);
            mNewFriendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                    if(data.get(position).getStatus().equals("0")){
                        new ActionSheetDialog(NewFriendActivity.this)
                                .builder()
                                .setCancelable(false)
                                .setCanceledOnTouchOutside(true)
                                .addSheetItem("同意", ActionSheetDialog.SheetItemColor.Blue,
                                        new ActionSheetDialog.OnSheetItemClickListener() {
                                            @Override
                                            public void onClick(int which) {
                                                status="1";
                                                Submit(status, data.get(position).getFriendRecordId());
                                            }
                                        })
                                .addSheetItem("拒绝", ActionSheetDialog.SheetItemColor.Blue,
                                        new ActionSheetDialog.OnSheetItemClickListener() {
                                            @Override
                                            public void onClick(int which) {
                                                status="-1";
                                                Submit(status,data.get(position).getFriendRecordId());
                                            }
                                        })
                                .show();
                    }
                }
            });
//            mRvRecycleview.addOnItemTouchListener(new OnItemChildClickListener() {
//                @Override
//                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//
//                }
//
//                @Override
//                public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
//                    super.onItemChildClick(adapter, view, position);
//                    int itemViewId = view.getId();
//                    try {
//                        if (itemViewId == R.id.chakan) {
//                            new ActionSheetDialog(NewFriendActivity.this)
//                                    .builder()
//                                    .setCancelable(false)
//                                    .setCanceledOnTouchOutside(true)
//                                    .addSheetItem("同意", ActionSheetDialog.SheetItemColor.Blue,
//                                            new ActionSheetDialog.OnSheetItemClickListener() {
//                                                @Override
//                                                public void onClick(int which) {
//                                                    status="1";
//                                                    Submit(status, data.get(position).getFriendRecordId());
//                                                }
//                                            })
//                                    .addSheetItem("拒绝", ActionSheetDialog.SheetItemColor.Blue,
//                                            new ActionSheetDialog.OnSheetItemClickListener() {
//                                                @Override
//                                                public void onClick(int which) {
//                                                    status="-1";
//                                                    Submit(status,data.get(position).getFriendRecordId());
//                                                }
//                                            })
//                                    .show();
//                        }
//                    } catch (Exception e) {
//                        ToastUtils.showLong(e.toString());
//                    }
//                }
//            });

        } catch (Exception e) {
            ToastUtils.showLong(e.toString());
        }
    }

    private void Submit(final String status, String friendRecordId) {
        mRequestDialog.show();
        String url = BaseUrl.BASE_URL + " user/friendreq/reply?token_id=" + token_id + "&loginOS=1&status="+status+"&friendRecordId="+friendRecordId;
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
                            if(status.equals("1")){
                                ToastUtils.showLong("已通过");
                                EventBus.getDefault().post(new MessageEvent("通过"));
                                getData();
                            }
                            else {
                                ToastUtils.showLong("已拒绝");
                                getData();
                            }
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(NewFriendActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
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
        return R.layout.activity_new_friend;
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
