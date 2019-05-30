package com.shushang.shushangjiatui.fragment.SecondFragment.ChildFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.activity.ChatActivity;
import com.shushang.shushangjiatui.adapter.XianSuoAdapter;
import com.shushang.shushangjiatui.base.BaseFragment;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.bean.TianJIaHaoYou;
import com.shushang.shushangjiatui.bean.XianSuo;
import com.shushang.shushangjiatui.ui.ActionSheetDialog;
import com.shushang.shushangjiatui.ui.ExtAlertDialog;
import com.shushang.shushangjiatui.utils.APPConfig;
import com.shushang.shushangjiatui.utils.Json.JSONUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.CallBackUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.OkhttpUtil;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.shushang.shushangjiatui.utils.SharedPreferencesUtils;

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

public class XianSuoFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{


    @BindView(R.id.rv_recycleview)
    RecyclerView mRvRecycleview;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    Unbinder unbinder;
    @BindView(R.id.ll_no_data)
    LinearLayout mLlNoData;
    private XianSuoAdapter mXianSuoAdapter;
    private List<String> data = new ArrayList<>();
	 private List<String> chatUser = new ArrayList<>();
    private EMClient mEMClient = EMClient.getInstance();
    private String sortId, isExchangeMobileNum, source;
    private List<XianSuo.DataBean.CustomerClueListBean> customerClueList = new ArrayList<>();
    private AlertDialog dlg;
    private int page = 1;
    private String token_id;
    private Dialog mRequestDialog;
    private  String url,url2,username,nickname;
    public XianSuoFragment() {
        // Required empty public constructor
    }

    public static XianSuoFragment newInstance() {
        return new XianSuoFragment();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        dlg = new AlertDialog.Builder(getActivity()).create();
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        username = PreferencesUtils.getString(getActivity(), "username");
        nickname = PreferencesUtils.getString(getActivity(), "userName");
        mSrlRefresh.setOnRefreshListener(this);
        mRequestDialog = ExtAlertDialog.creatRequestDialog(getActivity(), "请求中...");
        mRequestDialog.setCancelable(false);
        getData();
        EventBus.getDefault().register(this);
    }

    private void getData() {
        mSrlRefresh.setRefreshing(true);
        String url = BaseUrl.BASE_URL + "user/customerclue/list?token_id=" + token_id + "&loginOS=1&page=1&rows=10";
        Log.d("创建活动", url);
        HashMap<String, String> paramsMap = new HashMap<>();
        if (sortId != null && !sortId.equals("")) {
            paramsMap.put("sortId", sortId);
        }

        if (isExchangeMobileNum != null && !isExchangeMobileNum.equals("")) {
            paramsMap.put("isExchangeMobileNum", isExchangeMobileNum);
        }

        if (source != null && !source.equals("")) {
            paramsMap.put("source", source);
        }

        Log.d("asd",sortId+"==="+isExchangeMobileNum+"=="+source);

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
                        XianSuo yiXiangJin = JSONUtil.fromJson(response, XianSuo.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            customerClueList = yiXiangJin.getData().getCustomerClueList();
                            if (customerClueList.size() != 0) {
                                shouData(customerClueList);
                                mLlNoData.setVisibility(View.GONE);
                            } else {
                                shouData(customerClueList);
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

    private void shouData(final List<XianSuo.DataBean.CustomerClueListBean> data) {
        try {
            mXianSuoAdapter = new XianSuoAdapter(R.layout.item_xiansuo, data);
            final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mRvRecycleview.setLayoutManager(manager);
            mRvRecycleview.setAdapter(mXianSuoAdapter);
            mXianSuoAdapter.setOnLoadMoreListener(this,mRvRecycleview);
            mRvRecycleview.addOnItemTouchListener(new OnItemChildClickListener() {
                @Override
                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                }

                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                    super.onItemChildClick(adapter, view, position);
                    int itemViewId = view.getId();
                    try {
                        if (itemViewId == R.id.faxiaoxi) {
                            StartChat(data.get(position).getHeadPortraitImage(),data.get(position).getUserName());
                        }
                        else if(itemViewId==R.id.zhuanweiyixiangkehu){
                            ZhuanYiXiang(data.get(position).getCustomerId()+"");
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

    private void ZhuanYiXiang(String id) {
        mRequestDialog.show();
        String url = BaseUrl.BASE_URL + "user/customer/turnIntention?token_id=" + token_id + "&loginOS=1&customerId=" + id;
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
                            ToastUtils.showLong("成功");
                            EventBus.getDefault().post(new MessageEvent("转意向"));
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(getActivity(), "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
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

    private void StartChat(final int headPortraitImage, final String chatName) {
        boolean loggedInBefore = mEMClient.isLoggedInBefore();
        if (loggedInBefore) {
            gotoChat(headPortraitImage,chatName);
        } else {
            mEMClient.login(username, "#ssayeasemob8837", new EMCallBack() {
                @Override
                public void onSuccess() {
                    ToastUtils.showLong("登陆成功");
                    gotoChat(headPortraitImage, chatName);
                }

                @Override
                public void onError(int i, String s) {
                    ToastUtils.showLong("登陆失败");
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });

//            mEMClient.login("misaki2", "123", new EMCallBack() {
//                @Override
//                public void onSuccess() {
//                    gotoChat(headPortraitImage, chatName);
//                }
//
//                @Override
//                public void onError(int i, String s) {
//                    ToastUtils.showLong("登陆失败");
//                }
//
//                @Override
//                public void onProgress(int i, String s) {
//
//                }
//            });


        }
    }


    public void gotoChat(int headPortraitImage, String chatName) {
         url=BaseUrl.BASE_URL+ "image/show/?token_id="+PreferencesUtils.getString(mContext,"token_id")+"&fileId="+PreferencesUtils.getString(getActivity(),"avatar")+"&loginOS=1&imgType=1";
         //url2=BaseUrl.BASE_URL+ "image/show/?token_id="+PreferencesUtils.getString(mContext,"token_id")+"&fileId="+headPortraitImage+"&loginOS=1&imgType=1";
        //设置要发送出去的昵称
        SharedPreferencesUtils.setParam(getActivity(), APPConfig.USER_NAME, nickname);
        //设置要发送出去的头像
        SharedPreferencesUtils.setParam(getActivity(), APPConfig.USER_HEAD_IMG, url);
        //对话头像
//        SharedPreferencesUtils.setParam(getActivity(), APPConfig.CHAT_HEAD_IMG, url2);

        EMClient.getInstance().chatManager().loadAllConversations();
        // 跳转到聊天界面，开始聊天
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        // EaseUI封装的聊天界面需要这两个参数，聊天者的username，以及聊天类型，单聊还是群聊
        intent.putExtra("userId", chatName);
        intent.putExtra("chatType", EMMessage.ChatType.Chat);
        startActivity(intent);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_xiansuo, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if (messageEvent.getMessage().equals("筛选")) {
            sortId = null;
            isExchangeMobileNum = null;
            source = null;
            showSureDlg2(getActivity(), "筛选", "确定", false);
        }
    }

    public void showSureDlg2(Context context, String title, String btnTxt, boolean isCancelable) {
        dlg.show();
        dlg.setContentView(R.layout.ext_cancel_sure_dialog12);
        dlg.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        View titleLayout = dlg.findViewById(R.id.title_layout);
        TextView titleTxt = (TextView) dlg.findViewById(R.id.ext_dialog_title);
        if (TextUtils.isEmpty(title))
            titleLayout.setVisibility(View.GONE);
        else
            titleTxt.setText(title);


        RelativeLayout paixu = (RelativeLayout) dlg.findViewById(R.id.paixu);
        final TextView paixuText = (TextView) dlg.findViewById(R.id.paixu_text);
        paixu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(getActivity())
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("最后访问时间", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        sortId = "1";
                                        paixuText.setText("最后访问时间");
                                    }
                                })
                        .addSheetItem("访问次数", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        sortId = "2";
                                        paixuText.setText("访问次数");
                                    }
                                })
                        .addSheetItem("浏览页面数", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        sortId = "3";
                                        paixuText.setText("浏览页面数");
                                    }
                                })

                        .addSheetItem("意向度", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        sortId = "4";
                                        paixuText.setText("意向度");

                                    }
                                })
                        .addSheetItem("停留总时长", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        sortId = "5";
                                        paixuText.setText("停留总时长");
                                    }
                                })
                        .addSheetItem("全部", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        sortId = null;
                                        paixuText.setText("全部");
                                    }
                                })
                        .show();
            }
        });


        RelativeLayout shoujihao = (RelativeLayout) dlg.findViewById(R.id.shoujihao);
        final TextView shoujihao_text = (TextView) dlg.findViewById(R.id.shoujihao_text);
        shoujihao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(getActivity())
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("有手机号", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        isExchangeMobileNum = "1";
                                        shoujihao_text.setText("有手机号");
                                    }
                                })
                        .addSheetItem("无手机号", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        isExchangeMobileNum = "0";
                                        shoujihao_text.setText("无手机号");
                                    }
                                })
                        .addSheetItem("全部", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        isExchangeMobileNum =null;
                                        shoujihao_text.setText("全部");
                                    }
                                })
                        .show();
            }
        });


        RelativeLayout laiyuan = (RelativeLayout) dlg.findViewById(R.id.laiyuan);
        final TextView laiyuan_text = (TextView) dlg.findViewById(R.id.laiyuan_text);
        laiyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(getActivity())
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("主动搜索", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source = "1";
                                        laiyuan_text.setText("主动搜索");
                                    }
                                })
                        .addSheetItem("名片扫码", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source = "2";
                                        laiyuan_text.setText("名片扫码");
                                    }
                                })
                        .addSheetItem("活动分发", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source = "3";
                                        laiyuan_text.setText("活动分发");
                                    }
                                })
                        .addSheetItem("分享文章", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source = "4";
                                        laiyuan_text.setText("分享文章");
                                    }
                                })
                        .addSheetItem("客户转发", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source = "5";
                                        laiyuan_text.setText("客户转发");
                                    }
                                })
                        .addSheetItem("全部", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source =null;
                                        laiyuan_text.setText("全部");
                                    }
                                })
                        .show();
            }
        });


        Button btn = (Button) dlg.findViewById(R.id.cancle);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });

        Button sureBnt = (Button) dlg.findViewById(R.id.sure);
        if (!TextUtils.isEmpty(btnTxt))
            sureBnt.setText(btnTxt);

        sureBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                dlg.dismiss();
            }
        });
        dlg.setCancelable(isCancelable);
        dlg.setCancelable(false);
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
            String url = BaseUrl.BASE_URL + "user/customerclue/list?token_id=" + token_id + "&loginOS=1&page="+page+"&rows=10";
            HashMap<String, String> paramsMap = new HashMap<>();
            if (sortId != null && !sortId.equals("")) {
                paramsMap.put("sortId", sortId);
            }

            if (isExchangeMobileNum != null && !isExchangeMobileNum.equals("")) {
                paramsMap.put("isExchangeMobileNum", isExchangeMobileNum);
            }

            if (source != null && !source.equals("")) {
                paramsMap.put("source", source);
            }
            OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
                @Override
                public void onFailure(Call call, Exception e) {
                    Toast.makeText(getActivity(), "错误了", Toast.LENGTH_SHORT).show();
                    mXianSuoAdapter.loadMoreComplete();
                    mXianSuoAdapter.loadMoreEnd();
                }

                @Override
                public void onResponse(String response) {
                    Log.d("创建活动", response);
                    if(response!=null) {
                        Log.d("nnnnnnn",response);
                        XianSuo test = JSONUtil.fromJson(response, XianSuo.class);
                        if(test.getRet().equals("200")){
                            if(page>test.getData().getIntmaxPage()){
                                page=1;
                                mXianSuoAdapter.loadMoreComplete();
                                mXianSuoAdapter.loadMoreEnd();
                            }
                            else if(test.getData().getCustomerClueList().size()!=0){
                                List<XianSuo.DataBean.CustomerClueListBean> customerClueList = test.getData().getCustomerClueList();
                                LoadMoreData(customerClueList);
                                Log.d("33333333333",response);
                                mXianSuoAdapter.loadMoreComplete();
                            }
                            else if(test.getData().getCustomerClueList().size()==0){
                                mXianSuoAdapter.loadMoreComplete();
                                mXianSuoAdapter.loadMoreEnd();
                            }
                        }
                        else {
                            mXianSuoAdapter.loadMoreComplete();
                            mXianSuoAdapter.loadMoreEnd();
                        }
                    }

                }
            });
        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }
    }

    private void LoadMoreData(List<XianSuo.DataBean.CustomerClueListBean> dataList) {
        if(dataList.size()!=0){
            mXianSuoAdapter.addData(dataList);
            customerClueList.addAll(dataList);
            mXianSuoAdapter.loadMoreComplete();
        }

    }
}
