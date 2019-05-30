package com.shushang.shushangjiatui.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.bean.YouHuiQuan;

import java.util.List;

public class YouHuiQuanAdapter extends BaseQuickAdapter<YouHuiQuan.DataBean.CouponListBean,BaseViewHolder> {


    public YouHuiQuanAdapter(int layoutResId, @Nullable List<YouHuiQuan.DataBean.CouponListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, YouHuiQuan.DataBean.CouponListBean item) {
        try {
            helper.setText(R.id.name,item.getCouponName());
            helper.setText(R.id.money,item.getCouponPrice());
            helper.setText(R.id.manqian,item.getCouponDesc());

            helper.setText(R.id.youxiaoqi,"有效期:"+item.getStartTime().substring(0,10)+"至"+item.getEndTime().substring(0,10));
           if(item.getUserd()==0){
               helper.getView(R.id.weishiyong).setVisibility(View.VISIBLE);
               helper.getView(R.id.yishiyong).setVisibility(View.GONE);
               helper.getView(R.id.yiguoqi).setVisibility(View.GONE);
           }
           else if(item.getUserd()==1){
               helper.getView(R.id.weishiyong).setVisibility(View.GONE);
               helper.getView(R.id.yishiyong).setVisibility(View.VISIBLE);
               helper.getView(R.id.yiguoqi).setVisibility(View.GONE);
           }
           else {
               helper.getView(R.id.weishiyong).setVisibility(View.GONE);
               helper.getView(R.id.yishiyong).setVisibility(View.GONE);
               helper.getView(R.id.yiguoqi).setVisibility(View.VISIBLE);
           }
           helper.addOnClickListener(R.id.weishiyong);
        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }
    }
}

