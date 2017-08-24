package com.lzm.handlerhelper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * 主线程给子线程发消息:
 */

public class MainActivity extends AppCompatActivity {

    private Handler mSubHandler;
    private final static String TAG = "MainActivity";
    private Looper mLooper;
    private Button mSendMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSendMsg = (Button) findViewById(R.id.sendmsg);
        mSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.obj = "Main Thread send msg";
                mSubHandler.sendMessage(message);
            }
        });

        MyThread myThread = new MyThread();
        myThread.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mLooper != null) {
            mLooper.quit();
        }
    }

    class MyThread extends Thread {

        @Override
        public void run() {

            //1. 创建一个Looper对象(内部创建了MessageQueue,并将MessageQueue作 为Looper对象的成员,然后将Looper对象绑定到ThreadLocal中)
            Looper.prepare();

            mSubHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {

                    //处理主线程发来的消息
                    Log.d(TAG," msg: " + msg);
                }
            };

            //2. 获取当前Looper对象
            mLooper = Looper.myLooper();

            // 消息循环起来
            Looper.loop();
            Log.d(TAG,"子线程退出");
        }
    }
}
