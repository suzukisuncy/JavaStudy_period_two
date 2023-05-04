package cn.tedu.exception;

/**
 * 通过此案例学习异常捕获,了解基础语法结构
 * try{
 * 可能出现异常的代码;
 * }catch(异常类型 变量名){
 * 异常处理代码;
 * //当try中的代码出现了异常并且这个异常能和catch中的异常类型匹配上, 才会执行catch
 * //反之, 如果出现的异常和catch中的异常类型不匹配, 就不会执行catch
 * }
 */
public class TryCatchDemo01 {
    public static void main(String[] args) {
        System.out.println("程序开始了!");
        //①将可能发生异常的代码放在try中
        try {
            String str = null;
            //在java中,null表示什么也没有,是不能调用方法的,所以虚拟机会提示空指针异常
            System.out.println(str.length());
        } catch (NullPointerException e) {//②通过catch关键字捕获try中代码出现的异常类型
            //③如果try中出现空指针异常,会捕获,并执行该catch中的内容
            System.out.println("服务器忙,请稍后重试!!!");
            //④将捕获的异常信息打印出来,一般是给程序员观看解决问题的
            e.printStackTrace();
        }
        System.out.println("程序结束了!");
    }
}
