#####参考文章
######java ThreadLocal(应用场景及使用方式及原理)
https://www.cnblogs.com/yxysuanfa/p/7125761.html
######AOP+ThreadLocal实现数据源切换的案例
https://download.csdn.net/download/y_xp/9526999

##知识点
####一：Threadlocal的简介
######1.ThreadLocal为变量在每个线程中都创建了一个副本，那么每个线程可以访问自己内部的副本变量。
######  我的理解：ThreadLocal中含有一个ThreadLocalMap,这个Map的key就相当于线程id,值就相当于线程中需要存储的变量
####二：举例说明ThreadLocal的使用
######借用Aop+Threadlocal实现数据源切换的案例说明 demo.java