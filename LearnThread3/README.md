#####参考文章
######常见的几个辅助类
http://www.importnew.com/21889.html
https://www.cnblogs.com/lizhangyong/p/8906774.html

##知识点
####一：CountDownLatch 线程计数器 CountDownLatchDemo.java
#####利用它可以实现类似计数器的功能。比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
#####类似于线程计数器,也称之为发令枪,也常被用于做并发测试
####二：CyclicBarrier 循环障碍器 CyclicBarrierDemo.java
#####通过它可以实现让一组线程等待至某个状态之后再全部同时执行,和CountDownLatch类似
#####相较于CountDownLatch可以重用
####三：Semaphore 信号量 SemaphoreDemo.java
#####可以控同时访问的线程个数 
####四：Phaser 同步辅助类 (待完成)
#####

##总结：
#####1）CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同
#####CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行
#####而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行
#####另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的
#####
#####2）Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限









