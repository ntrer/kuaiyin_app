package com.shushang.shushangjiatui.adapter;

import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.bean.GenJInLieBiao;

import java.util.List;

public class GenJinJiLuAdapter extends BaseQuickAdapter<GenJInLieBiao.DataBean,BaseViewHolder> {


    public GenJinJiLuAdapter(int layoutResId, @Nullable List<GenJInLieBiao.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GenJInLieBiao.DataBean item) {
        try {
            helper.setText(R.id.name,item.getUserName());
            if(item.getFollowUpMode()==1){
                helper.setText(R.id.genjinfangshi,"电话");
            }
            else if(item.getFollowUpMode()==2){
                helper.setText(R.id.genjinfangshi,"微信");
            }
            else if(item.getFollowUpMode()==3){
                helper.setText(R.id.genjinfangshi,"QQ");
            }
            else {
                helper.setText(R.id.genjinfangshi,"面聊");
            }
            helper.setText(R.id.content,item.getFollowUpText());
            helper.setText(R.id.xiacigenjinshijian,item.getNextFollowUpTime().substring(0,19));
            helper.setText(R.id.shijian,item.getCreateTime().substring(0,19));
//            if(item.getDraftRecord()==1){
//                helper.getView(R.id.shiyong).setVisibility(View.VISIBLE);
//            }
//            else {
//                helper.getView(R.id.shiyong).setVisibility(View.GONE);
//            }
        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }
    }
}

