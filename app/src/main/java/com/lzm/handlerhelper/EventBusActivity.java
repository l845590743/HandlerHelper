package com.lzm.handlerhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by lzm on 2017/8/25.
 */
public class EventBusActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(EventBusActivity.this, EventBus2Activity.class);
        startActivity(intent);
    }

    /**
     * 需求：从a界面跳转b界面 b界面发送一个消息到a界面
     * a界面收到消息之后 去后台请求数据然后展示出来
     *
     *
     */

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void GetDataEvent(MessageEvent messageEvent)     //必须puliic 否则报异常
    {

        //请求服务器拿数据
        EventBus.getDefault().post(new ShowMessageEvent("拿到数据 展示出来！"));

        //EventBus.getDefault().postSticky();
        // 发送一个粘性的消息，可以在发送消息之后再注册 也是可以收到消息的 但是只能收到一个最新发送的一条
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ShowDataEvent(ShowMessageEvent showMessageEvent)
    {
        Toast.makeText(getApplicationContext(),showMessageEvent.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
