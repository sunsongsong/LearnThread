package threadMethods;

/**
 * methods: getName() currentThread()
 */
public class threadMethods1 {

    public static void main(String args[]){
        MyThread mt = new MyThread() ;    // 实例化Runnable子类对象
        new Thread(mt).start() ;        // 系统自动设置线程名称
        new Thread(mt,"线程-A").start() ;        // 手工设置线程名称
        new Thread(mt,"线程-B").start() ;        // 手工设置线程名称
        new Thread(mt).start() ;        // 系统自动设置线程名称
        new Thread(mt).start() ;        // 系统自动设置线程名称
    }

}
class MyThread implements Runnable{    // 实现Runnable接口
    public void run(){    // 覆写run()方法
        for(int i=0;i<3;i++){
            System.out.println(Thread.currentThread().getName()
                    + "运行，i = " + i) ;    // 取得当前线程的名字
        }
    }
}