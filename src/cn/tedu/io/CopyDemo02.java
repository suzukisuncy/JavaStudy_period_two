package cn.tedu.io;

import java.io.*;

/**
 * 通过此案例学习多字节复制文件
 * 单字节复制文件,由于需要频繁的内存和硬盘进行交互,导致整体效率极低,
 * 所以可以提高每次交互时读写的字节数量,减少实际交互的次数,进而提高读写效率,
 * 而一组字节一组字节的读写称为: 块读写形式
 */
public class CopyDemo02 {
    public static void main(String[] args) throws IOException {
        //创建文件字节输入流,用于读取目标文件字节数据
        FileInputStream fis = new FileInputStream("./demo/ZhenDe.jpg");
        //创建文件字节输出流,用于写出数据到复制文件
        FileOutputStream fos = new FileOutputStream("./demo/ZhenDe_02.jpg");
        long start = System.currentTimeMillis();
        //使用块读的方式复制图片
        //1kb=1024byte
        byte[] data = new byte[10 * 1024];//10kb的字节数组
        //每次读取目标文件10kb的字节数据
        while (fis.read(data) != -1) {
            //每次读取的字节数据,都会存储到data中,所以直接将data写出即可
            fos.write(data);
        }
        long end = System.currentTimeMillis();
        System.out.println("复制该图片共耗时:" + (end - start) + "毫秒!");
        //释放资源
        fis.close();
        fos.close();
    }
}
