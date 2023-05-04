package cn.tedu.exception;

/**
 * 通过此案例学习异常捕获,异常可以捕获多个
 */
public class TryCatchDemo02 {
    public static void main(String[] args) {
        System.out.println("程序开始了!");
        try {
            String str = "";
            System.out.println(str.length());
            //char charAt(int index) 获取字符串中指定下标的字符
            System.out.println(str.charAt(0));
            //try中代码一旦发生异常,异常发生位置之后的代码不会被执行
            System.out.println("try中代码执行结束了!");
        } catch (NullPointerException e) {
            System.out.println("出现空指针异常!!!");
        } catch (StringIndexOutOfBoundsException e) {//catch可以定义多个
            System.out.println("出现了字符串下标越界异常!!!");
        }
        System.out.println("程序结束了!");
    }
}
