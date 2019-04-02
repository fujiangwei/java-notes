## 线程

# sleep & wait

> sleep
```text
sleep()是Thread类的Static(静态)的方法；因此他不能改变对象的机锁，所以当在一个Synchronized块中调用Sleep()方法是，线程虽然休眠了，但是对象的机锁并木有被释放，其他线程无法访问这个对象（即使睡着也持有对象锁）。
在sleep()休眠时间期满后，该线程不一定会立即执行，这是因为其它线程可能正在运行而且没有被调度为放弃执行，除非此线程具有更高的优先级。 
```
> wait 
```text
wait()方法是Object类里的方法；当一个线程执行到wait()方法时，它就进入到一个和该对象相关的等待池中，同时失去（释放）了对象的机锁（暂时失去机锁，wait(long timeout)超时时间到后还需要返还对象锁）；其他线程可以访问；
wait()使用notify或者notifyAlll或者指定睡眠时间来唤醒当前等待池中的线程。
wiat()必须放在synchronized block中，否则会在program runtime时扔出”java.lang.IllegalMonitorStateException“异常。
多用在生产者-消费者模式中
```

# 线程同步
> Object

* 线程producer


    synchronize(obj){ 
        //有东西了，唤醒 
        obj.notify();
    }
    
* 线程consumer	


    synchronize(obj){ 
        //没东西了，等待
        obj.wait(); 
    }	

> lock

* 线程producer


    Condition condition = lock.newCondition();
    lock.lock(); 
    //唤醒
    condition.signal(); 
    lock.unlock();
    
* 线程consumer	


    Condition condition = lock.newCondition();
    lock.lock(); 
    //等待
    condition.await(); 
    lock.unlock();	
    
lock用synchronize把同步代码包装起来；
阻塞需要另外一个对象condition；
同步和唤醒的对象是condition而不是lock，对应的方法是await和signal，而不是wait和notify。

# 好习惯

* 新建线程时给线程加上名字，便于后续定位

    
    Thread thread = new Thread(myRunnable, "myThread");