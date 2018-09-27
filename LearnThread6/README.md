#####参考文章
######Java队列存储结构及实现
https://www.cnblogs.com/Dylansuns/archive/2017/04/30/6789161.html
######队列学习
http://ifeve.com/java-blocking-queue/

##知识点
####一：队列的认识
######队列是一种特殊的线性表，它只允许在表的前段（front）进行删除操作，只允许在表的后端（rear）进行插入操作。进行插入操作的端称为队尾，进行删除操作的端称为队头。
######对于一个队列来说，每个元素总是从队列的rear端进入队列，然后等待该元素之前的所有元素出队之后，当前元素才能出对，遵循先进先出（FIFO）原则。

####二：常见的几种队列
#####1.阻塞队列
###### ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。
###### LinkedBlockingQueue ：一个由链表结构组成的有界阻塞队列。
###### PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。
###### DelayQueue：一个使用优先级队列实现的无界阻塞队列。
###### SynchronousQueue：一个不存储元素的阻塞队列。
###### LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
###### LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。

#####其中常用的是：ArrayBlockingQueue、LinkedBlockingQueue和CurrentLinkedQueue，它们都是线程安全的队列。
#####LinkedBlockingQueue队列的吞吐量通常比ArrayBlockingQueue队列高，但在大多数并发应用程序中，LinkedBlockingQueue的性能要低。
#####除了LinkedBlockingQueue队列之外，JDK还提供了另外一种链队列ConcurrentLinkedQueue，它基于一种先进的、无等待（wait-free）队列算法实现。

####三：队列的使用
#####1.LinkedBlockingQueue的使用 实现阻塞式的生产者消费者模型
#####2.


