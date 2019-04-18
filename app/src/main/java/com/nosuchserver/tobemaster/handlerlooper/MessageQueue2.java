package com.nosuchserver.tobemaster.handlerlooper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过链表实现MessageQueue
 * @author rere
 */
public class MessageQueue2 extends BaseMessageQueue {

    private Message messageHead;

    private Lock lock;
    private Condition notEmpty;

    public MessageQueue2() {
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
    }

    @Override
    public void enqueueMessage(Message msg) {
        try {
            lock.lock();
            if (null == messageHead) {
                messageHead = msg;
            } else {
                Message p = messageHead;
                while (null != p.next) {
                    p = p.next;
                }
                p.next = msg;
            }
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Message next() {
        Message msg = null;
        try {
            lock.lock();
            while (null == messageHead) {
                notEmpty.await();
            }
            msg = messageHead;
            messageHead = msg.next;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return msg;
    }

}
