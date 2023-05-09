package cn.tedu.thread;

/**
 * 该案例学习线程的优先级
 * 线程的优先级分为10级,分别对应整数1-10,其中1是最低优先级,10是最高优先级,所有线程如果不设置
 * 优先级,则默认优先级为5
 */
public class PriorityDemo {
    public static void main(String[] args) {
        Thread min = new Thread() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println("我是min");
                }
            }
        };
        Thread norm = new Thread() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println("我是norm");
                }
            }
        };
        Thread max = new Thread() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println("我是max");
                }
            }
        };
        min.setPriority(1);//设置最低的优先级
        norm.setPriority(5);//设置中度的优先级(不设置也是5)
        max.setPriority(10);//设置最高的优先级
        min.start();
        norm.start();
        max.start();
    }
}
