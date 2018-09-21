/**
 * 线程的几种状态：
 *
 *
 *
 * learn thread demo1
 * 方式1：继承thread,重写run方法
 * 方式2：实现Runnable接口,重写run方法
 *
 * 线程的start是怎么调用到call()方法的？
 * 源码：start() --> start0() [这个方法是JVM层面的,底层有某种机制调用到了run()]
 *
 */
public class ThreadDemo1 {

    /**
     * 方式1和方式2的优缺
     * 实现接口runnable的方式更为灵活
     *
     * @param args
     */
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread myRunnableThread = new Thread(new MyRunnable());
        myThread.start();
        myRunnableThread.start();
    }

}

/**
 * 实现多线程的方式1：继承thread,重写run方法
 * 说明：
 *      Thread类本质上是实现了Runnable接口的一个实例，代表一个线程的实例。
 *      启动线程的唯一方法就是通过Thread类的start()实例方法。
 *      start()方法是一个native方法，它将启动一个新线程，并执行run()方法。
 *      这种方式实现多线程很简单，通过自己的类直接extend Thread，并复写run()方法，就可以启动新线程并执行自己定义的run()方法。
 */
class MyThread extends Thread {

    private int i = 0;

    @Override
    public void run() {
        System.out.println("in MyThread run");
        for (i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

/**
 * 实现多线程的方式2：实现Runnable接口,重写run方法
 * 优点：如果自己的类已经extends另一个类，就无法直接extends Thread，此时，可以实现一个Runnable接口
 */
class MyRunnable implements Runnable {
    private int i = 0;

    @Override
    public void run() {
        System.out.println("in MyRunnable run");
        for (i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
