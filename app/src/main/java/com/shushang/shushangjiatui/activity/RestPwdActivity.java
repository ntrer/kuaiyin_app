package com.shushang.shushangjiatui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseActivity2;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.bean.TianJIaHaoYou;
import com.shushang.shushangjiatui.ui.ExtAlertDialog;
import com.shushang.shushangjiatui.utils.Json.JSONUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.CallBackUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.OkhttpUtil;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class RestPwdActivity extends BaseActivity2 {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.oldpwd)
    EditText mOldpwd;
    @BindView(R.id.newpwd)
    EditText mNewpwd;
    @BindView(R.id.confirmpwd)
    EditText mConfirmpwd;
    @BindView(R.id.reset)
    Button mReset;
    private Dialog mRequestDialog;
    private String oldPassword,newPassword,newPassword2;
    private String token_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        token_id = PreferencesUtils.getString(RestPwdActivity.this, "token_id");
        mRequestDialog = ExtAlertDialog.creatRequestDialog(this, "修改成功");
        mRequestDialog.setCancelable(false);
    }


    @Override
    public int setLayout() {
        return R.layout.activity_rest_pwd;
    }

    @Override
    public void init() {
    }


    @OnClick(R.id.reset)
    void reset(){
        oldPassword=mOldpwd.getText().toString();
        newPassword=mNewpwd.getText().toString();
        newPassword2=mConfirmpwd.getText().toString();
        if(oldPassword.equals("")||newPassword.equals("")||newPassword2.equals("")){
            ToastUtils.showLong("请填写完整信息");
            return;
        }

        if(!newPassword.equals(newPassword2)){
            ToastUtils.showLong("两次密码填写不一样");
            return;
        }

        Reset(oldPassword,newPassword,newPassword2);
    }

    private void Reset(String oldPassword, String newPassword, String newPassword2) {
        mRequestDialog.show();
        String url = BaseUrl.BASE_URL + "/user/update/pasw?token_id=" + token_id + "&loginOS=1";
        Log.d("创建活动", url);
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("oldPassWord", oldPassword);
        paramsMap.put("newPassWord", newPassword);
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
                if (response != null) {
                    try {
                        TianJIaHaoYou yiXiangJin = JSONUtil.fromJson(response, TianJIaHaoYou.class);
                        if (yiXiangJin.getRet().equals("200")) {
                          ToastUtils.showLong("密码修改成功");
                          finish();
                        } else if (yiXiangJin.getRet().equals("201")) {
                            if (mRequestDialog != null && mRequestDialog.isShowing()) {
                                mRequestDialog.dismiss();
                            }
                            Toast.makeText(RestPwdActivity.this, "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
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

}
