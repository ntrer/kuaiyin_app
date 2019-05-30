package com.shushang.shushangjiatui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.bean.XinPengYou;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class NewFriendAdapter extends BaseQuickAdapter<XinPengYou.DataBean.UserSaleInfoBean,BaseViewHolder> {


    public NewFriendAdapter(int layoutResId, @Nullable List<XinPengYou.DataBean.UserSaleInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XinPengYou.DataBean.UserSaleInfoBean item) {
        try {
            helper.setText(R.id.name,item.getUserName());
            helper.setText(R.id.shanghuname,item.getMerchantName());
            helper.setText(R.id.shenfen,item.getPosition());
            helper.setText(R.id.phone,item.getCardMobileNumber());
            if(item.getStatus().equals("0")){
                helper.getView(R.id.chakan).setVisibility(View.VISIBLE);
                helper.getView(R.id.zhuangtai1).setVisibility(View.GONE);
                helper.getView(R.id.zhuangtai2).setVisibility(View.GONE);
            }
            else if(item.getStatus().equals("1")){
                helper.getView(R.id.chakan).setVisibility(View.GONE);
                helper.getView(R.id.zhuangtai1).setVisibility(View.VISIBLE);
                helper.getView(R.id.zhuangtai2).setVisibility(View.GONE);
            }
            else if(item.getStatus().equals("-1")){
                helper.getView(R.id.chakan).setVisibility(View.GONE);
                helper.getView(R.id.zhuangtai1).setVisibility(View.GONE);
                helper.getView(R.id.zhuangtai2).setVisibility(View.VISIBLE);
            }
//            helper.addOnClickListener(R.id.chakan);
            ImageView view = helper.getView(R.id.avatar);
            String url=BaseUrl.BASE_URL+ "image/show/?token_id="+PreferencesUtils.getString(mContext,"token_id")+"&fileId="+item.getHeadPortraitImage()+"&loginOS=1&imgType=1";
            Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(view);
        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }
    }
}

