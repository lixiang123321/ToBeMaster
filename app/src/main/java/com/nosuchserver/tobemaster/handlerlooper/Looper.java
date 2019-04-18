package com.nosuchserver.tobemaster.handlerlooper;

public class Looper {

    BaseMessageQueue queue;

    private static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();

    private Looper(BaseMessageQueue queue) {
        this.queue = queue;
    }

    public static void prepare() {
        Looper looper = sThreadLocal.get();
        if (looper != null) {
            throw new RuntimeException("one thread can only call prepare() once.");
        }
        sThreadLocal.set(new Looper(new MessageQueue2()));
    }

    public static void loop() {
        Looper looper = myLooper();
        for (; ; ) {
            Message msg = looper.queue.next();
            if (null != msg) {
                msg.target.dispatchMessage(msg);
            }
        }
    }

    public static Looper myLooper() {
        Looper looper = sThreadLocal.get();
        if (null == looper) {
            throw new RuntimeException("Looper.prepare() should be call to create.");
        }
        return looper;
    }


}
