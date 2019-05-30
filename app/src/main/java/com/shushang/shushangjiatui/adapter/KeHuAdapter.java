package com.shushang.shushangjiatui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.bean.KeHu;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class KeHuAdapter extends BaseQuickAdapter<KeHu.DataBean.CustomerListBean,BaseViewHolder> {


    public KeHuAdapter(int layoutResId, @Nullable List<KeHu.DataBean.CustomerListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KeHu.DataBean.CustomerListBean item) {
        try {
            helper.setText(R.id.name,item.getUserName());
            helper.setText(R.id.phone,item.getMobileNumber());
            helper.setText(R.id.fangwenshijian,item.getLastVisitTime());
            helper.setText(R.id.zhuanfa,item.getSourceDesc());
            helper.setText(R.id.fangwencishu_text,item.getVisitNum()+"");
            ImageView view = helper.getView(R.id.avatar);
            String url=BaseUrl.BASE_URL+ "image/show/?token_id="+PreferencesUtils.getString(mContext,"token_id")+"&fileId="+item.getLocalHeadPortraitImage()+"&loginOS=1&imgType=1";
            Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(view);
            helper.addOnClickListener(R.id.tianjiagenjin);
            helper.addOnClickListener(R.id.qianyue);
        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }
    }
}

