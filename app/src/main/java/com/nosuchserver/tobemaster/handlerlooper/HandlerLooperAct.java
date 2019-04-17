package com.nosuchserver.tobemaster.handlerlooper;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.nosuchserver.tobemaster.base.activity.TestBaseActivity;
import com.nosuchserver.tobemaster.base.utils.TagLog;

import java.util.Random;
import java.util.UUID;

public class HandlerLooperAct extends TestBaseActivity {

    private Handler handler;

    public static void start(Context context) {
        context.startActivity(new Intent(context, HandlerLooperAct.class));
    }

    @Override
    protected void addViews(LinearLayout layout) {
        getButton(layout, "start Looper", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start Looper
                startLoop();
            }

        });
    }

    private void startLoop() {
        TagLog.i(TAG, "startLoop() : ");

        Looper.prepare();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                TagLog.i(TAG, "handleMessage() : "
                        + " Thread.currentThread() = " + Thread.currentThread().getName() + ","
                        + " msg.getWhat() = " + msg.getObject() + ","
                );
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        Message msg = new Message();
                        synchronized (UUID.class) {
                            msg.setObject(UUID.randomUUID().toString());
                        }
                        TagLog.i(TAG, "sendMessage() : "
                                + " Thread.currentThread() = " + Thread.currentThread().getName() + ","
                                + " msg.getWhat() = " + msg.getObject() + ","
                        );
                        handler.sendMessage(msg);
                        try {
                            Thread.sleep(new Random().nextInt(10000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }

        Looper.loop();
    }

}
