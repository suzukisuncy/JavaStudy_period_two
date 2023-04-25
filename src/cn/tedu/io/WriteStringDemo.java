package cn.tedu.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 通过此案例向文件中写入字符串
 */
public class WriteStringDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("./demo/string.txt");
        String line = "鹅鹅鹅,曲颈向刀割,拔毛烧开水,铁锅炖大鹅!";
        /*
         * 在使用IO写入中文时,最好指定字符集,防止乱码
         * ASCII: 最早的编码表之一,它包含了128个字符,包括英文,数字,标点符号和一些特殊字符,不包含中文
         * Unicode: 支持超过130000个字符,包括世界各地的语言和符号
         * UTF-8: 是Unicode的传输格式
         */
        fos.write(line.getBytes(StandardCharsets.UTF_8));
        fos.close();
    }
}
