package cn.tedu.thread;

/**
 * 互斥锁
 * 当使用多个synchronized关键字锁定多个代码片段,并且指定的锁对象都是相同的,那么这些代码片段之间就是互斥的
 */
public class SyncDemo03 {
    public static void main(String[] args) {
        Person person = new Person();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                person.eat();
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                person.breath();
            }
        };
        t1.start();
        t2.start();
    }
}

class Person {
    public synchronized void eat() {
        try {
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + ": 正在吃饭...");
            Thread.sleep(5000);
            System.out.println(t.getName() + ": 吃饭完毕!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void breath() {
        try {
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + ": 正在呼吸...");
            Thread.sleep(5000);
            System.out.println(t.getName() + ": 呼吸完毕!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}