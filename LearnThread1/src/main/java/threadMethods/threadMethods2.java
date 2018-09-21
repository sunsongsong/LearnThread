package threadMethods;

/**
 * methods: isAlive()
 */
public class threadMethods2 {

    public static void main(String[] args) {
        MyThread2 mt = new MyThread2() ;    // 实例化Runnable子类对象
        Thread t = new Thread(mt,"线程");        // 实例化Thread对象
        System.out.println("线程开始执行之前 --> " + t.isAlive()) ;     // 判断是否启动
        t.start() ;    // 启动线程
        System.out.println("线程开始执行之后 --> " + t.isAlive()) ;     // 判断是否启动
        for(int i=0;i<3;i++){
            System.out.println(" main运行 --> " + i) ;
        }
        // 以下的输出结果不确定
        System.out.println("代码执行之后 --> " + t.isAlive()) ;     // 判断是否启动
    }

}

class MyThread2 implements Runnable{    // 实现Runnable接口
    public void run(){    // 覆写run()方法
        for(int i=0;i<3;i++){
            System.out.println(Thread.currentThread().getName()
                    + "运行，i = " + i) ;    // 取得当前线程的名字
        }
    }
};