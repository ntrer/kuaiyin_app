package com.shushang.shushangjiatui.activity;

import android.os.Bundle;

import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseActivity2;
import com.shushang.shushangjiatui.fragment.MyChatFragment;

public class ChatActivity extends BaseActivity2 {

    // 当前聊天的 ID
    private String mChatId;
    private MyChatFragment chatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这里直接使用EaseUI封装好的聊天界面
        chatFragment = new MyChatFragment();
        // 将参数传递给聊天界面
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.ec_layout_container, chatFragment).commit();

    }

    @Override
    public int setLayout() {
        return R.layout.activity_chat;
    }

    @Override
    public void init() {

    }
}
