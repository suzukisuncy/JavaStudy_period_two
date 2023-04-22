package cn.tedu.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 通过此案例学习多字节复制文件时,文件大小保持一致
 * 由于块读时,最后一次如果不满足data数组的长度,则会导致最后一次写入数据时,
 * 会将多余的上次读取的残余数据也一同写入,导致复制的文件偏大
 */
public class CopyDemo03 {
    public static void main(String[] args) throws IOException {
        //创建文件字节输入流,用于读取目标文件字节数据
        FileInputStream fis = new FileInputStream("./demo/ZhenDe.jpg");
        FileOutputStream fos = new FileOutputStream("./demo/ZhenDe_03.jpg");
        long start = System.currentTimeMillis();
        byte[] data = new byte[10 * 1024];
        int len;//定义len记录本次读取的字节量
        while ((len = fis.read(data)) != -1) {
            //根据len,保证本次写入的字节的个数为实际读取的个数
            fos.write(data, 0, len);
        }
        long end = System.currentTimeMillis();
        System.out.println("复制该图片共耗时:" + (end - start) + "毫秒!");
        fis.close();
        fos.close();
    }
}
