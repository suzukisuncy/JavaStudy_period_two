package cn.tedu.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * 缓冲字符流
 * PrintWriter是实际开发中使用的缓冲字符输出流
 * 功能:
 * ①可以提高写出字符的效率
 * ②可以按行写出字符串
 * ③可以自动行刷新
 */
public class PWDemo {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("./demo/pw.txt");
        //按行写出字符串
        pw.println("无竹令人俗,");
        pw.println("无肉使人瘦.");
        pw.println("不俗又不瘦,");
        pw.println("竹笋焖猪肉.");
        pw.println("出自--<苏轼的竹笋焖猪肉>");
        pw.close();
    }
}
