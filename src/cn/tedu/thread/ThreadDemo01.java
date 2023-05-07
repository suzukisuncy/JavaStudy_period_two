package cn.tedu.thread;

/**
 * 多线程
 * 多线程可以并发执行多个任务
 * 线程的第一种创建方式:
 * 继承Thread类,并重写run方法,在run方法中定义需要并发执行的任务代码
 */
public class ThreadDemo01 {
    public static void main(String[] args) {
        //④创建要使用的线程实例
        MyThread01 t1 = new MyThread01();
        MyThread02 t2 = new MyThread02();
        //⑤将要执行的线程启动
        /*
         * Thread中提供的start方法,作用是将线程启动,并且纳入到线程调度器中,被统一管理,
         * 当线程被分配到CPU的时间片时,就会开始自动去执行各自线程中run方法定义的内容
         */
        t1.start();
        t2.start();
    }
}

/**
 * Thread是线程的父类
 * ①可以通过继承Thread的方式,创建线程实例
 */
class MyThread01 extends Thread {
    //②重写父类中的run方法 线程启动后,会自动执行run方法
    @Override
    public void run() {
        //③run方法中则定义线程要执行的任务
        for (int i = 0; i < 1000; i++) {
            System.out.println("我是金刚葫芦娃!");
        }
    }
}

class MyThread02 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("我是查水表的!!");
        }
    }
}