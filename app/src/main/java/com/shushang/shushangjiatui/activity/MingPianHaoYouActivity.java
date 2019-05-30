package com.shushang.shushangjiatui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.shushang.shushangjiatui.LoginActivity;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.adapter.TianJIaHaoYouAdapter;
import com.shushang.shushangjiatui.base.BaseActivity2;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.bean.FuJinDeRen;
import com.shushang.shushangjiatui.bean.TianJIaHaoYou;
import com.shushang.shushangjiatui.ui.ExtAlertDialog;
import com.shushang.shushangjiatui.utils.Json.JSONUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.CallBackUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.OkhttpUtil;
import com.shushang.shushangjiatui.utils.ShareFileUtils;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.shushang.shushangjiatui.utils.ToImg3;
import com.shushang.shushangjiatui.utils.ToImg4;
import com.squareup.picasso.Picasso;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MingPianHaoYouActivity extends BaseActivity2 implements SwipeRefreshLayout.OnRefreshListener{

    private static final int SCAN_CODE = 1007;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.txt_shanghu)
    AppCompatEditText mTxtShanghu;
    @BindView(R.id.tianjiagenjin)
    TextView mTianjiagenjin;
    @BindView(R.id.search_title)
    RelativeLayout mSearchTitle;
    @BindView(R.id.wodemingpian)
    LinearLayout mWodemingpian;
    @BindView(R.id.saoyisao)
    ImageView mSaoyisao;
    @BindView(R.id.saomiao)
    RelativeLayout mSaomiao;
    @BindView(R.id.xindepengyou)
    RelativeLayout mXindepengyou;
    @BindView(R.id.fujinderen)
    RelativeLayout mFujinderen;
    @BindView(R.id.rv_recycleview)
    RecyclerView mRvRecycleview;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.ll_no_data)
    LinearLayout mLlNoData;
    private TianJIaHaoYouAdapter mTianJIaHaoYouAdapter;
    private List<String> data = new ArrayList<>();
    private String token_id,longitude,latitude;
    private Dialog mRequestDialog;
    private List<FuJinDeRen.DataBean.MerchantUserListBean> merchantUserList = new ArrayList<>();
    private String searchText;
    private AlertDialog dlg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSrlRefresh.setOnRefreshListener(this);
        mRequestDialog = ExtAlertDialog.creatRequestDialog(this,"请求中...");
        mRequestDialog.setCancelable(false);
        dlg = new AlertDialog.Builder(this).create();
        token_id = PreferencesUtils.getString(MingPianHaoYouActivity.this, "token_id");
        longitude = PreferencesUtils.getString(this, "longitude");
        latitude = PreferencesUtils.getString(this, "Latitude");
        getData();
    }

    private void getData() {
        mSrlRefresh.setRefreshing(true);
        String url = BaseUrl.BASE_URL + "client/nearbyUser?token_id=" + token_id + "&loginOS=1&longitude=" + longitude+ "&latitude=" + latitude;
        Log.d("创建活动", url);
        HashMap<String, String> paramsMap = new HashMap<>();
        OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                mSrlRefresh.setRefreshing(false);
                ToastUtils.showLong(e.toString());
            }

            @Override
            public void onResponse(String response) {
                Log.d("创建活动", response);
                mSrlRefresh.setRefreshing(false);
                if (response != null) {
                    try {
                        FuJinDeRen yiXiangJin = JSONUtil.fromJson(response, FuJinDeRen.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            merchantUserList = yiXiangJin.getData().getMerchantUserList();
                            if (merchantUserList.size() != 0) {
                                shouData(merchantUserList);
                                mLlNoData.setVisibility(View.GONE);
                            } else {
                                shouData(merchantUserList);
                                mLlNoData.setVisibility(View.VISIBLE);
                            }
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(MingPianHaoYouActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        else if(yiXiangJin.getMsg().equals("202")){
                            PreferencesUtils.putString(MingPianHaoYouActivity.this, "token_id",null);
                            EventBus.getDefault().post(new MessageEvent("退出名片好友"));
                            startActivity(new Intent(MingPianHaoYouActivity.this, LoginActivity.class));
                            finish();
                        }
                    } catch (Exception e) {
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });
    }

    private void shouData(final List<FuJinDeRen.DataBean.MerchantUserListBean> data) {
        try {
            mTianJIaHaoYouAdapter = new TianJIaHaoYouAdapter(R.layout.item_tianjiahaoyou, data);
            final LinearLayoutManager manager = new LinearLayoutManager(this);
            mRvRecycleview.setLayoutManager(manager);
            mRvRecycleview.setAdapter(mTianJIaHaoYouAdapter);
            mRvRecycleview.addOnItemTouchListener(new OnItemChildClickListener() {
                @Override
                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                }

                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                    super.onItemChildClick(adapter, view, position);
                    int itemViewId = view.getId();
                    try {
                        if (itemViewId == R.id.tianjiahaoyou) {
                            tianjiahaoyou(data.get(position).getId());
                        }
                    } catch (Exception e) {
                        ToastUtils.showLong(e.toString());
                    }
                }
            });

        } catch (Exception e) {
            ToastUtils.showLong(e.toString());
        }
    }

    private void tianjiahaoyou(int id) {
      mRequestDialog.show();
        String url = BaseUrl.BASE_URL + "user/friend/req?token_id=" + token_id + "&loginOS=1&merchantUserId="+id;
        Log.d("创建活动", url);
        HashMap<String, String> paramsMap = new HashMap<>();
        OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                if (mRequestDialog != null && mRequestDialog.isShowing()) {
                    mRequestDialog.dismiss();
                }
                ToastUtils.showLong(e.toString());
            }

            @Override
            public void onResponse(String response) {
                Log.d("创建活动", response);
                if (mRequestDialog != null && mRequestDialog.isShowing()) {
                    mRequestDialog.dismiss();
                }
                if (response != null) {
                    try {
                        TianJIaHaoYou yiXiangJin = JSONUtil.fromJson(response, TianJIaHaoYou.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            ToastUtils.showLong("发送添加申请成功，可在新的朋友界面查看结果");
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(MingPianHaoYouActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        if (mRequestDialog != null && mRequestDialog.isShowing()) {
                            mRequestDialog.dismiss();
                        }
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });
    }


    @OnClick(R.id.xindepengyou)
    void xindepengyou() {
        startActivity(new Intent(MingPianHaoYouActivity.this, NewFriendActivity.class));
    }


    @OnClick(R.id.saomiao)
    void saomiao() {
        Intent intent=new Intent(MingPianHaoYouActivity.this, CaptureActivity.class);
        intent.putExtra("haoyou","haoyou");
        startActivityForResult(intent,SCAN_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        searchText=PreferencesUtils.getString(MingPianHaoYouActivity.this,"haoyou");
        if(searchText!=null&&!searchText.equals("")){
            Intent intent = new Intent(MingPianHaoYouActivity.this, SouSuoJIeGuoActivity.class);
            intent.putExtra("number", searchText);
            startActivity(intent);
        }
        else {
            ToastUtils.showLong("二维码无效");
        }
    }


    @OnClick(R.id.wodemingpian)
    void wodemingpian() {
        dlg.show();
        dlg.setContentView(R.layout.alert_dialog);
        dlg.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        ImageView imageView=dlg.findViewById(R.id.mingpian);
        TextView textView=dlg.findViewById(R.id.share);
        final String url = BaseUrl.BASE_URL + "user/qrcode/image?token_id=" + PreferencesUtils.getString(this, "token_id");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Bitmap bitmap = ToImg3.returnBitMap(url);
                            String imageName = System.currentTimeMillis()+".png";
                            File file = ToImg4.saveFile(bitmap, imageName);
                            String absolutePath = file.getAbsolutePath();
                            ShareFileUtils.shareImageToWeChat(MingPianHaoYouActivity.this,absolutePath);
                        }
                        catch (Exception e){
                            ToastUtils.showLong(e.toString());
                        }
                    }
                }).start();
            }
        });
        Log.d("创建活动",url);
        Picasso.with(this).load(url).into(imageView);
        dlg.setCancelable(true);
    }



    @OnClick(R.id.tianjiagenjin)
    void tianjiagenjin() {
        Intent intent = new Intent(MingPianHaoYouActivity.this, SouSuoJIeGuoActivity.class);
        searchText = mTxtShanghu.getText().toString();
        if(searchText!=null&&!searchText.equals("")){
            intent.putExtra("number", searchText);
            startActivity(intent);
        }
        else {
            ToastUtils.showLong("请输入搜索内容");
        }
    }


    @Override
    public int setLayout() {
        return R.layout.activity_ming_pian_hao_you;
    }

    @Override
    public void init() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 退出activity的动画效果不起作用，要在java代码里写
     * 复写activity的finish方法，在overridePendingTransition中写入自己的动画效果
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }




    @Override
    public void onRefresh() {
        getData();
    }
}
