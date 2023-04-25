package cn.tedu.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 此案例来学习一次性从文件中读取字符串
 */
public class ReadStringDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./demo/string.txt");
        /*
         * int available()
         * 预估本次使用输入流读取该文件的字节量,可以利用它表示文件长度
         */
        byte[] data = new byte[fis.available()];
        //一次性读取data大小的内容(文件中所有的数据)
        fis.read(data);
        //利用String的构造方法将data数组按照指定的编码表还原为字符串
        String str = new String(data, StandardCharsets.UTF_8);
        System.out.println(str);
        fis.close();
    }
}
