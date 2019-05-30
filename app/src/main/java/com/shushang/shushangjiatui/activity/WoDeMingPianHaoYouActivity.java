package com.shushang.shushangjiatui.activity;

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
import com.shushang.shushangjiatui.adapter.MingPianHaoYouAdapter;
import com.shushang.shushangjiatui.base.BaseActivity2;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.bean.WoDeHaoYou;
import com.shushang.shushangjiatui.utils.Json.JSONUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.CallBackUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.OkhttpUtil;
import com.shushang.shushangjiatui.utils.ShareFileUtils;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.shushang.shushangjiatui.utils.ToImg3;
import com.shushang.shushangjiatui.utils.ToImg4;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class WoDeMingPianHaoYouActivity extends BaseActivity2 implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.search)
    TextView mSearch;
    @BindView(R.id.txt_shanghu)
    AppCompatEditText mTxtShanghu;
    @BindView(R.id.search_input)
    RelativeLayout mSearchInput;
    @BindView(R.id.rv_recycleview)
    RecyclerView mRvRecycleview;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.tianjia)
    ImageView mTianjia;
    @BindView(R.id.ll_no_data)
    LinearLayout mLlNoData;
    private MingPianHaoYouAdapter mMingPianHaoYouAdapter;
    private List<String> data = new ArrayList<>();
    private List<WoDeHaoYou.DataBean.UserSaleInfoBean> userSaleInfo = new ArrayList<>();
    private String searchText;
    private String token_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSrlRefresh.setOnRefreshListener(this);
        token_id = PreferencesUtils.getString(WoDeMingPianHaoYouActivity.this, "token_id");
        getData(searchText);
        EventBus.getDefault().register(this);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_wo_de_ming_pian_hao_you;
    }

    @Override
    public void init() {

    }

    private void getData(String searchText) {
        mSrlRefresh.setRefreshing(true);
        String url = BaseUrl.BASE_URL + "user/friend/list?token_id=" + token_id + "&loginOS=1&page=1&rows=10";
        Log.d("创建活动", url);
        HashMap<String, String> paramsMap = new HashMap<>();
        if(searchText!=null&&!searchText.equals("")){
            paramsMap.put("phone",searchText);
        }
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
                        WoDeHaoYou yiXiangJin = JSONUtil.fromJson(response, WoDeHaoYou.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            userSaleInfo = yiXiangJin.getData().getUserSaleInfo();
                            if (userSaleInfo.size() != 0) {
                                Log.d("创建活动", "显示");
                                shouData(userSaleInfo);
                                mLlNoData.setVisibility(View.GONE);
                            } else {
                                Log.d("创建活动", "没显示");
                                shouData(userSaleInfo);
                                mLlNoData.setVisibility(View.VISIBLE);
                            }
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(WoDeMingPianHaoYouActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        else if(yiXiangJin.getMsg().equals("202")){
                            PreferencesUtils.putString(WoDeMingPianHaoYouActivity.this, "token_id", null);
                            EventBus.getDefault().post(new MessageEvent("退出"));
                            startActivity(new Intent(WoDeMingPianHaoYouActivity.this, LoginActivity.class));
                            finish();
                            Toast.makeText(WoDeMingPianHaoYouActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });
    }


    private void shouData(final List<WoDeHaoYou.DataBean.UserSaleInfoBean> data) {
        try {
            Log.d("创建活动", data.size()+"");
            mMingPianHaoYouAdapter = new MingPianHaoYouAdapter(R.layout.item_mingpianhaoyou, data);
            final LinearLayoutManager manager = new LinearLayoutManager(this);
            mRvRecycleview.setLayoutManager(manager);
            mRvRecycleview.setAdapter(mMingPianHaoYouAdapter);
            mRvRecycleview.addOnItemTouchListener(new OnItemChildClickListener() {
                @Override
                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                }

                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                    super.onItemChildClick(adapter, view, position);
                    int itemViewId = view.getId();
                    try {
                        if (itemViewId == R.id.fenxiang) {
                              shareImg(data.get(position).getTokenUuid());
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

    private void shareImg(final String tokenUuid) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = BaseUrl.BASE_URL + "user/qrcode/image?token_id=" + PreferencesUtils.getString(WoDeMingPianHaoYouActivity.this, "token_id")+"&btokenUuid="+tokenUuid;
                    Bitmap bitmap = ToImg3.returnBitMap(url);
                    String imageName = System.currentTimeMillis()+".png";
                    File file = ToImg4.saveFile(bitmap, imageName);
                    String absolutePath = file.getAbsolutePath();
                    ShareFileUtils.shareImageToWeChat(WoDeMingPianHaoYouActivity.this,absolutePath);
                }
                catch (Exception e){
                    ToastUtils.showLong(e.toString());
                }
            }
        }).start();
    }


    @OnClick(R.id.tianjia)
    void tianjia() {
        startActivity(new Intent(WoDeMingPianHaoYouActivity.this, MingPianHaoYouActivity.class));
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if (messageEvent.getMessage().equals("退出名片好友")) {
            EventBus.getDefault().post(new MessageEvent("退出"));
            finish();
        }
        else if(messageEvent.getMessage().equals("通过")){
            getData(searchText);
        }
    }

    @OnClick(R.id.search)
    void search() {
        searchText = mTxtShanghu.getText().toString();
        if(searchText!=null&&!searchText.equals("")){
          getData(searchText);
        }
        else {
            ToastUtils.showLong("请输入搜索内容");
        }
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
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onRefresh() {
        getData(searchText);
    }
}
