package com.shushang.shushangjiatui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.bean.FuJinDeRen;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class TianJIaHaoYouAdapter extends BaseQuickAdapter<FuJinDeRen.DataBean.MerchantUserListBean,BaseViewHolder> {


    public TianJIaHaoYouAdapter(int layoutResId, @Nullable List<FuJinDeRen.DataBean.MerchantUserListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FuJinDeRen.DataBean.MerchantUserListBean item) {
        try {
            helper.setText(R.id.name,item.getUserName());
            ImageView view = helper.getView(R.id.avatar);
            String url=BaseUrl.BASE_URL+ "image/show/?token_id="+PreferencesUtils.getString(mContext,"token_id")+"&fileId="+item.getHeadPortraitImage()+"&loginOS=1&imgType=1";
            Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(view);
            helper.setText(R.id.shenfen,"【"+item.getPosition()+"】");
            helper.setText(R.id.dianpu,item.getMerchantName());
            helper.addOnClickListener(R.id.tianjiahaoyou);
        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }
    }
}

