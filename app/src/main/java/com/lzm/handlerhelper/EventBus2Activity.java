package com.lzm.handlerhelper;

import android.app.Activity;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by lzm on 2017/8/25.
 */
public class EventBus2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().post(new MessageEvent("发消息给a界面"));
    }
}
