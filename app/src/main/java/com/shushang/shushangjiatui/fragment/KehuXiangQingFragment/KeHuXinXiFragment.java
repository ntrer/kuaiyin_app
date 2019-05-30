package com.shushang.shushangjiatui.fragment.KehuXiangQingFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.shushang.shushangjiatui.LoginActivity;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseFragment;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.bean.KeHu;
import com.shushang.shushangjiatui.bean.KeHuXinXi;
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

public class KeHuXinXiFragment extends BaseFragment {


    @BindView(R.id.shengri)
    TextView mShengri;
    @BindView(R.id.first)
    RelativeLayout mFirst;
    @BindView(R.id.weixinhao)
    TextView mWeixinhao;
    @BindView(R.id.xiaoqudizhi)
    TextView mXiaoqudizhi;
    @BindView(R.id.zhuangxiufengge)
    TextView mZhuangxiufengge;
    @BindView(R.id.yixiangxuwqiu)
    TextView mYixiangxuwqiu;
    Unbinder unbinder;
    private String token_id;
    private List<KeHu.DataBean.CustomerListBean> customerList = new ArrayList<>();
    private int id;
    private String openId;
    private KeHu.DataBean.CustomerListBean customerListBean ;
    private Dialog mRequestDialog;
    private  KeHuXinXi.DataBean data;
    public KeHuXinXiFragment() {
        // Required empty public constructor
    }

    public static KeHuXinXiFragment newInstance() {
        return new KeHuXinXiFragment();
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        id = PreferencesUtils.getInt(getActivity(), "kehuId");
        openId=PreferencesUtils.getString(getActivity(), "KeHuopenId");
        mRequestDialog = ExtAlertDialog.creatRequestDialog(getActivity(),"请求中...");
        mRequestDialog.setCancelable(false);
        getData(id,openId);
        EventBus.getDefault().register(this);
    }


    private void getData(int id, String openId) {
        mRequestDialog.show();
        String url = BaseUrl.BASE_URL + "user/customer/details?token_id=" + token_id + "&loginOS=1&customerId="+id+"&openId="+openId;
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
                        KeHuXinXi yiXiangJin = JSONUtil.fromJson(response, KeHuXinXi.class);
                        if (yiXiangJin.getRet().equals("200")) {
                           data = yiXiangJin.getData();
                            if (data!=null) {
                                shouData(data);
                            } else {
                                shouData(data);
                            }
                        } else if (yiXiangJin.getRet().equals("201")) {
                            if (mRequestDialog != null && mRequestDialog.isShowing()) {
                                mRequestDialog.dismiss();
                            }
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
                        if (mRequestDialog != null && mRequestDialog.isShowing()) {
                            mRequestDialog.dismiss();
                        }
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });
    }


    private void shouData(KeHuXinXi.DataBean  data) {
        try {
            if(data.getBrthdayDate()!=null&&!data.getBrthdayDate().equals("")){
                mShengri.setText(data.getBrthdayDate());
            }
            else {
                mShengri.setText("无");
            }
            if(data.getWxNum()!=null&&!data.getWxNum().equals("")){
                mWeixinhao.setText(data.getWxNum().toString());
            }
            else {
                mWeixinhao.setText("无");
            }
            if(data.getCellAddress()!=null&&!data.getCellAddress().equals("")){
                mXiaoqudizhi.setText(data.getCellAddress().toString());
            }
            else {
                mXiaoqudizhi.setText("无");
            }

            if(data.getDecorationStyle()!=null&&!data.getDecorationStyle().equals("")){
                mZhuangxiufengge.setText(data.getDecorationStyle().toString());
            }
            else {
                mZhuangxiufengge.setText("无");
            }

            if(data.getIntentionalProduct()!=null&&!data.getIntentionalProduct().equals("")){
                mYixiangxuwqiu.setText(data.getIntentionalProduct().toString());
            }
            else {
                mYixiangxuwqiu.setText("无");
            }

        } catch (Exception e) {
            ToastUtils.showLong(e.toString());
        }
    }




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if(messageEvent.getMessage().equals("编辑")){
            getData(id,openId);
        }
    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_kehuxinxi, null);
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
}
