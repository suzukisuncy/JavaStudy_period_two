package cn.tedu.thread;

/**
 * 使用匿名内部类简化两种线程的创建方式
 */
public class ThreadDemo03 {
    public static void main(String[] args) {
        //简化直接继承Thread重写run方法的创建方式
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("我是毛利小五郎!");
                }
            }
        };
        //简化实现Runnable重写run方法的创建方式
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("我是齐天大圣!!");
                }
            }
        };
        Thread t2 = new Thread(r1);
        //通过Lambda表达式简化实现Runnable重写run方法的创建方式
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("我是武大郎!!");
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
