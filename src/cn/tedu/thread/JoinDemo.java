package cn.tedu.thread;

/**
 * 线程提供的join方法可以协调线程进入同步运行状态
 * 多线程本身就是并发运行的,所以本就是一种异步的状态
 * 而异步运行就表示: 多条线程各自执行各自的
 * 而同步运行则表示: 多条线程在运行时存在了先后的顺序
 */
public class JoinDemo {
    static boolean isFinish = false;//表示图片默认是未下载完

    public static void main(String[] args) {
        Thread download = new Thread() {
            public void run() {
                System.out.println("down: 开始下载图片...");
                for (int i = 1; i <= 100; i++) {
                    System.out.println("已下载" + i + "%");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("down: 图片下载完毕!!!");
                isFinish = true;//表示图片此时已下载完毕
            }
        };
        Thread show = new Thread() {
            @Override
            public void run() {
                System.out.println("show: 开始显示文字...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("show: 显示文字完毕!!!");
                System.out.println("show: 开始显示图片...");
                //先等待下载线程运行结束后,再继续执行
                try {
                    /*
                     * 是show线程进入到阻塞状态,直到download执行完毕时,阻塞状态结束
                     * 理解为插队,show线程让download线程插队
                     * join方法和sleep方法的区别:
                     * ①sleep方法,可以让线程阻塞指定的时间
                     * ②join方法,可以让线程阻塞,但是时间不确定,具体得看插队的线程执行的时间
                     */
                    System.out.println("图片此时没下载完,等待下载ing...");
                    download.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //开始显示图片之前,判断图片下载状态
                if (isFinish == false) {
                    throw new RuntimeException("图片加载失败!!");
                }
                System.out.println("show: 图片显示完毕!!!");
            }
        };
        download.start();
        show.start();
    }
}
