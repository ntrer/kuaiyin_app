package com.shushang.shushangjiatui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseActivity2;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.bean.TianJIaHaoYou;
import com.shushang.shushangjiatui.ui.ActionSheetDialog;
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

public class TianJiaGenJinActivity extends BaseActivity2 implements DatePickerDialog.OnDateSetListener{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.arrow)
    ImageView mArrow;
    @BindView(R.id.genjinfangshi)
    TextView mGenjinfangshi;
    @BindView(R.id.arrow2)
    ImageView mArrow2;
    @BindView(R.id.genjinshijian)
    TextView mGenjinshijian;
    @BindView(R.id.genjin)
    TextView mGenjin;
    @BindView(R.id.genjinjieguo)
    EditText mGenjinjieguo;
    @BindView(R.id.submit)
    Button submit;
    private int select_year, select_mounth, select_day;
    private int now_year, now_mounth, now_day;
    private String followUpMode,draftRecord,followUpText,nextFollowUpTime;
    private Dialog mRequestDialog;
    private String token_id,id,openId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRequestDialog = ExtAlertDialog.creatRequestDialog(this,"请求中...");
        mRequestDialog.setCancelable(false);
        token_id = PreferencesUtils.getString(TianJiaGenJinActivity.this, "token_id");
        id=getIntent().getStringExtra("kehuId");
        openId=getIntent().getStringExtra("KeHuopenId");
    }

    @Override
    public int setLayout() {
        return R.layout.activity_tian_jia_gen_jin;
    }

    @Override
    public void init() {

    }


    @OnClick(R.id.genjinfangshi)
    void genjinfangshi() {
        new ActionSheetDialog(TianJiaGenJinActivity.this)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("电话", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                 followUpMode="1";
                                 mGenjinfangshi.setText("电话");
                            }
                        })
                .addSheetItem("微信", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                followUpMode="2";
                                mGenjinfangshi.setText("微信");
                            }
                        })
                .addSheetItem("QQ", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                followUpMode="3";
                                mGenjinfangshi.setText("QQ");
                            }
                        })
                .addSheetItem("面聊", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                followUpMode="4";
                                mGenjinfangshi.setText("面聊");
                            }
                        })
                .show();
    }


    @OnClick(R.id.genjinshijian)
    void genjinshijian() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                TianJiaGenJinActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setAccentColor(getResources().getColor(R.color.colorPrimary));
        dpd.setAutoHighlight(true);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }


    @OnClick(R.id.submit)
    void submit() {
        new ActionSheetDialog(TianJiaGenJinActivity.this)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("存为草稿", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                draftRecord ="1";
                                Submit();
                            }
                        })
                .addSheetItem("提交", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                draftRecord ="0";
                                Submit();
                            }
                        })
                .show();
    }

    private void Submit() {
        followUpText=mGenjinjieguo.getText().toString();
        nextFollowUpTime=mGenjinshijian.getText().toString();
        if(followUpText.equals("")||nextFollowUpTime.equals("")||followUpMode==null){
            ToastUtils.showLong("请填写完整");
            return;
        }
        else {
            mRequestDialog.show();
            String url = BaseUrl.BASE_URL + "user/followup/submit?token_id=" + token_id + "&loginOS=1&customerId="+id+"&openId="+openId;
            Log.d("创建活动", url);
            HashMap<String, String> paramsMap = new HashMap<>();
            paramsMap.put("nextFollowUpTime",nextFollowUpTime);
            paramsMap.put("followUpText",followUpText);
            paramsMap.put("followUpMode",followUpMode);
            paramsMap.put("draftRecord",draftRecord);
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
                                if(draftRecord.equals("0")){
                                    ToastUtils.showLong("跟进成功");
                                }
                                else {
                                    EventBus.getDefault().post(new MessageEvent("草稿"));
                                }
                                finish();
                            } else if (yiXiangJin.getRet().equals("201")) {
                                Toast.makeText(TianJiaGenJinActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
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
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                followUpText=mGenjinjieguo.getText().toString();
                nextFollowUpTime=mGenjinshijian.getText().toString();
                if(!followUpText.equals("")&&!nextFollowUpTime.equals("")&&followUpMode!=null){
                    ExtAlertDialog.showSureDlg(TianJiaGenJinActivity.this, "提醒", "是否存为草稿", "确认", new ExtAlertDialog.IExtDlgClick() {
                        @Override
                        public void Oclick(int result) {
                            if (result == 1) {
                                draftRecord ="1";
                                Submit();
                                finish();
                            }
                            else {
                                finish();
                            }

                        }
                    });
                }
                else {
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            followUpText=mGenjinjieguo.getText().toString();
            nextFollowUpTime=mGenjinshijian.getText().toString();
            if(!followUpText.equals("")&&!nextFollowUpTime.equals("")&&followUpMode!=null){
                ExtAlertDialog.showSureDlg(TianJiaGenJinActivity.this, "提醒", "是否存为草稿", "确认", new ExtAlertDialog.IExtDlgClick() {
                    @Override
                    public void Oclick(int result) {
                        if (result == 1) {
                            draftRecord ="1";
                            Submit();
                            finish();
                        }
                        else {
                            finish();
                        }

                    }
                });
            }
            else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
        if (select_year < now_year) {
            ToastUtils.showLong("请选择当前时间以后的年份");
        } else if (select_year == now_year && select_mounth < now_mounth) {
            ToastUtils.showLong("请选择当前时间以后的月份");
        } else if (select_mounth == now_mounth && select_day < now_day) {
            ToastUtils.showLong("请选择当前时间以后的日期");
        }
        else {
            String time = select_year + "-" + select_mounth + "-" + select_day ;
            Date date = new Date(select_year - 1900, select_mounth - 1, select_day);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
            Log.d("time", simpleDateFormat.format(date));
            mGenjinshijian.setText(simpleDateFormat.format(date));
        }

    }
}
