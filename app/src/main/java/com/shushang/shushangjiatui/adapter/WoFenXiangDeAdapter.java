package com.shushang.shushangjiatui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.bean.JIaDou;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class WoFenXiangDeAdapter extends BaseQuickAdapter<JIaDou.DataBean.CustomerFollowUpListBean,BaseViewHolder> {


    public WoFenXiangDeAdapter(int layoutResId, @Nullable List<JIaDou.DataBean.CustomerFollowUpListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JIaDou.DataBean.CustomerFollowUpListBean item) {
        try {
            helper.setText(R.id.name,item.getUserName());
            helper.setText(R.id.shenfen,item.getMerchantName());
            helper.setText(R.id.jiadou_num,item.getSumBean());
            helper.setText(R.id.jiadou_zhuangtai,"+1");
            ImageView view = helper.getView(R.id.avatar);
            String url=BaseUrl.BASE_URL+ "image/show/?token_id="+PreferencesUtils.getString(mContext,"token_id")+"&fileId="+item.getHeadImage()+"&loginOS=1&imgType=1";
            Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(view);
        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }
    }
}

