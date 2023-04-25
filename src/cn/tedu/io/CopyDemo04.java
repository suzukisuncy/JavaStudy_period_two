package cn.tedu.io;

import java.io.*;

/**
 * 此案例使用缓冲流高效复制文件
 */
public class CopyDemo04 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./demo/ZhenDe.jpg");
        //创建高级流,缓冲字节输入流,绑定低级流
        BufferedInputStream bis = new BufferedInputStream(fis);
        FileOutputStream fos = new FileOutputStream("./demo/ZhenDe_04.jpg");
        //创建高级流,缓冲字节输出流,绑定低级流
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        long start = System.currentTimeMillis();
        int data;
        //循环通过高级流单字节读取数据
        while ((data = bis.read()) != -1) {
            //通过高级流写出该字节数据
            bos.write(data);
        }
        long end = System.currentTimeMillis();
        System.out.println("复制该图片共耗时:" + (end - start) + "毫秒!");
        //关闭资源(关闭高级流,会将所连的低级流也一同关闭)
        bis.close();
        bos.close();
    }
}
