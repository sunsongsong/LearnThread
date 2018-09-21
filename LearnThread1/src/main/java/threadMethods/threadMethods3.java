package threadMethods;

/**
 * methods: join()
 */
public class threadMethods3 {

    public static void main(String args[]){
        MyThread3 mt = new MyThread3() ;    // 实例化Runnable子类对象
        Thread t = new Thread(mt,"线程");        // 实例化Thread对象
        t.start() ;    // 启动线程
        for(int i=0;i<50;i++){
            if(i>10){
                try{
                    t.join() ;    // 使得一个线程强制运行，线程强制运行期间，其他线程无法运行，必须等待此线程完成之后，才可以继续运行。
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println("Main线程运行 --> " + i);
        }
    }

}

class MyThread3 implements Runnable{    // 实现Runnable接口
    public void run(){    // 覆写run()方法
        for(int i=0;i<50;i++){
            System.out.println(Thread.currentThread().getName()
                    + "运行，i = " + i) ;    // 取得当前线程的名字
        }
    }
}