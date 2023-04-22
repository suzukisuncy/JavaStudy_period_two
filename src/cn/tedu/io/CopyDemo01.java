package cn.tedu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 通过此案例学习单字节复制文件
 */
public class CopyDemo01 {
    public static void main(String[] args) throws IOException {
        //创建文件字节输入流,用于读取目标文件字节数据
        FileInputStream fis = new FileInputStream("./demo/ZhenDe.jpg");
        //创建文件字节输出流,用于写出数据到复制文件
        FileOutputStream fos = new FileOutputStream("./demo/ZhenDe_01.jpg");
        int data;
        long start = System.currentTimeMillis();
        //循环读取目标文件中的字节数据
        while ((data = fis.read()) != -1) {
            //将本次读取的字节数据,写出到复制文件中
            fos.write(data);
        }
        long end = System.currentTimeMillis();
        System.out.println("复制该图片共耗时:" + (end - start) + "毫秒!");
        //释放资源
        fis.close();
        fos.close();
    }
}
