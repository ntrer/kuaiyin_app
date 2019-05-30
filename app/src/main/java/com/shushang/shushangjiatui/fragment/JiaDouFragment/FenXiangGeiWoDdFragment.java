package com.shushang.shushangjiatui.fragment.JiaDouFragment;

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
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.adapter.FenXiangGeiWoDeAdapter;
import com.shushang.shushangjiatui.base.BaseFragment;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.bean.JIaDou;
import com.shushang.shushangjiatui.utils.Json.JSONUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.CallBackUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.OkhttpUtil;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

public class FenXiangGeiWoDdFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.rv_recycleview)
    RecyclerView mRvRecycleview;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    Unbinder unbinder;
    @BindView(R.id.ll_no_data)
    LinearLayout mLlNoData;
    private FenXiangGeiWoDeAdapter mFenXiangGeiWoDeAdapter;
    private List<JIaDou.DataBean.CustomerFollowUpListBean> customerFollowUpList = new ArrayList<>();
    private List<String> data = new ArrayList<>();
    private String token_id;
    public FenXiangGeiWoDdFragment() {
        // Required empty public constructor
    }

    public static FenXiangGeiWoDdFragment newInstance() {
        return new FenXiangGeiWoDdFragment();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        mSrlRefresh.setOnRefreshListener(this);
        getData();
    }


    private void getData() {
        mSrlRefresh.setRefreshing(true);
        String url = BaseUrl.BASE_URL + "user/goldCoin/list?token_id=" + token_id + "&loginOS=1&page=1&rows=10&type=2";
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
                        JIaDou yiXiangJin = JSONUtil.fromJson(response, JIaDou.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            customerFollowUpList = yiXiangJin.getData().getCustomerFollowUpList();
                            if (customerFollowUpList.size() != 0) {
                                shouData(customerFollowUpList);
                                mLlNoData.setVisibility(View.GONE);
                            } else {
                                shouData(customerFollowUpList);
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

    private void shouData(List<JIaDou.DataBean.CustomerFollowUpListBean> data) {
        try {
            mFenXiangGeiWoDeAdapter = new FenXiangGeiWoDeAdapter(R.layout.item_jiadou, data);
            final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mRvRecycleview.setLayoutManager(manager);
            mRvRecycleview.setAdapter(mFenXiangGeiWoDeAdapter);
        } catch (Exception e) {
            ToastUtils.showLong(e.toString());
        }
    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_fenxianggeiwode, null);
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
    }

    @Override
    public void onRefresh() {
        getData();
    }
}
