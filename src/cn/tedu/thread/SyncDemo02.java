package cn.tedu.thread;

/**
 * 同步块的应用
 * 有效的缩小同步范围,并可以在保证并发安全的情况下,尽可能的提高并发效率
 */
public class SyncDemo02 {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Thread t1 = new Thread("缪铖航") {
            @Override
            public void run() {
                shop.buy();
            }
        };
        Thread t2 = new Thread("薛宏举") {
            @Override
            public void run() {
                shop.buy();
            }
        };
        t1.start();
        t2.start();
    }
}

class Shop {
    public void buy() {
        try {
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + ": 正在挑衣服...");
            Thread.sleep(5000);
            /*
             * 同步块在指定同步监视器对象时,可以是任何引用类型实例,
             * 只要保证多个执行该代码片段的线程看到的这个对象是"同一个"即可
             * 此处使用this this代表当前实例化对象的引用,也就是调用buy方法的实例对象
             * t1线程中调用buy方法时,this指向的是shop实例,而t2线程也是shop实例,所以此处可以使用this
             */
            synchronized (this) {
                System.out.println(t.getName() + ": 正在试衣服...");
                Thread.sleep(5000);
            }
            System.out.println(t.getName() + ": 结账离开!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}