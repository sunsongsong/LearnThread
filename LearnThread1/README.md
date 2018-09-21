#####参考文章
######多线程的创建方式
https://www.cnblogs.com/lwbqqyumidi/p/3804883.html
######Java线程的6种状态及切换
https://blog.csdn.net/pange1991/article/details/53860651
######线程常用的操作方法
https://www.cnblogs.com/alsf/p/5651888.html

##知识点
####一：线程的几种状态
#####1. 初始(NEW):新创建了一个线程对象，但还没有调用start()方法。
#####2. 运行(RUNNABLE):
#####   Java线程中将就绪（ready）和运行中（running）两种状态笼统的称为“运行”。
#####   线程对象创建后，其他线程(比如main线程）调用了该对象的start()方法。
#####   该状态的线程位于可运行线程池中，等待被线程调度选中，获取CPU的使用权，此时处于就绪状态(ready).
#####   就绪状态的线程在获得CPU时间片后变为运行中状态(running).
#####3. 阻塞(BLOCKED):表示线程阻塞于锁。
#####4. 等待(WAITING)：进入该状态的线程需要等待其他线程做出一些特定动作（通知或中断）。
#####5. 超时等待(TIMED_WAITING)：该状态不同于WAITING，它可以在指定的时间后自行返回。
#####6. 终止(TERMINATED)：表示该线程已经执行完毕。

####二：线程的常用方法【threadMethods】
#####1. getName()方法取得线程名称 Thread.currentThread().getName() 【threadMethods1.java】
#####2. currentThread()方法取得当前正在运行的线程对象 【threadMethods1.java】
#####3. isAlive() 判断线程是否启动 【threadMethods2.java】
#####4. join() 使得一个线程强制运行，线程强制运行期间，其他线程无法运行，必须等待此线程完成之后，才可以继续运行。 【threadMethods3.java】
#####5. sleep()  线程休眠,直接使用Thread.sleep()方法 Thread.sleep(500)休眠500毫秒 【threadMethods4.java】
#####6. interrupt() 线程的中断 可以实现条件判断不让线程继续执行 【threadMethods5.java】
#####7. setDaemon() 让线程在后台执行 【threadMethods6.java】
#####8. getPriority() 获取线程的优先级 setPriority() 设置线程的优先级 【threadMethods7.java】
#####9. yield() 线程的礼让 【threadMethods8.java】
 

####三：常见的三种多线程的创建方法【ThreadDemo1.java ThreadDemo2.java】
#####1. 继承thread,重写run方法
#####2. 实现Runnable接口,重写run方法
#####3. 使用Callable和Future接口创建线程
#####4. Callable案例：利用多线程实现分组计算【SumCallable.java】  


 











