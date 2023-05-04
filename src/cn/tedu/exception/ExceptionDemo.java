package cn.tedu.exception;

import java.io.FileInputStream;

/**
 * @author 老安
 * @data 2023-05-04 19:51
 */
public class ExceptionDemo {
    public static void main(String[] args) {
        int a = 1;
        //①编译期异常 就是常见的红线报错,必须立即解决,否则程序不能执行
        //a = "hello";
        //FileInputStream fis = new FileInputStream("");
        //②运行时异常 编译期不会报错,程序是可以运行的,但是运行期间会在控制台打印异常信息
        //异常信息包括三部分: 异常类型 异常提示 异常所在行号
        a = 1 / 0;
        System.out.println("程序结束");
    }
}
