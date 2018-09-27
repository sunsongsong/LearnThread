#####参考文章
######java常用的几种线程池比较
https://www.cnblogs.com/aaron911/p/6213808.html

##知识点
####一：线程池是什么？为什么要使用线程池？
#####线程的创建和销毁线程本身就会带来额外的开销,如果过于频繁更是消耗系统资源
#####线程池将创建和销毁资源这一块统一管理,可以降低资源消耗


####二：线程池会有什么问题
#####1.死锁【一句话说明死锁:比如做油泼面,我拿到油了就等面了,你拿到面了就等油了,咱两谁都不让,结果就死锁了】
#####2.资源不足
#####3.并发错误
#####4.线程泄漏
#####5.请求过载
      
####三：几种常见的线程池 【ThreadPoolDemo.java】
#####1.FixedThreadPool 线程池大小固定，任务队列无界
#####2.SingleThreadExecutor 线程池大小固定为1，任务队列无界.
#####3.CachedThreadPool 线程池无限大（MAX INT），等待队列长度为1
#####4.ScheduledThreadPool 定长的线程池，而且支持定时的以及周期性的任务执行，支持定时及周期性任务执行
######4.1scheduleAtFixedRate() 按照固定频率去执行任务的
######4.2scheduleWithFixedDelay() 按照固定的延迟去执行任务

####四：直接使用ThreadPoolExecutor来创建线程池 ThreadPoolExecutorDemo.java
######ThreadPoolExecutor是Executors类的底层实现
######官方建议使用Executors的工程方法来创建线程池
######Executors.newCachedThreadPool() 无界线程池,可以进行自动线程回收
######Executors.newFixedThreadPool()  固定大小线程池
######Executors.newSingleThreadPool() 单个后台线程

####五：额外篇 使用Fork/Join并行计算框架
###### LearnExtendsRecursiveTask.java












