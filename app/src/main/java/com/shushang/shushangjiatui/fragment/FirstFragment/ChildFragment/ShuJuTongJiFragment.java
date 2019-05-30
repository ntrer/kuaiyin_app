package com.shushang.shushangjiatui.fragment.FirstFragment.ChildFragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.shushang.shushangjiatui.LoginActivity;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.adapter.TabRecyclerViewAdapter;
import com.shushang.shushangjiatui.base.BaseFragment;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.bean.DianPuRenYuan;
import com.shushang.shushangjiatui.bean.ShuJuZongLan;
import com.shushang.shushangjiatui.bean.Shuju;
import com.shushang.shushangjiatui.ui.ExtAlertDialog;
import com.shushang.shushangjiatui.ui.segmentView2;
import com.shushang.shushangjiatui.ui.segmentView3;
import com.shushang.shushangjiatui.utils.Json.JSONUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.CallBackUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.OkhttpUtil;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.xys.libzxing.zxing.net.RestClient;
import com.xys.libzxing.zxing.net.callback.IError;
import com.xys.libzxing.zxing.net.callback.IFailure;
import com.xys.libzxing.zxing.net.callback.ISuccess;

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

public class ShuJuTongJiFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rl_tab)
    RecyclerView mRlTab;
    @BindView(R.id.TabLayout)
    LinearLayout mTabLayout;
    Unbinder unbinder;
    @BindView(R.id.line)
    View mLine;
    @BindView(R.id.shujuzonglan)
    TextView mShujuzonglan;
    @BindView(R.id.shiyongtianshu)
    TextView mShiyongtianshu;
    @BindView(R.id.line3)
    View mLine3;
    @BindView(R.id.leijikehu)
    TextView mLeijikehu;
    @BindView(R.id.leijikehu_text)
    TextView mLeijikehuText;
    @BindView(R.id.leijifangwen)
    TextView mLeijifangwen;
    @BindView(R.id.leijifangwen_text)
    TextView mLeijifangwenText;
    @BindView(R.id.leijizixunkehu)
    TextView mLeijizixunkehu;
    @BindView(R.id.leijizixunkehu_text)
    TextView mLeijizixunkehuText;
    @BindView(R.id.leijigenjinkehu)
    TextView mLeijigenjinkehu;
    @BindView(R.id.leijigenjinkehu_text)
    TextView mLeijigenjinkehuText;
    @BindView(R.id.leijibeifenxiang)
    TextView mLeijibeifenxiang;
    @BindView(R.id.leijibeifenxiang_text)
    TextView mLeijibeifenxiangText;
    @BindView(R.id.jichuxinxi_title)
    RelativeLayout mJichuxinxiTitle;
    @BindView(R.id.card1)
    CardView mCard1;
    @BindView(R.id.line4)
    View mLine4;
    @BindView(R.id.shujuzonglanqushi)
    TextView mShujuzonglanqushi;
    @BindView(R.id.keshushu)
    TextView mKeshushu;
    @BindView(R.id.fangwencishu)
    TextView mFangwencishu;
    @BindView(R.id.zixuncishu)
    TextView mZixuncishu;
    @BindView(R.id.genjincishu)
    TextView mGenjincishu;
    @BindView(R.id.card2)
    CardView mCard2;
    Unbinder unbinder1;
    @BindView(R.id.line_chart)
    LineChart lineChart;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.segmentView)
    segmentView2 mSegmentView;
    @BindView(R.id.segmentView3)
    segmentView3 mSegmentView3;
    @BindView(R.id.ll_no_data)
    LinearLayout mLlNoData;
    private List<String> reyuan = new ArrayList<>();
    private TabRecyclerViewAdapter mTabRecyclerViewAdapter;
    private Typeface mTf;//声明字体库
    private List<String> day = new ArrayList<>();
    private List<String> count = new ArrayList<>();
    private String token_id;
    private String type = "1";
    private int id = 0;
    private String dataType = "1", type2 = "1";
    private List<DianPuRenYuan.DataBean> data = new ArrayList<>();
    private List<Shuju.DataBean> data3 = new ArrayList<>();
    private ShuJuZongLan.DataBean data2;
    private Dialog mRequestDialog;

    public ShuJuTongJiFragment() {
        // Required empty public constructor
    }

    public static ShuJuTongJiFragment newInstance() {
        return new ShuJuTongJiFragment();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        mKeshushu.setActivated(true);
        mTf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        mRequestDialog = ExtAlertDialog.creatRequestDialog(getActivity(), getString(R.string.submit));
        mRequestDialog.setCancelable(false);
        mSrlRefresh.setOnRefreshListener(this);
        getRenYuan();
        mSegmentView.setOnSegmentViewClickListener(new segmentView2.onSegmentViewClickListener() {
            @Override
            public void onSegmentViewClick(View view, int position) {
                if (position == 0) {
                    type = "1";
                    getData(id, type);

                } else if (position == 1) {
                    type = "2";
                    getData(id, type);
                }
            }
        });

        mSegmentView3.setOnSegmentViewClickListener(new segmentView3.onSegmentViewClickListener() {
            @Override
            public void onSegmentViewClick(View view, int position) {
                if (position == 0) {
                    type2 = "1";
                    getQuShi(id, type2, dataType);

                } else if (position == 1) {
                    type2 = "2";
                    getQuShi(id, type2, dataType);
                }
            }
        });
        EventBus.getDefault().register(this);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shujutongji, null);
        return view;
    }


    private void getRenYuan() {
        String url = BaseUrl.BASE_URL + "user/statistics/userlist?token_id=" + token_id + "&loginOS=1";
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
                        DianPuRenYuan yiXiangJin = JSONUtil.fromJson(response, DianPuRenYuan.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            data = yiXiangJin.getData();
                            if (data.size() != 0) {
                                showTabData(data);
                            } else {
                                showTabData(data);
                            }
                            id = data.get(0).getId();
                            getData(id, type);
                            getQuShi(id, type2, dataType);
                        } else if (yiXiangJin.getRet().equals("201")) {
                            mSrlRefresh.setRefreshing(false);
                            Toast.makeText(getActivity(), "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        else if(yiXiangJin.getRet().equals("202")){
                            mSrlRefresh.setRefreshing(false);
                            Toast.makeText(getActivity(), "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                            PreferencesUtils.putString(getActivity(), "token_id", null);
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            getActivity().finish();
                        }
                    } catch (Exception e) {
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });

    }

    private void getQuShi(int id, String type2, String dataType) {
        day.clear();
        count.clear();
        String url = BaseUrl.BASE_URL + "statistics/trend?token_id=" + token_id + "&loginOS=1" + "&merchantUserId=" + id + "&timeType=" + type2 + "&dataType=" + dataType;
        Log.d("BaseUrl", url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            if (response != null) {
                                try {
                                    Shuju activityList = JSONUtil.fromJson(response, Shuju.class);
                                    if (activityList.getRet().equals("200")) {
                                        data3 = activityList.getData();
                                        if (data3 != null) {
                                            lineChart.setVisibility(View.VISIBLE);
                                            mLlNoData.setVisibility(View.GONE);
                                            initData(data3);
                                        } else {
                                            lineChart.setVisibility(View.GONE);
                                           mLlNoData.setVisibility(View.VISIBLE);
                                        }
                                    } else if (activityList.getRet().equals("201")) {
                                        Toast.makeText(getActivity(), "" + activityList.getMsg(), Toast.LENGTH_SHORT).show();
                                    } else if (activityList.getRet().equals("-996")) {
                                        Toast.makeText(getActivity(), "您无权查看", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(getActivity(), "连接超时", Toast.LENGTH_SHORT).show();
                        }
                    }).error(new IError() {
                @Override
                public void onError(int code, String msg) {
                    Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
                }
            })
                    .build()
                    .get();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();
        }
    }

    private void showTabData(final List<DianPuRenYuan.DataBean> reyuan) {
        mTabRecyclerViewAdapter = new TabRecyclerViewAdapter(R.layout.tab_items, reyuan);
        mTabRecyclerViewAdapter.setThisPosition(0);
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRlTab.setLayoutManager(manager);
        mRlTab.setAdapter(mTabRecyclerViewAdapter);
        mTabRecyclerViewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTabRecyclerViewAdapter.setThisPosition(position);
                mTabRecyclerViewAdapter.notifyDataSetChanged();
                mRlTab.scrollToPosition(position);
                id = reyuan.get(position).getId();
                getData(id, type);
                getQuShi(id,type2,dataType);
            }
        });

    }


    private void getData(int id, String type) {
        mSrlRefresh.setRefreshing(true);
        String url = BaseUrl.BASE_URL + "statistics/overview?token_id=" + token_id + "&loginOS=1&merchantUserId=" + id + "&timeType=" + type;
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
                        ShuJuZongLan yiXiangJin = JSONUtil.fromJson(response, ShuJuZongLan.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            data2 = yiXiangJin.getData();
                            if (data2 != null) {
                                ShowShuJuZongLan(data2);
                            }
                        } else if (yiXiangJin.getRet().equals("201")) {
                            mSrlRefresh.setRefreshing(false);
                            Toast.makeText(getActivity(), "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });

    }

    private void ShowShuJuZongLan(ShuJuZongLan.DataBean data2) {
        mLeijikehuText.setText(data2.getCumCustomerNum() + "人");
        mLeijifangwenText.setText(data2.getCumVisitNum() + "次");
        mLeijizixunkehuText.setText(data2.getCumConsultationNum() + "人");
        mLeijigenjinkehuText.setText(data2.getCumFollowUpNum() + "人");
        mLeijibeifenxiangText.setText(data2.getCumShareNum() + "次");
        mShiyongtianshu.setText(data2.getUseDayNum());
    }


    private void initData(List<Shuju.DataBean> list) {

        //显示边界
        lineChart.setDrawBorders(false);
        //设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            entries.add(new Entry(i, Float.parseFloat(String.valueOf(list.get(i).getCount()))));
        }
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        //线颜色
        lineDataSet.setColor(Color.parseColor("#F15A4A"));
        //线宽度
        lineDataSet.setLineWidth(1.6f);
        //不显示圆点
        lineDataSet.setDrawCircles(true);
        //设置小圆圈的尺寸
        lineDataSet.setCircleSize(2.5f);

        //设置高亮的颜色
        lineDataSet.setHighLightColor(Color.rgb(244, 0, 0));
        lineDataSet.setValueTextSize(10f);
        //线条平滑
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        //设置折线图填充
//        lineDataSet.setDrawFilled(true);
        LineData data = new LineData(lineDataSet);
        //折线图不显示数值
        data.setDrawValues(true);


        //得到X轴
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setDrawAxisLine(true);
        String[] str = new String[list.size()];
        for (int j = 0; j < list.size(); j++) {
            str[j] = list.get(j).getTime();
        }
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(1f);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(list.size() / 6, false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum((float) list.size() - 1);
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(-55);
        xAxis.setLabelCount(list.size() + 1);
        LabelFormatter labelFormatter = new LabelFormatter(str);
        xAxis.setValueFormatter(labelFormatter);
        xAxis.setGranularity(1f);
        xAxis.setGridColor(Color.parseColor("#d8d8d8"));
        //设置最后和第一个标签不超出x轴
//        xAxis.setAvoidFirstLastClipping(true);
//        设置线的宽度
        xAxis.setAxisLineWidth(1.0f);
        xAxis.setAxisLineColor(Color.parseColor("#d5d5d5"));


        //得到Y轴
        YAxis yAxis = lineChart.getAxisLeft();
        YAxis rightYAxis = lineChart.getAxisRight();
        yAxis.setEnabled(true);
        yAxis.setDrawAxisLine(true);
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
        //设置y轴坐标之间的最小间隔
        //显示网格线
        yAxis.setDrawGridLines(true);
        yAxis.enableGridDashedLine(10f, 10f, 0f);
        yAxis.setGridColor(Color.parseColor("#d8d8d8"));
        yAxis.setAxisLineColor(Color.parseColor("#d5d5d5"));


        int max = list.get(0).getCount();

        //设置Y轴坐标之间的最小间隔
        for (int i = 0; i <list.size(); i++) {
            if(max<list.get(i).getCount()){
                max=list.get(i).getCount();
            }
        }

        if(max<=10){
            yAxis.setGranularity(1);
        }
        else if(max>10){
            yAxis.setGranularity(2);
        }
        else if(max>20){
            yAxis.setGranularity(4);
        }
        else if(max>40){
            yAxis.setGranularity(6);
        }
        else if(max>60){
            yAxis.setGranularity(8);
        }
        else if(max>80){
            yAxis.setGranularity(10);
        }

        //设置y轴的刻度数量
        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+2
        yAxis.setLabelCount(10, false);
        //设置从Y轴值
        yAxis.setAxisMinimum(0f);
        yAxis.enableGridDashedLine(10f, 10f, 0f);
        //+1:y轴多一个单位长度，为了好看
//        yAxis.setAxisMaximum(Float.parseFloat(String.valueOf(list.get(list.size()-1).getCount())));
        //y轴
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int IValue = (int) value;
                Log.d("IValue", IValue + "");
                return String.valueOf(IValue);
            }
        });
        //图例：得到Lengend
        Legend legend = lineChart.getLegend();
        //隐藏Lengend
        legend.setEnabled(false);
        //隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        //设置XY轴动画效果
        lineChart.animateY(1000);
        lineChart.animateX(500);
        lineChart.setBackgroundColor(Color.WHITE);
        //设置数据
        lineChart.setData(data);
        lineChart.notifyDataSetChanged();
        //图标刷新
        lineChart.invalidate();

    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if(messageEvent.getMessage().equals("编辑用户信息")){
           getRenYuan();
        }
    }



    @Override
    public void onRefresh() {
        getData(id, type);
        getQuShi(id,type2,dataType);
    }

    class LabelFormatter implements IAxisValueFormatter {
        private final String[] mLabels;

        public LabelFormatter(String[] labels) {
            mLabels = labels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            try {
                return mLabels[(int) value];
            } catch (Exception e) {
                e.printStackTrace();
                return mLabels[0];
            }
        }
    }


    @OnClick(R.id.keshushu)
    void kehushu() {
        mKeshushu.setActivated(true);
        mFangwencishu.setActivated(false);
        mZixuncishu.setActivated(false);
        mGenjincishu.setActivated(false);
        dataType = "1";
        getQuShi(id, type2, dataType);
    }

    @OnClick(R.id.fangwencishu)
    void fangwencishu() {
        mKeshushu.setActivated(false);
        mFangwencishu.setActivated(true);
        mZixuncishu.setActivated(false);
        mGenjincishu.setActivated(false);
        dataType = "2";
        getQuShi(id, type2, dataType);
    }

    @OnClick(R.id.zixuncishu)
    void zixuncishu() {
        mKeshushu.setActivated(false);
        mFangwencishu.setActivated(false);
        mZixuncishu.setActivated(true);
        mGenjincishu.setActivated(false);
        dataType = "3";
        getQuShi(id, type2, dataType);
    }

    @OnClick(R.id.genjincishu)
    void genjincishu() {
        mKeshushu.setActivated(false);
        mFangwencishu.setActivated(false);
        mZixuncishu.setActivated(false);
        mGenjincishu.setActivated(true);
        dataType = "4";
        getQuShi(id, type2, dataType);
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
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
