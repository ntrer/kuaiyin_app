package com.shushang.shushangjiatui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.shushang.shushangjiatui.LoginActivity;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseActivity2;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.bean.TianJIaHaoYou;
import com.shushang.shushangjiatui.ui.ExtAlertDialog;
import com.shushang.shushangjiatui.utils.Json.JSONUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.CallBackUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.OkhttpUtil;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class EditKehuXinXiActivity extends BaseActivity2 implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.shengri_text)
    TextView mShengriText;
    @BindView(R.id.shengri)
    RelativeLayout mShengri;
    @BindView(R.id.line_second)
    View mLineSecond;
    @BindView(R.id.wx_text)
    EditText mWxText;
    @BindView(R.id.wx)
    RelativeLayout mWx;
    @BindView(R.id.line_third)
    View mLineThird;
    @BindView(R.id.xiaoqudizhi_text)
    EditText mXiaoqudizhiText;
    @BindView(R.id.xiaoqudizhi)
    RelativeLayout mXiaoqudizhi;
    @BindView(R.id.line_five)
    View mLineFive;
    @BindView(R.id.zhaungxiufengge_text)
    EditText mZhaungxiufenggeText;
    @BindView(R.id.zhaungxiufengge)
    RelativeLayout mZhaungxiufengge;
    @BindView(R.id.line_six)
    View mLineSix;
    @BindView(R.id.yixiangxuqiu_text)
    EditText mYixiangxuqiuText;
    @BindView(R.id.yixiangxuqiu)
    RelativeLayout mYixiangxuqiu;
    @BindView(R.id.bianji)
    Button mBianji;
    private String token_id;
    private int id;
    private int select_year, select_mounth, select_day;
    private int now_year, now_mounth, now_day;
    private Dialog mRequestDialog;
    private String brthdayDate, wxNum, cellAddress, intentionalProduct, decorationStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRequestDialog = ExtAlertDialog.creatRequestDialog(this, "请求中...");
        mRequestDialog.setCancelable(false);
        token_id = PreferencesUtils.getString(EditKehuXinXiActivity.this, "token_id");
        id = PreferencesUtils.getInt(EditKehuXinXiActivity.this, "kehuId");
    }

    @Override
    public int setLayout() {
        return R.layout.activity_edit_kehu_xin_xi;
    }

    @Override
    public void init() {

    }


    @OnClick(R.id.shengri)
    void shengri() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                EditKehuXinXiActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setAccentColor(getResources().getColor(R.color.colorPrimary));
        dpd.setAutoHighlight(true);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }


    @OnClick(R.id.bianji)
    void bianji() {
        brthdayDate = mShengriText.getText().toString();
        wxNum = mWxText.getText().toString();
        cellAddress = mXiaoqudizhiText.getText().toString();
        intentionalProduct = mYixiangxuqiuText.getText().toString();
        decorationStyle = mZhaungxiufenggeText.getText().toString();
        mRequestDialog.show();
        String url = BaseUrl.BASE_URL + "user/customerinfo/update?token_id=" + token_id + "&loginOS=1&customerId=" + id;
        Log.d("创建活动", url);
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("brthdayDate", brthdayDate);
        paramsMap.put("wxNum", wxNum);
        paramsMap.put("cellAddress", cellAddress);
        paramsMap.put("intentionalProduct", intentionalProduct);
        paramsMap.put("decorationStyle", decorationStyle);
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
                            ToastUtils.showLong("编辑成功");
                            EventBus.getDefault().post(new MessageEvent("编辑"));
                            finish();
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(EditKehuXinXiActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        } else if (yiXiangJin.getRet().equals("202")) {
                            PreferencesUtils.putString(EditKehuXinXiActivity.this, "token_id", null);
                            EventBus.getDefault().post(new MessageEvent("退出客户"));
                            startActivity(new Intent(EditKehuXinXiActivity.this, LoginActivity.class));
                            finish();
                            Toast.makeText(EditKehuXinXiActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
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
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        select_year = year;
        select_mounth = monthOfYear + 1;
        select_day = dayOfMonth;
        Log.d("time", select_year + "---------" + select_mounth + "---------" + select_day);
        String time = select_year + "-" + select_mounth + "-" + select_day;
        Date date = new Date(select_year - 1900, select_mounth - 1, select_day);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        Log.d("time", simpleDateFormat.format(date));
        mShengriText.setText(simpleDateFormat.format(date));


    }
}
