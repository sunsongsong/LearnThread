import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 来自：https://www.cnblogs.com/lizhangyong/p/8906774.html
 *
 * ThreadPoolExecutor的使用
 * 1.ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue)
 * 2.ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,  ThreadFactory threadFactory)
 * 3.ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler)
 * 4.ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory,RejectedExecutionHandler handler)
 *
 * 对于构造方法的参数说明如下：
 *   corePoolSize
     核心线程数，默认情况下核心线程会一直存活，即使处于闲置状态也不会受keepAliveTime限制。除非将allowCoreThreadTimeOut设置为true。
     maximumPoolSize
     线程池所能容纳的最大线程数。超过这个数的线程将被阻塞。当任务队列为没有设置大小的LinkedBlockingDeque时，这个值无效。
     keepAliveTime
     非核心线程的闲置超时时间，超过这个时间就会被回收。
     unit
     指定keepAliveTime的单位，如TimeUnit.SECONDS。当将allowCoreThreadTimeOut设置为true时对corePoolSize生效。
     workQueue
     线程池中的任务队列。
     常用的队列有：LinkedBlockingQueue，SynchronousQueue,LinkedBlockingDeque,ArrayBlockingQueue。
     threadFactory
     线程工厂，提供创建新线程的功能。ThreadFactory是一个接口，只有一个方法：
     public interface ThreadFactory {
        Thread newThread(Runnable r);
     }
     RejectedExecutionHandler
     RejectedExecutionHandler也是一个接口，只有一个方法
     public interface RejectedExecutionHandler {
        void rejectedExecution(Runnable var1, ThreadPoolExecutor var2);
     }
     当线程池中的资源已经全部使用，添加新线程又被拒绝时，会调用RejectedExecutionHandler的rejectedExecution方法。
 *
 *
 * 线程池的线程执行规则跟任务队列有很大的关系。其中：
 * （1）在任务队列没有大小限制时：
     ①如果线程数量<=核心线程数量，那么直接启动一个核心线程来执行任务，不会放入队列中。
     ② 如果线程数量>核心线程数，但<=最大线程数，并且任务队列是LinkedBlockingDeque的时候，超过核心线程数量的任务会放在任务队列中排队。
     ③如果线程数量>核心线程数，但<=最大线程数，并且任务队列是SynchronousQueue的时候，线程池会创建新线程执行任务，这些任务也不会被放在任务队列中。这些线程属于非核心线程，在任务完成后，闲置时间达到了超时时间就会被清除。
     ④如果线程数量>核心线程数，并且>最大线程数，当任务队列是LinkedBlockingDeque时，会将超过核心线程的任务放在任务队列中排队。也就是说，当任务队列是LinkedBlockingDeque并且没有大小限制时，线程池的最大线程数设置是无效的，它的线程数最多不会超过核心线程数。
     ⑤如果线程数量>核心线程数，并且>最大线程数，当任务队列是SynchronousQueue的时候，会因为线程池拒绝添加任务而抛出异常。
     （2）在任务队列大小有限时：
     ①当LinkedBlockingDeque塞满时，新增的任务会直接创建新线程来执行，当创建的线程数量超过最大线程数量时会抛异常。
     ②SynchronousQueue没有数量限制。因为它根本不保持这些任务，而是直接交给线程池去执行。当任务数量超过最大线程数时会直接抛异常。
 */
public class ThreadPoolExecutorDemo {

    private static final ThreadPoolExecutor threadPool =
            new ThreadPoolExecutor(5, 10, 60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>());

    public static void main(String[] args) {
        threadPool.execute(new Thread(){
            @Override
            public void run() {
                System.out.println("run...");
            }
        });
    }


}
