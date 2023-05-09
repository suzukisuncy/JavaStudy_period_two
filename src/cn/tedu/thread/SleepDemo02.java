package cn.tedu.thread;

import java.util.Scanner;

/**
 * 利用sleep写一个倒计时的程序
 */
public class SleepDemo02 {
    public static void main(String[] args) {
        System.out.println("程序开始了!");
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入要倒计时的时间!");
            int time = scanner.nextInt();
            //变量.forr 生成逆向for循环
            for (int i = time; i > 0; i--) {
                System.out.println(i);
                //没循环一次,睡眠阻塞1秒
                Thread.sleep(1000);
            }
            System.out.println("时间到!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("程序结束了!");
    }
}
