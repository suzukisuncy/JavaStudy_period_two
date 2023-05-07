package cn.tedu.thread;

/**
 * Thread中提供了一个静态的方法currentThread方法
 * 该方法可以返回运行这个方法的线程实例
 * java中所有的代码都是依靠线程运行的,main方法也不例外,JVM启动后,会自动创建一个线程,
 * 执行main方法,所以我们会将这条线程称为主线程,而这个线程的名字也叫"main"
 */
public class CurrentThreadDemo {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        /*
         * Thread中提供了getName方法,用于获取线程名
         */
        System.out.println("主线程:" + t.getName());
        Thread t2 = new Thread("无敌风火轮");
        System.out.println("t2线程:" + t2.getName());
        Thread t3 = new Thread(() -> {
            Thread tt = Thread.currentThread();
            System.out.println("t3线程:" + tt.getName());
        });
        t3.setName("疯狂霸王鸡");
        t3.start();
        Thread t4 = new Thread("美丽金针菇") {
            @Override
            public void run() {
                Thread tt = currentThread();
                System.out.println("t4线程:" + tt.getName());
            }
        };
        t4.start();
    }
}