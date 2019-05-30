package com.shushang.shushangjiatui.fragment.FourFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.activity.CaoGaoActivity;
import com.shushang.shushangjiatui.activity.ChatListActivity;
import com.shushang.shushangjiatui.activity.EditUserActivity;
import com.shushang.shushangjiatui.activity.JiaDouActivity;
import com.shushang.shushangjiatui.activity.NewFriendActivity;
import com.shushang.shushangjiatui.activity.SettingActivity;
import com.shushang.shushangjiatui.activity.WoDeMingPianHaoYouActivity;
import com.shushang.shushangjiatui.activity.YouHuiQuanActivity;
import com.shushang.shushangjiatui.base.BaseFragment;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.bean.GeRen;
import com.shushang.shushangjiatui.bean.Image;
import com.shushang.shushangjiatui.bean.XinPengYou;
import com.shushang.shushangjiatui.ui.ActionSheetDialog;
import com.shushang.shushangjiatui.ui.ExtAlertDialog;
import com.shushang.shushangjiatui.utils.Json.JSONUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.CallBackUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.OkhttpUtil;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

public class FourFragment extends BaseFragment {
    private static final int SCAN_OPEN_PHONE = 1001;
    private static final int PHONE_CAMERA = 1002;
    private static final int PHONE_CROP = 1003;
    private static final int OPEN_ACTIVITY = 1004;
    @BindView(R.id.setting)
    ImageView mSetting;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.shenfen)
    TextView mShenfen;
    @BindView(R.id.phone)
    TextView mPhone;
    @BindView(R.id.jiadoushuliang)
    TextView mJiadoushuliang;
    @BindView(R.id.jiadou)
    LinearLayout mJiadou;
    @BindView(R.id.haoyoushuliang)
    TextView mHaoyoushuliang;
    @BindView(R.id.haoyou)
    LinearLayout mHaoyou;
    @BindView(R.id.jilushuliang)
    TextView mJilushuliang;
    @BindView(R.id.liaotian)
    LinearLayout mLiaotian;
    @BindView(R.id.caogaoshuliang)
    TextView mCaogaoshuliang;
    @BindView(R.id.caogao)
    LinearLayout mCaogao;
    @BindView(R.id.youhuiquanshuliang)
    TextView mYouhuiquanshuliang;
    @BindView(R.id.youhuiquan)
    LinearLayout mYouhuiquan;
    Unbinder unbinder;
    @BindView(R.id.wode)
    ImageView mWode;
    Unbinder unbinder1;
    @BindView(R.id.status_bar_fix)
    View mStatusBarFix;
    @BindView(R.id.newfriend)
    RelativeLayout mNewfriend;
    @BindView(R.id.jichuxinxi)
    RelativeLayout mJichuxinxi;
    private String username, position, phone;
    private String token_id;
    private GeRen.DataBean.MerchantUserBean merchantUser;
    private Uri mCutUri;
    private Dialog mRequestDialog;
    private Image image;
    private boolean isFirst=true;
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        initStateBar();
        username = PreferencesUtils.getString(getActivity(), "userName");
        position = PreferencesUtils.getString(getActivity(), "position");
        phone = PreferencesUtils.getString(getActivity(), "cardMobileNumber");
        mName.setText(username);
        mPhone.setText(phone);
        mShenfen.setText(position);
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        mRequestDialog = ExtAlertDialog.creatRequestDialog(getActivity(), "请求中...");
        mRequestDialog.setCancelable(false);
//        getData();
        getFriend();
        getGenRenXinXi();
        EventBus.getDefault().register(this);
    }

    private void getGenRenXinXi() {
        String url = BaseUrl.BASE_URL + "user/personalCenter?token_id=" + token_id + "&loginOS=1";
        Log.d("创建活动", url);
        HashMap<String, String> paramsMap = new HashMap<>();
        OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                ToastUtils.showLong(e.toString());
            }

            @Override
            public void onResponse(String response) {
                Log.d("创建活动", response);
                if (response != null) {
                    try {
                        GeRen yiXiangJin = JSONUtil.fromJson(response, GeRen.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            if (yiXiangJin.getData() != null) {
                                merchantUser = yiXiangJin.getData().getMerchantUser();
                                showGeRen(merchantUser);
                                isFirst=false;
                            }
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(getActivity(), "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });
    }

    private void showGeRen(GeRen.DataBean.MerchantUserBean merchantUser) {
        if (merchantUser.getGoldCoin() > 0) {
            mJiadoushuliang.setVisibility(View.VISIBLE);
            mJiadoushuliang.setText(merchantUser.getGoldCoin() + "");
        } else {
            mJiadoushuliang.setVisibility(View.GONE);
        }

        if (merchantUser.getDraftFollowUpNum() > 0) {
            mCaogaoshuliang.setVisibility(View.VISIBLE);
            mCaogaoshuliang.setText(merchantUser.getDraftFollowUpNum() + "");
        } else {
            mCaogaoshuliang.setVisibility(View.GONE);
        }

        if (merchantUser.getCouponNum() > 0) {
            mYouhuiquanshuliang.setVisibility(View.VISIBLE);
            mYouhuiquanshuliang.setText(merchantUser.getCouponNum() + "");
        } else {
            mYouhuiquanshuliang.setVisibility(View.GONE);
        }

        if (merchantUser.getGoodFriendNum() > 0) {
            mHaoyoushuliang.setVisibility(View.VISIBLE);
            mHaoyoushuliang.setText(merchantUser.getGoodFriendNum() + "");
        } else {
            mHaoyoushuliang.setVisibility(View.GONE);
        }
        mName.setText(merchantUser.getUserName());
        if(isFirst){
            String url = BaseUrl.BASE_URL + "image/show/?token_id=" + PreferencesUtils.getString(mContext, "token_id") + "&fileId=" + merchantUser.getHeadPortraitImage() + "&loginOS=1&imgType=1";
            Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(mAvatar);
            Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(mWode);
        }
    }

    private void getFriend() {
        String url = BaseUrl.BASE_URL + "user/friendreq/list?token_id=" + token_id + "&loginOS=1&page=1&rows=10";
        Log.d("创建活动", url);
        HashMap<String, String> paramsMap = new HashMap<>();
        OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                ToastUtils.showLong(e.toString());
            }

            @Override
            public void onResponse(String response) {
                Log.d("创建活动", response);
                if (response != null) {
                    try {
                        XinPengYou yiXiangJin = JSONUtil.fromJson(response, XinPengYou.class);
                        if (yiXiangJin.getRet().equals("200")) {
                            if (yiXiangJin.getData().getIsExamine().equals("1")) {
                                mNewfriend.setVisibility(View.VISIBLE);
                            } else {
                                mNewfriend.setVisibility(View.GONE);
                            }
                        } else if (yiXiangJin.getRet().equals("201")) {
                            Toast.makeText(getActivity(), "" + yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("出错了", e.toString());
                    }
                }

            }
        });
    }


    private void getData() {
        String url = BaseUrl.BASE_URL + "image/show/?token_id=" + PreferencesUtils.getString(mContext, "token_id") + "&fileId=" + PreferencesUtils.getString(getActivity(), "avatar") + "&loginOS=1&imgType=1";
        Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(mAvatar);
        Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(mWode);
    }

    /**
     * 设置状态栏
     */
    private void initStateBar() {
        mStatusBarFix.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStateBarHeight(getActivity())));//填充状态栏
    }


    /**
     * 得到状态栏高度
     *
     * @param a
     * @return
     */
    public static int getStateBarHeight(Activity a) {
        int result = 0;
        int resourceId = a.getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = a.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_four, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        StatusBarCompat.setStatusBarColor(getActivity(),getResources().getColor(R.color.toolbar));
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if (messageEvent.getMessage().equals("通过")) {
            getFriend();
        }
        else  if(messageEvent.getMessage().equals("编辑用户信息")){
              getGenRenXinXi();
        }
        else  if(messageEvent.getMessage().equals("草稿")){
            getGenRenXinXi();
        }
    }


    @OnClick(R.id.avatar)
    void avatar() {
        new ActionSheetDialog(getActivity())
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("相册", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Intent intent = new Intent(Intent.ACTION_PICK, null);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                startActivityForResult(intent, SCAN_OPEN_PHONE);
                            }
                        })
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                openCamera();
                            }
                        })
                .show();
    }


    private void openCamera() {
        //创建一个file，用来存储拍照后的照片
        File outputfile = new File(getActivity().getExternalCacheDir(), "output.png");
        try {
            if (outputfile.exists()) {
                outputfile.delete();//删除
            }
            outputfile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Uri imageuri;
        if (Build.VERSION.SDK_INT >= 24) {
            imageuri = FileProvider.getUriForFile(getActivity(),
                    getActivity().getPackageName() + ".fileProvider", //可以是任意字符串
                    outputfile);
        } else {
            imageuri = Uri.fromFile(outputfile);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
        startActivityForResult(intent, PHONE_CAMERA);
    }


    @OnClick(R.id.jiadou)
    void jiadou() {

        startActivityForResult(new Intent(getContext(), JiaDouActivity.class),OPEN_ACTIVITY);
    }


    @OnClick(R.id.newfriend)
    void newfriend() {
        startActivityForResult(new Intent(getContext(), NewFriendActivity.class),OPEN_ACTIVITY);
    }

    @OnClick(R.id.jichuxinxi)
    void jichuxinxi() {
        startActivityForResult(new Intent(getContext(), EditUserActivity.class),OPEN_ACTIVITY);
    }

    @OnClick(R.id.liaotian)
    void liaotian() {
        startActivityForResult(new Intent(getContext(), ChatListActivity.class),OPEN_ACTIVITY);
    }


    @OnClick(R.id.haoyou)
    void haoyou() {
        startActivityForResult(new Intent(getContext(), WoDeMingPianHaoYouActivity.class),OPEN_ACTIVITY);
    }

    @OnClick(R.id.caogao)
    void caogao() {
        startActivityForResult(new Intent(getContext(), CaoGaoActivity.class),OPEN_ACTIVITY);
    }


    @OnClick(R.id.setting)
    void setting() {
        startActivity(new Intent(getContext(), SettingActivity.class));
    }

    @OnClick(R.id.youhuiquan)
    void youhuiquan() {
        startActivityForResult(new Intent(getContext(), YouHuiQuanActivity.class),OPEN_ACTIVITY);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SCAN_OPEN_PHONE: //从相册图片后返回的uri
                    //启动裁剪
                    startActivityForResult(CutForPhoto(data.getData()), PHONE_CROP);
                    break;
                case PHONE_CAMERA: //相机返回的 uri
                    //启动裁剪
                    String path = getActivity().getExternalCacheDir().getPath();
                    String name = "output.png";
                    startActivityForResult(CutForCamera(path, name), PHONE_CROP);
                    break;
                case PHONE_CROP:
                    try {
                        //获取裁剪后的图片，并显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(
                                getActivity().getContentResolver().openInputStream(mCutUri));
                        File file = getFile(bitmap);
                        upLoadImg(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        else if(requestCode==OPEN_ACTIVITY){
            getGenRenXinXi();
            getFriend();
        }
    }


    private void upLoadImg(File file) {
        mRequestDialog.show();
        String url = BaseUrl.BASE_URL + "/upload/image/?token_id=" + token_id + "&loginOS=1&isPortrait=1";
        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("zhaopian", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        RequestBody body = builder.build();
        final Request request = new Request.Builder().url(url).post(body).build();
        try {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (mRequestDialog != null && mRequestDialog.isShowing()) {
                        mRequestDialog.dismiss();
                    }
//                    Log.d("创建活动",response.body().string());
                    image = JSONUtil.fromJson(response.body().string(), Image.class);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String url = BaseUrl.BASE_URL + "image/show/?token_id=" + PreferencesUtils.getString(mContext, "token_id") + "&fileId=" + image.getData().getFileId() + "&loginOS=1&imgType=1";
                            Log.d("创建活动", url);
                            Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(mAvatar);
                            Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).placeholder(R.mipmap.touxiang).into(mWode);
                        }
                    });

                }

                @Override
                public void onFailure(Call call, IOException e) {
                    if (mRequestDialog != null && mRequestDialog.isShowing()) {
                        mRequestDialog.dismiss();
                    }
                    Log.d("上传失败", e.toString());
                }
            });
        } catch (Exception e) {
            if (mRequestDialog != null && mRequestDialog.isShowing()) {
                mRequestDialog.dismiss();
            }
            ToastUtils.showLong("上传失败");
        }


    }


    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只解析图片边沿，获取宽高
        BitmapFactory.decodeFile(filePath, options);
        // 计算缩放比
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        // 完整解析图片返回bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }


    /**
     * 获取图片的旋转角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 将图片按照指定的角度进行旋转
     *
     * @param bitmap 需要旋转的图片
     * @param degree 指定的旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bitmap, int degree) {
        if (bitmap != null) {
            // 根据旋转角度，生成旋转矩阵
            Matrix matrix = new Matrix();
            matrix.postRotate(degree);
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//        if (bitmap != null & !bitmap.isRecycled()) {
//            bitmap.recycle();
//        }
            return newBitmap;
        } else {
            return null;
        }

    }


    private File getFile(Bitmap bitmap) {
        String pictureDir = "";
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ByteArrayOutputStream baos = null;
        File file = null;
        try {
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] byteArray = baos.toByteArray();
            String saveDir = Environment.getExternalStorageDirectory()
                    + "/dreamtownImage";
            File dir = new File(saveDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            file = new File(saveDir, "photo.jpg");
            file.delete();
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(byteArray);
            pictureDir = file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        return file;
    }


    private Intent CutForPhoto(Uri uri) {
        try {
            //直接裁剪
            Intent intent = new Intent("com.android.camera.action.CROP");
            //设置裁剪之后的图片路径文件
            File cutfile = new File(Environment.getExternalStorageDirectory().getPath(),
                    "cutcamera.png"); //随便命名一个
            if (cutfile.exists()) { //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            cutfile.createNewFile();
            //初始化 uri
            Uri imageUri = uri; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            outputUri = Uri.fromFile(cutfile);
            mCutUri = outputUri;
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("crop", true);
            // aspectX,aspectY 是宽高的比例，这里设置正方形
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            //设置要裁剪的宽高
            intent.putExtra("outputX", ConvertUtils.dp2px(getActivity(), 200)); //200dp
            intent.putExtra("outputY", ConvertUtils.dp2px(getActivity(), 200));
            intent.putExtra("scale", true);
            //如果图片过大，会导致oom，这里设置为false
            intent.putExtra("return-data", false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }
            if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }
            intent.putExtra("noFaceDetection", true);
            //压缩图片
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            return intent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Intent CutForCamera(String camerapath, String imgname) {
        try {

            //设置裁剪之后的图片路径文件
            File cutfile = new File(Environment.getExternalStorageDirectory().getPath(),
                    "cutcamera.png"); //随便命名一个
            if (cutfile.exists()) { //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            cutfile.createNewFile();
            //初始化 uri
            Uri imageUri = null; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            Intent intent = new Intent("com.android.camera.action.CROP");
            //拍照留下的图片
            File camerafile = new File(camerapath, imgname);
            if (Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                imageUri = FileProvider.getUriForFile(getActivity(),
                        getActivity().getPackageName() + ".fileProvider",
                        camerafile);
            } else {
                imageUri = Uri.fromFile(camerafile);
            }
            outputUri = Uri.fromFile(cutfile);
            //把这个 uri 提供出去，就可以解析成 bitmap了
            mCutUri = outputUri;
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("crop", true);
            // aspectX,aspectY 是宽高的比例，这里设置正方形
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            //设置要裁剪的宽高
            intent.putExtra("outputX", ConvertUtils.dp2px(getActivity(), 200));
            intent.putExtra("outputY", ConvertUtils.dp2px(getActivity(), 200));
            intent.putExtra("scale", true);
            //如果图片过大，会导致oom，这里设置为false
            intent.putExtra("return-data", false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }
            if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }
            intent.putExtra("noFaceDetection", true);
            //压缩图片
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            return intent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}