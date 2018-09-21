package threadMethods;

/**
 * methods: setDaemon()
 */
public class threadMethods6 {

    public static void main(String args[]){
        MyThread6 mt = new MyThread6() ;    // 实例化Runnable子类对象
        Thread t = new Thread(mt,"线程");        // 实例化Thread对象
        t.setDaemon(true) ;    // 此线程在后台运行
        t.start() ;    // 启动线程
    }

}

class MyThread6 implements Runnable{    // 实现Runnable接口
    public void run(){    // 覆写run()方法
        int i=0;
        while(true){
            //设置死循环，这样来实现线程不断运行，设置后台运行。
            System.out.println(Thread.currentThread().getName() + "在运行。"+i) ;
        }
    }
}