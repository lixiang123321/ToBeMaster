package com.nosuchserver.tobemaster.handlerlooper;

public class Handler {

    public void sendMessage(Message msg) {
        Looper looper = Looper.myLooper();
        looper.queue.enqueueMessage(msg);
    }

    public void handleMessage(Message msg) {

    }

}
