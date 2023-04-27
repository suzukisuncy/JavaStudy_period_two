package cn.tedu.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 学习字符流时,必须要掌握转换流的内容,原因:
 * 字节流和字符流不能直接相连,需要转换流做协调,
 * 并且转换流具备以下功能:
 * ①在流链接中,衔接其他的高级字符流和下面的字节流
 * ②负责将字符与对应的字节按照指定的字符集进行自动转换方便读写
 */
public class OSWDemo {
    public static void main(String[] args) throws Exception {
        //低级的文件字节输出流
        FileOutputStream fos = new FileOutputStream("./demo/osw.txt");
        //高级的输出字符转换流,指定编码
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        //利用输出字符流,自动将写出的字符串按照编码写出
        osw.write("鹅鹅鹅");
        osw.write("曲颈向刀割");
        osw.write("拔毛烧开水");
        osw.write("铁锅炖大鹅");
        System.out.println("写出完毕!");
        osw.close();
    }
}
