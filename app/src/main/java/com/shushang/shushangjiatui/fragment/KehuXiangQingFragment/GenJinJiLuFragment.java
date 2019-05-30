package com.shushang.shushangjiatui.fragment.KehuXiangQingFragment;

import android.content.Intent;
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
import com.shushang.shushangjiatui.LoginActivity;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.adapter.GenJinJiLuAdapter;
import com.shushang.shushangjiatui.base.BaseFragment;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.bean.GenJInLieBiao;
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
import butterknife.Unbinder;
import okhttp3.Call;

public class GenJinJiLuFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.rv_recycleview)
    RecyclerView mRvRecycleview;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    Unbinder unbinder;
    @BindView(R.id.ll_no_data)
    LinearLayout mLlNoData;
    Unbinder unbinder1;
    private List<GenJInLieBiao.DataBean> data = new ArrayList<>();
    private GenJinJiLuAdapter mGenJinJiLuAdapter;
    private String token_id,  openId;
    private int id;

    public GenJinJiLuFragment() {
        // Required empty public constructor
    }

    public static GenJinJiLuFragment newInstance() {
        return new GenJinJiLuFragment();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        id = PreferencesUtils.getInt(getActivity(), "kehuId");
        openId = PreferencesUtils.getString(getActivity(), "KeHuopenId");
        mSrlRefresh.setOnRefreshListener(this);
        getData();
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_genjinjilu, null);
        return view;
    }


    private void getData() {
        mSrlRefresh.setRefreshing(true);
        String url = BaseUrl.BASE_URL + "user/followup/list?token_id=" + token_id + "&loginOS=1&id=" + id + "&openId=" + openId + "&draftRecord=0";
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
                            Toast.makeText(getActivity(), "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        else if (yiXiangJin.getRet().equals("202")) {
                            PreferencesUtils.putString(getActivity(), "token_id",null);
                            EventBus.getDefault().post(new MessageEvent("退出"));
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            getActivity().finish();
                            Toast.makeText(getActivity(), "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });
    }

    private void shouData(List<GenJInLieBiao.DataBean> data) {
        try {
            mGenJinJiLuAdapter = new GenJinJiLuAdapter(R.layout.item_genjinjilu, data);
            final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mRvRecycleview.setLayoutManager(manager);
            mRvRecycleview.setAdapter(mGenJinJiLuAdapter);
        } catch (Exception e) {
            ToastUtils.showLong(e.toString());
        }
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

    @Override
    public void onRefresh() {
       getData();
    }
}
