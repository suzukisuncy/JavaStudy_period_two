package cn.tedu.thread;

/**
 * 同步块的应用
 * 有效的缩小同步范围,并可以在保证并发安全的情况下,尽可能的提高并发效率
 */
public class SyncDemo02 {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Thread t1 = new Thread("缪铖航"){
            @Override
            public void run() {
                shop.buy();
            }
        };
        Thread t2 = new Thread("薛宏举"){
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
            System.out.println(t.getName() + ": 正在试衣服...");
            Thread.sleep(5000);
            System.out.println(t.getName() + ": 结账离开!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}