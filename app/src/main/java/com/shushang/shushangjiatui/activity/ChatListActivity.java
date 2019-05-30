package com.shushang.shushangjiatui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseActivity2;

import java.util.List;

public class ChatListActivity  extends BaseActivity2 {

    private EaseConversationListFragment easeConversationListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public int setLayout() {
        return R.layout.activity_chat_list;
    }


    public void init(){
        easeConversationListFragment=new EaseConversationListFragment();
        easeConversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                Intent intent=new Intent(ChatListActivity.this,ChatActivity.class);
                //传入参数
                // EaseUI封装的聊天界面需要这两个参数，聊天者的username，以及聊天类型，单聊还是群聊
                intent.putExtra("userId", conversation.conversationId());
                intent.putExtra("chatType", EMMessage.ChatType.Chat);
                startActivity(intent);
//                Bundle args=new Bundle();
//                args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
//                args.putString(EaseConstant.EXTRA_USER_ID,c);
////                Log.d("asdas",conversation.conversationId());
//                intent.putExtra("conversation",args);
//                startActivity(intent);
            }
        });

        getSupportFragmentManager().beginTransaction()
                .add(R.id.my_conversation_list,easeConversationListFragment)
                .commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


    EMMessageListener messageListener=new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> list) {
            //接收到新的消息
            refreshUIWithMessage();
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageRead(List<EMMessage> list) {

        }

        @Override
        public void onMessageDelivered(List<EMMessage> list) {

        }

        @Override
        public void onMessageRecalled(List<EMMessage> list) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    };

    private void refreshUIWithMessage() {
        runOnUiThread(new Runnable() {
            public void run() {
                // refresh unread count
                // refresh conversation list
                if (easeConversationListFragment != null) {
                    easeConversationListFragment.refresh();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EMClient.getInstance().chatManager().removeMessageListener(messageListener);
    }

}
