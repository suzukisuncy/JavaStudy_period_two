package cn.tedu.thread;

/**
 * 第二种创建线程的方式:
 * 实现Runnable接口单独定义线程任务
 */
public class ThreadDemo02 {
    public static void main(String[] args) {
        //④先将要执行的线程任务实例化
        MyRunnable01 r1 = new MyRunnable01();
        MyRunnable02 r2 = new MyRunnable02();
        //⑤将线程的任务分配给线程实例
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        //⑥将线程启动,会自动执行分配给其的任务
        t1.start();
        t2.start();
    }
}

/**
 * Runnable是线程任务类接口,不是线程本身
 * //①实现Runnable,创建线程任务子类
 */
class MyRunnable01 implements Runnable {
    //②强制要求必须重写run方法,线程启动后,会自动执行run方法的内容
    @Override
    public void run() {
        //③定义线程任务
        for (int i = 0; i < 1000; i++) {
            System.out.println("我是迪迦奥特曼!!!");
        }
    }
}

class MyRunnable02 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("我是小怪兽!!");
        }
    }
}