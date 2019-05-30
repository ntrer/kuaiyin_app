package com.shushang.shushangjiatui.fragment.SecondFragment.ChildFragment;

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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.adapter.LeiDaAdapter;
import com.shushang.shushangjiatui.base.BaseFragment;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.bean.LeiDa;
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

public class LeiDaFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.rv_recycleview)
    RecyclerView mRvRecycleview;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    Unbinder unbinder;
    @BindView(R.id.ll_no_data)
    LinearLayout mLlNoData;
    private LeiDaAdapter mLeiDaAdapter;
    private List<LeiDa.DataBean.RadarRecordListBean> radarRecordList = new ArrayList<>();
    private int page = 1;
    private String token_id;

    public LeiDaFragment() {
        // Required empty public constructor
    }

    public static LeiDaFragment newInstance() {
        return new LeiDaFragment();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        mSrlRefresh.setOnRefreshListener(this);
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        getData();
    }

    private void getData() {
        mSrlRefresh.setRefreshing(true);
        String url = BaseUrl.BASE_URL + "user/radar/list?token_id=" + token_id + "&loginOS=1&page=1&rows=10";
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
                        LeiDa yiXiangJin = JSONUtil.fromJson(response, LeiDa.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            radarRecordList = yiXiangJin.getData().getRadarRecordList();
                            if (radarRecordList.size() != 0) {
                                shouData(radarRecordList);
                                mLlNoData.setVisibility(View.GONE);
                            } else {
                                shouData(radarRecordList);
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

    private void shouData(List<LeiDa.DataBean.RadarRecordListBean> data) {
        try {
            mLeiDaAdapter = new LeiDaAdapter(R.layout.item_leida, data);
            final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mLeiDaAdapter.setOnLoadMoreListener(this,mRvRecycleview);
            mRvRecycleview.setLayoutManager(manager);
            mRvRecycleview.setAdapter(mLeiDaAdapter);
        } catch (Exception e) {
            ToastUtils.showLong(e.toString());
        }
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_leida, null);
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
        page=1;
        getData();
    }

    @Override
    public void onLoadMoreRequested() {
        loadMore();
    }

    private void loadMore() {
        try {
            page=page+1;
            String url = BaseUrl.BASE_URL + "user/radar/list?token_id=" + token_id + "&loginOS=1&page="+page+"&rows=10";
            HashMap<String, String> paramsMap = new HashMap<>();

            OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
                @Override
                public void onFailure(Call call, Exception e) {
                    Toast.makeText(getActivity(), "错误了", Toast.LENGTH_SHORT).show();
                    mLeiDaAdapter.loadMoreComplete();
                    mLeiDaAdapter.loadMoreEnd();
                }

                @Override
                public void onResponse(String response) {
                    Log.d("创建活动", response);
                    if(response!=null) {
                        Log.d("nnnnnnn",response);
                        LeiDa test = JSONUtil.fromJson(response, LeiDa.class);
                        if(test.getRet().equals("200")){
                            if(page>test.getData().getIntmaxPage()){
                                page=1;
                                mLeiDaAdapter.loadMoreComplete();
                                mLeiDaAdapter.loadMoreEnd();
                            }
                            else if(test.getData().getRadarRecordList().size()!=0){
                                List<LeiDa.DataBean.RadarRecordListBean> radarRecordList = test.getData().getRadarRecordList();
                                LoadMoreData(radarRecordList);
                                Log.d("33333333333",response);
                                mLeiDaAdapter.loadMoreComplete();
                            }
                            else if(test.getData().getRadarRecordList().size()==0){
                                mLeiDaAdapter.loadMoreComplete();
                                mLeiDaAdapter.loadMoreEnd();
                            }
                        }
                        else {
                            mLeiDaAdapter.loadMoreComplete();
                            mLeiDaAdapter.loadMoreEnd();
                        }
                    }

                }
            });
        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }
    }

    private void LoadMoreData(List<LeiDa.DataBean.RadarRecordListBean> dataList) {
        if(dataList.size()!=0){
            mLeiDaAdapter.addData(dataList);
            radarRecordList.addAll(dataList);
            mLeiDaAdapter.loadMoreComplete();
        }

    }
}
