package com.nosuchserver.tobemaster.handlerlooper;

public abstract class BaseMessageQueue {

    public abstract void enqueueMessage(Message msg);

    public abstract Message next();

}