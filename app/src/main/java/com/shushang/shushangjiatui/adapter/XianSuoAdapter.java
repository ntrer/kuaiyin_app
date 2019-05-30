package com.shushang.shushangjiatui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.bean.XianSuo;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class XianSuoAdapter extends BaseQuickAdapter<XianSuo.DataBean.CustomerClueListBean,BaseViewHolder> {


    public XianSuoAdapter(int layoutResId, @Nullable List<XianSuo.DataBean.CustomerClueListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XianSuo.DataBean.CustomerClueListBean item) {
        try {
            helper.setText(R.id.fangwenshijian,item.getLastVisitTime());
            helper.setText(R.id.name,item.getUserName());
            helper.setText(R.id.fangwencishu_text,item.getVisitBcardNum()+"");
            helper.setText(R.id.liulan_text,item.getBrowsePageNum()+"");
            helper.setText(R.id.laiyuan,item.getSourceDesc());
            if(item.getAuthMobile()==1){
                 helper.setText(R.id.phone,item.getMobileNumber());
            }
            else {
                helper.setText(R.id.phone,"");
            }
            helper.setText(R.id.yixiangdu,item.getIntentionality()+"");
            helper.addOnClickListener(R.id.faxiaoxi);
            helper.addOnClickListener(R.id.zhuanweiyixiangkehu);
            ImageView view = helper.getView(R.id.avatar);
            String url=BaseUrl.BASE_URL+ "image/show/?token_id="+PreferencesUtils.getString(mContext,"token_id")+"&fileId="+item.getHeadPortraitImage()+"&loginOS=1&imgType=1";
            Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(view);
        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }
    }
}

