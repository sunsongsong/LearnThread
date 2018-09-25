import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 循环障碍器
 * 重要的方法:
 * 1.用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务
 * public int await() throws InterruptedException, BrokenBarrierException { };
 * 2.让这些线程等待至一定的时间，如果还有线程没有到达barrier状态就直接让到达barrier的线程执行后续任务
 * public int await(long timeout, TimeUnit unit)throws InterruptedException,BrokenBarrierException,TimeoutException { };
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        //缺点：继续处理其他任务... 会重复
        //testCyclicBarrier();

        //在所有线程写入操作完之后，进行额外的其他操作可以为CyclicBarrier提供Runnable参数
        //这样实现的功能就和CountDownLatch一样了
        testCyclicBarrier2();

    }

    public static void testCyclicBarrier(){
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N);
        for(int i=0;i<N;i++){
            new Writer(barrier).start();
        }
    }

    public static void testCyclicBarrier2(){
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                System.out.println("都执行完了,我们来干点别的...");
            }
        });
        for(int i=0;i<N;i++){
            new Writer(barrier).start();
        }
    }

    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            //System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }





}
