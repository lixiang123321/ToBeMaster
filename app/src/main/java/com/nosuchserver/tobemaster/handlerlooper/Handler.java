package com.nosuchserver.tobemaster.handlerlooper;

public class Handler {

    private Looper me;

    public Handler() {
        this(Looper.myLooper());
    }

    public Handler(Looper me) {
        this.me = me;
    }

    public void sendMessage(Message msg) {
        msg.target = this;
        me.queue.enqueueMessage(msg);
    }

    public void dispatchMessage(Message msg) {
        handleMessage(msg);
    }

    public void handleMessage(Message msg) {

    }

}
