package com.shushang.shushangjiatui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.bean.XiaoShouPaiHang;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class XiaoShouPaiHangAdapter extends BaseQuickAdapter<XiaoShouPaiHang.DataBean.UserSaleInfoBean,BaseViewHolder> {


    public XiaoShouPaiHangAdapter(int layoutResId, @Nullable  List<XiaoShouPaiHang.DataBean.UserSaleInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XiaoShouPaiHang.DataBean.UserSaleInfoBean item) {
        try {
            helper.setText(R.id.name,item.getUserName());
            helper.setText(R.id.shenfen,item.getPosition());
            helper.setText(R.id.kehuzongshu_text,item.getCustomerNum()+"");
            helper.setText(R.id.zuorixinzeng_text,item.getAddCustomerNum()+"");
            helper.setText(R.id.genjinkehu_text,item.getFollowUpCustomerNum()+"");
            helper.setText(R.id.zixunkehu_text,item.getConsCustomerNum()+"");
            helper.setText(R.id.zonghezhi,item.getComprehensiveNum()+"");
            ImageView view = helper.getView(R.id.avatar);
            String url=BaseUrl.BASE_URL+ "image/show/?token_id="+PreferencesUtils.getString(mContext,"token_id")+"&fileId="+item.getHeadPortraitImage()+"&loginOS=1&imgType=1";
            Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(view);
        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }
    }
}

