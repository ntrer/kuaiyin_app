package com.shushang.shushangjiatui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.bean.LeiDa;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class LeiDaAdapter extends BaseQuickAdapter<LeiDa.DataBean.RadarRecordListBean,BaseViewHolder> {


    public LeiDaAdapter(int layoutResId, @Nullable List<LeiDa.DataBean.RadarRecordListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LeiDa.DataBean.RadarRecordListBean item) {
        try {
            helper.setText(R.id.shijian,item.getEndTime());
            helper.setText(R.id.name,item.getTextDesc());
            helper.setText(R.id.tingliushichang_text,item.getStopTimeStr());
            ImageView view = helper.getView(R.id.avatar);
            String url=BaseUrl.BASE_URL+ "image/show/?token_id="+PreferencesUtils.getString(mContext,"token_id")+"&fileId="+item.getHeadPortraitImage()+"&loginOS=1&imgType=0";
            Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(view);
        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }
    }
}

