package com.shushang.shushangjiatui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.bean.SouSuoJieGuo;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class SouSuoAdapter extends BaseQuickAdapter<SouSuoJieGuo.DataBean.MerchantUserBean,BaseViewHolder> {


    public SouSuoAdapter(int layoutResId, @Nullable List<SouSuoJieGuo.DataBean.MerchantUserBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SouSuoJieGuo.DataBean.MerchantUserBean item) {
        try {
            helper.setText(R.id.name,item.getUserName());
            helper.setText(R.id.shenfen,item.getPosition());
            helper.setText(R.id.dianhua,item.getCardMobileNumber());
            if(item.getStatus().equals("1")){
                 helper.getView(R.id.jiahaoyou).setVisibility(View.GONE);
            }
            else {
                helper.getView(R.id.jiahaoyou).setVisibility(View.VISIBLE);
            }
            ImageView view = helper.getView(R.id.avatar);
            String url=BaseUrl.BASE_URL+ "image/show/?token_id="+PreferencesUtils.getString(mContext,"token_id")+"&fileId="+item.getHeadPortraitImage()+"&loginOS=1&imgType=1";
            Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(view);
            helper.addOnClickListener(R.id.jiahaoyou);
        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }
    }
}

