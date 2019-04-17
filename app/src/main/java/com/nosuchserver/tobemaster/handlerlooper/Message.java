package com.nosuchserver.tobemaster.handlerlooper;

public class Message {

    private int what;

    private Object object;

    Handler target;

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
