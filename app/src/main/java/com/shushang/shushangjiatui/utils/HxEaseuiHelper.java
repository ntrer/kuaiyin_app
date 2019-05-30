package com.shushang.shushangjiatui.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EaseNotifier;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.util.EMLog;
import com.shushang.shushangjiatui.Constant;
import com.shushang.shushangjiatui.MyApplication;
import com.shushang.shushangjiatui.base.BaseUrl;
import com.shushang.shushangjiatui.bean.ChatUser;
import com.shushang.shushangjiatui.db.InviteMessgeDao;
import com.shushang.shushangjiatui.db.UserDao;
import com.shushang.shushangjiatui.domain.RobotUser;
import com.shushang.shushangjiatui.utils.Json.JSONUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.CallBackUtil;
import com.shushang.shushangjiatui.utils.OkhttpUtils.OkhttpUtil;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/1/12.
 */

public class HxEaseuiHelper {

    private static HxEaseuiHelper instance = null;
    protected EMMessageListener messageListener = null;
    private Context appContext;
    private String username;
    private EaseUI easeUI;
    private InviteMessgeDao inviteMessgeDao;
    private UserDao userDao;
    private Map<String, EaseUser> contactList;
    private Map<String, RobotUser> robotList;
    private DemoModel demoModel = null;

    private String TAG="HxEaseuiHelper";

    public synchronized static HxEaseuiHelper getInstance() {
        if (instance == null) {
            instance = new HxEaseuiHelper();
        }
        return instance;
    }


    public void init(Context context) {
        demoModel = new DemoModel(context);
        EMOptions options = initChatOptions();
        //use default options if options is null
        if (EaseUI.getInstance().init(context, options)) {
            appContext = context;

            //获取easeui实例
            easeUI = EaseUI.getInstance();
            //初始化easeui
            easeUI.init(appContext,options);
            //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
            EMClient.getInstance().setDebugMode(true);
            setEaseUIProviders();
            //设置全局监听
            setGlobalListeners();

//            broadcastManager = LocalBroadcastManager.getInstance(appContext);
            initDbDao();

        }
    }


    private EMOptions initChatOptions(){
        EMOptions options = new EMOptions();
        // 设置Appkey，如果配置文件已经配置，这里可以不用设置
        options.setAppKey("1118190428216448#shushangjiatui");
//        options.setAppKey("1124161228178748#wzly");
        // set if accept the invitation automatically
        options.setAcceptInvitationAlways(false);
        // set if you need read ack
        options.setRequireAck(true);
        // set if you need delivery ack
        options.setRequireDeliveryAck(false);

        return options;
    }

    protected void setEaseUIProviders() {
        // set profile provider if you want easeUI to handle avatar and nickname
        easeUI.setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {

            @Override
            public EaseUser getUser(String username) {
                return getUserInfo(username);
            }
        });
    }

    private EaseUser getUserInfo(String username){
        //获取 EaseUser实例, 这里从内存中读取
        //如果你是从服务器中读读取到的，最好在本地进行缓存
        EaseUser user = null;
        //如果用户是本人，就设置自己的头像
        String currentUser = EMClient.getInstance().getCurrentUser();
        Log.d("asdasd",username+"========"+currentUser);
        if(username.equals(currentUser)){
            user=new EaseUser(username);
            user.setAvatar((String) SharedPreferencesUtils.getParam(appContext, APPConfig.USER_HEAD_IMG,""));
            user.setNickname((String)SharedPreferencesUtils.getParam(appContext, APPConfig.USER_NAME,"nike"));
            return user;
        }
//        if (user==null && getRobotList()!=null){
//            user=getRobotList().get(username);
//        }

        //收到别人的消息，设置别人的头像
        if (contactList!=null && contactList.containsKey(username)){
            user=contactList.get(username);
        }else { //如果内存中没有，则将本地数据库中的取出到内存中
            contactList=getContactList();
            user=contactList.get(username);
        }

        //如果用户不是你的联系人，则进行初始化
        if(user == null){
            user = new EaseUser(username);
            EaseCommonUtils.setUserInitialLetter(user);
        }else {
            if (TextUtils.isEmpty(user.getNickname())){//如果名字为空，则显示环信号码
                user.setNickname(user.getUsername());
            }
        }
        //Log.i("zcb","头像："+user.getAvatar());

        return user;
    }

    private void initDbDao() {
        inviteMessgeDao = new InviteMessgeDao(appContext);
        userDao = new UserDao(appContext);
    }

    public Map<String, RobotUser> getRobotList() {
        if (isLoggedIn() && robotList == null) {
            robotList = demoModel.getRobotList();
        }
        return robotList;
    }

    /**
     * get current user's id
     */
    public String getCurrentUsernName(){
        if(username == null){
            username = (String)SharedPreferencesUtils.getParam(appContext, Constant.HX_CURRENT_USER_ID,"");
        }
        return username;
    }

    /**
     *获取所有的联系人信息
     *
     * @return
     */
    public Map<String, EaseUser> getContactList() {
        if (isLoggedIn() && contactList == null) {
            contactList = demoModel.getContactList();
        }

        // return a empty non-null object to avoid app crash
        if(contactList == null){
            return new Hashtable<String, EaseUser>();
        }

        return contactList;
    }
    /**
     * if ever logged in
     *
     * @return
     */
    public boolean isLoggedIn() {
        return EMClient.getInstance().isLoggedInBefore();
    }

    /**
     * set global listener
     */
    protected void setGlobalListeners(){
        registerMessageListener();
    }

    /**
     * Global listener
     * If this event already handled by an activity, you don't need handle it again
     * activityList.size() <= 0 means all activities already in background or not in Activity Stack
     */
    protected void registerMessageListener() {
        messageListener = new EMMessageListener() {
            private BroadcastReceiver broadCastReceiver = null;

            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                for (EMMessage message : messages) {
                    EMLog.d(TAG, "onMessageReceived id : " + message.getMsgId());

                    final String userName1 = message.getUserName();
                    final String hxIdFrom=message.getFrom();

                    String url = BaseUrl.BASE_URL + "user/info/tokenuuid?tokenUuid=" + userName1;
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
                                    ChatUser yiXiangJin = JSONUtil.fromJson(response, ChatUser.class);
                                    if (yiXiangJin.getRet().equals("200")) {
                                        String userName=yiXiangJin.getData();
                                        String url = BaseUrl.BASE_URL + "image/show/?token_id=" + PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "token_id") + "&tokenUuid="+userName1+ "&loginOS=1&imgType=1";
                                        getChatUser(userName,url,hxIdFrom);
                                    }
                                } catch (Exception e) {
                                    Log.d("出错了", e.toString());
                                }
                            }

                        }
                    });

                }
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                for (EMMessage message : messages) {
                    EMLog.d(TAG, "receive command message");
                    //get message body
                    //end of red packet code
                    //获取扩展属性 此处省略
                    //maybe you need get extension of your message
                    //message.getStringAttribute("");
                }
            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
            }

            @Override
            public void onMessageRecalled(List<EMMessage> list) {

            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {

            }
        };

        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }


    private void getChatUser(String userName,String userPic,String hxIdFrom) {
        System.out.println("helper接收到的用户名："+userName+"helper头像："+userPic);
        EaseUser easeUser=new EaseUser(hxIdFrom);
        easeUser.setAvatar(userPic);
        easeUser.setNickname(userName);

        //存入内存
        getContactList();
        contactList.put(hxIdFrom,easeUser);

        //存入db
        UserDao dao=new UserDao(appContext);
        List<EaseUser> users=new ArrayList<EaseUser>();
        users.add(easeUser);
        dao.saveContactList(users);
    }

    public EaseNotifier getNotifier(){
        return easeUI.getNotifier();
    }

}
