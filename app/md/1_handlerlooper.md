1. ThreadLocal的本质是每个Thread的局部信息，但在Looper内是静态的，归属于类，为什么能做到每个Thread能保存各自的局部信息。

    答：ThreadLocal对象的set，先取出对应Thread，再取出对应的ThreadLocalMap。
    并将ThreadLocal对象作为key值以及value值set进去。
    
2. Condition从lock.newCondition()来，要注意使用的是signalAll()而不是notifyAll()。

    Lock与condition对应方法
    * lock.lock()
    * condition.await()
    * condition.signalAll()
    * lock.unlock()
    
    synchronized(Object)对应方法
    * object.wait()
    * object.notifyAll()
    
    注意锁和同步代码块的范围要尽可能小，避免其他线程无法执行。
    
    
3. Handler创建时就要指定Looper，不能在sendMessage的时候才指定，否则会使用sendMessage线程的Looper（甚至没有创建）
    
    