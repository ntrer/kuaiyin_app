package com.shushang.shushangjiatui.fragment.ThirdFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.activity.KeHuDetailActivity;
import com.shushang.shushangjiatui.activity.TianJiaGenJinActivity;
import com.shushang.shushangjiatui.adapter.KeHuAdapter;
import com.shushang.shushangjiatui.base.BaseFragment;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.bean.KeHu;
import com.shushang.shushangjiatui.bean.TianJIaHaoYou;
import com.shushang.shushangjiatui.ui.ExtAlertDialog;
import com.shushang.shushangjiatui.utils.Json.JSONUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.CallBackUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.OkhttpUtil;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

public class ThirdFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.status_bar_fix)
    View mStatusBarFix;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_recycleview)
    RecyclerView mRvRecycleview;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.ll_no_data)
    LinearLayout mLlNoData;
    Unbinder unbinder1;
    private KeHuAdapter mKeHuAdapter;
    private List<String> data = new ArrayList<>();
    private List<KeHu.DataBean.CustomerListBean> customerList = new ArrayList<>();
    private int page = 1;
    private String token_id;
    Unbinder unbinder;
    private Dialog mRequestDialog;
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        initStateBar();
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        mSrlRefresh.setOnRefreshListener(this);
        mRequestDialog = ExtAlertDialog.creatRequestDialog(getActivity(),"请求中...");
        mRequestDialog.setCancelable(false);
        getData();
        EventBus.getDefault().register(this);
    }


    private void getData() {
        mSrlRefresh.setRefreshing(true);
        String url = BaseUrl.BASE_URL + "user/customer/list?token_id=" + token_id + "&loginOS=1&page=1&rows=10";
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
                        KeHu yiXiangJin = JSONUtil.fromJson(response, KeHu.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            customerList = yiXiangJin.getData().getCustomerList();
                            if (customerList.size() != 0) {
                                shouData(customerList);
                                mLlNoData.setVisibility(View.GONE);
                            } else {
                                shouData(customerList);
                                mLlNoData.setVisibility(View.VISIBLE);
                            }
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(getActivity(), "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });
    }

    private void shouData(final List<KeHu.DataBean.CustomerListBean> data) {
        try {
            PreferencesUtils.putInt(getActivity(), "id", 0);
            PreferencesUtils.putString(getActivity(), "openId", "");
            mKeHuAdapter = new KeHuAdapter(R.layout.item_kehu, data);
            final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mKeHuAdapter.setOnLoadMoreListener(this, mRvRecycleview);
            mRvRecycleview.setLayoutManager(manager);
            mRvRecycleview.setAdapter(mKeHuAdapter);
            mKeHuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(getContext(), KeHuDetailActivity.class);
                    intent.putExtra("data", data.get(position));
                    PreferencesUtils.putInt(getActivity(), "kehuId", data.get(position).getId());
                    PreferencesUtils.putString(getActivity(), "KeHuopenId", data.get(position).getOpenId());
                    startActivity(intent);
                }
            });
            mRvRecycleview.addOnItemTouchListener(new OnItemChildClickListener() {
                @Override
                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                }

                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                    super.onItemChildClick(adapter, view, position);
                    int itemViewId = view.getId();
                    try {
                        if (itemViewId == R.id.tianjiagenjin) {
                            Intent intent = new Intent(getContext(), TianJiaGenJinActivity.class);
                            intent.putExtra("kehuId", data.get(position).getId() + "");
                            intent.putExtra("KeHuopenId", data.get(position).getOpenId());
                            startActivity(intent);
                        }
                        else if(itemViewId==R.id.qianyue){
                               qianyue(data.get(position).getOpenId(),data.get(position).getId());
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

    private void qianyue(String openId, int id) {
        mRequestDialog.show();
        String url = BaseUrl.BASE_URL + "user/followup/submit?token_id=" + token_id + "&loginOS=1&customerId="+id+"&openId="+openId;
        Log.d("创建活动", url);
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("contractText","签约");
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
                            ToastUtils.showLong("签约成功");

                        } else if (yiXiangJin.getRet().equals("201")) {
                            ToastUtils.showLong(yiXiangJin.getMsg());
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if (messageEvent.getMessage().equals("转意向")) {
            getData();
        }
    }


    /**
     * 设置状态栏
     */
    private void initStateBar() {
        mStatusBarFix.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStateBarHeight(getActivity())));//填充状态栏
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
        View view = View.inflate(mContext, R.layout.fragment_three, null);
        return view;
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
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        getData();
    }

    @Override
    public void onLoadMoreRequested() {
        loadMore();
    }

    private void loadMore() {
        try {
            page = page + 1;
            String url = BaseUrl.BASE_URL + "user/customer/list?token_id=" + token_id + "&loginOS=1&page=" + page + "&rows=10";
            HashMap<String, String> paramsMap = new HashMap<>();
            OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
                @Override
                public void onFailure(Call call, Exception e) {
                    Toast.makeText(getActivity(), "错误了", Toast.LENGTH_SHORT).show();
                    mKeHuAdapter.loadMoreComplete();
                    mKeHuAdapter.loadMoreEnd();
                }

                @Override
                public void onResponse(String response) {
                    Log.d("创建活动", response);
                    if (response != null) {
                        Log.d("nnnnnnn", response);
                        KeHu test = JSONUtil.fromJson(response, KeHu.class);
                        if (test.getRet().equals("200")) {
                            if (page > test.getData().getIntmaxPage()) {
                                page = 1;
                                mKeHuAdapter.loadMoreComplete();
                                mKeHuAdapter.loadMoreEnd();
                            } else if (test.getData().getCustomerList().size() != 0) {
                                List<KeHu.DataBean.CustomerListBean> userSaleInfo = test.getData().getCustomerList();
                                LoadMoreData(userSaleInfo);
                                Log.d("33333333333", response);
                                mKeHuAdapter.loadMoreComplete();
                            } else if (test.getData().getCustomerList().size() == 0) {
                                mKeHuAdapter.loadMoreComplete();
                                mKeHuAdapter.loadMoreEnd();
                            }
                        } else {
                            mKeHuAdapter.loadMoreComplete();
                            mKeHuAdapter.loadMoreEnd();
                        }
                    }

                }
            });
        } catch (Exception e) {
            ToastUtils.showLong(e.toString());
        }
    }

    private void LoadMoreData(List<KeHu.DataBean.CustomerListBean> dataList) {
        if (dataList.size() != 0) {
            mKeHuAdapter.addData(dataList);
            customerList.addAll(dataList);
            mKeHuAdapter.loadMoreComplete();
        }

    }
}
