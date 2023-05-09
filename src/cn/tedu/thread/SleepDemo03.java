package cn.tedu.thread;

/**
 * sleep方法调用时,必须要处理中断异常
 * 当一个线程调用sleep方法处于睡眠阻塞状态的过程中,如果该线程的interrupt方法被调用时,
 * 会立即中断该睡眠阻塞,并抛出中断异常
 */
public class SleepDemo03 {
    public static void main(String[] args) {
        Thread lin = new Thread() {
            public void run() {
                System.out.println("林:刚打扫完卫生,小憩一会~");
                try {
                    Thread.sleep(10000);
                    System.out.println("林:睡的真舒服啊~~");
                } catch (InterruptedException e) {
                    System.out.println("林:干嘛呢!干嘛呢!都破了相!!!");
                }
            }
        };
        lin.start();
    }
}
