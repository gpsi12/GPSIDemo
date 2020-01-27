package com.example.feature_chat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单聊天框
 */
public class ChatActivity extends Activity {
    private List<Msg> msgList = new ArrayList<>();
    private EditText input_text;
    private Button send;
    private RecyclerView msgRV;
    private MsgAdapter adapter;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatactivity);
        input_text = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgRV = findViewById(R.id.msg_recyclerview);
        adapter = new MsgAdapter(msgList);
        initMsg();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRV.setLayoutManager(linearLayoutManager);
        msgRV.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = input_text.getText().toString();
                String reContent = "重复 ".toString();
                if (!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRV.scrollToPosition(msgList.size()-1);
                    input_text.setText("");

                    Msg msg1 = new Msg(reContent+content,Msg.TYPE_RECEIVED);
                    msgList.add(msg1);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRV.scrollToPosition(msgList.size()-1);
                }
            }
        });
    }

    private void initMsg() {
        Msg msg1 = new Msg("你好",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("哈",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("fdsfsdfds",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
        Msg msg4 = new Msg("sagsatfgfdshad",Msg.TYPE_SENT);
        msgList.add(msg4);
    }
}
