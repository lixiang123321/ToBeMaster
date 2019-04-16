package com.nosuchserver.tobemaster.handlerlooper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageQueue {

    private static final int MAX = 50;

    private Message[] messages = new Message[MAX];

    private int putIndex = 0;
    private int getIndex = 0;

    private int count = 0;

    private Lock lock;
    private Condition notEmpty;
    private Condition notFull;

    public MessageQueue() {
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void enqueueMessage(Message msg) {
        try {
            lock.lock();
            while (MAX == count) {
                notFull.await();
            }
            putIndex = putIndex == MAX ? 0 : putIndex;
            messages[putIndex] = msg;
            count++;
            notEmpty.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Message next() {
        Message msg = null;
        try {
            lock.lock();
            while (0 == count) {
                notEmpty.await();
            }
            getIndex = getIndex == MAX ? 0 : getIndex;
            messages[putIndex] = msg;
            count--;
            notFull.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return msg;
    }

}
