package cn.tedu.exception;

/**
 * 通过此案例学习异常捕获,异常可以合并捕获
 */
public class TryCatchDemo03 {
    public static void main(String[] args) {
        System.out.println("程序开始了!");
        try {
            String str = null;
            System.out.println(str.length());
            System.out.println(str.charAt(0));
            System.out.println("try中代码执行结束了!");
        } catch (NullPointerException|StringIndexOutOfBoundsException e) {//合并捕获
            System.out.println("出现空指针异常或者字符串下标越界异常!!!");
        }
        System.out.println("程序结束了!");
    }
}
