package cn.tedu.exception;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 释放IO流资源时,可以使用JDK7时推出的自动关闭特性
 */
public class IOCloseDemo02 {
    public static void main(String[] args) {
        //①在try关键字后,添加"()",将IO流对象的声明写入到这部分
        try (
                FileOutputStream fos = new FileOutputStream("./demo/fos.txt");
        ) {
            //②关于流的使用代码则放在"{}"中
            fos.write(1);
            //③当try代码块结束,在"()"声明的流会自动释放资源
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
