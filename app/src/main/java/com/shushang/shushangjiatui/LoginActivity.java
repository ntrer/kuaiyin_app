package com.shushang.shushangjiatui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shushang.shushangjiatui.base.BaseActivity;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.bean.Login3;
import com.shushang.shushangjiatui.utils.Json.JSONUtil;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.xys.libzxing.zxing.net.RestClient;
import com.xys.libzxing.zxing.net.callback.IError;
import com.xys.libzxing.zxing.net.callback.IFailure;
import com.xys.libzxing.zxing.net.callback.ISuccess;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.shanghu)
    TextView mShanghu;
    @BindView(R.id.txt_shanghu)
    AppCompatEditText mTxtShanghu;
    @BindView(R.id.zhanghao)
    TextView mZhanghao;
    @BindView(R.id.txt_username)
    AppCompatEditText mTxtUsername;
    @BindView(R.id.mima)
    TextView mMima;
    @BindView(R.id.txt_pwd)
    AppCompatEditText mTxtPwd;
    @BindView(R.id.login)
    Button mLogin;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        mContext = MyApplication.getInstance().getApplicationContext();
        initData();
    }


    private void initData() {
        if (PreferencesUtils.getString(mContext, "token_id")!=null) {
            Log.d("sdad",PreferencesUtils.getString(mContext, "token_id"));
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }



    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }



    @Override
    public void init() {

    }



    @OnClick(R.id.login)
    void Login(){
        checkLogin();
//        startActivity(new Intent(LoginActivity.this,MainActivity.class));
//        finish();
    }



    private void checkLogin() {
        final String company = mTxtShanghu.getText().toString();
        final String user_name = mTxtUsername.getText().toString();
//            final String company = AESCrypt.encrypt(mTxtCompany.getText().toString(),"");
//            final String user_name = AESCrypt.encrypt(mTxtUserName.getText().toString(),"");
        final String password = mTxtPwd.getText().toString();
        if (company.equals("")) {
            Toast.makeText(mContext, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (user_name.equals("")) {
            Toast.makeText(mContext, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (password.equals("")) {
            Toast.makeText(mContext, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            Toast.makeText(mContext, "登录中...", Toast.LENGTH_LONG).show();
        }

        if(!company.equals("")&&!user_name.equals("")&&!password.equals("")){
//           if(LoginActivity2.this!=null&&!LoginActivity2.this.isDestroyed()&&!LoginActivity2.this.isFinishing()){
//               mRequestDialog.show();
//           }
            String url = BaseUrl.BASE_URL +"user/login?userAccount=" + user_name + "&merchantCode=" + company + "&userPassword=" + password + "&loginOS=" + "1";
            Log.d("response", url);
            try {
                RestClient.builder()
                        .url(url)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                Log.d("response",response);
                                try {
                                    Login3 login = JSONUtil.fromJson(response, Login3.class);
                                    if(login.getRet().equals("200")){
                                        Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                                        PreferencesUtils.putString(mContext, "token_id",login.getData().getTokenId());
                                        PreferencesUtils.putString(mContext, "userName",login.getData().getUserName());
                                        PreferencesUtils.putString(mContext, "cardMobileNumber",login.getData().getCardMobileNumber());
                                        PreferencesUtils.putString(mContext, "position",login.getData().getPosition());
                                        PreferencesUtils.putString(mContext, "address",login.getData().getAddress());
                                        PreferencesUtils.putString(mContext, "UserId",String.valueOf(login.getData().getId()));
                                        PreferencesUtils.putString(mContext, "openId",String.valueOf(login.getData().getOpenId()));
                                        PreferencesUtils.putString(mContext, "username",String.valueOf(login.getData().getTokenUuid()));
                                        PreferencesUtils.putString(mContext, "avatar",login.getData().getHeadPortraitImage()+"");
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                        finish();
                                    }
                                    else if(Integer.parseInt(login.getRet())==201) {
                                        Toast.makeText(mContext, ""+login.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){
                                    Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        }).failure(new IFailure() {
                    @Override
                    public void onFailure() {

                        Toast.makeText(mContext, "请求超时", Toast.LENGTH_SHORT).show();
                    }
                }).error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(mContext, ""+msg, Toast.LENGTH_SHORT).show();
                    }
                })
                        .build()
                        .get();
            }
            catch (Exception e){
                Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
            }

        }

    }



    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};

    public static String toHexString(byte[] b) {
        //String to byte
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            return toHexString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }



}
