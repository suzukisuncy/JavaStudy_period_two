package cn.tedu.exception;

/**
 * finally块
 * finally是异常处理机制中的最后一块,可以直接跟在try语句块最后一个catch之后
 */
public class FinallyDemo {
    public static void main(String[] args) {
        System.out.println("程序开始了!");
        try {
            String str = "";
            System.out.println(str.length());
            //程序遇到return,直接跳出当前方法
            return;
        } catch (Exception e) {
            System.out.println("出现了一个错误");
        } finally {//一定会被执行
            System.out.println("必须要执行的内容~~");
        }
        System.out.println("程序结束了!");
    }
}
