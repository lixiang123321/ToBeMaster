1. ThreadLocal的本质是每个Thread的局部信息，但在Looper内是静态的，归属于类，为什么能做到每个Thread能保存各自的局部信息。

    答：ThreadLocal对象的set，先取出对应Thread，再取出对应的ThreadLocalMap。
    并将ThreadLocal对象作为key值以及value值set进去。
    
1. Condition从lock.newCondition()来，要注意使用的是signalAll()而不是notifyAll()。

    Lock与condition对应方法
    * lock.lock()
    * condition.await()
    * condition.signalAll()
    * lock.unlock()
    
    synchronized(Object)对应方法
    * object.wait()
    * object.notifyAll()
    
    注意锁和同步代码块的范围要尽可能小，避免其他线程无法执行。
    
    
1. Handler创建时就要指定Looper，不能在sendMessage的时候才指定，否则会使用sendMessage线程的Looper（甚至没有创建）
    
1. Handler dispatchMessage三种方式，优先级
    * msg.callback.run()
    * mCallback.handleMessage(msg)
    * handleMessage(msg)
    
   ```
   public void dispatchMessage(Message msg) {
        if (msg.callback != null) { 
            // msg.callback的赋值，通过静态方法Message.obtain(handler, runnable); 
            handleCallback(msg); 
            // 实际执行
            // msg.callback.run();
        } else {
            if (mCallback != null) { // handler
                // handler内部的mCallback，为了不写内名内部类/子类
                // 在handler构造时完成赋值，Handler(Callback callback, boolean async);
                // mCallback接口只有一个方法：handleMessage(Message msg)
                if (mCallback.handleMessage(msg)) {
                    return;
                }
            }
            // 重写的handlerMessage
            handleMessage(msg);
        }
    }
    ```
    
1. MessageQueue的数据结构实际上是Message链表，Message.next。而不是实际的队列。

1. Handler的sendMessage/post相关方法，最后调用sendMessageAtTime，含时间(Message when)，所以enqueueMessage有一个时间排序

1. 为什么Looper一直在循环，主线程没有被卡死？
