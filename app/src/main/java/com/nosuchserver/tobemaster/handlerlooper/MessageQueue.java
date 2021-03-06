package com.nosuchserver.tobemaster.handlerlooper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过数组实现MessageQueue
 */
public class MessageQueue extends BaseMessageQueue {

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

    @Override
    public void enqueueMessage(Message msg) {
        try {
            lock.lock();
            while (MAX == count) {
                notFull.await();
            }
            messages[putIndex] = msg;
            putIndex = ++putIndex == MAX ? 0 : putIndex;
            count++;
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Message next() {
        Message msg = null;
        try {
            lock.lock();
            while (0 == count) {
                notEmpty.await();
            }
            msg = messages[getIndex];
            getIndex = ++getIndex == MAX ? 0 : getIndex;
            count--;
            notFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return msg;
    }

}
