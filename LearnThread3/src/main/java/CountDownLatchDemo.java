import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch学习：
 * 几个重要方法：
 * 1.参数count为计数值
 * public CountDownLatch(int count) {  };
 * 2.调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
 * public void await() throws InterruptedException { };
 * 3.和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
 * public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };
 * 4.将count值减1
 * public void countDown() { };
 */
public class CountDownLatchDemo {


    public static void main(String[] args){
        //初识
        testCountDownLatch();
        //发令枪的使用
//        concurrentTest();
    }

    /**
     * CountDownLatch的用法
     */
    private static void testCountDownLatch(){
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread( () -> {
            try {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                Thread.sleep(3000);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread( () -> {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }).start();

        try {
            System.out.println("等待2个子线程执行完毕...");
            countDownLatch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 并发测试
     * 100个线程放在线程池中,等待一个线程结束（发令枪鸣枪）,同时进行其余的操作
     * @throws InterruptedException
     */
    private static void concurrentTest() {
        int poolSize = 100;
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(poolSize);
        ExecutorService exce = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        start.await();
                        testLoad(num);//并发操作
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            };
            exce.submit(run);
        }
        start.countDown();
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exce.shutdown();
    }
    private static int num = 100;
    private static void testLoad(int num){
        System.out.println(num--);
    }

}
