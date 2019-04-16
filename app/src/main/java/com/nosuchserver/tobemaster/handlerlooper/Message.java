package com.nosuchserver.tobemaster.handlerlooper;

public class Message {

    private String what;

    private Object object;

    Handler target;

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
