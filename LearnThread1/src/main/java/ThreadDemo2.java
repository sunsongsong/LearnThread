import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * learn thread demo2
 * 方式3：使用Callable和Future接口创建线程
 *
 * Callable和Runnable的区别
 * Runnable run方法是被线程调用的，在run方法是异步执行的
 * Callable的call方法，不是异步执行的，是由Future的run方法调用的
 *
 * FutureTask本身实现了Runnable和Future,
 * 关键的get()方法, get() 获取任务执行结果，任务结束之前会阻塞。
 *
 */
public class ThreadDemo2 {
    public static void main(String[] args) {

        Callable<Integer> myCallable = new MyCallable();    // 创建MyCallable对象
        FutureTask<Integer> ft = new FutureTask(myCallable); //使用FutureTask来包装MyCallable对象

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                Thread thread = new Thread(ft);   //FutureTask对象作为Thread对象的target创建新的线程
                thread.start();                      //线程进入到就绪状态
            }
        }

        System.out.println("主线程for循环执行完毕..");

        try {
            int sum = ft.get();            //取得新创建的新线程中的call()方法返回的结果
            System.out.println("sum = " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
class MyCallable implements Callable<Integer> {
    private int i = 0;

    // 与run()方法不同的是，call()方法具有返回值
    @Override
    public Integer call() {
        int sum = 0;
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            sum += i;
        }
        return sum;
    }

}
