package cn.tedu.thread;

/**
 * Thread中提供了一个静态的sleep方法
 * 当线程调用sleep方法后,会进入阻塞状态指定的毫秒,超过这个时间后,会自动进入到就绪状态,当CPU分配时间片后,会继续执行
 */
public class SleepDemo01 {
    public static void main(String[] args) {
        System.out.println("程序开始了!");
        try {
            //1秒=1000毫秒 让线程进入睡眠阻塞5秒时间
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("程序结束了!");
    }
}
