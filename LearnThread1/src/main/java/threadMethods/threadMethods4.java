package threadMethods;

/**
 * methods: sleep()
 */
public class threadMethods4 {

    public static void main(String args[]){
        MyThread4 mt = new MyThread4() ;    // 实例化Runnable子类对象
        Thread t = new Thread(mt,"线程");        // 实例化Thread对象
        t.start() ;    // 启动线程
    }

}

class MyThread4 implements Runnable{    // 实现Runnable接口
    public void run(){    // 覆写run()方法
        for(int i=0;i<50;i++){
            try{
                //sleep()方法要有InterruptedException 异常处理
                //而且sleep()调用方法通常为Thread.sleep(500)的形式。
                Thread.sleep(500) ;    // 线程休眠
            }catch(InterruptedException e){

            }
            System.out.println(Thread.currentThread().getName()
                    + "运行，i = " + i) ;    // 取得当前线程的名字
        }
    }
}