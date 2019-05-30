package com.shushang.shushangjiatui.fragment.FirstFragment.ChildFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.adapter.TabRecyclerViewAdapter2;
import com.shushang.shushangjiatui.adapter.XiaoShouPaiHangAdapter;
import com.shushang.shushangjiatui.base.BaseFragment;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.bean.XiaoShouPaiHang;
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
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

public class XiaoShouPaiHangFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


//    @BindView(R.id.rl_tab)
//    RecyclerView mRlTab;
    @BindView(R.id.TabLayout)
    LinearLayout mTabLayout;
    @BindView(R.id.rv_recycleview)
    RecyclerView mRvRecycleview;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.ll_no_data)
    LinearLayout mLlNoData;
    Unbinder unbinder;
    @BindView(R.id.zonghezhi)
    TextView mZonghezhi;
    @BindView(R.id.kehushu)
    TextView mKehushu;
    @BindView(R.id.xinzengkehushu)
    TextView mXinzengkehushu;
    @BindView(R.id.genjinkehushu)
    TextView mGenjinkehushu;
    private List<String> tab = new ArrayList<>();
    private List<XiaoShouPaiHang.DataBean.UserSaleInfoBean> userSaleInfo = new ArrayList<>();
    private TabRecyclerViewAdapter2 mTabRecyclerViewAdapter;
    private XiaoShouPaiHangAdapter mXiaoShouPaiHangAdapter;
    private int page = 1;
    private String token_id,merchantUserId;
    private String sortId = "1";

    public XiaoShouPaiHangFragment() {
        // Required empty public constructor
    }

    public static XiaoShouPaiHangFragment newInstance() {
        return new XiaoShouPaiHangFragment();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        merchantUserId = String.valueOf(PreferencesUtils.getString(getActivity(), "UserId"));
        mZonghezhi.setActivated(true);
        mSrlRefresh.setOnRefreshListener(this);
//        getTab();
        getData();
        EventBus.getDefault().register(this);
    }


    private void getData() {
        mSrlRefresh.setRefreshing(true);
        String url = BaseUrl.BASE_URL + "user/sale/list?token_id=" + token_id + "&loginOS=1&page=1&rows=10&merchantUserId=" + merchantUserId;
        Log.d("创建活动", url);
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("sortId", sortId);
        Log.d("sortId", sortId);
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
                        XiaoShouPaiHang yiXiangJin = JSONUtil.fromJson(response, XiaoShouPaiHang.class);
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
                            Toast.makeText(getActivity(), "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });
    }

    private void shouData(List<XiaoShouPaiHang.DataBean.UserSaleInfoBean> data) {
        try {
            mXiaoShouPaiHangAdapter = new XiaoShouPaiHangAdapter(R.layout.item_xiaoshoupaihang, data);
            final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mXiaoShouPaiHangAdapter.setOnLoadMoreListener(this, mRvRecycleview);
            mRvRecycleview.setLayoutManager(manager);
            mRvRecycleview.setAdapter(mXiaoShouPaiHangAdapter);
        } catch (Exception e) {
            ToastUtils.showLong(e.toString());
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if(messageEvent.getMessage().equals("编辑用户信息")){
           getData();
        }
        else if(messageEvent.getMessage().equals("头像")){
            getData();
        }
    }



    @OnClick(R.id.zonghezhi)
    void zonghezhi() {
        mKehushu.setActivated(false);
        mZonghezhi.setActivated(true);
        mXinzengkehushu.setActivated(false);
        mGenjinkehushu.setActivated(false);
        sortId = "1";
        getData();
    }

    @OnClick(R.id.kehushu)
    void kehushu() {
        mKehushu.setActivated(true);
        mZonghezhi.setActivated(false);
        mXinzengkehushu.setActivated(false);
        mGenjinkehushu.setActivated(false);
        sortId = "2";
        getData();
    }

    @OnClick(R.id.xinzengkehushu)
    void xinzengkehushu() {
        mKehushu.setActivated(false);
        mZonghezhi.setActivated(false);
        mXinzengkehushu.setActivated(true);
        mGenjinkehushu.setActivated(false);
        sortId = "3";
        getData();
    }

    @OnClick(R.id.genjinkehushu)
    void genjinkehushu() {
        mKehushu.setActivated(false);
        mZonghezhi.setActivated(false);
        mXinzengkehushu.setActivated(false);
        mGenjinkehushu.setActivated(true);
        sortId = "4";
        getData();
    }



    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_xiaoshoupaihang, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if(EventBus.getDefault().isRegistered(this)) {
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
            String url = BaseUrl.BASE_URL + "user/sale/list?token_id=" + token_id + "&loginOS=1&page=" + page + "&rows=10&merchantUserId=" + merchantUserId;
            HashMap<String, String> paramsMap = new HashMap<>();
            paramsMap.put("sortId", sortId);
            OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
                @Override
                public void onFailure(Call call, Exception e) {
                    Toast.makeText(getActivity(), "错误了", Toast.LENGTH_SHORT).show();
                    mXiaoShouPaiHangAdapter.loadMoreComplete();
                    mXiaoShouPaiHangAdapter.loadMoreEnd();
                }

                @Override
                public void onResponse(String response) {
                    Log.d("创建活动", response);
                    if (response != null) {
                        Log.d("nnnnnnn", response);
                        XiaoShouPaiHang test = JSONUtil.fromJson(response, XiaoShouPaiHang.class);
                        if (test.getRet().equals("200")) {
                            if (page > test.getData().getIntmaxPage()) {
                                page = 1;
                                mXiaoShouPaiHangAdapter.loadMoreComplete();
                                mXiaoShouPaiHangAdapter.loadMoreEnd();
                            } else if (test.getData().getUserSaleInfo().size() != 0) {
                                List<XiaoShouPaiHang.DataBean.UserSaleInfoBean> userSaleInfo = test.getData().getUserSaleInfo();
                                LoadMoreData(userSaleInfo);
                                Log.d("33333333333", response);
                                mXiaoShouPaiHangAdapter.loadMoreComplete();
                            } else if (test.getData().getUserSaleInfo().size() == 0) {
                                mXiaoShouPaiHangAdapter.loadMoreComplete();
                                mXiaoShouPaiHangAdapter.loadMoreEnd();
                            }
                        } else {
                            mXiaoShouPaiHangAdapter.loadMoreComplete();
                            mXiaoShouPaiHangAdapter.loadMoreEnd();
                        }
                    }

                }
            });
        } catch (Exception e) {
            ToastUtils.showLong(e.toString());
        }
    }

    private void LoadMoreData(List<XiaoShouPaiHang.DataBean.UserSaleInfoBean> dataList) {
        if (dataList.size() != 0) {
            mXiaoShouPaiHangAdapter.addData(dataList);
            userSaleInfo.addAll(dataList);
            mXiaoShouPaiHangAdapter.loadMoreComplete();
        }

    }
}
