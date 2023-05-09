package cn.tedu.thread;

/**
 * 守护线程
 * java将线程分为两类,用户线程和守护线程,也称为前台线程和后台线程
 * 守护线程和用户线程区别不大,守护线程就是用户线程通过调用setDaemon(true)方法转变而来,
 * 而用户线程就是普通线程
 * 而两者最主要的区别在于当一个java进程中所有的用户线程都结束时,进程就会结束,此时会将所有的守护线程杀死
 */
public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread rose = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("rose:Let me die!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("rose:Ah, ah, ah, ah, ah, ah, ah....");
                System.out.println("噗通!咕噜噜噜噜");
            }
        };
        Thread jack = new Thread() {
            public void run() {
                while (true) {
                    System.out.println("jack:My darling,you jump!i jump!!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        rose.start();
        //在jack线程启动之前,先将其设置为守护线程
        jack.setDaemon(true);
        jack.start();
        while (true);//程序会一直卡在此处,导致main永远不会结束
    }
}
