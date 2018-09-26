#####参考文章
######Java多线程中Synchronized和Lock的区别
https://blog.csdn.net/u010842515/article/details/67634813
https://www.cnblogs.com/baizhanshi/p/6419268.html


##知识点
####一：Synchronized和lock的用法和区别
#####1.用法
###### Synchronized是java内置的关键字,用于在方法代码块上加锁,默认采用悲观锁
###### lock加于开始和结束处,一般用Reentrantlock类为锁,使用lock()加锁和unlock()解锁,unlock()一般放在finally块中保证一定能执行,采用乐观锁
#####2.区别
###### Synchronized不需要用户手动释放锁,由系统自动释放锁
###### lock需要手动释放锁,如果没有则会出现死锁
###### lock可以知道没有成功获取锁的时候,可以采取别的操作

####二：Lock接口、ReentrantLock、ReentrantReadWriteLock
#####

一：基础知识
首先Synchronized是java语音的关键字,是其一个内置的特性.
主要用作于修饰代码块或者方法,当一个线程执行到被修饰的地方时,会获得一个对应的锁,并执行该代码,
其它线程如果也执行到这里,便只能一直等待.等待拿到锁的线程执行完,释放锁之后,此时再执行.
释放锁的情况只有：
1.线程执行完该代码块,线程会自动释放锁
2.线程在执行中遇到异常,JVM会让线程自动释放锁
那么当程由于要等待IO或者其他原因(比如调用sleep方法)被阻塞了,但是又没有释放锁.
其他线程便只能干巴巴地等待,这样就很影响效率了.
总而言之：这种就只能排队+等等等了,而且这里的排队还不一定先到先得
从而产生了lock这种锁,它可以能够不让线程一直的等待下去,(比如只等待一定的时间或者能够响应中断)
再举个例子：当有多个线程读写文件时,读操作和写操作会发生冲突现象,写操作和写操作会发生冲突现象,但是读操作和读操作不会发生冲突现象.
但是采用synchronized关键字来实现同步的话,就会导致一个问题：
如果多个线程都只是进行读操作,所以当一个线程在进行读操作时,其他线程只能等待无法进行读操作.
因此就需要一种机制来使得多个线程都只是进行读操作时,线程之间不会发生冲突,通过Lock就可以办到.
另外,通过Lock可以知道线程有没有成功获取到锁.这个是synchronized无法办到的.
总结一下,也就是说Lock提供了比synchronized更多的功能.但是要注意以下几点：
1.Lock不是Java语言内置的,synchronized是Java语言的关键字,因此是内置特性.Lock是一个类,通过这个类可以实现同步访问;
2.Lock和synchronized有一点非常大的不同,采用synchronized不需要用户去手动释放锁,当synchronized方法或者synchronized代码块执行完之后,系统会自动让线程释放对锁的占用;而Lock则必须要用户去手动释放锁,如果没有主动释放锁,就有可能导致出现死锁现象.
二：Lock的介绍和使用
1.介绍
Lock接口的方法：lock()、tryLock()、tryLock(long time, TimeUnit unit)和lockInterruptibly()是用来获取锁的。
unLock()方法是用来释放锁的。newCondition()是用于线程协作的.
四种获取锁的区别:
(1)lock()是获取锁,如果获取不到,则等待。
使用方法:
Lock lock = ...;
lock.lock();
try{
    //处理任务
}catch(Exception ex){
     
}finally{
    lock.unlock();   //释放锁
}
(2)tryLock()方法是有返回值的，它表示用来尝试获取锁，如果获取成功，则返回true，如果获取失败（即锁已被其他线程获取），则返回false，
也就说这个方法无论如何都会立即返回。在拿不到锁时不会一直在那等待。
使用方法：
Lock lock = ...;
if(lock.tryLock()) {
     try{
         //处理任务
     }catch(Exception ex){
         
     }finally{
         lock.unlock();   //释放锁
     } 
}else {
    //如果不能获取锁，则直接做其他事情
}
(3)lockInterruptibly()方法比较特殊，当通过这个方法去获取锁时，如果线程正在等待获取锁，则这个线程能够响应中断，即中断线程的等待状态。也就使说，当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，假若此时线程A获取到了锁，而线程B只有在等待，那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程。
　　由于lockInterruptibly()的声明中抛出了异常，所以lock.lockInterruptibly()必须放在try块中或者在调用lockInterruptibly()的方法外声明抛出InterruptedException。
　　因此lockInterruptibly()一般的使用形式如下：
   public void method() throws InterruptedException {
       lock.lockInterruptibly();
       try {  
        //.....
       }
       finally {
           lock.unlock();
       }  
   }
　　注意，当一个线程获取了锁之后，是不会被interrupt()方法中断的。因为本身在前面的文章中讲过单独调用interrupt()方法不能中断正在运行过程中的线程，只能中断阻塞过程中的线程。
　　因此当通过lockInterruptibly()方法获取某个锁时，如果不能获取到，只有进行等待的情况下，是可以响应中断的。
　　而用synchronized修饰的话，当一个线程处于等待某个锁的状态，是无法被中断的，只有一直等待下去。

三：ReentrantLock的介绍和使用
1.介绍
    意思是“可重入锁”
2.使用
    lock() trylock() lockInterruptibly() 使用案例：ReentrantLockDemo包下
    
四：ReadWriteLock下的ReentrantReadWriteLock介绍和使用
1.ReadWriteLock
    它是一个接口,方法读锁Lock readLock();写锁Lock writeLock();
2.ReentrantReadWriteLock实现了ReadWriteLock接口
    ReentrantReadWriteLockDemo包下 demo1 使用synchronized和 demo2使用readlock对比
    readlock()可以保证线程同时读取

五：Lock和synchronized的选择
总结来说，Lock和synchronized有以下几点不同：
1）Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；
2）synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
3）Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
4）通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
5）Lock可以提高多个线程进行读操作的效率。
在性能上来说，如果竞争资源不激烈，两者的性能是差不多的，而当竞争资源非常激烈时（即有大量线程同时竞争），此时Lock的性能要远远优于synchronized。所以说，在具体使用时要根据适当情况选择。
    













