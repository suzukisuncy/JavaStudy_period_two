package cn.tedu.exception;

/**
 * 通过此案例学习异常捕获,异常可以进行捕获父类异常进行兜底
 */
public class TryCatchDemo04 {
    public static void main(String[] args) {
        System.out.println("程序开始了!");
        try {
            String str = "a";
            System.out.println(str.length());
            System.out.println(str.charAt(0));
            //将字符串转换为整数,但是前提是字符串必须由数字组成
            System.out.println(Integer.parseInt(str));
            System.out.println("try中代码执行结束了!");
        } catch (NullPointerException | StringIndexOutOfBoundsException e) {//合并捕获
            System.out.println("出现空指针异常或者字符串下标越界异常!!!");
        } catch (Exception e) {//捕获父类异常 工作中,一般情况下,依旧是出现一个异常写一个catch,不建议直接捕获Exception
            System.out.println("我也不知道出现什么异常,但是就是出现了异常!!!");
        }
        System.out.println("程序结束了!");
    }
}
