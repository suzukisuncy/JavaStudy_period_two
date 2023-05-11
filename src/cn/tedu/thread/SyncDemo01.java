package cn.tedu.thread;

/**
 * 多线程并发安全问题
 * 当多个线程并发操作同一临界资源时,由于线程的切换存在不可确定性,这就会导致线程的切换顺序出现混乱,而产生各种的逻辑错误
 * 而临界资源就是指操作资源的完整过程应该同一时刻只能由单线程执行
 */
public class SyncDemo01 {
    public static void main(String[] args) {
        Table table = new Table();
        Thread t1 = new Thread("白露") {
            @Override
            public void run() {
                while (true) {
                    int bean = table.getBean();
                    System.out.println(getName() + "抢一颗豆子,此时豆子数量为:" + (bean - 1));
                }
            }
        };
        Thread t2 = new Thread("青雀") {
            @Override
            public void run() {
                while (true) {
                    int bean = table.getBean();
                    System.out.println(getName() + "抢一颗豆子,此时豆子数量为:" + (bean - 1));
                }
            }
        };
        t1.start();
        t2.start();
    }
}

class Table {
    private int beans = 20;//桌子上有20颗豆子

    public int getBean() {
        if (beans == 0) {
            throw new RuntimeException("桌子上已经没有豆子了!!!");
        }
        return beans--;
    }
}