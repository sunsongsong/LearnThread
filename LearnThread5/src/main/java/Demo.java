/**
 * 使用线程测试threadLocal的使用
 * 例子中可以看出,在线程中使用threadlocal,可以做到后期不用传递参数,也能获取到之前的存储的变量
 * 从而做到的线程资源的隔离
 * Threadlocal与Synchronized不同的是：threadlocal(资源隔离) Synchronized(资源共享)
 */
public class Demo {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                DynamicDataSourceHolder.setDataSource("master");
                getSource();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                DynamicDataSourceHolder.setDataSource("salve");
                getSource();
            }
        }.start();
    }

    public static void getSource() {
        String source = DynamicDataSourceHolder.getDataSource();
        System.out.println("source=" + source);
        //todo:如果确定之后不会使用了,需要注意移除当前线程存储的变量
        //一般的使用： try{ get() use() }finally{remove()}
        DynamicDataSourceHolder.clearDataSource();
        String source_ = DynamicDataSourceHolder.getDataSource();
        System.out.println("source_=" + source_);
    }

}
