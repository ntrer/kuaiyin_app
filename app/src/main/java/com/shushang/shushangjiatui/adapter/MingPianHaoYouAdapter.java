package com.shushang.shushangjiatui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.bean.WoDeHaoYou;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MingPianHaoYouAdapter extends BaseQuickAdapter<WoDeHaoYou.DataBean.UserSaleInfoBean,BaseViewHolder> {


    public MingPianHaoYouAdapter(int layoutResId, @Nullable List<WoDeHaoYou.DataBean.UserSaleInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WoDeHaoYou.DataBean.UserSaleInfoBean item) {
        try {
            helper.setText(R.id.name,item.getUserName());
            helper.setText(R.id.shenfen,item.getPosition());
            helper.setText(R.id.dianhua,item.getCardMobileNumber());
            helper.setText(R.id.dianpu,item.getMerchantName());
            ImageView view = helper.getView(R.id.avatar);
            String url=BaseUrl.BASE_URL+ "image/show/?token_id="+PreferencesUtils.getString(mContext,"token_id")+"&fileId="+item.getHeadPortraitImage()+"&loginOS=1&imgType=1";
            Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(view);
            helper.addOnClickListener(R.id.fenxiang);
        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }
    }
}

