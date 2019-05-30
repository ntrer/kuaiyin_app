package com.shushang.shushangjiatui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.ToastUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.shushang.shushangjiatui.base.BaseActivity;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.base.PermissionListener;
import com.shushang.shushangjiatui.fragment.FirstFragment.FirstFragment;
import com.shushang.shushangjiatui.fragment.FourFragment.FourFragment;
import com.shushang.shushangjiatui.fragment.SecondFragment.SecondFragment;
import com.shushang.shushangjiatui.fragment.ThirdFragment.ThirdFragment;
import com.shushang.shushangjiatui.ui.BottomNavigationViewHelper;
import com.shushang.shushangjiatui.ui.ExtAlertDialog;
import com.shushang.shushangjiatui.utils.ActivityManager.ActivityStackManager;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private FirstFragment mFirstFragment;
    private SecondFragment mSecondFragment;
    private ThirdFragment mThirdFragment;
    private FourFragment mFourFragment;
    private BottomNavigationView mNavigationView;
    private int lastfragment;//用于记录上个选择的Fragment
    private static final int GPS_REQUEST_CODE = 0x100;
    private Fragment[] mFragments;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new MyAMapLocationListener();
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private boolean oPen = false;
    //退出时的时间
    private long mExitTime;
    private EMClient mEMClient = EMClient.getInstance();
    private  String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            FragmentManager manager = getSupportFragmentManager();
            manager.popBackStackImmediate(null, 1);
        }
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        permission();
        oPen = isOPen(MainActivity.this);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            if (!oPen) {
                ToastUtils.showLong("如果定位失败，请手动打开GPS定位");
            }
        }
        EventBus.getDefault().register(this);
    }


    private void reGetPermission() {
        ExtAlertDialog.showSureDlg(MainActivity.this, "警告", "权限被拒绝，部分功能将无法使用，请重新授予权限", "确定", new ExtAlertDialog.IExtDlgClick() {
            @Override
            public void Oclick(int result) {
                if (result == 1) {
                    permission();
                }
            }
        });
    }


    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        mNavigationView = findViewById(R.id.navigation_fragment);
        mNavigationView.setItemIconTintList(null);
        initFragment();
        username = PreferencesUtils.getString(MainActivity.this, "username");
        Log.d("username",username);
        Login();
    }


    private void Login() {
        mEMClient.login(username, "#ssayeasemob8837", new EMCallBack() {
            @Override
            public void onSuccess() {
                ToastUtils.showLong("登陆成功");
            }

            @Override
            public void onError(int i, String s) {
                ToastUtils.showLong("登陆失败");
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });



//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    final String username = "misaki3";
//                    String password = "123";
//                    EMClient.getInstance().createAccount(username, password);
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_LONG).show();
//                        }
//                    });
//                } catch (final HyphenateException e) {
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if(messageEvent.getMessage().equals("退出")){
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    //初始化fragment和fragment数组
    private void initFragment() {
        mFirstFragment = new FirstFragment();
        mSecondFragment = new SecondFragment();
        mThirdFragment = new ThirdFragment();
        mFourFragment = new FourFragment();
        mFragments = new Fragment[]{mFirstFragment, mSecondFragment, mThirdFragment, mFourFragment};
        lastfragment = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mFirstFragment, "tag1").show(mFirstFragment).commit();
        BottomNavigationViewHelper.disableShiftMode(mNavigationView);
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_fragment_zero: {
                        if (lastfragment != 0) {
//                            setStatusBar(getResources().getColor(R.color.white));
                            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                            switchFragment(lastfragment, 0);
                            lastfragment = 0;

                        }
                        return true;

                    }
                    case R.id.navigation_fragment_one: {
                        if (lastfragment != 1) {
//                            setStatusBar(getResources().getColor(R.color.white));
                            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                            switchFragment(lastfragment, 1);
                            lastfragment = 1;

                        }

                        return true;
                    }
                    case R.id.navigation_fragment_two: {
                        if (lastfragment != 2) {
//                            setStatusBar(getResources().getColor(R.color.white));
                            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                            switchFragment(lastfragment, 2);
                            lastfragment = 2;

                        }

                        return true;
                    }
                    case R.id.navigation_fragment_three: {
                        if (lastfragment != 3) {
//                            setStatusBar(getResources().getColor(R.color.toolbar));
                            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                            switchFragment(lastfragment, 3);
                            lastfragment = 3;

                        }

                        return true;
                    }

                }
                return false;
            }
        });


    }


    //切换Fragment
    private void switchFragment(int lastfragment, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(mFragments[lastfragment]);//隐藏上个Fragment
        if (mFragments[index].isAdded() == false) {
            transaction.add(R.id.fragment_container, mFragments[index], "tag" + index);


        }
        transaction.show(mFragments[index]).commitAllowingStateLoss();

    }

    private void initMap() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    private void permission() {
        requestRunPermisssion(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE
        }, new PermissionListener() {
            @Override
            public void onGranted() {
                initMap();
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                for (String permission : deniedPermission) {
                    reGetPermission();
                }
            }
        });
    }


    public static final boolean isOPen(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }

        return false;
    }


    private class MyAMapLocationListener implements AMapLocationListener {

        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
//                    mTextView3.setText(aMapLocation.getAddress());
//                    if (aMapLocation.getCity() != null) {
//                        mLoction.setText(aMapLocation.getCity());
//                    } else {
//                        ToastUtils.showLong("定位失败，请重新定位");
//                    }
                    PreferencesUtils.putString(getApplicationContext(),"Latitude",aMapLocation.getLatitude()+"");//纬度
                    PreferencesUtils.putString(getApplicationContext(),"longitude",aMapLocation.getLongitude()+"");//经度
                    Log.d("jingwei",aMapLocation.getLatitude()+"====="+aMapLocation.getLongitude());

                } else {
//                    ExtAlertDialog.showSureDlg2(MainActivity.this, "提醒", "您没有开启定位服务，功能无法正常使用", "开启",false, new ExtAlertDialog.IExtDlgClick() {
//                        @Override
//                        public void Oclick(int result) {
//                            if (result == 1) {
//                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                                startActivityForResult(intent,887);
//                            }
//
//                        }
//                    });
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==887){
            mLocationClient.startLocation();
        }
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //退出应用
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
//            MyApplication.getInstance().exitApp();
            ActivityStackManager.getActivityStackManager().popAllActivity();//remove all activity.
//            System.exit(0);
            finish();
        }
    }
}

