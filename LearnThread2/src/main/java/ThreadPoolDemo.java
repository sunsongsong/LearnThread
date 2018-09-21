import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * java 线程池demo
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
//        useCachedThreadPool();

//        useFixedThreadPool();

//        useSingleThreadExecutor();

//        useScheduleThreadPool();

//        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
//        scheduleAtFixedRate(scheduledThreadPool,1000);

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduleWithFixedDelay(scheduledThreadPool,1000);
    }

    /**
     * 固定线程大小,多余的排队
     * 1）从线程池中获取可用线程执行任务，如果没有可用线程则使用ThreadFactory创建新的线程，
     * 直到线程数达到nThreads(最大个数)。
     * <p>
     * 2）线程池线程数达到nThreads以后，新的任务将被放入队列。
     * FixedThreadPool的优点是能够保证所有的任务都被执行，永远不会拒绝新的任务；
     * 同时缺点是队列数量没有限制，在任务执行时间无限延长的这种极端情况下会造成内存问题。
     * <p>
     * 3)FixedThreadPool是一个典型且优秀的线程池，它具有线程池提高程序效率和节省创建线程时所耗的开销的优点。
     * 但是，在线程池空闲时，即线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源。
     */
    private static void useFixedThreadPool() {
        int threadNum = 2;
        //创建固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        for (int i = 0; i < 5; i++) {
            Thread t = new MyThread(i);
            threadPoolExecutor.execute(t);
            System.out.println("线程池中现在的线程数目是："
                    + threadPoolExecutor.getPoolSize() +
                    ",  队列中正在等待执行的任务数量为：" +
                    threadPoolExecutor.getQueue().size());
        }
        threadPoolExecutor.shutdown();
    }

    /**
     * 只建一个,单线程处理,其它的排队
     * SingleThreadExecutor 适用于在逻辑上需要单线程处理任务的场景，
     * 同时无界的LinkedBlockingQueue保证新任务都能够放入队列，不会被拒绝；
     * 缺点和FixedThreadPool相同，当处理任务无限等待的时候会造成内存问题。
     */
    private static void useSingleThreadExecutor() {
        //创建一个单线程的线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 5; i++) {
            Thread t = new MyThread(i);
            //将线程放到池中执行
            executor.execute(t);
        }
        //关闭线程池
        executor.shutdown();
    }

    /**
     * 来多少,建多少
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     * 工作线程的创建数量几乎没有限制(其实也有限制的,数目为Interger. MAX_VALUE), 这样可灵活的往线程池中添加线程。
     * 如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程。
     * 在使用CachedThreadPool时，一定要注意控制任务的数量，否则，由于大量线程同时运行，很有会造成系统瘫痪。
     */
    private static void useCachedThreadPool() {
        //创建一个可缓存的线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;

        for (int i = 1; i <= 5; i++) {
            Thread t = new MyThread(i);
            //将线程放到池中执行
            threadPoolExecutor.execute(t);
            System.out.println("线程池中现在的线程数目是："
                    + threadPoolExecutor.getPoolSize() +
                    ",  队列中正在等待执行的任务数量为：" +
                    threadPoolExecutor.getQueue().size());
        }
        //关闭线程池
        threadPoolExecutor.shutdown();
    }

    /**
     * 创建一个定长的线程池，而且支持定时的以及周期性的任务执行，支持定时及周期性任务执行。
     * 延迟3秒执行，延迟执行示例代码如下：
     */
    private static void useScheduleThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);
    }

    /**
     * scheduleAtFixedRate()和scheduleWithFixedDelay方法参数是一样的。
     * 第一个参数是任务实例，第二个参数是延迟时间，第三个是间隔时间，第四个是时间单元。
     * 这两个方法的不同之处在方法名也能看得出来：scheduleAtFixedRate方法是按照固定频率去执行任务的。
     * 而scheduleWithFixedDelay方法则是按照固定的延迟去执行任务。
     */

    /**
     * scheduleAtFixedRate方法是按照固定频率去执行任务的
     * @param service
     * @param sleepTime
     */
    private static void scheduleAtFixedRate(ScheduledExecutorService service, final int sleepTime) {
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long start = new Date().getTime();
                System.out.println("scheduleAtFixedRate 开始执行时间:" + DateFormat.getTimeInstance().format(new Date()));
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long end = new Date().getTime();
                System.out.println("scheduleAtFixedRate 执行花费时间=" + (end - start) / 1000 + "m");
                System.out.println("scheduleAtFixedRate 执行完成时间：" + DateFormat.getTimeInstance().format(new Date()));
                System.out.println("======================================");
            }
        }, 1000, 5000, TimeUnit.MILLISECONDS);
    }

    /**
     * scheduleWithFixedDelay方法则是按照固定的延迟去执行任务。
     * @param service
     * @param sleepTime
     */
    private static void scheduleWithFixedDelay(ScheduledExecutorService service, final int sleepTime) {
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                long start = new Date().getTime();
                System.out.println("scheduleWithFixedDelay 开始执行时间:" + DateFormat.getTimeInstance().format(new Date()));
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long end = new Date().getTime();
                System.out.println("scheduleWithFixedDelay执行花费时间=" + (end - start) / 1000 + "m");
                System.out.println("scheduleWithFixedDelay执行完成时间：" + DateFormat.getTimeInstance().format(new Date()));
                System.out.println("======================================");
            }
        }, 1000, 5000, TimeUnit.MILLISECONDS);
    }

}

class MyThread extends Thread {
    private Integer num; // 正在执行的任务数

    public MyThread(Integer num) {
        this.num = num;
    }

    @Override
    public void run() {
        // System.out.println("LearnExtendsThread");
        System.out.println(Thread.currentThread().getName() + " 正在执行第 " + num + "个任务");
        try {
            Thread.sleep(500);// 模拟执行任务需要耗时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 执行完毕第 " + num + "个任务");
    }

}
