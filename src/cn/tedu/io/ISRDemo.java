package cn.tedu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 使用输入字符转换流读取文本内容
 */
public class ISRDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./demo/osw.txt");
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        int d;
        //使用字符流读取内容时,是一个字符一个字符读取,比如一次性可以将"鹅"这个字读进来
        //而使用字节流读取内容,是一个字节一个字节读取,在UTF-8中,一个中文由三个字节组成,所以读取三次才可以将中文"鹅读取出来
        while ((d = isr.read()) != -1) {
            System.out.print((char) d);
        }
        isr.close();
    }
}
