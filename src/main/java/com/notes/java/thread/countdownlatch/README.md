# CountDownLatch

该类位于java.util.concurrent包下，利用它可以实现类似计数器的功能。
比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
  
> CountDownLatch类只提供了一个构造器：
  
    //参数count为计数值
    public CountDownLatch(int count) { 
     
    };  
    
> 下面这3个方法是CountDownLatch类中最重要的方法

    //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
    public void await() throws InterruptedException {
     
    };   
    
    //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
    public boolean await(long timeout, TimeUnit unit) throws InterruptedException { 
    
    }; 
    
    //将count值减1
    public void countDown() { 
    
    }; 
    
> CountDownLatch/CyclicBarrier/Semaphore

1）CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：
  CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；
  而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
  另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。
2）Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限。 