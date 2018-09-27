package LinkedBlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 利用LinkedBlockingQueue实现生产者消费者模型
 */
public class ProducerConsumerPattern {

    public static void main(String[] args) {
        //创建一个分享的队列
        BlockingQueue sharedQueue = new LinkedBlockingQueue();

        //创建一个生产者和消费者
        Thread prodThread = new Thread(new Producer(sharedQueue));
        Thread consThread = new Thread(new Consumer(sharedQueue));

        //启动线程
        prodThread.start();
        consThread.start();
    }
}

/**
 * 生产者
 */
class Producer implements Runnable{

    private final BlockingQueue sharedQueue;

    public Producer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("Produced:"+i);
            try {
                sharedQueue.put(i);//阻塞式
            } catch (InterruptedException e) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();
            }
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable{
    private final BlockingQueue sharedQueue;

    Consumer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                System.out.println("Consumed:"+sharedQueue.take());//阻塞式
            } catch (InterruptedException e) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();
            }
        }
    }
}

